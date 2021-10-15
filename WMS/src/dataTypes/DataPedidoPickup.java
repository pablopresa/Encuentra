package dataTypes;

import java.util.List;

public class DataPedidoPickup 
{
	private String idPedido,cliente, fecha, ubicacion;
	private int cantTotal, cantPickeada;
	private List<DataArtPedidoPickup>articulos;
	
	
	public String getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public List<DataArtPedidoPickup> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<DataArtPedidoPickup> articulos) {
		this.articulos = articulos;
	}
	public DataPedidoPickup(String idPedido, String cliente, String fecha, List<DataArtPedidoPickup> articulos, int cantTotal, int cantPickeada) {
		this.idPedido = idPedido;
		this.cliente = cliente;
		this.fecha = fecha;
		this.articulos = articulos;
		this.cantTotal = cantTotal;
		this.cantPickeada = cantPickeada;
	}
	public int getCantTotal() {
		return cantTotal;
	}
	public void setCantTotal(int cantTotal) {
		this.cantTotal = cantTotal;
	}
	public int getCantPickeada() {
		return cantPickeada;
	}
	public void setCantPickeada(int cantPickeada) {
		this.cantPickeada = cantPickeada;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
	
	
	

}
