package integraciones.marketplaces.objetos;

import java.util.List;
import java.util.Map;

import beans.datatypes.DataIDDescripcion;
import beans.encuentra.EncuentraPedido;
import integraciones.wms.Call_WS_APIENCUENTRA;

public interface marketPlaceInterface {
	List<UpdateStateResponse> UpdateState(String json,int canal);
	String JSONUpdateState(Long idPedido, String idEcommerce, String track, int estado);
	List<EncuentraPedido> getPedidos(int canal, String status, int dias) ;
	List<EncuentraPedido> getPedidos(int canal, String status, int dias, Map<String, String> pedidosIn, Map<String, Integer> depositosPickHT);
	List<EncuentraPedido> buscarEtiquetas(List<DataIDDescripcion> pedidosSinE, Call_WS_APIENCUENTRA cen, String token, int canal, Map<String, Integer> depositosPickHT);
}


