package jsonObjects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PrintObject {

	private String id;
	private String url;
	private String porait;
	private String printerId;
	private String idEquipo;
	private int vias;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPrinterId() {
		return printerId;
	}
	public void setPrinterId(String printerId) {
		this.printerId = printerId;
	}
	public String getPorait() {
		return porait;
	}
	public void setPorait(String porait) {
		this.porait = porait;
	}
	public String getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	public int getVias() {
		return vias;
	}
	public void setVias(int vias) {
		this.vias = vias;
	}
	
	
	
}
