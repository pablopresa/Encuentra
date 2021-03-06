package web.pickup;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
//import clienteWSNAD.WSCommunicateVS;
import cliente_rest_Invoke.Call_WS_analoga;
//import cliente_rest_Invoke.Call_WS_meli;
import dataTypes.DataArtPedidoPickup;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import logica.Logica;
import logica.Utilidades;
import main.process_ecommerce.Call_WS_meli;

public class _EncuentraDetallePedidoPickupVerif extends Action 
{


	@Override
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

		 List<DataArtPedidoPickup> detallePedido = (List<DataArtPedidoPickup>) session.getAttribute("detallePedido");
		 Long idPedido = new Long(detallePedido.get(0).getIdPedido());
		 int canal = detallePedido.get(0).getCanal();
		 int idTienda = Integer.parseInt(uLog.getDeposito());
		
		log.ProcesarPickup(idPedido, canal, idEmpresa,uLog); 
		log.updateEcommerceEstado(idPedido, 5, idEmpresa,uLog); 
		//FACTURACION AUTOMATICA
        util.facturacionAutomatica(idPedido,uLog.getIdEquipo(),idEmpresa,log);
		
		List<DataIDDescripcion> listaMovStock = new ArrayList<>();
		for (DataArtPedidoPickup pp : detallePedido) 
		{
		
			DataIDDescripcion ms = new DataIDDescripcion(pp.getCantRequerida(), pp.getIdArticulo());
			listaMovStock.add(ms);
		}
		
		
		
		//WSCommunicateVS cl = new WSCommunicateVS();
		//cl.grabarmovStockTienda(Integer.parseInt(uLog.getDeposito()),71,uLog.getNumero(),Integer.parseInt(uLog.getDeposito()),"Mov. Stock por Venta WEB"+idPedido,listaMovStock);
				
		
		
		if(log.esMLPedido(idPedido,idEmpresa))
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
			
			String nombrePickup = log.darNombreDepoPick(idTienda,idEmpresa);
			String message = "";
			message = mailML(nombrePickup, idTienda, idPedido, log, idEmpresa);
			ws.MessagePackToCustomer(idPedido,message,asunto,tokens);
		}
		else
		{
			//sino, llamar al WS de analoga para marcar como listo para retirar
			
			String jotason="[ "+
					 "     { "+
					 "        \"id\":\""+idPedido+"\", "+
					 "        \"estado\":\"listoretirar\" "+
					 "     } "+
					 "]";
			if(idEmpresa != 4) { //El Rey no usa Fenicio
				Call_WS_analoga call = new Call_WS_analoga(); 
				call.setPedidos(jotason,canal,idEmpresa);
			}
			
			
		
		}
		 
	 	uLog.registrarEventoMin(session.getId(), "Pedido "+detallePedido.get(0).getIdPedido()+" preparado (Pickup)");
	 	request.setAttribute("menError", "Pedido preparado");
	 	
	 	List<DataPedidoPickup> pedidosPickup = log.darPedidosPickup(Integer.parseInt(uLog.getDeposito()),idEmpresa);
		session.setAttribute("pedidosPickup", pedidosPickup);
		return mapping.findForward("ok");

		
	
	}
	
	public String mailML(String nombrePickup, int idTienda, Long idPedidoI, Logica Logica, int idEmpresa){
		
		 
			String casaHorario="";
						
			String cliente = "-";
			DataIDDescripcion dataCliente = Logica.darDataIdDescripcion("select idpedido,descripcion from ecommerce_pedido where idpedido = "+idPedidoI+" and idempresa="+idEmpresa);
			if(dataCliente!=null && dataCliente.getDescripcion()!=null) {
				cliente = dataCliente.getDescripcion().replace("MELI: ", "");
			}
			
			switch (idTienda){
			case 1:
				casaHorario="Stadium Shopping Terminal Paysand?, Bulevar Artigas 770 Local 24. Su horario de atenci?n es de Lun a Dom 11:00 a 20:00hs";
				break;
			case 2:
				casaHorario="Stadium Pando, Av. Artigas 1001. Su horario de atenci?n es de Lun a Vie 10:00 a 19:00hs - S?b 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 3:
				casaHorario="Stadium Cord?n, Av. 18 de Julio 1777. Su horario de atenci?n es de Lun a Vie 10:00 a 19:00hs - S?b 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 4:
				casaHorario="Stadium  San Jos?, Paseo de los Constituyentes 563.  Su horario de atenci?n es de Lun a S?b 09:00 a 13:00 y 14:30 a 19:00. Domingos cerrado";
				break;
			case 5:
				casaHorario="Stadium Shopping Portones, Av Italia 5775 Local 230. Su horario de atenci?n es de Lun a Vie 12:00 a 21:00hs - Sab y Dom 11:00 a 21:00hs";
				break;
			case 6:
				casaHorario="Stadium Uni?n, Av. 8 de Octubre 3781. Su horario de atenci?n es de Lun a Vie 10:00 a 19:00hs - S?b 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 7:
				casaHorario="Stadium Centro, 18 de Julio 1061. Su horario de atenci?n es de Lun a Vie 10:00 a 19:00hs - S?b 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 8:
				casaHorario="Stadium Paso Molino - Sucursal Chica, Av. Agraciada 4127. Su horario de atenci?n es de Lun a Vie 10:00 a 19:00hs - S?b 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 9:
				casaHorario="Stadium Shopping Punta Carretas, Jos? Ellauri 350 105 -106. Su horario de atenci?n es de Lun a Vie 12:00 a 21:00hs - Sab y Dom 11:00 a 21:00hs";
				break;
			case 10:
				casaHorario="en Stadium Tacuaremb?, 18 de julio 235. Su horario de atenci?n es de Lun a S?b 09:00 a 13:00hs y de 14:30 a 19:00 hs Domingo cerrado";
				break;
			case 11:
				casaHorario="Stadium Centro - Intendencia, Av. 18 de Julio 1363. Su horario de atenci?n es de Lun a Vie 10:00 a 19:00hs - Sab 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 12:
				casaHorario="Stadium Shopping Tres Cruces, Bulevar General Artigas 1825. Su horario de atenci?n es de Lun a Vie 12:00 a 21:00hs - Sab y Dom 11:00 a 21:00hs";
				break;
			case 14:
				casaHorario="Stadium Paso Molino - Sucursal Grande, Av. Agraciada 4097. Su horario de atenci?n es de Lun a Vie 10:00 a 19:00hs - Sab 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 15:
				casaHorario="Stadium Montevideo Shopping, Av. Dr. Luis Alberto de Herrera 1290. Su horario de atenci?n es de Lun a Vie 12:00 a 21:00hs - Sab y Dom 11:00 a 21:00hs";
				break;
			case 16:
				casaHorario="Stadium Punta Shopping, Parada 7 - Playa Mansa, Av. Franklin Delano Roosevelt. Su horario de atenci?n es de Lun a Vie 12:00 a 21:00hs - Sab y Dom 11:00 a 21:00hs";
				break;
			case 18:
				casaHorario="en Stadium Maldonado, Sarand? 931. Su horario de atenci?n es de Lun a S?b 10:00 a 19:00hs. Domingos cerrado";
				break;
			case 19:
				casaHorario="Stadium Salto, Uruguay 779. Su horario de atenci?n es de Lun a S?b 09:00 a 13:00 y de 14:30 a 19:00hs. Domingos cerrado";
				break;
			case 20:
				casaHorario="Stadium Shopping Terminal Salto, Local 102. Su horario de atenci?n es de Lun a Dom 11:00 a 20:00hs";
				break;
			case 21:
				casaHorario="Stadium Rivera Sucursal Chica, Sarand? 500. Su horario de atenci?n es de Lun a S?b 08:30 a 19:30hs. Domingo cerrado";
				break;
			case 22:
				casaHorario="Stadium Shopping Costa Urbana, Av. Giannattasio km21. Su horario de atenci?n es de Lun a Vie 12:00 a 21:00hs - Sab y Dom 11:00 a 21:00hs";
				break;
			case 23:
				casaHorario="Stadium Rivera Sucursal Grande, Sarand? 435. Su horario de atenci?n es de Lun a S?b 10:00 a 19:00hs. Domingo cerrado";
				break;
			case 24:
				casaHorario="Stadium Shopping Nuevo Centro, Av. Dr. Luis Alberto de Herrera 3365 Local 135. Su horario de atenci?n es de Lun a Vie 12:00 a 21:00hs - Sab y Dom 11:00 a 21:00hs";
				break;
			case 25:
				casaHorario="Stadium Mercedes, De Castro y Careaga 750. Su horario de atenci?n es de Lun a S?b 09:00 a 13:00hs y de 14:30 a 18:30hs. Domingos cerrado";
				break;
			case 26:
				casaHorario="Stadium Cerro, Carlos Ma. Ram?rez 1563. Su horario de atenci?n es de Lun a Vie 10:00 a 19:00hs - S?b 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 27:
				casaHorario="Stadium Las Piedras, Av. Artigas 710. Su horario de atenci?n es de Lun a Vie 10:00 a 19:00hs - S?b 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 28:
				casaHorario="Stadium Shopping Las Piedras, Bulevar del Bicentenario entre Sauce y Francisca Arnal de Artigas. Su horario de atenci?n es de Lun a Vie 12:00 a 21:00hs - Sab y Dom 11:00 a 21:00hs";
				break;
			case 29:
				casaHorario="Stadium Shopping Colonia, Roosvelt 458. Su horario de atenci?n es de Lun a Dom 11:00 a 20:00hs";
				break;
			case 30:
				casaHorario="Stadium Florida, Independencia 617. Su horario de atenci?n es de Lun a Vie 09:00 a 13:00hs y 14:30 a 19:00hs. Sab 10:00 a 14:00hs. Domingos cerrado";
				break;
			case 41:
				casaHorario="Clarks Shopping Punta Carretas, Jos? Ellauri 350 Local. Su horario de atenci?n es de Lun a Dom 10:00 a 22:00hs";
				break;
			default:
				casaHorario = "donde has elegido, "+nombrePickup+".";
				break;
			}
			
			String message="Tu pedido "+idPedidoI+" a nombre de: "+cliente.toUpperCase()+" est? listo para retirar en "+casaHorario
					+ ". Puede retirarlo otra persona presentando foto de tu c?dula e indicando n?mero de pedido. Este, es un mensaje autom?tico, "
					+ "si hiciste una consulta que a?n no respondimos, por favor, ponte en contacto. Tu pedido permanecer? en la sucursal por 30 d?as. ";
			
			return message;
		
	}

}
