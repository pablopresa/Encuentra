package web.util;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;


import beans.Menu;
import beans.Usuario;
import beans.encuentra.Tarjeta;

public class _EncuentraGuest extends Action {

	String Mensaje = "El nombre de usuario y la contraseña no son correctos";
	String MensajeSuc = "no existe la tienda ";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();

		String tienda = request.getParameter("tienda");
		String idEmpresaSTR = request.getParameter("idEmpresa");
		int idTienda = 0;
		try
		{
			idTienda = Integer.parseInt(tienda);
			session.setAttribute("idTienda", idTienda);
			
			
			
			
		}
		catch(Exception e)
		{
			request.setAttribute("menError", MensajeSuc);
			return mapping.findForward("ok");
		}
		
		String pass = request.getParameter("password");
		
		String pc = "1";
		
		
		try 
		{
				Usuario uLog = new Usuario();
				int idEmpresa = Integer.parseInt(idEmpresaSTR);
				
				uLog = Logica.loginEncuentra("invitado", "guest",idEmpresa);
				
				//trae la lista de reposiciones
				session.setAttribute("uLogeado", uLog);
				int idGrupo = 100;
				List<DataIDDescripcion> grupos = new ArrayList<>();
				grupos.add(new DataIDDescripcion(100,""));
				
				List<Menu> menu = Logica.darMenu(grupos,false,idEmpresa);
				List<Tarjeta> tarjetas = Logica.darTarjetasGuest(idGrupo,idEmpresa);
				
				session.setAttribute("tarjetas", tarjetas);
				
				
				session.setAttribute("menu", menu);
				
				return mapping.findForward("ok");
				
			
		} catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			request.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("no");
		//comentario de prueba
		//otro
		}
		
		//comentario
	}
	//COMENTARIO
}
