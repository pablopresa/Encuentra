package web.ecommerce;

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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArtPedidoPickup;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class PedidoDetalleClasificaXID extends Action 
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


		String siguiente = request.getParameter("preparar");
		String idPedido = request.getParameter("idPedido");
		Long idPedidoP = new Long(idPedido);
		List<DataArtPedidoPickup> detallePedido = new ArrayList<>();
		
		detallePedido = log.darArticulosPedidoEcommercePickup(idPedidoP,false,idEmpresa);
		
		request.setAttribute("idPedido", idPedido);
		session.setAttribute("detallePedido", detallePedido);
		
		uLog.registrarEventoMin(session.getId(), "Consulta lista de articulos del pedido"+idPedido);
		
		int sig = Integer.parseInt(siguiente);
		
		//Si ya esta pickeado
		if(sig==1)
			return mapping.findForward("preparar");
		else
			return mapping.findForward("ok");

		
	
	}

}
