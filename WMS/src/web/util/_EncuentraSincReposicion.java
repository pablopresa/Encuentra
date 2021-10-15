package web.util;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.TestUnit2;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import dataTypes.DataIDDescripcion;
import logica.Logica;

public class _EncuentraSincReposicion extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		try {
			TestUnit2 sinc = new TestUnit2();
			//SincronizaEncuentra.sincro();
			Logica Logica = new Logica();
			Usuario u = Logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");
			
			int idEmpresa = u.getIdEmpresa();
			sinc.SincronizarDistribuciones(idEmpresa);
			
			List<DataIDDescripcion> distribuciones = Logica.darListaDataIdDescripcion("vista_rep_art_distribucion",idEmpresa);
			HttpSession session = request.getSession();
			session.setAttribute("distribuciones", distribuciones);
			
		} catch (Exception e) {
		}
		
		
		return mapping.findForward("ok");
		
	
	
	}
	
	
	
}
