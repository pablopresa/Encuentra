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

import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.elementoPicking;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.MovimientoAlmacen;
import dataTypes.DataIDDescripcion;
import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;
import models.PickingBulto;

public class OptionPickingPackage extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		
		try {
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}			
			
			String option = request.getParameter("option");	//opcion ingresada por el usuario
			int opcion = 0;
			try {
				opcion = Integer.parseInt(option);
			} catch (Exception e) {
				session.setAttribute("menError", "Seleccione una opcion valida");
				return mapping.findForward("no");
			}						
				
			if (opcion == 0) {//mando a indicar el ojo de los sobrantes
				return mapping.findForward("toStock");
			}
			else {
				PickingBulto pickingBulto = (PickingBulto) session.getAttribute("modeloPickingBulto");				
				String origen = pickingBulto.getIdBulto();
				if(origen == null || origen.equals("")) {
					session.setAttribute("menError", "No se encontro el origen de esta mercaderia");
					return mapping.findForward("no");
				}
				
				bulto b = Logica.BuscarBulto(pickingBulto.getIdBulto(), idEmpresa);
				
				MovimientoAlmacen movimientoAlmacen = null;
				List<elementoPicking> movsAlmacen = null;
				boolean result = false;
											//compruebo que se pueda mover ese bulto (no tiene mas mercaderia reservada)
				if(opcion == 1) {			//tomar bulto y especificar sobrantes
											//voy a buscar el bulto a la BD 
											//busco los articulos que no necesito y estan en el bulto
											//muestro articulos sobrantes y asignar una ubicacion (por defecto la ubicacion 0)
					String idOjo = request.getParameter("idOjo");
					if(idOjo == null || idOjo.equals("")) {
						session.setAttribute("menError", "Debe indicar una ubicacion valida donde dejar la mercaderia");
						return mapping.findForward("toStock");
					}
					int deposito = Integer.parseInt(uLog.getDeposito());
					if(Logica.encuentraExisteUbica(idOjo, idEmpresa, deposito) <= 0) {	//existe la ubicacion
						session.setAttribute("menError", "Esta ubicacion no esta registrada en el sistema");
						return mapping.findForward("toStock");
					}
					if(!Logica.isBultoConVariasReservas(pickingBulto.getIdBulto(),idEmpresa, pickingBulto.getIdPicking())) {
						session.setAttribute("menError", "Este bulto no se puede mover, ya hay mercaderia reservada dentro de el");
						return mapping.findForward("no");
					}
					movsAlmacen = darSobrantes(pickingBulto, b);
					movimientoAlmacen = new MovimientoAlmacen(origen,idOjo, uLog.getNumero(),movsAlmacen);
					result = Logica.MovimientoDeArticulos(movimientoAlmacen, idEmpresa);
					if(result){
						//movimiento de bulto
						movsAlmacen = new ArrayList<>();
						elementoPicking movBulto = new elementoPicking(1, pickingBulto.getIdBulto(), 0, 0, 0, null);
						movsAlmacen.add(movBulto);
						movimientoAlmacen = new MovimientoAlmacen(pickingBulto.getUbicacion(),"1", uLog.getNumero(),movsAlmacen);
						result = Logica.MovimientoDeArticulos(movimientoAlmacen, idEmpresa);
					}
				}
				else {						
											//modifico el packing list 
											//(me llevo articulos sueltos y dejo bulto con PL modificado en la ubicacion)
											//redirecciono a /v3/handHeld/MobLineaRepoForm.jsp para continuar con el picking normal

					
					movsAlmacen = darEncontrados(pickingBulto, b);					
					
					movimientoAlmacen = new MovimientoAlmacen(origen,"1",uLog.getNumero(),movsAlmacen);
					result = Logica.MovimientoDeArticulos(movimientoAlmacen, idEmpresa);
				}
				
				if(result) {
					session.removeAttribute("modeloPickingBulto");
					return finalizarLineaRepo(idEmpresa, session, mapping, pickingBulto.getUbicacion(), Logica, uLog);
				}
				else {
					if(opcion == 1) {
						session.setAttribute("menError", "Sucedio un error haciendo el movimiento de mercaderia, intentelo e nuevo o consulte con soporte");
						return mapping.findForward("toStock");
					}
					else {						
						session.setAttribute("menError", "Sucedio un error haciendo el movimiento de mercaderia, intentelo e nuevo o consulte con soporte");
						return mapping.findForward("no");
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return mapping.findForward("ok");
	}
	
	public List<elementoPicking> darSobrantes(PickingBulto pickingBulto, bulto b){
		List<elementoPicking> movsAlmacen = new ArrayList<>();
		
		try {			
			elementoPicking s = null;
			Hashtable<String, elementoPicking> repetidos = new Hashtable<>();			
			elementoPicking elemento = null;
			
			//sobrantes
			for(bultoContenido bc: b.getContenidoList()) {
				if(pickingBulto.getElementos().get(bc.getIdArticulo()) != null) {
					
					if(repetidos.get(bc.getIdArticulo()) != null) {	
						elemento = repetidos.get(bc.getIdArticulo());
						if(bc.getCantidad() > elemento.getPick()) {	//si mi bultocontenido tiene mas cantida entonces van a haber sobrantes						
							s = new elementoPicking(bc.getCantidad() - elemento.getPick(),bc.getIdArticulo(),0, bc.getPicking(), bc.getRecepcion(), b.getIdBulto());
							s.setReserva(elemento.getPick());
							movsAlmacen.add(s);
						}
						if(bc.getCantidad() < elemento.getPick()) {	//si mi bultocontenido tiene menos unidades de las encontradas significa que voy a tener 
																	//otra linea con el mismo articulo, en esta linea no hay sobrantes pero guardo la diferencia para comparar en la otra linea
							s = new elementoPicking(elemento.getPick() - bc.getCantidad(),bc.getIdArticulo(),0, bc.getPicking(), bc.getRecepcion(), b.getIdBulto());
							repetidos.put(s.getIdArticulo(), s);						
						}
					}
					else {
						elemento = pickingBulto.getElementos().get(bc.getIdArticulo());
						if(bc.getCantidad() > elemento.getPick()) {	//si mi bultocontenido tiene mas cantida entonces van a haber sobrantes						
							s = new elementoPicking(bc.getCantidad() - elemento.getPick(),bc.getIdArticulo(),0, bc.getPicking(), bc.getRecepcion(), b.getIdBulto());
							s.setReserva(elemento.getPick());
							movsAlmacen.add(s);
						}
						if(bc.getCantidad() < elemento.getPick()) {	//si mi bultocontenido tiene menos unidades de las encontradas significa que voy a tener 
																	//otra linea con el mismo articulo, en esta linea no hay sobrantes pero guardo la diferencia para comparar en la otra linea
							s = new elementoPicking(elemento.getPick() - bc.getCantidad(),bc.getIdArticulo(),0, bc.getPicking(), bc.getRecepcion(), b.getIdBulto());
							repetidos.put(s.getIdArticulo(), s);						
						}
					}
										
				}
				else {																							//si mi elementoPicking no existe es que no se reservo ese art del bulto 
																												//entonces van a haber sobrantes
					s = new elementoPicking(bc.getCantidad(),bc.getIdArticulo(),0, bc.getPicking(), bc.getRecepcion(), b.getIdBulto());
					s.setReserva(0);
					movsAlmacen.add(s);
				}
			}
		} catch (Exception e) {
			System.out.println("error dando sobrantes bulto parcial");
			e.printStackTrace();
		}
		return movsAlmacen;
	}
	
	public List<elementoPicking> darEncontrados(PickingBulto pickingBulto, bulto b){
		List<elementoPicking> movsAlmacen = new ArrayList<>();
		
		try {			
			elementoPicking s = null;
			Hashtable<String, elementoPicking> repetidos = new Hashtable<>();			
			elementoPicking elemento = null;
			
			//encontrados
			for(bultoContenido bc: b.getContenidoList()) {
				if(pickingBulto.getElementos().get(bc.getIdArticulo()) != null) {
					
					if(repetidos.get(bc.getIdArticulo()) != null) {	
						elemento = repetidos.get(bc.getIdArticulo());					
						s = new elementoPicking(elemento.getPick(),bc.getIdArticulo(),0, bc.getPicking(), bc.getRecepcion(), b.getIdBulto());
						s.setReserva(elemento.getPick());
						movsAlmacen.add(s);
						
						if(bc.getCantidad() < elemento.getPick()) {	//si mi bultocontenido tiene menos unidades de las encontradas significa que voy a tener 
																	//otra linea con el mismo articulo, en esta linea no hay sobrantes pero guardo la diferencia para comparar en la otra linea
							s = new elementoPicking(elemento.getPick() - bc.getCantidad(),bc.getIdArticulo(),0, bc.getPicking(), bc.getRecepcion(), b.getIdBulto());
							repetidos.put(s.getIdArticulo(), s);						
						}
					}
					else {
						elemento = pickingBulto.getElementos().get(bc.getIdArticulo());				
						s = new elementoPicking(elemento.getPick(),bc.getIdArticulo(),0, bc.getPicking(), bc.getRecepcion(), b.getIdBulto());
						s.setReserva(elemento.getPick());
						movsAlmacen.add(s);
						
						if(bc.getCantidad() < elemento.getPick()) {	//si mi bultocontenido tiene menos unidades de las encontradas significa que voy a tener 
																	//otra linea con el mismo articulo
							s = new elementoPicking(elemento.getPick() - bc.getCantidad(),bc.getIdArticulo(),0, bc.getPicking(), bc.getRecepcion(), b.getIdBulto());
							repetidos.put(s.getIdArticulo(), s);						
						}
					}
										
				}
			}
		} catch (Exception e) {
			System.out.println("error dando sobrantes bulto parcial");
			e.printStackTrace();
		}
		return movsAlmacen;
	}
	
	public ActionForward finalizarLineaRepo(int idEmpresa, HttpSession session, ActionMapping mapping, String ubicacion, Logica Logica, Usuario uLog) {
		try {
			Utilidades util = new Utilidades();
			int idDepoWEB = util.darParametroEmpresaINT(idEmpresa,5);
			boolean verific = util.darParametroEmpresaBool(idEmpresa,1);
			int idTarea = (Integer) session.getAttribute("idTarea");
			int idMain = (Integer) session.getAttribute("idMain");
			String nombreTarea = (String) session.getAttribute("nombreTarea");
			List <DataLineaRepo> lista = (List<DataLineaRepo>) session.getAttribute("repoArt");
			
			List <DataLineaRepo> noNcontrados = (List<DataLineaRepo>) session.getAttribute("noNcontrados");
			DataLineaRepo voy = (DataLineaRepo) session.getAttribute("voy");
			int cauntasVan = (Integer) session.getAttribute("cuantasVoy");
			//int unidad = (Integer) session.getAttribute("unidad");
			int idPicking = (Integer) session.getAttribute("idPicking");
			String confirmar = ubicacion;
			
			int qty = 1;
			int porcentaje = 0;
			int unidad = 1;
			List<DataIDDescripcion> remitoEC = (List<DataIDDescripcion>) session.getAttribute("remitoECtarea");
			
			Logica.encuentraUpdateAvanceLineaTarea(idTarea,unidad,idMain,idEmpresa );
			
			//PARAMETRO VERIFICACIONES AUTOMATICAS
			if(voy.getAutoVerificacion()==1) {
				if(remitoEC==null) {
					remitoEC = new ArrayList<>();
				}
				DataIDDescripcion data = new DataIDDescripcion(qty,voy.getIdArticulo());
				data.setIdB(idDepoWEB);
				data.setDescripcionB(voy.getPedido()+"");
				remitoEC.add(data);
				session.setAttribute("remitoECtarea", remitoEC);
				
				EcommerceProcessOrders pro = new EcommerceProcessOrders();
				pro.confirmarSKUForus(voy.getIdArticulo(), voy.getIdDepOrigen(), qty,voy.getPedido(),idEmpresa,uLog);
				//MOVIMIENTO DE STOCK
				//GENERACION DE REMITO
			}
			
			//pregunto cuanto pide la linea
			if(voy.getSolicitada()==unidad)
			{
				
				
				cauntasVan++;
				porcentaje = 0;
				try
				{
					porcentaje = ((cauntasVan)*100) / (lista.size()-1);
				}
				catch
				(Exception e)
				{
					
				}
				 /* 	sumo cuantas van++*/
				 
				/*  pongo unidad en 0*/
				unidad=0;
				/*  agarro el nuevo voy con lista.get(cuantasVan)*/
				/*
				 *  
				 *  guardo el avance*/
				Logica.encuentraUpdateAvanceTarea(idTarea, porcentaje, cauntasVan,idEmpresa);
				Logica.encuentraAltaNoncontrados(idTarea, voy, 1,cauntasVan,idEmpresa);
				
				if((cauntasVan)!=lista.size())
				{
					Logica.encuentraUpdateAvanceLineaTarea(idTarea,unidad,idMain,idEmpresa );
					voy = lista.get(cauntasVan);
					 /*  seteo voy
					 *  seteo cuantasVan
					 *  Seteo unidad
					 *  chau
					 */
					if(!voy.getCubi().equals(confirmar)){
						session.setAttribute("ojoConfirmado", "");
					}
					session.setAttribute("voy", voy);
					session.setAttribute("cuantasVoy", cauntasVan);
					session.setAttribute("unidad",unidad);
					
					return mapping.findForward("ok");
					
				}
				else
				{
					
					Logica.encuentraUpdateLineasPicking(idPicking,idEmpresa);
					Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(),0,idEmpresa);
					//int idDeposito = Integer.parseInt(uLog.getDeposito());
					List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
					
					//PARAMETRO VERIFICACIONES AUTOMATICAS IMPRIME EL TICKET DEL PICKING
					if(voy.getAutoVerificacion()==1) {
						if(remitoEC!=null) {
							ImpresionesPDF.imprimirTicketMovStock(voy.getIdDepOrigen(), idDepoWEB, uLog.getNick(), 
									"Remito del picking "+idPicking, 
									remitoEC, idPicking+"",1, uLog.getIdEquipo(),idEmpresa, 1);
						}
					}
					
					session.setAttribute("tarMob", tarMob);
					session.setAttribute("menError","Ha concretado correctamente la reposicion");
					
					
					if (noNcontrados == null)
					{
						uLog.registrarEventoHilo(session.getId(), "Tarea Finalizada", idPicking,101);
						util.etiquetas_verificacion_destinos(lista, uLog, idPicking, idEmpresa, nombreTarea);
						return mapping.findForward("fin");
					
					}
					else
					{
						session.setAttribute("menError","Muestre la siguiente lista a su supervisor");
						uLog.registrarEventoHilo(session.getId(), "Tarea Finalizada con faltantes", idPicking,101);
						util.etiquetas_verificacion_destinos(lista, uLog, idPicking, idEmpresa, nombreTarea);
						return mapping.findForward("noFin");
					}
				}
			}
			else
			{
				/*
				 * esto quiere decir que hay mas
				 * unidad ya est? sumada
				 
				 *  seteo voy
				 *  seteo cuantasVan
				 *  Seteo unidad*/
				 session.setAttribute("voy", voy);
				 session.setAttribute("cuantasVoy", cauntasVan);
				 session.setAttribute("unidad",unidad);
				 return mapping.findForward("ok");
				 /*  chau
				 */
				
			}			
		
		} catch (Exception e) {
			System.out.println("error finalizando linea de bulto");
			e.printStackTrace();
			session.setAttribute("menError","error finalizando linea de bulto");
			return mapping.findForward("no");
		}
	}
}
