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
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataOC;
import dataTypes.DataRecepcion;
import javassist.bytecode.analysis.Util;
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
import beans.encuentra.ValueObjects.VORecepcionSinOrden;

public class _EncuentraAgendarEntregaSinOrden extends Action 
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
				
				DataRecepcion recepcion = new DataRecepcion();
				DataDescDescripcion proveedor = new DataDescDescripcion();
				proveedor.setId("1");
				proveedor.setDescripcion("Importacion");
				recepcion.setProveedor(proveedor);
				
				int cantidad = 0;
				
				List<VORecepcionSinOrden> articulosEnOrden = (List<VORecepcionSinOrden>) session.getAttribute("ordenesAux");
				
				List<DataOC> OCIncluidas = new ArrayList<>();
				
				if(articulosEnOrden==null || articulosEnOrden.isEmpty()) {
					request.setAttribute("menError", "Por favor intente importar el Excel nuevamente");
					return mapping.findForward("no");
				}
				
				
				//Instancio una OC
				DataOC oc = new DataOC();
				//Seteo numero de OC
				int idOrdenAutogenerado = util.darUltimoIdAutomatico(31, idEmpresa);
				oc.setNumeroDocumento(idOrdenAutogenerado);
				System.out.println(oc.getNumeroDocumento());
				//instancio la lista de articulos
				List <DataArticuloOC> articulos = new ArrayList<>();
				int cantidadSolOrden = 0;
				int idLinea = 0;
					
				//instancio la lista de colores
				for (VORecepcionSinOrden d : articulosEnOrden) 
				{
					String idArticulo = d.getIdArticulo();
					int totalXLinea = d.getCantidadTotal();
					idLinea ++;
					
					List<ColorOC> colores = new ArrayList<>();
					//instancio Articulo
					DataArticuloOC art = new DataArticuloOC();
					art.setIdArticulo(idArticulo);
					
					//instancio color
					ColorOC col = new ColorOC();
					col.setId("");
					col.setDescripcion("");
					List<TalleOC>talles = new ArrayList<>();
					
					//agrego las cantidades de los talles
					TalleOC tal = new TalleOC();
					tal.setTalle("");
					tal.setSolicitada(totalXLinea);
					tal.setIdLinea(idLinea);
					tal.setEntregada(0);
					if(totalXLinea>0)
					{
						talles.add(tal);
						cantidad += tal.getSolicitada();
						cantidadSolOrden += tal.getSolicitada();
					}
						
					if(!talles.isEmpty())
					{
						col.setTalles(talles);
						colores.add(col);
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
					oc.setUnidadesSolicitadas(cantidadSolOrden);
				}
						
				recepcion.setCantidadEsperada(cantidad);
				recepcion.setOrdenes(OCIncluidas);
				
				
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
				
				session.setAttribute("recepcion",recepcion);
				return mapping.findForward("ok");
				
			}
	
	
		
		}


