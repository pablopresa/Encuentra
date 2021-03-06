package web.recepcion;

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
import dataTypes.DataDetalleRecepcion;

public class _EncuentraDetallarRecepcion extends Action {
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
		String idRecepcion=request.getParameter("idRecepcion");
		session.setAttribute("idRecepcion", idRecepcion);
		List<DataDetalleRecepcion> recepciones=null;
		if(idRecepcion!=null){
			recepciones=Logica.DetalleRecepcion(Integer.parseInt(idRecepcion),idEmpresa);
		}
		session.setAttribute("recepciones", recepciones);
		return mapping.findForward("ok");
		
	}



}
