package endpoints;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import logica.LogicaAPI;


import beans.Usuario;
import beans.encuentra.Movimiento;

import beans.encuentra.MovimientosC;

@Path("/BITs")
public class UnilamSetMovStock 
{
	
	@POST
	@Path("/SetMovStockToMyWhs")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String createUser(String data,@QueryParam ("token") String a) throws IOException
	{
	   
	  
		
		//System.out.println(data);
		
		String mensaje ="";
		String json = "";
		int codResp=0;
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				mensaje="access_token invalido";
				
			}
			
			
			
			MovimientosC m = new MovimientosC();
			m=m.desempaquetar(data);
			
			List<Movimiento> movs = m.getMovimientos();
			if(movs.isEmpty())
			{
				mensaje="No se ha recibido ningun movimiento de Stock";
				codResp=0;
			}
			else
			{
				String movsIn = "";
				for (Movimiento mo : movs) 
				{
					movsIn+=mo.getNroDoc()+" ";
				}
				
				
				System.out.println(m.getMovimientos().size());

				
				mensaje="Movimientos recibidos "+movsIn;
				codResp=1;
			}
			
			
		}
		catch (Exception e) 
		{
			mensaje="ha ocurrido un error de tipo "+ e.getMessage();
			codResp=-1;
		}
		
		
		json= "{"+
				  "\"mensaje\": \""+mensaje+"\","+
				  "\"cod_respuesta\":\""+codResp+"\""+
				"}";
		
		
	      
		
		return json;
	   }

	
}
