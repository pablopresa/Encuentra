package integraciones.erp.odoo.laIsla;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import beans.helper.PropertiesHelperAPI;
import okhttp3.*;
public class DescargarFactura 
{
	final static String urlTicket = "https://pos.laisla.com.uy/report/pdf/point_of_sale_invoice.report_venta_document/608474";
	public static void main(String[] args) 
	{
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = new okhttp3.MultipartBody.Builder().setType(okhttp3.MultipartBody.FORM)
				  .addFormDataPart("password","1234ws_encuentra.1")
				  .addFormDataPart("login","ws_encuentra")
				  .build();
				Request request = new Request.Builder()
				  .url("https://pos.laisla.com.uy/web/login")
				  .method("POST", body)
				  .addHeader("Cookie", "session_id=4564fd30c8c51b66bdff5088e56268f0d10d296a")
				  .build();
				try {
					Response response = client.newCall(request).execute();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				
				client = new OkHttpClient().newBuilder()
						  .build();
						request = new Request.Builder()
						  .url(urlTicket)
						  .method("GET", null)
						  .addHeader("Cookie", "session_id=4564fd30c8c51b66bdff5088e56268f0d10d296a")
						  .build();
						try 
						{
							Response response = client.newCall(request).execute();
							InputStream in = response.body().byteStream();
							PropertiesHelperAPI pHa= new PropertiesHelperAPI("paths");
							pHa.loadProperties();
							String path = pHa.getValue("pdf");
							String filePath = path+"/facturaLI.pdf";
							
							
									  FileOutputStream fileOutputStream = new FileOutputStream(filePath); 
									    byte dataBuffer[] = new byte[1024];
									    int bytesRead;
									    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) 
									    {
									        fileOutputStream.write(dataBuffer, 0, bytesRead);
									    }
									
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		

	}

}
