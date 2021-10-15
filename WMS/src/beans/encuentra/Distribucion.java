package beans.encuentra;

import java.util.ArrayList;
import java.util.List;

import dataTypes.DataDescDescripcion;
import beans.Fecha;
import beans.Usuario;

public class Distribucion 
{
	private Usuario usuario;
	private Fecha fecha;
	private DataDescDescripcion motivo;
	private DataDescDescripcion prioridad;
	private DataDescDescripcion depositoOrigen;
	private String comentario;
	private int idBack;
	private boolean clearing;
	
	private List<ArticuloDistribucion> articulos;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Distribucion() 
	{
		this.articulos = new ArrayList<>();
		this.idBack = 0;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public List<ArticuloDistribucion> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<ArticuloDistribucion> articulos) {
		this.articulos = articulos;
	}

	public DataDescDescripcion getMotivo() {
		return motivo;
	}

	public void setMotivo(DataDescDescripcion motivo) {
		this.motivo = motivo;
	}

	public DataDescDescripcion getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(DataDescDescripcion prioridad) {
		this.prioridad = prioridad;
	}

	public DataDescDescripcion getDepositoOrigen() {
		return depositoOrigen;
	}

	public void setDepositoOrigen(DataDescDescripcion depositoOrigen) {
		this.depositoOrigen = depositoOrigen;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getIdBack() {
		return idBack;
	}

	public void setIdBack(int idBack) {
		this.idBack = idBack;
	}

	public boolean isClearing() {
		return clearing;
	}

	public void setClearing(boolean clearing) {
		this.clearing = clearing;
	}
	
	
	

}
