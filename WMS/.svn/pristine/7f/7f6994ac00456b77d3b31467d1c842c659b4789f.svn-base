package web.expedicion;



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
import beans.encuentra.Envio;


public class _EncuentraDarEntregas extends Action 
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
				
				
				String depo = request.getParameter("depo");
				String fechas = request.getParameter("fini");
				String fechaI = "";
				String fechaF = "";
				
				if(fechas!= null && !fechas.equals("")){
					fechaI = fechas.split(" - ")[0]+" 00:00:00";
					fechaF = fechas.split(" - ")[1]+" 23:59:59";
				}
				
				List<Envio> envios = null;
				int deposito;
				deposito = Integer.parseInt(depo);
				
				envios = Logica.encuentraDarEnvios(fechaI,fechaF,1,null,deposito, idEmpresa);					
				
				
				session.setAttribute("envios",envios);
				
				return mapping.findForward("ok");
			}
	
	
		
		}

