package cliente_rest_Invoke;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
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
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.DocumentException;

import beans.Fecha;
import beans.Usuario;
import beans.encuentra.DepositoAdmin;
import beans.encuentra.IPrint;


import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataEcommerce_canales_envio;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.EncuentraPedidoArticulo;
import eCommerce_jsonObjectsII.MercadoEnvioModdo;
import eCommerce_jsonObjectsII.PedidoModdo;
import eCommerce_jsonObjectsII.ShippingData;
import eCommerce_jsonObjectsII.linesModdo;
import helper.PropertiesHelper;
import jsonObjects.Credenciales;
import jsonObjects.Shipping;
import logica.Logica;
import logica.Utilidades;

public class Call_WS_MODDO {	
	
	//String host = "http://qa-lt.moddopt.com"; /*QA*/
	String host = "https://adminco.modalia.com/hermesCore/";
	Logica Logica = new Logica();
	
	public static void main (String[] args)
	{
		Logica Logica = new Logica();
		Usuario u = Logica.loginEncuentraSinEmpresa("Moddo", "Forus!#$");
		
		int idEmpresa = u.getIdEmpresa();
		Call_WS_MODDO moddo = new Call_WS_MODDO();
		String token = moddo.getToken(2);
		//moddo.getPedidos(token, 0, "[1,38]", idEmpresa,"");
		List<DataIDDescripcion> canales = Logica.canalesActivosModdo(idEmpresa);
		canales.remove(0);
		int periodoDias = 1;
		for(DataIDDescripcion c : canales) {
			moddo.getPedidos(token, periodoDias, "[2,3,4]", idEmpresa, c.getId(), c.getDescripcionB(), u);
		}
		
	}
	
	public String getToken(int idEmpresa){
		
		Logica Logica = new Logica();
		String url = "login/authenticate";	/*QA*/
		String json = "{\"userName\": \"Nviera\", \"password\": \"Nviera200!\"}";
		String tok = "";
		try {
			DataIDDescripcion token = Logica.darDataIdDescripcion("SELECT TIME_TO_SEC(timediff(CURRENT_TIMESTAMP(),Obtenido)) tiempo,token FROM apitoken_cliente WHERE idCliente = 1");
			if(token!=null)
			{
				if(token.getId()<3600)//una hora de vida
				{
					return token.getDescripcion();
				}
				else
				{
					tok = callWSPOST_ParamJSON(host,url, json,"");
					Logica.persistir("UPDATE apitoken_cliente SET token='"+tok+"' WHERE IdEmpresa="+idEmpresa+" and  `idCliente`=1;");
					return tok;
				}
			}
			else
			{
				tok = callWSPOST_ParamJSON(host,url, json,"");
				Logica.persistir("UPDATE apitoken_cliente SET token='"+token+"' WHERE IdEmpresa="+idEmpresa+" and `idCliente`=1;");
				return tok;
			}
			
			
		} catch (Exception e) {
			return tok;
		}
		
		
	}
	
	public void getPedidos(String tok, int dias,String jsonEstados, int idEmpresa, int canal, String idSite, Usuario u){
		Utilidades util = new Utilidades();
		try {
			Fecha startDate = new Fecha(-dias,0,0);
			Fecha endDate = new Fecha(0,0,0);
			Gson gson = new Gson();
			String funcion = "rest/orders/idSite/"+idSite+"/startDate/"+startDate.darFechaAnio_Mes_Dia()+
					"T00:00:00Z/endDate/"+endDate.darFechaAnio_Mes_Dia()+"T23:59:59Z";	
			List<String> parameters = new ArrayList<>();
			parameters.add("");
			List<String> values = new ArrayList<>();
			values.add(jsonEstados);
			String salida =  callWSGET(host, funcion, parameters, values, tok);
			List<PedidoModdo> ventas = gson.fromJson(salida, new TypeToken<List<PedidoModdo>>(){}.getType()); 
			
			Logica Logica = new Logica();	
			List<DataIDDescripcion> couriers = Logica.darListaDataIdDescripcionMYSQLConsulta("select id,idModdo from couriers where idEmpresa="+idEmpresa);
			couriers.remove(0);
			List<DataIDDescripcion> pedido9000 = new ArrayList<>();
			int depoCentral = Integer.parseInt(Logica.darParametroEmpresa(idEmpresa, 4));//Id de Deposito Principal
			int depoWeb = Integer.parseInt(Logica.darParametroEmpresa(idEmpresa, 5));//Id de Deposito Ecommerce
						
			Hashtable<String, Hashtable<String, EncuentraPedidoArticulo>> subpedidos = null;
			Hashtable<String, EncuentraPedidoArticulo> lineas = null;
			Hashtable<String, Integer> estadoSubpedidos = null;
			
			Hashtable<String, DataIDDescripcion> pedidosEncuentra = Logica.PedidosPorCanal(idEmpresa, canal, dias);
			
			for(PedidoModdo v:ventas){				
				
				Double importeTotal = 0.0; 
				Double importetotalOV = 0.0;
				Double descuento=0.0;
				Double costoEnvio = 0.0;
				Double porcDescuento = 0.0;
							
				System.out.println("");
				System.out.println(v.getOrderNumber());
				
				EncuentraPedido p = new EncuentraPedido();						//DATOS DE LA VENTA
				String fechaPedido = v.getDateIso().replace("T", " ");
				p.setUrlEtiqueta("");
				p.setFecha(fechaPedido.substring(0,fechaPedido.length()-5));
				p.setDescripcion(v.getInvoiceData().getName()+" "+v.getInvoiceData().getSurnames());				
				p.setEstado("preparando");					
				p.setIdFenicio(v.getOrderNumber());
				p.setTicketNumber(v.getTicketNumber());							
				p.setFormaPago(v.getPaymentType());
				p.setCanalAnaloga(canal);					
				costoEnvio = (double) v.getShippingCost();
				String shippingType = v.getShippingType();
				boolean clickCollect = shippingType.equals("P");
				
				
				//SEPARO SUBPEDIDOS
				subpedidos = new Hashtable<>();	
				estadoSubpedidos = new Hashtable<>();
				for ( linesModdo it: v.getLines()) 
				{
					try {
						int storeId = 0;
						try {storeId = Integer.parseInt(it.getStoreId());} catch (Exception e) {}
						
						if(storeId != depoCentral) {
							int estado_sub = statusMapperModdo_Encuentra((int)it.getIdStatusSubOrder());
							if(estado_sub==0) {
								estado_sub = statusMapperModdo_Encuentra((int)v.getIdStatusOrder());
							}
							if(p.getIdEstado() == 1) {
								estado_sub = 3;
							}
							estadoSubpedidos.put(it.getSubOrderNumber(), estado_sub);
						}
						else {
							estadoSubpedidos.put(it.getSubOrderNumber(),1);
						}
						
						if(pedidosEncuentra.get(it.getSubOrderNumber()) == null) {
							Double importe = (double) it.getAmount();
							int cantidadItem =  1; 
							try {
								cantidadItem = Integer.parseInt(it.getQuantity());
							}catch (Exception e) {}
							EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
							
							art.setArticulo(it.getEan());
							art.setCantidad(cantidadItem);
							art.setProcesada(0);
							art.setImporte(importe);							
							
							
							art.setOrigen(storeId);
							art.setClickCollect(clickCollect);
							
							if(it.getTrackingNumber()!= null) 
								{art.setSubTracking(it.getTrackingNumber());}
							
							int distribucion = 0;
							try {distribucion = Integer.parseInt(it.getTransferErpNumber());} catch (Exception e) {};
							
							art.setDistribucionAfecta(distribucion);
							
							try {
								if(subpedidos.get(it.getSubOrderNumber()) == null) {
									lineas = new Hashtable<>();
									lineas.put(it.getEan(), art);
									subpedidos.put(it.getSubOrderNumber(), lineas);
								}else {
									if(subpedidos.get(it.getSubOrderNumber()).get(it.getEan()) == null) {
										subpedidos.get(it.getSubOrderNumber()).put(it.getEan(), art);
									}else {
										EncuentraPedidoArticulo artE = new EncuentraPedidoArticulo();
										artE = subpedidos.get(it.getSubOrderNumber()).get(it.getEan());
										artE.setCantidad(art.getCantidad()+1);
										artE.setImporte(art.getImporte()+importe);
										subpedidos.get(it.getSubOrderNumber()).put(it.getEan(), artE);
									}
								}	
							} catch (Exception e) {
								System.out.println(it.getSubOrderNumber());
							}
																	
							//setPedidoSincronizado(tok, it.getIdSiteProducto()+"", it.getStoreId(), v.getOriginalOrderNumber());
						}
						else {
							System.out.println("");							
							if(pedidosEncuentra.get(it.getSubOrderNumber()).getId() != estadoSubpedidos.get(it.getSubOrderNumber())) {
								System.out.println("ACTUALIZANDO SUBPEDIDO "+it.getSubOrderNumber());
								System.out.println("estado Enc: "+pedidosEncuentra.get(it.getSubOrderNumber()).getId() +" - estado Mdo: "+ estadoSubpedidos.get(it.getSubOrderNumber()));
								Logica.updateEcommerceEstado(pedidosEncuentra.get(it.getSubOrderNumber()).getIdLong(), estadoSubpedidos.get(it.getSubOrderNumber()), idEmpresa, u);
							}
						}
					} catch (Exception e) {
						System.out.println("SUBPEDIDO NO GENERADO AUN POR PARTE DE MODDO");
					}									
				}
				
				if(subpedidos.size() > 0) {	//si me viene con subpedido null o sin distribucion no lo ingreso, espero a la proxima sincro
					//CREO TANTOS PEDIDOS COMO SUBPEDIDOS TENGO
					 Enumeration<String> keys = subpedidos.keys();
					 boolean preparaCentral = false;
					 
					 while (keys.hasMoreElements()) {						 
						 int cantidad=0;	
						 
						 int idPedido = Logica.darSeries("insert into utilseries (IdTipo,IdEmpresa) values ('MODDO',"+idEmpresa+")").get(0);
						 p.setIdPedido(new Long(idPedido+""));
						 String subPedido = keys.nextElement();
						 p.setSubpedido(subPedido);
						 
						 Logica.logPedido(p.getIdPedido(), 0, 0, "comienzo sinconizacion",0,idEmpresa);
							
						Logica.logger(0, 100, "desempaquetando pedido WEB "+p.getIdPedido(),idEmpresa);
										
						if(costoEnvio>0.0)
						{
							Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando costo de envio "+costoEnvio,0,idEmpresa);
						}
									
						List<EncuentraPedidoArticulo> articulos = new ArrayList<>(subpedidos.get(subPedido).values());
						for(EncuentraPedidoArticulo it: articulos){
							Logica.logPedido(p.getIdPedido(), 0, 0, "Agregando linea articulo "+it.getArticulo(),0,idEmpresa);	
							cantidad+=it.getCantidad();
							importeTotal+=it.getImporte();
						}
						
						try {
							p.setIdEstado(estadoSubpedidos.get(subPedido));
							
						} catch (Exception e) {
							System.out.println("FALLO INTERPRETANDO ESTADO");
						}
						
						importetotalOV = (double) v.getAmount();
						porcDescuento = (descuento*100)/importeTotal;
						if(descuento==0 && importeTotal==0){
							porcDescuento=0.0;
						}
						p.setDescuento(descuento);
						p.setPrecio(importetotalOV);
						p.setCantidad(cantidad);
						
						p.setArticulosPedido(articulos);				
						p.setArticulosPedidoReq(new ArrayList<>());
						
						preparaCentral = p.getArticulosPedido().get(0).getOrigen() == depoCentral;	//pedido sale de deposito central ???
						
						//SETEO DESTINO
						p.setSucursalPick("");						
						int sucursal = util.parseStringInt(v.getStoreDevileryId());
						int operador = (int)v.getIdOperator();
						for(DataIDDescripcion c:couriers){
							if(c.getDescripcion().equals(operador+"")){
								p.setSucursalPick(String.valueOf(c.getId()));
								break;
							}
						}
						
						DataIDDescripcion formaEnvio = new DataIDDescripcion();
						switch (shippingType) {
						case "MD":
						case "C":
							p.setIdDepositoEnvio(0);
							formaEnvio.setId(1);
							break;
							
						case "RR":
							p.setIdDepositoEnvio(sucursal);
							if(p.getSucursalPick().equals("")) {
								p.setSucursalPick(sucursal+"");
							}
							formaEnvio.setId(2);
							break;
							
						case "P": 
							p.setIdDepositoEnvio(sucursal);
							if(p.getSucursalPick().equals("")) {
								p.setSucursalPick(sucursal+"");
							}		
							formaEnvio.setId(3);
							break;
							
						default:
							break;
						}
						/*if(shippingType.equals("C")) {
							
						}
						else {
							p.setSucursalPick(sucursal+"");
						}*/
						
						p.setShippingType(formaEnvio);
						
						if(p.save(idEmpresa))
						{
							Logica.logPedido(p.getIdPedido(), 0, 0, "Pedido Sincronizado ",0,idEmpresa);														
							Logica.logPedido(p.getIdPedido(), 0, 0, "Marcando el pedido en estado preparando ",0,idEmpresa);
																					
							//GUARDO CLIENTE
							GuardarCliente(v.getShippingData(),p.getIdPedido()+"",idEmpresa);
							
							//GUARDO DESTINO Y ETIQUETA
							//SOLO GENERAMOS ETIQUETAS PARA LOS PEDIDOS QUE SALEN DE CENTRAL DE MOMENTO
							GuardarDestino(p,preparaCentral,idEmpresa,p.getCanalAnaloga(), tok);						
																			
							//GUARDO A QUE DEPOSITOS SE VAN A PEDIR LOS ARTICULOS							
							pedido9000.addAll(Logica.AltaArticuloReq(p, idEmpresa, depoCentral, depoWeb));
							
							//CAMBIO DE ESTADO (envio etiqueta)
							DataArticuloEcommerceVerifR r = Logica.darArticuloEcommerceReqReclasifica(p.getIdPedido(),0,idEmpresa);
							r.setEstadoEncuentra(p.getIdEstado());
							Logica.CambioEstadoMarketPlace(idEmpresa, r);							
						}
						
						
					 }
				}							 	
			}
			
			if(pedido9000.size() > 0) {
				boolean manual = false;
				Logica.darArticuloRepoFromLoadForus(pedido9000,depoWeb,manual,idEmpresa,depoCentral,2,false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPedidoSincronizado(String tok, String site, String store, String pedido){
		 try {
			String url = "/hermesCore/rest/orders/updatePendingAcceptByStore/idSite/"+site+"/idStore/"+store+"/orderNumber/"+pedido;
			callWSGET(host, url, new ArrayList<>(), new ArrayList<>(), tok);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String JSONUpdateState(String pedido, String track, int estado, String ticket, int courier, String fechaEntrega, String etiqueta){
		String json = "";
		try {
			String formatDocument = "";
			if(!etiqueta.equals("")) {
				formatDocument = "PDF";
			}
			json = "{"+
								"\"orderNumber\":\""+pedido+"\","+
								"\"trackingNumber\":\""+track+"\","+
								"\"status\":\""+statusMapperEncuentra_Moddo(estado)+"\","+
								"\"ticketNumber\":\""+ticket+"\","+
								"\"idOperator\":\""+CouriersMapperEncuentra_Moddo(courier)+"\","+
								"\"deliveryDate\":\""+fechaEntrega+"\","+
								"\"logisticDocument\":\""+etiqueta+"\","+
								"\"logisticDocumentFormat\":\""+formatDocument+"\""+
							"}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return json;
	}
	
	public void UpdateState(String pedido, String track, int estado, String ticket, int courier, String fechaEntrega, String etiqueta, String tok, String idSite){
		 try {
			String url = "/rest/orders/updateOrders/idSite/"+idSite;
			String formatDocument = "";
			if(!etiqueta.equals("")) {
				formatDocument = "PDF";
			}
			String json = "{"+
								"\"orderNumber\":\""+pedido+"\","+
								"\"trackingNumber\":\""+track+"\","+
								"\"status\":\""+statusMapperEncuentra_Moddo(estado)+"\","+
								"\"ticketNumber\":\""+ticket+"\","+
								"\"idOperator\":\""+CouriersMapperEncuentra_Moddo(courier)+"\","+
								"\"deliveryDate\":\""+fechaEntrega+"\""+
								"\"logisticDocument\":\""+etiqueta+"\""+
								"\"logisticDocumentFormat\":\""+formatDocument+"\""+
							"}";
			callWSPOST_ParamJSON(host, url, json, tok);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DataDescDescripcion setEtiqueta (Long idpedido, int destino, int canal, int idEmpresa) {
		Logica Logica = new Logica();
		DataDescDescripcion etiqueta = new DataDescDescripcion("","");
		try {
			DataEcommerce_canales_envio envio = Logica.darEcommerce_canal_envio(canal, idEmpresa, destino);
			
			if(envio!= null)
			{	
				Cliente clienteSh = null;
				if(destino > 9000) {
					clienteSh = Logica.darClienteShippingEcommerce(idpedido, idEmpresa);
				}
				else {
					DepositoAdmin d = Logica.BuscarDeposito(destino, idEmpresa);
					clienteSh.setNombre(destino+"");
					clienteSh.setApellido(d.getNombre());
					clienteSh.setCalle(d.getDireccion());
					clienteSh.setNroPuerta(d.getNroPuerta()+"");
					clienteSh.setCiudad(d.getCiudad());
					clienteSh.setDepartamento(d.getDepartamento());
					clienteSh.setTelefono(d.getTelefono());
					clienteSh.setObs(d.getLocal()+"");
				}
				
				Credenciales credenciales = new Credenciales();
				
				credenciales.setUser(envio.getUsuario());
				credenciales.setPass(envio.getPass());
				credenciales.setPedido(idpedido+"");										
				
				Shipping shipp = new Shipping();
				
				shipp.setCredenciales(credenciales);
				shipp.setCliente(clienteSh);
														
				Call_WS_APIENCUENTRA enc = new Call_WS_APIENCUENTRA();
				etiqueta = enc.setEnvio(shipp, envio, idpedido+"", envio.getFechaEntrega(), "PM", idEmpresa);				
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return etiqueta;
	}
	
	public int CouriersMapperEncuentra_Moddo(int courier) {
		int estado = 6;
		try {
			switch (courier) {
			case 400000:
				estado = 0;
				break;
			case 500000:
				estado = 22;
				break;
			case 700000:
				estado = 33;
				break;			
			}
		} catch (Exception e) {
			return estado;
		}
		return estado;
	}
	
	public int statusMapperEncuentra_Moddo(int status) {
		int estado = 0;
		try {
			switch (status) {
			case 1:
				estado = 2;
				break;
			case 2:
				estado = 2;
				break;
			case 3:
				estado = 2;
				break;
			case 4:
				estado = 3;
				break;
			case 5:
				estado = 3;
				break;
			case 6:
				estado = 4;
			case 99:
				estado = 8;	
				break;	

			}
		} catch (Exception e) {
			return estado;
		}
		return estado;
	}
	
	public int statusMapperModdo_Encuentra(int status) {
		int estado = 0;
		try {
			switch (status) {
			case 1:
				estado = 1;
				break;
			case 2:
				estado = 3;
				break;
			case 3:
				estado = 4;
				break;
			case 4:
				estado = 6;
				break;
			case 8:
				estado = 99;	
				break;	

			}
		} catch (Exception e) {
			return estado;
		}
		return estado;
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
	
	public void GuardarCliente(ShippingData s, String idPedido, int idEmpresa) {
		
		Cliente c = new Cliente();
		Utilidades u = new Utilidades();
		try {
			c.setNombre(s.getName());
			c.setApellido(s.getSurnames());
			
			c.setCalle(s.getAddress());
			
			String [] puerta_apto = null;
			try {
				String puerta_apto_String = u.soloNumeros(s.getAddress());
				puerta_apto = puerta_apto_String.split(" ");					
			} catch (Exception e) {				
				
			}
			try {
				c.setNroPuerta(puerta_apto[0]);
			} catch (Exception e) {
				c.setNroPuerta("");
			}
			try {
				c.setNroApto(puerta_apto[1]);
			} catch (Exception e) {
				c.setNroApto("");
			}
			
			
			c.setCiudad(s.getLocation());
			c.setDepartamento(s.getProvince());
			c.setCp(s.getPostalCode());
			
			c.setTelefono(s.getMobilePhone());
			c.setEmail(s.getEmail());
			
			c.setDocumentoNro(s.getCustomerDocument());
			c.setIdPedido(idPedido);
			
			c.save(idEmpresa);
			
		} catch (Exception e) {
			System.out.println("Error guardando Cliente Moddo");
			e.printStackTrace();
		}
		
	}
	
	public void GuardarDestino(EncuentraPedido p, boolean preparaCentral, int idEmpresa, int canal, String token) {
		try {
			System.out.println("SETEANDO DESTINO");
			System.out.println("----------------");
			String url = "";	
			String tracking = "";
			int destino = 0;
			try {destino = Integer.parseInt(p.getSucursalPick());} catch (Exception e) {}
			
			if(canal == 9) {	//ETIQUETAS DE MERCADO ENVIO
				try {
					DataDescDescripcion eti = etiquetaMercadoEnvio(p.getArticulosPedido().get(0).getOrigen(), p.getSubpedido(), token);
					url = eti.getDescripcion();
					tracking = eti.getId();
				} catch (Exception e) {
					System.out.println("No se pudo ir a buscar etiqueta a ML");
				}
				
			}
			else {
				if(preparaCentral) {
					if(p.getSucursalPick().equals(p.getIdDepositoEnvio()+"")){	//pickup con Envio propio
						DataArticuloEcommerceVerifR articuloR = new DataArticuloEcommerceVerifR(p.getIdPedido(), 0, p.getCantidad(), "", p.getDescripcion(), "", Integer.parseInt(p.getSucursalPick()));
						Fecha fechaActual = new Fecha();
						articuloR.setFecha(fechaActual.darFechaAnio_Mes_Dia());
						//GENERO ETIQUETA PICKUP
						try 
						{
							DataIDDescripcion env = Logica.darEnvioPedido(articuloR.getIdPedido(),idEmpresa);
							url = IPrint.ImprimirEtiquetasNuevas(articuloR,idEmpresa,env,"CD 9000"," Av. Italia 4346   								   						    Tel.:2613 7566 		  Montevideo-Uruguay",1,true,"","");
							tracking = p.getIdPedido()+"";
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
					}
					else{	//COURIER
						
							DataDescDescripcion eti = setEtiqueta(p.getIdPedido(), destino, p.getCanalAnaloga(), idEmpresa);								
						
						if(!eti.getDescripcion().equals("")){								
							url = eti.getDescripcion();
							tracking = eti.getId();
						}
					}
				}
				else {
					Utilidades u = new Utilidades();
					if(u.darParametroEmpresaINT(idEmpresa, 40) == 1) {	//PARAMETROS PARA GENERAR ENVIOS DESDE TIENDA
						if(p.getSucursalPick().equals(p.getIdDepositoEnvio()+"")){	// GENERO ENVIO DE TIENDA
							DataDescDescripcion eti = setEtiqueta(p.getIdPedido(), destino, p.getCanalAnaloga(), idEmpresa);								
							
							if(!eti.getDescripcion().equals("")){								
								url = eti.getDescripcion();
								tracking = eti.getId();
							}
						}						
					}
				}
			}
			
			
			try {
				if(tracking.equals("") && p.getArticulosPedido().get(0).getSubTracking()!= null) {
					tracking = p.getArticulosPedido().get(0).getSubTracking();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}							
			
			if(destino!=0) {
				p.setUrlEtiqueta(url);
				p.updateEtiqueta(0,idEmpresa);
				p.updateShipping(destino, tracking,"",idEmpresa);
			}
		} catch (Exception e) {
			System.out.println("Error guardando Destino Moddo");
			e.printStackTrace();
		}
	}
	
	public DataDescDescripcion etiquetaMercadoEnvio(int idStore,String orderNumber, String token) {
		DataDescDescripcion data = new DataDescDescripcion("","");
		try {
			String funcion = "/rest/orders/documents/idSite/"+437+"/idStore/"+idStore+"/orderNumber/"+orderNumber;
			if(token == null || token.equals("")) {
				token = getToken(2);
			}
			String salida =  callWSGET(host, funcion, new ArrayList<>(), new ArrayList<>(), token);
			Gson gson = new Gson();
			MercadoEnvioModdo menvio = gson.fromJson(salida, MercadoEnvioModdo.class);
			
			Utilidades u = new Utilidades();
			String path = "";
			String pathHTTP = "";
			String track = "";
			
			if(menvio != null && !menvio.getContentFile().equals("")) {
				try {
					PropertiesHelper ph = u.initPropertiesHelper("paths");
					
					byte[] decoded = u.decodeFile(menvio.getContentFile());
					if(decoded != null) {
						path = ph.getValue("pdf")+orderNumber+".pdf";
						pathHTTP = ph.getValue("HTTP_pdf")+"/"+orderNumber+".pdf";
						track = "";
						u.writeToFile(path, decoded);
					}
					
					
				} catch (Exception e) {}
			}			
					
			data.setId(track);
			data.setDescripcion(pathHTTP);
		} catch (Exception e) {
			System.out.println("Error guardando etiqueta MercadoEnvio Moddo");
			e.printStackTrace();
		}
		return data;
	}
	
	
}

