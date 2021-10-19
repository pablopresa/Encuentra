package web.mantenimiento;

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

public class _EncuentraEliminarArticulo extends Action {
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
		String id = request.getParameter("id");
		boolean puede = Logica.encuentraBajaArticulo(id, idEmpresa);
		List<DataArticulo> articulos = (List<DataArticulo>) session.getAttribute("articulos");
		String mens = null;
		if (!puede) {
			mens = "No puede eliminar un articulo  que ya tiene asignada";
			uLog.registrarEventoMin(session.getId(), "(Eliminar_Arti) Error en articulo: "+id+mens);
		} else {
			uLog.registrarEventoMin(session.getId(), "(Eliminar_Arti) Articulo eliminado: "+id);
			for (int i = 0; i < articulos.size(); i++) {
				if (articulos.get(i).getId().equals(id)) {
					articulos.remove(i);
				}
			}

		}
		session.setAttribute("articulos", articulos);
		request.setAttribute("menError", mens);
		return mapping.findForward("ok");

	}

}
