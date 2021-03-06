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
import main.EcommerceProcessOrders;

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

public class _EncuentraEditValueLineaRegla extends Action 
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
				System.out.println("");
				
				
				
				Logica.updateValuelineaRepo(idSincro, idArticulo, origen, destino, cantidad, borrar,pedido,pick,idEmpresa);
				if(borrar==1){
					uLog.registrarEvento(0, 0,101,0,true, session.getId(), " se elimino de reposiciones pendientes el articulo "
							+idArticulo+" correspondiente a la distribucion "+pedido);
					
				}
				/*if(pedido>0){
					EcommerceProcessOrders pro = new EcommerceProcessOrders();
					pro.confirmarSKUForus(idArticulo, 9000, cantidad, pedido,idEmpresa,uLog.getNumero());
					Logica.logPedido(new Long(pedido), uLog.getNumero(), 0, 
							"Confirmacion de articulo "+idArticulo+", debido a eliminacion de reposiciones pendientes", 0,idEmpresa);
					if(pro.todosConfirmados(new Long(pedido),idEmpresa)){
						Logica.updateEcommerceEstado(new Long(pedido), 2,idEmpresa,uLog.getNumero());
					}
				}*/
				
				/*		
				Logica.actualizarLogSincRepo(idSincro, "ATENCION: editando lineas: "+request.getQueryString(),100);
				
				
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
				}
				catch(Exception e)
				{
					
				}
				
				List<ArticuloLineaReposicion> articulos = Logica.darListaLineasRepo(deposito,marca,clase,seccion, centr, mayo, genero,categoria,"","","");
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
					int capacidadClasificacion = Logica.encuentrDarCapacidadSorter();
					
					if(cantidaDestino>capacidadClasificacion)
					{
						request.setAttribute("menError", "Atencion: la capacidad que dispone para clasificar pedidos es inferior a los distintos destinos de los mismos <br/> "+cantidaDestino+" destinos y "+capacidadClasificacion+" ubicaciones de clasificacion <br/> Puede generar pickings hasta a "+capacidadClasificacion+" destinos diferentes");
						session.setAttribute("capacidadClasificacion", capacidadClasificacion);
					}
				}
				
				List<DataIDDescripcion> depositos= null;
				if(mayo)
				{
					depositos= Logica.darListaDepositos(100, true,false,false);
				}
				else
				{
					depositos= Logica.darListaDepositos(0,true,false,false);
				}
				
				if(session.getAttribute("secciones")==null)
				{
					List<DataIDDescripcion> marcas = Logica.darListaDataIdDescripcion("art_marca");
					List<DataIDDescripcion> secciones = Logica.darListaDataIdDescripcion("art_seccion");
					List<DataIDDescripcion> clases = Logica.darListaDataIdDescripcion("art_clase");
					List<DataIDDescripcion> generos = Logica.darListaDataIdDescripcion("art_genero");
					List<DataIDDescripcion> categorias = Logica.darListaDataIdDescripcion("art_categoria");
					
					
					
					session.setAttribute("marcas", marcas);
					session.setAttribute("secciones", secciones);
					session.setAttribute("clases", clases);
					session.setAttribute("generos", generos);
					session.setAttribute("categorias", categorias);
					
					
				}
					
				session.setAttribute("depositos", depositos);
				session.setAttribute("articulosRepo", articulos);*/
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


