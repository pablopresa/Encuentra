package beans.encuentra;

import java.io.Serializable;
import java.util.List;

import dataTypes.DataIDDescripcion;
import beans.ArticuloLineaReposicion;
import beans.Usuario;

public class Tarea  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private List<Usuario> usuarios;
	private Usuario usuario;
	private DataIDDescripcion tipo;
	private String fechaInicio;
	private DataIDDescripcion responsable;
	private int porcentaje;
	private DataIDDescripcion estado;
	private String observacion;
	private String fechaFin;
	private int productuvidad;
	private int cantidadPares;
	private int idDocumento;
	private List<ArticuloLineaReposicion> articulosIn;
	private int main;
	boolean parcial;
	private int idDeposito;
	
	
	
	
	
	public List<ArticuloLineaReposicion> getArticulosIn() {
		return articulosIn;
	}
	public void setArticulosIn(List<ArticuloLineaReposicion> articulosIn) {
		this.articulosIn = articulosIn;
	}
	public boolean isParcial() {
		return parcial;
	}
	public void setParcial(boolean parcial) {
		this.parcial = parcial;
	}
	private List <Tarea> subtareas;
	
	
	
	
	
	
	
	public int getIdDeposito() {
		return idDeposito;
	}
	public void setIdDeposito(int idDeposito) {
		this.idDeposito = idDeposito;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Tarea> getSubtareas() {
		return subtareas;
	}
	public void setSubtareas(List<Tarea> subtareas) {
		this.subtareas = subtareas;
	}
	public int getMain() {
		return main;
	}
	public void setMain(int main) {
		this.main = main;
	}
	public int getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}
	public int getCantidadPares() {
		return cantidadPares;
	}
	public void setCantidadPares(int cantidadPares) {
		this.cantidadPares = cantidadPares;
	}
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
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public DataIDDescripcion getResponsable() {
		return responsable;
	}
	public void setResponsable(DataIDDescripcion responsable) {
		this.responsable = responsable;
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
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getProductuvidad() {
		return productuvidad;
	}
	public void setProductuvidad(int productuvidad) {
		this.productuvidad = productuvidad;
	}
	public Tarea() 
	{
	}
	
	
	
	
}