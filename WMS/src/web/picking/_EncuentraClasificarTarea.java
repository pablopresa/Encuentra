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



public class _EncuentraClasificarTarea extends Action 
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
		
		try 
		{
			String idT = request.getParameter("idTarea");
			int idTarea = Integer.parseInt(idT);
			session.setAttribute("idTarea", idT);
			int idMain = Integer.parseInt(request.getParameter("idMain"));
			List<DataPicking> pickings = Logica.encuentraDarClasificacion(idMain,idEmpresa);
			Logica.encuentraAltaEventoTarea(idTarea, 1, uLog.getNumero(),0,idEmpresa);
			
			int totalSol = 0;
			int totalEnc = 0;
			int totalVer = 0;
			
			for (DataPicking p : pickings) 
			{
			
				totalSol+=p.getSol();
				totalEnc+=p.getPick();
				totalVer+=p.getVerificada();
				
			}
		
			session.setAttribute("totSol",totalSol);
			session.setAttribute("totEnc",totalEnc);
			session.setAttribute("totVer",totalVer);
			request.setAttribute("destinoSort", "");
			
			session.setAttribute("pickings", pickings);
			
			
				
			StringBuilder arregloArts = new StringBuilder();
			StringBuilder arregloDestino = new StringBuilder();
			
			List <DataPicking> pickingsToVer = new ArrayList<>();
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
				request.setAttribute("menError", "ya se clasificaron todos los articulos ");
				//Si se termina la tarea, vuelve a pedir todas las tareas del usuario para cargarlas en la Session
				//int idDeposito = Integer.parseInt(uLog.getDeposito());
				List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
				session.setAttribute("tarMob", tarMob);
				return mapping.findForward("fin");
			}
			
			

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		
	}
}















