package integraciones.mains.unilam;


import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Response;

public class keep 
{
	
	public static void main(String[] args) 
	{
	
		try
		{
			OkHttpClient client = new OkHttpClient().newBuilder()
					  .build();
					
					
					Request request = new Request.Builder()
							  .url("http://127.0.0.1/manager/text/reload?path=/unilam")
							  .method("GET", null)
							  .addHeader("Authorization", "Basic cm9vdDpTdGQ=")
							  .build();
					
					Response response = client.newCall(request).execute();
					System.out.println(response.message());
					
					
					
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		
	}

}
