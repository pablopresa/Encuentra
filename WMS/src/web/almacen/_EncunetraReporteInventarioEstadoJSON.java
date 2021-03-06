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

public class _EncunetraReporteInventarioEstadoJSON extends Action 
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
		DatosInventarioStatus estado = Logica.DatosInventarioStatus(us.getDeposito(), idEmpresa);
				
		String jsonData2 =" { \"data\":[{\"desde\":\""+estado.getDesde()+"\", \"hasta\":\""+estado.getHasta()+"\", \"cantidad\":\""+estado.getCantidad()+"\", \"UnidadesHora\":\""+estado.getUnidadesHora()+"\", \"porcentaje\":\""+estado.getPorcentaje()+"\"}]} ";
		
		System.out.println(jsonData2);
		
		request.setAttribute("json", jsonData2);
		return mapping.findForward("ok");
			
	}
	
	

}
