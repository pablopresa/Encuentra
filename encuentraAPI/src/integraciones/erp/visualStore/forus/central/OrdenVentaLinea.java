package integraciones.erp.visualStore.forus.central;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrdenVentaLinea")
public class OrdenVentaLinea 
{
	private String idArticulo; 
	private int cantidad; 
	private double precio,precioImp,precioLista;
	
	
	
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
	public OrdenVentaLinea() {
	}
	public double getPrecioLista() {
		return precioLista;
	}
	public void setPrecioLista(double precioLista) {
		this.precioLista = precioLista;
	} 

	
	
	
	
	
	
	

}
