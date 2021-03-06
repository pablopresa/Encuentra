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


public class _EncuentraReposicionPicking extends Action //CLASE QUE EJECUTA LA ACCION
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
		session.setAttribute("ultimaUbicacion",ultimaUbicacion); // Lo seteo por si va de una al segundo formulario
		ReposicionPicking reposicionPicking = Logica.creoReposicionPicking(idTarea,ultiUbicacion, idEmpresa); //le llega ubicacion 0 y retorna null, por eso termina la tarea
		
		
		if (reposicionPicking.getIdArticulo() != null) { // SI EL OBJETO NO ES NULO
			
			int cB =reposicionPicking.getCantidadBajada();
			int cM = reposicionPicking.getCantidadMovida();
			if(cB - cM > 0) { // SI TODAVIA QUEDAN COSAS POR GUARDAR  ( SIGO EN EL SEGUNDO FORMULARIO)
				uLog.registrarEventoMin(session.getId(), "Entra al segundo formulario ya que no se termino todavia");
				session.setAttribute("ultiUbicacion",ultiUbicacion);
				session.setAttribute("reposicionPicking", reposicionPicking);
				session.setAttribute("ojosOriginal", reposicionPicking.getListaUbicaciones());
				int dep = Integer.parseInt(uLog.getDeposito());
				List<PosicionesPosibles> posicionesPosibles = Logica.getPosicionesPosibles(reposicionPicking.getIdArticulo(),ultiUbicacion,dep); // esto puede devolver vacio
				session.setAttribute("posicionesPosibles",posicionesPosibles);
				
				return mapping.findForward("mover");			
				
				

			} else { //SI NO QUEDAN COSAS POR GUARDAR ( PASO AL SIGUIENTE)
	//			if( reposicionPicking.getListaUbicaciones().size() > 0) {  // SI LA LISTA DE OJOS TIENE ALGO
					uLog.registrarEventoMin(session.getId(), "Entra al primer formulario ya que hay ubicaciones");
					session.setAttribute("ultiUbicacion",ultiUbicacion);
					session.setAttribute("reposicionPicking", reposicionPicking);	
					session.setAttribute("ojosOriginal", reposicionPicking.getListaUbicaciones());
					Gson gson = new Gson(); // serializa a json 
					String json = gson.toJson(reposicionPicking);
					request.setAttribute("json", json);
					return mapping.findForward("ok");
	/*			} else {  // deberia pasar al siguiente
					//TERMINA LA TAREA SI LA LISTA DE OJOS ES NULA
				//	return mapping.findForward("ok");
					
					Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(),0,idEmpresa);
					request.setAttribute("menError","Finalizo la tarea");
					List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
					session.setAttribute("tarMob", tarMob);
					uLog.registrarEventoMin(session.getId(), "Se termino la tarea: "+idTarea+" Porque la linea no tiene ubicaciones");
					return mapping.findForward("listaTareas"); // lo mando a la lista de tareas   
				}  */
			}	
					
			
			
		} else { // TERMINA LA TAREA SI TRAE UN OBJETO NULO
			Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(),0,idEmpresa);
			request.setAttribute("menError","Finalizo la tarea");
			List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
			session.setAttribute("tarMob", tarMob);
			uLog.registrarEventoMin(session.getId(), "Se termino la tarea: "+idTarea);
			return mapping.findForward("listaTareas"); // lo mando a la lista de tareas 
		}
		
				
	}

}