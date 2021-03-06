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
import dataTypes.DataIDDescripcion;


public class _EncuentraFiltrarZona extends Action 
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
		 				
				try{
					List<DataIDDescripcion> listaEstanterias = Logica.ListaZonas(0, idEmpresa);
					session.setAttribute("lstEstanterias", listaEstanterias);
					
					int zona=0;
					try{
						String req=request.getParameter("zona");
						session.setAttribute("zonaSeleccionada", req);
						zona = Integer.parseInt(req);							
					}
					catch(Exception e){
						request.setAttribute("menError", "Debe ingresar una zona");
						return mapping.findForward("ok");
					}
					
					List<DataIDDescripcion> grupoEstanterias=new ArrayList<>();
					grupoEstanterias= Logica.ListaZonas(zona, idEmpresa);
					request.setAttribute("grupoEstanterias", grupoEstanterias);
				}
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
		}
