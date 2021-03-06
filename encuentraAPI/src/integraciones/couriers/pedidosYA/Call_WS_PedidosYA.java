package integraciones.couriers.pedidosYA;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import beans.Fecha;
import beans.api.json.Cliente;
import beans.api.json.Credenciales;
import beans.api.json.Shipping;
import beans.datatypes.DataDescDescripcion;
import beans.helper.PropertiesHelper;
import beans.helper.PropertiesHelperAPI;
import integraciones.couriers.districad.DISTRIaltaEnvio;
import logica.LogicaAPI;

public class Call_WS_PedidosYA {
	
	
	public String login (int idEmpresa, Credenciales cr)
	{
		LogicaAPI l = new LogicaAPI();
		String tok =l.darTokenVivoCliente(idEmpresa, "PedidosYa", 45);
		
		if(tok==null)
		{
			
			CredencialesPeYa cre = new CredencialesPeYa();
			cre.setClient_id(cr.getIdCliente());
			cre.setPassword(cr.getPass());
			cre.setUsername(cr.getUser());
			cre.setClient_secret(cr.getToken());
			cre.setGrant_type("password");
			
			
			Gson gson = new Gson();
			String json = gson.toJson(cre);
			
			
			
			String salida = callWSPOST_ParamJSON("https://auth-api.pedidosya.com/v1/token",	json);
			
			
			
			CredencialesPeYaResponse out = gson.fromJson(salida, CredencialesPeYaResponse.class);
			l.actualizarokenVivoCliente(idEmpresa, "PedidosYa",out.getAccess_token());
			return out.getAccess_token();
			
			
		}
		else
		{
			return tok;
		}
		
		
		
	}
	
	
	
	public OutputDespachoPeya setEnvio(Shipping shipp, int idEmpresa) 
	{
		String accessToken = login(idEmpresa, shipp.getCredenciales());
		
		OutputDespachoPeya out = new OutputDespachoPeya();
		try {
			DespachoPeYA despacho = new DespachoPeYA();
			
			despacho.setIsTest(false);
			
			Items item = new Items();
			item.setCategoryId(shipp.getTipoShipping()+"");
			item.setDescription("Pedido ecommerce"+shipp.getCredenciales().getPedido());
			item.setQuantity(shipp.getCantidadPaquetes()+"");
			item.setValue(shipp.getCredenciales().getPrecio());
			item.setVolume( (Double.parseDouble(shipp.getVolumen())/shipp.getCantidadPaquetes())+"" );
			item.setWeight((Double.parseDouble(shipp.getPeso())/shipp.getCantidadPaquetes())+"" );
			
			Items[] items = {item};
			
			despacho.setItems(items);
				
			
			despacho.setDeliveryTime(shipp.getCredenciales().getFecha()+"T"+shipp.getCredenciales().getHora());
			despacho.setNotificationMail(shipp.getMailNotificacion());
			despacho.setReferenceId(shipp.getCredenciales().getPedido());
			despacho.setVolume(shipp.getVolumen());
			despacho.setWeight(shipp.getPeso());
			
			
			Waypoints origen = new Waypoints();
			
			origen.setAddressStreet(shipp.getSender().getCalle()+" "+shipp.getSender().getNroPuerta()+" "+shipp.getSender().getNroApto());
			origen.setAddressAdditional(shipp.getSender().getObs());
			origen.setCity(shipp.getSender().getCiudad());
			origen.setName(shipp.getSender().getNombre()+" "+shipp.getSender().getApellido());
			origen.setOrder("1");
			origen.setType("PICK_UP");
			origen.setPhone(shipp.getSender().getTelefono());
			
			
			Waypoints destino = new Waypoints();
			
			destino.setAddressStreet(shipp.getCliente().getCalle()+" "+shipp.getCliente().getNroPuerta()+" "+shipp.getCliente().getNroApto());
			destino.setAddressAdditional(shipp.getCliente().getObs());
			destino.setCity(shipp.getCliente().getCiudad());
			destino.setName(shipp.getCliente().getNombre()+" "+shipp.getCliente().getApellido());
			destino.setOrder("2");
			destino.setType("DROP_OFF");
			destino.setPhone(shipp.getCliente().getTelefono());
			
			
			Waypoints [] waypoints = {origen,destino};
			
			despacho.setWaypoints(waypoints);
			
			
			Gson gson = new Gson();
			String json = gson.toJson(despacho);
			
			
			
			String salida = callWSPOST_ParamJSON_OAUTH("https://courier-api.pedidosya.com/v1/shippings",json,accessToken);
			System.out.println(salida);
			out = gson.fromJson(salida, OutputDespachoPeya.class);
			
			if(out.getId()==null)
			{
				out = new OutputDespachoPeya();
				out.setStatus(salida);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out;		
	}
	
	
	public OutputDespachoPeya confirmarEnvio(Credenciales cre,String tracking, int idEmpresa) 
	{
		String accessToken = login(idEmpresa, cre);
		
		OutputDespachoPeya out = new OutputDespachoPeya();
		try {
			
			String json = "";
			

			Gson gson = new Gson();
			
			String salida = callWSPOST_ParamJSON_OAUTH("https://courier-api.pedidosya.com/v1/shippings/"+tracking+"/confirm",json,accessToken);
			System.out.println(salida);
			out = gson.fromJson(salida, OutputDespachoPeya.class);
			
			System.out.println(out.getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out;		
	}
	
	
	private String callWSPOST_ParamJSON(String URLbase, String jotason)
	{
		String retorno = "";
		HttpClient httpClient = HttpClientBuilder.create().build();
		try 
		{	    	
	    	System.out.println("llamando a "+URLbase);
	    	System.out.println("Parametros JSON "+jotason);
	    	System.out.println("Respuesta");

	    	String payload = jotason;
	    	StringEntity entityI;
	    	
	    	entityI = new StringEntity(payload, ContentType.APPLICATION_JSON);
	    
	    	HttpPost request = new HttpPost(URLbase);
	    	request.setEntity(entityI);
	    	
		  	HttpResponse httpResponse = httpClient.execute(request);
		  	
		  	
		  	int code=httpResponse.getStatusLine().getStatusCode();
		  	HttpEntity entity = httpResponse.getEntity();
		  	
		  	byte[] buffer = new byte[1024];
		    
		  	if (entity != null) 
		  	{
		        InputStream inputStream = entity.getContent();
		        try 
		        {
		          int bytesRead = 0;
		          BufferedInputStream bis = new BufferedInputStream(inputStream);
		          byte[] contents = new byte[1024];
		          String strFileContents=""; 
		          while((bytesRead = bis.read(contents)) != -1) 
		          { 
		              strFileContents += new String(contents, 0, bytesRead);              
		          }

		          return strFileContents;
		          
		          
		        }
		        catch (RuntimeException runtimeException) 
		        {
		          // In case of an unexpected exception you may want to abort
		          // the HTTP request in order to shut down the underlying
		          // connection immediately.
		          request.abort();
		          runtimeException.printStackTrace();
		        }
		        finally 
		        {
		          // Closing the input stream will trigger connection release
		          try 
		          {
		            inputStream.close();
		          } 
		          catch (Exception ignore) 
		          {
		        	  
		          }
		        }
		      }
		    } 
	    	catch (ClientProtocolException e) 
		    {
		      e.printStackTrace();
		    }
	    	catch (IOException e) 
	    	{
		      // thrown by entity.getContent();
		      e.printStackTrace();
		    }
	    	finally 
	    	{
		      httpClient.getConnectionManager().shutdown();
		    }
		System.out.println(retorno);
		return retorno;
		
	}
	
	
	
	private String callWSPOST_ParamJSON_OAUTH(String URLbase, String jotason, String token)
	{
		String retorno = "";
		HttpClient httpClient = HttpClientBuilder.create().build();
		try 
		{	    	
	    	System.out.println("llamando a "+URLbase);
	    	System.out.println("Parametros JSON "+jotason);
	    	System.out.println("Respuesta");

	    	String payload = jotason;
	    	StringEntity entityI;
	    	
	    	entityI = new StringEntity(payload, ContentType.APPLICATION_JSON);
	    
	    	HttpPost request = new HttpPost(URLbase);
	    	request.setEntity(entityI);
	    	
	    	request.addHeader("Authorization", token);
	    	
		  	HttpResponse httpResponse = httpClient.execute(request);
		  	
		  	
		  	int code=httpResponse.getStatusLine().getStatusCode();
		  	HttpEntity entity = httpResponse.getEntity();
		  	
		  	byte[] buffer = new byte[1024];
		    
		  	if (entity != null) 
		  	{
		        InputStream inputStream = entity.getContent();
		        try 
		        {
		          int bytesRead = 0;
		          BufferedInputStream bis = new BufferedInputStream(inputStream);
		          byte[] contents = new byte[1024];
		          String strFileContents=""; 
		          while((bytesRead = bis.read(contents)) != -1) 
		          { 
		              strFileContents += new String(contents, 0, bytesRead);              
		          }

		          return strFileContents;
		          
		          
		        }
		        catch (RuntimeException runtimeException) 
		        {
		          // In case of an unexpected exception you may want to abort
		          // the HTTP request in order to shut down the underlying
		          // connection immediately.
		          request.abort();
		          runtimeException.printStackTrace();
		        }
		        finally 
		        {
		          // Closing the input stream will trigger connection release
		          try 
		          {
		            inputStream.close();
		          } 
		          catch (Exception ignore) 
		          {
		        	  
		          }
		        }
		      }
		    } 
	    	catch (ClientProtocolException e) 
		    {
		      e.printStackTrace();
		    }
	    	catch (IOException e) 
	    	{
		      // thrown by entity.getContent();
		      e.printStackTrace();
		    }
	    	finally 
	    	{
		      httpClient.getConnectionManager().shutdown();
		    }
		System.out.println(retorno);
		return retorno;
		
	}
	
	
}
