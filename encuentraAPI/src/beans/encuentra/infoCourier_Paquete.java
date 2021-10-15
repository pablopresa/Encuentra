package beans.encuentra;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class infoCourier_Paquete {

	private String tamanio;
	private String peso;
	
	
	
	
	public String getTamanio() {
		return tamanio;
	}




	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}




	public String getPeso() {
		return peso;
	}




	public void setPeso(String peso) {
		this.peso = peso;
	}




	public infoCourier_Paquete(){
		
	}
}
