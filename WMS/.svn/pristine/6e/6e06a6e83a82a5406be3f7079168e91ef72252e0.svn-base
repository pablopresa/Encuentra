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


public class _EncuentraAsociarEstanteriasAZona extends Action 
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
		 		List<Integer> altasEstanterias = new ArrayList<>() ;
		 		int zona=-1;
		 		try{
		 			String zonaSTR = (String) session.getAttribute("zonaSeleccionada");
		 			zona = Integer.parseInt(zonaSTR);			 		
		 		}
		 		catch(Exception e){
		 			request.setAttribute("menError", "Debe ingresar una zona");
					return mapping.findForward("ok");
		 		}
		 		
				try{
					String[] valores = altas.split(" ");
					int id=0;
					for(String v:valores){
						try{
							id=Integer.parseInt(v);
							altasEstanterias.add(id);
						}
						catch(Exception e){
							
						}
						
					}
							
					Logica.AsignarQuitarEstanteriasAZona(altasEstanterias, zona, opcion, idEmpresa);
					
					List<DataIDDescripcion> grupoEstanterias=new ArrayList<>();
					grupoEstanterias= Logica.ListaZonas(zona, idEmpresa);
					request.setAttribute("grupoEstanterias", grupoEstanterias);
					session.setAttribute("grupoX", zona);
								 		
				}
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
	
	
	
	
		
		}

