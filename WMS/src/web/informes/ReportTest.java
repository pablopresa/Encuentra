package web.informes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
import dataTypes.DataIDDescripcion;
import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;


public class ReportTest extends Action //CLASE QUE EJECUTA LA ACCION
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
		
		
		int desde = util.tryParse(request.getParameter("desde"));
		int hasta = util.tryParse(request.getParameter("hasta"));
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		List<ReporteTest> lista = new ArrayList<>();
		
		System.out.println("facil");
		lista = Logica.reporteTests(desde, hasta);
		
		request.setAttribute("lista", lista);
		
			
		return mapping.findForward("ok");
		
	}

}