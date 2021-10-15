package web.expedicion;

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

import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import beans.Usuario;



public class _SaveEnvioEcommerceEncuentra extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
		try 
		{
			
			int depoEnvio = (Integer) session.getAttribute("depoSel");
			String arregloPedidos = request.getParameter("destino");
			
			Usuario us = (Usuario) session.getAttribute("uLogeado");
			PropertiesHelper pH=new PropertiesHelper("configuracion");
			pH.loadProperties();		  
		    int depoEC = Integer.parseInt(pH.getValue("depositoWEB"));
			
			Usuario ulog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(ulog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			
			String arregloPedidosAr [] = arregloPedidos.split(",");
			List<DataIDDescripcion> pedidosSave = new ArrayList<>();
			for (String p : arregloPedidosAr) 
			{
				try
				{
					String desc = p.split(":")[0].trim();
					int id = Integer.parseInt(p.split(":")[1]); // cantidad
					DataIDDescripcion d = new DataIDDescripcion(id,desc);
					pedidosSave.add(d);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}	
				
			Logica.guardarEnvioEcommerce(depoEnvio,pedidosSave,us,depoEC,2,idEmpresa);
		}
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		return mapping.findForward("ok");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
