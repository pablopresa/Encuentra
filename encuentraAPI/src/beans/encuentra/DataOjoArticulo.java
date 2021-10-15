package beans.encuentra;

public class DataOjoArticulo 
{
	private String idOjo;
	private int idSector;
	private int estante;
	private int modulo;
	private String articulo, fechaupdated, descripcion; 
	private int cantidad;
	

	
	
	
	public String getFechaupdated() {
		return fechaupdated;
	}
	public void setFechaupdated(String fechaupdated) {
		this.fechaupdated = fechaupdated;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getIdOjo() {
		return idOjo;
	}
	public void setIdOjo(String idOjo) {
		this.idOjo = idOjo;
	}
	public int getIdSector() {
		return idSector;
	}
	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}
	public int getEstante() {
		return estante;
	}
	public void setEstante(int estante) {
		this.estante = estante;
	}
	public int getModulo() {
		return modulo;
	}
	public void setModulo(int modulo) {
		this.modulo = modulo;
	}
	
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public DataOjoArticulo() {
	}
	
	
	

}
