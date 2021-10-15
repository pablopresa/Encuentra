package beans.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.sun.xml.wss.saml.internal.saml11.jaxb10.X509DataType.X509Certificate;

import beans.helper.PropertiesHelperAPI;
import logica.Util;

public class downloadImage {
	public static void main(String[] args) {
		
		try {
			
			 /*DefaultHttpClient client = new DefaultHttpClient();
			 HttpGet securedResource = new HttpGet(url);
			 HttpPost authpost = new HttpPost(url);
	           
	            HttpResponse httpResponse = client.execute(securedResource);
	            HttpEntity responseEntity = httpResponse.getEntity();
	            
	            System.out.println("");
	            
	            
	            PropertiesHelperAPI pHa= new PropertiesHelperAPI("paths");
				pHa.loadProperties();
				String path = pHa.getValue("pdf");
				String filePath = path+"/facturaLI.pdf";
				InputStream is = responseEntity.getContent();
				File file = new File(filePath);
	            file.delete();
	            FileOutputStream fos = new FileOutputStream(new File(filePath));
	            int inByte;
	            while ((inByte = is.read()) != -1) 
	            {
	                fos.write(inByte);
	            }
	            is.close();
	            fos.close();*/
			exec();
	            
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void exec() {
		String url ="https://pos.laisla.com.uy/report/pdf/point_of_sale_invoice.report_venta_document/608474";
		try {
			byte[] base = null;
			certs();
			base = recoverImageFromUrl(url);
			Util.bytesToPdf(base , "testLI");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static byte[] recoverImageFromUrl(String urlText) throws Exception {
        URL url = new URL(urlText);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
          
        try (InputStream inputStream = url.openStream()) {
            int n = 0;
            byte [] buffer = new byte[ 1024 ];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }
      
        return output.toByteArray();
    }
	
	public static void certs () {
		TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
				public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
				public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
			}
			};

			// Install the all-trusting trust manager
			SSLContext sc;
			try {
				sc = SSLContext.getInstance("SSL");
			
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			
			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			// 	Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);  
	}
}
