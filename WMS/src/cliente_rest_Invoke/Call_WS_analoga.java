package cliente_rest_Invoke;

import helper.PropertiesHelper;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.net.ssl.SSLContext;


import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;
import persistencia.MSSQL;
import persistencia._EncuentraPersistir;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;



import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import beans.Articulo;
import beans.Fecha;
import beans.Usuario;
import beans.encuentra.IPrint;

import jsonObjects.*;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataEcommerce_canales_envio;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.Compras;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.EncuentraPedidoArticulo;
import eCommerce_jsonObjectsII.EncuentraPedidoArticuloReq;
import eCommerce_jsonObjectsII.Items;
import eCommerce_jsonObjectsII.Pedido;
import eCommerce_jsonObjectsII.RspEtiqueta;



public class Call_WS_analoga {
	
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
	
	public String JSONUpdateState (Long idPedido, String idEcommerce, String track, int estado) {
		String json = "";
		if(!track.equals("") && !track.equals(idPedido+""))
		{
			/*if(articuloR.getIdDestino()==700000){
				track = "UES000"+track;
			}*/
			
				json=
						 "     { "+
						 "        \"id\":\""+idEcommerce+"\", "+
						 "        \"estado\":\""+statusMapperEncuentra_Fenicio(estado)+"\", "+
						 "		  \"trackingID\":\""+track+"\" "+
						 "     } "
						 ;
		} 
		else
		{
			json=
					 "     { "+
					 "        \"id\":\""+idEcommerce+"\", "+
					 "        \"estado\":\""+statusMapperEncuentra_Fenicio(estado)+"\" "+
					 "     } "
					 ;
		}
		
		return json;
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
		switch (canal) {
		case 1:
			URLbase = "https://catlifestyle.com.uy/tracking/";
			break;
		case 2:
			URLbase = "https://www.columbia.com.uy/tracking/";		
			break;
		case 3:
			URLbase = "https://www.hushpuppies.com.uy/tracking/";
			break;
		case 4:
			URLbase = "https://www.merrell.com.uy/tracking/";
			break;		
		case 6:
			URLbase = "https://www.pasqualini.com.uy/tracking/";
			break;
		case 7:
			URLbase = "https://www.rockford.com.uy/tracking/";
			break;
		}
		
		String funcion = "set/compras";
		String retorno = this.callWSPOST_ParamJSON(URLbase, funcion, jotason,canal,idEmpresa);
		System.out.println(retorno);
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
 
        try 
        {
        
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
	
	
	
	
	
	public Hashtable<Long, EncuentraPedido> getPedidos(int idEmpresa, Hashtable<String, DataIDDescripcion> destinoPedidos) 
	{
		Logica Logica = new Logica();	
		
		Fecha fechaActual = new Fecha();
		
		List<DataIDDescDescripcion> canales = Logica.EcommercedarCanalesAnaloga(idEmpresa);
		Hashtable<String, Integer> canalesHT = new Hashtable<>();
		
		for(DataIDDescDescripcion c:canales)
		{
			canalesHT.put(c.getDescripcionII(), c.getId());
		}
		
		Hashtable<Long, EncuentraPedido> pedidosHT = new Hashtable<>();
		List<Compras> compras = Logica.sincroPedidosWeb(idEmpresa,destinoPedidos);
		
		List<DataIDDescripcion> pedido9000 = new ArrayList<>();
		pedido9000 = Logica.ArticulosECSinPedir(idEmpresa);
		
		boolean integracionActiva = false;
		integracionActiva = Logica.darIntegracionProductiva(3, idEmpresa);
						
		String idMail = "";
		String fails = "";
		
		System.out.println(compras.size());	
			for (Compras c : compras) 
			{
				
				if(c.getCompra().getItems().size()==0){	
					Logica.persistir("insert into aaatemporal (id,IdEmpresa) values ("+c.getCompra().getId()+","+idEmpresa+")");				
				}
				if(c.getCompra().getItems().size()!=0){			
					
				
				
					System.out.println(c.getCompra().getId());
					
					EncuentraPedido p = new EncuentraPedido();
					p.setDescripcion(c.getCliente().getNombre()+" "+c.getCliente().getApellido());
					
					int cantidad=0;
					Double descuento=0.0;
					
					p.setTicketNumber("");
					p.setEstado(c.getCompra().getEstado());
					p.setUrlEtiqueta(c.getEtiqueta());
					List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
				
					p.setIdPedido(Long.parseLong(c.getCompra().getId()));
					p.setSubpedido(p.getIdPedido()+"");
					p.setIdFenicio(c.getCompra().getIdVenta());
					
					
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
						
							cantidad+=cantidadItem;
							EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
													
							art.setArticulo(it.getSku());
							art.setCantidad(cantidadItem);
							art.setProcesada(0);
							pedidos.add(art);
							importeTotal+=importe;
							
							
							Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando linea articulo "+art.getArticulo(),0,idEmpresa);	
					}
					
					p.setCantidad(cantidad);
					p.setDescuento(descuento);
					p.setArticulosPedido(pedidos);
					p.setSucursalPick(c.getCompra().getSucursal());
					p.setArticulosPedidoReq(new ArrayList<>());
					
					
					p.setFormaPago(c.getCompra().getMetodoPago());
					Double porcDescuento = (descuento*100)/importeTotal;
					if(descuento==0 && importeTotal==0){
						porcDescuento=0.0;
					}
					
					try
					{
						//p.setCanalAnaloga(canalesHT.get(c.getCompra().getIdCanal()));
						p.setCanalAnaloga(Integer.parseInt(c.getCompra().getIdCanal()));
					}
					catch (Exception e) 
					{
						p.setCanalAnaloga(0);
					}
					
					if(p.getCanalAnaloga()==7){
						System.out.println("");
					}
					boolean canalActivo = false;
					canalActivo = Logica.canalActivoEC(p.getCanalAnaloga(), idEmpresa);
					
					p.setUrlEtiqueta("");
					p.setFecha(c.getCompra().getFecha());
					p.setPrecio(importetotalOV);
					
					boolean esPickup = false;
					//
					if(p.getSucursalPick()!=null && !p.getSucursalPick().equals(""))
					{
						esPickup = true;		
						if(!c.getCompra().iscNc())
						{
							p.setShippingType(new DataIDDescripcion(2,""));
						}
						else
						{
							p.setShippingType(new DataIDDescripcion(3,""));
						}
					}
					else {
						p.setShippingType(new DataIDDescripcion(1,""));
					}
					
					if(p.save(idEmpresa))
					{
						try
						{
							Logica.logPedido(p.getIdPedido(), 0, 0, "Pedido Sincronizado ",0,idEmpresa);
													
							Logica.logPedido(p.getIdPedido(), 0, 0, "Marcando el pedido en estado preparando ",0,idEmpresa);										
													
							//pickup
							if(esPickup)
							{
								System.out.println(p.getSucursalPick());
								Utilidades u = new Utilidades();
								int sucPick = u.tryParse(p.getSucursalPick());
								p.updateShipping(sucPick, p.getIdPedido()+"","",idEmpresa);
								
								DataArticuloEcommerceVerifR articuloR = new DataArticuloEcommerceVerifR(p.getIdPedido(), 0, p.getCantidad(), "", p.getDescripcion(), "", sucPick);
							
								articuloR.setTelCliente(c.getCliente().getTelefono());
								articuloR.setFecha(fechaActual.darFechaAnio_Mes_Dia());
								articuloR.setIdEcommerce(p.getIdFenicio());
								//articuloR.setTracking(p.getIdFenicio());
								articuloR.setEstadoEncuentra(1);		
								articuloR.setCanal(p.getCanalAnaloga());
								articuloR.setTracking(p.getIdPedido()+"");
								
								String url = "";											//GENERO ETIQUETA PICKUP
								try 
								{
									DataIDDescripcion env = Logica.darEnvioPedido(articuloR.getIdPedido(),idEmpresa);
									url = IPrint.ImprimirEtiquetasNuevas(articuloR,idEmpresa,env,"CD 9000"," Av. Italia 4346   								   						    Tel.:2613 7566 		  Montevideo-Uruguay",1
											,true,"","");
									
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (DocumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							
								p.setUrlEtiqueta(url);
								p.updateEtiqueta(0,idEmpresa);
																				//COMUNICO CAMBIO DE ESTADO A FENICIO
								if(canalActivo){
								
									if (integracionActiva)
									{
										//setPedidos(jotason,p.getCanalAnaloga(),idEmpresa);
										if(p.getShippingType().getId()!=3)
										{
											Logica.CambioEstadoMarketPlace(idEmpresa, articuloR);
										}
										
									}
									
								}
							}
							
							Logica.logPedido(p.getIdPedido(), 0, 0, "grabando Orden para importar",0,idEmpresa);
						
							
							//GUARDO A QUE DEPOSITOS SE VAN A PEDIR LOS ARTICULOS
							
							DataIDDescripcion data;
							for (Items it : c.getCompra().getItems())
							{
								if(it.getOrigen().equals("9000")){
									data = new DataIDDescripcion(Integer.parseInt(it.getCant()),it.getSku());
									data.setIdLong(p.getIdPedido());
									data.setIdB(Integer.parseInt(it.getDocVisual()));
									pedido9000.add(data);							
								}
								// prueba por caidas cuando no guarda en articulos req
								if(it.getOrigen()==null)
									it.setOrigen("900001");
								if(it.getDocVisual()==null)
									it.setDocVisual("0");
								
								boolean req = Logica.persistir("INSERT INTO `ecommerce_pedido_articulos_req` (`idPedido`, `idArticulo`, `Deposito`, `CantidadRequerida`,"+
										" `CantidadProcesasa`, `Confirmado`,cantConfirmada,docVisual, idEmpresa) " +
										" VALUES ('"+p.getIdPedido()+"', '"+it.getSku()+"', "+it.getOrigen()+", '"+it.getCant()+"', '0', '0',0,"+it.getDocVisual()+","+idEmpresa+");");
							
								if(!req) {
									fails += p.getIdPedido()+",";
									idMail += p.getIdPedido()+"";
								}
							
								Logica.logPedido(p.getIdPedido(), 0, 0, " Guardando pedido de articulo "+it.getSku()+
										" a deposito "+it.getOrigen(),0,idEmpresa);							
								
								
							}
							
							/*
							if(pro.todosConfirmados(p.getIdPedido(),idEmpresa)){
								Logica.updateEcommerceEstado(p.getIdPedido(), 2,idEmpresa);
							}
							*/
							pedidosHT.put(p.getIdPedido(), p);
						
						}
						catch (Exception e) 
						{
							System.out.println("error");
						}
						//PERSISTO DATOS DEL CLIENTE
						c.getCliente().setIdPedido(p.getIdPedido()+"");
						c.getCliente().save(idEmpresa);
						
					}
					else {
						fails += p.getIdPedido()+",";
						idMail += p.getIdPedido()+"";
					}
					
				}
			
			}
			
			if(!pedido9000.isEmpty())
			{
				boolean manual = false;
				Logica.darArticuloRepoFromLoadForus(pedido9000,1200,manual,idEmpresa,9000,2,false);
				int last = Logica.darNextSincRepo(idEmpresa)-1;
				Logica.actualizarOKSincRepo(0,last,idEmpresa);//la dejo en OK 0 para que no interfiera con el picking
			}
			
			try {
				if(!fails.equals("")) {
					fails = fails.substring(0,fails.length()-1);
					
					List<SendMail> mails = new ArrayList<>();
					String bodyName = "Los siguientes pedidos no se sincronizaron, o se sincronizaron incorrectamente: "+fails;
					SendMail mail = new SendMail("SFORUY"+idMail, "mguerra@200.com.uy,onviera@200.com.uy,gmonzon@200.com.uy", "Error en sincro de pedidos - FORUS UY", bodyName, "encuentra@200.com.uy");
					mails.add(mail);
					
					Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();			
					//api.PutColaEnvioMails(mails, idEmpresa);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		
		return pedidosHT;
		
	}
	

	
public String getEtiqueta(int id, int canal,int idEmpresa){
	String URLbase2 = getUrl(canal);
	String funcion2 = "/get/etiqueta?idCompra="+id+"";
	String retorno2 = callWSGET(URLbase2, funcion2,canal,idEmpresa);
	return retorno2;
}

public String getUrl(int canal){
	
	String URLbase = "";
	switch (canal) {
	case 1:
		URLbase = "https://catlifestyle.com.uy/tracking/";
		break;
	case 2:
		URLbase = "https://columbia.com.uy/tracking/";		
		break;
	case 3:
		URLbase = "https://hushpuppies.com.uy/tracking/";
		break;
	case 4:
		URLbase = "https://merrell.com.uy/tracking/";
		break;
	
	case 6:
		URLbase = "https://www.pasqualini.com.uy/tracking/";
		break;
	case 7:
		URLbase = "https://www.rockford.com.uy/tracking/";
		break;
	case 5:
		URLbase = "https://www.supermall.uy/tracking/";
		break;
	}
	return URLbase;
}
	
	
	
	
	
	
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public String callWSGET(String URLbase, String funcion, int idCanal,int  idEmpresa)
	{
		Logica Logica = new Logica();
		DataIDDescDescripcion canal= Logica.EcommercedarCanalesAnaloga(idCanal,idEmpresa);
		String usu=canal.getDesc();
		String pass=canal.getDescripcion();
		
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
		
		
		HttpClient httpClient = getHttpClient("","");
		
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

	public List<JSONRespPedidos> SetEtiquetas(Hashtable<String, DataIDDescripcion> pedidos, int canal, int idEmpresa, Hashtable<String, DataIDDescripcion> destinoPedidos) 
	{
		Logica Logica = new Logica();
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		//List<DataEcommerce_canales_envio> envios = Logica.darListaEcommerce_canales_envio("select tipoEnvio,codigo,courier,iddeposito,Usuario,Pass from ecommerce_canales_envio CE inner join ecommerce_envioml EM on EM.idEmpresa=CE.idEmpresa AND nombre=courier where idcanal="+canal+" AND CE.IdEmpresa="+idEmpresa);
		List<DataEcommerce_canales_envio> envios = Logica.darListaEcommerce_canales_envio(canal,idEmpresa);
		
		List<String> estadosConsultar = new ArrayList<>();
		estadosConsultar.add("preparado");
		estadosConsultar.add("preparando");
		estadosConsultar.add("procesando");
		
		
		
		boolean integracionActiva = false;
		integracionActiva = Logica.darIntegracionProductiva(3, idEmpresa);
		
		for (String estdo : estadosConsultar) 
		{
			int cantidadPasadas = 10;
			Fecha fecha = new Fecha(-10, 0, 0);
			Fecha fechaActual = new Fecha();
			int pag =1;
			boolean pri=true;
			while (cantidadPasadas>=0) 
			{
				String URLbase = getUrl(canal);
				//String funcion = "get/compras/1/100/"+fecha.darFechaAnio_Mes_Dia()+"/procesando/";
				/*preparando*/
				String funcion = "get/compras/"+pag+"/100/"+fecha.darFechaAnio_Mes_Dia()+"/"+estdo+"/";
				String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
				
				
				Pedido pedido =JSONReader.readJsonPedidos(retorno);
		         
				if(pri){
					cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
					pri=false;
				}
				
				for (Compras c : pedido.getCompras()) 
				{				
					
					String idFenicio = c.getCompra().getId();
					
					if(idFenicio.equals("1365244"))
					{
						System.out.println("case");
					}
					
					System.out.println(idFenicio);
					
					
					if(pedidos.get(idFenicio)!=null)
					{
						EncuentraPedido p = new EncuentraPedido();
						p.setIdPedido(new Long(pedidos.get(idFenicio).getDescripcion()));
						
						if(p.getIdPedido()==178629) {
							System.out.println(p.getIdPedido());
						}
						
						Shipping shipp = new Shipping();
						shipp.setCliente(c.getCliente());
						/*Esto se cambio*/
						Utilidades utilidades = new Utilidades();
						shipp.getCliente().setApellido(utilidades.validarComillas(shipp.getCliente().getApellido()));
						shipp.getCliente().setNombre(utilidades.validarComillas(shipp.getCliente().getNombre()));
						shipp.getCliente().setCalle(utilidades.validarComillas(shipp.getCliente().getCalle()));
						
						
						if(c.getCliente().getCiudad()==null)
						{
							shipp.getCliente().setCiudad(c.getCliente().getLocalidad());
						}
						shipp.getCliente().setObs(c.getCompra().getObservaciones());
						shipp.getCliente().setTelefono(c.getCliente().getTelefono());
						shipp.getCliente().setEmail(c.getCliente().getEmail());
						
						DataDescDescripcion eti = null;
						int destino = 0;
						
						DataArticuloEcommerceVerifR r = Logica.darArticuloEcommerceReqReclasifica(p.getIdPedido(),0,idEmpresa);
						if(c.getEnvio()!=null)
						{
							System.out.println(c.getEnvio().getCodigo());
							
							for(DataEcommerce_canales_envio env:envios){
								if(c.getEnvio().getCodigo().equals(env.getCodigo()))
								{								
									destino = Integer.parseInt(env.getIddeposito());
									System.out.println(env.getCourier());
									if(env.getCourier().equals("SOYDELIVERY") || env.getCourier().equals("PEDIDOS_YA")) 
									{
										
										p.updateShipping(destino,"","",idEmpresa);
										
										if(integracionActiva)
										{
											Logica.CambioEstadoMarketPlace(idEmpresa, r);
										}
									}				
									else 
									{
										eti = api.setEnvio(shipp, env,idFenicio+"",c.getCompra().getFecha(),"PM",idEmpresa);
									}
									break;
								}
							}
						}
						
						
						if(eti==null)//es pickup
						{
							
							int idDestino = 0;
							
							try
							{
								//idDestino =destinoPedidos.get(idFenicio).getId();
								idDestino = Integer.parseInt(c.getCompra().getSucursal());
							}
							catch (Exception e){ System.out.println("el pedido "+idFenicio+" no tiene destino o es sdl");}
							
							if(idDestino!=0)
							{
								System.out.println("SETEANDO DESTINO");
								System.out.println("----------------");
								
								
								p.updateShipping(idDestino, p.getIdPedido()+"","",idEmpresa);
								
								//int sucPick =Integer.parseInt(p.getSucursalPick());
								
								
								DataArticuloEcommerceVerifR articuloR = new DataArticuloEcommerceVerifR(p.getIdPedido(), 0, p.getCantidad(), "", c.getCliente().getNombre()+ " "+c.getCliente().getApellido(), "", idDestino);
							
								articuloR.setTelCliente(c.getCliente().getTelefono());
								articuloR.setFecha(fechaActual.darFechaAnio_Mes_Dia());
								articuloR.setIdEcommerce(c.getCompra().getId());
																
								String url = "";											//GENERO ETIQUETA PICKUP
								try 
								{
									DataIDDescripcion env = Logica.darEnvioPedido(articuloR.getIdPedido(),idEmpresa);
									url = IPrint.ImprimirEtiquetasNuevas(articuloR,idEmpresa,env,"CD 9000"," Av.  Italia 4346   								   						    Tel.:2613 7566 		  Montevideo-Uruguay",1,true,"","");
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (DocumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							
								p.setUrlEtiqueta(url);
								p.updateEtiqueta(0,idEmpresa);
							}
							
							//COMUNICO CAMBIO DE ESTADO A FENICIO							
							if(integracionActiva)
							{
								/*String jotason="[ "+
										 "     { "+
										 "        \"id\":\""+idFenicio+"\", "+
										 "        \"estado\":\"preparando\" "+
										 "     } "+
										 "]";
								setPedidos(jotason,canal,idEmpresa);*/
								try {
									r.setTracking(p.getIdPedido()+"");
									Logica.CambioEstadoMarketPlace(idEmpresa, r);
								} catch (Exception e) {
									System.out.println("error yebdo a buscar datos a BD (PUT COLA CAMBIO DE ESTADOS)");
								}
								
							}							
						}
						else
						{
							System.out.println("SETEANDO DESTINO");
							System.out.println("----------------");
							p.setUrlEtiqueta(eti.getDescripcion());
							p.updateEtiqueta(0,idEmpresa);
							p.updateShipping(destino, eti.getId(),shipp.getCliente().getEmail(),idEmpresa);
												//COMUNICO CAMBIO DE ESTADO A FENICIO
							String track = eti.getId();
							if(destino == 700000)
							{
								track = ""+eti.getId();
							}
								
							if(integracionActiva)
							{
								/*String jotason="[ "+
										 "     { "+
										 "        \"id\":\""+idFenicio+"\", "+
										 "        \"estado\":\"preparando\", "+
										 "		  \"trackingID\":\""+track+"\" "+
										 "     } "+
										 "]";
								setPedidos(jotason,canal,idEmpresa);*/
								r.setTracking(track);
								Logica.CambioEstadoMarketPlace(idEmpresa, r);
							}																
								
						}
						/*if(pedidos.get(idFenicio).getId()==0) {
							Logica.persistir("update ecommerce_pedido set idcanalml = "+canal+" where idpedido="+p.getIdPedido());
						}*/
						
					}
					
				}
				cantidadPasadas--;
				pag++;
			}
		}
		return null;
		
		
		
	}
	
	public List<JSONRespPedidos> ReSetCanal(Hashtable<String, DataIDDescripcion> pedidos, int canal, int idEmpresa) 
	{
		Logica Logica = new Logica();
		
		List<String> estadosConsultar = new ArrayList<>();
		estadosConsultar.add("procesando");
		estadosConsultar.add("preparando");
		estadosConsultar.add("preparado");		
		
		for (String estdo : estadosConsultar) 
		{
			int cantidadPasadas = 10;
			Fecha fecha = new Fecha(-10, 0, 0);
			Fecha fechaActual = new Fecha();
			int pag =1;
			boolean pri=true;
			while (cantidadPasadas>=0) 
			{
				String URLbase = getUrl(canal);
				//String funcion = "get/compras/1/100/"+fecha.darFechaAnio_Mes_Dia()+"/procesando/";
				/*preparando*/
				String funcion = "get/compras/"+pag+"/100/"+fecha.darFechaAnio_Mes_Dia()+"/"+estdo+"/";
				String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
				
				
				Pedido pedido =JSONReader.readJsonPedidos(retorno);
		         
				if(pri){
					cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
					pri=false;
				}
				
				for (Compras c : pedido.getCompras()) 
				{				
					
					String idFenicio = c.getCompra().getId();					
					
					if(idFenicio.equals("1260260") ) {
						System.out.println("caso");
					}
					
					System.out.println(idFenicio);
					
					
					if(pedidos.get(idFenicio)!=null)
					{
						Logica.persistir("update ecommerce_pedido set idcanalml = "+canal+" where idpedido="+pedidos.get(idFenicio).getDescripcion());						
					}
					
				}
				cantidadPasadas--;
				pag++;
			}
		}
		return null;
		
		
		
	}
	
	public List<DataIDDescripcion> PedidosPorEstado(String estado, int canal, int dias, int idEmpresa, int estadoSetEncuentra, Usuario u) 
	{
		Logica Logica = new Logica();
		
		int cantidadPasadas = 10;
		Fecha fecha = new Fecha(-dias, 0, 0);
		Fecha fechaActual = new Fecha();
		int pag =1;
		boolean pri=true;
		List<DataIDDescripcion> pedidos = new ArrayList<>();
		Hashtable<Integer, Compras> comprasHT = new Hashtable<>();
		String idsFenicio = "";
		
		while (cantidadPasadas>=0) 
		{
			String URLbase = getUrl(canal);
			//String funcion = "get/compras/1/100/"+fecha.darFechaAnio_Mes_Dia()+"/procesando/";
			/*preparando*/
			String funcion = "get/compras/"+pag+"/100/"+fecha.darFechaAnio_Mes_Dia()+"/"+estado+"/";
			String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
			
			System.out.println(retorno);
			Pedido pedido =JSONReader.readJsonPedidos(retorno);
	         
			if(pri){
				cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
				pri=false;
			}
			
			for (Compras c : pedido.getCompras()) 
			{			
				System.out.println(c.getCompra().getId());
				comprasHT.put(Integer.parseInt(c.getCompra().getId()),c);
				
				DataIDDescripcion data = new DataIDDescripcion(Integer.parseInt(c.getCompra().getId()),statusMapper(estado));			
				pedidos.add(data);
				idsFenicio += "'"+c.getCompra().getId()+"',";			
			}
			cantidadPasadas--;
			pag++;
		}
		
		StringBuilder sb = new StringBuilder();
		int i = 0;
		List<DataIDDescripcion> pedidosPeYA = new ArrayList<>();
		CredencialesPEYA cr = null;
		List<String> cambiosEstado = new ArrayList();
		List<DataIDDescripcion> estados = Logica.darListaDataIdDescripcionConsulMYSQL("select id,descripcion from ecommerce_estado_encuentra where idEmpresa="+idEmpresa);
		estados.remove(0);
		
		try {
			idsFenicio = idsFenicio.substring(0,idsFenicio.length()-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Hashtable<Integer, DataIDDescripcion> htPedidos = Logica.darHasIdPedidoEstado(idsFenicio, idEmpresa); 
		
		//String descEstado= "";
		for(DataIDDescripcion d:pedidos)
		{
			
			//Long idPedidoEn = Logica.darIdPedidoIdFenicio(d.getId()+"", idEmpresa);
			//DataIDDescripcion ped = Logica.darIdPedidoEstado(d.getId()+"", idEmpresa);
			DataIDDescripcion ped = htPedidos.get(d.getId());
			if(ped!=null) 
			{	
				System.out.println("pedido "+ped.getIdLong());
				if(ped.getIdLong().equals("215974") ) {
					System.out.println("test");
				}
				
				//descEstado = d.getDescripcion();
				/*sb.append("update ecommerce_pedido set EstadoEcommerce ='"+d.getDescripcion()+"' where IdEmpresa="+idEmpresa+" and idfenicio="+d.getId()+"; ");
				i++;
				if(i>500)
				{
					Logica.persistir(sb.toString());
					i=0;
					sb = new StringBuilder();
					System.out.println("PERSISTIENDO");
				}*/
				
				if(estadoSetEncuentra!=Integer.parseInt(ped.getDescripcion()))
				{
					if(estadoSetEncuentra==3)
					{
						String trackingPeYA = Logica.tackingpedidoPeYA(d.getId()+"",idEmpresa);
						if(!trackingPeYA.equals(""))
						{
							pedidosPeYA.add(new DataIDDescripcion(d.getId(), trackingPeYA));
							if(cr==null)
							{
								Gson gson = new Gson();
								String pass = Logica.darListaEcommerce_canales_envioPY(canal, idEmpresa).getPass();
								cr = gson.fromJson(pass, CredencialesPEYA.class);
								
							}
						}
					}
					
					
					if(estadoSetEncuentra==3 && Integer.parseInt(ped.getDescripcion())==34) 
					{
						
					}
					else if(estadoSetEncuentra==3 && Integer.parseInt(ped.getDescripcion())==25) 
					{
						
					}
					else 
					{
						cambiosEstado.addAll(Logica.listquery_updateEcommerceEstado(ped.getIdLong(),estadoSetEncuentra,idEmpresa, u, estados));					
					}
					
				}
			}
			
		}
		//Logica.persistir("update ecommerce_pedido set EstadoEcommerce ='"+descEstado+"' where IdEmpresa="+idEmpresa+" and idfenicio in ("+idsFenicio+"); ");
		Logica.getEper().persistirTransacciones(cambiosEstado);
		
		Call_WS_APIENCUENTRA call = new Call_WS_APIENCUENTRA();
		for (DataIDDescripcion d : pedidosPeYA) 
		{
			Compras c = comprasHT.get(d.getId());
			call.confirmarPickupPeYA(d.getDescripcion(), idEmpresa, cr, c,canal);
		}
		
		System.out.println("PERSISTIENDO");
		
		
		return null;
		
	}
	
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
			case 25:
				estado = "preparado";
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
	
	
	public Hashtable<String, DataIDDescripcion> DestinoPedidos(int canal, int dias, int idEmpresa, Hashtable<String, DataIDDescripcion> retornable) 
	{
		Logica Logica = new Logica();
		
		int cantidadPasadas = 10;
		Fecha fecha = new Fecha(-dias, 0, 0);
		Fecha fechaActual = new Fecha();
		int pag =1;
		boolean pri=true;
		
		try {
			while (cantidadPasadas>=0) 
			{
				String URLbase = getUrl(canal);
				//String funcion = "get/compras/1/100/"+fecha.darFechaAnio_Mes_Dia()+"/procesando/";
				/*preparando*/
				String funcion = "get/compras/"+pag+"/100/"+fecha.darFechaAnio_Mes_Dia()+"/";
				String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
				
				System.out.println(URLbase);
				Pedido pedido =JSONReader.readJsonPedidos(retorno);
		         
				if(pri){
					cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
					pri=false;
				}
				
				for (Compras c : pedido.getCompras()) 
				{			
					if(c.getCompra().getId().equals("1260260") ) {
						System.out.println("caso");
					}
					
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
					
					
					retornable.put(c.getCompra().getId(),new DataIDDescripcion(destino,""+canal));
					
				}

				cantidadPasadas--;
				pag++;
			}				
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return retornable;
		
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
	
	
	public String reSetEtiquetas(String idPedidoFenicio,Long idPedido, int canal, int idEmpresa, String fechaP, boolean returnPath, Compras c) 
	{
		Logica Logica = new Logica();
		String track = "";
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		Fecha fechaActual = new Fecha();
		//List<DataEcommerce_canales_envio> envios = Logica.darListaEcommerce_canales_envio("select tipoEnvio,codigo,courier,iddeposito,Usuario,Pass from ecommerce_canales_envio CE inner join ecommerce_envioml EM on EM.idEmpresa=CE.idEmpresa AND nombre=courier where idcanal="+canal+" AND CE.IdEmpresa="+idEmpresa);
		List<DataEcommerce_canales_envio> envios = Logica.darListaEcommerce_canales_envio(canal,idEmpresa);
		
		List<String> estadosConsultar = new ArrayList<>();
		estadosConsultar.add("preparando");
		estadosConsultar.add("preparado");
		
		boolean integracionActiva = false;
		integracionActiva = Logica.darIntegracionProductiva(3, idEmpresa);
		
		boolean canalActivo = false;
		try {
			canalActivo = Logica.canalActivoEC(canal, idEmpresa);
		} catch (Exception e) {}
		if(c==null)
		{
			c = darCompra (idPedidoFenicio, idPedido,  idEmpresa,  fechaP,integracionActiva,envios,estadosConsultar,canal);
		}
		
		 
		if(c==null)
		{
			return null;
		}
		String idFenicio = c.getCompra().getId();
		
		EncuentraPedido p = new EncuentraPedido();
		p.setIdPedido(idPedido);
		
		
		Shipping shipp = new Shipping();
		
		if(c.getHoraDesde ()!=null && !c.getHoraDesde().equals(""))
		{
			shipp.setHoraDesde(c.getHoraDesde());
			shipp.setHoraHasta(c.getHoraHasta());
		}
		
		shipp.setCliente(c.getCliente());
		
		if(c.getCliente().getCiudad()==null)
		{
			shipp.getCliente().setCiudad(c.getCliente().getLocalidad());
		}
		shipp.getCliente().setObs(c.getCompra().getObservaciones());
		shipp.getCliente().setEmail(c.getCliente().getEmail());
		DataDescDescripcion eti = null;
		int destino = 0;
		
		if(c.getEnvio()!=null)
		{
			System.out.println(c.getEnvio().getCodigo());
			
			for(DataEcommerce_canales_envio env:envios){
				if(c.getEnvio().getCodigo().equals(env.getCodigo())){								
					destino = Integer.parseInt(env.getIddeposito());
					eti = api.setEnvio(shipp, env,idFenicio+"",c.getCompra().getFecha(),"PM",idEmpresa);														
					break;
				}
			}
		}
		
		if(eti==null)//es pickup
		{
			
			int idDestino = 0;
			try
			{
				idDestino = Integer.parseInt(c.getCompra().getSucursal());
			}
			catch (Exception e){ System.out.println("el pedido "+idFenicio+" no entra");}
			
			if(idDestino!=0)
			{
				System.out.println("SETEANDO DESTINO");
				System.out.println("----------------");
				
				
				p.updateShipping(idDestino, p.getIdPedido()+"","",idEmpresa);
				
				//int sucPick =Integer.parseInt(p.getSucursalPick());
				
				
				DataArticuloEcommerceVerifR articuloR = new DataArticuloEcommerceVerifR(p.getIdPedido(), 0, p.getCantidad(), "", c.getCliente().getNombre()+ " "+c.getCliente().getApellido(), "", idDestino);
			
				articuloR.setTelCliente(c.getCliente().getTelefono());
				articuloR.setFecha(fechaActual.darFechaAnio_Mes_Dia());
				articuloR.setIdEcommerce(c.getCompra().getId());
												
				String url = "";											//GENERO ETIQUETA PICKUP
				try 
				{
					DataIDDescripcion env = Logica.darEnvioPedido(articuloR.getIdPedido(),idEmpresa);
					url = IPrint.ImprimirEtiquetasNuevas(articuloR,idEmpresa,env,"CD 9000"," Av. Italia 4346   								   						    Tel.:2613 7566 		  Montevideo-Uruguay",1,true,"","");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}							
				p.setUrlEtiqueta(url);
				p.updateEtiqueta(0,idEmpresa);
			}
			
		}
		else
		{
			System.out.println("SETEANDO DESTINO");
			System.out.println("----------------");
			p.setUrlEtiqueta(eti.getDescripcion());
			p.updateEtiqueta(0,idEmpresa);
			p.updateShipping(destino, eti.getId(),shipp.getCliente().getEmail(),idEmpresa);
								//COMUNICO CAMBIO DE ESTADO A FENICIO
			track = eti.getId();
			if(destino == 700000)
			{
				track = ""+eti.getId();
			}
			if(canalActivo)
			{
				
				if(integracionActiva)
				{
					/*String jotason="[ "+
							 "     { "+
							 "        \"id\":\""+idFenicio+"\", "+
							 "        \"estado\":\""+c.getCompra().getEstado()+"\", "+
							 "		  \"trackingID\":\""+track+"\" "+
							 "     } "+
							 "]";
					setPedidos(jotason,canal,idEmpresa);*/
					DataArticuloEcommerceVerifR r = Logica.darArticuloEcommerceReqReclasifica(p.getIdPedido(),0,idEmpresa);
					Logica.CambioEstadoMarketPlace(idEmpresa, r);
				}																
			}	
		}
		
		if(returnPath)
		{
			return p.getUrlEtiqueta();
		}
		else
		{
			return track;
		}
		
		
		
		
	}


	private Compras darCompra(String idPedidoFenicio, Long idPedido, int idEmpresa, String fechaP,boolean integracionActiva, List<DataEcommerce_canales_envio> envios, List<String> estadosConsultar, int canal)  
	{
		
		int dia = Integer.parseInt(fechaP.split(" ")[0].split("-")[2]);
		int mes = Integer.parseInt(fechaP.split(" ")[0].split("-")[1]);
		int anio = Integer.parseInt(fechaP.split(" ")[0].split("-")[0]);
		if(dia-1==0){
			dia=28;
			mes=mes-1;
			if(mes==0)
			{
				mes = 12;
				anio = anio-1;
			}
		}
		else{
			dia =dia-4;
		}
		String fecha, diaS, mesS = "";
		
		if(dia < 1) {
			dia = 28 + dia; 
			mes--;
			if(mes == 0) {
				mes = 12;
				anio--;
			}
		}
		
		if(dia<10) {
			diaS = "0"+dia; 
		}
		else
		{
			diaS = ""+dia;
		}
		if(mes<10) {
			mesS = "0"+mes;
		}
		else
		{
			mesS = ""+mes;
		}
		
		
		fecha = anio+"-"+mesS+"-"+diaS;
		
		for (String estdo : estadosConsultar) 
		{
			int cantidadPasadas = 10;
			
			
			
			int pag =1;
			boolean pri=true;
			while (cantidadPasadas>=0) 
			{
				String URLbase = getUrl(canal);
				//String funcion = "get/compras/1/100/"+fecha.darFechaAnio_Mes_Dia()+"/procesando/";
				/*preparando*/
				String funcion = "get/compras/"+pag+"/100/"+fecha+"/"+estdo+"/";
				String retorno = this.callWSGET(URLbase, funcion,canal,idEmpresa);
				
				
				Pedido pedido =JSONReader.readJsonPedidos(retorno);
		         
				if(pri)
				{
					cantidadPasadas = (Integer.parseInt(pedido.getTotal())/100);
					pri=false;
				}
				
				for (Compras c : pedido.getCompras()) 
				{				
					String idFenicio = c.getCompra().getId();
					System.out.println(idFenicio);
					
					if(idFenicio!=null && idFenicio.equals(idPedidoFenicio))
					{
						return c;
					}					
				}
				cantidadPasadas--;
				pag++;
				
			}
		}
		return null;
	}
	

}
