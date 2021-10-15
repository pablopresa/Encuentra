package web.expedicion;

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



public class _ReportePedidosPendientesExpedicion extends Action 
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
		
		String fechas = request.getParameter("fini");
		String destinos = request.getParameter("destinos");
		
		String req = request.getQueryString();
		
		String fechaI ="";
		String fechaF = "";
		
		if(fechas!=null)
		{
			String []fechaIF = fechas.split(" - ");
		
			fechaI = fechaIF[0];
			fechaF = fechaIF[1];
	
			fechaI = fechaI+" 00:00:00";
			fechaF = fechaF+" 23:59:59";						
		}
		
		String[] values;
		if(req!=null){
			values = req.split("&");
			destinos =  buscarFiltro("destinos", values);
		}
		
		
		List<DataIDDescripcion> pedidosPendientes = Logica.PedidosPendientesExpedicion(fechaI, fechaF,destinos, idEmpresa);
		
		session.setAttribute("pedidosP", pedidosPendientes);
		
		uLog.registrarEventoMin(session.getId(), "Consulta pedidos pendientes de expedición");
		
			return mapping.findForward("ok");

		
	
	}
	public String buscarFiltro (String buscado, String[]values)
	{
		 String retorno= "";
		 System.out.println(values);
		 for (int i = 0; i < values.length; i++) 
		 {
			 if(values[i].contains(buscado))
			 {
				 try
				 {
					 String valor = values[i].split("=")[1];
					 retorno+=valor+",";
				 }
				 catch (Exception e)
				 {
					 
				 }
				 
			 }
		 }
		
		 if(!retorno.equals(""))
		 {
			 return retorno.substring(0, retorno.length()-1);
		 }
		 return retorno;
	}
}

						


