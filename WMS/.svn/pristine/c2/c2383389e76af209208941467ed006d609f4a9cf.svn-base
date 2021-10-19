package web.recepcion;

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

import dataTypes.DataRecepcion;

import beans.Usuario;


public class _EncuentraDarRecepcionesContadas extends Action {
	
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
		
		String[]estados = {"3","1"};
		
		List<DataRecepcion>recepciones=Logica.darRecepcionesEstados(estados,idEmpresa);
		session.setAttribute("sf", "0");
		
		List<DataRecepcion>recepcionesVan = new ArrayList<>();
		
		for (DataRecepcion r : recepciones) 
		{
			if(r.getCantidadContada()==0 && r.getCantidadRecepcionada()==0)
			{
				recepcionesVan.add(r);
			}
			
			if(r.getCantidadContada()>r.getCantidadRecepcionada())
			{
				recepcionesVan.add(r);
			}
		}
		
		session.setAttribute("recepciones", recepcionesVan);
		
		return mapping.findForward("ok");
		
	}

}



























