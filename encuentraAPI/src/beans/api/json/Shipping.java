package beans.api.json;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;





@JsonIgnoreProperties(ignoreUnknown = true)
public class Shipping {


	private Credenciales credenciales;
	private Cliente cliente;
	
	private int cantidadPaquetes,xGrandes, grandes, medianos, chicos,tipoShipping;
	private String nombreRemite, horaDesde, horaHasta;
	private String peso,volumen,mailNotificacion;//se usan en pedidosYA
	private Cliente Sender;// se usa para pedidosYA
	
	
	public String getHoraDesde() {
		return horaDesde;
	}
	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}
	public String getHoraHasta() {
		return horaHasta;
	}
	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}
	public Credenciales getCredenciales() 
	{
		return credenciales;
	}
	public void setCredenciales(Credenciales credenciales2) 
	{
		this.credenciales = credenciales2;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Shipping(){
		
	}
	public int getCantidadPaquetes() {
		return cantidadPaquetes;
	}
	public void setCantidadPaquetes(int cantidadPaquetes) {
		this.cantidadPaquetes = cantidadPaquetes;
	}
	public int getGrandes() {
		return grandes;
	}
	public void setGrandes(int grandes) {
		this.grandes = grandes;
	}
	public int getMedianos() {
		return medianos;
	}
	public void setMedianos(int medianos) {
		this.medianos = medianos;
	}
	public int getChicos() {
		return chicos;
	}
	public void setChicos(int chicos) {
		this.chicos = chicos;
	}
	public int getTipoShipping() {
		return tipoShipping;
	}
	public void setTipoShipping(int tipoShipping) {
		this.tipoShipping = tipoShipping;
	}
	public String getNombreRemite() {
		return nombreRemite;
	}
	public void setNombreRemite(String nombreRemite) {
		this.nombreRemite = nombreRemite;
	}
	public int getxGrandes() {
		return xGrandes;
	}
	public void setxGrandes(int xGrandes) {
		this.xGrandes = xGrandes;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getVolumen() {
		return volumen;
	}
	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}
	public String getMailNotificacion() {
		return mailNotificacion;
	}
	public void setMailNotificacion(String mailNotificacion) {
		this.mailNotificacion = mailNotificacion;
	}
	public Cliente getSender() {
		return Sender;
	}
	public void setSender(Cliente sender) {
		Sender = sender;
	}
	public Shipping(Credenciales credenciales, Cliente cliente, int cantidadPaquetes, int tipoShipping, String peso,
			String volumen, String mailNotificacion, Cliente sender) {
		this.credenciales = credenciales;
		this.cliente = cliente;
		this.cantidadPaquetes = cantidadPaquetes;
		this.tipoShipping = tipoShipping;
		this.peso = peso;
		this.volumen = volumen;
		this.mailNotificacion = mailNotificacion;
		Sender = sender;
	}

	
	
	
	
}
