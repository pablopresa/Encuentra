package web.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.LeerHTML;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import beans.ProcessEcommerce;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.RecepcionExpedicion;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class _EncuentraDarDocsRecepExp extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		Logica log = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		

		String error ="ATENCION"; 
		session.setAttribute("recepcionablesE", null);
		session.setAttribute("recepcionablesS", null);
		try
		{
			int idDepoRecibe = Integer.parseInt(uLog.getDeposito());
			
			List<RecepcionExpedicion> recepcionables = log.darListaEnviosRec(idDepoRecibe,idEmpresa);
			
			if(recepcionables.isEmpty())
			{
				request.setAttribute("menError", "No tiene expediciones para recibir");
				return mapping.findForward("ok");
			}
			
			List<RecepcionExpedicion> recepcionablesSEL = new ArrayList<>();			
			
			session.setAttribute("recepcionablesE", recepcionables);
			session.setAttribute("recepcionablesS", recepcionablesSEL);
			
			if(recepcionables.size()>1)
			{
				return mapping.findForward("ok");
			}
			else
			{
				recepcionablesSEL.add(recepcionables.get(0));
				session.setAttribute("recepcionablesS", recepcionablesSEL);				
				return mapping.findForward("uno");
			}			
			
		}
		catch (Exception e) 
		{
			return mapping.findForward("ok");
		}
	}

}
