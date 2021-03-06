package endpoints;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.TaskStatusChange;
import beans.Usuario;
import beans.jsonEstadoMP;
import beans.api.DataMovimiento;
import beans.api.Order;
import beans.api.ResponseOrder;
import beans.api.ResponseOrderFunctions;
import beans.api.wsResponse;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.MovimientosC;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.marketplaces.fenicio.FenicioAPIorden;
import integraciones.marketplaces.objetos.CanalMarketPlace;
import integraciones.marketplaces.objetos.marketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

@Path("/another")
public class Pedidos {

	@POST
	@Path("/users")

	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// public String createUser(@FormParam("name") String name) throws IOException
	public String createUser(String data, @QueryParam("token") String a) {
		// System.out.println(data);

		String mensaje = "";
		String json = "";
		int codResp = 0;
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);

		if (u.getNick() == null) {
			mensaje = "access_token invalido";

		}

		MovimientosC m = new MovimientosC();
		m = m.desempaquetar(data);

		System.out.println(m.getMovimientos().size());
		/*
		 * mensaje y catch faltan
		 */

		json = "{" + "\"mensaje\": \"" + mensaje + "\"," + "\"cod_respuesta\":\"" + codResp + "\"" + "}";
		System.out.println(a);

		return json;
	}

	@GET
	@Path("/getFenicioOrder")

	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// public String createUser(@FormParam("name") String name) throws IOException
	public String getFenicioOrder(String data, @QueryParam("token") String a, @QueryParam("orden") String o,
			@QueryParam("canal") String c) {
		// System.out.println(data);

		String mensaje = "";
		String json = "";
		int codResp = 0;
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);

		if (u.getNick() == null) {
			mensaje = "access_token invalido";

		}
		LogicaAPI l = new LogicaAPI();
		String host = l.darHostFenicioAPI(u.getIdEmpresa(), c);

		String servicio = host + "/API_V1/ordenes/" + o;
		Call_WS_APIENCUENTRA call = new Call_WS_APIENCUENTRA();

		String salida = call.callWSGET(servicio, false);

		Gson gson = new Gson();

		System.out.println(salida);

		FenicioAPIorden or = gson.fromJson(salida, FenicioAPIorden.class);

		EncuentraPedido pe = new EncuentraPedido(or.getOrden());

		return gson.toJson(pe);

	}

	@POST
	@Path("/putTracking")

	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// public String createUser(@FormParam("name") String name) throws IOException
	public String putTracking(String data, @QueryParam("token") String a) {
		// System.out.println(data);

		String mensaje = "";
		String json = "";
		Gson gson = new Gson();
		ResponseOrderFunctions response = new ResponseOrderFunctions();
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);

		if (u.getNick() == null) {
			response.setMessage("access_token invalido");
		} else {
			System.out.println(data);
			Order order = gson.fromJson(data, Order.class);

			LogicaAPI.persistir(
					"insert into producteca_ecom_envios (OPERATIONID, TRACKINGNUMBER, COURIER, URLTRACKING) values "
							+ "(" + order.getId() + ",'" + order.getTracking() + "'," + order.getCourier() + ",'"
							+ order.getUrlTracking() + "')");

			response.setMessage("ok");
			response.setOrders(new ArrayList<>());
			response.getOrders().add(new ResponseOrder(order.getId(), "ok"));
		}

		json = gson.toJson(response);

		return json;
	}

	@POST
	@Path("/updateOrder")

	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// public String createUser(@FormParam("name") String name) throws IOException
	public String updateOrder(String data, @QueryParam("token") String a) {
		String json = "";
		Gson gson = new Gson();
		ResponseOrderFunctions response = new ResponseOrderFunctions();
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);

		if (u.getNick() == null) {
			response.setMessage("access_token invalido");
		} else {
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);
			Order order = gson.fromJson(data, Order.class);

			LogicaAPI l = new LogicaAPI();
			OrdenVenta ov = l.darOrdenVenta_(idEmpresa, order.getId());

			Map<String, List<DataIDDescripcion>> hasMovs = new HashMap<>();
			Map<String, OrdenVentaLinea> hasLineas = new HashMap<>();
			Double precioOrden = 0.0;
			double descuento = 0.0;
			for (OrdenVentaLinea ovl : ov.getOrdenVentaLineas()) { // PONGO EN HASH ARTICULOS DE LA ORDEN
				if(ovl.getIdArticulo().substring(0, 3).equalsIgnoreCase("CMB")) {
					List<DataIDDescripcion> articulosCombo = l.darArticulosCombo(ovl.getIdArticulo(), idEmpresa);
					for(DataIDDescripcion d : articulosCombo) {
						hasLineas.put(d.getDescripcion(), ovl);
					}
				}
				else {
					hasLineas.put(ovl.getIdArticulo(), ovl);
				}
				precioOrden += ovl.getPrecioNeto();
				descuento = (ovl.getDescuento() * 100) / ovl.getPrecioImp();
			}
			precioOrden = Double.parseDouble(Math.round(precioOrden) + "");

			List<DataIDDescripcion> lineasRegalo = new ArrayList<>();

//			AGREGAR DEVOLUCIONES
			
			for (DataMovimiento dm : order.getDevoluciones()) {
				
				if (dm.getIdArticulo().substring(0, 3).equalsIgnoreCase("CMB")) {
//					Si es un combo, obtengo los articulos del combo
					List<DataIDDescripcion> articulosCombo = l.darArticulosCombo(dm.getIdArticulo(), idEmpresa);
//					Itero sobre los art?culos del combo y por cada
//					entrada, agrego el art?culo y la cantidad a las devoluciones
					for (DataIDDescripcion did : articulosCombo) {
						try {
							DataIDDescripcion d;
							OrdenVentaLinea ord = hasLineas.get(did.getDescripcion());
							if (ord != null) { // QUITO ARTICULOS A DEVOLVER
								precioOrden -= Double.parseDouble(did.getDescripcionB());
								if (ord.getCantidad() == did.getId()) {
									hasLineas.remove(did.getDescripcion());
									d = new DataIDDescripcion(ord.getLinea(), ord.getCantidadRegalo());
								} else {
									int cantidad = ord.getCantidad() - did.getId();
									ord.setCantidad(cantidad);
									int regalo = 0;
									if (ord.getCantidadRegalo() > 1) {
										regalo = ord.getCantidadRegalo() - did.getId();
									}
									d = new DataIDDescripcion(-1, ord.getCantidadRegalo() - regalo);
									ord.setCantidadRegalo(regalo);
								}
								lineasRegalo.add(d);
								if (hasMovs.get(dm.getOrigen() + "-" + dm.getDestino()) == null) { // DEVOLUCIONES A ERP
									List<DataIDDescripcion> listaMovs = new ArrayList<>();
									listaMovs.add(new DataIDDescripcion(did.getId(), did.getDescripcion()));
									
									hasMovs.put(dm.getOrigen() + "-" + dm.getDestino(), listaMovs);
								} else {
									hasMovs.get(dm.getOrigen() + "-" + dm.getDestino())
											.add(new DataIDDescripcion(did.getId(), did.getDescripcion()));
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} 
				else {
//					Si no es un combo, agrego a las devoluciones el art?culo
					try {
						DataIDDescripcion d;
						OrdenVentaLinea ord = hasLineas.get(dm.getIdArticulo());
						if (ord != null) { // QUITO ARTICULOS A DEVOLVER
							precioOrden -= ord.getPrecioNeto();
							if (ord.getCantidad() == dm.getCantidad()) {
								hasLineas.remove(dm.getIdArticulo());
								d = new DataIDDescripcion(ord.getLinea(), ord.getCantidadRegalo());
							} else {
								int cantidad = ord.getCantidad() - dm.getCantidad();
								ord.setCantidad(cantidad);
								int regalo = 0;
								if (ord.getCantidadRegalo() > 1) {
									regalo = ord.getCantidadRegalo() - dm.getCantidad();
								}
								d = new DataIDDescripcion(-1, ord.getCantidadRegalo() - regalo);
								ord.setCantidadRegalo(regalo);
							}

							lineasRegalo.add(d);

							if (hasMovs.get(dm.getOrigen() + "-" + dm.getDestino()) == null) { // DEVOLUCIONES A ERP
								List<DataIDDescripcion> listaMovs = new ArrayList<>();
								listaMovs.add(new DataIDDescripcion(dm.getCantidad(), dm.getIdArticulo()));
								hasMovs.put(dm.getOrigen() + "-" + dm.getDestino(), listaMovs);
							} else {
								hasMovs.get(dm.getOrigen() + "-" + dm.getDestino())
										.add(new DataIDDescripcion(dm.getCantidad(), dm.getIdArticulo()));
							}

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			double precioArt = 0.0;
			double descuentoItem = 0.0;
			int linea = 0;
			int regalo = 0;
			int iterador = 0;
			
//			AGREGAR REMITOS
			
			for (DataMovimiento r : order.getRemitos()) {
				if (r.getIdArticulo().substring(0, 3).equalsIgnoreCase("CMB")) {
					List<DataIDDescripcion> articulosCombo = l.darArticulosCombo(r.getIdArticulo(), idEmpresa);
					for (DataIDDescripcion did : articulosCombo) {
						try {
							try {
								precioArt = Double.parseDouble(did.getDescripcionB());
								descuentoItem = (precioArt * descuento / 100);
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								linea = (lineasRegalo.get(iterador)!=null)? lineasRegalo.get(iterador).getId() : 0;
								regalo = (lineasRegalo.get(iterador)!=null)? lineasRegalo.get(iterador).getIdB() : 0;

								if (linea == -1) {
									linea = hasLineas.size() + 1;
								}
							} catch (Exception e) {
//								e.printStackTrace();
							}

							OrdenVentaLinea ovl = new OrdenVentaLinea(precioArt, r.getCantidad() + "",
									did.getDescripcion(), regalo, linea, descuentoItem, did.getDescripcionC());
							hasLineas.put(did.getDescripcion(), ovl);
							precioOrden += ovl.getPrecioNeto();

							if (hasMovs.get(r.getOrigen() + "-" + r.getDestino()) == null) { // REMITOS A ERP
								List<DataIDDescripcion> listaMovs = new ArrayList<>();
								listaMovs.add(new DataIDDescripcion(r.getCantidad(), did.getDescripcion()));
								hasMovs.put(r.getOrigen() + "-" + r.getDestino(), listaMovs);
							} else {
								hasMovs.get(r.getOrigen() + "-" + r.getDestino())
										.add(new DataIDDescripcion(r.getCantidad(), did.getDescripcion()));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						iterador++;
					}
				} else {
					// AGREGO ARTICULOS A MOVER
					try {
						// ir a buscar precio de articulo
						try {
							precioArt = LogicaAPI.darArtPrecio_(r.getIdArticulo(), idEmpresa);
							descuentoItem = (precioArt * descuento / 100);
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							linea = (lineasRegalo.get(iterador)!=null)? lineasRegalo.get(iterador).getId() : 0;
							regalo = (lineasRegalo.get(iterador)!=null)? lineasRegalo.get(iterador).getIdB() : 0;

							if (linea == -1) {
								linea = hasLineas.size() + 1;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						OrdenVentaLinea ovl = new OrdenVentaLinea(precioArt, r.getCantidad() + "", r.getIdArticulo(),
								regalo, linea, descuentoItem, r.getDescripcion());
						hasLineas.put(r.getIdArticulo(), ovl);
						precioOrden += ovl.getPrecioNeto();

						if (hasMovs.get(r.getOrigen() + "-" + r.getDestino()) == null) { // REMITOS A ERP
							List<DataIDDescripcion> listaMovs = new ArrayList<>();
							listaMovs.add(new DataIDDescripcion(r.getCantidad(), r.getIdArticulo()));
							hasMovs.put(r.getOrigen() + "-" + r.getDestino(), listaMovs);
						} else {
							hasMovs.get(r.getOrigen() + "-" + r.getDestino())
									.add(new DataIDDescripcion(r.getCantidad(), r.getIdArticulo()));
						}
					} catch (Exception e) {
//						e.printStackTrace();
					}
					iterador++;
				}
			}

			precioOrden = Double.parseDouble(Math.round(precioOrden) + "");

			List<OrdenVentaLinea> nuevasLineas = new ArrayList<>(hasLineas.values());
			ov.setOrdenVentaLineas(nuevasLineas);
			ov.setImportePago(precioOrden);

			ov.update(idEmpresa); // ACTUALIZO ORDEN

			int usu = order.getRemitos().get(0).getUsuario();
			for (Map.Entry<String, List<DataIDDescripcion>> entry : hasMovs.entrySet()) {
				try {
					int origen = Integer.parseInt(entry.getKey().split("-")[0]);
					int destino = Integer.parseInt(entry.getKey().split("-")[1]);

					l.RegistrarMovimientoStock(origen, destino, usu, entry.getValue(), idEmpresa,
							new Long(ov.getIdCarrito()), 0, 0, false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			response.setMessage("ok");
			response.setOrders(new ArrayList<>());
			response.getOrders().add(new ResponseOrder(order.getId(), "ok"));
		}

		json = gson.toJson(response);

		return json;
	}

	@POST
	@Path("/CheckInOrder")

	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// public String createUser(@FormParam("name") String name) throws IOException
	public String CheckInOrder(@QueryParam("order") String order, @QueryParam("printer") String printer,
			@QueryParam("token") String a) {

		String json = "";
		Gson gson = new Gson();
		ResponseOrderFunctions response = new ResponseOrderFunctions();
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);

		int idPrinter = 0;
		try {
			idPrinter = Integer.parseInt(printer);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (u.getNick() == null) {
			response.setMessage("access_token invalido");
		} else {
			System.out.println(order);
			Long idOrder = new Long(order);
			int idEmpresa = u.getIdEmpresa();
			LogicaAPI l = new LogicaAPI();
			l.CheckInOrder(idOrder, idPrinter, idEmpresa);

			response.setMessage("ok");
			response.setOrders(new ArrayList<>());
			response.getOrders().add(new ResponseOrder(idOrder, "ok"));
		}

		json = gson.toJson(response);

		return json;
	}

	@POST
	@Path("/putCambioEstadoMarketPlace")

	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	// public String createUser(@FormParam("name") String name) throws IOException
	public String putCambioEstadoMarketPlace(String data, @QueryParam("token") String token) {
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");
		boolean cambioEstado = false;
		Gson gson = new Gson();
		String json = "";

		try {
			Usuario u = LogicaAPI.loginEncuentraAPI2(token);

			if (u != null && u.getIdEmpresa() != 0) {
				int idEmpresa = u.getIdEmpresa();

				List<jsonEstadoMP> estadosMP = gson.fromJson(data, new TypeToken<List<jsonEstadoMP>>() {
				}.getType());
				cambioEstado = LogicaAPI.putColaEstadoMarketPlace(estadosMP);

				if (!cambioEstado) {
					response.setCode("500");
					response.setDescription("No se pudo poner en cola el cambio de estado");
				} else {
					if (!estadosMP.isEmpty()) {
						List<marketPlace> marketPlaces = TaskStatusChange.darInstanciasMarketPlaces(idEmpresa);
						for (marketPlace mp : marketPlaces) {
							/*Enumeration<Integer> canales = mp.getCanales().keys();
							while (canales.hasMoreElements()) {
								int c = canales.nextElement();
								if (c == estadosMP.get(0).getCanal()) {
									TaskStatusChange.StatusChange(mp, estadosMP, c);
									break;
								}
							}*/

							for(Map.Entry<Integer, CanalMarketPlace> pair : mp.getCanales().entrySet()) {
								int c = pair.getKey();
								if (c == estadosMP.get(0).getCanal()) {
									TaskStatusChange.StatusChange(mp, estadosMP, c);
									break;
								}
							}
							
						}
					}

				}
			} else {
				response.setCode("500");
				response.setDescription("Token incorrecto");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}

		json = gson.toJson(response);
		return json;
	}

}
