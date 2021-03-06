package beans.encuentra.ValueObjects;

public class VORecepcionSinOrden {
	
	private String idArticulo;
	private int cantidadPacking;
	private int cantidadBultos;
	private int cantidadTotal;
	
	public VORecepcionSinOrden(String idArticulo, int cantidadPacking, int cantidadBultos, int cantidadTotal) {
		super();
		this.idArticulo = idArticulo;
		this.cantidadPacking = cantidadPacking;
		this.cantidadBultos = cantidadBultos;
		this.cantidadTotal = cantidadTotal;
	}
	
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public int getCantidadPacking() {
		return cantidadPacking;
	}
	public void setCantidadPacking(int cantidadPacking) {
		this.cantidadPacking = cantidadPacking;
	}
	public int getCantidadBultos() {
		return cantidadBultos;
	}
	public void setCantidadBultos(int cantidadBultos) {
		this.cantidadBultos = cantidadBultos;
	}
	public int getCantidadTotal() {
		return cantidadTotal;
	}
	public void setCantidadTotal(int cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}

	@Override
	public String toString() {
		return "VORecepcionSinOrden [idArticulo=" + idArticulo + ", cantidadPacking=" + cantidadPacking
				+ ", cantidadBultos=" + cantidadBultos + ", cantidadTotal=" + cantidadTotal + "]";
	}
	
}
