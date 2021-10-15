package integraciones.erp.odoo.laIsla;

import java.util.HashMap;
import java.util.List;

public class DTO_Transferencia {
	private HashMap<Object, Object> idArticulo = new HashMap<Object, Object>();
	private HashMap<Object, List<Object>> objeto = new HashMap<Object, List<Object>>();
	public DTO_Transferencia(HashMap<Object, Object> idArticulo, HashMap<Object, List<Object>> objeto) {
		this.idArticulo = idArticulo;
		this.objeto = objeto;
	}
	public HashMap<Object, Object> getIdArticulo() {
		return idArticulo;
	}
	public HashMap<Object, List<Object>> getObjeto() {
		return objeto;
	}
	public void setIdArticulo(HashMap<Object, Object> idArticulo) {
		this.idArticulo = idArticulo;
	}
	public void setObjeto(HashMap<Object, List<Object>> objeto) {
		this.objeto = objeto;
	} 
	
	
}
