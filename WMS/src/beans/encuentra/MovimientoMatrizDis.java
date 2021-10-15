package beans.encuentra;

public class MovimientoMatrizDis implements Comparable  
{
	private String request;
	private String talle, articulo;
	private int origen, destino, cantidad, xOrigen, xDestino, yOrigen, yDestino;
	
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getTalle() {
		return talle;
	}
	public void setTalle(String talle) {
		this.talle = talle;
	}
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public MovimientoMatrizDis() 
	{
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public int getxOrigen() {
		return xOrigen;
	}
	public void setxOrigen(int xOrigen) {
		this.xOrigen = xOrigen;
	}
	public int getxDestino() {
		return xDestino;
	}
	public void setxDestino(int xDestino) {
		this.xDestino = xDestino;
	}
	public int getyOrigen() {
		return yOrigen;
	}
	public void setyOrigen(int yOrigen) {
		this.yOrigen = yOrigen;
	}
	public int getyDestino() {
		return yDestino;
	}
	public void setyDestino(int yDestino) {
		this.yDestino = yDestino;
	}
	
	@Override
	public int compareTo(Object o) 
	{
		MovimientoMatrizDis dl = (MovimientoMatrizDis)o;        
       	if(this.articulo.compareToIgnoreCase(dl.getArticulo()) == 0) 
		{ 
       		if(this.talle.compareToIgnoreCase(dl.getTalle()) == 0) 
    		{ 
           		return 0;
    		} 
    		else if(this.talle.compareToIgnoreCase(dl.getTalle()) > 0)
    		{ 
    			return 1; 
    		}
    		else
    		{
    			return -1;
    		}
		} 
		else if(this.articulo.compareToIgnoreCase(dl.getArticulo()) > 0)
		{ 
			return 1; 
		}
		else
		{
			return -1;
		}

	        	
	        		
	}
	
	
	
	

}






















