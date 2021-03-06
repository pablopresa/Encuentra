package beans;

import dataTypes.DataIDDescripcion;

public class ArticuloLineaReposicion 
{
	private int cantidad,toPick,idPick, solicitud;
	private String idArticulo, justificacion, ubicaciones, artDesc, posClasif;
	private DataIDDescripcion sinc, origen, destino;
	private Long pedido;
	private boolean ecommerce;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public DataIDDescripcion getSinc() {
		return sinc;
	}
	public void setSinc(DataIDDescripcion sinc) {
		this.sinc = sinc;
	}
	public DataIDDescripcion getOrigen() {
		return origen;
	}
	public void setOrigen(DataIDDescripcion origen) {
		this.origen = origen;
	}
	public DataIDDescripcion getDestino() {
		return destino;
	}
	public void setDestino(DataIDDescripcion destino) {
		this.destino = destino;
	}
	public ArticuloLineaReposicion(int cantidad, String idArticulo,
			String justificacion, DataIDDescripcion sinc,
			DataIDDescripcion origen, DataIDDescripcion destino) {
		this.cantidad = cantidad;
		this.idArticulo = idArticulo;
		this.justificacion = justificacion;
		this.sinc = sinc;
		this.origen = origen;
		this.destino = destino;
		this.posClasif = "1";
		this.pedido=0L;
	}
	public String getPosClasif() {
		return posClasif;
	}
	public void setPosClasif(String posClasif) {
		this.posClasif = posClasif;
	}
	public Long getPedido() {
		return pedido;
	}
	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}
	public String getUbicaciones() {
		return ubicaciones;
	}
	public void setUbicaciones(String ubicaciones) {
		this.ubicaciones = ubicaciones;
	}
	public int getToPick() {
		return toPick;
	}
	public void setToPick(int toPick) {
		this.toPick = toPick;
	}
	public int getIdPick() {
		return idPick;
	}
	public void setIdPick(int idPick) {
		this.idPick = idPick;
	}
	public String getArtDesc() {
		return artDesc;
	}
	public void setArtDesc(String artDesc) {
		this.artDesc = artDesc;
	}
	public boolean isEcommerce() {
		return ecommerce;
	}
	public void setEcommerce(boolean ecommerce) {
		this.ecommerce = ecommerce;
	}
	public int getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(int solicitud) {
		this.solicitud = solicitud;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
