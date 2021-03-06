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
import beans.encuentra.DataArticulo;
import beans.encuentra.SKUType;


public class _EncuentraArticulos extends Action  {
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
			List<SKUType> types=Logica.encuentraDarTipoSKU(idEmpresa);
			String texto = request.getParameter("texto");
			session.setAttribute("types", types);
			
			if(texto != null){
				List<DataArticulo> articulos=Logica.encuentraDarArticulos(texto, idEmpresa);
				session.setAttribute("articulos", articulos);
			}
			return mapping.findForward("ok");
		}
	
	
	}
