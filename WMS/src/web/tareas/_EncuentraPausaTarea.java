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
import beans.encuentra.DataLineaListaTareasMob;

public class _EncuentraPausaTarea extends Action 
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
		String sale = request.getParameter("sale");
		
		if(sale!=null)
		{
			return mapping.findForward("menu");
		}
		try
		{
			int idTarea = (Integer) session.getAttribute("idTarea");
			
			
			Logica.encuentraAltaEventoTarea(idTarea, 4, uLog.getNumero(),0,idEmpresa);

			Integer idPicking = (Integer) session.getAttribute("idPicking");
			
			if(idPicking!=null)
			{
				
				uLog.registrarEventoHilo(session.getId(), " pausando tarea picking "+ idPicking, idPicking ,107);
			}
			else
			{
				uLog.registrarEventoHilo(session.getId(), " pausando tarea", 0, 101);
			}
			
					
			
			//int idDeposito = Integer.parseInt(uLog.getDeposito());
			List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
			session.setAttribute("tarMob", tarMob);
			
		}
		catch (Exception e)
		{
			return mapping.findForward("menu");
		}
		
		
		
	
		return mapping.findForward("ok");
	}

}
