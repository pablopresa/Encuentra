package dataTypes;

import java.util.List;

public class ReposicionPicking {

	private int idTarea;
	private String idArticulo,imagen;
	private String descripcionArticulo;
	private int cantidad;
	private int cantidadMovida;
	private int cantidadBajada;	
	List<Ubicacion_ReposicionPicking> listaUbicaciones;
	private boolean ultimo;
	private int packing;
	
	public String getImagen() {
		return imagen;
	}



	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	public ReposicionPicking(int idTarea, String idArticulo, String descripcionArticulo, int cantidad,
			int cantidadMovida, int cantidadBajada, List<Ubicacion_ReposicionPicking> listaUbicaciones, boolean ultimo) {
		super();
		this.idTarea = idTarea;
		this.idArticulo = idArticulo;
		this.descripcionArticulo = descripcionArticulo;
		this.cantidad = cantidad;
		this.cantidadMovida = cantidadMovida;
		this.cantidadBajada = cantidadBajada;
		this.listaUbicaciones = listaUbicaciones;
		this.ultimo = ultimo;
	}
	
	
	
	
	
	public int getPacking() {
		return packing;
	}



	public void setPacking(int packing) {
		this.packing = packing;
	}



	public boolean isUltimo() {
		return ultimo;
	}



	public void setUltimo(boolean ultimo) {
		this.ultimo = ultimo;
	}



	public ReposicionPicking(){
		
	}
	
	public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public String getDescripcionArticulo() {
		return descripcionArticulo;
	}
	public void setDescripcionArticulo(String descripcionArticulo) {
		this.descripcionArticulo = descripcionArticulo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCantidadMovida() {
		return cantidadMovida;
	}
	public void setCantidadMovida(int cantidadMovida) {
		this.cantidadMovida = cantidadMovida;
	}
	public int getCantidadBajada() {
		return cantidadBajada;
	}
	public void setCantidadBajada(int cantidadBajada) {
		this.cantidadBajada = cantidadBajada;
	}
	public List<Ubicacion_ReposicionPicking> getListaUbicaciones() {
		return listaUbicaciones;
	}
	public void setListaUbicaciones(List<Ubicacion_ReposicionPicking> listaUbicaciones) {
		this.listaUbicaciones = listaUbicaciones;
	}
	
	
	
	
	
	
	
	
}
