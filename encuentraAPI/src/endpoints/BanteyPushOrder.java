package endpoints;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import logica.LogicaAPI;

import org.codehaus.jackson.map.ObjectMapper;

import beans.Usuario;
import beans.api.json.RetornoPedido;
import beans.encuentra.ArrayOfArticulos;
import beans.encuentra.ArrayOfMasters;
import beans.encuentra.ArrayOfOrders;
import beans.encuentra.Articulo;
import beans.encuentra.Maestros;
import beans.encuentra.Movimiento;

import beans.encuentra.MovimientosC;
import beans.encuentra.Ordenes;


@Path("/LOI")
public class BanteyPushOrder 
{
	
	@POST
	@Path("/pushOrder")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String createUser(String data,@QueryParam ("token") String a) throws IOException
	{
	   
		//System.out.println(data);
		
		data = data.replace("'","");
		
		//Logica.persistir("insert into ecommerce_venta_log (json) values ('"+data+"')");
		
		String mensaje ="";
		String json = "";
		int codResp=0;
		String ords="";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraBantey(a);
			
			if (u.getNick() == null) 
			{
				mensaje="access_token invalido";
				
			}
			else
			{
			
			
				ArrayOfOrders orders = new ArrayOfOrders();
				orders=orders.desempaquetar(data);
				
				Ordenes[] ordenes = orders.getOrdenes();
				if(ordenes.length==0)
				{
					mensaje="No se ha recibido ninguna orden de venta";
					codResp=0;
				}
				else
				{
					mensaje=LogicaAPI.ingresarOrdenBantey(ordenes);					
				}
			}
		}
		catch (Exception e) 
		{
			/*mensaje="ha ocurrido un error de tipo "+ e.getMessage();
			codResp=-1;*/
		}
		
		
		json= mensaje;
			return json;
	   }
	
	
	@POST
	@Path("/pushArticle")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String createUserArticulos(String data,@QueryParam ("token") String a) throws IOException
	{
	   
		//System.out.println(data);
		
		String mensaje ="";
		String json = "";
		int codResp=0;
		String ords="";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraBantey(a);
			
			if (u.getNick() == null) 
			{
				mensaje="access_token invalido";				
			}
			else
			{			
				ArrayOfArticulos articles = new ArrayOfArticulos();
				articles=articles.desempaquetar(data);
				
				Articulo[] arts = articles.getArticulos();
				if(arts.length==0)
				{
					mensaje="No se ha recibido ningun articulo";
					codResp=0;
				}
				else
				{
					mensaje=LogicaAPI.ingresarArticuloBantey(arts);					
				}
			}
		}
		catch (Exception e) 
		{
			/*mensaje="ha ocurrido un error de tipo "+ e.getMessage();
			codResp=-1;*/
		}
		
		
		json= mensaje;
			return json;
	   }

	
	@POST
	@Path("/pushMasters")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String createUserMaestros(String data,@QueryParam ("token") String a) throws IOException
	{
	   
		//System.out.println(data);
		
		String mensaje ="";
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraBantey(a);
			
			if (u.getNick() == null) 
			{
				mensaje="access_token invalido";				
			}
			else
			{			
				ArrayOfMasters masters = new ArrayOfMasters();
				masters=masters.desempaquetar(data);
				
				Maestros[] msts = masters.getMaestros();
				if(msts.length==0)
				{
					mensaje="No se ha recibido ningun maestro";
				}
				else
				{
					mensaje=LogicaAPI.ingresarMaestrosBantey(msts);					
				}
			}
		}
		catch (Exception e) 
		{
			/*mensaje="ha ocurrido un error de tipo "+ e.getMessage();
			codResp=-1;*/
		}
		
		
		json= mensaje;
			return json;
	   }
	
	
	@POST
	@Path("/pushEcommercePedido")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String createUserEcommercePedido(String data,@QueryParam ("token") String a) throws IOException
	{
	   
		//System.out.println(data);
		
		String mensaje ="";
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraBantey(a);
			
			if (u.getNick() == null) 
			{
				mensaje="access_token invalido";				
			}
			else
			{			
				ArrayOfOrders orders = new ArrayOfOrders();
				orders=orders.desempaquetar(data);
				
				Ordenes[] ordenes = orders.getOrdenes();
				if(ordenes.length==0)
				{
					mensaje="No se ha recibido ninguna orden de venta";
				}
				else
				{
					mensaje=LogicaAPI.ingresarOrdenBantey(ordenes);					
				}
			}
		}
		catch (Exception e) 
		{
			/*mensaje="ha ocurrido un error de tipo "+ e.getMessage();
			codResp=-1;*/
		}
		
		
		json= mensaje;
			return json;
	   }
	
	@POST
	@Path("/cancelOrder")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String CancelOrder(@QueryParam ("token") String a, @QueryParam ("idVenta") String idVenta) throws IOException
	{
	   
		//System.out.println(data);
		
		
		String mensaje ="";
		String json = "";
		int codResp=0;
		String ords="";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraBantey(a);
			
			if (u.getNick() == null) 
			{
				mensaje = "{\"ordenes\": [{\"idVenta\": \""+idVenta+"\",\"codRespuesta\": \"-1\" }]}";
				
			}
			else
			{ 
				//Logica.updateEcommerceEstado(Integer.parseInt(idVenta), 99);
				mensaje="{\"ordenes\": [{\"idVenta\": \""+idVenta+"\",\"codRespuesta\": \"1\" }]}";
			}
		}
		catch (Exception e) 
		{
			//e.printStackTrace();
			/*mensaje="ha ocurrido un error de tipo "+ e.getMessage();
			codResp=-1;*/
			mensaje = "{\"ordenes\": [{\"idVenta\": \""+idVenta+"\",\"codRespuesta\": \"0\" }]}";
		}
		
		
		json= mensaje;
			return json;
	   }
	
	@POST
	@Path("/alterOrder")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String alterOrder(String data,@QueryParam ("token") String a) throws IOException
	{
	   
		//System.out.println(data);
		//Logica.persistir("insert into ecommerce_venta_log (json) values ('"+data+"')");
		
		String mensaje ="";
		String json = "";
		int codResp=0;
		String ords="";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraBantey(a);
			
			if (u.getNick() == null) 
			{
				mensaje="access_token invalido";
				
			}
			else
			{
			
			
				ArrayOfOrders orders = new ArrayOfOrders();
				orders=orders.desempaquetar(data);
				
				Ordenes[] ordenes = orders.getOrdenes();
				if(ordenes.length==0)
				{
					mensaje="No se ha recibido ninguna orden de venta";
					codResp=0;
				}
				else
				{
					mensaje=LogicaAPI.modificarOrdenBantey(ordenes);					
				}
			}
		}
		catch (Exception e) 
		{
			/*mensaje="ha ocurrido un error de tipo "+ e.getMessage();
			codResp=-1;*/
		}
		
		
		json= mensaje;
			return json;
	   }
}
