package cliente_rest_Invoke;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Fecha;
import beans.MovStock;
import beans.Usuario;
import beans.jsonEstadoMP;
import beans.api.CallBackPedido;
import beans.api.ColeccionMovStock;
import beans.api.GrabarRecepcion;
import beans.api.Order;
import beans.api.articuloBarra;
import beans.encuentra.DataPicking;
import beans.encuentra.Remito;
import dataTypes.DataDescDescripcion;
import dataTypes.DataEcommerce_canales_envio;
import dataTypes.DataIDDescripcion;
import dataTypes.trackingPedido;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.Compras;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.Productos;
import eCommerce_jsonObjectsII.UESEnvio;
import helper.PropertiesHelper;
import jsonObjects.Credenciales;
import jsonObjects.CredencialesPEYA;
import jsonObjects.DISTRIaltaEnvio;
import jsonObjects.PrintObject;
import jsonObjects.RespuestaMIR;
import jsonObjects.SendMail;
import jsonObjects.Shipping;
import jsonObjects.trackingMIRTRANS;
import jsonObjects.trackingSDL;
import jsonObjects.trackingUES;
import logica.Logica;
import logica.Utilidades;

public class Call_WS_APIENCUENTRA {

	private String apiHost;
	
	public Call_WS_APIENCUENTRA() {
		
		PropertiesHelper ph = new PropertiesHelper("paths");
		try
    	{
    		ph.loadProperties();
    		apiHost = ph.getValue("api");
    	}
    	catch(Exception e)
    	{
    		System.out.println("No se encontro el path para direccionar a la API");
    		e.printStackTrace();
    	}
	}
	
	public String[] prepararRemito (int idDepoCentral, int destino, Usuario uLog,List<DataPicking> l,int idEmpresa, int idDepoWEB,List<DataIDDescripcion> list )
	{
		
		String [] menError = {"",""};
		DataIDDescripcion respVisual = null;								
		
		MovStock m = new MovStock();
		m.setOrigen(idDepoCentral);
		m.setDestino(destino);
		m.setIdUsuario(uLog.getNumero());
		m.setDetalle(list);
		m.setOrigenDoc(new Long(l.get(0).getIdPicking()));
		m.setDocSolicitud(l.get(0).getSolicitud());
		m.setDoc(0);
		m.setRazon(0);
		m.setObservacion("");
		m.setUsuario(uLog.getNombre()+" "+uLog.getApellido());
		if(destino!=idDepoWEB) { m.setEntrega(true);}
		
		//respVisual = new DataIDDescripcion(0,"");
		respVisual = movStock(m, false, idEmpresa);								
		
		if(respVisual!=null)
		{
			
			menError[0]=respVisual.getDescripcion();
			menError[1]=respVisual.getDescripcionB();
			
		}
		
		return menError;
	}
	
	
	public void putCallBackPedidos(CallBackPedido cbp){
		try {
			Utilidades util = new Utilidades();
			String token_API = util.darParametroEmpresaSTR(cbp.getIdEmpresa(), 10);
			String host = apiHost;
			//String host = "10.108.0.165:8080";
			String servicio = "http://"+host+"/encuentraAPI/Integraciones/callback/save?token="+token_API; 
						
			Gson gson = new Gson();
			String json = gson.toJson(cbp);
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void PutColaEnvioMails(List<SendMail> mails, int idEmpresa){
		try {
			Utilidades util = new Utilidades();
			String token_API = util.darParametroEmpresaSTR(idEmpresa, 10);
			String host = apiHost;
			//String host = "10.108.0.165:8080";
			String servicio = "http://"+host+"/encuentraAPI/Integraciones/MailSpooler/putMails?token="+token_API; 
						
			Gson gson = new Gson();
			String json = gson.toJson(mails);
			json = "{\"mails\":"+json+"}";
			System.out.println(json);
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void PutColaImpresion(String id, String url, int porait, int printer, int idEquipo, int idEmpresa, int vias)
	{
		try 
		{
			
			Logica log = new Logica();
			
			
			
			String token_API=log.darParametroEmpresa(idEmpresa,10);
			
			
			
			String host = apiHost;
			String servicio = "http://"+host+"/encuentraAPI/Integraciones/PrintSpooler/put?token="+token_API; 
			
			PrintObject p = new PrintObject();
			p.setId(id);
			p.setUrl(url);
			p.setPorait(porait+"");
			p.setPrinterId(printer+"");
			p.setIdEquipo(idEquipo+"");
			p.setVias(vias);
			
			Gson gson = new Gson();
			String json = gson.toJson(p);
			
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void RePrint(String id, String url, int porait, int printer, int idEmpresa){
		try {
			Logica log = new Logica();
			String token_API=log.darParametroEmpresa(idEmpresa,10);
			String host = apiHost;
			String servicio = "http://"+host+"/encuentraAPI/Integraciones/PrintSpooler/reprint?token="+token_API; 
			
			PrintObject p = new PrintObject();
			p.setId(id);
			p.setUrl(url);
			p.setPorait(porait+"");
			p.setPrinterId(printer+"");
			
			Gson gson = new Gson();
			String json = gson.toJson(p);
			
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DataDescDescripcion setEnvio(Shipping ship,DataEcommerce_canales_envio env,String pedido,String fecha,String hora,int idEmpresa){
		String url="";
		String track = "";
		String host = apiHost;
		
		
		Logica log = new Logica();
		String token_API=log.darParametroEmpresa(idEmpresa,10);
		
		
		try {
			Credenciales c = new Credenciales();
			Gson gson = new Gson();
			String json="";
			String salida = "";			
			
			switch (env.getCourier()) 
			{
				case "PEDIDOS_YA":
				{
					Fecha fechaPe = null;
					String horaEnvioD = "";
					String horaEnvioH = "";
					if(ship.getHoraDesde()!=null)
					{
						horaEnvioD = ship.getHoraDesde();
						horaEnvioH = ship.getHoraHasta();
						
						
						Fecha fechaEnvioD = new Fecha(horaEnvioD.replace("T", " "));
						Fecha fechaEnvioH = new Fecha(horaEnvioH.replace("T", " "));
						Fecha fechaActual = new Fecha(true,0);
						System.out.println(horaEnvioD);
						
						if(fechaActual.getDiaDelAnio()>=fechaEnvioD.getDiaDelAnio())
						{
							if(fechaActual.getHora()>=fechaEnvioD.getHora() && fechaActual.getHora()<=fechaEnvioH.getHora())
							{
								//envialo YA
								fechaPe = new Fecha(fechaActual.getDia(), fechaActual.getMes(), fechaActual.getAnio(), fechaActual.getHora(), fechaActual.getMinuto()+3,3,"");
								
							}
							else
							{
								//que pasa si la hora no esta dentro de la ventana horaria
								//si es antes de la hora?
								if(fechaActual.getHora()<fechaEnvioD.getHora())
								{
									//envialo YA
									fechaPe = new Fecha(fechaActual.getDia(), fechaActual.getMes(), fechaActual.getAnio(), fechaEnvioD.getHora(), fechaEnvioD.getMinuto(),3,"");
									
								}
								else
								{
									//si es despues de la hora?
									//envialo YA
									fechaPe = new Fecha(fechaActual.getDia(), fechaActual.getMes(), fechaActual.getAnio(), fechaEnvioD.getHora(), fechaEnvioD.getMinuto(),27,"");//se le suma 24Hs
									
								}
								
								
								
							}
						}
						else
						{
							//es para enviar mas adelante
							fechaPe = new Fecha(fechaEnvioD.getDia(), fechaEnvioD.getMes(), fechaEnvioD.getAnio(), fechaEnvioD.getHora(), fechaEnvioD.getMinuto(),3,"");
							
						}
						
					}
					else
					{
						Fecha fecha_ = new Fecha(true,3);
				    	System.out.println(fecha_.darHoraMinutoSegundoZ());
				    	System.out.println(fecha_.getDiaSemana());
				    	
				    	Utilidades ut = new Utilidades();
				    	Fecha posible = ut.darHoraHabilDeposito(512,2,fecha_) ;
				    	
				    	System.out.println(posible.darHoraMinutoSegundoZ());
				    	System.out.println(posible.getDiaSemana());
				    	
				    	fechaPe = new Fecha(posible.getDia(), posible.getMes(), posible.getAnio(), posible.getHora(), posible.getMinuto(),3,"");
				    	
				    	
						
					}
					
					
					
					
					
					System.out.println("fecha pedidosYA "+ fechaPe.darHoraMinutoSegundoZ());
			    	
					
					CredencialesPEYA creds = gson.fromJson(env.getPass(), CredencialesPEYA.class);
					
					
					
					
					Credenciales credenciales = new Credenciales(creds.getUsername(), creds.getPassword(), creds.getClient_secret(), creds.getClient_id(), pedido, fechaPe.darFechaAnio_Mes_Dia(), fechaPe.darHoraMinutoSegundoZ(), "", "5000", "");
					ship.setCredenciales(credenciales);
					ship.setTipoShipping(38719561);
					ship.setVolumen("10.0");
					ship.setPeso("1.0");
					ship.setCantidadPaquetes(1);
					ship.setMailNotificacion(ship.getCliente().getEmail());
					
					
					Utilidades ut = new Utilidades();
					
					ship.getCliente().setCalle(ut.limpiarS(ship.getCliente().getCalle()));
					ship.getCliente().setNroPuerta(ut.soloNumeros(ship.getCliente().getNroPuerta()));
					
					
					
					
					Cliente sender = log.darSenderPeYa(idEmpresa,pedido);
					ship.setSender(sender);
					System.out.println(sender.getCiudad());
					json = gson.toJson(ship);
					salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/PeYaShipping?token="+token_API, json);
					
					DataDescDescripcion respuesta = gson.fromJson(salida, DataDescDescripcion.class);
					Long idPedido = log.darIdPedidoIdFenicio(pedido, idEmpresa);
					if(respuesta.getId().equals("FAIL"))
					{
					
						log.logPedido(idPedido, 0, 3, "ERROR AL GENERAR DESPACHO"+respuesta.getDescripcion(), -1, idEmpresa);
						
						Utilidades util = new Utilidades();
						String mailsD = util.darParametroEmpresaSTR(idEmpresa, 48);
						List<SendMail> mails = new ArrayList<>();
						SendMail sm = new SendMail("PY"+idPedido, mailsD, "ERROR AL GENERAR DESPACHO PEDIDOS YA", "Al pedido "+idPedido+" no se le pudo generar el despacho correspondiente.", "encuentra@200.com.uy");
						mails.add(sm);
						PutColaEnvioMails(mails,idEmpresa);
						
						return new DataDescDescripcion("", "PEDIDOS_YA");
					}
					else
					{
						log.logPedido(idPedido, 0, 3, "Tracking pedidosYA: " +respuesta.getDescripcion(), 1, idEmpresa);
						try
						{
							log.logPedido(idPedido, 0, 3, "URL tracking pedidosYA: <a target=\"_BLANK\" href=\""+respuesta.getFecha()+"\"> " +respuesta.getFecha() +"</a>" , 1, idEmpresa);
						}
						catch (Exception e) 
						{
							
						}
						
						
						return new DataDescDescripcion(respuesta.getDescripcion(), "PEDIDOS_YA");
					}
					
				
				}
				case "DAC":
				{
					json = gson.toJson(ship);
					salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/DACShipping?token="+token_API, json);
					UESEnvio ues = gson.fromJson(salida, UESEnvio.class);
					
					if(ues.getDescripcion().contains("http"))
					{
						
						System.out.println("\r\n\r\n\r\n\r\n");
						System.out.println("Etiqueta DAC="+ues.getDescripcion());
						System.out.println("\r\n\r\n\r\n\r\n");
						
						return new DataDescDescripcion(ues.getId()+"", ues.getDescripcion());
					}
					else
					{
						
						System.out.println(ues.getId()+""+ ues.getDescripcion());
						return new DataDescDescripcion("","");
					}
				
				}
				case "UES":
				{
					c.setIdCliente(env.getUsuario());
					c.setPass(env.getPass());
					c.setPrestador("FORUS");
					c.setTipoEnvio(env.getTipoEnvio());
					c.setToken(env.getPass());
					c.setUser(env.getUsuario());
					c.setPedido("encuentra_"+pedido+"");
					c.setFecha("");
					c.setHora("");
					ship.setCredenciales(c);
									
					json = gson.toJson(ship);
					
					int intentos = 3;
					while (intentos>0) 
					{
						salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/UESShipping?token="+token_API, json);
						if(salida.contains("Barrio no encontrado"))
						{
							ship.getCliente().setLocalidad("Otro");
							json = gson.toJson(ship);
							salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/UESShipping?token="+token_API, json);
						}
						if(salida.contains("Etiqueta no generada"))
						{
							intentos--;
							salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/UESShipping?token="+token_API, json);
							if(!salida.contains("Etiqueta no generada"))
							{
								intentos =0;
							}
						}
						else
						{
							
							intentos=0;
						}
						
					}
					
					
					UESEnvio ues = gson.fromJson(salida, UESEnvio.class);
					System.out.println(ues.getDescripcion());
					
					if(ues.getDescripcion().contains("http"))
					{
						
						System.out.println("\r\n\r\n\r\n\r\n");
						System.out.println("Etiqueta UES="+ues.getDescripcion());
						System.out.println("\r\n\r\n\r\n\r\n");
						
						return new DataDescDescripcion(ues.getId()+"", ues.getDescripcion());
					}
					else
					{
						
						System.out.println(ues.getId()+""+ ues.getDescripcion());
						return new DataDescDescripcion("","");
					}
				}
				case "DISTRICAD":
				{
					c.setIdCliente("2576");
					c.setPass("pr56!!mntv345");
					c.setPrestador("FORUS");
					c.setTipoEnvio("EXPRESS");
					c.setToken("PbkF0XZX2UeBHw7agWvuow==");
					c.setUser("INTERFASEFORUS");
					c.setPedido("");
					c.setFecha(fecha);
					c.setHora(hora);
					
					ship.setCredenciales(c);
					
					json = gson.toJson(ship);
					salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/DistricadShipping?token="+token_API, json);
					DISTRIaltaEnvio distr = gson.fromJson(salida, DISTRIaltaEnvio.class);
					url = distr.getEtiqueta();
					track = distr.getSrvTrkNbr();
					break;	
				}
				case "MIRTRANS":
				{
						c.setIdCliente("");
						c.setPass(env.getPass());
						c.setPrestador("FORUS");
						c.setTipoEnvio("C");
						c.setToken("");
						c.setUser(env.getUsuario());
						c.setPedido(pedido+"");
						c.setFecha("");
						c.setHora("");
						ship.setCredenciales(c);
						
						json = gson.toJson(ship);
						
						
						salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/MirtransShipping?token="+token_API, json);
						
						RespuestaMIR mir = gson.fromJson(salida, RespuestaMIR.class);
						if(mir.getEtiqueta().contains("http"))
						{
							System.out.println("\r\n\r\n\r\n\r\n");
							System.out.println("Etiqueta MIRTRANS="+mir.getEtiqueta());
							System.out.println("\r\n\r\n\r\n\r\n");
							return new DataDescDescripcion(mir.getCodigoBarra(), mir.getEtiqueta());
						}
						else
						{
							if(mir.getEtiqueta().contains("Clave externa ya uilizada")) {
								c.setPedido(pedido+"_2");
								ship.setCredenciales(c);
								json = gson.toJson(ship);								
								salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/MirtransShipping?token="+token_API, json);
								mir = gson.fromJson(salida, RespuestaMIR.class);
								if(mir.getEtiqueta().contains("http"))
								{
									System.out.println("\r\n\r\n\r\n\r\n");
									System.out.println("Etiqueta MIRTRANS="+mir.getEtiqueta());
									System.out.println("\r\n\r\n\r\n\r\n");
									return new DataDescDescripcion(mir.getCodigoBarra(), mir.getEtiqueta());
								}
								else
								{
									System.out.println(mir.getCodigoBarra()+" "+ mir.getEtiqueta());
									return new DataDescDescripcion("","");
								}
							}else {
								System.out.println(mir.getCodigoBarra()+" "+ mir.getEtiqueta());
								return new DataDescDescripcion("","");
							}
							
						}
						
				}
				case "SOYDELIVERY":
				{
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
					c.setIdCliente(env.getUsuario());
					c.setPass(env.getPass());
					c.setPrestador("FORUS");
					c.setIdCliente(env.getIdTienda());
					c.setToken(env.getPass());
					c.setUser(env.getUsuario());
					c.setPedido(pedido+"");
					c.setFecha(env.getFechaEntrega());
					c.setHora("");
				
					ship.setCredenciales(c);
									
					json = gson.toJson(ship);
					
					salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/SDLShipping?token="+token_API, json);
					DataDescDescripcion despacho = gson.fromJson(salida, DataDescDescripcion.class);
					System.out.println(despacho);
					return despacho;						
				}
					
				default:
				break;
				
			}
		} catch (Exception e) 
		{
			return new DataDescDescripcion("","");
		}
		
		return new DataDescDescripcion("","");
	}
	
	public DataIDDescripcion getTracking(trackingPedido t,int idEmpresa) {


		String host = apiHost;
		
		Logica log = new Logica();
		String token_API=log.darParametroEmpresa(idEmpresa,10);
		
		DataIDDescripcion tracking = new DataIDDescripcion(t.getIdPedido(),"");
		try {
			Credenciales c = new Credenciales();
			Gson gson = new Gson();
			String json="";
			String salida = "";			
			
			switch (t.getCourier()) 
			{
				case "DAC":
				{
					break;
				}
				/*case "UES":
				{
					c.setUser(t.getUser());
					c.setPass(t.getPass());
					c.setToken(t.getPass());
					c.setPedido(t.getIdEcommerce()+"");
					c.setTracking(t.getTracking());
					
					json = gson.toJson(c);
					if(json.contains("\\u0026")) {
						json = json.replace("\\u0026", "&");
					}
					
					salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/getTrackingUES?token="+token_API, json);
					trackingUES out = gson.fromJson(salida, trackingUES.class);
					tracking.setDescripcion(out.getEstado());
					try {
						String fecha = "";
						String dia = out.getFecha().split("/")[0];
						String mes = out.getFecha().split("/")[1];
						String anio = out.getFecha().split("/")[2].split(" ")[0];
						
						int hora = Integer.parseInt(out.getFecha().split(" ")[1].split(":")[0]);
						String min = out.getFecha().split(" ")[1].split(":")[1];
						String am_pm = out.getFecha().split(" ")[2];
						
						if (am_pm.equals("pm") && hora!= 12) {
							hora += 12;
						}
						
						String horaStr = hora+"";
						if(hora < 10) {
							horaStr = "0"+hora;
						}
						fecha = anio+"-"+mes+"-"+dia+" "+hora+":"+min+":00";
						tracking.setDescripcionB(fecha);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				}*/
				case "MIRTRANS":
				{
					c.setUser(t.getUser());
					c.setPass(t.getPass());
					c.setToken(t.getPass());
					c.setPedido(t.getIdEcommerce()+"");
					c.setTracking(t.getTracking());
					
					json = gson.toJson(c);
					salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/getTrackingMirtrans?token="+token_API, json);
					trackingMIRTRANS out = gson.fromJson(salida, trackingMIRTRANS.class);
					tracking.setDescripcion(out.getEstadoDescripcion());
					tracking.setDescripcionB(out.getFecha());
					break;
				}
				case "SOYDELIVERY":
				{
					c.setUser(t.getUser());
					c.setPass(t.getPass());
					c.setToken(t.getPass());
					c.setPedido(t.getIdEcommerce()+"");
					c.setTracking(t.getTracking());
					
					json = gson.toJson(c);
					salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/Couriers/getTrackingSDL?token="+token_API, json);
					trackingSDL out = gson.fromJson(salida, trackingSDL.class);
					tracking.setDescripcion(out.getPedido_estado_desc());
					tracking.setDescripcionB(out.getFecha_entrega()+" 00:00:00");
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tracking;
	}
	
	public DataDescDescripcion BajarPDF(String eti, String trkNmb){
		try {
						//PASO A PDF
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
	
	public void PutTracking(Long id, String url, String tracking, int courier, int idEmpresa)
	{
		try 
		{
			if(tracking.contains("{")) {
				tracking = tracking.split("\"sender_id\":")[1].split(",")[0];
			}
			Logica log = new Logica();		
			
			String token_API=log.darParametroEmpresa(idEmpresa,10);			
			
			String host = apiHost;
			String servicio = "http://"+host+"/encuentraAPI/Integraciones/another/putTracking?token="+token_API; 
			
			Order o = new Order();
			o.setId(id);
			o.setTracking(tracking);
			o.setCourier(courier);
			o.setUrlTracking("");
			
			Gson gson = new Gson();
			String json = gson.toJson(o);
			
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
			System.out.println(salida);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateOrder(Order order, int idEmpresa)
	{
		try 
		{
			
			Logica log = new Logica();		
			
			String tokenApi=log.darParametroEmpresa(idEmpresa,10);			
			
			String host = apiHost;
			String servicio = "http://"+host+"/encuentraAPI/Integraciones/another/updateOrder?token="+tokenApi; 
			
			Gson gson = new Gson();
			String json = gson.toJson(order);
			
			
			String salida =  callWSPOST_ParamJSON(servicio, json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void confirmarPickupPeYA(String tracking, int idEmpresa, CredencialesPEYA creds, Compras c, int canal)
	{
		try 
		{
			
			Logica log = new Logica();		
			String tokenApi=log.darParametroEmpresa(idEmpresa,10);		
			Gson gson = new Gson();
			Credenciales credenciales = new Credenciales(creds.getUsername(), creds.getPassword(), creds.getClient_secret(), creds.getClient_id(), "", "", "", "", "", "");
			String host = apiHost;
			String servicio="";
			String jsonR;
			String json = gson.toJson(credenciales);
			
			
			if(tracking.equals("PEDIDOS_YA"))//si viene esto es que no hay tracking
			{
				//obtenemos la ventana horaria
				System.out.println("http://"+host+"/encuentraAPI/Integraciones/another/getFenicioOrder?token="+tokenApi+"&orden="+c.getCompra().getId()+"&canal="+canal);
				String jsonEP = callWSGET("http://"+host+"/encuentraAPI/Integraciones/another/getFenicioOrder?token="+tokenApi+"&orden="+c.getCompra().getId()+"&canal="+canal,false);
				EncuentraPedido ep = gson.fromJson(jsonEP , EncuentraPedido.class);
				String horaD = "";
				String horaH = "";
				if(ep.getHorarioD()!=null && !ep.getHorarioD().equals(""))
				{
					horaD = ep.getHorarioD();
					horaH = ep.getHorarioH();
				}
				c.setHoraDesde(horaD);
				c.setHoraHasta(horaH);
				Call_WS_analoga ca = new Call_WS_analoga();
				
				Long idPedido = log.darIdPedidoIdFenicio(c.getCompra().getId(), idEmpresa);
				tracking = ca.reSetEtiquetas("", idPedido, canal, idEmpresa, "", false,c);
				
				
				
				servicio = "http://"+host+"/encuentraAPI/Integraciones/Couriers/PeYaConfirmShipping?token="+tokenApi+"&tracking="+tracking;
				
				jsonR = callWSPOST_ParamJSON(servicio, json);
				
				DataDescDescripcion retorno = gson.fromJson(jsonR, DataDescDescripcion.class);
				
				if(!retorno.getId().equals("OK"))
				{
				
					log.logPedido(idPedido, 0, 3, "ERROR AL CONFIRMAR DESPACHO"+retorno.getDescripcion(), -1, idEmpresa);
					
					Utilidades util = new Utilidades();
					String mailsD = util.darParametroEmpresaSTR(idEmpresa, 48);
					List<SendMail> mails = new ArrayList<>();
					SendMail sm = new SendMail("PY"+idPedido, mailsD, "ERROR AL GENERAR CONFIRMAR PEDIDOS YA", "Al pedido "+idPedido+" no se le pudo generar la confirmacion del despacho correspondiente. Se reintentara dentro de 5 minutos. ", "encuentra@200.com.uy");
					mails.add(sm);
					PutColaEnvioMails(mails,idEmpresa);
					
					EncuentraPedido p = new EncuentraPedido();
					p.setIdPedido(idPedido);
					p.updateShipping(300000,"",idEmpresa);
					Usuario u = new Usuario();
					log.updateEcommerceEstado(idPedido, 2, idEmpresa, u);
					
				}
				
				System.out.println(retorno.getDescripcion());
			}
			/*else
			{
				servicio = "http://"+host+"/encuentraAPI/Integraciones/Couriers/PeYaConfirmShipping?token="+token_API+"&tracking="+tracking;
				
				
				
				jsonR = callWSPOST_ParamJSON(servicio, json);
				
				DataDescDescripcion retorno = gson.fromJson(jsonR, DataDescDescripcion.class);
				
				if(!retorno.getId().equals("OK"))//si no me deja  intento generar un envio desde 0
				{
					//tengo que mandar el llamado de nuevo
					Call_WS_analoga ca = new Call_WS_analoga();
					tracking = ca.reSetEtiquetas("", 0L, canal, idEmpresa, "", false,c);
					servicio = "http://"+host+"/encuentraAPI/Integraciones/Couriers/PeYaConfirmShipping?token="+token_API+"&tracking="+tracking;
					
					jsonR = callWSPOST_ParamJSON(servicio, json);
					
					retorno = gson.fromJson(jsonR, DataDescDescripcion.class);
					
					System.out.println(retorno.getDescripcion());
				}
			}*/
			
			
			
			
			
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void CheckInOrder(Long order, int equipo, int idEmpresa)
	{
		try 
		{
			
			Logica log = new Logica();		
			
			String token_API=log.darParametroEmpresa(idEmpresa,10);			
			
			String host = apiHost;
			String servicio = "http://"+host+"/encuentraAPI/Integraciones/another/CheckInOrder?token="+token_API+"&order="+order+"&printer="+equipo; 
			
			Gson gson = new Gson();
						
			String salida =  callWSPOST_ParamJSON(servicio, "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DataIDDescripcion movStock(MovStock m,boolean resend, int idEmpresa){
		
		DataIDDescripcion data = new DataIDDescripcion(0,"");
		data.setDescripcionB("");
		String host =apiHost;

		
		Logica log = new Logica();
		String token_API=log.darParametroEmpresa(idEmpresa,10);
		
		
		try {
			Gson gson = new Gson();
			String json="";
			String salida = "";			
			
			json = gson.toJson(m);
			salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/ERPintegrations/stockMovement?token="+token_API+"&resend="+resend, json);
			data = gson.fromJson(salida, DataIDDescripcion.class);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	public List<MovStock> QueueMovStock(int idEmpresa){
		
		List<MovStock> m = null;
		String host =apiHost;
	
		
		Logica log = new Logica();
		String token_API=log.darParametroEmpresa(idEmpresa,10);
		
		
		try {
			Gson gson = new Gson();
			String salida = "";			
			
			salida = callWSGET("http://"+host+"/encuentraAPI/Integraciones/ApiEntities/queueStockMovement?token="+token_API,true);
			m = gson.fromJson(salida, ColeccionMovStock.class).getColeccion();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return m;
	}
	
	public MovStock darMovStock(int id, int idEmpresa){
		
		MovStock m = null;
		String host =apiHost;
		
		
		Logica log = new Logica();
		String token_API=log.darParametroEmpresa(idEmpresa, 10);
		
		
		try {
			Gson gson = new Gson();
			String salida = "";			
			
			salida = callWSGET("http://"+host+"/encuentraAPI/Integraciones/ApiEntities/queueStockMovement?idMov="+id+"&token="+token_API,true);
			m = gson.fromJson(salida, ColeccionMovStock.class).getColeccion().get(0);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return m;
	}
	
	public boolean confirmarTransferencia(MovStock m, int idEmpresa){
		
		boolean grabo = false;
		String host =apiHost;
		
		
		Logica log = new Logica();
		String token_API=log.darParametroEmpresa(idEmpresa,10);
		
		
		try {
			Gson gson = new Gson();
			String json="";
			String salida = "";			
			
			json = gson.toJson(m);
			salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/ERPintegrations/confirmTransf?token="+token_API, json);
			grabo = gson.fromJson(salida, boolean.class);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return grabo;
	}
	
	public boolean grabarRecepcion(GrabarRecepcion r, int idEmpresa){
		
		boolean grabo = false;
		String host =apiHost;
	
		Logica log = new Logica();
		String token_API=log.darParametroEmpresa(idEmpresa,10);
		
		
		try {
			Gson gson = new Gson();
			String json="";
			String salida = "";			
			
			json = gson.toJson(r);
			salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/ERPintegrations/grabarRecepcion?token="+token_API, json);
			grabo = gson.fromJson(salida, boolean.class);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return grabo;
	}
	
	public boolean grabarBarra(articuloBarra ab, int idEmpresa){
		
		boolean grabo = false;
		String host =apiHost;

		
		Logica log = new Logica();
		String token_API=log.darParametroEmpresa(idEmpresa,10);
		
		
		try {
			Gson gson = new Gson();
			String json="";
			String salida = "";			
			
			json = gson.toJson(ab);
			salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/ERPintegrations/grabarBarra?token="+token_API, json);
			grabo = gson.fromJson(salida, boolean.class);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return grabo;
	}
	
public void putColaCambioEstadoMarketPlace(List<jsonEstadoMP> lista, int idEmpresa){
		
		String host =apiHost;

		
		Logica log = new Logica();
		String token_API=log.darParametroEmpresa(idEmpresa,10);		
		
		try {
			Gson gson = new Gson();
			String json="";
			String salida = "";			
			
			json = gson.toJson(lista);
			salida = callWSPOST_ParamJSON("http://"+host+"/encuentraAPI/Integraciones/another/putCambioEstadoMarketPlace?token="+token_API, json);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
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
		  	
		  	System.out.println("Codigo: "+code);
		  	
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
	
	private String callWSGET(String URL,boolean auth)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		if(auth)
		{
			((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("produccion@200data.com", "200data2017"));
		}
	    
	    try 
		{
	    	HttpGet httpGetRequest = new HttpGet(URL);
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpGetRequest);
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
		          httpGetRequest.abort();
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
		return retorno;
		
	}
	
	public List<Remito> albaranesListosLaIsla(String idDeposito,String idDepositoO, boolean transito, int idEmpresa){
			
			List<Remito> remitos = new ArrayList<>();
			String host = apiHost;
			
			Logica log = new Logica();
			String token_API=  log.darParametroEmpresa(idEmpresa,10); //"wteK2Z0uOqFqZ5LaFclXcLMoLpqvTRjB"
			
			
			try {
				Gson gson = new Gson();
				String salida = "";			
				
				salida = callWSGET("http://"+host+"/encuentraAPI/Integraciones/ERPintegrations/albaranesListos?idDeposito="+idDeposito+"&idDepositoOrigen="
				+idDepositoO+"&transito="+transito+"&token="+token_API,false);
				remitos = gson.fromJson(salida, new TypeToken<List<Remito>>(){}.getType());
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			return remitos;
	}
	

	public Map<String, Double> getPrecios(String body, int idEmpresa) {
		String salida = "";
		Logica logica = new Logica();
		Gson gson = new Gson();
		try {
			String tokenAPI = logica.darParametroEmpresa(idEmpresa, 10);
			salida = callWSPOST_ParamJSON(apiHost+"encuentraAPI/Integraciones/Fenicio/preciosProductos?token="+tokenAPI, body);
			return gson.fromJson(salida, new TypeToken<HashMap<String, Double>>(){}.getType());
		}
		catch(Exception e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}
	
	public Productos[] getProductos(List<DataIDDescripcion> lista, int idEmpresa) {
		String salida = "";
		Logica logica = new Logica();
		Gson gson = new Gson();
		try {
			String tokenAPI=  logica.darParametroEmpresa(idEmpresa, 10);
			String body = gson.toJson(lista, new TypeToken<List<DataIDDescripcion>>(){}.getType());
			salida =  callWSPOST_ParamJSON(apiHost+"encuentraAPI/Integraciones/Fenicio/darProductos?token=" + tokenAPI, body);
			return gson.fromJson(salida, new TypeToken<Productos[]>(){}.getType());
		}
		catch(Exception e) {
			e.printStackTrace();
			return new Productos[0];
		}
		
	}
}

