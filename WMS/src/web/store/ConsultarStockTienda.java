package web.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.LeerHTML;
import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataArtPedidoPickup;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import beans.ProcessEcommerce;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.IPrint;
import beans.encuentra.RecepcionExpedicion;
import beans.encuentra.RemitoLinea;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class ConsultarStockTienda extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		Logica log = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		

		try 
		{
			String base = request.getParameter("barra");
			int idTienda = Integer.parseInt(uLog.getDeposito());
			
			
			List<DataDescDescripcion> matches =  log.matchArticulo(base,idTienda,idEmpresa);	
			
			try 
			{				
				List<String> tablas = new ArrayList<>();
				
				for (DataDescDescripcion match : matches) 
				{
					Hashtable<String, Integer> depArtTalle = log.ConsultarStockBaseColorInStore("'"+match.getId()+"'", idTienda+"", idTienda,idEmpresa);
					
					if(!depArtTalle.isEmpty())
					{
						String descripcion = match.getDescripcion();
						
						String HTML3="<div class=\"card-header\"><h5>"+descripcion+"</h5></div>";
						HTML3+="<img class=\"d-block w-100\" src=\"http://10.108.0.19:8085/Imagenes/"+match.getId()+".jpg\" style=\"width:200px\">";
						
						
						List<String> tallesHeadSA = log.darTallesBase(match.getId());
						List<String> linea0 = new ArrayList<>();
						linea0.add("---");
						
						Hashtable<String, Integer> ttlTalles = log.ConsultarStockBaseColorInStore("'"+match.getId()+"'", idTienda+"", idTienda,idEmpresa);
						List<String> tallesHead = new ArrayList<>(); 
						List<DataIDDescripcion> deposSort = log.darDeposStockInStore(match.getId(),idTienda,idTienda,idEmpresa);
						
						for (DataIDDescripcion d : deposSort) 
						{
							String formatted = String.format("%03d", d.getId());
							d.setDescripcion(formatted);
						}
						
						Collections.sort(deposSort);
						
						
						for (DataIDDescripcion d : deposSort) 
						{
							
							for (String t : tallesHeadSA) 
							{
								if(depArtTalle.get(d.getId()+"-"+match.getId()+"-"+t)!=null)
								{
									
									if(ttlTalles.get(t)==null)
									{
										ttlTalles.put(t,depArtTalle.get(d.getId()+"-"+match.getId()+"-"+t));
										
									}
									else
									{
										int currentTTL = ttlTalles.get(t);
										currentTTL+=depArtTalle.get(d.getId()+"-"+match.getId()+"-"+t);
										
										ttlTalles.put(t, currentTTL);
										
									}
								}
								
							}
							
						}
						
						
						for (String t : tallesHeadSA) 
						{
							try
							{
								if(ttlTalles.get(t)!=null)
								{
									tallesHead.add(t);
								}
								
							}
							catch(Exception e)
							{
								
							}
							
						}
						
						
						
						
						
						for (String t : tallesHead) 
						{
							linea0.add(t);
						}
						linea0.add("TTL.");
						List<List<String>> lineas = new ArrayList<List<String>>();
						
						
						
						
						for (DataIDDescripcion d : deposSort) 
						{
							List<String> linea = new ArrayList<>();
							linea.add(d.getId()+"");
							int total =0;
							for (String t : tallesHead) 
							{
								if(depArtTalle.get(d.getId()+"-"+match.getId()+"-"+t)!=null)
								{
									linea.add(depArtTalle.get(d.getId()+"-"+match.getId()+"-"+t)+"");
									total+=depArtTalle.get(d.getId()+"-"+match.getId()+"-"+t);
								}
								else
								{
									linea.add("");
								}
							}
							if(total>0)
							{
								linea.add(total+"");
								lineas.add(linea);
							}
							
						}
						
						/*linea total fondo*/
						
						List<String> linea = new ArrayList<>();
						linea.add("TOTAL:");
						
						
						int ttlGRL = 0;
						for (String t : tallesHead) 
						{
							try
							{
								linea.add(ttlTalles.get(t)+"");
								ttlGRL+=ttlTalles.get(t);
							}
							catch(Exception e)
							{
								
							}
							
						}
						linea.add(ttlGRL+"");
						lineas.add(linea);
						
						
						String tabla = "<table class='table table-striped table-bordered table-hover' id='encuentra-default' style='padding: 1px; text-align:center'>" +
										"	<tr>";
						for (String head : linea0) 
						{
							tabla+="<td style='padding: 2px;'>"+head+"</td> ";
						}
						tabla+="</tr>";
						for (List<String> lines : lineas) 
						{
							tabla+="<tr>";
							for (String t : lines) 
							{
								tabla+="<td style='padding: 2px;'>"+t+"</td>";
							}
							
							tabla+="</tr>";
							
						}
						tabla+="</table>";
						
						
						HTML3 += tabla+" ";
						
						
						tablas.add(HTML3);
						
					}
					
					
					
					
				}
				
				
				
				/*
				String carrusel0 = " <!-- Indicators -->" + 
						"							  <ol class=\"carousel-indicators\">" ;
				
				String carruselLIs =	"";
				//aca va el primer for
				int slideTo = 0;
				boolean pri = true;
				for (String t : tablas) 
				{
					if(pri)
					{
						pri=false;
						carruselLIs+=" <li data-target=\"#carouselExampleIndicators\" data-slide-to=\"0\" class=\"active\"></li>" ;
						
					}
					else
					{
						carruselLIs+=" <li data-target=\"#carouselExampleIndicators\" data-slide-to=\""+slideTo+"\"></li>" ;
					}
					
					slideTo++;
					// "							     
				}
										
				*/		 
				String carruselMD=		/*"							  </ol>" + */
						"							" + 
						"							  <!-- Wrapper for slides -->" + 
						"							  <div class=\"carousel-inner\">" ;
				String carruselBody="";
			
				boolean pri2 = true;
				for (String t : tablas) 
				{
					String classe ="";
					if(pri2)
					{
						pri2=false;
						classe = "carousel-item active";
						
					}
					else
					{
						classe = "carousel-item";
					}
					
					carruselBody+="							<div class=\""+classe+"\">" + 
																t+
							"							    </div>" ; 
					
					
				}
			
						 
			String carruselPie=			"							  </div>" + 
						"							" + 
						"							  <!-- Left and right controls -->" + 
						"			<a class=\"carousel-control-prev\" href=\"#carouselExampleControls\" role=\"button\" data-slide=\"prev\"><span class=\"carousel-control-prev-icon\" aria-hidden=\"true\" style=\"background-color: #008CBA;\"></span><span class=\"sr-only\">Previous</span></a>" + 
						"           <a class=\"carousel-control-next\" href=\"#carouselExampleControls\" role=\"button\" data-slide=\"next\"><span class=\"carousel-control-next-icon\" aria-hidden=\"true\" style=\"background-color: #008CBA;\"></span><span class=\"sr-only\">Next</span></a>";
				
			
			
			
			
				
				//session.setAttribute("htmls", tablas);
			
			session.setAttribute("htmls", /*carrusel0+carruselLIs+*/carruselMD+carruselBody+carruselPie);

			} 
			catch (Exception e) 
			{
				System.out.println("llegamos al catch");
				String tablaError ="<table class='table table-striped table-bordered table-hover' id='encuentra-default' style='padding: 1px; text-align:center'>" +
				"	<tr><td style='padding: 2px;'>ARTICULO INCORRECTO</td></tr></table>";
				session.setAttribute("html", tablaError);
				session.setAttribute("mensaje", e.getMessage());
				return mapping.findForward("ok");

			}
			
		} 
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");
		}
		
		return mapping.findForward("ok");
	}

}
