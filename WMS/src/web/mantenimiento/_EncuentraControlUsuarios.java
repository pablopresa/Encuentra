package web.mantenimiento;

import java.util.ArrayList;
import java.util.Hashtable;
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

public class _EncuentraControlUsuarios extends Action 
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
		String accion = request.getParameter("action");
		Hashtable<Integer, Usuario> usersHT= new Hashtable<>();
		List<Usuario> users;
		int idUser=0;
		
		session.setAttribute("lstGruposAsociarPermisos", null);
		
		switch (accion) {
		case "index":		
			usersHT = Logica.darUsuarios(idEmpresa);
			users = new ArrayList<>(usersHT.values());
			request.setAttribute("users", users);
			session.setAttribute("usersHT", usersHT);
			return mapping.findForward("ok");
		case "delete":
			usersHT = (Hashtable<Integer, Usuario>) session.getAttribute("usersHT");
			idUser = Integer.parseInt(request.getParameter("idUser"));
			Logica.persistir("update usuarios set baja=1 where idEmpresa="+idEmpresa+" AND idUsuario="+idUser);
			usersHT.remove(idUser);
			users = new ArrayList<>(usersHT.values());
			request.setAttribute("users", users);
			session.setAttribute("usersHT", usersHT);
			request.setAttribute("menError", "Usuario eliminado correctamente");
			return mapping.findForward("ok");
		case "update":
			usersHT = (Hashtable<Integer, Usuario>) session.getAttribute("usersHT");
			idUser = Integer.parseInt(request.getParameter("idUser"));
			Usuario u = usersHT.get(idUser);
			request.setAttribute("numero", u.getNumero());
			request.setAttribute("nombre", u.getNombre());
			request.setAttribute("apellido",u.getApellido());
			request.setAttribute("deposito", u.getDeposito());
			request.setAttribute("tipo", u.getPerfil());
			request.setAttribute("login", u.getNick());
			request.setAttribute("p1", u.getPass());
			request.setAttribute("p2", u.getPass());			
			request.setAttribute("upd", true);
			List<DataIDDescripcion> tipos = Logica.encuentraDarTiposUsuarios(idEmpresa);
			List<DataIDDescripcion> depositos = Logica.encuentraDarDepositos(idEmpresa);
			session.setAttribute("tiposU", tipos);
			session.setAttribute("depositoU", depositos);
			return mapping.findForward("update");

		default:
			return mapping.findForward("ok");
			
		}	
	
	}
	
	

}
