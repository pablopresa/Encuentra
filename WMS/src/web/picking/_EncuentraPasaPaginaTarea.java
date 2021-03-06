package web.picking;

import java.util.ArrayList;
import java.util.Enumeration;
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

import beans.Usuario;
import beans.elementoPicking;
import beans.encuentra.DataArticulo;
import beans.encuentra.DataLineaListaTareasMob;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataPicking;
import dataTypes.DataIDDescripcion;
import logica.ImpresionesPDF;
import logica.Logica;
import logica.LogicaBulto;
import logica.Utilidades;
import main.EcommerceProcessOrders;
import models.PickingBulto;
import persistencia._EncuentraConexion;
import persistencia._EncuentraPersistir;

public class _EncuentraPasaPaginaTarea extends Action {

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		LogicaBulto logicaB = new LogicaBulto();
		session.removeAttribute("menError");

		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if (idEmpresa == 0) {
			return mapping.findForward("LOGIN");
		}

		int idDepoWEB = util.darParametroEmpresaINT(idEmpresa, 5);
		boolean verific = util.darParametroEmpresaBool(idEmpresa, 1);
		int idTarea = (Integer) session.getAttribute("idTarea");
		int idMain = (Integer) session.getAttribute("idMain");
		String nombreTarea = (String) session.getAttribute("nombreTarea");

		String art = "";
		try {
			art = request.getParameter("base").trim().toUpperCase();
			art = util.validarComillas(art);
		}
		catch (Exception e) {
			art = request.getParameter("base");
		}

		List<DataLineaRepo> lista = (List<DataLineaRepo>) session.getAttribute("repoArt");

		List<DataLineaRepo> noNcontrados = (List<DataLineaRepo>) session.getAttribute("noNcontrados");
		DataLineaRepo voy = (DataLineaRepo) session.getAttribute("voy");
		int cauntasVan = (Integer) session.getAttribute("cuantasVoy");
		int unidad = (Integer) session.getAttribute("unidad");
		int idPicking = (Integer) session.getAttribute("idPicking");

		String confirmar = request.getParameter("confirmarOjo");

		String noNco = request.getParameter("noEncontrado");
		// List<DataIDDescripcion> remitoEC = (List<DataIDDescripcion>)
		// session.getAttribute("remitoECtarea");

		if (noNco == null && !confirmar.equalsIgnoreCase(voy.getCubi())) {
			session.setAttribute("menError", "Tome la mercaderia de donde se indica");
			return mapping.findForward("ok");
		}

		session.setAttribute("ojoConfirmado", confirmar);

		// noEncontrado
		// cuantasVoy
		// unidad

		if (noNco == null) {
			// SUMO UNA UNIDAD
			boolean esValido = false;

			if (cauntasVan == 0) {
				Logica.encuentraAltaEventoTarea(idTarea, 1, uLog.getNumero(), 0, idEmpresa);
			}
			// cambiar control para que valide contra el que le pide y no contra la lista. 0

			// traigo barras del context
			ServletContext context = request.getSession().getServletContext();
			Hashtable<String, String> artBarra = (Hashtable<String, String>) context
					.getAttribute("barras_" + idEmpresa);
			DataArticulo articulo = new DataArticulo();

			try {
				articulo.setId(artBarra.get(art));
			} catch (Exception e) {
				articulo = Logica.encuentraCodArticulo(art, idEmpresa);
			}

			if (articulo.getId() == null) { // SI NO ENCUENTRO NADA EN EL CONTEXT VOY A LA BASE
				articulo = Logica.encuentraCodArticulo(art, idEmpresa);
			}

			/*
			 * String artic = articulo.getId(); String voyARt = voy.getIdArticulo().trim();
			 * System.out.println(artic.equals(voyARt));
			 */

			if ((articulo.getId() != null) && articulo.getId().equalsIgnoreCase(voy.getIdArticulo().trim())) {
				esValido = true;
			} else {
				// List<String> codigos = Logica.bultosValidosEnOjo(voy.getIdArticulo(),
				// voy.getCubi(), idEmpresa);
				if (voy.getDescripcion().equalsIgnoreCase("Bulto")) {
					List<String> codigos = logicaB.bultosValidosEnOjo(voy.getIdArticulo(), voy.getCubi(), idEmpresa,
							voy.getContenido());
					if (codigos.contains(art)) {
						esValido = true;
						_EncuentraConexion ec = new _EncuentraConexion();
						// Aca deberia cambiar el contenido reservado
						boolean result = ec.cambiarReserva(voy.getIdArticulo(), art, voy.getCubi(), idPicking,
								idEmpresa, voy.getContenido());
						if (result == false) {
							session.setAttribute("menError", "Error al intercambiar bultos, intente nuevamente");
							return mapping.findForward("ok");
						}
					}
				} else {
					esValido = false;
					uLog.registrarEvento(idPicking, 0, 107, 0, true, session.getId(),
							" articulo incorrecto " + articulo.getId());
				}
			}

			int porcentaje = 0;
			if (esValido) {

				int qty = 1;

				try {
					String cantidad = request.getParameter("qty");
					qty = Integer.parseInt(cantidad);
				} catch (Exception e) {
					qty = 1;
				}
				List<String> queries = new ArrayList<>();
				for (int i = 0; i < qty; i++) {
					unidad++;

					if (!voy.getDescripcion().equalsIgnoreCase("Bulto")) {

						queries.add(Logica.query_encuentraUpdateAvanceLineaTarea(idTarea, unidad, idMain, idEmpresa));

					} else {

						if (voy.isBultoCerrado()) {
							uLog.registrarEvento(idPicking, 1, 107, 0, true, session.getId(),
									" bulto completo pickeado " + articulo.getId());
							queries.addAll(MovimientosAlmacen(voy, articulo, idEmpresa, uLog, Logica, 1));

							Logica.encuentraUpdateAvanceLineaTarea(idTarea, unidad, idMain, idEmpresa); // AVANCE DE

							// List<DTO_ArticuloCantidad> contenido =
							// ec.buscarContenidoBulto(voy.getIdArticulo());
							for (DataLineaRepo articuloEnBulto : voy.getContenido()) {
								Logica.encuentraUpdateAvancePicking(idPicking, articuloEnBulto.getIdArticulo(),
										articuloEnBulto.getSolicitada(), articuloEnBulto.getIdDepOrigen(),
										articuloEnBulto.getIdDepDestino(), verific, articuloEnBulto.getPedido(),
										articuloEnBulto.getDocumento(), idEmpresa, idDepoWEB,
										articuloEnBulto.getAutoVerificacion()); // AVANCE DE LA REPO
							}
						} else {
							// unificar articulos para mostrar
							// hashParaPicking
							// uLog.registrarEvento(idPicking, 1,107,0,true, session.getId(), " pickeando
							// bulto parcial "+articulo.getId());
							Hashtable<String, List<DataLineaRepo>> articulo_lineasRepo = new Hashtable<>();
							Hashtable<String, elementoPicking> elementos = new Hashtable<>();
							String articulosStr = "";
							int cantidadTotal = 0;
							int count = 0;
							for (DataLineaRepo articuloEnBulto : voy.getContenido()) {
								cantidadTotal += articuloEnBulto.getSolicitada();
								count += articuloEnBulto.getPicked();

								articulosStr += "'" + articuloEnBulto.getIdArticulo() + "',";
								// HASH PARA UNIFICAR ARTICULOS
								elementoPicking dataArt = new elementoPicking(articuloEnBulto.getSolicitada(),
										articuloEnBulto.getIdArticulo(), articuloEnBulto.getPicked());
								if (elementos.get(articuloEnBulto.getIdArticulo()) == null) {
									elementos.put(articuloEnBulto.getIdArticulo(), dataArt);
								} else {
									dataArt.setCantidad(
											dataArt.getCantidad() + elementos.get(articuloEnBulto).getCantidad());
									dataArt.setPick(dataArt.getPick() + elementos.get(articuloEnBulto).getPick());
									elementos.put(articuloEnBulto.getIdArticulo(), dataArt);
								}

								// HASH PARA PICKING
								if (articulo_lineasRepo.get(articuloEnBulto.getIdArticulo()) == null) {
									List<DataLineaRepo> array = new ArrayList<>();
									array.add(articuloEnBulto);
									articulo_lineasRepo.put(articuloEnBulto.getIdArticulo(), array);
								} else {
									articulo_lineasRepo.get(articuloEnBulto.getIdArticulo()).add(articuloEnBulto);
								}
							}

							try {
								articulosStr = articulosStr.substring(0, articulosStr.length() - 1);
							} catch (Exception e) {
								request.setAttribute("menError", "No se pudieron encontrar los articulos del bulto");
								return mapping.findForward("no");
							}
							Hashtable<String, String> barras = Logica.darBarrasHT(articulosStr, idEmpresa);

							PickingBulto pickingBulto = new PickingBulto();
							pickingBulto.setArticulo_lineasRepo(articulo_lineasRepo);
							pickingBulto.setElementos(elementos);
							pickingBulto.setBarras(barras);
							pickingBulto.setCantidadTotal(cantidadTotal);
							pickingBulto.setCount(count);
							pickingBulto.setIdPicking(idPicking);
							pickingBulto.setIdBulto(art);
							pickingBulto.setUbicacion(voy.getCubi());

							session.setAttribute("modeloPickingBulto", pickingBulto);

							return mapping.findForward("pickPackage");
						}
					}

					if (voy.getSolicitada() == unidad) {
						break;
					}
				}
				if (!queries.isEmpty()) {
					queries.add(uLog.query_registrarEvento(idPicking, qty, 107, 0, true, session.getId(),
							" articulo pickeado " + articulo.getId()));
					queries.addAll(MovimientosAlmacen(voy, articulo, idEmpresa, uLog, Logica, qty));
					queries.add(Logica.query_encuentraUpdateAvancePicking(idPicking, voy.getIdArticulo(), qty,
							voy.getIdDepOrigen(), voy.getIdDepDestino(), verific, voy.getPedido(), voy.getDocumento(),
							idEmpresa, idDepoWEB, voy.getAutoVerificacion())); // AVANCE DE LA REPO

					_EncuentraPersistir pe = new _EncuentraPersistir();
					StringBuilder sb = new StringBuilder();
					for (String q : queries) {

						System.out.println(q);

						sb.append(q);
					}
					boolean ok = pe.persistir(sb.toString());
					if (!ok) {
						// hay que manejar este error
					}
				}

				// PARAMETRO VERIFICACIONES AUTOMATICAS
				if (voy.getAutoVerificacion() == 1) {
					/*
					 * if(remitoEC==null) { remitoEC = new ArrayList<>(); } DataIDDescripcion data =
					 * new DataIDDescripcion(qty,voy.getIdArticulo()); data.setIdB(idDepoWEB);
					 * data.setDescripcionB(voy.getPedido()+""); remitoEC.add(data);
					 * session.setAttribute("remitoECtarea", remitoEC);
					 */

					EcommerceProcessOrders pro = new EcommerceProcessOrders();
					pro.confirmarSKUForus(voy.getIdArticulo(), voy.getIdDepOrigen(), qty, voy.getPedido(), idEmpresa,
							uLog);
					// MOVIMIENTO DE STOCK
					// GENERACION DE REMITO
				}

				// pregunto cuanto pide la linea
				if (voy.getSolicitada() == unidad) {

					cauntasVan++;
					porcentaje = 0;
					try {
						porcentaje = ((cauntasVan) * 100) / (lista.size() - 1);
						if(porcentaje > 100) 
							porcentaje = 100;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					/* sumo cuantas van++ */

					/* pongo unidad en 0 */
					unidad = 0;
					/* agarro el nuevo voy con lista.get(cuantasVan) */
					/*
					 * 
					 * guardo el avance
					 */
					Logica.encuentraUpdateAvanceTarea(idTarea, porcentaje, cauntasVan, idEmpresa);
					Logica.encuentraAltaNoncontrados(idTarea, voy, 1, cauntasVan, idEmpresa);

					if ((cauntasVan) != lista.size()) {
						Logica.encuentraUpdateAvanceLineaTarea(idTarea, unidad, idMain, idEmpresa);
						voy = lista.get(cauntasVan);
						/*
						 * seteo voy seteo cuantasVan Seteo unidad chau
						 */
						if (!voy.getCubi().equalsIgnoreCase(confirmar)) {
							session.setAttribute("ojoConfirmado", "");
						}
						session.setAttribute("voy", voy);
						if (voy.getDescripcion().equalsIgnoreCase("Bulto")) {
							List<String> bultos = logicaB.bultosValidosEnOjo(voy.getIdArticulo(), voy.getCubi(), idEmpresa, voy.getContenido());
							session.setAttribute("codigosRecomendados", bultos);
						}
						session.setAttribute("cuantasVoy", cauntasVan);
						session.setAttribute("unidad", unidad);

						return mapping.findForward("ok");						
					}
					else
					{
						
						Logica.encuentraUpdateLineasPicking(idPicking,idEmpresa);
						Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(),0,idEmpresa);
						//int idDeposito = Integer.parseInt(uLog.getDeposito());
						List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog,idEmpresa);
						
						//PARAMETRO VERIFICACIONES AUTOMATICAS IMPRIME EL TICKET DEL PICKING
						Remitos(voy, idPicking, idEmpresa, idDepoWEB, uLog, util, Logica);

						session.setAttribute("tarMob", tarMob);
						session.setAttribute("menError", "Ha concretado correctamente la reposicion");

						if (noNcontrados == null) {
							uLog.registrarEventoHilo(session.getId(), "Tarea Finalizada", idPicking, 101);
							util.etiquetas_verificacion_destinos(lista, uLog, idPicking, idEmpresa, nombreTarea);
							return mapping.findForward("fin");

						} else {
							devuelveStock(idPicking, idEmpresa, idDepoWEB, uLog, util, Logica);

							session.setAttribute("menError", "Muestre la siguiente lista a su supervisor");
							uLog.registrarEventoHilo(session.getId(), "Tarea Finalizada con faltantes", idPicking, 101);
							util.etiquetas_verificacion_destinos(lista, uLog, idPicking, idEmpresa, nombreTarea);
							return mapping.findForward("noFin");
						}
					}
				} else {
					/*
					 * esto quiere decir que hay mas unidad ya est? sumada
					 * 
					 * seteo voy seteo cuantasVan Seteo unidad
					 */
					session.setAttribute("voy", voy);
					if (voy.getDescripcion().equalsIgnoreCase("Bulto")) {
						List<String> bultos = logicaB.bultosValidosEnOjo(voy.getIdArticulo(), voy.getCubi(), idEmpresa, voy.getContenido());
						session.setAttribute("codigosRecomendados", bultos);
					}
					session.setAttribute("cuantasVoy", cauntasVan);
					session.setAttribute("unidad", unidad);
					
					///
					
					return mapping.findForward("ok");
					/*
					 * chau
					 */

				}

			} else {
				// setea mensaje de error devolve NO
				uLog.registrarEvento(idPicking, 0, 107, 0, true, session.getId(),
						"El c?digo del articulo no es valido");
				session.setAttribute("menError", "El c?digo del articulo no es valido");
				return mapping.findForward("ok");
			}
		} else {
			/*********************************************
			 * no lo encontr?
			 **************************************************/
			uLog.registrarEvento(idPicking, 0, 107, 0, true, session.getId(),
					"No encontro el articulo " + voy.getIdArticulo());
			Logica.justificarNoEncontrado(idPicking, voy.getIdArticulo(), voy.getIdDepOrigen(), voy.getIdDepDestino(),
					voy.getPedido(), idEmpresa);
			/*
			 * si no encuentra le pasa la cantidad solicitada a la cantidad que resta. si
			 * sale o se pausa, cuando retome va a ir a la linea con el numero de la nueva
			 * cantidad solicitada, o sea 0 o 1
			 */
			if (noNcontrados == null) {
				noNcontrados = new ArrayList<>();
			}
			// unidades que se pasa por parametro en el script
			int unidadesNOENC = voy.getSolicitada();
			unidadesNOENC = unidadesNOENC - unidad;
			unidad++;

			Logica.encuentraUpdateAvanceLineaTarea(idTarea, 0, idMain, idEmpresa); // en donde esta 0 antes tenia "unidad", dejaba mal el ultimo item para la siguiente linea y se rompia todo

			int unidadesRestan = voy.getSolicitada() - unidad;
			int unidadIba = unidad;

			DataLineaRepo iba = new DataLineaRepo();
			iba.setModulo(voy.getModulo());
			iba.setIdArticulo(voy.getIdArticulo());
			iba.setEstnte(voy.getEstnte());
			iba.setEstnteria(voy.getEstnteria());
			iba.setSolicitada(unidadesNOENC);
			iba.setSotck(voy.getSotck());
			iba.setPedido(voy.getPedido());
			iba.setIdDepOrigen(voy.getIdDepOrigen());
			noNcontrados.add(iba);
			session.setAttribute("noNcontrados", noNcontrados);

			unidad = 0;List<String> codigos = logicaB.bultosValidosEnOjo(voy.getIdArticulo(), voy.getCubi(), idEmpresa, voy.getContenido());
			session.setAttribute("codigosRecomendados", codigos);

			if (unidadesRestan <= 0) {

				if (lista.size() > cauntasVan) {
					if ((cauntasVan + 1) != lista.size()) {
						cauntasVan++;
						int porcentaje = ((cauntasVan) * 100) / (lista.size() - 1);
						Logica.encuentraUpdateAvanceTarea(idTarea, porcentaje, cauntasVan, idEmpresa);
						voy = lista.get(cauntasVan);
						session.setAttribute("voy", voy);
						if (voy.getDescripcion().equalsIgnoreCase("Bulto")) {
							List<String> bultos = logicaB.bultosValidosEnOjo(voy.getIdArticulo(), voy.getCubi(), idEmpresa, voy.getContenido());
							session.setAttribute("codigosRecomendados", bultos);
						}
						if (!voy.getCubi().equalsIgnoreCase(confirmar)) {
							session.setAttribute("ojoConfirmado", "");
						}

					}
					else
					{
						Logica.encuentraUpdateAvanceLineaTarea(idTarea,unidad,idMain,idEmpresa );
						
						//PARAMETRO VERIFICACIONES AUTOMATICAS IMPRIME EL TICKET DEL PICKING
						Remitos(voy, idPicking, idEmpresa, idDepoWEB, uLog, util, Logica);


						System.out.println("aca");
						Logica.encuentraUpdateLineasPicking(idPicking, idEmpresa);
						Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(), 0, idEmpresa);
						// int idDeposito = Integer.parseInt(uLog.getDeposito());
						List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog, idEmpresa);
						session.setAttribute("tarMob", tarMob);

						devuelveStock(idPicking, idEmpresa, idDepoWEB, uLog, util, Logica);

						session.setAttribute("menError", "Muestre la siguiente lista a su supervisor");
						uLog.registrarEventoHilo(session.getId(), "Tarea Finalizada con faltantes", idPicking, 101);
						util.etiquetas_verificacion_destinos(lista, uLog, idPicking, idEmpresa, nombreTarea);
						return mapping.findForward("noFin");
					}
					session.setAttribute("cuantasVoy", cauntasVan);
					session.setAttribute("unidad", unidad);
					return mapping.findForward("ok");

				}
				else
				{
					Logica.encuentraUpdateAvanceLineaTarea(idTarea,unidad,idMain,idEmpresa );

					//PARAMETRO VERIFICACIONES AUTOMATICAS IMPRIME EL TICKET DEL PICKING
					Remitos(voy, idPicking, idEmpresa, idDepoWEB, uLog, util, Logica);

					Logica.encuentraUpdateLineasPicking(idPicking, idEmpresa);
					Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(), 0, idEmpresa);
					// int idDeposito = Integer.parseInt(uLog.getDeposito());
					List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog, idEmpresa);
					session.setAttribute("tarMob", tarMob);

					devuelveStock(idPicking, idEmpresa, idDepoWEB, uLog, util, Logica);

					session.setAttribute("menError", "Muestre la siguiente lista a su supervisor");
					uLog.registrarEventoHilo(session.getId(), "Tarea Finalizada con faltantes", idPicking, 101);
					util.etiquetas_verificacion_destinos(lista, uLog, idPicking, idEmpresa, nombreTarea);
					return mapping.findForward("noFin");
				}
			} else {
				/// marco como no encontradas las unidades que restan
				for (int i = 1; i < unidadesNOENC; i++) {
					voy.setSolicitada(unidadesRestan);
					session.setAttribute("voy", voy);
					if (voy.getDescripcion().equalsIgnoreCase("Bulto")) {
						List<String> bultos = logicaB.bultosValidosEnOjo(voy.getIdArticulo(), voy.getCubi(), idEmpresa, voy.getContenido());
						session.setAttribute("codigosRecomendados", bultos);
					}
					Logica.encuentraUpdateAvanceLineaTarea(idTarea, unidadIba, idMain, idEmpresa);
					unidadIba++;
					unidadesRestan--;
					session.setAttribute("cuantasVoy", cauntasVan);
					session.setAttribute("unidad", unidad);
					try {
						int porcentaje = ((cauntasVan) * 100) / (lista.size() - 1);
						Logica.encuentraUpdateAvanceTarea(idTarea, porcentaje, cauntasVan, idEmpresa);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Logica.encuentraUpdateAvanceLineaTarea(idTarea, 0, idMain, idEmpresa);

				/// salto a la pr?xima linea
				if ((cauntasVan + 1) != lista.size()) {
					cauntasVan++;
					int porcentaje = ((cauntasVan) * 100) / (lista.size() - 1);
					Logica.encuentraUpdateAvanceTarea(idTarea, porcentaje, cauntasVan, idEmpresa);
					voy = lista.get(cauntasVan);
					session.setAttribute("voy", voy);
					if (voy.getDescripcion().equalsIgnoreCase("Bulto")) {
						List<String> bultos = logicaB.bultosValidosEnOjo(voy.getIdArticulo(), voy.getCubi(), idEmpresa, voy.getContenido());
						session.setAttribute("codigosRecomendados", bultos);
					}
					if (!voy.getCubi().equalsIgnoreCase(confirmar)) {
						session.setAttribute("ojoConfirmado", "");
					}

				}
				else
				{
					//PARAMETRO VERIFICACIONES AUTOMATICAS IMPRIME EL TICKET DEL PICKING
					Remitos(voy, idPicking, idEmpresa, idDepoWEB, uLog, util, Logica);

					System.out.println("aca");
					Logica.encuentraUpdateLineasPicking(idPicking, idEmpresa);
					Logica.encuentraAltaEventoTarea(idTarea, 2, uLog.getNumero(), 0, idEmpresa);
					// int idDeposito = Integer.parseInt(uLog.getDeposito());
					List<DataLineaListaTareasMob> tarMob = Logica.encuentraDarTareasMob(uLog, idEmpresa);
					session.setAttribute("tarMob", tarMob);

					devuelveStock(idPicking, idEmpresa, idDepoWEB, uLog, util, Logica);

					session.setAttribute("menError", "Muestre la siguiente lista a su supervisor");
					uLog.registrarEventoHilo(session.getId(), "Tarea Finalizada con faltantes", idPicking, 101);
					util.etiquetas_verificacion_destinos(lista, uLog, idPicking, idEmpresa, nombreTarea);
					return mapping.findForward("noFin");
				}
				session.setAttribute("cuantasVoy", cauntasVan);
				session.setAttribute("unidad", unidad);
				return mapping.findForward("ok");

			}
		}
	}

	public List<String> MovimientosAlmacen(DataLineaRepo voy, DataArticulo articulo, int idEmpresa, Usuario uLog,
			Logica Logica, int qty) {
		// BAJO CANTIDAD FISICA DEL OJO Y LAS RESERVADAS;
		// AGREGO ARTICULO A ZONA DE PICKING
		List<String> retorno = new ArrayList<>();
		retorno.addAll(Logica.query_encuentraUpdateOjosTienenArt(voy.getCubi(), articulo.getId(), idEmpresa, qty)); 
		retorno.addAll(Logica.querys_encuentraMoverOjos("1", articulo.getId(), qty, uLog.getNumero(), idEmpresa)); 
		// REGISTRO EL MOVIMIENTO DE MERCADERIA A ZONA DE PICKING
		retorno.add(Logica.query_IngresarMovimientoArticulo(voy.getCubi(), "1", articulo.getId(), qty, uLog.getNumero(),
				idEmpresa)); 
		return retorno;
	}

	public void devuelveStock(int pick, int idEmpresa, int idDepoWEB, Usuario uLog, Utilidades util, Logica l) {
		try {
			boolean devuelveStock = util.darParametroEmpresaBool(idEmpresa, 56);
			if (devuelveStock) {
				EcommerceProcessOrders pro = new EcommerceProcessOrders();

				Hashtable<Long, List<DataIDDescripcion>> devoluciones = new Hashtable<>();
				List<DataPicking> noEncontrados = l.encuentraDarNoEncontrados(pick, idEmpresa);
				for (DataPicking ne : noEncontrados) {
					DataIDDescripcion data = new DataIDDescripcion(ne.getSol(), ne.getArticulo());
					if (devoluciones.get(ne.getIdPedido()) == null) {
						devoluciones.put(ne.getIdPedido(), new ArrayList<>());
						devoluciones.get(ne.getIdPedido()).add(data);
					} else {
						devoluciones.get(ne.getIdPedido()).add(data);
					}
					pro.negarSKU(ne.getArticulo(), ne.getIdPedido(), ne.getOrigen().getId(), "", ne.getSol(), idEmpresa,
							false, devuelveStock, uLog);
				}

				Enumeration<Long> elements = devoluciones.keys();
				while (elements.hasMoreElements()) {
					Long key = elements.nextElement();

					util.remitir(idDepoWEB, Integer.parseInt(uLog.getDeposito()), uLog.getNumero(),
							devoluciones.get(key), key, 0, 0, 0, uLog.getNombre() + " " + uLog.getApellido(),
							"Cancelacion de movimiento de stock", idEmpresa, null, true);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Remitos(DataLineaRepo voy, int idPicking, int idEmpresa, int idDepoWEB, Usuario uLog, Utilidades util, Logica l) {
		if(voy.getAutoVerificacion()==1) {
			int cantidad_remitos = util.darParametroEmpresaINT(idEmpresa, 59);
			if(cantidad_remitos > 0) {
				List<DataIDDescripcion> remitoEC = l.remitoEc(idPicking, idEmpresa);						
				ImpresionesPDF.imprimirTicketMovStock(voy.getIdDepOrigen(), idDepoWEB, uLog.getNick(), 
						"Remito del picking "+idPicking, 
						remitoEC, idPicking+"",1, uLog.getIdEquipo(),idEmpresa, cantidad_remitos);
			}						
		}
	}
}
