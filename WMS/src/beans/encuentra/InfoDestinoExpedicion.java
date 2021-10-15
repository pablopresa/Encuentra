package beans.encuentra;

public class InfoDestinoExpedicion 
{
	private int cantidad;
	private int local;
	private int tiempo;
	
	
	
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getLocal() {
		return local;
	}
	public void setLocal(int local) {
		this.local = local;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public InfoDestinoExpedicion(int loc, int cant, int t) {
		
		this.cantidad=cant;
		this.local=loc;
		this.tiempo=t;
		
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
