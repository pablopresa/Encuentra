package web.mantenimiento;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import logica.Logica;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



import beans.Usuario;


public class _EncuentraCambiarEquipoTrabajo extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Logica Logica = new Logica();
		
		int numero = uLog.getNumero();//numero
		int empresa = uLog.getIdEmpresa();//empresa
		int equipo = Integer.parseInt(request.getParameter("equipo")); 
		String esColector = request.getParameter("mob");

		
		String mensaje = "";
		
		request.setAttribute("equipo", equipo);	
		
		Logica.encuentraCambiarEquipoTrabajo(equipo, numero, empresa);
		uLog.setEquipo_trabajo(equipo);
		session.setAttribute("uLogeado", uLog);
		
		uLog.registrarEventoMin(session.getId(), "Cambia a Grupo de Trabajo "+equipo);
		
		mensaje = "Grupo de Trabajo cambiado correctamente";
		request.setAttribute("menError", mensaje);
			
		if(esColector.equals("0"))
			return mapping.findForward("ok");
		else
			return mapping.findForward("mob");
	
	}

}