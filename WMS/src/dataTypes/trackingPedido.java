package dataTypes;

public class trackingPedido {
	private Long idPedido;
	private int idEcommerce;
	private int canal;
	private String courier;
	private String tracking;
	private String user;
	private String pass;
	private int destinoFinal;
	
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdEcommerce() {
		return idEcommerce;
	}
	public void setIdEcommerce(int idEcommerce) {
		this.idEcommerce = idEcommerce;
	}
	public int getCanal() {
		return canal;
	}
	public void setCanal(int canal) {
		this.canal = canal;
	}
	public String getTracking() {
		return tracking;
	}
	public void setTracking(String tracking) {
		this.tracking = tracking;
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
	
	public trackingPedido() {
		
	}
	public String getCourier() {
		return courier;
	}
	public void setCourier(String courier) {
		this.courier = courier;
	}
	public int getDestinoFinal() {
		return destinoFinal;
	}
	public void setDestinoFinal(int destinoFinal) {
		this.destinoFinal = destinoFinal;
	}
}
