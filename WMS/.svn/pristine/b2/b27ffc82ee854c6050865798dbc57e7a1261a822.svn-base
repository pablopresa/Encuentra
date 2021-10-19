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


public class _EncuentraCambiarDeposito extends Action 
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
		String deposito = request.getParameter("deposito"); //deposito
		String esColector = request.getParameter("mob");
		
		String mensaje = "";
		
		request.setAttribute("deposito", deposito);	
		
		Logica.encuentraCambiarDeposito(deposito, numero, empresa);
		uLog.setDeposito(deposito);
		session.setAttribute("uLogeado", uLog);
		
		uLog.registrarEventoMin(session.getId(), "(USU_DEP) Cambia a deposito "+deposito);
		
		mensaje = "Depósito cambiado correctamente, cierre sesión para ver los cambios";
		request.setAttribute("menError", mensaje);
		
		List<DataIDDescripcion> equipos = Logica.encuentraDarEquiposDeposito(uLog.getDeposito(),uLog.getIdEmpresa());
		equipos.remove(0);				
		session.setAttribute("equipos", equipos);
			
		if(esColector.equals("0"))
			return mapping.findForward("ok");
			
		else
			return mapping.findForward("mob");	
	
	
	}

}