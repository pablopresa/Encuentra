package web.mantenimiento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.LeerHTML;
import logica.Logica;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;


import beans.Usuario;
import beans.encuentra.TipoSector;

public class _EncuentraEditarTipo extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
		
		String idTipo = request.getParameter("idTipo");
		
		
		request.setAttribute("idEdita", idTipo);
	
		return mapping.findForward("ok");
	
	
	}

}
