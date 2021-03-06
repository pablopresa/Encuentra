package web.picking;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

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
import beans.Fecha;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataPicking;

public class _EncuentraConfirmarCantVerificacion extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		 Logica Logica = new Logica();
		 Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
		 int equipo = 0;
		 
		 String barras = (String) session.getAttribute("thisB");
		 
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
				String posicion = "";
				
				if(pickings ==  null || pickings.isEmpty()){
					request.setAttribute("html", "ok;;NO SE IDENTIFICO EL PICKING, CIERRE SESSION E INTENTELO NUEVAMENTE;;"+barras);
					return mapping.findForward("ok");
				}
				else{
					posicion = pickings.get(0).getDestino().getId()+"";
				}
				
				int idPick = 0;
				//List<DataPicking> pickingsSel = new ArrayList<>();
				DataPicking pickingsSel = new DataPicking();
				
				for (DataPicking pi : pickings) 
				{
					if(pi.getArticulo().equals(art))
					{
						if(pi.getArticulo().equals(art))
						{
							pi.setVerificada(1);
							//pickingsSel.add(pi);
							pickingsSel = pi;
							idPick = pi.getIdPicking();
							break;
						}						
					}
				}
				
				int cantidadLog = 0;
				if (idPick!=0){
					String enCaja = "";
					if(!posicion.equals("1200")){						
						bulto b = null;
						bultoContenido bc = null;
						if(cajasAbiertas.get(equipo+"-"+posicion)==null){
							Fecha fecha = new Fecha(0,0,0);
							fecha.now();
							b = new bulto(equipo+posicion+fecha.darFechaString(),"Caja distribucion lc."+posicion,false,0,0,0,0.0,false,"",
									uLog.getNumero(),posicion.replace(" ", ""),idEmpresa);
							b.setEquipo_trabajo(equipo);
							b.setRemision_al_cerrar(pickingsSel.getRemision_bulto()==1);
							bc = new bultoContenido();
							bc.setIdArticulo(art);
							bc.setCantidad(1);
							bc.setPicking(idPick);
							bc.setUsuario(uLog.getNumero());
							b.Crear_Bulto(idEmpresa);
							//b.Agregar_A_Bulto(bc);
							uLog.registrarEventoHilo(session.getId(), "ABRIENDO CAJA - cantidad: 0", pickings.get(0).getIdPicking(), 112);
							b = b.Agregar_A_Bulto_Reposicion(bc, pickingsSel);
							cajasAbiertas.put(equipo+"-"+posicion, b);
							
							Logica.encuentraMoverOjos(posicion+"P",b.getIdBulto(),1,uLog.getNumero(),idEmpresa);		//AGREGO BULTO A ZONA DE CLASIFICACION
							Logica.IngresarMovimientoArticuloTipo("",posicion+"P", b.getIdBulto(), 1,  uLog.getNumero(),"ADD",idEmpresa);	//REGISTRO LA UBICACION DEL BULTO
						}
						else{
							if(cajasAbiertas.get(equipo+"-"+posicion).isRemision_al_cerrar() != (pickingsSel.getRemision_bulto()==1)) {
								//VERIFICO COMO SE VA A REMITIR ESTA CAJA
								request.setAttribute("html", "ok;;USTED ESTA TRABAJANDO CON 2 MODALIDADES DE REMISION DIFERENTES, DEBE CERRAR EL BULTO EXISTENTE PARA PODER VERIFICAR ESTE ARTICULO;;"+barras);
								return mapping.findForward("ok");
							}
							bc = new bultoContenido();
							bc.setIdArticulo(art);
							bc.setCantidad(1);
							bc.setPicking(idPick);
							bc.setUsuario(uLog.getNumero());
							
							b = cajasAbiertas.get(equipo+"-"+posicion);
							try {
								cantidadLog = b.unidadesEnBulto();
							} catch (Exception e) {
								cantidadLog = 0;
							}
							
							uLog.registrarEventoHilo(session.getId(), "CAJA ABIERTA - cantidad antes de ingresar art: "+ cantidadLog
									, pickings.get(0).getIdPicking(), 112);
							b = b.Agregar_A_Bulto_Reposicion(bc, pickingsSel);
							cajasAbiertas.put(equipo+"-"+posicion, b);
						}
						enCaja = ", ingresado en caja "+b.getIdBulto();
						
						uLog.registrarEventoHilo(session.getId(), "PERSISTIO: "+b.isPersistencia(), pickings.get(0).getIdPicking(), 112);
						
						try {
							cantidadLog = b.unidadesEnBulto();
						} catch (Exception e) {
							cantidadLog = 0;
						}
						uLog.registrarEventoHilo(session.getId(), "cantidad luego de ingresar art: "+cantidadLog, pickings.get(0).getIdPicking(), 112);
						
						
						if(!b.isPersistencia()) {	//SI NO LOGRO AGREGAR EL ARTICULO EN EL BULTO
							request.setAttribute("html", "ok;;ESTE ARTICULO NO SE GUARDO EN LA CAJA, INTENTELO NUEVAMENTE;;"+barras);
							return mapping.findForward("ok");
						}
						
						uLog.registrarEventoHilo(session.getId(), "encuentraBajaArticulosOjos(1,"+art+",1"+idEmpresa+")", pickings.get(0).getIdPicking(), 112);
						Logica.encuentraBajaArticulosOjos(1,art, "1",idEmpresa);	//BAJO DE ZONA DE PICKING
						
						uLog.registrarEventoHilo(session.getId(), "IngresarMovimientoArticuloTipo(1, "+b.getIdBulto()+", "+art+", 1,  "+uLog.getNumero()+",BUL,"+idEmpresa+")",
								pickings.get(0).getIdPicking(), 112);
						Logica.IngresarMovimientoArticuloTipo("1",b.getIdBulto(), art, 1,  uLog.getNumero(),"BUL",idEmpresa);	//REGISTRO EL MOVIMIENTO DE PICKING AL BULTO
					}
					
					
					context.setAttribute(idEmpresa+"cajasAbiertas", cajasAbiertas);
					
					//Logica.encuentraAltaCantVerifII(verif, idPicking, articulo, destino, pedido, doc);
					if(posicion.equals("1200")){	
						List<DataPicking> dp = new ArrayList<>();
						dp.add(pickingsSel);
						Logica.encuentraUpdateVerifPicking(dp,false,idEmpresa);
					}
					
					
					//LOGUEAMOS EVENTO
					uLog.registrarEvento(idPick, 1,112,0,true, session.getId(), "Verificando articulo "+art+enCaja);
					
					pickings = Logica.encuentraDarPicking(idPick,idEmpresa);
					List <DataPicking> pickingsToVer = new ArrayList<>();
					StringBuilder arregloArts = new StringBuilder();
					StringBuilder arregloDestino = new StringBuilder();
					
					for (DataPicking d : pickings) 
					{
						if(d.getSol()-d.getVerificada()>0)
						{
							for(String s:d.getBarras()){
								arregloArts.append("'"+s+"'='"+d.getArticulo()+"',");
							}
							
							pickingsToVer.add(d);							
						}						
					}
					
					
					if(pickingsToVer.size()>0)
					{
						int corteA = arregloArts.toString().length()-1;
						
						barras = arregloArts.toString().substring(0, corteA);
						request.setAttribute("html", "ok;; ;;"+barras);
						session.setAttribute("pickings", pickingsToVer);
						session.setAttribute("thisB",barras);
						return mapping.findForward("ok");
					}
					else
					{
						// se cambio de session a request
						request.setAttribute("mensajeVS", "Ha clasificado todos los articulos del picking "+idPick);
						request.setAttribute("html", "fin;;Ha clasificado todos los articulos del picking "+idPick+";;"+barras);
						if(idPick>0){
							try {
								if(!posicion.equals("1200") && pickings.get(0).getRemision_bulto()==1){
									//CAMBIO ESTADO REPO
									Logica.UpdatePickingStatus(7, pickings.get(0).getIdPicking(),idEmpresa);
								}
													
								//COMENTARIO LOG
								uLog.registrarEventoHilo(session.getId(), "Se finalizo la verificacion del picking", pickings.get(0).getIdPicking(), 112);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						else{
							request.setAttribute("mensajeVS", "Ha clasificado todos los articulos del picking "+idPick+", pero no se pudo marcar como terminado");
							request.setAttribute("html", "fin;;Ha clasificado todos los articulos del picking "+idPick+", pero no se pudo marcar como terminado;;"+barras);
						}
						
						
						return mapping.findForward("ok");
					}
				}
				else{
					request.setAttribute("html", "ok;;EL ARTICULO "+art+" NO SE GUARDO EN LA CAJA, CIERRE SESSION E INTENTELO NUEVAMENTE;;"+barras);
					return mapping.findForward("ok");
				}
					
				
			}
			catch (Exception e) 
			{
				System.out.println("llegamos al catch");
				e.printStackTrace();
				request.setAttribute("html", "ok;;Sucedio un error;;"+barras);
				return mapping.findForward("ok");

			}	
		 }
		 else{
			 request.setAttribute("html", "log;; ;;"+barras);
				return mapping.findForward("ok");
			}
		 
		
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
