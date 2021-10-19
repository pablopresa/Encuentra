package web.mantenimiento;

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

public class _EncuentraCambiarContrasena extends Action 
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
		
		int numero = uLog.getNumero();//numero
		String p1 = request.getParameter("p1");//p1
		String p2 = request.getParameter("p2");//p2
		String esColector = request.getParameter("mob");
		
		String mensaje = "";
		
		request.setAttribute("p1", p1);
		request.setAttribute("p2", p2);
		
		
		//validaciones de pass
		if(!p2.equals(p1))
		{
			mensaje = "Las contraseñas no coinciden";
			request.setAttribute("menError", mensaje);
			uLog.registrarEventoMin(session.getId(), "(USU_PWD)"+mensaje);
			
			if(esColector.equals("1"))
				return mapping.findForward("mob");
				
			else{
				request.setAttribute("html", mensaje);
				return mapping.findForward("ok");
			}
					
			
		}
		
		Usuario u = new Usuario();
		u.setNumero(numero);
		u.setPass(p1);
		
		Logica.encuentraCambiarContrasena(u, idEmpresa);
		uLog.registrarEventoMin(session.getId(), "(USU_PWD)"+mensaje);

		
		mensaje = "Contraseña cambiada correctamente";
		request.setAttribute("p1", "");
		request.setAttribute("p2","");
		request.setAttribute("menError", mensaje);
		
		if(esColector.equals("1"))
			return mapping.findForward("mob");
			
		else
			request.setAttribute("html", mensaje);
			return mapping.findForward("ok");	
	
	
	}

}
