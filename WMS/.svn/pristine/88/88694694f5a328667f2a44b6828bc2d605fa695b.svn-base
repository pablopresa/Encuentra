package web.tareas;

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
import beans.encuentra.Tarea;

public class _EncuentraJSON extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		int idSinc = Integer.parseInt((request.getParameter("idSinc")));
		
		List<Tarea> tareas;
		//id=0
		//sub=0
		//est=99
		//Ver Todas
		int idMain;
		int idSub;
		int estado;
		idMain =0;
		idSub =0;
		estado = -5;
		
		
		tareas = Logica.encuentraDarTareas(estado, idSub, idMain, idEmpresa);
		
		System.out.println("llamaron a json");
		
		StringBuilder json = new StringBuilder();
		
		json.append("[");
		
		for (Tarea t : tareas) 
		{
			json.append("{\"idTar\": \""+t.getMain()+"\",\"Porcent\": \""+t.getPorcentaje()+"\"},");
		}
		
		String jsStr = json.toString();
		jsStr = jsStr.substring(0, jsStr.length()-1);
		jsStr+="]";
		
		
		request.setAttribute("json", jsStr);
		return mapping.findForward("ok");
	
	
	}
}
