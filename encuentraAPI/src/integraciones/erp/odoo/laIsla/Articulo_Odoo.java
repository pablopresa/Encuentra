package integraciones.erp.odoo.laIsla;

import java.util.ArrayList;

public class Articulo_Odoo {
	private Object id, name, sexo, ean13, default_code, talle_proveedor, color_proveedor;
	private Object marca, seller_ids,categoria, temporada, list_price;
	
	public Articulo_Odoo(Object name, Object sexo, Object ean13, Object default_code, Object talle_proveedor,
			Object color_proveedor, Object marca, Object categoria, Object temporada,
			Object seller_ids) {
		this.name = name;
		this.sexo = sexo;
		this.ean13 = ean13;
		this.default_code = default_code;
		this.talle_proveedor = talle_proveedor;
		this.color_proveedor = color_proveedor;
		this.marca = marca;
		this.categoria = categoria;
		this.temporada = temporada;
		this.seller_ids = seller_ids;
	}

	



	public Articulo_Odoo(Object id, Object name, Object sexo, Object ean13, Object default_code, Object talle_proveedor,
			Object color_proveedor, Object marca, Object seller_ids, Object categoria, Object temporada) {
		this.id = id;
		this.name = name;
		this.sexo = sexo;
		this.ean13 = ean13;
		this.default_code = default_code;
		this.talle_proveedor = talle_proveedor;
		this.color_proveedor = color_proveedor;
		this.marca = marca;
		this.seller_ids = seller_ids;
		this.categoria = categoria;
		this.temporada = temporada;
	}





	public Object getName() {
		return name;
	}




	public Object getSexo() {
		return sexo;
	}




	public Object getEan13() {
		return ean13;
	}




	public Object getDefault_code() {
		return default_code;
	}




	public Object getTalle_proveedor() {
		return talle_proveedor;
	}




	public Object getColor_proveedor() {
		return color_proveedor;
	}




	public Object getMarca() {
		return marca;
	}




	public Object getSeller_ids() {
		return seller_ids;
	}




	public Object getCategoria() {
		return categoria;
	}




	public Object getTemporada() {
		return temporada;
	}




	public void setName(Object name) {
		this.name = name;
	}




	public void setSexo(Object sexo) {
		this.sexo = sexo;
	}




	public void setEan13(Object ean13) {
		this.ean13 = ean13;
	}




	public void setDefault_code(Object default_code) {
		this.default_code = default_code;
	}




	public void setTalle_proveedor(Object talle_proveedor) {
		this.talle_proveedor = talle_proveedor;
	}




	public void setColor_proveedor(Object color_proveedor) {
		this.color_proveedor = color_proveedor;
	}




	public void setMarca(Object marca) {
		this.marca = marca;
	}




	public void setSeller_ids(Object seller_ids) {
		this.seller_ids = seller_ids;
	}




	public void setCategoria(Object categoria) {
		this.categoria = categoria;
	}




	public void setTemporada(Object temporada) {
		this.temporada = temporada;
	}

	



	public Object getId() {
		return id;
	}





	public void setId(Object id) {
		this.id = id;
	}





	public Articulo_Odoo(Articulo_Odoo_1 aodoo) 
	{
		try
		{
			if(aodoo.getTemporada().equals("false") || (!(Boolean)aodoo.getTemporada()))
			{
				Object[] temp = {};
				this.temporada = temp;
			}
			else
			{
				ArrayList<Object> array = (ArrayList<Object> )aodoo.getTemporada();
				Object[] temp = array.toArray();
				this.temporada = temp;
			}
		}
		catch (Exception e) 
		{
			ArrayList<Object> array = (ArrayList<Object> )aodoo.getTemporada();
			Object[] temp = array.toArray();
			this.temporada = temp;
		}
		
		try
		{
			if(aodoo.getCategoria().equals("false") || ( !(Boolean)aodoo.getCategoria()))
			{
				Object[] temp = {};
				this.categoria = temp;
			}
			else
			{
				ArrayList<Object> array = (ArrayList<Object> )aodoo.getCategoria();
				Object[] temp = array.toArray();
				this.categoria = temp;
			}
		}
		catch (Exception e) 
		{
			ArrayList<Object> array = (ArrayList<Object> )aodoo.getCategoria();
			Object[] temp = array.toArray();
			this.categoria = temp;
		}
		
		try
		{
			if(aodoo.getMarca().equals("false")|| (!(Boolean)aodoo.getMarca()))
			{
				Object[] temp = {};
				this.marca = temp;
			}
			else
			{
				ArrayList<Object> array = (ArrayList<Object> )aodoo.getMarca();
				Object[] temp = array.toArray();
				this.marca =  temp;
			}
		}
		catch (Exception e) 
		{
			ArrayList<Object> array = (ArrayList<Object> )aodoo.getMarca();
			Object[] temp = array.toArray();
			this.marca =  temp;
		}
		
		
		try
		{
			if(aodoo.getSeller_ids().equals("false")|| (!(Boolean)aodoo.getSeller_ids()))
			{
				Object[] temp = {};
				this.seller_ids = temp;
			}
			else
			{
				ArrayList<Object> array = (ArrayList<Object> )aodoo.getSeller_ids();
				Object[] temp = array.toArray();
				this.seller_ids =  temp;
			}
		}
		catch (Exception e) 
		{
			ArrayList<Object> array = (ArrayList<Object> )aodoo.getSeller_ids();
			Object[] temp = array.toArray();
			this.seller_ids =  temp;
		}
		
		
		this.color_proveedor = aodoo.getColor_proveedor();
		this.default_code = aodoo.getDefault_code();
		this.ean13 = aodoo.getEan13();
		this.name = aodoo.getName();
		this.sexo = aodoo.getSexo();
		this.talle_proveedor = aodoo.getTalle_proveedor();
		
		
		
		
		
	}

	@Override
	public String toString() {
		return this.name + " - " + this.sexo + " - " + this.temporada + " - " + this.ean13 + " - " + this.marca + " - " + this.categoria
				 + " - " + this.default_code + " - " + this.talle_proveedor + " - " + this.color_proveedor + " - " + this.seller_ids;
	}





	public Object getList_price() {
		return list_price;
	}





	public void setList_price(Object list_price) {
		this.list_price = list_price;
	}
}
