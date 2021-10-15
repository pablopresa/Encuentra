package dataTypes;

import java.util.List;

public class Catalogo 
{
	private String nombre,marca, carpeta, lp1, lp2;
	private int id,idMarca;
	private List<DataArticuloCatalogo> articulos;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public List<DataArticuloCatalogo> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<DataArticuloCatalogo> articulos) {
		this.articulos = articulos;
	}
	public Catalogo() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCarpeta() {
		return carpeta;
	}
	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}
	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	public String getLp1() {
		return lp1;
	}
	public void setLp1(String lp1) {
		this.lp1 = lp1;
	}
	public String getLp2() {
		return lp2;
	}
	public void setLp2(String lp2) {
		this.lp2 = lp2;
	}
	
	
	
	
	
	
	

}
