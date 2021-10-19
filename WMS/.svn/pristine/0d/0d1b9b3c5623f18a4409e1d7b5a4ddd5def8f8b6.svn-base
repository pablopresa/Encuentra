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
import beans.encuentra.ArticuloConteo;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class DarArticulosConteoStore extends Action 
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

		String req = request.getQueryString();
		session.setAttribute("REQ", req);
		String menError = "";
		
		int idDepo = Integer.parseInt(uLog.getDeposito());
				
	
		List<DataDescDescripcion> marcas = (List<DataDescDescripcion>) session.getAttribute("marcas");
		List<DataDescDescripcion> secciones = (List<DataDescDescripcion>) session.getAttribute("secciones");
		List<DataDescDescripcion> categorias = (List<DataDescDescripcion>) session.getAttribute("categorias");
		List<DataDescDescripcion> clases = (List<DataDescDescripcion>) session.getAttribute("clases");
		List<DataDescDescripcion> generos = (List<DataDescDescripcion>) session.getAttribute("generos"); 
		
	
		String marcaIN = request.getParameter("marca");
		String claseIN = request.getParameter("clase");
		String seccionIN = request.getParameter("seccion");
		String categoriaIN = request.getParameter("categoria");
		String generoIN = request.getParameter("genero");
		
		
		
		String marca = "";
		String clase = "";
		String seccion = "";
		String genero = "";
		String categoria = "";
		
		
		
		try
		{
			String[] values = req.split("&");
			
			//deposito=26&deposito=41&Submit=Filtrar
			System.out.println(req);
			
			
			marca = buscarFiltro("marca", values);
			clase = buscarFiltro("clase", values);
			seccion = buscarFiltro("seccion", values);
			genero = buscarFiltro("genero", values);
			categoria = buscarFiltro("categoria", values);
			
			String articulo = request.getParameter("articulo");
			
			
			List<ArticuloConteo> articulos = log.darListaArticulosConteo(marca,clase,seccion,genero,categoria,articulo,idDepo,idEmpresa);
			
			
			
			
			marcaIN = "";
			claseIN = "";
			seccionIN = "";
			categoriaIN = "";
			generoIN = "";
			String artIN = "";
			
			
			if(!marca.equals(""))
			{
				 marcaIN = " Marcas:"+marca+" ";
			}
			
			if(!clase.equals(""))
			{
				claseIN = " Clases:"+clase+" ";
			}
			if(!seccion.equals(""))
			{
				seccionIN = "  Secciones:"+seccion+" ";
			}
			if(!categoria.equals(""))
			{
				categoriaIN = " Categorias:"+categoria+" ";
			}
			if(!genero.equals(""))
			{
				generoIN = " Generos:"+genero+" ";
			}
			if(articulo!=null && !articulo.equals(""))
			{
				artIN = " Articulo: "+articulo+" ";
			}
			
			String filtro = "filtros aplicados: "+marcaIN+claseIN+seccionIN+generoIN+categoriaIN+artIN;
			session.setAttribute("filtros", filtro);
			
			int total = 0;
			int totalSKUs = 0;
			
			for (ArticuloConteo a : articulos) 
			{
				totalSKUs++;
				total += a.getStock();
			}
			
			
			request.setAttribute("totalSKU", totalSKUs);
			request.setAttribute("total", total);
			session.setAttribute("articuloC", articulos);
			
		}
		catch (Exception e) 
		{
			
		}
		
		return mapping.findForward("ok");
		
	
	}
	
	public String buscarFiltro (String buscado, String[]values)
	{
		 String retorno= "";
		 System.out.println(values);
		 for (int i = 0; i < values.length; i++) 
		 {
			 if(values[i].contains(buscado))
			 {
				 try
				 {
					 String valor = values[i].split("=")[1];
					 retorno+=valor+",";
				 }
				 catch (Exception e)
				 {
					 
				 }
				 
			 }
		 }
		
		 if(!retorno.equals(""))
		 {
			 return retorno.substring(0, retorno.length()-1);
		 }
		 return retorno;
	}
}
