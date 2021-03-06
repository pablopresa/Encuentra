package integraciones.mains.laisla;

import java.util.ArrayList;
import java.util.HashMap;
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
import integraciones.erp.odoo.laIsla.LaIsla;
import integraciones.erp.odoo.laIsla.StockArticulos;
import integraciones.marketplaces.fenicio.Ordenes;
import integraciones.marketplaces.fenicioTrack.FenicioLAISLA;
import integraciones.marketplaces.objetos.CanalMarketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class ClienteLAISLAFenicio 
{
 
	public static void main(String[] args) 
	{
		LaIsla li = new LaIsla();
		FenicioLAISLA f = new FenicioLAISLA();
		Map<Integer, Integer> mapeoTiendas = new HashMap<>();
		mapeoTiendas.put(3,91);
		mapeoTiendas.put(2,127);
		mapeoTiendas.put(4,133);
		mapeoTiendas.put(7,121);
		mapeoTiendas.put(6,115);
		mapeoTiendas.put(8,139);
		mapeoTiendas.put(5,103);
		mapeoTiendas.put(10,0);
		mapeoTiendas.put(9,9);
		
		 Map<Integer, CanalMarketPlace> canales = f.getCanales();
		 
		 List<CanalMarketPlace> canalesL = new ArrayList<> (canales.values());
		 
		 LogicaAPI logica = new LogicaAPI();
		 String token = logica.darToken(f.getIdEmpresa());
		 Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
		 Map<Integer, String> parametros = cen.darParametros(token, "");
		 Map<Integer, String> integraciones = cen.darIntegraciones(token);
		 
		 String cambioEstado = integraciones.get(3) != null ? integraciones.get(3) : "0";
		 
		 
		 int idDepositoEcommerce =  158;//Integer.parseInt(parametros.get(5));
		 int idDepositoCentral = Integer.parseInt(parametros.get(4));
		 int diasBusqueda = 6;

		 
		 List<String> dirtySKUs = new ArrayList<>();
		 
		 for (CanalMarketPlace canal : canalesL) 
		 {
			 List<Ordenes> pedidosFenicio = f.getPedidosAPI(canal.getId(),diasBusqueda);
			 List<EncuentraPedido> pedidosALL = f.getPedidos(canal.getId(),"procesando",diasBusqueda, null, null);
			 Map<String, String> pedidosEncuentra = cen.PedidosID(token, diasBusqueda, "");
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
			 			 
			 for (EncuentraPedido p : pedidos) 
			 {
				
				 
					 Clientes clien = p.getCliente().transformar(p.getCliente(), f.getIdEmpresa());
					 clien.setIdPedido(p.getIdFenicio());
					 clien.save();
					 for (EncuentraPedidoArticulo a : p.getArticulosPedido()) 
					 {
						 try
						 {
							 String [] arreglo = a.getCodFenicio().split(":");
							 //1:HOME-OLABAS:HOME-OLABAS:UNI:1
							 //0 1			 2			 3	 4
							 String sKU  = "";
							 String variacion = "";
							 
							 if(arreglo[2].equals(arreglo[1]))
							 {
								 sKU = arreglo[2];
								 variacion = arreglo[3];
								 
							 }
							 else
							 {
								 sKU = arreglo[1]+"-"+arreglo[2];
								 variacion = arreglo[3];
							 }
							 
							 dirtySKUs.add(sKU+":"+variacion);
						 }
						 catch (Exception e) 
						 {
							System.out.println(a.getCodFenicio());
						 }
					 }
			 }
			 
			 Map<String, String> skuMapper = cen.searchSKU(token, dirtySKUs);
			 
			 
			 Map<String, String> articulos = new HashMap<>();
			 for (EncuentraPedido p : pedidos) 
			 {
				 	 int contador = 0;
				 	 System.out.println(p.getIdFenicio());
				 	 if(p.getIdFenicio().equals("76717") || p.getIdFenicio().equals("76766")) {
				 		 System.out.println("test");
				 	 }
					 for (EncuentraPedidoArticulo a : p.getArticulosPedido()) 
					 {
						 
						 try
						 {
							 String [] arreglo = a.getCodFenicio().split(":");
							 
							 //1:HOME-OLABAS:HOME-OLABAS:UNI:1
							 //0 1			 2			 3	 4
							 //1:CPAAM9:	64:			34:	 1
							 
							 
							 String sKU  = "";
							 String variacion = "";
							 
							 if(arreglo[2].equals(arreglo[1]))
							 {
							 
								 sKU = arreglo[2];
								 variacion = arreglo[3];
								 
							 }
							 else
							 {
								 sKU = arreglo[1]+"-"+arreglo[2];
								 variacion = arreglo[3];
							 }
							 
							 
							 String arti = skuMapper.get(sKU+":"+variacion);
							 if(arti.startsWith("AV-")) {
								 arti = arti.replace("AV-", "");
							 }
							 
							 articulos.put(arti, arti);
							 a.setOrigen(1);
							 
							 a.setArticulo(arti);
							 
							 //SETTEAR EL OVL con el valor del str nuevo del idArticulo
							 p.getOrden().getOrdenVentaLineas().get(contador).setIdArticulo(arti);
						 }
						 catch (Exception e)
						 {
							//aca hay que agregar a la lista de lo que se manda por mail.
							 // ver vta 75997
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
							logica.mapeoArticulosfenicio(p.getArticulosPedido(), f.getIdEmpresa());
							pedidos2.add(p);

							if (cambioEstado != null && cambioEstado.equals("1")) {
								jsonEstadoMP estado = new jsonEstadoMP(p.getIdPedido() + "", 0, p.getIdPedido(),
										f.JSONUpdateState(p.getIdPedido(), p.getIdPedido() + "", "", 1), canal.getId(),
										f.getIdEmpresa());
								estadosMP.add(estado);
							}
						}
					}
				}
			 
			 if(!estadosMP.isEmpty()){
				 LogicaAPI.putColaEstadoMarketPlace(estadosMP);
			 }
			 
			 pedidos = pedidos2;
			 
			 List<DataIDDescripcion> depositosDestino =cen.DarDatosPutOrders(token, 2);
			 
			 Map<Integer, DataIDDescripcion> mailsDepositos = new HashMap<>();
			 for(DataIDDescripcion d: depositosDestino) 
			 {
				 mailsDepositos.put(d.getId(), d);
			 }
			 
			 articulos = new HashMap<>();
			 
			 for (EncuentraPedido p : pedidos) 
			 {
				 Ordenes ovf = f.OrdenVentaFenicio(p.getIdPedido(), f.getIdEmpresa(), 1);

				 for (EncuentraPedidoArticulo a : p.getArticulosPedido()) 
				 {
					 articulos.put(a.getArticulo(), a.getArticulo());
				 }
				 
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
						 try 
						 {
							idDepositoEnvio = mapeoTiendas.get(idDepositoEnvio) ;
						 }
						 catch (Exception e) 
						 {
							 idDepositoEnvio = Integer.parseInt(p.getSucursalPick());
						 
						 }
							
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
						 if(ovf.getEntrega().getServicioEntrega()!=null  && ovf.getEntrega().getServicioEntrega().getNombre().equals("Entregas La isla"))
						 {
							 p.setShippingType(new DataIDDescripcion(1, ""));
							 p.setIdDepositoEnvio(20);
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
			
			 List<StockArticulos> stockOdoo = li.buscarStockArticulo(new ArrayList<>(hashartCantEnc.values()), new HashMap<>());
			 List<StockDeposito> stockOdoD = new ArrayList<>();
			 
			 for (StockArticulos s : stockOdoo) 
			 {
				stockOdoD.add(new StockDeposito(""+s.getOrigen(), s.getIdArticulo(), Double.parseDouble(""+s.getStockDisponible())));
				try {
					hashartCantEnc.remove(s.getIdArticulo());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			 }
				 
			 if(!hashartCantEnc.isEmpty()) {
					for (String key : hashartCantEnc.keySet()) {
						stockOdoD.add(new StockDeposito("0", key, 0.0));						
					}
			 }
			 
			 cen.putStk(token,stockOdoD);
			 			 
			 
			 List<DepositoStock> stock = cen.darDepositos(arts, token, canal.getId());
			 
			 System.out.println("");

			 OrderDerivator od = new OrderDerivator();
			 pedidos = od.derivar(pedidos, cen, token, stock);
			 
			 List<DataIDDescripcion> guardadas = cen.SaveOrdersArticulosReq(token, pedidos,canal.getId());
			 
			 ImpresionesPDF_API ip = new ImpresionesPDF_API(f.getIdEmpresa());
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
				 p.getOrden().save("", f.getIdEmpresa());
				 
				 
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
							 llamarMovimientoStock(arp, idDepositoEcommerce, logica, ip, f.getIdEmpresa(), p.getIdPedido(), confirmarMovimiento, false);
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
							 llamarMovimientoStock(arp, idDepositoEcommerce, logica, ip, f.getIdEmpresa(), p.getIdPedido(), confirmarMovimiento, central );
						 }
					 }
					 else if(central && !todosMismoOrigen)
					 {
						 for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
						 {
							 
							 if(arp.getOrigen()==idDepositoCentral)
							 {
								 boolean confirmarMovimiento = false;
								 llamarMovimientoStock(arp, idDepositoEcommerce, logica, ip, f.getIdEmpresa(), p.getIdPedido(), confirmarMovimiento, central );
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
						
						 int idMS = logica.RegistrarMovimientoStock(tienda, idDepositoEcommerce, 0, movsStock, f.getIdEmpresa(), p.getIdPedido(), 0, 0, confirmarMovimiento);
						 ip.imprimirTicketMovStock(tienda, idDepositoEcommerce, 0, "Para Venta WEB "+p.getIdPedido(), movsStock, idMS, tienda);
						 
						 try 
						{
							ip.ImprimirPedidosArticuloReq(p, tienda);
						}
						catch (Exception e) 
						{
							e.printStackTrace();
						}
					}					
					
					//mails					
					mails.add(od.mailsSolicitudPickup(pedidosTienda, mailsDepositos.get(tienda)));
				}
				
				for(Map.Entry<Integer, List<EncuentraPedidoArticulo>> entry : solicitudesTiendas.entrySet())
//				for(Integer tienda: solicitudesTiendas.keySet())
				{
					List<EncuentraPedidoArticulo> solicitudesTienda = entry.getValue();				
					//mails
					mails.add(od.mailsSolicitudWeb(solicitudesTienda, mailsDepositos.get(entry.getKey())));
				}
				
				//envio mails
				LogicaAPI.PutMailSpooler(mails.toArray(new SendMail[mails.size()]), f.getIdEmpresa());
			 
				//SINCRO ETIQUETAS
				 System.out.println("esperando 10 segundos para sincronizar etiquetas nuevamente");
				    try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				 f.buscarEtiquetas(pedidosFenicio, cen, token, canal.getId());
				 
				 //CONSULTO ESTADO DE DESPACHADOS
				 Map<String, String> pedidosDespachados = cen.PedidosID(token, 30, "4");
				 List<DataIDDescripcion> entregados = f.tackPedidosDespachados(pedidosDespachados, canal.getId());
				 if(!entregados.isEmpty()) {
					 cen.updateOrdersStatus(token,entregados);
				 }
		 }//for de canales	 
	}
	
	static void llamarMovimientoStock(EncuentraPedidoArticulo arp, int idDepositoEcommerce, LogicaAPI logica, ImpresionesPDF_API ip, 
			int idEmpresa, Long idPedido, boolean confirmarMovimiento, boolean central )
	{
		 List<DataIDDescripcion> movsStock = new ArrayList<>();
		 movsStock.add(new DataIDDescripcion(arp.getCantidad(), arp.getArticulo()));
		 idDepositoEcommerce = 158;
		 int idMS = logica.RegistrarMovimientoStock(arp.getOrigen(), idDepositoEcommerce, 0, movsStock, idEmpresa, idPedido, 0, 0, confirmarMovimiento);
		 if(!confirmarMovimiento || (confirmarMovimiento && !central)) {
			 ip.imprimirTicketMovStock(arp.getOrigen(), idDepositoEcommerce, 0, "Para Venta WEB "+idPedido, movsStock, idMS, arp.getOrigen());
		 }
		 
	}
	
}

