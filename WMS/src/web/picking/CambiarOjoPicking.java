package web.picking;

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
import dataTypes.DataIDIDDescripcion;
import beans.Usuario;
import beans._EncuentraTomaPedidosGEx;
import beans.encuentra.DataLineaRepo;

public class CambiarOjoPicking extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica lo = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		
		
		
		String idOjo = request.getParameter("idOjo");
		
		
		DataLineaRepo dl = (DataLineaRepo) session.getAttribute("voy");
		
		
		if(!dl.getCubi().equals(idOjo))
		{
			lo.liberarReserva(dl,idOjo,idEmpresa);
		}
		
		DataIDIDDescripcion nuevaUbi = lo.DarEstanteria(idOjo, idEmpresa);
	
		
		dl.setEstnte(Integer.parseInt(nuevaUbi.getDescripcion()));
		dl.setEstnteria(nuevaUbi.getId());
		dl.setModulo(nuevaUbi.getIid());
		dl.setCubi(idOjo);
		dl.setDescEstanteria(nuevaUbi.getDescripcionB());
		
		
		session.setAttribute("voy", dl);
		
		
		
		
		return mapping.findForward("ok");
		
				
		
	
	}

}
