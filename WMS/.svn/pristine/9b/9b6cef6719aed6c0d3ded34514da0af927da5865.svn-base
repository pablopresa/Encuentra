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



public class _EncuentraEliminarSector extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
	Usuario uLog = (Usuario) session.getAttribute("uLogeado");
	Utilidades util = new Utilidades();
	int idEmpresa = util.darEmpresa(uLog);
	if(idEmpresa==0)
	{
		return mapping.findForward("LOGIN");
	}
		try 
		{
			int id = Integer.parseInt(request.getParameter("idTipo"));
			
			
			
			boolean tieneMerca = Logica.encuentraPuedeBajaEstanteria(Integer.toString(id), idEmpresa);

			if(tieneMerca)
			{

				request.setAttribute("menError", "La estantería que está intentando eliminar tiene mercadería en sus ubicaciones, por favor mueva esa mercadería e intente de nuevo.");
				uLog.registrarEventoMin(session.getId(), "Baja Estanteria, no se pudo eliminar la estanteria: "+id+". Tiene mercadería.");
				return mapping.findForward("no");
			}
			else{

				boolean pudo =	Logica.encuentraBajaSector(id, idEmpresa);
				if(pudo)
				{
					request.setAttribute("menError", "Estanteria Eliminada Correctamente ");
					uLog.registrarEventoMin(session.getId(), "Baja Estanteria: "+id);
					return mapping.findForward("ok");
				}
				else
				{
					request.setAttribute("menError", "No se ha podido eliminar ");
					uLog.registrarEventoMin(session.getId(), "Baja Estanteria, no se pudo eliminar la estanteria: "+id);
					return mapping.findForward("ok");
				}

			}

			
			

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			e.printStackTrace();
			
			request.setAttribute("menError", "Error grave: "+ e.getMessage());
			return mapping.findForward("ok");

		}

		
		
	}
}
