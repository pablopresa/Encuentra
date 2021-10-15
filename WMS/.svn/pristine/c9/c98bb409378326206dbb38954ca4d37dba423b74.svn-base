package web.mantenimiento;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.Sector;

public class _EncuentraDarEstanterias extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
 		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		
		List<Sector> sectores = Logica.encuentraDarSectores(uLog.getIdEmpresa());
		
		
		session.setAttribute("sectoresL", sectores);
		
		return mapping.findForward("ok");
	
	
	}

}
