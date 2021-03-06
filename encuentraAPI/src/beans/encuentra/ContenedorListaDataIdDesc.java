package beans.encuentra;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

import beans.datatypes.DataIDDescripcion;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContenedorListaDataIdDesc 
{
	private int idTabla;
	private List<DataIDDescripcion> datos;
	public int getIdTabla() {
		return idTabla;
	}
	public void setIdTabla(int idTabla) {
		this.idTabla = idTabla;
	}
	public List<DataIDDescripcion> getDatos() {
		return datos;
	}
	public void setDatos(List<DataIDDescripcion> datos) {
		this.datos = datos;
	}
	public ContenedorListaDataIdDesc(int idTabla, List<DataIDDescripcion> datos) {
		
		this.idTabla = idTabla;
		this.datos = datos;
	}
	public ContenedorListaDataIdDesc() 
	{
	
	}
	
	
	
	public ContenedorListaDataIdDesc(String json)
	{
		try 
		{
			System.out.println(json);
			ObjectMapper mapper = new ObjectMapper();
			ContenedorListaDataIdDesc obj = mapper.readValue(json, ContenedorListaDataIdDesc.class);
			
			this.idTabla = obj.getIdTabla();
			this.datos = obj.getDatos();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
