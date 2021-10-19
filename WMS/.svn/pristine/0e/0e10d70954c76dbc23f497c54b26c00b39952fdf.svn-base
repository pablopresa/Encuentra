package web.almacen;

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

public class _EncuentraCantidadOjo extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String error ="ATENCION";
		String idOjo = request.getParameter("idOjo");
		idOjo = idOjo.toUpperCase();
		
		int cantidad = 0;
		
		try
		{
			cantidad = Integer.parseInt(request.getParameter("cantOjo"));
		}
		catch (NumberFormatException e)
		{
			request.setAttribute("menError", error+ " Cantidad incorrecta");
			return mapping.findForward("ok");
		}
		
		
	
		int deposito = Integer.parseInt(uLog.getDeposito());
		int existe = Logica.encuentraExisteUbica(idOjo, idEmpresa, deposito);
		
		if(existe==1)
		{
			Logica.encuentraCantOjos(idOjo, cantidad,uLog.getNumero(),idEmpresa);
			request.setAttribute("menError", "Se guardó la cantidad correctamente");
			
		}
		else
		{
			request.setAttribute("menError", "No existe la ubicacion en el sistema");
		}
		
		
		return mapping.findForward("ok");
	}

}
