package web.util;

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

public class _EncuentraDarTareasUsuario extends Action {
	
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
		
		//int idDeposito = Integer.parseInt(uLog.getDeposito());
		List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
		

		uLog.registrarEventoMin(session.getId(), "Buscando tareas del usuario tiene " +tarMob.size()+ " Tareas" );
		
		
		session.setAttribute("tarMob", tarMob);
		
		return mapping.findForward("ok");


	}
}
