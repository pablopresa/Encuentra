package beans.reportes;

public class PedidosProcesadosXOperario {
	private String cantidadProcesados, pickeds, usuario;

	public PedidosProcesadosXOperario(String cantidadProcesados, String pickeds, String usuario) {
		this.cantidadProcesados = cantidadProcesados;
		this.pickeds = pickeds;
		this.usuario = usuario;
	}

	public String getCantidadProcesados() {
		return cantidadProcesados;
	}

	public String getPickeds() {
		return pickeds;
	}

	public String getUsuario() {
		return usuario;
	}
	
}
