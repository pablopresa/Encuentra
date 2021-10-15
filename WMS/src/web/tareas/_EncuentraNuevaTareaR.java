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
import dataTypes.DataIDDescripcion;
import dataTypes.DataRecepcion;



public class _EncuentraNuevaTareaR extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		try 
		{
			Utilidades util = new Utilidades();
			
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			
			int idSinc = Integer.parseInt((request.getParameter("idSinc")));

			List <DataIDDescripcion> usuariosOperarios = Logica.encuentraDaroperarios(idEmpresa);
			List <DataIDDescripcion> usuariosSuper = Logica.encuentraDarSuper(idEmpresa);
			List <DataIDDescripcion> tiposTarea = Logica.encuentraDarTiposTarea(idEmpresa);
			

			session.setAttribute("operarios", usuariosOperarios);
			session.setAttribute("supervisores", usuariosSuper);
			session.setAttribute("tiposTarea", tiposTarea);
			
			int idRec = Integer.parseInt(request.getParameter("id"));
			
			List<DataRecepcion> recepciones = (List<DataRecepcion>) session.getAttribute("recepciones");
			
			
			for (DataRecepcion d : recepciones) 
			{
				if(d.getId()==idRec)
				{
					session.setAttribute("recepcionSel", d);
					break;
				}
			}
			
			
			

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		return mapping.findForward("ok");
	}
}
