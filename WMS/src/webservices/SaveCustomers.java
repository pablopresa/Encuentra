package webservices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;

import beans.Usuario;
import beans.encuentra.ContenedorListaClientes;
import beans.encuentra.ContenedorListaPedidos;
import beans.encuentra.IPrint;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.EncuentraPedidoArticulo;
import eCommerce_jsonObjectsII.Items;
import logica.Logica;
import logica.Utilidades;
import persistencia._EncuentraPersistir;




@Path("/SaveCustomers")
public class SaveCustomers 
{
	@POST
	@Path("/save")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String SaveCustomers(String data,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();
		
		Gson gson = new Gson();
		String json = "";
		
		
		
		ContenedorListaClientes cont =  new ContenedorListaClientes(data);
		List<Cliente> listaClientes = cont.getDatos();
		
		Usuario u = l.loginEncuentraAPI2(a);
		List<DataIDDescripcion> retorno = new ArrayList<>();
		int idEmpresa = u.getIdEmpresa();
		
		
		StringBuilder sb = new StringBuilder();
		for (Cliente p : listaClientes) 
		{
			
			DataIDDescripcion d = new DataIDDescripcion(0, p.getDocumentoNro()+"");
			
			
			sb.append(p.query_save(idEmpresa));
						
			retorno.add(d);
			
		}
		
		_EncuentraPersistir ep = new _EncuentraPersistir();
		try 
		{
			ep.persistirBatch(sb.toString());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		json = gson.toJson(retorno);
		
		
		try
		{
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		String padre = "{ \"idTabla\":0,\"datos\": ";
		return padre+json+"}";
	   }	

}
