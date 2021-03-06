package integraciones.mains.stadium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beans.jsonEstadoMP;
import beans.api.ImpresionesPDF_API;
import beans.api.json.SendMail;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.StockDeposito;
import beans.derivation.DepositoStock;
import beans.derivation.OrderDerivator;
import beans.encuentra.Clientes;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import integraciones.erp.odoo.laIsla.ArticuloCantidadEncuentra;
import integraciones.erp.odoo.laIsla.ClienteOdoo_LaIsla;
import integraciones.erp.odoo.laIsla.StockArticulos;
import integraciones.erp.visualStore.objetos.ClienteStock;
import integraciones.marketplaces.MercadoLibre.MercadoLibre;
import integraciones.marketplaces.fenicio.Ordenes;
import integraciones.marketplaces.fenicioTrack.Fenicio;
import integraciones.marketplaces.fenicioTrack.FenicioLAISLA;
import integraciones.marketplaces.fenicioTrack.FenicioStd;
import integraciones.marketplaces.objetos.CanalMarketPlace;
import integraciones.marketplaces.objetos.marketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class ClienteStdFenicio 
{
 
	public static void main(String[] args) 
	{
		List<marketPlace> mps = new ArrayList<marketPlace>();
		FenicioStd f = new FenicioStd();
		MercadoLibre ml = new MercadoLibre();		
		mps.add(ml);
		//mps.add(f);
			 
		 
		 LogicaAPI logica = new LogicaAPI();
		 String token = logica.darToken(f.getIdEmpresa());
		 Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
		 
		 Map<Integer, String> integraciones = cen.darIntegraciones(token);
		 
		 String cambioEstado = integraciones.get(3) != null ? integraciones.get(3) : "0";
		 
		 int diasBusqueda = 7;	
		 Map<String, String> pedidosEncuentra = cen.PedidosID(token, diasBusqueda, "");		
		 List<DataIDDescripcion> depositosDestino =cen.DarDatosPutOrders(token, 2);
		 
		 Map<Integer, String> mailsDepositos = new HashMap<>();
		 Map<String, Integer> mapDepositos = new HashMap<>();
		 for(DataIDDescripcion d: depositosDestino) 
		 {
			 mailsDepositos.put(d.getId(), d.getDescripcionB());
			 mapDepositos.put(d.getDescripcion(), d.getId());
		 }
		 
		 for(marketPlace mp : mps) {
			 Map<Integer, CanalMarketPlace> canales = mp.getCanales();			 
			 List<CanalMarketPlace> canalesL = new ArrayList<> (canales.values());
			 
			 for (CanalMarketPlace canal : canalesL) 
			 {
				 Map<String, Integer> deposCanal = cen.darDepositosEcommerce(token, canal.getId());
				 int idDepositoEcommerce =  deposCanal.get("id_de_Deposito_Ecommerce");
				 int idDepositoCentral = deposCanal.get("id_de_Deposito_Principal");
				 			 
				 
				 //List<Ordenes> pedidosFenicio = f.getPedidosAPI(canal.getId(),diasBusqueda);
				 //List<EncuentraPedido> pedidosALL = f.getPedidos(canal.getId(),"procesando",diasBusqueda);
				 List<EncuentraPedido> pedidosALL = mp.getPedidos(canal.getId(),"preparando",diasBusqueda, pedidosEncuentra, mapDepositos);			 
				 
				 List<EncuentraPedido> pedidos = new ArrayList<>();
				 for (EncuentraPedido p : pedidosALL) 
				 {
					 if(!pedidosEncuentra.containsKey(p.getIdPedido()+""))
					 {
						 p.setTrackingNumber(p.getIdPedido()+"");					 
						 List<EncuentraPedidoArticulo> removes = new ArrayList<>();
						 					 
						 for (EncuentraPedidoArticulo a: p.getArticulosPedido()) {
							if(a.getImporte() < 0.0) {
								p.setCantidad(p.getCantidad()-a.getCantidad());
								removes.add(a);
							}		
						}
						 
						 if (!removes.isEmpty()) {
							 for(EncuentraPedidoArticulo r: removes) {
								 p.getArticulosPedido().remove(r);
							 }
						 }
						 
						 pedidos.add(p);
						 
					 }
				 }
				 			 
				 
				 
				 Map<String, String> articulos = new HashMap<>();
				 for (EncuentraPedido p : pedidos) 
				 {

					 Clientes clien = p.getCliente().transformar(p.getCliente(), mp.getIdEmpresa());
					 clien.setIdPedido(p.getIdPedido()+"");
	 				 clien.save();
					 
					 int contador = 0;
					 System.out.println(p.getIdPedido());
					 
					 for (EncuentraPedidoArticulo a : p.getArticulosPedido()) 
					 {
						 try
						 {
							 String sku = "";
							 if(mp instanceof Fenicio) {
								 String [] arreglo = a.getCodFenicio().split(":");
								 //1:057.N1915:0148:42.0:1
								 //0 1			 2			 3	 4
								 String base  = arreglo[1];
								 String color = arreglo[2];
								 String talle = arreglo[3];
								 sku = base+color+talle;
							 }
							 else {
								 sku = a.getArticulo();
							 }
							 
							 a.setArticulo(sku);
							 articulos.put(sku, sku);
							 a.setOrigen(1);
							 p.getOrden().getOrdenVentaLineas().get(contador).setIdArticulo(sku);
						 }
						 catch (Exception e)
						 {
							 //aca hay que agregar a la lista de lo que se manda por mail.
							 System.out.println("no se pudo mapear el SKU " +a.getSKUFenicio());
						 }
							 
						 contador++;
					 }
				 	
				 }
				 
				 List<DataIDDescripcion> listaOK = cen.SaveOrders(token, pedidos);
				 
				 List<EncuentraPedido> pedidos2 = new ArrayList<>();
				 List<jsonEstadoMP> estadosMP = new ArrayList<>();
				 
					for (DataIDDescripcion pl : listaOK) {
						for (EncuentraPedido p : pedidos) {
							if (((p.getIdPedido()) + "").equals(pl.getDescripcion()) && pl.getDescripcionB().equals("OK")) {
								logica.mapeoArticulosfenicio(p.getArticulosPedido(), mp.getIdEmpresa());
								pedidos2.add(p);

								if (cambioEstado != null && cambioEstado.equals("1") && mp instanceof Fenicio) {
									jsonEstadoMP estado = new jsonEstadoMP(p.getIdPedido() + "", 0, p.getIdPedido(),
											((Fenicio)mp).JSONUpdateState(p.getIdPedido(), p.getIdPedido() + "", "", 1), canal.getId(),
											mp.getIdEmpresa());
									estadosMP.add(estado);
								}
							}
						}
					}
				 
				 if(!estadosMP.isEmpty()){
					 LogicaAPI.putColaEstadoMarketPlace(estadosMP);
				 }
				 
				 pedidos = pedidos2;
				 
				 
				 
				 articulos = new HashMap<>();
				 
					 for (EncuentraPedido p : pedidos) 
					 {
						 for (EncuentraPedidoArticulo a : p.getArticulosPedido()) 
						 {
							 articulos.put(a.getArticulo(), a.getArticulo());
						 }
						 
						 if(mp instanceof Fenicio) {						 
							 Ordenes ovf = ((Fenicio)mp).OrdenVentaFenicio(p.getIdPedido(), mp.getIdEmpresa(), canal.getId());
								 
							 try
							 {
								 boolean found = false;
								 if(ovf.getOrigen().equals("MERCADOLIBRE"))
								 {
									 p.setShippingType(new DataIDDescripcion(1, ""));
									 p.setIdDepositoEnvio(60);
									found=true;
									
								 }
								 else if(!p.getSucursalPick().equals(""))
								 {
									 //es pickup
									 p.setShippingType(new DataIDDescripcion(2, ""));
									 int idDepositoEnvio = Integer.parseInt(p.getSucursalPick());
									 idDepositoEnvio = Integer.parseInt(p.getSucursalPick());
									 	
									 p.setIdDepositoEnvio(idDepositoEnvio);
									 found=true;
								 }
								 else
								 {
									 p.setShippingType(new DataIDDescripcion(1, ""));
									for (DataIDDescripcion d : depositosDestino) 
									 {
										if(d.getDescripcion().equals(p.getEmpresaEnvio()))
										{
											p.setIdDepositoEnvio(d.getId());
											found=true;
											break;
										}
									 }
								 }
								 if(!found)
								 {
									 if(ovf.getEntrega().getServicioEntrega()!=null  && ovf.getEntrega().getServicioEntrega().getNombre().equals("Retiro Sucursal Agencia Central"))
									 {
										 p.setShippingType(new DataIDDescripcion(1, ""));
										 p.setIdDepositoEnvio(50);
									 }
									 
									 
									 
									 else
									 {
										 p.setShippingType(new DataIDDescripcion(1, ""));
										 System.out.println("No encontre "+p.getEmpresaEnvio());
										 p.setIdDepositoEnvio(0);
									 }
									 
								 }
							 }
							 catch (Exception e) 
							 {
								 p.setShippingType(new DataIDDescripcion(1, ""));
								 e.printStackTrace();
								 p.setIdDepositoEnvio(0);
							 }
						 }
					 }
				 
			 	
				 
				 List<String> articulosS = new ArrayList<>(articulos.values());
				 List<DataIDDescripcion> arts = new ArrayList<>();
				 
				 Map<String, ArticuloCantidadEncuentra> hashartCantEnc = new HashMap<>();
				 
				 for (String a : articulosS) 
				 {
					 arts.add(new DataIDDescripcion(1, a));
					 ArticuloCantidadEncuentra ace = new ArticuloCantidadEncuentra(1.0, 0, a);
					 
					 hashartCantEnc.put(a, ace);
					 //artCantEnc.add(ace);
					 
				 }
				 
				 ClienteStock clvS = new ClienteStock(1);
				 
				
				 List<StockArticulos> stockVss = clvS.darStockArts(new ArrayList<>(hashartCantEnc.values()), new HashMap<>());
				 List<StockDeposito> stockVssD = new ArrayList<>();
				 
				 for (StockArticulos s : stockVss) 
				 {
					stockVssD.add(new StockDeposito(""+s.getOrigen(), s.getIdArticulo(), Double.parseDouble(""+s.getStockDisponible())));
					try {
						hashartCantEnc.remove(s.getIdArticulo());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				 }
					 
				 if(!hashartCantEnc.isEmpty()) 
				 {
						for (String key : hashartCantEnc.keySet()) {
							stockVssD.add(new StockDeposito("0", key, 0.0));						
						}
				 }
				 
				 cen.putStk(token,stockVssD);
				 			 
				 
				 List<DepositoStock> stock = cen.darDepositos(arts, token,canal.getId());
				 
				 
				 
				 System.out.println("");

				 OrderDerivator od = new OrderDerivator();
				 pedidos = od.derivar(pedidos, cen, token, stock);
				 
				 List<DataIDDescripcion> guardadas = cen.SaveOrdersArticulosReq(token, pedidos,canal.getId());
				 
				 ImpresionesPDF_API ip = new ImpresionesPDF_API(mp.getIdEmpresa());
				 Map<Integer, List<EncuentraPedido>> pedidosTiendas = new HashMap<>();
				 Map<Integer, List<EncuentraPedidoArticulo>> solicitudesTiendas = new HashMap<>();
				 
				 for (EncuentraPedido p : pedidos) 
				 {
					 
					 if(p.isPreparaTienda())
					 {
						 p.setShippingType(new DataIDDescripcion(3, ""));
					 }
					 cen.updateDestinoPedido(token, p, p.getIdDepositoEnvio(), p.getMontoEnvio());
					 
					 /************orden de venta************/
					 p.getOrden().save("", mp.getIdEmpresa());
					 
					 
					 if(p.isPreparaTienda())
					 {
						 List<EncuentraPedido> lista = null;
						 if(pedidosTiendas.get(p.getArticulosPedido().get(0).getOrigen())==null)
						 {
							 lista = new  ArrayList<>();
							 
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
								 solis = new  ArrayList<>();
								 
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
							 
							 if(arp.getOrigen()!=idDepositoCentral)
							 {
								 boolean confirmarMovimiento = false;
								 //158 deposito de transito
								 llamarMovimientoStock(arp, idDepositoEcommerce, logica, ip, mp.getIdEmpresa(), p.getIdPedido(), confirmarMovimiento, false);
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
						 
						 if(central && todosMismoOrigen)
						 {
							 for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
							 {
								 boolean confirmarMovimiento = true;
								 llamarMovimientoStock(arp, idDepositoEcommerce, logica, ip, mp.getIdEmpresa(), p.getIdPedido(), confirmarMovimiento, central );
							 }
						 }
						 else if(central && !todosMismoOrigen)
						 {
							 for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
							 {
								 
								 if(arp.getOrigen()==idDepositoCentral)
								 {
									 boolean confirmarMovimiento = false;
									 llamarMovimientoStock(arp, idDepositoEcommerce, logica, ip, mp.getIdEmpresa(), p.getIdPedido(), confirmarMovimiento, central );
								 }
								 
							 }
						 } 
						 
					 }
				 }
				 
				 	List<SendMail> mails = new ArrayList<>();
				 
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
							
							 int idMS = logica.RegistrarMovimientoStock(tienda, idDepositoEcommerce, 0, movsStock, mp.getIdEmpresa(), p.getIdPedido(), 0, 0, confirmarMovimiento);
							 ip.imprimirTicketMovStock(tienda, idDepositoEcommerce, 0, "Para Venta WEB "+p.getIdPedido(), movsStock, idMS, tienda);
						}
						
						try 
						{
							ip.ImprimirPedidosArticuloReq(pedidosTienda, tienda);
						}
						catch (Exception e) 
						{
							e.printStackTrace();
						}
						
						//mails
						
						//mails.add(od.mailsSolicitudPickup(pedidosTienda, mailsDepositos.get(tienda), tienda));
					}
					
					for(Map.Entry<Integer, List<EncuentraPedidoArticulo>> entry : solicitudesTiendas.entrySet())
//					for(Integer tienda: solicitudesTiendas.keySet())
					{
						List<EncuentraPedidoArticulo> solicitudesTienda = entry.getValue();				
						//mails
						//mails.add(od.mailsSolicitudWeb(solicitudesTienda, mailsDepositos.get(entry.getKey()), entry.getKey()));
					}
					
					//envio mails
					LogicaAPI.PutMailSpooler(mails.toArray(new SendMail[mails.size()]), mp.getIdEmpresa());
				 
					//SINCRO ETIQUETAS
					 System.out.println("esperando 10 segundos para sincronizar etiquetas nuevamente");
					    try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					 
					 if(mp instanceof Fenicio) {
						 
						 Map<String, String> pedidosDespachados = cen.PedidosID(token, 30, "4");
						 List<DataIDDescripcion> entregados = ((Fenicio)mp).tackPedidosDespachados(pedidosDespachados, canal.getId());
						 if(!entregados.isEmpty()) {
							 cen.updateOrdersStatus(token,entregados);
						 } 
					 }
					 
			 }//for de canales
		 }
		 
		 	 
	}
	
	static void llamarMovimientoStock(EncuentraPedidoArticulo arp, int idDepositoEcommerce, LogicaAPI logica, ImpresionesPDF_API ip, 
			int idEmpresa, Long idPedido, boolean confirmarMovimiento, boolean central )
	{
		 List<DataIDDescripcion> movsStock = new ArrayList<>();
		 movsStock.add(new DataIDDescripcion(arp.getCantidad(), arp.getArticulo()));
		 
		 int idMS = logica.RegistrarMovimientoStock(arp.getOrigen(), idDepositoEcommerce, 0, movsStock, idEmpresa, idPedido, 0, 0, confirmarMovimiento);
		 if(!confirmarMovimiento || (confirmarMovimiento && !central)) {
			 ip.imprimirTicketMovStock(arp.getOrigen(), idDepositoEcommerce, 0, "Para Venta WEB "+idPedido, movsStock, idMS, arp.getOrigen());
		 }
		 
	}
	
	
}

