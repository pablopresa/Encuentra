package integraciones.couriers.mirtrans;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.itextpdf.text.DocumentException;

import beans.api.json.Cliente;
import beans.api.json.Credenciales;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.IPrint_API;
import integraciones.couriers.mirtrans.despachoMIRTRANS;
import logica.LogicaAPI;

public class Call_WS_MIRTRANS {

	public despachoMIRTRANS altaEnvio(Cliente c, Credenciales cred){
			
		despachoMIRTRANS despacho = null;
		try {			
		
			List<DataIDDescripcion> parametros = new ArrayList<>();
			DataIDDescripcion data = new DataIDDescripcion(0,"cliente");
			data.setDescripcionB(cred.getUser());
			parametros.add(data);
			data = new DataIDDescripcion(0,"pwd");
			data.setDescripcionB(cred.getPass());
			parametros.add(data);
			data = new DataIDDescripcion(0,"generarRemito");
			data.setDescripcionB("S");
			parametros.add(data);
			data = new DataIDDescripcion(0,"agenciaOrigen");
			data.setDescripcionB("001");
			parametros.add(data);
			data = new DataIDDescripcion(0,"claveExterna");
			data.setDescripcionB(cred.getPedido()+"");
			parametros.add(data);
			data = new DataIDDescripcion(0,"tipo");
			data.setDescripcionB(cred.getTipoEnvio());
			//data.setDescripcionB("C");
			parametros.add(data);
			data = new DataIDDescripcion(0,"producto");
			data.setDescripcionB("241");
			parametros.add(data);
			data = new DataIDDescripcion(0,"bultos");
			data.setDescripcionB("1");
			parametros.add(data);
			data = new DataIDDescripcion(0,"kilos");
			data.setDescripcionB("2");
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
			System.out.println(salida);
			
			LogicaAPI.persistir("insert into respuestas_wss(idPedido,mensaje,respuesta) values('"
			+cred.getPedido()+"','"+parametros.toString()+"','"+salida+"')");
			
			int intentos = 4;
			int sub = 0;
			if(salida.contains("Clave externa ya utilizada"))
			{
				while (intentos>0) 
				{
					intentos --;
					sub++;
					
					parametros.get(4).setDescripcionB(parametros.get(4).getDescripcionB()+"_"+sub);
					salida = callWSPOSTparam("http://cargopostal.uy:8080/axis2/services/WSPostalNet/WSDespachar", parametros);
					LogicaAPI.persistir("insert into respuestas_wss(idPedido,mensaje,respuesta) values('"
							+cred.getPedido()+"','"+parametros.toString()+"','"+salida+"')");
					if(!salida.contains("Clave externa ya utilizada"))
					{
						intentos=0;
					}
					
				}
				
			}
			if(salida.contains("La localidad no es"))
			{
				parametros.get(11).setDescripcionB(c.getDepartamento());
				salida = callWSPOSTparam("http://cargopostal.uy:8080/axis2/services/WSPostalNet/WSDespachar", parametros);	
				LogicaAPI.persistir("insert into respuestas_wss(idPedido,mensaje,respuesta) values('"
						+cred.getPedido()+"','"+parametros.toString()+"','"+salida+"')");
			}
			
			if(salida.contains("Clave externa ya utilizada"))
			{
				despacho = new despachoMIRTRANS();
				despacho.setEtiqueta("Clave externa ya utilizada");
				despacho.setCodigoBarra("0");
			
			}
			else
			{
				despacho = parserAltanEnvio(salida, c, cred);
			}
			
			
			
			
			return despacho;
		} catch (Exception e) {
			e.printStackTrace();
			return despacho;
		}
	}

	public trackingMIRTRANS getTracking(Credenciales cred) {
		
		trackingMIRTRANS tracking = new trackingMIRTRANS();
		try {
			List<DataIDDescripcion> parametros = new ArrayList<>();
			DataIDDescripcion data = new DataIDDescripcion(0,"cliente");
			data.setDescripcionB(cred.getUser());
			parametros.add(data);
			data = new DataIDDescripcion(0,"pwd");
			data.setDescripcionB(cred.getPass());
			parametros.add(data);
			data = new DataIDDescripcion(0,"claveExterna");
			data.setDescripcionB(cred.getPedido());
			parametros.add(data);
			
			
			String salida = callWSPOSTparam("https://cargopostal.uy:443/axis2/services/WSPostalNet/WSConsultarRemitoClaveExterna", parametros); 
			System.out.println(salida);
			
			
			int intentos = 2;
			int sub = 0;
			if(!salida.contains("Entregado"))
			{
				String pedido = parametros.get(2).getDescripcionB();
				while (intentos>0) 
				{
					intentos --;
					sub++;
										
					parametros.get(2).setDescripcionB(pedido+"_"+sub);
					salida = callWSPOSTparam("https://cargopostal.uy:443/axis2/services/WSPostalNet/WSConsultarRemitoClaveExterna", parametros);
					if(salida.contains("Entregado"))
					{
						intentos=0;
					}
					
				}
				
			}

			String estado = salida.split("<ax21:estadoDescripcion>")[1].split("</ax21:estadoDescripcion>")[0];
			String fecha = salida.split("<ax21:fecha>")[1].split("</ax21:fecha>")[0];
			String remito = salida.split("<ax21:remito>")[1].split("</ax21:remito>")[0];
			
			tracking.setEstadoDescripcion(estado);
			tracking.setFecha(fecha);
			tracking.setRemito(remito);
			
		} catch (Exception e) {
			tracking.setEstadoDescripcion("");
			tracking.setFecha("");
			tracking.setRemito("");
		}
		return tracking;
		
	}
	
	public String callWSPOSTparam(String URLbase, List<DataIDDescripcion> params)
	{	
		String retorno = "";
		
		
		final HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 60000);
		HttpClient httpClient = new DefaultHttpClient(httpParams);
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

	public static despachoMIRTRANS parserAltanEnvio(String xml, Cliente c, Credenciales cred) throws FileNotFoundException, DocumentException
	{
		despachoMIRTRANS retorno = new despachoMIRTRANS();
		String eti = "";
		if(xml.contains("<ax21:etiqueta>")) {
			eti = xml.split("<ax21:etiqueta>")[1].split("</ax21:etiqueta>")[0];
		}else {
			eti = xml.split("<ax23:etiqueta>")[1].split("</ax23:etiqueta>")[0];
		}
		
		System.out.println(eti);
		String path = IPrint_API.ImprimirEtiquetasMIRTRANS(eti, c.getTelefono(),c.getCiudad(), c.getObs());

		String [] pathTrack = path.split(",");
		
		
		
		retorno.setEtiqueta(pathTrack[0]);
		retorno.setCodigoBarra(pathTrack[1]);
		
		
		
		return retorno;
	}

	
}
