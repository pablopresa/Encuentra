package beans.derivation;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beans.Fecha;
import beans.api.ImpresionesPDF_API;
import beans.api.json.SendMail;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.StockDeposito;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import integraciones.erp.odoo.laIsla.ArticuloCantidadEncuentra;
import integraciones.erp.odoo.laIsla.ClienteOdoo_LaIsla;
import integraciones.erp.odoo.laIsla.StockArticulos;
import integraciones.marketplaces.fenicioTrack.FenicioLAISLA;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class OrderDerivator 
{

	public OrderDerivator() {
	}



	

	public static void main(String[] args) 
	{
		FenicioLAISLA f = new FenicioLAISLA();
		LogicaAPI logica = new LogicaAPI();
		String token = logica.darToken(f.getIdEmpresa());
		
		Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
		List<Deposito> depositos = cen.vistaDepositos(f.getIdEmpresa());
		
		//por cada venta me guardo los articulos en un hash
		//armo la consulta de stock
		
		
		Collections.sort(depositos);
		
		
		for (Deposito d : depositos) 
		{
			System.out.println(d.getId());
		}
		
		List<DataIDDescripcion> lista = new ArrayList<>();
		DataIDDescripcion a = new DataIDDescripcion(1,"0A31J1BLK-U");
		DataIDDescripcion b = new DataIDDescripcion(1,"33531305-U");
		DataIDDescripcion c = new DataIDDescripcion(1,"A31J1Y28-U");
		
		lista.add(a);
		lista.add(b);
		lista.add(c);
		List<DepositoStock> stock = cen.darDepositos(lista,token,1);
		
		
		
		
		
	}
	
	
	public  List<EncuentraPedido> derivar (List<EncuentraPedido> retorno,Call_WS_APIENCUENTRA cen, String token,List<DepositoStock> stock)  
	{
		
		
		
		
		for (EncuentraPedido pe : retorno) 
		{
			if(pe.getIdPedido().equals(new Long("76792")) || pe.getIdPedido().equals(new Long("75827"))) {
				System.out.println("******test*******");
			}
			
			if(derivaPickup(pe, stock))
			{
				for (DepositoStock ds : stock) 
				{
					if(ds.getIdDeposito()==pe.getIdDepositoEnvio())
					{
						
						for (EncuentraPedidoArticulo art : pe.getArticulosPedido()) 
						{
							for (DataIDDescripcion ar : ds.getArticulos()) 
							{
								if(ar.getDescripcion().equals(art.getArticulo()))
								{
									art.setOrigen(ds.getIdDeposito());
									art.setClickCollect(true);
									pe.setPreparaTienda(true);
									ar.setId(ar.getId()-art.getCantidad());
									
								}//if
							}//for art stock
							
						}//for art pedido
					}//for depo
				}//for stock
			}//if
			else
			{
				List<EncuentraPedidoArticulo> articulos = new ArrayList<>();
				for (EncuentraPedidoArticulo art :pe.getArticulosPedido()) 
				{
					int cantidadReq=art.getCantidad();
					String a = art.getArticulo();
					int cantidadPendiente = art.getCantidad();
					
					for (DepositoStock ds : stock) 
					{
						if(ds.isPreparaEnvioCD())
						{
							for (DataIDDescripcion as :ds.getArticulos()) 
							{
								if(as.getDescripcion().equals(a))
								{
									if(as.getId()>=cantidadPendiente)
									{
										//messirve ;)
										art.setOrigen(ds.getIdDeposito());
										articulos.add(art);
					 					as.setId(as.getId()-cantidadPendiente);
										cantidadPendiente-=cantidadPendiente;
										break;
										
									}
									else if(as.getId()>0)
									{
										try
										{
											EncuentraPedidoArticulo n = (EncuentraPedidoArticulo) art.clone();
											n.setCantidad(as.getId());
											n.setOrigen(ds.getIdDeposito());
											articulos.add(n);
											
											cantidadPendiente-=as.getId();
											as.setId(0);											
											break;
										}
										catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
						
						
						if (cantidadPendiente==0)
						{
							break;
						}
							
					}
					
					if (cantidadPendiente>0)
					{
						try
						{
							EncuentraPedidoArticulo n = (EncuentraPedidoArticulo) art.clone();
							n.setOrigen(0);
							articulos.add(n);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
						
					}
					
				}
				
				pe.setArticulosPedido(articulos);
			}
			
			
			System.out.println("");
		}
		return retorno;
	}
	
	private boolean derivaPickup (EncuentraPedido pe, List<DepositoStock> stock)
	{
		
		
		int idDestino = pe.getIdDepositoEnvio();
		String firma = "";
		String firmaB="";
		for (EncuentraPedidoArticulo a : pe.getArticulosPedido()) 
		{
			firma+=a.getCantidad()+a.getArticulo();
		}
		for (DepositoStock ds : stock) 
		{
			if(ds.getIdDeposito()==idDestino)
			{
				if(ds.isPreparaPickup() || ds.isPreparaDelivery())
				{
					for (EncuentraPedidoArticulo a : pe.getArticulosPedido()) 
					{
						for (DataIDDescripcion ar : ds.getArticulos()) 
						{
							
							if(ar.getDescripcion().equals(a.getArticulo()) && ar.getId()>=a.getCantidad())
							{
								firmaB+=a.getCantidad()+a.getArticulo();
							}
						}
					}
				}
			}
		}
		
		if(firma.equals(firmaB))
		{
			return true;
		}
		
		
		return false;
	}
		
	
	public void operaciones (ClienteOdoo_LaIsla cLIS, Hashtable<String, ArticuloCantidadEncuentra> hashartCantEnc,  
			List<DataIDDescripcion> arts, List<EncuentraPedido> pedidos, int idEmpresa, boolean update_destino_orden, boolean rederivacion, 
			Hashtable<Integer, Integer> excluir_deps, Hashtable<Integer, DataIDDescripcion> mailsDepositos)	{
		
		
		 LogicaAPI logica = new LogicaAPI();
		 String token = logica.darToken(idEmpresa);
		 Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
		 Map<Integer, String> parametros = cen.darParametros(token, "");
		 
		 int id_de_Deposito_Ecommerce =  158;//Integer.parseInt(parametros.get(5));
		 int id_de_Deposito_Central = Integer.parseInt(parametros.get(4));
		 
		 
		
		 List<StockArticulos> stockOdoo = cLIS.buscarStockArticulo(new ArrayList<>(
				 hashartCantEnc.values()), excluir_deps);
		 List<StockDeposito> stockOdoD = new ArrayList<>();
		 for (StockArticulos s : stockOdoo) 
		 {
			stockOdoD.add(new StockDeposito(""+s.getOrigen(), s.getIdArticulo(), Double.parseDouble(""+s.getStockDisponible())));
			try {
				hashartCantEnc.remove(s.getIdArticulo());
			} catch (Exception e) {}
			
		 }
			 
		 if(hashartCantEnc.size() > 0) {
			 Enumeration<String> elements = hashartCantEnc.keys();
				while (elements.hasMoreElements()) {
					stockOdoD.add(new StockDeposito("0", elements.nextElement(), 0.0));						
				}
		 }
		 
		 cen.putStk(token,stockOdoD);
		 			 
		 
		 List<DepositoStock> stock = cen.darDepositos(arts, token,1);
		 

		 System.out.println("");

		 pedidos = derivar(pedidos, cen, token, stock);
		 
		 
		 
		 
		 
		 List<DataIDDescripcion> guardadas = cen.SaveOrdersArticulosReq(token, pedidos,1);
		 
		 
		 ImpresionesPDF_API ip = new ImpresionesPDF_API(idEmpresa);
		 Hashtable<Integer, List<EncuentraPedido>> pedidosTiendas = new Hashtable<Integer, List<EncuentraPedido>>();
		 Hashtable<Integer, List<EncuentraPedidoArticulo>> solicitudesTiendas = new Hashtable<Integer, List<EncuentraPedidoArticulo>>();
		
		 
		 for (EncuentraPedido p : pedidos) 
		 {
			 
			 if(update_destino_orden) {
				 if(p.isPreparaTienda())
				 {
					 p.setShippingType(new DataIDDescripcion(3, ""));
				 }
				 cen.updateDestinoPedido(token, p, p.getIdDepositoEnvio(), p.getMontoEnvio());
				 
				 /************orden de venta************/
				 p.getOrden().save("", idEmpresa);
			 }
			 
			 if(p.isPreparaTienda())
			 {
				 List<EncuentraPedido> lista = null;
				 if(pedidosTiendas.get(p.getArticulosPedido().get(0).getOrigen())==null)
				 {
					 lista = new  ArrayList<EncuentraPedido>();
					 
				 }
				 else
				 {
					lista = pedidosTiendas.get(p.getArticulosPedido().get(0).getOrigen()); 
				 }
				 lista.add(p);
				 pedidosTiendas.put(p.getArticulosPedido().get(0).getOrigen(),lista);
				 
			 }
			 else
			 {
				  /************movimiento de stock************/
				 boolean todosMismoOrigen=true;
				 boolean central = false;
				 int origenAnt=0;
				 boolean pri=true;
				 for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
				 {
					 List<EncuentraPedidoArticulo> solis = null;
					 if(solicitudesTiendas.get(arp.getOrigen())==null)
					 {
						 solis = new  ArrayList<EncuentraPedidoArticulo>();
						 
					 }
					 else
					 {
						 solis = solicitudesTiendas.get(arp.getOrigen()); 
					 }
					 solis.add(arp);
					 solicitudesTiendas.put(arp.getOrigen(),solis);
					 
					 if(pri)
					 {
						 pri=false;
						 origenAnt=arp.getOrigen();
					 }
					 
					 
					 if(arp.getOrigen()!=id_de_Deposito_Central)
					 {
						 boolean confirmarMovimiento = false;
						 //158 deposito de transito
						 llamarMovimientoStock(arp, id_de_Deposito_Ecommerce, logica, ip, idEmpresa, p.getIdPedido(), confirmarMovimiento );
					 }
					 else
					 {
						 central=true;
					 }
					
					 if(origenAnt!=arp.getOrigen())
					 {
						todosMismoOrigen=false;
					 }
					 
					origenAnt=arp.getOrigen();
				 }
				 
				 todosMismoOrigen = rederivacion ? false: todosMismoOrigen;
				 
				 if(central && todosMismoOrigen)
				 {
					 for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
					 {
						 boolean confirmarMovimiento = true;
						 llamarMovimientoStock(arp, id_de_Deposito_Ecommerce, logica, ip, idEmpresa, p.getIdPedido(), confirmarMovimiento );
					 }
				 }
				 else if(central && !todosMismoOrigen)
				 {
					 for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
					 {
						 
						 if(arp.getOrigen()==id_de_Deposito_Central)
						 {
							 boolean confirmarMovimiento = false;
							 llamarMovimientoStock(arp, id_de_Deposito_Ecommerce, logica, ip, idEmpresa, p.getIdPedido(), confirmarMovimiento );
						 }
						 
					 }
				 } 
				 
				 
				 
					
			 }
		 }
		 
		 	List<SendMail> mails = new ArrayList<SendMail>();
		 	Set<Integer> keys = pedidosTiendas.keySet();
			for(Integer tienda: keys)
			{
				List<EncuentraPedido> pedidosTienda = pedidosTiendas.get(tienda);
				
				for (EncuentraPedido p : pedidosTienda) 
				{
					 /************movimiento de stock************/
					 List<DataIDDescripcion> movsStock = new ArrayList<>();
					 for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
					 {
						movsStock.add(new DataIDDescripcion(arp.getCantidad(), arp.getArticulo()));
					 }
					 
					//PERSISTO MOVIMIENTOS DE STOCK
					 boolean confirmarMovimiento = true;
					
					 int idMS = logica.RegistrarMovimientoStock(tienda, id_de_Deposito_Ecommerce, 0, movsStock, idEmpresa, p.getIdPedido(), 0, 0, confirmarMovimiento);
					 ip.imprimirTicketMovStock(tienda, id_de_Deposito_Ecommerce, 0, "Para Venta WEB "+p.getIdPedido(), movsStock, idMS, tienda);
				}
				
				try 
				{
					ip.ImprimirPedidosArticuloReq(pedidosTienda, tienda);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			keys = solicitudesTiendas.keySet();
			for(Integer tienda: keys)
			{
				List<EncuentraPedidoArticulo> solicitudesTienda = solicitudesTiendas.get(tienda);				
									
				//mails
				mails.add(mailsSolicitudWeb(solicitudesTienda, mailsDepositos.get(tienda)));
			}
			
			//envio mails
			LogicaAPI.PutMailSpooler(mails.toArray(new SendMail[mails.size()]), idEmpresa);
	}
	
	static void llamarMovimientoStock(EncuentraPedidoArticulo arp, int id_de_Deposito_Ecommerce, LogicaAPI logica, ImpresionesPDF_API ip, int idEmpresa, Long idPedido, boolean confirmarMovimiento )
	{
		 List<DataIDDescripcion> movsStock = new ArrayList<>();
		 movsStock.add(new DataIDDescripcion(arp.getCantidad(), arp.getArticulo()));
		 id_de_Deposito_Ecommerce = 158;
		 int idMS = logica.RegistrarMovimientoStock(arp.getOrigen(), id_de_Deposito_Ecommerce, 0, movsStock, idEmpresa, idPedido, 0, 0, confirmarMovimiento);
		 ip.imprimirTicketMovStock(arp.getOrigen(), id_de_Deposito_Ecommerce, 0, "Para Venta WEB "+idPedido, movsStock, idMS, arp.getOrigen());
	}
	
	public SendMail mailsSolicitudWeb(List<EncuentraPedidoArticulo> pedidos, DataIDDescripcion destinos){
		try {
			String head= ""+
					"<p>&nbsp;</p> "+
						"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
						"	<tbody> "+
						"	<tr> "+
						"	<td style='padding: 10px 0 30px 0;'> "+
						"	<table style='border: 1px solid #cccccc; border-collapse: collapse;' border='0' width='600' cellspacing='0' cellpadding='0' align='center'> "+
						"	<tbody> "+
						"	<tr> "+
						"	<td style='padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;' align='center' bgcolor='#70bbd9'> "+
						"	<p>Solicitud de articulos facturados por la web</p> "+
						"	</td> "+
						"	</tr> "+
						"	<tr> "+
						"	<td style='padding: 40px 30px 40px 30px;' bgcolor='#ffffff'> "+
						"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
						"	<tbody> "+
						"	<tr> "+
						"	<td style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'><strong>Hola, estamos necesitando ciertos articulos que hemos vendido y se encuentran en su tienda.</strong></td> "+
						"	</tr> "+
						"	<tr> "+
						"	<td style='padding: 20px 0px 30px; color: #153643; font-family: Arial,sans-serif; font-size: 16px; line-height: 20px; text-align: center;'> "+
						"	<p>En su colector encontrar&aacute; una tarea con los items que se vendieron.<br /> Por favor realice la tarea para el correcto envio de los articulos hacia la Web. Gracias!!</p> ";
						
			String table =	"<br /><br /><table>"+
		     "   <thead>"+
		      "      <tr>"+
		     //  "        <th>Fecha</th>"+
				"		<th>Pedido</th>"+
				"		<th>Articulo</th>"+
				"		<th>Cantidad</th>"+
		         "   </tr>"+
		        "</thead>"+
		        "<tbody>";
			//for(EncuentraPedidoArticulo p : pedidos){
				for(EncuentraPedidoArticulo a : pedidos){
					table += "<tr>"+
			        		//"<td>"+p.getFecha()+"</td>"+
			        		//"<td>"+p.getIdPedido()+"</td>"+
			        		"<td>"+a.getDistribucionAfecta()+"</td>"+
			        		"<td>"+a.getArticulo()+"</td>"+
			        		"<td>"+a.getCantidad()+"</td>"+
			        	"</tr>";
				}
			//}
		        	
			table += "</tbody>"+
					"</table>";
	        
						
					
					
					String footer = "</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"<tr> "+
								"<td style='padding: 30px 30px 30px 30px;' bgcolor='#ee4c50'> "+
								"<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
								"<tbody> "+
								"<tr> "+
								"<td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>Notificaci&oacute;n generada automaticamente por ENCUENTRA<br />No es necesario que responda a esta direcci&oacute;n de correo.</td> "+
								"<td align='right' width='25%'> "+
								"<table border='0' cellspacing='0' cellpadding='0'> "+
								"<tbody> "+
								"<tr> "+
								"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
								"<td style='font-size: 0; line-height: 0;' width='20'>&nbsp;</td> "+
								"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"<!--analytics--> "+
								"<p>&nbsp;</p>";
					
					SendMail mail = new SendMail(destinos.getId()+"dw"+new Fecha().darFechaAnio_Mes_Dia_hhmm(), destinos.getDescripcionB(), 
							"Solicitud de articulos facturados por la web - " + destinos.getDescripcion(), head+table+footer, "encuentra@200.com.uy",null);
					return mail;
		} catch (Exception e) {
			return new SendMail();
		}
	}
	
	public SendMail mailsSolicitudPickup(List<EncuentraPedido> pedidos, DataIDDescripcion destinos){
		try {
			String head= ""+
					"<p>&nbsp;</p> "+
						"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
						"	<tbody> "+
						"	<tr> "+
						"	<td style='padding: 10px 0 30px 0;'> "+
						"	<table style='border: 1px solid #cccccc; border-collapse: collapse;' border='0' width='600' cellspacing='0' cellpadding='0' align='center'> "+
						"	<tbody> "+
						"	<tr> "+
						"	<td style='padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;' align='center' bgcolor='#ee4c50'> "+
						"	<p>Pedido C&C facturados por la web</p> "+
						"	</td> "+
						"	</tr> "+
						"	<tr> "+
						"	<td style='padding: 40px 30px 40px 30px;' bgcolor='#ffffff'> "+
						"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
						"	<tbody> "+
						"	<tr> "+
						"	<td style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'><strong>Hola, los siguientes pedidos son de ventas WEB que deben ser procesadas por su tienda, ya que los clientes han elegido recoger alli.</strong></td> "+
						"	</tr> "+
						"	<tr> "+
						"	<td style='padding: 20px 0px 30px; color: #153643; font-family: Arial,sans-serif; font-size: 16px; line-height: 20px; text-align: center;'> "+
						"	<p>En su colector encontrar&aacute; los pedidos que debe elaborar.<br /> Por favor realice la tarea, una vez finalizada se notificara automaticamente al cliente que debe pasar a buscar su pedido. Gracias!!</p> ";
						
			String table =	"<br /><br /><table>"+
		     "   <thead>"+
		      "      <tr>"+
		       "        <th>Fecha</th>"+
				"		<th>Pedido</th>"+
				"		<th>Articulo</th>"+
				"		<th>Cantidad</th>"+
		         "   </tr>"+
		        "</thead>"+
		        "<tbody>";
			for(EncuentraPedido p : pedidos){
				for(EncuentraPedidoArticulo a : p.getArticulosPedido()){
					table += "<tr>"+
			        		"<td>"+p.getFecha()+"</td>"+
			        		"<td>"+p.getIdPedido()+"</td>"+
			        		"<td>"+a.getArticulo()+"</td>"+
			        		"<td>"+a.getCantidad()+"</td>"+
			        	"</tr>";
				}
			}		
		        	
			table += "</tbody>"+
					"</table>";
	        
						
					
					
					String footer = "</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"<tr> "+
								"<td style='padding: 30px 30px 30px 30px;' bgcolor='#ee4c50'> "+
								"<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
								"<tbody> "+
								"<tr> "+
								"<td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>Notificaci&oacute;n generada automaticamente por ENCUENTRA<br />No es necesario que responda a esta direcci&oacute;n de correo.</td> "+
								"<td align='right' width='25%'> "+
								"<table border='0' cellspacing='0' cellpadding='0'> "+
								"<tbody> "+
								"<tr> "+
								"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
								"<td style='font-size: 0; line-height: 0;' width='20'>&nbsp;</td> "+
								"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"<!--analytics--> "+
								"<p>&nbsp;</p>";
					
					SendMail mail = new SendMail(destinos.getId()+"cc"+new Fecha().darFechaAnio_Mes_Dia_hhmm(), destinos.getDescripcionB(), 
							"Pedido C&C facturados por la web - " + destinos.getDescripcion(), head+table+footer, "encuentra@200.com.uy",null);
					
					
					return mail;
		} catch (Exception e) {
			return new SendMail();
		}
		
	}
	
	public SendMail mailNoEncontrados(List<EncuentraPedidoArticulo> pedidos, DataIDDescripcion destinos){
		try {
			String head= ""+
					"<p>&nbsp;</p> "+
						"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
						"	<tbody> "+
						"	<tr> "+
						"	<td style='padding: 10px 0 30px 0;'> "+
						"	<table style='border: 1px solid #cccccc; border-collapse: collapse;' border='0' width='600' cellspacing='0' cellpadding='0' align='center'> "+
						"	<tbody> "+
						"	<tr> "+
						"	<td style='padding: 40px 0 30px 0; color: white; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;' align='center' bgcolor='black'> "+
						"	<p>Articulos no encontrados</p> "+
						"	</td> "+
						"	</tr> "+
						"	<tr> "+
						"	<td style='padding: 40px 30px 40px 30px;' bgcolor='#ffffff'> "+
						"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
						"	<tbody> "+
						"	<tr> "+
						"	<td style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'><strong>Los siguientes articulos no han sido encontrados. Se recomienda revisar el stock de la tienda y realizar los ajustes correspondientes.</strong></td> "+
						"	</tr> "+
						"	<tr> "+
						"	<td style='padding: 20px 0px 30px; color: #153643; font-family: Arial,sans-serif; font-size: 16px; line-height: 20px; text-align: center;'> "+
						"	<p></p> ";
						
			String table =	"<br /><br /><table>"+
		     "   <thead>"+
		      "      <tr>"+
		      // "        <th>Fecha</th>"+
				"		<th>Pedido</th>"+
				"		<th>Articulo</th>"+
				"		<th>Cantidad</th>"+
		         "   </tr>"+
		        "</thead>"+
		        "<tbody>";
			for(EncuentraPedidoArticulo a : pedidos){
				table += "<tr>"+
		        		//"<td>"+p.getFecha()+"</td>"+
		        		//"<td>"+p.getIdPedido()+"</td>"+
		        		"<td>"+a.getDistribucionAfecta()+"</td>"+
		        		"<td>"+a.getArticulo()+"</td>"+
		        		"<td>"+a.getCantidad()+"</td>"+
		        	"</tr>";
			}
		        	
			table += "</tbody>"+
					"</table>";
	        
						
					
					
					String footer = "</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"<tr> "+
								"<td style='padding: 30px 30px 30px 30px;' bgcolor='#ee4c50'> "+
								"<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
								"<tbody> "+
								"<tr> "+
								"<td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>Notificaci&oacute;n generada automaticamente por ENCUENTRA<br />No es necesario que responda a esta direcci&oacute;n de correo.</td> "+
								"<td align='right' width='25%'> "+
								"<table border='0' cellspacing='0' cellpadding='0'> "+
								"<tbody> "+
								"<tr> "+
								"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
								"<td style='font-size: 0; line-height: 0;' width='20'>&nbsp;</td> "+
								"<td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"</td> "+
								"</tr> "+
								"</tbody> "+
								"</table> "+
								"<!--analytics--> "+
								"<p>&nbsp;</p>";
					
					SendMail mail = new SendMail("ne"+new Fecha().darFechaAnio_Mes_Dia_hhmm(), destinos.getDescripcionB(), 
							"Articulos no encontrados para la web - " + destinos.getDescripcion(), head+table+footer, "encuentra@200.com.uy",null);
					
					
					return mail;
		} catch (Exception e) {
			return new SendMail();
		}
		
	}

}
