package dataTypes;

import java.util.List;

public class DataRecepcion 
{

	private int id;
	private int cantidadRecepcionada;
	private int cantidadEsperada;
	private int cantidadFacturada;
	private int idEstado;
	private List<DataOC>ordenes;
	private String agenda;
	private String fechaSQL;
	private String vehiculo;
	private DataDescDescripcion proveedor;
	private boolean importa;
	private int cantidadContada;
	private DataIDDescripcion idAgendaNomProvReagenda;
	
	
	
	public int getCantidadContada() {
		return cantidadContada;
	}
	public void setCantidadContada(int cantidadContada) {
		this.cantidadContada = cantidadContada;
	}
	public int getCantidadFacturada() {
		return cantidadFacturada;
	}
	public void setCantidadFacturada(int cantidadFacturada) {
		this.cantidadFacturada = cantidadFacturada;
	}
	public DataIDDescripcion getIdAgendaNomProvReagenda() {
		return idAgendaNomProvReagenda;
	}
	public void setIdAgendaNomProvReagenda(DataIDDescripcion idAgendaNomProvReagenda) {
		this.idAgendaNomProvReagenda = idAgendaNomProvReagenda;
	}
	public int getCantidadEsperada() {
		return cantidadEsperada;
	}
	
	public DataDescDescripcion getProveedor() {
		return proveedor;
	}
	public void setProveedor(DataDescDescripcion proveedor) {
		this.proveedor = proveedor;
	}
	public void setCantidadEsperada(int cantidadEsperada) {
		this.cantidadEsperada = cantidadEsperada;
	}
	public List<DataOC> getOrdenes() {
		return ordenes;
	}
	public void setOrdenes(List<DataOC> ordenes) {
		this.ordenes = ordenes;
	}
	public String getAgenda() {
		return agenda;
	}
	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	public String getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFechaSQL() {
		return fechaSQL;
	}
	public void setFechaSQL(String fechaSQL) 
	{
		this.fechaSQL = fechaSQL;
	}
	public int getCantidadRecepcionada() {
		return cantidadRecepcionada;
	}
	public void setCantidadRecepcionada(int cantidadRecepcionada) {
		this.cantidadRecepcionada = cantidadRecepcionada;
	}
	public boolean isImporta() {
		return importa;
	}
	public void setImporta(boolean importa) {
		this.importa = importa;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
	public DataRecepcion(){
		
	}
	
}
