package beans.encuentra;

import java.util.List;


import eCommerce_jsonObjectsII.Cliente;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContenedorListaClientes 
{
	private int idTabla;
	private  List<Cliente> datos;
	public int getIdTabla() {
		return idTabla;
	}
	public void setIdTabla(int idTabla) {
		this.idTabla = idTabla;
	}
	public List<Cliente> getDatos() {
		return datos;
	}
	public void setDatos(List<Cliente> datos) {
		this.datos = datos;
	}
	public ContenedorListaClientes(int idTabla, List<Cliente> datos) {
		
		this.idTabla = idTabla;
		this.datos = datos;
	}
	public ContenedorListaClientes() 
	{
	
	}
	
	
	
	public ContenedorListaClientes(String json)
	{
		try 
		{
			System.out.println(json);
			ObjectMapper mapper = new ObjectMapper();
			ContenedorListaClientes obj = mapper.readValue(json, ContenedorListaClientes.class);
			
			this.idTabla = obj.getIdTabla();
			this.datos = obj.getDatos();
			
			
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
