package beans;

public class TalleMatrizRepo implements Comparable, Cloneable
{
	private String id, coordenadas, coordenadasD;
	private int stock,sugerido,pedido, origen, destino;
	private Double porcentaje;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getSugerido() {
		return sugerido;
	}
	public void setSugerido(int sugerido) {
		this.sugerido = sugerido;
	}
	public int getPedido() {
		return pedido;
	}
	public void setPedido(int pedido) {
		this.pedido = pedido;
	}
	
	public TalleMatrizRepo() {
	}

	@Override
	public int compareTo(Object o) 
	{
		TalleMatrizRepo dl = (TalleMatrizRepo)o;        

		  if(this.id.compareToIgnoreCase(dl.getId()) == 0) 
          { 
              return 0;
          } 
          else if(this.id.compareToIgnoreCase(dl.getId()) > 0)
          { 
          	
              return 1; 
          }
          else
          {
          	return -1;
          }
	        /*
	        	if(this.getId()==dl.getId())
	        	{
	        		if(this.descripcion.compareToIgnoreCase(dl.getDescripcion()) == 0) 
		            { 
		                return 0;
		            } 
		            else if(this.descripcion.compareToIgnoreCase(dl.getDescripcion()) > 0)
		            { 
		            	
		                return 1; 
		            }
		            else
		            {
		            	return -1;
		            }

	        	}
	        	
	        	else if(this.getId()>dl.getId())
	        	{
	        		return 1;
	        	}
	        	else
	        	{
	        		return -1;
	        	}
	        	*/	
	}
	public int getOrigen() {
		return origen;
	}
	public void setOrigen(int origen) {
		this.origen = origen;
	}
	public int getDestino() {
		return destino;
	}
	public void setDestino(int destino) {
		this.destino = destino;
	}
	
	@Override
	public TalleMatrizRepo clone() {
		TalleMatrizRepo clone = null;
        try{
            clone = (TalleMatrizRepo) super.clone();
           
        }catch(CloneNotSupportedException e){
            throw new RuntimeException(e); // won't happen
        }
       
        return clone;
       
    }
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(String coordenadas) {
		this.coordenadas = coordenadas;
	}
	public String getCoordenadasD() {
		return coordenadasD;
	}
	public void setCoordenadasD(String coordenadasD) {
		this.coordenadasD = coordenadasD;
	}
	
	
	
}
