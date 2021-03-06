package beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nota {

	private Long idPedido;
	private int idNota;
	private int idUsuario;
	private String usuario;
	private String txtNota;
	private Fecha fechaNota;
	
	
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public int getIdNota() {
		return idNota;
	}
	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTxtNota() {
		return txtNota;
	}
	public void setTxtNota(String txtNota) {
		this.txtNota = txtNota;
	}
	public Fecha getFechaNota() {
		return fechaNota;
	}
	public void setFechaNota(Fecha fechaNota) {
		this.fechaNota = fechaNota;
	}
	public Nota(Long idPedido, int idNota, int idUsuario, String usuario,
			String txtNota, Fecha fechaNota) {
		this.idPedido = idPedido;
		this.idNota = idNota;
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.txtNota = txtNota;
		this.fechaNota = fechaNota;
	}
	
	
	
}
