package web.util;

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
import beans.encuentra.DataArticulo;



public class _EncuentraAltaMedArti extends Action 
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
		try 
		{

			
			/*
			 * 
						<input type="text" class="input" name="base" id="base" value="${articulo.codBase}"/>
					  	<input type="text" class="input" name="ancho" id="ancho"/>
					  	<input type="text" class="input" name="alto" id="alto"/>
					  	<input type="text" class="input" name="prof" id="prof"/>
						
					</div>
			 */
			String codB = request.getParameter("base");
			String alto = request.getParameter("alto");
			String ancho = request.getParameter("ancho");
			String prof = request.getParameter("prof");
			String afBase = request.getParameter("chkCod");
			
			boolean afectaBase = true;
			
			if(!afBase.equals("on"))
			{
				afectaBase = false;
			}
			
			int anch=0;
			int alt=0;
			int pro=0;
			
			//DataArticulo d = Logica.encuentraCodArticulo(cod);
			
			//hay que validar los numeros
			String error = "";
			try 
			{
				error = ancho;
				anch = Integer.parseInt(ancho);
				
				error = alto;
				alt = Integer.parseInt(alto);
				
				error = prof;
				pro = Integer.parseInt(prof);
				
				
				
			} 
			catch (Exception e) 
			{
				request.setAttribute("menError", "Error de conversión cerca de "+ error);
				return mapping.findForward("no");
			}
			
			
			DataArticulo d = (DataArticulo) session.getAttribute("articulo");
			d.setAltoCaja(alt);
			d.setAnchoCaja(anch);
			d.setProfCaja(pro);
			
			Logica.encuentraActualizaMedidaArticulo(d, afectaBase,idEmpresa);
			
			

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		return mapping.findForward("ok");
	}
}
