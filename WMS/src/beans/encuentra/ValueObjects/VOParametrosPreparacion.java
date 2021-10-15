
package beans.encuentra.ValueObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VOParametrosPreparacion {

@SerializedName("numero")
@Expose
private String numero;
@SerializedName("nombre")
@Expose
private String nombre;
@SerializedName("prioridad")
@Expose
private String prioridad;
@SerializedName("preparaPickup")
@Expose
private String preparaPickup;
@SerializedName("preparaDelivery")
@Expose
private String preparaDelivery;
@SerializedName("preparaEnvioCD")
@Expose
private String preparaEnvioCD;
@SerializedName("grupo")
@Expose
private String grupo;

/**
* No args constructor for use in serialization
*
*/
public VOParametrosPreparacion() {
}

/**
*
* @param preparaDelivery
* @param numero
* @param grupo
* @param preparaEnvioCD
* @param preparaPickup
* @param nombre
* @param prioridad
*/
public VOParametrosPreparacion(String numero, String nombre, String prioridad, String preparaPickup, String preparaDelivery, String preparaEnvioCD, String grupo) {
super();
this.numero = numero;
this.nombre = nombre;
this.prioridad = prioridad;
this.preparaPickup = preparaPickup;
this.preparaDelivery = preparaDelivery;
this.preparaEnvioCD = preparaEnvioCD;
this.grupo = grupo;
}

public String getNumero() {
return numero;
}

public void setNumero(String numero) {
this.numero = numero;
}

public String getNombre() {
return nombre;
}

public void setNombre(String nombre) {
this.nombre = nombre;
}

public String getPrioridad() {
return prioridad;
}

public void setPrioridad(String prioridad) {
this.prioridad = prioridad;
}

public String getPreparaPickup() {
return preparaPickup;
}

public void setPreparaPickup(String preparaPickup) {
this.preparaPickup = preparaPickup;
}

public String getPreparaDelivery() {
return preparaDelivery;
}

public void setPreparaDelivery(String preparaDelivery) {
this.preparaDelivery = preparaDelivery;
}

public String getPreparaEnvioCD() {
return preparaEnvioCD;
}

public void setPreparaEnvioCD(String preparaEnvioCD) {
this.preparaEnvioCD = preparaEnvioCD;
}

public String getGrupo() {
return grupo;
}

public void setGrupo(String grupo) {
this.grupo = grupo;
}

@Override
public String toString() {
	return "VOParametrosPreparacion [numero=" + numero + ", nombre=" + nombre + ", prioridad=" + prioridad
			+ ", preparaPickup=" + preparaPickup + ", preparaDelivery=" + preparaDelivery + ", preparaEnvioCD="
			+ preparaEnvioCD + ", grupo=" + grupo + "]";
}

}