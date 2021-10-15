package logica;

import beans.encuentra.DataLineaRepo;

public class DTO_ArticuloCantidad {
	private String idArticulo;
	private int cantidad, idRecorrido, destino, solicitud;
	private Long pedido;
	private String ojo;
	private boolean utilizado;
	
	public DTO_ArticuloCantidad(String idArticulo, int cantidad, String ojo, int idRecorrido) {
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
		this.ojo = ojo;
		this.idRecorrido = idRecorrido;
		this.utilizado = false;
	}
	
	public DTO_ArticuloCantidad(String idArticulo, int cantidad) {
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
	}
	
	public String getIdArticulo() {
		return idArticulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getOjo() {
		return ojo;
	}
	public void setOjo(String ojo) {
		this.ojo = ojo;
	}
	public int getIdRecorrido() {
		return idRecorrido;
	}
	public int getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(int solicitud) {
		this.solicitud = solicitud;
	}
	public int getDestino() {
		return destino;
	}
	public void setIdRecorrido(int idRecorrido) {
		this.idRecorrido = idRecorrido;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public boolean isUtilizado() {
		return utilizado;
	}

	public void setUtilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}

@Override
public boolean equals(Object obj) {
	return super.equals(((DataLineaRepo)obj).getIdArticulo().equalsIgnoreCase(this.idArticulo));
}

public Long getPedido() {
	return pedido;
}

public void setPedido(Long pedido) {
	this.pedido = pedido;
}
	
	
}