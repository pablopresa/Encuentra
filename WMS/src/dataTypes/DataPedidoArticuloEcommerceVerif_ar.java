package dataTypes;

public class DataPedidoArticuloEcommerceVerif_ar 
{
	String idArticulo,deposito,	idOjo;
	int cantidadR,cantidadPro;
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public String getIdOjo() {
		return idOjo;
	}
	public void setIdOjo(String idOjo) {
		this.idOjo = idOjo;
	}
	public int getCantidadR() {
		return cantidadR;
	}
	public void setCantidadR(int cantidadR) {
		this.cantidadR = cantidadR;
	}
	public int getCantidadPro() {
		return cantidadPro;
	}
	public void setCantidadPro(int cantidadPro) {
		this.cantidadPro = cantidadPro;
	}
	
	public DataPedidoArticuloEcommerceVerif_ar(String idArticulo, String deposito, String idOjo, int cantidadR,	int cantidadPro)
	{
		
		this.idArticulo = idArticulo;
		this.deposito = deposito;
		this.idOjo = idOjo;
		this.cantidadR = cantidadR;
		this.cantidadPro = cantidadPro;
	}
	
	
	
	
	
}
