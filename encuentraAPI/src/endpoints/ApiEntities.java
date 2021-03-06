package endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import logica.LogicaAPI;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

import beans.Usuario;
import beans.api.ColeccionMovStock;
import beans.api.GrabarRecepcion;
import beans.api.MovStock;

import beans.api.ResponseOrder;
import beans.api.ResponseOrderFunctions;
import beans.api.articuloBarra;
import beans.api.json.RetornoPedido;
import beans.datatypes.DataIDDescripcion;
import integraciones.erp.visualStore.elrey.ecommerce.ClienteWS;
import integraciones.erp.visualStore.forus.central.ClienteWSVisualForus;
import integraciones.erp.visualStore.forus.ecommerce.ClienteWSVisualForus1200;
import integraciones.erp.visualStore.stadium.v1.WSCommunicate;
import integraciones.wms.Call_WS_APIENCUENTRA;

@Path("/ApiEntities")
public class ApiEntities 
{	
	@GET
	@Path("/queueStockMovement")

	
	
	@Produces(MediaType.APPLICATION_JSON)
	public String movStock(String data,@QueryParam ("token") String token, @QueryParam ("idMov") String idMov)
	{
	      //System.out.println(data);
		
		String json = "";
		Gson gson = new Gson();

		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		ColeccionMovStock movements = new ColeccionMovStock();
		
		if (u.getNick() != null) 
		{
			int idEmpresa = u.getIdEmpresa();	
			int id = 0;
			LogicaAPI l = new LogicaAPI();			
			try {
				if(idMov!=null) {
					id = Integer.parseInt(idMov);
				}				
				List<MovStock> lista = l.queueMovsStock(idEmpresa,id);
				movements.setColeccion(lista);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}	
		
		json = gson.toJson(movements);      
		
		return json;
	   }	

}
