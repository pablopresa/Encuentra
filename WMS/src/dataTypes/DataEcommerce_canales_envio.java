package dataTypes;

public class DataEcommerce_canales_envio 
{
	private String tipoEnvio,codigo,courier,iddeposito,usuario,pass,NombreRemite, idTienda, fechaEntrega;

	public String getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public String getIddeposito() {
		return iddeposito;
	}

	public void setIddeposito(String iddeposito) {
		this.iddeposito = iddeposito;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public String getNombreRemite() {
		return NombreRemite;
	}

	public void setNombreRemite(String nombreRemite) {
		NombreRemite = nombreRemite;
	}

	public DataEcommerce_canales_envio(String tipoEnvio, String codigo, String courier, String iddeposito,
			String usuario, String pass) {
		this.tipoEnvio = tipoEnvio;
		this.codigo = codigo;
		this.courier = courier;
		this.iddeposito = iddeposito;
		this.usuario = usuario;
		this.pass = pass;
	}

	public String getIdTienda() {
		return idTienda;
	}

	public void setIdTienda(String idTienda) {
		this.idTienda = idTienda;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	

}
