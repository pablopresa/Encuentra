package dataTypes;

import java.util.ArrayList;
import java.util.List;

public class DTO_Articulo {
	private String idArticulo;
	private String descripcion;
	private String talle;
	private String color;
	private String idTemporada;
	private String coleccion;
	private String idProveedor;
	private String idMarca;
	private String idClase;
	private String idCategoria;
	private String idGenero;
	private List<String> codigoBarras;
	private String imagen;
	private int empaque;
	private double stock;
	private int cantMinDeVenta;
	private int idFamilia;
	private int idSubfamilia;


	public DTO_Articulo(String idArticulo, String descripcion, String talle, String color, String idTemporada,
			String coleccion, String idProveedor, String idMarca, String idClase, String idCategoria) {
		this.idArticulo = idArticulo;
		this.descripcion = descripcion;
		this.talle = talle;
		this.color = color;
		this.idTemporada = idTemporada;
		this.coleccion = coleccion;
		this.idProveedor = idProveedor;
		this.idMarca = idMarca;
		this.idClase = idClase;
		this.idCategoria = idCategoria;
	}
	
	public DTO_Articulo(String idArticulo, String descripcion, String talle, String color, String idTemporada,
			String coleccion, String idProveedor, String idMarca, String idClase, String idCategoria, String idGenero, List<String> codigoBarras) {
		this.idArticulo = idArticulo;
		this.descripcion = descripcion;
		this.talle = talle;
		this.color = color;
		this.idTemporada = idTemporada;
		this.coleccion = coleccion;
		this.idProveedor = idProveedor;
		this.idMarca = idMarca;
		this.idClase = idClase;
		this.idCategoria = idCategoria;
		this.idGenero = idGenero;
		this.codigoBarras = codigoBarras;
	}
	
	public int getCantMinDeVenta() {
		return cantMinDeVenta;
	}

	public void setCantMinDeVenta(int cantMinDeVenta) {
		this.cantMinDeVenta = cantMinDeVenta;
	}


	public String getIdArticulo() {
		return idArticulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getTalle() {
		return talle;
	}

	public String getColor() {
		return color;
	}

	public String getIdTemporada() {
		return idTemporada;
	}

	public String getColeccion() {
		return coleccion;
	}

	public String getIdProveedor() {
		return idProveedor;
	}

	public String getIdMarca() {
		return idMarca;
	}

	public String getIdClase() {
		return idClase;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setTalle(String talle) {
		this.talle = talle;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setIdTemporada(String idTemporada) {
		this.idTemporada = idTemporada;
	}

	public void setColeccion(String coleccion) {
		this.coleccion = coleccion;
	}

	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}

	public void setIdMarca(String idMarca) {
		this.idMarca = idMarca;
	}

	public void setIdClase(String idClase) {
		this.idClase = idClase;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}

	public List<String> getCodigoBarras() {
		if(codigoBarras==null) 
			codigoBarras=new ArrayList<>();
		
		return codigoBarras;
	}

	public void setCodigoBarras(List<String> codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getEmpaque() {
		return empaque;
	}

	public void setEmpaque(int empaque) {
		this.empaque = empaque;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public int getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(int idFamilia) {
		this.idFamilia = idFamilia;
	}

	public int getIdSubfamilia() {
		return idSubfamilia;
	}

	public void setIdSubfamilia(int idSubfamilia) {
		this.idSubfamilia = idSubfamilia;
	}
	
	
}
