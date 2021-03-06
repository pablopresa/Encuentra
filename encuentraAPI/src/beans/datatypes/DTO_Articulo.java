package beans.datatypes;

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
	private String unidadMedida;
	private List<String> codigoBarras;
	private String imagen;
	private int empaque;
	private double stock;
	private int idFamilia;
	private int idSubfamilia;
	private int cantMinDeVenta;


	public DTO_Articulo(String idArticulo, String descripcion, String talle, String color, String idTemporada,
			String coleccion, String idProveedor, String idMarca, String idClase, String idCategoria) {
		setIdArticulo(idArticulo);
		setDescripcion(descripcion);
		setTalle(talle);
		setColor(color);
		setIdTemporada(idTemporada);
		setColeccion(coleccion);
		setIdProveedor(idProveedor);
		setIdMarca(idMarca);
		setIdClase(idClase);
		setIdCategoria(idCategoria);
	}
	
	
	
	public int getCantMinDeVenta() {
		return cantMinDeVenta;
	}



	public void setCantMinDeVenta(int cantMinDeVenta) {
		this.cantMinDeVenta = cantMinDeVenta;
	}



	public DTO_Articulo(String idArticulo, String descripcion, String talle, String color, String idTemporada,
			String coleccion, String idProveedor, String idMarca, String idClase, String idCategoria, String idGenero, List<String> codigoBarras) {
		setIdArticulo(idArticulo);
		setDescripcion(descripcion);
		setTalle(talle);
		setColor(color);
		setIdTemporada(idTemporada);
		setColeccion(coleccion);
		setIdProveedor(idProveedor);
		setIdMarca(idMarca);
		setIdClase(idClase);
		setIdCategoria(idCategoria);
		setIdGenero(idGenero);
		setCodigoBarras(codigoBarras);
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

	public void setDescripcion(String descripcio) {
		if(descripcio == null || descripcio.equals("")) {
			this.descripcion = "0";
		}
		else {
			this.descripcion = descripcio;
		}
		
	}

	public void setTalle(String tall) {
		if(tall == null || tall.equals("")) {
			this.talle = "0";
		}
		else {
			this.talle = tall;
		}
		
	}

	public void setColor(String colo) {
		if(colo == null || colo.equals("") ) {
			this.color = "0";
		}
		else {
			this.color = colo;
		}
		
	}

	public void setIdTemporada(String idTemporad) {
		if(idTemporad == null || idTemporad.equals("")) {
			this.idTemporada = "0";
		}
		else {
			this.idTemporada = idTemporad;
		}
		
	}

	public void setColeccion(String coleccio) {
		if(coleccio == null || coleccio.equals("")) {
			this.coleccion = "0";
		}
		else {
			this.coleccion = coleccio;
		}
		
	}

	public void setIdProveedor(String idProveedo) {
		if(idProveedo == null || idProveedo.equals(""))  {
			this.idProveedor = "0";
		}
		else {
			this.idProveedor = idProveedo;
		}
		
	}

	public void setIdMarca(String idMarc) {
		if(idMarc == null || idMarc.equals("")) {
			this.idMarca = "0";
		}
		else {
			this.idMarca = idMarc;
		}
		
	}

	public void setIdClase(String idClas) {
		if(idClas == null || idClas.equals("")) {
			this.idClase = "0";
		}
		else {
			this.idClase = idClas;
		}
		
	}

	public void setIdCategoria(String idCategori) {
		if(idCategori == null || idCategori.equals("")) {
			this.idCategoria = "0";
		}
		else {
			this.idCategoria = idCategori;
		}
		
	}

	public String getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(String idGene) {
		if(idGene == null || idGene.equals("unisex") || idGene.equals("")) {
			this.idGenero = "0";
		}
		else {
			this.idGenero = idGene;
		}
		
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
		if(imagen == null || imagen.equals("null")) {
			this.imagen = "";
		}
		else {
			this.imagen = imagen;
		}
		
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

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
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
