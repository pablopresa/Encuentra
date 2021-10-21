package integraciones.marketplaces.objetos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.jsonEstadoMP;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import integraciones.marketplaces.forus.GetPedidosForusVS;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class CanalMarketPlace {
	private int id;
	private String user;
	private String pass;
	private String nombre;
	private String host;
	private String seller;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String dominio) {
		this.host = dominio;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}	
	
	public void guardarOrdenes(int idEmpresa, Call_WS_APIENCUENTRA wms, String token, String cambioEstado, 
			int diasBusqueda, Map<String, DataIDDescripcion> destinoPedidos, GetPedidosForusVS gp, List<DataIDDescripcion> depositosDestino, marketPlace mp ) {

		// 1 - le pedimos los pedidos a fenicio API (son datos complementarios)
//	List<Ordenes> pedidosFenicio = f.getPedidosAPI(canal.getKey(), diasBusqueda);
		// 2 - le pedimos los pedidos a VISUAL STORE estructura del pedido
		Map<Long, EncuentraPedido> pedidosVS = gp.getPedidos(idEmpresa, destinoPedidos,
				this.id, depositosDestino);
		List<EncuentraPedido> pedidosALL = new ArrayList<>(pedidosVS.values());
		// 3 - traemos los pedidos de encuentra para determinar cuales son los que
		// debemos sincronizar y cuales no
		Map<String, String> pedidosEncuentra = wms.PedidosID(token, diasBusqueda, "");
		// 4 traemos los pedidos de la base de Visual de forus

		// esta es la lista de pedidos buenos, los que vamos a meter en encuentra
		List<EncuentraPedido> pedidos = new ArrayList<>();
		for (EncuentraPedido p : pedidosALL) {
			if (!pedidosEncuentra.containsKey(p.getIdPedido() + "")) {
				p.setTrackingNumber(p.getIdPedido() + "");
				List<EncuentraPedidoArticulo> removes = new ArrayList<>();

				for (EncuentraPedidoArticulo a : p.getArticulosPedido()) {
					if (a.getImporte() != null && a.getImporte() < 0.0) {
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

		List<DataIDDescripcion> listaOK = wms.SaveOrders(token, pedidos);

		List<EncuentraPedido> pedidos2 = new ArrayList<>();
		List<jsonEstadoMP> estadosMP = new ArrayList<>();

		for (DataIDDescripcion pl : listaOK) {
			for (EncuentraPedido p : pedidos) {
				if (((p.getIdPedido()) + "").equals(pl.getDescripcion())
						&& pl.getDescripcionB().equals("OK")) {
					pedidos2.add(p);
					if (cambioEstado != null && cambioEstado.equals("1")) {
						jsonEstadoMP estado = new jsonEstadoMP(p.getIdPedido() + "", 0, p.getIdPedido(),
								mp.JSONUpdateState(p.getIdPedido(), p.getIdPedido() + "", "", 1),
								this.id, idEmpresa);
						estadosMP.add(estado);
					}
				}
			}
		}

		if (!estadosMP.isEmpty()) {
			LogicaAPI.putColaEstadoMarketPlace(estadosMP);
		}

		pedidos = pedidos2;

		Map<String, DataIDDescripcion> pedidosHT = new HashMap<>();
		for (EncuentraPedido p : pedidos) {
			// Ordenes ovf = f.OrdenVentaFenicio(p.getIdPedido(), f.getIdEmpresa(), 1);
			try {
				boolean found = false;

				for (DataIDDescripcion d : depositosDestino) {
					if (d.getDescripcion().equals(p.getEmpresaEnvio())) {
						p.setIdDepositoEnvio(d.getId());
						p.setShippingType(new DataIDDescripcion(1, ""));
						found = true;
						break;
					}
				}
				if (!found) {

					p.setShippingType(new DataIDDescripcion(1, ""));
					System.out.println("No encontre " + p.getEmpresaEnvio());
					p.setIdDepositoEnvio(0);

				}
			} catch (Exception e) {
				p.setShippingType(new DataIDDescripcion(1, ""));
				e.printStackTrace();
				p.setIdDepositoEnvio(0);
			}

			DataIDDescripcion pdid = new DataIDDescripcion(0, p.getIdPedido() + "");
			pedidosHT.put(p.getIdFenicio() + "", pdid);

		}

		System.out.println("");
		wms.SaveOrdersArticulosReq(token, pedidos, this.id);

		pedidos = gp.setEtiquetas(pedidosHT, this.id, token, diasBusqueda, mp, wms);

		for (EncuentraPedido p : pedidos) {
			wms.updateDestinoPedido(token, p, p.getIdDepositoEnvio(), p.getMontoEnvio());
		}

		/*
		 * for (EncuentraPedido p : pedidos) { if (p.isPreparaTienda()) {
		 * p.setShippingType(new DataIDDescripcion(3, "")); }
		 * wms.updateDestinoPedido(token, p, p.getIdDepositoEnvio(), p.getMontoEnvio());
		 * /************ orden de venta *********** p.getOrden().save("",
		 * f.getIdEmpresa()); } // CONSULTO ESTADO DE DESPACHADOS Map<String, String>
		 * pedidosDespachados = wms.PedidosID(token, 30, "4"); List<DataIDDescripcion>
		 * entregados = f.tackPedidosDespachados(pedidosDespachados, canal.getId()); if
		 * (!entregados.isEmpty()) { wms.updateOrdersStatus(token, entregados); }
		 */
	
	}
	
}
