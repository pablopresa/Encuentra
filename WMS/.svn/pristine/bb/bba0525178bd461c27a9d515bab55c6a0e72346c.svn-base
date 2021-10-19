package web.ecommerce;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataArticuloEcommerceVerifR;
import beans.Usuario;



public class _EncuentraDespacharTodo extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		 Logica Logica = new Logica();
		 Utilidades util = new Utilidades();
			
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
		
		String idPedido = request.getParameter("idPedidoDespachado");
		String canal = request.getParameter("canal");
		
		Long idPedidoP = 0L;
		int canalP = 0;
		boolean canalActivo = false;
		
		try {
			idPedidoP = new Long(idPedido);
			canalP = Integer.parseInt(canal);
			
			canalActivo = Logica.canalActivoEC(canalP, idEmpresa);
			
		} catch (Exception e) {
			System.out.println("fallo parseo");
		}
		
		int idDestino = 0;
		idDestino = Logica.darDestinoEcommercePedido(idPedidoP, idEmpresa);

		String idFenicio = request.getParameter("idFenicio");
		boolean integracionActiva = false;
		integracionActiva = Logica.darIntegracionProductiva(3, idEmpresa);
		
			Logica.updateEcommerceEstado(idPedidoP, 4,idEmpresa,uLog);
			Logica.logPedido(idPedidoP,uLog.getNumero(),4,"El usuario "+uLog.getNick()+" modifico el estado del Pedido a Despachado",0,idEmpresa);
			request.setAttribute("menError", "Se modifico el estado del Pedido a Despachado");
			if(canalActivo){
				if(integracionActiva){
					/*Call_WS_analoga c = new Call_WS_analoga();
					String track = Logica.darTrackingPedido(idPedidoP,idEmpresa);
					String jotason2="";
					/*if(!track.equals(""))
					{
						if(idDestino==700000){
							jotason2="[ "+
									 "     { "+
									 "        \"id\":\""+idFenicio+"\", "+
									 "        \"estado\":\"preparado\" "+
									 "		  \"trackingID\":\""+track+"\" "+
									 "     } "+
									 "]";
						}
						else{
							jotason2="[ "+
									 "     { "+
									 "        \"id\":\""+idFenicio+"\", "+
									 "        \"estado\":\"preparado\" "+
									 "		  \"trackingID\":\""+track+"\" "+
									 "     } "+
									 "]";
						}
					}
					else
					{
						jotason2="[ "+
								 "     { "+
								 "        \"id\":\""+idFenicio+"\", "+
								 "        \"estado\":\"preparado\" "+
								 "     } "+
								 "]";
					}
					//llamar a WS para cerrar
						
						
						c.setPedidos(jotason2,canalP,idEmpresa);
				}*/
				DataArticuloEcommerceVerifR r = Logica.darArticuloEcommerceReqReclasifica(idPedidoP,0,idEmpresa);
				Logica.CambioEstadoMarketPlace(idEmpresa, r);
			}
					
			}
		return mapping.findForward("ok");	
	}
	
}
