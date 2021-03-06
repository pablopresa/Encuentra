package beans.datatypes;

public class RemitoLinea {
	private String idArticulo,entrega,venta ;
	private int cantidad, cantidadVerificada;
	
	
	
	public int getCantidadVerificada() {
		return cantidadVerificada;
	}
	public void setCantidadVerificada(int cantidadVerificada) {
		this.cantidadVerificada = cantidadVerificada;
	}
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
	public RemitoLinea(String idArticulo, int cantidad) {
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
		this.cantidadVerificada=0;
	}
	public RemitoLinea(String idArticulo, int cantidad, String afectacion) {
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
		this.cantidadVerificada=0;
		this.entrega = afectacion;
	}
	public String getEntrega() {
		return entrega;
	}
	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}
	public String getVenta() {
		return venta;
	}
	public void setVenta(String venta) {
		this.venta = venta;
	}
}
