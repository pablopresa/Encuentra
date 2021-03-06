package integraciones.erp.odoo.laIsla;

import java.util.ArrayList;

public class FormaPago {
	private int id;
	private ArrayList<Object> default_credit_account_id;
	private String auth_number, name;
	private double amount;
	
	public FormaPago(int id, ArrayList<Object> default_credit_account_id) {
		this.id = id;
		this.default_credit_account_id = default_credit_account_id;
	}

	public FormaPago(int id, ArrayList<Object> default_credit_account_id, String auth_number, String name, double amount) {
		this.id = id;
		this.default_credit_account_id = default_credit_account_id;
		this.auth_number = auth_number;
		this.name = name;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Object> getDefault_credit_account_id() {
		return default_credit_account_id;
	}

	public String getAuth_number() {
		return auth_number;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDefault_credit_account_id(ArrayList<Object> default_credit_account_id) {
		this.default_credit_account_id = default_credit_account_id;
	}

	public void setAuth_number(String auth_number) {
		this.auth_number = auth_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
