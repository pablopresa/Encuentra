package beans;

public class DepositoParametros {
	
	private int idDeposito;
	private int idParametro;
	private String descripcion;
	private boolean habilitado;
	
	public int getIdDeposito() {
		return idDeposito;
	}
	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}
	public int getIdParametro() {
		return idParametro;
	}
	public void setIdParametro(int idParametro) {
		this.idParametro = idParametro;
	}
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
