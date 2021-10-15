package beans.api;

import java.util.Hashtable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ColeccionHasIntString {
	private Hashtable<Integer, String> coleccion;

	public Hashtable<Integer, String> getColeccion() {
		return coleccion;
	}

	public void setColeccion(Hashtable<Integer, String> coleccion) {
		this.coleccion = coleccion;
	}
	
	public ColeccionHasIntString() {
		
	}
}
