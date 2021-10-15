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

public class _EncuentraSelDocsRecepExp extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		

		String error ="ATENCION"; 		
		try
		{
			int idDepoRecibe = Integer.parseInt(uLog.getDeposito());
			
			List<RecepcionExpedicion> recepcionables = (List<RecepcionExpedicion>) session.getAttribute("recepcionablesE");
			List<RecepcionExpedicion> recepcionablesSEL = (List<RecepcionExpedicion>) session.getAttribute("recepcionablesS");
				
			
			String docSel = request.getParameter("nroDoc");
			if(docSel!=null && !docSel.equals(""))
			{
				int idDoc = Integer.parseInt(docSel);
				recepcionablesSEL = addTolista(recepcionables,idDoc,recepcionablesSEL,null);
				session.setAttribute("recepcionablesS", recepcionablesSEL);
				return mapping.findForward("ok");
			}
					
			
			for (RecepcionExpedicion r : recepcionables) 
			{
				String on = request.getParameter(r.getIdEnvio()+"");
				
				if(on!=null && !on.equals(""))
				{					
					recepcionablesSEL = addTolista(null,r.getIdEnvio(),recepcionablesSEL,r);				
				}				
			}
			session.setAttribute("recepcionablesS", recepcionablesSEL);
			return mapping.findForward("ok");		
			
		}
		catch (Exception e) 
		{
			return mapping.findForward("LOGIN");
		}
				
	}
	
	private List <RecepcionExpedicion> addTolista (List <RecepcionExpedicion> recepcionables, int buscado, List <RecepcionExpedicion> recepcionablesSEL,RecepcionExpedicion re)
	{
		if(recepcionables!=null)
		{
			for (RecepcionExpedicion r : recepcionables) 
			{
				if(r.getIdEnvio()==buscado)
				{
					boolean agrego = true;
					for (RecepcionExpedicion rs : recepcionablesSEL) 
					{
						if(rs.getIdEnvio()==buscado)
						{
							agrego=false;
							break;
						}
					}
					
					if(agrego)
					{
						recepcionablesSEL.add(r);
					}
					break;
				}
			}
		}
		else
		{
			boolean agrego = true;
			for (RecepcionExpedicion rs : recepcionablesSEL) 
			{
				if(rs.getIdEnvio()==re.getIdEnvio())
				{
					agrego=false;
					break;
				}
			}
			
			if(agrego)
			{
				recepcionablesSEL.add(re);
			}
		}
		
		
		return recepcionablesSEL;
	}

}
