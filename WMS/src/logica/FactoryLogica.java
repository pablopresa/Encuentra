package logica;

import java.util.Hashtable;
import java.util.List;

import beans.encuentra.Ruta;
import logica.expedicion.LogicaRutas;

public class FactoryLogica 
{
	public FactoryLogica()
	{
		
	}
	
	public List<Ruta> darRutas(int idEmpresa,int idRuta)
	{
		LogicaRutas lr = new LogicaRutas();
		return lr.darRutas(idRuta, idEmpresa);
	}
	
	public Hashtable<Integer, Ruta> darRutasHT(int idEmpresa,int idRuta)
	{
		LogicaRutas lr = new LogicaRutas();
		return lr.darRutasHT(idRuta, idEmpresa);
	}

}
