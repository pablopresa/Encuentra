package endpoints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import logica.LogicaAPI;
import logica.Util;

import com.google.gson.Gson;

import beans.Usuario;
import beans.jsonEstadoMP;
import beans.api.json.Credenciales;
import beans.api.json.Shipping;
import beans.datatypes.DataDescDescripcion;
import beans.datatypes.DataIDDescripcion;
import beans.encuentra.DataArticuloEcommerceVerifR;
import beans.encuentra.EncuentraPedido;
import integraciones.couriers.dac.Call_WS_DAC;
import integraciones.couriers.districad.Call_WS_DISTRICAD;
import integraciones.couriers.mirtrans.Call_WS_MIRTRANS;
import integraciones.couriers.mirtrans.despachoMIRTRANS;
import integraciones.couriers.mirtrans.trackingMIRTRANS;
import integraciones.couriers.pedidosYA.CallBackPeYA;
import integraciones.couriers.pedidosYA.Call_WS_PedidosYA;
import integraciones.couriers.pedidosYA.OutputDespachoPeya;
import integraciones.couriers.soydelivery.Call_WS_SOYDELIVERY;
import integraciones.couriers.soydelivery.OutputDespachoSDL;
import integraciones.couriers.soydelivery.trackingSDL;
import integraciones.couriers.ues.CallBackUES;
import integraciones.couriers.ues.Call_WS_UES;
import integraciones.couriers.ues.trackingUES;
import integraciones.wms.Call_WS_APIENCUENTRA;


@Path("/Couriers")
public class Couriers 
{
	
	@POST
	@Path("/MirtransShipping")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String MirtransShipping(String data,@QueryParam ("token") String a) throws IOException
	{
	   
		System.out.println(data);
		
		String json = "";
		despachoMIRTRANS despacho = null;
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{		
				Gson gson = new Gson();
				Shipping shipp = gson.fromJson(data, Shipping.class);				
				
				if(shipp==null)
				{
					System.out.println("No se ha recibido ningun envio");
					json = "No se ha recibido ningun envio";
				}
				else
				{
					Call_WS_MIRTRANS call = new Call_WS_MIRTRANS();
					despacho = call.altaEnvio(shipp.getCliente(), shipp.getCredenciales());
					json = gson.toJson(despacho);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
			return json;
	   }
	
	@POST
	@Path("/DistricadShipping")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String DistricadShipping(String data,@QueryParam ("token") String a) throws IOException
	{

		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{		
				Gson gson = new Gson();
				Shipping shipp = gson.fromJson(data, Shipping.class);				
				
				if(shipp==null)
				{
					System.out.println("No se ha recibido ningun envio");
					json = "No se ha recibido ningun envio";
				}
				else
				{
					Call_WS_DISTRICAD call = new Call_WS_DISTRICAD();
					DataDescDescripcion despacho = call.setEnvio(shipp.getCliente(), shipp.getCredenciales());
					json = gson.toJson(despacho);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
			return json;
		
	}
		
	@POST
	@Path("/UESShipping")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String UESShipping(String data,@QueryParam ("token") String a,@QueryParam ("fecha") String fecha,
			@QueryParam ("hora") String hora) throws IOException
	{
		Util ul = new Util();
		data = ul.removerTildes(data);
		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{
				Gson gson = new Gson();
				Shipping shipp = gson.fromJson(data, Shipping.class);				
				
				if(shipp==null)
				{
					System.out.println("No se ha recibido ningun envio");
					json = "No se ha recibido ningun envio";
				}
				else
				{
					Call_WS_UES call = new Call_WS_UES();
					
					DataDescDescripcion despacho = call.generarEtiqueta(shipp.getCredenciales().getIdCliente(), shipp.getCredenciales().getToken(), shipp.getCredenciales().getTipoEnvio() , 
							shipp.getCliente().getNombre() + " "+shipp.getCliente().getApellido(), shipp.getCliente().getCalle() , shipp.getCliente().getNroPuerta() , 
							shipp.getCliente().getNroApto() , shipp.getCliente().getLocalidad() ,shipp.getCliente().getDepartamento() , shipp.getCliente().getTelefono(), shipp.getCliente().getEmail(), shipp.getCliente().getObs() , shipp.getCredenciales().getPedido(), shipp.getCliente().getLatitud(), shipp.getCliente().getLongitud());
					
					
					
					json = gson.toJson(despacho);
				}
			}
				
				
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
			return json;
		
	}
	
	@POST
	@Path("/DACShipping")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String DacShipping(String data,@QueryParam ("token") String a,@QueryParam ("fecha") String fecha,
			@QueryParam ("hora") String hora) throws IOException
	{
		Util ul = new Util();
		data = ul.removerTildes(data);
		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{
				Gson gson = new Gson();
				Shipping shipp = gson.fromJson(data, Shipping.class);				
				
				if(shipp==null)
				{
					System.out.println("No se ha recibido ningun envio");
					json = "No se ha recibido ningun envio";
				}
				else
				{
					Call_WS_DAC call = new Call_WS_DAC();
					
					DataDescDescripcion despacho = call.generarEtiqueta
							(
									shipp.getCliente().getNombre() + " "+shipp.getCliente().getApellido(),
									shipp.getCliente().getNombre() + " "+shipp.getCliente().getApellido(),
									shipp.getCliente().getCalle() , 
									shipp.getCliente().getNroPuerta() , 
									shipp.getCliente().getNroApto() , 
									shipp.getCliente().getCiudad() ,
									shipp.getCliente().getDepartamento() , 
									shipp.getCliente().getTelefono(), 
									shipp.getCliente().getEmail(), 
									shipp.getCliente().getObs() , 
									shipp.getCredenciales().getPedido(), 
									shipp.getCliente().getLatitud(), 
									shipp.getCliente().getLongitud(),
									u.getIdEmpresa(),
									shipp.getCredenciales().getUser(),
									shipp.getCredenciales().getPass(),
									shipp.getNombreRemite(),
									shipp.getCliente().getDocumentoNro()+"",
									shipp.getCantidadPaquetes(),
									shipp.getxGrandes(),
									shipp.getGrandes(),
									shipp.getMedianos(),
									shipp.getChicos(),
									shipp.getTipoShipping()
									
									);
					
					
					
					json = gson.toJson(despacho);
				}
			}
				
				
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
			return json;
		
	}
	
	@POST
	@Path("/SDLShipping")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String SDLShipping(String data,@QueryParam ("token") String a,@QueryParam ("fecha") String fecha,
			@QueryParam ("hora") String hora) throws IOException
	{
		Util ul = new Util();
		data = ul.removerTildes(data);
		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{
				Gson gson = new Gson();
				Shipping shipp = gson.fromJson(data, Shipping.class);				
				
				if(shipp==null)
				{
					System.out.println("No se ha recibido ningun envio");
					json = "No se ha recibido ningun envio";
				}
				else
				{
					Call_WS_SOYDELIVERY call = new Call_WS_SOYDELIVERY();
					OutputDespachoSDL despacho = call.setEnvio(shipp.getCliente(), shipp.getCredenciales());
					if(despacho.getPedido_id()!=0) {
						DataDescDescripcion trackEti = call.getEtiqueta(despacho.getPedido_id(),shipp.getCredenciales());
						json = gson.toJson(trackEti);
					}
					else {
						json = gson.toJson(new DataDescDescripcion());
					}
					
				}
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		
		return json;		
	}
	
	
	@POST
	@Path("/getTrackingMirtrans")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String getTrackingMirtrans(String data,@QueryParam ("token") String a) throws IOException
	{

		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{		
				Gson gson = new Gson();
				Credenciales c = gson.fromJson(data, Credenciales.class);				
				
				if(c==null)
				{
					System.out.println("No se ha recibido credenciales");
					json = "No se ha recibido credenciales";
				}
				else
				{
					Call_WS_MIRTRANS call = new Call_WS_MIRTRANS();
					trackingMIRTRANS tracking = call.getTracking(c);
					json = gson.toJson(tracking);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
			return json;
		
	}
	
	@POST
	@Path("/getTrackingUES")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String getTrackingUES(String data,@QueryParam ("token") String a) throws IOException
	{

		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{		
				Gson gson = new Gson();
				Credenciales c = gson.fromJson(data, Credenciales.class);				
				
				if(c==null)
				{
					System.out.println("No se ha recibido credenciales");
					json = "No se ha recibido credenciales";
				}
				else
				{
					Call_WS_UES call = new Call_WS_UES();
					
					String envio = "{"+
							  " 	\"Cliente\":\""+c.getUser()+"\","+
							  "		\"Tocken\":\""+c.getPass()+"\","+
							  "		\"guia\": \""+c.getTracking()+"\""+
							  "}";
					
					trackingUES tracking = call.getTracking(envio);
					json = gson.toJson(tracking);
					
					
					LogicaAPI.persistir("insert into respuestas_wss(idPedido,mensaje,respuesta) values('"
							+c.getPedido()+"','"+gson.toJson(envio)+"','"+tracking+"')");
					
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
			return json;
		
	}
	
	
	@POST
	@Path("/getTrackingSDL")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String getTrackingSDL(String data,@QueryParam ("token") String a) throws IOException
	{

		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{		
				Gson gson = new Gson();
				Credenciales c = gson.fromJson(data, Credenciales.class);				
				
				if(c==null)
				{
					System.out.println("No se ha recibido credenciales");
					json = "No se ha recibido credenciales";
				}
				else
				{
					Call_WS_SOYDELIVERY call = new Call_WS_SOYDELIVERY();
					trackingSDL tracking = call.getTracking(c);
					json = gson.toJson(tracking);
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
			return json;
		
	}
	
	
	@POST
	@Path("/PeYaShipping")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String PeYaShipping(String data,@QueryParam ("token") String a,@QueryParam ("fecha") String fecha,@QueryParam ("hora") String hora) throws IOException
	{
		Util ul = new Util();
		data = ul.removerTildes(data);
		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{
				Gson gson = new Gson();
				Shipping shipp = gson.fromJson(data, Shipping.class);				
				
				if(shipp==null)
				{
					System.out.println("No se ha recibido ningun envio");
					json = "No se ha recibido ningun envio";
				}
				else
				{
					Call_WS_PedidosYA call = new Call_WS_PedidosYA();
					OutputDespachoPeya despacho = call.setEnvio(shipp,u.getIdEmpresa());
					
					if(despacho.getId()!=null && !despacho.getId().equals("") ) 
					{
						DataDescDescripcion retorno = new DataDescDescripcion("OK",despacho.getId());
						retorno.setFecha(despacho.getShareLocationUrl());//es e? URL de tracking
						
						json = gson.toJson(retorno);
						
					}
					else 
					{
						json = gson.toJson(new DataDescDescripcion("FAIL",despacho.getStatus() ));
					}
					
				}
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		
		return json;		
	}
	
	@POST
	@Path("/PeYaConfirmShipping")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String PeYaConfirmShipping(String data,@QueryParam ("token") String a,@QueryParam ("tracking") String tracking) throws IOException
	{
		Util ul = new Util();
		data = ul.removerTildes(data);
		System.out.println(data);
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{
				Gson gson = new Gson();
				Credenciales cre = gson.fromJson(data, Credenciales.class);				
				
				if(cre==null)
				{
					System.out.println("No se ha credenciales");
					json = "No se ha credenciales";
				}
				else
				{
					Call_WS_PedidosYA call = new Call_WS_PedidosYA();
					OutputDespachoPeya despacho = call. confirmarEnvio(cre, tracking, u.getIdEmpresa());
					if(despacho.getId()!=null && !despacho.getId().equals("") ) 
					{
						json = gson.toJson(new DataDescDescripcion("OK",despacho.getId()));
						
					}
					else 
					{
						json = gson.toJson(new DataDescDescripcion("FAIL",""));
					}
					
				}
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		
		return json;		
	}
	@POST
	@Path("/PeYaCallbackShipping")

	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	//public String createUser(@FormParam("name") String name) throws IOException
	public String PeYaCallbackShipping(String data,@QueryParam ("tracking") String tracking,@HeaderParam("Authorization") String a) throws IOException
	{
		Util ul = new Util();
		data = ul.removerTildes(data);
		System.out.println(data);
		
		LogicaAPI.persistir("INSERT INTO `callbacklog` (`idEmpresa`, `json`) VALUES ('0', '"+data+"');");
		
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(a);
			
			if (u.getNick() == null) 
			{
				System.out.println("access_token invalido");
				json = "access_token invalido";
			}
			else
			{
				Gson gson = new Gson();
				CallBackPeYA cb = gson.fromJson(data, CallBackPeYA.class);				
				
				if(cb==null)
				{
					System.out.println("No se ha credenciales");
					json = "No se ha credenciales";
				}
				else
				{
					/*hay que ver que hacemos con los estados*/
					String status =cb.getData().getStatus(); 
					
					if(status.equals("COMPLETED") || status.equals("PICKED_UP"))
					{
						Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
						String track = cb.getId();
						EncuentraPedido p = cen.darIdPedido(a,track);
						String dataArticuloEcommerceVerifR ="";
						
						
						if(status.equals("COMPLETED"))
						{
							 dataArticuloEcommerceVerifR = "{   \"idPedido\":"+p.getIdPedido()+",   \"estadoEncuentra\":6}";
							 
						}
						else
						{
							 dataArticuloEcommerceVerifR = "{   \"idPedido\":"+p.getIdPedido()+",   \"estadoEncuentra\":4}";
						}
						
						cen.updateOrdersStatus(a, dataArticuloEcommerceVerifR);
						
						
						String servicio = "http://"+cen.getHost()+"/WMS/Integraciones/OrderFunctions/getArticuloVerifR?idPedido="+p.getIdPedido()+"&token="+a;
						 String jsonAR = cen.callWSGET(servicio, false);
						jsonEstadoMP js = gson.fromJson(jsonAR, jsonEstadoMP.class);
						 List<jsonEstadoMP> lista = new ArrayList<>();
						 lista.add(js);
						 
						 LogicaAPI.putColaEstadoMarketPlace(lista);
						
					}
					
					
					
					
					
					
					
				}
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		
		return json;		
	}
	
	
	
	@POST
	@Path("/UESCallbackShipping")

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UESCallbackShipping(String data,@HeaderParam("Authorization") String token) throws IOException
	{
		Util ul = new Util();
		data = ul.removerTildes(data);
		System.out.println(data);
		
		LogicaAPI.persistir("INSERT INTO `callbacklog` (`idEmpresa`, `json`) VALUES ('0', '"+data+"');");
		
		String json = "";
		try
		{
			Usuario u = LogicaAPI.loginEncuentraAPI2(token);
			
			if (u.getNick() == null) 
			{
				json = "{ \"status\": \"access_token invalido\" }";
				System.out.println(json);
				return Response.serverError().entity(json).build();
			}
			else
			{
				Gson gson = new Gson();
				CallBackUES cb = gson.fromJson(data, CallBackUES.class);				
				
				if(cb==null)
				{
					json = "{ \"status\": \"Formato de env?o incorrecto\" }";
					System.out.println(json);
					return Response.serverError().entity(json).build();
				}
				else
				{
					
					String status =cb.getEvento_nombre(); 
					
					if(status.equals("ENTREGADO"))
					{
						Call_WS_APIENCUENTRA cen = new Call_WS_APIENCUENTRA();
						String track = cb.getNumero_guia();
						EncuentraPedido p = cen.darIdPedido(token,track);
						
						if(p.getIdPedido() != null) {
							String dataArticuloEcommerceVerifR ="{   \"idPedido\":"+p.getIdPedido()+",   \"estadoEncuentra\":6}";
							
							cen.updateOrdersStatus(token, dataArticuloEcommerceVerifR);
							
							String servicio = "http://"+cen.getHost()+"/WMS/Integraciones/OrderFunctions/getArticuloVerifR?idPedido="+p.getIdPedido()+"&token="+token;
							String jsonAR = cen.callWSGET(servicio, false);
							jsonEstadoMP js = gson.fromJson(jsonAR, jsonEstadoMP.class);
							List<jsonEstadoMP> lista = new ArrayList<>();
							lista.add(js);
							 
							LogicaAPI.putColaEstadoMarketPlace(lista);
							
							json = "{ \"status\": \"OK\" }";
						}else {
							json = "{ \"status\": \"Error\" }";
							return Response.serverError().entity(json).build();
						}
						
						
					}
																														
				}
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			json = "{ \"status\": \"Error\" }";
			return Response.serverError().entity(json).build();
		}		
		
		//return json;
		return Response.ok(json,MediaType.APPLICATION_JSON).build();
		
	}
	
}
