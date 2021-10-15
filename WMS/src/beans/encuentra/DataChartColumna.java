package beans.encuentra;

import java.util.ArrayList;
import java.util.List;

import dataTypes.DataIDDescripcion;

public class DataChartColumna 
{
	private String titulo;
	private List<DataIDDescripcion> valores;
	
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<DataIDDescripcion> getValores() {
		return valores;
	}
	public void setValores(List<DataIDDescripcion> valores) {
		this.valores = valores;
	}
	
	public DataChartColumna(String titulo, List<DataIDDescripcion> valores) {
		this.titulo = titulo;
		this.valores = valores;
	}
	
	
	public DataChartColumna() 
	{
		this.valores=new ArrayList<>();
	}
	

}
