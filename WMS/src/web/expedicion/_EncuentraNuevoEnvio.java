package web.expedicion;

import java.util.Hashtable;
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



import dataTypes.DataIDDescripcion;
import beans.Usuario;
import beans.encuentra.Transporte;

public class _EncuentraNuevoEnvio extends Action 
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
				
				List<DataIDDescripcion> choferes = Logica.darIDescUsuarioPorTipo(5, idEmpresa);
				List<DataIDDescripcion> razones = Logica.darRazonesEnvio(idEmpresa);
				List<Transporte> transportes = Logica.darTransportes(false, idEmpresa);
				List<DataIDDescripcion> acompaniantes = Logica.darIDescUsuarioPorTipo(2, idEmpresa);
				acompaniantes.remove(0);
				
				
				
				Hashtable<Integer, DataIDDescripcion> Hchoferes = new Hashtable<>();
				Hashtable<Integer, DataIDDescripcion> Hacompaniantes = new Hashtable<>();
				Hashtable<Integer, Transporte> Htransportes = new Hashtable<>();
				
				
				
				for (DataIDDescripcion d : choferes) 
				{
					Hchoferes.put(d.getId(), d);
				}
				
				for (DataIDDescripcion d : acompaniantes) 
				{
					Hacompaniantes.put(d.getId(), d);
				}
				for (Transporte d : transportes) 
				{
					Htransportes.put(d.getId(), d);
				}
				
				
				session.setAttribute("choferes", choferes);
				session.setAttribute("razonesDoc", razones);
				session.setAttribute("transportes", transportes);
				session.setAttribute("Hacompaniantes", Hacompaniantes);
				session.setAttribute("Hchoferes", Hchoferes);
				session.setAttribute("Htransportes", Htransportes);
				
				
				session.setAttribute("acompaniantes", acompaniantes);
				
				
				
				return mapping.findForward("ok");
				
			}
		
		}




