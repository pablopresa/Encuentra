package beans.encuentra;

import java.util.List;

import dataTypes.DataIDDescripcion;

import beans.Usuario;

public class DataTarea 
{
	private int id;
	private List<Usuario> usuarios;
	private DataIDDescripcion tipo;
	private int porcentaje;
	private DataIDDescripcion estado;
	private String observacion;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public DataIDDescripcion getTipo() {
		return tipo;
	}
	public void setTipo(DataIDDescripcion tipo) {
		this.tipo = tipo;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}
	public DataIDDescripcion getEstado() {
		return estado;
	}
	public void setEstado(DataIDDescripcion estado) {
		this.estado = estado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public DataTarea() 
	{
	}
	
	
	
	
}