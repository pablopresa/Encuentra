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

import beans.Tareas;
import beans.Usuario;

public class _EncuentraDarTareasYProductividad extends Action 
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
		
		//int idSinc = Integer.parseInt((request.getParameter("idSinc")));
		
		String filtro = request.getParameter("filtro");
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
		
		List<Tareas> tareas = Logica.darTareas(filtro, fechaI, fechaF,idEmpresa);
		
		session.setAttribute("tareas", tareas);
		
		
		
		uLog.registrarEventoMin(session.getId(), "Consulta reporte tareas y productividad");
		
			return mapping.findForward("ok");

		
	
	}

}
