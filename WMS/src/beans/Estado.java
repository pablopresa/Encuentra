package beans;

public class Estado {
	private int id;
	private String dscripcion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDscripcion() {
		return dscripcion;
	}

	public void setDscripcion(String dscripcion) {
		this.dscripcion = dscripcion;
	}

	public Estado(int id, String dscripcion) {
		this.id = id;
		this.dscripcion = dscripcion;
	}

	public Estado() {
	}

}
