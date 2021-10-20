package integraciones.erp.ascher;

import java.util.List;
import java.util.Map;

import beans.encuentra.ArticuloReposicion;
import beans.encuentra.DepositoMayorista;
import integraciones.erp.visualStore.objetos.OrdenVenta;


public class DataDeposOrdenes 
{
	private List<ArticuloReposicion> articulos;
	private List<DepositoMayorista> depositos;
	private List<SDTPedidoEncuentra> cabezales;
	
	Map<Integer, List<SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon>> renglones;
	Map<Integer, OrdenVenta> ordenes;
	
	
	public List<ArticuloReposicion> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<ArticuloReposicion> articulos) {
		this.articulos = articulos;
	}
	public List<DepositoMayorista> getDepositos() {
		return depositos;
	}
	public void setDepositos(List<DepositoMayorista> depositos) {
		this.depositos = depositos;
	}
	public DataDeposOrdenes(List<ArticuloReposicion> articulos, List<DepositoMayorista> depositos) {
		
		this.articulos = articulos;
		this.depositos = depositos;
	}
	
	
	public DataDeposOrdenes() {}
	public List<SDTPedidoEncuentra> getCabezales() {
		return cabezales;
	}
	public void setCabezales(List<SDTPedidoEncuentra> cabezales) {
		this.cabezales = cabezales;
	}
	public Map<Integer, List<SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon>> getRenglones() 
	{
		return renglones;
	}
	public void setRenglones(Map<Integer, List<SDTPedidoEncuentraRenglonesSDTPedidoEncuentraRenglon>> renglones) {
		this.renglones = renglones;
	}
	public Map<Integer, OrdenVenta> getOrdenes() {
		return ordenes;
	}
	public void setOrdenes(Map<Integer, OrdenVenta> ordenes) {
		this.ordenes = ordenes;
	}
}