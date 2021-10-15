package beans.encuentra;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;



@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrayOfMasters {

	private Maestros[] maestros;

    public Maestros[] getMaestros ()
    {
        return maestros;
    }

    public void setMaestros (Maestros[] maestros)
    {
        this.maestros = maestros;
    }

   	public ArrayOfMasters desempaquetar(String json)
	{
		
		try 
		{
			
			ObjectMapper mapper = new ObjectMapper();
			ArrayOfMasters obj = mapper.readValue(json, ArrayOfMasters.class);
			return obj;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;	
		}
	}
	
	
	
	 
	
	

}
