package dataTypes;

import java.util.Hashtable;
import java.util.List;



public class DataReglaReposicion implements Comparable
{
	private int idRegla,prioridad,minCentral,maxLocal;
	private String nombre;
	private Hashtable<Integer, Integer> destinos;
	private Hashtable<Integer, DataIDDescripcion> filtros;
	private List<DataIDDescDescripcion> filtrosValues;
	private List<DataIDDescDescripcion> depositosValues;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdRegla() {
		return idRegla;
	}
	public void setIdRegla(int idRegla) {
		this.idRegla = idRegla;
	}
	public int getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	public int getMinCentral() {
		return minCentral;
	}
	public void setMinCentral(int minCentral) {
		this.minCentral = minCentral;
	}
	public int getMaxLocal() {
		return maxLocal;
	}
	public void setMaxLocal(int maxLocal) {
		this.maxLocal = maxLocal;
	}
	public Hashtable<Integer, Integer> getDestinos() {
		return destinos;
	}
	public void setDestinos(Hashtable<Integer, Integer> destinos) {
		this.destinos = destinos;
	}
	public Hashtable<Integer, DataIDDescripcion> getFiltros() {
		return filtros;
	}
	public void setFiltros(Hashtable<Integer, DataIDDescripcion> filtros) {
		this.filtros = filtros;
	}
	public DataReglaReposicion() {
	}
	
	
	
	
	public List<DataIDDescDescripcion> getDepositosValues() {
		return depositosValues;
	}
	public void setDepositosValues(List<DataIDDescDescripcion> depositosValues) {
		this.depositosValues = depositosValues;
	}
	public List<DataIDDescDescripcion> getFiltrosValues() {
		return filtrosValues;
	}
	public void setFiltrosValues(List<DataIDDescDescripcion> filtrosValues) {
		this.filtrosValues = filtrosValues;
	}
	@Override
	public int compareTo(Object o) 
	{
		DataReglaReposicion rd = (DataReglaReposicion)o;        
		if(this.getPrioridad()==rd.getPrioridad())
	    {
			return 0;
	    }
		else if(this.getPrioridad()>rd.getPrioridad())
		{
			return 1;
		}
	    else
	    {
	       return -1;
	    }
	        		
	}
	
	

}
