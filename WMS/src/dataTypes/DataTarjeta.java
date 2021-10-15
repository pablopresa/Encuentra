package dataTypes;

public class DataTarjeta 
{
	private String icon,titulo,actual,anterior,color,diferencia;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getAnterior() {
		return anterior;
	}

	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(String diferencia) {
		this.diferencia = diferencia;
	}

	public DataTarjeta(String icon, String titulo, String actual, String anterior, String color, String diferencia) {
		this.icon = icon;
		this.titulo = titulo;
		this.actual = actual;
		this.anterior = anterior;
		this.color = color;
		this.diferencia = diferencia;
	}
	
	
	
	public DataTarjeta() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
