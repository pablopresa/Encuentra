package web.pickup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.LeerHTML;
import logica.Logica;
import logica.Utilidades;
import main.process_ecommerce.Call_WS_meli;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import beans.ProcessEcommerce;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.ArticuloConteo;
import beans.encuentra.ConteoTiendas;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;
import cliente_rest_Invoke.Call_WS_analoga;
//import cliente_rest_Invoke.Call_WS_meli;

public class _EncuentraAlmacenarPickup extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		Logica log = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		

		String pedidos = request.getParameter("articulos");
		 
		 String ubi = request.getParameter("idOjo");
		 int cantidad = 0;
		 
		 if(pedidos==null || pedidos.equals(""))
		 {
		
			 int estado = 5;
			 String para = request.getParameter("para");
			 
			 if(!para.equals("Almacenar"))
			 {
				 estado=4;
			 }
			 
			 List<DataIDDescripcion> pickups = log.darListaDataIdDescripcionMYSQLConsulta("select 1,p.idpedido from ecommerce_pedido p inner join "
			 		+ "ecommerce_pedido_destino d on d.idpedido=p.idpedido and p.idEmpresa=d.idEmpresa "
			 		+ "where p.estadoencuentra="+estado+"  and d.iddestino="+uLog.getDeposito()+" and p.idEmpresa ="+idEmpresa);
			 pickups.remove(0);
			 System.out.println("");
			 String pedidosDepo= "";
			 for(DataIDDescripcion p:pickups){
				 pedidosDepo+=p.getDescripcion()+",";
			 }
			 try {
				pedidosDepo=pedidosDepo.substring(0,pedidosDepo.length()-1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 request.setAttribute("para", para);
			 session.setAttribute("arrayPedidosDepo", pedidosDepo);
			 return mapping.findForward("ok");
		 }
		
		 boolean existe = log.encuentraExisteUbicaBool(ubi,idEmpresa);
			
		if(existe)
		{
			pedidos = pedidos.replace(" ", "");
			pedidos = pedidos.replaceAll("[\n\r]","");
			
			String [] arreglo = pedidos.split(",");			
			
			for(String a:arreglo){
				if(!a.trim().equals("")){
					log.encuentraUpdateOjos(ubi.toUpperCase(), a.trim(), 1, false, uLog.getNumero(), false,"ADD", idEmpresa);
					cantidad++;
				}				
			}
			
			String para = request.getParameter("paraquien");
			if(para.equals("Recibir"))
			{
				request.setAttribute("para", para);
				
				String jotason1="[ ";
				String jsonmedio="";
				String jotason3= "]";
				
				Hashtable<Integer, DataIDDescDescripcion> caneles = new Hashtable<>();
				
				for(String a:arreglo)
				{
					if(!a.trim().equals(""))
					{						
						log.updateEcommerceEstado(Long.parseLong(a.trim()), 5,idEmpresa,uLog);
						//preguintar si es ML
						if(log.esMLPedido(Long.parseLong(a.trim()),idEmpresa))
						{
							Call_WS_meli ws = new Call_WS_meli();
							List<DataIDDescripcion> tokens = new ArrayList<>();
							DataIDDescripcion data;
							List<DataIDDescDescripcion> canales = log.EcommercedarCanalesML(idEmpresa);
									
							String usrCanal1 = canales.get(0).getDesc();
							String secretCanal1 = canales.get(0).getDescripcion();
							String seller1 = canales.get(0).getDescII();
							String access_token1= ws.getToken(usrCanal1,secretCanal1);
							data= new DataIDDescripcion();
							data.setDescripcion(seller1);
							data.setDescripcionB(access_token1);
							tokens.add(data);
							
							String usrCanal2 = canales.get(1).getDesc();
							String secretCanal2 = canales.get(1).getDescripcion();
							String access_token2= ws.getToken(usrCanal2,secretCanal2);
							String seller2 = canales.get(1).getDescII();	
							data= new DataIDDescripcion();
							data.setDescripcion(seller2);
							data.setDescripcionB(access_token2);
							tokens.add(data);
							//si es mandar mensaje para retirno
												
							String asunto="Pedido Listo para retirar";
							int idTienda = Integer.parseInt(uLog.getDeposito());
							String nombrePickup = log.darNombreDepoPick(idTienda,idEmpresa);
							String message = "";
							message = mailML(nombrePickup, idTienda);
							ws.MessagePackToCustomer(new Long(a.trim()),message,asunto,tokens);
						}
						else
						{
							DataIDDescDescripcion canal = log.EcommercedarCanalAnaloga(Long.parseLong(a.trim()),idEmpresa);
							caneles.put(canal.getId(),canal);
							
							jsonmedio+="{ "+
							 "        \"id\":\""+a.trim()+"\", "+
							 "        \"estado\":\"listoretirar\" "+
							 "     },";
							
						}
					}				
				}
				
				if (!jsonmedio.equals(""))
				{
					jsonmedio = jsonmedio.substring(0,jsonmedio.length()-1);
					List<DataIDDescDescripcion> canalesL = new ArrayList<>(caneles.values());
					
					Call_WS_analoga call = new Call_WS_analoga();
					
					for (DataIDDescDescripcion canal : canalesL) 
					{
						call.setPedidos(jotason1+jsonmedio+jotason3,canal.getId(),idEmpresa);
					}
					
					
				}
					
				
				
				
				
				request.setAttribute("menError", "Se recibieron "+cantidad+" pedidos y se asignaron a la ubicacion "+ubi); 
				uLog.registrarEventoMin(session.getId(), "Se recibieron "+cantidad+" pedidos y se asignaron a la ubicacion "+ubi);
				return mapping.findForward("ok");
			}
			
		}
		 
		request.setAttribute("menError", "Se agregaron "+cantidad+" pedidos en la ubicacion "+ubi); 
		uLog.registrarEventoMin(session.getId(), "Se agregaron "+cantidad+" pedidos en la ubicacion "+ubi);
		return mapping.findForward("ok");
	
	}

	public String mailML(String nombrePickup, int idTienda){
		
		 
		String casaHorario="";
					
		switch (idTienda){
		case 1:
			casaHorario="Stadium Shopping Terminal Paysand?, Bulevar Artigas 770 Local 24. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
			break;
		case 2:
			casaHorario="Stadium Pando, Av. Artigas 1001. Su horario de atenci?n es de 	Lun a Vie 10:00 a 18:00hs - S?b 10:00 a 14:00hs. Domingos cerrado";
			break;
		case 3:
			casaHorario="Stadium Cord?n, Av. 18 de Julio 1777. Su horario de atenci?n es de 	Lun a Vie 10:00 a 18:00hs - S?b 10:00 a 14:00hs. Domingos cerrado";
			break;
		case 4:
			casaHorario="Stadium  San Jos?, Paseo de los Constituyentes 563.  Su horario de atenci?n es de Martes a S?bado de 09:00 a 13:00 y de 14:30 a 18:30; Lunes de 14:30 a 18:30. Domingos cerrado";
			break;
		case 5:
			casaHorario="Stadium Shopping Portones, Av Italia 5775 Local 230. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
			break;
		case 6:
			casaHorario="Stadium Uni?n, Av. 8 de Octubre 3781. Su horario de atenci?n es de Lunes a Viernes de 10:00 a 18:00; S?bado de 10:00 a 14:00. Domingos cerrado";
			break;
		case 7:
			casaHorario="Stadium Centro, 18 de Julio 1061. Su horario de atenci?n es de Lunes a Viernes de 10:00 a 18:00; S?bado de 10:00 a 14:00. Domingos cerrado";
			break;
		case 8:
			casaHorario="Stadium Paso Molino - Sucursal Chica, Av. Agraciada 4127. Su horario de atenci?n es de Lunes a Viernes de 10:00 a 18:00; S?bado de 10:00 a 14:00. Domingos cerrado";
			break;
		case 9:
			casaHorario="Stadium Shopping Punta Carretas, Jos? Ellauri 350 105 -106. Su horario de atenci?n es de 	Lun a Dom 10:00 a 22:00hs";
			break;
		case 10:
			casaHorario="en Stadium Tacuaremb?, 18 de julio 235. Su horario de atenci?n es de Martes a S?bado de 09:00 a 13:00 y de 14:30 a 18:30; Lunes de 14:30 a 18:30. Domingo cerrado";
			break;
		case 11:
			casaHorario="Stadium Centro - Intendencia, Av. 18 de Julio 1363. Su horario de atenci?n es de Lunes a Viernes de 10:00 a 18:00; S?bado de 10:00 a 14:00. Domingos cerrado";
			break;
		case 12:
			casaHorario="Stadium Shopping Tres Cruces, Bulevar General Artigas 1825. Su horario de atenci?n es de Lun a Dom 09:00 a 22:00 hs";
			break;
		case 14:
			casaHorario="Stadium Paso Molino - Sucursal Grande, Av. Agraciada 4097. Su horario de atenci?n es de Lunes a Viernes de 10:00 a 18:00; S?bado de 10:00 a 14:00. Domingos cerrado";
			break;
		case 15:
			casaHorario="Stadium Montevideo Shopping, Av. Dr. Luis Alberto de Herrera 1290. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
			break;
		case 16:
			casaHorario="Stadium Punta Shopping, Parada 7 - Playa Mansa, Av. Franklin Delano Roosevelt. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
			break;
		case 18:
			casaHorario="en Stadium Maldonado, Sarand? 931. Su horario de atenci?n es de Lunes a Viernes de 10:00 a 18:00; S?bado de 10:00 a 14:00.  Domingos cerrado";
			break;
		case 19:
			casaHorario="Stadium Salto, Uruguay 779. Su horario de atenci?n es de Martes a S?bado de 09:00 a 13:00 y de 14:30 a 18:30; Lunes de 14:30 a 18:30. Domingos cerrado";
			break;
		case 20:
			casaHorario="Stadium Shopping Terminal Salto, Local 102. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
			break;
		case 21:
			casaHorario="Stadium Rivera Sucursal Chica, Sarand? 500. Su horario de atenci?n es de Lun a S?b 08:30 a 19:30hs. Domingo cerrado";
			break;
		case 22:
			casaHorario="Stadium Shopping Costa Urbana, Av. Giannattasio km21. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
			break;
		case 23:
			casaHorario="Stadium Rivera Sucursal Grande, Sarand? 435. Su horario de atenci?n es de Martes a S?bado de 10:00 a 18:00; Lunes de 14:00 a 18:00. Domingo cerrado";
			break;
		case 24:
			casaHorario="Stadium Shopping Nuevo Centro, Av. Dr. Luis Alberto de Herrera 3365 Local 135. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
			break;
		case 25:
			casaHorario="Stadium Mercedes, De Castro y Careaga 750. Su horario de atenci?n es de Martes a S?bado de 09:00 a 13:00 y de 14:30 a 18:30; Lunes de 14:30 a 18:30. Domingos cerrado";
			break;
		case 26:
			casaHorario="Stadium Cerro, Carlos Ma. Ram?rez 1563. Su horario de atenci?n es de Lunes a Viernes de 10:00 a 18:00; S?bado de 10:00 a 14:00.  Domingos cerrado ";
			break;
		case 27:
			casaHorario="Stadium Las Piedras, Av. Artigas 710. Su horario de atenci?n es de Lunes a Viernes de 10:00 a 18:00; S?bado de 10:00 a 14:00. Domingos cerrado";
			break;
		case 28:
			casaHorario="Stadium Shopping Las Piedras, Bulevar del Bicentenario entre Sauce y Francisca Arnal de Artigas. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
			break;
		case 29:
			casaHorario="Stadium Shopping Colonia, Roosvelt 458. Su horario de atenci?n es de Dom a Jue 10:00 a 21:00hs - Vie y Sab 10:00 a 22:00hs";
			break;
		case 30:
			casaHorario="Stadium Florida, Independencia 617. Su horario de atenci?n es de Lunes a Viernes de 08:30 a 12:30 y de 14:00 a 18:00; S?bado de 10:00 a 14:00. Domingos cerrado";
			break;
		case 41:
			casaHorario="Clarks Shopping Punta Carretas, Jos? Ellauri 350 Local. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
			break;
		default:
			casaHorario = "donde has elegido, "+nombrePickup+".";
			break;
		}
		
		String message="Tu pedido est? listo para retirar en "+casaHorario
				+ ". Puede retirarlo otra persona presentando la factura que te enviamos anteriormente. Este, es un mensaje autom?tico, "
				+ "si hiciste una consulta que a?n no respondimos, por favor, ponte en contacto.  ";
	
	return message;
	
}

}
