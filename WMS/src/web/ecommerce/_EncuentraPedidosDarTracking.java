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

import dataTypes.DataIDDescripcion;
import beans.Usuario;

public class _EncuentraPedidosDarTracking extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
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
		
		String fechas = request.getParameter("fini");

		String fechaI ="";
		String fechaF = "";
		
		if(fechas!=null)
		{
			String []fechaIF = fechas.split(" - ");
		
			fechaI = fechaIF[0];
			fechaF = fechaIF[1];
	
			fechaI = fechaI+" 00:00:00";
			fechaF = fechaF+" 23:59:59";						
		}	
		
		List<DataIDDescripcion> pedidosTracking = Logica.PedidosDarTracking(fechaI, fechaF,"",false, idEmpresa);
		
		session.setAttribute("pedidosT", pedidosTracking);
		
		uLog.registrarEventoMin(session.getId(), "Consulta reporte pedidos/tracking");
		
			return mapping.findForward("ok");

		
	
	}

}
