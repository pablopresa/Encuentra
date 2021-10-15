package beans;

public class Sucursal {

	private int id;
	private String direccion;
	private String CiuPais;
	private String telefono;
	private String ip;
	private String proveedor;
	private int cantPerdidas;
	
	
	
	
	
	

	public String getCiuPais() {
		return CiuPais;
	}

	public void setCiuPais(String ciuPais) {
		CiuPais = ciuPais;
	}

	public int getCantPerdidas() {
		return cantPerdidas;
	}

	public void setCantPerdidas(int cantPerdidas) {
		this.cantPerdidas = cantPerdidas;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Sucursal(int id, String direccion, String telefono) {
		this.id = id;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Sucursal() {
	}

}
