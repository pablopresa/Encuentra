package integraciones.erp.odoo.laIsla;

public class StockArticulos {
	private int origen;
	private String idArticulo;
	private int stockSolicitado;
	private int stockDisponible;
	
	public StockArticulos(int origen, String idArticulo, int stockSolicitado, int stockDisponible) {
		this.origen = origen;
		this.idArticulo = idArticulo;
		this.stockSolicitado = stockSolicitado;
		this.stockDisponible = stockDisponible;
	}

	public int getOrigen() {
		return origen;
	}

	public String getIdArticulo() {
		return idArticulo;
	}

	public int getStockSolicitado() {
		return stockSolicitado;
	}

	public int getStockDisponible() {
		return stockDisponible;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public void setIdArticulo(String idArticulo) {
		this.idArticulo = idArticulo;
	}

	public void setStockSolicitado(int stockSolicitado) {
		this.stockSolicitado = stockSolicitado;
	}

	public void setStockDisponible(int stockDisponible) {
		this.stockDisponible = stockDisponible;
	}
}
