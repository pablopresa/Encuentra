package web.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import beans.DepositoParametros;
import beans.Menu;
import beans.Usuario;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.Tarjeta;

public class _EncuentraLogout extends Action {

	String Mensaje = "El nombre de usuario y la contraseña no son correctos";
	String MensajeSuc = "no existe la tienda ";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		try 
		{
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			int idEmpresa = uLog.getIdEmpresa();
			uLog.registrarEventoMin(session.getId(), "Logout del sistema");
			
			String mobile = request.getParameter("mobile");
			String urlRetorno = "";
			
			try
			{
				//obtengo prefijo url empresa
				PropertiesHelper pH=new PropertiesHelper("urlPrefijoEmpresas");
				pH.loadProperties();
				String prefijo = pH.getValue(idEmpresa+"");
				
				if (mobile!= null && mobile.equals("1")){
					urlRetorno=prefijo+"m/login.jsp";
				}
				else{
					urlRetorno=prefijo+"login.jsp";
				}
				
			}
			catch (FileNotFoundException e) // si no encuentra el archivo de configuracion
			{
				e.printStackTrace();
				urlRetorno="v3/util/login.jsp";
			}
			
			session.invalidate();
			
			request.setAttribute("URL", urlRetorno);
			return mapping.findForward("redir");
			
			
		} catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			request.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("redir");
		//comentario de prueba
		//otro
		}
		
		//comentario
	}
	//COMENTARIO
}
