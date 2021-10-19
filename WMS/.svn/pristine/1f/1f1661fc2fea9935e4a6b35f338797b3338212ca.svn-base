package web.ecommerce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import eCommerce_jsonObjectsII.Presentaciones;
import logica.Logica;

public class _EcommerceInformeStockPrecio extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		
		Logica logica = new Logica();
		HttpSession session = request.getSession();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		String articulo = request.getParameter("articulo");
		String menError = null;
		
		if(uLog==null || logica.loginEncuentra(uLog.getNombre(), uLog.getPass(), uLog.getIdEmpresa()) == null)
			return mapping.findForward("LOGIN");
		
		try {
			List<Presentaciones> presentaciones = new ArrayList<>();
			if(articulo!=null)
				presentaciones = logica.darListaStockPrecio(articulo, uLog.getIdEmpresa());
			
			request.setAttribute("lista", presentaciones);
		}
		catch(Exception e) {
			menError = e.getMessage();
			request.setAttribute("menError", menError);
		}
		return mapping.findForward("ok");
	}
}