package beans.encuentra;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class infoCourier {

	private String idPedido;
	private String canalSalida;
	
	private String destinatario;
	private String barrio;
	private String domicilio;
	private String nroPuerta;
	private String dpto;
	private String cp;
	private String tel;
	private String mail;
	private String observacion;
	private String rut_ci;
	private String tipoEnvio;
	private String fechaEntrega;
	private String horaEntrega;
	
	private infoCourier_Paquete[] paquetes;
	
	private String importe;


	public String getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}


	public String getCanalSalida() {
		return canalSalida;
	}


	public void setCanalSalida(String canalSalida) {
		this.canalSalida = canalSalida;
	}


	public String getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}


	public String getBarrio() {
		return barrio;
	}


	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public String getNroPuerta() {
		return nroPuerta;
	}


	public void setNroPuerta(String nroPuerta) {
		this.nroPuerta = nroPuerta;
	}


	public String getDpto() {
		return dpto;
	}


	public void setDpto(String dpto) {
		this.dpto = dpto;
	}


	public String getCp() {
		return cp;
	}


	public void setCp(String cp) {
		this.cp = cp;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getObservacion() {
		return observacion;
	}


	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}


	public String getRut_ci() {
		return rut_ci;
	}


	public void setRut_ci(String rut_ci) {
		this.rut_ci = rut_ci;
	}


	public String getTipoEnvio() {
		return tipoEnvio;
	}


	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}


	public String getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}


	public String getHoraEntrega() {
		return horaEntrega;
	}


	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}


	public String getImporte() {
		return importe;
	}


	public void setImporte(String importe) {
		this.importe = importe;
	}
	
	
	public infoCourier(){
		
	}
}
