package beans.encuentra;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;



@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrayOfOrders {

	private Ordenes[] ordenes;

    public Ordenes[] getOrdenes ()
    {
        return ordenes;
    }

    public void setOrdenes (Ordenes[] ordenes)
    {
        this.ordenes = ordenes;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ordenes = "+ordenes+"]";
    }

	public ArrayOfOrders desempaquetar(String json)
	{
		
		try 
		{
			
			json = json.replaceAll("\"guia\":\"\"" , "\"guia\":null");
			System.out.println(json);
			ObjectMapper mapper = new ObjectMapper();
			ArrayOfOrders obj = mapper.readValue(json, ArrayOfOrders.class);
			return obj;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;	
		}
	}
	
	
	
	 
	
	

}
