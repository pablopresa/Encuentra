package endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Usuario;
import beans.api.GrabarRecepcion;
import beans.api.MovStock;
import beans.api.articuloBarra;
import beans.api.wsResponse;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.datatypes.Remito;
import beans.datatypes.RemitoLinea;
import integraciones.erp.odoo.laIsla.ClienteOdoo_LaIsla;
import integraciones.erp.odoo.laIsla.EstadoAlbaranes;
import integraciones.erp.odoo.laIsla.LaIsla;
import integraciones.erp.odoo.laIsla.PedidoAlbaran;
import integraciones.erp.odoo.laIsla.SincroLaIsla;
import integraciones.erp.visualStore.elrey.ecommerce.ClienteWS;
import integraciones.erp.visualStore.forus.central.ClienteWSVisualForus;
import integraciones.erp.visualStore.forus.ecommerce.ClienteWSVisualForus1200;
import integraciones.erp.visualStore.stadium.v1.WSCommunicate;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;
import logica.Util;

@Path("/ERPintegrations")
public class ERPintegrations 
{	
		
	@POST
	@Path("/stockMovement")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String movStock(String data,@QueryParam ("token") String token, @QueryParam ("resend") String resend) throws IOException
	{
	      //System.out.println(data);
		
		String json = "";
		Gson gson = new Gson();
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		DataIDDescripcion erp = null;
		Boolean reEnvio = false;
		try {
			reEnvio = Boolean.parseBoolean(resend);
		} catch (Exception e) {}
		
		if (u.getNick() == null) 
		{
			erp = new DataIDDescripcion(0,"");
		}
		else {
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);
			MovStock m = new MovStock();
			m = gson.fromJson(data, MovStock.class);
			
			LogicaAPI l = new LogicaAPI();
			int idMov = 0;			
			int idDoc = 0;
			
			try {
				if(!reEnvio) {
					idMov = l.RegistrarMovimientoStock(m.getOrigen(), m.getDestino(), m.getIdUsuario(), m.getDetalle(), idEmpresa, 
							m.getOrigenDoc(), m.getDocSolicitud(), m.getRazon(), m.isEntrega());
					m.setId(idMov);
				}				
				
				switch (idEmpresa) {
				case 1:		//STADIUM
					WSCommunicate cl = new WSCommunicate();
					int tienda = m.getOrigen();
					int idEquipo = 2000+tienda;
					if(tienda==99)
					{
						erp= cl.GrabarTransferencia(tienda, m.getDestino(), m.getDetalle(), "Generado por encuentra", (short)99, (short)1, 
								(short)99, Long.parseLong(m.getIdUsuario()+""), false,  "Generado por encuentra", m.getRazon());
						idDoc = erp.getId();
					}
					else
					{
						erp= cl.GrabarTransferenciaTienda(tienda, m.getDestino(), m.getDetalle(), "Generado por encuentra", (short)tienda, 
								(short)idEquipo, (short)tienda, Long.parseLong(m.getIdUsuario()+""), false,  "Generado por encuentra", m.getRazon());
						idDoc = erp.getId();
					}
					break;
					
					
				case 2:		//FORUS
					Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
					Map<String, Integer> stocks = api.vistaStocks(m.getDetalle(), m.getOrigen(), idEmpresa);
					ClienteWSVisualForus ws = new ClienteWSVisualForus();
					if(m.getDocSolicitud()==0) {	//remito						
						erp = ws.GrabarTransferencia(m.getOrigen(), m.getDestino(), m.getRazon(), m.getDetalle(),
								"Transferencia realizada por "+m.getUsuario()+" desde encuentra", stocks);
						idDoc = erp.getId();
					}
					else {	//afectacion de orden
						if(m.isEntrega()) {
							erp = ws.EntregaPreparar(m.getDocSolicitud(), m.getDetalle(),m.getDestino(), stocks);	//MAYORISTA
							idDoc = m.getDocSolicitud();
						}
						else {
							erp = ws.EntregaPrepararYTerminar(m.getDocSolicitud(), m.getDetalle(),m.getDestino(), stocks);	//ECOMMERCE
							idDoc = m.getDocSolicitud();
						}
						
					}
					break;	
					
					
				case 4:		//EL REY DEL ENTRETENIMIENTO
					ClienteWS wsEC = new ClienteWS();
					erp = wsEC.GrabarTransferencia(m.getOrigen(), m.getDestino(), m.getRazon(), m.getDetalle(),
							"Transferencia realizada desde encuentra");
					idDoc = erp.getId();
					
					break;	
					
					
				case 5:		//PEPE GANGA
					break;
					

				case 6:		//LaIsla
					/*ClienteOdoo_LaIsla cli = new ClienteOdoo_LaIsla(true);
					List<Integer> idsAlbaranes_ATransferir = gson.fromJson(data, new TypeToken<List<Integer>>(){}.getType());
					for (Integer id : idsAlbaranes_ATransferir) {
						cli.transferirPicking(id);
					}*/
					/*LaIsla li = new LaIsla();
					m.setDestino(158);
					m.setEntrega(true);
					erp = li.moverAlbaran(m);*/
					erp = new DataIDDescripcion(1,"");
					break;
				default:
					break;
				}
				
				//REGISTRO LA SALIDA DEL MOVIMIENTO DE STOCK
				if(idEmpresa!=6) {
					if(erp.getId()==0){
						l.RegistrarDocMovimientoStock(0, m.getId(), idDoc, erp.getDescripcion(), 
								idEmpresa,m.getIdUsuario(),erp.getIdB());
					}
					else {
						l.RegistrarDocMovimientoStock(1, m.getId(), idDoc, erp.getDescripcion(),
								idEmpresa,m.getIdUsuario(),erp.getIdB());
					}
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}					
			
		}
		
		json = gson.toJson(erp);      
		
		return json;
	   }	

	@POST
	@Path("/confirmTransf")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String confirmTransf(String data,@QueryParam ("token") String token) throws IOException
	{
	      //System.out.println(data);
		
		String json = "";
		Gson gson = new Gson();
		
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		boolean grabo = false;
		
		if (u.getNick() == null) 
		{
			
		}
		else {
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);
			MovStock m = new MovStock();
			m = gson.fromJson(data, MovStock.class);
			
			LogicaAPI l = new LogicaAPI();
			int idMov = 0;
			
			try {
				idMov = l.RegistrarConfirmacionTransferencia(m.getOrigen(), m.getDestino(), m.getIdUsuario(), m.getDetalle(), idEmpresa, 
						m.getOrigenDoc(), m.getDocSolicitud(), m.getRazon(),m.getDoc(), false);
				
				
				switch (idEmpresa) {
				case 1:		//STADIUM
					
					break;
					
					
				case 2:		//FORUS
					short idTienda = 1;
					
					if(m.getDestino()==1200)
					{
						idTienda=1200;
						ClienteWSVisualForus1200 vs = new ClienteWSVisualForus1200();
						grabo = vs.ConfirmarTransferencia(m.getOrigen(), m.getDestino(), m.getDoc(), m.getDetalle(), 
								"Confirmacion desde encuentra por "+m.getUsuario(),idTienda);
					}
					else
					{
						ClienteWSVisualForus vs = new ClienteWSVisualForus();
						grabo = vs.ConfirmarTransferencia(m.getOrigen(), m.getDestino(), m.getDoc(), m.getDetalle(), 
								"Confirmacion desde encuentra por "+m.getUsuario(),idTienda);
					}
					break;	
					
					
				case 4:		//EL REY DEL ENTRETENIMIENTO
					
					
					break;	
					
					
				case 5:		//PEPE GANGA
					break;
					
				case 6:		//LA ISLA
					LaIsla li = new LaIsla();
					LogicaAPI lo = new LogicaAPI();
					MovStock ms = gson.fromJson(data, MovStock.class);
					if (ms != null) {
						int idRecibido = li.recibirAlbaran(ms.getDoc(), true);
						//lo pongo en confirm con estado
						if(idRecibido >0) {
							EstadoAlbaranes estadoREC = li.verEstadoAlbaranesFiltrados(3, idRecibido);
							if(estadoREC == null) {
								lo.RegistrarEstadoConfirmacionTransferencia(false, idMov, "Fallo al intentar transferir a Encuentra", idEmpresa, u.getNumero(), 1);
								grabo = false;
							} else {
								lo.RegistrarEstadoConfirmacionTransferencia(true, idMov, "OK", idEmpresa, u.getNumero(), 1);
								grabo = true;
							}
						}
						else {
							lo.RegistrarEstadoConfirmacionTransferencia(false, idMov, "Fallo al intentar transferir a Encuentra", idEmpresa, u.getNumero(), 1);
							grabo = false;
						}
					}
					break;
					
				default:
					break;
				}
				
				//REGISTRO LA SALIDA DEL MOVIMIENTO DE STOCK
				//l.RegistrarEstadoConfirmacionTransferencia(grabo, idMov, "", idEmpresa,m.getIdUsuario(),1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}					
			
		}
		
		json = gson.toJson(grabo);      
		
		return json;
	   }	
	
	@POST
	@Path("/grabarRecepcion")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String grabarRecepcion(String data,@QueryParam ("token") String token) throws IOException
	{
	      //System.out.println(data);
		
		String json = "";
		Gson gson = new Gson();
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		boolean grabo = false;
		
		if (u.getNick() == null) 
		{
			
		}
		else {
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);
			GrabarRecepcion r = new GrabarRecepcion();
			r = gson.fromJson(data, GrabarRecepcion.class);
			
			LogicaAPI l = new LogicaAPI();
			int idReg = 0;
			
			try {
				idReg = l.RegistrarRecepcion(r);				
				
				switch (idEmpresa) {
				case 1:		//STADIUM
					
					break;
					
					
				case 2:		//FORUS
					ClienteWSVisualForus ws=new ClienteWSVisualForus();
					grabo = ws.GrabarRecepcion(r.getDetalle(),r.getTipoAfecta(),r.getObservacion(), r.getNumeroDoc(), 
							r.getNroProveedor(), r.getSerieRemito(),r.getNroRemito());
					
					break;	
					
					
				case 4:		//EL REY DEL ENTRETENIMIENTO
					
					
					break;	
					
					
				case 5:		//PEPE GANGA
					break;
					
					
				default:
					break;
				}
				
				//REGISTRO LA SALIDA DEL MOVIMIENTO DE STOCK
				l.RegistrarEstadoConfirmacionTransferencia(grabo, idReg, "", idEmpresa,r.getIdUsuario(),1);
				
			} catch (Exception e) {
				e.printStackTrace();
			}					
			
		}
		
		json = gson.toJson(grabo);      
		
		return json;
	   }
	
	@POST
	@Path("/grabarBarra")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String grabarBarra(String data,@QueryParam ("token") String token) throws IOException
	{
	      //System.out.println(data);
		
		String json = "";
		Gson gson = new Gson();
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		boolean grabo = false;
		articuloBarra art = new articuloBarra();
		
		if (u.getNick() == null) 
		{
			art.setRespuesta("fail");
		}
		else {
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);
			
			art = gson.fromJson(data, articuloBarra.class);
			
			try {
				switch (idEmpresa) {
				case 1:		//STADIUM
					
					break;
					
					
				case 2:		//FORUS
					ClienteWSVisualForus ws=new ClienteWSVisualForus();
					grabo = ws.GrabarArtBarra(art.getArticulo(), art.getBarra());
					
					break;	
					
					
				case 4:		//EL REY DEL ENTRETENIMIENTO
					
					
					break;	
					
					
				case 5:		//PEPE GANGA
					break;
					
					
				default:
					break;
				}
				
				if(grabo) {
					art.setRespuesta("ok");
				}
				else {
					art.setRespuesta("fail");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				art.setRespuesta("fail");
			}					
			
		}
		
		json = gson.toJson(art);      
		
		return json;
	   }
	
	@POST
	@Path("/stockReserve")
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String reservarStock(String data,@QueryParam ("token") String token, @QueryParam ("resend") String resend) throws IOException
	{
	      //System.out.println(data);
		
		String json = "";
		Gson gson = new Gson();
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		DataIDDescripcion erp = null;
		Boolean reEnvio = false;
		try {
			reEnvio = Boolean.parseBoolean(resend); // que e eto???
		} catch (Exception e) {}
		
		if (u.getNick() == null) 
		{
			erp = new DataIDDescripcion(0,"");
		}
		else {
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);
			MovStock m = new MovStock();
			m = gson.fromJson(data, MovStock.class);
			
			LogicaAPI l = new LogicaAPI();
			int idMov = 0;			
			int idDoc = 0;
			
			try {
				if(!reEnvio) {
					idMov = l.RegistrarMovimientoStock(m.getOrigen(), m.getDestino(), m.getIdUsuario(), m.getDetalle(), idEmpresa, 
							m.getOrigenDoc(), m.getDocSolicitud(), m.getRazon(), m.isEntrega());
					m.setId(idMov);
				}				
				
				switch (idEmpresa) {
				case 1:		//STADIUM
					
					break;
					
					
				case 2:		//FORUS
				
					break;	
					
					
				case 4:		//EL REY DEL ENTRETENIMIENTO
				
					break;	
					
					
				case 5:		//PEPE GANGA
					break;
					

				case 6:		//LaIsla
					List<PedidoAlbaran> pas = gson.fromJson(data, new TypeToken<List<PedidoAlbaran>>(){}.getType());
					LaIsla li = new LaIsla();
					List<Integer> idsAlbaranesCreados = new ArrayList<>();
					for (PedidoAlbaran pa : pas) {
						idsAlbaranesCreados = li.crearAlbaranInterno(pa,m.getOrigenDoc());
					}
					break;
				default:
					break;
				}
				
				//REGISTRO LA SALIDA DEL MOVIMIENTO DE STOCK
				if(erp.getId()==0){
					l.RegistrarDocMovimientoStock(0, idMov, idDoc, erp.getDescripcion(), 
							idEmpresa,m.getIdUsuario(),erp.getIdB());
				}
				else {
					l.RegistrarDocMovimientoStock(1, idMov, idDoc, erp.getDescripcion(),
							idEmpresa,m.getIdUsuario(),erp.getIdB());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}					
			
		}
		
		json = gson.toJson(erp);      
		
		return json;
	   }		
	
	@GET
	@Path("/albaranesListos")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String albaranesListos(String data,@QueryParam ("idDeposito") String idDeposito, @QueryParam ("token") String token, @QueryParam ("idDepositoOrigen") String idDepositoO, 
			@QueryParam ("transito") String transito) throws IOException
	{		
		String json = "";
		Gson gson = new Gson();
		
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		
		List<Remito> remitos = new ArrayList<>();
		
		if (u.getNick() == null) 
		{
			
		}
		else {
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);		
			
			try {
				
				switch (idEmpresa) {
				case 1:		//STADIUM
					
					break;
					
					
				case 2:		//FORUS
					boolean tr = false;
					try {
						tr = transito.equalsIgnoreCase("true");
					} catch (Exception e) {}
					remitos = LogicaAPI.DarRemitosForus(idDeposito, idDepositoO, tr, idEmpresa);
					LogicaAPI.RemitosForus(remitos, idDeposito, idEmpresa);
					
					break;	
					
					
				case 4:		//EL REY DEL ENTRETENIMIENTO
					
					
					break;	
					
					
				case 5:		//PEPE GANGA
					break;
					
				case 6:		//LA ISLA
					
					//ClienteOdoo_LaIsla cli = new ClienteOdoo_LaIsla(true);
					int id = Integer.parseInt(idDeposito);
					LogicaAPI lo = new LogicaAPI();
					LaIsla li = new LaIsla();

					remitos = li.albaranesListosParaRecibir(id, idEmpresa);
					break;
					
				default:
					break;
				}
				
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}					
			
		}
		
		json = gson.toJson(remitos);      
		
		return json;
	   }
	
	@GET
	@Path("/revertirMovStock")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String revertirMovStock(String data,@QueryParam ("idDeposito") String idDeposito, @QueryParam ("token") String token) throws IOException
	{
	      //System.out.println(data);
		
		String json = "";
		Gson gson = new Gson();
		
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		
		if (u.getNick() == null) 
		{
			
		}
		else {
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);
			
			LogicaAPI l = new LogicaAPI();
			
			try {
				
				switch (idEmpresa) {
				case 1:		//STADIUM
					
					break;
					
					
				case 2:		//FORUS
					
					break;	
					
					
				case 4:		//EL REY DEL ENTRETENIMIENTO
					
					
					break;	
					
					
				case 5:		//PEPE GANGA
					break;
					
				case 6:		//LA ISLA
					int id = Integer.parseInt(idDeposito);
					//ClienteOdoo_LaIsla cli = new ClienteOdoo_LaIsla(true);
					LogicaAPI lo = new LogicaAPI();
					SincroLaIsla si = new SincroLaIsla();
					break;
					
				default:
					break;
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}					
			
		}
		    
		
		return json;
	   }
	
	
	@POST
	@Path("/CallbackBiller")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String callbackBiller(String data,@QueryParam ("token") String token) throws IOException
	{
	      //System.out.println(data);
		
		String json = "";
		Gson gson = new Gson();
		Usuario u = LogicaAPI.loginEncuentraAPI2(token);
		boolean grabo = false;
		wsResponse response = new wsResponse();
		
		
		if (u.getNick() == null) 
		{
			response.setCode("404");
			response.setDescription("Usuario invalido");
		}
		else 
		{
			int idEmpresa = u.getIdEmpresa();
			System.out.println(data);
							
			response.setCode("200");
			response.setDescription("OK");
			
			Util ul = new Util();
			data = ul.removerTildes(data);
			System.out.println(data);
			
			LogicaAPI.persistir("INSERT INTO `callbacklog` (`idEmpresa`, `json`) VALUES ('"+idEmpresa+"', '"+data+"');");
			
			
		}
		
		json = gson.toJson(response);      
		
		return json;
	   }
	
}
