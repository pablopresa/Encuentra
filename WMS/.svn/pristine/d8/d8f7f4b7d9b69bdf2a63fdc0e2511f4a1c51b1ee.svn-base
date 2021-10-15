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

public class CloseConteoStore extends Action 
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

		try
		{
			String idConteo = request.getParameter("idConteo");
			int idC = 0;
			try {
				idC = Integer.parseInt(idConteo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(idC!=0) {
				log.closeConteo(idC, idEmpresa);
				int indice = -1;
				List<ConteoTiendas> conteos = (List<ConteoTiendas>) session.getAttribute("conteosTienda");
				for(ConteoTiendas c: conteos) {
					indice++;
					if (c.getIdConteo()==idC) {
						break;
					}
				}
				conteos.remove(indice);
				session.setAttribute("conteosTienda",conteos);
			}	
			
		}
		catch (Exception e) 
		{
			
		}		
		return mapping.findForward("ok");
	}

}
