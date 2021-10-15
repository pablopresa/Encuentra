package web.mantenimiento;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import dataTypes.DataIDDescripcion;

public class _EncuentraDarEquiposTrabajo extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		Logica logica = new  Logica();
		HttpSession session = request.getSession();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		String esColector = request.getParameter("mob");
		
		List<DataIDDescripcion> equipos = logica.encuentraDarEquiposTrabajo(uLog.getDeposito(),uLog.getIdEmpresa());
		equipos.remove(0);
		
		session.setAttribute("equipos_trabajo", equipos);
		
		if(esColector.equals("1"))
			return mapping.findForward("mob");
			
		else
			return mapping.findForward("ok");	
	
	
	}

}
