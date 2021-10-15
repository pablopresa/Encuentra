package beans.encuentra;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import dataTypes.DataIDDescripcion;

public class DataChart 
{

	private String nombreChart,titulo,subtitulo;
	private List<DataChartColumna>valores;
	private List<String> columnas;
	
	
	
	
	public String getNombreChart() {
		return nombreChart;
	}
	public void setNombreChart(String nombreChart) {
		this.nombreChart = nombreChart;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	public List<DataChartColumna> getValores() {
		return valores;
	}
	public void setValores(List<DataChartColumna> valores) {
		this.valores = valores;
	}
	
	
	public List<String> getColumnas() {
		return columnas;
	}
	public void setColumnas(List<String> columnas) {
		this.columnas = columnas;
	}
	public DataChart() 
	{
		
	}
	
	public DataChart( List<DataChartColumna> Listasvalores,String nombre, String titulo, String subtitulo) 
	{
		this.nombreChart=nombre;
		this.titulo=titulo;
		this.subtitulo=subtitulo;
		List<String> columnas = new ArrayList<>();
		Hashtable<Integer, String> meses = new Hashtable<>();
		meses.put(1,"enero");
		meses.put(2,"febrero");
		meses.put(3,"marzo");
		meses.put(4,"abril");
		meses.put(5,"mayo");
		meses.put(6,"junio");
		meses.put(7,"julio");
		meses.put(8,"agosto");
		meses.put(9,"setiembre");
		meses.put(10,"octubre");
		meses.put(11,"noviembre");
		meses.put(12,"diciembre");
		
		boolean pri = true;
		List<DataChartColumna> valoresL = new ArrayList<>();
		
		for (DataChartColumna valores : Listasvalores) 
		{
			int total = 0;
			for (DataIDDescripcion v : valores.getValores()) 
			{
				total+=v.getId();
			}
			int pasada = 1;
			for (DataIDDescripcion v : valores.getValores()) 
			{
				int porcentaje = 0;
				if(total>0)
				{
					porcentaje =(v.getId()*100)/total;
				}
				
				v.setIdB(porcentaje);
				String[] mesAnio = v.getDescripcion().split("-");
				String mes = meses.get(Integer.parseInt(mesAnio[0]));
				v.setDescripcionB(mes);
				
				if(pri)
				{
					columnas.add(mes);
				}
				
				pasada++;
			}
			pri=false;
		}
		
		
		this.setValores(Listasvalores);
		this.setColumnas(columnas);
		
	}
	

}

