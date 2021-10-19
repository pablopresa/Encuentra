package beans.datatypes;

public class DataDescDescripcion implements Comparable
{
	private String id;
	private String descripcion;
	private String fecha;
	private String fechaRec;
	private Double precio;
	private int cantDias;
	
	
	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public DataDescDescripcion(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public DataDescDescripcion(String id, String descripcion, String fecha) 
	{
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}
	
	public String getFechaRec() {
		return fechaRec;
	}
	public void setFechaRec(String fechaRec) {
		this.fechaRec = fechaRec;
	}
	
	
	
	public int getCantDias() {
		return cantDias;
	}
	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}
	public DataDescDescripcion() {
	}
	
	@Override
	public int compareTo(Object o) 
	{
		DataDescDescripcion dl = (DataDescDescripcion)o;        

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
	
	

}
