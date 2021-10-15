package web.tareas;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import beans.Usuario;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataPicking;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
import dataTypes.Bulto_ReposicionPicking;
import dataTypes.DataIDDescripcion;
import dataTypes.PosicionesPosibles;
import dataTypes.ReposicionPicking;
import dataTypes.Ubicacion_ReposicionPicking;
import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;


public class _EncuentraReposicionPickingNE extends Action //CLASE QUE EJECUTA LA ACCION
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
		
		int idTarea = 0;
		try {
		idTarea = Integer.parseInt(request.getParameter("tarea"));
		int estadoTarea = Integer.parseInt(request.getParameter("estado"));	
		if (estadoTarea == 0) {
			Logica.encuentraAltaEventoTarea(idTarea, 1, uLog.getNumero(),0,idEmpresa);
		}
		} catch (Exception e) {
			idTarea =(Integer)(((ReposicionPicking) session.getAttribute("reposicionPicking")).getIdTarea());
		}
		
		int ultiUbicacion = 0;
		String ultimaUbicacion = (String) session.getAttribute("ultimaUbicacion");
		try {
		
			ultiUbicacion = Integer.parseInt(ultimaUbicacion);
			
		} catch (Exception e){
			ultiUbicacion = Logica.getUltimaUbicacion(idTarea,idEmpresa);
		} 
		
		
		ReposicionPicking reposicionPicking = (ReposicionPicking) session.getAttribute("reposicionPicking");
		Logica.noEncontradoRepo(reposicionPicking,idEmpresa);
		return mapping.findForward("ok");    
				
	}

}