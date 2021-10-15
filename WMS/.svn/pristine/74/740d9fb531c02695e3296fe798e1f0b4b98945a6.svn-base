package beans;

import java.util.Hashtable;

import dataTypes.DataIDIDDescripcion;

public class StockReposicion implements Comparable
{
	private String Articulo;
	private Hashtable <Integer, DataIDIDDescripcion> depositos;
	
	
	
	
	
	public String getArticulo() {
		return Articulo;
	}
	public void setArticulo(String articulo) {
		Articulo = articulo;
	}
	public Hashtable<Integer, DataIDIDDescripcion> getDepositos() {
		return depositos;
	}
	public void setDepositos(Hashtable<Integer, DataIDIDDescripcion> depositos) {
		this.depositos = depositos;
	}
	public StockReposicion(String articulo,
			Hashtable<Integer, DataIDIDDescripcion> depositos) {
		Articulo = articulo;
		this.depositos = depositos;
	}
	public StockReposicion() {
	}
	
	
	@Override
	public int compareTo(Object o) 
	{
		StockReposicion dl = (StockReposicion)o;        

	        
	     if(this.Articulo.compareToIgnoreCase(dl.getArticulo()) == 0) 
		 { 
	    	 return 0;
		 } 
		 else if(this.Articulo.compareToIgnoreCase(dl.getArticulo())> 0)
		 { 
			 return 1; 
		 }
		 else
		 {
			 return -1;
		 }

	        	        		
	}
	
	
	
	

}
