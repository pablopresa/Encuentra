package dataTypes;

import beans.Fecha;

public class DataIDDescripcionDate implements Comparable, Cloneable
{
	private int id,idB;
	private String descripcion,descripcionB;
	private boolean in;
	private Double idD;
	private Fecha date;
	
	
	
	public Fecha getDate() {
		return date;
	}
	public void setDate(Fecha date) {
		this.date = date;
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
	public DataIDDescripcionDate(int id, Fecha dat) {
		this.id = id;
		this.date = dat;
		this.descripcion= dat.darFechaAnio_Mes_Dia(); 
	}
	
	public DataIDDescripcionDate(int id, int idB,String descripcion) {
		this.id = id;
		this.idB = idB;
		this.descripcion = descripcion;
	}
	
	public DataIDDescripcionDate(int id, String descripcion,String descripcionB ) 
	{
		this.id = id;
		this.descripcion = descripcion;
		this.descripcionB=descripcionB;
	}
	public DataIDDescripcionDate() {
	}
	
	
	@Override
	public int compareTo(Object o) 
	{
		  DataIDDescripcionDate dl = (DataIDDescripcionDate)o;        

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
	public DataIDDescripcionDate clone() throws CloneNotSupportedException 
	{
		DataIDDescripcionDate clone = null;
	    try
	    {
	        clone = (DataIDDescripcionDate) super.clone();
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
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

