package web.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import beans.Fecha;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataArticulo;

public class AltaBultoI extends Action 
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
		
		ServletContext context = request.getSession().getServletContext();
		Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
		
		
		List<DataDescDescripcion> barrasBultos = new ArrayList<>();
		
		Set<String> keys = artBarra.keySet();
        for(String key: keys)
        {
        	barrasBultos.add(new DataDescDescripcion(key, artBarra.get(key)));
        }
		
        session.setAttribute("barrasBultos", barrasBultos);
        
		
		
		return mapping.findForward("ok");
	}
}



