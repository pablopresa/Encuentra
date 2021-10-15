package web.recepcion;

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


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;
import dataTypes.DataOC;
import dataTypes.DataRecepcion;




import beans.Articulo;

import beans.Estado;
import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.ColorOC;
import beans.encuentra.DataArticuloOC;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.TalleOC;
import beans.encuentra.Transporte;

public class _EncuentraAgendarEntrega extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
 Logica Logica = new Logica();
				DataRecepcion recepcion = (DataRecepcion) session.getAttribute("recepcion");
				
				int cantidad = 0;
				
				List<DataOC> ordenes = (List<DataOC>) session.getAttribute("ordenes");
				
				List<DataOC> OCIncluidas = new ArrayList<>();
				
				if(ordenes==null || ordenes.isEmpty()) {
					request.setAttribute("menError", "Debe seleccionar algun articulo");
					return mapping.findForward("no");
				}
				
				for (DataOC d : ordenes) 
				{
					int cantidadSol = 0;
					System.out.println(d.getNumeroDocumento());
					
						//Instancio una OC
						DataOC oc = d;
						//instancio la lista de articulos
						List <DataArticuloOC> articulos = new ArrayList<>();
						//instancio la lista de colores
						
						
						for (DataArticuloOC a : d.getArticulos()) 
						{
								List<ColorOC> colores = new ArrayList<>();
								//instancio Articulo
								DataArticuloOC art = new DataArticuloOC();
								art.setIdArticulo(a.getIdArticulo());
								for (ColorOC c : a.getColores()) 
								{
									if(request.getParameter(d.getNumeroDocumento()+a.getIdArticulo()+c.getId())!=null)
									{	//instancio color
										ColorOC col = new ColorOC();
										col.setId(c.getId());
										col.setDescripcion(c.getDescripcion());
										List<TalleOC>talles = new ArrayList<>();
										System.out.println("ENTRE ");
										for (TalleOC t : c.getTalles()) 
										{
											//agrego las cantidades de los talles
											String cant = request.getParameter(d.getNumeroDocumento()+""+a.getIdArticulo()+c.getId()+t.getTalle());
											if(cant!=null)
											{
												TalleOC tal = t;
												tal.setSolicitada(Integer.parseInt(cant));
												tal.setIdLinea(t.getIdLinea());
												tal.setEntregada(0);
												if(tal.getSolicitada()>0)
												{
													talles.add(tal);
													cantidad += tal.getSolicitada();
													cantidadSol += tal.getSolicitada();
												}
											}
											
										}
										if(!talles.isEmpty())
										{
											col.setTalles(talles);
											colores.add(col);
										}
										
									}
									
								}
								if(!colores.isEmpty())
								{
									art.setColores(colores);
									articulos.add(art);
								}
								
							
								
						}
						
						if(!articulos.isEmpty())
						{
							oc.setArticulos(articulos);
							OCIncluidas.add(oc);
							oc.setUnidadesSolicitadas(cantidadSol);
						}
						
					
						
					
					
				}
				
				recepcion.setCantidadEsperada(cantidad);
				recepcion.setOrdenes(OCIncluidas);
				
				/*
				for (DataOC d : OCIncluidas) 
		        {
		        	System.out.println("ORDEN " + d.getNumeroDocumento());
		        	for (DataArticuloOC a : d.getArticulos()) 
		        	{
							System.out.println("**************ARTICULO "+a.getIdArticulo());
							for (ColorOC co : a.getColores()) 
							{
								System.out.println("***************************COLOR "+ co.getId());
								for (TalleOC ta : co.getTalles()) 
								{
									
									System.out.println("****************************************** TALLE "+ta.getTalle() + " SOL: "+ta.getSolicitada() +" ENT: " + ta.getEntregada());
								}
							}
							
					}
				}
				*/
				session.setAttribute("recepcion",recepcion);
				return mapping.findForward("ok");
				
			}
	
	
		
		}


