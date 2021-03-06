package integraciones.couriers.ues;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.time.Instant;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.Body;
import com.sun.xml.wss.saml.internal.saml11.jaxb10.X509DataType.X509Certificate;

import beans.api.json.Credenciales;
import beans.api.json.RespuestaUES;
import beans.datatypes.DataDescDescripcion;
import integraciones.couriers.correoUY.ArrayRSPCorreo;
import integraciones.couriers.correoUY.RSPCorreoUY;
import integraciones.couriers.districad.DISTRIaltaEnvio;
import logica.LogicaAPI;
import logica.Util;

public class Call_WS_UES {

	
	public Call_WS_UES (){}
	
	
	public DataDescDescripcion generarEtiqueta(String Cliente, String Tocken, String TipoServicio, String Destinatario, String Calle, String Nro, String NroDeApto, String BarrioLocalidad, String Departamento, 
			String TelefonoContacto, String EmailRecibe, String Observaciones, String NroPedido, String lat, String lon)
	{
		DataDescDescripcion retorno = new DataDescDescripcion();
		Util u = new Util();
		
		try {
			Cliente = u.validarString(Cliente);
			Tocken = u.validarString(Tocken);
			TipoServicio = u.validarString(TipoServicio);
			Destinatario = u.validarString(Destinatario);
			Calle = u.validarString(Calle);
			Nro = u.validarString(Nro);
			NroDeApto = u.validarString(NroDeApto);
			BarrioLocalidad = u.validarString(BarrioLocalidad);
			Departamento = u.validarString(Departamento);
			TelefonoContacto = u.validarString(TelefonoContacto);
			EmailRecibe = u.validarString(EmailRecibe);
			Observaciones = u.validarString(Observaciones);
			NroPedido = u.validarString(NroPedido);
			lat = u.validarString(lat);
			lon = u.validarString(lon);
			
			String barrio = BarrioLocalidad;
			String dir = "";
			if(Departamento.equals("Montevideo"))
			{
				
				dir = Calle+" "+Nro+", Montevideo";
				dir = dir.replace(" ", "%20");
				
				//Unirest.setTimeouts(0, 0);
				HttpResponse<String> response = null;
				try 
				{
					response = Unirest.get("http://geo.correo.com.uy/serviciosv2/BusquedaDireccion?direccion="+dir+"&localidad=Montevideo")
							  .header("Content-Type", "application/x-www-form-urlencoded")
							  .asString();
					
					
					Gson gson = new Gson();
					String json = gson.toJson(response.getBody());
					json=json.replace("[", "").replace("]", "");
					json = json.substring(1, json.length()-1);
					
					RSPCorreoUY obj= new RSPCorreoUY();
					json = json.replace("\\", "");
					obj = gson.fromJson(json, RSPCorreoUY.class);
					String cpostal = obj.getCodigoPostal();
					
					barrio = LogicaAPI.darBarrio(cpostal);
					
					if(barrio!=null)
					{
						BarrioLocalidad=barrio;
					}
					
				} 
				catch (Exception e) 
				{
					BarrioLocalidad="Otro";
				}
			}
			/*********cambio*********/
			//https://3793234.restlets.api.netsuite.com/app/site/hosting/restlet.nl?script=215&deploy=1
			
			//*********y ahora cambiamos tambien****************/
			//https://meli.ues.com.uy:9443/ues_commerce/create_guia
			
			OAuthConfig authConfig = new OAuthConfig( "596b98ba5db79c0f326426bd305fc9dd41d34706e2d2668f6b43f747ca8c7fa7", "cf5ae186c52b2773dea88693fa6b5dcb96c5ffef2233ae7213eed03a80056985");
			OAuth1AccessToken token = new OAuth1AccessToken("2bfeb5d3218bc8a42744107dd7526624e7bc0e975cb1057bea0aa6ebeaff5811", "f9085e9cd2ce7f16faed1bad95916be6f561ebc2aa3a33279a451a7967c3e18d");
		    OAuth10aService auth10aServiceImpl = new OAuth10aService(new NetSuiteApi(), authConfig);
		    
		    //OAuthRequest request = new OAuthRequest(Verb.POST, "https://3793234.restlets.api.netsuite.com/app/site/hosting/restlet.nl?script=215&deploy=1");
		    OAuthRequest request = new OAuthRequest(Verb.POST, "https://meli.ues.com.uy:9443/ues_commerce/create_guia");
		    request.setRealm("3793234");
		    auth10aServiceImpl.signRequest(token, request);
		    String authorization = request.getHeaders().get("Authorization");
		    if(NroDeApto.length()>9)
		    {
		    	NroDeApto = NroDeApto.substring(0, 9).replace("\"", "");;
		    }
		    
		    if(Nro.length()>9)
		    {
		    	Nro = Nro.substring(0, 9);
		    	
		    	
		    }
		    Nro = Nro.replace("\"", "").replace("'", "");
		    try
		    {
		    	SSLContext sslcontext = SSLContexts.custom()
	                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
	                    .build();

				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);

			
			HttpResponse<String> response = null;
			try 
			{
				/*response = Unirest.get("http://geo.correo.com.uy/serviciosv2/BusquedaDireccion?direccion="+dir+"&localidad=Montevideo")
						  .header("Content-Type", "application/x-www-form-urlencoded")
						  .asString();*/

				
				RequestConfig config = RequestConfig.custom()
						  .setConnectTimeout(30000)
						  .setConnectionRequestTimeout(30000)
						  .setSocketTimeout(30000).build();
				
				CloseableHttpClient httpclient = HttpClients.custom()
				                                     .setSSLSocketFactory(sslsf)
				                                     .setDefaultRequestConfig(config)
				                                     .build();
				
				
				Unirest.setHttpClient(httpclient);
				
		    	
				response = Unirest.post("https://meli.ues.com.uy:9443/ues_commerce/create_guia")
				.header("Authorization", authorization)
				.header("User-Agent", "Mozilla/5.0")
				.header("Content-Type", "application/json")
				
				.body("{\n    \"Cliente\": \""+Cliente+"\",\n    \"Tocken\": \""+Tocken+"\",\n    \"CentroCliente\": \"F\",\n    \"NroPedido\": \""+NroPedido+"\",\n    \"TP\": \"\",\n   "
						+ " \"Entrega\": \"\",\n    \"Transporte\": \"\",\n    \"FechaCrea\": \"\",\n    \"PExp\": \"\",\n    \"TipoMaterial\": \"\",\n    "
						+ "\"TipoServicio\": \""+TipoServicio+"\",\n    \"PickUp\": \"\",\n    \"Cbtos\": \"\",\n    \"CantTotal\": \"\",\n    \"PesoTotal\": \"0\",\n    \"UMP\": \"\",\n  "
								+ "  \"ValorMonetario\": \"\",\n    \"FecEntrega\": \"\",\n    \"VentanaHoraria\": \"\",\n    \"Destinatario\": \""+Destinatario+"\",\n    \"Calle\": \""+Calle+"\",\n  "
										+ "  \"Nro\": \""+Nro+"\",\n    \"BIS\": \"\",\n    \"NroDeApto\": \""+NroDeApto+"\",\n    \"BarrioLocalidad\": \""+BarrioLocalidad+"\",\n    \"Departamento\": \""+Departamento+"\",\n  "
												+ "  \"Localidad\": \"\",\n    \"CodPo\": \"\",\n    \"Latitud\": \""+lat+"\",\n    \"Longitud\": \""+lon+"\",\n    \"Soc\": \"\",\n   "
												+ " \"Observaciones\": \""+NroPedido+"\",\n    \"TelefonoContacto\": \""+TelefonoContacto+"\",\n    \"EmailRecibe\": \""+EmailRecibe+"\",\n    \"MontoCobrar\": \"\",\n    \"Comentarios\": \""+Observaciones+"\",\n    \"Cantidad\": \"\",\n    \"UM\": \"\",\n    \"guia\": \"json\"\n}")
				
				
				.asString();
				
				System.out.println("********************");
				System.out.println(authorization);
				System.out.println("********************");
				System.out.println("{\n    \"Cliente\": \""+Cliente+"\",\n    \"Tocken\": \""+Tocken+"\",\n    \"CentroCliente\": \"F\",\n    \"NroPedido\": \""+NroPedido+"\",\n    \"TP\": \"\",\n   "
						+ " \"Entrega\": \"\",\n    \"Transporte\": \"\",\n    \"FechaCrea\": \"\",\n    \"PExp\": \"\",\n    \"TipoMaterial\": \"\",\n    "
						+ "\"TipoServicio\": \""+TipoServicio+"\",\n    \"PickUp\": \"\",\n    \"Cbtos\": \"\",\n    \"CantTotal\": \"\",\n    \"PesoTotal\": \"0\",\n    \"UMP\": \"\",\n  "
								+ "  \"ValorMonetario\": \"\",\n    \"FecEntrega\": \"\",\n    \"VentanaHoraria\": \"\",\n    \"Destinatario\": \""+Destinatario+"\",\n    \"Calle\": \""+Calle+"\",\n  "
										+ "  \"Nro\": \""+Nro+"\",\n    \"BIS\": \"\",\n    \"NroDeApto\": \""+NroDeApto+"\",\n    \"BarrioLocalidad\": \""+BarrioLocalidad+"\",\n    \"Departamento\": \""+Departamento+"\",\n  "
												+ "  \"Localidad\": \"\",\n    \"CodPo\": \"\",\n    \"Latitud\": \""+lat+"\",\n    \"Longitud\": \""+lon+"\",\n    \"Soc\": \"\",\n   "
												+ " \"Observaciones\": \""+NroPedido+"\",\n    \"TelefonoContacto\": \""+TelefonoContacto+"\",\n    \"EmailRecibe\": \""+EmailRecibe+"\",\n    \"MontoCobrar\": \"\",\n    \"Comentarios\": \""+Observaciones+"\",\n    \"Cantidad\": \"\",\n    \"UM\": \"\",\n    \"guia\": \"json\"\n}");
				
				System.out.println("**--**");
				System.out.println(response.getBody());
				
				
				
				Gson gson = new Gson();
				String json = gson.toJson(response.getBody());
				
				
				
				if(json.contains("No se encontr? el barrio") || json.contains("SSS_INVALID_SRCH_OPERATOR") || json.contains("No se encontro el barrio"))
				{
					System.out.println("**--**");
					System.out.println("FALLO - "+json);
					
					String barrioOriginal = BarrioLocalidad;
					BarrioLocalidad = "Otro";
					 
					authConfig = new OAuthConfig( "596b98ba5db79c0f326426bd305fc9dd41d34706e2d2668f6b43f747ca8c7fa7", "cf5ae186c52b2773dea88693fa6b5dcb96c5ffef2233ae7213eed03a80056985");
					token = new OAuth1AccessToken("2bfeb5d3218bc8a42744107dd7526624e7bc0e975cb1057bea0aa6ebeaff5811", "f9085e9cd2ce7f16faed1bad95916be6f561ebc2aa3a33279a451a7967c3e18d");
				    auth10aServiceImpl = new OAuth10aService(new NetSuiteApi(), authConfig);
				    
				    request = new OAuthRequest(Verb.POST, "https://meli.ues.com.uy:9443/ues_commerce/create_guia");
				    request.setRealm("3793234");
				    auth10aServiceImpl.signRequest(token, request);
				    authorization = request.getHeaders().get("Authorization");
				    if(NroDeApto.length()>9)
				    {
				    	NroDeApto = NroDeApto.substring(0, 9);
				    }
				    
				    try
				    {
				    				    	
						Unirest.setHttpClient(httpclient);			    	
				    	
						response = Unirest.post("https://meli.ues.com.uy:9443/ues_commerce/create_guia")
						.header("Authorization", authorization)
						.header("User-Agent", "Mozilla/5.0")
						.header("Content-Type", "application/json")
						
						.body("{\n    \"Cliente\": \""+Cliente+"\",\n    \"Tocken\": \""+Tocken+"\",\n    \"CentroCliente\": \"F\",\n    \"NroPedido\": \""+NroPedido+"\",\n    \"TP\": \"\",\n   "
								+ " \"Entrega\": \"\",\n    \"Transporte\": \"\",\n    \"FechaCrea\": \"\",\n    \"PExp\": \"\",\n    \"TipoMaterial\": \"\",\n    "
								+ "\"TipoServicio\": \""+TipoServicio+"\",\n    \"PickUp\": \"\",\n    \"Cbtos\": \"\",\n    \"CantTotal\": \"\",\n    \"PesoTotal\": \"0\",\n    \"UMP\": \"\",\n  "
										+ "  \"ValorMonetario\": \"\",\n    \"FecEntrega\": \"\",\n    \"VentanaHoraria\": \"\",\n    \"Destinatario\": \""+Destinatario+"\",\n    \"Calle\": \""+Calle+"\",\n  "
												+ "  \"Nro\": \""+Nro+"\",\n    \"BIS\": \"\",\n    \"NroDeApto\": \""+NroDeApto+"\",\n    \"BarrioLocalidad\": \""+BarrioLocalidad+"\",\n    \"Departamento\": \""+Departamento+"\",\n  "
														+ "  \"Localidad\": \"\",\n    \"CodPo\": \"\",\n    \"Latitud\": \""+lat+"\",\n    \"Longitud\": \""+lon+"\",\n    \"Soc\": \"\",\n   "
														+ " \"Observaciones\": \""+NroPedido+"\",\n    \"TelefonoContacto\": \""+TelefonoContacto+"\",\n    \"EmailRecibe\": \""+EmailRecibe+"\",\n    \"MontoCobrar\": \"\",\n    \"Comentarios\": \"Localidad/barrio "+barrioOriginal+"\",\n    \"Cantidad\": \"\",\n    \"UM\": \"\",\n    \"guia\": \"json\"\n}")
						.asString();
						
						System.out.println("**--**");
						System.out.println("segundo getBody");
						System.out.println(response.getBody());
					
					
						gson = new Gson();
						json = gson.toJson(response.getBody());
						
						
						RespuestaUES obj= new RespuestaUES();
						
						obj = gson.fromJson(response.getBody(), RespuestaUES.class);
						
						System.out.println("**--**");
						System.out.println("track - "+obj.getGuiaId());
						System.out.println("etiqueta - "+obj.getEtiquetaURL());
						retorno.setId(obj.getGuiaId());
						retorno.setDescripcion(obj.getEtiquetaURL());
				    }
				    catch (Exception e) 
				    {
				    	System.out.println("**--**");
						System.out.println("exepcion UES");
						e.printStackTrace();
					}
				}
				else
				{
					RespuestaUES obj= new RespuestaUES();
					obj = gson.fromJson(response.getBody(), RespuestaUES.class);
					
					System.out.println("**--**");
					System.out.println("track - "+obj.getGuiaId());
					System.out.println("etiqueta - "+obj.getEtiquetaURL());
					
					retorno.setId(obj.getGuiaId());
					retorno.setDescripcion(obj.getEtiquetaURL());
				}
				
		    }
		    catch (Exception e) 
		    {
		    	System.out.println("**--**");
				System.out.println("exepcion UES");
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("**--**");
			System.out.println("exepcion UES general");
			e.printStackTrace();
		}
		
			
	}
		catch (Exception e) {
			System.out.println("**--**");
			System.out.println("exepcion UES general");
			e.printStackTrace();
		}
		return retorno;
	}
	public trackingUES getTracking(String json) {
		eventosTrackingUES eventos;
		trackingUES track = new trackingUES();
		Gson gson = new Gson();
		
		HttpResponse<String> response = null;
		try {
			
			/*String json = "{"+
						  " 	\"Cliente\":\""+c.getUser()+"\","+
						  "		\"Tocken\":\""+c.getPass()+"\","+
						  "		\"guia\": \""+c.getTracking()+"\""+
						  "}";*/
			SSLContext sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
			
			RequestConfig config = RequestConfig.custom()
					  .setConnectTimeout(1000)
					  .setConnectionRequestTimeout(1000)
					  .setSocketTimeout(1000).build();
			
			CloseableHttpClient httpclient = HttpClients.custom()
			                                     .setSSLSocketFactory(sslsf)
			                                     .setDefaultRequestConfig(config)
			                                     .build();
			
			
			Unirest.setHttpClient(httpclient);
			
	 
	    	
			//response = Unirest.post("https://3793234.extforms.netsuite.com/app/site/hosting/scriptlet.nl?script=490&deploy=3&compid=3793234&h=bef2fa96a42a586782b7")
			response = Unirest.post("https://meli.ues.com.uy:9443/ues_commerce/get_eventos_tracking")
			
			.header("User-Agent", "Mozilla/5.0")
			.header("Content-Type", "application/json")
			
			.body(json)
			
			
			.asString();
			
			System.out.println(response.getBody());
			
			String salida = gson.toJson(response.getBody());
			salida = salida.replace("\\", "");
			salida = salida.replaceFirst("\"", "");
			salida = salida.substring(0,salida.length()-1);
			
			salida ="{\"trackings\":"+salida+"}";
	    
			
			//String salida = callWSPOST_ParamJSON("https://3793234.extforms.netsuite.com/app/site/hosting/scriptlet.nl?script=490&deploy=3&compid=3793234&h=bef2fa96a42a586782b7",json);
			eventos = gson.fromJson(salida, eventosTrackingUES.class);
			track = eventos.getTrackings().get(0);
			
			
		} catch (Exception e) {
			String salida = gson.toJson(response.getBody());			
			salida ="{\"trackings\":"+salida+"}";
		}
		
		return track;
	}

	
	public static void main(String[] args) 
	{
		try
		{
			
			
			OAuthConfig authConfig = new OAuthConfig( "596b98ba5db79c0f326426bd305fc9dd41d34706e2d2668f6b43f747ca8c7fa7", "cf5ae186c52b2773dea88693fa6b5dcb96c5ffef2233ae7213eed03a80056985");
			OAuth1AccessToken token = new OAuth1AccessToken("2bfeb5d3218bc8a42744107dd7526624e7bc0e975cb1057bea0aa6ebeaff5811", "f9085e9cd2ce7f16faed1bad95916be6f561ebc2aa3a33279a451a7967c3e18d");
		    OAuth10aService auth10aServiceImpl = new OAuth10aService(new NetSuiteApi(), authConfig);
		    
		    OAuthRequest request = new OAuthRequest(Verb.POST, "https://3793234.restlets.api.netsuite.com/app/site/hosting/restlet.nl?script=215&deploy=1");
		    request.setRealm("3793234");
		    auth10aServiceImpl.signRequest(token, request);
		    String authorization = request.getHeaders().get("Authorization");
		            
		    
		           

		
				
			
			
			
			System.out.println(authorization);
			
			
			HttpResponse<String> response = Unirest.post("https://3793234.restlets.api.netsuite.com/app/site/hosting/restlet.nl?script=215&deploy=1")
			.header("Authorization", authorization)
			.header("User-Agent", "Mozilla/5.0")
			.header("Content-Type", "application/json")
			
			.body("{\n    \"Cliente\": \"11088\",\n    \"Tocken\": \"-/*P4squ4lini*/-\",\n    \"CentroCliente\": \"F\",\n    \"NroPedido\": \"\",\n    \"TP\": \"\",\n    \"Entrega\": \"\",\n    \"Transporte\": \"\",\n    \"FechaCrea\": \"\",\n    \"PExp\": \"\",\n    \"TipoMaterial\": \"\",\n    \"TipoServicio\": \"2111\",\n    \"PickUp\": \"\",\n    \"Cbtos\": \"\",\n    \"CantTotal\": \"\",\n    \"PesoTotal\": \"0\",\n    \"UMP\": \"\",\n    \"ValorMonetario\": \"\",\n    \"FecEntrega\": \"\",\n    \"VentanaHoraria\": \"\",\n    \"Destinatario\": \"Oscar Lopez\",\n    \"Calle\": \"Alarcon\",\n    \"Nro\": \"1457\",\n    \"BIS\": \"Bis\",\n    \"NroDeApto\": \"602\",\n    \"BarrioLocalidad\": \"Baltasar Brum\",\n    \"Departamento\": \"Artigas\",\n    \"Localidad\": \"\",\n    \"CodPo\": \"11600\",\n    \"Latitud\": \"\",\n    \"Longitud\": \"\",\n    \"Soc\": \"\",\n    \"Observaciones\": \"o1\",\n    \"TelefonoContacto\": \"1234567\",\n    \"EmailRecibe\": \"destinatario@test.com\",\n    \"MontoCobrar\": \"\",\n    \"Comentarios\": \"c1\",\n    \"Cantidad\": \"\",\n    \"UM\": \"\",\n    \"guia\": \"json\"\n}")
			.asString();
			
			System.out.println(response.getBody());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		

	}

}
