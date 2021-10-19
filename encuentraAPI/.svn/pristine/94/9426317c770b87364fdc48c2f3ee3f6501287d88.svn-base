package integraciones.erp.ws_Tata;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ws_tata
{
	final String hostBiller = "http://biller.tata.com.uy/Biller/saveOrder";
	private OkHttpClient client;
	private Gson gson;
	
	public ws_tata ()
	{
		this.client = new OkHttpClient().newBuilder()
				  .build();
		this.gson  = new Gson();
				
	}
	
	public List<Articulo> precioArticulos ()
	{
		ArrayArticulo articulos = null;
		try
		{
			Request request = new Request.Builder()
			  .url("https://ws.tata.com.uy/WSECommerce/articulos/579")
			  .method("GET", null)
			  .build();
			Response response = client.newCall(request).execute();

			
			int bytesRead = 0;
	        BufferedInputStream bis = new BufferedInputStream(response.body().byteStream());
	        byte[] contents = new byte[1024];
	        String strFileContents=""; 
	        while((bytesRead = bis.read(contents)) != -1) 
	        { 
	            strFileContents += new String(contents, 0, bytesRead);              
	        }
	        response.close();
			articulos = this.gson.fromJson(strFileContents, ArrayArticulo.class);       
			return articulos.getArticulos();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return new ArrayList<>();
		}
		
		
	}
}

