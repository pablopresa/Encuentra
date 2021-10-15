package web.recepcion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.DataArticulo;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.Utilidades;

public class _EncuentraReporteRecepcion extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Logica logica = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		
		if(idEmpresa==0)
			return mapping.findForward("LOGIN");

		String idArticulo = request.getParameter("idArticulo");
		
		if(idArticulo==null || idArticulo.isEmpty()) {
			session.setAttribute("lista", new ArrayList<>());
			return mapping.findForward("ok");
		}
		
		List<DataArticulo> art = logica.encuentraDarArticulos(idArticulo, uLog.getIdEmpresa());
		
		if(art.isEmpty()) {
			session.setAttribute("menError", "El artículo ingresado no existe.");
			session.setAttribute("lista", new ArrayList<>());
			return mapping.findForward("ok");
		}
		
		String fechas = request.getParameter("fechas");
		
		String[] fechaIF = fechas.split(" - ");

		String fechaI = fechaIF[0];
		String fechaF = fechaIF[1];
		
		String fechaDesde = fechaI + " 00:00:00";
		String fechaHasta = fechaF + " 23:59:59";
		
		List<DataIDDescripcion> lista = logica.darReporteRecepcionCantidad(idEmpresa, idArticulo, fechaDesde, fechaHasta);
		
		if(lista.isEmpty()) {
			session.setAttribute("menError", "El artículo no tiene recepciones entre las fechas especificadas.");
			session.setAttribute("lista", new ArrayList<>());
			return mapping.findForward("ok");
		}
			
		session.setAttribute("menError", null);
		session.setAttribute("lista", lista);
		
		return mapping.findForward("ok");
	}

}
