package web.picking;

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


public class _EncuentraConfirmarTodoPicked extends Action 
{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		Logica Logica = new Logica();
		try
		{
			int idPicking = Integer.parseInt(request.getParameter("idPicking"));
			HttpSession session = request.getSession();
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			Logica.ConfirmarPickingAll(idPicking,true,idEmpresa);
			
			request.setAttribute("menError", "Picking:todas las unidades pickeadas al 100%");
			
		}
		
		catch(Exception e)
		{
			request.setAttribute("menError", "Ocurrió un error interno con el número de picking");
		}
		
		
		
		
		return mapping.findForward("ok");
		
		
	}

}