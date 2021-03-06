package main.process_ecommerce;

import helper.PropertiesHelper;

import java.io.BufferedInputStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.net.ssl.SSLContext;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import logica.EnviaMail;
import logica.Logica;
import main.process_ecommerce.mercadoLibreObjects.MLPagos;
import main.process_ecommerce.mercadoLibreObjects.MLPedidos;
import main.process_ecommerce.mercadoLibreObjects.MLUitem;
import main.process_ecommerce.mercadoLibreObjects.Order_item;
import main.process_ecommerce.mercadoLibreObjects.Payment;
import main.process_ecommerce.mercadoLibreObjects.Pickup;
import main.process_ecommerce.mercadoLibreObjects.Result;
import main.process_ecommerce.mercadoLibreObjects.Shipping;
import main.process_ecommerce.mercadoLibreObjects.TokenData;
import persistencia.MSSQL;

import org.apache.http.HttpEntity;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import beans.Fecha;
import beans.Usuario;
import cliente_rest_Invoke.JSONReader;
import clientesVisual_Store.Std.clienteWSVS_new.Clientes;
import clientesVisual_Store.Std.clienteWSVS_new.OrdenVenta;
import clientesVisual_Store.Std.clienteWSVS_new.OrdenVentaLinea;
import clientesVisual_Store.Std.clienteWSVS_new.WSCommunicate;
import jsonObjects.*;
import dataTypes.DataDescDescripcion;
import dataTypes.DataEcommerce_canales_envio;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.EncuentraPedidoArticulo;
import eCommerce_jsonObjectsII.EncuentraPedidoArticuloReq;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Call_WS_meli 
{
	
	Hashtable<Integer, Integer> pickupsIDS;
	
	
	
	
	
	public Hashtable<Integer, Integer> getPickupsIDS() {
		return pickupsIDS;
	}



	public void setPickupsIDS(Hashtable<Integer, Integer> pickupsIDS) {
		this.pickupsIDS = pickupsIDS;
	}

	
	


	public Call_WS_meli() 
	{
		this.pickupsIDS = new Hashtable<>();
		
		pickupsIDS.put(13066762, 3);
		
		
	}



	public String getToken(String id, String secret)
	{
		//https://api.mercadolibre.com
		
		
		String URLbase = "https://api.mercadolibre.com";
		//String funcion = "/oauth/token?grant_type=client_credentials&client_id=5690238116767269&client_secret=R9VaoeTj8XKeo7MSi50wUTVXuTGSMXgk";
		String funcion = "/oauth/token?grant_type=client_credentials&client_id="+id+"&client_secret="+secret;
		String retorno = this.callWSPOST(URLbase, funcion);
		TokenData tokenData = JSONReader.readJsonMeliToken(retorno);
		return tokenData.getAccess_token();
	}
	
	public String getToken()
	{
		//https://api.mercadolibre.com
		
		
		String URLbase = "https://api.mercadolibre.com";
		String funcion = "/oauth/token?grant_type=client_credentials&client_id=5690238116767269&client_secret=R9VaoeTj8XKeo7MSi50wUTVXuTGSMXgk";
		
		String retorno = this.callWSPOST(URLbase, funcion);
		TokenData tokenData = JSONReader.readJsonMeliToken(retorno);
		return tokenData.getAccess_token();
	}
	
	
	
	public void setNoteToOrder(Long idOrder, String token, String texto, int idEmpresa)
	{
		String URLbase = "https://api.mercadolibre.com";
		String funcion = "/orders/"+idOrder+"/notes?access_token="+token;
		
		Fecha fecha = new Fecha();
		
		
		String jotason = "{\"note\": \""+texto+" "+fecha.darFechaAnio_Mes_Dia_hhmm()+"\"}";
		
		this.callWSPOSTParam(jotason,URLbase+funcion);	
		//this.sendPut(jotason,URLbase+funcion);
	}
	
	public DataIDDescripcion pdfEtiquetaML (String idShipping, String access_token, Long idPedido, int idEmpresa)
	{
		Logica Logica = new Logica();
		
		String urlShipping = "//shipments/"+idShipping+"?access_token="+access_token;
		String URLbase = "https://api.mercadolibre.com";
		String retorno = this.callWSGET(URLbase, urlShipping);
		//System.out.println(retorno);
		Shipping ship = JSONReader.readJsonShippingMeli(retorno);
		
		String shippingName = "";
		DataIDDescripcion data = new DataIDDescripcion();
		data.setDescripcion("");
		data.setDescripcionB("");
		
		if(ship!=null)
		{
			shippingName = ship.getShipping_option().getName();
			data.setDescripcionB(shippingName);
			
			String url = "http://api.mercadolibre.com/shipment_labels?shipment_ids="+idShipping+"&access_token="+access_token;
			
			String pathToZip = downloadZIP(url);
			String pathTopdf = "";
			
			if(!pathToZip.equals(""))
			{
				String zpl = "";
			    try
			    {
			    	ZipFile zipFile = new ZipFile(pathToZip);
				    Enumeration<? extends ZipEntry> entries = zipFile.entries();
			    	while(entries.hasMoreElements())
			    	{
				        ZipEntry entry = entries.nextElement();
				        if(entry.getName().contains("txt"))
				        {
				        	InputStream stream = zipFile.getInputStream(entry);
				        	String str = "";
				            StringBuffer buf = new StringBuffer();
				        	BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
				            if (stream != null) 
				            {                            
				                while ((str = reader.readLine()) != null) 
				                {    
				                    buf.append(str + "\n" );
				                }                
				            }
				            System.out.println(buf.toString());
				            zpl = buf.toString();
				        }
				    }
			    }
			    catch (Exception e)
			    {
			    	
			    }
			    
			    pathTopdf = downloadPDFZPL("http://api.labelary.com/v1/printers/8dpmm/labels/4x6/0/", zpl, idShipping);
			    try
			    {
			    	PropertiesHelper pH=new PropertiesHelper("paths");
					pH.loadProperties();
					String path = pH.getValue("HTTP_pdf");
					pathTopdf=path+"/"+idShipping+".pdf";
			    }
			    catch (Exception e)
			    {
			    	
			    }
			    
			    Logica.logPedido(idPedido, 0, 0, "pedido, con etiqueta: <a href=\""+pathTopdf+"\">Ver etiqueta</a>",0,idEmpresa);
			}
			else
			{
				Logica.logPedido(idPedido, 0, 0, "etiqueta NO encontrada",-1,idEmpresa);
			}
			
			data.setDescripcion(pathTopdf);
			
			return data;
			
		}
		else
		{
			Logica.logPedido(idPedido, 0, 0, "No se busc? etiqueta porque no est? en estado impreso",1,idEmpresa);
			return data;
		}
		
	}
	
	
	
	public List<String> pdfRemitoML (List<String>shipmentes, String access_token, Hashtable<String, String> shipedidos, int idEnvio)
	{
		
		List<String> retorno = new ArrayList<>();
		int pasada = 0;
		String pathTopdf = "";
		for (String idShipping : shipmentes) 
		{
			pasada++;
			String url = "http://api.mercadolibre.com/shipment_labels?shipment_ids="+idShipping+"&access_token="+access_token;
			String pathToZip = downloadZIP(url);
			
			try
			{
				PropertiesHelper pH=new PropertiesHelper("paths");
				pH.loadProperties();
				String path = pH.getValue("pdf");
				pathTopdf=path+"/remitoML"+idEnvio+"_"+pasada+".pdf";
			}
			catch (Exception e)
			{
				return retorno;
			}
			if(!pathToZip.equals(""))
			{
				try
			    {
			    	ZipFile zipFile = new ZipFile(pathToZip);
				    Enumeration<? extends ZipEntry> entries = zipFile.entries();
			    	while(entries.hasMoreElements())
			    	{
				        ZipEntry entry = entries.nextElement();
				        if(entry.getName().contains("pdf"))
				        {
				        	InputStream stream = zipFile.getInputStream(entry);
				        	
				            File file = new File(pathTopdf);
				            file.delete();
				           
				            
				            FileOutputStream fos = new FileOutputStream(new File(pathTopdf));
				 
				            int inByte;
				            while ((inByte = stream.read()) != -1) 
				            {
				                fos.write(inByte);
				            }
				 
				            stream.close();
				            fos.close();
				 
				            
				            System.out.println("File Download Completed!!!");
				        
				        
					        File f = new File(pathTopdf);
					        if(f.exists())
					        {
					        	try
					  		    {
					  		    	PropertiesHelper pH=new PropertiesHelper("paths");
					  				pH.loadProperties();
					  				String path = pH.getValue("HTTP_pdf");
					  				pathTopdf=path+"/remitoML"+idEnvio+"_"+pasada+".pdf";
					  				retorno.add(pathTopdf);
					  				//return pathTopdf;
					  		    }
					  		    catch (Exception e)
					  		    {
					  		    	//return "";
					  		    	pathTopdf="";
					  		    }
					        }
					        else
					        {
					        	//return "";
					        	pathTopdf="";
					        }
				            
				            
				        }
				    }
			    }
			    catch (Exception e)
			    {
			    	//return "";
			    	pathTopdf="";
			    }
			    
			}//IF
			else
			{
				String [] enviosDeaUno = idShipping.split(",");
				int pasada2 = 0;
				for (String shipp : enviosDeaUno) 
				{
					pasada2++;
					String url2 = "http://api.mercadolibre.com/shipment_labels?shipment_ids="+shipp+"&access_token="+access_token;
					String pathToZip2 = downloadZIP(url2);
					
					
					if(!pathToZip2.equals(""))
					{
						
						try
						{
							PropertiesHelper pH=new PropertiesHelper("paths");
							pH.loadProperties();
							String path = pH.getValue("pdf");
							pathTopdf=path+"/remitoML"+idEnvio+"_"+pasada2+".pdf";
						}
						catch (Exception e)
						{
							//return retorno;
						}
						try
					    {
					    	ZipFile zipFile = new ZipFile(pathToZip2);
						    Enumeration<? extends ZipEntry> entries = zipFile.entries();
					    	while(entries.hasMoreElements())
					    	{
						        ZipEntry entry = entries.nextElement();
						        if(entry.getName().contains("pdf"))
						        {
						        	InputStream stream = zipFile.getInputStream(entry);
						        	
						            File file = new File(pathTopdf);
						            file.delete();
						           
						            
						            FileOutputStream fos = new FileOutputStream(new File(pathTopdf));
						 
						            int inByte;
						            while ((inByte = stream.read()) != -1) 
						            {
						                fos.write(inByte);
						            }
						 
						            stream.close();
						            fos.close();
						 
						            
						            System.out.println("File Download Completed!!!");
						        
						        
							        File f = new File(pathTopdf);
							        if(f.exists())
							        {
							        	try
							  		    {
							  		    	PropertiesHelper pH=new PropertiesHelper("paths");
							  				pH.loadProperties();
							  				String path = pH.getValue("HTTP_pdf");
							  				pathTopdf=path+"/remitoML"+idEnvio+"_"+pasada2+".pdf";
							  				retorno.add(pathTopdf);
							  				//return pathTopdf;
							  		    }
							  		    catch (Exception e)
							  		    {
							  		    	//return "";
							  		    	pathTopdf="";
							  		    }
							        }
							        else
							        {
							        	//return "";
							        	pathTopdf="";
							        }
						            
						            
						        }
						    }
					    }
					    catch (Exception e)
					    {
					    	//return "";
					    	pathTopdf="";
					    }
					    
					}//IF
					if(pathTopdf.equals(""))
					{
						retorno.add(pathTopdf);
					}
					
				}//foreach
			}//else
			
			if(pathTopdf.equals(""))
			{
				retorno.add(pathTopdf);
			}
			
		}//for
		
		
		return retorno;
	}//METHOD
	
	public List<JSONRespPedidos> getPedidosML(String access_token, int idCanal, String seller, int idEmpresa, Usuario u) 
	{
		Logica Logica = new Logica();
		
		int est=0;
		Long cantidadPasadas = new Long(1);
		Fecha fecha = new Fecha(-10, 0, 0);
		
		Fecha fechaActual = new Fecha();
		
		Logica.logger(0, 100, "consultando pedidos en Mercado Libre desde "+fecha.FechaMostrableSM(),idEmpresa);
		
		/*******************traigo los depositos para el pickup*********************/
		List<DataIDDescripcion>depositosPick = Logica.darListaDataIdDescripcionMYSQLConsulta("select idDeposito,nombre from ecommerce_envioml where idDeposito !=0 and idEmpresa="+idEmpresa+"");
		
		List<DataIDDescripcion> pedidosSincronizados = new ArrayList<>();
		
		List<DataIDDescripcion> pedidosMLSinc = Logica.darListaDataIdDescripcionMYSQLConsulta("select '-',URLetiqueta, idPedido from ecommerce_pedido where  idEmpresa="+idEmpresa+" AND ML =1 and DATEDIFF(CURRENT_TIMESTAMP(), stamptime)<10 union all select '-', '',idVenta from ecommerce_ml_exclusiones where  idEmpresa="+idEmpresa+"");
		pedidosMLSinc.remove(0);
		
		Hashtable<Long, String> pedidosIn =new Hashtable<>();
		for (DataIDDescripcion d : pedidosMLSinc) 
		{
			pedidosIn.put(d.getIdLong(), d.getDescripcion());
		}
		depositosPick.remove(0);
		Hashtable<String, Integer> depositosPickHT = new Hashtable<>();
		for (DataIDDescripcion d : depositosPick) 
		{
			depositosPickHT.put(d.getDescripcion(), d.getId());
		}
		
		
		int error = 0;
		int offset=0;
		boolean pri = true;
		while (cantidadPasadas>=0) 
		{
			try
			{		
				String URLbase = "https://api.mercadolibre.com";				
				
				String funcion = "/orders/search?order.status=paid&seller="+seller+"&order.date_created.from="+fecha.darFechaAnio_Mes_Dia()+"T00:00:00.000-00:00&limit=50&offset="+offset+"&access_token="+access_token;//+"&q=1859247654";
				String retorno = this.callWSGET(URLbase, funcion);
				//System.out.println(retorno);
				MLPedidos pedidosML = JSONReader.readJsonMeli(retorno);
		        if(pri)
				{
					pri=false;
					cantidadPasadas = (pedidosML.getPaging().getTotal())/50;
				}
		        
				for (Result pml : pedidosML.getResults()) 
				{
					System.out.println(pedidosIn.get(pml.getId()));					
					String envio="";
					if(pml.getId()== new Long("2601758113"))
					{
						System.out.println("caso");
					}
					
					if(pedidosIn.get(pml.getId())==null)
					{ //es un pedido nuevo					
						
						System.out.println("sincronizando vta "+pml.getId());
						Logica.logPedido(pml.getId(), 0, 0, "comienzo sinconizacion",0,idEmpresa);
						
						Logica.logger(0, 100, "desempaquetando pedido "+pml.getId(),idEmpresa);
												
						//"payment_method_id": "redpagos", 
						//  ??????????????????????
						
						/*Long idPayment =  pml.getPayments().get(0).getId();
												
						funcion = "/collections/"+idPayment+"?access_token="+access_token;
						retorno = this.callWSGET(URLbase, funcion);
						MLPagos pagos = JSONReader.readJsonPagoMeli(retorno);*/
						
						String idPayment="";
						MLPagos pagos = new MLPagos();
						Long longo = new Long("0");
						for(Payment p:pml.getPayments()){
							if(p.getDate_approved()!=null){
								idPayment += p.getId()+" "+p.getDate_approved().substring(0, 10)+",";
								funcion = "/collections/"+p.getId()+"?access_token="+access_token;
								retorno = this.callWSGET(URLbase, funcion);
								pagos = JSONReader.readJsonPagoMeli(retorno);
								longo += new Long(pagos.getTransaction_amount());
							}							
						}
						idPayment = idPayment.substring(0,idPayment.length());
						
						
						EncuentraPedido p = new EncuentraPedido();
						p.setIdPedido(pml.getId());
						
						
						if(p.getIdPedido()<0){
							System.out.println(pml.getId());
							System.out.println(p.getIdPedido());
						}
						/*************le seteo el canal*************/
						p.setCanalMercadoLibre(new DataIDDescripcion(idCanal, ""));
						
						
						Logica.logger(0, 100, "Leyendo Datos de Pago pedido "+pml.getId(),idEmpresa);
						
						int cantidad=0;
						Double descuento=0.0;
						
						p.setEstado("mercadoLibre");
						p.setUrlEtiqueta("");
						
						List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
						List<OrdenVentaLinea> ventaLineas = new ArrayList<>();
						
						Double importeTotal = 0.0; 
						
						p.setSucursalPick(""); 
						Double costoEnvio = 0.0;
						
						
						try{
							for(Payment pp:pml.getPayments()){
								costoEnvio = Double.parseDouble(String.valueOf(pp.getShipping_cost()));
							}
						}
						catch(Exception e){
							System.out.println("error costo de envio");
						}
						
						if(costoEnvio>0.0)
						{
							ventaLineas.add(new OrdenVentaLinea(costoEnvio, "1", "0002000"));
							
							Logica.logPedido(pml.getId(), 0, 0, "Agrgando costo de envio "+costoEnvio,0,idEmpresa);							
						}
						
						p.setDescripcion("MELI: "+ pml.getBuyer().getFirst_name()+" "+pml.getBuyer().getLast_name());
						
						for (Order_item it : pml.getOrder_items()) 
						{
							Double importe = it.getUnit_price();
							int cantidadItem = (int) it.getQuantity(); 
							importe = importe*cantidadItem;
							if(importe>0)
							{
								cantidad+=cantidadItem;
								EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
								art.setArticulo(it.getItem().getSeller_custom_field());
								art.setCantidad(cantidadItem);
								art.setProcesada(0);
								pedidos.add(art);
								importeTotal+=importe;
								try
								{
									OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", it.getItem().getSeller_custom_field());
									
									Logica.logPedido(pml.getId(), 0, 0, "Agregando linea articulo "+it.getItem().getSeller_custom_field(),0,idEmpresa);
									ventaLineas.add(ovl);
									
									//ME FIJO SI ES DE LA TIENDA CLARKS
									
									if(it.getItem().getSeller_custom_field().contains("061.")){
										funcion = "/items/"+it.getItem().getId();
										retorno = this.callWSGET(URLbase, funcion);
										MLUitem mlu = JSONReader.readJsonMLUitem(retorno);
										 if(mlu.getOfficial_store_id().equals("542")){
										 		p.setCanalMercadoLibre(new DataIDDescripcion(-2, ""));
										  }
									}
								}
								catch(Exception e)
								{
									try{
										art.setArticulo(it.getItem().getSeller_sku());
										
										OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", it.getItem().getSeller_sku());
										
										Logica.logPedido(pml.getId(), 0, 0, "Agregando linea articulo "+it.getItem().getSeller_sku(),0,idEmpresa);
										ventaLineas.add(ovl);
										
										//ME FIJO SI ES DE LA TIENDA CLARKS
										
										if(it.getItem().getSeller_sku().contains("061.")){
											funcion = "/items/"+it.getItem().getId();
											retorno = this.callWSGET(URLbase, funcion);
											MLUitem mlu = JSONReader.readJsonMLUitem(retorno);
											 if(mlu.getOfficial_store_id().equals("542")){
											 		p.setCanalMercadoLibre(new DataIDDescripcion(-2, ""));
											  }
										}
									}catch (Exception ex) {
										Logica.logPedido(pml.getId(), 0, 0, "ERROR Agregando linea articulo se agrega linea como SINARTICULO",-1,idEmpresa);
										OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", "SINARTICULO");
										ventaLineas.add(ovl);
										EnviaMail.enviarMail("MercadoLibre@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
										EnviaMail.enviarMail("sicardo@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
										EnviaMail.enviarMail("ndelgado@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
										EnviaMail.enviarMail("onviera@200.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
									}
									
								}
								
								
							}
							else
							{
								descuento+=importe;
							}
							
							
							
						}
						System.out.println("");
						p.setCantidad(cantidad);
						p.setDescuento(descuento);
						p.setArticulosPedido(pedidos);
						
						p.setArticulosPedidoReq(new ArrayList<>());
						p.setFormaPago(idPayment+" "+fechaActual.darFechaAnio_Mes_Dia());
						//p.setFormaPago(idPaymentForm);
						Double porcDescuento = (descuento*100)/importeTotal;
						
						
						if((porcDescuento*-1)>0)
						{
							for (OrdenVentaLinea ol : ventaLineas) 
							{
								if(!ol.getIdArticulo().equals("0002000"))
								{
									Double fullPrice = ol.getPrecioImp();
									Double discount = ((porcDescuento*-1)*fullPrice)/100;
									
									ol.setPrecioImp(fullPrice-discount);
								}
							}
						}
						if(p.save(idEmpresa))
						{
							try
							{						
								String idShipping = pml.getShipping().getId();
								DataIDDescripcion pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token,pml.getId(),idEmpresa);
								int num = depositosPickHT.get(pathEtiqueta.getDescripcionB());
								
								if(!pathEtiqueta.getDescripcionB().equals("")){
									envio+=pathEtiqueta.getDescripcionB();
								}
																
								if(!pathEtiqueta.getDescripcion().equals(""))
								{	
									p.setUrlEtiqueta(pathEtiqueta.getDescripcion());
									p.updateEtiqueta(0,idEmpresa);
									
								}
								
								try
								{
									p.updateShipping(num,null, idEmpresa);
								}
								catch(Exception e)
								{
									p.updateShipping(0,null,idEmpresa);
								}								
								
							}
							catch (Exception e)
							{								
								try
								{
									funcion = "/pickups/"+pml.getPickup_id()+"?access_token="+access_token;
									retorno = this.callWSGET(URLbase, funcion);
									
									Pickup pi = JSONReader.readJsonPickup(retorno);
									System.out.println(pi.getStore_info().getDescription()+"");
									int num = depositosPickHT.get(pi.getStore_info().getDescription());
									String sucursalPick = String.format("%02d", num);
									envio+="Pickup "+sucursalPick;
									p.setSucursalPick(sucursalPick);
									String personaRetira = pi.getPickup_person().getFull_name();
									p.setPersonaRetira(personaRetira);
									p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
									Logica.logPedido(pml.getId(), 0, 0, "Agregando Etiqueta de pickup "+sucursalPick,0,idEmpresa);
									p.updateEtiqueta(0,idEmpresa);
									p.updateDestino(num,"",idEmpresa,0.0,false);
									
									try
									{
										p.updateShipping(num,null,idEmpresa);
									}
									catch(Exception e12)
									{
										p.updateShipping(0,null,idEmpresa);
									}
								}
								catch (Exception e2)
								{
									Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1,idEmpresa);
								}
							}
							
							pedidosSincronizados.add(new DataIDDescripcion(p.getIdPedido(), p.getDescripcion()));
							p.updateML(idEmpresa);
							
							
							setNoteToOrder(pml.getId(),access_token, "Sincronizado por encuentra ",idEmpresa);
							
							
							Logica.logPedido(pml.getId(), 0, 0, "Agregando nota de sincronizacion en ML ",0,idEmpresa);
							
							
							System.out.println("Pedido "+pml.getId()+" gurdado");
							Logica.logPedido(pml.getId(), 0, 0, "Pedido Sincronizado ",0,idEmpresa);
							WSCommunicate ws = new WSCommunicate();
							
							Clientes cli = darCliente(pml,pagos);
							
							ws.grabarCliente(cli,idEmpresa);
							
							
							String curr = pml.getCurrency_id();
							String moneda = "";
							if(curr.equals("UYU"))
							{
								moneda = "$";
							}
							else
							{
								moneda="USD";
							}
							
							Logica.logPedido(pml.getId(), 0, 0, "grabando el cliente "+cli.toString(),0,idEmpresa);
							
							
							Double importePago = longo.doubleValue() + costoEnvio;
							
							
							OrdenVenta orden = new OrdenVenta(Integer.parseInt(cli.getNumero()), porcDescuento*-1, moneda, cli.getNumero()+"", p.getDescripcion(), cli.getCalle(), "", cli.getTelefono(), ventaLineas,pml.getId(),2003, ""+idPayment+" "+fechaActual.darFechaAnio_Mes_Dia(),1,importePago);
							orden.setCliMail("");
							
							Logica.logPedido(pml.getId(), 0, 0, "grabando Orden para importar "+orden.toString(),0,idEmpresa);
							orden.setComentario("ENVIO:"+envio);
							orden.setFormaPagoVisual("MP");
							orden.save(p.getUrlEtiqueta(),idEmpresa);
							
							///////////////////GENERO LA ORDEN
							try {
								Logica.procesarOrdenPedido(p.getIdPedido(),idEmpresa);
								
								////////////////////////////VOY A BUSCAR LA ORDEN Y LA FACTURA//////////////////////////////
								DataIDIDDescripcion factVenta = MSSQL.darIdOrdenVenta(p.getIdPedido()+"").get(0);
								String urlFact ="";
								Logica.altaOrdenPedido(p.getIdPedido(), factVenta.getId(), factVenta.getIid(),urlFact,idEmpresa);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
						
						
					}//if
					else
					{//el pedido ya est?, debo actualizar los datos el envio
						
						
						
						//consulto si el pedido fue cancelado manualmente
						boolean cancelado = Logica.hayRegistro("select * from ecommerce_pedido where idPedido="+pml.getId()+" and cancelado=1 and estadoencuentra=99");
						
						if(!cancelado){
						
						//consulto si se modifico el destino manualmente
						boolean etiquetaManual = Logica.hayRegistro("select * from ecommerce_pedido_destino where idPedido="+pml.getId()+" and manual=1");
						
						if (!etiquetaManual)
						{
							

							if(pml.getId()==1939633665){
								System.out.println("entro");
							}
						
						//el pedido ya est?, debo actualizar los datos el envio
						Logica.logPedido(pml.getId(), 0, 0, "comprobando si hay etiqueta del pedido",0,idEmpresa);
						try
						{
							EncuentraPedido p = new EncuentraPedido();
							p.setIdPedido(pml.getId());
							String et = pedidosIn.get(pml.getId());
							
							String idShipping = pml.getShipping().getId();
							DataIDDescripcion pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token,pml.getId(),idEmpresa);
							if(!pathEtiqueta.getDescripcion().equals("")){
								envio+=pathEtiqueta.getDescripcionB();
							}
							int num = depositosPickHT.get(pathEtiqueta.getDescripcionB());
							
							if(!pathEtiqueta.getDescripcion().equals(""))
							{	
								p.setUrlEtiqueta(pathEtiqueta.getDescripcion());
								p.updateEtiqueta(0,idEmpresa);
								
							}								
							try
							{
								p.updateShipping(num,null,idEmpresa);
							}
							catch(Exception e)
							{
								p.updateShipping(0,null,idEmpresa);
							}
							
							if(!et.contains("http"))
							{
								est=(Logica.darDataIdDescripcion("select EstadoEncuentra,'' from ecommerce_pedido where idEmpresa="+idEmpresa+" AND idpedido= "+p.getIdPedido())).getId();
								if(est==25){
									Logica.updateEcommerceEstado(p.getIdPedido(), 3,idEmpresa, u);
								}								
							}
													
						}
						catch (Exception e)
						{
							
							try
							{
								EncuentraPedido p = new EncuentraPedido();
								p.setIdPedido(pml.getId());
								funcion = "/pickups/"+pml.getPickup_id()+"?access_token="+access_token;
								if((Integer)pml.getPickup_id()!=null)
								{	
									retorno = this.callWSGET(URLbase, funcion);
									Pickup pi = JSONReader.readJsonPickup(retorno);
									System.out.println(pi.getStore_info().getDescription()+"");
									int num = depositosPickHT.get(pi.getStore_info().getDescription());
									String sucursalPick = String.format("%02d", num);
									envio+="Pickup: "+sucursalPick;
									p.setSucursalPick(sucursalPick);
									String personaRetira = pi.getPickup_person().getFull_name();
									p.setPersonaRetira(personaRetira);
									p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
									Logica.logPedido(pml.getId(), 0, 0, "Agregando Etiqueta de pickup "+sucursalPick,0,idEmpresa);
									p.updateEtiqueta(0,idEmpresa);
									p.updateDestino(num,"",idEmpresa,0.0,false);
									
									//////////
									est=(Logica.darDataIdDescripcion("select EstadoEncuentra,'' from ecommerce_pedido where idpedido= "+p.getIdPedido())).getId();
									if(est==25){
										Logica.updateEcommerceEstado(p.getIdPedido(), 3,idEmpresa, u);
									}
								}
								else
								{
									Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1,idEmpresa);
								}
								
								
							}
							catch (Exception e2)
							{
								Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1,idEmpresa);
							}
							
						}
						
						}//if etiqueta manual
						
					}//if cancelado
						
					}//if de pedido existente
				}//for
				
				cantidadPasadas--;
				
				offset+=50;
			}
			catch(Exception e)
			{
				error++;
				if(error==100)
				{
					cantidadPasadas=new Long(-1);
				}
			}
		}//while
		
		EnviaMail em = new EnviaMail();
		em.enviarMailLogEcommerce(pedidosSincronizados);
		//EcommerceProcessOrders.process(null,0,null,0);
		return null;
		
	}
	
	
	
	private Clientes darCliente(Result pml, MLPagos pagos) 
	{
		String apellido = pml.getBuyer().getLast_name();
		String nombre = pml.getBuyer().getFirst_name();
		
		String departamento = "";
		try
		{
			departamento= pml.getShipping().getReceiver_address().getState().getName();
		}
		catch(Exception e){}
		String localidad ="";
		try
		{
			localidad=pml.getShipping().getReceiver_address().getCity().getName();
		}
		catch(Exception e){}
		String calle = "";
		try
		{
			calle=pml.getShipping().getReceiver_address().getAddress_line();
		}
		catch(Exception e){}
		String email = "";
		
		if((String)pagos.getPayer().getEmail()!=null || pagos.getPayer().getIdentification().getNumber()!=null)//!pagos.getPayer().getIdentification().getNumber().equals("null"))
		{
			email = (String) pagos.getPayer().getEmail();
			if(email==null)
			{
				email="";
			}
		}
		else
		{
			email = pml.getBuyer().getEmail();
		}
		
		String telefono = "";
		String nroPuerta = "";
		String nroApto = "";

		if(pml.getBuyer().getPhone()!=null)
		{
			telefono = pml.getBuyer().getPhone().getNumber();
		}
	
		
		//String documentoTipo = "ci";
		//String documentoNro = "";
		String documentoTipo = pml.getBuyer().getBilling_info().getDoc_type().toString();
		String documentoNro = pml.getBuyer().getBilling_info().getDoc_number().toString();
		
		if(documentoNro==null ||documentoNro==""){
			if(pagos.getPayer().getIdentification().getNumber()==null)
			{
				documentoTipo = "MLID";
				documentoNro = String.valueOf(pml.getBuyer().getId());
			}
			else
			{
				documentoNro = String.valueOf(pagos.getPayer().getIdentification().getNumber());
			}
		}
		
		
		return new Clientes(apellido, nombre, localidad, email, departamento, telefono, nroPuerta, nroApto, documentoTipo, calle, documentoNro);
	}



	public List<JSONRespPedidos> getPedidosMLOV(String access_token, String idPedido,int idCanal, String seller, int idEmpresa, Usuario u) 
	{
		Logica Logica = new Logica();
		int est=0;
		Long cantidadPasadas = new Long(1);
		Fecha fecha = new Fecha(-30, 0, 0);
		
		Logica.logger(0, 100, "consultando pedidos en Mercado Libre desde "+fecha.FechaMostrableSM(),idEmpresa);
		
		
		
		/*******************traigo los depositos para el pickup*********************/
		List<DataIDDescripcion>depositosPick = Logica.darListaDataIdDescripcionMYSQLConsulta("select idDeposito,nombre from ecommerce_envioml where idDeposito !=0");
		
		List<DataIDDescripcion> pedidosSincronizados = new ArrayList<>();
		
		List<DataIDDescripcion> pedidosMLSinc = Logica.darListaDataIdDescripcionMYSQLConsulta("select '-',URLetiqueta,idPedido from ecommerce_pedido where ML =1 and DATEDIFF(CURRENT_TIMESTAMP(), stamptime)<15 union all select '-', '',idVenta from ecommerce_ml_exclusiones");
		pedidosMLSinc.remove(0);
		
		Hashtable<Long, String> pedidosIn =new Hashtable<>();
		for (DataIDDescripcion d : pedidosMLSinc) 
		{
			pedidosIn.put(new Long(d.getId()), d.getDescripcion());
		}
		depositosPick.remove(0);
		Hashtable<String, Integer> depositosPickHT = new Hashtable<>();
		for (DataIDDescripcion d : depositosPick) 
		{
			depositosPickHT.put(d.getDescripcion(), d.getId());
		}
		
		
		int error = 0;
		int offset=0;
		boolean pri = true;
		while (cantidadPasadas>=0) 
		{	
			try
			{
				
			
				String URLbase = "https://api.mercadolibre.com";
				
				
				String funcion = "/orders/search?seller="+seller+"&order.date_created.from="+fecha.darFechaAnio_Mes_Dia()+"T00:00:00.000-00:00&limit=50&offset="+offset+"&q="+idPedido+"&access_token="+access_token;
				String retorno = this.callWSGET(URLbase, funcion);
				//System.out.println(retorno);
				MLPedidos pedidosML = JSONReader.readJsonMeli(retorno);
		        
		        if(pri)
				{
					pri=false;
					cantidadPasadas = (pedidosML.getPaging().getTotal())/50;
				}
		        
				for (Result pml : pedidosML.getResults()) 
				{
					boolean cancelado = Logica.hayRegistro("select * from ecommerce_pedido where idPedido="+pml.getId()+" and cancelado=1 and estadoencuentra=99");
					
					if(!cancelado){
					
					System.out.println("sincronizando vta "+pml.getId());
					Logica.logPedido(pml.getId(), 0, 0, "comienzo sinconizacion",0,idEmpresa);
						
						Logica.logger(0, 100, "desempaquetando pedido "+pml.getId(),idEmpresa);
						
						Long idPayment =  pml.getPayments().get(0).getId();
						//String idPaymentForm = pml.getPayments().get(0).getPayment_method_id();	
						
						funcion = "/collections/"+idPayment+"?access_token="+access_token;
						retorno = this.callWSGET(URLbase, funcion);
						MLPagos pagos = JSONReader.readJsonPagoMeli(retorno);
						
						
						EncuentraPedido p = new EncuentraPedido();
						p.setIdPedido(pml.getId());
						
						/*************le seteo el canal*************/
						p.setCanalMercadoLibre(new DataIDDescripcion(idCanal, ""));
						
						
						Logica.logger(0, 100, "Leyendo Datos de Pago pedido "+pml.getId(),idEmpresa);
						
						int cantidad=0;
						Double descuento=0.0;
						
						p.setEstado("mercadoLibre");
						p.setUrlEtiqueta("");
						
						List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
						List<OrdenVentaLinea> ventaLineas = new ArrayList<>();
						
						Double importeTotal = 0.0; 
						
						p.setSucursalPick(""); 
						Double costoEnvio = 0.0;
						String envio="";
						
						try{
							for(Payment pp:pml.getPayments()){
								costoEnvio += Double.parseDouble(String.valueOf(pp.getShipping_cost()));
							}
						}
						catch(Exception e){
							System.out.println("error costo de envio");
						}
						
						try
						{
							String idShipping = pml.getShipping().getId();
							DataIDDescripcion pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token,pml.getId(),idEmpresa);
							int num = depositosPickHT.get(pathEtiqueta.getDescripcionB());
							if(!pathEtiqueta.getDescripcionB().equals("")){
								envio+=pathEtiqueta.getDescripcionB();
							}
															
							if(!pathEtiqueta.getDescripcion().equals(""))
							{	
								p.setUrlEtiqueta(pathEtiqueta.getDescripcion());
								p.updateEtiqueta(0,idEmpresa);
								
							}
							
							try
							{
								p.updateShipping(num,null,idEmpresa);
							}
							catch(Exception e)
							{
								p.updateShipping(0,null,idEmpresa);
							}
							
						}
						catch (Exception e)
						{
							
							try
							{
								funcion = "/pickups/"+pml.getPickup_id()+"?access_token="+access_token;
								retorno = this.callWSGET(URLbase, funcion);
								
								Pickup pi = JSONReader.readJsonPickup(retorno);
								System.out.println(pi.getStore_info().getDescription()+"");
								int num = depositosPickHT.get(pi.getStore_info().getDescription());
								String sucursalPick = String.format("%02d", num);
								envio+="Pickup: "+sucursalPick;
								p.setSucursalPick(sucursalPick);
								String personaRetira = pi.getPickup_person().getFull_name();
								p.setPersonaRetira(personaRetira);
								p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
								Logica.logPedido(pml.getId(), 0, 0, "Agregando Etiqueta de pickup "+sucursalPick,0,idEmpresa);
								p.updateEtiqueta(0,idEmpresa);
								p.updateDestino(num,"",idEmpresa,0.0,false);
								
								/////////////
								est=(Logica.darDataIdDescripcion("select EstadoEncuentra,'' from ecommerce_pedido where idpedido= "+p.getIdPedido())).getId();
								if(est==25){
									Logica.updateEcommerceEstado(p.getIdPedido(), 3,idEmpresa, u);
								}
							}
							catch (Exception e2)
							{
								Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1,idEmpresa);
							}
						}
									
						if(costoEnvio>0.0)
						{
							ventaLineas.add(new OrdenVentaLinea(costoEnvio, "1", "0002000"));
							
							Logica.logPedido(pml.getId(), 0, 0, "Agrgando costo de envio "+costoEnvio,0,idEmpresa);						
						}
						
						p.setDescripcion("MELI: "+ pml.getBuyer().getFirst_name()+" "+pml.getBuyer().getLast_name()+" ENVIO: "+envio);
						
						for (Order_item it : pml.getOrder_items()) 
						{
							Double importe = it.getUnit_price();
							int cantidadItem = (int) it.getQuantity(); 
							importe=importe*cantidadItem;
							if(importe>0)
							{
								cantidad+=cantidadItem;
								EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
								art.setArticulo(it.getItem().getSeller_custom_field());
								art.setCantidad(cantidadItem);
								art.setProcesada(0);
								pedidos.add(art);
								importeTotal+=importe;
								try
								{
									OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", it.getItem().getSeller_custom_field());
									
									Logica.logPedido(pml.getId(), 0, 0, "Agregando linea articulo "+it.getItem().getSeller_custom_field(),0,idEmpresa);
									ventaLineas.add(ovl);
									
									//ME FIJO SI ES DE LA TIENDA CLARKS
									
									if(it.getItem().getSeller_custom_field().contains("061.")){
										funcion = "/items/"+it.getItem().getId();
										retorno = this.callWSGET(URLbase, funcion);
										MLUitem mlu = JSONReader.readJsonMLUitem(retorno);
										 if(mlu.getOfficial_store_id().equals("542")){
										 		p.setCanalMercadoLibre(new DataIDDescripcion(-2, ""));
										  }
									}
								}
								catch(Exception e)
								{
									try{
										art.setArticulo(it.getItem().getSeller_sku());
										
										OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", it.getItem().getSeller_sku());
										
										Logica.logPedido(pml.getId(), 0, 0, "Agregando linea articulo "+it.getItem().getSeller_sku(),0,idEmpresa);
										ventaLineas.add(ovl);
										
										//ME FIJO SI ES DE LA TIENDA CLARKS
										
										if(it.getItem().getSeller_sku().contains("061.")){
											funcion = "/items/"+it.getItem().getId();
											retorno = this.callWSGET(URLbase, funcion);
											MLUitem mlu = JSONReader.readJsonMLUitem(retorno);
											 if(mlu.getOfficial_store_id().equals("542")){
											 		p.setCanalMercadoLibre(new DataIDDescripcion(-2, ""));
											  }
										}
									}catch (Exception ex) {
										Logica.logPedido(pml.getId(), 0, 0, "ERROR Agregando linea articulo se agrega linea como SINARTICULO",-1,idEmpresa);
										OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", "SINARTICULO");
										ventaLineas.add(ovl);
										EnviaMail.enviarMail("MercadoLibre@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
										EnviaMail.enviarMail("sicardo@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
										EnviaMail.enviarMail("ndelgado@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
										EnviaMail.enviarMail("onviera@200.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
									}
								}
								
								
							}
							else
							{
								descuento+=importe;
							}
							
							
							
						}
						System.out.println("");
						p.setCantidad(cantidad);
						p.setDescuento(descuento);
						p.setArticulosPedido(pedidos);
						Fecha fechaActual = new Fecha();
						
						p.setArticulosPedidoReq(new ArrayList<>());
						p.setFormaPago(""+idPayment+" "+fechaActual.darFechaAnio_Mes_Dia());
						//p.setFormaPago(idPaymentForm);
						Double porcDescuento = (descuento*100)/importeTotal;
						
						
						if((porcDescuento*-1)>0)
						{
							for (OrdenVentaLinea ol : ventaLineas) 
							{
								if(!ol.getIdArticulo().equals("0002000"))
								{
									Double fullPrice = ol.getPrecioImp();
									Double discount = ((porcDescuento*-1)*fullPrice)/100;
									
									ol.setPrecioImp(fullPrice-discount);
								}
							}
						}
							pedidosSincronizados.add(new DataIDDescripcion(p.getIdPedido(), p.getDescripcion()));
							p.updateML(idEmpresa);
							setNoteToOrder(pml.getId(),access_token, "Sincronizado por encuentra ",idEmpresa);
							Logica.logPedido(pml.getId(), 0, 0, "Agregando nota de sincronizacion en ML ",0,idEmpresa);
							
							
							System.out.println("Pedido "+pml.getId()+" gurdado");
							Logica.logPedido(pml.getId(), 0, 0, "Pedido Sincronizado ",0,idEmpresa);
							WSCommunicate ws = new WSCommunicate();
							
							
							String apellido = pml.getBuyer().getLast_name();
							String nombre = pml.getBuyer().getFirst_name();
							
							String departamento = "";
							try
							{
								departamento= pml.getShipping().getReceiver_address().getState().getName();
							}
							catch(Exception e){}
							String localidad ="";
							
							try
							{
								localidad=pml.getShipping().getReceiver_address().getCity().getName();
							}
							catch(Exception e){}
							String calle = "";
							try
							{
								calle=pml.getShipping().getReceiver_address().getAddress_line();
							}
							catch(Exception e){}
							String email = "";
							
							try
							{
								if((String)pagos.getPayer().getEmail()!=null || pagos.getPayer().getIdentification().getNumber()!=null)
								{
									email = (String) pagos.getPayer().getEmail();
									if(email==null)
									{
										email="";
									}
								}
								else
								{
									email = pml.getBuyer().getEmail();
								}
							}
							catch(Exception e){}
							
							String telefono = "0";
							try {
								 telefono = pml.getBuyer().getPhone().getNumber();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							String nroPuerta = "";
							String nroApto = "";
							
							
							String documentoTipo = "ci";
							String documentoNro = "";
							try
							{
								if(pagos.getPayer().getIdentification().getNumber()==null)
								{
									documentoTipo = "MLID";
									documentoNro = String.valueOf(pml.getBuyer().getId());
								}
								else
								{
									documentoNro = String.valueOf(pagos.getPayer().getIdentification().getNumber());
								}
							}
							catch(Exception ex)
							{
								documentoTipo = "MLID";
								documentoNro = String.valueOf(pml.getBuyer().getId());
							}
							
							
							String curr = pml.getCurrency_id();
							String moneda = "";
							if(curr.equals("UYU"))
							{
								moneda = "$";
							}
							else
							{
								moneda="USD";
							}
							Clientes cli = new Clientes(apellido, nombre, localidad, email, departamento, telefono, nroPuerta, nroApto, documentoTipo, calle, documentoNro);
							ws.grabarCliente(cli,idEmpresa);
							Logica.logPedido(pml.getId(), 0, 0, "grabando el cliente "+cli.toString(),0,idEmpresa);
							
							Double importePago =0.0;
							try
							{
								Long longo = new Long(pagos.getTransaction_amount());
								
								importePago = longo.doubleValue() + costoEnvio;
							}
							catch(Exception e)
							{
								
							}
							
							
							
							OrdenVenta orden = new OrdenVenta(Integer.parseInt(cli.getNumero()), porcDescuento*-1, moneda, cli.getNumero()+"", p.getDescripcion(), calle, "", telefono, ventaLineas, pml.getId(),2003, ""+idPayment+" "+fechaActual.darFechaAnio_Mes_Dia(),1,importePago);
							
							orden.setCliMail("");
							
							Logica.logPedido(pml.getId(), 0, 0, "grabando Orden para importar "+orden.toString(),0,idEmpresa);
							orden.setComentario("ENVIO:"+envio);
							orden.setFormaPagoVisual("MP");
							orden.save(p.getUrlEtiqueta(),idEmpresa);
							
								///////////////////GENERO LA ORDEN
										try {
											Logica.procesarOrdenPedido(p.getIdPedido(),idEmpresa);
											
											////////////////////////////VOY A BUSCAR LA ORDEN Y LA FACTURA//////////////////////////////
											DataIDIDDescripcion factVenta = MSSQL.darIdOrdenVenta(p.getIdPedido()+"").get(0);
											String urlFact ="";
											Logica.altaOrdenPedido(p.getIdPedido(), factVenta.getId(), factVenta.getIid(),urlFact,idEmpresa);
										} catch (Exception e) {
											e.printStackTrace();
										}
							
						
				}
				}//for
				
				cantidadPasadas--;
				
				offset+=50;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				error++;
				if(error==100)
				{
					cantidadPasadas=new Long(-1);
				}
				break;
			}
		}//while
		
		EnviaMail em = new EnviaMail();
		em.enviarMailLogEcommerce(pedidosSincronizados);
		//EcommerceProcessOrders.process(null,0,0,0);
		
		return null;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public String callWSGET(String URLbase, String funcion)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		
	    
	    try 
		{
	    	HttpGet httpGetRequest = new HttpGet(URLbase+funcion);
	    	
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
	
	public String downloadZIP(String urlZip) 
	{
		try
		{
			PropertiesHelper pH=new PropertiesHelper("paths");
			pH.loadProperties();
			String path = pH.getValue("zip");
			
			Double ran = Math.random();
			
			String filePath = path+"/file"+ran+".zip";
	        try {
	            HttpClient client = new DefaultHttpClient();
	            HttpGet request = new HttpGet(urlZip);
	 
	            HttpResponse response = client.execute(request);
	            HttpEntity entity = response.getEntity();
	 
	            int responseCode = response.getStatusLine().getStatusCode();
	 
	            System.out.println("Request Url: " + request.getURI());
	            System.out.println("Response Code: " + responseCode);
	            if(responseCode==400)
	            {
	            	return "";
	            }
	 
	            InputStream is = entity.getContent();
	 
	            
	            
	            File file = new File(filePath);
	            file.delete();
	           
	            
	            FileOutputStream fos = new FileOutputStream(new File(filePath));
	 
	            int inByte;
	            while ((inByte = is.read()) != -1) {
	                fos.write(inByte);
	            }
	 
	            is.close();
	            fos.close();
	 
	            
	            System.out.println("File Download Completed!!!");
	            client.getConnectionManager().shutdown();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (UnsupportedOperationException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        File f = new File(filePath);
	        if(f.exists())
	        {
	        	return filePath;
	        }
	        else
	        {
	        	return "";
	        }
		}
		catch (Exception e)
		{
			return "";
			
		}
        	
       
    }
	
	
	public String downloadPDFZPL(String url, String parameter,String idShipping) 
	{
		try
		{
			PropertiesHelper pH=new PropertiesHelper("paths");
			pH.loadProperties();
			String path = pH.getValue("pdf");
			String filePath = path+"/"+idShipping+".pdf";
	        try {
	            HttpClient client = new DefaultHttpClient();
	            HttpPost request = new HttpPost(url);
	            
	            StringEntity params =new StringEntity(parameter);
	            request.setEntity(params);
		    	request.setHeader("Content-type", "application/x-www-form-urlencoded");
		    	request.addHeader("Accept", "application/pdf");
	            HttpResponse response = client.execute(request);
	            HttpEntity entity = response.getEntity();
	 
	            int responseCode = response.getStatusLine().getStatusCode();
	 
	            System.out.println("Request Url: " + request.getURI());
	            System.out.println("Response Code: " + responseCode);
	 
	            InputStream is = entity.getContent();
	 
	            
	            
	            File file = new File(filePath);
	            file.delete();
	           
	            
	            FileOutputStream fos = new FileOutputStream(new File(filePath));
	 
	            int inByte;
	            while ((inByte = is.read()) != -1) {
	                fos.write(inByte);
	            }
	 
	            is.close();
	            fos.close();
	 
	            
	            System.out.println("File Download Completed!!!");
	            client.getConnectionManager().shutdown();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (UnsupportedOperationException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        File f = new File(filePath);
	        if(f.exists())
	        {
	        	return filePath;
	        }
	        else
	        {
	        	return "";
	        }
		}
		catch (Exception e)
		{
			return "";
		}
        	
       
    }
	
	
	
	
	
	public int sendPut(String data, String url) 
	{
	    int responseCode = -1;
	    HttpClient httpClient = new DefaultHttpClient();
	    try {
	        HttpPut request = new HttpPut(url);
	        StringEntity params =new StringEntity(data,"UTF-8");
	        params.setContentType("application/json");
	        request.addHeader("content-type", "application/json");
	        request.addHeader("Accept", "*/*");
	        request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
	        request.addHeader("Accept-Language", "en-US,en;q=0.8");
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);
	        responseCode = response.getStatusLine().getStatusCode();
	        if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 204) {

	            BufferedReader br = new BufferedReader(
	                    new InputStreamReader((response.getEntity().getContent())));

	            String output;
	           // System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n");
	            while ((output = br.readLine()) != null) {
	               // System.out.println(output);
	            }
	        }
	        else{
	            //logger.error(response.getStatusLine().getStatusCode());

	            throw new RuntimeException("Failed : HTTP error code : "
	                    + response.getStatusLine().getStatusCode());
	        }

	    }catch (Exception ex) 
	    {
	        System.out.println("ex Code sendPut: " + ex);
	        System.out.println("url:" + url);
	        System.out.println("data:" + data);
	    } finally {
	        httpClient.getConnectionManager().shutdown();
	    }

	    return responseCode;

	}
	
	
	public int sendPost(String data, String url) 
	{
	    int responseCode = -1;
	    HttpClient httpClient = new DefaultHttpClient();
	    try {
	        HttpPost request = new HttpPost(url);
	        StringEntity params =new StringEntity(data,"UTF-8");
	        params.setContentType("application/json");
	        request.addHeader("content-type", "application/x-www-form-urlencoded");
	        request.addHeader("Accept", "*/*");
	        request.addHeader("Accept-Encoding", "gzip,deflate,sdch");
	        request.addHeader("Accept-Language", "en-US,en;q=0.8");
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);
	        responseCode = response.getStatusLine().getStatusCode();
	        if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 204) {

	            BufferedReader br = new BufferedReader(
	                    new InputStreamReader((response.getEntity().getContent())));

	            String output;
	           // System.out.println("Output from Server ...." + response.getStatusLine().getStatusCode() + "\n");
	            while ((output = br.readLine()) != null) {
	               // System.out.println(output);
	            }
	        }
	        else{
	            //logger.error(response.getStatusLine().getStatusCode());

	            throw new RuntimeException("Failed : HTTP error code  : "
	                    + response.getStatusLine().getStatusCode());
	        }

	    }catch (Exception ex) 
	    {
	        System.out.println("ex Code sendPost: " + ex);
	        System.out.println("url:" + url);
	        System.out.println("data:" + data);
	    } finally {
	        httpClient.getConnectionManager().shutdown();
	    }

	    return responseCode;

	}
	
	
	
	
	private String callWSPOST_ParamJSON(String URLbase, String funcion, String jotason)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		 
	    try 
		{
	    	System.out.println("llamando a "+URLbase+funcion);
	    	System.out.println("Parametros JSON "+jotason);
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost(URLbase+funcion);
	    	
	    	StringEntity params =new StringEntity(jotason);
	    	httpPostRequest.setHeader("Content-type", "application/x-www-form-urlencoded");
	    	
	    	//httpPostRequest.setEntity(params);
	    	
	    	ArrayList<NameValuePair> postParameters;
	    	postParameters = new ArrayList<>();
	        postParameters.add(new BasicNameValuePair("compras", jotason));
	        

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
		          //System.out.println(strFileContents);
		          return strFileContents;
		          
		          
		        }
		        catch (RuntimeException runtimeException) 
		        {
		          // In case of an unexpected exception you may want to abort
		          // the HTTP request in order to shut down the underlying
		          // connection immediately.
		          httpPostRequest.abort();
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
	
	

	
	
	
	private String callWSPOST(String URLbase, String funcion)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
	   // ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("produccion@200data.com", "200data2017"));
	    try 
		{
	    	System.out.println("llamando a "+URLbase+funcion);
	    	
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost(URLbase+funcion);
	    	
	    	
	    	
	    	
	    	
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpPostRequest);
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
		          httpPostRequest.abort();
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
	

	

	private String callWSGETPrint()
	{
		String retorno = "";
		
		
		HttpClient httpClient = getHttpClient();
		
	    ((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("Stadium.53778", "ae93a7e435b02996c7a3a348d65332498a9d53b9"));
	    try 
		{
	    	HttpGet httpGetRequest = new HttpGet("http://app.printnode.com/api/printers");
	    	
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
		          System.out.println(strFileContents); ;
		          
		          
		          
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
	
	private static HttpClient getHttpClient() 
	{

	    try {
	        SSLContext sslContext = SSLContext.getInstance("SSL");

	       
	        //SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	        HttpClientBuilder construct = HttpClientBuilder.create();//.setSSLSocketFactory(socketFactory); 
	        
	        CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("", "ae93a7e435b02996c7a3a348d65332498a9d53b9");
			provider.setCredentials(AuthScope.ANY, credentials);
			
			construct.setDefaultCredentialsProvider(provider);
			

	        HttpClient httpClient = construct.build();

	        return httpClient;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return HttpClientBuilder.create().build();
	    }
	}

	
	public String callWSPOSTPrint(String jotason)
	{
		
		System.out.println("json: ");
		System.out.println(jotason);
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		((AbstractHttpClient) httpClient).getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),new UsernamePasswordCredentials("Stadium.53778", "ae93a7e435b02996c7a3a348d65332498a9d53b9"));
	    try 
		{
	    	
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost("https://api.printnode.com/printjobs");
	    	
	    	StringEntity params =new StringEntity(jotason);
	    	httpPostRequest.setHeader("Content-type", "application/json");
	    	
	    	httpPostRequest.setEntity(params);
	    	
	    	
	    	
	    	
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpPostRequest);
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
	
	
	public String callWSPOSTParam(String jotason, String urlP)
	{
		
		System.out.println("json: ");
		System.out.println(jotason);
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		
	    try 
		{
	    	
	    	System.out.println("Respuesta");
	    	HttpPost httpPostRequest = new HttpPost(urlP);
	    	
	    	StringEntity params =new StringEntity(jotason);
	    	httpPostRequest.setHeader("Content-type", "application/json");
	    	
	    	httpPostRequest.setEntity(params);
	    	
	    	
	    	
	    	
	    	
		  	HttpResponse httpResponse = httpClient.execute(httpPostRequest);
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

	

	
	/***********para un solo pedido**********/
	public List<JSONRespPedidos> getPedidoML(String access_token, String idPedido,List<DataIDDescripcion>depositosPick,List<DataIDDescripcion> pedidosMLSinc, int idCanal, String seller,int idEmpresa, Usuario u) 
	{
		Logica Logica = new Logica();
		List<DataIDDescripcion> pedidosSincronizados = new ArrayList<>();
		
		int est=0;
		Long cantidadPasadas = new Long(1);
		Fecha fecha = new Fecha(-15, 0, 0);
		Logica.logger(0, 100, "consultando pedido en Mercado Libre "+idPedido,idEmpresa);
		
		Hashtable<Long, String> pedidosIn =new Hashtable<>();
		for (DataIDDescripcion d : pedidosMLSinc) 
		{
			pedidosIn.put(new Long(d.getIdLong()), d.getDescripcion());
		}
		depositosPick.remove(0);
		Hashtable<String, Integer> depositosPickHT = new Hashtable<>();
		for (DataIDDescripcion d : depositosPick) 
		{
			depositosPickHT.put(d.getDescripcion(), d.getId());
		}
		
		int pedido=0;
		try{
			pedido=Integer.parseInt(idPedido);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		int error = 0;
		int offset=0;
		boolean pri = true;
		while (cantidadPasadas>=0) 
		{
			try
			{
				
			
				String URLbase = "https://api.mercadolibre.com";
				
				String funcion = "/orders/search?order.status=paid&seller="+seller+"&order.date_created.from="+fecha.darFechaAnio_Mes_Dia()+"T00:00:00.000-00:00&limit=50&offset="+offset+"&access_token="+access_token+"&q="+idPedido;
				String retorno = this.callWSGET(URLbase, funcion);
				//System.out.println(retorno);
				MLPedidos pedidosML = JSONReader.readJsonMeli(retorno);
		        
		        if(pri)
				{
					pri=false;
					cantidadPasadas = (pedidosML.getPaging().getTotal())/50;
				}
		        
				for (Result pml : pedidosML.getResults()) 
				{
					if(pml.getId()== new Long(idPedido)){
					if(pedidosIn.get(pml.getId())==null)
					{
						System.out.println("sincronizando vta "+pml.getId());
						Logica.logPedido(pml.getId(), 0, 0, "comienzo sinconizacion",0,idEmpresa);
						
						Logica.logger(0, 100, "desempaquetando pedido "+pml.getId(),idEmpresa);
						
						Long idPayment =  pml.getPayments().get(0).getId();
						//String idPaymentForm = pml.getPayments().get(0).getPayment_method_id();
						
						funcion = "/collections/"+idPayment+"?access_token="+access_token;
						retorno = this.callWSGET(URLbase, funcion);
						MLPagos pagos = JSONReader.readJsonPagoMeli(retorno);
						
						
						
						EncuentraPedido p = new EncuentraPedido();
						p.setIdPedido(pml.getId());
						
						/*************le seteo el canal*************/
						p.setCanalMercadoLibre(new DataIDDescripcion(idCanal, ""));
						
						
						Logica.logger(0, 100, "Leyendo Datos de Pago pedido "+pml.getId(),idEmpresa);
						
						int cantidad=0;
						Double descuento=0.0;
						
						p.setEstado("mercadoLibre");
						p.setUrlEtiqueta("");
						
						List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
						List<OrdenVentaLinea> ventaLineas = new ArrayList<>();
						
						Double importeTotal = 0.0; 
						
						p.setSucursalPick(""); 
						Double costoEnvio = 0.0;
						String envio="";
											
						try{
							for(Payment pp:pml.getPayments()){
								costoEnvio += Double.parseDouble(String.valueOf(pp.getShipping_cost()));
							}
						}
						catch(Exception e){
							System.out.println("error costo de envio");
						}
						
						if(costoEnvio>0.0)
						{
							ventaLineas.add(new OrdenVentaLinea(costoEnvio, "1", "0002000"));
							Logica.logPedido(pml.getId(), 0, 0, "Agrgando costo de envio "+costoEnvio,0,idEmpresa);
						}
						
						p.setDescripcion("MELI: "+ pml.getBuyer().getFirst_name()+" "+pml.getBuyer().getLast_name()+" ENVIO: "+envio);
						
						for (Order_item it : pml.getOrder_items()) 
						{
							Double importe = it.getUnit_price();
							int cantidadItem = (int) it.getQuantity();
							importe=importe*cantidadItem;
							if(importe>0)
							{
								cantidad+=cantidadItem;
								EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
								art.setArticulo(it.getItem().getSeller_custom_field());
								art.setCantidad(cantidadItem);
								art.setProcesada(0);
								pedidos.add(art);
								importeTotal+=importe;
								try
								{
									OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", it.getItem().getSeller_custom_field());
									
									Logica.logPedido(pml.getId(), 0, 0, "Agregando linea articulo "+it.getItem().getSeller_custom_field(),0,idEmpresa);
									ventaLineas.add(ovl);
									
									//ME FIJO SI ES DE LA TIENDA CLARKS
									
									if(it.getItem().getSeller_custom_field().contains("061.")){
										funcion = "/items/"+it.getItem().getId();
										retorno = this.callWSGET(URLbase, funcion);
										MLUitem mlu = JSONReader.readJsonMLUitem(retorno);
										 if(mlu.getOfficial_store_id().equals("542")){
										 		p.setCanalMercadoLibre(new DataIDDescripcion(-2, ""));
										  }
									}
								}
								catch(Exception e)
								{
									try{
										art.setArticulo(it.getItem().getSeller_sku());
										
										OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", it.getItem().getSeller_sku());
										
										Logica.logPedido(pml.getId(), 0, 0, "Agregando linea articulo "+it.getItem().getSeller_sku(),0,idEmpresa);
										ventaLineas.add(ovl);
										
										//ME FIJO SI ES DE LA TIENDA CLARKS
										
										if(it.getItem().getSeller_sku().contains("061.")){
											funcion = "/items/"+it.getItem().getId();
											retorno = this.callWSGET(URLbase, funcion);
											MLUitem mlu = JSONReader.readJsonMLUitem(retorno);
											 if(mlu.getOfficial_store_id().equals("542")){
											 		p.setCanalMercadoLibre(new DataIDDescripcion(-2, ""));
											  }
										}
									}catch (Exception ex) {
										Logica.logPedido(pml.getId(), 0, 0, "ERROR Agregando linea articulo se agrega linea como SINARTICULO",-1,idEmpresa);
										OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", "SINARTICULO");
										ventaLineas.add(ovl);
										EnviaMail.enviarMail("MercadoLibre@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
										EnviaMail.enviarMail("sicardo@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
										EnviaMail.enviarMail("ndelgado@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
										EnviaMail.enviarMail("onviera@200.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
									}
								}
								
								
							}
							else
							{
								descuento+=importe;
							}
							
							
							
						}
						
						Fecha fechaActual = new Fecha();
						System.out.println("");
						p.setCantidad(cantidad);
						p.setDescuento(descuento);
						p.setArticulosPedido(pedidos);
						
						p.setArticulosPedidoReq(new ArrayList<>());
						p.setFormaPago(idPayment+" "+fechaActual.darFechaAnio_Mes_Dia());
						//p.setFormaPago(idPaymentForm);
						Double porcDescuento = (descuento*100)/importeTotal;
						
						
						if((porcDescuento*-1)>0)
						{
							for (OrdenVentaLinea ol : ventaLineas) 
							{
								if(!ol.getIdArticulo().equals("0002000"))
								{
									Double fullPrice = ol.getPrecioImp();
									Double discount = ((porcDescuento*-1)*fullPrice)/100;
									
									ol.setPrecioImp(fullPrice-discount);
								}
							}
						}
						if(p.save(idEmpresa))
						{
							try
							{
								String idShipping = pml.getShipping().getId();
								DataIDDescripcion pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token,pml.getId(),idEmpresa);
								int num = depositosPickHT.get(pathEtiqueta.getDescripcionB());
								
								if(!pathEtiqueta.getDescripcionB().equals("")){
									envio+=pathEtiqueta.getDescripcionB();
								}
																
								if(!pathEtiqueta.getDescripcion().equals(""))
								{	
									p.setUrlEtiqueta(pathEtiqueta.getDescripcion());
									p.updateEtiqueta(0,idEmpresa);
									
								}
								
								try
								{
									p.updateShipping(num,null,idEmpresa);
								}
								catch(Exception e)
								{
									p.updateShipping(0,null,idEmpresa);
								}
								
							}
							catch (Exception e)
							{
								
								try
								{
									funcion = "/pickups/"+pml.getPickup_id()+"?access_token="+access_token;
									retorno = this.callWSGET(URLbase, funcion);
									
									Pickup pi = JSONReader.readJsonPickup(retorno);
									System.out.println(pi.getStore_info().getDescription()+"");
									int num = depositosPickHT.get(pi.getStore_info().getDescription());
									String sucursalPick = String.format("%02d", num);
									envio = "Pickup: "+sucursalPick;
									p.setSucursalPick(sucursalPick);
									String personaRetira = pi.getPickup_person().getFull_name();
									p.setPersonaRetira(personaRetira);
									p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
									Logica.logPedido(pml.getId(), 0, 0, "Agregando Etiqueta de pickup "+sucursalPick,0,idEmpresa);
									p.updateEtiqueta(0,idEmpresa);
									p.updateDestino(num,"",idEmpresa,0.0,false);
								}
								catch (Exception e2)
								{
									Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1,idEmpresa);
								}
							}
							
							pedidosSincronizados.add(new DataIDDescripcion(p.getIdPedido(), p.getDescripcion()));
							p.updateML(idEmpresa);
							setNoteToOrder(pml.getId(),access_token, "Sincronizado por encuentra ",idEmpresa);
							Logica.logPedido(pml.getId(), 0, 0, "Agregando nota de sincronizacion en ML ",0,idEmpresa);
							
							
							System.out.println("Pedido "+pml.getId()+" gurdado");
							Logica.logPedido(pml.getId(), 0, 0, "Pedido Sincronizado ",0,idEmpresa);
							WSCommunicate ws = new WSCommunicate();
							
							
							String apellido = pml.getBuyer().getLast_name();
							String nombre = pml.getBuyer().getFirst_name();
							
							String departamento = "";
							try
							{
								departamento= pml.getShipping().getReceiver_address().getState().getName();
							}
							catch(Exception e){}
							String localidad ="";
							
							try
							{
								localidad=pml.getShipping().getReceiver_address().getCity().getName();
							}
							catch(Exception e){}
							String calle = "";
							try
							{
								calle=pml.getShipping().getReceiver_address().getAddress_line();
							}
							catch(Exception e){}
							String email = "";
							if((String)pagos.getPayer().getEmail()!=null || pagos.getPayer().getIdentification().getNumber()!=null)
							{
								email = (String) pagos.getPayer().getEmail();
								if(email==null)
								{
									email="";
								}
							}
							else
							{
								email = pml.getBuyer().getEmail();
							}
							
							String telefono = "";
							String nroPuerta = "";
							String nroApto = "";
							
							
							String documentoTipo = "ci";
							String documentoNro = "";
							if(pagos.getPayer().getIdentification().getNumber()==null)
							{
								documentoTipo = "MLID";
								documentoNro = String.valueOf(pml.getBuyer().getId());
							}
							else
							{
								documentoNro = String.valueOf(pagos.getPayer().getIdentification().getNumber());
							}
							
							String curr = pml.getCurrency_id();
							String moneda = "";
							if(curr.equals("UYU"))
							{
								moneda = "$";
							}
							else
							{
								moneda="USD";
							}
							Clientes cli = new Clientes(apellido, nombre, localidad, email, departamento, telefono, nroPuerta, nroApto, documentoTipo, calle, documentoNro);
							ws.grabarCliente(cli,idEmpresa);
							Logica.logPedido(pml.getId(), 0, 0, "grabando el cliente "+cli.toString(),0,idEmpresa);
							
							Long longo = new Long(pagos.getTransaction_amount());
							Double importePago = longo.doubleValue() + costoEnvio;
							
							OrdenVenta orden = new OrdenVenta(Integer.parseInt(cli.getNumero()), porcDescuento*-1, moneda, cli.getNumero()+"", p.getDescripcion(), calle, "", telefono, ventaLineas,pml.getId(),2003, ""+idPayment+" "+fechaActual.darFechaAnio_Mes_Dia(),1,importePago);
							
							orden.setCliMail("");
							
							Logica.logPedido(pml.getId(), 0, 0, "grabando Orden para importar "+orden.toString(),0,idEmpresa);
							orden.setComentario("ENVIO:"+envio);
							orden.setFormaPagoVisual("MP");
							orden.save(p.getUrlEtiqueta(),idEmpresa);
							
								///////////////////GENERO LA ORDEN
										try {
											Logica.procesarOrdenPedido(p.getIdPedido(),idEmpresa);
											
											////////////////////////////VOY A BUSCAR LA ORDEN Y LA FACTURA//////////////////////////////
											DataIDIDDescripcion factVenta = MSSQL.darIdOrdenVenta(p.getIdPedido()+"").get(0);
											String urlFact ="";
											Logica.altaOrdenPedido(p.getIdPedido(), factVenta.getId(), factVenta.getIid(),urlFact,idEmpresa);
										} catch (Exception e) {
											e.printStackTrace();
										}
						}
						
						
					}//if
					else
					{//el pedido ya est?, debo actualizar los datos el envio
					
					
						//consulto si el pedido fue cancelado manualmente
						boolean cancelado = Logica.hayRegistro("select * from ecommerce_pedido where idPedido="+pml.getId()+" and cancelado=1 and estadoencuentra=99");
						
						if(!cancelado){
						
						//consulto si se modifico el destino manualmente
						boolean etiquetaManual = Logica.hayRegistro("select * from ecommerce_pedido_destino where idPedido="+pml.getId()+" and manual=1");
						
						if (!etiquetaManual){					
					
						//el pedido ya est?, debo actualizar los datos el envio
						Logica.logPedido(pml.getId(), 0, 0, "Pedido existente en encuentra, no se importar?, solo se consulta etiquetas",0,idEmpresa);
						Logica.logPedido(pml.getId(), 0, 0, "comprobando si hay etiqueta del pedido",0,idEmpresa);
						
						try
						{
							EncuentraPedido p = new EncuentraPedido();
							p.setIdPedido(pml.getId());
							String et = pedidosIn.get(pml.getId());
							
							String idShipping = pml.getShipping().getId();
							DataIDDescripcion pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token,pml.getId(),idEmpresa);
							int num = depositosPickHT.get(pathEtiqueta.getDescripcionB());
							
							if(!pathEtiqueta.getDescripcion().equals(""))
							{	
								p.setUrlEtiqueta(pathEtiqueta.getDescripcion());
								p.updateEtiqueta(0,idEmpresa);
							
							}								
							try
							{
								p.updateShipping(num,null,idEmpresa);
							}
							catch(Exception e)
							{
								p.updateShipping(0,null,idEmpresa);
							}
							
							if(!et.contains("http"))
							{
								est=(Logica.darDataIdDescripcion("select EstadoEncuentra,'' from ecommerce_pedido where idpedido= "+p.getIdPedido())).getId();
								if(est==25){
									Logica.updateEcommerceEstado(p.getIdPedido(), 3,idEmpresa, u);
								}								
							}
						}
						catch (Exception e)
						{
							try
							{
								EncuentraPedido p = new EncuentraPedido();
								p.setIdPedido(pml.getId());
								
								
								funcion = "https://api.mercadolibre.com/pickups/"+pml.getPickup_id()+"?access_token="+access_token;
								if((Integer)pml.getPickup_id()!=null)
								{	
									retorno = this.callWSGET(URLbase, funcion);								
									Pickup pi = JSONReader.readJsonPickup(retorno);							
									int num = depositosPickHT.get(pi.getStore_info().getDescription());
									String sucursalPick = String.format("%02d", num);
									p.setSucursalPick(sucursalPick);
									String personaRetira = pi.getPickup_person().getFull_name();
									p.setPersonaRetira(personaRetira);
									p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
									Logica.logPedido(pml.getId(), 0, 0, "Agregando Etiqueta de pickup "+sucursalPick,0,idEmpresa);
									p.updateEtiqueta(0,idEmpresa);
									p.updateDestino(num,"",idEmpresa,0.0,false);
									//////////
									est=(Logica.darDataIdDescripcion("select EstadoEncuentra,'' from ecommerce_pedido where idpedido= "+p.getIdPedido())).getId();
									if(est==25){
										Logica.updateEcommerceEstado(p.getIdPedido(), 3,idEmpresa, u);
									}
								}
								else
								{
									Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1,idEmpresa);
								}
								
								
							}
							catch (Exception e2)
							{
								Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1,idEmpresa);
							}
							
						}
						
							}//if etiqueta manual
						
						}//if cancelado
						
					}//if de pedido existente
					
					Logica.logPedido(pml.getId(), 0, 0, "Fin de sincronizaci?n de Pedido",0,idEmpresa);
				}	
				}//for
				
				cantidadPasadas--;
				
				offset+=50;
			}
			catch(Exception e)
			{
				error++;
				if(error==100)
				{
					cantidadPasadas=new Long(-1);
				}
			}
		}//while
		
		EnviaMail em = new EnviaMail();
		em.enviarMailLogEcommerce(pedidosSincronizados);
		//EcommerceProcessOrders.process(null,new Long("0"),null,0);
		
		
		return null;
		
	}
	
	
	
	
	/***********para etiquetas de varios pedidos
	 * @param idEnvio **********/
	public List<String> getRemitoPedidosML(String access_token, List<String> pedidos, int idEnvio, int idEmpresa) 
	{
		Logica Logica = new Logica();
		String URLbase = "https://api.mercadolibre.com";
		List<String> shippings = new ArrayList<>();
		Hashtable<String, String> shipedidos = new Hashtable<>();
		for (String idPedido : pedidos) 
		{
			String funcion = "/orders/search?order.status=paid&seller=265129396&limit=50&offset="+0+"&access_token="+access_token+"&q="+idPedido;
			String retorno = this.callWSGET(URLbase, funcion);
			//System.out.println(retorno);
			MLPedidos pedidosML = JSONReader.readJsonMeli(retorno);
			
			for (Result pml : pedidosML.getResults())
			{
				try
				{
					if(!String.valueOf(pml.getShipping().getId()).equals("27592690334"))
					{
						//176448859
						System.out.println(pml.getShipping().getId());
						
						if((pml.getShipping().getId()+"").equals("0"))
						{
							Logica.logPedido(pml.getId(), 0, 0, "Error buscando envio para armar remito, probablemente aun no tiene envio acordado, pero cuidado, porque esta enviando algo sin envio acordado...  ",-1,idEmpresa);
						}
						else
						{
							shippings.add(pml.getShipping().getId()+"");
						
							Logica.logPedido(pml.getId(), 0, 0, "asociando informacion del envio, "+pml.getShipping().getId(),0,idEmpresa);
							shipedidos.put(pml.getShipping().getId()+"", pml.getId()+"");
						}
					}
					else
					{
						System.out.println("ERROR con "+idPedido);
					}
					
				}
				
				catch (Exception e)
				{
					System.out.println("ERROR con "+idPedido);
					Logica.logPedido(pml.getId(), 0, 0, "Error buscando envio para armar remito, probablemente aun no tiene envio acordado, pero cuidado, porque esta enviando algo sin envio acordado...  ",-1,idEmpresa);
				}
			}			
		}
				
				
		StringBuilder shippingsArraySB = new StringBuilder();
		
		List<String> shippinges = new ArrayList<>();
		
		int pasada = 0;
		for (String s : shippings) 
		{
			shippingsArraySB.append(s+",");
			if(pasada==25)
			{
				String shippingsArray = shippingsArraySB.toString();
				shippingsArray = shippingsArray.substring(0, shippingsArray.length()-1);
				shippinges.add(shippingsArray);
				
				shippingsArraySB = new StringBuilder();
				pasada=0;
				
			}
			
			pasada++;
		}
		try
		{
			String shippingsArray = shippingsArraySB.toString();
			shippingsArray = shippingsArray.substring(0, shippingsArray.length()-1);
			shippinges.add(shippingsArray);
		}
		catch(Exception e)
		{
			
		}
		
		
		
		System.out.println("");
		        
		List<String> pathRemito =  pdfRemitoML (shippinges, access_token,shipedidos,idEnvio);
		
		return pathRemito;
		
	}
	
	public List<JSONRespPedidos> u(String access_token, String idPedido, int idEmpresa) 
	{
		Logica Logica = new Logica();
		Long cantidadPasadas = new Long(1);
		Fecha fecha = new Fecha(-45, 0, 0);
		
		Logica.logger(0, 100, "consultando pedidos en Mercado Libre desde "+fecha.FechaMostrableSM(),idEmpresa);
		
		/*******************traigo los depositos para el pickup*********************/
		List<DataIDDescripcion>depositosPick = Logica.darListaDataIdDescripcionMYSQLConsulta("select idDeposito,nombre from ecommerce_envioml where idDeposito !=0");
		
		List<DataIDDescripcion> pedidosSincronizados = new ArrayList<>();
		
		List<DataIDDescripcion> pedidosMLSinc = Logica.darListaDataIdDescripcionMYSQLConsulta("select idPedido,URLetiqueta from ecommerce_pedido where ML =1 and DATEDIFF(CURRENT_TIMESTAMP(), stamptime)<15 union all select idVenta, '' from ecommerce_ml_exclusiones");
		pedidosMLSinc.remove(0);
		
		Hashtable<Long, String> pedidosIn =new Hashtable<>();
		for (DataIDDescripcion d : pedidosMLSinc) 
		{
			pedidosIn.put(new Long(d.getId()), d.getDescripcion());
		}
		depositosPick.remove(0);
		Hashtable<String, Integer> depositosPickHT = new Hashtable<>();
		for (DataIDDescripcion d : depositosPick) 
		{
			depositosPickHT.put(d.getDescripcion(), d.getId());
		}
		
		
		int error = 0;
		int offset=0;
		boolean pri = true;
		while (cantidadPasadas>=0) 
		{
			try
			{
				
			
				String URLbase = "https://api.mercadolibre.com";
				
				
				String funcion = "/orders/search?seller=265129396&order.date_created.from="+fecha.darFechaAnio_Mes_Dia()+"T00:00:00.000-00:00&limit=50&offset="+offset+"&q="+idPedido+"&access_token="+access_token;
				String retorno = this.callWSGET(URLbase, funcion);
				//System.out.println(retorno);
				MLPedidos pedidosML = JSONReader.readJsonMeli(retorno);
		        
		        if(pri)
				{
					pri=false;
					cantidadPasadas = (pedidosML.getPaging().getTotal())/50;
				}
		        
				for (Result pml : pedidosML.getResults()) 
				{
					System.out.println("sincronizando vta "+pml.getId());
					Logica.logPedido(pml.getId(), 0, 0, "comienzo sinconizacion",0,idEmpresa);
						
						Logica.logger(0, 100, "desempaquetando pedido "+pml.getId(),idEmpresa);
						
						Long idPayment =  pml.getPayments().get(0).getId();
						
						funcion = "/collections/"+idPayment+"?access_token="+access_token;
						retorno = this.callWSGET(URLbase, funcion);
						MLPagos pagos = JSONReader.readJsonPagoMeli(retorno);
						
						
						EncuentraPedido p = new EncuentraPedido();
						p.setIdPedido(pml.getId());
						Logica.logger(0, 100, "Leyendo Datos de Pago pedido "+pml.getId(),idEmpresa);
						
						int cantidad=0;
						Double descuento=0.0;
						
						p.setEstado("mercadoLibre");
						p.setUrlEtiqueta("");
						
						List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
						List<OrdenVentaLinea> ventaLineas = new ArrayList<>();
						
						Double importeTotal = 0.0; 
						
						p.setSucursalPick(""); 
						Double costoEnvio = 0.0;
						String envio = pml.getShipping().getShipping_option().getName();
						try
						{
							envio = pml.getShipping().getShipping_option().getName();
							costoEnvio = Double.parseDouble(String.valueOf(pml.getShipping().getCost()));
							//Long idShipping = pml.getShipping().getId();
							Logica.logPedido(pml.getId(), 0, 0, "buscando etiqueta",0,idEmpresa);
							//String pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token, pml.getId());
							
							/*if(!pathEtiqueta.equals(""))
							{
								p.setUrlEtiqueta(pathEtiqueta);
							}*/
						}
						catch(Exception e)
						{
							Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1,idEmpresa);
						}
									
						if(costoEnvio>0.0)
						{
							ventaLineas.add(new OrdenVentaLinea(costoEnvio, "1", "0002000"));
							
							Logica.logPedido(pml.getId(), 0, 0, "Agrgando costo de envio "+costoEnvio,0,idEmpresa);
							
							
							
						}
						
						p.setDescripcion("MELI: "+ pml.getBuyer().getFirst_name()+" "+pml.getBuyer().getLast_name()+" ENVIO: "+envio);
						
						for (Order_item it : pml.getOrder_items()) 
						{
							Double importe = it.getUnit_price();
							int cantidadItem = (int) it.getQuantity(); 
							importe=importe*cantidadItem;
							if(importe>0)
							{
								cantidad+=cantidadItem;
								EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
								art.setArticulo(it.getItem().getSeller_custom_field());
								art.setCantidad(cantidadItem);
								art.setProcesada(0);
								pedidos.add(art);
								importeTotal+=importe;
								try
								{
									OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", it.getItem().getSeller_custom_field());
									
									Logica.logPedido(pml.getId(), 0, 0, "Agregando linea articulo "+it.getItem().getSeller_custom_field(),0,idEmpresa);
									ventaLineas.add(ovl);
								}
								catch(Exception e)
								{
									Logica.logPedido(pml.getId(), 0, 0, "ERROR Agregando linea articulo se agrega linea como SINARTICULO",-1,idEmpresa);
									OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", "SINARTICULO");
									ventaLineas.add(ovl);
									EnviaMail.enviarMail("MercadoLibre@stadium.com.uy","PEDIDO SIN ARTICULO" , "El pedido numero "+p.getIdPedido()+" no contiene articulo.");
								}
								
								
							}
							else
							{
								descuento+=importe;
							}
							
						}
						System.out.println("");
						p.setCantidad(cantidad);
						p.setDescuento(descuento);
						p.setArticulosPedido(pedidos);
						Fecha fechaActual = new Fecha();
						
						p.setArticulosPedidoReq(new ArrayList<>());
						p.setFormaPago(""+idPayment+" "+fechaActual.darFechaAnio_Mes_Dia());
						Double porcDescuento = (descuento*100)/importeTotal;
						
						
						if((porcDescuento*-1)>0)
						{
							for (OrdenVentaLinea ol : ventaLineas) 
							{
								if(!ol.getIdArticulo().equals("0002000"))
								{
									Double fullPrice = ol.getPrecioImp();
									Double discount = ((porcDescuento*-1)*fullPrice)/100;
									
									ol.setPrecioImp(fullPrice-discount);
								}
							}
						}
							pedidosSincronizados.add(new DataIDDescripcion(p.getIdPedido(), p.getDescripcion()));
							p.updateML(idEmpresa);
							setNoteToOrder(pml.getId(),access_token, "Sincronizado por encuentra ",idEmpresa);
							Logica.logPedido(pml.getId(), 0, 0, "Agregando nota de sincronizacion en ML ",0,idEmpresa);
							try
							{
								String shippingOption = pml.getShipping().getShipping_option().getName();
								if(depositosPickHT.get(shippingOption)!=null)
								{
									int num = depositosPickHT.get(shippingOption);
									if(num<700)
									{
										String sucursalPick = String.format("%02d", num);
										p.setSucursalPick(sucursalPick);
										p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
										Logica.logPedido(pml.getId(), 0, 0, "Agregando Etiqueta de pickup "+sucursalPick,0,idEmpresa);
										p.updateEtiqueta(0,idEmpresa);
										p.updateDestino(num,"",idEmpresa,0.0,false);
									}
									try
									{
										p.updateShipping(num,null,idEmpresa);
									}
									catch(Exception e)
									{
										p.updateShipping(0,null,idEmpresa);
									}
									
								}
								
							}
							catch (Exception e)
							{
								Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta PICKUP, probablemente aun no tiene envio acordado",-1,idEmpresa);
							}
							
							System.out.println("Pedido "+pml.getId()+" gurdado");
							Logica.logPedido(pml.getId(), 0, 0, "Pedido Sincronizado ",0,idEmpresa);
							WSCommunicate ws = new WSCommunicate();
							
							
							String apellido = pml.getBuyer().getLast_name();
							String nombre = pml.getBuyer().getFirst_name();
							
							String departamento = "";
							try
							{
								departamento= pml.getShipping().getReceiver_address().getState().getName();
							}
							catch(Exception e){}
							String localidad ="";
							try
							{
								localidad=pml.getShipping().getReceiver_address().getCity().getName();
							}
							catch(Exception e){}
							String calle = "";
							try
							{
								calle=pml.getShipping().getReceiver_address().getAddress_line();
							}
							catch(Exception e){}
							String email = "";
							
							try
							{
								if((String)pagos.getPayer().getEmail()!=null || pagos.getPayer().getIdentification().getNumber()!=null)
								{
									email = (String) pagos.getPayer().getEmail();
									if(email==null)
									{
										email="";
									}
								}
								else
								{
									email = pml.getBuyer().getEmail();
								}
							}
							catch(Exception e){}
							
							
							String telefono = pml.getBuyer().getPhone().getNumber();
							String nroPuerta = "";
							String nroApto = "";
							
							
							String documentoTipo = "ci";
							String documentoNro = "";
							try
							{
								if(pagos.getPayer().getIdentification().getNumber()==null)
								{
									documentoTipo = "MLID";
									documentoNro = String.valueOf(pml.getBuyer().getId());
								}
								else
								{
									documentoNro = String.valueOf(pagos.getPayer().getIdentification().getNumber());
								}
							}
							catch(Exception ex)
							{
								documentoTipo = "MLID";
								documentoNro = String.valueOf(pml.getBuyer().getId());
							}
							
							
							String curr = pml.getCurrency_id();
							String moneda = "";
							if(curr.equals("UYU"))
							{
								moneda = "$";
							}
							else
							{
								moneda="USD";
							}
							Clientes cli = new Clientes(apellido, nombre, localidad, email, departamento, telefono, nroPuerta, nroApto, documentoTipo, calle, documentoNro);
							ws.grabarCliente(cli,idEmpresa);
							Logica.logPedido(pml.getId(), 0, 0, "grabando el cliente "+cli.toString(),0,idEmpresa);
							Double importePago =0.0;
							try
							{
								Long longo = new Long(pagos.getTransaction_amount());
								
								importePago = longo.doubleValue();
							}
							catch(Exception e)
							{
								
							}
							
							
							
							OrdenVenta orden = new OrdenVenta(Integer.parseInt(cli.getNumero()), porcDescuento*-1, moneda, cli.getNumero()+"", p.getDescripcion(), calle, "", telefono, ventaLineas,pml.getId(),2003, ""+idPayment+" "+fechaActual.darFechaAnio_Mes_Dia(),1,importePago);
							
							Logica.logPedido(pml.getId(), 0, 0, "grabando Orden para importar "+orden.toString(),0,idEmpresa);
							orden.setComentario("ENVIO:"+envio);
							orden.save(p.getUrlEtiqueta(),idEmpresa);
							
						
					
				}//for
				
				cantidadPasadas--;
				
				offset+=50;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
				error++;
				if(error==100)
				{
					cantidadPasadas=new Long(-1);
				}
				break;
			}
		}//while
		
		EnviaMail em = new EnviaMail();
		em.enviarMailLogEcommerce(pedidosSincronizados);
		//EcommerceProcessOrders.process(null,0,0,0);
		
		return null;
		
	}



	public void MessageToCustomer(Long idPedidoI, String message, String asunto, List<DataIDDescripcion> tokens) 
	{		
		String tok = tokens.get(0).getDescripcionB();	
		String sell = tokens.get(0).getDescripcion();;
		String customerID = darIdCustomer(idPedidoI, tok,sell);
		if(customerID.equals(""))
		{
			System.out.println("orden "+idPedidoI+" no se encontr? el cliente");
			tok=tokens.get(1).getDescripcionB();
			sell=tokens.get(1).getDescripcion();
			customerID = darIdCustomer(idPedidoI, tok,sell);
			if(customerID.equals("")){
				System.out.println("orden "+idPedidoI+" no se encontr? el cliente");
			}
		}
		
		
		if(!customerID.equals("")){
		
			System.out.println("se encontr? el cliente");
			String fromID=sell;
			String resourceID=idPedidoI+"";
			
			
			
			String json="{ "+
						"  \"from\": { "+
						"        \"user_id\": "+fromID+" "+
						"    }, "+
						"    \"to\": [ "+
						"        { "+
						"            \"user_id\": "+customerID+", "+
						"            \"resource\": \"orders\", "+
						"            \"resource_id\": "+resourceID+", "+
						"            \"site_id\": \"MLU\" "+
						"        } "+
						"    ], "+
						"    \"subject\": \""+asunto+"\", "+
						"    \"text\": { "+
						"    "+    
						"        \"plain\": \"<p>"+message+"</p>\" "+
						"    } "+
						"}";
			sendPost(json,"https://api.mercadolibre.com/messages?access_token="+tok);
		}
		
	}



	private String darIdCustomer(Long idPedidoI, String access_token,String seller) 
	{
		try{
			String URLbase = "https://api.mercadolibre.com";
			
			String funcion = "/orders/search?order.status=paid&seller="+seller+"&limit=50&offset=0&access_token="+access_token+"&q="+idPedidoI;
			String retorno = this.callWSGET(URLbase, funcion);
			//System.out.println(retorno);
			MLPedidos pedidosML = JSONReader.readJsonMeli(retorno);
	        
	        
			for (Result pml : pedidosML.getResults()) 
			{
				
				return String.valueOf(pml.getBuyer().getId());
			}
		}
		catch (Exception e){
			System.out.println("se cae en pedido: "+idPedidoI);
		}
		
			
		return "";
	}
	
					/*
					 * try
						{
							
							
							
							envio = pml.getShipping().getShipping_option().getName();
							costoEnvio = Double.parseDouble(String.valueOf(pml.getShipping().getCost()));
							Long idShipping = pml.getShipping().getId();
							Logica.logPedido((int) (long) pml.getId(), 0, 0, "buscando etiqueta",0);
							String pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token, pml.getId());
							
							if(!pathEtiqueta.equals(""))
							{
								p.setUrlEtiqueta(pathEtiqueta);
							}
						}
						catch(Exception e)
						{
							
							try
							{
								
								
								funcion = "https://api.mercadolibre.com/pickups/"+(Integer)pml.getPickup_id()+"?access_token="+access_token;
								retorno = this.callWSGET(URLbase, funcion);
								
								Pickup pi = JSONReader.readJsonPickup(retorno);
								try
								{
									System.out.println(pi.getStore_info().getId());
								}
								catch (Exception e1)
								{
									
								}
								
								int num = depositosPickHT.get(pi.getStore_info().getDescription());
								String sucursalPick = String.format("%02d", num);
								p.setSucursalPick(sucursalPick);
								p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
								Logica.logPedido((int) (long) pml.getId(), 0, 0, "Agregando Etiqueta de pickup "+sucursalPick,0);
								p.updateEtiqueta(0);
							}
							catch (Exception e2)
							{
								Logica.logPedido((int) (long) pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1);
							}
							
							
							
							
							
						}*/
	
	
	public List<DataIDDescDescripcion> getPedidosMLReporte(String access_token, int idCanal, String seller,String fecha,int i) 
	{
		Logica Logica = new Logica();	
		Long cantidadPasadas = new Long(1);
				
		
		List<DataIDDescDescripcion> pedidos = new ArrayList<>();
		
		
		int error = 0;
		int offset=0;
		boolean pri = true;
		while (cantidadPasadas>=0) 
		{
			try
			{			
			
				String URLbase = "https://api.mercadolibre.com";
				
				
				String funcion = "/orders/search?order.status=paid&seller="+seller+"&order.date_created.from="+fecha+"T00:00:00.000-00:00&limit=50&offset="+offset+"&access_token="+access_token;
				String retorno = this.callWSGET(URLbase, funcion);
				//System.out.println(retorno);
				MLPedidos pedidosML = JSONReader.readJsonMeli(retorno);
		        
		        if(pri)
				{
					pri=false;
					cantidadPasadas = (pedidosML.getPaging().getTotal())/50;
				}
		        
				for (Result pml : pedidosML.getResults()) 
				{
					String[]fech = pml.getDate_created().split("T");
					String f = fech[0];
					String f2=fech[1];
					String[] hora = f2.split("-"); 
					String h = hora[0];
					f=f+" "+h;
					
					DataIDDescDescripcion p = new DataIDDescDescripcion(pml.getId(),"NO SINCRONIZADO",f);	
					if(i==1){
						p.setDescII("Mercado Libre");
					}
					else{
						p.setDescII("Mercado Libre Promos");
					}
					p.setDescripcionII("");
					pedidos.add(p);
				}
			}
			catch(Exception e){
				
			}
			cantidadPasadas--;
			
			offset+=50;
		}
		
		return pedidos;
	}
	
	
	
	public String getEtiML(String access_token, int idCanal, String seller,Long pedido, int idEmpresa){
		Logica Logica = new Logica();
		Long cantidadPasadas = new Long(1);
		
		Fecha fecha = new Fecha(-10, 0, 0);				
		
		List<DataIDDescDescripcion> pedidos = new ArrayList<>();
		List<DataIDDescripcion>depositosPick = Logica.darListaDataIdDescripcionMYSQLConsulta("select idDeposito,nombre from ecommerce_envioml where idDeposito !=0");
		depositosPick.remove(0);
		Hashtable<String, Integer> depositosPickHT = new Hashtable<>();
		for (DataIDDescripcion d : depositosPick) 
		{
			depositosPickHT.put(d.getDescripcion(), d.getId());
		}
		
		int error = 0;
		int offset=0;
		boolean pri = true;
		EncuentraPedido p = new EncuentraPedido();
		p.setIdPedido(pedido);
		
		
		
			try
			{			
			
				String URLbase = "https://api.mercadolibre.com";
				
				
				String funcion = "/orders/search?order.status=paid&seller="+seller+"&order.date_created.from="+fecha.darFechaAnio_Mes_Dia()+"T00:00:00.000-00:00&limit=50&offset="+offset+"&access_token="+access_token+"&q="+pedido;
				String retorno = this.callWSGET(URLbase, funcion);
				//System.out.println(retorno);
				MLPedidos pedidosML = JSONReader.readJsonMeli(retorno);
		        
		        if(pri)
				{
					pri=false;
					cantidadPasadas = (pedidosML.getPaging().getTotal())/50;
				}
		        
				for (Result pml : pedidosML.getResults()) 
				{
					
										
					try
					{					
						String idShipping = pml.getShipping().getId();
						DataIDDescripcion pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token,pml.getId(),idEmpresa);
						int num = depositosPickHT.get(pathEtiqueta.getDescripcionB());
						
						if(!pathEtiqueta.getDescripcion().equals(""))
						{	
							p.setUrlEtiqueta(pathEtiqueta.getDescripcion());
							p.updateEtiqueta(0,idEmpresa);
						
						}
						
						try
						{
							p.updateShipping(num,null,idEmpresa);
						}
						catch(Exception e)
						{
							p.updateShipping(0,null,idEmpresa);
						}								
						
					}
					catch (Exception e)
					{
						
						try
						{
							funcion = "/pickups/"+pml.getPickup_id()+"?access_token="+access_token;
							retorno = this.callWSGET(URLbase, funcion);
							
							Pickup pi = JSONReader.readJsonPickup(retorno);
							System.out.println(pi.getStore_info().getDescription()+"");
							int num = depositosPickHT.get(pi.getStore_info().getDescription());
							String sucursalPick = String.format("%02d", num);
							String personaRetira = pi.getPickup_person().getFull_name();
							p.setPersonaRetira(personaRetira);
							p.setSucursalPick(sucursalPick);
							p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
							Logica.logPedido(pml.getId(), 0, 0, "Agregando Etiqueta de pickup "+sucursalPick,0,idEmpresa);
							p.updateEtiqueta(0,idEmpresa);
							p.updateDestino(num,"",idEmpresa,0.0,false);
						}
						catch (Exception e2)
						{
							Logica.logPedido(pml.getId(), 0, 0, "Error buscando etiqueta, probablemente aun no tiene envio acordado",-1,idEmpresa);
						}
					}
				}
			}
			catch(Exception e){
				
			}
			
		
		return p.getUrlEtiqueta();
	}
	
	public void PostFeedback(Long pedido, List<DataIDDescripcion> tokens){
		/*
			 curl -X POST -H "Content-Type: application/json" -d
			'{
			  "fulfilled": false,
			  "rating": "neutral",
			  "message": "Operation not completed",
			  "reason": "THEY_DIDNT_ANSWER",
			  "restock_item": false,
			  "has_seller_refunded_money": true
			}'
			
			"https://api.mercadolibre.com/orders/{order_Id}/feedback?access_token=$ACCESS_TOKEN"		  
		 */
		String json="{ "+				
				"   	\"fulfilled\": true, "+
				"       \"rating\": \"positive\", "+
				"       \"message\": \"Todo ok.\" "+			
				"}";
	
		try{
			int code = sendPost(json,"https://api.mercadolibre.com/orders/"+pedido+"/feedback?access_token="+tokens.get(0).getDescripcionB());
			
			if (code == 201 || code == 200 || code == 204){
				
			}
			else{
				sendPost(json,"https://api.mercadolibre.com/orders/"+pedido+"/feedback?access_token="+tokens.get(1).getDescripcionB());
			}
		}
		catch(Exception e){
			
		}
	}
	
	public List<Result> getPedidosMLestados(String access_token, int idCanal, String seller,Fecha fecha, String estado) 
	{
		int est=0;
		Long cantidadPasadas = new Long(1);
		List<Result> resultados = new ArrayList<>();	
		
		int error = 0;
		int offset=0;
		boolean pri = true;
		while (cantidadPasadas>=0) 
		{
			try
			{
				String URLbase = "https://api.mercadolibre.com";
								
				String funcion = "/orders/search?order.status="+estado+"&seller="+seller+"&order.date_created.from="+fecha.darFechaAnio_Mes_Dia()+"T00:00:00.000-00:00&limit=50&offset="+offset+"&access_token="+access_token;//+"&q=1859247654";
				String retorno = this.callWSGET(URLbase, funcion);
				//System.out.println(retorno);
				MLPedidos pedidosML = JSONReader.readJsonMeli(retorno);
		        
		        if(pri)
				{
					pri=false;
					cantidadPasadas = (pedidosML.getPaging().getTotal())/50;
				}				
				cantidadPasadas--;
				
				offset+=50;
				
				resultados.addAll(pedidosML.getResults());
			}
			catch(Exception e)
			{
				error++;
				if(error==100)
				{
					cantidadPasadas=new Long(-1);
				}
			}
		}//while
		
		return resultados;		
	}
	
	public void MessagePackToCustomer(Long idPedidoI, String message, String asunto, List<DataIDDescripcion> tokens) throws JsonParseException, JsonMappingException, IOException{
			
		String pack_id = "";
		String tok = "";
		String sell = "";
		String URLbase = "https://api.mercadolibre.com";
		String funcion = "";
		String customerID = "";
		
		try{
			tok = tokens.get(0).getDescripcionB();	
			sell = tokens.get(0).getDescripcion();;
			
			funcion = "/orders/"+idPedidoI+"?access_token="+tok;
			
			String retorno = this.callWSGET(URLbase, funcion);
			//System.out.println(retorno);
			ObjectMapper mapper = new ObjectMapper();		
			Result pedidoML = mapper.readValue(retorno, Result.class);
	        
	        try{
	        	pack_id = String.valueOf(pedidoML.getPack_id());
				customerID = String.valueOf(pedidoML.getBuyer().getId());
	        }catch (Exception e) {
	        	System.out.println("orden "+idPedidoI+" no se encontr? el pack del cliente -->Canal 1");
				tok=tokens.get(1).getDescripcionB();
				sell=tokens.get(1).getDescripcion();
				
				funcion = "/orders/"+idPedidoI+"?access_token="+tok;
				
				retorno = this.callWSGET(URLbase, funcion);
				mapper = new ObjectMapper();		
				pedidoML = mapper.readValue(retorno, Result.class);
				
				if(retorno.contains("Something went wrong")){
					System.out.println("orden "+idPedidoI+" no se encontr? el pack del cliente -->Canal 2");
				}
				else{
					pack_id = String.valueOf(pedidoML.getPack_id());
					customerID = String.valueOf(pedidoML.getBuyer().getId());
				}
				
			}
									
			
			if(!pack_id.equals("")){
			
				System.out.println("se encontr? el cliente");
				if(pack_id=="null"){
					funcion = "https://api.mercadolibre.com/messages/packs/"+idPedidoI+"/sellers/"+sell+"?access_token="+tok;
				}
				else{
					funcion = "https://api.mercadolibre.com/messages/packs/"+pack_id+"/sellers/"+sell+"?access_token="+tok;
				}
				
				String json="{ "+
							"  \"from\": { "+
							"        \"user_id\": "+sell+" "+
							"    }, "+
							"    \"to\":  "+
							"        { "+
							"            \"user_id\": "+customerID+" "+
							"        }, "+
							"    \"text\":  \"<p>"+message+"</p>\" "+
							"}";
				sendPost(json,funcion);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
