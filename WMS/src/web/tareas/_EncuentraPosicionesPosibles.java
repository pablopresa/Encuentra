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
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataPicking;
import dataTypes.Bulto_ReposicionPicking;
import dataTypes.DataIDDescripcion;
import dataTypes.PosicionesPosibles;
import dataTypes.ReposicionPicking;
import dataTypes.Ubicacion_ReposicionPicking;
import logica.Logica;
import logica.Utilidades;

public class _EncuentraPosicionesPosibles extends Action {
	
	
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
	
	
		ReposicionPicking repPicking =(ReposicionPicking) session.getAttribute("reposicionPicking");
		List<Ubicacion_ReposicionPicking> ojosOriginal = (List<Ubicacion_ReposicionPicking>) session.getAttribute("ojosOriginal");
		String idArticulo = repPicking.getIdArticulo();
		
		
		int ultiUbicacion = (Integer) session.getAttribute("ultiUbicacion");
		int dep = Integer.parseInt(uLog.getDeposito());
		List<PosicionesPosibles> posicionesPosibles = Logica.getPosicionesPosibles(idArticulo,ultiUbicacion,dep);
		
		
		
		String idOculto = request.getParameter("idOculto");
		String idOjo = request.getParameter("idOjo");
		String[] listaBultos = idOculto.split(",");
		if(idOculto != "") { // si esto es vacio es porque paso al segundo formuario de una	
				
		int cantidadBajadaTotal = 0;
		
		for (Ubicacion_ReposicionPicking ojo : ojosOriginal) // recorre todos los ojos del objeto reposicion picking 
		{
			for(Bulto_ReposicionPicking bulto : ojo.getListaBultos()) { //recorre todos los bultos de un ojo del objeto de reposicion picking
				for(String idBulto : listaBultos) // recorre la lista de bultos obtenidos al bajar
				{
					String[] arr2 = idBulto.split("-");
					String ojod = arr2[0];
					
					String[] idBultoSucio = arr2[2].split("\\(");
					idBulto = idBultoSucio[0];
					
					
					
					
					if(idBulto.equals(bulto.getIdBulto()) && ojo.getIdOjo().equals(ojod)) 
					{
						//codigoBulto(X)
						
						int cantidadBajada = Integer.parseInt(arr2[1]);
						cantidadBajadaTotal+=cantidadBajada;
						
						
						
						List<DataIDDescripcion> articulos = new ArrayList<>();
						
						//listaBultosObj.add(bulto);
						//cantidadBajada += bulto.getCantidadArticulos(); esto es la cantidad original, yo quiero la cantidad que movieron
						DataIDDescripcion data = new DataIDDescripcion(cantidadBajada,idArticulo);
						articulos.add(data);
						if(idBulto.equals(idArticulo)) // si es articulo suelto
						{
							uLog.registrarEventoMin(session.getId(), "Antes de bajar articulo");
							Logica.encuentraBajaArticulosOjos(cantidadBajada,idArticulo, ojo.getIdOjo(), idEmpresa);
							uLog.registrarEventoMin(session.getId(), "Baja articulos cantidad: "+cantidadBajada);
							Logica.IngresarMovimientoArticuloTipo(ojo.getIdOjo(),"2", idArticulo,cantidadBajada,uLog.getNumero(),"REP",idEmpresa);
						}
						else // si es el bulto
						{		
							uLog.registrarEventoMin(session.getId(), "Antes de bajar bulto");
							Logica.bajarSubirDeBulto(idBulto, articulos, idEmpresa, uLog.getNumero(),-1, true); //Saca de un bulto los articulos
							
							int total = 0;
							for(DataIDDescripcion art : articulos) {
								total+=art.getId();
							}
							uLog.registrarEventoMin(session.getId(), "Baja bulto:"+idBulto+", cantidad: "+total+", del articulo: "+idArticulo);
							Logica.IngresarMovimientoArticuloTipo(idBulto,"2", idArticulo,cantidadBajada,uLog.getNumero(),"REP",idEmpresa);
						}
						Logica.encuentraMoverOjos("2",idArticulo,cantidadBajada,uLog.getNumero(),idEmpresa);
						
					}
				}
			}
		}
				
		
		Logica.BajarReposicionPicking(idArticulo,cantidadBajadaTotal,repPicking.getIdTarea());
		repPicking.setCantidadBajada(cantidadBajadaTotal);
		}

		session.setAttribute("reposicionPicking",repPicking);
		session.setAttribute("posicionesPosibles", posicionesPosibles);
		
		
		return mapping.findForward("ok"); 
	}
}
