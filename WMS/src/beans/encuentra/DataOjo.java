package beans.encuentra;

import java.util.List;

public class DataOjo 
{
	private int cantLibre;
	private int cantOcupada;
	private String idOjo;
	
	private int anchoOjo;
	private int altoOjo;
	
	
	private int porcentaje;
	private List<DataArticulo> articulos;
		
	
	public int getAnchoOjo() {
		return anchoOjo;
	}
	public void setAnchoOjo(int anchoOjo) {
		this.anchoOjo = anchoOjo;
	}
	public int getAltoOjo() {
		return altoOjo;
	}
	public void setAltoOjo(int altoOjo) {
		this.altoOjo = altoOjo;
	}
	public int getCantLibre() {
		return cantLibre;
	}
	public void setCantLibre(int cantLibre) {
		this.cantLibre = cantLibre;
	}
	public int getCantOcupada() {
		return cantOcupada;
	}
	public void setCantOcupada(int cantOcupada) {
		this.cantOcupada = cantOcupada;
	}
	public String getIdOjo() {
		return idOjo;
	}
	public void setIdOjo(String idOjo) {
		this.idOjo = idOjo;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	public List<DataArticulo> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<DataArticulo> articulos) {
		this.articulos = articulos;
	}
	public DataOjo() {
	}
	
	
	
	
	
	
	
}
