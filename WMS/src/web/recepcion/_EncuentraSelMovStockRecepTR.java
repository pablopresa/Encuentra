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

public class _EncuentraSelMovStockRecepTR extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		String menError = "";
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		List<Remito> remitos =  (List<Remito>) session.getAttribute("remitosTRPen");
		List<Remito> remitosIn =  (List<Remito>) session.getAttribute("remitosTRIn");
		
		boolean esLista = false;
		String listaDocs = request.getParameter("listaDocs");
		if(listaDocs!=null)
		{
			esLista = true;
		}
		
		
		Utilidades utl = new Utilidades();
		String rSel = "";
		boolean depoYdoc = false;
		if(!esLista)		
		{
			rSel = request.getParameter("nroDoc");
			
			if(rSel.contains("_"))
			{
				depoYdoc = true;
			}
			else
			{
				if(utl.tryParse(rSel)==0)
				{
					menError ="No se encontro ningun documento con el numero "+rSel+" con destino a "+uLog.getDeposito();
					request.setAttribute("menError", menError);
					return mapping.findForward("ok");
				}
			}
		}
		
		
		
		List<Remito> remitosPosibles = new ArrayList<>(); 
		
		for (Remito r : remitos) 
		{
			if(!esLista)
			{
				if(depoYdoc)
				{
					
					if((r.getIdOrigen()+"_"+r.getNumeroDoc()).equals(rSel))
					{
						boolean agregarlo = true;
						for (Remito r2 : remitosIn) //controlo que no este adentro ya
						{
							if((r2.getIdOrigen()+"_"+r2.getNumeroDoc()).equals(rSel))
							{
								agregarlo = false;
							}
						}
						if(agregarlo)
						{
							remitosIn.add(r);
						}
						session.setAttribute("remitosTRIn", remitosIn);
						
						return mapping.findForward("ok");	
					}
				}
				else
				{
					int idRemito = utl.tryParse(rSel);
					if(r.getNumeroDoc()==idRemito)
					{
						boolean agregarlo = true;
						for (Remito r2 : remitosIn) //controlo que no este adentro ya
						{
							if((r2.getIdOrigen()+"_"+r2.getNumeroDoc()).equals(r.getIdOrigen()+"_"+r.getNumeroDoc()))
							{
								agregarlo = false;
							}
						}
						if(agregarlo)
						{
							remitosPosibles.add(r);
						}
						
						
					}
					
					
				}
			}//if no es lista
			else
			{
				String on = request.getParameter(r.getIdOrigen()+"_"+r.getNumeroDoc());
				System.out.println(on);
				if(on!=null && on.equals("on"))
				{
					boolean agregarlo = true;
					for (Remito r2 : remitosIn) //controlo que no este adentro ya
					{
						if((r2.getIdOrigen()+"_"+r2.getNumeroDoc()).equals(rSel))
						{
							agregarlo = false;
						}
					}
					if(agregarlo)
					{
						remitosIn.add(r);
					}
				}
			}
			
			
		}//for
		
		
		if(esLista)
		{
			session.setAttribute("remitosTRIn", remitosIn);
			
			return mapping.findForward("ok");
		}
		
		if(remitosPosibles.size()==1)
		{
			remitosIn.add(remitosPosibles.get(0));
			session.setAttribute("remitosTRIn", remitosIn);
			
			return mapping.findForward("ok");
		}
		else if(remitosPosibles.size()>1)
		{
			request.setAttribute("remitosTRIn", remitosPosibles);
			menError ="se encontró mas de un documento con el numero "+rSel+" y destino a "+uLog.getDeposito();
			request.setAttribute("menError", menError);
			return mapping.findForward("ok");
		}
		else if(remitosPosibles.size()==0)
		{
			menError ="No se encontro ningun documento con el numero "+rSel+" con destino a "+uLog.getDeposito();
			request.setAttribute("menError", menError);
			
			return mapping.findForward("ok");
		}
		
		
		return mapping.findForward("ok");	
		
		
		
		
	
	}
	
	

}
