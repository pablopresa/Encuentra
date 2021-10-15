package web.almacen;



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


public class _EncuentraDarRecorridoOjos extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
				Logica Logica = new Logica();
				Usuario uLog = (Usuario) session.getAttribute("uLogeado");
				Utilidades util = new Utilidades();
				int idEmpresa = util.darEmpresa(uLog);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				String filtro = request.getParameter("frecorrido");
				
				if(filtro!=null && filtro.equals("sin")){
					List<DataIDDescripcion> ubicaciones = Logica.darRecorrido(false, idEmpresa);
					session.setAttribute("ubicaciones", ubicaciones);
				}
				else{
					List<DataIDDescripcion> ubicaciones = Logica.darRecorrido(true, idEmpresa);
					session.setAttribute("ubicaciones",ubicaciones);
				}
					
				request.setAttribute("filtro", filtro);
				
				return mapping.findForward("ok");
			}
	
	
		
		}

