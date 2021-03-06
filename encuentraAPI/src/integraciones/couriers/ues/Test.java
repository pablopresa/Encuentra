package integraciones.couriers.ues;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class Test {

	public static void main(String[] args) 
	{
		try
		{
			
			
			OAuthConfig authConfig = new OAuthConfig( "596b98ba5db79c0f326426bd305fc9dd41d34706e2d2668f6b43f747ca8c7fa7", "cf5ae186c52b2773dea88693fa6b5dcb96c5ffef2233ae7213eed03a80056985");
			OAuth1AccessToken token = new OAuth1AccessToken("2bfeb5d3218bc8a42744107dd7526624e7bc0e975cb1057bea0aa6ebeaff5811", "f9085e9cd2ce7f16faed1bad95916be6f561ebc2aa3a33279a451a7967c3e18d");
		    OAuth10aService auth10aServiceImpl = new OAuth10aService(new NetSuiteApi(), authConfig);
		    
		    OAuthRequest request = new OAuthRequest(Verb.POST, "https://rest.na1.netsuite.com/app/site/hosting/restlet.nl?script=215&deploy=1");
		    request.setRealm("3793234");
		    
		    auth10aServiceImpl.signRequest(token, request);
		    
		    
		    //System.out.println(request.getHeaders().get("Authorization"));
			
			
			
			
			String authorization = request.getHeaders().get("Authorization");
		            
		            
		           

		
				
			
			
			
			System.out.println(authorization);
			
			Unirest.setTimeouts(0, 0);
			HttpResponse<String> response = Unirest.post("https://rest.na1.netsuite.com/app/site/hosting/restlet.nl?script=215&deploy=1")
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
