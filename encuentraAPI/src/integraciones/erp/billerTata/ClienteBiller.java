package integraciones.erp.billerTata;

import java.io.BufferedInputStream;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ClienteBiller
{
	final String hostBiller = "https://biller.tata.com.uy/Biller/saveOrder";
	final String headerI = "encuentra_app";
	final String headerP = "rUkbqrwGNBYE=*zaCF8A4=MC=C@8vmNnn#rM6uVmHCcC^NT7Q_Kx_aN2Gn8=5A@C";
	
	
	
	private OkHttpClient client;
	private Gson gson;
	
	public ClienteBiller ()
	{
		this.client = new OkHttpClient().newBuilder()
				  .build();
		this.gson  = new Gson();
				
	}
	
	public String grabarFactura (VentaBiller venta)
	{
		try
		{
			
			
			MediaType mediaType = MediaType.parse("application/json");
			String jotason = this.gson.toJson(venta);
			RequestBody body = RequestBody.create(mediaType, jotason);
			Request request = new Request.Builder()
			  .url(hostBiller)
			  .method("POST", body)
			  .addHeader("identify", headerI)
			  .addHeader("password", headerP)
			  .addHeader("Content-Type", "application/json")
			  .build();
			
					Response response = this.client.newCall(request).execute();
					
					int bytesRead = 0;
			        BufferedInputStream bis = new BufferedInputStream(response.body().byteStream());
			        byte[] contents = new byte[1024];
			        String strFileContents=""; 
			        while((bytesRead = bis.read(contents)) != -1) 
			        { 
			            strFileContents += new String(contents, 0, bytesRead);              
			        }
			        System.out.println(strFileContents);
			        response.close();
			       
			        System.out.println("Facturado");
			        System.out.println(strFileContents);
			        return strFileContents;
			        
		}
		catch (Exception e) 
		{
			System.out.println("Fallo en envio de factura");
			return "ERROR"+e.getMessage();
		}
		
	}
}

