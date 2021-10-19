package web.recepcion;



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
import dataTypes.DataRecepcion;


public class _EncuentraDarRecepcionesAgendadas extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				@SuppressWarnings("unused")
				HttpSession session = request.getSession();
				Logica Logica = new Logica();
				Usuario uLog = (Usuario) session.getAttribute("uLogeado");
				Utilidades util = new Utilidades();
				int idEmpresa = util.darEmpresa(uLog);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				String mensaje = request.getParameter("menError");
				List<DataRecepcion> recepciones = Logica.encuentraDarRecepcionesPendientes(idEmpresa); 
				
				for (DataRecepcion d : recepciones) 
				{
					String fecha = d.getAgenda().replace(" ", "T").substring(0, 19);
					d.setAgenda(fecha);
				}
				
				session.setAttribute("recepciones", recepciones);
				if(mensaje!=null)
				{
					request.setAttribute("menError", mensaje);
				}
				
				return mapping.findForward("ok");
			}
	
	
		
		}

