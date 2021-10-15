package beans.api.json;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SendMail {

	private String id;
	private String destino;
	private String asunto;
	private List<String> body;
	private String origen;
	private int idEmpresa;
	private String adjunto;
	
	
	
	
	
	public String getDestino() {
		return destino;
	}



	public void setDestino(String destino) {
		this.destino = destino;
	}



	public String getAsunto() {
		return asunto;
	}



	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}



	public List<String> getBody() {
		return body;
	}



	public void setBody(List<String> bodyName) {
		this.body = bodyName;
	}

	public SendMail(String id,String destino, String asunto, String bodyName, String origen, String adjunto) {
		super();
		this.id = id;
		this.destino = destino;
		this.asunto = asunto;
		this.origen = origen;
		this.body = new ArrayList<>(); 
		
		String aux = "";
		String resto = bodyName;
		while(resto.length()>5000){
			aux = resto.substring(0,5000);
			resto = resto.substring(5001,resto.length());
			this.body.add(aux);
		}
		
		this.body.add(resto);
		
		this.adjunto = adjunto;
		
		if(adjunto == null) {
			this.adjunto = "";
		}
		
		
	}



	public SendMail(){
		
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getOrigen() {
		return origen;
	}



	public void setOrigen(String origen) {
		this.origen = origen;
	}



	public int getIdEmpresa() {
		return idEmpresa;
	}



	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}



	public String getAdjunto() {
		return adjunto;
	}



	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}
}
