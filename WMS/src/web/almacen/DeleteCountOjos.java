package web.almacen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import logica.Logica;
import logica.Utilidades;

public class DeleteCountOjos extends Action
{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica logica = new Logica();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String articulosOjosAll = request.getParameter("articulos");
		
		String[] articulosOjos = articulosOjosAll.split(",");
		
		for(String art : articulosOjos) {
			
			String[] articuloOjo = art.split("-");
			 
			logica.borrarConteoOjo(articuloOjo[1], articuloOjo[0], uLog.getIdEmpresa());
		}

		return mapping.findForward("ok");
	}
}
