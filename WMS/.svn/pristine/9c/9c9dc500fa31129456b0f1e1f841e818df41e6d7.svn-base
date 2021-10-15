package cliente_rest_Invoke;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import com.google.gson.Gson;

import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import jsonObjects.DISTRIaltaEnvio;
import jsonObjects.DISTRIenvio;
import jsonObjects.DISTRIetiqueta;
import jsonObjects.DISTRIlogin;
import jsonObjects.DISTRIservicio;

public class Call_WS_DISTRICAD {

	String user = "INTERFASEFORUS";
	String pass = "pr56!!mntv345";
	String token = "PbkF0XZX2UeBHw7agWvuow==";
	String prestador = "FORUS"; //??
	String idCliente = "2576"; // Nro de Cliente 2576 (Producción), En ambiente de Testng Nro de Cuenta  2566
	String tEnvio = "EXPRESS";
	

	public DataDescDescripcion setEnvio(String fecha, String hora, int bultos, String contacto,String mail,String cel,String dpto,String dir,String loc,String obs){
		try {
			DISTRIenvio envio = new DISTRIenvio();
			DISTRIlogin login = new DISTRIlogin();
			DISTRIservicio servicio = new DISTRIservicio();
			
			login.setUsuario(user);
			login.setPassword(pass);
			login.setGuid(token);
			
			//servicio.setSrvTrkNbr(""); numerador interno , se genera automáticamente.
			servicio.setPrestCod(prestador);//??
			servicio.setCueId(idCliente);// Nro de Cliente (Producción), En ambiente de Testng Nro de Cuenta  2564
			servicio.setTSrvDsc(tEnvio); // CADETERIA/EXPRESS
			servicio.setSrvFchEnt(fecha); //Fecha de Entrega
			servicio.setSrvHorario(hora); // AM/PM
			servicio.setSrvCnt(bultos+""); //Cantidad Bultos
			servicio.setSrvCon(contacto); //Contacto
			servicio.setSrvMail(mail); //Email
			servicio.setSrvCel(cel); //Celular
			servicio.setPaiCod("UY"); //Codigo de pais
			servicio.setDeptoCod(dpto); //Departamento
			servicio.setLocCod(loc); //Localidad
			servicio.setSrvDstCalle(dir); //Dirección
			servicio.setSrvNotas(obs);
			servicio.setSrvReqDoc("N"); //Si requiere mostrar documento al recibir
			
			envio.setServicio(servicio);
			envio.setWSAutorizacion(login);
			
			Gson gson = new Gson();
			String json = gson.toJson(envio);
			
			String salida = callWSPOST_ParamJSON("http://test.districad.com.uy/rest/wsaltaservicioetiqueta",json);
			
			DISTRIaltaEnvio obj= new DISTRIaltaEnvio();
			
			obj = gson.fromJson(salida, DISTRIaltaEnvio.class);
			
			//PASO A PDF
			String eti = obj.getEtiqueta();
			String trkNmb = obj.getSrvTrkNbr();
			String path ="";
			String barra = "";
			try
			{
				PropertiesHelper pH=new PropertiesHelper("paths");
				pH.loadProperties();
				path = pH.getValue("pdf");
				String filePath = path+"/"+trkNmb+".pdf";
				
				 // Encode using basic encoder
		         //String base64encodedString = retorno.getPdfserializado();
		         
		         System.out.println("Base64 encoded string :" + eti);

		         // Decode
		         byte[] base64decodedBytes = Base64.getDecoder().decode(eti);

				 File file = new File(filePath);
		         file.delete();
		         
		         FileOutputStream fos = new FileOutputStream(new File(filePath));
		         
		         String str = eti;
		         
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
			    	 
			    	 
			    	 
			    	
			    	 //es lo ultimo
			    	 try (PDDocument document = PDDocument.load(new File(filePath))) 
			    	 {

			             document.getClass();

			             if (!document.isEncrypted()) {
			 			
			                 PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			                 stripper.setSortByPosition(true);

			                 PDFTextStripper tStripper = new PDFTextStripper();

			                 String pdfFileInText = tStripper.getText(document);
			                
			                 String lines[] = pdfFileInText.split("\\r?\\n");
			                 for (String line : lines) 
			                 {
			                     barra = line;
			                     break;
			                 }
			                 barra = barra.replace("*", "");
			                 System.out.println(barra);

			             }

			         }
			    	 catch (Exception e) 
			    	 {
			    		 e.printStackTrace();
					}
			    	 
			    	 
			    	 
			    	 
			        return  new DataDescDescripcion(barra, path+"/"+trkNmb+".pdf");
			     }
			     else
			     {
			    	 return new DataDescDescripcion("","");
			     }
		         				
			}
			catch (Exception e) 
			{
			 e.printStackTrace();
			}
			
			return  new DataDescDescripcion(barra, path+"/"+trkNmb+".pdf");
				
			
			
		} catch (Exception e) {
			return  new DataDescDescripcion("", "");
		}
		
	}
	
	public DataDescDescripcion getEtiqueta(String SrvTrkNbr){
		try {
			DISTRIetiqueta etiq = new DISTRIetiqueta();
			DISTRIlogin login = new DISTRIlogin();
			
			login.setUsuario(user);
			login.setPassword(pass);
			login.setGuid(token);			
			
			etiq.setWSAutorizacion(login);
			etiq.setSrvTrkNbr(SrvTrkNbr);
			
			Gson gson = new Gson();
			String json = gson.toJson(etiq);
			
			String salida = callWSPOST_ParamJSON("http://tracking.districad.com.uy/rest/wsgetetiqueta",json);
			
			DISTRIaltaEnvio obj= new DISTRIaltaEnvio();
			
			obj = gson.fromJson(salida, DISTRIaltaEnvio.class);
			
			//PASO A PDF
			String eti = obj.getEtiqueta();
			String trkNmb = SrvTrkNbr;
			String path ="";
			String barra = "";
			if(eti.equals("")){
				return new DataDescDescripcion("","");
			}
			try
			{
				PropertiesHelper pH=new PropertiesHelper("paths");
				pH.loadProperties();
				path = pH.getValue("pdf");
				String filePath = path+"/"+trkNmb+".pdf";
				
				 // Encode using basic encoder
		         //String base64encodedString = retorno.getPdfserializado();
		         
		         System.out.println("Base64 encoded string :" + eti);

		         // Decode
		         byte[] base64decodedBytes = Base64.getDecoder().decode(eti);

				 File file = new File(filePath);
		         file.delete();
		         
		         FileOutputStream fos = new FileOutputStream(new File(filePath));
		         
		         String str = eti;
		         
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
			    	 
			    	 
			    	 
			    	
			    	 //es lo ultimo
			    	 try (PDDocument document = PDDocument.load(new File(filePath))) 
			    	 {

			             document.getClass();

			             if (!document.isEncrypted()) {
			 			
			                 PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			                 stripper.setSortByPosition(true);

			                 PDFTextStripper tStripper = new PDFTextStripper();

			                 String pdfFileInText = tStripper.getText(document);
			                
			                 String lines[] = pdfFileInText.split("\\r?\\n");
			                 for (String line : lines) 
			                 {
			                     barra = line;
			                     break;
			                 }
			                 barra = barra.replace("*", "");
			                 System.out.println(barra);

			             }

			         }
			    	 catch (Exception e) 
			    	 {
			    		 e.printStackTrace();
					}
			    	 
			    	 
			    	 
			    	 
			        return  new DataDescDescripcion(barra, path+"/"+trkNmb+".pdf");
			     }
			     else
			     {
			    	 return new DataDescDescripcion("","");
			     }
		         				
			}
			catch (Exception e) 
			{
			 e.printStackTrace();
			}
			
			return  new DataDescDescripcion(barra, path+"/"+trkNmb+".pdf");
				
			
			
		} catch (Exception e) {
			return  new DataDescDescripcion("", "");
		}
		
	}
	
	private String callWSPOST_ParamJSON(String URLbase, String jotason)
	{
		String retorno = "";
		HttpClient httpClient = HttpClientBuilder.create().build();
		try 
		{
	    	
	    	System.out.println("llamando a "+URLbase);
	    	System.out.println("Parametros JSON "+jotason);
	    	System.out.println("Respuesta");

	    	String payload = jotason;
	    	StringEntity entityI;
	    	
	    	entityI = new StringEntity(payload, ContentType.APPLICATION_JSON);
	    
	    	HttpPost request = new HttpPost(URLbase);
	    	request.setEntity(entityI);
	    	
		  	HttpResponse httpResponse = httpClient.execute(request);
		  	
		  	
		  	int code=httpResponse.getStatusLine().getStatusCode();
		  	HttpEntity entity = httpResponse.getEntity();
		  	
		  	byte[] buffer = new byte[1024];
		    
		  	if (entity != null) 
		  	{
		        InputStream inputStream = entity.getContent();
		        try 
		        {
		          int bytesRead = 0;
		          BufferedInputStream bis = new BufferedInputStream(inputStream);
		          byte[] contents = new byte[1024];
		          String strFileContents=""; 
		          while((bytesRead = bis.read(contents)) != -1) 
		          { 
		              strFileContents += new String(contents, 0, bytesRead);              
		          }

		          return strFileContents;
		          
		          
		        }
		        catch (RuntimeException runtimeException) 
		        {
		          // In case of an unexpected exception you may want to abort
		          // the HTTP request in order to shut down the underlying
		          // connection immediately.
		          request.abort();
		          runtimeException.printStackTrace();
		        }
		        finally 
		        {
		          // Closing the input stream will trigger connection release
		          try 
		          {
		            inputStream.close();
		          } 
		          catch (Exception ignore) 
		          {
		        	  
		          }
		        }
		      }
		    } 
	    	catch (ClientProtocolException e) 
		    {
		      e.printStackTrace();
		    }
	    	catch (IOException e) 
	    	{
		      // thrown by entity.getContent();
		      e.printStackTrace();
		    }
	    	finally 
	    	{
		      httpClient.getConnectionManager().shutdown();
		    }
		System.out.println(retorno);
		return retorno;
		
	}
	
	public String callWSPOSTparam(String URLbase, List<DataIDDescripcion> params)
	{
		
	
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		
	    try 
		{
	    	
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost(URLbase);
	    	
	    	//StringEntity params =new StringEntity(jotason);
	    	httpPostRequest.setHeader("Content-type", "application/x-www-form-urlencoded");
	    	
	    	//httpPostRequest.setEntity(params);
	    	
	    	ArrayList<NameValuePair> postParameters;
	    	postParameters = new ArrayList<>();
	    	for(DataIDDescripcion p:params){
	    		postParameters.add(new BasicNameValuePair(p.getDescripcion(), p.getDescripcionB()));
	    	}
	        
	        httpPostRequest.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));    	
	    	
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpPostRequest);
		  	int code=httpResponse.getStatusLine().getStatusCode();
		  	HttpEntity entity = httpResponse.getEntity();
		  	
		  	byte[] buffer = new byte[1024];
		    
		  	if (entity != null) 
		  	{
		        InputStream inputStream = entity.getContent();
		        try 
		        {
		          int bytesRead = 0;
		          BufferedInputStream bis = new BufferedInputStream(inputStream);
		          byte[] contents = new byte[1024];
		          String strFileContents=""; 
		          while((bytesRead = bis.read(contents)) != -1) 
		          { 
		              strFileContents += new String(contents, 0, bytesRead);              
		          }

		          System.out.println(strFileContents);
		          return strFileContents;
		          
		          
		        }
		        catch (RuntimeException runtimeException) 
		        {
		          // In case of an unexpected exception you may want to abort
		          // the HTTP request in order to shut down the underlying
		          // connection immediately.
		          httpPostRequest.abort();
		          runtimeException.printStackTrace();
		          return "error";
		        }
		        finally 
		        {
		          // Closing the input stream will trigger connection release
		          try 
		          {
		            inputStream.close();
		          } 
		          catch (Exception ignore) 
		          {
		        	  
		          }
		        }
		      }
		    } 
	    	catch (ClientProtocolException e) 
		    {
		      e.printStackTrace();
		      return "error";
		      
		    }
	    	catch (IOException e) 
	    	{
		      // thrown by entity.getContent();
		      e.printStackTrace();
		      return "error";
		    }
	    	finally 
	    	{
		      httpClient.getConnectionManager().shutdown();
		    }
		return retorno;
	
		
	}
	
}

