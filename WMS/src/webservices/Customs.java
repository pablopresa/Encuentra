package webservices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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

import beans.Usuario;
import beans.encuentra.DepositoAdmin;
import dataTypes.ColectionDataIDDescripcion;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import persistencia.MSSQL;




@Path("/Customs")
public class Customs 
{
	
	@GET
	@Path("/distribucionForuspedidos")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String stocks(String data, @QueryParam ("distribuciones") String distribuciones, @QueryParam ("token") String token) throws IOException
	{	   
		Logica logica = new Logica();	
		Gson gson = new Gson();
		String json = "";
		Hashtable<Integer, List<DataDescDescripcion>> pedidosDist = new Hashtable<>();
		
		try
		{		
			Usuario u = logica.loginEncuentraAPI2(token);
			
			if(u!=null) {
				pedidosDist = logica.darIdPedidosXDistri(distribuciones, u.getIdEmpresa());
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		json = gson.toJson(pedidosDist);
		System.out.println(json);
		return json;
	}
	
	
	
	
}
