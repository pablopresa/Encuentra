package integraciones.marketplaces.objetos;

import java.util.List;
import java.util.Map;

import beans.encuentra.EncuentraPedido;

public interface marketPlaceInterface {
	List<UpdateStateResponse> UpdateState(String json,int canal);
	String JSONUpdateState(Long idPedido, String idEcommerce, String track, int estado);
	List<EncuentraPedido> getPedidos(int canal, String status, int dias) ;
	List<EncuentraPedido> getPedidos(int canal, String status, int dias, Map<String, String> pedidosIn, Map<String, Integer> depositosPickHT);
}


