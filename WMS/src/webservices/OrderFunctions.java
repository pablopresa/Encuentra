package webservices;

import java.io.FileNotFoundException;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.DocumentException;

import beans.Fecha;
import beans.Nota;
import beans.Usuario;
import beans.jsonEstadoMP;
import beans.api.ResponseOrder;
import beans.api.ResponseOrderFunctions;
import beans.api.coleccionPedidoFactura;
import beans.api.pedidoFactura;
import beans.encuentra.ContenedorListaClientes;
import beans.encuentra.ContenedorListaPedidos;
import beans.encuentra.DepositoStock;
import beans.encuentra.IPrint;
import beans.endpoints.itemsWHS;
import beans.endpoints.suborderWHS;
import beans.endpoints.updateSuborderWHS;
import beans.endpoints.wsResponse;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.ColectionDataIDDescripcion;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.EncuentraPedidoArticulo;
import eCommerce_jsonObjectsII.Items;
import logica.Logica;
import logica.Utilidades;





@Path("/OrderFunctions")
public class OrderFunctions 
{
	@POST
	@Path("/updateStatus")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String updateStatus(String data,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");
		String json = "";		
		List<DataArticuloEcommerceVerifR> pedidos = new ArrayList<>();
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(a);
			
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				
				DataArticuloEcommerceVerifR ecommerce = gson.fromJson(data, DataArticuloEcommerceVerifR.class);
				
				/*VERIFICO SI ME ENVIARON IDPEDIDO	  ==> cambio estado directamente
										  IDECOMMERCE ==> voy a buscar el/los idpedido/s para cambio de estado
										  SUBPEDIDO   ==> voy a buscar el subpedido para cambio de estado
				*************************************************************************************************/
				Long idPedido = ecommerce.getIdPedido();
				String idEcommerce = ecommerce.getIdEcommerce();
				String subpedido = ecommerce.getSubpedido();
				
				if(idPedido != null && idPedido != new Long("0")) {
					pedidos.add(ecommerce);
				}
				else if((idEcommerce != null && !idEcommerce.equals("")) || (subpedido!= null && !subpedido.equals(""))){
					List<DataArticuloEcommerceVerifR> subpedidos = l.SubPedidos(idEcommerce, subpedido, ecommerce.getEstadoEncuentra(),idEmpresa);
					pedidos.addAll(subpedidos);
				}
				else {
					response.setCode("404");
					response.setDescription("No se encontraron datos del pedido");
				}
				
				if(pedidos.size() > 0) {
					for(DataArticuloEcommerceVerifR pedido: pedidos) 
					{
						l.updateEcommerceEstado(pedido.getIdPedido(), pedido.getEstadoEncuentra(), idEmpresa, u);
						l.logPedido(pedido.getIdPedido(), u.getNumero(), pedido.getEstadoEncuentra(), "Cambio de estado generado por fuera de Encuentra",4,idEmpresa);
					}
				}
				else {
					response.setCode("404");
					response.setDescription("No se encontraron datos del pedido");
				}
				
				
				
			}
			else {
				response.setCode("404");
				response.setDescription("token incorrecto");
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}
		
		json = gson.toJson(response);
		
		return json;
	}
	
	
	@GET
	@Path("/putLog")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String putLog(String data,@QueryParam ("token") String token, @QueryParam ("idPedido") Long idPedido, @QueryParam ("estado") int estado, @QueryParam ("mensaje") String mensaje) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		wsResponse response = new wsResponse();
		Usuario u = l.loginEncuentraAPI2(token);
		response.setCode("200");
		response.setDescription("ok");
		String json = "";		
		l.logPedido(idPedido, u.getNumero(), estado, mensaje,4,u.getIdEmpresa());
		
		
		json = gson.toJson(response);
		
		return json;
	}
	
	@POST
	@Path("/darHoraHabilDeposito")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String darHoraHabilDeposito(String data,@QueryParam ("token") String token, @QueryParam ("idDeposito") int idDeposito) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		Usuario u = l.loginEncuentraAPI2(token);
		Utilidades ut = new Utilidades();
		Fecha fecha = gson.fromJson(data, Fecha.class);
		
		String json = "";		
		Fecha fh = ut.darHoraHabilDeposito(idDeposito,u.getIdEmpresa(), fecha);
		
		
		json = gson.toJson(fh);
		
		return json;
	}
	
	
	@GET
	@Path("/darSenderPeYa")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String darSenderPeYa(String data,@QueryParam ("token") String token, @QueryParam ("idPedido") String idPedido) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		
		Usuario u = l.loginEncuentraAPI2(token);
		
		String json = "";		
		Cliente sender = l.darSenderPeYa(u.getIdEmpresa(),idPedido);
		
		
		
		json = gson.toJson(sender);
		
		return json;
	}
	
	@POST
	@Path("/updateWHS")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String updateWHS(String data,@QueryParam ("token") String token) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		String json = "";	
		wsResponse response = new wsResponse();
		response.setCode("200");
		response.setDescription("ok");			
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(token);
			
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				updateSuborderWHS whs = gson.fromJson(data, updateSuborderWHS.class);
				EncuentraPedido p = l.EncuentraPedido(whs.getOriginalSubOrder(), idEmpresa);
				if(p!=null) {
					//COMPRUEBO QUE LAS CANTIDADES QUE ME MANDAN SEAN IGUALES A LAS DE LA SUBORDEN ORIGINAL
					boolean cantidadesCorrectas = false;
					Hashtable<String, Integer> hasArt = new Hashtable<>();
					int subOrdersSize = whs.getNewSuborders().size();					
					for(suborderWHS subO: whs.getNewSuborders()) {			
						/*if(subO.getSuborder().equals(whs.getOriginalSubOrder())) {
							subOoriginal = true;
						}*/
						for(itemsWHS it : subO.getItems()) {
							try {													//CONTABILIZO LO QUE ME VIENE EN EL DATASET
								int qtyArt = hasArt.get(it.getItem()) + it.getQty();
								hasArt.put(it.getItem(), qtyArt);
							} catch (Exception e) {
								hasArt.put(it.getItem(), it.getQty());
							}	
						}											
					}
					
					Hashtable<String, Integer> hasArtWMS = new Hashtable<>();					
					for(EncuentraPedidoArticulo a:p.getArticulosPedido()) {	
						try {													//CONTABILIZO LO QUE ME TENGO EN WMS
							int qtyArt = hasArtWMS.get(a.getArticulo()) + a.getCantidad();
							hasArtWMS.put(a.getArticulo(), qtyArt);
						} catch (Exception e) {
							hasArtWMS.put(a.getArticulo(), a.getCantidad());
						}	
																	
					}
					
					Enumeration<String> artsWMS = hasArtWMS.keys();
					
					while(artsWMS.hasMoreElements()) {	//COMPARO CON LO QUE TENGO EN WMS CONTRA LO QUE VIENE EN EL DATASET
						try {
							String art =  artsWMS.nextElement();
							if(hasArt.get(art) == hasArtWMS.get(art)) {
								cantidadesCorrectas = true;
							}
							else {
								cantidadesCorrectas = false;
								break;
							}
						} catch (Exception e) {
							break;
						}
					}
					
					if(cantidadesCorrectas) {
						l.updateSuborderWHSModdo(whs, p, idEmpresa,u);
					}
					else {
						response.setCode("404");
						response.setDescription("la cantidad de articulos no coincide con la suborden original");
					}
				}
				else {
					response.setCode("404");
					response.setDescription("no se encontro ningun subpedido con la informacion brindada");
				}		
				
			}
			else {
				response.setCode("404");
				response.setDescription("token incorrecto");
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setCode("500");
			response.setDescription("Sucedio un error");
		}
		
		json = gson.toJson(response);
		
		return json;
	}
	
	@POST
	@Path("/saveNote")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String saveNote(String data,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		DataIDDescripcion retorno = new DataIDDescripcion();
		String json = "";		
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(a);
			
			if(u==null) {
				retorno.setDescripcion("Token incorrecto");
			}
			else {
				int idEmpresa = u.getIdEmpresa();						
				
				Nota nuevaNota = gson.fromJson(data, Nota.class);
				
				nuevaNota.setIdUsuario(u.getNumero());
				l.AltaNota(nuevaNota,idEmpresa);
				
				retorno.setIdLong(nuevaNota.getIdPedido());
				retorno.setDescripcion("Nota ingresada correctamente");
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			retorno.setDescripcion("Sucedio un error");
		}
		
		json = gson.toJson(retorno);
		json = json.replace("\"id\":0,", "");
		json = json.replace("\"idB\":0,", "");
		json = json.replace("\"in\":false,", "");
		
		return json;
	}

	@GET
	@Path("/ordersNoLabel")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public List<DataIDDescripcion> ordersNoLabel(@QueryParam ("canal") String canal,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		List<DataIDDescripcion> retorno = new ArrayList<>();
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(a);
			int c = Integer.parseInt(canal);
			
			if(u!=null) {
				int idEmpresa = u.getIdEmpresa();					
				retorno = new ArrayList<>(l.darPedidosPorCanalFenicio(c, idEmpresa).values());				
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	@GET
	@Path("/ordersNotDispatched")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String ordersNotDispatched(@QueryParam ("canal") String canal,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();	;
		List<DataIDDescripcion> retorno = null;
		String json = "";
		try
		{		
			Usuario u = l.loginEncuentraAPI2(a);
			int c = Integer.parseInt(canal);
			Gson gson = new Gson();
						
			if(u!=null) {
				int idEmpresa = u.getIdEmpresa();					
				retorno = l.pedidosSinDespachar(idEmpresa);	
				json = "{ \"idTabla\":0,\"datos\": "+gson.toJson(retorno)+"}";
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return json;
	}
	
	@GET
	@Path("/getArticuloVerifR")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String getArticuloVerifR(@QueryParam ("idPedido") String idPedido,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();	;
		
		String json = "";
		try
		{		
			Usuario u = l.loginEncuentraAPI2(a);
			
			Gson gson = new Gson();
						
			if(u!=null) {
				int idEmpresa = u.getIdEmpresa();
				DataArticuloEcommerceVerifR r = l.darArticuloEcommerceReqReclasifica(Long.parseLong(idPedido),0,idEmpresa);
				jsonEstadoMP js = l.JSONCambioEstadoMarketPlace(idEmpresa, r);
				
					
				json = gson.toJson(js);
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return json;
	}
	
	
	@POST
	@Path("/getStockOrder")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String getStockOrder(String data,@QueryParam ("token") String a,@QueryParam ("idCanal") String idCanal) throws IOException
	{	 
		/*********21/09/21********/
		// se agrega IDCANAL para que las ventas que solo tienen que tomar de un deposito especifico (p/Ej Peppos) para la matriz de derivacion 
		// lo hagan leyendo de una lista reducida a los depositos que le donan 
		
		Logica l = new Logica();	
		Gson gson = new Gson();
		ResponseOrderFunctions response = new ResponseOrderFunctions();
		response.setOrders(new ArrayList<>());
		String json = "";		
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(a);
			
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				
				
				
				
				
				List<DataIDDescripcion> listaArticulos = gson.fromJson(data, new TypeToken<List<DataIDDescripcion>>(){}.getType());		
				
				
				
				
				
				//DepositoStock
				String inn = "";
				boolean pri = true;
				
				for (DataIDDescripcion da : listaArticulos) 
				{
					if(pri)
					{
						pri=false;
						inn+="'"+da.getDescripcion()+"'";
					}
					else
					{
						inn+=",'"+da.getDescripcion()+"'";
					}
				}
				
				List<DepositoStock> retorno = l.darDepositStockOrder(inn,idEmpresa,idCanal);
				json = gson.toJson(retorno);
						
			
			}
			else {
				
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		
		
		
		return json;
	}
	
	@POST
	@Path("/updateOrdersStatus")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String updateOrdersStatus(String data,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		ResponseOrderFunctions response = new ResponseOrderFunctions();
		response.setOrders(new ArrayList<>());
		String json = "";		
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(a);
			
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				
				ColectionDataIDDescripcion coleccionPedidos = gson.fromJson(data, ColectionDataIDDescripcion.class);
				
				for(DataIDDescripcion pedido: coleccionPedidos.getColeccion()) {
					try {
						l.updateEcommerceEstado(new Long(pedido.getDescripcion()), pedido.getId(), idEmpresa, u);
						l.logPedido(new Long(pedido.getDescripcion()), u.getNumero(), pedido.getId(), "Cambio de estado generado por fuera de Encuentra",4,idEmpresa);
						
						response.getOrders().add(new ResponseOrder(new Long(pedido.getDescripcion()),"ok"));						
					} catch (Exception e) {
						response.getOrders().add(new ResponseOrder(new Long(pedido.getDescripcion()),"fail"));	
					}					
				}				
			}
			else {
				response.setMessage("Token incorrecto");
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setMessage("Sucedio un error");
		}
		
		json = gson.toJson(response);
		
		return json;
	}
	
	@POST
	@Path("/putFacturas")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String putFacturas(String data,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		ResponseOrderFunctions response = new ResponseOrderFunctions();
		response.setOrders(new ArrayList<>());
		String json = "";		
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(a);
			
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				
				coleccionPedidoFactura coleccion = gson.fromJson(data, coleccionPedidoFactura.class);
				
				for(pedidoFactura pedido: coleccion.getColeccion()) {
					try {
						l.altaOrdenPedido(pedido.getIdPedido(), pedido.getNroFactura(), pedido.getNroFactura(), pedido.getPdf(), idEmpresa);
						
						response.getOrders().add(new ResponseOrder(pedido.getIdPedido(),"ok"));						
					} catch (Exception e) {
						response.getOrders().add(new ResponseOrder(pedido.getIdPedido(),"fail"));	
					}					
				}				
			}
			else {
				response.setMessage("Token incorrecto");
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setMessage("Sucedio un error");
		}
		
		json = gson.toJson(response);
		
		return json;
	}
	
}
