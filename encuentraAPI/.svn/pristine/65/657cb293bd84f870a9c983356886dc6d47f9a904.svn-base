package integraciones.erp.odoo.laIsla;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Collections;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.sun.xml.wss.saml.internal.saml11.jaxb10.X509DataType.X509Certificate;

public class Test 
{
	
	 public static void main(String[] args) throws MalformedURLException, XmlRpcException 
	 {
		 
		 
		 
		 
		 
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
		 
		 
		 
		 
		 
		 
  		String url = "https://laisla.quanam.com"; // work with odoo.com account!!
  		String db = "laisla";
  		String username = "ws_encuentra";
  		String password = "1234";
  		System.out.println("Get database list");
  		System.out.println("Login");
  		System.out.println("--------------");
  		int uid = login(url,db,username,password);
  		if (uid >0) {
      			System.out.println("Login Ok");
      			System.out.println(uid);
  		} else {
       			System.out.println("Login Fail");
  		}
}
	// login 
	static int login(String url, String db, String login, String password) throws XmlRpcException, MalformedURLException {
  		XmlRpcClient client = new XmlRpcClient();
  		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
  		config.setEnabledForExtensions(true);
  		//config.setServerURL(new URL(url+"/xmlrpc/common"));
  		config.setServerURL(new URL(url+"/xmlrpc/2/common"));
  		client.setConfig(config);
  		//Connect
  		//Object[] empty = null; // Ok
  		//Object[] params = new Object[] {db,login,password, empty}; // Ok
  		Object[] params = new Object[] {db,login,password,Collections.emptyList()}; // Ok & simple
  		Object uid = client.execute("authenticate", params);
  		if (uid instanceof Integer)
      			return (int) uid;
  		return -1;
	}

}
