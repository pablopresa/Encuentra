package integraciones.couriers.dac;

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

import com.google.gson.Gson;

import beans.api.BarcodeReader;
import beans.api.json.JSONdataDAC;
import beans.api.json.JSONresultDAC;
import beans.api.json.JsonRespuestaDAC;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.helper.PropertiesHelperAPI;
import logica.LogicaAPI;

public class Call_WS_DAC {

	private String getToken(String u, String p, int idEmpresa)
	{
		Gson gson = new Gson();
		JSONresultDAC result;
		String retorno = "";
		String token = "";
		String json = "{"+
						"\"Login\":\""+u+"\","+
						"\"Contrasenia\":\""+p+"\""+
					   "}";
		
		try{
			List<DataIDDescripcion> tokens = LogicaAPI.darListaDataIdDescripcionConsulMYSQL("SELECT TIME_TO_SEC(timediff(CURRENT_TIMESTAMP(),Obtenido)) tiempo,token FROM apitoken_cliente WHERE idCliente = 1 and idEmpresa="+idEmpresa+"");
			if(!tokens.isEmpty())
			{
				DataIDDescripcion tokenDID = tokens.get(0);
				if(tokenDID.getId()<1800)//una hora de vida
				{
					return tokenDID.getDescripcion();
				}
				else
				{				
					retorno = callWSPOST_ParamJSON("http://altis-web.grupoagencia.com:8087/JAgencia.asmx/wsLogin",json); 
					result = new JSONresultDAC();
					result = gson.fromJson(retorno, JSONresultDAC.class);
					token = result.getData()[0].getID_Session();
					
					LogicaAPI.persistir("UPDATE apitoken_cliente SET token='"+token+"' WHERE idEmpresa="+idEmpresa+" AND `idCliente`=1;");
					return token;
				}
			}
			else
			{
				retorno = callWSPOST_ParamJSON("http://altis-web.grupoagencia.com:8087/JAgencia.asmx/wsLogin",json); 
				result = new JSONresultDAC();
				result = gson.fromJson(retorno, JSONresultDAC.class);
				token = result.getData()[0].getID_Session();
				
				LogicaAPI.persistir("insert into apitoken_cliente (idCliente,token,idEmpresa) values (1,'"+token+"',"+idEmpresa+") ;");
				return token;
			}
		}
		catch(Exception e){
			return "";
		}
	}
	
	private JSONdataDAC getBarrios(String session,String barrio, String ciudad)
	{
		
		String retorno = "";
		
		String json = "{"+
						"\"Barrrio\":\""+barrio+"\","+
						"\"ID_Sesion\":\""+session+"\""+
					   "}";
		
		try{
			
			retorno = callWSPOST_ParamJSON("http://altis-web.grupoagencia.com:8087/JAgencia.asmx/wsBarrio",json); 
			beans.api.json.JSONresultDAC  resultado = new beans.api.json.JSONresultDAC();
			Gson gson = new Gson();
			resultado = gson.fromJson(retorno, beans.api.json.JSONresultDAC.class);
			
			for(JSONdataDAC j:resultado.getData())
			{
				if(j.getD_Estado().equals(ciudad.toUpperCase()))
				{
					if(barrio.toUpperCase().equals(j.getD_Barrio().toUpperCase()))
					{
						return j;
					}
				}
				
			}
			
			return null;
			
		}
		catch(Exception e)
		{
			
			return null;
		}
	}
	
	public DataDescDescripcion generarEtiqueta(String Cliente, String Destinatario, String Calle, String Nro, String NroDeApto, String BarrioLocalidad, String Departamento, 
			String TelefonoContacto, String EmailRecibe, String Observaciones, String NroPedido, String lat, String lon,int idEmpresa, String u, String p, String nombreRemitente, String documentoRecibe, int cantidadPaquetes,int xGrandes, int cantGrandes, int cantMedianos, int cantChicos, int tipoShipping)
	{
		DataDescDescripcion retorno = new DataDescDescripcion();
		//(Chicos 1, Medianos 2, Grandes 3, Extra 5
		String detCantTam = "";
		if(cantChicos>0)
		{
			detCantTam += "{\\\"Cantidad\\\": "+ cantChicos +", \\\"Tipo\\\": 1},";
		}
		
		if(cantMedianos>0)
		{
			detCantTam += "{\\\"Cantidad\\\": "+ cantMedianos +", \\\"Tipo\\\": 4},";
		}
		if(cantGrandes>0)
		{
			detCantTam += "{\\\"Cantidad\\\": "+ cantGrandes +", \\\"Tipo\\\": 5},";
		}
		
		if(xGrandes>0)
		{
			detCantTam += "{\\\"Cantidad\\\": "+ xGrandes +", \\\"Tipo\\\": 7},";
		}
		
		
		detCantTam=detCantTam.substring(0,detCantTam.length()-1);
		
		String token = getToken(u,p,idEmpresa);
		if(BarrioLocalidad==null)
		{
			BarrioLocalidad=Departamento;
		}
		
		
		JSONdataDAC bl = getBarrios(token, BarrioLocalidad, Departamento);
		if(bl==null)
		{
			bl = getBarrios(token, Departamento, Departamento);
		}
		cantidadPaquetes = cantChicos + cantMedianos + cantGrandes + xGrandes;
		
		String json = "{\r\n" + 
				"    \"ID_Sesion\": \""+token+"\",\r\n" + 
				"    \"K_Cliente_Remitente\": "+u+",\r\n" + 
				"    \"D_Cliente_Remitente\": \""+nombreRemitente+"\",\r\n" + 
				"    \"K_Cliente_Destinatario\": 5,\r\n" + 
				"    \"Cliente_Destinatario\": \""+Cliente+"\",\r\n" + 
				"    \"RUT\": \""+documentoRecibe+"\",\r\n" + 
				"    \"Direccion_Destinatario\": \""+Calle+" "+Nro+" "+NroDeApto+"\",\r\n" + 
				"    \"K_Barrio\": "+bl.getK_Barrio()+",\r\n" + 
				"    \"K_Ciudad_Destinatario\": "+bl.getK_Ciudad()+",\r\n" + 
				"    \"K_Estado_Destinatario\": "+bl.getK_Estado()+",\r\n" + 
				"    \"K_Pais_Destinatario\": "+bl.getK_Pais()+",\r\n" + 
				"    \"CP_Destinatario\": "+bl.getCodigo_Postal()+",\r\n" + 
				"    \"Telefono\": \""+TelefonoContacto+"\",\r\n" + 
				"    \"K_Oficina_Destino\": \"\",\r\n" +//dejar vacia 
				"    \"Entrega\": 2,\r\n" + 
				"	 \"K_Tipo_Envio\": 1, \r\n" +
				"    \"Paquetes_Ampara\": "+cantidadPaquetes+",\r\n" + 
				
				//"	 \"Detalle_Paquetes\": \"[{\\\"Cantidad\\\": "+ cantidadPaquetes +", \\\"Tipo\\\": 1}]\", \r\n" + 
				"	 \"Detalle_Paquetes\": \"["+detCantTam+"]\", \r\n" +
				"    \"Observaciones\": \""+Observaciones+"\",\r\n" + 
				"    \"K_Tipo_Guia\": "+tipoShipping+",\r\n" +
				"    \"K_Articulo\":\"\",\r\n" + 
				"    \"CostoMercaderia\": 0,\r\n" + 
				"    \"Referencia_Pago\":\"\",\r\n" + 
				"    \"CodigoPedido\": \""+NroPedido+"\",\r\n" + 
				"    \"Serv_DDF\": 0,\r\n" +
				"    \"Latitud_Destino\": \"\",\r\n" +
				"    \"Longitud_Destino\": \"\",\r\n" +
				"	 \"Serv_Cita\":1 \r\n"+
				"}";
		
		try{
			
			String devuelve = callWSPOST_ParamJSON("http://altis-web.grupoagencia.com:8087/JAgencia.asmx/wsInGuia_Nuevo",json);
			System.out.println(devuelve);
			beans.api.json.JSONresultDAC_2  resultado = new beans.api.json.JSONresultDAC_2();
			Gson gson = new Gson();
			resultado = gson.fromJson(devuelve, beans.api.json.JSONresultDAC_2.class);
			String guia = "";
			String ofi = "";
			JSONdataDAC r = resultado.getData(); 
			
			guia = r.getK_Guia().split("-")[1];
			ofi = r.getK_Guia().split("-")[0];
			//ofi = r.getK_Oficina_Destino();
			
			retorno = getEtiqueta(ofi,guia,NroPedido,idEmpresa,u,p);
			//retorno.setId(r.getK_Guia());
			return retorno;
			
		}
		catch(Exception e)
		{
			return new DataDescDescripcion("","ERROR de tipo"+e.getMessage());
		}
	}
	
	public DataDescDescripcion getEtiqueta (String oficina, String guia, String idPedido,int idEmpresa, String u, String p)
	{
		
		Gson gson = new Gson();
		JsonRespuestaDAC pegote;
		String retorno = "";
		String token = "";
		String barra = "";
		
		try{
			token = getToken(u,p,idEmpresa);
					
			String json = "{"+
					"\"K_Oficina\":\""+oficina+"\","+
					"\"K_Guia\":\""+guia+"\","+
					"\"CodigoPedido\":\""+idPedido+"\","+
					"\"ID_Sesion\":\""+token+"\""+
				   "}";
					
			
			retorno = callWSPOST_ParamJSON("http://altis-web.grupoagencia.com:8087/JAgencia.asmx/wsGetPegote", json);
			
			System.out.println(retorno);
			beans.api.json.JSONresultDAC_2  resultado = new beans.api.json.JSONresultDAC_2();
			gson = new Gson();
			resultado = gson.fromJson(retorno, beans.api.json.JSONresultDAC_2.class);
			
			JSONdataDAC r = resultado.getData();
			
			
			retorno = r.getPegote();
			
			//PASO A PDF
			String path ="";
			try
			{
				PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
				pH.loadProperties();
				path = pH.getValue("pdf");
				String filePath = path+oficina+"-"+guia+".pdf";
				
				 // Encode using basic encoder
		         //String base64encodedString = retorno.getPdfserializado();
		         
		         System.out.println("Base64 encoded string :" + retorno);

		         // Decode
		         byte[] base64decodedBytes = Base64.getDecoder().decode(retorno);

				 File file = new File(filePath);
		         file.delete();
		         
		         FileOutputStream fos = new FileOutputStream(new File(filePath));
		         
		         String str = retorno;
		         
		         InputStream in = new ByteArrayInputStream(base64decodedBytes);
		         
		         int inByte;
		            while ((inByte = in.read()) != -1) {
		                fos.write(inByte);
		            }
		 
		            in.close();
		           
		            
		         
		         fos.write(str.getBytes());
		         
		         
		         fos.close();
		         
		         
		         /*File f = new File(filePath);
			     if(f.exists())
			     {
			    	 path = pH.getValue("HTTP_pdf");		    	
			    	
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
			                 }
			                 System.out.println(barra);

			             }

			         }
			    	 catch (Exception e) 
			    	 {
						
					}
			    	 
			    	 
			    	 
			        return  new DataDescDescripcion(barra, path+"/"+oficina+"-"+guia+".pdf");
			     }
			     else
			     {
			    	 return new DataDescDescripcion("","");
			     }*/
		         path = pH.getValue("HTTP_pdf");
		         BarcodeReader br = new BarcodeReader(filePath, 50000, 1);
		         if(br!=null && !br.getBarcodes().isEmpty()) {
		        	 barra = br.getBarcodes().get(0);
		        	 return  new DataDescDescripcion(barra, path+"/"+oficina+"-"+guia+".pdf");
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
			
			return  new DataDescDescripcion(barra, path+"/"+oficina+"-"+guia+".pdf");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new DataDescDescripcion("","");
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

