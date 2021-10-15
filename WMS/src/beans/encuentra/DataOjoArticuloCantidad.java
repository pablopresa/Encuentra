package beans.encuentra;

public class DataOjoArticuloCantidad
{
	private String idOjo;
	private int cantidad;
	private String idArticulo;
	private String descripcion;

	

	public DataOjoArticuloCantidad(String idOjo, int cantidad, String idArticulo, String descripcion) {
		super();
		this.idOjo = idOjo;
		this.cantidad = cantidad;
		this.idArticulo = idArticulo;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "DataOjoArticuloCantidad [Ojo=" + idOjo + ", cantidad=" + cantidad+"]";
	}

	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getIdOjo() {
		return idOjo;
	}
	public void setIdOjo(String idOjo) {
		this.idOjo = idOjo;
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
	
	
	
		

}
