package dataTypes;

public class DataPorcion 
{
	private int cantidad;
	private String descripcion;
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public DataPorcion(int cantidad, String descripcion) {
		this.cantidad = cantidad;
		this.descripcion = descripcion;
	}
	
	public DataPorcion(){}
	
	

}
