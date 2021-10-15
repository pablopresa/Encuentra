package beans.encuentra;

public class UsoEstanteria 
{

	private int id;
	private String descripcion;
	boolean inventariable;
	
	
	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public boolean isInventariable() {
		return inventariable;
	}



	public void setInventariable(boolean inventariable) {
		this.inventariable = inventariable;
	}
	

	public UsoEstanteria() {
	}
	
	//Recibe un int devuelve un booleano
	public UsoEstanteria(int id, String descripcion, int inventariable) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		if(inventariable==1)
			this.inventariable = true;
		else
			this.inventariable = false;
	}

	public UsoEstanteria(String descripcion, int id, boolean inventariable) {
		this.descripcion = descripcion;
		this.id = id;
		this.inventariable = inventariable;
	}




}
