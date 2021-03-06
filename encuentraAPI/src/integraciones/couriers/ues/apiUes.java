package integraciones.couriers.ues;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import beans.api.json.RespuestaUES;
import beans.api.json.Shipping;
import beans.datatypes.DataDescDescripcion;
import integraciones.couriers.correoUY.RSPCorreoUY;
import logica.LogicaAPI;

public class apiUes {

	public static void main(String[] args) {
		
		String data ="{\r\n" + 
				"    \"credenciales\": {\r\n" + 
				"        \"user\": \"11869\",\r\n" + 
				"        \"pass\": \"\\u0026\\u0026C0LUM314\\u0026\\u0026\",\r\n" + 
				"        \"token\": \"\\u0026\\u0026C0LUM314\\u0026\\u0026\",\r\n" + 
				"        \"prestador\": \"FORUS\",\r\n" + 
				"        \"idCliente\": \"11869\",\r\n" + 
				"        \"tipoEnvio\": \"3118\",\r\n" + 
				"        \"pedido\": \"encuentra_1297373\",\r\n" + 
				"        \"fecha\": \"\",\r\n" + 
				"        \"hora\": \"\"\r\n" + 
				"    },\r\n" + 
				"    \"cliente\": {\r\n" + 
				"        \"apellido\": \"Bouissa\",\r\n" + 
				"        \"nombre\": \"Ana Laura\",\r\n" + 
				"        \"localidad\": \"Montevideo\",\r\n" + 
				"        \"email\": \"lalybouissa28@hotmail.com\",\r\n" + 
				"        \"departamento\": \"Montevideo\",\r\n" + 
				"        \"ciudad\": \"Montevideo\",\r\n" + 
				"        \"latitud\": \"-34.8362491\",\r\n" + 
				"        \"telefono\": \"+59897537928\",\r\n" + 
				"        \"longitud\": \"-56.2728322\",\r\n" + 
				"        \"nroPuerta\": \"6605\",\r\n" + 
				"        \"nroApto\": \"Es una farmacia\",\r\n" + 
				"        \"documentoTipo\": \"ci\",\r\n" + 
				"        \"calle\": \"Luis Batlle berres\",\r\n" + 
				"        \"documentoNro\": \"45068160\",\r\n" + 
				"        \"rut\": \"\",\r\n" + 
				"        \"obs\": \"La entrega Es en una farmacia.\"\r\n" + 
				"    },\r\n" + 
				"    \"cantidadPaquetes\": 0,\r\n" + 
				"    \"xGrandes\": 0,\r\n" + 
				"    \"grandes\": 0,\r\n" + 
				"    \"medianos\": 0,\r\n" + 
				"    \"chicos\": 0,\r\n" + 
				"    \"tipoShipping\": 0\r\n" + 
				"}";
		try {
			Gson gson = new Gson();
			Shipping shipp = gson.fromJson(data, Shipping.class);		
			Call_WS_UES call = new Call_WS_UES();
			
			for(int i=1; i<11; i++) {
				DataDescDescripcion despacho = call.generarEtiqueta(shipp.getCredenciales().getIdCliente(), shipp.getCredenciales().getToken(), shipp.getCredenciales().getTipoEnvio() , 
						shipp.getCliente().getNombre() + " "+shipp.getCliente().getApellido(), shipp.getCliente().getCalle() , shipp.getCliente().getNroPuerta() , 
						shipp.getCliente().getNroApto() , shipp.getCliente().getLocalidad() ,shipp.getCliente().getDepartamento() , shipp.getCliente().getTelefono(), shipp.getCliente().getEmail(), shipp.getCliente().getObs() , shipp.getCredenciales().getPedido(), shipp.getCliente().getLatitud(), shipp.getCliente().getLongitud());
				
				
				System.out.println("RESULTADO ITERACION N "+i);
				System.out.println(despacho.getId());
				System.out.println(despacho.getDescripcion());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	public static DataDescDescripcion generarEtiqueta(String Cliente, String Tocken, String TipoServicio, String Destinatario, String Calle, String Nro, String NroDeApto, String BarrioLocalidad, String Departamento, 
			String TelefonoContacto, String EmailRecibe, String Observaciones, String NroPedido, String lat, String lon)
	{
		DataDescDescripcion retorno = new DataDescDescripcion();
		
		try {
			String barrio = BarrioLocalidad;
			if(Departamento.equals("Montevideo"))
			{
				
				String dir = Calle+" "+Nro+", Montevideo";
				dir = dir.replace(" ", "%20");
				
				Unirest.setTimeouts(0, 0);
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
				CloseableHttpClient httpclient = HttpClients.custom()
				                                     .setSSLSocketFactory(sslsf)
				                                     .build();
				
				
				Unirest.setHttpClient(httpclient);
				
		    	
				HttpResponse<String> response = Unirest.post("https://meli.ues.com.uy:9443/ues_commerce/create_guia")
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
				
				
				
				if(json.contains("No se encontr? el barrio") || json.contains("SSS_INVALID_SRCH_OPERATOR"))
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
				    	//
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
		
		
		return retorno;
	}
}
