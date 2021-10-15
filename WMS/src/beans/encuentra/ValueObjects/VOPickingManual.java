package beans.encuentra.ValueObjects;

public class VOPickingManual {
	private String idArticulo;
	private int destinoArchivo;
	private int cantidad;
	private String justificacion;
	private int pedido;
	
	public VOPickingManual(String idArticulo, int destinoArchivo, int cantidad, String justificacion,int pedido) {
		super();
		this.idArticulo = idArticulo;
		this.destinoArchivo = destinoArchivo;
		this.cantidad = cantidad;
		this.justificacion = justificacion;
		this.pedido = pedido;
	}
	
	

	public String getIdArticulo() {
		return idArticulo;
	}



	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}



	public int getDestinoArchivo() {
		return destinoArchivo;
	}



	public void setDestinoArchivo(int destinoArchivo) {
		this.destinoArchivo = destinoArchivo;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public String getJustificacion() {
		return justificacion;
	}



	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}



	public int getPedido() {
		return pedido;
	}



	public void setPedido(int pedido) {
		this.pedido = pedido;
	}



	@Override
	public String toString() {
		return "VOPickingManual [idArticulo=" + idArticulo + ", destino=" + destinoArchivo
				+ ", cantidad=" + cantidad + ", justificacion=" + justificacion + ", pedido=" + pedido + "]";
	}
	
}
