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
import beans.api.json.SendMailSpooler;



@Path("/MailSpooler")
public class MailSpooler 
{	
	@POST
	@Path("/putMails")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String putMails(String data,@QueryParam ("token") String a) throws IOException
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
				SendMailSpooler mails = gson.fromJson(data, SendMailSpooler.class);	
				
				if(mails==null)
				{
					System.out.println("No se ha recibido informacion");
					json = "No se ha recibido informacion";
				}
				else
				{
					LogicaAPI.PutMailSpooler(mails.getMails(), u.getIdEmpresa());
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
	
}
