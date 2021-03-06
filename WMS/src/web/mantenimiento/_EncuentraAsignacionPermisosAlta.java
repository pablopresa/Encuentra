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


public class _EncuentraAsignacionPermisosAlta extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				try{
					HttpSession session = request.getSession();
 Logica Logica = new Logica();
	Usuario uLog = (Usuario) session.getAttribute("uLogeado");
	Utilidades util = new Utilidades();
	int idEmpresa = util.darEmpresa(uLog);
	if(idEmpresa==0)
	{
		return mapping.findForward("LOGIN");
	}
					//List<DataIDDescripcion> grupos = (List<DataIDDescripcion>)session.getAttribute("lstGrupos");
										
					
					int grupo=0;
					try{
						grupo = Integer.parseInt(request.getParameter("grupoPermisos"));
					}
					catch(Exception e){
						request.setAttribute("menError", "Debe ingresar un grupo");
						uLog.registrarEventoMin(session.getId(), "(Seg_Grupos) Error: debe ingresar un grupo.");
						return mapping.findForward("ok");
					}
					String permisos = request.getParameter("permisos");
					List<String> permisosIds=new ArrayList<>();
					
					String[] valores = permisos.split(",");
					
					String salidaCheck = "[";
					boolean pri=true;
					for(String v:valores)
					{
						if(pri)
						{
							salidaCheck +="'"+v+"'";
							pri = false;
						}
						else
						{
							salidaCheck +=",'"+v+"'";
						}
						permisosIds.add(v);
					}
					
					salidaCheck+="]";
					
					String salidaCheckS = (String) session.getAttribute("datosArbolCheck");
					System.out.println(salidaCheck);
					System.out.println(salidaCheckS);
					
					if(salidaCheckS.equals(salidaCheck))
					{
						//nada que hacer son iguales
					}
					else
					{
						Logica.AsignarPermisosAGrupo(permisosIds, grupo, idEmpresa);
						uLog.registrarEventoMin(session.getId(), "(Seg_Grupos) Se modificaron los permisos del grupo "+grupo);
					}
					
					
				
				
				}
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
	
		
	
	
		
		}

