package integraciones.mains.elRey;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.itextpdf.text.DocumentException;

import beans.Fecha;
import beans.Usuario;
import beans.api.BarcodeReader;
import beans.api.ImpresionesPDF_API;
import beans.api.json.SendMail;
import beans.api.json.SendMailSpooler;
import beans.datatypes.DataIDDescripcion;
import beans.derivation.OrderDerivator;
import beans.encuentra.Compras;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import beans.encuentra.EncuentraPedidoArticuloReq;

import beans.encuentra.Items;
import beans.helper.PropertiesHelperAPI;
import logica.LogicaAPI;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.wms.Call_WS_APIENCUENTRA;


public class SinPedidosProducteca 
{
	static LogicaAPI lo = new LogicaAPI();
	public static void main(String[] args) 
	{	
		
		LogicaAPI logica = new LogicaAPI();
		String token = logica.darToken(4,4000);
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		
		int idEmpresa = u.getIdEmpresa();
		Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
		System.out.println(token);
		setOrdersCancelled(idEmpresa, token, cen, logica);
		List<DataIDDescripcion> canalesFenicioALL = cen.DarDatosPutOrders(token,1);
		getCustomers(idEmpresa, cen, token);
		getPedidos(idEmpresa, canalesFenicioALL,cen, token, logica);
		
		//cen.verificarRepoArticulos(token);
	}
	private static void getCustomers(int idEmpresa,Call_WS_APIENCUENTRA cen, String token)
	{
		List<beans.encuentra.Cliente> clientes = lo.darClientesProducteca();
		//List<beans.encuentra.Cliente> clientes = lo.reDarClientesProducteca(); solo para resincronizar ver en logica le pasan los ID de pedido
		
		lo.saveImportCustomer(clientes, idEmpresa);
		List<DataIDDescripcion> retorno = cen.SaveCustomers(token, clientes);
		
	}

	private static void getPedidos(int idEmpresa, List<DataIDDescripcion> canalesFenicioALL,Call_WS_APIENCUENTRA cen, String token, LogicaAPI logica) 
	{
		
		
		Fecha fechaActual = new Fecha();
		
		Hashtable<Integer, Integer> canalesHT = new Hashtable<>();
		
		for(DataIDDescripcion c:canalesFenicioALL)
		{
			canalesHT.put(c.getId(), c.getId());
		}
		
		
		Hashtable<Long, EncuentraPedido> pedidosHT = new Hashtable<>();
		List<Compras> compras = lo.sincroPedidosWeb(idEmpresa,token);
		
		
		
		List<EncuentraPedido> listaPedidos = new ArrayList<>();
		List<EncuentraPedido> pedidosTienda = new ArrayList<EncuentraPedido>();
		
		for (Compras c : compras) 
		{
			/*	
			if(c.getCompra().getItems().size()==0)
			{	
					Logica.persistir("insert into aaatemporal (id,IdEmpresa) values ("+c.getCompra().getId()+","+idEmpresa+")");				
			}
			*/
			if(c.getCompra().getItems().size()!=0)
			{					
				System.out.println(c.getCompra().getId());
				
				
					
				EncuentraPedido p = new EncuentraPedido();
				p.setDescripcion(c.getCliente().getNombre()+" - "+ c.getCliente().getTelefono());
					
				int cantidad=0;
				Double descuento=0.0;
					
				p.setTicketNumber("");
				p.setCanalAnaloga(Integer.parseInt(c.getCompra().getIdCanal()));
				p.setEstado(c.getCompra().getEstado());
				
				p.setUrlEtiqueta(c.getEtiqueta());
				List<EncuentraPedidoArticulo> pedidos = new ArrayList<>();
				p.setIdPedido(new Long(c.getCompra().getId()));
				
				p.setIdPedidoSTR(c.getCompra().getId());
				
				p.setIdFenicio(c.getCompra().getIdVenta());
				
				p.setFreshipping(c.getCompra().isFreeshipping());
				
				Double importeTotal = 0.0; 
				Double importetotalOV = 0.0;
				
				Double importeEnvio = 0.0;
				try
				{
					importeEnvio= Double.parseDouble(c.getCompra().getMontoEnvio());
				}
				catch (Exception e) 
				{
					
				}
				Double importeCompra = 0.0;
				try
				{
					importeCompra= Double.parseDouble(c.getCompra().getImporte());
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				if(importeEnvio==importeCompra)
				{
					importetotalOV = importeEnvio;
				}
				else
				{
					importetotalOV = importeCompra;
				}
				
				
				p.setMontoEnvio(importeEnvio);
				p.setPrecio(importeCompra);
				
				List<OrdenVentaLinea> ventaLineas = new ArrayList<>();
				
				Double costoEnvio = 0.0;
				try {costoEnvio = Double.parseDouble(c.getCompra().getMontoEnvio());} catch (Exception e) {}	
				
				if(costoEnvio>0.0 && !c.getCompra().getArtEnvio().equals("")) {
					ventaLineas.add(new OrdenVentaLinea(costoEnvio, "1", c.getCompra().getArtEnvio()));
				}
				
				List<DataIDDescripcion> movsStock = new ArrayList<>();
				
				p.setSucursalPick(c.getCompra().getSucursal());
				DataIDDescripcion shippingType = new DataIDDescripcion(0,"");
				boolean procesarPickUp = false;
				try {
					int destino = Integer.parseInt(p.getSucursalPick());
					if(destino > 0 && destino <= 1200) {
						shippingType.setId(2);
						if(destino == 1) {
							procesarPickUp = true;
							shippingType.setId(3);
							p.setSucursalPick("101");
							destino = 101;
						}
					}
					else {
						shippingType.setId(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				for (Items it : c.getCompra().getItems()) 
				{
					Double importe = Double.parseDouble(it.getImporte());
					int cantidadItem = Integer.parseInt(it.getCant()); 
					//articulos para OV
					OrdenVentaLinea ovl = new OrdenVentaLinea(importe, it.getCant(), it.getSku());
					ventaLineas.add(ovl);
										
					//articulos para WMS
					List<DataIDDescripcion> articulosItm = new ArrayList<>(); 
					String articulo = it.getSku();
					
					List<DataIDDescripcion> comboArticulos = logica.darArticulosCombo(articulo, idEmpresa);
					
					
					if(articulo.contains("+"))
					{
						String articulosArr [] = articulo.split("\\+");
						for (int i = 0; i < articulosArr.length; i++) 
						{
							int qty = 0;
							String cadena = articulosArr [i];
							if(cadena.contains("*"))
							{
								String arr [] = cadena.split("\\*");
								DataIDDescripcion toIn = new DataIDDescripcion((Integer.parseInt(arr[0]))*cantidadItem, arr[1] );
								toIn.setDescripcionB(it.getVariacion());
								articulosItm.add(toIn);
							}
							else
							{
								DataIDDescripcion toIn = new DataIDDescripcion(cantidadItem, cadena );
								toIn.setDescripcionB(it.getVariacion());
								articulosItm.add(toIn);
							}
							
						}
					}
					else if(articulo.contains("*"))
					{
						String arr [] = articulo.split("\\*");
						DataIDDescripcion toIn = new DataIDDescripcion((Integer.parseInt(arr[0]))*cantidadItem, arr[1] );
						toIn.setDescripcionB(it.getVariacion());
						articulosItm.add(toIn);
					}
					else if(!comboArticulos.isEmpty())
					{
						for (DataIDDescripcion a : comboArticulos) 
						{
							DataIDDescripcion toIn = new DataIDDescripcion( a.getId()*cantidadItem, a.getDescripcion() );
							toIn.setDescripcionB(it.getVariacion());
							articulosItm.add(toIn);
						}
					}
					else 
					{
						DataIDDescripcion toIn = new DataIDDescripcion(cantidadItem, articulo );
						toIn.setDescripcionB(it.getVariacion());
						articulosItm.add(toIn);
					}
					
					Hashtable<String, DataIDDescripcion> hash = new Hashtable<>();
					
					for (DataIDDescripcion a : articulosItm) 
					{
						if(hash.get(a.getDescripcion())!=null)
						{
							DataIDDescripcion d = hash.get(a.getDescripcion());
							d.setId(a.getId()+d.getId());
							
						}
						else
						{
							hash.put(a.getDescripcion(),a);
						}
					}
					
					
					
					List<DataIDDescripcion> articulosItmSUM = new ArrayList<>(hash.values());
															
					for (DataIDDescripcion aritdesc : articulosItmSUM) 
					{
						cantidad+=aritdesc.getId();
						EncuentraPedidoArticulo art = new EncuentraPedidoArticulo();
														
						art.setArticulo(aritdesc.getDescripcion());
						art.setDistribucionAfectaSTR(it.getDocVisual());
						
						if(shippingType.getId() == 3) {
							art.setOrigen(101);
						}else {
							art.setOrigen(Integer.parseInt(it.getOrigen()));
						}
						
						
						
						
						art.setCantidad(aritdesc.getId());
						art.setVariacion(aritdesc.getDescripcionB());
						art.setProcesada(0);
						art.setClickCollect(procesarPickUp);
						pedidos.add(art);
						importeTotal+=importe;
						
						//articulos para movstock
						DataIDDescripcion movS = new DataIDDescripcion(aritdesc.getId(), aritdesc.getDescripcion());
						movsStock.add(movS);
					}
					
				}
				p.setCantidad(cantidad);
				p.setDescuento(descuento);
				p.setArticulosPedido(pedidos);
				
				p.setTrackingNumber(c.getCompra().getTrackingNumber());
				
				
				p.setArticulosPedidoReq(new ArrayList<>());
				p.setFormaPago(c.getCompra().getMetodoPago());
				
				
				Double porcDescuento = (descuento*100)/importeTotal;
				if(descuento==0 && importeTotal==0)
				{
					porcDescuento=0.0;
				}
				
				String urlE ="";
				if(c.getCompra().getZpl()!=null)
				{
					if(c.getCompra().getZpl().contains("^XA^") || c.getCompra().getZpl().contains("^XA\n^"))
					{
						urlE = downloadPDFZPL("http://api.labelary.com/v1/printers/8dpmm/labels/4x6/0/", c.getCompra().getZpl(), c.getCompra().getId());
					}
				}
				
				if(p.getSucursalPick().equals("90000") && !p.getTrackingNumber().startsWith("UES")) {
					PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
					try {
						pH.loadProperties();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String path = pH.getValue("pdf")+"/"+p.getIdPedido()+".pdf";;
					BarcodeReader br = new BarcodeReader(path, 90000, 1);
					if(br!=null && !br.getBarcodes().isEmpty()) {
			        	 String barra = br.getBarcodes().get(0);
			        	 p.setTrackingNumber(barra);
				     }
				}
				
				
				
				p.setShippingType(shippingType);				
				  
				p.setUrlEtiqueta(urlE);
				p.setFecha(c.getCompra().getFecha());
				
				//PERSISTO ORDEN
				try {
					String rut = "0";
					if(c.getCliente().getDocumentoNro().length()>8) {
						rut = c.getCliente().getDocumentoNro();
					}
					
					OrdenVenta orden = new OrdenVenta(0, porcDescuento*-1, c.getCompra().getMoneda(), 
							c.getCliente().getDocumentoNro()+"", c.getCliente().getNombre(), 
							c.getCliente().getCalle()+" "+c.getCliente().getNroPuerta()+" / "+c.getCliente().getNroApto(), rut, 
							c.getCliente().getTelefono(), ventaLineas, Long.parseLong(c.getCompra().getId()),200, 
							c.getCompra().getMetodoPago()+" "+fechaActual.darFechaAnio_Mes_Dia(),0,importetotalOV);
					orden.setCliMail(c.getCliente().getEmail());
					orden.save("",idEmpresa);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				//PERSISTO MOVIMIENTOS DE STOCK
				logica.RegistrarMovimientoStock(1, 1200, 0, movsStock, idEmpresa, new Long (c.getCompra().getId()), 0, 0, false);
				
				listaPedidos.add(p);
				if(p.getShippingType().getId() == 3) {
					pedidosTienda.add(p);
				}
				
			}
		}
		
		if(listaPedidos.size()>0) {
			List<DataIDDescripcion> retorno = cen.SaveOrders(token, listaPedidos);
			String inns = "";
			String fails = "";
			String idMail = "";
			
			for (DataIDDescripcion d : retorno) 
			{
				if(d.getDescripcionB().equals("FAIL"))
				{
					fails+=d.getDescripcion()+",";
					idMail = d.getDescripcion();
				}
				else
				{
					inns+=d.getDescripcion()+",";
				}
			}
			
			if(!inns.equals("")) 
			{
				inns = inns.substring(0,inns.length()-1);
				lo.updatePedidoSinc(inns,1);
				
				
				List<EncuentraPedido> listaPedidosAReq = new ArrayList<>();
				
				for (EncuentraPedido p : listaPedidos) 
				{
					p.setIdPedido(Long.parseLong(p.getIdPedidoSTR()));
					boolean actualizalo = false;
					String [] pedidos = inns.split(","); 
					for (int i = 0; i < pedidos.length; i++) 
					{
						if(pedidos[i].equals(p.getIdPedidoSTR()))
						{
							actualizalo = true;
							break;
						}
						
					}
					
					if(actualizalo)
					{
						//aca hay que actualizar los destinos
						double montoEnvio = 0.0;
						if(p.getMontoEnvio()!=null)
						{
							montoEnvio =p.getMontoEnvio(); 
						}
						
						cen.updateDestinoPedido(token, p,Integer.parseInt(p.getSucursalPick()), montoEnvio);
						
						//aca hay que actualizar los articulosREQ
						listaPedidosAReq.add(p);
					
					}
				}
				 
				
				cen.SaveOrdersArticulosReq(token, listaPedidosAReq,0);
				
				List<SendMail> mails = new ArrayList<SendMail>();
				ImpresionesPDF_API ip = new ImpresionesPDF_API(4);
				OrderDerivator od = new OrderDerivator();
				try {
					if(!pedidosTienda.isEmpty()) {
						ip.ImprimirPedidosArticuloReq(pedidosTienda, 3);
						mails.add(od.mailsSolicitudPickup(pedidosTienda, new DataIDDescripcion(1,"")));
						LogicaAPI.PutMailSpooler(mails.toArray(new SendMail[mails.size()]), 4);
					}
					
				} catch (FileNotFoundException | DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			if(!fails.equals("")) {
				fails = fails.substring(0,fails.length()-1);
				lo.updatePedidoSinc(fails,-1);
				
				String bodyName = "Los siguientes pedidos no se sincronizaron, o se sincronizaron incorrectamente: "+fails;
				SendMail mail = new SendMail("SREY"+idMail, "gdelisa@200.com.uy,onviera@200.com.uy,gmonzon@200.com.uy", "Error en sincro de pedidos - EL REY", bodyName, "encuentra@200.com.uy",null);
				SendMailSpooler mails = new SendMailSpooler();
				mails.setMails(new SendMail[]{mail});
				
				LogicaAPI.PutMailSpooler(mails.getMails(), 4);
			}	
		}
			
		
		
	}
	
	public static String downloadPDFZPL(String url, String parameter,String idShipping) 
	{
		try
		{
			
			PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
			pH.loadProperties();
			String path = pH.getValue("pdf");
			String pathH = pH.getValue("HTTP_pdf");
			
			
			String filePath = path+"/"+idShipping+".pdf";
			String patHTTH = pathH+"/"+idShipping+".pdf";
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
	        	return patHTTH;
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
	
	private static void setOrdersCancelled(int idEmpresa, String token, Call_WS_APIENCUENTRA cen, LogicaAPI l) {
		
		try {
			List<DataIDDescripcion> sinDespachar = cen.ordersNotDispatched(0, token);
			if(sinDespachar!= null && !sinDespachar.isEmpty()) {
				String pedidos = "";
				
				for(DataIDDescripcion d: sinDespachar) {
					pedidos += d.getDescripcion()+",";
				}
				pedidos = pedidos.substring(0,pedidos.length()-1);
				List<DataIDDescripcion> pedidosCancelados = l.pedidosCanceladosAPI(pedidos,idEmpresa);
				
				List<DataIDDescripcion> cancelar = new ArrayList<>();
				for (DataIDDescripcion d : pedidosCancelados) {
					DataIDDescripcion data = new DataIDDescripcion(99,d.getDescripcion());
					cancelar.add(data);
				}
				
				if(!cancelar.isEmpty()) {
					cen.updateOrdersStatus(token, cancelar);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
