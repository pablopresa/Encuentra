package dataTypes;

import java.util.List;

public class DataArticuloCatalogo implements Comparable
{
	private String articulo;
	private String color;
	private String precioCosto;
	private String precioVenta;
	private String foto;
	private String curva;
	private int idmarca;
	private boolean curvaExtra;
	
	private List<DataCurvaCatalogo> curvas;
	
	private int seccion;
	private int clase;
	private int grupo;
	private int posi;
	
	
	public List<DataCurvaCatalogo> getCurvas() {
		return curvas;
	}
	public void setCurvas(List<DataCurvaCatalogo> curvas) {
		this.curvas = curvas;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrecioCosto() {
		return precioCosto;
	}
	public void setPrecioCosto(String precioCosto) {
		this.precioCosto = precioCosto;
	}
	public String getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(String precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getCurva() {
		return curva;
	}
	public void setCurva(String curva) {
		this.curva = curva;
	}
	public DataArticuloCatalogo() {
	}
	public int getSeccion() {
		return seccion;
	}
	public void setSeccion(int seccion) {
		this.seccion = seccion;
	}
	public int getClase() {
		return clase;
	}
	public void setClase(int clase) {
		this.clase = clase;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	
	public int getPosi() {
		return posi;
	}
	public void setPosi(int posi) {
		this.posi = posi;
	}
	@Override
	public int compareTo(Object o) 
	{
		DataArticuloCatalogo dac = (DataArticuloCatalogo)o;        
		//if(this.foto.compareToIgnoreCase(dac.getFoto()) == 0)//borrar por favor
		if(true)
		{
			if(this.seccion==dac.getSeccion())
		    {
				if(this.clase==dac.getClase()) 
			    { 
					if(this.clase==dac.getGrupo())
		        	{
						return 0;
		        	}
		        	else if (this.grupo>dac.getGrupo())
		        	{
		        		return 1;
		        	}
		        	else
		        	{
		        		return -1;
		        	}
			    } 
			    else if(this.clase > dac.getClase())
			    {
			    	return 1;
			    }
			    else
			    {
			    	return -1;
			    }
		    }
		    else if(this.seccion > dac.getSeccion())
		    {
		    	return 1;
		    }
		    else
		    {
		    	return -1;
		    }
			
		}
		else if(this.foto.compareToIgnoreCase(dac.getFoto()) > 0)
		{
			return 1;
		}
		else
		{
			return -1;
		}	
		
		
		
	}
	public boolean isCurvaExtra() {
		return curvaExtra;
	}
	public void setCurvaExtra(boolean curvaExtra) {
		this.curvaExtra = curvaExtra;
	}
	public int getIdmarca() {
		return idmarca;
	}
	public void setIdmarca(int idmarca) {
		this.idmarca = idmarca;
	}
	
}
