package integraciones.wms;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Fecha;
import beans.api.ColeccionHasIntString;
import beans.api.ColeccionHasStringInt;
import beans.api.pedidoFactura;
import beans.datatypes.DTO_Articulo;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.StockDeposito;
import beans.derivation.Deposito;
import beans.derivation.DepositoStock;
import beans.encuentra.ArticuloRepoFromLoad;
import beans.encuentra.Cliente;
import beans.encuentra.ContenedorListaDTO_Articulo;
import beans.encuentra.ContenedorListaDataIdDesc;
import beans.encuentra.DataArticuloEcommerceVerifR;
import beans.encuentra.DataEcommerce_canales_envio;
import beans.encuentra.DepositoMayorista;
import beans.encuentra.EncuentraPedido;

import beans.helper.PropertiesHelper;
import logica.LogicaAPI;

public class Call_WS_APIENCUENTRA 
{
	String host = loadHost();
	
	public String callWSPOST_ParamJSON(String urlBase, String jotason)
	{
		String retorno = "";
		HttpClient httpClient = HttpClientBuilder.create().build();
		try 
		{
	    	
	    	System.out.println("llamando a "+urlBase);
	    	System.out.println("Parametros JSON "+jotason);
	    	System.out.println("Respuesta");

	    	StringEntity entityI;
	    	
	    	entityI = new StringEntity(jotason, ContentType.APPLICATION_JSON);
	    
	    	HttpPost request = new HttpPost(urlBase);
	    	request.setEntity(entityI);
	    	
		  	HttpResponse httpResponse = httpClient.execute(request);
		  	
		  	
		  	int code = httpResponse.getStatusLine().getStatusCode();
		  	HttpEntity entity = httpResponse.getEntity();
		  	
		  	byte[] buffer = new byte[1024];
		    
		  	if (entity != null) 
		  	{
		        InputStream inputStream = entity.getContent();
		        try (BufferedInputStream bis = new BufferedInputStream(inputStream);)
		        {
		          int bytesRead = 0;
		          
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
		          request.abort();
		          runtimeException.printStackTrace();
		        }
		        finally{
		          // Closing the input stream will trigger connection release
		          try 
		          {
		            inputStream.close();
		          } 
		          catch (Exception e) 
		          {
		        	e.printStackTrace();  
		          }
		        }
		      }
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
	
	public String loadHost() {
		PropertiesHelper propHelper = new PropertiesHelper("wms");
		try {
			propHelper.loadProperties();
			return propHelper.getValue("host");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getHost() {
		return this.host;
	}
		

	public String callWSGET(String URL, boolean auth)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		
		if(auth)
		{
			((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("produccion@200data.com", "200data2017"));
		}
	    
	    try 
		{
	    	HttpGet httpGetRequest = new HttpGet(URL);
	    	
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
	
	public String callWSPOSTparam(String URLbase, List<DataIDDescripcion> params)
	{
	
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		
	    try 
		{
	    	
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost(URLbase);
	    	
	    	//StringEntity params =new StringEntity(jotason);
	    	httpPostRequest.setHeader("Content-type", "application/x-www-form-urlencoded");
	    	
	    	//httpPostRequest.setEntity(params);
	    	
	    	ArrayList<NameValuePair> postParameters;
	    	postParameters = new ArrayList<>();
	    	for(DataIDDescripcion p:params){
	    		postParameters.add(new BasicNameValuePair(p.getDescripcion(), p.getDescripcionB()));
	    	}
	        
	        httpPostRequest.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));    	
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpPostRequest);
		  	int code = httpResponse.getStatusLine().getStatusCode();
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
	    	catch (IOException e) 
		    {
		      e.printStackTrace();
		      return "error";
		    }
	    	finally 
	    	{
		      httpClient.getConnectionManager().shutdown();
		    }
		return retorno;
	
		
	}
	
	public List<DataIDDescripcion> DarDatosPutOrders(String token, int idTabla)
	{
		try {
			
			//String host = "encuentra.200.com.uy";
			
			String servicio = "http://"+host+"/WMS/Integraciones/DarDatosPutOrders/tablas?token="+token; 
						
			String json = "{ "+
						"	   \"id\":"+idTabla+", "+
						"	   \"descripcion\":\"\" "+
						"	}";
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
			
			
			ContenedorListaDataIdDesc cont =  new ContenedorListaDataIdDesc(salida);
			return cont.getDatos();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	
	public List<DataIDDescripcion> SaveOrders(String token, List<EncuentraPedido> listaPedidos)
	{
		try {
			
			//String host = "encuentra.200.com.uy";
		
			String servicio = "http://"+host+"/WMS/Integraciones/SaveOrders/save?token="+token; 
			Gson gson = new Gson();
			
			String json = gson.toJson(listaPedidos, ArrayList.class);
			System.out.println(json);
			String padre = "{ \"idTabla\":0,\"datos\": ";
			String salida =  callWSPOST_ParamJSON(servicio, padre+json+"}");
			
			
			
			ContenedorListaDataIdDesc cont =  new ContenedorListaDataIdDesc(salida);
			
			return cont.getDatos();
			
		} catch (Exception e) {
		
		}
		return new ArrayList<>();
	}

	public List<DataIDDescripcion> SaveOrdersArticulosReq(String token, List<EncuentraPedido> listaPedidos,int idCanal)
	{
		try {
			
			//String host = "encuentra.200.com.uy";
		
			String servicio = "http://"+host+"/WMS/Integraciones/SaveOrders/saveArtReq?token="+token+"&idCanal="+idCanal; 
			Gson gson = new Gson();
			
			String json = gson.toJson(listaPedidos, ArrayList.class);
			System.out.println(json);
			String padre = "{ \"idTabla\":0,\"datos\": ";
			String salida =  callWSPOST_ParamJSON(servicio, padre+json+"}");
			
			ContenedorListaDataIdDesc cont =  new ContenedorListaDataIdDesc(salida);
			
			return cont.getDatos();
			
		} catch (Exception e) {
		
		}
		return new ArrayList<>();
	}
	
	public List<DataIDDescripcion> SaveCustomers(String token, List<Cliente> clientes) 
	{
		try {
			//String host = "encuentra.200.com.uy";
			
			String servicio = "http://"+host+"/WMS/Integraciones/SaveCustomers/save?token="+token; 
			Gson gson = new Gson();
			
			String json = gson.toJson(clientes, ArrayList.class);
			System.out.println(json);
			String padre = "{ \"idTabla\":0,\"datos\": ";
			String salida =  callWSPOST_ParamJSON(servicio, padre+json+"}");
			
			ContenedorListaDataIdDesc cont =  new ContenedorListaDataIdDesc(salida);
			
			return cont.getDatos();
			
		} catch (Exception e) {
		
		}
		return new ArrayList<>();
	}
	
	public List<DataIDDescripcion> ordersNotDispatched(int canal,String token)
	{
		try {
			
			//String host = "encuentra.200.com.uy";
			////String host = "10.108.0.165:8080";
		
			String servicio = "http://"+host+"/WMS/Integraciones/OrderFunctions/ordersNotDispatched?token="+token+"&canal="+canal; 
			
			String salida =  callWSGET(servicio,true);
						
			ContenedorListaDataIdDesc cont =  new ContenedorListaDataIdDesc(salida);
			
			return cont.getDatos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public void logOrder(int estado,String mensaje,Long idPedido,String token)
	{
		try 
		{
			String servicio = "http://"+host+"/WMS/Integraciones/OrderFunctions/putLog?token="+token+"&idPedido="+idPedido+"&estado="+estado+"&mensaje="+mensaje; 
			callWSGET(servicio,true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateOrdersStatus(String token, List<DataIDDescripcion> pedidos)
	{
		try {
			
			//String host = "encuentra.200.com.uy";
			////String host = "10.108.0.165:8080";
		
			String servicio = "http://"+host+"/WMS/Integraciones/OrderFunctions/updateOrdersStatus?token="+token; 

			Gson gson = new Gson();
			String json = gson.toJson(pedidos);
			json = "{ \"coleccion\" : "+json+"}";
			
			callWSPOST_ParamJSON(servicio,json);
			
		} catch (Exception e) {
		
		}
	}
	
	public Fecha darHoraHabilDeposito(String token, int idDeposito, Fecha fecha)
	{
		try {
			
			
		
			String servicio = "http://"+host+"/WMS/Integraciones/OrderFunctions/darHoraHabilDeposito?token="+token+"&idDeposito"+idDeposito; 

			Gson gson = new Gson();
			String json = gson.toJson(fecha);
			
			
			String rsp = callWSPOST_ParamJSON(servicio,json);
			
			Fecha retorno = gson.fromJson(rsp, Fecha.class);
			return retorno;
			
		} catch (Exception e) 
		{
			return null;
		}
	}
	
	
	public beans.api.json.Cliente darSenderPeYa(String token, String pedido)
	{
		try {
			
			String servicio = "http://"+host+"/WMS/Integraciones/OrderFunctions/darSenderPeYa?token="+token+"&idPedido"+pedido; 

			Gson gson = new Gson();
			
			String rsp = callWSGET(servicio,true);
			beans.api.json.Cliente retorno = gson.fromJson(rsp, beans.api.json.Cliente.class);
			return retorno;
			
		} catch (Exception e) 
		{
			return null;
		}
	}
	
	public void putFacturas(String token, List<pedidoFactura> facturas)
	{
		try {
			
			//String host = "encuentra.200.com.uy";
			////String host = "10.108.0.165:8080";
		
			String servicio = "http://"+host+"/WMS/Integraciones/OrderFunctions/putFacturas?token="+token; 

			Gson gson = new Gson();
			String json = gson.toJson(facturas);
			json = "{ \"coleccion\" : "+json+"}";
			
			callWSPOST_ParamJSON(servicio,json);
						
			
		} catch (Exception e) {
		
		}
	}
	
	public Map<String, Integer> vistaStocks(List<DataIDDescripcion> lista, int origen, int idEmpresa)
	{
		try {
			LogicaAPI logica = new LogicaAPI();
			String token = logica.darToken(2,2000);
			
			//String host = "encuentra.200.com.uy";
			////String host = "10.108.0.165:8080";
		
			String servicio = "http://"+host+"/WMS/Integraciones/Views/stocks?token="+token+"&origen="+origen; 
			
			Gson gson = new Gson();
			String json = gson.toJson(lista);
			json = "{ \"coleccion\" : "+json+"}";
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
			/*String salida = "{" + 
					"    \"coleccion\": {" + 
					"        \"BRX9020211380\": 29," + 
					"        \"BBC7873VN0000\": 15" + 
					"    }" + 
					"}";*/
						
			ColeccionHasStringInt cont =  gson.fromJson(salida, ColeccionHasStringInt.class);
			
			return cont.getColeccion();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Map<String, DataIDDescripcion> vistaStocksQ(DataIDDescripcion consulta,String token)
	{
		try 
		{
			String servicio = "http://"+host+"/WMS/Integraciones/Views/stockQ?token="+token; 
			Gson gson = new Gson();
			String json = gson.toJson(consulta);
			String salida =  callWSPOST_ParamJSON(servicio, json);
			return  gson.fromJson(salida, new TypeToken<Hashtable<String,DataIDDescripcion>>(){}.getType());
			
		} catch (Exception e) {
		
		}
		return null;
	}
	
	public List<Deposito> vistaDepositos(int idEmpresa)
	{
		try {
			LogicaAPI logica = new LogicaAPI();
			String token = logica.darToken(idEmpresa);
			
			
			String servicio = "http://"+host+"/WMS/Integraciones/Views/depositos?token="+token; 
			
			Gson gson = new Gson();
			String json = "";
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
		
			List<Deposito> datos = gson.fromJson(salida, new TypeToken<List<Deposito>>(){}.getType());			
			
			
			return datos;
			
		} catch (Exception e) {
		
		}
		return new ArrayList<>();
	}
	
	
	

	public void verificarRepoArticulos(String token) {
		try {
			
			//String host = "encuentra.200.com.uy";
			////String host = "10.108.0.165:8080";
		
			String servicio = "http://"+host+"/WMS/Integraciones/OrderFunctions/verificarRepoArticulos?token="+token; 

			
			
			callWSPOST_ParamJSON(servicio,"");						
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<DataIDDescripcion> putMaestros(String token, List<DataIDDescripcion> datos, String tabla)
	{
			
		try {
			
			//String host = "encuentra.200.com.uy";
			
			String servicio = "http://"+host+"/WMS/Integraciones/Synchronizer/putMasters?tabla="+tabla+"&token="+token; 
			Gson gson = new Gson();
			
			String jsonList = gson.toJson(datos);
			
			String salida =  callWSPOST_ParamJSON(servicio, jsonList);
			
			
			ContenedorListaDataIdDesc cont =  new ContenedorListaDataIdDesc(salida);
			return cont.getDatos();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public List<DataIDDescripcion> putConexionSubfamilias(String token, List<DataIDDescripcion> datos, String tabla, String columna1, String columna2)
	{
			
		try {
			
			//String host = "encuentra.200.com.uy";
			
			String servicio = "http://"+host+"/WMS/Integraciones/Synchronizer/putConexionSubfamilias?tabla="+tabla+"&columna1="+columna1+"&columna2="+columna2+"&token="+token; 
			Gson gson = new Gson();
			
			String jsonList = gson.toJson(datos);
			
			String salida =  callWSPOST_ParamJSON(servicio, jsonList);
			
			
			ContenedorListaDataIdDesc cont =  new ContenedorListaDataIdDesc(salida);
			return cont.getDatos();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public List<DTO_Articulo> putArticulos(String token, List<DTO_Articulo> datos)
	{
			
		try {
			
			//String host = "encuentra.200.com.uy";
			
			String servicio = "http://"+host+"/WMS/Integraciones/Synchronizer/putItems?token="+token; 
			
			Gson gson = new Gson();
			DTO_Articulo [] data = new DTO_Articulo[datos.size()];
			for (int i = 0; i < datos.size(); i++) 
			{
				data[i] = datos.get(i);
			}
			
			String jsonList = gson.toJson(data);
			
			String salida =  callWSPOST_ParamJSON(servicio, jsonList);
			
			ContenedorListaDTO_Articulo cont =  new ContenedorListaDTO_Articulo(salida);
			return cont.getDatos();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	
	public String putStk(String token, List<StockDeposito> datos)
	{
			
		try {
			
			//String host = "encuentra.200.com.uy";
			
			String servicio = "http://"+host+"/WMS/Integraciones/Synchronizer/putStk?token="+token; 
			
			Gson gson = new Gson();
			
			String jsonList = gson.toJson(datos);
			
			return  callWSPOST_ParamJSON(servicio, jsonList);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String putDeposM(String token, List<DepositoMayorista> datos)
	{
			
		try {
			
			//String host = "encuentra.200.com.uy";
			
			String servicio = "http://"+host+"/WMS/Integraciones/Synchronizer/putWhs?token="+token; 
			
			Gson gson = new Gson();
			
			String jsonList = gson.toJson(datos);
			
			return callWSPOST_ParamJSON(servicio, jsonList);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public void updateDestinoPedido(String token,EncuentraPedido p, int destino, double montoEnvio) 
	{
		
		try {
			
			String servicio = "http://"+host+"/WMS/Integraciones/SaveOrders/saveDestino?destino="+destino+"&costoEnvio="+montoEnvio+"&token="+token; 
			
			
			Gson gson = new Gson();
			
			String jsonP = gson.toJson(p);
			
			callWSPOST_ParamJSON(servicio, jsonP);
			
			
			
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}

	public Map<Integer, String> darParametros(String token, String params) 
	{
		try 
		{
			
			String servicio = "http://"+host+"/WMS/Integraciones/Views/parametros?token="+token;
			Gson gson = new Gson();
			String data = params;
			
			String salida =  callWSPOST_ParamJSON(servicio, data);
						
			ColeccionHasIntString cont =  gson.fromJson(salida, ColeccionHasIntString.class);
			return cont.getColeccion();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String, Integer> darDepositosEcommerce(String token,int idCanal) 
	{
		try 
		{
			
			String servicio = "http://"+host+"/WMS/Integraciones/Views/depositosEcommerce?token="+token+"&canal="+idCanal;
			Gson gson = new Gson();
			String json = "";
			json = "{ \"coleccion\" : "+json+"}";
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
						
			Map<String,Integer> datos = gson.fromJson(salida, new TypeToken<Hashtable<String,Integer>>(){}.getType());
			return datos;
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Map<Integer, String> darIntegraciones(String token) 
	{
		try 
		{
			
			String servicio = "http://"+host+"/WMS/Integraciones/Views/prod?token="+token;
			Gson gson = new Gson();
			String json = "";
			json = "{ \"coleccion\" : "+json+"}";
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
						
			ColeccionHasIntString cont =  gson.fromJson(salida, ColeccionHasIntString.class);
			return cont.getColeccion();
			
		} 
		catch (Exception e) 
		{
		
		}
		return null;
	}

	public List<DataIDDescripcion> savePickingOrder(String token, List<ArticuloRepoFromLoad> lista) 
	{
		
	try {
			
		//DataDeposOrdenes algo = ClientePPGA.darOrdenes();
        //algo.getArticulos();
		
			String servicio = "http://"+host+"/WMS/Integraciones/SaveOrders/savePickingOrder?token="+token; 
			Gson gson = new Gson();
			
			String json = gson.toJson(lista, ArrayList.class);
			System.out.println(json);
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
			
			
			ContenedorListaDataIdDesc cont =  new ContenedorListaDataIdDesc(salida);
			
			System.out.println(salida);
			return cont.getDatos();
			
			
		} catch (Exception e) {
			return null;
		}
		
	}

	public EncuentraPedido darIdPedido(String token, String track) 
	{

		try {
			
			String servicio = "http://"+host+"/WMS/Integraciones/DarDatosPutOrders/pedidoFromTracking?tracking="+track+"&token="+token; 
			
			
			Gson gson = new Gson();
			
			String salida = callWSPOST_ParamJSON(servicio, "");
			
			EncuentraPedido p = gson.fromJson(salida, EncuentraPedido.class);
			
			return p;
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;	
	}

	public void updateOrdersStatus(String token, String dataArticuloEcommerceVerifR)
	{
		
		try 
		{
			String servicio = "http://"+host+"/WMS/Integraciones/OrderFunctions/updateStatus?token="+token; 
			String salida = callWSPOST_ParamJSON(servicio, dataArticuloEcommerceVerifR);
			System.out.println(salida);
					
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public 	Map<String,String> searchSKU(String token, List<String> lista) 
	{
		try {
			
			String servicio = "http://"+host+"/WMS/Integraciones/DarDatosPutOrders/searchSKU?token="+token; 
			
			
			Gson gson = new Gson();
			String json = gson.toJson(lista, ArrayList.class);
			
			
			String salida = callWSPOST_ParamJSON(servicio, json);
			
			
			Map<String,String> datos = gson.fromJson(salida, new TypeToken<Hashtable<String,String>>(){}.getType());
			return datos;
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
			
	}
	
	public 	Map<String,String> PedidosID(String token, int dias, String estados) 
	{
		

		try {
			String servicio = "http://"+host+"/WMS/Integraciones/DarDatosPutOrders/PedidosID?token="+token+"&dias="+dias+"&estados="+estados; 
			Gson gson = new Gson();
			String salida = callWSGET(servicio, false);
			return gson.fromJson(salida, new TypeToken<Hashtable<String,String>>(){}.getType());
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	public List<DepositoStock> darDepositos(List<DataIDDescripcion> carrito, String token, int idCanal) 
	{
		try 
		{
			String servicio = "http://"+host+"/WMS/Integraciones/OrderFunctions/getStockOrder?token="+token+"&idCanal="+idCanal; 
			Gson gson = new Gson();
			String json = gson.toJson(carrito, ArrayList.class);
			String salida = callWSPOST_ParamJSON(servicio, json);
			List<DepositoStock>  datos = gson.fromJson(salida, new TypeToken<List<DepositoStock>>(){}.getType());
			Collections.sort(datos);
			return datos;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public void updateLabels(List<EncuentraPedido> pedidosUpEtiqueta,String token) 
	{
		//updateLabels
		try 
		{
			String servicio = "http://"+host+"/WMS/Integraciones/SaveOrders/updateLabels?token="+token; 
			Gson gson = new Gson();
			String json = gson.toJson(pedidosUpEtiqueta, ArrayList.class);
			callWSPOST_ParamJSON(servicio, json);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

	public List<DataEcommerce_canales_envio> darListaEcommerce_canales_envio(int canal,  String token) 
	{
		try 
		{
			String servicio = "http://"+host+"/WMS/Integraciones/DarDatosPutOrders/darListaEcommerce_canales_envio?canal="+canal+"&token="+token; 
			Gson gson = new Gson();
			
			String rsp =  callWSGET(servicio, false);
			
			List<DataEcommerce_canales_envio>  datos = gson.fromJson(rsp, new TypeToken<List<DataEcommerce_canales_envio>>(){}.getType());
			
			return datos;
		} catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<DataEcommerce_canales_envio>();
		}
	}
	
	public DataArticuloEcommerceVerifR darArticuloEcommerceReqReclasifica(Long idPedido,  String token) 
	{
		try 
		{
			String servicio = "http://"+host+"/WMS/Integraciones/DarDatosPutOrders/darArticuloEcommerceReqReclasifica?idPedido="+idPedido+"&token="+token; 
			Gson gson = new Gson();
			
			
			String rsp = callWSGET(servicio, false);
			System.out.println(rsp);
			DataArticuloEcommerceVerifR  datos = gson.fromJson(rsp, DataArticuloEcommerceVerifR.class);
			
			return datos;
		} catch (Exception e) 
		{
			e.printStackTrace();
			return new DataArticuloEcommerceVerifR();
		}
	}
	
	
	public Hashtable<Integer, List<DataDescDescripcion>> distribucion_forus_pedidos(String distribuciones, String token) 
	{
		Hashtable<Integer, List<DataDescDescripcion>> retorno = new Hashtable<Integer, List<DataDescDescripcion>>();
		try 
		{
			String servicio = "http://"+host+"/WMS/Integraciones/Customs/distribucionForuspedidos?distribuciones="+distribuciones+"&token="+token; 
			Gson gson = new Gson();
			String salida = callWSGET(servicio, false);
			retorno = gson.fromJson(salida, new TypeToken<Hashtable<Integer,List<DataDescDescripcion>>>(){}.getType());
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	public List<DataIDDescripcion> putMaestrosBarras(String token, List<DataDescDescripcion> datos, String tabla)
	{
		try {
			
			//String host = "encuentra.200.com.uy";
			
			String servicio = "http://"+host+"/WMS/Integraciones/Synchronizer/putMastersBarras?tabla="+tabla+"&token="+token; 
			
			Gson gson = new Gson();
			
			String jsonList = gson.toJson(datos);
			
			String salida =  callWSPOST_ParamJSON(servicio, jsonList);
			
			ContenedorListaDataIdDesc cont =  new ContenedorListaDataIdDesc(salida);
			return cont.getDatos();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
}

