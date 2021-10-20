package web.almacen;

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
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.Utilidades;

public class _CargarSubfamiliasUbicaciones extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		Logica logica = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Gson gson = new Gson();
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		
		if(idEmpresa==0)
			return mapping.findForward("LOGIN");

		String familias = request.getParameter("familias");
		
		List<DataIDDescripcion> subfamilias;
		
		if(!familias.equals("()"))
			subfamilias = logica.darSubfamilias(familias);
		else
			subfamilias = logica.darListaDataIdDescripcion("art_subfamilia", idEmpresa);
		
		String sSubfamilias = gson.toJson(subfamilias);

		session.setAttribute("html", sSubfamilias);
		
		return mapping.findForward("ok");
		
	}
}
	
