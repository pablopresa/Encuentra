package web.expedicion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.FactoryLogica;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.DepositoAdmin;
import beans.encuentra.Ruta;

public class DarRutas extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		
		
		
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String para = request.getParameter("paraQuien");
		if(para==null)
		{
			para="0";
		}
		int id=0;
		List<Ruta> rutas = null;
		
		
		if(!para.equals("0"))
		{
			id= Integer.parseInt(request.getParameter("id"));
			rutas = (List<Ruta>) session.getAttribute("rutas");
			for (Ruta r : rutas) 
			{
				if(r.getIdRuta()==id)
				{
					request.setAttribute("rutaSel", r);
					break;
				}
			}
			
		}
		else
		{
			
			FactoryLogica  Logica = new FactoryLogica();
			rutas = Logica.darRutas(idEmpresa, 0);
			
		}
		
	
		
		
		
		
		
		
		
		session.setAttribute("paraR", para);
		session.setAttribute("rutas", rutas);
		
		return mapping.findForward("ok");
	
	
	}

}
