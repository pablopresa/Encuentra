package beans.reportes;

import java.io.Serializable;

public class BultosRangoFechaDestino implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String fecha;
	private String origen;
	private String destino;
	private String cantBultos;

	public BultosRangoFechaDestino(String fecha, String origen, String destino, String cantBultos) {
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;
		this.cantBultos = cantBultos;
	}

	public String getFecha() {
		return fecha;
	}

	public String getOrigen() {
		return origen;
	}

	public String getDestino() {
		return destino;
	}

	public String getCantBultos() {
		return cantBultos;
	}
}
