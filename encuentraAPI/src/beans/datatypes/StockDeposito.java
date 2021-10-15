package beans.datatypes;



public class StockDeposito 
{
	private String idDeposito,idArticulo;
	private Double stock;

	public String getIdDeposito() {
		return idDeposito;
	}

	public void setIdDeposito(String idDeposito) {
		this.idDeposito = idDeposito;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public StockDeposito(String idDeposito, String idArticulo, Double stock) {
		this.idDeposito = idDeposito;
		this.idArticulo = idArticulo;
		this.stock = stock;
	}

	
	
	

}
