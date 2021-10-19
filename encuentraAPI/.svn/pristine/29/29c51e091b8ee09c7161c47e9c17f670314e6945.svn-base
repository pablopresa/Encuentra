package integraciones.erp.billerTata;

import beans.encuentra.Fecha;

public class TicketCambio 
{
	private String vendedor,sku,ntrans,fecha_hora,caja;
	private int qty;

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNtrans() {
		return ntrans;
	}

	public void setNtrans(String ntrans) {
		this.ntrans = ntrans;
	}

	

	public String getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(String fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public String getCaja() {
		return caja;
	}

	public void setCaja(String caja) {
		this.caja = caja;
	}
	

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public TicketCambio(String sku, String nTrans, int qty) 
	{
		Fecha fecha = new Fecha();
		
		this.fecha_hora = fecha.darFechaDia_Mes_Anio_HoraBarra();
		this.caja="001";
		this.sku = sku;
		this.ntrans = nTrans;
		this.vendedor = "[NO VENDEDOR]:";
		this.qty = qty;
	}
	
	
	

}
