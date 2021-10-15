package dataTypes;

import java.util.List;

public class DataDistribucion 
{
	private int id;
	private String comentario, fecha,idAlmacenTransito, idAlmacenOrigen;
	private DataIDDescripcion motivo, prioridad;
	private List<ArticuloPedido> articulos;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdAlmacenTransito() {
		return idAlmacenTransito;
	}
	public void setIdAlmacenTransito(String idAlmacenTransito) {
		this.idAlmacenTransito = idAlmacenTransito;
	}
	public String getIdAlmacenOrigen() {
		return idAlmacenOrigen;
	}
	public void setIdAlmacenOrigen(String idAlmacenOrigen) {
		this.idAlmacenOrigen = idAlmacenOrigen;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public DataIDDescripcion getMotivo() {
		return motivo;
	}
	public void setMotivo(DataIDDescripcion motivo) {
		this.motivo = motivo;
	}
	public DataIDDescripcion getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(DataIDDescripcion prioridad) {
		this.prioridad = prioridad;
	}
	public List<ArticuloPedido> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<ArticuloPedido> articulos) {
		this.articulos = articulos;
	}
	public DataDistribucion() {
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	

}
