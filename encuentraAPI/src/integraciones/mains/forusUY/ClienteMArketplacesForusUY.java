package integraciones.mains.forusUY;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import beans.datatypes.DataIDDescripcion;
import integraciones.marketplaces.fenicioTrack.FenicioForusUY;
import integraciones.marketplaces.forus.GetPedidosForusVS;
import integraciones.marketplaces.objetos.CanalMarketPlace;
import integraciones.marketplaces.objetos.marketPlace;
import integraciones.marketplaces.vtex.VtexForus;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class ClienteMArketplacesForusUY {

	public static void main(String[] args) {

		FenicioForusUY f = new FenicioForusUY();
		VtexForus v = new VtexForus();

		List<marketPlace> marketplaces = new ArrayList<>();
		marketplaces.add(f);
		marketplaces.add(v);

		Call_WS_APIENCUENTRA wms = new Call_WS_APIENCUENTRA();
		LogicaAPI logica = new LogicaAPI();
		String token = logica.darToken(v.getIdEmpresa());
		Map<Integer, String> integraciones = wms.darIntegraciones(token);
		String cambioEstado = integraciones.get(3) != null ? integraciones.get(3) : "0";
		int diasBusqueda = 6;

		Map<String, DataIDDescripcion> destinoPedidos = new HashMap<>();
		List<DataIDDescripcion> depositosDestino = wms.DarDatosPutOrders(token, 2);
		GetPedidosForusVS gp = new GetPedidosForusVS();

		for (marketPlace mp : marketplaces) {
			if (mp.getIdMarketPlace() != 1) {
				for (Entry<Integer, CanalMarketPlace> canal : mp.getCanales().entrySet()) {
//				 0 buscamos los destinos de los pedidos
					destinoPedidos = f.DestinoPedidos(canal.getKey(), diasBusqueda, destinoPedidos);
				}
			}
		}

		for (marketPlace mp : marketplaces) {
			if (mp.getIdMarketPlace() != 1) {

				for (Entry<Integer, CanalMarketPlace> entry : mp.getCanales().entrySet()) {

					CanalMarketPlace canal = entry.getValue();

					try {

						canal.guardarOrdenes(v.getIdEmpresa(), wms, token, cambioEstado, diasBusqueda, destinoPedidos,
								gp, depositosDestino, mp);

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} // for de canales
		}

	}

}
