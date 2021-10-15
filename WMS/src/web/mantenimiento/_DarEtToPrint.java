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



public class _DarEtToPrint extends Action 
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
			
			
			/*
			 * codigo de prueba
			 */
			
			
			
			//fin codigo de prueba

			String idOjo = request.getParameter("cod");
			
			List<Ojo> ojos = Logica.encuentraEtiqueta(idOjo, idEmpresa);
			
			if(ojos.isEmpty())
			{
				request.setAttribute("menError", "El codigo ingresado no está en el sistema");
				uLog.registrarEventoMin(session.getId(), "Error al imprimir, codigo ingresado no está en el sistema("+idOjo+")");
			}
			else
			{
				String grande=request.getParameter("idEtiqueta");
				//grande = "on";
				
				//Logica.encuentraAltaOjo(ojos);
				
				if(grande!=null && grande.equals("on"))
				{
					ImpresionesPDF.ImprimirEtiquetasGrandes(ojos,ojos.get(0).getDescripcionEstanteria(), idEmpresa);
					uLog.registrarEventoMin(session.getId(), "Imprime Etiqueta Grande Ojo("+idOjo+")");
				}
				else
				{
					ImpresionesPDF.ImprimirEtiquetas(ojos,ojos.get(0).getDescripcionEstanteria(), idEmpresa);
					uLog.registrarEventoMin(session.getId(), "Imprime Etiqueta Ojo("+idOjo+")");
				}

				return mapping.findForward("pdfEtiqueta");
				
				//request.setAttribute("eti", ojos.get(0));
				//return mapping.findForward("okE");
			}
			

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		return mapping.findForward("ok");
	}
}
