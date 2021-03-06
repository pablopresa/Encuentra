package web.almacen;

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

import beans.ConsolidarSku;
import beans.LineaOrdenAlmacen;
import beans.Usuario;

public class _EncuentraReporteConsolidacion extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		List<ConsolidarSku> lista = new ArrayList<>();
		try{
			String save = request.getParameter("save");
			
			if(save!=null){
				List<LineaOrdenAlmacen> listaAlmacen = new ArrayList<>();
				LineaOrdenAlmacen linea;
				int total=0;
				lista =  (List<ConsolidarSku>) session.getAttribute("consolidar");
				//List<>
				for(ConsolidarSku a:lista){
					for(LineaOrdenAlmacen aa:a.getListaArticulos()){
						if(request.getParameter(aa.getSku()+"-"+aa.getUbicacion())!=null){
							linea = new LineaOrdenAlmacen();
							linea.setSku(aa.getSku());
							linea.setUbicacion(aa.getUbicacion());
							linea.setCantidad(aa.getCantidad());
							
							listaAlmacen.add(linea);
							total += aa.getCantidad();
						}
					}
						
				}
				
				if(listaAlmacen.size()>0){
					Logica.GuardarOrdenAlmacen(listaAlmacen, total,idEmpresa);
				}
				
				uLog.registrarEventoMin(session.getId(), "Ingreso orden de consolidación");
				request.setAttribute("menError", "Orden de Consolidacion ingresada correctamente");
			}
			
			
			lista = Logica.ReporteConsolidacion("%","","","","","",idEmpresa);
			session.setAttribute("consolidar", lista);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("ok");
			
	}
	

}
