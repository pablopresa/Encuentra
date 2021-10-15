package integraciones.erp.oneBeat;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import beans.datatypes.DataIDDescripcion;
import beans.encuentra.ArticuloRepoFromLoad;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;
import persistencia.MSSQL_API;

public class SincroPickingOB {

	public static void main(String[] args) 
	{
		Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();
		LogicaAPI logica = new LogicaAPI();
		String token = logica.darToken(1,1000);
		
		
		
		
		
		Hashtable<Integer,ArticuloRepoFromLoad> pickingDestinos = MSSQL_API.darPickingsOB();
		
		List<ArticuloRepoFromLoad> lista2 = new ArrayList<ArticuloRepoFromLoad>(pickingDestinos.values());
	 	List <DataIDDescripcion> respuesta = cwa.savePickingOrder(token,lista2);

	}
	
	public SincroPickingOB() {}

}
