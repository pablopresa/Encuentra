package web.almacen;


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


import beans.OjosEnCero;
import beans.Usuario;
import dataTypes.DataIDDescripcion;


public class _EncuentraDarOjosEnCero extends Action 
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
		
		//traigo las zonas
		String query = "SELECT idZona, Descripcion FROM zonas WHERE idempresa = "+idEmpresa+";";
		List<DataIDDescripcion> zonas = Logica.darListaDataIdDescripcionMYSQLConsulta(query);
		zonas.remove(0);
		session.setAttribute("lstZonas", zonas);
		
		String zona="";
		try{
			zona=request.getParameter("zona");
			request.setAttribute("zonaSeleccionada", zona);
		}
		catch(Exception e){
			request.setAttribute("menError", "Debe ingresar una zona");
			return mapping.findForward("ok");
		}
		
		List<OjosEnCero> listaO = Logica.OjosEnCero(zona,idEmpresa);
		
		uLog.registrarEventoMin(session.getId(), "Usuario consulta ojos en cero");
		
		session.setAttribute("listaO", listaO);
		
		
			return mapping.findForward("ok");
		}
		
	
}


