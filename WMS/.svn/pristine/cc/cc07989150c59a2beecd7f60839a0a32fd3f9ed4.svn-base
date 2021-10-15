package web.picking;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Fecha;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataPicking;
import beans.encuentra.IPrint;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.LogicaBulto;
import logica.Utilidades;


public class _EncuentraConfirmarDistPick extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 HttpSession session = request.getSession();
		 Logica Logica = new Logica();
		 Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		 session.removeAttribute("menError");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			String redirect = (String) session.getAttribute("redirectVerificacion");
		 int equipo = 0;
		 
		 
		 if(uLog!=null){
			 equipo = uLog.getEquipo_trabajo();
			 ServletContext context = request.getSession().getServletContext();
			 Hashtable<String, bulto> cajasAbiertas = (Hashtable<String, bulto>) context.getAttribute(idEmpresa+"cajasAbiertas");
			try 
			{
				if(cajasAbiertas==null || cajasAbiertas.size()==0){
					cajasAbiertas = Logica.BultosAbiertos(idEmpresa);
				}

				int cantS = 0;
				List<DataPicking> pickings = (List<DataPicking>) session.getAttribute("pickings");
				String art = request.getParameter("art");
				String posicion = request.getParameter("posicion");
				String cantidad = request.getParameter("qty");
				int idDestino = 0;
				int qty	= util.parseStringInt(cantidad);
				if(qty <= 0) {
					session.setAttribute("menError", "Debe ingresar una cantidad mayor o igual a 1");
					return mapping.findForward(redirect);
				}
				
				int idPick = 0;
				//si es verificacion de un picking de un solo destino viene el destino vacio, me fijo el destino en la lista de picking
				if(posicion == null || posicion.equals("")) {
					if(pickings ==  null || pickings.isEmpty()){
						session.setAttribute("menError", "NO SE IDENTIFICO EL PICKING, CIERRE SESSION E INTENTELO NUEVAMENTE");
						return mapping.findForward(redirect);
					}
					else{
						posicion = pickings.get(0).getPosSort(); 
					}
				}
				DataPicking pickingsSel = new DataPicking();
				LogicaBulto logicaB = new LogicaBulto();
				Boolean clasificaPedido = util.darParametroEmpresaBool(idEmpresa, 49);
				Long pedido = new Long("0");
				
				Boolean paramVerificacion = (Boolean) session.getAttribute("paramVerificacion");
				if(paramVerificacion==null){
					paramVerificacion = util.darParametroEmpresaBool(idEmpresa, 52);
					session.setAttribute("paramVerificacion", paramVerificacion);
				}
												
				for (DataPicking pi : pickings) 
				{					
					if(pi.getArticulo().equalsIgnoreCase(art) && posicion.equals(pi.getPosSort()))
					{
						int cantidadTope = pi.getSol();
						if(paramVerificacion)
						{
							cantidadTope = pi.getPick();						
						}
						if(qty <= cantidadTope - pi.getVerificada()) {
							pi.setVerificada(qty);
							//pickingsSel.add(pi);
							if(pi.isEsBulto()) {
								pi.setEstaCerrado(logicaB.esBultoCerrado(pi.getArticulo(), idEmpresa));
							}
							pickingsSel = pi;
							idPick = pi.getIdPicking();
							idDestino = pi.getDestino().getId();
							if(clasificaPedido){
								pedido = pi.getIdPedido();
							}							
							break;
						}
						else {
							session.setAttribute("menError", "No se necesitan tantas unidades de este articulo, ingrese una cantidad menor");
							return mapping.findForward(redirect);
						}
						
					}
				}
				
				int idDepoWEB = util.darParametroEmpresaINT(idEmpresa,5);
				int cantidadLog = 0;
				String msjEventos = "";
				if (idPick!=0)
				{
					List<String> queries = new ArrayList<>();
					String enCaja = "";
					if(!posicion.equals(idDepoWEB+"")){
						bulto b = null;
						bultoContenido bc = null;
						if(pickingsSel.isEsBulto() && pickingsSel.isEstaCerrado()) { 
							
							b = logicaB.obtenerBultoCerrado(pickingsSel.getArticulo(), idEmpresa, pickingsSel.getIdPicking());
							b.setDestino(idDestino+"");
							b.setPosSort(posicion);
							b.setPedido(pedido);
							b.setRemision_al_cerrar(pickingsSel.getRemision_bulto() == 1);
							System.out.println("El bulto se va a remitir? "+b.isRemision_al_cerrar());
							
							//List<String> contenidoBulto = Collections.list(b.getContenido().keys());
							uLog.registrarEventoHilo(session.getId(), " VERIFICANDO BULTO "+b.getIdBulto(), pickings.get(0).getIdPicking(), 112);  
							b = b.Agregar_A_Bulto_Reposicion_BultoCerrado(b.getContenidoList(), pickingsSel);
							
							Logica.encuentraBajaArticulosOjos(qty,art, "1",idEmpresa);	//BAJO DE ZONA DE PICKING
							Logica.encuentraMoverOjos(idDestino+"P",b.getIdBulto(),qty,uLog.getNumero(),idEmpresa);		//AGREGO BULTO A ZONA DE CLASIFICACION
							Logica.IngresarMovimientoArticuloTipo("", idDestino+"P", b.getIdBulto(), qty,  uLog.getNumero(),"ADD",idEmpresa);	//REGISTRO LA UBICACION DEL BULTO
							session.setAttribute("remitirBulto", b);
							return clasificar(uLog, session, art, enCaja, pickings, idPick, idEmpresa, mapping,"cerrarBC" , idDepoWEB, posicion, util, qty,cajasAbiertas);
						}
						
						else if(cajasAbiertas.get(equipo+"-"+posicion)==null){
							Fecha fecha = new Fecha(0,0,0);
							fecha.now();
							String sufijo = posicion;
							if(posicion.length() > 4) {
								sufijo = sufijo.substring(0,4);
							}
							b = new bulto(equipo+sufijo+fecha.darFechaString(),"Caja distribucion lc."+idDestino,false,0,0,0,0.0,false,"",
									uLog.getNumero(),idDestino+"",idEmpresa);
							b.setEquipo_trabajo(equipo);
							b.setRemision_al_cerrar(pickingsSel.getRemision_bulto()==1);
							b.setPosSort(posicion);
							b.setPedido(pedido);
							bc = new bultoContenido();
							bc.setIdArticulo(art);
							bc.setCantidad(qty);
							bc.setPicking(idPick);
							bc.setUsuario(uLog.getNumero());
							b.Crear_Bulto(idEmpresa);
							//b.Agregar_A_Bulto(bc);
							//uLog.registrarEventoHilo(session.getId(), "ABRIENDO CAJA - cantidad: 0", pickings.get(0).getIdPicking(), 112);
							msjEventos += "-ABRIENDO CAJA - cantidad: 0";
							b = b.Agregar_A_Bulto_Reposicion(bc, pickingsSel);
								
							cajasAbiertas.put(equipo+"-"+posicion, b);
								
							Logica.encuentraMoverOjos(idDestino+"P",b.getIdBulto(),1,uLog.getNumero(),idEmpresa);		//AGREGO BULTO A ZONA DE CLASIFICACION
							Logica.IngresarMovimientoArticuloTipo("", idDestino+"P", b.getIdBulto(), 1,  uLog.getNumero(),"ADD",idEmpresa);	//REGISTRO LA UBICACION DEL BULTO
						}
						else{
							if(cajasAbiertas.get(equipo+"-"+posicion).isRemision_al_cerrar() != (pickingsSel.getRemision_bulto()==1)) {
								//VERIFICO COMO SE VA A REMITIR ESTA CAJA
								session.setAttribute("menError", "USTED ESTA TRABAJANDO CON 2 MODALIDADES DE REMISION DIFERENTES, DEBE CERRAR EL BULTO EXISTENTE PARA PODER VERIFICAR ESTE ARTICULO");
								return mapping.findForward(redirect);
							}
							bc = new bultoContenido();
							bc.setIdArticulo(art);
							bc.setCantidad(qty);
							bc.setPicking(idPick);
							bc.setUsuario(uLog.getNumero());
							
							b = cajasAbiertas.get(equipo+"-"+posicion);
							try {
								cantidadLog = b.unidadesEnBulto();
							} catch (Exception e) {
								cantidadLog = 0;
							}
							//uLog.registrarEventoHilo(session.getId(), "CAJA ABIERTA - cantidad antes de ingresar art: "+cantidadLog, pickings.get(0).getIdPicking(), 112);
							msjEventos += "-CAJA ABIERTA - cantidad antes de ingresar art: "+cantidadLog;
							b = b.Agregar_A_Bulto_Reposicion(bc, pickingsSel);
							cajasAbiertas.put(equipo+"-"+posicion, b);
						}
						
						context.setAttribute(idEmpresa+"cajasAbiertas", cajasAbiertas);
						
						enCaja = ", ingresado en caja "+b.getIdBulto();
						//uLog.registrarEventoHilo(session.getId(), "PERSISTIO: "+b.isPersistencia(), pickings.get(0).getIdPicking(), 112);
						msjEventos += "-PERSISTIO: "+b.isPersistencia();
						
						try {
							cantidadLog = b.unidadesEnBulto();
						} catch (Exception e) {
							cantidadLog = 0;
						}
						//uLog.registrarEventoHilo(session.getId(), "cantidad luego de ingresar art: "+cantidadLog, pickings.get(0).getIdPicking(), 112);
						msjEventos += "-cantidad luego de ingresar art: "+cantidadLog;
						
						if(!b.isPersistencia()) {	//SI NO LOGRO AGREGAR EL ARTICULO EN EL BULTO
							session.setAttribute("menError", "ESTE ARTICULO NO SE GUARDO EN LA CAJA, INTENTELO NUEVAMENTE");
							queries.add(uLog.query_registrarEventoHilo(session.getId(), msjEventos, pickings.get(0).getIdPicking(), 112));
							queries.add(uLog.query_registrarEventoHilo(session.getId(), "Fallo al querer ingresar a bulto", pickings.get(0).getIdPicking(), 112));
							return mapping.findForward(redirect);
						}
						
						//Logica.encuentraUpdateClasifPicking(pickingsSel,idEmpresa);
						//uLog.registrarEventoHilo(session.getId(), "encuentraBajaArticulosOjos(1,"+art+",1"+idEmpresa+")", pickings.get(0).getIdPicking(), 112);
						msjEventos += "-encuentraBajaArticulosOjos(1,"+art+",1"+idEmpresa+")";
						
						queries.add(Logica.query_encuentraBajaArticulosOjos(qty,art, "1",idEmpresa));	//BAJO DE ZONA DE PICKING
						
						//uLog.registrarEventoHilo(session.getId(), "IngresarMovimientoArticuloTipo(1, "+b.getIdBulto()+", "+art+", 1,  "+uLog.getNumero()+",BUL,"+idEmpresa+")",pickings.get(0).getIdPicking(), 112);
						msjEventos += "-IngresarMovimientoArticuloTipo(1, "+b.getIdBulto()+", "+art+", 1,  "+uLog.getNumero()+",BUL,"+idEmpresa+")";
						queries.add(uLog.query_registrarEventoHilo(session.getId(), msjEventos,pickings.get(0).getIdPicking(), 112));
						queries.add(Logica.query_IngresarMovimientoArticuloTipo("1", b.getIdBulto(), art, qty,  uLog.getNumero(),"BUL",idEmpresa));	//REGISTRO EL MOVIMIENTO DE PICKING AL BULTO
						
					}
					
					if(posicion.equals(idDepoWEB+"")){	
						List<DataPicking> dp = new ArrayList<>();
						if(pickingsSel.isEsBulto() && pickingsSel.isEstaCerrado()) {
							dp = pickingsSel.getContenido();
							for(DataPicking d: dp) {
								d.setVerificada(d.getContenidoQty());
							}
							redirect = "Verif";
						}
						else {
							dp.add(pickingsSel);
						}						
						queries.add(Logica.query_encuentraUpdateVerifPicking(dp,false,idEmpresa));
					}
					//---
					
					
					
					Logica.getEper().persistirL(queries);
					return clasificar(uLog, session, art, enCaja, pickings, idPick, idEmpresa, mapping, redirect, idDepoWEB, posicion, util, qty,cajasAbiertas);
				}//---
				else{
					session.setAttribute("menError", "ESTE ARTICULO NO SE GUARDO EN LA CAJA, CIERRE SESSION E INTENTELO NUEVAMENTE");
					return mapping.findForward(redirect);
				}
					
				
			}
			catch (Exception e) 
			{
				System.out.println("llegamos al catch");
				e.printStackTrace();
				session.setAttribute("mensaje", e.getMessage());
				return mapping.findForward(redirect);

			}	
		 }
		 else{
				return mapping.findForward("log");
			}
		 
		
		
	}
	
	public ActionForward clasificar(Usuario uLog, HttpSession session, String art, String enCaja, List<DataPicking> pickings, int idPick, int idEmpresa, 
			ActionMapping mapping, String redirect, int idDepoWEB, String posicion, Utilidades util, int qty, Hashtable<String, bulto> cajasAbiertas) {
		String retorno = "";
		//LOGUEAMOS EVENTO
		uLog.registrarEvento(idPick, qty,112,0,true, session.getId(), "Verificando articulo "+art+enCaja);
		Logica logica = new Logica();
		//4 seg
		pickings = logica.encuentraDarPickingCBulto(idPick,idEmpresa);
		List <DataPicking> pickingsToVer = new ArrayList<>();
		StringBuilder arregloArts = new StringBuilder();
		StringBuilder arregloDestino = new StringBuilder();
		StringBuilder arregloDestinosQty = new StringBuilder();
		
		Hashtable<String, Hashtable<String, DataPicking[]>> clasificador = new Hashtable<String, Hashtable<String,DataPicking[]>>();
		Hashtable<String,  DataPicking[]> htDestino = null;
		Boolean paramVerificacion = (Boolean) session.getAttribute("paramVerificacion");
		if(paramVerificacion==null){
			paramVerificacion = util.darParametroEmpresaBool(idEmpresa, 52);
			session.setAttribute("paramVerificacion", paramVerificacion);
		}
		for (DataPicking d : pickings) 
		{
			int cantidadTope = d.getSol();
			if(paramVerificacion)
			{
				cantidadTope = d.getPick();						
			}
			if(cantidadTope-d.getVerificada()>0)
			{
				pickingsToVer.add(d);
				
				try 
				{	//TENGO EL ARTICULO EN LA COLECCION Y TENGO EL DESTINO
					
					DataPicking[] arr = d.addX(clasificador.get(d.getArticulo()).get(d.getPosSort()),d);
					clasificador.get(d.getArticulo()).put(d.getPosSort(),arr);
				} 
				catch (Exception e) 
				{
					try 
					{	//TENGO EL ARTICULO EN LA COLECCION PERO NO TENGO EL DESTINO
						DataPicking[] arr0 = {};
						DataPicking[] arr = d.addX(arr0,d);
						clasificador.get(d.getArticulo()).put(d.getPosSort(),arr);
						
						
					} 
					catch (Exception e2) 
					{	//NO TENGO NI ARTICULO NI DESTINO
						DataPicking[] arr0 = {};
						htDestino = new Hashtable<String, DataPicking[]>();
						htDestino.put(d.getPosSort(), arr0);
						DataPicking[] arr = d.addX(arr0,d);
						htDestino.put(d.getPosSort(), arr);
						clasificador.put(d.getArticulo(), htDestino);
					}
				}
				
			}
			
		}
		System.out.println("");
		if(clasificador.size()>0)
		{
			session.setAttribute("pickings", pickingsToVer);
			/*
			int corteA = arregloArts.toString().length()-1;
			int corteB = arregloDestino.toString().length()-1;
			int corteC = arregloDestinosQty.toString().length()-1;
			
			session.setAttribute("arregloArticulos", arregloArts.toString().substring(0, corteA));
			session.setAttribute("arregloDestinos", arregloDestino.toString().substring(0, corteB));
			session.setAttribute("arregloDestinosQty", arregloDestinosQty.toString().substring(0, corteC));
			*/
			Boolean paramCantidad = util.darParametroEmpresaBool(idEmpresa, 46);
			Boolean paramDetalle = util.darParametroEmpresaBool(idEmpresa, 47);					
			
			session.setAttribute("paramCantidad", paramCantidad);
			session.setAttribute("paramDetalle", paramDetalle);
			
		
			
			Gson gson = new Gson();
			
			
			
			String clasificador_json = gson.toJson(clasificador);
			
			
			
			
			session.setAttribute("clasificador_json", clasificador_json);
			return mapping.findForward(redirect);
		}
		else
		{
			session.setAttribute("menError", "Ha clasificado todos los articulos del picking "+idPick);
			if(idPick>0){
				try {
					//CAMBIO ESTADO REPO SIEMPRE QUE NO SEA ECOMMERCE
					if(!posicion.equals(idDepoWEB+"") && pickings.get(0).getRemision_bulto()==1){
						logica.UpdatePickingStatus(7, pickings.get(0).getIdPicking(),idEmpresa);
					}				
					//COMENTARIO LOG
					uLog.registrarEventoHilo(session.getId(), "Se finalizo la verificacion del picking", pickings.get(0).getIdPicking(), 112);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
			{
				
				session.setAttribute("menError", "Ha clasificado todos los articulos del picking "+idPick+", pero no se pudo marcar como terminado");
				
			}
			
			if(util.darParametroEmpresaBool(idEmpresa, 54))
			{
				int equipo = uLog.getEquipo_trabajo();
				bulto bull = cajasAbiertas.get(equipo+"-"+posicion);
				List<bulto> boxe = new ArrayList<>();
				boxe.add(bull);
				session.setAttribute("myboxes", boxe);
				
				return mapping.findForward("boxes");
			}
			
			return mapping.findForward("fin");
		}
	}	
	
}















