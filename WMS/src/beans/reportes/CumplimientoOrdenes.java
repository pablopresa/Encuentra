package beans.reportes;

public class CumplimientoOrdenes {
private String idPicking,fecha,usuario,solicitadas,remitidas;

public CumplimientoOrdenes(String idPicking, String fecha, String usuario, String solicitadas, String remitidas) {
	this.idPicking = idPicking;
	this.fecha = fecha;
	this.usuario = usuario;
	this.solicitadas = solicitadas;
	this.remitidas = remitidas;
}

public String getIdPicking() {
	return idPicking;
}

public String getFecha() {
	return fecha;
}

public String getUsuario() {
	return usuario;
}

public String getSolicitadas() {
	return solicitadas;
}

public String getRemitidas() {
	return remitidas;
}

}
