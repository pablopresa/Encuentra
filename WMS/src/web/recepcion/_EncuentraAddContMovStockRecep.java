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
import dataTypes.DataIDDescripcion;

public class _EncuentraAddContMovStockRecep extends Action 
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
		
		
		
		int nroDoc = Integer.parseInt(request.getParameter("doc"));
		String afectacion = request.getParameter("entregaAfceta");
		String art = request.getParameter("articulo");
		
		for (Remito r : remitosIn) 
		{
			for (RemitoLinea l : r.getLineas()) 
			{
				if(r.getNumeroDoc()==nroDoc && l.getIdArticulo().equals(art) && l.getEntrega().equals(afectacion))
				{
					l.setCantidadVerificada(l.getCantidadVerificada()+1);
				}
			}
			
			
		}
		
		
		session.setAttribute("remitosIn", remitosIn);
		
		return mapping.findForward("ok");
		
		
		
		
		
	
	}
	
	

}
