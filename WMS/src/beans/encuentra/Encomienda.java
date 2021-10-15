package beans.encuentra;

public class Encomienda 
{
	
	private int idEnvio;
	private String fecha;
	private String courier;
	private String destino;
	private int bultos;
	private int remito;
	private String obs;
	
	
	
	public int getRemito() {
		return remito;
	}



	public void setRemito(int remito) {
		this.remito = remito;
	}



	public String getObs() {
		return obs;
	}



	public void setObs(String obs) {
		this.obs = obs;
	}



	public int getIdEnvio() {
		return idEnvio;
	}



	public void setIdEnvio(int idEnvio) {
		this.idEnvio = idEnvio;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public String getCourier() {
		return courier;
	}



	public void setCourier(String courier) {
		this.courier = courier;
	}



	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}



	public int getBultos() {
		return bultos;
	}



	public void setBultos(int bultos) {
		this.bultos = bultos;
	}


	

	public Encomienda(int idEnvio, String fecha, String courier, String destino, int bultos) {
		this.idEnvio = idEnvio;
		this.fecha = fecha;
		this.courier = courier;
		this.destino = destino;
		this.bultos = bultos;
	}



	public Encomienda() {
		
		
	}
	
	

}
