package endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import logica.LogicaAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.CallBackPedido;
import beans.TaskStatusChange;
import beans.Usuario;
import beans.jsonEstadoMP;
import beans.api.DataMovimiento;
import beans.api.Order;
import beans.api.ResponseOrder;
import beans.api.ResponseOrderFunctions;
import beans.api.wsResponse;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.ContenedorListaDataIdDesc;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.MovimientosC;
import beans.encuentra.Pedido;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.marketplaces.fenicio.FenicioAPIorden;
import integraciones.marketplaces.objetos.marketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;

@Path("/callback")
public class SaveCallBack 
{
	
	@POST
	@Path("/save")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String save(String data,@QueryParam ("token") String a) throws IOException
	{
	      //System.out.println(data);
		
		String mensaje ="";
		String json = "";
		int codResp=0;
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);
		
		if (u.getNick() == null) 
		{
			mensaje="access_token invalido";
			
		}
		
		
		CallBackPedido cbp = new CallBackPedido();
		Gson gson = new Gson();
		cbp = gson.fromJson(data,CallBackPedido.class);
		cbp.save();
		
		
		
		
		json= "{"+
				  "\"mensaje\": \""+mensaje+"\","+
				  "\"cod_respuesta\":\""+codResp+"\""+
				"}";
		System.out.println(a);
	      
		
		return json;
	   }


		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@POST
	@Path("/putTracking")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String putTracking(String data,@QueryParam ("token") String a) throws IOException
	{
	      //System.out.println(data);
		
		String mensaje ="";
		String json = "";
		Gson gson = new Gson();
		ResponseOrderFunctions response = new ResponseOrderFunctions();
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);
		
		if (u.getNick() == null) 
		{
			response.setMessage("access_token invalido");	
		}
		else {
			System.out.println(data);
			Order order = new Order();
			order = gson.fromJson(data, Order.class);
			
			LogicaAPI.persistir("insert into producteca_ecom_envios (OPERATIONID, TRACKINGNUMBER, COURIER, URLTRACKING) values "
					+ "("+order.getId()+",'"+order.getTracking()+"',"+order.getCourier()+",'"+order.getUrlTracking()+"')");
			
			response.setMessage("ok");
			response.setOrders(new ArrayList<>());
			response.getOrders().add(new ResponseOrder(order.getId(),"ok"));	
		}
		
		json = gson.toJson(response);      
		
		return json;
	   }
	
	@POST
	@Path("/updateOrder")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String updateOrder(String data,@QueryParam ("token") String a) throws IOException
	{
	      //System.out.println(data);
		
		String json = "";
		Gson gson = new Gson();
		ResponseOrderFunctions response = new ResponseOrderFunctions();
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);
		
		if (u.getNick() == null) 
		{
			response.setMessage("access_token invalido");	
		}
		else {
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);
			Order order = new Order();
			order = gson.fromJson(data, Order.class);
			
			LogicaAPI l = new LogicaAPI();
			OrdenVenta ov = l.darOrdenVenta(order.getId());
			
			Hashtable<String, List<DataIDDescripcion>> hasMovs = new Hashtable<String, List<DataIDDescripcion>>();
			Hashtable<String, OrdenVentaLinea> hasLineas = new Hashtable<>();
			Double precioOrden = 0.0;
			for(OrdenVentaLinea ovl: ov.getOrdenVentaLineas()) {				//PONGO EN HASH ARTICULOS DE LA ORDEN
				hasLineas.put(ovl.getIdArticulo(), ovl);
				precioOrden += ovl.getPrecioImp() * ovl.getCantidad();
			}
			
			for(DataMovimiento dm: order.getDevoluciones()) {	
				try {
					if(hasLineas.get(dm.getIdArticulo())!=null) {					//QUITO ARTICULOS A DEVOLVER
						precioOrden -= hasLineas.get(dm.getIdArticulo()).getPrecioImp() * dm.getCantidad();
						if(hasLineas.get(dm.getIdArticulo()).getCantidad()==dm.getCantidad()) {
							hasLineas.remove(dm.getIdArticulo());						
						}
						else {
							int cantidad = hasLineas.get(dm.getIdArticulo()).getCantidad() - dm.getCantidad();
							hasLineas.get(dm.getIdArticulo()).setCantidad(cantidad);
						}
						
						if(hasMovs.get(dm.getOrigen()+"-"+dm.getDestino())==null) {		//DEVOLUCIONES A ERP
							List<DataIDDescripcion> listaMovs = new ArrayList<>();
							listaMovs.add(new DataIDDescripcion(dm.getCantidad(),dm.getIdArticulo()));
							hasMovs.put(dm.getOrigen()+"-"+dm.getDestino(),listaMovs);
						}
						else {
							hasMovs.get(dm.getOrigen()+"-"+dm.getDestino()).add(new DataIDDescripcion(dm.getCantidad(),dm.getIdArticulo()));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
								
			}
			
			for(DataMovimiento r: order.getRemitos()) {							//AGREGO ARTICULOS A MOVER
				try {
					// ir a buscar precio de articulo
					double precioArt = 0.0;
					hasLineas.put(r.getIdArticulo(), new OrdenVentaLinea(precioArt, r.getCantidad()+"", r.getIdArticulo()));
					precioOrden += precioArt * r.getCantidad();
					
					if(hasMovs.get(r.getOrigen()+"-"+r.getDestino())==null) {		//REMITOS A ERP
						List<DataIDDescripcion> listaMovs = new ArrayList<>();
						listaMovs.add(new DataIDDescripcion(r.getCantidad(),r.getIdArticulo()));
						hasMovs.put(r.getOrigen()+"-"+r.getDestino(),listaMovs);
					}
					else {
						hasMovs.get(r.getOrigen()+"-"+r.getDestino()).add(new DataIDDescripcion(r.getCantidad(),r.getIdArticulo()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			List<OrdenVentaLinea> nuevasLineas = new ArrayList<>(hasLineas.values());
			ov.setOrdenVentaLineas(nuevasLineas);
			ov.setImportePago(precioOrden);
			
			ov.save("", idEmpresa);												//ACTUALIZO ORDEN
			
			int usu = order.getRemitos().get(0).getUsuario();
			Enumeration<String> elements = hasLineas.keys();					//REGISTRO MOVIMIENTOS DE STOCKS
			while (elements.hasMoreElements()) {
				String key=elements.nextElement();
				try {
					int origen = Integer.parseInt(key.split("-")[0]);
					int destino = Integer.parseInt(key.split("-")[1]);
					
					l.RegistrarMovimientoStock(origen, destino, usu, hasMovs.get(key), idEmpresa, new Long(ov.getIdCarrito()),0,0,false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
						
			response.setMessage("ok");
			response.setOrders(new ArrayList<>());
			response.getOrders().add(new ResponseOrder(order.getId(),"ok"));
		}
		
		json = gson.toJson(response);      
		
		return json;
	   }
	
	@POST
	@Path("/CheckInOrder")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String CheckInOrder(@QueryParam ("order") String order,@QueryParam ("token") String a) throws IOException
	{
	     
		String json = "";
		Gson gson = new Gson();
		ResponseOrderFunctions response = new ResponseOrderFunctions();
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);
		
		if (u.getNick() == null) 
		{
			response.setMessage("access_token invalido");	
		}
		else {
			System.out.println(order);
			Long idOrder = new Long(order);
			int idEmpresa = u.getIdEmpresa();
			LogicaAPI l = new LogicaAPI();
			l.CheckInOrder(idOrder, 0, idEmpresa);
			
			response.setMessage("ok");
			response.setOrders(new ArrayList<>());
			response.getOrders().add(new ResponseOrder(idOrder,"ok"));	
		}
		
		json = gson.toJson(response);      
		
		return json;
	   }

	@POST
	@Path("/putCambioEstadoMarketPlace")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String putCambioEstadoMarketPlace(String data,@QueryParam ("token") String token) throws IOException
	{		
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");
		boolean cambioEstado = false;
		Gson gson = new Gson();
		String json = "";		
		
		try
		{		
			Usuario u = LogicaAPI.loginEncuentraAPI2(token);
			
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				
				List<jsonEstadoMP> estadosMP = gson.fromJson(data, new TypeToken<List<jsonEstadoMP>>(){}.getType());
				cambioEstado = LogicaAPI.putColaEstadoMarketPlace(estadosMP);
				
				if(!cambioEstado) {
					response.setCode("500");
					response.setDescription("No se pudo poner en cola el cambio de estado");
				}
				else
				{
					/*TaskStatusChange chan = new TaskStatusChange();
					List<marketPlace> marketPlaces= chan.darInstanciasMarketPlaces(idEmpresa);
					for(marketPlace mp: marketPlaces) {
						Enumeration<Integer> canales = mp.getCanales().keys();
						while (canales.hasMoreElements()) {
							int c=canales.nextElement();
							if(estadosMP.size() > 0) 
							{
								chan.StatusChange(mp, estadosMP, c);
							}					
						}
					}	*/		
						
				}
			}
			else {
				response.setCode("500");
				response.setDescription("Token incorrecto");
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}
			
		json =gson.toJson(response);
		return json;
	}
	
	
	
}
