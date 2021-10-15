package web.almacen;

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

public class _EncuentraArtRecepSinMov extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		
		
		String idOjo="0";
		Logica logica = new Logica();
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		List<DataIDDescripcion> list = logica.ArtRecepSinMov(idOjo, idEmpresa);
		
		request.setAttribute("art", list);

		uLog.registrarEventoMin(session.getId(), "Usuario consulta reporte Artículos Recepcionados Sin movimiento");
		
		return mapping.findForward("ok");
		
	
	}

}





























