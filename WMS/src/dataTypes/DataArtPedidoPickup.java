package dataTypes;

public class DataArtPedidoPickup 
{
	private String idPedido,idArticulo;
	private int cant,idDestino,cantRequerida,canal,deposito;
	public String getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}
	public String getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}
	
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	
	
	
	
	public int getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}
	public DataArtPedidoPickup(String idPedido, String idArticulo, int idDestino, int cant, int cantRequerida,int canal) {
		this.idPedido = idPedido;
		this.idArticulo = idArticulo;
		this.idDestino = idDestino;
		this.cant = cant;
		this.cantRequerida = cantRequerida;
		this.canal = canal;
	}
	public int getCantRequerida() {
		return cantRequerida;
	}
	public void setCantRequerida(int cantRequerida) {
		this.cantRequerida = cantRequerida;
	}
	public int getCanal() {
		return canal;
	}
	public void setCanal(int canal) {
		this.canal = canal;
	}
	public int getDeposito() {
		return deposito;
	}
	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}
	
	

}
