package web.picking;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.EnviaMail;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;
import dataTypes.DataReglaReposicion;




import beans.Articulo;


import beans.Estado;

import beans.ArticuloLineaReposicion;
import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Transporte;

public class _EncuentraEditValueLineaVM extends Action 
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
				
			
				int idSincro=Integer.parseInt(request.getParameter("idSincro"));
				String idArticulo = request.getParameter("articulo"); 
				int origen =Integer.parseInt(request.getParameter("origenID"));
				int destino=Integer.parseInt(request.getParameter("destinoID"));
				int cantidad=Integer.parseInt(request.getParameter("cant"));
				int borrar=Integer.parseInt(request.getParameter("borrar"));
				Long pedido=Long.parseLong(request.getParameter("pedido"));
				int pick=Integer.parseInt(request.getParameter("idpick"));
				
				
				
				Logica.updateValuelineaRepo(idSincro, idArticulo, origen, destino, cantidad, borrar,pedido,pick,idEmpresa);
				
				if(borrar==1){
					uLog.registrarEvento(0, 0,101,0,true, session.getId(), " se elimino de reposiciones pendientes el articulo "
							+idArticulo+" correspondiente a la distribucion "+pedido);
					
				}
						
				Logica.actualizarLogSincRepo(idSincro, "ATENCION: editando lineas: "+request.getQueryString(),100,idEmpresa);
				
				
				String marcaIN = (String) session.getAttribute("marcaSEL");
				String depositoIN = (String) session.getAttribute("depositoSEL");
				String claseIN = (String) session.getAttribute("claseSEL");
				String seccionIN = (String) session.getAttribute("seccionSEL");
				String categoriaIN = (String) session.getAttribute("categoriaSEL");
				String generoIN = (String) session.getAttribute("generoSEL");
				
				String central = (String) session.getAttribute("central");
					
				String mayorista = (String) session.getAttribute("mayorista");
					if(mayorista==null)
					{
						mayorista="0";
					}
					
				
				session.setAttribute("mayorista",mayorista);
				session.setAttribute("central",central);
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
				
				String req = (String) session.getAttribute("REQ");

				String deposito = "";
				String marca = "";
				String clase = "";
				String seccion = "";
				String genero = "";
				String categoria = "";
				String canales = "";
				String destinos = "";
				
				
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
					canales = buscarFiltro("canales", values);
					destinos = buscarFiltro("destinos", values);
				}
				catch(Exception e)
				{
					
				}
				int idOrigen = Integer.parseInt(uLog.getDeposito());
				
				List<ArticuloLineaReposicion> articulos = Logica.darListaLineasRepo(deposito,marca,clase,seccion, centr, mayo, genero,categoria,"","","","","","",idEmpresa,idOrigen,canales,destinos,0,"3","");
				int cantidadLineas = 0;
				int cantidadUnidades = 0;
				
				
				int cantidaDestino = 0;
				
				Hashtable<Integer, Integer> depositosHT = new Hashtable<>();
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
						session.setAttribute("capacidadClasificacion", capacidadClasificacion);
					}
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
					
					
					
					session.setAttribute("marcas", marcas);
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


