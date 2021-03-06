package beans.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class callHttepClient {
		//POST
		private static String callWSPOST_ParamJSON_Credential(String URLbase, String funcion, String jotason, int idCanal, String usu, String pass)
		{
			
			String retorno = "";
			HttpClient httpClient = new DefaultHttpClient();
			 ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
					 new UsernamePasswordCredentials(usu,pass));//"std_comuy", "STD#Tck_%$774$3"));
		    try 
			{
		    	System.out.println("llamando a "+URLbase+funcion);
		    	System.out.println("Parametros JSON "+jotason);
		    	System.out.println("Respuesta");
		    	HttpPost httpPostRequest = new HttpPost(URLbase+funcion);
		    	
		    	StringEntity params =new StringEntity(jotason);
		    	httpPostRequest.setHeader("Content-type", "application/x-www-form-urlencoded");
		    	
		    	//httpPostRequest.setEntity(params);
		    	
		    	ArrayList<NameValuePair> postParameters;
		    	postParameters = new ArrayList<>();
		        postParameters.add(new BasicNameValuePair("compras", jotason));
		        

		        httpPostRequest.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		    	
		    	
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
		
		private static String callWSPOST_ParamJSON(String URLbase,String funcion, String jotason,String token)
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
		
		//GET CLIENT
		private static HttpClient getHttpClient(String u, String p) 
		{

		    try {
		        //SSLContext sslContext = SSLContext.getInstance("SSL");

		       
		        //SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		        HttpClientBuilder construct = HttpClientBuilder.create();//.setSSLSocketFactory(socketFactory); 
		        
		        CredentialsProvider provider = new BasicCredentialsProvider();
				UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(u, p);
				provider.setCredentials(AuthScope.ANY, credentials);
				
				construct.setDefaultCredentialsProvider(provider);
				

		        HttpClient httpClient = construct.build();

		        return httpClient;

		    } catch (Exception e) {
		        e.printStackTrace();
		        return HttpClientBuilder.create().build();
		    }
		}
		
		//GET
		public static String callWSGET_Credential(String URLbase, String funcion, int canal, String usu, String pass)
		{
			String retorno = "";
			//HttpClient httpClient = new DefaultHttpClient();
			
			HttpClient httpClient = getHttpClient(usu, pass);
			
		    //((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials(usu,pass));//"std_comuy", "STD#Tck_%$774$3"));
		    try 
			{
		    	HttpGet httpGetRequest = new HttpGet(URLbase+funcion);
		    	
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

			          return strFileContents;
			          
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
		
		private static String callWSGET(String URLbase, String funcion, List<String> parameters,List<String> values, String token)
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
