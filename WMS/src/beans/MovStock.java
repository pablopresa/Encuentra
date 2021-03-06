package beans;

import java.util.List;

import dataTypes.DataIDDescripcion;

public class MovStock {

	private int id, cantidad, estado, destino, doc, origen,  intentos, docSolicitud, idUsuario, razon;
	private String fecha, observacion, usuario, nombreDestino, msjErp;
	private List<DataIDDescripcion> detalle;
	private Long origenDoc;
	private boolean entrega;
	
	

	public Long getOrigenDoc() {
		return origenDoc;
	}



	public void setOrigenDoc(Long picking) {
		this.origenDoc = picking;
	}



	public int getIntentos() {
		return intentos;
	}



	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}



	public String getNombreDestino() {
		return nombreDestino;
	}



	public void setNombreDestino(String nombreDestino) {
		this.nombreDestino = nombreDestino;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public int getEstado() {
		return estado;
	}



	public void setEstado(int estado) {
		this.estado = estado;
	}



	public int getDestino() {
		return destino;
	}



	public void setDestino(int destino) {
		this.destino = destino;
	}



	public int getDoc() {
		return doc;
	}



	public void setDoc(int doc) {
		this.doc = doc;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public String getObservacion() {
		return observacion;
	}



	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	public MovStock() {
	}



	public String getUsuario() {
		return usuario;
	}



	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}



	public int getOrigen() {
		return origen;
	}



	public void setOrigen(int origen) {
		this.origen = origen;
	}



	public List<DataIDDescripcion> getDetalle() {
		return detalle;
	}



	public void setDetalle(List<DataIDDescripcion> detalle) {
		this.detalle = detalle;
	}



	public int getDocSolicitud() {
		return docSolicitud;
	}



	public void setDocSolicitud(int docSolicitud) {
		this.docSolicitud = docSolicitud;
	}



	public int getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}



	public int getRazon() {
		return razon;
	}



	public void setRazon(int razon) {
		this.razon = razon;
	}



	public String getMsjErp() {
		return msjErp;
	}



	public void setMsjErp(String msjErp) {
		this.msjErp = msjErp;
	}



	public boolean isEntrega() {
		return entrega;
	}



	public void setEntrega(boolean entregaSolicitud) {
		this.entrega = entregaSolicitud;
	}



	

}
