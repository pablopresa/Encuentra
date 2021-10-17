package integraciones.mains.lacancha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import beans.jsonEstadoMP;
import beans.api.ImpresionesPDF_API;
import beans.api.json.SendMail;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.StockDeposito;
import beans.derivation.DepositoStock;
import beans.derivation.OrderDerivator;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import integraciones.erp.contawin.lacancha.ClienteContaWinLaCancha;
import integraciones.erp.odoo.laIsla.ArticuloCantidadEncuentra;
import integraciones.erp.odoo.laIsla.StockArticulos;
import integraciones.marketplaces.fenicio.Ordenes;
import integraciones.marketplaces.fenicioTrack.FenicioLaCancha;
import integraciones.marketplaces.objetos.CanalMarketPlace;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class ClienteLaCanchaFenicio {

	public static void main(String[] args) 
	{
		
		FenicioLaCancha f = new FenicioLaCancha();
		
		Map<Integer, CanalMarketPlace> canales = f.getCanales();

		List<CanalMarketPlace> canalesL = new ArrayList<>(canales.values());

		LogicaAPI logica = new LogicaAPI();
		String token = logica.darToken(f.getIdEmpresa());
		Call_WS_APIENCUENTRA wms = new Call_WS_APIENCUENTRA();

		Map<Integer, String> parametros = wms.darParametros(token, "");

		Map<Integer, String> integraciones = wms.darIntegraciones(token);

		String cambioEstado = integraciones.get(3) != null ? integraciones.get(3) : "0";

		int idDepositoEcommerce = Integer.parseInt(parametros.get(5));
		int idDepositoCentral = Integer.parseInt(parametros.get(4));
		int diasBusqueda = 1;

		for (CanalMarketPlace canal : canalesL) {
			
			// 1 - le pedimos los pedidos a fenicio API (son datos complementarios)
			List<Ordenes> pedidosFenicio = f.getPedidosAPI(canal.getId(), diasBusqueda);
			// 2 - le pedimos los pedidos a fenicio Tracking estructura del pedido
			List<EncuentraPedido> pedidosALL = f.getPedidos(canal.getId(), "procesando", diasBusqueda);
			// 3 - traemos los pedidos de encuentra para determinar cuales son los que debemos sincronizar y cuales no
			Map<String, String> pedidosEncuentra = wms.PedidosID(token, diasBusqueda, "");
			
			
			//esta es la lista de pedidos buenos, los que vamos a meter en encuentra
			List<EncuentraPedido> pedidos = new ArrayList<>();
			
			//
			for (EncuentraPedido p : pedidosALL) {
				if (!pedidosEncuentra.containsKey(p.getIdPedido() + "")) {
					p.setTrackingNumber(p.getIdPedido() + "");
					List<EncuentraPedidoArticulo> removes = new ArrayList<>();

					for (EncuentraPedidoArticulo a : p.getArticulosPedido()) {
						if (a.getImporte()!=null && a.getImporte() < 0.0) {
							p.setCantidad(p.getCantidad() - a.getCantidad());
							removes.add(a);
						}
					}

					if (!removes.isEmpty()) {
						for (EncuentraPedidoArticulo r : removes) {
							p.getArticulosPedido().remove(r);
						}
					}

					pedidos.add(p);

				}
			}


			Hashtable<String, String> couriersHT = new Hashtable<String, String>();
			Hashtable<String, String> puisHT = new Hashtable<String, String>();

					
			
			
			Map<String, String> articulos = new HashMap<>();

			
			
			
			
			for (EncuentraPedido p : pedidos)
			{
				int contador = 0;
				System.out.println(p.getIdFenicio());

			
				for (EncuentraPedidoArticulo a : p.getArticulosPedido()) {
					
					String articulo = a.getSKUFenicio();
					
					articulo = articulo.replace("[A]", "");
					articulo = articulo.replace("[T]", "");
					articulo = articulo.replace(":", "-");
					try
					{
						String preGuion = articulo.substring(0, articulo.lastIndexOf('-'));
						String postGuion = articulo.substring(articulo.lastIndexOf('-') + 1);
							
						articulo = preGuion + postGuion;
						
					}
					catch (Exception e) 
					{
						System.out.println("el articulo "+articulo+" no entra");
					}
					articulos.put(articulo, articulo);
					a.setOrigen(1);
					a.setArticulo(articulo);

					

				

					// SETTEAR EL OVL con el valor del str nuevo del idArticulo
					p.getOrden().getOrdenVentaLineas().get(contador).setIdArticulo(articulo);
				}

			}
			List<DataIDDescripcion> listaOK = wms.SaveOrders(token, pedidos);

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

			if (!estadosMP.isEmpty()) {
				LogicaAPI.putColaEstadoMarketPlace(estadosMP);
			}

			pedidos = pedidos2;

			List<DataIDDescripcion> depositosDestino = wms.DarDatosPutOrders(token, 2);

			 Map<Integer, DataIDDescripcion> mailsDepositos = new HashMap<>();
			for (DataIDDescripcion d : depositosDestino) {
				 mailsDepositos.put(d.getId(), d);
			}

			
			
			
			for (EncuentraPedido p : pedidos) 
			{
				Ordenes ovf = f.OrdenVentaFenicio(p.getIdPedido(), f.getIdEmpresa(), 1);
				try {
					boolean found = false;

					if (ovf.getOrigen().equals("MERCADOLIBRE")) {
						p.setShippingType(new DataIDDescripcion(1, ""));
						p.setIdDepositoEnvio(60);
						found = true;

					} else if (!p.getSucursalPick().equals("")) {
						// es pickup
						p.setShippingType(new DataIDDescripcion(2, ""));
						int idDepositoEnvio = Integer.parseInt(p.getSucursalPick());
						p.setIdDepositoEnvio(idDepositoEnvio);
						found = true;
					} else {
						p.setShippingType(new DataIDDescripcion(1, ""));
						for (DataIDDescripcion d : depositosDestino) {
							if (d.getDescripcion().equals(p.getEmpresaEnvio())) {
								p.setIdDepositoEnvio(d.getId());
								found = true;
								break;
							}
						}
					}
					if (!found) {
						if (ovf.getEntrega().getServicioEntrega() != null && ovf.getEntrega().getServicioEntrega()
								.getNombre().equals("Retiro Sucursal Agencia Central")) {
							p.setShippingType(new DataIDDescripcion(1, ""));
							p.setIdDepositoEnvio(50);
						}
						if (ovf.getEntrega().getServicioEntrega() != null
								&& ovf.getEntrega().getServicioEntrega().getNombre().equals("Entregas La Cancha")) {
							p.setShippingType(new DataIDDescripcion(1, ""));
							p.setIdDepositoEnvio(20);
						}

						else {
							p.setShippingType(new DataIDDescripcion(1, ""));
							System.out.println("No encontre " + p.getEmpresaEnvio());
							p.setIdDepositoEnvio(0);
						}

					}
				} catch (Exception e) {
					p.setShippingType(new DataIDDescripcion(1, ""));
					e.printStackTrace();
					p.setIdDepositoEnvio(0);
				}
			}

			List<DataIDDescripcion> arts = new ArrayList<>();

			Map<String, ArticuloCantidadEncuentra> hashartCantEnc = new HashMap<>();

			for(Entry<String, String> articulo : articulos.entrySet()) {
			
				arts.add(new DataIDDescripcion(1, articulo.getValue()));
			
			}

			
			
			 
			int idCanal = canal.getId();
			
			List<DepositoStock> stock = wms.darDepositos(arts, token, idCanal);

			System.out.println("");

			OrderDerivator od = new OrderDerivator();
			pedidos = od.derivar(pedidos, wms, token, stock);

			wms.SaveOrdersArticulosReq(token, pedidos, idCanal);

			ImpresionesPDF_API ip = new ImpresionesPDF_API(f.getIdEmpresa());
			Map<Integer, List<EncuentraPedido>> pedidosTiendas = new HashMap<>();
			Map<Integer, List<EncuentraPedidoArticulo>> solicitudesTiendas = new HashMap<>();

			for (EncuentraPedido p : pedidos) 
			{

				if (p.isPreparaTienda()) {
					p.setShippingType(new DataIDDescripcion(3, ""));
				}
				wms.updateDestinoPedido(token, p, p.getIdDepositoEnvio(), p.getMontoEnvio());

				/************ orden de venta ************/
				p.getOrden().save("", f.getIdEmpresa());

				if (p.isPreparaTienda()) 
				{
					List<EncuentraPedido> lista = null;
					if (pedidosTiendas.get(p.getArticulosPedido().get(0).getOrigen()) == null) {
						lista = new ArrayList<>();

					} else {
						lista = pedidosTiendas.get(p.getArticulosPedido().get(0).getOrigen());
					}
					lista.add(p);
					pedidosTiendas.put(p.getArticulosPedido().get(0).getOrigen(), lista);

				}
				else 
				{

					/************ movimiento de stock ************/
					boolean todosMismoOrigen = true;
					boolean central = false;
					int origenAnt = 0;
					boolean pri = true;
					for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
					{
						List<EncuentraPedidoArticulo> solis = null;
						if (solicitudesTiendas.get(arp.getOrigen()) == null) 
						{
							solis = new ArrayList<>();

						} 
						else 
						{
							solis = solicitudesTiendas.get(arp.getOrigen());
						}
						solis.add(arp);
						solicitudesTiendas.put(arp.getOrigen(), solis);

						if (pri) {
							pri = false;
							origenAnt = arp.getOrigen();
						}

						if (arp.getOrigen() != idDepositoCentral) 
						{
							boolean confirmarMovimiento = false;
							
							llamarMovimientoStock(arp, idDepositoEcommerce, logica, ip, f.getIdEmpresa(),p.getIdPedido(), confirmarMovimiento, false);
						} 
						else 
						{
							central = true;
						}

						if (origenAnt != arp.getOrigen()) 
						{
							todosMismoOrigen = false;
						}

						origenAnt = arp.getOrigen();
					}

					if (central && todosMismoOrigen) 
					{
						for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
						{
							boolean confirmarMovimiento = true;
							llamarMovimientoStock(arp, idDepositoEcommerce, logica, ip, f.getIdEmpresa(),p.getIdPedido(), confirmarMovimiento, central);
						}
					} 
					else if (central && !todosMismoOrigen) 
					{
						for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) {

							if (arp.getOrigen() == idDepositoCentral) {
								
								boolean confirmarMovimiento = false;
								llamarMovimientoStock(arp, idDepositoEcommerce, logica, ip, f.getIdEmpresa(),p.getIdPedido(), confirmarMovimiento, central);
							}
						}
					}
				}
			}

			List<SendMail> mails = new ArrayList<>();

			for (Entry<Integer,List<EncuentraPedido>> pedido : pedidosTiendas.entrySet()) 
			{
				
				List<EncuentraPedido> pedidosTienda = pedido.getValue();

				for (EncuentraPedido p : pedidosTienda) {
					/************ movimiento de stock ************/
					List<DataIDDescripcion> movsStock = new ArrayList<>();
					for (EncuentraPedidoArticulo arp : p.getArticulosPedido()) 
					{
						movsStock.add(new DataIDDescripcion(arp.getCantidad(), arp.getArticulo()));
					}

					// PERSISTO MOVIMIENTOS DE STOCK
					boolean confirmarMovimiento = true;

					int idMS = logica.RegistrarMovimientoStock(pedido.getKey(), idDepositoEcommerce, 0, movsStock,	f.getIdEmpresa(), p.getIdPedido(), 0, 0, confirmarMovimiento);
					ip.imprimirTicketMovStock(pedido.getKey(), idDepositoEcommerce, 0, "Para Venta WEB " + p.getIdPedido(),	movsStock, idMS, pedido.getKey());
				}

				try 
				{
					ip.ImprimirPedidosArticuloReq(pedidosTienda, pedido.getKey());
				} catch (Exception e) {
					e.printStackTrace();
				}

				// mails


				mails.add(od.mailsSolicitudPickup(pedidosTienda, mailsDepositos.get(pedido.getKey())));

			}

			for (Entry<Integer, List<EncuentraPedidoArticulo>> entry : solicitudesTiendas.entrySet())
			{
				List<EncuentraPedidoArticulo> solicitudesTienda = entry.getValue();
				mails.add(od.mailsSolicitudWeb(solicitudesTienda, mailsDepositos.get(entry.getKey())));

			}

			// envio mails
			LogicaAPI.PutMailSpooler(mails.toArray(new SendMail[mails.size()]), f.getIdEmpresa());

			// SINCRO ETIQUETAS
			System.out.println("esperando 60 segundos para sincronizar etiquetas nuevamente");
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			f.buscarEtiquetas(pedidosFenicio, wms, token, canal.getId());

			// CONSULTO ESTADO DE DESPACHADOS
			Map<String, String> pedidosDespachados = wms.PedidosID(token, 30, "4");
			List<DataIDDescripcion> entregados = f.tackPedidosDespachados(pedidosDespachados, canal.getId());
			if (!entregados.isEmpty()) {
				wms.updateOrdersStatus(token, entregados);
			}
		} // for de canales
	}

	static void llamarMovimientoStock(EncuentraPedidoArticulo arp, int idDepositoEcommerce, LogicaAPI logica,ImpresionesPDF_API ip, int idEmpresa, Long idPedido, boolean confirmarMovimiento, boolean central) 
	{
		List<DataIDDescripcion> movsStock = new ArrayList<>();
		movsStock.add(new DataIDDescripcion(arp.getCantidad(), arp.getArticulo()));
		
		int idMS = logica.RegistrarMovimientoStock(arp.getOrigen(), idDepositoEcommerce, 0, movsStock, idEmpresa,idPedido, 0, 0, confirmarMovimiento);
		if (!confirmarMovimiento || (confirmarMovimiento && !central)) {
			ip.imprimirTicketMovStock(arp.getOrigen(), idDepositoEcommerce, 0, "Para Venta WEB " + idPedido, movsStock,
					idMS, arp.getOrigen());
		}
	}
}
