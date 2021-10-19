package beans.encuentra;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;



@JsonIgnoreProperties(ignoreUnknown = true)
public class MovimientosC {

	private List<Movimiento> movimientos;

	
	
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}



	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}



	public MovimientosC desempaquetar(String json)
	{
		
		try 
		{
			
			ObjectMapper mapper = new ObjectMapper();
			MovimientosC obj = mapper.readValue(json, MovimientosC.class);
			return obj;
			
		}
		catch (Exception e)
		{
			return null;	
		}
	}
	
	
	
	 
	
	

}
