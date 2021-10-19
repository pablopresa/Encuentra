package web.ecommerce;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import dataTypes.DataPedidoArticuloEcommerceVerif_ar;
import beans.Usuario;



public class _EncuentraDeralleVerifVerifEcommerce extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
	
		String idPedido = request.getParameter("idPedido");
		String accion = request.getParameter("accion");
		
		
	    //Logica.updateEcommerceEstado(idPedidoP, 4);
		
	    //Logica.logPedido(idPedidoP,uLog.getNumero(),4,"El usuario "+uLog.getNick()+" modifico el estado del Pedido a Despachado",0);
		
		
		List<DataPedidoArticuloEcommerceVerif_ar> pedidosVerificadosArt = Logica.darListaPedidosVerificadosArt_ar(idPedido, idEmpresa);
		   
		request.setAttribute("PedidosVerDetalle", pedidosVerificadosArt);
		request.setAttribute("accion", accion);
		request.setAttribute("pedDespachar", idPedido);
		
		
		;
		return mapping.findForward("ok");
	
		
	}
	
}
