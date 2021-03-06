package dataTypes;

import java.util.ArrayList;
import java.util.List;

public class DataArticuloCantBarra 
{
	private String articulo,barra,idOC, color,folio;
	private int cantidadContada, cantidadOrden,cantBase, cantBaseCol, cantFacturada, cantAFacturar, idLineaOC;
	List<DataDescDescripcion> posiblesArt = new ArrayList<>();
	
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getBarra() {
		return barra;
	}
	public void setBarra(String barra) {
		this.barra = barra;
	}
	public int getCantidadContada() {
		return cantidadContada;
	}
	public void setCantidadContada(int cantidadContada) {
		this.cantidadContada = cantidadContada;
	}
	public int getCantidadOrden() {
		return cantidadOrden;
	}
	public void setCantidadOrden(int cantidadOrden) {
		this.cantidadOrden = cantidadOrden;
	}
	public DataArticuloCantBarra(String bar, int cantC) 
	{
		this.articulo = "??????";
		this.barra = bar;
		this.cantidadContada = cantC;
		this.cantidadOrden = 0;
	}
	public List<DataDescDescripcion> getPosiblesArt() {
		return posiblesArt;
	}
	public void setPosiblesArt(List<DataDescDescripcion> posiblesArt) {
		this.posiblesArt = posiblesArt;
	}
	public int getCantBase() {
		return cantBase;
	}
	public void setCantBase(int cantBase) {
		this.cantBase = cantBase;
	}
	public int getCantBaseCol() {
		return cantBaseCol;
	}
	public void setCantBaseCol(int cantBaseCol) {
		this.cantBaseCol = cantBaseCol;
	}
	public int getCantFacturada() {
		return cantFacturada;
	}
	public void setCantFacturada(int cantFacturada) {
		this.cantFacturada = cantFacturada;
	}
	public DataArticuloCantBarra() {
	}
	public int getCantAFacturar() {
		return cantAFacturar;
	}
	public void setCantAFacturar(int cantAFacturar) {
		this.cantAFacturar = cantAFacturar;
	}
	public String getIdOC() {
		return idOC;
	}
	public void setIdOC(String idOC) {
		this.idOC = idOC;
	}
	public int getIdLineaOC() {
		return idLineaOC;
	}
	public void setIdLineaOC(int idLineaOC) {
		this.idLineaOC = idLineaOC;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	
	
	
	
	
}
