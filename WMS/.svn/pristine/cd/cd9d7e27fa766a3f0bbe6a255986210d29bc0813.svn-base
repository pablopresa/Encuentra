package web.tareas;

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

public class _EncuentraActionEventTarea extends Action 
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
		
		
		
		String tar = request.getParameter("idTarea");
		String action = request.getParameter("action");
		String state = request.getParameter("state");
		String devolverA = request.getParameter("devolverA");
		
		
		System.out.println(tar +" " +action + " "+ state + " " + devolverA);
		
		try//escribimos el fichero de la repo
		{
			int accion = Integer.parseInt(action);
			int idTarea = Integer.parseInt(tar);
			
			if(accion==0)//eliminar
			{
				Logica.encuentraBajaTarea(idTarea,idEmpresa);
			}
			else if(accion==1)//modificarEstado
			{
				int estado = Integer.parseInt(state);
				Logica.encuentraAltaEventoTarea(idTarea, estado, uLog.getNumero(),0,idEmpresa);
			}
			
		
			
						
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
			
		
		return mapping.findForward(devolverA);
			
			
		
			
	}

}
