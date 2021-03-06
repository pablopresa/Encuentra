package web.tareas;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;
import beans.encuentra.Tarea;



public class _EncuentraAltaTareaI extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		try 
		{
			/*
			 * 	  	<input type="text" class="input" name="descripcion" id="descripcion" value="${descripcion}" />
					 <select name="tipo" id="tipo" class="select">
					 <select name="sup" id="sup" class="select">
					 <input type="text" class="input" name="unit" id="unit" value="${unit}" />
				     <input type="text"  name="fini" style="width: 270px;"></input>
			 */
			
			String descripcion = request.getParameter("descripcion");
			String idTip =  request.getParameter("tipo");
			String idSup  = request.getParameter("sup");
			String uni  = request.getParameter("unit");
			String fechaI = request.getParameter("fini");

			
			int idInt=0;
			int idTipo = 0;
			int unidades =0;
			
			
			Tarea tarea = new Tarea();
			
			
			List<DataIDDescripcion> suoervisores = (List<DataIDDescripcion>) session.getAttribute("supervisores");
			List<DataIDDescripcion> tiposTarea =(List<DataIDDescripcion>) session.getAttribute("tiposTarea");
			
			
			String error = "";
			try
			{
				error = "hay un error con el supervisor"+idSup;
				
				idInt = Integer.parseInt(idSup);
				
				error = "hay un error con el Tipo de tarea"+idTip;
				
				idTipo = Integer.parseInt(idTip);
				
				error = "EL valor del campo unidades debe ser numerico y no puede ser vacio. "+uni;
				
				unidades = Integer.parseInt(uni);
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				request.setAttribute("menError", error);
				return mapping.findForward("no");
			}
			
			
			DataIDDescripcion tipo = null;
			for (DataIDDescripcion d : tiposTarea) 
			{	
				if(d.getId()==idTipo)
				{
					tipo = d;
					break;
				}
			}

			DataIDDescripcion superVi = null;
			for (DataIDDescripcion d : suoervisores) 
			{	
				if(d.getId()==idInt)
				{
					superVi = d;
					break;
				}
			}
			
			
			DataIDDescripcion estado = new DataIDDescripcion();
			estado.setId(0);
			
			tarea.setEstado(estado);
			tarea.setFechaInicio(fechaI);
			tarea.setObservacion(descripcion);
			tarea.setPorcentaje(0);
			tarea.setResponsable(superVi);
			tarea.setTipo(tipo);
			tarea.setCantidadPares(unidades);
			
			
			session.setAttribute("tarea", tarea);
			
			

		} catch (Exception e) {
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		return mapping.findForward("ok");
	}
}
