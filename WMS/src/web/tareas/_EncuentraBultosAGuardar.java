package web.tareas;

import java.util.ArrayList;
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
import dataTypes.Bulto_ReposicionPicking;
import dataTypes.DataIDDescripcion;
import dataTypes.PosicionesPosibles;
import dataTypes.ReposicionPicking;
import dataTypes.Ubicacion_ReposicionPicking;
import logica.Logica;
import logica.Utilidades;

public class _EncuentraBultosAGuardar extends Action {

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica logica = new Logica();
		
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		
		ReposicionPicking repPicking =(ReposicionPicking) session.getAttribute("reposicionPicking");
		String idArticulo = repPicking.getIdArticulo();
		
		
		int ultiUbicacion = (Integer) session.getAttribute("ultiUbicacion");   
		List<PosicionesPosibles> posicionesPosibles =(List<PosicionesPosibles>) session.getAttribute("posicionesPosibles");
				
		int deposito = Integer.parseInt(uLog.getDeposito());
		int cantSeleccionada = Integer.parseInt(request.getParameter("confirmarCantidad"));
		
		String idOjoDestino = request.getParameter("confirmarOjo");
		if(idOjoDestino.equals("")) {
			idOjoDestino = request.getParameter("posicionSeleccionada");
		} else {
			
			int existe = logica.encuentraExisteUbica(idOjoDestino, idEmpresa, deposito); // BUSCAR POR DEPOSITO TAMBIEN
			if (existe == 0) {
				request.setAttribute("menError","El ojo indicado no existe"); // ver tema del deposito
				
				return mapping.findForward("mover");// lo vuelve a mandar al mismo
			} 
		}
		
		
		logica.encuentraMoverOjos(idOjoDestino,idArticulo,cantSeleccionada,uLog.getNumero(),idEmpresa);
		uLog.registrarEventoMin(session.getId(), "Alta en OjosTienenArticulos, cantidad: "+cantSeleccionada+", en el ojo: "+idOjoDestino+", en el articulo: "+idArticulo);
		logica.encuentraBajaArticulosOjos(cantSeleccionada,idArticulo, "2", idEmpresa);
		logica.IngresarMovimientoArticuloTipo("2",idOjoDestino, idArticulo,cantSeleccionada,uLog.getNumero(),"REP",idEmpresa);
		logica.MoverReposicionPicking(idArticulo,cantSeleccionada,repPicking.getIdTarea());
	
		
		
		repPicking.setCantidadMovida(repPicking.getCantidadMovida()+cantSeleccionada);
		posicionesPosibles = logica.getPosicionesPosibles(idArticulo,ultiUbicacion,deposito);
		int recorrido = 0;
		if(posicionesPosibles!=null && !posicionesPosibles.isEmpty())
		{
			for(PosicionesPosibles rp : posicionesPosibles) {
				if(rp.getIdOjo().equals(idOjoDestino)) {
					recorrido = rp.getRecorrido();
					break;
				}
			}
		}
		request.setAttribute("ultiUbicacion",recorrido);
		if(repPicking.getCantidadBajada() - repPicking.getCantidadMovida() == 0) {  // si termino				
				return mapping.findForward("tarea");// lo manda al siguiente articulo		
		} else {
			uLog.registrarEventoMin(session.getId(), "Segundo formulario ya que no guardo todo de una el articulo: "+idArticulo+" y guardo "+cantSeleccionada);
			return mapping.findForward("mover");// lo vuelve a mandar al mismo
		}      

	}
}













