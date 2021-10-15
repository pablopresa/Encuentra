package web.mantenimiento;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.encuentra.TipoSector;

public class _EncuentraCopiarTipo extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
		
		
		String idTipo = request.getParameter("idTipo"); // si es un alta el tipo es 999999
		
		TipoSector elTipo = new TipoSector();
		
		List <TipoSector> tips = (List<TipoSector>) session.getAttribute("tiposS");
		
		for (TipoSector t : tips) 
		{
			if(t.getIdTipo() == Integer.parseInt(idTipo))
			{
				elTipo = t;
				break;
			}
		}
		
		//los cuelgo
		request.setAttribute("ancho", elTipo.getAncho());
		request.setAttribute("alto", elTipo.getAlto());
		request.setAttribute("profundidad", elTipo.getProfundidad());
		request.setAttribute("descripcion", elTipo.getDescripcion());
		
		
		
		return mapping.findForward("ok");
	
	
	}

}
