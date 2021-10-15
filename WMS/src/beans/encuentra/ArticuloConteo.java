package beans.encuentra;

import dataTypes.DataIDDescripcion;

public class ArticuloConteo {
	private String articulo, descripcion,fechaContada,idOjo;
	private int stock, cantidadContada;
	private DataIDDescripcion usuarioCuenta;
	private boolean inn;
	
	
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCantidadContada() {
		return cantidadContada;
	}
	public void setCantidadContada(int cantidadContada) {
		this.cantidadContada = cantidadContada;
	}
	public ArticuloConteo(String articulo, int stock, String descripcion) {
		
		this.articulo = articulo;
		this.stock = stock;
		this.descripcion = descripcion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechaContada() {
		return fechaContada;
	}
	public void setFechaContada(String fechaContada) {
		this.fechaContada = fechaContada;
	}
	public String getIdOjo() {
		return idOjo;
	}
	public void setIdOjo(String idOjo) {
		this.idOjo = idOjo;
	}
	public DataIDDescripcion getUsuarioCuenta() {
		return usuarioCuenta;
	}
	public void setUsuarioCuenta(DataIDDescripcion usuarioCuenta) {
		this.usuarioCuenta = usuarioCuenta;
	}
	public boolean isInn() {
		return inn;
	}
	public void setInn(boolean inn) {
		this.inn = inn;
	}
	
	public ArticuloConteo(String articulo, String fechaContada, String idOjo, int cantidadContada,
			DataIDDescripcion usuarioCuenta, boolean inn) {
		this.articulo = articulo;
		this.fechaContada = fechaContada;
		this.idOjo = idOjo;
		this.cantidadContada = cantidadContada;
		this.usuarioCuenta = usuarioCuenta;
		this.inn = inn;
	}
}
