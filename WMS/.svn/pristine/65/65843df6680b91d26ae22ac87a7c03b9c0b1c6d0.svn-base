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


public class _EncuentraSeguridadUsuariosAlta extends Action 
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
		 				 		
		 		String opcion = request.getParameter("opcion");
		 		String altas = request.getParameter("altas");
		 		List<Integer> altasUser = new ArrayList<>() ;
		 		int grupo=-1;
		 		try{
		 			grupo =(Integer) session.getAttribute("grupoX");			 		
		 		}
		 		catch(Exception e){
		 			request.setAttribute("menError", "Debe ingresar un grupo");
		 			uLog.registrarEventoMin(session.getId(), "(Seg_Usuarios) Error: debe ingresar un grupo.");
					return mapping.findForward("ok");
		 		}
		 		
				try{
					String[] valores = altas.split(" ");
					int id=0;
					for(String v:valores){
						try{
							id=Integer.parseInt(v);
							altasUser.add(id);
						}
						catch(Exception e){
							
						}
						
					}
							
					Logica.AsignarQuitarUsuarioAGrupo(altasUser, grupo,opcion, idEmpresa);
					for(Integer l:altasUser){
						uLog.registrarEventoMin(session.getId(), "(Seg_Usuarios) Usuario: "+l+", Grupo: "+grupo+", Opcion: "+opcion);
					}
					
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

