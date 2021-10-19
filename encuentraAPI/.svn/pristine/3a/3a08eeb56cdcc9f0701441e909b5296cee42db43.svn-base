package beans.encuentra;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

import beans.datatypes.DTO_Articulo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContenedorListaDTO_Articulo {
	private int idTabla;
	private List<DTO_Articulo> datos;
	public int getIdTabla() {
		return idTabla;
	}
	public void setIdTabla(int idTabla) {
		this.idTabla = idTabla;
	}
	public List<DTO_Articulo> getDatos() {
		return datos;
	}
	public void setDatos(List<DTO_Articulo> datos) {
		this.datos = datos;
	}
	public ContenedorListaDTO_Articulo(int idTabla, List<DTO_Articulo> datos) {
		
		this.idTabla = idTabla;
		this.datos = datos;
	}
	public ContenedorListaDTO_Articulo() 
	{
	
	}
	
	
	
	public ContenedorListaDTO_Articulo(String json)
	{
		try 
		{
			System.out.println(json);
			ObjectMapper mapper = new ObjectMapper();
			ContenedorListaDTO_Articulo obj = mapper.readValue(json, ContenedorListaDTO_Articulo.class);
			
			this.idTabla = obj.getIdTabla();
			this.datos = obj.getDatos();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}