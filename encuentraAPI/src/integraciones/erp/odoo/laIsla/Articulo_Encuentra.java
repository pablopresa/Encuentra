package integraciones.erp.odoo.laIsla;

public class Articulo_Encuentra {
	private String name, sexo, ean13, default_code, talle_proveedor, color_proveedor;
	private int id, marca, categoria, temporada, seller_ids;
	
	
	public Articulo_Encuentra(String name, String sexo, String ean13, String default_code, String talle_proveedor,
			String color_proveedor, int marca, int categoria, int temporada, int seller_ids) {
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

	public Articulo_Encuentra(int id, String name, String sexo, String ean13, String default_code, String talle_proveedor,
			String color_proveedor, int marca, int categoria, int temporada, int seller_ids) {
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
		this.id = id;
	}




	public String getName() {
		return name;
	}





	public String getSexo() {
		return sexo;
	}





	public String getEan13() {
		return ean13;
	}





	public String getDefault_code() {
		return default_code;
	}





	public String getTalle_proveedor() {
		return talle_proveedor;
	}





	public String getColor_proveedor() {
		return color_proveedor;
	}





	public int getMarca() {
		return marca;
	}





	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoria() {
		return categoria;
	}





	public int getTemporada() {
		return temporada;
	}





	public int getSeller_ids() {
		return seller_ids;
	}





	public void setName(String name) {
		this.name = name;
	}





	public void setSexo(String sexo) {
		this.sexo = sexo;
	}





	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}





	public void setDefault_code(String default_code) {
		this.default_code = default_code;
	}





	public void setTalle_proveedor(String talle_proveedor) {
		this.talle_proveedor = talle_proveedor;
	}





	public void setColor_proveedor(String color_proveedor) {
		this.color_proveedor = color_proveedor;
	}





	public void setMarca(int marca) {
		this.marca = marca;
	}





	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}





	public void setTemporada(int temporada) {
		this.temporada = temporada;
	}





	public void setSeller_ids(int seller_ids) {
		this.seller_ids = seller_ids;
	}





	@Override
	public String toString() {
		return this.name + " - " + this.sexo + " - " + this.temporada + " - " + this.ean13 + " - " + this.marca + " - " + this.categoria
				 + " - " + this.default_code + " - " + this.talle_proveedor + " - " + this.color_proveedor + " - " + this.seller_ids;
	}
}
