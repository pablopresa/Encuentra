package dataTypes;

public class DataDepositoSAP 
{
	private String idDepo;
	int idEncuentra;
	private String nombre;
	private String direccion;
	private String padre;
	
	public String getIdDepo() {
		return idDepo;
	}
	public void setIdDepo(String idDepo) {
		this.idDepo = idDepo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public DataDepositoSAP() {
	}
	public int getIdEncuentra() {
		return idEncuentra;
	}
	public void setIdEncuentra(int idEncuentra) {
		this.idEncuentra = idEncuentra;
	}
	public String getPadre() {
		return padre;
	}
	public void setPadre(String padre) {
		this.padre = padre;
	}
	
	
	
	
	
}
