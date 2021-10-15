package beans;

import logica.Logica;

public class RemitoRegistro {

	private int hilo;
	private String usuario;
	private int idEquipo;
	private String remito;
	private String etiqueta;
	private int idDeposito;
	private int idEmpresa;
	
	public int getHilo() {
		return hilo;
	}


	public void setHilo(int hilo) {
		this.hilo = hilo;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public int getIdEquipo() {
		return idEquipo;
	}


	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}


	public String getRemito() {
		return remito;
	}


	public void setRemito(String remito) {
		this.remito = remito;
	}


	public String getEtiqueta() {
		return etiqueta;
	}


	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}


	public int getIdEmpresa() {
		return idEmpresa;
	}


	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	public int getIdDeposito() {
		return idDeposito;
	}


	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}

	public RemitoRegistro(int hilo, String usuario, int idImpresora, String remito, String etiqueta, int idDeposito, int idEmpresa) {
		super();
		this.hilo = hilo;
		this.usuario = usuario;
		this.idEquipo = idImpresora;
		this.remito = remito;
		this.etiqueta = etiqueta;
		this.idDeposito = idDeposito;
		this.idEmpresa = idEmpresa;
	}


	public void registrarRemitos(RemitoRegistro remito)
	{
		Logica lo = new Logica();
		
		lo.encuentraRegistrarRemitos(remito);
		
	}



	

}
