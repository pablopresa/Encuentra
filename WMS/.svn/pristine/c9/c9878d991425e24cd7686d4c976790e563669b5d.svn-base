package web.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.LeerHTML;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import beans.ProcessEcommerce;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class DarRangosConteoStore extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		Logica log = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		

		String menError = "";		
		int idDepo = Integer.parseInt(uLog.getDeposito());
		
		Hashtable<String, List<DataDescDescripcion>> filtros = log.DarRangosConteoStore(idDepo,idEmpresa);
		
		/*
		 * marca
		 * seccion
		 * categoria
		 * clase
		 * genero
		 * articulos
		 * */
		List<DataDescDescripcion> marcas = filtros.get("marca");
		List<DataDescDescripcion> seccion = filtros.get("seccion");
		List<DataDescDescripcion> categoria = filtros.get("categoria");
		List<DataDescDescripcion> clase = filtros.get("clase");
		List<DataDescDescripcion> genero = filtros.get("genero");
		//List<DataDescDescripcion> articulos = filtros.get("articulos");
		
		
		java.util.Collections.sort(marcas);
		java.util.Collections.sort(seccion);
		java.util.Collections.sort(categoria);
		java.util.Collections.sort(clase);
		java.util.Collections.sort(genero);
		//java.util.Collections.sort(articulos);
		
		
		
		session.setAttribute("marcas", marcas);
		session.setAttribute("secciones", seccion);
		session.setAttribute("categorias", categoria);
		session.setAttribute("clases", clase);
		session.setAttribute("generos", genero);
		//session.setAttribute("articulos", articulos);

		return mapping.findForward("ok");
	}

}
