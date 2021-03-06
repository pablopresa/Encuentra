package web.recepcion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;

import beans.encuentra.Remito;
import beans.encuentra.RemitoLinea;

import cliente_rest_Invoke.Call_WS_APIENCUENTRA;

import dataTypes.DataIDDescripcion;

import helper.PropertiesHelper;
import jsonObjects.SendMail;

public class BorrarRecepcion extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		String menError = "";
		
		HttpSession session = request.getSession();
		Logica l = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		int idR = Integer.parseInt(request.getParameter("idR"));
		
		int afectados = l.DeleteReception(idR,idEmpresa);
		if(afectados<=0){
			request.setAttribute("menError", "Esta recepcion no se puede eliminar, ya se empezo a controlar las unidades.");
		}
		
		return mapping.findForward("ok");
	
	}
	
	

}
