package web.expedicion;

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
import main.process_ecommerce.mercadoLibreObjects.Sort;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;




import beans.Articulo;
import beans.Estado;
import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DataArticulo;
import beans.encuentra.DataDocTipoEnvio;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Transporte;

public class _EncuentraAltaEnvioIII extends Action 
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
				
				List<DepositoEnvio> depositos = (List<DepositoEnvio>) session.getAttribute("depositosSel");
				List <DocumentoEnvio> documentosSel = (List<DocumentoEnvio>) session.getAttribute("documentosSel");
				String depo = (String) session.getAttribute("depositoSelected");
				String sub = request.getParameter("subm");
				
				Integer pos = 0;
				int accion = 0;
				//1 vista previa
				//2 cargar otro
				//3 guardar parcial
				//4 asignar y guardar
				//5 generar tarea "Cargar Transporte"
				
				if(sub.equals("Vista Previa"))
				{
					accion = 1;
				}
				else if(sub.equals("Cargar otro"))
				{
					accion = 2;
				}
				else if(sub.equals("Guardar Parcial"))
				{
					accion = 3;
				}
				else if(sub.equals("Asignar y Guardar"))
				{
					accion = 4;
				}
				else if(sub.equals("Generar Tarea"))
				{
					accion = 5;
				}
				int idDepo = Integer.parseInt(depo);
				
				
				DepositoEnvio deposito = null;
				for (DepositoEnvio d : depositos) 
				{
					if(d.getIdDeposito()==idDepo)
					{
						deposito=d;
						break;
					}
				}
				
				Envio envio = (Envio) session.getAttribute("envio");
				int idEnvio = 0;
				
				String cerrado = (String) session.getAttribute("envioCerrado");
				if(cerrado!=null && cerrado.equals("1")) {
					session.setAttribute("envioCerrado", "0");
					envio.setDepositos(null);
					envio.setIdEnvio(0);
				}
				
				
				
				if(documentosSel!=null)
				{
						
				
					List<DocumentoEnvio> documentos = new ArrayList<>();
					List<DocumentoEnvio> documentosExtra = new ArrayList<>();
					
					
					for (DocumentoEnvio d : documentosSel)
					{
						String on = request.getParameter(String.valueOf(d.getNumeroDoc()));
						if(on!=null && on.equals("on"))
						{
							String comentario = request.getParameter("com"+String.valueOf(d.getNumeroDoc()));
							d.setComentario(comentario);
							if(d.getRazon().getId()==37 || d.getRazon().getId()==36 || d.getRazon().getId()==34)
							{
								
								documentosExtra.add(d);
							}
							else
							{
								documentos.add(d);
							}
							
						}
					}
					
					Hashtable<Integer, DataDocTipoEnvio> htDocs = new Hashtable<>();
					Hashtable<Integer, DataDocTipoEnvio> htDocsExtra = new Hashtable<>();
					
					for (DocumentoEnvio d : documentos) 
					{
						if(htDocs.get(d.getRazon().getId())==null)
						{
							DataDocTipoEnvio dt = new DataDocTipoEnvio();
							dt.setIdTipo(d.getRazon().getId());
							dt.setDecTipo(d.getRazon().getDescripcion());
							dt.setCantidad(d.getCantidad());
							List<DocumentoEnvio> docs = new ArrayList<>();
							docs.add(d);
							dt.setDocumentos(docs);
							htDocs.put(d.getRazon().getId(), dt);
						}
						else//sumo a los totales porque ya esta dentro de la lista
						{
							DataDocTipoEnvio dt = htDocs.get(d.getRazon().getId());
							dt.setCantidad(dt.getCantidad()+d.getCantidad());
							dt.getDocumentos().add(d);
							htDocs.put(d.getRazon().getId(), dt);
						}
					}
					
					for (DocumentoEnvio d : documentosExtra) 
					{
						if(htDocsExtra.get(d.getRazon().getId())==null)
						{
							DataDocTipoEnvio dt = new DataDocTipoEnvio();
							dt.setIdTipo(d.getRazon().getId());
							dt.setDecTipo(d.getRazon().getDescripcion());
							dt.setCantidad(d.getCantidad());
							List<DocumentoEnvio> docs = new ArrayList<>();
							docs.add(d);
							dt.setDocumentos(docs);
							htDocsExtra.put(d.getRazon().getId(), dt);
						}
						else//sumo a los totales porque ya esta dentro de la lista
						{
							DataDocTipoEnvio dt = htDocsExtra.get(d.getRazon().getId());
							dt.setCantidad(dt.getCantidad()+d.getCantidad());
							dt.getDocumentos().add(d);
							htDocsExtra.put(d.getRazon().getId(), dt);
						}
					}
					
					ArrayList<DataDocTipoEnvio> docsPorTipo = new ArrayList<>(htDocs.values());
					ArrayList<DataDocTipoEnvio> docsPorTipoExtra = new ArrayList<>(htDocsExtra.values());
					
					int totalU = 0;
					int totalB = 0;
					
					int tipoD = 0;
					String desctipoD = "";
					Hashtable<Integer, DataIDDescripcion> cantTipos = new Hashtable<>();
					DataIDDescripcion data = new DataIDDescripcion(2,"Ecommerce");
					data.setIdB(0);
					cantTipos.put(2, data);
					data = new DataIDDescripcion(3,"Reposicion");
					data.setIdB(0);
					cantTipos.put(3, data);
					data = new DataIDDescripcion(6,"Mayorista");
					data.setIdB(0);
					cantTipos.put(6, data);
					
					
					for (DataDocTipoEnvio da : docsPorTipo) 
					{
						for(DocumentoEnvio daa: da.getDocumentos()){
							totalB+= daa.getCantidad();
							
							tipoD = daa.getRazon().getId();
							desctipoD = daa.getRazon().getDescripcion();
							for(DataIDDescripcion it:daa.getListaDocs()){
								totalU+= it.getId();
								if(cantTipos.get(tipoD)==null) {
									data = new DataIDDescripcion(tipoD,desctipoD);
									data.setIdB(1);
									cantTipos.put(tipoD, data);
								}else {
									int cant = cantTipos.get(tipoD).getIdB()+1;
									cantTipos.get(tipoD).setIdB(cant);
								}
							}
						}						
					}
					
					deposito.setCantidadesXTipoDocs(new ArrayList<>(cantTipos.values()));
					Collections.sort(deposito.getCantidadesXTipoDocs());
					
					deposito.setTotalBultos(totalB);
					deposito.setTotalU(totalU);
					deposito.setDocumentos(docsPorTipo);
					deposito.setDocumentosExtra(docsPorTipoExtra);
					
					if((Integer) session.getAttribute("posicionUltimo")==null)
					{
						pos = 1;
						deposito.setOrdenCarga(pos);
						
					}
					else
					{
						pos = (Integer) session.getAttribute("posicionUltimo")+1;
						deposito.setOrdenCarga(pos);
					}
					session.setAttribute("posicionUltimo", pos);
					
					
					if(envio.getDepositos()==null)
					{
						List<DepositoEnvio> depositosN = new ArrayList<>();
						depositosN.add(deposito);
						envio.setDepositos(depositosN);
					}
					else
					{
						List <DepositoEnvio> depos = new ArrayList<>();
						
						
						for (DepositoEnvio de : envio.getDepositos()) 
						{
							if(de.getIdDeposito()!=deposito.getIdDeposito())
							{
								depos.add(de);
							}
							
							
						}
						
						depos.add(deposito);
						
						Collections.sort(depos);
						
						envio.setDepositos(depos);
						
					}
					
					
					
					session.setAttribute("documentosSel", null);
					session.setAttribute("depositoSelected", null);
					
					
					
					
					envio.setSentido(1);//ida
					session.setAttribute("envio", envio);
					
					
					session.setAttribute("depositoSelected", depo);
				}

				String mensaje = "";
				
				if(envio!=null && !envio.getDepositos().isEmpty())
				{
				
					if(accion==1)//vista previa
					{
						return mapping.findForward("vp");
					}
					else if(accion==2)//cargar otro
					{
						return mapping.findForward("cargarOtro");
					}
					else if(accion == 3)//guardar Parcial
					{
						envio.setParcial(true);
						idEnvio = Logica.altaEnvio(envio, idEmpresa,uLog);
						if (idEnvio!=0){
						mensaje = "La entrega se guard? como parcial, el numero es "+idEnvio;
						envio.setIdEnvio(idEnvio);
						session.setAttribute("envio", envio);
						}
						else{
							 mensaje = "Ocurrio un error dando de alta el Envio";
						}
						request.setAttribute("menError", mensaje);
						return mapping.findForward("fin");
					}
					else if(accion==4)//guardar
					{
						envio.setParcial(false);
						idEnvio = Logica.altaEnvio(envio, idEmpresa,uLog);
						if (idEnvio!=0){
							mensaje = "La entrega se guard? correctamente, el numero es "+idEnvio;
							envio.setIdEnvio(idEnvio);
							session.setAttribute("envio", envio);
														
							for(DepositoEnvio d : envio.getDepositos()){
								for(DataDocTipoEnvio dd : d.getDocumentos()){
									for(DocumentoEnvio de: dd.getDocumentos()){
										for(DataArticulo da: de.getArticulos()){
											Logica.encuentraBajaArticulosOjos(1,da.getId(), d.getIdDeposito()+"E", idEmpresa);	//MOVIMIENTO DE MERCADERIA
											//Logica.encuentraMoverOjos("2",da.getId(),1,uLog.getNumero());		
											//Logica.IngresarMovimientoArticulo(d.getIdDeposito()+"E", "2", da.getId(), 1,  uLog.getNumero());	//REGISTRO EL MOVIMIENTO DE MERCADERIA
											Logica.IngresarMovimientoArticuloTipo(d.getIdDeposito()+"E", "", da.getId(), 1,  uLog.getNumero(),"EXP",idEmpresa);
										}										
									}	
								}
							}
							
							
						}
						else{
							 mensaje = "Ocurrio un error dando de alta el Envio";
						}

						session.setAttribute("envioCerrado", "1");
						request.setAttribute("menError", mensaje);						
						return mapping.findForward("fin");
					}
					else if(accion == 5)//generar tarea
					{
						envio.setParcial(true);
						idEnvio = Logica.altaEnvio(envio,idEmpresa,uLog);
						if (idEnvio!=0){
						mensaje = "ACCION NO IMPLEMENTADA A?N. La entrega se guard? como parcial, el numero es "+idEnvio;
						envio.setIdEnvio(idEnvio);
						session.setAttribute("envio", envio);
						}
						else{
							 mensaje = "Ocurrio un error dando de alta el Envio";
						}

						request.setAttribute("menError", mensaje);	
						return mapping.findForward("fin");
					}
				}
					
				return mapping.findForward("ok");
			}
	
	
		
		}


