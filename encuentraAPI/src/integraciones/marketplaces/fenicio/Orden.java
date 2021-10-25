package integraciones.marketplaces.fenicio;

public class Orden {
	private Ordenes orden;
	private String msj;
	private String error;

	public String getMsj ()
	{
	return msj;
	}

	public void setMsj (String msj)
	{
	this.msj = msj;
	}

	public String getError ()
	{
	return error;
	}

	public void setError (String error)
	{
	this.error = error;
	}

	public Ordenes getOrden() {
		return orden;
	}

	public void setOrden(Ordenes orden) {
		this.orden = orden;
	}

}
