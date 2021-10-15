package logica;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.cert.CertificateException;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.sun.xml.wss.saml.internal.saml11.jaxb10.X509DataType.X509Certificate;

import beans.datatypes.DataDescDescripcion;
import beans.helper.PropertiesHelperAPI;

public class Util 
{
	public Util(){}
	

	public String removerTildes(String cadena) {
	    return cadena.replace("Á", "A")
	            .replace("É", "E")
	            .replace("Í", "I")
	            .replace("Ó", "O") 
	            .replace("Ú", "U")
	            .replace("á", "a")
	            .replace("é", "e")
	            .replace("í", "i")
	            .replace("ó", "o")
	            .replace("ú", "u");
	}
	
	public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
	
	public static String base64ToPdf(String base64, String name) {
		try
		{
			PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
			pH.loadProperties();
			String path = pH.getValue("pdf");
			String filePath = path+"/"+name+".pdf";
			path = pH.getValue("HTTP_pdf");
			String htmlPath = path+"/"+name+".pdf";
			
			 // Encode using basic encoder
	         //String base64encodedString = retorno.getPdfserializado();
	         
	         System.out.println("Base64 encoded string :" + base64);

	         // Decode
	         byte[] base64decodedBytes = Base64.getDecoder().decode(base64);

			 File file = new File(filePath);
	         file.delete();
	         
	         FileOutputStream fos = new FileOutputStream(new File(filePath));
	         
	         String str = base64;
	         
	         InputStream in = new ByteArrayInputStream(base64decodedBytes);
	         
	         int inByte;
	            while ((inByte = in.read()) != -1) {
	                fos.write(inByte);
	            }
	 
	            in.close();            
	         
	         fos.write(str.getBytes());	         
	         
	         fos.close();	         
	         
	         return htmlPath;				
		}
		catch (Exception e) 
		{
		 e.printStackTrace();
		 return "";
		}
	}
	
	public static String bytesToPdf(byte[] base, String name) {
		try
		{
			PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
			pH.loadProperties();
			String path = pH.getValue("pdf");
			String filePath = path+"/"+name+".pdf";
			path = pH.getValue("HTTP_pdf");
			String htmlPath = path+"/"+name+".pdf";
			
			 // Encode using basic encoder
	         //String base64encodedString = retorno.getPdfserializado();
	         
	         System.out.println("bytes[] :" + base);

	         // Decode
	         //byte[] base64decodedBytes = Base64.getDecoder().decode(base);

			 File file = new File(filePath);
	         file.delete();
	         
	         FileOutputStream fos = new FileOutputStream(new File(filePath));
	         
	         //String str = base64;
	         
	         InputStream in = new ByteArrayInputStream(base);
	         
	         int inByte;
	            while ((inByte = in.read()) != -1) {
	                fos.write(inByte);
	            }
	 
	            in.close();            
	         
	         //fos.write(str.getBytes());	         
	         
	         fos.close();	         
	         
	         return htmlPath;				
		}
		catch (Exception e) 
		{
		 e.printStackTrace();
		 return "";
		}
	}
	
	public static String saltoDeLinea(String texto, int tope) {
		String salida = "";
		
		try {
			String[] coleccion = texto.split(" ");
			if(coleccion.length>tope){						
				for(int i = 0; i < coleccion.length; i++){					
					salida += coleccion[i] + " ";
					if( (i+1) % tope == 0) {
						salida += "<br>";
					}
				}
			}
			else {
				salida = texto;
			}
		} catch (Exception e) {
			salida = texto;
		}
		
		return salida;
	}
	
	public String validarString(String texto) {
		try {
			if(texto.contains("\'")) {
				texto = texto.replace('\'',' ');
			}
			if(texto.contains("\"")) {
				texto = texto.replace('\"',' ');
			}
			if(texto.contains("\\")) {
				texto = texto.replace('\\',' ');
			}
		} catch (Exception e) {
			return "";
		}
		
		return texto;
	}
	
	public boolean saveFile(URL url, String file) throws IOException {
		    
			try
			{
				  
				
				if(true)
				{
					try (InputStream in = url.openStream()) {
					   Files.copy(in, Paths.get(file), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
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
							SSLContext sc = SSLContext.getInstance("SSL");
							sc.init(null, trustAllCerts, new java.security.SecureRandom());
							HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

							// 	Create all-trusting host name verifier
							HostnameVerifier allHostsValid = new HostnameVerifier() {
								public boolean verify(String hostname, SSLSession session) {
									return true;
								}
							};

							// Install the all-trusting host verifier
							HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);    
							
							System.out.println("[OK] - open");
						    
						    InputStream in = url.openStream();
						    FileOutputStream fos = new FileOutputStream(new File(file));
						    System.out.println("[OK] - reading file...");
						    int length = -1;
						    byte[] buffer = new byte[1024];

						    while ((length = in.read(buffer)) > -1) {
						        fos.write(buffer, 0, length);
						    }
						    fos.close();
						    in.close();

						    System.out.println("[OK] - downloaded");  			            
			            }
			            catch (Exception ex)
			            {
			                   ex.printStackTrace();
			            }
					}
					
					return true;
				}
				
				
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
		        return false;
		        	
			}
		return false;
			
		  }
	
}
