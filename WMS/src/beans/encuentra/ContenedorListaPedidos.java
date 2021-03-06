package beans.encuentra;

import java.util.List;

import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.EncuentraPedido;
import jsonObjects.JSONArticulo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
			
			for (EncuentraPedido ep : datos) 
			{
				try
				{
					if(ep.getIdPedido()==null)
					{
						ep.setIdPedido(Long.parseLong(ep.getIdPedidoSTR()));
					}
				}
				catch (Exception e) 
				{
					
				}
				
			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
