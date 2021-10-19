package beans;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beans.datatypes.DataIDDescripcion;
import integraciones.marketplaces.fenicioTrack.Fenicio;
import integraciones.marketplaces.fenicioTrack.FenicioBAS;
import integraciones.marketplaces.fenicioTrack.FenicioForus;
import integraciones.marketplaces.fenicioTrack.FenicioLAISLA;
import integraciones.marketplaces.moddo.Moddo;
import integraciones.marketplaces.objetos.CanalMarketPlace;
import integraciones.marketplaces.objetos.UpdateStateResponse;
import integraciones.marketplaces.objetos.marketPlace;
import logica.LogicaAPI;

public class TaskStatusChange {

	public static void main(String[] args) {
		List<DataIDDescripcion> empresas = LogicaAPI.Empresas();
		
		for(DataIDDescripcion e: empresas) {
			List<marketPlace> marketPlaces= darInstanciasMarketPlaces(e.getId());
			for(marketPlace mp: marketPlaces) {
				/*Enumeration<Integer> canales = mp.getCanales().keys();
				while (canales.hasMoreElements()) {
					int c=canales.nextElement();
					List<jsonEstadoMP> pendientes = LogicaAPI.pendienteColaEstadoMarketPlace(c,mp.getIdEmpresa());
					if(!pendientes.isEmpty()) {
						StatusChange(mp, pendientes, c);
					}					
				}*/
				
				for(Integer c : mp.getCanales().keySet()) {
					List<jsonEstadoMP> pendientes = LogicaAPI.pendienteColaEstadoMarketPlace(c,mp.getIdEmpresa());
					if(!pendientes.isEmpty()) {
						StatusChange(mp, pendientes, c);
					}	
				}
			}			
		}
		
	}
	
	public TaskStatusChange () {}
	
	public static List<marketPlace> darInstanciasMarketPlaces(int idEmmpresa){
		List<marketPlace> lista = new ArrayList<>();
		try {
			marketPlace mp = null;
			switch (idEmmpresa) {
			case 2:
				mp = new FenicioForus();
				lista.add(mp);
				mp = new Moddo();
				lista.add(mp);
				break;
			case 4:
				
				break;
			case 8:
				mp = new FenicioBAS();
				lista.add(mp);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	public static void StatusChange(marketPlace mp, List<jsonEstadoMP> pendientes, int canal) {
		try {
			String json = "";
			int count = 0;
			if(mp instanceof Fenicio) {
				json += "[";
				for(jsonEstadoMP estado: pendientes) {
					count++;
					json += estado.getJson()+",";
					if(count >= 50) {
						json = json.substring(0,json.length()-1)+"]";
						ManipulacionRespuestaCliente(mp, json, canal);
						json = "[";
						count = 0;
					}
				}
				json = json.substring(0,json.length()-1)+"]";
				ManipulacionRespuestaCliente(mp, json, canal);
			}
			else {
				for(jsonEstadoMP estado: pendientes) {
					json += estado.getJson();
					ManipulacionRespuestaCliente(mp, json, canal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void ManipulacionRespuestaCliente(marketPlace mp, String json, int canal) {
		try {
			List<UpdateStateResponse> respuestas = mp.UpdateState(json, canal);
			for(UpdateStateResponse r: respuestas) {
				if(r.isStatus()) {
					LogicaAPI.updateColaEstadoMarketPlace(r.getId());
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
