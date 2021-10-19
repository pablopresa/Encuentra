package web.mantenimiento;

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

import beans.Usuario;
import beans.encuentra.Ojo;
import beans.encuentra.Sector;



public class _EncuentraAltaSectorII extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
 		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		try 
		{
			Sector sec = (Sector) session.getAttribute("sector");
			List<Ojo> ojos = (List<Ojo>) session.getAttribute("ojosLista");
			
			Logica.encuentraAltaOjo(ojos, idEmpresa);
			request.setAttribute("menError", "Alta Correcta");
			uLog.registrarEventoMin(session.getId(), "Alta Estanteria: "+sec.getId());

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			session.setAttribute("menError", "Sucedio un error");
			uLog.registrarEventoMin(session.getId(), "Error al dar de Alta Estanteria");
			return mapping.findForward("ok");

		}

		
		
		
		return mapping.findForward("mensajeAOK");
	}
}
