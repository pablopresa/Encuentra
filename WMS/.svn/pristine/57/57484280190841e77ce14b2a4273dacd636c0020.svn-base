package cliente_rest_Invoke;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.xerces.parsers.DOMParser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.Cliente;
import jsonObjects.despachoMIRTRANS;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Call_WS_MIRTRANS {

	public despachoMIRTRANS altaEnvio(int pedido, Cliente c){
			
		despachoMIRTRANS despacho = null;
		try {			
		
			List<DataIDDescripcion> parametros = new ArrayList<>();
			DataIDDescripcion data = new DataIDDescripcion(0,"cliente");
			data.setDescripcionB("1729");
			parametros.add(data);
			data = new DataIDDescripcion(0,"pwd");
			data.setDescripcionB("1729");
			parametros.add(data);
			data = new DataIDDescripcion(0,"generarRemito");
			data.setDescripcionB("S");
			parametros.add(data);
			data = new DataIDDescripcion(0,"agenciaOrigen");
			data.setDescripcionB("001");
			parametros.add(data);
			data = new DataIDDescripcion(0,"claveExterna");
			data.setDescripcionB(pedido+"");
			parametros.add(data);
			data = new DataIDDescripcion(0,"tipo");
			data.setDescripcionB("C");
			parametros.add(data);
			data = new DataIDDescripcion(0,"producto");
			data.setDescripcionB("111");
			parametros.add(data);
			data = new DataIDDescripcion(0,"bultos");
			data.setDescripcionB("1");
			parametros.add(data);
			data = new DataIDDescripcion(0,"kilos");
			data.setDescripcionB("1");
			parametros.add(data);
			data = new DataIDDescripcion(0,"destinatario");
			data.setDescripcionB(c.getNombre()+" "+c.getApellido());
			parametros.add(data);
			data = new DataIDDescripcion(0,"direccion");
			data.setDescripcionB(c.getCalle()+" "+c.getNroPuerta());
			parametros.add(data);
			data = new DataIDDescripcion(0,"localidad");
			data.setDescripcionB(c.getCiudad());
			parametros.add(data);
			data = new DataIDDescripcion(0,"rut");
			data.setDescripcionB(c.getRut());
			parametros.add(data);
			data = new DataIDDescripcion(0,"valorCR");
			data.setDescripcionB("0.0");
			parametros.add(data);
			data = new DataIDDescripcion(0,"facturaCR");
			data.setDescripcionB("");
			parametros.add(data);
			
			String salida = callWSPOSTparam("http://cargopostal.uy:8080/axis2/services/WSPostalNet/WSDespachar", parametros); 
			
			despacho = parserAltanEnvio(salida, "return");
			
			
			return despacho;
		} catch (Exception e) {
			return despacho;
		}
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

	public static despachoMIRTRANS parserAltanEnvio(String xml, String tagname)
	{
		despachoMIRTRANS retorno = new despachoMIRTRANS();
		String toSave ="";
		String valorBuscado = "";
		DOMParser parser = new DOMParser();
		try 
		{
		    parser.parse(new InputSource(new java.io.StringReader(xml)));
		    Document doc = parser.getDocument();
		    NodeList nodo = doc.getElementsByTagName(tagname);
		    
		    
		    for (int i = 0; i < nodo.getLength(); i++) 
		    {  
		    	try
		    	{		    		
			    	Node n = nodo.item(i);
			    	
			    	String contenido = n.getTextContent();
			    	contenido = contenido.replace(" ", "");
			    	contenido = contenido.replace("\t", "");
			    	String[]content=contenido.split("\n");
			    	int pasada = 0;
			    	for (int j = 0; j < content.length; j++) 
			    	{
			    		
						String val = content[j];
						//if(!val.equals(""))
						//{
							
							switch (pasada) 
							{
								case 1:
								{
									retorno.setAgencia(val);
									break;
								}
								case 2:
								{
									retorno.setCodigoBarra(val);
									break;
								}
								case 3:
								{
									retorno.setErrorCodigo(val);
									break;
								}
								case 4:
								{
									retorno.setErrorDescripcion(val);
									break;
								}
								case 5:
								{
									retorno.setEtiqueta(val);
									break;
								}
								case 6:
								{
									retorno.setFechaHora(val);
									break;
								}
								case 7:
								{
									retorno.setLocalidadDestinoTiempost(val);
									break;
								}
								case 8:
								{
									retorno.setPrecio(val);
									break;
								}
								case 9:
								{
									retorno.setRemito(val);
									break;
								}						
								
							default:
								break;
							}
							
							pasada++;
						//}
					}
			    	
			    	
		    	}
		    	catch (Exception e)
		    	{
		    		
		    	}
			}  
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}    
		return retorno;
	}
}
