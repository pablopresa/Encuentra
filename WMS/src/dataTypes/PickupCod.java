package dataTypes;

public class PickupCod {
	
	private Long idPedido;
	private int local;
	private int operacion;
	private String qrLocal;
	private String qrCliente;
	private String codigoLocal;
	private String codigoCliente;
	
	
	public int getOperacion() {
		return operacion;
	}
	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public int getLocal() {
		return local;
	}
	public void setLocal(int local) {
		this.local = local;
	}
	public String getQrLocal() {
		return qrLocal;
	}
	public void setQrLocal(String qrLocal) {
		this.qrLocal = qrLocal;
	}
	public String getQrCliente() {
		return qrCliente;
	}
	public void setQrCliente(String qrCliente) {
		this.qrCliente = qrCliente;
	}
	public String getCodigoLocal() {
		return codigoLocal;
	}
	public void setCodigoLocal(String codigoLocla) {
		this.codigoLocal = codigoLocla;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	public PickupCod(Long ped, int loc, int op, String qrLoc, String qrCli, String codLoc, String codCli){
		this.idPedido=ped;
		this.local=loc;
		this.operacion=op;
		this.qrLocal=qrLoc;
		this.qrCliente=qrCli;
		this.codigoLocal=codLoc;
		this.codigoCliente=codCli;
	}
	
	
}
