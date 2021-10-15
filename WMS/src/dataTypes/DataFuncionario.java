package dataTypes;

public class DataFuncionario {
	int numero;
	String nombre;
	String apellido;
	DataIDDescripcion seccion;
	DataIDDescripcion deposito;
	String color;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public DataIDDescripcion getSeccion() {
		return seccion;
	}
	public void setSeccion(DataIDDescripcion seccion) {
		this.seccion = seccion;
	}
	public DataIDDescripcion getDeposito() {
		return deposito;
	}
	public void setDeposito(DataIDDescripcion deposito) {
		this.deposito = deposito;
	}
	

}
