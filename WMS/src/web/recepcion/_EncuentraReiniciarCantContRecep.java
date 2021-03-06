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
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.RecepcionExpedicion;
import beans.encuentra.Remito;
import beans.encuentra.RemitoLinea;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;

public class _EncuentraReiniciarCantContRecep extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		
		List<RecepcionExpedicion> recepcionablesSEL = (List<RecepcionExpedicion>) session.getAttribute("recepcionablesS");
		
		
		for (RecepcionExpedicion r : recepcionablesSEL) 
		{
			for (DocumentoEnvio d : r.getDocumentos()) 
			{
				for (RemitoLinea l : d.getLineas()) 
				{
						l.setCantidadVerificada(0);
				}
			}
		}
		
		session.setAttribute("recepcionablesS", recepcionablesSEL);
		
		
		
		return mapping.findForward("ok");
		
		
		
		
		
	
	}
	
	

}
