package web.tareas;

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
import beans.encuentra.Tarea;

public class _EncuentraDarListaTareas extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
	
		
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		int idSinc = Integer.parseInt((request.getParameter("idSinc")));
		List<Tarea> tareas;
		//id=0
		//sub=0
		//est=99
		//Ver Todas
		int idMain;
		int idSub;
		int estado;
		try
		{
			idMain = Integer.parseInt(request.getParameter("id"));
		}
		catch (Exception e) 
		{
			idMain =0;
		}
		try
		{
			idSub = Integer.parseInt(request.getParameter("sub"));
		}
		catch (Exception e) 
		{
			idSub =0;
		}
		try
		{
			estado = Integer.parseInt(request.getParameter("est"));
		}
		catch (Exception e) 
		{
			estado =0;
		}
		
		
		
		
		
		
		
		
		
		tareas = Logica.encuentraDarTareas(estado, idSub, idMain,idEmpresa);
	
		
	
		session.setAttribute("tareas", tareas);
		
		
		
		if(estado==99)
		{
			return mapping.findForward("okLlistadoSG");
		}
		else
		{
			return mapping.findForward("okLlistado");
		}
			
		
		
	}

}
