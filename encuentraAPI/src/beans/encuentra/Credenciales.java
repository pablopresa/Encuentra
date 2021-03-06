package beans.encuentra;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Credenciales {

	private String user;
	private String pass;
	private String token;
	private String prestador;
	private String idCliente;
	private String tipoEnvio;
	private String pedido;
	private String fecha;
	private String hora;
	private String tracking;
	private String precio;
	private String idDestinoEncuentra;
	
	
	public String getPedido() {
		return pedido;
	}
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPrestador() {
		return prestador;
	}
	public void setPrestador(String prestador) {
		this.prestador = prestador;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getIdDestinoEncuentra() {
		return idDestinoEncuentra;
	}
	public void setIdDestinoEncuentra(String idDestinoEncuentra) {
		this.idDestinoEncuentra = idDestinoEncuentra;
	}
	public Credenciales(){
		
	}
	public String getTracking() {
		return tracking;
	}
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}
	public Credenciales(String user, String pass, String token, String idCliente, String pedido, String fecha,
			String hora, String tracking, String precio, String idDestinoEncuentra) {
		this.user = user;
		this.pass = pass;
		this.token = token;
		this.idCliente = idCliente;
		this.pedido = pedido;
		this.fecha = fecha;
		this.hora = hora;
		this.tracking = tracking;
		this.precio = precio;
		this.idDestinoEncuentra = idDestinoEncuentra;
	}
	
	
}
