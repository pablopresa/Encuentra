package web.expedicion;

import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
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

import com.google.gson.Gson;

import dataTypes.DataIDDescripcion;




import beans.Articulo;


import beans.Estado;
import beans.OjosEnCero;
import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DataIDCollection;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Transporte;

public class MoverAExpedicion extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
		
				HttpSession session = request.getSession();
				Usuario uLog = (Usuario) session.getAttribute("uLogeado");
				Logica Logica = new Logica();
				
				String depo = request.getParameter("depo");
				String movimientos = request.getParameter("colection"); //movimientos =" ";
				List<DataIDDescripcion> listaDestinosExpedicion;
				List<DataIDDescripcion> ojosExpedicion ;
				List<DataIDDescripcion> Clasificados = null;
				
				
				Utilidades util = new Utilidades();
				int idEmpresa = util.darEmpresa(uLog);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				if(uLog!=null){
					if(depo==null){					//CARGA INICIAL DE DESTINOS Y CANTIDAD DE BULTOS
						listaDestinosExpedicion = Logica.ArticulosEnEstanteriaClasificacion("", idEmpresa);
						if(listaDestinosExpedicion!= null && listaDestinosExpedicion.size()>0){
							listaDestinosExpedicion.remove(0);
						}						
						session.setAttribute("listaDestinosExpedicion", listaDestinosExpedicion);
						return mapping.findForward("ok");
					}
					else if(movimientos==null){		//VOY A BUSCAR LOS BULTOS DE UN DESTINO ESPECIFICO
						
						try {
							Clasificados = Logica.ArticulosEnEstanteriaClasificacion(depo, idEmpresa);
							Clasificados.remove(0);
						} catch (Exception e) {
							Clasificados = new ArrayList<>();
						}
						
						try {
							ojosExpedicion = Logica.encuentraOjosExpedicion(depo,"", idEmpresa);
							ojosExpedicion.remove(0);
						} catch (Exception e) {
							ojosExpedicion = new ArrayList<>();
						}
						
						session.setAttribute("depoExp", depo);
						session.setAttribute("ojosExpedicion", ojosExpedicion);
						//session.setAttribute("ojoExp", ojosExpedicion.get(0).getDescripcion());
						session.setAttribute("ClasificadosExpedicion", Clasificados);
						
						if(depo.equals("sort")) {
							session.setAttribute("ojoExp","sort");
							return mapping.findForward("sort");	
						}
						session.setAttribute("ojoExp", ojosExpedicion.get(0).getDescripcion());
						return mapping.findForward("dep");			
					}
					else{							//MUEVO A EXPEDICION Y CREO LOS DOCUMENTOS
						session.setAttribute("depoExp", depo);
						String idOjo = request.getParameter("idOjo"); //idOjo="nico";
						//int idDestino = 0;
						if(!idOjo.equals("")){
							/*try {
								idDestino =Integer.parseInt(depo);
							} catch (Exception e) {
								request.setAttribute("menError", "Destino incorrecto");
								return mapping.findForward("dep");
							}*/
							
							if(!movimientos.equals("")){
								DataIDCollection colection = new DataIDCollection();
								Gson gson = new Gson();
								colection = gson.fromJson(movimientos, DataIDCollection.class);
								List<DataIDDescripcion> docsSeleccionados= colection.getCollection();
								/*String q = "SELECT 2,p.idpedido,'',900000 FROM ecommerce_pedido p " + 
										"where p.idempresa=2 and p.idpedido in (159916,160465,161353)";
								List<DataIDDescripcion> docsSeleccionados = Logica.darDataIdDescripcionAnySys(q);
								docsSeleccionados.remove(0);*/
								
								Hashtable<Integer, List<DataIDDescripcion>> docsRazones = new Hashtable<Integer, List<DataIDDescripcion>>();
								Hashtable<Integer, Hashtable<Integer, List<DataIDDescripcion>>> destinosHT = new Hashtable<Integer, Hashtable<Integer,List<DataIDDescripcion>>>();
								List<DataIDDescripcion> dataDocumento;
								
								for(DataIDDescripcion b :docsSeleccionados){
									if(destinosHT.get(b.getIdB())!=null) {
										if(destinosHT.get(b.getIdB()).get(b.getId())!=null){
											destinosHT.get(b.getIdB()).get(b.getId()).add(b);
										}
										else{
											dataDocumento = new ArrayList<>();
											dataDocumento.add(b);
											destinosHT.get(b.getIdB()).put(b.getId(), dataDocumento);
										}
										
									}
									else {
										destinosHT.put(b.getIdB(),new Hashtable<Integer, List<DataIDDescripcion>>());
										if(destinosHT.get(b.getIdB()).get(b.getId())!=null){
											destinosHT.get(b.getIdB()).get(b.getId()).add(b);
										}
										else{
											dataDocumento = new ArrayList<>();
											dataDocumento.add(b);
											destinosHT.get(b.getIdB()).put(b.getId(), dataDocumento);
										}
										
									}
									
									/*if(docsRazones.get(b.getId())!=null){
										docsRazones.get(b.getId()).add(b);
									}
									else{
										dataDocumento = new ArrayList<>();
										dataDocumento.add(b);
										docsRazones.put(b.getId(), dataDocumento);
									}
									*/
									Logica.encuentraBajaArticulosOjos(1,b.getDescripcion(), b.getIdB()+"P", idEmpresa);	//MOVIMIENTO DE MERCADERIA
									Logica.encuentraMoverOjos(b.getIdB()+"E",b.getDescripcion(),1,uLog.getNumero(), idEmpresa);		
									Logica.IngresarMovimientoArticulo(b.getIdB()+"P", b.getIdB()+"E", b.getDescripcion(), 1,  uLog.getNumero(), idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA
								}
								List<Integer> allDocs = new ArrayList<>();
								List<Integer> docs;
								Enumeration<Integer> elements = destinosHT.keys();
								while (elements.hasMoreElements()) {
									int key=elements.nextElement();
									docs = Logica.GenerarDocumnetoExpedicion(key, destinosHT.get(key), uLog, Integer.parseInt(uLog.getDeposito()),idEmpresa);	//GENERO DOC PARA EXPEDICION
									allDocs.addAll(docs);
								}
								//docs = Logica.GenerarDocumnetoExpedicion(idDestino, docsRazones, uLog.getNumero(), Integer.parseInt(uLog.getDeposito()),idEmpresa);	//GENERO DOC PARA EXPEDICION
								
								if (allDocs.size()>0){
									String docs_ = "";
									for(Integer d :allDocs){
										docs_ += d+",";
									}
									docs_ = docs_.substring(0,docs_.length()-1);
									uLog.registrarEventoMin(session.getId(), "Se generaron los siguientes documentos ("+docs_+")");
									
									if(depo.equals("sort")) {
										try {
											Clasificados = Logica.ArticulosEnEstanteriaClasificacion(depo, idEmpresa);
											Clasificados.remove(0);
										} catch (Exception e) {
											Clasificados = new ArrayList<>();
										}
										session.setAttribute("ClasificadosExpedicion", Clasificados);
									}
									else {

										listaDestinosExpedicion = Logica.ArticulosEnEstanteriaClasificacion("", idEmpresa);
										if(listaDestinosExpedicion!= null && listaDestinosExpedicion.size()>0){
											listaDestinosExpedicion.remove(0);
										}
										session.setAttribute("listaDestinosExpedicion", listaDestinosExpedicion);
									}								
									
									session.setAttribute("menError", "Se generaron los siguientes documentos ("+docs_+")");
									return forw(depo,mapping);
								}
								else{
									session.setAttribute("menError", "No se pudieron grabar documentos, cierre sesion e intente nuevamente");
									return forw(depo,mapping);
								}
								
							}
							else{
								session.setAttribute("menError", "No se recibio informacion para el movimiento");
								return forw(depo,mapping);
							}
						}
						else{
							session.setAttribute("menError", "No se recibio informacion de la ubicacion de destino");
							return forw(depo,mapping);
						}							
					}
				}
				else{
					return mapping.findForward("log");
				}
				
					
			}
	
		public ActionForward forw(String depo,ActionMapping mapping) {
			if(depo.equals("sort")) {
				return mapping.findForward("sort");	
			}
			return mapping.findForward("dep");	
		}
	
		}


