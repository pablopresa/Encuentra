package beans;

public class RepoDimension implements Comparable
{
	private int cantidadVendida;
	private int idDeposito;
	private Double porcentajeDelTotal;
	
	
	
	public int getCantidadVendida() {
		return cantidadVendida;
	}



	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}



	public int getIdDeposito() {
		return idDeposito;
	}



	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}



	public Double getPorcentajeDelTotal() {
		return porcentajeDelTotal;
	}



	public void setPorcentajeDelTotal(Double porcentajeDelTotal) {
		this.porcentajeDelTotal = porcentajeDelTotal;
	}



	@Override
	public int compareTo(Object o) 
	{
		RepoDimension rd = (RepoDimension)o;        
		if(this.getCantidadVendida()==rd.getCantidadVendida())
	    {
			return 0;
	    }
		else if(this.getCantidadVendida()>rd.getCantidadVendida())
		{
			return -1;
		}
	    else
	    {
	       return 1;
	    }
	        		
	}
	

}


















