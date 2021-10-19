package web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import logica.Logica;
import logica.Utilidades;


public class _EncuentraAsociarBarras extends Action 
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
		
		String articulo = request.getParameter("art");
		String barra = request.getParameter("bar");
		
		boolean pude = Logica.GenerarVinculo(barra, articulo, idEmpresa);
		
		String mensaje = "";

		if (pude) {
			mensaje = "Se agrega nueva barra (" + barra + ") para el articulo: " + articulo;
			uLog.registrarEventoMin(session.getId(), mensaje);
		} else {
			mensaje = "Error al intentar agregar nueva barra (" + barra + ") para el articulo: " + articulo;
			uLog.registrarEventoMin(session.getId(), mensaje);
		}
		
		request.setAttribute("menError", mensaje);
		
		if(request.getParameter("barraManual")!=null)
		{
			return mapping.findForward("nuevaBarra");
		}
		
		return mapping.findForward("ok");
		
	}

}