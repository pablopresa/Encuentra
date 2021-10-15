package dataTypes;

public class DataLineaEnvio 
{
	private int cantidad;
	private DataIDDescripcion transporte;
	private DataIDDescripcion razon;
	
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public DataIDDescripcion getTransporte() {
		return transporte;
	}
	public void setTransporte(DataIDDescripcion transporte) {
		this.transporte = transporte;
	}
	public DataIDDescripcion getRazon() {
		return razon;
	}
	public void setRazon(DataIDDescripcion razon) {
		this.razon = razon;
	}
	
	
	public DataLineaEnvio() 
	{
		
	}
	
	
	
	
	

}
