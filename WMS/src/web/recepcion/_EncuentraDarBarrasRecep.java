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
import beans.encuentra.RemitoLinea;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;

public class _EncuentraDarBarrasRecep extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		String menError = "";
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		List<Remito> remitosIn =  (List<Remito>) session.getAttribute("remitosIn");
		
		String artsIN = "";
		for (Remito r : remitosIn) 
		{
			for (RemitoLinea l : r.getLineas()) 
			{
				artsIN+="'"+l.getIdArticulo()+"',";
			}
			
		}
		
		try {
			artsIN = artsIN.substring(0, artsIN.length()-1);
		} catch (Exception e) {
			request.setAttribute("menError", "No se pudieron encontrar los articulos del remito");
			return mapping.findForward("no");
		}
		
		
		
		Logica lo = new Logica();
		List<DataDescDescripcion> barrasR =  lo.darBarrasInn(artsIN, uLog.getIdEmpresa());
		
		session.setAttribute("barrasR", barrasR);
		
		return mapping.findForward("ok");
		
		
		
		
		
	
	}
	
	

}
