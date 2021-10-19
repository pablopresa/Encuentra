package web.recepcion;

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

import dataTypes.DataArticuloCantBarra;
import dataTypes.DataRecepcion;
import beans.Usuario;


public class _EncuentraDarRecepcionesContadasSF extends Action {
	
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
		
		
		
		List<DataRecepcion>recepciones=Logica.darRecepcionessf(idEmpresa);
		/*Gson prettyJson = new Gson();
		String json = prettyJson.toJson(recepciones);
		json = json.replace("\n", "");
		json = json.replace("\"", "'");*/
		session.setAttribute("sf", "1");
		session.setAttribute("recepciones", recepciones);
		//session.setAttribute("recepcionesJson", json);
		request.setAttribute("sf", true);
		
		return mapping.findForward("ok");
		
	}

}



























