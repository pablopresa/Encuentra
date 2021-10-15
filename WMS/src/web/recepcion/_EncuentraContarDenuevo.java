package web.recepcion;

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


public class _EncuentraContarDenuevo extends Action 
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
		
		int recep = Integer.parseInt(request.getParameter("rec"));
		String barra = request.getParameter("bar");
		
		Logica.encuentraContarNuevamente(recep, barra,idEmpresa);
		
		//int idtarea = (Integer) session.getAttribute("idTarea");
		//Logica.encuentraAltaEventoTarea(idtarea, 0, uLog.getNumero(),0,idEmpresa);
		
		
		return mapping.findForward("ok");
		
	}

}
































