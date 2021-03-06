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

import dataTypes.DataIDDescripcion;
import dataTypes.TareasPendientes;
import beans.Usuario;



public class _EncuentraTareasPendientes extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		 
		 
		 String idP = request.getParameter("idPick");
		 List<TareasPendientes> tareasPendientes;		 
		try 
		{
			if(idP!=null){
				int idPick = Integer.parseInt(idP);
				Logica.deshacerTarea(idPick, idEmpresa);
								
			}
			tareasPendientes = Logica.DarTareasPendientes(idEmpresa);
			request.setAttribute("tarPendientes", tareasPendientes);
			
			return mapping.findForward("ok");
		}
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			request.setAttribute("menError", "Sucedio un error");
			e.printStackTrace();
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}	
			
			


		
		
	}
	
	
}















