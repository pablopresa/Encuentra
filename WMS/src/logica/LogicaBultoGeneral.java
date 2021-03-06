package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpSession;

import beans.ArticuloLineaReposicion;
import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataPicking;
import beans.encuentra.Tarea;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import persistencia._EncuentraConexion;
import persistencia._EncuentraPersistir;

public class LogicaBultoGeneral {
	public bulto AgregarABultoReposicion_BultoCerrado(List<bultoContenido> bc, bulto b, DataPicking art, int idEmpresa) 
	{
		boolean salida = false;
		Hashtable<String,bultoContenido> bAux = new Hashtable<>();
		try{
			bAux = b.clonarContenido();
			_EncuentraPersistir eper = new _EncuentraPersistir();
			salida = eper.AgregarABulto_AfectarRepo_BultoCerrado(bc, b.getIdBulto(), art, idEmpresa);
			
			if (salida) {
				b.setPersistencia(salida);
				return b;
			}
			else {
				b.setPersistencia(salida);
				b.setContenido(bAux);
				return b;
			}
		}catch(Exception e){
			e.printStackTrace();
			b.setPersistencia(salida);
			b.setContenido(bAux);
			return b;
		}
	
	}
	
	public List<DataLineaRepo> BuscoEnBultos(List<DataLineaRepo> contenidoPickingActual, int valorMinimo, int idPicking, int idEmpresa, Usuario uLog, HttpSession session) {
		Logica logica = new Logica();
		List<DataLineaRepo> mayorMinimo = new ArrayList<>();
		List<DataLineaRepo> menorMinimo = new ArrayList<>();
		try {
			for (DataLineaRepo ac : contenidoPickingActual) {
				if(ac.getSolicitada() > valorMinimo) {
					mayorMinimo.add(ac.Clonar());
				} else {
					menorMinimo.add(ac.Clonar());
				}
			}
			
			/*Validar que los bultos no esten reservados*/
			
			//Recorrido bultos
			StringBuilder consultas = new StringBuilder();
			ListasPickingBultos lpb = devolverPickingBultos(valorMinimo, mayorMinimo, idPicking, idEmpresa);
			// encuentraReservarUbicacionII(String articulo, String cubi, int cantidad,int idPick,int dest,Long idpedido,boolean reserva, int idE) 

			for(DTO_BultoPuntaje bp : lpb.getBultosSirven()) {
				//for(DTO_ArticuloCantidad articulo : bp.getContenidoUsadoPicking()) {
					//consultas.append("UPDATE bulto_contenido SET cantidadReservada = cantidadReservada+"+articulo.getCantidad()+" WHERE idBulto = '"+bp.getCodigoBulto()+"' AND idArticulo = '"+articulo.getIdArticulo()+"' AND idEmpresa ="+idEmpresa+"; ");
				//}
				if(bp.isUsaBultoCompleto()) {
					consultas.append("UPDATE ojostienenarticulos SET Reservada = Reservada+1 WHERE idOjo = '"+bp.getOjo()+"' AND idArticulo = '"+bp.getCodigoBulto()+"' AND idEmpresa ="+idEmpresa+"; ");
				}
			}
			if(lpb.getBultosSirven().size() > 0){
				encuentraUpdateEstadoArticulosPickingPrueba(lpb.getBultosSirven(), idPicking, 3, idEmpresa, uLog, 
						session, contenidoPickingActual);
				logica.persistir(consultas.toString());//reservo los bultos 
			}			
			
			/*for (DTO_BultoPuntaje bp : lpb.getBultosSirven()) {
				for (DTO_ArticuloCantidad ac : bp.getContenidoRestanteEnBulto()) {
					System.out.println(ac.getSolicitud());
				}
			}*/
			//Recorrido articulos
			menorMinimo.addAll(lpb.getFaltantes());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return menorMinimo;
	}
	
	/***********************************************************************************/
	/*																				   */
	/* Modulo de busqueda eficiente en picking - por bulto cerrado o articulos sueltos */
	/*																				   */
	/***********************************************************************************/
	public void pickingEficiente_DivisionBusquedas(int valorMinimo, List<DataLineaRepo> contenidoPickingActual, int idPicking, 
			int destino, int idEmpresa, boolean buscaEnAlmacen, Usuario uLog, 
			HttpSession session, boolean prioridadBultos, boolean elevadoristas) {
		
		List<DataLineaRepo> sin_asignar = new ArrayList<>();
		
		try {		
			if(prioridadBultos) {
				sin_asignar = BuscoEnBultos(contenidoPickingActual, valorMinimo, idPicking, idEmpresa, uLog, session);
				if(sin_asignar.size() > 0) {
					//mando a pedir sobrantes y que persista los articulos sin ubicacion
					encuentraUpdateEstadoArticulosPickingBulto(sin_asignar, idPicking+"", 3, null, idEmpresa, buscaEnAlmacen, false, true, elevadoristas); 
				}
			}
			else {
				//me devuelve los articulos sin ubicacion para pedir a bultos
				sin_asignar = encuentraUpdateEstadoArticulosPickingBulto(contenidoPickingActual, idPicking+"", 3, null, idEmpresa, 
						buscaEnAlmacen, true, false, elevadoristas);
			
				//busco sobrantes en bultos
				if(sin_asignar.size() > 0){
					sin_asignar = BuscoEnBultos(sin_asignar, valorMinimo, idPicking, idEmpresa, uLog, session);
					
					if(sin_asignar.size() > 0) {
						//mando a pedir sobrantes y que persista los articulos sin ubicacion
						encuentraUpdateEstadoArticulosPickingBulto(sin_asignar, idPicking+"", 3, null, idEmpresa, buscaEnAlmacen, false, true, elevadoristas); 
					}
				}				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public ListasPickingBultos devolverPickingBultos(int valorMinimo, List<DataLineaRepo> contenidoPickingActual, int idPicking, int idEmpresa) {

		 Logica logica = new Logica();
		 List<DataLineaRepo> contenidoPickingC = new ArrayList<>(contenidoPickingActual);
		 Hashtable<String, DataLineaRepo> contenidoConsolidado = new Hashtable<>();
		 List<DTO_BultoPuntaje> bultosUsados = new ArrayList<DTO_BultoPuntaje>();
		 List<DataLineaRepo> faltantes = new ArrayList<>(); //guardo lista faltantes para los que no lleguen al valor minimo
		 List<String> clavesBultosUsados = new ArrayList<>();
		
		 Hashtable<String, List<DataLineaRepo>> mapeoContenidoPicking = new Hashtable<String, List<DataLineaRepo>>();
		 /*
		  * Estructura del hash
		  * String (codigo articulo), List<DataLineaRepo> (Mismos articulos diferentes destinos) 
		  * */
		 for(DataLineaRepo dlr : contenidoPickingC) {
			 DataLineaRepo dlr2 = dlr.Clonar();
			 dlr2.setDocumento(0);
			 dlr2.setPedido(0L);
			 if(mapeoContenidoPicking.containsKey(dlr.getIdArticulo())) {
				 List<DataLineaRepo> content = mapeoContenidoPicking.get(dlr.getIdArticulo());
				 content.add(dlr);
				 mapeoContenidoPicking.put(dlr.getIdArticulo(), content);
				 
				 
				 dlr2.setSolicitada(contenidoConsolidado.get(dlr2.getIdArticulo()).getSolicitada()+dlr.getSolicitada());
				 contenidoConsolidado.put(dlr2.getIdArticulo(), dlr2);
				
			 } else {
				 List<DataLineaRepo> content = new ArrayList<>();
				 content.add(dlr);
				 mapeoContenidoPicking.put(dlr.getIdArticulo(), content);
				 contenidoConsolidado.put(dlr2.getIdArticulo(), dlr2);
			 }
		 }
		 
		 	 List<DataLineaRepo> listaConsolidada = null;
			 boolean hayBultos = true;
			 while(mapeoContenidoPicking.size() > 0 && hayBultos == true) {
				 try {
					listaConsolidada = new ArrayList<>(contenidoConsolidado.values());
					List<DTO_BultoPuntaje> bultos = MBP_ObtenerBultos(listaConsolidada, clavesBultosUsados, idPicking, idEmpresa); // aca mandar hash
					if(bultos.size() != 0) {
						DTO_BultoPuntaje bultoSeleccionado = bultos.get(0);
						if(clavesBultosUsados.contains(bultoSeleccionado.getCodigoBulto())) {
							for(DTO_BultoPuntaje bp : bultosUsados) {
								if(bp.getCodigoBulto().equalsIgnoreCase(bultoSeleccionado.getCodigoBulto())) {
									bultoSeleccionado = bp;
								}
							}
						}
						
						int cantidadArtsBulto = bultoSeleccionado.getContenidoRestanteEnBulto().size(); // calculo todos los elementos que trae el bulto adentro
						boolean encontroArticuloEnBulto = true; //Puede encontrar que el bulto ya este utilizado, pero este no tiene el articulo actual adentro, creo este filtro para iterar los demas bultos que me trajo el alg
						int contadorIndiceBultoUso = 0;
						boolean primerBulto = true;
						while(hayBultos) { //paso al siguiente bulto que me trajo
							/*if(bultos.size() < contadorIndiceBultoUso) {
								bultoSeleccionado = bultos.get(contadorIndiceBultoUso); //itero el bulto
							}*/
							if(!primerBulto && bultos.size() > contadorIndiceBultoUso){
								bultoSeleccionado = bultos.get(contadorIndiceBultoUso);
							}
							else if (bultos.size() <= contadorIndiceBultoUso){
								break;
							}
							primerBulto = false;
							contadorIndiceBultoUso++;
							List<DTO_ArticuloCantidad> acClon = new ArrayList<DTO_ArticuloCantidad>(bultoSeleccionado.getContenidoRestanteEnBulto());
						for (DTO_ArticuloCantidad ac : acClon) { //actualizo contenido
							if(ac.getIdArticulo().equalsIgnoreCase("G1713") || ac.getIdArticulo().equalsIgnoreCase("QF65DN") || ac.getIdArticulo().equalsIgnoreCase("JU3589")){
								System.out.println("");
							}
							//tendria que iterar en base al consolidado
							List<DataLineaRepo> content = mapeoContenidoPicking.get(ac.getIdArticulo());
							if(content != null && content.size() > 0 ) {
								//encontroArticuloEnBulto = true;
								List<DataLineaRepo> contentClon = new ArrayList<>(content);
								for(DataLineaRepo artPedido: contentClon){								
									//DataLineaRepo artPedido = content.get(0);
									if(ac.getCantidad() > 0){
										if(artPedido.getSolicitada()-ac.getCantidad() == 0 ) { //si ya recolecte la cantidad necesaria
	
											String update = "UPDATE bulto_contenido SET cantidadReservada = cantidadReservada+"+artPedido.getSolicitada()+" WHERE idBulto = '"+bultoSeleccionado.getCodigoBulto()+"' AND idArticulo = '"+artPedido.getIdArticulo()+"' AND idEmpresa ="+idEmpresa+"; ";
											logica.persistir(update); // actualizo en cantidadReservada la nueva cantidad
											
											DTO_ArticuloCantidad acUsado = new DTO_ArticuloCantidad(ac.getIdArticulo(), artPedido.getSolicitada());
											acUsado.setOjo(ac.getOjo());
											acUsado.setDestino(artPedido.getIdDepDestino());
											acUsado.setIdRecorrido(ac.getIdRecorrido());
											acUsado.setPedido(artPedido.getPedido());
											acUsado.setSolicitud(artPedido.getDocumento());
											bultoSeleccionado.getContenidoUsadoPicking().add(acUsado);
											cantidadArtsBulto -= 1; // resto uno al total, ya que es un elemento que pertenece al picking
											
											boolean borro = removeObject(content, artPedido);
											//mapeoContenidoPicking.put(artPedido.getIdArticulo(), content);
											System.out.println("Se borro de la lista: " + borro);
											if(borro && content.size() == 0) {
												removeObjects(mapeoContenidoPicking, artPedido.getIdArticulo());
												removeObject(contenidoConsolidado, artPedido.getIdArticulo());
											}else{
												try {
													DataLineaRepo dlr = contenidoConsolidado.get(artPedido.getIdArticulo());
													dlr.setSolicitada(dlr.getSolicitada()-artPedido.getSolicitada());
													if(dlr.getSolicitada() == 0){
														removeObject(contenidoConsolidado, artPedido.getIdArticulo());
													}
													else{
														contenidoConsolidado.put(artPedido.getIdArticulo(), dlr);
													}													
												} catch (Exception e) {
													System.out.println("ARTICULO YA REMOVIDO DE LA COLECCION CONSOLIDADA -1");
												}
											}
											boolean borro2 = removeObject(contenidoPickingC,artPedido);
											ac.setCantidad(0);
										}
										else if(artPedido.getSolicitada() > ac.getCantidad()){ //la cantidad de elementos de la lista de picking es mayor a la cantidad de elementos del bulto
	
												String update = "UPDATE bulto_contenido SET cantidadReservada = cantidadReservada+"+ac.getCantidad()+" WHERE idBulto = '"+bultoSeleccionado.getCodigoBulto()+"' AND idArticulo = '"+artPedido.getIdArticulo()+"' AND idEmpresa ="+idEmpresa+"; ";
												logica.persistir(update); // actualizo en cantidadReservada la nueva cantidad					
												
												DTO_ArticuloCantidad acUsado = new DTO_ArticuloCantidad(ac.getIdArticulo(), ac.getCantidad());
												acUsado.setOjo(ac.getOjo());
												acUsado.setDestino(artPedido.getIdDepDestino());
												acUsado.setIdRecorrido(ac.getIdRecorrido());
												acUsado.setPedido(artPedido.getPedido());
												acUsado.setSolicitud(artPedido.getDocumento());
												bultoSeleccionado.getContenidoUsadoPicking().add(acUsado);
												cantidadArtsBulto -= 1; // resto uno al total, ya que es un elemento que pertenece al picking
												
												artPedido.setSolicitada(artPedido.getSolicitada()-ac.getCantidad());
												
												if(artPedido.getSolicitada() < valorMinimo) { //si la cantidad faltante es menor al valor minimo
													faltantes.add(artPedido);				// elimino el articulo de la lista pickingBultos y lo agrego en faltantes	
													boolean borro = removeObject(content, artPedido);
													//mapeoContenidoPicking.put(artPedido.getIdArticulo(), content);
													System.out.println("Se borro de la lista: " + borro);
													if(borro && content.size() == 0) {
														removeObjects(mapeoContenidoPicking, artPedido.getIdArticulo());
														removeObject(contenidoConsolidado, artPedido.getIdArticulo());
													}
													else{
														try {
															DataLineaRepo dlr = contenidoConsolidado.get(artPedido.getIdArticulo());
															dlr.setSolicitada(dlr.getSolicitada()-ac.getCantidad());
															if(dlr.getSolicitada() == 0){
																removeObject(contenidoConsolidado, artPedido.getIdArticulo());
															}
															else{
																contenidoConsolidado.put(artPedido.getIdArticulo(), dlr);
															}																		
															
															content.stream().
															filter((x) -> x.equals(artPedido.getIdArticulo())).
															forEach((n) -> n.setSolicitada(artPedido.getSolicitada()));
														} catch (Exception e) {
															System.out.println("ARTICULO YA REMOVIDO DE LA COLECCION CONSOLIDADA 0");
														}
													}
												}
												else{
													try {
														DataLineaRepo dlr = contenidoConsolidado.get(artPedido.getIdArticulo());
														dlr.setSolicitada(dlr.getSolicitada()-ac.getCantidad());
														contenidoConsolidado.put(artPedido.getIdArticulo(), dlr);	
														
														content.stream().
														filter((x) -> x.equals(artPedido.getIdArticulo())).
														forEach((n) -> n.setSolicitada(artPedido.getSolicitada()));
													} catch (Exception e) {
														System.out.println("ARTICULO YA REMOVIDO DE LA COLECCION CONSOLIDADA 1");
													}
												}									
												
												ac.setCantidad(0);
												}
										else {//la cantidad de elementos del bulto es mayor a la cantidad de elementos de la lista de picking 
																								
												DTO_ArticuloCantidad acUsado = new DTO_ArticuloCantidad(ac.getIdArticulo(), artPedido.getSolicitada());
												acUsado.setOjo(ac.getOjo());
												acUsado.setDestino(artPedido.getIdDepDestino());
												acUsado.setIdRecorrido(ac.getIdRecorrido());
												acUsado.setPedido(artPedido.getPedido());
												acUsado.setSolicitud(artPedido.getDocumento());
												bultoSeleccionado.getContenidoUsadoPicking().add(acUsado);
												
												String update = "UPDATE bulto_contenido SET cantidadReservada = cantidadReservada+"+artPedido.getSolicitada()+" WHERE idBulto = '"+bultoSeleccionado.getCodigoBulto()+"' AND idArticulo = '"+artPedido.getIdArticulo()+"' AND idEmpresa ="+idEmpresa+"; ";
												logica.persistir(update); // actualizo en cantidadReservada la nueva cantidad
	
												boolean borro = removeObject(content, artPedido);
												//mapeoContenidoPicking.put(artPedido.getIdArticulo(), content);
												System.out.println("Se borro de la lista: " + borro);
												if(borro && content.size() == 0) {
													removeObjects(mapeoContenidoPicking, artPedido.getIdArticulo());
													removeObject(contenidoConsolidado, artPedido.getIdArticulo());
												}
												else{
													try {
														DataLineaRepo dlr = contenidoConsolidado.get(artPedido.getIdArticulo());
														dlr.setSolicitada(dlr.getSolicitada()-artPedido.getSolicitada());
														if(dlr.getSolicitada() == 0){
															removeObject(contenidoConsolidado, artPedido.getIdArticulo());
														}
														else{
															contenidoConsolidado.put(artPedido.getIdArticulo(), dlr);
														}													
													} catch (Exception e) {
														System.out.println("ARTICULO YA REMOVIDO DE LA COLECCION CONSOLIDADA 2");
													}	
												}
												boolean borro2 = removeObject(contenidoPickingC,artPedido);		
												
												ac.setCantidad(ac.getCantidad()-artPedido.getSolicitada()); //actualizo la cantidad restante en el bulto
											}
								if(cantidadArtsBulto > 0) {
									bultoSeleccionado.setUsaBultoCompleto(false);
								}
								if(bultosUsados.size() == 0 || !clavesBultosUsados.contains(bultoSeleccionado.getCodigoBulto())) {
									bultosUsados.add(bultoSeleccionado);
									clavesBultosUsados.add(bultoSeleccionado.getCodigoBulto()); // bulto seleccionado
								}
								}
									else{
										encontroArticuloEnBulto = false;
										//contadorIndiceBultoUso++;
									}
							}
								
							} else {
								encontroArticuloEnBulto = false;
								//contadorIndiceBultoUso++;
							}
						}
					}
					} else { 
							if(mapeoContenidoPicking.size() > 0) {
								List<String> keys = Collections.list(mapeoContenidoPicking.keys());
								for (String key : keys) {
									List<DataLineaRepo> content = mapeoContenidoPicking.get(key);
									for(DataLineaRepo dlr : content) {
										faltantes.add(dlr);
									}
								}
							}
							hayBultos = false;
						}
							
				} catch (Exception e) {
					e.printStackTrace();
					hayBultos = false;
				}
			 }
		 
		 ListasPickingBultos lpb = new ListasPickingBultos(	bultosUsados, faltantes);
		 return lpb;
	}
	
	private boolean removeObject(List<DataLineaRepo> coleccion, DataLineaRepo d){
		boolean out = false;
		try {
			out = coleccion.remove(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
	
	private void removeObjects(Hashtable<String, List<DataLineaRepo>> coleccionHas, String key){
		try {
			coleccionHas.remove(key);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removeObject(Hashtable<String, DataLineaRepo> coleccionHas, String key){
		try {
			coleccionHas.remove(key);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* Compruebo si el total de la mercaderia restante de picking, no es menor que la variable establecida
	 * Recibo una lista de bultos cerrados con alguno de los articulos del pedido dentro.
	 * Filtro la lista con el algoritmo
	 * Devuelvo el mejor bulto
	 * */
	public List<DTO_BultoPuntaje> MBP_ObtenerBultos(List<DataLineaRepo> contenidoPicking, List<String> clavesBultosUsados, int idPicking, int idEmpresa) throws Exception{
		//Recibo el contenido del picking
		//Me traigo todos los bultos que tengan picking 0, sean de la empresa logueada y tengan al menos 1 artiuclo
				// de los que se estan buscando
		/*String noIncluirEstosBultos = "";
		for (String idBulto : clavesBultosUsados) {  //por si hay mas de 1 cantidad de un mismo bulto
			noIncluirEstosBultos += " AND bc.idBulto <> '"+idBulto+"'";
		}*/
		
		
		_EncuentraConexion ec = new _EncuentraConexion();
		Connection connection = null;
		connection = ec.getConnection();
		
		
		String articulosBuscados = "";
		if(contenidoPicking.size() > 0) {
			String artAnterior = "";
			for (DataLineaRepo art : contenidoPicking) {
				if(!art.getIdArticulo().equalsIgnoreCase(artAnterior)) {
					articulosBuscados += "'" + art.getIdArticulo() + "',";
					artAnterior = art.getIdArticulo();
				}
			}
			articulosBuscados = articulosBuscados.substring(0, articulosBuscados.length() - 1);
		}
		
		
		/*
		String query = "SELECT bc.idBulto, bc.idArticulo, bc.cantidad, ota.idOjo, o.idRecorrido FROM bulto_contenido bc \r\n" + 
				" INNER JOIN bulto b ON b.idBulto = bc.idBulto AND b.IdEmpresa = bc.IdEmpresa\r\n" + 
				" INNER JOIN ojostienenarticulos ota ON ota.idArticulo = bc.idBulto AND ota.IdEmpresa = bc.IdEmpresa\r\n" + 
				" INNER JOIN ojos o ON o.idOjo = ota.idOjo AND o.IdEmpresa = ota.IdEmpresa\r\n"+  
				" WHERE bc.picking = 0 AND ota.Reservada = 0 AND bc.IdEmpresa ="+idEmpresa+"\r\n" + 
				" AND bc.idArticulo IN (SELECT ra.idArticulo FROM reposicion_articulos ra WHERE ra.IdEmpresa ="+idEmpresa+" AND ra.idPicking = "+idPicking+")"+noIncluirEstosBultos;
		
		
		*/
		
		/*String query = " SELECT bc.idBulto, bc.idArticulo, bc.cantidad, ota.idOjo, o.idRecorrido FROM bulto_contenido bc\r\n" + 
				" INNER JOIN (SELECT DISTINCT(bcc.idBulto) FROM bulto_contenido bcc\r\n" + 
				" WHERE bcc.idArticulo IN\r\n" + 
				" 		(SELECT ra.idArticulo FROM reposicion_articulos ra WHERE ra.IdEmpresa = "+idEmpresa+" AND ra.idPicking = "+idPicking+")\r\n" + 
				" ) t1 ON t1.idBulto = bc.idBulto\r\n" + 
				" INNER JOIN bulto b ON b.idBulto = bc.idBulto AND b.IdEmpresa = bc.IdEmpresa\r\n" + 
				" INNER JOIN ojostienenarticulos ota ON ota.idArticulo = bc.idBulto AND ota.IdEmpresa = bc.IdEmpresa\r\n" + 
				" INNER JOIN ojos o ON o.idOjo = ota.idOjo AND o.IdEmpresa = ota.IdEmpresa\r\n" + 
				" WHERE bc.picking = 0 AND bc.IdEmpresa ="+idEmpresa+" AND o.;";*/
		
		String query = "SELECT bc.idBulto, bc.idArticulo, bc.cantidad-bc.cantidadReservada, ota.idOjo, o.idRecorrido FROM bulto_contenido bc\r\n" + 
				"				 INNER JOIN (SELECT DISTINCT(bcc.idBulto) FROM bulto_contenido bcc\r\n" + 
				"				 WHERE bcc.idArticulo IN\r\n" + 
				"				 		(SELECT ra.idArticulo FROM reposicion_articulos ra WHERE ra.IdEmpresa = "+idEmpresa+" AND ra.idPicking = "+idPicking+" ";
		
		if(contenidoPicking.size() > 0) {query += " AND ra.idArticulo IN ("+articulosBuscados+") \r\n";}
		
		/*query += ")) t1 ON t1.idBulto = bc.idBulto\r\n" + 
				"	INNER JOIN bulto b ON b.idBulto = bc.idBulto AND b.IdEmpresa = bc.IdEmpresa \r\n" + 
				"	INNER JOIN ojostienenarticulos ota ON ota.idArticulo = bc.idBulto AND ota.IdEmpresa = bc.IdEmpresa \r\n" + 
				"	INNER JOIN ojos o ON o.idOjo = ota.idOjo AND o.IdEmpresa = ota.IdEmpresa \r\n" + 
				"	WHERE bc.picking = 0 AND ota.Reservada < ota.Cantidad AND bc.IdEmpresa = "+idEmpresa+" AND o.idOjo not like '%P' AND o.idOjo not like '%E' AND o.idOjo <> 1 " + noIncluirEstosBultos +" " +
				"	AND bc.cantidadReservada < bc.cantidad;";*/
		
		query += ")) t1 ON t1.idBulto = bc.idBulto\r\n" + 
				"	INNER JOIN bulto b ON b.idBulto = bc.idBulto AND b.IdEmpresa = bc.IdEmpresa \r\n" + 
				"	INNER JOIN ojostienenarticulos ota ON ota.idArticulo = bc.idBulto AND ota.IdEmpresa = bc.IdEmpresa \r\n" + 
				"	INNER JOIN ojos o ON o.idOjo = ota.idOjo AND o.IdEmpresa = ota.IdEmpresa \r\n" + 
				"	WHERE bc.picking = 0 AND ota.Reservada < ota.Cantidad AND bc.IdEmpresa = "+idEmpresa+" AND o.idOjo not like '%P' AND o.idOjo not like '%E' AND o.idOjo <> 1 " +
				"	AND bc.cantidadReservada < bc.cantidad;";
		
		
		System.out.println(query);
		PreparedStatement pstmt = null;
		Hashtable<String, List<DTO_ArticuloCantidad>> bultos = new Hashtable<String, List<DTO_ArticuloCantidad>>();
		try {
			pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			while (rs.next()) 
			{
				if(bultos.containsKey(rs.getString(1))){
					List<DTO_ArticuloCantidad> contenido = bultos.get(rs.getString(1));
					if(contenido == null) {
						contenido = new ArrayList<DTO_ArticuloCantidad>();
					} 
					DTO_ArticuloCantidad ac = new DTO_ArticuloCantidad(rs.getString(2), rs.getInt(3), rs.getString(4), 
							rs.getInt(5));
					contenido.add(ac);
				} else {
					DTO_ArticuloCantidad dc = new DTO_ArticuloCantidad(rs.getString(2), rs.getInt(3), rs.getString(4), 
							rs.getInt(5));
					List<DTO_ArticuloCantidad> contenido = new ArrayList<DTO_ArticuloCantidad>();
					contenido.add(dc);
					bultos.put(rs.getString(1), contenido);
				}
			}
			rs.close();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			ec.desconectar(null,pstmt,null);
		}
			return MBP_ObtenerMejor(contenidoPicking, bultos);
		}

	//Me fijo las cantidades totales de cada articulo que coincida en el bulto y en el picking
		//Sumo 1 por cada cantidad necesaria y disponible
		//Resto 1 por cada cantidad excedente
		//Hago una sumatoria entre todos los numeros +/-
		//Los divido por la cantidad de articulos diferentes en el bulto   - RESULTADO 1
		
		//Calculo el porcentaje entre la cantidad de productos que tengo en el bulto y pertenecen al picking 
				//y la cantidad total de articulos que me pide el picking
		//Le sumo a RESULTADO1, el % anterior
	
		public List<DTO_BultoPuntaje> MBP_ObtenerMejor(List<DataLineaRepo> contenidoPicking, 
				Hashtable<String, List<DTO_ArticuloCantidad>> bultos) {
			int cantidadElementosPicking = 0;
			for (DataLineaRepo articuloLineaReposicion : contenidoPicking) {
				System.out.println("Faltan por conseguir los articulos:");
				System.out.println("Codigo: " + articuloLineaReposicion.getIdArticulo());
				System.out.println("Cantidad: " + articuloLineaReposicion.getSolicitada());
				System.out.println("");
				cantidadElementosPicking += articuloLineaReposicion.getSolicitada();
			}
			System.out.println("Cantidad total de articulos solicitados en picking: "+cantidadElementosPicking);
			List<DTO_BultoPuntaje> bpList = new ArrayList<DTO_BultoPuntaje>();
			List<String> keys = Collections.list(bultos.keys());
			for (String key : keys){ //recorro todos los bultos que contienen articulos que estan en la lista picking
				List<String> codigosRecorridos = new ArrayList<>();

				System.out.println("************");
				System.out.println("Bulto actual: ");
				System.out.println(" Nro bulto: "+key);
				System.out.println("************");
				System.out.println("");
				
				List<DTO_ArticuloCantidad> contenidoBulto = bultos.get(key);
				double pjePositivo = 0;
				double pjeNegativo = 0;
				for (DTO_ArticuloCantidad ac : contenidoBulto) { // recorro el contenido de cada bulto, generalmente es de 1 articulo solo
					boolean tieneArticulo = false;
					for (DataLineaRepo dataLineaRepo : contenidoPicking) { // recorro la lista de picking para ver si estan los elementos del bulto
						if(!codigosRecorridos.contains(dataLineaRepo.getIdArticulo()) && dataLineaRepo.getIdArticulo().equalsIgnoreCase(ac.getIdArticulo())) { // si los ids coinciden
							codigosRecorridos.add(dataLineaRepo.getIdArticulo());
							System.out.println("Cantidad del articulo "+ac.getIdArticulo()+" dentro del bulto cerrado: " + ac.getCantidad());
							System.out.println("Cantidad del articulo " + dataLineaRepo.getIdArticulo() + " necesario: "+ dataLineaRepo.getSolicitada());
							//System.out.println("###################### PRUEBA DE DESTINO ###################### \n" + dataLineaRepo.getIdDepDestino());
							System.out.println("");
							ac.setSolicitud(dataLineaRepo.getDocumento());
							ac.setPedido(dataLineaRepo.getPedido());
							if(ac.getCantidad() > dataLineaRepo.getSolicitada()) { 
								pjePositivo += dataLineaRepo.getSolicitada();
								pjeNegativo += ac.getCantidad() - dataLineaRepo.getSolicitada();
							} else if (ac.getCantidad() < dataLineaRepo.getSolicitada()) {
								pjePositivo += ac.getCantidad();
							} else {
								pjePositivo += ac.getCantidad();
							}
							tieneArticulo = true;
							break;
						}
					}
					if(!codigosRecorridos.contains(ac.getIdArticulo()) && !tieneArticulo) { //Bultos que tengan articulos que no estan en la lista de picking, generan pje negativo
						System.out.println("El articulo con codigo: "+ac.getIdArticulo()+" cantidad:"+ac.getCantidad()+" no forma parte de los articulos solicitados en el picking");
						pjeNegativo += ac.getCantidad();
					}
				}
				System.out.println("Puntaje positivo: " + pjePositivo);
				System.out.println("Puntaje negativo: " + pjeNegativo);
				System.out.println("");
				System.out.println("Formula del calculo porcentaje: (puntaje positivo * 100) / cantidad total de elementos en la lista picking");
				double porcentajeAproximacion = (pjePositivo*100)/cantidadElementosPicking;
				System.out.println("Resultado: " + porcentajeAproximacion);
				System.out.println("");
				System.out.println("Formula del calculo sub-total: (puntaje positivo - puntaje negativo) / cantidad total de elementos en bulto cerrado");
				System.out.println("Cantidad total de elementos en bulto cerrado: " + contenidoBulto.size());
				double subTotal = (pjePositivo-pjeNegativo)/contenidoBulto.size();
				System.out.println("Resultado: " + subTotal);
				System.out.println("");
				System.out.println("Formula del calculo total: sub-total + ((sub - total * porcentaje) / 100)");
				double recompensa = 100 - porcentajeAproximacion;
				double total = subTotal + ((subTotal*recompensa)/100);
				System.out.println("Resultado: " + total);
				DTO_BultoPuntaje oBp = new DTO_BultoPuntaje(key,contenidoBulto.get(0).getOjo(), total, contenidoBulto, contenidoBulto.get(0).getIdRecorrido());
				//Si tiene puntaje negativo no lo agrego (de momento, hasta mas adelante que se implemente el dejar articulos sobrantes)
				//if(pjeNegativo == 0) {
					bpList.add(oBp);
				//}
				System.out.println("-----------------------------------------------");
				System.out.println("-----------------------------------------------");
				System.out.println("");
				System.out.println("");
			}
			if(bpList.size() > 0) {Collections.sort(bpList);};
			return bpList;
		}
		
		

public List<DataLineaRepo> encuentraUpdateEstadoArticulosPickingBulto(List<DataLineaRepo> articulosIn, String idPick, int estado, 
		Hashtable<String, List<DataIDIDDescripcion>> reservas, int idEmpresa, boolean buscarEnAlmacen, boolean devuelvo_sin_asignar, boolean update_sin_asignar, boolean elevadoristas) 
{
	List<DataLineaRepo> sin_asingar = new ArrayList<>();	
	
	Logica logica = new Logica();
	_EncuentraPersistir eper = new _EncuentraPersistir();
	try 
	{		
		StringBuilder sb = new StringBuilder();		 
		StringBuilder sb2 = new StringBuilder();
		Hashtable<String, List<DataIDIDDescripcion>>  ubicacionesArtuculoHT;

			/**
			 * for (DataLineaRepo art : articulosIn) 
			{
				try
				{
					 sb.append("UPDATE `reposicion_articulos` SET Estado = "+estado+" WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+idPick+" AND `idArticulo`='"+art.getIdArticulo()+"' AND `Origen`="+art.getIdDepOrigen()+" AND `Destino`="+art.getIdDepDestino()+";");
				}
				catch(Exception e)
				{
					
				}					
			}
			eper.persistir(sb.toString());
			*/
								
			StringBuilder innsSB = new StringBuilder();
			
			for (DataLineaRepo da : articulosIn) 
			{
				innsSB.append("'"+da.getIdArticulo()+"',");
			}
			
			String inns = innsSB.toString().substring(0, innsSB.toString().length()-1);
			ubicacionesArtuculoHT = logica.encuentraDarSectoresHT(inns,idEmpresa, elevadoristas);		
		
		boolean updateEstadoRepo = true;
		for (DataLineaRepo da : articulosIn) 
		{
			System.out.println("Faltantes: ");
			System.out.println(da.getIdArticulo() + " - " + da.getSolicitada());
			updateEstadoRepo = true;
			
			try			
			{
				int documento =da.getDocumento();	
				
				List<DataIDIDDescripcion> ubicacionesArtuculo = buscarEnAlmacen ? ubicacionesArtuculoHT.get(da.getIdArticulo()) : null; // Agregue esto para el SIN ASIGNAR
				
				if (ubicacionesArtuculo!=null && !ubicacionesArtuculo.isEmpty()) 
				{
					// entro si hay ubicaciones para ese articulo
					int cantidadSol = da.getSolicitada();
					
					for (DataIDIDDescripcion ubi : ubicacionesArtuculo) 
					{
						
						if(ubi.getIid()>0)
						{
						
							DataLineaRepo temp = new DataLineaRepo();
							//temp.setIdArticulo(da.getIdArticulo());
							temp.setIdArticulo(da.getIdArticulo());
							temp.setPedido(da.getPedido());
							temp.setIdDepDestino(da.getIdDepDestino());
							temp.setDocumento(documento);
							
							try
							{
								if(cantidadSol>0)
								{
									
									//temp = da; // hago un linearepo temp
									int cantOjo = ubi.getIid(); // obtengo la cantidad en ese ojo
									
									
									if(cantidadSol<=cantOjo)
									{
										// si entra ac? quiere decir que de ese ojo se puede sacar todo lo que le piden de ese articulo
										temp.setSolicitada(cantidadSol);
										ubi.setIid(ubi.getIid()-cantidadSol);
										cantidadSol = 0;
										
										
									}
									else
									{
										temp.setSolicitada(cantOjo); // le pido que saque todo lo de ese ojo
										cantidadSol = cantidadSol-cantOjo; // bajo lo que saco del total solicitado
										ubi.setIid(0);
										
									}
										
									temp.setCubi(ubi.getDescripcion());
									temp.setRecorrido(ubi.getId());
									
									DataIDIDDescripcion estEstMod = logica.DarEstanteria(temp.getCubi(),idEmpresa);
									System.out.println("");
									
									temp.setEstnteria(estEstMod.getId());
									temp.setEstnte(Integer.parseInt(estEstMod.getDescripcion()));
									temp.setModulo(estEstMod.getIid());
									
									/*
									 * hay que reservar la cantidad en ese ojo
									 * */
									
									//encuentraReservarUbicacion(temp.getIdArticulo(),temp.getCubi(),temp.getSolicitada());
									sb2.append(logica.encuentraReservarUbicacionII(temp.getIdArticulo(),temp.getCubi(),temp.getSolicitada(),
											Integer.parseInt(idPick),temp.getIdDepDestino(),temp.getPedido(),true,idEmpresa,temp.getDocumento()));
									
									
																		
								}
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
						}
					}
					if(cantidadSol>0){
						DataLineaRepo temp = new DataLineaRepo();
						//temp.setIdArticulo(da.getIdArticulo());
						temp = new DataLineaRepo();
						temp.setIdArticulo(da.getIdArticulo());
						temp.setPedido(da.getPedido());
						temp.setIdDepOrigen(da.getIdDepOrigen());
						temp.setIdDepDestino(da.getIdDepDestino());
						temp.setDocumento(documento);
						temp.setCubi("SIN ASIGNAR");
						temp.setSolicitada(cantidadSol);
						if(devuelvo_sin_asignar) {
							sin_asingar.add(temp);
							updateEstadoRepo = update_sin_asignar;
						}
						else {
							sb2.append(logica.encuentraReservarUbicacionII(temp.getIdArticulo(),temp.getCubi(),temp.getSolicitada(),
									Integer.parseInt(idPick),temp.getIdDepDestino(),temp.getPedido(),true,idEmpresa,temp.getDocumento()));
						}
						
						
					}
				}
				else 
				{
					//no hay ubicaciones para ese articulo
					DataLineaRepo temp = new DataLineaRepo();
					temp = new DataLineaRepo();
					temp.setIdArticulo(da.getIdArticulo());
					temp.setPedido(da.getPedido());
					temp.setIdDepOrigen(da.getIdDepOrigen());
					temp.setIdDepDestino(da.getIdDepDestino());
					temp.setDocumento(documento);
					temp.setCubi("SIN ASIGNAR");
					temp.setSolicitada(da.getSolicitada());
					if(devuelvo_sin_asignar) {
						sin_asingar.add(temp);
						updateEstadoRepo = update_sin_asignar;
					}
					else {
						sb2.append(logica.encuentraReservarUbicacionII(temp.getIdArticulo(),temp.getCubi(),temp.getSolicitada(),
								Integer.parseInt(idPick),temp.getIdDepDestino(),temp.getPedido(),true,idEmpresa,temp.getDocumento()));
					}					
				}
				
				if(updateEstadoRepo) {
					sb.append("UPDATE `reposicion_articulos` SET Estado = "+estado+" WHERE idEmpresa="+idEmpresa+" AND `idPicking`="+idPick+" AND "
							+ "`idArticulo`='"+da.getIdArticulo()+"' AND `Origen`="+da.getIdDepOrigen()+" AND `Destino`="+da.getIdDepDestino()+";");
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		
		logica.persistir(sb.toString());
		logica.persistir(sb2.toString());
		//////////////////////////
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	return sin_asingar;
}

//encuentraReservarUbicacionIII
public  String  encuentraReservarUbicacionIII(String articulo, String cubi, int cantidad,int idPick,int dest,
		Long idpedido, int idE, String idBulto, int solicitud, int idUsuario) 
{
	try 
	{
		String retorno ="";
		retorno+="INSERT IGNORE INTO `reposicion_articulos_ojos` (`idArticulo`, `idPicking`, `cUbicacion`, `Cantidad`, `destino`, `pedido`,idEmpresa, idBulto, solicitud, idUsuario) "+
				"VALUES ('"+articulo+"', "+idPick+", '"+cubi+"',"+cantidad+","+dest+","+idpedido+","+idE+",'"+idBulto+"', "+solicitud+","+idUsuario+");";
		//eper.persistir(insertII);
	
		return retorno;
		//eper.desconectar();
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		return "";
	}
	
}

public List<String> bultosValidosEnOjo(String bp_reservado, String codigoOjo, int idEmpresa, List<DataLineaRepo> contenido) {
	boolean valido = false;
	_EncuentraPersistir ep = new _EncuentraPersistir();
	try {
		List<String> bultos = ep.buscarContenidoBultos(bp_reservado, codigoOjo, idEmpresa, contenido);
		return bultos;
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	return null;
}


/***********************************************************/
public  void encuentraUpdateEstadoArticulosPickingPrueba(List<DTO_BultoPuntaje> bultosUsados, int oldidPicking, 
		int estado, int idEmpresa, Usuario uLog, HttpSession session, List<DataLineaRepo> contenidoPickingActual) 
{
	_EncuentraPersistir eper = new _EncuentraPersistir();
	Logica logica = new Logica();
	Utilidades util = new Utilidades();
	try 
	{		
		StringBuilder sb = new StringBuilder();
		List<DataIDDescripcion> list = null;
		// articulosIn destinosLST cantidades
		 
		StringBuilder sb2 = new StringBuilder();
		//Hashtable<String, List<DataIDIDDescripcion>>  ubicacionesArtuculoHT;
		
		
		int idPickingNuevo = oldidPicking;
		int idUsuarioBultos = 0;
		boolean parametroTareasIndividuales = util.darParametroEmpresaBool(idEmpresa, 41);
		List<ArticuloLineaReposicion> articulosIn = new ArrayList<>();
		if(parametroTareasIndividuales) {
			List<Integer> destinos = new ArrayList<>();
			int cantidadTotal = 0;
			for(DTO_BultoPuntaje bp : bultosUsados) {
				for(DTO_ArticuloCantidad ac : bp.getContenidoUsadoPicking()) {
					cantidadTotal += ac.getCantidad();
					if(!destinos.contains(ac.getDestino())) {
						destinos.add(ac.getDestino()); 
					}
				}
			}
			idUsuarioBultos = util.darParametroEmpresaINT(idEmpresa, 44);
			idPickingNuevo = crearTareaIndividual(idEmpresa, uLog, logica, 
						session, destinos, cantidadTotal, oldidPicking, bultosUsados, contenidoPickingActual, idUsuarioBultos);
		}

			for (DTO_BultoPuntaje b : bultosUsados) 
			{
				for(DTO_ArticuloCantidad articulo : b.getContenidoUsadoPicking()) {
				try
				{
					 sb.append("UPDATE `reposicion_articulos` SET Estado = "+estado+" WHERE idEmpresa="+idEmpresa+" "
					 		+ "AND `idPicking`="+idPickingNuevo+" AND `idArticulo`='"+articulo.getIdArticulo()+"' "
					 				+ "AND `Destino`="+articulo.getDestino()+" and seccion="+articulo.getPedido()+" and idsolicitudtraslado="+articulo.getSolicitud()+
					 				";");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}	
				
			}
			}
			eper.persistir(sb.toString());
			
		for (DTO_BultoPuntaje bp : bultosUsados) 
		{
			for(DTO_ArticuloCantidad articulo : bp.getContenidoUsadoPicking()) {
			try
			{
					int cantidadSol = articulo.getCantidad();
											
						
							DataLineaRepo temp = new DataLineaRepo();
							//temp.setIdArticulo(da.getIdArticulo());
							temp.setIdArticulo(articulo.getIdArticulo());
							temp.setIdDepDestino(articulo.getDestino());
							temp.setDocumento(articulo.getSolicitud());  
							temp.setCubi(articulo.getOjo());
							temp.setRecorrido(articulo.getIdRecorrido());
							temp.setSolicitada(articulo.getCantidad());
									
							DataIDIDDescripcion estanteriaEstanteModulo = logica.DarEstanteria(temp.getCubi(),idEmpresa);
							System.out.println("");
							temp.setPedido(articulo.getPedido());
							temp.setEstnteria(estanteriaEstanteModulo.getId());
							temp.setEstnte(Integer.parseInt(estanteriaEstanteModulo.getDescripcion()));
							temp.setModulo(estanteriaEstanteModulo.getIid());
							
							sb2.append(encuentraReservarUbicacionIII(temp.getIdArticulo(),temp.getCubi(), temp.getSolicitada(), 
									idPickingNuevo,temp.getIdDepDestino(), temp.getPedido(),idEmpresa, bp.getCodigoBulto(), temp.getDocumento(),idUsuarioBultos)); //Lo agregue aca
						
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
		}
		logica.persistir(sb2.toString());
		//////////////////////////
		
	} 
	catch (Exception e) 
	{
		
		e.printStackTrace();
		
		
	}
	
}


public bulto obtenerBultoCerrado(String idBulto, int idEmpresa, int idPicking) {
	_EncuentraConexion ec = new _EncuentraConexion();
	return ec.obtenerBultoCerrado(idBulto, idEmpresa, idPicking);
}

public boolean esBultoCerrado(String idBulto, int idEmpresa) {
	_EncuentraConexion ec = new _EncuentraConexion();
	return ec.esBultoCerrado(idBulto, idEmpresa);
}

public List<DataLineaRepo> filtrarPorDestino(List<DataLineaRepo> contenidoPickingActual){
	List<DataLineaRepo> contenidoConMismoDestino = new ArrayList<>();
	int destino = contenidoPickingActual.get(0).getIdDepDestino();
	for (DataLineaRepo dataLineaRepo : contenidoPickingActual) {
		if(dataLineaRepo.getIdDepDestino() == destino) {
			contenidoConMismoDestino.add(dataLineaRepo);
		}
	}
	
	return contenidoConMismoDestino;
}

public Hashtable<Integer,List<DataLineaRepo>> filtrarPorDestinoHT(List<DataLineaRepo> contenidoPickingActual){
	Hashtable<Integer,List<DataLineaRepo>> contenidoConMismoDestino = new Hashtable<Integer, List<DataLineaRepo>>();
	for (DataLineaRepo dataLineaRepo : contenidoPickingActual) {
		if(contenidoConMismoDestino.get(dataLineaRepo.getIdDepDestino()) == null) {
			contenidoConMismoDestino.put(dataLineaRepo.getIdDepDestino(), new ArrayList<>());
			contenidoConMismoDestino.get(dataLineaRepo.getIdDepDestino()).add(dataLineaRepo);
		}
		else {
			contenidoConMismoDestino.get(dataLineaRepo.getIdDepDestino()).add(dataLineaRepo);
		}
	}
	
	return contenidoConMismoDestino;
}

public int crearTareaIndividual(int idEmpresa, Usuario uLog, Logica logica, HttpSession session, List<Integer> destinosLST, 
		int cantidades, int idPickingViejo, List<DTO_BultoPuntaje> bultosUsados, List<DataLineaRepo> contenidoPickingActual, int idUsuarioBultos ) {
	int idPickingNuevo = 0;
	
	try {						
	//String observacion = "Picking "+destinos+" "+uLog.getNick();
	String observacion = "BULTOS(comlpementa Picking "+idPickingViejo+")";		
	DataIDDescripcion estado = new DataIDDescripcion();
	estado.setId(0);
	
	Tarea tarea = new Tarea();
	tarea.setEstado(estado);		
	tarea.setPorcentaje(0);
	tarea.setResponsable(new DataIDDescripcion(uLog.getNumero(),uLog.getNick()));
	tarea.setTipo(new DataIDDescripcion(1,"Picking"));
	tarea.setParcial(true);
	
	
						
	_EncuentraPersistir eper = new _EncuentraPersistir();
	idPickingNuevo = eper.persistirDarUltimo("INSERT INTO `picking` (`unidades`,Filtro,idEmpresa) VALUES ("+cantidades+", 'Sin filtros aplicados',"+idEmpresa+");", "picking", "id",idEmpresa);
	tarea.setIdDocumento(idPickingNuevo);
	tarea.setObservacion(observacion);
	tarea.setCantidadPares(cantidades);		
	tarea.setIdDeposito(Integer.parseInt(uLog.getDeposito()));
	//USUARIO
	Utilidades util = new Utilidades();
	Usuario usu = new Usuario();
	usu.setNumero(idUsuarioBultos);
	
	List<Usuario> operariosSelect = new ArrayList<>();
	operariosSelect.add(usu);
	tarea.setUsuarios(operariosSelect);
	
	StringBuilder sb3 = new StringBuilder();
	for(DTO_BultoPuntaje bp : bultosUsados) {
		for(DTO_ArticuloCantidad ac : bp.getContenidoUsadoPicking()) {		
			
						sb3.append("UPDATE reposicion_articulos SET cantidad = cantidad - "+ac.getCantidad()+", estado=3 "+
								" WHERE idEmpresa="+idEmpresa+ " AND idPicking="+idPickingViejo+" AND `idArticulo`='"+ac.getIdArticulo()+"' "
									+ "AND `Destino`="+ac.getDestino()+" and seccion="+ac.getPedido()+" and idsolicitudtraslado="+ac.getSolicitud()+"; ");			
			
						sb3.append("INSERT INTO reposicion_articulos (IdSincronizacion,idArticulo,Origen,Destino,Cantidad,Estado,Justificacion,Mayorista,idpicking,idUsuario,IdSolicitudTraslado,IdEmpresa,remision_bulto,idTipo,seccion,idPosSort) (\r\n" + 
								" SELECT IdSincronizacion,idArticulo,Origen,Destino,"+ac.getCantidad()+",estado,Justificacion,Mayorista,"+idPickingNuevo+","+idUsuarioBultos+",IdSolicitudTraslado,IdEmpresa,remision_bulto,idTipo,seccion,idPosSort \r\n" + 
								" FROM reposicion_articulos ra\r\n" + 
								" WHERE idEmpresa= "+idEmpresa+" AND idPicking= "+idPickingViejo+" AND idArticulo= '"+ac.getIdArticulo()+"' AND Destino= "+ac.getDestino()+
									" and seccion="+ac.getPedido()+" and idsolicitudtraslado="+ac.getSolicitud()+") "
											+ "on duplicate key update cantidad="
											+ "(select cantidad from reposicion_articulos WHERE idEmpresa= "+idEmpresa+" AND idPicking= "+idPickingNuevo+" AND idArticulo= '"+ac.getIdArticulo()+"' AND Destino= "+ac.getDestino()+
									" and seccion="+ac.getPedido()+" and idsolicitudtraslado="+ac.getSolicitud()+")+ "+ac.getCantidad()+"; " +  
								"");
			
								
						
					//}
				
			
		}
	}
	
	//_EncuentraAltaTareaII eat = new _EncuentraAltaTareaII();
	/*return eat.guardarTarea(tarea, uLog, idEmpresa,session, logica, idPickingNuevo, paramThisPickPB, 
			paramThisPickCantMin, paramUsoBultoParcial, paramTomaArticulosAlmacen,false); //Cambiar booleano 29/1*/
	sb3.append("delete from reposicion_articulos where cantidad=0 and idpicking="+idPickingViejo+";");
	sb3.append("update tareas set unidades = unidades-"+cantidades+" where iddoc="+idPickingViejo+" and idEmpresa="+idEmpresa+"; ");
	logica.persistir(sb3.toString());
	logica.encuentraAltaTarea(tarea, false, uLog.getNumero(),idEmpresa,false);
	
	} catch(Exception e) {
		e.printStackTrace();
	}
	return idPickingNuevo;
}

}
