package web.tareas;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.encuentra.DataLineaRepo;

public class _EncuentraFiltraRepo extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
		//paraQuien
		//idDoc
		
		
		String marca = request.getParameter("marca");
		List <DataLineaRepo> repos = (List<DataLineaRepo>) session.getAttribute("repoArt");
		
		List <DataLineaRepo> retorno = new ArrayList<>();
		
		for (DataLineaRepo s : repos) 
			{
				
				String marcaArt = s.getIdArticulo().substring(0, 4);
				
				if(marca.equals(marcaArt))
				{
					retorno.add(s);
				}
				
					
			}
			
			
			
			session.setAttribute("repoArt", retorno);
			
			return mapping.findForward("tar");
			
			
			
			
			
			
			
		
	
	
	}

}
