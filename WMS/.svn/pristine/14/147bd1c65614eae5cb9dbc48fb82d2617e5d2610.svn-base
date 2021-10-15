package web.mantenimiento;



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


public class _EncuentraAgregarNuevaZona extends Action 
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
		 		
		 		String nuevaZona= request.getParameter("nomZona");
		 				
				try{
					if(nuevaZona==null ){
						request.setAttribute("menError", "Debe ingresar un nombre para crear la zona");
						return mapping.findForward("ok");
					}
					else{
						try{
							/////////////////////ALTA Zona!!!
							if(!Logica.AltaZona(nuevaZona, idEmpresa)){
								request.setAttribute("menError", "Debe ingresar otro nombre para crear la zona");
								return mapping.findForward("ok");
							}
						}
						catch(Exception e){
							request.setAttribute("menError", "Debe ingresar otro nombre para crear la zona");
							return mapping.findForward("ok");
						}
						
						String query = "SELECT idZona, Descripcion FROM zonas WHERE idempresa = "+idEmpresa+";";
						List<DataIDDescripcion> zonas = Logica.darListaDataIdDescripcionMYSQLConsulta(query);
						zonas.remove(0);
						
						session.setAttribute("lstZonas", zonas);
					}
								 		
				}
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
	
	
	
	
		
		}

