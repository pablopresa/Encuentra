package dataTypes;

public class DataEcommerce_LogPedido 
{
	private int level;
	private String fecha, mensaje;
	private Long idpedido;
	private String usuario;
	
	public Long getIdpedido() {
		return idpedido;
	}
	public void setIdpedido(Long idpedido) {
		this.idpedido = idpedido;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public DataEcommerce_LogPedido(Long idpedido, int level, String fecha,String mensaje) 
	{
		this.idpedido = idpedido;
		this.level = level;
		this.fecha = fecha;
		this.mensaje = mensaje;
	}
	
	public DataEcommerce_LogPedido(Long idpedido, int level, String fecha,String mensaje, String usuario) 
	{
		this.idpedido = idpedido;
		this.level = level;
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.setUsuario(usuario);
	}
	
	public DataEcommerce_LogPedido() 
	{
			
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	

	
	
	
	
}
