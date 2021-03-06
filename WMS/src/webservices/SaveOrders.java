package webservices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.ArticuloLineaReposicion;
import beans.Usuario;
import beans.encuentra.ArticuloRepoFromLoad;
import beans.encuentra.ContenedorListaPedidos;
import beans.encuentra.Tarea;
import clientesVisual_Store.Std.clienteWSVS_new.Clientes;
import clientesVisual_Store.Std.clienteWSVS_new.OrdenVenta;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.EncuentraPedidoArticulo;
import logica.Logica;
import logica.Utilidades;
import main.utilidades.PonerEnReposicionArtElRey;




@Path("/SaveOrders")
public class SaveOrders 
{
	@POST
	@Path("/save")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String saveOrders(String data,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();
		
		Gson gson = new Gson();
		String json = "";
		
		ContenedorListaPedidos cont =  new ContenedorListaPedidos(data);
		List<EncuentraPedido> listaPedidos = cont.getDatos();
		
		Usuario u = l.loginEncuentraAPI2(a);
		List<DataIDDescripcion> retorno = new ArrayList<>();
		int idEmpresa = u.getIdEmpresa();
		
		for (EncuentraPedido p : listaPedidos) 
		{
			if(p.getIdPedido() == new Long("75815")){
				System.out.println(p.getIdPedido());
			}
			p.setSubpedido(p.getIdPedido()+"");
			p.setIdFenicio(p.getIdEcommerce());
			DataIDDescripcion d = new DataIDDescripcion(0, p.getIdPedido()+"");
			d.setDescripcion(p.getIdPedido()+"");
			
			if(p.save(idEmpresa))
			{
				try
				{
					Clientes clien = p.getCliente().transformar(p.getCliente(), idEmpresa);
					 
					 clien.save(idEmpresa,p.getIdPedido());
				}
				catch (Exception e) 
				{
					//e.printStackTrace();
				}
				
				l.logPedido(p.getIdPedido(), 0, 0, "Pedido Sincronizado ",0,idEmpresa);
				d.setDescripcionB("OK");
				try {
					p.getOrden().save(p.getUrlEtiqueta(), idEmpresa);
				} catch (Exception e) {
					System.out.println("ECOMMERCE SIN ORDEN");
				}
			}
			else
			{
				d.setDescripcionB("FAIL");
			}
					
			retorno.add(d);
			
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
	
		@POST
		@Path("/saveArtReq")

		//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		//public String createUser(@FormParam("name") String name) throws IOException
		public String saveArtReq(String data,@QueryParam ("token") String a,@QueryParam ("idCanal") String idCanal) throws IOException
		{	   
			Logica l = new Logica();
			Gson gson = new Gson();
			String json = "";
				
			ContenedorListaPedidos cont =  new ContenedorListaPedidos(data);
			List<EncuentraPedido> listaPedidos = cont.getDatos();
			
			Usuario u = l.loginEncuentraAPI2(a);
			List<DataIDDescripcion> retorno = new ArrayList<>();
			int idEmpresa = u.getIdEmpresa();
			
			
			int id_de_Deposito_Principal = l.darDepositoCentralCanal(idEmpresa, Integer.parseInt(idCanal));
			int id_de_Deposito_Ecommerce  = l.darDepositoEcommerceCanal(idEmpresa, Integer.parseInt(idCanal));
			
			
			String q = "";
			Hashtable<Integer, List<DataIDDescripcion>> pedidoCentral = new Hashtable<Integer, List<DataIDDescripcion>>();
			
			
			
			for (EncuentraPedido p : listaPedidos) 
			{
				p.setIdFenicio(p.getIdEcommerce());
				
				
				DataIDDescripcion d = new DataIDDescripcion(0, p.getIdPedido()+"");
				d.setDescripcion(p.getIdPedido()+"");
				
				
				try
				{
					DataIDDescripcion did;
					for (EncuentraPedidoArticulo it : p.getArticulosPedido())
					{
						if(it.getVariacion()==null)
						{
							it.setVariacion("");
						}
					
						did = new DataIDDescripcion(it.getCantidad(),it.getArticulo());
						did.setIdLong(p.getIdPedido());
						did.setDescripcionB(it.getVariacion());
						if(it.getVariacion()==null) {
							did.setDescripcionB("");
						}
						did.setIdB(it.getDistribucionAfecta());
						
						
						int clickAndC = 0;
						if(it.isClickCollect())
						{
							clickAndC = 1; //picking para entregar en tienda
						}
						else
						{
							List<DataIDDescripcion> lista = null;
							if(pedidoCentral.get(it.getOrigen())==null)
							{
								lista = new ArrayList<>();
							}
							else
							{
								lista =pedidoCentral.get(it.getOrigen()); 
							}
							lista.add(did);
							pedidoCentral.put(it.getOrigen(),lista);
						}
						
						boolean req = true;
						req = l.persistir("INSERT INTO `ecommerce_pedido_articulos_req` (`idPedido`, `idArticulo`, `Deposito`, `CantidadRequerida`,"+
								" `CantidadProcesasa`, `Confirmado`,cantConfirmada,docVisual, idEmpresa,procesarEnPickup) " +
								" VALUES ('"+p.getIdPedido()+"', '"+it.getArticulo()+"', '"+it.getOrigen()+"', '"+it.getCantidad()+"', '0', '0',0,"+it.getDistribucionAfecta()+","+idEmpresa+","+clickAndC+") "
										+ "ON duplicate key update CantidadRequerida = CantidadRequerida + "+it.getCantidad()+";");
						if (req) 
						{
							l.logPedido(p.getIdPedido(), 0, 0, " Guardando pedido de articulo "+it.getArticulo()+
									" a deposito "+it.getOrigen(),0,idEmpresa);			
						}
						else 
						{
							d.setDescripcionB("FAIL");
						}
					}
					
				}
				catch (Exception e) 
				{
					System.out.println("error");
					d.setDescripcionB("FAIL");
				}
						
				retorno.add(d);
			}
			
			
			Set<Integer> keys = pedidoCentral.keySet();
			boolean manual = false;
			Utilidades util = new Utilidades();
			
	        for(Integer deposito: keys)
	        {
	        	
	            l.darArticuloRepoFromLoadForus(pedidoCentral.get(deposito),id_de_Deposito_Ecommerce,manual,idEmpresa,deposito,2,false);
	            int idSinc = l.darNextSincRepo(idEmpresa)-1;
	            
	            
	            if(deposito!=id_de_Deposito_Principal)
	            {
	            	List<ArticuloLineaReposicion> articulosIn = new ArrayList<>();
		            
		            for (DataIDDescripcion d : pedidoCentral.get(deposito)) 
		            {
		            	ArticuloLineaReposicion dl = new ArticuloLineaReposicion(d.getId(), d.getDescripcion(), "Venta WEB: "+d.getIdLong(), new DataIDDescripcion(idSinc, ""), new DataIDDescripcion(deposito,""), new DataIDDescripcion(id_de_Deposito_Ecommerce, ""));
		            	dl.setEcommerce(true);
		            	dl.setPedido(d.getIdLong());
		            	dl.setPosClasif("0");
		            	dl.setSolicitud(d.getIdB());
		            	articulosIn.add(dl);
					}
		            
//		            Boolean paramRemision = util.darParametroEmpresaBool(idEmpresa, 22);
//		            Boolean paramPickingBulto = util.darParametroEmpresaBool(idEmpresa, 27);
//		            int paramBultoCantMinxSku = util.darParametroEmpresaINT(idEmpresa, 28)+1;
//		            Boolean paramPickingBultosGenerales = util.darParametroEmpresaBool(idEmpresa, 37);
//		            Boolean paramPickingArticulosAlmacen = util.darParametroEmpresaBool(idEmpresa, 38);
//		            Boolean paramPrioridadBulto = util.darParametroEmpresaBool(idEmpresa, 42);
		            
		            int cantidades=0;
		            for(ArticuloLineaReposicion ae:articulosIn)
		            {				
		            		cantidades+= ae.getCantidad();
		            }

		            List<Usuario> operariosSelect = new ArrayList<>();
		            Usuario us = new Usuario();
		            us.setNumero(0);
		            us.setNick("Integraciones");
		            operariosSelect.add(us);
		            				
		            Tarea tarea = util.TaskInit(u, operariosSelect);	
		            					
		            String destinos = deposito+"";
		            String observacion = destinos+" "+u.getNick();
		            						
		            int idPicking = l.encuentraAltaPicking(articulosIn, cantidades, "Sin filtros aplicados",idEmpresa,true,false);						
		            tarea = util.CompleteTask(tarea, idPicking, observacion, articulosIn, cantidades);	
		            tarea.setIdDeposito(deposito);
		            
		            HttpSession session = util.darDummySesion("SINCRO");
		            
		            util.guardarTarea(tarea, u, idEmpresa,session, l, idPicking,false, 10000,false, false, false,false); //Cambiar booleano 29/1
	            }
	            
	        }
			
			json = gson.toJson(retorno);
			
			String padre = "{ \"idTabla\":0,\"datos\": ";
			return padre+json+"}";
		}
		
		
		@POST
		@Path("/saveDestino")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		//public String createUser(@FormParam("name") String name) throws IOException

		public String saveDestino(String data,
									@QueryParam ("token") String a,
									@QueryParam ("destino") String destino,
									@QueryParam ("costoEnvio") String costoEnvio) throws IOException
		{	   
			Logica l = new Logica();
			Usuario u = l.loginEncuentraAPI2(a);
			
			Gson gson = new Gson();
			
			
			EncuentraPedido p = gson.fromJson(data, EncuentraPedido.class);
			boolean freshipping = p.isFreshipping();
			String json = "";
			
			List<DataIDDescripcion> retorno = new ArrayList<>();
			int idEmpresa = u.getIdEmpresa();
			DataIDDescripcion d = new DataIDDescripcion(0,"Ok");
			boolean actualiza = p.updateDestino(Integer.parseInt(destino),p.getTrackingNumber(), idEmpresa,Double.parseDouble(costoEnvio),freshipping);
			p.updateShippingType(idEmpresa);
			if(!actualiza)
			{
				p.updateDestino(0,"", idEmpresa,Double.parseDouble(costoEnvio),freshipping);
				
			}
			
			retorno.add(d);
			json = gson.toJson(retorno);
			
			
			
			String padre = "{ \"idTabla\":0,\"datos\": ";
			return padre+json+"}";
		 }
	
		@POST
		@Path("/updateLabel")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		//public String createUser(@FormParam("name") String name) throws IOException

		public String updateLabel(String data,@QueryParam ("token") String a) throws IOException
		{	   
			Logica l = new Logica();
			Usuario u = l.loginEncuentraAPI2(a);
			
			Gson gson = new Gson();
			
			
			EncuentraPedido p = gson.fromJson(data, EncuentraPedido.class);
			boolean freshipping = p.isFreshipping();
			String json = "";
			
			DataIDDescripcion retorno = new DataIDDescripcion();
			
			retorno.setId(0);
			retorno.setDescripcion("");
			
			p.updateEtiqueta(0, u.getIdEmpresa());
			
			json = gson.toJson(retorno);
			
			return json;
		 }
		
		
		@POST
		@Path("/updateLabels")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		//public String createUser(@FormParam("name") String name) throws IOException

		public String updateLabels(String data,@QueryParam ("token") String a) throws IOException
		{	   
			Logica l = new Logica();
			Usuario u = l.loginEncuentraAPI2(a);
			
			Gson gson = new Gson();
			
			List<EncuentraPedido> datos = gson.fromJson(data, new TypeToken<List<EncuentraPedido>>(){}.getType());
			
			for (EncuentraPedido p : datos) 
			{
				p.updateEtiqueta(0, u.getIdEmpresa());
			}
						
			String json = "";
			DataIDDescripcion retorno = new DataIDDescripcion();
			retorno.setId(0);
			retorno.setDescripcion("");
			json = gson.toJson(retorno);
			
			return json;
		 }
		
		@POST
		@Path("/savePickingOrder")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		//public String createUser(@FormParam("name") String name) throws IOException

		public String savePickingOrder(String data,	@QueryParam ("token") String a) throws IOException
		{	   
			Logica l = new Logica();
			Usuario u = l.loginEncuentraAPI2(a);
			
			Gson gson = new Gson();
			
			List <ArticuloRepoFromLoad> articulos = gson.fromJson(data, new TypeToken<List<ArticuloRepoFromLoad>>(){}.getType());
			
			String json = "";
			
			List<DataIDDescripcion> retorno = new ArrayList<>();
			int idEmpresa = u.getIdEmpresa();
			
			
			/*****************guardo los picking***********/
			
			Map<Integer,Integer> ordenesHTIn = new Hashtable<>();
			for (ArticuloRepoFromLoad ar : articulos) 
			{
				l.darArticuloRepoFromLoadForus(ar.getArticulosCantidad(), ar.getSucursal(), ar.getManual(), ar.getIdE(), ar.getOrigen(), 
						ar.getTipo(), false);
				
				
				for (DataIDDescripcion ac : ar.getArticulosCantidad()) //los articulos de las ordenes
				{
					ordenesHTIn.put(ac.getIdB(), ac.getIdB());
				}
				
			}
			
			/*****************guardo los enviados por idPedido en un hash***********/
			
			Map<Integer, List<DataIDDescripcion>> enviadosXIddePedido = new Hashtable<>();
			for (ArticuloRepoFromLoad ar : articulos) //las listas de ordenes
			{
				for (DataIDDescripcion s : ar.getArticulosCantidad()) //los articulos de las ordenes
				{
					if(enviadosXIddePedido.containsKey(s.getIdB()))
					{
						enviadosXIddePedido.get(s.getIdB()).add(s);
					}
					else
					{
						List<DataIDDescripcion> toIn = new ArrayList<>();
						toIn.add(s);
						enviadosXIddePedido.put(s.getIdB(), toIn);
					}
				}
			}

			/*****************consulto los ingresados para todos los id de pedidos***********/
			List<Integer> ordersIn = new ArrayList<>(ordenesHTIn.values());
			
			List<DataIDDescripcion> sincronizados = l.darListaPickingsSincronizados(ordersIn, idEmpresa);
			
			Map<Integer, List<DataIDDescripcion>> sincronizadosXIddePedido = new Hashtable<>();
			for (DataIDDescripcion s : sincronizados) 
			{
				if(sincronizadosXIddePedido.containsKey(s.getId()))
				{
					sincronizadosXIddePedido.get(s.getId()).add(s);
				}
				else
				{
					List<DataIDDescripcion> toIn = new ArrayList<>();
					toIn.add(s);
					sincronizadosXIddePedido.put(s.getId(), toIn);
				}
				
			}

			/*****************comparo las listas***********/
			
			List<List<DataIDDescripcion>> listasPickXPedido = new ArrayList<>(enviadosXIddePedido.values());
			for (List<DataIDDescripcion> list : listasPickXPedido) 
			{//por cada pedido que envie
				
				int idPedido = list.get(0).getIdB();
				DataIDDescripcion statusPedido = new DataIDDescripcion(idPedido, "OK");
				
				
				List<DataIDDescripcion> listaSincronizada = sincronizadosXIddePedido.get(idPedido);
				
				if(listaSincronizada!=null && !listaSincronizada.isEmpty())
				{
					boolean todosOK = true;
					for (DataIDDescripcion enviado : list) 
					{
						boolean found = false;
						for (DataIDDescripcion  sincronizado: listaSincronizada) 
						{
							if(sincronizado.getDescripcion().equals(enviado.getDescripcion()))
							{
								found = true;
								break;
							}
						}
						//si despues de recorrer found sigue falso, ese articulo no quedo
						if(!found)
						{
							todosOK=false;
							break;
						}
					}
					if(!todosOK)
					{
						statusPedido.setDescripcion("FAIL");
					}
					
				}
				else
				{
					statusPedido.setDescripcion("FAIL");
				}
				
				
				retorno.add(statusPedido);
			}
			

			json = gson.toJson(retorno);
			
			
			
			String padre = "{ \"idTabla\":0,\"datos\": ";
			return padre+json+"}";
		 }
	
	@POST
	@Path("/verificarRepoArticulos")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public void verificarRepoArticulos(@QueryParam ("token") String token) throws IOException
	{	   
		Logica l = new Logica();
		try
		{		
			Usuario u = l.loginEncuentraAPI2(token);
			
			if(u!=null && u.getIdEmpresa()!=0) {
				int idEmpresa = u.getIdEmpresa();						
				
				PonerEnReposicionArtElRey repos = new PonerEnReposicionArtElRey();
				repos.main(null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@POST
	@Path("/saveSaleOrder")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String saveSaleOrders(String data,@QueryParam ("token") String a) throws IOException
	{	   
		Logica l = new Logica();
		
		Gson gson = new Gson();
		String json = "";
		
		List <OrdenVenta> listaPedidos = gson.fromJson(data, new TypeToken<List<OrdenVenta>>(){}.getType());
		
		Usuario u = l.loginEncuentraAPI2(a);
		List<DataIDDescripcion> retorno = new ArrayList<>();
		int idEmpresa = u.getIdEmpresa();
		
		for (OrdenVenta p : listaPedidos) 
		{
			
			DataIDDescripcion d = new DataIDDescripcion(0, p.getIdCarrito()+"");
			
			if(p.save("", idEmpresa))
			{
				d.setDescripcionB("OK");
					
			}
			else
			{
				d.setDescripcionB("FAIL");
			}
					
			retorno.add(d);
			
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
