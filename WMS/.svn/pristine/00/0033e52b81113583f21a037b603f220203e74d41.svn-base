package logica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DTO_BultoPuntaje implements Comparable<DTO_BultoPuntaje>, Comparator<DTO_BultoPuntaje> {
	private String codigoBulto;
	private String ojo;
	private double puntaje;
	private List<DTO_ArticuloCantidad> contenidoRestanteEnBulto;
	private int recorrido;
	private List<DTO_ArticuloCantidad> contenidoUsadoPicking;
	private boolean usaBultoCompleto;
	
	public DTO_BultoPuntaje() {
		this.contenidoUsadoPicking = new ArrayList<DTO_ArticuloCantidad>();
		this.usaBultoCompleto = true;
		}
	
	public DTO_BultoPuntaje(String codigoBulto, String ojo, double puntaje, List<DTO_ArticuloCantidad> contenidoRestanteEnBulto, int recorrido) {
		this.codigoBulto = codigoBulto;
		this.ojo = ojo;
		this.puntaje = puntaje;
		this.contenidoRestanteEnBulto = contenidoRestanteEnBulto;
		this.recorrido = recorrido;
		this.contenidoUsadoPicking = new ArrayList<DTO_ArticuloCantidad>();
		this.usaBultoCompleto = true;
	}
	public String getCodigoBulto() {
		return codigoBulto;
	}
	public double getPuntaje() {
		return puntaje;
	}
	public void setCodigoBulto(String codigoBulto) {
		this.codigoBulto = codigoBulto;
	}
	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}
	public void setOjo(String ojo) {
		this.ojo = ojo;
	}
	public List<DTO_ArticuloCantidad> getContenidoRestanteEnBulto() {return this.contenidoRestanteEnBulto;}

	public void setContenidoRestanteEnBulto(List<DTO_ArticuloCantidad> contenidoRestanteEnBulto) {
		this.contenidoRestanteEnBulto = contenidoRestanteEnBulto;
	}
	public String getOjo() {return this.ojo;}
	
	public int getRecorrido() {
		return recorrido;
	}
	public void setRecorrido(int recorrido) {
		this.recorrido = recorrido;
	}
	
	public List<DTO_ArticuloCantidad> getContenidoUsadoPicking() {
		return contenidoUsadoPicking;
	}

	public void setContenidoUsadoPicking(List<DTO_ArticuloCantidad> contenidoUsadoPicking) {
		this.contenidoUsadoPicking = contenidoUsadoPicking;
	}
	

	public boolean isUsaBultoCompleto() {
		return usaBultoCompleto;
	}

	public void setUsaBultoCompleto(boolean usaBultoCompleto) {
		this.usaBultoCompleto = usaBultoCompleto;
	}

	@Override
	public int compareTo(DTO_BultoPuntaje o) {
		if(this.puntaje == o.puntaje)
			return 0;
		else if(this.puntaje < o.puntaje)
			return 1;
		else
			return -1;
	}
	@Override
	public int compare(DTO_BultoPuntaje bp1, DTO_BultoPuntaje bp2) {
		// TODO Auto-generated method stub
		return bp1.getRecorrido() - bp2.getRecorrido();
	}
	
	
	
}
