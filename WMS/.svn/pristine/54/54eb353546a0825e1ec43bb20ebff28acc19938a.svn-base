package web.picking;

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
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataOjoArticuloCantidad;

public class _EncuentraRePrintTicketPicking extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String error ="";
		int idpicking = 0;
		try {
			idpicking = Integer.parseInt(request.getParameter("idpicking"));
		} catch (Exception e) {
			request.setAttribute("menError", "Debe ingresar un numero de picking");
			return mapping.findForward("ok");
		}
		List<DataLineaRepo> data = Logica.encuentraDarArtRepos(idpicking, "",5, uLog.getNumero());
		if(data == null || data.isEmpty()) {
			request.setAttribute("menError", "Usted no realizo este picking. Solo el ejecutor del picking puede reimprimir el ticket.");
			return mapping.findForward("ok");
		}
		Utilidades u = new Utilidades();
		u.etiquetas_verificacion_destinos(data,uLog,idpicking,5,"");
		
		
		return mapping.findForward("ok");
	}

}
