package beans.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class pedidoFactura {
	
	private Long idPedido;
	private int nroFactura;
	private String pdf;
	
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public int getNroFactura() {
		return nroFactura;
	}
	public void setnNoFactura(int factura) {
		this.nroFactura = factura;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	
	public pedidoFactura (Long pedido, int factura) {
		this.idPedido = pedido;
		this.nroFactura = factura;
		this.pdf = "";
	}
}
