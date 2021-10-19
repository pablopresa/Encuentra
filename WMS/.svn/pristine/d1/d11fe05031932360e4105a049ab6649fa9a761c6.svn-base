package web.mantenimiento;



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
import beans.encuentra.IPrint;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataIDDescripcion;


public class _EncuentraPrintLabelDestinos extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
		 		HttpSession session = request.getSession();
				Usuario uLog = (Usuario) session.getAttribute("uLogeado");
				Utilidades util = new Utilidades();
				int idEmpresa = util.darEmpresa(uLog);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
		 				 		
				try{
					Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
					String etiq = "";
					
					int id = Integer.parseInt(request.getParameter("idDestiny"));
					String nombre = request.getParameter("descDestiny");
					
					DataIDDescripcion data = new DataIDDescripcion(id,nombre);
					
					//resumen picking	
					etiq = IPrint.etiquetas_verificacion_destinos(data, uLog);
					api.PutColaImpresion("destiny"+data.getId()+"_"+idEmpresa, etiq, 0, 1,uLog.getIdEquipo(),idEmpresa,1);
				}
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
	
	
	
	
		
		}

