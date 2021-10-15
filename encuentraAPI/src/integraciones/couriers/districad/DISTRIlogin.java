package integraciones.couriers.districad;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class DISTRIlogin
{
	 private String Guid;
	 private String Usuario;
	 private String Password;


	 // Getter Methods 

	 public String getGuid() {
	  return Guid;
	 }

	 public String getUsuario() {
	  return Usuario;
	 }

	 public String getPassword() {
	  return Password;
	 }

	 // Setter Methods 

	 public void setGuid(String Guid) {
	  this.Guid = Guid;
	 }

	 public void setUsuario(String Usuario) {
	  this.Usuario = Usuario;
	 }

	 public void setPassword(String Password) {
	  this.Password = Password;
	 }
	 
	 public DISTRIlogin(){
		 
	 }
}