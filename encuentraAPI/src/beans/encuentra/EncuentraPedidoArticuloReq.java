package beans.encuentra;

public class EncuentraPedidoArticuloReq implements Cloneable
{
	private String articulo;
	private int deposito, cantReq, cantProc,confirmado;
	
	
	
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public int getDeposito() {
		return deposito;
	}
	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}
	public int getCantReq() {
		return cantReq;
	}
	public void setCantReq(int cantReq) {
		this.cantReq = cantReq;
	}
	public int getCantProc() {
		return cantProc;
	}
	public void setCantProc(int cantProc) {
		this.cantProc = cantProc;
	}
	public int getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(int confirmado) {
		this.confirmado = confirmado;
	}
	
	
	  public EncuentraPedidoArticuloReq clone() throws CloneNotSupportedException
	    {
		  EncuentraPedidoArticuloReq e = null;
		  try {
			  e = (EncuentraPedidoArticuloReq) super.clone();
			  e.articulo =this.articulo;
			  e.cantProc =this.cantProc;
			  e.cantReq = this.cantReq;
			  e.confirmado = this.confirmado;
			  e.deposito = this.deposito;
			  
			  
			} catch (Exception ex) {
				ex.printStackTrace();
				 e = new EncuentraPedidoArticuloReq();
				  e.articulo =this.articulo;
				  e.cantProc =this.cantProc;
				  e.cantReq = this.cantReq;
				  e.confirmado = this.confirmado;
				  e.deposito = this.deposito;
			}  
		  return e;
		  
	    }
	
	
	
}
