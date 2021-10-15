package beans.encuentra;

import java.util.List;

public class DataArticuloConsulta implements Comparable
{
	private List<String> lineaTalles, lineaTotales;
	private List<List<String>> lineas;
	private String baseColor, descripcion;
	private String precio;
	private int posicion;
	
	
	public List<String> getLineaTalles() {
		return lineaTalles;
	}
	public void setLineaTalles(List<String> lineaTalles) {
		this.lineaTalles = lineaTalles;
	}
	public List<String> getLineaTotales() {
		return lineaTotales;
	}
	public void setLineaTotales(List<String> lineaTotales) {
		this.lineaTotales = lineaTotales;
	}
	public List<List<String>> getLineas() {
		return lineas;
	}
	public void setLineas(List<List<String>> lineas) {
		this.lineas = lineas;
	}
	public String getBaseColor() {
		return baseColor;
	}
	public void setBaseColor(String baseColor) {
		this.baseColor = baseColor;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	@Override
	public int compareTo(Object o) 
	{
		DataArticuloConsulta dl = (DataArticuloConsulta)o;        
       	if(this.baseColor.compareToIgnoreCase(dl.getBaseColor()) == 0) 
		{ 
       		if(this.baseColor.compareToIgnoreCase(dl.getBaseColor()) == 0) 
    		{ 
           		return 0;
    		} 
    		else if(this.baseColor.compareToIgnoreCase(dl.getBaseColor()) > 0)
    		{ 
    			return 1; 
    		}
    		else
    		{
    			return -1;
    		}
		} 
		else if(this.baseColor.compareToIgnoreCase(dl.getBaseColor()) > 0)
		{ 
			return 1; 
		}
		else
		{
			return -1;
		}

	        	
	        		
	}
}
