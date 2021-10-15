package dataTypes;

public class DataIDDescDescripcion implements Comparable
{
	private int id;
	private String desc;
	private String descripcion;
	private int porcentaje;
	private String descII;
	private String descripcionII;
	private int idII;
	private double porcentajeII; 
	private double total;
	private Long idLong;
	
	
	
	
	public Long getIdLong() {
		return idLong;
	}





	public void setIdLong(Long idLong) {
		this.idLong = idLong;
	}





	public double getTotal() {
		return total;
	}





	public void setTotal(double total) {
		this.total = total;
	}





	public double getPorcentajeII() {
		return porcentajeII;
	}





	public void setPorcentajeII(double porcentajeII) {
		this.porcentajeII = porcentajeII;
	}





	public int getIdII() {
		return idII;
	}





	public void setIdII(int idII) {
		this.idII = idII;
	}





	public DataIDDescDescripcion(int id, String desc, String descripcion) {
		this.id = id;
		this.desc = desc;
		this.descripcion = descripcion;
	}
	
	public DataIDDescDescripcion(Long id, String desc, String descripcion) {
		this.idLong = id;
		this.desc = desc;
		this.descripcion = descripcion;
	}





	public String getDescripcionII() {
		return descripcionII;
	}





	public void setDescripcionII(String descripcionII) {
		this.descripcionII = descripcionII;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getDesc() {
		return desc;
	}





	public void setDesc(String desc) {
		this.desc = desc;
	}





	public String getDescripcion() {
		return descripcion;
	}





	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}





	public String getDescII() {
		return descII;
	}





	public void setDescII(String descII) {
		this.descII = descII;
	}





	@Override
	public int compareTo(Object o) 
	{
		DataIDDescDescripcion dl = (DataIDDescDescripcion)o;        

	        
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





	public int getPorcentaje() {
		return porcentaje;
	}





	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}





	public DataIDDescDescripcion() {
	}
	
	public DataIDDescDescripcion Clonar(){
		
		DataIDDescDescripcion clon= new DataIDDescDescripcion(this.id,this.desc,this.descripcion);
		clon.descripcionII=this.descripcionII;
		clon.porcentaje=this.porcentaje;
		
		return clon;
		
	}
	
	

}
