package web.almacen;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
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
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataOjoArticulo;

public class DarDetalleBulto extends Action 
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
		
		String bulto = request.getParameter("bulto");		
		
		List<bultoContenido> detalle;
		try {
			bulto b = Logica.BuscarBulto(bulto, idEmpresa);
			detalle = new ArrayList<>(b.getContenido().values());
			request.setAttribute("OTAdetalle", detalle);
			request.setAttribute("bulto", bulto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("ok");
		
		
	
	}
		

}
