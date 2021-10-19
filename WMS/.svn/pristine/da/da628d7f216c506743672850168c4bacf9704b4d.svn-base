package beans.encuentra;

public class DataListVenta implements Comparable{
	
	private int cantidades;
	private int deposito;
	private int prioridades;
	
	public int getPrioridades() {
		return prioridades;
	}
	public void setPrioridades(int prioridades) {
		this.prioridades = prioridades;
	}
	public int getCantidades() {
		return cantidades;
	}
	public void setCantidades(int cantidades) {
		this.cantidades = cantidades;
	}
	public int getDeposito() {
		return deposito;
	}
	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}
	@Override
	public int compareTo(Object o) {
	  int cantidadesVendidas=((DataListVenta)o).getCantidades()-
		cantidades;
	  if(cantidadesVendidas==0)
		  return prioridades-((DataListVenta)o).getPrioridades();
	  else
		  return cantidadesVendidas;
	}
	

}
