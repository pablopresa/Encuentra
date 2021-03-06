package beans;

public class bultoContenido implements Comparable, Cloneable{
	private String idArticulo;
	private int cantidad;
	private int cantidadReservada;
	private int picking;
	private int recepcion;
	private String fecha;
	private int usuario;
	
	
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getPicking() {
		return picking;
	}
	public void setPicking(int picking) {
		this.picking = picking;
	}
	public int getRecepcion() {
		return recepcion;
	}
	public void setRecepcion(int recepcion) {
		this.recepcion = recepcion;
	}
	
	public bultoContenido(){
		
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	
	public int getCantidadReservada() {
		return cantidadReservada;
	}
	public void setCantidadReservada(int cantidadReservada) {
		this.cantidadReservada = cantidadReservada;
	}
	public bultoContenido(String idArticulo, int cantidad, int recepcion, int usuario) {
		
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
		this.recepcion = recepcion;
		this.usuario = usuario;
	}
	
	public bultoContenido(String idArticulo, int cantidad) {
		
		this.idArticulo = idArticulo;
		this.cantidad = cantidad;
	}
	
	@Override
	public bultoContenido clone() throws CloneNotSupportedException
	 { 
		return (bultoContenido) super.clone(); 
	 }
	
	@Override
	public int compareTo(Object o) 
	{
		bultoContenido bc = (bultoContenido)o;        

		  if(this.idArticulo.compareToIgnoreCase(bc.getIdArticulo()) == 0) 
          { 
              return 0;
          } 
          else if(this.idArticulo.compareToIgnoreCase(bc.getIdArticulo()) < 0)
          { 
          	
              return 1; 
          }
          else
          {
          	return -1;
          }
	}
	
	
	
	
}
