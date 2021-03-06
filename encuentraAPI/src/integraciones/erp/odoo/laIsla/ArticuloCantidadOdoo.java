package integraciones.erp.odoo.laIsla;

import java.util.ArrayList;

public class ArticuloCantidadOdoo {
	private Object qty, product_id, id;
	private ArrayList<Object> location_id;

	public ArticuloCantidadOdoo(Object qty, Object product_id, Object id, ArrayList<Object> location_id) {
		this.qty = qty;
		this.product_id = product_id;
		this.id = id;
		this.location_id = location_id;
	}

	public Object getQty() {
		return qty;
	}

	public Object getProduct_id() {
		return product_id;
	}

	public Object getId() {
		return id;
	}

	public ArrayList<Object> getLocation_id() {
		return location_id;
	}

	public void setQty(Object qty) {
		this.qty = qty;
	}

	public void setProduct_id(Object product_id) {
		this.product_id = product_id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public void setLocation_id(ArrayList<Object> location_id) {
		this.location_id = location_id;
	}
	
}
