package beans.api;

public class DataMovimiento {
	private String idArticulo;
	private int cantidad;
	private int origen;
	private int destino;
	private int usuario;
	private String descripcion;
	
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	

	public DataMovimiento() {
		
	}
	
	public DataMovimiento(String art, int cant, int or, int dest) {
		this.idArticulo = art;
		this.cantidad = cant;
		this.origen = or;
		this.destino = dest;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
