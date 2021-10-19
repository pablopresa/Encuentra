package cliente_rest_Invoke;

import helper.PropertiesHelper;

import java.io.BufferedInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;



import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import jsonObjects.*;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescDescripcion;



public class Call3 {
	
	public DataIDDescDescripcion getWsData(){
		PropertiesHelper pH=new PropertiesHelper("scantech");
		DataIDDescDescripcion d=new DataIDDescDescripcion();
		try {
			pH.loadProperties();
			d.setDesc(pH.getValue("url"));
			d.setDescII(pH.getValue("empresa"));
			d.setDescripcion(pH.getValue("local"));
			return d;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
	}
	
	public static void main(String args[]) {
	       
		
		String URLbase = "https://res.cloudinary.com/dgaaedmn4/image/upload/";
		
		String funcion = "";
		
		
		//String jotason = "{  \"codigoEmpresa\": 4544,  \"codigoProveedor\": 2081,  \"fecha\": \"2017-02-16T17:22:25.000-0300\",  \"codigoMonedaCompra\": 85,  \"codigoMonedaVenta\": 85,  \"tipDocumento\": 6,  \"serie\": \"A\",  \"numero\": 10,  \"codigoDeposito\": 1,  \"codigoLocal\": 1,  \"redondeo\": 0,  \"totalIngresado\": 10,  \"totalCalculado\": 10,  \"factDetalle\": [    {      \"codigoArticulo\": \"000000000102325\",      \"numeroLinea\": 1,      \"unidades\": 10,      \"cantidad\": 1    }  ]} ";
		
		callWSPOST_ParamJSON(URLbase, funcion,"");
		

    }
	
	
	public void SubirI() 
	{
		DataIDDescDescripcion d=getWsData();
		String URLbase = "https://res.cloudinary.com/dgaaedmn4/image/upload/";
		
		String funcion = "";
		
		
		//String jotason = "{  \"codigoEmpresa\": 4544,  \"codigoProveedor\": 2081,  \"fecha\": \"2017-02-16T17:22:25.000-0300\",  \"codigoMonedaCompra\": 85,  \"codigoMonedaVenta\": 85,  \"tipDocumento\": 6,  \"serie\": \"A\",  \"numero\": 10,  \"codigoDeposito\": 1,  \"codigoLocal\": 1,  \"redondeo\": 0,  \"totalIngresado\": 10,  \"totalCalculado\": 10,  \"factDetalle\": [    {      \"codigoArticulo\": \"000000000102325\",      \"numeroLinea\": 1,      \"unidades\": 10,      \"cantidad\": 1    }  ]} ";
		
		String retorno = Call3.callWSPOST_ParamJSON(URLbase, funcion,"");
		
		System.out.println(retorno);
		
				
		
		
	}
	
	
	
	
	
	
	
	private static String callWSPOST_ParamJSON(String URLbase, String funcion, String jotason)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
	    ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("563526861483215", "RajhsKwsNtN9GpUhRbHe_aSeKJE"));
	    try 
		{
	    	System.out.println("llamando a "+URLbase+funcion);
	    	System.out.println("Parametros JSON "+jotason);
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost(URLbase+funcion);
	    	
	    	StringEntity params =new StringEntity(jotason);
	    	httpPostRequest.setHeader("Content-type", "application/json");
	    	httpPostRequest.setHeader("Content-type", "application/json");
	    	
	    	httpPostRequest.setEntity(params);
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpPostRequest);
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
		          httpPostRequest.abort();
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
		return retorno;
		
	}
	
	

	
	
	
	public static String login()
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
	    ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("apiuser", "apiuser"));
	    try 
		{
	    
	    	
	    	System.out.println("Respuesta");
	    	
	    	HttpPost httpPostRequest = new HttpPost("http://200.58.149.126:8889/token");
	    	httpPostRequest.setHeader("Content-type", "application/x-www-form-urlencoded");
	    	
	    	
	    	
	    	
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpPostRequest);
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
		          System.out.println(strFileContents);
		          return strFileContents;
		          
		          
		        }
		        catch (RuntimeException runtimeException) 
		        {
		          // In case of an unexpected exception you may want to abort
		          // the HTTP request in order to shut down the underlying
		          // connection immediately.
		          httpPostRequest.abort();
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
	

	

	private String callWSGETPrint()
	{
		String retorno = "";
		
		HttpClient httpClient = new DefaultHttpClient();
		
	    ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("200SuperNow.39411", "ab3616a9bf32f218cad693da699ee2db352b5d1a"));
	    try 
		{
	    	HttpGet httpGetRequest = new HttpGet("https://app.printnode.com/api/printers");
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpGetRequest);
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

		          
		          
		          System.out.println(strFileContents); ;
		          
		          
		          
		        }
		        catch (RuntimeException runtimeException) 
		        {
		          // In case of an unexpected exception you may want to abort
		          // the HTTP request in order to shut down the underlying
		          // connection immediately.
		          httpGetRequest.abort();
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
		return retorno;
		
	}
	
	
	
	public static String login2(String host)
	{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://"+host+":8889/token");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		// post.setHeader("Accept", "application/x-www-form-urlencoded");
		JSONObject obj = new JSONObject();
		try {
		    
		    post.setEntity(new StringEntity("grant_type=password&username=apiuserOSO&password=apiuserOSO", "UTF-8"));
		    HttpResponse response = client.execute(post);
		    HttpEntity entity = response.getEntity();
		    String results = EntityUtils.toString(entity);
		    obj = new JSONObject(results);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return obj.getString("access_token");
	}
	
	
	public static String login3(String host)
	{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://"+host+":8889/token");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		// post.setHeader("Accept", "application/x-www-form-urlencoded");
		JSONObject obj = new JSONObject();
		try {
		    
		    post.setEntity(new StringEntity("grant_type=password&username=apiuser&password=apiuser", "UTF-8"));
		    HttpResponse response = client.execute(post);
		    HttpEntity entity = response.getEntity();
		    String results = EntityUtils.toString(entity);
		    obj = new JSONObject(results);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return obj.getString("access_token");
	}
	
	
	
	
	public static String envioSalesOrder(String jsonSTR,String token,String host)
	{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://"+host+":8889/ArgentisApi/AddDocument?pDocuments=SalesOrder");
		//HttpPost post = new HttpPost("http://200.58.149.126:8889/ArgentisApi/AddDocument?pDocuments=GoodsIssue");
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Authorization", "Bearer "+token);
		String retorno = "";
		
		JSONObject obj = new JSONObject();
		try 
		{
		    post.setEntity(new StringEntity(jsonSTR));
		    HttpResponse response = client.execute(post);
		    HttpEntity entity = response.getEntity();
		    retorno = EntityUtils.toString(entity);
		    
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			return "";
		}
		System.out.println(retorno);
		return retorno;
	}
	
	
	public static String envioDatos(String jsonSTR,String token,String host,String pDocuments )
	{
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://"+host+":8889/ArgentisApi/AddDocument?pDocuments="+pDocuments);
		//HttpPost post = new HttpPost("http://200.58.149.126:8889/ArgentisApi/AddDocument?pDocuments=GoodsIssue");
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Authorization", "Bearer "+token);
		String retorno = "";
		
		JSONObject obj = new JSONObject();
		try 
		{
		    post.setEntity(new StringEntity(jsonSTR));
		    HttpResponse response = client.execute(post);
		    HttpEntity entity = response.getEntity();
		    retorno = EntityUtils.toString(entity);
		    
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			return "";
		}
		System.out.println(retorno);
		return retorno;
	}
	
	
	
	
	
	
	private String callWSPOSTPrint(String jotason)
	{
		System.out.println("json: ");
		System.out.println(jotason);
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("200SuperNow.39411", "ab3616a9bf32f218cad693da699ee2db352b5d1a"));
	    try 
		{
	    	
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost("https://api.printnode.com/printjobs");
	    	
	    	StringEntity params =new StringEntity(jotason);
	    	httpPostRequest.setHeader("Content-type", "application/json");
	    	
	    	httpPostRequest.setEntity(params);
	    	
	    	
	    	
	    	
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpPostRequest);
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

		          System.out.println(strFileContents);
		          return strFileContents;
		          
		          
		        }
		        catch (RuntimeException runtimeException) 
		        {
		          // In case of an unexpected exception you may want to abort
		          // the HTTP request in order to shut down the underlying
		          // connection immediately.
		          httpPostRequest.abort();
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
		return retorno;
		
	}



	

	
	
	

}
