package dataTypes;

import java.util.List;

public class DataArticuloTalle {
	private String articulo;
	private List<String>talles;
	
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public List<String> getTalles() {
		return talles;
	}
	public void setTalles(List<String> talles) {
		this.talles = talles;
	}
	
	@Override
	public boolean equals(Object other) {
		  return (this.articulo.equals(((DataArticuloTalle) other).getArticulo()));
		}
	
	
	

}
