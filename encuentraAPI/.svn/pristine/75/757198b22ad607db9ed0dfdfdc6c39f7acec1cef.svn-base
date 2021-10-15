package beans.derivation;

import java.util.Hashtable;

import beans.datatypes.DataIDDescripcion;

public class Deposito implements Comparable
{
	
	private Hashtable<String,Integer> stocks;
	private String direccion, nombre, ciudad, departamento, telefono;
	private int id, login, nroPuerta, local;
	private DataIDDescripcion tipo;
	private ParametrosPreparacion parametros;
	
	
	
	public ParametrosPreparacion getParametros() {
		return parametros;
	}

	public void setParametros(ParametrosPreparacion parametros) {
		this.parametros = parametros;
	}

	public int getNroPuerta() {
		return nroPuerta;
	}

	public void setNroPuerta(int nroPuerta) {
		this.nroPuerta = nroPuerta;
	}

	public int getLocal() {
		return local;
	}

	public void setLocal(int local) {
		this.local = local;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLogin() {
		return login;
	}

	public void setLogin(int login) {
		this.login = login;
	}

	public DataIDDescripcion getTipo() {
		return tipo;
	}

	public void setTipo(DataIDDescripcion tipo) {
		this.tipo = tipo;
	}

	public Deposito()  {
	}
	
	
	
	@Override
	public int compareTo(Object o) 
	{
		Deposito dl = (Deposito)o;        
		  	
		if(dl.getParametros().getPrioridad()==this.getParametros().getPrioridad())
		{
			return 0;
		}
	    else if(dl.getParametros().getPrioridad()>this.getParametros().getPrioridad())
       	{
       		return 1;
       	}
      	else
       	{
      		return -1;
       	}
	
	}
	
	
	
	
}
