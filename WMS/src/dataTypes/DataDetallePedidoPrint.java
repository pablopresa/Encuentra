package dataTypes;

import java.util.List;

public class DataDetallePedidoPrint 
{

	private String idPedido,Nombre,Apellido,cedula,Direccion,DireccionNota,mail,telefono,porcDescuento,cliRuc,clicedula,FP,importePago,Comentario,costoEnvio;
	private List<DataDetallePedidoPrintArt> articulos;
	public String getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getDireccionNota() {
		return DireccionNota;
	}
	public void setDireccionNota(String direccionNota) {
		DireccionNota = direccionNota;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPorcDescuento() {
		return porcDescuento;
	}
	public void setPorcDescuento(String porcDescuento) {
		this.porcDescuento = porcDescuento;
	}
	public String getCliRuc() {
		return cliRuc;
	}
	public void setCliRuc(String cliRuc) {
		this.cliRuc = cliRuc;
	}
	public String getClicedula() {
		return clicedula;
	}
	public void setClicedula(String clicedula) {
		this.clicedula = clicedula;
	}
	public String getFP() {
		return FP;
	}
	public void setFP(String fP) {
		FP = fP;
	}
	public String getImportePago() {
		return importePago;
	}
	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}
	public String getComentario() {
		return Comentario;
	}
	public void setComentario(String comentario) {
		Comentario = comentario;
	}
	public List<DataDetallePedidoPrintArt> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<DataDetallePedidoPrintArt> articulos) {
		this.articulos = articulos;
	}
	
	
	public String getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio(String costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	public DataDetallePedidoPrint(String idPedido, String nombre, String apellido, String cedula, String direccion,
			String direccionNota, String mail, String telefono, String porcDescuento, String cliRuc, String clicedula,
			String fP, String importePago, String comentario,String costoEnvio) 
	{
		this.idPedido = idPedido;
		Nombre = nombre;
		Apellido = apellido;
		this.cedula = cedula;
		Direccion = direccion;
		DireccionNota = direccionNota;
		this.mail = mail;
		this.telefono = telefono;
		this.porcDescuento = porcDescuento;
		this.cliRuc = cliRuc;
		this.clicedula = clicedula;
		FP = fP;
		this.importePago = importePago;
		Comentario = comentario;
		this.costoEnvio = costoEnvio;
		
	}
	
	
	
	
	
	
	
	
	
	
}
