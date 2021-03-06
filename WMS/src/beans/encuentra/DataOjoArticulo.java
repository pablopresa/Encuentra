package beans.encuentra;

public class DataOjoArticulo 
{
	private String idOjo;
	private int idSector;
	private int estante;
	private int modulo;
	private String articulo, fechaupdated; 
	private int cantidad;
	private int stock;
	private String descripcion;
	private String alias;
	private int cantidadReservada;
	private int tipoSku;
	private String idBulto;
	private String descArticulo;
	
	
	
	
	
	public String getDescArticulo() {
		return descArticulo;
	}
	public void setDescArticulo(String descArticulo) {
		this.descArticulo = descArticulo;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "DataOjoArticulo [Ojo=" + idOjo + ", EST.=" + idSector
				+ ", e=" + estante + ", M=" + modulo + ", cantidad="
				+ cantidad + "]";
	}
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
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getCantidadReservada() {
		return cantidadReservada;
	}
	public void setCantidadReservada(int cantidadReservada) {
		this.cantidadReservada = cantidadReservada;
	}
	public int getTipoSku() {
		return tipoSku;
	}
	public void setTipoSku(int tipoSku) {
		this.tipoSku = tipoSku;
	}
	public String getIdBulto() {
		return idBulto;
	}
	public void setIdBulto(String idBulto) {
		this.idBulto = idBulto;
	}

	
	
	

}
