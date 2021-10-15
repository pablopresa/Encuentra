package dataTypes;

import java.util.List;

public class DataProveedorCybe implements Comparable
{
	private String nombre;
	private int cantidad;
	private Double importeTotal;
	private List<DataListaArregloCybe> lista;
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
	
	public Double getImporteTotal() 
	{
		return importeTotal;
	}
	public void setImporteTotal(Double importeTotal) 
	{
		this.importeTotal = importeTotal;
	}
	public List<DataListaArregloCybe> getLista() {
		return lista;
	}
	public void setLista(List<DataListaArregloCybe> lista) {
		this.lista = lista;
	}
	public DataProveedorCybe(String nombre, int cantidad, Double importeTotal,
			List<DataListaArregloCybe> lista) 
	{
		
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.importeTotal = importeTotal;
		this.lista = lista;
	}
	public DataProveedorCybe(){}
	
	
	@Override
	public int compareTo(Object o) 
	{
		if(this.getCantidad()>((DataProveedorCybe) o).getCantidad())
			return -1;
		if(this.getCantidad()<((DataProveedorCybe) o).getCantidad())
			return 1;
		else
			return 0;
	}

	
	
	

}
