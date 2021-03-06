package integraciones.erp.sapBO.beansSL;



import java.io.BufferedInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.io.IOException;

import javax.net.ssl.TrustManager;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login 
{
	private String usuario,password,compania;

	public Login(String usuario, String password, String compania) {
		this.usuario = usuario;
		this.password = password;
		this.compania = compania;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}
	
	 final TrustManager[] trustAllCerts = new TrustManager[]{
             new X509TrustManager() {

                 @Override
                 public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                                String authType) {
                 }

                 @Override
                 public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                                String authType) {
                 }
                 @Override
                 public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                     return new java.security.cert.X509Certificate[]{};
                 }
             }
     };
	
	
	public String doLogin()
	{
		try
		{
			
	          
	          OkHttpClient client = getUnsafeOkHttpClient();
			
				
					MediaType mediaType = MediaType.parse("application/json");
					RequestBody body = RequestBody.create(mediaType, "{\r\n    \"CompanyDB\": \""+this.compania+"\",\r\n    \"UserName\": \""+this.usuario+"\",\r\n    \"Password\": \""+this.password+"\"\r\n}");
					Request request = new Request.Builder()
					  .url("https://190.64.140.140:50000//b1s/v1/Login")
					  .method("POST", body)
					  .addHeader("Content-Type", "application/json")
					  .build();
					try 
					{
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
				       
				        
				        System.out.println(strFileContents);
				        Gson gsonn = new Gson();
				        
				        DoLoginResponse rsp = gsonn.fromJson(strFileContents, DoLoginResponse.class);
				        
				        return rsp.getSessionId();
				        
						
					}
					catch (IOException e) 
					{
						e.printStackTrace();
						return "";
					}
		}
		catch (Exception eee) 
		{
			return "";
		}
		  
				
		
	}
	private OkHttpClient getUnsafeOkHttpClient() {
		   try {
			   final TrustManager[] trustAllCerts = new TrustManager[]{
			             new X509TrustManager() {

			                 @Override
			                 public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
			                                                String authType) {
			                 }

			                 @Override
			                 public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
			                                                String authType) {
			                 }
			                 @Override
			                 public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			                     return new java.security.cert.X509Certificate[]{};
			                 }
			             }
			     };

		       final SSLContext sslContext = SSLContext.getInstance("SSL");
		       sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

		       final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

		       OkHttpClient.Builder builder = new OkHttpClient.Builder();
		       builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);

		       builder.hostnameVerifier(new HostnameVerifier() {
		           @Override
		           public boolean verify(String hostname, SSLSession session) {
		               return true;
		           }
		       });

		       return builder.build();
		   } catch (Exception e) {
		       throw new RuntimeException(e);
		   }
		}
	

}
