package web.tareas;

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
import beans.LineaOrdenAlmacen;
import beans.Usuario;

public class _EncuentraOrdenAlmacen extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		List<DataIDDescripcion> articulosRecepcionados = new ArrayList<>();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		try{
			
			String save = request.getParameter("save");
			
			if(save!=null){
				List<LineaOrdenAlmacen> listaAlmacen = new ArrayList<>();
				LineaOrdenAlmacen linea;
				int total=0;
				articulosRecepcionados = (List<DataIDDescripcion>) session.getAttribute("articulos");
				//List<>
				for(DataIDDescripcion ar:articulosRecepcionados){
					if(request.getParameter(ar.getDescripcion())!=null){
						linea = new LineaOrdenAlmacen();
						linea.setSku(ar.getDescripcion());
						linea.setUbicacion("A1");
						linea.setCantidad(ar.getId());
						
						listaAlmacen.add(linea);
						total += ar.getId();
					}
				}
				
				if(listaAlmacen.size()>0){
					Logica.GuardarOrdenAlmacen(listaAlmacen, total,idEmpresa);
				}
				
				
				request.setAttribute("menError", "Orden de Almacen ingresada correctamente");
			}
			
			articulosRecepcionados = Logica.ArticulosAAlmacenar(idEmpresa);
			session.setAttribute("articulos", articulosRecepcionados);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("ok");
			
	}
	

}
