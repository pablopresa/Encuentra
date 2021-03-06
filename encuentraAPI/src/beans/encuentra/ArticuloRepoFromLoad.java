package beans.encuentra;

import java.util.List;

import beans.datatypes.DataIDDescripcion;

public class ArticuloRepoFromLoad 
{
	private int  sucursal,  idE, origen, tipo;
	private boolean manual, prioridad;
	private List<DataIDDescripcion> articulosCantidad;
	private String descripcionVenta;
	
	
	
	public String getDescripcionVenta() {
		return descripcionVenta;
	}
	public void setDescripcionVenta(String descripcionVenta) {
		this.descripcionVenta = descripcionVenta;
	}
	public int getSucursal() {
		return sucursal;
	}
	public void setSucursal(int sucursal) {
		this.sucursal = sucursal;
	}
	public boolean getManual() {
		return manual;
	}
	public void setManual(boolean manual) {
		this.manual = manual;
	}
	public int getIdE() {
		return idE;
	}
	public void setIdE(int idE) {
		this.idE = idE;
	}
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public List<DataIDDescripcion> getArticulosCantidad() {
		return articulosCantidad;
	}
	public void setArticulosCantidad(List<DataIDDescripcion> articulosCantidad) {
		this.articulosCantidad = articulosCantidad;
	}
	public ArticuloRepoFromLoad(int sucursal, boolean manual, int idE, int origen, int tipo,
			List<DataIDDescripcion> articulosCantidad) {
		this.sucursal = sucursal;
		this.manual = manual;
		this.idE = idE;
		this.origen = origen;
		this.tipo = tipo;
		this.articulosCantidad = articulosCantidad;
	}
	public boolean isPrioridad() {
		return prioridad;
	}
	public void setPrioridad(boolean prioridad) {
		this.prioridad = prioridad;
	}
	
	
	
	
}
