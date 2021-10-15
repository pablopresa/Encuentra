package eCommerce_jsonObjectsII;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class VentasModdo {
	private List<PedidoModdo> ventas;

	public List<PedidoModdo> getVentas() {
		return ventas;
	}

	public void setVentas(List<PedidoModdo> ventas) {
		this.ventas = ventas;
	}
	
	public VentasModdo(){
		
	}
}
