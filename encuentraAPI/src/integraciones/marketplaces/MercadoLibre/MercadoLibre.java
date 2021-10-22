package integraciones.marketplaces.MercadoLibre;

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
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import beans.Fecha;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.Cliente;
import beans.encuentra.Clientes;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import beans.encuentra.EncuentraPedidoArticuloReq;
import beans.helper.PropertiesHelperAPI;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.marketplaces.MercadoLibre.entidades.Buyer;
import integraciones.marketplaces.MercadoLibre.entidades.JSONRespPedidos;
import integraciones.marketplaces.MercadoLibre.entidades.MLPagos;
import integraciones.marketplaces.MercadoLibre.entidades.MLPedidos;
import integraciones.marketplaces.MercadoLibre.entidades.MLUitem;
import integraciones.marketplaces.MercadoLibre.entidades.Order_item;
import integraciones.marketplaces.MercadoLibre.entidades.Payment;
import integraciones.marketplaces.MercadoLibre.entidades.Pickup;
import integraciones.marketplaces.MercadoLibre.entidades.Result;
import integraciones.marketplaces.MercadoLibre.entidades.Shipping;
import integraciones.marketplaces.objetos.marketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;
import integraciones.marketplaces.MercadoLibre.entidades.TokenData;



public class MercadoLibre extends marketPlace{
	
	public MercadoLibre() {
		super();
		this.idEmpresa = 1;
		this.setIdMarketPlace(3);
		setCanales();
		//this.canales = null;
		this.etiquetaEnvios = false;
		this.MovStocks = false;
		this.ordenes = false;
		this.matrizDisponibilidad = true;
	}
	
	private Hashtable<Long,List<Result>> ConsolidarCarritos(List<Result> pmls){
		Hashtable<Long,List<Result>> carritos = new Hashtable<Long,List<Result>>();
		for(Result pml : pmls) {
			if(pml.getPack_id() == null) {
				List<Result> lista = new ArrayList<Result>();
				lista.add(pml);
				carritos.put(pml.getId(), lista);
			}
			else {
				if(carritos.get(pml.getPack_id())==null) {
					List<Result> lista = new ArrayList<Result>();
					lista.add(pml);
					carritos.put(pml.getPack_id(), lista);
				}
				else {
					carritos.get(pml.getPack_id()).add(pml);
				}
			}
		}
		return carritos;
	}
	
	public String getToken(String id, String secret)
	{
		//https://api.mercadolibre.com
		
		
		String URLbase = "https://api.mercadolibre.com";
		//String funcion = "/oauth/token?grant_type=client_credentials&client_id=5690238116767269&client_secret=R9VaoeTj8XKeo7MSi50wUTVXuTGSMXgk";
		String funcion = "/oauth/token?grant_type=client_credentials&client_id="+id+"&client_secret="+secret;
		String retorno = this.callWSPOSTParam("", URLbase+funcion);
		Gson gson = new Gson();
		TokenData tokenData = gson.fromJson(retorno, TokenData.class);
		return tokenData.getAccess_token();
	}
	
	
	
	@Override
	public List<EncuentraPedido> getPedidos(int canal, String status, int dias, Map<String, String> pedidosIn, Map<String, Integer> depositosPickHT) 
	{
		List<EncuentraPedido> pedidosAll = new ArrayList<EncuentraPedido>();
		Gson gson = new Gson();
		int est=0;
		Long cantidadPasadas = new Long(1);
		Fecha fecha = new Fecha(-dias, 0, 0);
		
		Fecha fechaActual = new Fecha();
		
		String access_token = this.getToken(this.getCanales().get(canal).getUser(), this.getCanales().get(canal).getPass()); 
		String seller = this.getCanales().get(canal).getSeller();
			
		int error = 0;
		int offset=0;
		boolean pri = true;
		while (cantidadPasadas>=0) 
		{
			try
			{		
				String URLbase = "https://api.mercadolibre.com";				
				
				String funcion = "/orders/search?order.status=paid&seller="+seller+"&order.date_created.from="+fecha.darFechaAnio_Mes_Dia()+"T00:00:00.000-00:00&limit=50&offset="+offset+"&access_token="+access_token;//&q=46622730806;
				String retorno = this.callWSGET(URLbase, funcion);
				//System.out.println(retorno);
				MLPedidos pedidosML = gson.fromJson(retorno, MLPedidos.class);
		        if(pri)
				{
					pri=false;
 					cantidadPasadas = (pedidosML.getPaging().getTotal())/50;
				}
		        
		        int pasada=1;
		        Hashtable<Long, List<Result>> carritos = ConsolidarCarritos(pedidosML.getResults());
		        Set<Long> keys = carritos.keySet();
				for(Long key: keys) {
				
					List<Result> pmls = carritos.get(key);
					Result pml = carritos.get(key).get(0);
					System.out.println(pedidosIn.get(key+""));					
					String envio="";
					if(pml.getId()== new Long("4108860379"))
					{
						System.out.println("caso");
						System.out.println(pml.getId());
					}				
					
					
					if(pedidosIn.get(key+"")==null)
					{ //es un pedido nuevo		
						
						//seteo datos del cliente
						funcion = "/orders/"+pml.getId();
						retorno = callWSGETauth(URLbase, funcion, access_token);
						
						Result PMLBUYER = gson.fromJson(retorno,Result.class);
						
						pml.setBuyer(PMLBUYER.getBuyer());
						
						//seteo documento
						funcion = "/orders/"+pml.getId()+"/billing_info";
						retorno = this.callWSGETauth(URLbase, funcion, access_token);
						/*retorno = retorno.replace("{\"billing_info\":", "");
						retorno = retorno.replaceFirst("}", "");*/
						
		
						gson = new Gson();
						try {
							Buyer billing_info = gson.fromJson(retorno,Buyer.class);
							
							pml.getBuyer().setBilling_info(billing_info.getBilling_info());
							System.out.println(pml.getBuyer().getBilling_info().getDoc_type()+" "+pml.getBuyer().getBilling_info().getDoc_number()+" - "+ pml.getBuyer().getFirst_name()+" "+pml.getBuyer().getLast_name());
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						
						
						System.out.println("sincronizando vta "+key);
						
						
						String idPayment="";
						MLPagos pagos = new MLPagos();
						double longo = 0.0;
						try {							
							for(Result line: pmls) {
								for(Payment p:line.getPayments()){
									if(p.getDate_approved()!=null){
										idPayment += p.getId()+" "+p.getDate_approved().substring(0, 10)+",";
										funcion = "/collections/"+p.getId()+"?access_token="+access_token;
										retorno = this.callWSGET(URLbase, funcion);
										pagos = gson.fromJson(retorno, MLPagos.class);
										longo += pagos.getTransaction_amount();
									}							
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						
						idPayment = idPayment.substring(0,idPayment.length());
						
						
						EncuentraPedido p = new EncuentraPedido();
						p.setIdPedido(key);
						p.setMl(1);
						p.setPrecio(0.0);
						p.setMontoEnvio(0.0);
						
						if(p.getIdPedido()<0){
							System.out.println(key);
							System.out.println(p.getIdPedido());
						}
						/*************le seteo el canal*************/
						p.setCanalMercadoLibre(new DataIDDescripcion(canal, ""));						
						
						int cantidad=0;
						Double descuento=0.0;
						
						p.setEstado("mercadoLibre");
						p.setUrlEtiqueta("");
						
						List<EncuentraPedidoArticulo> pedidos = new ArrayList<EncuentraPedidoArticulo>();
						List<OrdenVentaLinea> ventaLineas = new ArrayList<OrdenVentaLinea>();
						
						Double importeTotal = 0.0; 
						
						p.setSucursalPick(""); 
						Double costoEnvio = 0.0;
						
						
						try{
							for(Payment pp:pml.getPayments()){
								costoEnvio = Double.parseDouble(String.valueOf(pp.getShipping_cost()));
								if(costoEnvio!=0) {
									break;
								}								
							}
						}
						catch(Exception e){
							System.out.println("error costo de envio");
						}
						
						if(costoEnvio>0.0)
						{
							ventaLineas.add(new OrdenVentaLinea(costoEnvio, "1", "0002000"));		
							p.setMontoEnvio(costoEnvio);
						}
						
						p.setDescripcion("MELI: "+ pml.getBuyer().getFirst_name()+" "+pml.getBuyer().getLast_name());
						
						for(Result line: pmls) {
							for (Order_item it : line.getOrder_items()) 
							{
								Double importe = it.getUnit_price();
								int cantidadItem = (int) it.getQuantity(); 
								importe = importe*cantidadItem;
								if(importe>0)
								{
									cantidad+=cantidadItem;
									EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
									art.setImporte(it.getUnit_price());
									art.setArticulo(it.getItem().getSeller_custom_field());
									art.setCantidad(cantidadItem);
									art.setProcesada(0);
									pedidos.add(art);
									importeTotal+=importe;
									try
									{
										OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", it.getItem().getSeller_custom_field());
										
										ventaLineas.add(ovl);
										
										//ME FIJO SI ES DE LA TIENDA CLARKS
										
										if(it.getItem().getSeller_custom_field().contains("061.")){
											funcion = "/items/"+it.getItem().getId();
											retorno = this.callWSGET(URLbase, funcion);
											MLUitem mlu = gson.fromJson(retorno,MLUitem.class);
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
											
											ventaLineas.add(ovl);
											
											//ME FIJO SI ES DE LA TIENDA CLARKS
											
											if(it.getItem().getSeller_sku().contains("061.")){
												funcion = "/items/"+it.getItem().getId();
												retorno = this.callWSGET(URLbase, funcion);
												MLUitem mlu = gson.fromJson(retorno,MLUitem.class);
												 if(mlu.getOfficial_store_id().equals("542")){
												 		p.setCanalMercadoLibre(new DataIDDescripcion(-2, ""));
												  }
											}
										}catch (Exception ex) {
											OrdenVentaLinea ovl = new OrdenVentaLinea(importe, cantidadItem+"", "SINARTICULO");
											ventaLineas.add(ovl);
										}
										
									}
									
									
								}
								else
								{
									descuento+=importe;
								}
								
								
								
							}
						}
						
						System.out.println("");
						p.setCantidad(cantidad);
						p.setDescuento(descuento);
						p.setArticulosPedido(pedidos);
						
						p.setArticulosPedidoReq(new ArrayList<EncuentraPedidoArticuloReq>());
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
						
						try
						{						
							String idShipping = pml.getShipping().getId();
							DataIDDescripcion pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token,pml.getId());
							int destino = depositosPickHT.get(pathEtiqueta.getDescripcionB());
							
							if(!pathEtiqueta.getDescripcionB().equals("")){
								envio+=pathEtiqueta.getDescripcionB();
							}
															
							if(!pathEtiqueta.getDescripcion().equals(""))
							{	
								p.setUrlEtiqueta(pathEtiqueta.getDescripcion());
							}
							p.setIdDepositoEnvio(destino);
							p.setShippingType(new DataIDDescripcion(2,""));							
							
						}
						catch (Exception e)
						{								
							try
							{
								funcion = "/pickups/"+pml.getPickup_id()+"?access_token="+access_token;
								retorno = this.callWSGET(URLbase, funcion);
								
								Pickup pi = gson.fromJson(retorno,Pickup.class);
								System.out.println(pi.getStore_info().getDescription()+"");
								int num = depositosPickHT.get(pi.getStore_info().getDescription());
								String sucursalPick = String.format("%02d", num);
								envio+="Pickup "+sucursalPick;
								p.setIdDepositoEnvio(Integer.parseInt(sucursalPick));
								String personaRetira = pi.getPickup_person().getFull_name();
								p.setPersonaRetira(personaRetira);
								p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
								
								p.setShippingType(new DataIDDescripcion(1,""));		
							}
							catch (Exception e2)
							{
								System.out.println("error");
							}
						}					
						
						for(Result line: pmls) {
							setNoteToOrder(line.getId(),access_token, "Sincronizado por encuentra ");
						}
						
						
						System.out.println("Pedido "+key+" gurdado");
												
						Cliente cli = darCliente(pml,pagos);
						cli.setIdPedido(p.getIdPedido()+"");
						p.setCliente(cli);						
						
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
											
						Double importePago = longo + costoEnvio;					
						OrdenVenta orden = new OrdenVenta(Integer.parseInt(cli.getDocumentoNro()), porcDescuento*-1, moneda, cli.getDocumentoNro(), 
								p.getDescripcion(), cli.getCalle(), "", cli.getTelefono(), ventaLineas,key,2003, 
								""+idPayment+" "+fechaActual.darFechaAnio_Mes_Dia(),1,importePago);
						orden.setCliMail("");
						
						orden.setComentario("ENVIO:"+envio);
						//orden.setFormaPagoVisual("MP");
						p.setOrden(orden);
											
						pedidosAll.add(p);
					}//if
										
				System.out.println(" ");
				System.out.println(" ");
				System.out.println(" ");
				System.out.println(" ");
				System.out.println(" ");
				System.out.println(" ");
				System.out.println(" ");
				System.out.println(" Pasada "+pasada);
				pasada++;
				}//for
				
				cantidadPasadas--;
				
				offset+=50;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				error++;
				if(error==10)
				{
					cantidadPasadas=new Long(-1);
				}
			}
		}//while
		
		return pedidosAll;
		
	}
	
	private Cliente darCliente(Result pml, MLPagos pagos) 
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
		
		try {
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
		} catch (Exception e) {
			// TODO: handle exception
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
		String documentoTipo = "";
		String documentoNro = "";
		try {
			documentoTipo = pml.getBuyer().getBilling_info().getDoc_type().toString();
			documentoNro = pml.getBuyer().getBilling_info().getDoc_number().toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
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
		
		
		return new Cliente(apellido, nombre, localidad, email, departamento, telefono, 
				nroPuerta, nroApto, documentoTipo, calle, documentoNro,"0","", pml.getId()+"");
	}
	
	public String downloadPDFZPL(String url, String parameter,String idShipping) 
	{
		try
		{
			PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
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
	
	public String downloadZIP(String urlZip) 
	{
		try
		{
			PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
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
	
	public DataIDDescripcion pdfEtiquetaML (String idShipping, String access_token, Long idPedido)
	{
		Gson gson = new Gson();
		String urlShipping = "//shipments/"+idShipping+"?access_token="+access_token;
		String URLbase = "https://api.mercadolibre.com";
		String retorno = this.callWSGET(URLbase, urlShipping);
		//System.out.println(retorno);
		Shipping ship = gson.fromJson(retorno, Shipping.class);
		
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
			    	PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
					pH.loadProperties();
					String path = pH.getValue("HTTP_pdf");
					pathTopdf=path+"/"+idShipping+".pdf";
			    }
			    catch (Exception e)
			    {
			    	
			    }
			    
			    //Logica.logPedido(idPedido, 0, 0, "pedido, con etiqueta: <a href=\""+pathTopdf+"\">Ver etiqueta</a>",0);
			}
			else
			{
				//Logica.logPedido(idPedido, 0, 0, "etiqueta NO encontrada",-1);
			}
			
			data.setDescripcion(pathTopdf);
			
			return data;
			
		}
		else
		{
			//Logica.logPedido(idPedido, 0, 0, "No se buscó etiqueta porque no está en estado impreso",1);
			return data;
		}
		
	}
	
	public void setNoteToOrder(Long idOrder, String token, String texto)
	{
		String URLbase = "https://api.mercadolibre.com";
		String funcion = "/orders/"+idOrder+"/notes?access_token="+token;
		
		Fecha fecha = new Fecha();
		
		
		String jotason = "{\"note\": \""+texto+" "+fecha.darFechaAnio_Mes_Dia_hhmm()+"\"}";
		
		this.callWSPOSTParam(jotason,URLbase+funcion);	
		//this.sendPut(jotason,URLbase+funcion);
	}
	
	@Override
	public List<EncuentraPedido> buscarEtiquetas(List<DataIDDescripcion> pedidosSinE, Call_WS_APIENCUENTRA cen, String token, int canal, Map<String, Integer> depositosPickHT)
	{
		List<EncuentraPedido> pedidos = new ArrayList<EncuentraPedido>();
		Gson gson = new Gson();
		int est=0;
		Long cantidadPasadas = new Long(1);
		Fecha fecha = new Fecha(-30, 0, 0);		
		Fecha fechaActual = new Fecha();		
		String access_token = this.getToken(this.getCanales().get(canal).getUser(), this.getCanales().get(canal).getPass()); 
		String seller = this.getCanales().get(canal).getSeller();			
		int offset=0;
		String URLbase = "https://api.mercadolibre.com";
		for(DataIDDescripcion se : pedidosSinE) {
			try
			{								
				String funcion = "/orders/search?order.status=paid&seller="+seller+"&order.date_created.from="+fecha.darFechaAnio_Mes_Dia()+
						"T00:00:00.000-00:00&limit=50&offset="+offset+"&access_token="+access_token+"&q="+se.getDescripcion();
				String retorno = this.callWSGET(URLbase, funcion);
				//System.out.println(retorno);
				MLPedidos pedidosML = gson.fromJson(retorno, MLPedidos.class);
		        	        
				for(Result pml: pedidosML.getResults()) {
					EncuentraPedido p = new EncuentraPedido();
					p.setIdPedido(Long.parseLong(se.getDescripcion()));
					p.setTrackingNumber(se.getDescripcion());
					String envio = "";
					try
					{									
						String idShipping = pml.getShipping().getId();
						DataIDDescripcion pathEtiqueta = pdfEtiquetaML(String.valueOf(idShipping), access_token,pml.getId());
						int destino = depositosPickHT.get(pathEtiqueta.getDescripcionB());
						
						if(!pathEtiqueta.getDescripcionB().equals("")){
							envio+=pathEtiqueta.getDescripcionB();
						}
														
						if(!pathEtiqueta.getDescripcion().equals(""))
						{	
							p.setUrlEtiqueta(pathEtiqueta.getDescripcion());
						}
						p.setIdDepositoEnvio(destino);
						p.setShippingType(new DataIDDescripcion(2,""));							
						
					}
					catch (Exception e)
					{								
						try
						{
							funcion = "/pickups/"+pml.getPickup_id()+"?access_token="+access_token;
							retorno = this.callWSGET(URLbase, funcion);
							
							Pickup pi = gson.fromJson(retorno,Pickup.class);
							System.out.println(pi.getStore_info().getDescription()+"");
							int num = depositosPickHT.get(pi.getStore_info().getDescription());
							String sucursalPick = String.format("%02d", num);
							envio+="Pickup "+sucursalPick;
							p.setIdDepositoEnvio(Integer.parseInt(sucursalPick));
							String personaRetira = pi.getPickup_person().getFull_name();
							p.setPersonaRetira(personaRetira);
							p.setUrlEtiqueta("https://www.stadium.com.uy/public/ctm/"+sucursalPick+".pdf");
							
							p.setShippingType(new DataIDDescripcion(1,""));		
						}
						catch (Exception e2)
						{
							System.out.println("error");
						}
					}
					if(p.getUrlEtiqueta()!= null && !p.getUrlEtiqueta().equals(""))
						pedidos.add(p);
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
				
		return pedidos;
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
	
	public String callWSGETauth(String URLbase, String funcion, String auth)
	{
		String retorno = "";
		HttpClient httpClient = new DefaultHttpClient();
		
	    
	    try 
		{
	    	HttpGet httpGetRequest = new HttpGet(URLbase+funcion);
	    	httpGetRequest.addHeader("Authorization", "Bearer "+auth);
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

}
