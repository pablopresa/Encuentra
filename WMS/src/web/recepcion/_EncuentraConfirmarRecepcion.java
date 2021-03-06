package web.recepcion;

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

import dataTypes.DataIDDescripcion;

import beans.Usuario;
import beans.encuentra.DataLineaListaTareasMob;

public class _EncuentraConfirmarRecepcion extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
			HttpSession session = request.getSession();
			Logica Logica = new Logica();
		try 
		{
			String StringRecepciones=request.getParameter("recepciones");
			String accion=request.getParameter("accion");
			int idMain=Integer.parseInt((String) session.getAttribute("idMain"));
			int idTarea=(Integer) session.getAttribute("idTarea");
			int typeSave = Integer.parseInt(request.getParameter("typeSave"));
			int cantS = 0;
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			
			DataIDDescripcion tipo = (DataIDDescripcion) session.getAttribute("tipoTar");			
			String articulosComa = request.getParameter("destino");
			
			articulosComa = articulosComa.replace(" ", "");
			articulosComa = articulosComa.replaceAll("[\n\r]","");
			
			String [] desorden = articulosComa.split(",");
			
			
			List<DataIDDescripcion> ordenable = (List<DataIDDescripcion>) session.getAttribute("ordenable");
			if(ordenable==null)
			{
				ordenable = new ArrayList<>();
			}
			
			for (String s : desorden) 
			{
				String[]data = s.split(":");
				int cant = Integer.parseInt(data[1]);
				cantS+=cant;
				DataIDDescripcion d = new DataIDDescripcion(cant,data[0]);
				ordenable.add(d);
			}
			if(typeSave==1)
			{
				session.setAttribute("ordenable", ordenable);
				String cantRecepSaved = (String) session.getAttribute("cantRecepSaved");
				if(cantRecepSaved==null)
				{
					cantRecepSaved = new String();
				}
				
				session.setAttribute("cantRecepSaved", cantS+" + ");
				return mapping.findForward("saved");
				
			}
			else
			{
				session.setAttribute("cantRecepSaved", null);
				session.setAttribute("ordenable", null);
				List<DataIDDescripcion> articulosCant = Logica.EncuentraSortearListaDICant(ordenable);
				Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(),0,idEmpresa);
				Logica.updateEstadoRecepFromTar(idMain, -3,idEmpresa);
				Logica.encuentraAltaCantRecep(articulosCant, uLog.getNumero(), tipo.getId(),idEmpresa);
				//int idDeposito = Integer.parseInt(uLog.getDeposito());
				List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
				session.setAttribute("tarMob", tarMob);
			}
		}
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			e.printStackTrace();
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("error");

		}	
		return mapping.findForward("ok");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
