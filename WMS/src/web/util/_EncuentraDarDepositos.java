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
import dataTypes.DataIDDescripcion;



public class _EncuentraDarDepositos extends Action 
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
			
			
			
			List<DataIDDescripcion>depositos = Logica.encuentraDarDepositosConLogin(idEmpresa);
			
			
			session.setAttribute("depositos", depositos);
			
			String jq = (String) session.getAttribute("jquery");
			
			if(jq!=null)
			{
				String en = request.getParameter("destino");
				if(en!=null)
				{
					return mapping.findForward("okJQMEntregas");
				}
				else
				{
					return mapping.findForward("okJQM");
				}
			}
			else
			{
				return mapping.findForward("ok");
			}

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			e.printStackTrace();
			
			request.setAttribute("menError", "Error grave: "+ e.getMessage());
			return mapping.findForward("ok");

		}

		
		
	}
}
