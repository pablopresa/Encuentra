package beans.encuentra;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrdenesLineas {

	private int nroLineaOrden;
	private String idArticulo;
	private int cantidad;
	
	
	
	
	
	
	
	public int getNroLineaOrden() {
		return nroLineaOrden;
	}
	public void setNroLineaOrden(int nroLineaOrden) {
		this.nroLineaOrden = nroLineaOrden;
	}
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
