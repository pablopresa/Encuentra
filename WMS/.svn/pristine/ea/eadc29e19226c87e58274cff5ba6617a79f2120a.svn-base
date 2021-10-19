package logica.expedicion;

import java.util.Hashtable;
import java.util.List;

import beans.encuentra.Ruta;
import persistencia.expedicion.PersistenciaRutas;

public class LogicaRutas 
{
	public List<Ruta> darRutas(int idRuta, int idEmpresa)
	{
		PersistenciaRutas pr = new PersistenciaRutas();
		return pr.darRutas(idEmpresa, idRuta);
		
	}
	
	public Hashtable<Integer, Ruta> darRutasHT(int idRuta, int idEmpresa)
	{
		PersistenciaRutas pr = new PersistenciaRutas();
		return pr.darRutasHT(idEmpresa, idRuta);
		
	}

}
