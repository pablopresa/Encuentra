package web.almacen;

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
import beans.encuentra.DataOjoArticulo;

public class BajarLinea_Ubicaciones extends Action 
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
		
		String idOjo = request.getParameter("idOjo");
		int cant = Integer.parseInt(request.getParameter("cantidad"));
		String articulo = request.getParameter("idArt");
		
		Logica.encuentraBajarOjosXArt(idOjo,articulo, cant, idEmpresa);
		uLog.registrarEventoMin(session.getId(), "Usuario bajo "+cant+" unidades del articulo "+articulo+" del ojo ("+idOjo+")");
		
		List<DataOjoArticulo> ojosArticulos = (List<DataOjoArticulo>) session.getAttribute("ojosTienen");
		
		for(int i=0;i<ojosArticulos.size();i++){
			if(ojosArticulos.get(i).getArticulo().equals(articulo) && ojosArticulos.get(i).getCantidad()==cant && ojosArticulos.get(i).getIdOjo().equals(idOjo)){
				ojosArticulos.remove(i);
				i=ojosArticulos.size()+1;
			}
		}
		
		session.setAttribute("ojosTienen", ojosArticulos);
		
		return mapping.findForward("ok");
		
		
	
	}

}
