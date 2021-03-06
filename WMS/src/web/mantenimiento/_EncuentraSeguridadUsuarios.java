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


public class _EncuentraSeguridadUsuarios extends Action 
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
					List<DataIDDescripcion> users = Logica.ListaUsuarios(0, idEmpresa);
					session.setAttribute("lstUsuarios", users);
					
					//grupos
					List<DataIDDescripcion> grupos = new ArrayList<>();
					grupos = Logica.darListaDataIdDescripcion("seg_grupos", idEmpresa);
					grupos.remove(0);
					session.setAttribute("lstGrupos", grupos);
					
					int grupo=0;
					try{
						String req=request.getParameter("grupo");
						grupo = Integer.parseInt(req);							
					}
					catch(Exception e){
						request.setAttribute("menError", "Debe ingresar un grupo");
						return mapping.findForward("ok");
					}
					
					//usuarios
					List<DataIDDescripcion> grupoUsuarios=new ArrayList<>();
					grupoUsuarios= Logica.ListaUsuarios(grupo, idEmpresa);
					request.setAttribute("grupoUsuarios", grupoUsuarios);
					session.setAttribute("grupoX", grupo);
					
				}
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
	
	
	
	
		
		}

