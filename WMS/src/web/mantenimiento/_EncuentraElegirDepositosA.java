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

import beans.encuentra.DepositoAdmin;

public class _EncuentraElegirDepositosA extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		
		String para = request.getParameter("paraQuien");
		int id = Integer.parseInt(request.getParameter("id"));
		
		List<DepositoAdmin> depositos = (List<DepositoAdmin>) session.getAttribute("depositosA"); 
		
		for (DepositoAdmin depositoAdmin : depositos) 
		{
			if(depositoAdmin.getId()==id)
			{
				request.setAttribute("depoSel", depositoAdmin);
				break;
			}
		}
		
		
		request.setAttribute("para", para);
		
		return mapping.findForward("ok");
	
	
	}

}
