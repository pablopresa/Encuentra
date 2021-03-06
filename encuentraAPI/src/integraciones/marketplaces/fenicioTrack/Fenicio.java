package integraciones.marketplaces.fenicioTrack;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.mashape.unirest.http.Unirest;

import beans.Fecha;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.Compras;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import beans.encuentra.Items;
import beans.encuentra.Pedido;
import beans.helper.PropertiesHelper;
import beans.helper.PropertiesHelperAPI;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.marketplaces.fenicio.Ordenes;
import integraciones.marketplaces.fenicio.RspFenicioAPI;
import integraciones.marketplaces.fenicio.RspFenicioAPIOrden;
import integraciones.marketplaces.objetos.UpdateStateResponse;
import integraciones.marketplaces.objetos.marketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Fenicio extends marketPlace{
	
	//CONSTRUCTOR
	public Fenicio() {
		this.setIdMarketPlace(1);
	}
	
	//MAPEA ESTADOS DE WS A ESTADOS FENICIO
	public String statusMapper(String status) {
		String estado = "";
		try {
			switch (status) {
			case "preparado":
				estado = "Listo para enviar";
				break;
			case "listoretirar":
				estado = "Listo para retirar";
				break;
			case "despachado":
				estado = "En camino";
				break;
			case "entregado":
				estado = "Pedido entregado";
				break;	

			default:
				estado = status;
				break;
			}
		} catch (Exception e) {
			return status;
		}
		return estado;
	}
	
	//MAPEA ESTADOS DE ENCUENTRA AL ESTADO CORRESPONDIENTE DE FENICIO
	public String statusMapperEncuentra_Fenicio(int status) {
		String estado = "";
		try {
			switch (status) {
			case 1:
				estado = "preparando";
				break;
			case 2:
				estado = "preparando";
				break;
			case 3:
				estado = "preparado";
				break;
			case 5:
				estado = "listoretirar";
				break;
			case 4:
				estado = "despachado";
				break;
			case 6:
				estado = "entregado";
				break;	

			default:
				estado = "";
				break;
			}
		} catch (Exception e) {
			return "";
		}
		return estado;
	}
	
	//PREPARA EL JSON QUE SE DEBE ENVIAR PARA CAMBIO DE ESTADO
	public String JSONUpdateState (Long idPedido, String idEcommerce, String track, int estado) {
		String json = "";
			json=
					 "     { "+
					 "        \"id\":\""+idEcommerce+"\", "+
					 "        \"estado\":\""+statusMapperEncuentra_Fenicio(estado)+"\" "+
					 "     } "
					 ;
		
		return json;
	}
	
	//CAMBIO DE ESTADO
	@Override
	public List<UpdateStateResponse> UpdateState(String json,int canal)
	{
		ColectionUpdateState salida = new ColectionUpdateState();
		json = json.replace("\"preparando\",", "\"preparando\"");
		try {
			String urlBase = this.canales.get(canal).getHost();
			
			String funcion = "set/compras";
			String retorno = this.callPostOKHttp(urlBase+funcion, canal, json);
			Gson gson = new Gson();
			salida = gson.fromJson(retorno, ColectionUpdateState.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return salida.getCompras();
	}
	
	//SINCRO PEDIDOS
	public void sincroFenicio() {
		/*Enumeration<Integer> canales = this.canales.keys();
		while (canales.hasMoreElements()) {
			int idCanal=canales.nextElement();
			getPedidos(idCanal);
		}*/
	}
	
	//GET PEDIDOS
	@Override
	public List<EncuentraPedido> getPedidos(int canal, String status, int dias, Map<String, String> pedidosIn, Map<String, Integer> depositosPickHT) 
	{					
		int pag=1;
        int cantidadPasadas = 20;
        Fecha fechaQuery = new Fecha(dias*-1, 0, 0);
                     
        Fecha fechaActual = new Fecha();
        
        List<EncuentraPedido>  pedidosR = new ArrayList<>();
        
        List <Compras> pedidosALL = new ArrayList<>();
        
        if(!status.equals(""))
        {
        	status="/"+status;
        }
        
        boolean pri = true;
        while (cantidadPasadas>=0) 
        {
        	
        	  String urlBase = this.getCanales().get(canal).getHost();
               String funcion = "get/compras/"+pag+"/100/"+fechaQuery.darFechaAnio_Mes_Dia()+status;
               
               String retorno = this.callPostOKHttp(urlBase+funcion, canal, null);
               
               System.out.println(retorno);
               Gson gson = new Gson();
               Pedido pedido =gson.fromJson(retorno,Pedido.class);
           
               if(pri)
               {
                     cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
                     System.out.println(pedido.getTotal()+ " son "+cantidadPasadas +" Pasadas");
                     pri = false;
               }
               
               System.out.println("quedan "+cantidadPasadas+" pasadas");
               
                     for (Compras c : pedido.getCompras()) 
                     {
                    	 
                            pedidosALL.add(c);
                     }                                
                                  
               cantidadPasadas--;
               pag++;
        }
                                                      
        
        int current = 1;
        System.out.println(pedidosALL.size()); 
        for (Compras c : pedidosALL) 
        {
        	
        	System.out.println("\r \r \r iteracion numero "+current +" de "+pedidosALL.size() );
        	if(!c.getCompra().getItems().isEmpty())
        	{        
        		List<OrdenVentaLinea> ventaLineas = new ArrayList<>();//PARA LA OC
        		if(c.getCompra().getId().equals("262482"))
        		{
        			System.out.println("F");
        		}
        		System.out.println(c.getCompra().getId());
                EncuentraPedido p = new EncuentraPedido();
                p.setDescripcion(c.getCliente().getNombre()+" "+c.getCliente().getApellido());
                            
                int cantidad=0;
                Double descuento=0.0;
                            
                p.setTicketNumber("");
                p.setEstado(c.getCompra().getEstado());
                p.setUrlEtiqueta(c.getEtiqueta());
                
                
                try
                {
                	p.setEmpresaEnvio(c.getEnvio().getEmpresa());
                	p.setEmpresaEnvioCod(c.getEnvio().getCodigo());
                }
                catch (Exception e) 
                {
					p.setEmpresaEnvio("");
					p.setEmpresaEnvioCod("");
				}
                List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
                     
                p.setIdPedido(Long.parseLong(c.getCompra().getId()));
                p.setIdFenicio(c.getCompra().getId());
                p.setCanalAnaloga(canal);
                Double importeTotal = 0.0; 
                Double importetotalOV;
                            
                if(Double.parseDouble(c.getCompra().getMontoEnvio())==Double.parseDouble(c.getCompra().getImporte()))
                {
                	importetotalOV = Double.parseDouble(c.getCompra().getMontoEnvio());
                }
                else
                {
                	importetotalOV = Double.parseDouble(c.getCompra().getImporte());
                }
                 
                Double costoEnvio = Double.parseDouble(c.getCompra().getMontoEnvio());
                
                for (Items it : c.getCompra().getItems()) 
                {
                	Double importe = Double.parseDouble(it.getImporte());
                	
                	int cantidadItem = Integer.parseInt(it.getCant()); 
                    
                	
                	
					//articulos para OV
					OrdenVentaLinea ovl = new OrdenVentaLinea(importe, it.getCant(), it.getSku());
					ovl.setDescripcion(it.getVariacion());
					ventaLineas.add(ovl);
                	
                	
                	cantidad+=cantidadItem;
                	EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
                	
                	art.setCodFenicio(it.getCod());
                	art.setSKUFenicio(it.getSku());
                	art.setArticulo(it.getSku());
                	art.setCantidad(cantidadItem);
                	art.setProcesada(0);
                	art.setDistribucionAfecta(p.getIdPedido()+"");
                	art.setImporte(importe);
                	pedidos.add(art);
                	importeTotal+=importe;
                }
                
                
               
               
                
                p.setMontoEnvio(costoEnvio);
                            
                p.setCantidad(cantidad);
                p.setDescuento(descuento);
                p.setArticulosPedido(pedidos);
                p.setSucursalPick(c.getCompra().getSucursal());
                p.setArticulosPedidoReq(new ArrayList<>());
                p.setFormaPago(c.getCompra().getMetodoPago());
                
                Double porcDescuento = (descuento*100)/importeTotal;
                
                if(descuento==0 && importeTotal==0)
                {
                	porcDescuento=0.0;
                }
                
                // ORDEN
				try 
				{
					String rut = "0";
					if(c.getCliente().getDocumentoNro().length()>8) 
					{
						rut = c.getCliente().getDocumentoNro();
					}
					
					OrdenVenta orden = new OrdenVenta(0, porcDescuento*-1, c.getCompra().getMoneda(), 
							c.getCliente().getDocumentoNro()+"", c.getCliente().getNombre() + " " + c.getCliente().getApellido(), 
							c.getCliente().getCalle()+" "+c.getCliente().getNroPuerta()+" / "+c.getCliente().getNroApto(), rut, 
							c.getCliente().getTelefono(), ventaLineas, Long.parseLong(c.getCompra().getId()),200, 
							c.getCompra().getMetodoPago()+" | "+fechaActual.darFechaAnio_Mes_Dia(),0,importetotalOV);
					orden.setCliMail(c.getCliente().getEmail());
					orden.setComentario(c.getCliente().getObs());
					
					p.setOrden(orden);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
                            
               
                p.setFecha(c.getCompra().getFecha());
                p.setPrecio(importetotalOV);
                
                c.getCliente().setObs(c.getCompra().getObservaciones());
                                                                          
                p.setCliente(c.getCliente()); 
                
                
                /*seteo de destino*/
                int destino = 0;
				if(!c.getCompra().getSucursal().equals(""))
				{
					try
					{
						destino = Integer.parseInt(c.getCompra().getSucursal());
					}
					catch (Exception e) 
					{
						try
						{
							destino = Integer.parseInt(c.getCompra().getSucursal().replace("CC", ""));
						}
						catch (Exception e2) 
						{
							
						}
					}
				}
				
				
				p.setDestino(new DataIDDescripcion(destino,""+canal));
                
                
                pedidosR.add(p);
                
                           
        	}
               
        }
               
        return pedidosR;

		
	}
	//get pedidos destino
	public Hashtable<String, DataIDDescripcion> DestinoPedidos(int canal, int dias, Hashtable<String, DataIDDescripcion> retornable)
	{
		List<EncuentraPedido> pedidos = getPedidos(canal,"",dias, null, null);
		
		for (EncuentraPedido p : pedidos) 
		{
			System.out.println(p.getIdPedido());
			retornable.put(p.getIdPedido()+"", p.getDestino());
		}
		
		return retornable;
		
		
	}
	
	


	
	//GET PEDIDOS Api Fenicio
	public List<Ordenes> getPedidosAPI(int canal, int dias) 
	{					
		int pag=1;
        int cantidadPasadas = 20;
        Fecha fechaQuery = new Fecha(dias*-1, 0, 0);
        List <Ordenes> pedidosALL = new ArrayList<>();
        boolean pri = true;
        while (cantidadPasadas>=0) 
        {
         
        	
        	  String urlBase = this.getCanales().get(canal).getHost();
        	  urlBase = urlBase.replace("/tracking/", "");
               String funcion = "/API_V1/ordenes?pag="+pag+"&fDesde="+fechaQuery.darFechaAnio_Mes_Dia();
               
               String retorno = this.callPostOKHttp(urlBase+funcion, canal, null);
               
               System.out.println(retorno);
               Gson gson = new Gson();
               RspFenicioAPI pedidos =gson.fromJson(retorno,RspFenicioAPI.class);
           
               if(pri)
               {
                     cantidadPasadas = (Integer.parseInt(pedidos.getTotAbs())/50);
                     System.out.println(pedidos.getTotAbs()+ " son "+cantidadPasadas +" Pasadas");
                     pri = false;
               }
               
               System.out.println("quedan "+cantidadPasadas+" pasadas");
               
               for (Ordenes o : pedidos.getOrdenes()) 
               {
            	   pedidosALL.add(o);
            	   //System.out.println(o.getEntrega().getEtiqueta());
               }
               cantidadPasadas--;
               pag++;
        }
    return pedidosALL;

	
}
	
	
	
	//GET ETIQUETAS
	public String getEtiqueta(int id, int canal,int idEmpresa){
		String url = this.canales.get(canal).getHost();
		String funcion2 = "/get/etiqueta?idCompra="+id+"";
		return callWSGET(url, funcion2,canal);
	}
	
	
	public List<DataIDDescripcion> tackPedidosDespachados(Map<String, String> pedidosEncuentra, int canal) 
	{		
		List<DataIDDescripcion> entregados = new ArrayList<>();
		try {
			String urlBase = this.getCanales().get(canal).getHost();
	    	  urlBase = urlBase.replace("/tracking/", "");
	    	  
	    	  
	    	  Set<String> keys = pedidosEncuentra.keySet();
				for(String pedido: keys)
				{
					String funcion = "/API_V1/ordenes/"+pedido;
			           
			           String retorno = this.callPostOKHttp(urlBase+funcion, canal, null);
			           
			           System.out.println(retorno);
			           Gson gson = new Gson();
			           RspFenicioAPIOrden orden =gson.fromJson(retorno,RspFenicioAPIOrden.class);	
			           
			           try {
			        	   if(orden.getOrden().getEntrega().getEstado().equals("ENTREGADO")) {
			        		   entregados.add(new DataIDDescripcion(6,pedido));
			        	   }
						} catch (Exception e) {
							System.out.println("no se encontro el pedido");
						}
			           
				}
				
			} catch (Exception e) {
				System.out.println("no se pudo actualizar ordenes");
		}
		return entregados;
}
	
	
	//POST
	private String callWSPOST_ParamJSON(String URLbase, String funcion, String jotason, int idCanal)
	{
		String usu = this.canales.get(idCanal).getUser();
		String pass = this.canales.get(idCanal).getPass();
		
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		 ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
				 new UsernamePasswordCredentials(usu,pass));
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

			return construct.build();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return HttpClientBuilder.create().build();
	    }
	}
	
	
	//post unirest
	public String callPostOKHttp(String urlFuncion, int canal, String json)
	{
		try
		{
			Unirest.setTimeouts(0, 0);
			
			String usu = this.canales.get(canal).getUser();
			String pass = this.canales.get(canal).getPass();

	       
	        String credencial = usu+ ":" + pass;
	        String base64 = new String(Base64.getEncoder().encode(credencial.getBytes()));
	        
	        
	        OkHttpClient client = new OkHttpClient().newBuilder()
	        		  .build();
	        MediaType mediaType = MediaType.parse("text/plain");
	        String cuerpo = "";
	        RequestBody body = null;
	        if(json != null) {
	        	body = new FormBody.Builder()
	                    .add("compras", json)
	                    .build();
	        }
	        else {
	        	body = RequestBody.create(mediaType, cuerpo);
	        }
	        		
	        		
	        		Request request = new Request.Builder()
	        		  .url(urlFuncion)
	        		  .method("POST", body)
	        		  .addHeader("Authorization", "Basic "+base64)
	        		  .addHeader("Cookie", "_FNID=vk4tmp9ibqnuta0uvc7erlpa90")
	        		  .build();
	        		Response response = client.newCall(request).execute();
	        
	        		
	        		int bytesRead = 0;
			          BufferedInputStream bis = new BufferedInputStream(response.body().byteStream());
			          byte[] contents = new byte[1024];
			          StringBuilder strFileContents = new StringBuilder(); 
			          while((bytesRead = bis.read(contents)) != -1) 
			          { 
			              strFileContents.append(new String(contents, 0, bytesRead));              
			          }
			          System.out.println(strFileContents);
			          response.close();
			          return strFileContents.toString();
			
		}
		catch (Exception e) 
		{
			return "";
		}
		
	}
	
	
	
	//GET
	public String callWSGET(String urlBase, String funcion, int canal)
	{
		String usu = this.canales.get(canal).getUser();
		String pass = this.canales.get(canal).getPass();
		
		String retorno = "";
		//HttpClient httpClient = new DefaultHttpClient();
		
		HttpClient httpClient = getHttpClient(usu, pass);
		
	    //((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials(usu,pass));//"std_comuy", "STD#Tck_%$774$3"));
	    try 
		{
	    	HttpGet httpGetRequest = new HttpGet(urlBase + funcion);
	    	
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
		          StringBuilder strFileContents = new StringBuilder(); 
		          while((bytesRead = bis.read(contents)) != -1) 
		          { 
		              strFileContents.append(new String(contents, 0, bytesRead));              
		          }

		          return strFileContents.toString();
		          
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
	
	public Ordenes OrdenVentaFenicio(Long idOrden, int idEmpresa, int canal){
		Ordenes of = null;
		try 
		{
			Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();
	        
			LogicaAPI l = new LogicaAPI();
			String host = l.darHostFenicioAPI(idEmpresa, ""+canal);
			String jsonEP = cwa.callWSGET(host+"/API_V1/ordenes/"+idOrden, false);
			
			Gson gson = new Gson();
			
			String[] parts = jsonEP.split("\"orden\":");
			String orden = parts[1].substring(0, parts[1].length()-1);
		
			of = gson.fromJson(orden , Ordenes.class);				
		} 
		catch (Exception e) 
		{
			e.printStackTrace();				
		}
		return of;
	}
	
	public DataIDDescripcion darEtiquetaPE(String urletiqueta, int canal, String prefix) 
	{
		DataIDDescripcion retorno = new DataIDDescripcion();
		retorno.setDescripcion("");
		retorno.setDescripcionB("");
        
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
           
            PropertiesHelper pH=new PropertiesHelper("credencialesFenicio");
			pH.loadProperties();
			String usuario ="";
			String pass = "";
			String dom="";
			System.out.println("");
			
			dom = this.getCanales().get(canal).getHost().replace("/tracking/", "");
			usuario = pH.getValue("txtUsuario"+idEmpresa);
			pass = pH.getValue("txtPassword"+idEmpresa);
			
			System.out.println("USUARIO:"+usuario+"   PASS:"+pass);
            
            HttpPost authpost = new HttpPost(dom+"/admin.php/ingresar");
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
            
            PropertiesHelperAPI pHa= new PropertiesHelperAPI("paths");
			pHa.loadProperties();
			String path = pHa.getValue("pdf");
			String filePath = path+"/"+prefix+".pdf";
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
            
            String HTTPpath = pHa.getValue("HTTP_pdf");
			
            String fileHTTPPath = HTTPpath+"/"+prefix+".pdf";
            String tracking = darTracking(filePath,"");
            
            //retorno.setId(idPedido);
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

	public static String darTracking(String path, String prefix)
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
            	for(int j = 0; i<=strategy.getResultantText().length(); j++){
            		retorno = strategy.getResultantText().split("\n")[j];
            		if(prefix.equals("")){
            			break;
            		}
            		else{
            			if(retorno.startsWith(prefix)){
	            			break;
		            	}
            		}
            			            	
            	}            	
	            
	            
	            System.out.println(retorno);
	        }
	        reader.close();
		}
		catch(Exception e)
		{
			
		}
		
				
		return retorno;
	}
	
	public void buscarEtiquetas(List<Ordenes> pedidosFenicio, Call_WS_APIENCUENTRA cen, String token, int canal)
	{
		 List<DataIDDescripcion> pedidosSinE = cen.DarDatosPutOrders(token, 5);
		 
		 Map<String, String> pedidosSinEtiqueta = new HashMap<>();
		 for (DataIDDescripcion ps : pedidosSinE) 
		 {
			pedidosSinEtiqueta.put(ps.getDescripcion(),ps.getDescripcion());
		 }
		 
		 List<EncuentraPedido> pedidosUpEtiqueta = new ArrayList<>();
		 
		 for (Ordenes o : pedidosFenicio) 
		 {
			 try {
				 if(pedidosSinEtiqueta.containsKey(o.getIdOrden()) && o.getEntrega()!=null && o.getEntrega().getEtiqueta()!=null &&!o.getEntrega().getEtiqueta().equals(""))
					{
						System.out.println("BUSCANDO ETIQUETA");
						EncuentraPedido eps = new EncuentraPedido();
						eps.setIdPedido(Long.parseLong(o.getIdOrden()));
						//etiqueta
						if(o.getEntrega().getEtiqueta().contains("correouy")){
							DataIDDescripcion dataEti = darEtiquetaPE(o.getEntrega().getEtiqueta(), canal, o.getIdOrden()+"correo");						
							eps.setUrlEtiqueta(dataEti.getDescripcion());						
						}
						else{
							eps.setUrlEtiqueta(o.getEntrega().getEtiqueta());						
						}	
						//tracking
						if(o.getEntrega().getEtiqueta().contains("dac")){
							eps.setTrackingNumber(darTracking(o.getEntrega().getEtiqueta(),"870-"));
						}
						else if (o.getEntrega().getEtiqueta().contains("soydelivery")) {
							eps.setTrackingNumber("SD"+o.getEntrega().getCodigoTracking());
						}
						else if (o.getEntrega().getCodigoTracking()!= null && o.getEntrega().getCodigoTracking().equals("Mercadoenv?os")) {
							eps.setTrackingNumber(darTracking(o.getEntrega().getEtiqueta(),"UES"));
						}
						else{
							eps.setTrackingNumber(o.getEntrega().getCodigoTracking());
						}
						 
						pedidosUpEtiqueta.add(eps);
					}
			} catch (Exception e) {
				System.out.println("fallo buscando etiqueta");
			}
			
		 }
		 
		 cen.updateLabels(pedidosUpEtiqueta,token);
	}
	
}
