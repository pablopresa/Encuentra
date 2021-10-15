package jsonObjects;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONDetalleOC 
{
	private String descripcion,	cantidad,unidades,	artCodExterno,cantidadRecibida;
	private String [] codigosBarras;
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	public String getArtCodExterno() {
		return artCodExterno;
	}

	public void setArtCodExterno(String artCodExterno) {
		this.artCodExterno = artCodExterno;
	}

	

	public String[] getCodigosBarras() {
		return codigosBarras;
	}

	public void setCodigosBarras(String[] codigosBarras) {
		this.codigosBarras = codigosBarras;
	}

	
	
	
	public String getCantidadRecibida() {
		return cantidadRecibida;
	}

	public void setCantidadRecibida(String cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}

	public JSONDetalleOC(String descripcion, String cantidad, String unidades,
			String artCodExterno, String[] codigosBarras, String cantidadRecibida) 
	{
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.unidades = unidades;
		this.artCodExterno = artCodExterno;
		this.codigosBarras = codigosBarras;
		this.cantidadRecibida = cantidadRecibida;
	}
	
	public JSONDetalleOC() 
	{
	
	}

	

}
