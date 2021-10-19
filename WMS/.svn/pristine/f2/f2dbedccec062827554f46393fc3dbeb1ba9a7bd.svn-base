package web.util;



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
import dataTypes.DataIDDescripcion;


public class _EncuentraDistribucionesVisual extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
				Logica Logica = new Logica();
				Utilidades util = new Utilidades();
				
				Usuario usu = (Usuario) session.getAttribute("uLogeado");		
				int idEmpresa = util.darEmpresa(usu);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				String distr = request.getParameter("distr");
				String fechas = request.getParameter("fini");
				String fechaI = "";
				String fechaF = "";
				
				if(fechas!=null)
				{
					if (!fechas.equals("")){
					String []fechaIF = fechas.split(" - ");
					
					fechaI = fechaIF[0];
					fechaF = fechaIF[1];
					
					fechaI = fechaI+" 00:00:00.000";
					fechaF = fechaF+" 00:00:00.000";
					}
					
				}
				
				
				
					List<DataIDDescripcion> distribuciones = Logica.DarDistribucionesVisual(fechaI, fechaF);
					session.setAttribute("distVS", distribuciones);
				
					List<DataIDDescripcion> ventas = Logica.DarVtasSinDistribucionesVisual(fechaI, fechaF,idEmpresa);
					session.setAttribute("vtasVS", ventas);
				
				
					
					
				request.setAttribute("fini", fechas);
				
				
				return mapping.findForward("ok");
			}
	
	
		
		}

