package integraciones.erp.odoo.laIsla;

import java.util.ArrayList;

public class PickingDuplication {
	private ArrayList<Object> picking_duplication;
	private int id;
	
	public PickingDuplication(ArrayList<Object> picking_duplication, int id) {
		this.picking_duplication = picking_duplication;
		this.id = id;
	}

	public ArrayList<Object> getPicking_duplication() {
		return picking_duplication;
	}

	public int getId() {
		return id;
	}

	public void setPicking_duplication(ArrayList<Object> picking_duplication) {
		this.picking_duplication = picking_duplication;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
