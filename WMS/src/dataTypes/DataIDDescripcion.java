package dataTypes;

public class DataIDDescripcion implements Comparable, Cloneable
{
	private int id,idB;
	private String descripcion,descripcionB,descripcionC;
	private boolean in;
	private Double idD;
	private Long idLong;
	
	
	public Long getIdLong() {
		return idLong;
	}
	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public DataIDDescripcion(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public DataIDDescripcion(String descripcion, String descripcionB) {
		this.descripcion = descripcion;
		this.descripcionB = descripcionB;
	}
	
	public DataIDDescripcion(Long id, String descripcion) {
		this.idLong = id;
		this.descripcion = descripcion;
	}
	
	public DataIDDescripcion(int id, int idB,String descripcion) {
		this.id = id;
		this.idB = idB;
		this.descripcion = descripcion;
	}
	
	public DataIDDescripcion(int id, String descripcion, int idB, String descripcionB, String descripcionC) {
		this.id = id;
		this.descripcion = descripcion;
		this.idB = idB;
		this.descripcionB = descripcionB;
		this.descripcionC = descripcionC;
	}
	
	public DataIDDescripcion(int id, String descripcion,String descripcionB ) 
	{
		this.id = id;
		this.descripcion = descripcion;
		this.descripcionB=descripcionB;
	}
	public DataIDDescripcion() {
	}
	
	public DataIDDescripcion(int id, String descripcion, Long idL, int idB ) 
	{
		this.id = id;
		this.descripcion = descripcion;
		this.idLong = idL;
		this.idB = idB;
	}
	
	
	@Override
	public int compareTo(Object o) 
	{
		  DataIDDescripcion dl = (DataIDDescripcion)o;        

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
	
	
	public Double getIdD() {
		return idD;
	}
	public void setIdD(Double idD) {
		this.idD = idD;
	}
	public String getDescripcionB() {
		return descripcionB;
	}
	public void setDescripcionB(String descripcionB) {
		this.descripcionB = descripcionB;
	}
	public boolean isIn() {
		return in;
	}
	public void setIn(boolean in) {
		this.in = in;
	}
	
	
	
	
	
	public int getIdB() {
		return idB;
	}
	public void setIdB(int idB) {
		this.idB = idB;
	}
	@Override
	public DataIDDescripcion clone() throws CloneNotSupportedException 
	{
		DataIDDescripcion clone = null;
	    try
	    {
	        clone = (DataIDDescripcion) super.clone();
	        clone.setDescripcionB(descripcionB);
	        clone.setIdB(idB);
	        clone.setId(id);
	        clone.setDescripcion(descripcion);
	        
	               
	    }
	    catch(CloneNotSupportedException e)
	    {
	        throw new RuntimeException(e); // won't happen
	    }
   
    return clone;
   
	}
	public String getDescripcionC() {
		return descripcionC;
	}
	public void setDescripcionC(String descripcionC) {
		this.descripcionC = descripcionC;
	}
	
	public DataIDDescripcion(int id, int idB, Long idLong, String descripcion, String descripcionB) {
		this.setId(id);
		this.setDescripcion(descripcion);
		this.setDescripcionB(descripcionB);
		this.setIdB(idB);
		this.setIdLong(idLong);
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

