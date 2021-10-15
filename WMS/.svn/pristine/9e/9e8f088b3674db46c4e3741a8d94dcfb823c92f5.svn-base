package web.mantenimiento;

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

public class _EncuentraEliminarTipo extends Action 
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
		
		
		String idTipo = request.getParameter("idTipo"); // si es un alta el tipo es 999999
		
		String esSKU=request.getParameter("esSKU");
		boolean puede=false;
		if(esSKU==null){
			puede = Logica.encuentraBajaTipoEstanteria(Integer.parseInt(idTipo), idEmpresa);
			uLog.registrarEventoMin(session.getId(), "Baja Tipo_Estanteria: "+idTipo);
		}else{
			puede = Logica.encuentraBajaTipoSKU(Integer.parseInt(idTipo), idEmpresa);
			uLog.registrarEventoMin(session.getId(), "Baja Tipo_Estanteria: "+idTipo);
		}
			
		String mens = null;
		if(!puede)
		{
			mens = "No puede eliminar un tipo de estanteria que ya tiene asignada";
			uLog.registrarEventoMin(session.getId(), "Baja Tipo_Estanteria, no se pudo eliminar el tipo de estanteria: "+idTipo);
		}
		
		request.setAttribute("menError", mens);
		return mapping.findForward("ok");
	
	
	}

}
