package web.tareas;

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

import beans.Usuario;
import beans.encuentra.DataLineaRepo;

public class _EncuentraTraeRepo extends Action 
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
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		int idSinc = Integer.parseInt((request.getParameter("idSinc")));
		
		String idDoc = request.getParameter("idDoc");
		String paraQuien = request.getParameter("paraQuien");
		String numVisual = request.getParameter("numVS");
		
		session.setAttribute("numVS", numVisual);
		
		List <DataLineaRepo> repos = Logica.darListaLineasRepoPick(Integer.parseInt(idDoc),idEmpresa);
		
		session.setAttribute("repoArt", repos);
		if(paraQuien.equals("tareas"))
		{
			session.setAttribute("idDoc", idDoc);
			List<String> marcas = new ArrayList<>();
			String marcaAnt = "";
			boolean pri = true;
			for (DataLineaRepo s : repos) 
			{
				if(pri)
				{
					marcaAnt = s.getIdArticulo().substring(0, 4); 
					marcas.add(marcaAnt);
					pri = false;
				}
				else if(!marcaAnt.equals(s.getIdArticulo().substring(0, 4)))
				{
					marcas.add(s.getIdArticulo().substring(0, 4));
				}
				
				marcaAnt = s.getIdArticulo().substring(0, 4);
			}
			
			
			for (String s : marcas) 
			{
				System.out.println(s);
			}
			session.setAttribute("marcas", marcas);
			return mapping.findForward("tar");
			
			
			
			
			
			
			
		}
		else
		{
			return mapping.findForward("ok");
		}
	
	
	}

}
