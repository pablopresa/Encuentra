package endpoints;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import logica.LogicaAPI;


import com.google.gson.Gson;

import beans.Usuario;
import beans.api.EquiposPrintSpool;
import beans.api.json.PrintObject;


@Path("/PrintSpooler")
public class PrintSpooler 
{	
	@POST
	@Path("/put")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String put(String data,@QueryParam ("token") String a) throws IOException
	{	   
		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{		
				Gson gson = new Gson();
				PrintObject print = gson.fromJson(data, PrintObject.class);	
				
				if(print==null)
				{
					System.out.println("No se ha recibido informacion");
					json = "No se ha recibido informacion";
				}
				else
				{
					LogicaAPI.PutPrintSpooler(print,u.getIdEmpresa());
					json = "ok";
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
			return json;
	   }	


@POST
@Path("/reprint")

//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//public String createUser(@FormParam("name") String name) throws IOException
public String reprint(String data,@QueryParam ("token") String a) throws IOException
{	   
	System.out.println(data);
	
	String json = "";
	try
	{
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);
		
		if (u.getNick() == null) 
		{
			System.out.println("access_token invalido");
			json = "access_token invalido";
		}
		else
		{		
			Gson gson = new Gson();
			PrintObject print = gson.fromJson(data, PrintObject.class);	
			
			if(print==null)
			{
				System.out.println("No se ha recibido informacion");
				json = "No se ha recibido informacion";
			}
			else
			{
				LogicaAPI.RePrintSpooler(print,u.getIdEmpresa());
				json = "ok";
			}
		}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
		return json;
}


@POST
@Path("/getPrinters")

//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//public String createUser(@FormParam("name") String name) throws IOException
public String getPrinters(String data,@QueryParam ("token") String a) throws IOException
{	   
	System.out.println(data);
	String json = "";
	
	try
	{
		Usuario u = LogicaAPI.loginEncuentraAPI2(a);
		
		if (u.getNick() == null) 
		{
			System.out.println("access_token invalido");
			json = "access_token invalido";
		}
		else
		{		
			Gson gson = new Gson();
			//-1 es todos
			Entero idEquipo = gson.fromJson(data, Entero.class);
			
			
			int idEmpresa = u.getIdEmpresa();
			
			if(idEquipo==null)
			{
				System.out.println("No se ha recibido informacion");
				json = "No se ha recibido informacion";
			}
			else
			{
				
				EquiposPrintSpool equipos =  LogicaAPI.darListaEquiposPrintSpool(idEquipo.getIdEquipo(),idEmpresa);
				json = gson.toJson(equipos);
			}
		}
	}
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	
		return json;
}

}

class Entero 
{
	Integer idEquipo;

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Entero() {
		super();
	}
	
	
	
}

