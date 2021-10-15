package jsonObjects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class DISTRIenvio
{
	private DISTRIlogin WSAutorizacion;
	private DISTRIservicio Servicio;


	 // Getter Methods 

	 public DISTRIlogin getWSAutorizacion() {
	  return WSAutorizacion;
	 }

	 public DISTRIservicio getServicio() {
	  return Servicio;
	 }

	 // Setter Methods 

	 public void setWSAutorizacion(	DISTRIlogin WSAutorizacionObject) {
	  this.WSAutorizacion = WSAutorizacionObject;
	 }

	 public void setServicio(DISTRIservicio ServicioObject) {
	  this.Servicio = ServicioObject;
	 }
	 
	 public DISTRIenvio(){
		 
	 }
}