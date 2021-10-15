package web.util;

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

public class _EncuentraClickTarjeta extends Action 
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
		
		int idTarjeta = Integer.parseInt(request.getParameter("idTarjeta"));
		if(idTarjeta==1){
			return mapping.findForward("artis");
		}
		else if(idTarjeta==22){
			request.setAttribute("Id", "Cantidad de Ventas");
			request.setAttribute("descripcion", "Articulo");
			request.setAttribute("lstDetalil", "Articulos sin imagenes");
		}
		else{
			request.setAttribute("Id", "Unidades");
			request.setAttribute("descripcion", "Destino");
			request.setAttribute("lstDetalil", "Pickings pendientes de tarea");
		}
		
		List <DataIDDescripcion> lista = Logica.darListaDetallTarjeta(idTarjeta);
		
		
		uLog.registrarEventoMin(session.getId(), "click en tarjeta "+ idTarjeta);
		
		if(idTarjeta==5 || idTarjeta==6){
			/*if(idTarjeta==5){
				List<DataIDDescripcion> admWeb = Logica.DiscriminarPicking71();
				lista.addAll(admWeb);
			}*/
			
			List<DataIDDescripcion> secciones = Logica.darListaDataIdDescripcion("art_seccion",idEmpresa);
			request.setAttribute("secciones", secciones);
		}
		
		request.setAttribute("lista", lista);
		request.setAttribute("tarje", idTarjeta);
		
		
		return mapping.findForward("ok");
		
	
	}

}





























