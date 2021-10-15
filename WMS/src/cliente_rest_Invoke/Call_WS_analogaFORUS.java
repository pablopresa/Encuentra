package cliente_rest_Invoke;

import helper.PropertiesHelper;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;


import logica.Logica;
import main.EcommerceProcessOrders;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import beans.Articulo;
import beans.Fecha;
import jsonObjects.*;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.Compras;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.EncuentraPedidoArticulo;
import eCommerce_jsonObjectsII.EncuentraPedidoArticuloReq;
import eCommerce_jsonObjectsII.Items;
import eCommerce_jsonObjectsII.Pedido;
import eCommerce_jsonObjectsII.RspEtiqueta;


public class Call_WS_analogaFORUS {
	
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
	
	
	public String setPedidos(String jotason,int canal, int idEmpresa)
	{
		/*
		jotason="[ "+
				 "     { "+
				 "        \"id\":\"42882\", "+
				 "        \"estado\":\"preparando\" "+
				 "     } "+
				 ","+
				 "{ "+
				 "        \"id\":\"42703\", "+
				 "        \"estado\":\"preparando\" "+
				 "     } "+
				 "]";
		*/
		String URLbase = "";
		if(canal==0){
			URLbase = "https://www.stadium.com.uy/tracking/";
		}
		else{
			URLbase = "https://misscarol.com.uy/tracking/";
		}
		String funcion = "set/compras";
		String retorno = this.callWSPOST_ParamJSON(URLbase, funcion, jotason,canal,idEmpresa);
		return "";
	}
	
	
	public static String darTracking(String path)
	{
		String retorno = "";
		try
		{
			PdfReader reader = new PdfReader(path);
	        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
	        TextExtractionStrategy strategy;
	        
	        for (int i = 1; i <= reader.getNumberOfPages(); i++) 
	        {
	            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
	            if(strategy.getResultantText().contains("PE"))
	            {
	            	retorno = strategy.getResultantText().split("\n")[0];
	            	break;
	            }
	            
	            System.out.println(strategy.getResultantText());
	        }
	        reader.close();
		}
		catch(Exception e)
		{
			
		}
		
				
		return retorno;
	}
	
	
	public static DataIDDescripcion darEtiquetaPE(String urletiqueta, Long idPedido, int canal) 
	{
		DataIDDescripcion retorno = new DataIDDescripcion();
        
        DefaultHttpClient client = new DefaultHttpClient();
 
        try {
        
            HttpGet securedResource = new HttpGet(urletiqueta);            
            HttpResponse httpResponse = client.execute(securedResource);
            HttpEntity responseEntity = httpResponse.getEntity();
            String strResponse = EntityUtils.toString(responseEntity);
            
            String [] arreglo = strResponse.split("\r\n");
            
            String token = "";
            for (String s : arreglo) 
            {
            	//System.out.println(s);
            	if(s.contains("  var STOKEN = '"))
            	{
            		token = s.replace("var STOKEN","");
            		token = token.replace("=","");
            		token = token.replace("\t","");
            		token = token.replace(" ","");
            		token = token.replace(";","");
            		token = token.replace("'","");
            		System.out.println(token);
            		break;
            		
            	}
			}
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            
 
            System.out.println("Http status code for Unauthenticated Request: " + statusCode);// Statue code should be 200
           
            PropertiesHelper pH=new PropertiesHelper("clienteAdminAnaloga");
			pH.loadProperties();
			String usuario ="";
			String pass = "";
			String dom="";
			if(canal==0){
				usuario = pH.getValue("txtUsuario");
				pass = pH.getValue("txtPassword");
				dom="stadium";
			}
			else{
				usuario = pH.getValue("txtUsuarioMC");
				pass = pH.getValue("txtPasswordMC");
				dom="misscarol";
			}
            
            HttpPost authpost = new HttpPost("https://www."+dom+".com.uy/admin.php/ingresar");
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("txtUsuario", usuario));
            nameValuePairs.add(new BasicNameValuePair("txtPassword", pass));
            nameValuePairs.add(new BasicNameValuePair("token", token));
            nameValuePairs.add(new BasicNameValuePair("_frm", "frmLogin"));
            
            authpost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            authpost.setHeader("Content-Type", "application/x-www-form-urlencoded");
 
            httpResponse = client.execute(authpost);
            responseEntity = httpResponse.getEntity();
            strResponse = EntityUtils.toString(responseEntity);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            //EntityUtils.consume(responseEntity);
 
            System.out.println("Http status code for Authenticattion Request: " + statusCode);// Status code should be 302
            System.out.println("Response for Authenticattion Request: n" + strResponse); // Should be blank string
            System.out.println("================================================================n");
 
            httpResponse = client.execute(securedResource);
            responseEntity = httpResponse.getEntity();
            
            
            /***********************************descargo el PDF******************************************/
            pH=new PropertiesHelper("paths");
			pH.loadProperties();
			String path = pH.getValue("pdf");
			String filePath = path+"/"+idPedido+".pdf";
			InputStream is = responseEntity.getContent();
			File file = new File(filePath);
            file.delete();
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            int inByte;
            while ((inByte = is.read()) != -1) 
            {
                fos.write(inByte);
            }
            is.close();
            fos.close();
            
            
            String HTTPpath = pH.getValue("HTTP_pdf");
			
            String fileHTTPPath = HTTPpath+"/"+idPedido+".pdf";
            String tracking = darTracking(filePath);
            
            //retorno.setId(idPedido);
            retorno.setIdLong(idPedido);
            retorno.setDescripcion(tracking);
            retorno.setDescripcionB(fileHTTPPath);
            return retorno;
            
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
            return retorno;
        }
    }
	
	
	
	
	
	public List<JSONRespPedidos> getPedidos(int canal,int idEmpresa) 
	{
		Logica Logica = new Logica();
		String dom="";
		if(canal==0){
			dom="www.stadium";
		}
		else{
			dom="misscarol";
		}
		
		int cantidadPasadas = 10;
		Fecha fecha = new Fecha(-10, 0, 0);
		
		int pag=1;
		
		Fecha fechaActual = new Fecha();
		
		while (cantidadPasadas>=0) 
		{
			String URLbase = "https://"+dom+".com.uy/tracking/";
			String funcion = "get/compras/"+pag+"/100/"+fecha.darFechaAnio_Mes_Dia()+"/procesando/";
			/*preparando*/
			//String funcion = "get/compras/1/100/"+fecha.darFechaAnio_Mes_Dia()+"/preparando/";
			String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
			
			System.out.println(retorno);
			Pedido pedido =JSONReader.readJsonPedidos(retorno);
	         
			cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
			
			System.out.println(pedido.getTotal());
			
			
			for (Compras c : pedido.getCompras()) 
			{
				
				if(Integer.parseInt(c.getCompra().getId())==123728 ){
					System.out.println("casos");
				}
				
				EncuentraPedido p = new EncuentraPedido();
				p.setDescripcion(c.getCliente().getNombre()+" "+c.getCliente().getApellido());
				
				int cantidad=0;
				Double descuento=0.0;
				
				p.setEstado(c.getCompra().getEstado());
				p.setUrlEtiqueta(c.getEtiqueta());
				p.setEstado(c.getCompra().getEstado());
				List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
				p.setIdPedido(Long.parseLong(c.getCompra().getId()));
				Double importeTotal = 0.0; 
				Double importetotalOV = 0.0;
				
				if(Double.parseDouble(c.getCompra().getMontoEnvio())==Double.parseDouble(c.getCompra().getImporte())){
					importetotalOV = Double.parseDouble(c.getCompra().getMontoEnvio());
				}
				else{
					importetotalOV = Double.parseDouble(c.getCompra().getImporte());
				}
				
				Logica.logPedido(p.getIdPedido(), 0, 0, "comienzo sinconizacion",0,idEmpresa);
				
				Logica.logger(0, 100, "desempaquetando pedido WEB "+p.getIdPedido(),idEmpresa);
				
				p.setSucursalPick(c.getCompra().getSucursal()); 
				Double costoEnvio = 0.0;
				
				costoEnvio = Double.parseDouble(c.getCompra().getMontoEnvio());
				
				if(costoEnvio>0.0)
				{
					Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando costo de envio "+costoEnvio,0,idEmpresa);
					
					
				}
				
				StringBuilder sb = new StringBuilder();
				for (Items it : c.getCompra().getItems()) 
				{
					Double importe = Double.parseDouble(it.getImporte());
					int cantidadItem = Integer.parseInt(it.getCant()); 
					if(importe>=0)
					{
						cantidad+=cantidadItem;
						EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
						art.setArticulo(it.getSku().replace(":", ""));
						
						art.setCantidad(cantidadItem);
						art.setProcesada(0);
						pedidos.add(art);
						importeTotal+=importe;
						
						Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando linea articulo "+art.getArticulo(),0,idEmpresa);
						
						if(importe==0){		//CAMBIO - agregar cupon
							//sb.append("");
						}
						
					}
					else
					{						
						sb.append(it.getSku()+": $"+importe+" - ");
						descuento+=importe;
						Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando descuento $"+importe*-1,0,idEmpresa);
					}
					
				}
				
				p.setCantidad(cantidad);
				p.setDescuento(descuento);
				p.setArticulosPedido(pedidos);
				p.setSucursalPick(c.getCompra().getSucursal());
				p.setArticulosPedidoReq(new ArrayList<>());
				
				if(c.getCompra().getMetodoPago().contains(" MP ")){
					c.getCompra().setMetodoPago("mercadopago");
				}
				
				p.setFormaPago(c.getCompra().getMetodoPago());
				Double porcDescuento = (descuento*100)/importeTotal;
				if(descuento==0 && importeTotal==0){
					porcDescuento=0.0;
				}
				/*
				if((porcDescuento*-1)>0)
				{
					for (OrdenVentaLinea ol : ventaLineas) 
					{
						if(!ol.getIdArticulo().equals("0002000"))
						{
							Double fullPrice = ol.getPrecioImp();
							Double discount = ((porcDescuento*-1)*fullPrice)/100;
							
							ol.setPrecioImp(fullPrice-discount);
						}
					}
				}
				*/
				p.setCanalAnaloga(canal);
				
				if(p.save(idEmpresa))
				{
					Logica.logPedido(p.getIdPedido(), 0, 0, "Pedido Sincronizado ",0,idEmpresa);
					
					String jotason="[ "+
							 "     { "+
							 "        \"id\":\""+p.getIdPedido()+"\", "+
							 "        \"estado\":\"preparando\" "+
							 "     } "+
							 "]";
					
					
					
					
					Logica.logPedido(p.getIdPedido(), 0, 0, "Marcando el pedido en estado preparando ",0,idEmpresa);
					setPedidos(jotason,canal,idEmpresa);
					
					
				
					RspEtiqueta et = new RspEtiqueta();
					if(p.getSucursalPick().equals(""))
					{
						try 
						{
							Thread.sleep(4000);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
						
						String URLbase2 = "https://"+dom+".com.uy/tracking/";
						String funcion2 = "/get/etiqueta?idCompra="+p.getIdPedido()+"";
						String retorno2 = callWSGET(URLbase2, funcion2,canal,idEmpresa);
						
						
						et =JSONReader.readJsonPedidoEti(retorno2);
						
					}
					else
					{
						String sucursalPick = String.format("%02d", Integer.parseInt(p.getSucursalPick()));
						et.setEtiqueta("https://"+dom+".com.uy/public/ctm/"+sucursalPick+".pdf");
					}
					p.setUrlEtiqueta(et.getEtiqueta());
					
					//pickup
					if(et.getEtiqueta().contains("https://"+dom+".com.uy/public/ctm/"))
					{
						String pick = et.getEtiqueta().replace("https://"+dom+".com.uy/public/ctm/", "");
						pick = pick.replace(".pdf", "");
						int idPicking = 0;
						try
						{
							idPicking = Integer.parseInt(pick);
						}
						catch(Exception e){}
						p.updateShipping(idPicking, null,"",idEmpresa);
					}
					
					//UES
					else if(et.getEtiqueta().contains("https://system.netsuite.com/core/media/"))
					{
						p.updateShipping(800, null,"",idEmpresa);
					}
					//correo
					else if(et.getEtiqueta().contains("https://"+dom+".com.uy/files.php/correouy/"))
					{
						DataIDDescripcion trackingURL =  darEtiquetaPE(et.getEtiqueta(),  p.getIdPedido(),canal);
						p.updateShipping(900, trackingURL.getDescripcion(),"",idEmpresa);
						p.setUrlEtiqueta(trackingURL.getDescripcionB());
						
					}
					//DAC
					else if(et.getEtiqueta().contains("https://"+dom+".com.uy/files.php/dac/"))
					{
						DataIDDescripcion trackingURL =  darEtiquetaPE(et.getEtiqueta(),  p.getIdPedido(),canal);
						p.updateShipping(701, trackingURL.getDescripcion(),"",idEmpresa);
						p.setUrlEtiqueta(trackingURL.getDescripcionB());
						
					}
					
					
					p.updateEtiqueta(0,idEmpresa);
					Logica.logPedido(p.getIdPedido(), 0, 0, "grabando Orden para importar",0,idEmpresa);
				}
				else
				{
					Logica.logPedido(p.getIdPedido(), 0, 0, "el pedido estaba sincronizado, actualizando etiqueta por las dudas.",0,idEmpresa);
					RspEtiqueta et = new RspEtiqueta();
					if(p.getSucursalPick().equals(""))
					{
						
						
						String URLbase2 = "https://"+dom+".com.uy/tracking/";
						String funcion2 = "/get/etiqueta?idCompra="+p.getIdPedido()+"";
						String retorno2 = callWSGET(URLbase2, funcion2,canal,idEmpresa);
						
						
						et =JSONReader.readJsonPedidoEti(retorno2);
						
					}
					else
					{
						String sucursalPick = String.format("%02d", Integer.parseInt(p.getSucursalPick()));
						et.setEtiqueta("https://"+dom+".com.uy/public/ctm/"+sucursalPick+".pdf");
					}
						
					p.setUrlEtiqueta(et.getEtiqueta());
						
						
					//pickup
					if(et.getEtiqueta().contains("https://"+dom+".com.uy/public/ctm/"))
					{
						String pick = et.getEtiqueta().replace("https://"+dom+".com.uy/public/ctm/", "");
						pick = pick.replace(".pdf", "");
						int idPicking = 0;
						try
						{
							idPicking = Integer.parseInt(pick);
						}
						catch(Exception e){}
						p.updateShipping(idPicking, null,"",idEmpresa);
					}
					//UES
					else if(et.getEtiqueta().contains("https://system.netsuite.com/core/media/"))
					{
						p.updateShipping(800, null,"",idEmpresa);
					}
					//correo
					else if(et.getEtiqueta().contains("https://"+dom+".com.uy/files.php/correouy/"))
					{
						DataIDDescripcion trackingURL =  darEtiquetaPE(et.getEtiqueta(),  p.getIdPedido(),canal);
						p.updateShipping(900, trackingURL.getDescripcion(),"",idEmpresa);
						p.setUrlEtiqueta(trackingURL.getDescripcionB());
						
					}
					//DAC
					else if(et.getEtiqueta().contains("https://"+dom+".com.uy/files.php/dac/"))
					{
						DataIDDescripcion trackingURL =  darEtiquetaPE(et.getEtiqueta(),  p.getIdPedido(),canal);
						p.updateShipping(701, trackingURL.getDescripcion(),"",idEmpresa);
						p.setUrlEtiqueta(trackingURL.getDescripcionB());
						
					}
					p.updateEtiqueta(0,idEmpresa);
				}
			
			}
			
			cantidadPasadas--;
			pag++;
		}
		
		
		//EcommerceProcessOrders.process(null,0,null,0);
		
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public String callWSGET(String URLbase, String funcion, int idCanal, int idEmpresa)
	{
		Logica Logica = new Logica();
		DataIDDescDescripcion canal= Logica.EcommercedarCanalesAnaloga(idCanal,idEmpresa);
		String usu=canal.getDesc();
		String pass=canal.getDescripcion();
		
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		
	    ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials(usu,pass));//"std_comuy", "STD#Tck_%$774$3"));
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
	
	
	
	
	
	
	
	
	private String callWSPOST_ParamJSON(String URLbase, String funcion, String jotason, int idCanal, int idEmpresa)
	{
		Logica Logica = new Logica();
		DataIDDescDescripcion canal= Logica.EcommercedarCanalesAnaloga(idCanal,idEmpresa); 
		String usu=canal.getDesc();
		String pass=canal.getDescripcion();
		
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		 ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials(usu,pass));//"std_comuy", "STD#Tck_%$774$3"));
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
		
		
		HttpClient httpClient = getHttpClient();
		
	    ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("Stadium.53778", "ae93a7e435b02996c7a3a348d65332498a9d53b9"));
	    try 
		{
	    	HttpGet httpGetRequest = new HttpGet("http://app.printnode.com/api/printers");
	    	
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
	
	private static HttpClient getHttpClient() 
	{

	    try {
	        SSLContext sslContext = SSLContext.getInstance("SSL");

	       
	        //SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	        HttpClientBuilder construct = HttpClientBuilder.create();//.setSSLSocketFactory(socketFactory); 
	        
	        CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("", "ae93a7e435b02996c7a3a348d65332498a9d53b9");
			provider.setCredentials(AuthScope.ANY, credentials);
			
			construct.setDefaultCredentialsProvider(provider);
			

	        HttpClient httpClient = construct.build();

	        return httpClient;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return HttpClientBuilder.create().build();
	    }
	}

	
	public String callWSPOSTPrint(String jotason)
	{
		
		System.out.println("json: ");
		System.out.println(jotason);
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("Stadium.53778", "ae93a7e435b02996c7a3a348d65332498a9d53b9"));
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
		          return "error";
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
		      return "error";
		      
		    }
	    	catch (IOException e) 
	    	{
		      // thrown by entity.getContent();
		      e.printStackTrace();
		      return "error";
		    }
	    	finally 
	    	{
		      httpClient.getConnectionManager().shutdown();
		    }
		return retorno;
		
	}
	
	
	

	public List<JSONRespPedidos> getPedidosOV(int canal, int idEmpresa) 
	{
		Logica Logica = new Logica();
		int cantidadPasadas = 10;
		Fecha fecha = new Fecha(-10, 0, 0);
		Fecha fechaActual = new Fecha();
		
		while (cantidadPasadas>=0) 
		{
			String URLbase = "https://www.stadium.com.uy/tracking/";
			//String funcion = "get/compras/1/100/"+fecha.darFechaAnio_Mes_Dia()+"/procesando/";
			/*preparando*/
			String funcion = "get/compras/1/100/"+fecha.darFechaAnio_Mes_Dia()+"/preparando/";
			String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
			
			System.out.println(retorno);
			Pedido pedido =JSONReader.readJsonPedidos(retorno);
	         
			cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
			
			System.out.println(pedido.getTotal());
			
			
			for (Compras c : pedido.getCompras()) 
			{
				boolean cancelado = Logica.hayRegistro("select * from ecommerce_pedido where idEmpresa="+idEmpresa+" AND idPedido="+c.getCompra().getId()+" and cancelado=1 and estadoencuentra=99");
				
				if(!cancelado){
					
				EncuentraPedido p = new EncuentraPedido();
				p.setDescripcion(c.getCliente().getNombre()+" "+c.getCliente().getApellido());
				
				int cantidad=0;
				Double descuento=0.0;
				
				p.setEstado(c.getCompra().getEstado());
				p.setUrlEtiqueta(c.getEtiqueta());
				p.setEstado(c.getCompra().getEstado());
				List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
				p.setIdPedido(Long.parseLong(c.getCompra().getId()));
				Double importeTotal = 0.0; 
				
				Double importetotalOV = Double.parseDouble(c.getCompra().getMontoEnvio());
				
				
				Logica.logPedido(p.getIdPedido(), 0, 0, "comienzo sinconizacion",0,idEmpresa);
				
				Logica.logger(0, 100, "desempaquetando pedido WEB "+p.getIdPedido(),idEmpresa);
				
				p.setSucursalPick(c.getCompra().getSucursal()); 
				Double costoEnvio = 0.0;
				
				costoEnvio = Double.parseDouble(c.getCompra().getMontoEnvio());
				
				if(costoEnvio>0.0)
				{
					Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando costo de envio "+costoEnvio,0,idEmpresa);
					
					
				}
				StringBuilder sb = new StringBuilder();
				for (Items it : c.getCompra().getItems()) 
				{
					Double importe = Double.parseDouble(it.getImporte());
					int cantidadItem = Integer.parseInt(it.getCant()); 
					if(importe>=0)
					{
						cantidad+=cantidadItem;
						EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
						art.setArticulo(it.getSku().replace(":", ""));
						
						art.setCantidad(cantidadItem);
						art.setProcesada(0);
						pedidos.add(art);
						importeTotal+=importe;
						
						Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando linea articulo "+art.getArticulo(),0,idEmpresa);
						
						if(importe==0){		//CAMBIO - agregar cupon
							//sb.append("");
						}
						
					}
					else
					{						
						sb.append(it.getSku()+": $"+importe+" - ");
						descuento+=importe;
						Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando descuento $"+importe*-1,0,idEmpresa);
					}
					
				}
				
				p.setCantidad(cantidad);
				p.setDescuento(descuento);
				p.setArticulosPedido(pedidos);
				p.setSucursalPick(c.getCompra().getSucursal());
				p.setArticulosPedidoReq(new ArrayList<>());
				
				if(c.getCompra().getMetodoPago().contains(" MP ")){
					c.getCompra().setMetodoPago("mercadopago");
				}
				
				p.setFormaPago(c.getCompra().getMetodoPago());
				Double porcDescuento = (descuento*100)/importeTotal;
				if(descuento==0 && importeTotal==0){
					porcDescuento=0.0;
				}
				/*
				if((porcDescuento*-1)>0)
				{
					for (OrdenVentaLinea ol : ventaLineas) 
					{
						if(!ol.getIdArticulo().equals("0002000"))
						{
							Double fullPrice = ol.getPrecioImp();
							Double discount = ((porcDescuento*-1)*fullPrice)/100;
							
							ol.setPrecioImp(fullPrice-discount);
						}
					}
				}
				*/
				
			cantidadPasadas--;
			
			}
		
			}
		
		}
		return null;
		
	}

	
	
	public List<JSONRespPedidos> getPedidoOV(Long idPedido, int canal,int idEmpresa) 
	{
		Logica Logica = new Logica();
		String dom="";
		if(canal==0){
			dom="www.stadium";
		}
		else{
			dom="misscarol";
		}
		
		int cantidadPasadas = 10;
		Fecha fecha = new Fecha(-15, 0, 0);
		Fecha fechaActual = new Fecha();
		int pag =1;
		boolean pri=true;
		while (cantidadPasadas>=0) 
		{
			String URLbase = "https://"+dom+".com.uy/tracking/";
			//String funcion = "get/compras/1/100/"+fecha.darFechaAnio_Mes_Dia()+"/procesando/";
			/*preparando*/
			String funcion = "get/compras/"+pag+"/100/"+fecha.darFechaAnio_Mes_Dia();
			String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
			
			System.out.println(retorno);
			Pedido pedido =JSONReader.readJsonPedidos(retorno);
	         
			if(pri){
				cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
				pri=false;
			}
			
			
			
			for (Compras c : pedido.getCompras()) 
			{
				
				
				EncuentraPedido p = new EncuentraPedido();
				System.out.println(Integer.parseInt(c.getCompra().getId()));
				if(Integer.parseInt(c.getCompra().getId())==idPedido)
				{
					p.setDescripcion(c.getCliente().getNombre()+" "+c.getCliente().getApellido());
					
					int cantidad=0;
					Double descuento=0.0;
					
					p.setEstado(c.getCompra().getEstado());
					p.setUrlEtiqueta(c.getEtiqueta());
					p.setEstado(c.getCompra().getEstado());
					List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
					p.setIdPedido(Long.parseLong(c.getCompra().getId()));
					Double importeTotal = 0.0; 
					
					Double importetotalOV = Double.parseDouble(c.getCompra().getImporte());
					
					
					Logica.logPedido(p.getIdPedido(), 0, 0, "Buscando Orden de Venta",0,idEmpresa);
					
					p.setSucursalPick(c.getCompra().getSucursal()); 
					Double costoEnvio = 0.0;
					
					costoEnvio = Double.parseDouble(c.getCompra().getMontoEnvio());
					
					if(costoEnvio>0.0)
					{	
						Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando costo de envio "+costoEnvio,0,idEmpresa);
						
					}	
					StringBuilder sb = new StringBuilder();
					for (Items it : c.getCompra().getItems()) 
					{
						Double importe = Double.parseDouble(it.getImporte());
						int cantidadItem = Integer.parseInt(it.getCant()); 
						if(importe>=0)
						{
							cantidad+=cantidadItem;
							EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
							art.setArticulo(it.getSku().replace(":", ""));
							
							art.setCantidad(cantidadItem);
							art.setProcesada(0);
							pedidos.add(art);
							importeTotal+=importe;
							
							Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando linea articulo "+art.getArticulo(),0,idEmpresa);
							
							if(importe==0){		//CAMBIO - agregar cupon
								//sb.append("");
							}
							
						}
						else
						{						
							sb.append(it.getSku()+": $"+importe+" - ");
							descuento+=importe;
							Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando descuento $"+importe*-1,0,idEmpresa);
						}
						
					}
					
					p.setCantidad(cantidad);
					p.setDescuento(descuento);
					p.setArticulosPedido(pedidos);
					p.setSucursalPick(c.getCompra().getSucursal());
					p.setArticulosPedidoReq(new ArrayList<>());
					
					if(c.getCompra().getMetodoPago().contains(" MP ")){
						c.getCompra().setMetodoPago("mercadopago");
					}
					
					p.setFormaPago(c.getCompra().getMetodoPago());
					Double porcDescuento = (descuento *100)/importeTotal;
					if(descuento==0 && importeTotal==0){
						porcDescuento=0.0;
					}
					if(descuento<0 && importeTotal==0){
						porcDescuento=100.0;
					}
					/*
					if((porcDescuento*-1)>0)
					{
						for (OrdenVentaLinea ol : ventaLineas) 
						{
							if(!ol.getIdArticulo().equals("0002000"))
							{
								Double fullPrice = ol.getPrecioImp();
								Double discount = ((porcDescuento*-1)*fullPrice)/100;
								
								ol.setPrecioImp(fullPrice-discount);
							}
						}
					}
					*/
					
					try
					{
						RspEtiqueta et = new RspEtiqueta();
						if(p.getSucursalPick().equals(""))
						{
							try 
							{
								Thread.sleep(4000);
							} 
							catch (InterruptedException e) 
							{
								e.printStackTrace();
							}
							
							String URLbase2 = "https://"+dom+".com.uy/tracking/";
							String funcion2 = "/get/etiqueta?idCompra="+p.getIdPedido()+"";
							String retorno2 = callWSGET(URLbase2, funcion2,canal,idEmpresa);
							
							
							et =JSONReader.readJsonPedidoEti(retorno2);
							
						}
						else
						{
							String sucursalPick = String.format("%02d", Integer.parseInt(p.getSucursalPick()));
							et.setEtiqueta("https://"+dom+".com.uy/public/ctm/"+sucursalPick+".pdf");
						}
						p.setUrlEtiqueta(et.getEtiqueta());
						
						//pickup
						if(et.getEtiqueta().contains("https://"+dom+".com.uy/public/ctm/"))
						{
							String pick = et.getEtiqueta().replace("https://"+dom+".com.uy/public/ctm/", "");
							pick = pick.replace(".pdf", "");
							int idPicking = 0;
							try
							{
								idPicking = Integer.parseInt(pick);
							}
							catch(Exception e){}
							p.updateShipping(idPicking, null,"",idEmpresa);
						}
						
						//UES
						else if(et.getEtiqueta().contains("https://system.netsuite.com/core/media/"))
						{
							p.updateShipping(800, null,"",idEmpresa);
						}
						//correo
						else if(et.getEtiqueta().contains("https://"+dom+".com.uy/files.php/correouy/"))
						{
							DataIDDescripcion trackingURL =  darEtiquetaPE(et.getEtiqueta(),  p.getIdPedido(),canal);
							p.updateShipping(900, trackingURL.getDescripcion(),"",idEmpresa);
							p.setUrlEtiqueta(trackingURL.getDescripcionB());
							
						}
						//DAC
						else if(et.getEtiqueta().contains("https://"+dom+".com.uy/files.php/dac/"))
						{
							DataIDDescripcion trackingURL =  darEtiquetaPE(et.getEtiqueta(),  p.getIdPedido(),canal);
							p.updateShipping(701, trackingURL.getDescripcion(),"",idEmpresa);
							p.setUrlEtiqueta(trackingURL.getDescripcionB());
							
						}
						
						
						p.updateEtiqueta(0,idEmpresa);
						Logica.logPedido(p.getIdPedido(), 0, 0, "grabando Orden para importar",0,idEmpresa);
					}
					catch(Exception e)
					{
					}
					
				
				
				
				}
				
				
			
		
			
			}
			cantidadPasadas--;
			pag++;
		}
		return null;
		
	}
	
	
	
	public List<JSONRespPedidos> getPedidosAllDebug(int canal, int idEmpresa) 
	{
		
		String dom="";
		if(canal==0){
			dom="stadium";
		}
		else{
			dom="misscarol";
		}
		
		int cantidadPasadas = 10;
		
		
		while (cantidadPasadas>=0) 
		{
			String URLbase = "https://www."+dom+".com.uy/tracking/";
			String funcion = "get/compras/1/100/2017-11-25/";
			String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
			Pedido pedido =JSONReader.readJsonPedidos(retorno);
	        
			cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
			for (Compras c : pedido.getCompras()) 
			{
			
				System.out.println(Integer.parseInt(c.getCompra().getId())+"	"+c.getCompra().getEstado() );
				
			}
			
			cantidadPasadas--;
			
		}
		
		
		
		
		return null;
		
	}

	
	public List<DataIDDescDescripcion> getPedidosReporte(String fecha, int canal, int idEmpresa) {
		int cantidadPasadas = 10;
		
		String dom="";
		if(canal==0){
			dom="www.stadium";
		}
		else{
			dom="misscarol";
		}
		
		List<DataIDDescDescripcion> pedidos = new ArrayList<>();
		int pag =1;
		boolean pri=true;
		
		while (cantidadPasadas>=0) 
		{
			try{
				
			String URLbase = "https://"+dom+".com.uy/tracking/";
			String funcion = "get/compras/"+pag+"/100/"+fecha;
			
			String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
			
			System.out.println(retorno);
			Pedido pedido =JSONReader.readJsonPedidos(retorno);
	         
			if(pri){ 
				cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
				pri=false;
			}
			
			System.out.println(pedido.getTotal());
			
			
			
			for (Compras c : pedido.getCompras()) 
			{
				DataIDDescDescripcion p = new DataIDDescDescripcion(Long.parseLong(c.getCompra().getId()),"NO SINCRONIZADO",c.getCompra().getFecha());	
				p.setDescII(dom);
				p.setDescripcionII(c.getCompra().getEstado());
				pedidos.add(p);
			}
			
			
			}
			catch(Exception e){
			
			}
			
			cantidadPasadas--;
			pag++;
		}		
		
		
		return pedidos;
	}
	

}
