package integraciones.erp.odoo.laIsla;

public class Articulo_Odoo_1 {
	private Object name, sexo, ean13, default_code, talle_proveedor, color_proveedor,temporada, marca, seller_ids,categoria;
	public Articulo_Odoo_1(Object name, Object sexo, Object ean13, Object default_code, Object talle_proveedor,
			Object color_proveedor, Object marca, Object categoria, Object temporada,
			Object seller_ids) 
	{
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



	public Object getName() {
		return name;
	}





	public void setName(Object name) {
		this.name = name;
	}





	public Object getSexo() {
		return sexo;
	}





	public void setSexo(Object sexo) {
		this.sexo = sexo;
	}





	public Object getEan13() {
		return ean13;
	}





	public void setEan13(Object ean13) {
		this.ean13 = ean13;
	}





	public Object getDefault_code() {
		return default_code;
	}





	public void setDefault_code(Object default_code) {
		this.default_code = default_code;
	}





	public Object getTalle_proveedor() {
		return talle_proveedor;
	}





	public void setTalle_proveedor(Object talle_proveedor) {
		this.talle_proveedor = talle_proveedor;
	}





	public Object getColor_proveedor() {
		return color_proveedor;
	}





	public void setColor_proveedor(Object color_proveedor) {
		this.color_proveedor = color_proveedor;
	}





	public Object getTemporada() {
		return temporada;
	}





	public void setTemporada(Object temporada) {
		this.temporada = temporada;
	}





	public Object getMarca() {
		return marca;
	}





	public void setMarca(Object marca) {
		this.marca = marca;
	}





	public Object getSeller_ids() {
		return seller_ids;
	}





	public void setSeller_ids(Object seller_ids) {
		this.seller_ids = seller_ids;
	}





	public Object getCategoria() {
		return categoria;
	}





	public void setCategoria(Object categoria) {
		this.categoria = categoria;
	}





	@Override
	public String toString() {
		return this.name + " - " + this.sexo + " - " + this.temporada + " - " + this.ean13 + " - " + this.marca + " - " + this.categoria
				 + " - " + this.default_code + " - " + this.talle_proveedor + " - " + this.color_proveedor + " - " + this.seller_ids;
	}
}
