package integraciones.erp.visualStore.objetos;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrdenVentaLinea")
public class OrdenVentaLinea 
{
	private String idArticulo;
	private String descripcion; 
	private int cantidad;
	private int esCupon;
	private int linea;
	private int cantidadRegalo; 
	private double precio;
	private double precioImp;
	private double precioLista;
	private double descuento;
	
	
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getLinea() {
		return linea;
	}
	public void setLinea(int linea) {
		this.linea = linea;
	}
	@Override
	public String toString() {
		return "OrdenVentaLinea [idArticulo=" + idArticulo + ", cantidad="
				+ cantidad + ", precio=" + precio + ", precioImp=" + precioImp
				+ ", precioLista=" + precioLista + "]";
	}
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getPrecioImp() {
		return precioImp;
	}
	public void setPrecioImp(double precioImp) {
		this.precioImp = precioImp;
	}
	public OrdenVentaLinea(Double importe,String cant,String sku) 
	{
		//importe = importe/Integer.parseInt(cant);
		this.idArticulo = sku.replace(":", "");
		this.cantidad = Integer.parseInt(cant);
		this.precio = importe/1.22;
		this.precioImp = importe;
	}
	public OrdenVentaLinea(Double importe,String cant,String sku, int regalo, int linea, double descuento, String descripcion) 
	{
		//importe = importe/Integer.parseInt(cant);
		this.idArticulo = sku.replace(":", "");
		this.cantidad = Integer.parseInt(cant);
		this.precio = importe/1.22;
		this.precioImp = importe;
		this.cantidadRegalo = regalo;
		this.linea = linea;
		this.descuento = descuento;
		this.descripcion = descripcion;
	}
	public OrdenVentaLinea() {
	}
	public double getPrecioLista() {
		return precioLista;
	}
	public void setPrecioLista(double precioLista) {
		this.precioLista = precioLista;
	}
	public int getEsCupon() {
		return esCupon;
	}
	public void setEsCupon(int esCupon) {
		this.esCupon = esCupon;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public int getCantidadRegalo() {
		return cantidadRegalo;
	}
	public void setCantidadRegalo(int cantidadRegalo) {
		this.cantidadRegalo = cantidadRegalo;
	} 

	public double getPrecioNeto() {
		return this.getPrecioImp() * this.getCantidad() - this.getDescuento() * this.getCantidad();
	}
	
	
	
	
	

}
