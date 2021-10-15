package web.picking;

import java.util.ArrayList;
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

import beans.ArticuloLineaReposicion;
import beans.Usuario;

public class _EncuentraAltaPickingVM extends Action{

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
		
		
		
		List<ArticuloLineaReposicion> articulosIn = new ArrayList<>();
		
		List<ArticuloLineaReposicion> articulos = (List<ArticuloLineaReposicion>) session.getAttribute("articulosRepo");
		
		
		for (ArticuloLineaReposicion ar : articulos) 
		{
			String param = request.getParameter(ar.getSinc().getId()+"-"+ar.getIdArticulo());
			if(param!=null && param.equals("on"))
			{
				int cant = Integer.parseInt(request.getParameter(ar.getSinc().getId()+ "-"+ar.getIdArticulo()+"-"+ar.getOrigen().getId()+"-"+ar.getDestino().getId()));
				ar.setToPick(cant);
				articulosIn.add(ar);
				
			}
		}
		
		
		Logica.pasarAPickingVM(articulosIn,idEmpresa);
		
			return mapping.findForward("ok");
		
		
		
		
		
		
	
	}
	
	
	
	
	
}








































