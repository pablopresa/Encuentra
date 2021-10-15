package integraciones.erp.taface.clienteWSTAFACE;

import java.io.ByteArrayInputStream;
import java.io.File;

import java.io.FileOutputStream;

import java.io.InputStream;

import java.security.cert.X509Certificate;

import java.util.Base64;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import beans.helper.PropertiesHelper;


public class ClienteWS {

	
	
	
	
	public ClienteWS() {
	}
	public static void main(String[] args) 
	{
		try
		{
		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		WSIntegracionFullDescargarPDF0300 servicio = new WSIntegracionFullDescargarPDF0300();
		
		WSIntegracionFullDescargarPDF0300SoapPort cliente = servicio.getWSIntegracionFullDescargarPDF0300SoapPort();
		
		WSIntegracionFullDescargarPDF0300Execute parameters = new WSIntegracionFullDescargarPDF0300Execute();
		//parameters.setPctehash("frjq8P");
		parameters.setPempruc("210703920014");
		parameters.setPlicencia("WebvB/armzyj8akd5UKYhDhgiwF1zvuomtXTSgQYIAKOish2GYOjq9o74Gqci8XbKE/XVaWChwymrYeH763gpw==");
		parameters.setPiddocnro(5905942);
		parameters.setPiddocserie("A");
		parameters.setPiddoctipocfe((short) 101);
		//parameters.setPtotmnttotal(1425.00);
		
		
		WSIntegracionFullDescargarPDF0300ExecuteResponse rsp = cliente.execute(parameters);
		
		try
		{
			PropertiesHelper pH=new PropertiesHelper("paths");
			pH.loadProperties();
			String path = pH.getValue("pdf");
			String filePath = path+1212+".pdf";
			
			
			
			 // Encode using basic encoder
	         String base64encodedString = rsp.getPdfserializado();
	         
	         System.out.println("Base64 encoded string :" + base64encodedString);

	         // Decode
	         byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);

	         
	         
	         
			
			 File file = new File(filePath);
	         file.delete();
	         
	         FileOutputStream fos = new FileOutputStream(new File(filePath));
	         
	         
	         	         
	         
	         String str = rsp.getPdfserializado();
	         
	         InputStream in = new ByteArrayInputStream(base64decodedBytes);
	         
	         int inByte;
	            while ((inByte = in.read()) != -1) {
	                fos.write(inByte);
	            }
	 
	            in.close();
	           
	         
	         fos.write(str.getBytes());
	         
	         
	         fos.close();
			
		}
		catch (Exception e) 
		{
		 e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
	public String darPDFFactura(int iddFactura)
	{
		
		try
		{
			System.out.println("BAJANDO factura");
		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("servicio");
		WSIntegracionFullDescargarPDF0300 servicio = new WSIntegracionFullDescargarPDF0300();
		System.out.println(" paso servicio");
		
		WSIntegracionFullDescargarPDF0300SoapPort cliente = servicio.getWSIntegracionFullDescargarPDF0300SoapPort();
		
		WSIntegracionFullDescargarPDF0300Execute parameters = new WSIntegracionFullDescargarPDF0300Execute();
		//parameters.setPctehash("frjq8P");
		parameters.setPempruc("210703920014");
		parameters.setPlicencia("WebvB/armzyj8akd5UKYhDhgiwF1zvuomtXTSgQYIAKOish2GYOjq9o74Gqci8XbKE/XVaWChwymrYeH763gpw==");
		parameters.setPiddocnro(iddFactura);
		parameters.setPiddocserie("A");
		parameters.setPiddoctipocfe((short) 101);
		//parameters.setPtotmnttotal(1425.00);
		
		System.out.println("parametros");
		
		WSIntegracionFullDescargarPDF0300ExecuteResponse rsp = cliente.execute(parameters);
		System.out.println("exec");
		try
		{
			PropertiesHelper pH=new PropertiesHelper("paths");
			pH.loadProperties();
			String path = pH.getValue("pdf");
			String filePath = path+iddFactura+".pdf";
			
			
			
			 // Encode using basic encoder
	         String base64encodedString = rsp.getPdfserializado();
	         
	         System.out.println("Base64 encoded string :" + base64encodedString);

	         // Decode
	         byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);

	         
	         
	         
			
			 File file = new File(filePath);
	         file.delete();
	         
	         FileOutputStream fos = new FileOutputStream(new File(filePath));
	         
	         
	         	         
	         
	         String str = rsp.getPdfserializado();
	         
	         InputStream in = new ByteArrayInputStream(base64decodedBytes);
	         
	         int inByte;
	            while ((inByte = in.read()) != -1) {
	                fos.write(inByte);
	            }
	 
	            in.close();
	           
	         
	         fos.write(str.getBytes());
	         
	         
	         fos.close();
	         
	         
	         File f = new File(filePath);
		     if(f.exists())
		     {
		    	 path = pH.getValue("HTTP_pdf");
		        return path+"/"+iddFactura+".pdf";
		     }
		     else
		     {
		    	 return "";
		     }
	         
			
		}
		catch (Exception e) 
		{
		 e.printStackTrace();
		}
		
		
		
		return "";
	}
	

}
