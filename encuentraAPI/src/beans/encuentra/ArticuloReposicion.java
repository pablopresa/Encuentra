package beans.encuentra;

import java.math.BigInteger;
import java.util.List;

public class ArticuloReposicion 
{
	private String articulo, articuloBaseColor, articuloBase, depoCliente, nombreRegla,descripcionVenta;
	private int marca,clase,venta,stockCentral,stockLocal, stockBaseColor,precio, sucursal, origen, destino, idpicking, idLineaSAP, genero, categoria, minCentral, maxLocal,temporada,solicitud,tipo;
	private boolean prioridad;
	private Long seccion;
	
	
	public String getDescripcionVenta() {
		return descripcionVenta;
	}
	public void setDescripcionVenta(String descripcionVenta) {
		this.descripcionVenta = descripcionVenta;
	}
	public boolean isPrioridad() {
		return prioridad;
	}
	public void setPrioridad(boolean prioridad) {
		this.prioridad = prioridad;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getArticuloBaseColor() {
		return articuloBaseColor;
	}
	public void setArticuloBaseColor(String articuloBaseColor) {
		this.articuloBaseColor = articuloBaseColor;
	}
	public String getArticuloBase() {
		return articuloBase;
	}
	public void setArticuloBase(String articuloBase) {
		this.articuloBase = articuloBase;
	}
	public int getMarca() {
		return marca;
	}
	public void setMarca(int marca) {
		this.marca = marca;
	}
	public Long getSeccion() {
		return seccion;
	}
	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}
	public int getClase() {
		return clase;
	}
	public void setClase(int clase) {
		this.clase = clase;
	}
	public int getVenta() {
		return venta;
	}
	public void setVenta(int venta) {
		this.venta = venta;
	}
	public int getStockCentral() {
		return stockCentral;
	}
	public void setStockCentral(int stockCentral) {
		this.stockCentral = stockCentral;
	}
	public int getStockLocal() {
		return stockLocal;
	}
	public void setStockLocal(int stockLocal) {
		this.stockLocal = stockLocal;
	}
	public int getStockBaseColor() {
		return stockBaseColor;
	}
	public void setStockBaseColor(int stockBaseColor) {
		this.stockBaseColor = stockBaseColor;
	}
	
	
	public String getDepoCliente() {
		return depoCliente;
	}
	public void setDepoCliente(String depoCliente) {
		this.depoCliente = depoCliente;
	}
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	public int getSucursal() {
		return sucursal;
	}
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public ArticuloReposicion(String articulo, String articuloBaseColor, String articuloBase, int marca, Long seccion, int clase, int venta,	
			int stockCentral, int stockLocal, int stockBaseColor, int precio, int sucursal, int genero, int categoria, int temporada, int tipo, String descripcionVenta) 
	{
		this.articulo = articulo;
		this.articuloBaseColor = articuloBaseColor;
		this.articuloBase = articuloBase;
		this.marca = marca;
		this.seccion = seccion;
		this.clase = clase;
		this.venta = venta;
		this.stockCentral = stockCentral;
		this.stockLocal = stockLocal;
		this.stockBaseColor = stockBaseColor;
		this.precio = precio;
		this.sucursal = sucursal;
		this.idLineaSAP = 0;
		this.genero = genero;
		this.categoria = categoria;
		this.temporada = temporada;
		this.tipo = tipo;
		this.descripcionVenta = descripcionVenta;
	}

	public int getIdpicking() {
		return idpicking;
	}
	public void setIdpicking(int idpicking) {
		this.idpicking = idpicking;
	}
	public int getIdLineaSAP() {
		return idLineaSAP;
	}
	public void setIdLineaSAP(int idLineaSAP) {
		this.idLineaSAP = idLineaSAP;
	}
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	public int getMinCentral() {
		return minCentral;
	}
	public void setMinCentral(int minCentral) {
		this.minCentral = minCentral;
	}
	public int getMaxLocal() {
		return maxLocal;
	}
	public void setMaxLocal(int maxLocal) {
		this.maxLocal = maxLocal;
	}
	public String getNombreRegla() {
		return nombreRegla;
	}
	public void setNombreRegla(String nombreRegla) {
		this.nombreRegla = nombreRegla;
	}
	public int getTemporada() {
		return temporada;
	}
	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}
	public int getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(int solicitud) {
		this.solicitud = solicitud;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
