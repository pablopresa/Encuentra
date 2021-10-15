package beans.encuentra;



import java.util.Hashtable;
import java.util.List;

import dataTypes.DataIDDescripcion;

public class DataArticuloOC 
{
	String idArticulo;
	String descripcionCorta;
	int idLinea;
	List <DataIDDescripcion> curva;
	private Hashtable<String, DataIDDescripcion>curvaH;
	
	
	List <ColorOC> colores;
	
	
	public String getIdArticulo() 
	{
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public List<DataIDDescripcion> getCurva() {
		return curva;
	}
	public void setCurva(List<DataIDDescripcion> curva) {
		this.curva = curva;
	}
	public List<ColorOC> getColores() {
		return colores;
	}
	public void setColores(List<ColorOC> colores) {
		this.colores = colores;
	}
	public DataArticuloOC() {
		super();
	}
	public Hashtable<String, DataIDDescripcion> getCurvaH() {
		return curvaH;
	}
	public void setCurvaH(Hashtable<String, DataIDDescripcion> curvaH) {
		try {
			this.curvaH = curvaH;
		} catch (Exception e) {
			this.curvaH = new Hashtable<>();
		}
		
	}
	public int getIdLinea() {
		return idLinea;
	}
	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}
	public String getDescripcionCorta() {
		return descripcionCorta;
	}
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}
	
	
	
	
}
