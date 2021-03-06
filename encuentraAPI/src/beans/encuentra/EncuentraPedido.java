package beans.encuentra;

import java.util.ArrayList;
import java.util.List;

import beans.datatypes.DataIDDescripcion;
import integraciones.erp.visualStore.objetos.OrdenVenta;
import integraciones.erp.visualStore.objetos.OrdenVentaLinea;
import integraciones.marketplaces.fenicio.FenicioAPIorden;
import integraciones.marketplaces.fenicio.Lineas;
import integraciones.marketplaces.fenicio.Ordenes;

public class EncuentraPedido 
{
	private int cantidad, cerrado, ml, idDepositoEnvio;
	private String descripcion, estado,urlEtiqueta, sucursalPick, formaPago,fecha,empresaEnvio,empresaEnvioCod;
	private Double descuento,precio, montoEnvio;
	private List <EncuentraPedidoArticulo> articulosPedido;
	private List <EncuentraPedidoArticuloReq> articulosPedidoReq;
	private DataIDDescripcion canalMercadoLibre;
	private DataIDDescripcion shippingType;
	private int canalAnaloga;
	private String personaRetira;
	private Long idPedido;
	private String idPedidoSTR;
	private String idEcommerce;
	private String ticketNumber;
	private String trackingNumber;
	private String subpedido;
	private Cliente cliente;
	private OrdenVenta orden;
	private boolean freshipping,preparaTienda;
	DataIDDescripcion destino;
	
	
	
	
	
	public DataIDDescripcion getDestino() {
		return destino;
	}
	public void setDestino(DataIDDescripcion destino) {
		this.destino = destino;
	}
	public String getSubpedido() {
		return subpedido;
	}
	public void setSubpedido(String subpedido) {
		this.subpedido = subpedido;
	}
	public boolean isPreparaTienda() {
		return preparaTienda;
	}
	public void setPreparaTienda(boolean preparaTienda) {
		this.preparaTienda = preparaTienda;
	}
	private String horarioD,horarioH;
	
	
	
	public String getHorarioD() {
		return horarioD;
	}
	public void setHorarioD(String horarioD) {
		this.horarioD = horarioD;
	}
	public String getHorarioH() {
		return horarioH;
	}
	public void setHorarioH(String horarioH) {
		this.horarioH = horarioH;
	}
	
	
	
	
	
	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getMontoEnvio() {
		return montoEnvio;
	}
	public void setMontoEnvio(Double montoEnvio) {
		this.montoEnvio = montoEnvio;
	}
	public String getIdPedidoSTR() {
		return idPedidoSTR;
	}
	public void setIdPedidoSTR(String idPedidoSTR) {
		this.idPedidoSTR = idPedidoSTR;
	}
	
	public String getPersonaRetira() {
		return personaRetira;
	}
	public void setPersonaRetira(String personaRetira) {
		this.personaRetira = personaRetira;
	}
	public int getCanalAnaloga() {
		return canalAnaloga;
	}
	public void setCanalAnaloga(int canalAnaloga) {
		this.canalAnaloga = canalAnaloga;
	}
	public DataIDDescripcion getCanalMercadoLibre() {
		return canalMercadoLibre;
	}
	public void setCanalMercadoLibre(DataIDDescripcion canalMercadoLibre) {
		this.canalMercadoLibre = canalMercadoLibre;
	}
	public int getMl() {
		return ml;
	}
	public void setMl(int ml) {
		this.ml = ml;
	}
	public String getSucursalPick() {
		return sucursalPick;
	}
	public void setSucursalPick(String sucursalPick) {
		this.sucursalPick = sucursalPick;
	}
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}
	public List<EncuentraPedidoArticulo> getArticulosPedido() {
		return articulosPedido;
	}
	public void setArticulosPedido(List<EncuentraPedidoArticulo> articulosPedido) {
		this.articulosPedido = articulosPedido;
	}
	public List<EncuentraPedidoArticuloReq> getArticulosPedidoReq() {
		return articulosPedidoReq;
	}
	public void setArticulosPedidoReq(
			List<EncuentraPedidoArticuloReq> articulosPedidoReq) {
		this.articulosPedidoReq = articulosPedidoReq;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCerrado() {
		return cerrado;
	}
	public void setCerrado(int cerrado) {
		this.cerrado = cerrado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUrlEtiqueta() {
		return urlEtiqueta;
	}
	public void setUrlEtiqueta(String urlEtiqueta) {
		this.urlEtiqueta = urlEtiqueta;
	}
	public EncuentraPedido() {
	}
	
	
	
	public EncuentraPedido(Ordenes or) 
	{
		this.idEcommerce = or.getIdOrden();
		if(or.getEntrega().getHorario()!=null)
		{
			this.horarioD = or.getEntrega().getHorario().getDesde();
			this.horarioH = or.getEntrega().getHorario().getHasta();
		}
		else
		{
			this.horarioD = "";
			this.horarioH =  "";
		}
		
		
		 List <EncuentraPedidoArticulo> articulosPe = new ArrayList<>(); 
		
		for (Lineas li : or.getLineas()) 
		{
			EncuentraPedidoArticulo ar = new EncuentraPedidoArticulo();
			int cantidad = 0;
			int cantidadRegalo = 0;
			try
			{
				cantidad = Integer.parseInt(li.getCantidad());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			try
			{
				cantidadRegalo = Integer.parseInt(li.getCantidadRegalo());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			ar.setArticulo(li.getSku());
			ar.setCantidad(cantidad);
			ar.setCantidadRegalo(cantidadRegalo);
			
			articulosPe.add(ar);
		}
		
		this.setArticulosPedido(articulosPe);
		
		
		
	}
	
	
	
	
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	
	
	
	
	public int getIdDepositoEnvio() {
		return idDepositoEnvio;
	}
	public void setIdDepositoEnvio(int idDepositoEnvio) {
		this.idDepositoEnvio = idDepositoEnvio;
	}
	
	public String getIdFenicio() {
		return idEcommerce;
	}
	public void setIdFenicio(String id) {
		this.idEcommerce = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public boolean isFreshipping() {
		return freshipping;
	}
	public void setFreshipping(boolean freshipping) {
		this.freshipping = freshipping;
	}
	public OrdenVenta getOrden() {
		return orden;
	}
	public void setOrden(OrdenVenta orden) {
		this.orden = orden;
	}
	public String getEmpresaEnvio() {
		return empresaEnvio;
	}
	public void setEmpresaEnvio(String empresaEnvio) {
		this.empresaEnvio = empresaEnvio;
	}
	public String getEmpresaEnvioCod() {
		return empresaEnvioCod;
	}
	public void setEmpresaEnvioCod(String empresaEnvioCod) {
		this.empresaEnvioCod = empresaEnvioCod;
	}
	public DataIDDescripcion getShippingType() {
		return shippingType;
	}
	public void setShippingType(DataIDDescripcion shippingType) {
		this.shippingType = shippingType;
	}
	
	

	
	
}
