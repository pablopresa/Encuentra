package web.almacen;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
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
import beans.encuentra.DataOjoArticulo;

public class _EncuentraOjosArticulos extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		String mensaje = "buscando articulo";
		String articulo = request.getParameter("art");
		String idOjo = request.getParameter("idOjo");
		String bultos = request.getParameter("desconsolidar");
		String switchStock = request.getParameter("uStockERP");
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int bandera = 1;
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		//seteo para mostrar lo que esta filtrando
		request.setAttribute("art", articulo);
		
		String req = request.getQueryString();
		String usos = null;
		String estanteria = null;
		String categoria = null;
		String subcategoria = null;
		String[] values;
		if(req!=null)
		{
			values = req.split("&");
			
			String[] arreglo = {"uRecepcion","uAlmacen","uClasificacion","uExpedicion","uEcommerce"};
			usos = buscarFiltro(arreglo, values);
			estanteria =  buscarFiltro("estanteria", values);
			categoria = buscarFiltro("categoria", values);
			subcategoria = buscarFiltro("subcategory", values);
		}
		
		
		
		
		
		/*String uRecepcion = buscarFiltro("uRecepcion", values);
		String uAlmacen = buscarFiltro("uAlmacen", values);
		String uClasificacion = buscarFiltro("uClasificacion", values);
		String uExpedicion = buscarFiltro("uExpedicion", values);
		String uEcommerce = buscarFiltro("uEcommerce", values);
		*/
				
		
		String mob = request.getParameter("mob");
		String pick = request.getParameter("picking");
		
		
		ServletContext context = request.getSession().getServletContext();
		Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
		List<DataIDDescripcion> categorias = Logica.darListaDataIdDescripcion("art_categoria",idEmpresa);
		List<DataIDDescripcion> subCategorias = Logica.darListaDataIdDescripcion("art_genero",idEmpresa);
		List<DataIDDescripcion> estanterias = Logica.darEstanterias(idEmpresa);
		if(idOjo==null && estanteria ==null && categoria == null && (articulo ==null || articulo.isEmpty()) && subcategoria ==null)
		{
			session.setAttribute("ojosTienen", null);
			session.setAttribute("estanterias", estanterias);
			session.setAttribute("categorias", categorias);
			session.setAttribute("subCategorias", subCategorias);
			session.setAttribute("cantidad", 0);
			
			if(mob !=null && mob.equals("1"))
			{
				return mapping.findForward("mob");
			}
			else
			{
				return mapping.findForward("ok");
			}
		}
		
		
		
		
		//Voy con la barra y traigo el articulo
		String articuloAux = null;
		if (articulo != null && !articulo.equals(""))		
			articuloAux = Logica.darArticuloAlEscanear(articulo,artBarra,idEmpresa);
		
		
		boolean desconsolidar = false;
		boolean stockERP = switchStock!=null && switchStock.equals("on");
		
		if(bultos!=null && bultos.equals("on")){
			desconsolidar = true;
		}
		
		if(articuloAux != null)
			articulo = articuloAux;
		
		if(mob !=null && mob.equals("1")){
			usos = "";
			desconsolidar = true;
	//		if (idOjo != null) // rompera algo esto ? Esto es para que no devuelva las ubicaciones logicas en Art/Ubicaciones
			usos = "1,2,3";
		}
		
		int empaque = 0;
		String urlFoto = "";
		String descripcionArticulo = "";
		List<DataIDDescripcion> femps = Logica.darEmpaqueFoto("'"+articulo+"'", idEmpresa);
		
		
		if(!femps.isEmpty())
		{
			DataIDDescripcion fotoEmpaque = femps.get(0);
			empaque = fotoEmpaque.getId();
			urlFoto = fotoEmpaque.getDescripcion();
			descripcionArticulo = fotoEmpaque.getDescripcionB();
		}
		
		
		
		int deposito = 0;
		try {deposito = Integer.parseInt(uLog.getDeposito());}catch (Exception e) { e.printStackTrace(); }
		
		Integer existeOjo = Logica.encuentraExisteUbica(articulo,idEmpresa,deposito);

		if(existeOjo==1) { // Si existe ojo
			idOjo=articulo;
			articulo = null;
			mensaje = "buscando articulos en ubicacion: ";
			bandera = 2;
		}
		request.setAttribute("mensaje", mensaje);
		request.setAttribute("bandera", bandera);
		List<DataOjoArticulo> ojosArticulos = Logica.encuentraDarOjosArticulos(idOjo, estanteria,categoria, usos, articulo,stockERP,desconsolidar, idEmpresa, deposito, subcategoria);
		
		session.setAttribute("listaUbicaciones", ojosArticulos);

		int cantidad = 0;
		
		
		for (DataOjoArticulo d : ojosArticulos) 
		{
			cantidad +=d.getCantidad();
		}
		
		session.setAttribute("packing", empaque);
		session.setAttribute("urlFoto", urlFoto);
		session.setAttribute("descripcionArticulo", descripcionArticulo);
		
		
		session.setAttribute("stockERP", stockERP);
		session.setAttribute("ojosTienen", ojosArticulos);

		String descripcionOjo = "";
		try {
			descripcionOjo = ojosArticulos.get(0).getDescripcion();
		} catch (Exception e) {
			e.printStackTrace();
		}session.setAttribute("descripcionOjo", "");
		
		
		session.setAttribute("estanterias", estanterias);
		session.setAttribute("cantidad", cantidad);
		session.setAttribute("categorias", categorias);
		session.setAttribute("subCategorias", subCategorias);
		
		uLog.registrarEventoMin(session.getId(), "Consulta ubicaciones del art?culo "+articulo);
		
		if(pick!=null && pick.equals("1"))
		{
			return mapping.findForward("mobPick");
		}
		
		
		if(mob !=null && mob.equals("1"))
		{
			return mapping.findForward("mob");
		}
		else
		{
			return mapping.findForward("ok");
		}
		
	
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
	
	public String buscarFiltro (String[] buscados, String[]values)
	{
		 String retorno= "";
		 System.out.println(values);
		 for (int i = 0; i < values.length; i++) 
		 {
			 for (int j = 0; j < buscados.length; j++) 
			 {
				 if(values[i].contains(buscados[j]))
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
		 }
		
		 if(!retorno.equals(""))
		 {
			 return retorno.substring(0, retorno.length()-1);
		 }
		 return retorno;
	}

}
