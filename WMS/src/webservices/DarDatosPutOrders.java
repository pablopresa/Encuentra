package webservices;

import java.io.IOException;
import java.util.ArrayList;
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

import beans.Usuario;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataEcommerce_canales_envio;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.EncuentraPedido;
import logica.Logica;
import logica.Utilidad;

@Path("/DarDatosPutOrders")
public class DarDatosPutOrders 
{
	@POST
	@Path("/tablas")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String darTablas(String data, @QueryParam ("token") String a)
	{	   
		Logica l = new Logica();
		
		System.out.println(data);
		Gson gson = new Gson();
		String json = "";
		
		int idTabla = gson.fromJson(data, DataIDDescripcion.class).getId();
		String padre = "{ \"idTabla\":"+idTabla+",\"datos\": ";
		
		Usuario u = l.loginEncuentraAPI2(a);
		
		int idEmpresa = u.getIdEmpresa();
		String q = "";
		
		switch (idTabla) 
		{
			case 1:
			{
				q = "select id,nombre from ecommerce_canal_ml where id NOT IN (5,0)  AND idEmpresa="+idEmpresa;
				break;
			}
			case 2:
			{
				q = "select e.idDeposito,e.nombre,d.email from ecommerce_envioml e "
					+ "left outer join depositos d on d.idDeposito=e.idDeposito and e.idEmpresa=d.idEmpresa "
					+ "WHERE e.idEmpresa="+idEmpresa;
				break;
			}
			case 3: // ULTIMA VENTA
			{
				int ultimaVenta = l.darNextSincWEB(idEmpresa);
				List<DataIDDescripcion> retorno = new ArrayList<>();
				retorno.add(new DataIDDescripcion(ultimaVenta,""));
				json = gson.toJson(retorno);
				
				return padre+json+"}";
			}
			case 4:
			{
				List<DataIDDescripcion> retorno = l.ArticulosECSinPedir(idEmpresa);
				json = gson.toJson(retorno);
				
				return padre+json+"}";
			}
			case 5://pedidos sin factura
			{
				q = "SELECT 0,idPedido FROM ecommerce_pedido WHERE idEmpresa="+idEmpresa+" AND URLetiqueta='' AND stamptime>= DATE_ADD(CURDATE() ,INTERVAL -30 DAY)";
				break;
			}
			default:
				break;
		}
		
		List<DataIDDescripcion> canalesFenicioALL = l.darListaDataIdDescripcionMYSQLConsulta(q);
		canalesFenicioALL.remove(0);
		l.logger(0, 100, "Iniciando sincronizacion Encuentra MercadoLibre",idEmpresa);
		
		json = gson.toJson(canalesFenicioALL);
		
		return padre+json+"}";
	   }
	
	@POST
	@Path("/pedidoFromTracking")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String pedidoFromTracking(String data,@QueryParam ("token") String a,@QueryParam ("tracking") String tracking) throws IOException
	{	   
		Logica l = new Logica();
		
		
		Gson gson = new Gson();
		Usuario u = l.loginEncuentraAPI2(a);
		int idEmpresa = u.getIdEmpresa();
		
		EncuentraPedido EP = l.darPedidoTracking (tracking, idEmpresa);
		String json = "";
		
		try
		{

			json = gson.toJson(EP);
			return json;
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return json;
	   }
	
	@POST
	@Path("/searchSKU")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String searchSKU(String data,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();
		Utilidad ut = new Utilidad();
		
		Gson gson = new Gson();
		Usuario u = l.loginEncuentraAPI2(a);
		int idEmpresa = u.getIdEmpresa();
		
		List<String> datos = gson.fromJson(data, new TypeToken<List<String>>(){}.getType());		
		
		
		Map<String, String> articulos =ut.buscarArticulos (datos, idEmpresa);

		return gson.toJson(articulos);
		
	   }	
	
	@GET
	@Path("/PedidosID")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String PedidosID(String data,@QueryParam ("token") String a, @QueryParam ("dias") int dias,  
			@QueryParam ("estados") String estados) throws IOException
	{	   
		Logica l = new Logica();
		
		Gson gson = new Gson();
		Usuario u = l.loginEncuentraAPI2(a);
		int idEmpresa = u.getIdEmpresa();
		
		Map<String, String> pedidos = l.buscarPedido (dias, idEmpresa, estados);
		
		return gson.toJson(pedidos);
		
	   }	
	
	@GET
	@Path("/darListaEcommerce_canales_envio")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String darListaEcommerce_canales_envio(String data,@QueryParam ("token") String a, @QueryParam ("canal") int canal) throws IOException
	{	   
		Logica l = new Logica();
		
		Gson gson = new Gson();
		Usuario u = l.loginEncuentraAPI2(a);
		int idEmpresa = u.getIdEmpresa();
		
		List<DataEcommerce_canales_envio> envios = l.darListaEcommerce_canales_envio(canal,idEmpresa);
		
		return gson.toJson(envios);
		
	}	
	
	@GET
	@Path("/darArticuloEcommerceReqReclasifica")
			

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String darArticuloEcommerceReqReclasifica(String data,@QueryParam ("token") String a, @QueryParam ("idPedido") Long idPedido) throws IOException
	{	   
		Logica l = new Logica();
		
		Gson gson = new Gson();
		Usuario u = l.loginEncuentraAPI2(a);
		int idEmpresa = u.getIdEmpresa();
		
		DataArticuloEcommerceVerifR r = l.darArticuloEcommerceReqReclasifica(idPedido,0,idEmpresa);
		
	
		
		return gson.toJson(r);
		
	}	
	
	
	
	
	
	
	

}
