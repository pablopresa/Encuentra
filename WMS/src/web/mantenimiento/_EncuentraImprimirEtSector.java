package web.mantenimiento;

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
import beans.encuentra.Ojo;
import beans.encuentra.Sector;



public class _EncuentraImprimirEtSector extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		response.setContentType( "application/pdf" );
		try 
		{
			Sector sec = (Sector) session.getAttribute("sector");
			List<Ojo> ojos = (List<Ojo>) session.getAttribute("ojosLista");
			String grande=request.getParameter("idEtiqueta");
			
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			
			Logica.encuentraAltaOjo(ojos, idEmpresa);
			
			
			
			if(grande!=null && grande.equals("on"))
				ImpresionesPDF.ImprimirEtiquetasGrandes(ojos,sec.getDescripcion(), idEmpresa);
			else
				ImpresionesPDF.ImprimirEtiquetas(ojos,sec.getDescripcion(), idEmpresa);
			return mapping.findForward("pdfEtiqueta");
			

		} 
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		
	}
}
