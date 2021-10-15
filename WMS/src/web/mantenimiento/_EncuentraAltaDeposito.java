package web.mantenimiento;

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
import beans.encuentra.DepositoAdmin;

public class _EncuentraAltaDeposito extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		String mensaje = "";
		try
		{
			HttpSession session = request.getSession();
 Logica Logica = new Logica();
 
			int numero = Integer.parseInt(request.getParameter("id"));
			String descripcion = request.getParameter("descripcion");
			String direccion = request.getParameter("direccion");
			int login = Integer.parseInt(request.getParameter("login"));//login
			
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
		
			DepositoAdmin depoA = new DepositoAdmin();
			
			depoA.setId(numero);
			depoA.setNombre(descripcion);
			depoA.setDireccion(direccion);
			depoA.setLogin(login);
			
			request.setAttribute("depoA", depoA);
			
			int idDepo = Logica.encuentraAltaDepoA(depoA,idEmpresa);
			
			uLog.registrarEventoMin(session.getId(), "Alta/Update deposito:  "+ depoA.getId()+"|"+depoA.getNombre()+"|"+depoA.getDireccion()+"|"+depoA.getLogin());
			
			mensaje = "Alta Correcta";
			
			
			List<DepositoAdmin> depositos = Logica.encuentraDarDepositosAdmin(9000, idEmpresa);
			session.setAttribute("depositosA", depositos);
			
			return mapping.findForward("ok");
			
		}
		catch (Exception e) 
		{
			mensaje = "Los Valores numericos no están correctamente expresados o vacios";
			request.setAttribute("menError", mensaje);
			return mapping.findForward("ok");
		}
		
		
		
		
	
	
	}

}
