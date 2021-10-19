package web.mantenimiento;

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

import beans.Usuario;
import dataTypes.DataIDDescripcion;

public class _EncuentraDarTiposU extends Action 
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
		
		List<DataIDDescripcion> tipos = Logica.encuentraDarTiposUsuarios(idEmpresa);
		List<DataIDDescripcion> deposito = Logica.encuentraDarDepositosConLogin(idEmpresa);
		deposito.remove(0);
		List<DataIDDescripcion> grupos = Logica.darListaDataIdDescripcion("seg_grupos", idEmpresa);
		grupos.remove(0);
		
		session.setAttribute("lstGruposAsociarPermisos", grupos);
		session.setAttribute("tiposU", tipos);
		session.setAttribute("depositoU", deposito);
		
		return mapping.findForward("ok");
	
	
	}

}
