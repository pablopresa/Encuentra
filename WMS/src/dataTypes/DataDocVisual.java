package dataTypes;

public class DataDocVisual 
{
	private int idInterno, 
				idVisual;
	private String estado, 
				usuario;
	private DataIDDescripcion depoOrigen, 
							depoDestino;
	
	
	
	
	
	public int getIdInterno() {
		return idInterno;
	}
	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}
	public int getIdVisual() {
		return idVisual;
	}
	public void setIdVisual(int idVisual) {
		this.idVisual = idVisual;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public DataIDDescripcion getDepoOrigen() {
		return depoOrigen;
	}
	public void setDepoOrigen(DataIDDescripcion depoOrigen) {
		this.depoOrigen = depoOrigen;
	}
	public DataIDDescripcion getDepoDestino() {
		return depoDestino;
	}
	public void setDepoDestino(DataIDDescripcion depoDestino) {
		this.depoDestino = depoDestino;
	}
	public DataDocVisual() {
	}
	
	
	
	
	
	
	
}
