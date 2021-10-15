package beans.encuentra;

import java.util.List;

public class DataArtMovS 
{
	private int idMS;
	private int idDoc;
	private String articulo;
	private int cantidad;
	private int cantidadDoc;
	private List<String> barras;
	private boolean in;
	
	
	public int getCantidadDoc() {
		return cantidadDoc;
	}
	public void setCantidadDoc(int cantidadDoc) {
		this.cantidadDoc = cantidadDoc;
	}
	public boolean isIn() {
		return in;
	}
	public void setIn(boolean in) {
		this.in = in;
	}
	public int getIdMS() {
		return idMS;
	}
	public void setIdMS(int idMS) {
		this.idMS = idMS;
	}
	public int getIdDoc() {
		return idDoc;
	}
	public void setIdDoc(int idDoc) {
		this.idDoc = idDoc;
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public List<String> getBarras() {
		return barras;
	}
	public void setBarras(List<String> barras) {
		this.barras = barras;
	}
	public DataArtMovS() 
	{
		
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	
	
	
	
	
	
}
