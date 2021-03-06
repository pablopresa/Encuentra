package web.picking;

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
import beans.encuentra.DataLineaRepo;

public class _EncuentraPasaCierreTarea extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
	
		int cauntasVan = (Integer) session.getAttribute("cuantasVoy");
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		
		int idTarea = (Integer) session.getAttribute("idTarea");
		
		
		List <DataLineaRepo> noNcontrados = (List<DataLineaRepo>) session.getAttribute("noNcontrados");
		
		
		//noEncontrado
		//cuantasVoy
		//unidad
		
		
			
			for (DataLineaRepo d : noNcontrados) 
			{
				cauntasVan++;
				Logica.encuentraAltaNoncontrados(idTarea, d, 0,cauntasVan,idEmpresa);
				
			}
			
			
			//int idDeposito = Integer.parseInt(uLog.getDeposito());
			List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
			session.setAttribute("tarMob", tarMob);
			
		return mapping.findForward("ok");
			
	}

}
