package web.mantenimiento;

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
import beans.encuentra.DepositoAdmin;

public class _EncuentraDarDeposAdmin extends Action 
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
		String depoPrincipal = Logica.darParametroEmpresa(idEmpresa, 4);
		int depoParsed = 0;
		try{
			depoParsed = Integer.parseInt(depoPrincipal);
			}
		catch (Exception e){}
		List<DepositoAdmin> depositos = Logica.encuentraDarDepositosAdmin(depoParsed, idEmpresa);
		
		
		session.setAttribute("depositosA", depositos);
		
		return mapping.findForward("ok");
	
	
	}

}
