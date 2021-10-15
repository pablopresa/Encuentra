package integraciones.marketplaces.moddo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import com.google.gson.Gson;

import beans.datatypes.DataIDDescripcion;
import integraciones.marketplaces.fenicioTrack.ColectionUpdateState;
import integraciones.marketplaces.objetos.UpdateStateResponse;
import integraciones.marketplaces.objetos.marketPlace;
import integraciones.marketplaces.objetos.marketPlaceInterface;

public class Moddo extends marketPlace{

//CONSTRUCTOR
	public Moddo() {
		this.setIdEmpresa(2);
		this.setIdMarketPlace(2);
		this.setCanales();
	}
	
private String token = "";	
		
public String getToken(int canal){
		
		String host = this.canales.get(canal).getHost();
		String url = "login/authenticate";	/*QA*/
		String json = "{\"userName\": \"Nviera\", \"password\": \"Nviera200!\"}";
		String token = "";
		try {			
			token = callWSPOST_ParamJSON(host,url, json,"");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return token;		
	}
	
	@Override
	public List<UpdateStateResponse> UpdateState(String json, int canal) {
		List<UpdateStateResponse> salida = new ArrayList<>();
		try {
			String host = this.canales.get(canal).getHost();			
			String funcion = "rest/orders/updateOrders/idSite/"+this.canales.get(canal).getSeller();
			if(this.token.equals("")) {
				this.token = this.getToken(canal);
			}
			
			String retorno = callWSPOST_ParamJSON(host, funcion, json, this.token);
			Gson gson = new Gson();
			salida.add(gson.fromJson(retorno, ModdoUpdateStateResponse.class).darRespuesta());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}
	
	private String callWSPOST_ParamJSON(String URLbase,String funcion, String jotason,String token)
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
	    
	    	HttpPost request = new HttpPost(URLbase+funcion);
	    	request.setEntity(entityI);
	    	
	    	if(!token.equals(""))
	        {
	        	request.setHeader("core-token",token);
	        }
	    	
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
	
	private String callWSGET(String URLbase, String funcion, List<String> parameters,List<String> values, String token)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
	    try 
		{
	    	
	    	System.out.println("Respuesta");
	    	HttpResponse httpResponse;
	    	
    		HttpParams params = new BasicHttpParams();
	        
	        int pasada=0;
	        String q="";
	    	
	        for (String par : parameters) 
	        {	
	        	q+="?";
	    		q+=par+"="+values.get(pasada)+"&";
				pasada++;
			}
	    	
	        /*
	        OkHttpClient client = new OkHttpClient();
	        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
	        RequestBody body = RequestBody.create(mediaType, "%20modificacionDat=%2020190909");
	        Request request = new Request.Builder()
	          .url("http://banteysa.dyndns.org:8080/OpusEncuentraWMS/wms/services/getProducts")
	          .get()
	          .addHeader("Content-Type", "application/x-www-form-urlencoded")
	          .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcHVzUGF0aCI6Ii9ob21lL3BydWViYSIsImV4cCI6MTU2ODA3ODkyOCwiaWF0IjoxNTY4MDUwMTI4LCJlbnRlcnByaXNlIjoiMDAwMCIsInVzZXJuYW1lIjoiRU5DVUVOVFJBIn0.O42CmKzVi9WiJj1r9rrZASjCWO117gr4eTb2Oejv-IY")
	          
	          .addHeader("Host", "banteysa.dyndns.org:8080")
	          .build();
	        Response response = client.newCall(request).execute();
	        String jsonData = response.body().string();
	        System.out.println( jsonData);
	        */
	        
	        HttpGet httpPostRequest = new HttpGet(URLbase+funcion+q);
	        System.out.println("llamando a "+URLbase+funcion+q);
	    	if(!token.equals(""))
	        {
	        	httpPostRequest.setHeader("core-token",token);
	        }
	    	httpResponse = httpClient.execute(httpPostRequest);
    		
	    	
	    	
	    	
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
		          //httpPostRequest.abort();
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
