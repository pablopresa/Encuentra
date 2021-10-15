package logica;


public class PedidosAtr {
	private String idPedido,fecha,cliente,tel,mail,articulo,deposito,cantidad,fechaConfirmacion;

	public PedidosAtr(String idPedido, String fecha, String cliente, String tel, String mail, String articulo,
			String deposito, String cantidad, String fechaConfirmacion) {
		this.idPedido = idPedido;
		this.fecha = fecha;
		this.cliente = cliente;
		this.tel = tel;
		this.mail = mail;
		this.articulo = articulo;
		this.deposito = deposito;
		this.cantidad = cantidad;
		this.fechaConfirmacion = fechaConfirmacion;
	}

	public String getIdPedido() {
		return idPedido;
	}

	public String getFecha() {
		return fecha;
	}

	public String getCliente() {
		return cliente;
	}

	public String getTel() {
		return tel;
	}

	public String getMail() {
		return mail;
	}

	public String getArticulo() {
		return articulo;
	}

	public String getDeposito() {
		return deposito;
	}

	public String getCantidad() {
		return cantidad;
	}

	public String getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public void setFechaConfirmacion(String fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}
	
	
}
