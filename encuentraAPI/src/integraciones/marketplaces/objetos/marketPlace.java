package integraciones.marketplaces.objetos;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import beans.datatypes.DataIDDescripcion;
import beans.encuentra.EncuentraPedido;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class marketPlace implements marketPlaceInterface{
	protected int idEmpresa;
	protected int idMarketPlace; 
	protected Map<Integer, CanalMarketPlace> canales;
	protected boolean etiquetaEnvios;
	protected boolean MovStocks;
	protected boolean ordenes;
	protected boolean matrizDisponibilidad;
	
	public int getIdMarketPlace() {
		return idMarketPlace;
	}

	public void setIdMarketPlace(int idMarketPlace) {
		this.idMarketPlace = idMarketPlace;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Map<Integer, CanalMarketPlace> getCanales() {
		return canales;
	}

	public void setCanales(Map<Integer, CanalMarketPlace> canales) {
		this.canales = canales;
	}

	public boolean isEtiquetaEnvios() {
		return etiquetaEnvios;
	}

	public void setEtiquetaEnvios(boolean etiquetaEnvios) {
		this.etiquetaEnvios = etiquetaEnvios;
	}

	public boolean isMovStocks() {
		return MovStocks;
	}

	public void setMovStocks(boolean movStocks) {
		MovStocks = movStocks;
	}

	public boolean isOrdenes() {
		return ordenes;
	}

	public void setOrdenes(boolean ordenes) {
		this.ordenes = ordenes;
	}

	public boolean isMatrizDisponibilidad() {
		return matrizDisponibilidad;
	}

	public void setMatrizDisponibilidad(boolean matrizDisponibilidad) {
		this.matrizDisponibilidad = matrizDisponibilidad;
	}

	//SET DE CANALES
	protected void setCanales(){
		try {
			canales = LogicaAPI.canalesMarketPlace(this.idEmpresa, this.idMarketPlace);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	//METODOS
	@Override
	public List<UpdateStateResponse> UpdateState(String json, int canal) {
		return new ArrayList<>();
	}
	
	@Override
	public List<EncuentraPedido> getPedidos(int canal, String status, int dias) {
		return new ArrayList<>();
	}

	@Override
	public String JSONUpdateState(Long idPedido, String idEcommerce, String track, int estado) {
		return null;
	}



	@Override
	public List<EncuentraPedido> getPedidos(int canal, String status, int dias, Map<String, String> pedidosIn,
			Map<String, Integer> depositosPickHT) {
		return new ArrayList<>();
	}

	@Override
	public Map<String, DataIDDescripcion> DestinoPedidos(int canal, int dias,
			Map<String, DataIDDescripcion> retornable) {
		return null;
	}
	
	@Override
	public List<EncuentraPedido> buscarEtiquetas(List<DataIDDescripcion> pedidosSinE, Call_WS_APIENCUENTRA cen, String token, int canal, Map<String, Integer> depositosPickHT)
	{
		return null;
	}

	public void sinEtiquetas(Call_WS_APIENCUENTRA cen, String token, int canal, Map<String, Integer> depositosPickHT) {
		List<DataIDDescripcion> pedidosSinE = cen.DarDatosPutOrders(token, 5, canal+"");
		if (!pedidosSinE.isEmpty()) {
			List<EncuentraPedido> pedidosUpEtiqueta = this.buscarEtiquetas(pedidosSinE, cen, token, canal, depositosPickHT);
			if (!pedidosUpEtiqueta.isEmpty())
				cen.updateLabels(pedidosUpEtiqueta,token);
		}
			
		
	}

	
	
	
}
