package beans.reportes;

public class ExpedicionMovimiento {
	
	private String nroSolicitud,origen,destino,seccion,sku,marca, clase,nroBulto,nroDocumento,fechaCarga;



	public ExpedicionMovimiento(String nroSolicitud, String origen, String destino, String seccion, String sku,
			String marca, String clase, String nroBulto, String nroDocumento, String fechaCarga) {
		this.nroSolicitud = nroSolicitud;
		this.origen = origen;
		this.destino = destino;
		this.seccion = seccion;
		this.sku = sku;
		this.marca = marca;
		this.clase = clase;
		this.nroBulto = nroBulto;
		this.nroDocumento = nroDocumento;
		this.fechaCarga = fechaCarga;
	}



	public String getNroSolicitud() {
		return nroSolicitud;
	}



	public String getOrigen() {
		return origen;
	}



	public String getDestino() {
		return destino;
	}



	public String getSeccion() {
		return seccion;
	}



	public String getSku() {
		return sku;
	}



	public String getMarca() {
		return marca;
	}



	public String getClase() {
		return clase;
	}



	public String getNroBulto() {
		return nroBulto;
	}



	public String getNroDocumento() {
		return nroDocumento;
	}



	public String getFechaCarga() {
		return fechaCarga;
	}


	
}
