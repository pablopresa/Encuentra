package web.recepcion;

import java.util.ArrayList;
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
import dataTypes.DataArtBarraCant;
import dataTypes.DataArticuloCantBarra;
import dataTypes.DataIDDescripcion;
import dataTypes.DataRecepcion;

import beans.Usuario;


public class _EncuentraGuardarCantidadesTodoRecep extends Action 
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
		
		int recep = Integer.parseInt(request.getParameter("idRecepcion"));
		
		
		Logica.updateRecepcionConfirmarCantidadesAll(recep, uLog.getNumero(),idEmpresa);
		
		
		
		
		
		List<DataArticuloCantBarra> artFacturables = Logica.encuentraDarListaArticulosRecepcionados(recep,idEmpresa);
		session.setAttribute("artFacturables", artFacturables);
		
		
		
		return mapping.findForward("ok");
		
		
	}

}