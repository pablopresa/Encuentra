package dataTypes;

import java.util.List;

public class DataDocumento implements Comparable
{
	private String nombre;
	private int id;
	private DataIDDescripcion tipo;
	private  List<DataIDDescDescripcion> alcances;
	private  DataIDDescripcion categoria;
	private String autor;
	private String fecha;
	private String inicial;
	private int posicion;
	private int lecturas;
	private String nombreAutor;
	
	
	
	
	
	

	public String getInicial() {
		return inicial;
	}
	public void setInicial(String inicial) {
		this.inicial = inicial;
	}
	public int getLecturas() {
		return lecturas;
	}
	public void setLecturas(int lecturas) {
		this.lecturas = lecturas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreAutor() {
		return nombreAutor;
	}
	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
	
	
	
	public DataIDDescripcion getTipo() {
		return tipo;
	}
	public void setTipo(DataIDDescripcion tipo) {
		this.tipo = tipo;
	}
	
	public DataIDDescripcion getCategoria() {
		return categoria;
	}
	public void setCategoria(DataIDDescripcion categoria) {
		this.categoria = categoria;
	}
	public List<DataIDDescDescripcion> getAlcances() {
		return alcances;
	}
	public void setAlcances(List<DataIDDescDescripcion> alcances) {
		this.alcances = alcances;
	}
	
	
	
	
	
	
	
	@Override
	public int compareTo(Object o) 
	{
		DataDocumento in = (DataDocumento)o;
		 return this.nombre.compareTo(in.getNombre());
		
	}
	
	
	
	
	
	
	

}
