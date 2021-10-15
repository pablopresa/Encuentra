package beans.api;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import beans.datatypes.DataIDDescripcion;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GrabarRecepcion {

	private int id, estado, destino, intentos, idUsuario, numeroDoc, nroProveedor, nroRemito, idRecepcion, idEmpresa, cantidad;
	private String fecha, observacion, msjErp, tipoAfecta, serieRemito;
	private List<DataIDDescripcion> detalle;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIntentos() {
		return intentos;
	}
	
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(int numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public int getNroProveedor() {
		return nroProveedor;
	}

	public void setNroProveedor(int nroProveedor) {
		this.nroProveedor = nroProveedor;
	}

	public int getNroRemito() {
		return nroRemito;
	}

	public void setNroRemito(int nroRemito) {
		this.nroRemito = nroRemito;
	}

	public int getIdRecepcion() {
		return idRecepcion;
	}

	public void setIdRecepcion(int idRecepcion) {
		this.idRecepcion = idRecepcion;
	}

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
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

	public String getMsjErp() {
		return msjErp;
	}

	public void setMsjErp(String msjErp) {
		this.msjErp = msjErp;
	}

	public String getTipoAfecta() {
		return tipoAfecta;
	}

	public void setTipoAfecta(String tipoAfecta) {
		this.tipoAfecta = tipoAfecta;
	}

	public String getSerieRemito() {
		return serieRemito;
	}

	public void setSerieRemito(String serieRemito) {
		this.serieRemito = serieRemito;
	}

	public List<DataIDDescripcion> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DataIDDescripcion> detalle) {
		this.detalle = detalle;
	}

	public GrabarRecepcion() {
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
