package beans.encuentra;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;



@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrayOfArticulos {

	private Articulo[] articulos;

    public Articulo[] getArticulos ()
    {
        return articulos;
    }

    public void setArticulos (Articulo[] articulos)
    {
        this.articulos=articulos;
    }


	public ArrayOfArticulos desempaquetar(String json)
	{
		
		try 
		{
			
			ObjectMapper mapper = new ObjectMapper();
			ArrayOfArticulos obj = mapper.readValue(json, ArrayOfArticulos.class);
			return obj;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;	
		}
	}
	
	
	
	 
	
	

}
