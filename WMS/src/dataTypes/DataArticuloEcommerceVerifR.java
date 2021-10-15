package dataTypes;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataArticuloEcommerceVerifR 
{
	int cantidadPendiente;
	int totalPedido;
	int totalProcesado;
	int idDeposito;
	int ml;
	int canal;
	int idImpresora;
	int estadoEncuentra;
	int idDestino;
	String idArticulo;
	String descripcion;
	String urlEtiqueta;
	String mensaje;
	String ciCliente;
	String telCliente;
	String mailCliente;
	String fecha;
	String idEcommerce;
	String subpedido;
	String ticketNumber; 
	String fechaEntrega;
	String tracking;//idFenicio
	Long idPedido;
	boolean pickup;
	
	public boolean isPickup() {
		return pickup;
	}
	public void setPickup(boolean pickup) {
		this.pickup = pickup;
	}
	public String getTracking() {
		return tracking;
	}
	public void setTracking(String tracking) {
		this.tracking = tracking;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getSubpedido() {
		return subpedido;
	}
	public void setSubpedido(String subpedido) {
		this.subpedido = subpedido;
	}
	public int getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}
	public int getEstadoEncuentra() {
		return estadoEncuentra;
	}
	public void setEstadoEncuentra(int estadoEncuentra) {
		this.estadoEncuentra = estadoEncuentra;
	}
	public String getIdEcommerce() {
		return idEcommerce;
	}
	public void setIdEcommerce(String id) {
		this.idEcommerce = id;
	}
	public int getIdImpresora() {
		return idImpresora;
	}
	public void setIdImpresora(int idImpresora) {
		this.idImpresora = idImpresora;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCanal() {
		return canal;
	}
	public void setCanal(int canal) {
		this.canal = canal;
	}
	public String getMailCliente() {
		return mailCliente;
	}
	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
	}
	public String getCiCliente() {
		return ciCliente;
	}
	public void setCiCliente(String ciCliente) {
		this.ciCliente = ciCliente;
	}
	public String getTelCliente() {
		return telCliente;
	}
	public void setTelCliente(String telCliente) {
		this.telCliente = telCliente;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public int getCantidadPendiente() {
		return cantidadPendiente;
	}
	public void setCantidadPendiente(int cantidadPendiente) {
		this.cantidadPendiente = cantidadPendiente;
	}
	public int getTotalPedido() {
		return totalPedido;
	}
	public void setTotalPedido(int totalPedido) {
		this.totalPedido = totalPedido;
	}
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getIdDeposito() {
		return idDeposito;
	}
	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}
	public int getTotalProcesado() {
		return totalProcesado;
	}
	public void setTotalProcesado(int totalProcesado) {
		this.totalProcesado = totalProcesado;
	}
	public String getUrlEtiqueta() {
		return urlEtiqueta;
	}
	public void setUrlEtiqueta(String urlEtiqueta) {
		this.urlEtiqueta = urlEtiqueta;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getMl() {
		return ml;
	}
	public void setMl(int ml) {
		this.ml = ml;
	}
	public DataArticuloEcommerceVerifR(Long idPedido, int cantidadPendiente,	int totalPedido, String idArticulo, String descripcion, String urlEtiqueta, int idDeposito) 
	{
		this.idPedido = idPedido;
		this.cantidadPendiente = cantidadPendiente;
		this.totalPedido = totalPedido;
		this.idArticulo = idArticulo;
		this.descripcion = descripcion;
		this.urlEtiqueta=urlEtiqueta;
		this.idDeposito=idDeposito;
	}
	
	public DataArticuloEcommerceVerifR() 
	{
	}
	
	
	

}
