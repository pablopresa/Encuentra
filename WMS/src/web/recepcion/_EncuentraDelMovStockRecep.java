package web.recepcion;

import java.util.ArrayList;
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
import beans.encuentra.Remito;
import dataTypes.DataIDDescripcion;

public class _EncuentraDelMovStockRecep extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		String menError = "";
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		List<Remito> remitosInOld =  (List<Remito>) session.getAttribute("remitosIn");
		
		List<Remito> remitosIn = new ArrayList<>();
		
		String rSel = request.getParameter("nroDoc");
		
		for (Remito r : remitosInOld) 
		{
			if(!(r.getIdOrigen()+"_"+r.getNumeroDoc()).equals(rSel))
			{
				remitosIn.add(r);
			
			}
			
		}
		
		
		session.setAttribute("remitosIn", remitosIn);
		
		return mapping.findForward("ok");
		
		
		
		
		
	
	}
	
	

}
