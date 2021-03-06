package web.picking;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.ArticuloLineaReposicion;
import beans.Usuario;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.Utilidades;
import web.ecommerce.DarPedidosClasificaXID;

public class _EncuentraVerReposicionPendiente extends Action{

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

 		String distriIN = request.getParameter("distr");
 		String marcaIN = request.getParameter("marca");
		String depositoIN = request.getParameter("deposito");
		String claseIN = request.getParameter("clase");
		String seccionIN = request.getParameter("seccion");
		String categoriaIN = request.getParameter("categoria");
		String generoIN = request.getParameter("genero");
		String courierIN = request.getParameter("courier");
		String fechas = request.getParameter("fini");
		String tope = request.getParameter("tope");
		String spick = request.getParameter("spick");
		String sprioridad = request.getParameter("sprioridad");
		
		session.setAttribute("marcaSEL", marcaIN);
		session.setAttribute("distriSEL", distriIN);
		
		session.setAttribute("depositoSEL", depositoIN);
		session.setAttribute("claseSEL", claseIN);
		session.setAttribute("seccionSEL", seccionIN);
		session.setAttribute("categoriaSEL", categoriaIN);
		session.setAttribute("generoSEL", generoIN);
		session.setAttribute("courierSEL", courierIN);

		
		String central = request.getParameter("central");
		String mayorista = request.getParameter("mayorista");
		String tipoRe = request.getParameter("tipoRe");
		
		
		String fechaI ="";
		String fechaF = "";
		
		if(fechas!=null)
		{
			if(!fechas.equals("Sin Rango")){
				String []fechaIF = fechas.split(" - ");
			
				fechaI = fechaIF[0];
				fechaF = fechaIF[1];
		
				fechaI = fechaI+" 00:00:00";
				fechaF = fechaF+" 23:59:59";
			}
		}	
				
		if(central==null)
		{
			central = (String) session.getAttribute("central");
			
		}
		if(mayorista==null)
		{
			mayorista = (String) session.getAttribute("mayorista");
			if(mayorista==null)
			{
				mayorista="0";
			}
			
		}
		
		if(tipoRe == null) {
			tipoRe=(String) session.getAttribute("tipoRe");
		}
		
		session.setAttribute("mayorista",mayorista);
		session.setAttribute("central",central);
		session.setAttribute("tipoRe", tipoRe);
		
		boolean centr = false;
		boolean mayo = false;
		if(central.equals("1"))
		{
			centr=true;
		}
		else
		{
			centr=false;
		}
		
		if(mayorista.equals("1"))
		{
			mayo=true;
		}
		else
		{
			mayo = false;
		}
		
		String req = request.getQueryString();
		session.setAttribute("REQ", req);
		
	
		uLog.registrarEventoMin(session.getId(), "filtrando lista de picking pendiente "+ req);

		String deposito = "";
		String marca = "";
		String clase = "";
		String seccion = "";
		String genero = "";
		String categoria = "";
		String distribucion = "";
		String canales = "";
		String couriers = "";
		
		
		try
		{
			String[] values = req.split("&");
			
			//deposito=26&deposito=41&Submit=Filtrar
			System.out.println(req);
			
			deposito = buscarFiltro("deposito", values);
			marca = buscarFiltro("marca", values);
			clase = buscarFiltro("clase", values);
			seccion = buscarFiltro("seccion", values);
			genero = buscarFiltro("genero", values);
			categoria = buscarFiltro("categoria", values);
			distribucion = buscarFiltro("distr", values);
			canales = buscarFiltro("canales", values);
			couriers = buscarFiltro("courier", values);
		}
		catch(Exception e)
		{
			
		}
		
		
		
		if(tipoRe.equals("2"))
		{
			/********todos los destinos son validos como peppos 71 y 75*******/
			
			//String depositoEcommerce = Logica.darParametroEmpresa(idEmpresa, 5);
			//deposito=depositoEcommerce;
			mayo=true;
		}
		
		int idOrigen = Integer.parseInt(uLog.getDeposito());
		int reqFactura =0;
		try
		{
			reqFactura = Integer.parseInt(Logica.darParametroEmpresa(idEmpresa, 16));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		boolean usarTiposPicking = (Logica.darParametroEmpresa(idEmpresa, 33)).equals("1");
				
		
		if(!usarTiposPicking)
		{
			tipoRe = "0";
		}
		
		String zonas = "";
		
		if(tipoRe.equals("3")){
			try {
				String[] values = req.split("&");
				zonas = buscarFiltro("zonas", values);
			} catch (Exception e) {
			}
		}
		
		List<ArticuloLineaReposicion> articulos = Logica.darListaLineasRepo(deposito,marca,clase,seccion, centr, mayo, genero,categoria, fechaI, fechaF,tope,spick,sprioridad, distribucion,idEmpresa, idOrigen, canales, couriers,reqFactura, tipoRe, zonas);

		int cantidadLineas = 0;
		int cantidadUnidades = 0;
		
		
		int cantidaDestino = 0;
		
		Hashtable<Integer, Integer> depositosHT = new Hashtable<>();
		
		if(articulos == null) {
			return mapping.findForward("ok");
		}
		
		for (ArticuloLineaReposicion ar : articulos) 
		{
			depositosHT.put(ar.getDestino().getId(), ar.getDestino().getId());
			cantidadLineas++;
			cantidadUnidades+=ar.getCantidad();
		}
		
		request.setAttribute("cantidadLineas", cantidadLineas);
		request.setAttribute("cantidadUnidades", cantidadUnidades);
		
		
		cantidaDestino = new ArrayList<>(depositosHT.values()).size();
		
		if(cantidaDestino>1)
		{
			int capacidadClasificacion = Logica.encuentrDarCapacidadSorter(idEmpresa);
			
			if(cantidaDestino>capacidadClasificacion)
			{
				request.setAttribute("menError", "Atencion: la capacidad que dispone para clasificar pedidos es inferior a los distintos destinos de los mismos <br/> "+cantidaDestino+" destinos y "+capacidadClasificacion+" ubicaciones de clasificacion <br/> Puede generar pickings hasta a "+capacidadClasificacion+" destinos diferentes");
				//session.setAttribute("capacidadClasificacion", capacidadClasificacion);
			}
			session.setAttribute("capacidadClasificacion", capacidadClasificacion);
		}
		
		List<DataIDDescripcion> depositos= null;
		if(mayo)
		{
			depositos= Logica.darListaDepositos(100, true,false,false,idEmpresa);
		}
		else
		{
			depositos= Logica.darListaDepositos(0,true,false,false,idEmpresa);
		}
		
		if(session.getAttribute("secciones")==null)
		{
			List<DataIDDescripcion> marcas = Logica.darListaDataIdDescripcion("art_marca",idEmpresa);
			List<DataIDDescripcion> secciones = Logica.darListaDataIdDescripcion("art_seccion",idEmpresa);
			List<DataIDDescripcion> clases = Logica.darListaDataIdDescripcion("art_clase",idEmpresa);
			List<DataIDDescripcion> generos = Logica.darListaDataIdDescripcion("art_genero",idEmpresa);
			List<DataIDDescripcion> categorias = Logica.darListaDataIdDescripcion("art_categoria",idEmpresa);
			List<DataIDDescripcion> distribuciones = Logica.darListaDataIdDescripcion("vista_rep_art_distribucion",idEmpresa);
			
			
			
			session.setAttribute("marcas", marcas);
			session.setAttribute("distribuciones", distribuciones);
			session.setAttribute("secciones", secciones);
			session.setAttribute("clases", clases);
			session.setAttribute("generos", generos);
			session.setAttribute("categorias", categorias);
			
			
		}
			
		session.setAttribute("depositos", depositos);
		session.setAttribute("articulosRepo", articulos);
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








































