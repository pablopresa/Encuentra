package web.picking;

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
import beans.encuentra.DataPickingS;



public class _EncuentraDarPickingS extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		try 
		{
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}

			String menError = (String) session.getAttribute("mensajeVS");
			String destino = request.getParameter("destino");
			if(destino==null){
				destino = (String) session.getAttribute("destino");
			}
			session.setAttribute("destino", destino);
			
			int idDeposito = Integer.parseInt(uLog.getDeposito());
			
			List<DataPickingS> pickings = Logica.encuentraDarPickingS(destino,idEmpresa, idDeposito);
			
			
			session.setAttribute("pickingsS", pickings);
			if(destino.equals("diferencias"))
			{
				uLog.registrarEventoMin(session.getId(), "Consultando pickings con diferencias");
				return mapping.findForward("Diferencia");
			}
			else if(destino.equals("packing"))
			{
				uLog.registrarEventoMin(session.getId(), "Consultando pickings para packing");
				return mapping.findForward("Packing");
				
			}
			else if(destino.equals("verifica"))
			{
				uLog.registrarEventoMin(session.getId(), "Consultando pickings para verificar");
				session.setAttribute("accio", "ver");
				if(menError!=null){
					request.setAttribute("menError", menError);
				}
				return mapping.findForward("Verifica");
				
			}
			else if(destino.equals("remite"))
			{
				uLog.registrarEventoMin(session.getId(), "Consultando pickings para remitir");
				session.setAttribute("accio", "rem");
				return mapping.findForward("Verifica");
			}
			else if(destino.equals("activos"))
			{
				uLog.registrarEventoMin(session.getId(), "Consultando pickings activos");
				return mapping.findForward("Activos");
			}
			else if(destino.equals("externos"))
			{
				uLog.registrarEventoMin(session.getId(), "Consultando pickings externos");
				return mapping.findForward("externos");
			}
			else
			{
				if(menError!=null){
					request.setAttribute("menError", menError);
				}
				uLog.registrarEventoMin(session.getId(), "Consultando pickings para verificar");
				return mapping.findForward("ok");
			}
			

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		
	}
}
