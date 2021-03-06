package integraciones.erp.ascher;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

import beans.encuentra.EncuentraPedido;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContenedorListaPedidos 
{
	private int idTabla;
	private  List<EncuentraPedido> datos;
	public int getIdTabla() {
		return idTabla;
	}
	public void setIdTabla(int idTabla) {
		this.idTabla = idTabla;
	}
	public List<EncuentraPedido> getDatos() {
		return datos;
	}
	public void setDatos(List<EncuentraPedido> datos) {
		this.datos = datos;
	}
	public ContenedorListaPedidos(int idTabla, List<EncuentraPedido> datos) {
		
		this.idTabla = idTabla;
		this.datos = datos;
	}
	public ContenedorListaPedidos() 
	{
	
	}
	
	
	
	public ContenedorListaPedidos(String json)
	{
		try 
		{
			System.out.println(json);
			ObjectMapper mapper = new ObjectMapper();
			ContenedorListaPedidos obj = mapper.readValue(json, ContenedorListaPedidos.class);
			
			this.idTabla = obj.getIdTabla();
			this.datos = obj.getDatos();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
