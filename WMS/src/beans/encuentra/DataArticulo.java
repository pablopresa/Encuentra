package beans.encuentra;

public class DataArticulo 
{
	private String id;
	private int anchoCaja;
	private int largoCaja;
	private int altoCaja;
	private int profCaja;
	private String barra;
	private String descripcion;
	private int cantidad;
	private String codBase;
	private int idTypeSKU;
	private String typeSKU;	
	private String funcion;
	
	public String getFuncion() {
		return funcion;
	}
	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}
	public String getCodBase() {
		return codBase;
	}
	public void setCodBase(String codBase) {
		this.codBase = codBase;
	}
	public String getBarra() {
		return barra;
	}
	public void setBarra(String barra) {
		this.barra = barra;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getAltoCaja() {
		return altoCaja;
	}
	public void setAltoCaja(int altoCaja) {
		this.altoCaja = altoCaja;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAnchoCaja() {
		return anchoCaja;
	}
	public void setAnchoCaja(int anchoCaja) {
		this.anchoCaja = anchoCaja;
	}
	public int getLargoCaja() {
		return largoCaja;
	}
	public void setLargoCaja(int largoCaja) {
		this.largoCaja = largoCaja;
	}
	public int getProfCaja() {
		return profCaja;
	}
	public void setProfCaja(int profCaja) {
		this.profCaja = profCaja;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public DataArticulo() 
	{
	}
	public int getIdTypeSKU() {
		return idTypeSKU;
	}
	public void setIdTypeSKU(int idTypeSKU) {
		this.idTypeSKU = idTypeSKU;
	}
	public String getTypeSKU() {
		return typeSKU;
	}
	public void setTypeSKU(String typeSKU) {
		this.typeSKU = typeSKU;
	}
	
	
	
	
}
