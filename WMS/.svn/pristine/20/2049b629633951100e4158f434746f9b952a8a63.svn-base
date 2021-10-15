package web.picking;

import java.util.ArrayList;
import java.util.Enumeration;
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
import beans.encuentra.DataLineaRepo;
import beans.encuentra.Tarea;
import dataTypes.DataIDDescripcion;
import logica.ListasPickingBultos;
import logica.Logica;
import logica.LogicaBulto;
import logica.Utilidades;

public class _EncuentraAltaPicking extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		System.out.println("alta de picking");
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		List<ArticuloLineaReposicion> articulosIn = new ArrayList<>();
		List<List<ArticuloLineaReposicion>> pickingsPedidos = null; //new ArrayList<List<ArticuloLineaReposicion>>(); 
		String saveType = request.getParameter("tipoSave");
		
		List<ArticuloLineaReposicion> articulos = (List<ArticuloLineaReposicion>) session.getAttribute("articulosRepo"); //Lista de filtrados
		int cantidades = 0;
		String tipoRe = (String) session.getAttribute("tipoRe");
		StringBuilder sb = new StringBuilder();
		
		//Boolean clasificaPedidos = util.darParametroEmpresaBool(idEmpresa, 49);
		Boolean clasificaPedidos = request.getParameter("chkSorter") != null 
				? Integer.parseInt(request.getParameter("chkSorter")) == 1 
				: false;
		for (ArticuloLineaReposicion ar : articulos) 
		{
			if(tipoRe.equals("2")){											//SI ES ECOMMERCE AGARRO TODOS LOS NUMEROS DE PEDIDOS Y VOY A BUSCAR LOS ARTICULOS A LA BASE
				String param = request.getParameter(ar.getPedido()+"-"+ar.getIdArticulo());			//PARA QUE NO ME QUEDEN PEDIDOS PARCIALES
				if(param!=null && param.equals("on"))
				{					
					sb.append(ar.getPedido()+",");
				}
			}
			else{
				String param = request.getParameter(ar.getSinc().getId()+"-"+ar.getIdArticulo()+"-"+ar.getDestino().getId()+"-"+ar.getSolicitud());
				if(param!=null && param.equals("on"))
				{
					if(clasificaPedidos){
						ar.setPosClasif(ar.getSolicitud()+""+ar.getDestino().getId());
					}
					else{
						ar.setPosClasif(""+ar.getDestino().getId());
					}
					articulosIn.add(ar);
					cantidades+=ar.getCantidad();
				}
			}
			
		}
		if(tipoRe.equals("2")){												//SI ES ECOMMERCE VOY A BUSCAR LOS ARTICULOS
			articulosIn = Logica.darArtsLineasRepoEC(sb,idEmpresa);						//Y SUMO SUS CANTIDADES
			for(ArticuloLineaReposicion a:articulosIn){				
				cantidades+= a.getCantidad();
			}
		}
		
		Hashtable<Integer, DataIDDescripcion> depositosHT = new Hashtable<>();
		int cantidaDestino =0;
		
		for (ArticuloLineaReposicion ar : articulosIn)    // articulosIn es la lista de los seleccionados
		{
			depositosHT.put(ar.getDestino().getId(), ar.getDestino());
		}
		
		List <DataIDDescripcion> destinosList = new ArrayList<>(depositosHT.values()); 
		cantidaDestino = destinosList.size();
		
		if(cantidaDestino>1)
		{
			int capacidadClasificacion = 0;
			try
			{
				capacidadClasificacion = (Integer) session.getAttribute("capacidadClasificacion");
			}
			catch(Exception e)
			{
				
			}
			
			if(cantidaDestino>capacidadClasificacion)
			{
				request.setAttribute("menError", "Atencion: Seleccione menos destinos <br/> "+cantidaDestino+" destinos y "+capacidadClasificacion+" ubicaciones de clasificacion <br/> Puede generar pickings hasta a "+capacidadClasificacion+" destinos diferentes");
				session.setAttribute("capacidadClasificacion", capacidadClasificacion);
				return mapping.findForward("no");
			}
			else
			{
				/*for (ArticuloLineaReposicion ar : articulos) 
				{					
					ar.setPosClasif(ar.getDestino().getId());
				}*/				
			}
		}
		
		session.setAttribute("depositosHTinRepo", depositosHT);
		session.setAttribute("cantiInRepo", cantidades);
		session.setAttribute("articulosIn",articulosIn);
		
		if(saveType.equals("3"))//lista de articulos a pickear de zona de estiba
		{					
			
			List<DataLineaRepo> list = new ArrayList<>();
			
			
			List<DataIDDescripcion> articulosEnZona = Logica.darArticuculosPallets(articulosIn, idEmpresa);
			Hashtable<String,String> bultosHT = new Hashtable<>();
			Hashtable<String,String> skuHT = new Hashtable<>();			
			String inns = "";
			boolean pri = true;
			
			for (DataIDDescripcion d : articulosEnZona) 
			{
			
				bultosHT.put(d.getDescripcionB(), d.getDescripcionB());
				skuHT.put(d.getDescripcion(), d.getDescripcion());
			}
			
			Enumeration<String> e = bultosHT.keys();
			  
	        while (e.hasMoreElements()) 
	        {
	        	if(pri)
	        	{
	        		pri = false;
	        		inns += "'"+e.nextElement()+"'";
	        	}
	        	else
	        	{
	        		inns += ",'"+e.nextElement()+"'";
	        	}
	        	
	        }
			Hashtable<String, DataIDDescripcion>  artOjos = Logica.darOjosArti(inns,idEmpresa,1);
			
			inns = "";
			pri = true;
			 e = skuHT.keys();
			while (e.hasMoreElements()) 
	        {
	        	if(pri)
	        	{
	        		pri = false;
	        		inns += "'"+e.nextElement()+"'";
	        	}
	        	else
	        	{
	        		inns += ",'"+e.nextElement()+"'";
	        	}
	        	
	        }
			Hashtable<String, String>  descripciones = Logica.darDescripcionArticulos(inns, idEmpresa);
			int canti = 0;
			
			List<ArticuloLineaReposicion> listaTarea = new  ArrayList<ArticuloLineaReposicion>();
			for (DataIDDescripcion d : articulosEnZona) 
			{			
				canti += d.getIdB();
				
				DataIDDescripcion ao = artOjos.get(d.getDescripcionB());						
				DataLineaRepo dl = new DataLineaRepo();
				dl.setDescripcion(d.getDescripcionB());
				dl.setPedidoSTR(d.getDescripcionC());
				dl.setIdArticulo(d.getDescripcion());
				dl.setCubi(ao.getDescripcionB());
				dl.setEstnte(ao.getIdB());
				dl.setRecorrido(ao.getId());
				dl.setDescEstanteria(ao.getDescripcion());
				dl.setModulo(Integer.parseInt(ao.getIdLong()+""));
				if(d.getIdB()<=d.getId())
				{
					dl.setSolicitada(d.getIdB());
				}
				else
				{
					dl.setSolicitada(d.getId());
				}
				
				
				dl.setJustificacion(descripciones.get(d.getDescripcion()));
				list.add(dl);
				
				ArticuloLineaReposicion ar = new ArticuloLineaReposicion(dl.getSolicitada(),dl.getIdArticulo(),"",
						null, null, null);
				listaTarea.add(ar);
				
			}
			
			
			Hashtable<String, DataLineaRepo> htArts = new Hashtable<>();
			
			for (DataLineaRepo d : list)
			{
				String key = d.getIdArticulo()+""+d.getCubi();
				//la clave es el  articulo y el ojo
				
				DataLineaRepo in = null;
				if(htArts.containsKey(key))
				{
					in = htArts.get(key);
					in.setSolicitada(in.getSolicitada()+d.getSolicitada());
					
				}
				else
				{
					in = d;
				}
				htArts.put(key,in);
			}
			

			list = new ArrayList<>(htArts.values());
			
			
			
			
			

	

			
			
			//Creo tarea de reposicion picking
			Tarea tarea = new Tarea();
			DataIDDescripcion estado = new DataIDDescripcion();
			estado.setId(0);
			
			tarea.setEstado(estado);		
			tarea.setPorcentaje(0);
			tarea.setResponsable(new DataIDDescripcion(uLog.getNumero(),uLog.getNick()));
			tarea.setTipo(new DataIDDescripcion(117,"Reposicion Picking"));
			List<Usuario> usuarios = new ArrayList<>();
			Usuario usu = new Usuario();
			usu.setNumero(0);
			usuarios.add(usu);
			tarea.setUsuarios(usuarios);
			if(tarea.getUsuarios().size()>1)
			{	
				tarea.setParcial(false);
			}
			else
			{
				tarea.setParcial(true);
			}
			
			tarea.setObservacion("Reposicion Picking");
			tarea.setArticulosIn(listaTarea);	
			tarea.setCantidadPares(canti);
			

			session.setAttribute("tareaRepo",tarea);
			

			
			// y le muestro las tareas a todos los elevadoristas
			session.setAttribute("bultos", list);			
			return mapping.findForward("listaBultos");
				
		}
		
		
		
		
		
		if(saveType.equals("2"))
		{					
			session.setAttribute("dipick", "dipick");			
			session.setAttribute("singleTar", "1");			
		}
		else if(saveType.equals("0"))
		{					
			session.setAttribute("singleTar", "0");		
		}
		else
		{						
			session.setAttribute("dipick", "dipick");			
			session.setAttribute("singleTar", "1");		
		}
		
		List <DataIDDescripcion> usuariosOperarios = Logica.encuentraDaroperarios(idEmpresa);
		List<DataIDDescripcion> distribucionCarga = Logica.infoAvanceTareasOperarios(idEmpresa);
		/*List <DataIDDescripcion> usuariosSuper = Logica.encuentraDarSuper(idEmpresa);
		List <DataIDDescripcion> tiposTarea = Logica.encuentraDarTiposTarea(idEmpresa);*/
		
		Boolean paramRemision = util.darParametroEmpresaBool(idEmpresa, 22);
		Boolean paramPickingBulto = util.darParametroEmpresaBool(idEmpresa, 27);
		int paramBultoCantMinxSku = util.darParametroEmpresaINT(idEmpresa, 28)+1;
		Boolean paramPickingBultosGenerales = util.darParametroEmpresaBool(idEmpresa, 37);
		Boolean paramPickingArticulosAlmacen = util.darParametroEmpresaBool(idEmpresa, 38);
		Boolean paramPrioridadBulto = util.darParametroEmpresaBool(idEmpresa, 42);
		
		session.setAttribute("paramRemision", paramRemision);
		session.setAttribute("paramPickingBulto", paramPickingBulto);
		session.setAttribute("paramBultoCantMinxSku", paramBultoCantMinxSku);
		session.setAttribute("paramPickingBultosGenerales", paramPickingBultosGenerales);
		session.setAttribute("paramPickingArticulosAlmacen", paramPickingArticulosAlmacen);
		session.setAttribute("paramPrioridadBulto", paramPrioridadBulto);
		
		session.setAttribute("operarios", usuariosOperarios);
		session.setAttribute("distribucionCarga", distribucionCarga);
		if(saveType.equals("2"))
		{	
			
			session.setAttribute("listaWMS","1");
			return mapping.findForward("listaWMS");
			
		}
		return mapping.findForward("tar");	
	}
}








































