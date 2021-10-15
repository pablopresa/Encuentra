package web.recepcion;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
import logica.Logica;
import logica.Utilidades;


public class _EncuentraPrintPackingRecepcion extends Action 
{
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String prov = request.getParameter("proveedorSel");
		session.setAttribute("proveedorOC", prov);
		
		List<VORecepcionSinOrden> listaPacking = new LinkedList<VORecepcionSinOrden>();
		int idRece = 0;
		try
		{
			idRece = Integer.parseInt(request.getParameter("idRecepcion"));
		}
		catch (Exception e)
		{
			idRece = (Integer) session.getAttribute("idRecepcion");
		}
		
		listaPacking = Logica.darPackingDeRecepcion(idRece,idEmpresa); 
		
		session.setAttribute("idRecepcion", idRece);
		session.setAttribute("listaPacking", listaPacking);
			
		return mapping.findForward("ok");
		
	}

}