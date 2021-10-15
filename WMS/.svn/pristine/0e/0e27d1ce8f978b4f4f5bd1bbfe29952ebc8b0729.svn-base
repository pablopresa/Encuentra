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

import jsonObjects.*;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescDescripcion;



public class Call {
	
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
	public int stockEnScantech(String barcode){
		DataIDDescDescripcion d=getWsData();
		String URLbase = d.getDesc();
		String retorno=this.callWSGET(URLbase, "minoristas/"+d.getDescII()+"/locales/"+d.getDescripcion()+"/stock/codigo-barra/"+barcode+"/");
		//int stock=JSONReader.getStock(retorno);
		return Integer.parseInt(retorno);
		//return JSONReader.getStock(retorno);
	}
	
	public List<DataDescDescripcion> getProveedores(Integer idProveedor, boolean open)
	{
		List<DataDescDescripcion> proveedoresRetorno = new ArrayList<>();
		String openOC = "true";
		if(!open)
		{
			openOC="false";
		}
		String retorno = "";
		DataIDDescDescripcion d=getWsData();
		String URLbase = d.getDesc();
		if(idProveedor==null)
		{
			 retorno = this.callWSGET(URLbase, "minoristas/"+d.getDescII()+"/locales/"+d.getDescripcion()+"/proveedores?filtrarConOCAbierta="+openOC+"&idProveedor=&razonSocial=&skip=0&take=300");
		}
		else
		{
			retorno = this.callWSGET(URLbase, "minoristas/"+d.getDescII()+"/locales/"+d.getDescripcion()+"/proveedores?filtrarConOCAbierta="+openOC+"&idProveedor="+idProveedor+"&razonSocial=&skip=0&take=300");
		}
		
		
		List<JSONProveedor> proveedores =JSONReader.readJsonProveedor(retorno);
        System.out.println("Se obtubieron "+proveedores.size()+" Proveedores");
        Hashtable<String, DataDescDescripcion> provHT = new Hashtable<>();
        for (JSONProveedor p : proveedores) 
        {
        	
        	provHT.put(p.getIdProveedor(),new DataDescDescripcion(String.valueOf(p.getIdProveedor()), p.getRazonSocial()));
      	  /*
      	  System.out.println("id: \n "+p.getIdProveedor()+" \n Proveedor: \n "+p.getRazonSocial()+"  \n RUT: \n "+p.getRazonSocial()+" \n Telefono: \n "+p.getTelefono() +"\n -------------------------");
      	  System.out.println("LISTAR OC PARA EL PROV "+p.getIdProveedor());
      	  List<JSONCabezalOC> cabezales = this.getOCProveedor(Integer.parseInt(p.getIdProveedor()), null, null, open); 
      	  if(!cabezales.isEmpty())
      	  {
      		proveedoresRetorno.add(new DataDescDescripcion(String.valueOf(p.getIdProveedor()), p.getRazonSocial()));
      		for (JSONCabezalOC j : cabezales) 
      		{
				System.out.println(j.getCodigo()+" - "+j.getFechaEmision());
			}
      	  }
			*/
        }
        proveedoresRetorno = new ArrayList<>(provHT.values());
        Collections.sort(proveedoresRetorno);
		return proveedoresRetorno;
	}
	
	
	
	public String cllPrint(String documento, int printerID)
	{
		String retorno = "";
		
		//String jotason = "{ \"printerId\":218257, \"title\": \"Prueba_encuentra\", \"contentType\": \"pdf_uri\", \"content\": \"http:\\/\\/encuentra.200.com.uy\\/supernow\\/pdf\\/"+documento+".pdf\", \"source\": \"encuentra\" }";
		String jotason = "{ \"printerId\":"+printerID+", \"title\": \"Prueba_encuentra\", \"contentType\": \"pdf_uri\", \"content\": \"http:\\/\\/encuentra.200.com.uy\\/supernow\\/pdf\\/"+documento+"\", \"source\": \"encuentra\" }";
		retorno = this.callWSPOSTPrint(jotason);
		return retorno;
		
	}
	
	
	
	
	
	public List<JSONCabezalOC> getOCProveedor(Integer idProveedor, String serie, Integer numero, boolean open)
	{
		String retorno = "";
		
		String openOC = "true";
		if(!open)
		{
			openOC="false";
		}
		
		
		DataIDDescDescripcion d=getWsData();
		String URLbase = d.getDesc();
		if(numero != null && serie !=null)
		{
			retorno = this.callWSGET(URLbase, "/minoristas/"+d.getDescII()+"/locales/"+d.getDescripcion()+"/proveedores/"+idProveedor+"/ordenesCompra?numero="+numero+"&serie="+serie+"&skip=0&take=300");
		}
		else
		{
			retorno = this.callWSGET(URLbase,"/minoristas/"+d.getDescII()+"/locales/"+d.getDescripcion()+"/proveedores/"+idProveedor+"/ordenesCompra?numero=&serie=&skip=0&take=300");
		}
		System.out.println(retorno);
		if(!retorno.equals("[]"))
		{
			List<JSONCabezalOC> ordenes =JSONReader.readJsonCabezalOC(retorno);
	        System.out.println("Se obtubieron "+ordenes.size()+" ordenes");
	        for (JSONCabezalOC p : ordenes) 
	        {
	      	  
	      	  System.out.println(p.getNumero()+ " - "+ p.getSerie()+ " - "+p.getFechaEmision());
	      	  p.setDetalles(this.getDetalleOCProveedor(Integer.parseInt(p.getCodigo()), p.getFechaEmision(),openOC));
	      	  
				
	        }
	        return ordenes;
		}
		else
		{
			return new ArrayList<>();
		}
		
		
		
		
		
	}
	
	public List<JSONDetalleOC> getDetalleOCProveedor(Integer idOrden, String fecha, String openOC)
	{
		String jotason = "{  \"codigo\": "+idOrden+",  \"fechaEmision\": \""+fecha+"\",  \"pendientes\": \""+openOC+"\"} ";
		DataIDDescDescripcion de=getWsData();
		String URLbase = de.getDesc();
		String retorno = this.callWSPOST_ParamJSON(URLbase,"/minoristas/"+de.getDescII()+"/ordenDeCompraDetalle", jotason);
		System.out.println(retorno);
		if(!retorno.equals("[]"))
		{
			List<JSONDetalleOC> detalle =JSONReader.readJsonDetalleOC(retorno);
	        System.out.println("Se obtubieron "+detalle.size()+" lineas de detalle");
	        for (JSONDetalleOC d : detalle) 
	        {
	      	  
	      	  System.out.println(d.getDescripcion());
				
	        }
			
			return detalle;
		}
		else
		{
			return new ArrayList<>();
		}
		
	}
	
	
	public List<JSONArticulo> getArticulo(String barra)
	{
		DataIDDescDescripcion d=getWsData();
		String URLbase = d.getDesc();
		String retorno = this.callWSGET(URLbase, "/minoristas/"+d.getDescII()+"/locales/"+d.getDescripcion()+"/codigoBarras/"+barra+"/obtenerArticuloPorCodigoBarras");
		
		System.out.println(retorno);
		List<JSONArticulo> articulos =JSONReader.readJsonArticulo(retorno);
		if(articulos.size()==0)
		{
			
			System.out.println(retorno);
			System.out.println(barra);
		}
        //System.out.println("Se obtubieron "+articulos.size()+" articulos");
        for (JSONArticulo a : articulos) 
        {
      	  
      	 //System.out.println(a.getDescripcion());
			
        }
		
		return articulos;
	}
	
	
	
	
	
	
	
	
	
	
	public List<JSONRespPedidos> getPedidos() 
	{
		DataIDDescDescripcion d=getWsData();
		String URLbase = d.getDesc();
		String funcion = "/minoristas/"+d.getDescII()+"/locales/"+d.getDescripcion()+"/obtenerPedidosPendientes";
		String retorno = this.callWSGET(URLbase, funcion);
		
		System.out.println(retorno);
		
		List<JSONRespPedidos> pedidos =null;//JSONReader.readJsonPedidos(retorno);
         for (JSONRespPedidos a : pedidos) 
        {
        	System.out.println(a.toString());
        	if(a.getPedidosApiDTO()!=null){
      	  for (JSONPedido j : a.getPedidosApiDTO()) 
      	  {
			System.out.println("\t"+j.toString());
			for (JSONArticuloPedidos ja : j.getArticulos()) 
			{
				System.out.println("\t\t"+ ja.toString());
			}
      	  }
        	}
			
        }
		
		return pedidos;
		
	}
	
	
	
	
	
	
	
	public void GrabarFactura(JSONFacturaRecepcion factura) 
	{
		DataIDDescDescripcion d=getWsData();
		String URLbase = d.getDesc();
		
		String funcion = "minoristas/"+d.getDescII()+"/guardarFactura";
		
		String jotason = JSONReader.generateJSONFactura(factura);
		//String jotason = "{  \"codigoEmpresa\": 4544,  \"codigoProveedor\": 2081,  \"fecha\": \"2017-02-16T17:22:25.000-0300\",  \"codigoMonedaCompra\": 85,  \"codigoMonedaVenta\": 85,  \"tipDocumento\": 6,  \"serie\": \"A\",  \"numero\": 10,  \"codigoDeposito\": 1,  \"codigoLocal\": 1,  \"redondeo\": 0,  \"totalIngresado\": 10,  \"totalCalculado\": 10,  \"factDetalle\": [    {      \"codigoArticulo\": \"000000000102325\",      \"numeroLinea\": 1,      \"unidades\": 10,      \"cantidad\": 1    }  ]} ";
		
		String retorno = this.callWSPOST_ParamJSON(URLbase, funcion,jotason);
		
		System.out.println(retorno);
		
				
		
		
	}
	
	
	
	public void GrabarRecepcionOC(JSONRecepcionOC recepcionOC) 
	{
		DataIDDescDescripcion d=getWsData();
		String URLbase = d.getDesc();
		String funcion = "minoristas/"+d.getDescII()+"/modificarOC";		
		String jotason = JSONReader.generateJSONRececpcion(recepcionOC);
		String retorno = this.callWSPOST_ParamJSON(URLbase, funcion,jotason);
		
		System.out.println(retorno);
		
	}
	
	public void updatePedidoCerrado(int idPedido) 
	{
		DataIDDescDescripcion d=getWsData();
		String URLbase = d.getDesc();
		String funcion = "minoristas/"+d.getDescII()+"/locales/"+d.getDescripcion()+"/notificarPedido?idPedido="+idPedido;
		
		
		String retorno = this.callWSPOST(URLbase, funcion);
		
		System.out.println(retorno);
		
	}
	public void updatePedidoRetirado(int idPedido) 
	{
		DataIDDescDescripcion d=getWsData();
		String URLbase = d.getDesc();
		String funcion = "minoristas/"+d.getDescII()+"/locales/"+d.getDescripcion()+"/notificarPedidoRetirado?idPedido="+idPedido;
		
		
		String retorno = this.callWSPOST(URLbase, funcion);
		
		System.out.println(retorno);
		
	}



	private String callWSGET(String URLbase, String funcion)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		
	    ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("produccion@200data.com", "200data2017"));
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
	
	
	
	
	
	private String callWSPOST_ParamJSON(String URLbase, String funcion, String jotason)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
	    ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("produccion@200data.com", "200data2017"));
	    try 
		{
	    	System.out.println("llamando a "+URLbase+funcion);
	    	System.out.println("Parametros JSON "+jotason);
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost(URLbase+funcion);
	    	
	    	StringEntity params =new StringEntity(jotason);
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
	
	

	
	
	
	private String callWSPOST(String URLbase, String funcion)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
	    ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("produccion@200data.com", "200data2017"));
	    try 
		{
	    	System.out.println("llamando a "+URLbase+funcion);
	    	
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost(URLbase+funcion);
	    	
	    	
	    	
	    	
	    	
	    	
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
