package beans.encuentra;

import java.util.Collection;
import java.util.List;

import beans.ArticuloMatrizHTML;

public class ArticuloDistribucion 
{
	private String articuloBase,descripcion;
	private int cantidad;
	private List <ArticuloMatrizHTML> artis;
	private Collection<ArticuloMatrizHTML> artisOriginales;
	
	public String getArticuloBase() {
		return articuloBase;
	}
	public void setArticuloBase(String articuloBase) {
		this.articuloBase = articuloBase;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public List<ArticuloMatrizHTML> getArtis() {
		return artis;
	}
	public void setArtis(List<ArticuloMatrizHTML> artis) {
		this.artis = artis;
	}
	
	public ArticuloDistribucion() 
	{
	}
	public Collection<ArticuloMatrizHTML> getArtisOriginales() {
		return artisOriginales;
	}
	public void setArtisOriginales(Collection<ArticuloMatrizHTML> artisOriginales) {
		this.artisOriginales = artisOriginales;
	}
	
	
	
	
	
	
	

}
