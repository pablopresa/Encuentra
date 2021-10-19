package integraciones.marketplaces.forus;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;

import beans.Fecha;
import beans.api.Utilidades;
import beans.api.json.Cliente;
import beans.api.json.Credenciales;
import beans.api.json.RespuestaMIR;
import beans.api.json.SendMail;
import beans.api.json.Shipping;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.Compras;
import beans.encuentra.CredencialesPEYA;
import beans.encuentra.DataArticuloEcommerceVerifR;
import beans.encuentra.DataEcommerce_canales_envio;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Items;
import beans.encuentra.UESEnvio;
import endpoints.Couriers;
import endpoints.MailSpooler;
import integraciones.marketplaces.objetos.marketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;


public class GetPedidosForusVS 
{
	public GetPedidosForusVS() 
	{
		
	}
	
	public Map<Long, EncuentraPedido> getPedidos(int idEmpresa, Map<String, DataIDDescripcion> destinoPedidos, int idCanal,List<DataIDDescripcion> depositosPick) 
	{
		LogicaAPI logica = new LogicaAPI();	
		
		Map<Long, EncuentraPedido> pedidosHT = new HashMap<>();
		
		List<Compras> compras = logica.sincroPedidosWebForusUY(idEmpresa, destinoPedidos, depositosPick, idCanal);
	
		System.out.println(compras.size());	
			for (Compras c : compras) 
			{
				
				if(c.getCompra().getItems().isEmpty())
				{	
					LogicaAPI.persistir("insert into aaatemporal (id,IdEmpresa) values ("+c.getCompra().getId()+","+idEmpresa+")");				
				}
				if(!c.getCompra().getItems().isEmpty())
				{			
					System.out.println(c.getCompra().getId());
					
					EncuentraPedido p = new EncuentraPedido();
					p.setDescripcion(c.getCliente().getNombre()+" "+c.getCliente().getApellido());
					
					int cantidad=0;
					Double descuento=0.0;
					
					p.setTicketNumber("");
					p.setEstado(c.getCompra().getEstado());
					p.setUrlEtiqueta(c.getEtiqueta());
					List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
				
					p.setIdPedido(Long.parseLong(c.getCompra().getId()));
					p.setSubpedido(p.getIdPedido()+"");
					p.setIdFenicio(c.getCompra().getIdVenta());
					
					Double importeTotal = 0.0; 
					Double importetotalOV = 0.0;
					
					if(Double.parseDouble(c.getCompra().getMontoEnvio()) == Double.parseDouble(c.getCompra().getImporte())){
						importetotalOV = Double.parseDouble(c.getCompra().getMontoEnvio());
					}
					else{
						importetotalOV = Double.parseDouble(c.getCompra().getImporte());
					}
					
					for (Items it : c.getCompra().getItems()) 
					{
						Double importe = Double.parseDouble(it.getImporte());
						int cantidadItem = Integer.parseInt(it.getCant()); 
						
							cantidad+=cantidadItem;
							EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
													
							art.setArticulo(it.getSku());
							art.setCantidad(cantidadItem);
							art.setProcesada(0);
							art.setOrigen(Integer.parseInt(it.getOrigen()));
							pedidos.add(art);
							importeTotal+=importe;
								
								
					}
					
					p.setCantidad(cantidad);
					p.setDescuento(descuento);
					p.setArticulosPedido(pedidos);
					p.setSucursalPick(c.getCompra().getSucursal());
					p.setArticulosPedidoReq(new ArrayList<>());
					
					
					p.setFormaPago(c.getCompra().getMetodoPago());
					
					try
					{
						//p.setCanalAnaloga(canalesHT.get(c.getCompra().getIdCanal()));
						p.setCanalAnaloga(Integer.parseInt(c.getCompra().getIdCanal()));
					}
					catch (Exception e) 
					{
						p.setCanalAnaloga(0);
					}
					
					if(p.getCanalAnaloga()==7){
						System.out.println("");
					}
					
					
					p.setUrlEtiqueta("");
					p.setFecha(c.getCompra().getFecha());
					p.setPrecio(importetotalOV);
					
					//
					if(p.getSucursalPick()!=null && !p.getSucursalPick().equals(""))
					{
						
						if(!c.getCompra().iscNc())
						{
							p.setShippingType(new DataIDDescripcion(2,""));
						}
						else
						{
							p.setShippingType(new DataIDDescripcion(3,""));
						}
					}
					else {
						p.setShippingType(new DataIDDescripcion(1,""));
					}
					
					if(p.getCanalAnaloga()==idCanal)
					{
						pedidosHT.put(p.getIdPedido(), p);
					}
					
				}//if
			}//for de compras
			return pedidosHT;
			
		}
	
	
	public List<EncuentraPedido> setEtiquetas(Map<String, DataIDDescripcion> pedidos, int canal, String token, int dias, marketPlace mp, Call_WS_APIENCUENTRA wms) 
	{
		List<EncuentraPedido> retorno = new ArrayList<>();
		
		List<DataEcommerce_canales_envio> envios = wms.darListaEcommerce_canales_envio(canal, token);
		List<DataIDDescripcion> depositosDestino = wms.DarDatosPutOrders(token, 2);		
	
		List<EncuentraPedido> pedidosMarketplace = mp.getPedidos(canal,"",dias);
		
		for (EncuentraPedido p : pedidosMarketplace) 
		{
			if(pedidos.get(p.getIdFenicio())!=null)
			{
				p.setIdPedido(Long.parseLong(pedidos.get(p.getIdFenicio()).getDescripcion()));
				
				Shipping shipp = new Shipping();
				Cliente cli = new Cliente(p.getCliente());
				shipp.setCliente(cli);
				/*Esto se cambio*/
				Utilidades utilidades = new Utilidades();
				shipp.getCliente().setApellido(utilidades.validarComillas(shipp.getCliente().getApellido()));
				shipp.getCliente().setNombre(utilidades.validarComillas(shipp.getCliente().getNombre()));
				shipp.getCliente().setCalle(utilidades.validarComillas(shipp.getCliente().getCalle()));
				
				if(cli.getCiudad()==null)
				{
					shipp.getCliente().setCiudad(cli.getLocalidad());
				}
				shipp.getCliente().setObs(cli.getObs());
				shipp.getCliente().setTelefono(cli.getTelefono());
				shipp.getCliente().setEmail(cli.getEmail());
				
				DataDescDescripcion eti = null;
				int destino = 0;
				
//				DataArticuloEcommerceVerifR r = wms.darArticuloEcommerceReqReclasifica(p.getIdPedido(),token);
				if(!p.getEmpresaEnvioCod().equals(""))
				{
					System.out.println(p.getEmpresaEnvioCod());
					
					for(DataEcommerce_canales_envio env:envios){
						if(p.getEmpresaEnvioCod().equals(env.getCodigo()))
						{								
							destino = Integer.parseInt(env.getIddeposito());
							System.out.println(env.getCourier());
							p.setIdDepositoEnvio(destino); 
							
							if(env.getCourier().equals("SOYDELIVERY") || env.getCourier().equals("PEDIDOS_YA"))
								//esta validacion de courier deberia hacerse contra un parametro del objeto *ENV* 
								//deberia existir un atributo que indique si el envio genera etiquetas en la sincro.
							{
								
								p.setIdDepositoEnvio(destino); 
								
							}				
							else 
							{
								eti = setEnvio(shipp, env,p.getIdPedido()+"",p.getFecha(),"PM",mp.getIdEmpresa(),token,wms,p.getIdFenicio());
							}
							p.setDestino(new DataIDDescripcion(destino,""));
							break;
						}
					}
				}
				
				if(eti==null)//es pickup
				{
					
					Fecha fechaActual = new Fecha();
					System.out.println(p.getSucursalPick());
					Utilidades u = new Utilidades();
					int sucPick = u.tryParse(p.getSucursalPick());
					
					
					DataArticuloEcommerceVerifR articuloR = new DataArticuloEcommerceVerifR(p.getIdPedido(), 0, p.getCantidad(), "", p.getDescripcion(), "", sucPick);
				
					try
					{
						articuloR.setTelCliente(p.getCliente().getTelefono());
					}
					catch (Exception e) {
						articuloR.setTelCliente("");
					}
					
					articuloR.setFecha(fechaActual.darFechaAnio_Mes_Dia());
					articuloR.setIdEcommerce(p.getIdFenicio());
					//articuloR.setTracking(p.getIdFenicio());
					articuloR.setEstadoEncuentra(1);		
					articuloR.setCanal(p.getCanalAnaloga());
					articuloR.setTracking(p.getIdPedido()+"");
					
					String url = "";											//GENERO ETIQUETA PICKUP
					try 
					{
						DataIDDescripcion env = new DataIDDescripcion();
						for (DataIDDescripcion dd : depositosDestino) 
						{
							if(dd.getId()==sucPick)
							{
								env = dd;
								p.setDestino(env);
								p.setIdDepositoEnvio(sucPick);
								break;
							}
						}
						url = IPrint.ImprimirEtiquetasNuevas(articuloR,mp.getIdEmpresa(),env,"CD 9000"," Av. Italia 4346   								   						    Tel.:2613 7566 		  Montevideo-Uruguay",1,true,"","");
						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}							
					p.setUrlEtiqueta(url);
				}
				else//hay etiqueta
				{
					p.setUrlEtiqueta(eti.getDescripcion()); 
				}
				
				retorno.add(p);
			}
		}
		return retorno;
		
	}
	
	
	public DataDescDescripcion setEnvio(Shipping ship,DataEcommerce_canales_envio env,String pedido,String fecha,String hora,int idEmpresa,String token,Call_WS_APIENCUENTRA wms, String idPedidoFenicio)
	{
		Couriers couriers = new Couriers();
		MailSpooler mailspooler = new MailSpooler();
		
		Map<Integer, String> parametros = wms.darParametros(token,"");

		try {
			Credenciales c = new Credenciales();
			Gson gson = new Gson();
			String json="";
			String salida = "";			
			
			switch (env.getCourier()) 
			{
				case "PEDIDOS_YA":
				{
					Fecha fechaPe = null;
					String horaEnvioD = "";
					String horaEnvioH = "";
					if(ship.getHoraDesde()!=null)
					{
						horaEnvioD = ship.getHoraDesde();
						horaEnvioH = ship.getHoraHasta();
						
						Fecha fechaEnvioD = new Fecha(horaEnvioD.replace("T", " "));
						Fecha fechaEnvioH = new Fecha(horaEnvioH.replace("T", " "));
						Fecha fechaActual = new Fecha(true,0);
						System.out.println(horaEnvioD);
						
						if(fechaActual.getDiaDelAnio()>=fechaEnvioD.getDiaDelAnio())
						{
							if(fechaActual.getHora()>=fechaEnvioD.getHora() && fechaActual.getHora()<=fechaEnvioH.getHora())
							{
								//envialo YA
								fechaPe = new Fecha(fechaActual.getDia(), fechaActual.getMes(), fechaActual.getAnio(), fechaActual.getHora(), fechaActual.getMinuto()+3,3,"");
								
							}
							else
							{
								//que pasa si la hora no esta dentro de la ventana horaria
								//si es antes de la hora?
								if(fechaActual.getHora()<fechaEnvioD.getHora())
								{
									//envialo YA
									fechaPe = new Fecha(fechaActual.getDia(), fechaActual.getMes(), fechaActual.getAnio(), fechaEnvioD.getHora(), fechaEnvioD.getMinuto(),3,"");
									
								}
								else
								{
									//si es despues de la hora?
									//envialo YA
									fechaPe = new Fecha(fechaActual.getDia(), fechaActual.getMes(), fechaActual.getAnio(), fechaEnvioD.getHora(), fechaEnvioD.getMinuto(),27,"");//se le suma 24Hs
									
								}
							}
						}
						else
						{
							//es para enviar mas adelante
							fechaPe = new Fecha(fechaEnvioD.getDia(), fechaEnvioD.getMes(), fechaEnvioD.getAnio(), fechaEnvioD.getHora(), fechaEnvioD.getMinuto(),3,"");
							
						}
						
					}
					else
					{
						Fecha fecha2 = new Fecha(true,3);
				    	System.out.println(fecha2.darHoraMinutoSegundoZ());
				    	System.out.println(fecha2.getDiaSemana());
				    	
				    	Fecha posible = wms.darHoraHabilDeposito(token, 512,fecha2) ;
				    	
				    	System.out.println(posible.darHoraMinutoSegundoZ());
				    	System.out.println(posible.getDiaSemana());
				    	
				    	fechaPe = new Fecha(posible.getDia(), posible.getMes(), posible.getAnio(), posible.getHora(), posible.getMinuto(),3,"");
				    	
					}
					
					System.out.println("fecha pedidosYA "+ fechaPe.darHoraMinutoSegundoZ());
			    	
					
					CredencialesPEYA creds = gson.fromJson(env.getPass(), CredencialesPEYA.class);
					
					
					
					
					Credenciales credenciales = new Credenciales(creds.getUsername(), creds.getPassword(), creds.getClient_secret(), creds.getClient_id(), pedido, fechaPe.darFechaAnio_Mes_Dia(), fechaPe.darHoraMinutoSegundoZ(), "", "5000", "");
					ship.setCredenciales(credenciales);
					ship.setTipoShipping(38719561);
					ship.setVolumen("10.0");
					ship.setPeso("1.0");
					ship.setCantidadPaquetes(1);
					ship.setMailNotificacion(ship.getCliente().getEmail());
					
					
					Utilidades ut = new Utilidades();
					
					ship.getCliente().setCalle(ut.limpiarS(ship.getCliente().getCalle()));
					ship.getCliente().setNroPuerta(ut.soloNumeros(ship.getCliente().getNroPuerta()));
					
					
					
					
					Cliente sender = wms.darSenderPeYa(token,pedido);
					ship.setSender(sender);
					System.out.println(sender.getCiudad());
					json = gson.toJson(ship);
					
					
					salida = couriers.PeYaShipping(json, token, fecha, horaEnvioH) ;
							
					
					DataDescDescripcion respuesta = gson.fromJson(salida, DataDescDescripcion.class);
					Long idPedido = Long.parseLong(idPedidoFenicio);
					if(respuesta.getId().equals("FAIL"))
					{
					
						wms.logOrder(3,"ERROR AL GENERAR DESPACHO"+respuesta.getDescripcion(),idPedido,token);
						
						String mailsD = parametros.get(48);
						
						List<SendMail> mails = new ArrayList<>();
						SendMail sm = new SendMail("PY"+idPedido, mailsD, "ERROR AL GENERAR DESPACHO PEDIDOS YA", "Al pedido "+idPedido+" no se le pudo generar el despacho correspondiente.", "encuentra@200.com.uy","");
						mails.add(sm);
						
						String data = gson.toJson(mails);
						mailspooler.putMails(data, token);
						
						
						return new DataDescDescripcion("", "PEDIDOS_YA");
					}
					else
					{
						
						wms.logOrder(3, "Tracking pedidosYA: " +respuesta.getDescripcion(),idPedido,token);
						try
						{
							
							wms.logOrder(3, "URL tracking pedidosYA: <a target=\"_BLANK\" href=\""+respuesta.getFecha()+"\"> " +respuesta.getFecha() +"</a>",idPedido,token);
							
						}
						catch (Exception e) 
						{
							
						}
						
						
						return new DataDescDescripcion(respuesta.getDescripcion(), "PEDIDOS_YA");
					}
					
				
				}
				case "DAC":
				{
					json = gson.toJson(ship);
					
					salida =couriers.DacShipping(json, token, fecha, hora); 
							
					
					
					UESEnvio ues = gson.fromJson(salida, UESEnvio.class);
					
					if(ues.getDescripcion().contains("http"))
					{
						
						System.out.println("\r\n\r\n\r\n\r\n");
						System.out.println("Etiqueta DAC="+ues.getDescripcion());
						System.out.println("\r\n\r\n\r\n\r\n");
						
						return new DataDescDescripcion(ues.getId()+"", ues.getDescripcion());
					}
					else
					{
						
						System.out.println(ues.getId()+""+ ues.getDescripcion());
						return new DataDescDescripcion("","");
					}
				
				}
				case "UES":
				{
					c.setIdCliente(env.getUsuario());
					c.setPass(env.getPass());
					c.setPrestador("FORUS");
//					TODO: Ampliar para todos los clientes
//					Debería traerlo de bdd al objeto env
					c.setTipoEnvio(env.getTipoEnvio());
					c.setToken(env.getPass());
					c.setUser(env.getUsuario());
					c.setPedido("encuentra_"+pedido+"");
					c.setFecha("");
					c.setHora("");
					ship.setCredenciales(c);
									
					json = gson.toJson(ship);
					
					int intentos = 3;
					while (intentos>0) 
					{
						salida = couriers.UESShipping(json, token, fecha, hora); 
								
						if(salida.contains("Barrio no encontrado"))
						{
							ship.getCliente().setLocalidad("Otro");
							json = gson.toJson(ship);
							salida = couriers.UESShipping(json, token, fecha, hora);
						}
						if(salida.contains("Etiqueta no generada"))
						{
							intentos--;
							salida = couriers.UESShipping(json, token, fecha, hora);
							if(!salida.contains("Etiqueta no generada"))
							{
								intentos =0;
							}
						}
						else
						{
							
							intentos=0;
						}
						
					}
					
					
					UESEnvio ues = gson.fromJson(salida, UESEnvio.class);
					System.out.println(ues.getDescripcion());
					
					if(ues.getDescripcion().contains("http"))
					{
						
						System.out.println("\r\n\r\n\r\n\r\n");
						System.out.println("Etiqueta UES="+ues.getDescripcion());
						System.out.println("\r\n\r\n\r\n\r\n");
						
						return new DataDescDescripcion(ues.getId()+"", ues.getDescripcion());
					}
					else
					{
						
						System.out.println(ues.getId()+""+ ues.getDescripcion());
						return new DataDescDescripcion("","");
					}
				}
				case "DISTRICAD":
				{
					c.setIdCliente("2576");
					c.setPass("pr56!!mntv345");
					c.setPrestador("FORUS");
					c.setTipoEnvio("EXPRESS");
					c.setToken("PbkF0XZX2UeBHw7agWvuow==");
					c.setUser("INTERFASEFORUS");
					c.setPedido("");
					c.setFecha(fecha);
					c.setHora(hora);
					
					ship.setCredenciales(c);
					
					json = gson.toJson(ship);
					salida = couriers.DistricadShipping(json,token);
					break;	
				}
				case "MIRTRANS":
				{
						c.setIdCliente("");
						c.setPass(env.getPass());
						c.setPrestador("FORUS");
						c.setTipoEnvio("C");
						c.setToken("");
						c.setUser(env.getUsuario());
						c.setPedido(pedido+"");
						c.setFecha("");
						c.setHora("");
						ship.setCredenciales(c);
						
						json = gson.toJson(ship);
						
						
						salida = couriers.MirtransShipping(json,token);
						
						RespuestaMIR mir = gson.fromJson(salida, RespuestaMIR.class);
						if(mir.getEtiqueta().contains("http"))
						{
							System.out.println("\r\n\r\n\r\n\r\n");
							System.out.println("Etiqueta MIRTRANS="+mir.getEtiqueta());
							System.out.println("\r\n\r\n\r\n\r\n");
							return new DataDescDescripcion(mir.getCodigoBarra(), mir.getEtiqueta());
						}
						else
						{
							if(mir.getEtiqueta().contains("Clave externa ya uilizada")) {
								c.setPedido(pedido+"_2");
								ship.setCredenciales(c);
								json = gson.toJson(ship);								
								salida = couriers.MirtransShipping(json, token);
								mir = gson.fromJson(salida, RespuestaMIR.class);
								if(mir.getEtiqueta().contains("http"))
								{
									System.out.println("\r\n\r\n\r\n\r\n");
									System.out.println("Etiqueta MIRTRANS="+mir.getEtiqueta());
									System.out.println("\r\n\r\n\r\n\r\n");
									return new DataDescDescripcion(mir.getCodigoBarra(), mir.getEtiqueta());
								}
								else
								{
									System.out.println(mir.getCodigoBarra()+" "+ mir.getEtiqueta());
									return new DataDescDescripcion("","");
								}
							}else {
								System.out.println(mir.getCodigoBarra()+" "+ mir.getEtiqueta());
								return new DataDescDescripcion("","");
							}
							
						}
						
				}
				case "SOYDELIVERY":
				{
					/*	TESTING
					 * http://testing.soydelivery.com.uy/rest/awsnuevopedido1
					 * "Negocio_id": 2143,
						"Negocio_clave": 1081,
					 */
					
					/*	PROD
					 * https://soydelivery.com.uy/rest/awsnuevopedido1
					 * "Negocio_id": 3669,
						"Negocio_clave": 1081,
					 */
					c.setIdCliente(env.getUsuario());
					c.setPass(env.getPass());
					c.setPrestador("FORUS");
					c.setIdCliente(env.getIdTienda());
					c.setToken(env.getPass());
					c.setUser(env.getUsuario());
					c.setPedido(pedido+"");
					c.setFecha(env.getFechaEntrega());
					c.setHora("");
				
					ship.setCredenciales(c);
									
					json = gson.toJson(ship);
					
					salida = couriers.SDLShipping(json,token, fecha, hora);
					DataDescDescripcion despacho = gson.fromJson(salida, DataDescDescripcion.class);
					System.out.println(despacho);
					return despacho;						
				}
					
				default:
				break;
				
			}
		} catch (Exception e) 
		{
			return new DataDescDescripcion("","");
		}
		
		return new DataDescDescripcion("","");
	}
	
	

}
