package web.informes;

import java.util.ArrayList;
import java.util.List;

public class NodoArgs {
	private String clave;
	private List<Object> parametros = new ArrayList<>();
	private String consultaBD;

	public NodoArgs(String clave, List<Object> parametros) {
		this.clave = clave;
		this.parametros = parametros;
	}

	public NodoArgs(String clave, List<Object> parametros, String consultaBD) {
		this.clave = clave;
		this.parametros = parametros;
		this.consultaBD = consultaBD;
	}

	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public List<Object> getParametros() {
		return parametros;
	}
	public void setParametros(List<Object> parametros) {
		this.parametros = parametros;
	}

	public String getConsultaBD() {
		return consultaBD;
	}

	public void setConsultaBD(String consultaBD) {
		this.consultaBD = consultaBD;
	}


}
