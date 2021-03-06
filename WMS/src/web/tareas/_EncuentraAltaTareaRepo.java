package web.tareas;

import java.util.ArrayList;
import java.util.List;

//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.Hashtable;
//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.ArticuloLineaReposicion;
import beans.Fecha;
//import beans.ArticuloLineaReposicion;
import beans.Usuario;
//import beans.encuentra.DataLineaRepo;
import beans.encuentra.Tarea;
import logica.EnviaMail;
//import dataTypes.DataIDDescripcion;
//import logica.ListasPickingBultos;
import logica.Logica;
//import logica.LogicaBulto;
import logica.Utilidades;

public class _EncuentraAltaTareaRepo extends Action
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		System.out.println("alta de picking");
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
			
			 List<ArticuloLineaReposicion> listaSinFiltar = (List<ArticuloLineaReposicion>) session.getAttribute("articulosIn");
			//Creo tarea de reposicion picking
			Tarea tarea = (Tarea) session.getAttribute("tareaRepo"); 
			tarea.setIdDeposito(Integer.parseInt(uLog.getDeposito()));
			Logica.encuentraAltaTarea(tarea, false, uLog.getNumero(),idEmpresa,false );//Le cargo la tarea a todos los usuarios
			int contador = 0;
			String queries = "";
			for (ArticuloLineaReposicion l : listaSinFiltar) {
				contador++;				
				queries+=uLog.query_registrarEvento(tarea.getId(),l.getCantidad(), tarea.getTipo().getId(), 0, true, session.getId(), "Verificando articulo "+l.getIdArticulo());
				if ( contador == 50 ) {
					Logica.persistir(queries);
					contador = 0;
					queries = "";
				}
				
			}
			
			if (!queries.equals("")) {
				Logica.persistir(queries);
			}
			// y le muestro las tareas a todos los elevadoristas		
			
			EnviaMail em = new EnviaMail();
			Fecha fecha = new Fecha();
			List<String> lista = new ArrayList<>();
			lista.add("elevadorista.ppgg@gmail.com");
			em.enviarMailHTMLOD( "encuentra@200.com.uy",lista, "****atencion nueva tarea de Reposicion(Prioridad normal)******", "Fecha de creacion "+fecha.darFechaAnio_Mes_Dia_hhmm(),idEmpresa);
			
			return mapping.findForward("ok");
				
	}
}
