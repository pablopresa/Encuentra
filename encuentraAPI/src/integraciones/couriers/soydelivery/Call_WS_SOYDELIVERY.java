package integraciones.couriers.soydelivery;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import beans.Fecha;
import beans.api.json.Cliente;
import beans.api.json.Credenciales;
import beans.datatypes.DataDescDescripcion;
import beans.helper.PropertiesHelper;
import beans.helper.PropertiesHelperAPI;
import integraciones.couriers.districad.DISTRIaltaEnvio;

public class Call_WS_SOYDELIVERY {
	
	public OutputDespachoSDL setEnvio(Cliente c, Credenciales cred) {
		
		OutputDespachoSDL out = new OutputDespachoSDL();
		try {
			DespachoSDL despacho = new DespachoSDL();
			
			//separo direccion de nro de puerta y apto
			/*String apto = "";
			String nro = "";
			try {
				String direccion = c.getCalle().replaceAll("[^\\d' '/]", "");
				String[] puertaApto;
				if(direccion.contains("/")) {
					puertaApto = direccion.split("/");
					nro = puertaApto[0].replaceAll(" ", "");
					apto = puertaApto[1].replaceAll(" ", "");
				}
				else {
					puertaApto = direccion.split(" ");
					int i = 0;
					for(String s:puertaApto) {
						if(s!=null && !s.equals("")) {
							i++;
							switch (i) {
							case 1:
								nro = s;
								break;
							case 2:
								apto = s;
								break;

							default:
								break;
							}
						}
					}
				}
				 
				
				if(nro.equals(apto)) {
					apto = "";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
			
			
			despacho.setNegocio_id(Integer.parseInt(cred.getUser()));
			despacho.setNegocio_clave(Integer.parseInt(cred.getPass()));
			despacho.setNegocio_sucursal_external_id(cred.getIdCliente());
			despacho.setNegocio_RepartidoId(0);
			despacho.setNombre_cliente(c.getNombre()+" "+c.getApellido());
			despacho.setTelefono_cliente(c.getTelefono());
			despacho.setEmail_cliente(c.getEmail());
			despacho.setCiudad_origen("");
			despacho.setCalle_origen("");
			despacho.setNumero_origen("");
			despacho.setApto_origen("");
			despacho.setEsquina_origen("");
			despacho.setObservacion_origen("");
			despacho.setLocation_origen("");
			//despacho.setCiudad_destino(c.getCiudad());
			despacho.setCiudad_destino(c.getDepartamento());
			despacho.setCalle_destino(c.getCalle());
			//despacho.setNumero_destino(c.getNroPuerta());
			despacho.setNumero_destino(c.getNroPuerta());
			despacho.setApto_destino(c.getNroApto());
			despacho.setEsquina_destino("");
			despacho.setLocation_destino("");
			despacho.setFecha_entrega(cred.getFecha());
			despacho.setFranja_horaria(1);
			despacho.setCantidad_bultos(1);
			despacho.setDetalle("Pedido "+cred.getPedido());
			despacho.setPedido_external_id(cred.getPedido()+"");
			despacho.setNro_Factura("");
			//despacho.setServicio("6");
			//despacho.setTipo_Vehiculo_Nombre("Camioneta");
			despacho.setTipo_Producto(0);
			despacho.setPreclasificacion("");
			despacho.setComplejidad("");
			despacho.setProductos(new ArrayList<>());
			
			Gson gson = new Gson();
			String json = gson.toJson(despacho);
			/*	TESTING
			 * http://testing.soydelivery.com.uy/rest/awsnuevopedido1
			 * "Negocio_id": 2143,
				"Negocio_clave": 1081,
			 */
			
			/*	PROD
			 * https://soydelivery.com.uy/rest/awsnuevopedido1
			 * "Negocio_id": 3669,
				"Negocio_clave": 1081,
			 */
			
			String salida = callWSPOST_ParamJSON("https://soydelivery.com.uy/rest/awsnuevopedido1",json);
			
			out = gson.fromJson(salida, OutputDespachoSDL.class);
			
			out = validateOutput(out, despacho, gson);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out;		
	}
	
	public DataDescDescripcion getEtiqueta (int guia, Credenciales cred){
        
		Gson gson = new Gson();
		EtiquetaSDL pegote;
		String retorno = "";

		String barra = guia+"";
		String oficina = "SDL";

		try{

			String jotason = "{ "+
					" \"Pedido_id\":"+guia+", "+
					" \"Negocio_id\":"+cred.getUser()+", "+
					" \"Negocio_clave\":"+cred.getPass()+" "+
					"}";
			
			/*	TESTING
			 * http://testing.soydelivery.com.uy/rest/awsetiquetapedido1
			 * "Negocio_id": 2143,
				"Negocio_clave": 1081,
			 */
			
			/*	PROD
			 * https://soydelivery.com.uy/rest/awsetiquetapedido1
			 * "Negocio_id": 3669,
				"Negocio_clave": 1081,
			 */

			retorno = callWSPOST_ParamJSON("https://soydelivery.com.uy/rest/awsetiquetapedido1", jotason);

			pegote = gson.fromJson(retorno, EtiquetaSDL.class);
			retorno = pegote.getEtiqueta_base64();

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

				File f = new File(filePath);
				if(f.exists())
				{
					path = pH.getValue("HTTP_pdf");                                         
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

	public trackingSDL getTracking(Credenciales cred) {	
		
		trackingSDL out = new trackingSDL();
		try {			
			String json = "{\r\n" + 
						"\"Negocio_id\":"+cred.getUser()+",\r\n" + 	
						"\"Negocio_clave\":"+cred.getPass()+",\r\n" + 
						"\"Pedido_id\":"+cred.getTracking()+"\r\n" + //getTracking
						"}";
			
			/*	TESTING
			 *http://testing.soydelivery.com.uy/rest/awsconsultarpedido1
			 * "Negocio_id": 2143,
				"Negocio_clave": 1081,
			 */
			
			/*	PROD
			 * https://soydelivery.com.uy/rest/awsconsultarpedido1
			 * "Negocio_id": 3669,
				"Negocio_clave": 1081,
			 */
			
			String salida = callWSPOST_ParamJSON("https://soydelivery.com.uy/rest/awsconsultarpedido1",json);
			
			Gson gson = new Gson();
			out = gson.fromJson(salida, trackingSDL.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out;		
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
	
	private OutputDespachoSDL validateOutput(OutputDespachoSDL out, DespachoSDL despacho, Gson gson) {
		
		try {
			String salida = "";
			String json = "";
			
			int sumadias = 2;
			int subindice = 1;
			int intentos = 1;
			boolean ok = false;
			while (!ok && intentos<4) {
				switch (out.getError_code()) {
				case 0: 
					ok= true;
					break;
				case 22:	//Cambiar Fecha
				case 24:	
					Fecha fecha = new Fecha(sumadias,0,0);
					String fEntrega = fecha.getAnio()+"-"+fecha.getMes()+"-"+fecha.getDia();
					sumadias++;
					despacho.setFecha_entrega(fEntrega);
					
					json = gson.toJson(despacho);
					salida = callWSPOST_ParamJSON("https://soydelivery.com.uy/rest/awsnuevopedido1",json);
					
					out = gson.fromJson(salida, OutputDespachoSDL.class);
					break;
				case 25:	//quitar mail
					despacho.setEmail_cliente("");
					json = gson.toJson(despacho);
					salida = callWSPOST_ParamJSON("https://soydelivery.com.uy/rest/awsnuevopedido1",json);
					
					out = gson.fromJson(salida, OutputDespachoSDL.class);
					break;
				case 26:	//cambiar referencia externa del pedido
					despacho.setPedido_external_id(despacho.getPedido_external_id()+"_"+subindice);
					System.out.println(despacho.getPedido_external_id());
					json = gson.toJson(despacho);
					salida = callWSPOST_ParamJSON("https://soydelivery.com.uy/rest/awsnuevopedido1",json);
					
					out = gson.fromJson(salida, OutputDespachoSDL.class);
					subindice++;
					break;
				default:
					ok = true;
					break;
				}
				
				intentos++;
			}
				
		} catch (Exception e) {
			
		}
		
		return out;
	}
}
