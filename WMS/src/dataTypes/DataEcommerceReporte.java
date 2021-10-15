package dataTypes;

public class DataEcommerceReporte implements Comparable
{
	private String idPedido;
	private String idArt;
	private int dep;
	private String fecha;
	private String fechaR;
	private String fechaC;
	private int cantidad;
	private int orden;
	private int factura;
	private int docEnvio;
	
	private int visualCant;
	private String visualArt;
	private String visualRemitos;
	private String visualFecha;
	
	
	
	
	
	public String getVisualFecha() {
		return visualFecha;
	}





	public void setVisualFecha(String visualFecha) {
		this.visualFecha = visualFecha;
	}





	public int getDocEnvio() {
		return docEnvio;
	}





	public void setDocEnvio(int docEnvio) {
		this.docEnvio = docEnvio;
	}





	public int getFactura() {
		return factura;
	}





	public void setFactura(int factura) {
		this.factura = factura;
	}





	public int getOrden() {
		return orden;
	}





	public void setOrden(int orden) {
		this.orden = orden;
	}





	public int getVisualCant() {
		return visualCant;
	}





	public void setVisualCant(int visualCant) {
		this.visualCant = visualCant;
	}





	public String getVisualArt() {
		return visualArt;
	}





	public void setVisualArt(String visualArt) {
		this.visualArt = visualArt;
	}





	public String getVisualRemitos() {
		return visualRemitos;
	}





	public void setVisualRemitos(String visualRemitos) {
		this.visualRemitos = visualRemitos;
	}





	public int getCantidad() {
		return cantidad;
	}





	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}





	public String getFecha() {
		return fecha;
	}





	public void setFecha(String fecha) {
		this.fecha = fecha;
	}





	public String getIdPedido() {
		return idPedido;
	}





	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}





	





	public String getIdArt() {
		return idArt;
	}





	public void setIdArt(String idArt) {
		this.idArt = idArt;
	}





	public int getDep() {
		return dep;
	}





	public void setDep(int dep) {
		this.dep = dep;
	}





	public String getFechaR() {
		return fechaR;
	}





	public void setFechaR(String fechaR) {
		this.fechaR = fechaR;
	}





	public String getFechaC() {
		return fechaC;
	}





	public void setFechaC(String fechaC) {
		this.fechaC = fechaC;
	}



	public DataEcommerceReporte(String ped, String art,int dep) {
		this.idPedido=ped;
		this.idArt=art;
		this.dep=dep;
	}



	@Override
	public int compareTo(Object o) 
	{
		
	     return 0;   		
	}

	public DataEcommerceReporte Clonar(){
		
		DataEcommerceReporte clon=new DataEcommerceReporte(this.idPedido, this.idArt, this.dep);
		
		clon.cantidad=this.cantidad;
		clon.fecha=this.fecha;
		clon.fechaR=this.fechaR;
		clon.fechaC=this.fechaC;
		
		clon.orden=this.orden;
		clon.factura=this.factura;
		clon.docEnvio=this.docEnvio;
		
		clon.visualCant=this.visualCant;
		clon.visualArt=this.visualArt;
		clon.visualRemitos=this.visualRemitos;
				
		return clon;
	}



	
	
	
	
	

}
