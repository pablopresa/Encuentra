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

import dataTypes.DataIDDescDescripcion;

import beans.Fecha;
import beans.Usuario;

public class _EncuentraVerLogSincJSON extends Action{

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
		
		
		
		List<DataIDDescDescripcion> eventos = Logica.DarListaEventos(idSinc,idEmpresa);
		
		List<DataIDDescDescripcion> eventosSinc = (List<DataIDDescDescripcion>) session.getAttribute(String.valueOf(idSinc));
		
		StringBuilder json = new StringBuilder();
		json.append("[");
		
		if(eventosSinc==null || eventosSinc.isEmpty())
		{
			for (DataIDDescDescripcion e : eventos) 
			{
				agregar(json, e);
				
			}
			
		}
		else
		{
			if(eventosSinc.size()!=eventos.size())
			{
				for (DataIDDescDescripcion ev : eventos) 
				{
					if(!buscar(ev.getId(), eventosSinc))
					{
						agregar(json, ev);
					}
				}
			}
		}
		
		String jsStr = json.toString();
		jsStr = jsStr.substring(0, jsStr.length()-1);
		jsStr+="]";
		
		session.setAttribute(String.valueOf(idSinc),eventos);
		if(jsStr.equals("]"))
		{
			request.setAttribute("", jsStr);
		}
		else
		{
			request.setAttribute("json", jsStr);
		}
		
		
		return mapping.findForward("ok");
		
	
	
	}
	
	public StringBuilder agregar (StringBuilder json, DataIDDescDescripcion ev)
	{
		Fecha f = new Fecha(ev.getDesc());
		json.append("{\"fecha\": \""+f.FechaMostrable()+"\",\"evento\": \""+ev.getDescripcion()+"\",\"Porcent\": \""+ev.getPorcentaje()+"\"},");
		
		return json;
	}
	
	public boolean buscar (int id, List<DataIDDescDescripcion> lista)
	{
		for (DataIDDescDescripcion es : lista) 
		{
			if(es.getId()==id)
			{
				return true;
			}
		}
		return false;
	}
	
}





















