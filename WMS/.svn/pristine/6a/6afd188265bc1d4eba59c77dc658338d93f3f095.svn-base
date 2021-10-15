package dataTypes;

public class DataIDIDDescripcion implements Comparable
{
	private int id;
	private int iid;
	private String descripcion, descripcionB;
	private int idB;
	
	
	
	
	
	public int getIdB() {
		return idB;
	}




	public void setIdB(int idB) {
		this.idB = idB;
	}




	public String getDescripcionB() {
		return descripcionB;
	}




	public void setDescripcionB(String descripcionB) {
		this.descripcionB = descripcionB;
	}




	public DataIDIDDescripcion(int id, int iid, String descripcion) {
		this.id = id;
		this.iid = iid;
		this.descripcion = descripcion;
	}
	
	
	
	
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
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
	public DataIDIDDescripcion(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	public DataIDIDDescripcion() {
	}
	
	
	@Override
	public int compareTo(Object o) 
	{
		  DataIDDescripcion dl = (DataIDDescripcion)o;        

	        
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
	        		
	}
	
	
	
	

}
