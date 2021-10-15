package beans.encuentra;

import java.util.ArrayList;
import java.util.List;

import persistencia.expedicion.PersistenciaRutas;

public class Ruta 
{
	private int idRuta;
	private String descripcion;
	private List<RutaDeposito> depositos;
	
	public int getIdRuta() {
		return idRuta;
	}
	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<RutaDeposito> getDepositos() {
		return depositos;
	}
	public void setDepositos(List<RutaDeposito> depositos) {
		this.depositos = depositos;
	}
	public Ruta(int idRuta, String descripcion) {
		this.idRuta = idRuta;
		this.descripcion = descripcion;
		this.depositos = new ArrayList<RutaDeposito>();
	}
	
	
	public void ABM(int idEmpresa,boolean borrar) 
	{
		
		PersistenciaRutas pr = new PersistenciaRutas();
		pr.ABM(this.getIdRuta(),this.getDescripcion(),idEmpresa,borrar);
	}
	
	
	

}

