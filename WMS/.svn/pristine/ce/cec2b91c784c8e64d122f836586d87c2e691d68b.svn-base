package web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;



public class DarMapa extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
	
		
		//DibujarMapa.dibujarMapa();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");

		uLog.registrarEventoMin(session.getId(), "Usuario ve mapa");
			
		
		return mapping.findForward("ok");
	}
}
