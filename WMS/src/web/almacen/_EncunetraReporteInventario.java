package web.almacen;

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

import beans.DatosInventario;
import beans.DatosInventarioStatus;
import beans.Usuario;

public class _EncunetraReporteInventario extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Usuario us = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(us);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		List<DatosInventario> datos = new ArrayList<>();
		DatosInventarioStatus estado = null;
		
		try{		
			estado = Logica.DatosInventarioStatus(us.getDeposito(), idEmpresa);
			datos = Logica.DatosInventario(us.getDeposito(), idEmpresa);
			us.registrarEventoMin(session.getId(), "Usuario consulta detalle de inventario");
			
		}catch (Exception e) {
			us.registrarEventoMin(session.getId(), "Error al realizar consulta de detalle de inventario");
		}
		
		session.setAttribute("inventarioEstado", estado);
		session.setAttribute("inventario", datos);
		return mapping.findForward("ok");
			
	}
	

}
