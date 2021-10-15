package dataTypes;

import beans.Fecha;

public class DataOVOsoneca 
{
	String orden;
	String orden_Destino;
	String fechaEntrega;
	String rSocialCliente;
	String cEDIDireccionEntrega;
	String cEDIDireccionEntregaII;
	String cEDIZIPDirEntrega;
	String cEDICiudad;
	String cEDINombre;
	String direccionCliente;
	String zIPCliente;
	String ciudad;
	String fWD;
	String fechaEntregaII;
	String horaEntrega;
	String fechaProcesado;
	String horaProcesado;
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getOrden_Destino() {
		return orden_Destino;
	}
	public void setOrden_Destino(String orden_Destino) {
		this.orden_Destino = orden_Destino;
	}
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public String getrSocialCliente() {
		return rSocialCliente;
	}
	public void setrSocialCliente(String rSocialCliente) {
		this.rSocialCliente = rSocialCliente;
	}
	public String getcEDIDireccionEntrega() {
		return cEDIDireccionEntrega;
	}
	public void setcEDIDireccionEntrega(String cEDIDireccionEntrega) {
		this.cEDIDireccionEntrega = cEDIDireccionEntrega;
	}
	public String getcEDIDireccionEntregaII() {
		return cEDIDireccionEntregaII;
	}
	public void setcEDIDireccionEntregaII(String cEDIDireccionEntregaII) {
		this.cEDIDireccionEntregaII = cEDIDireccionEntregaII;
	}
	public String getcEDIZIPDirEntrega() {
		return cEDIZIPDirEntrega;
	}
	public void setcEDIZIPDirEntrega(String cEDIZIPDirEntrega) {
		this.cEDIZIPDirEntrega = cEDIZIPDirEntrega;
	}
	public String getcEDICiudad() {
		return cEDICiudad;
	}
	public void setcEDICiudad(String cEDICiudad) {
		this.cEDICiudad = cEDICiudad;
	}
	public String getcEDINombre() {
		return cEDINombre;
	}
	public void setcEDINombre(String cEDINombre) {
		this.cEDINombre = cEDINombre;
	}
	public String getDireccionCliente() {
		return direccionCliente;
	}
	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	public String getzIPCliente() {
		return zIPCliente;
	}
	public void setzIPCliente(String zIPCliente) {
		this.zIPCliente = zIPCliente;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getfWD() {
		return fWD;
	}
	public void setfWD(String fWD) {
		this.fWD = fWD;
	}
	public String getFechaEntregaII() {
		return fechaEntregaII;
	}
	public void setFechaEntregaII(String fechaEntregaII) {
		this.fechaEntregaII = fechaEntregaII;
	}
	public String getHoraEntrega() {
		return horaEntrega;
	}
	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}
	public String getFechaProcesado() {
		return fechaProcesado;
	}
	public void setFechaProcesado(String fechaProcesado) {
		this.fechaProcesado = fechaProcesado;
	}
	public String getHoraProcesado() {
		return horaProcesado;
	}
	public void setHoraProcesado(String horaProcesado) {
		this.horaProcesado = horaProcesado;
	}
	public DataOVOsoneca(String orden, String orden_Destino,String fechaEntrega, String rSocialCliente,	String cEDIDireccionEntrega, String cEDIDireccionEntregaII,	String cEDIZIPDirEntrega, String cEDICiudad, String cEDINombre, String direccionCliente, String zIPCliente, String ciudad, String fWD, String fechaEntregaII) 
	{
		this.orden = orden;
		this.orden_Destino = orden_Destino;
		this.fechaEntrega = fechaEntrega;
		this.rSocialCliente = rSocialCliente;
		this.cEDIDireccionEntrega = cEDIDireccionEntrega;
		this.cEDIDireccionEntregaII = cEDIDireccionEntregaII;
		this.cEDIZIPDirEntrega = cEDIZIPDirEntrega;
		this.cEDICiudad = cEDICiudad;
		this.cEDINombre = cEDINombre;
		this.direccionCliente = direccionCliente;
		this.zIPCliente = zIPCliente;
		this.ciudad = ciudad;
		this.fWD = fWD;
		this.fechaEntregaII = fechaEntregaII;
		this.horaEntrega = "120000";
		Fecha fecha = new Fecha();
		String amd = fecha.darFechaAnioMesDia();
		this.fechaProcesado = amd;
		this.horaProcesado = fecha.getHora()+""+fecha.getMinuto()+"00";
	}
	
	
	
	
	
	
	
	
	

}
