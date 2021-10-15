package beans;

public class BultosACrear {
	
	String ubicacion;
	String idArticulo;
	int cantidad;
	String idBulto;

	public BultosACrear() {
	}

	public String getIdBulto() {
		return idBulto;
	}

	public void setIdBulto(String idBulto) {
		this.idBulto = idBulto;
	}

	public BultosACrear(String ubicacion, String idArticulo, int cantidad, String idBulto) {
		super();
		this.ubicacion = ubicacion;
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
		this.idBulto = idBulto;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
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
