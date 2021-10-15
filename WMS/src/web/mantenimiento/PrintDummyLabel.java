package web.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.bulto;
import beans.encuentra.IPrint;
import beans.encuentra.Ojo;



public class PrintDummyLabel extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		response.setContentType( "application/pdf" );
		try 
		{
			
			
			int cant = Integer.parseInt(request.getParameter("cant"));
			
			String descripcion = request.getParameter("name");
			
			Utilidades u = new Utilidades();
			
			List<Integer> listaIds=u.darSeries(cant, "BULTOS_"+descripcion);
			
			List<bulto>  bultos = new ArrayList<>();

			for (Integer id : listaIds) 
			{
				bulto b = new bulto("DUMMY"+id,descripcion,false,0,0,0,0.0,false,"",uLog.getNumero(),"",idEmpresa);
				bultos.add(b);
			}
			
			Logica l = new Logica();
			
			l.CrearBultos(bultos, idEmpresa);
			
			
			IPrint ip = new IPrint();
			String pathPDF = ip.ImprimirEtiquetasBultosR(bultos, "etiquetasDummy",uLog);
		
		}
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		return mapping.findForward("pdfEtiqueta");
	}
}
