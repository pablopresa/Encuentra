package web.picking;

import java.util.ArrayList;
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
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataPicking;



public class _EncuentraClasificarTareaII extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		 Logica Logica = new Logica();
		 Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
		 String idT = (String) session.getAttribute("idTarea");
		 int idTarea = Integer.parseInt(idT);
		 int canti = Integer.parseInt(request.getParameter("canti"));
		 int porcentaje = 0;
		 
		 int totalSol = (int) session.getAttribute("totSol");
		 int totalVer = (int) session.getAttribute("totVer");
		 String dest = request.getParameter("destinoSort");
		 request.setAttribute("destinoSort", dest);
		 
		 
		try 
		{
			int cantS = 0;
			List<DataPicking> pickings = (List<DataPicking>) session.getAttribute("pickings");
			String art = request.getParameter("art");
			//int posicion = Integer.parseInt(request.getParameter("posicion"));
			int idPick = 0;
			List<DataPicking> pickingsSel = new ArrayList<>();
			
			for (DataPicking pi : pickings) 
			{
				if(pi.getArticulo().equals(art))
				{
					if(canti <=pi.getSol()){
						pi.setVerificada(canti);
						pickingsSel.add(pi);
						idPick = pi.getIdPicking();
					}
					else{
						request.setAttribute("menError", "La cantidad es superior a la requerida");
						return mapping.findForward("ok");
					}
					
				}
			}
			
			
			Logica.encuentraUpdateClasifTarea(pickingsSel,idEmpresa);
			Logica.encuentraUpdateOjos(pickingsSel.get(0).getPosSort(), art, canti, false,uLog.getNumero(),false,"SOR",idEmpresa);
			Logica.encuentraUpdateAlias(pickingsSel.get(0).getPosSort(), art, dest,idEmpresa);
			
			totalVer += pickingsSel.get(0).getVerificada();
			session.setAttribute("totSol",totalSol);
			session.setAttribute("totVer",totalVer);
			
			pickings = Logica.encuentraDarClasificacion(idPick,idEmpresa);
			
			try {
				porcentaje = totalVer*100/totalSol;
			} catch (Exception e) {
				porcentaje=100;
			}
			
			Logica.encuentraUpdateAvanceTarea(idTarea, porcentaje, 0,idEmpresa);
			
			List <DataPicking> pickingsToVer = new ArrayList<>();
			StringBuilder arregloArts = new StringBuilder();
			StringBuilder arregloDestino = new StringBuilder();
			
			for (DataPicking d : pickings) 
			{
				if(d.getSol()-d.getVerificada()>0)
				{
					arregloArts.append("'"+d.getArticulo()+"',");
					arregloDestino.append("'"+d.getDescripcion()+"',");
					pickingsToVer.add(d);
					
				}
				
			}
			
			if(pickingsToVer.size()>0)
			{
				session.setAttribute("pickings", pickingsToVer);
				int corteA = arregloArts.toString().length()-1;
				int corteB = arregloDestino.toString().length()-1;
				
				session.setAttribute("arregloArticulos", arregloArts.toString().substring(0, corteA));
				session.setAttribute("arregloDestinos", arregloDestino.toString().substring(0, corteB));
				return mapping.findForward("ok");
			}
			else
			{
				Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(),0,idEmpresa);
				Logica.LimpiarSorter(pickingsSel.get(0).getIdPicking(),idEmpresa);
				request.setAttribute("menError", "Ha clasificado todos los articulos del picking "+idPick);
				//Si se termina la tarea, vuelve a pedir todas las tareas del usuario para cargarlas en la Session
				//int idDeposito = Integer.parseInt(uLog.getDeposito());
				List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
				session.setAttribute("tarMob", tarMob);
				return mapping.findForward("fin");
			}
				
			
		}
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			request.setAttribute("menError", "Sucedio un error");
			e.printStackTrace();
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}	
			
			


		
		
	}
	
	
}















