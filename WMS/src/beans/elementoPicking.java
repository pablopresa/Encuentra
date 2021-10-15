package beans;


public class elementoPicking{

	private String idArticulo;
	private int cantidad;
	private int pick;
	private String doc;
	private int picking;
	private int recepcion;
	private String idBulto;
	private int reserva;
	
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String articulo) {
		this.idArticulo = articulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getPick() {
		return pick;
	}
	public void setPick(int pick) {
		this.pick = pick;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
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
	public elementoPicking(int cant, String idart, int pick) {
		this.idArticulo = idart;
		this.cantidad = cant;
		this.pick = pick;
	}
	
	public elementoPicking(int cant, String idart, int pick, int picking, int rec, String enBulto) {
		this.idArticulo = idart;
		this.cantidad = cant;
		this.pick = pick;
		this.picking = picking;
		this.recepcion = rec;
		this.idBulto = enBulto;
	}
	public String getIdBulto() {
		return idBulto;
	}
	public void setIdBulto(String idBulto) {
		this.idBulto = idBulto;
	}
	public int getReserva() {
		return reserva;
	}
	public void setReserva(int reserva) {
		this.reserva = reserva;
	}
	
}
