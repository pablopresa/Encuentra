
package integraciones.mains.stadium;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import beans.datatypes.DTO_Articulo;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.ArticuloRepoFromLoad;
import integraciones.erp.oneBeat.SincroPickingOB;
import integraciones.erp.visualStore.stadium.sincro.Masters;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;
import persistencia.MSSQL_API;

public class SincMastersStadium 
{
	public static void main(String[] args) 
	{
		Masters ma = new Masters(1,15);
		
		Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();
		LogicaAPI logica = new LogicaAPI();
		String token = logica.darToken(1,10);
		
		/*cwa.putMaestros(token, ma.getCategorias(), "art_categoria");
		cwa.putMaestros(token, ma.getGeneros(), "art_genero");
		cwa.putMaestros(token, ma.getMarcas(), "art_marca");
		cwa.putMaestros(token, ma.getSecciones(), "art_seccion");
		cwa.putMaestros(token, ma.getClases(), "art_clase");
		cwa.putMaestros(token, ma.getTemporadas(), "art_temporadas");
		
		
		cwa.putArticulos(token,ma.getArticuos());*/
		cwa.putDeposM(token, ma.getDepositos());
		
		
		//sincronizo los picking tambien
		/*Hashtable<Integer,ArticuloRepoFromLoad> pickingDestinos = MSSQL_API.darPickingsOB();
		List<ArticuloRepoFromLoad> lista2 = new ArrayList<ArticuloRepoFromLoad>(pickingDestinos.values());
	 	List <DataIDDescripcion> respuesta = cwa.savePickingOrder(token,lista2);
		*/
		
		
		
		
	}
}
