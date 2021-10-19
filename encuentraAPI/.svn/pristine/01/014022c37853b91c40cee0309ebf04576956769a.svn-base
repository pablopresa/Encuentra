package integraciones.couriers.ues;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Token;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

public class AppII 
{
	
	public static void main(String[] args) 
	{
		OAuthConfig authConfig = new OAuthConfig( "596b98ba5db79c0f326426bd305fc9dd41d34706e2d2668f6b43f747ca8c7fa7", "cf5ae186c52b2773dea88693fa6b5dcb96c5ffef2233ae7213eed03a80056985");
		OAuth1AccessToken token = new OAuth1AccessToken("2bfeb5d3218bc8a42744107dd7526624e7bc0e975cb1057bea0aa6ebeaff5811", "f9085e9cd2ce7f16faed1bad95916be6f561ebc2aa3a33279a451a7967c3e18d");
	    OAuth10aService auth10aServiceImpl = new OAuth10aService(new NetSuiteApi(), authConfig);
	    
	    OAuthRequest request = new OAuthRequest(Verb.POST, "https://rest.na1.netsuite.com/app/site/hosting/restlet.nl?script=215&deploy=1");
	    request.setRealm("3793234");
	    
	    auth10aServiceImpl.signRequest(token, request);
	    
	    
	    System.out.println(request.getHeaders().get("Authorization"));
	    
	    //Response response = request.set
	}
	
	
	
	

}
