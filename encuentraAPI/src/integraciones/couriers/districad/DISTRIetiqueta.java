package integraciones.couriers.districad;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class DISTRIetiqueta
{
	private DISTRIlogin WSAutorizacion;
	private String SrvTrkNbr;


	 // Getter Methods 

	 public DISTRIlogin getWSAutorizacion() {
	  return WSAutorizacion;
	 }

	 

	 // Setter Methods 

	 public void setWSAutorizacion(	DISTRIlogin WSAutorizacionObject) {
	  this.WSAutorizacion = WSAutorizacionObject;
	 }

	 
	 
	 public DISTRIetiqueta(){
		 
	 }



	public String getSrvTrkNbr() {
		return SrvTrkNbr;
	}



	public void setSrvTrkNbr(String srvTrkNbr) {
		SrvTrkNbr = srvTrkNbr;
	}
}