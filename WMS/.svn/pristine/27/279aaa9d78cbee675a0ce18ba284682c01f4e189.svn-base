package web.picking;

import java.util.ArrayList;
import java.util.Hashtable;
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
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataListaPicking;
import beans.encuentra.DataPicking;



public class _EncuentraDarListaPicking extends Action 
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
			
			int idPick = Integer.parseInt(request.getParameter("idPick"));
			List<DataListaPicking> listaPicking = Logica.encuentraDarListaPicking(idPick,idEmpresa);
						
			request.setAttribute("listaPicking", listaPicking);
		}
		catch (Exception e) {
			System.out.println("Error al mostrar la lista");
		}
		
		return mapping.findForward("ok");
		
	}

}















