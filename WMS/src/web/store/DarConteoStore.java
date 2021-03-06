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
import beans.encuentra.ConteoTiendas;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class DarConteoStore extends Action 
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


		/*
		 * fini
		 * deposito
		 * agrpTienda
		 */
		
		try
		{
			int idDeposito = Integer.parseInt(request.getParameter("deposito"));
			String rango = request.getParameter("fini");
			String abiertos = request.getParameter("agrpTienda");
			boolean terminados = false;
			if(abiertos!=null && abiertos.equals("1"))
			{
				terminados=true;
			}
			
			String fechaI ="";
			String fechaF = "";
			if(rango!=null)
			{
				if(!rango.equals("Sin Rango")){
					String []fechaIF = rango.split(" - ");
				
					fechaI = fechaIF[0];
					fechaF = fechaIF[1];
			
					fechaI = fechaI+" 00:00:00";
					fechaF = fechaF+" 23:59:59";
				}
			}
				
			int idConteo = 0;
			List<ConteoTiendas> conteos = log.darConteoTiendas(idDeposito,fechaI,fechaF,terminados,idConteo,idEmpresa);
			
			session.setAttribute("conteosTienda",conteos);
			
			
			
			
		}
		catch (Exception e) 
		{
			
		}		
		return mapping.findForward("ok");
	}

}
