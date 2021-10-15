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

public class _EncuentraEliminarDepositosA extends Action 
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
		
		String para = request.getParameter("paraQuien");
		int id = Integer.parseInt(request.getParameter("id"));
		
		List<DepositoAdmin> depositos = (List<DepositoAdmin>) session.getAttribute("depositosA"); 
		
		DepositoAdmin deposito = null;
		boolean puede = true;
		for (DepositoAdmin depositoAdmin : depositos) 
		{
			if(depositoAdmin.getId()==id)
			{
				puede = Logica.encuentraEliminarDepositoAdmin(id, idEmpresa);
				if(!puede)
				{
					request.setAttribute("menError", "depósito "+depositoAdmin.getNombre()+" no se puede eliminar, ya tiene transacciones en el sistema");
					uLog.registrarEventoMin(session.getId(), "Error al bajar deposito:  "+ id);
				}
				else
					uLog.registrarEventoMin(session.getId(), "Baja deposito:  "+ id);
				break;
			}
		}
		
		depositos = Logica.encuentraDarDepositosAdmin(9000, idEmpresa);
		session.setAttribute("depositosA", depositos);
		
		
		return mapping.findForward("ok");
	
	
	}

}
