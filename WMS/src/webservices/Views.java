package webservices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import beans.Usuario;
import beans.encuentra.DepositoAdmin;
import dataTypes.ColectionDataIDDescripcion;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import persistencia.MSSQL;




@Path("/Views")
public class Views 
{
	
	@POST
	@Path("/stocks")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String stocks(String data, @QueryParam ("origen") String origen, @QueryParam ("token") String token) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		String json = "";
		Hashtable<String, Integer> retorno = new Hashtable<>();
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(token);
			
			if(u!=null) {
				List<DataIDDescripcion> arts = new ArrayList<>();
				arts = gson.fromJson(data, ColectionDataIDDescripcion.class).getColeccion();
				retorno = MSSQL.darStocks(arts, Integer.parseInt(origen));	
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		json = gson.toJson(retorno);
		json = "{ \"coleccion\" : "+json+"}";
		System.out.println(json);
		return json;
	}
	
	
	@POST
	@Path("/stockQ")

	//recibe un query
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String stocksQ(String data,  @QueryParam ("token") String token) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		String json = "";
		Map<String, DataIDDescripcion> retorno = new HashMap<>();
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(token);
			
			if(u!=null) 
			{
				
				DataIDDescripcion q = gson.fromJson(data, DataIDDescripcion.class);
				retorno = darStock(q.getDescripcion());
				
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		json = gson.toJson(retorno);
		return json;
	}
	
	public Map<String, DataIDDescripcion> darStock(String query){
		
		Logica logica = new Logica();
		
		List<DataIDDescripcion> lista = logica.darListaDataIdDescripcionConsulMYSQL(query);
		
		Map<String, DataIDDescripcion> retorno = new HashMap<>();
		
		for (DataIDDescripcion a : lista) 
			retorno.put(a.getDescripcion(), a);
		
		return retorno;
	}
	
	@POST
	@Path("/parametros")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String parametros(String data,  @QueryParam ("token") String token) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		String json = "";
		Hashtable<Integer, String> retorno = new Hashtable<>();
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(token);
			
			if(u!=null) {
				int idEmpresa = u.getIdEmpresa();	
				List <DataIDDescripcion> parametros =  l.darParametrosEmpresa(idEmpresa, data);
				for (DataIDDescripcion p : parametros) 
				{
					retorno.put(p.getId(), p.getDescripcion());
				}
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		json = gson.toJson(retorno);
		json = "{ \"coleccion\" : "+json+"}";
		System.out.println(json);
		return json;
	}
	
	
	@POST
	@Path("/depositosEcommerce")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String depositosEcommerce(String data,  @QueryParam ("token") String token,  @QueryParam ("canal") int canal) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		String json = "";
		Hashtable<String, Integer> retorno = new Hashtable<>();
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(token);
			
			if(u!=null) 
			{
				int idEmpresa = u.getIdEmpresa();	
				int id_de_Deposito_Principal = l.darDepositoCentralCanal(idEmpresa, canal);
				int id_de_Deposito_Ecommerce  = l.darDepositoEcommerceCanal(idEmpresa, canal);
				
				retorno.put("id_de_Deposito_Principal", id_de_Deposito_Principal);
				retorno.put("id_de_Deposito_Ecommerce", id_de_Deposito_Ecommerce);
				
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		json = gson.toJson(retorno);
	
		System.out.println(json);
		return json;
	}
	
	
	@POST
	@Path("/depositos")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String depositos(String data,  @QueryParam ("token") String token) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		String json = "";
		List <DepositoAdmin> retorno = new ArrayList<>();
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(token);
			
			if(u!=null) 
			{
				int idEmpresa = u.getIdEmpresa();	
				retorno =  l.encuentraDarDepositosAdmin(0,idEmpresa);
				
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		json = gson.toJson(retorno);
		
		
		return json;
	}
	
	@POST
	@Path("/prod")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String prod(String data,  @QueryParam ("token") String token) throws IOException
	{	   
		Logica l = new Logica();	
		Gson gson = new Gson();
		String json = "";
		Hashtable<Integer, String> retorno = new Hashtable<>();
		
		try
		{		
			Usuario u = l.loginEncuentraAPI2(token);
			
			if(u!=null) {
				int idEmpresa = u.getIdEmpresa();	
				List <DataIDDescripcion> integraciones =  l.darIntegracionesEmpresa(idEmpresa);
				for (DataIDDescripcion p : integraciones) 
				{
					retorno.put(p.getId(), p.getDescripcion());
				}
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		json = gson.toJson(retorno);
		json = "{ \"coleccion\" : "+json+"}";
		System.out.println(json);
		return json;
	}
	
}
