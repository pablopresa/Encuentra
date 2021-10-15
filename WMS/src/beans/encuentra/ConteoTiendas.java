package beans.encuentra;

import java.util.List;

import dataTypes.DataIDDescripcion;

public class ConteoTiendas {
	private int idConteo, deposito, totalUnidades, totalArticulos;
	private String descripcion,fecha,nombreDepo;
	private DataIDDescripcion usuarioPide;
	boolean abierto;
	
	private List<ArticuloConteo> articulos;

	public int getIdConteo() {
		return idConteo;
	}

	public void setIdConteo(int idConteo) {
		this.idConteo = idConteo;
	}

	public int getDeposito() {
		return deposito;
	}

	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public DataIDDescripcion getUsuarioPide() {
		return usuarioPide;
	}

	public void setUsuarioPide(DataIDDescripcion usuarioPide) {
		this.usuarioPide = usuarioPide;
	}

	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}

	public List<ArticuloConteo> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<ArticuloConteo> articulos) {
		this.articulos = articulos;
	}

	
	
	public int getTotalUnidades() {
		return totalUnidades;
	}

	public void setTotalUnidades(int totalUnidades) {
		this.totalUnidades = totalUnidades;
	}

	public int getTotalArticulos() {
		return totalArticulos;
	}

	public void setTotalArticulos(int totalArticulos) {
		this.totalArticulos = totalArticulos;
	}

	public ConteoTiendas() {
		
	}

	public String getNombreDepo() {
		return nombreDepo;
	}

	public void setNombreDepo(String nombreDepo) {
		this.nombreDepo = nombreDepo;
	}
}
