package dataTypes;

import java.util.List;

public class DataContenedor 
{
	
	private int cantidad;
	private double importe;
	private List <DataInformeReclamos> listado;
	private String nombre;
	

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public List<DataInformeReclamos> getListado() {
		return listado;
	}
	public void setListado(List<DataInformeReclamos> listado) {
		this.listado = listado;
	}
	public DataContenedor(int cantidad, double importe,
			List<DataInformeReclamos> listado) {
		this.cantidad = cantidad;
		this.importe = importe;
		this.listado = listado;
	}
	public DataContenedor() {
	}
	
	
	

}
