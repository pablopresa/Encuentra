package web.ecommerce;



import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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

import beans.Usuario;
import dataTypes.DataIDDescripcion;


public class _EcommerceEmpaquetarPedido extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				try{
					HttpSession session = request.getSession();
					Logica Logica = new Logica();
					Usuario uLog = (Usuario) session.getAttribute("uLogeado");
					Utilidades util = new Utilidades();
					int idEmpresa = util.darEmpresa(uLog);
					if(idEmpresa==0)
					{
						return mapping.findForward("LOGIN");
					}
					Long idPedido= new Long(0);
					boolean completo=false;
					int total=0;
					int parcial=0;	
					List<DataIDDescripcion> paquetes = new ArrayList<>(); 
					
					String pedido= request.getParameter("pedido");
					
					if(pedido!=null){// busqueda pedido
					try{
						idPedido=new Long(pedido);
					}
					catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("menError", "Ocurrio un error durante el proceso");
						return mapping.findForward("init");
					}
					
					
						paquetes = Logica.darPaquetes(idPedido, idEmpresa);
						
						if(paquetes==null || paquetes.size()==0){
							request.setAttribute("menError", "Pedido sin procesar o ya se preparo para enviar");
							return mapping.findForward("init");
						}
					
						for(DataIDDescripcion p:paquetes){
							total++;
						}
					
						session.setAttribute("paqueteTotal", total);
						session.setAttribute("paqueteParcial", parcial);
						session.setAttribute("paqueteCompleto", completo);
						session.setAttribute("paquetePedido",idPedido);
						session.setAttribute("paquetes",paquetes);
					
					}
					else{ //control de articulos
						
						paquetes = (List<DataIDDescripcion>) session.getAttribute("paquetes");
						parcial = (Integer) session.getAttribute("paqueteParcial");
						total = (Integer) session.getAttribute("paqueteTotal");
						boolean pertenece = false;
						
						ServletContext context = request.getSession().getServletContext();
						Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
						
						String barra = request.getParameter("base");
						String articulo="";
						try
						{
							articulo = artBarra.get(barra);
						}
						catch(Exception e)
						{
							try
							{
								articulo = artBarra.get(barra.toUpperCase());
							}
							catch(Exception ex)
							{
								try
								{
									articulo = artBarra.get(barra.toLowerCase());
								}
								catch(Exception exx)
								{									
										
								}									
							}								
						}
						
						//SEGUNDO CONTROL, POR SI NO DA NULL Y DA ''
						if(articulo.equals("")){
							try
							{
								articulo = artBarra.get(barra.toUpperCase());
							}
							catch(Exception ex)
							{									
							}	
						}
						
						if(articulo.equals("")){
							try
							{
								articulo = artBarra.get(barra.toLowerCase());
							}
							catch(Exception ex)
							{									
							}	
						}
						
						if(articulo==null || articulo.equals(""))
						{
							List<String> articulos = new ArrayList<>(artBarra.values());
							for (String a : articulos) 
							{
								if(barra.toUpperCase().equals(a.toUpperCase()))
								{
									//puso el articulo y no el codebar
									articulo=barra;
									break;
								}
							}
							
							
						}
						
						for(DataIDDescripcion p:paquetes)
						{
							if(p.getDescripcion().toUpperCase().equals(articulo.toUpperCase()) && p.getId()==1){
								p.setId(0);
								parcial++;
								pertenece=true;
								break;
							}
						}
						
						if(parcial==total){
							completo=true;
						}
						
						if(!pertenece){
							request.setAttribute("menError", "Este articulo no pertenece al pedido");
							return mapping.findForward("ok");
						}
						
						session.setAttribute("paqueteParcial", parcial);
					}
					
					if(completo){
						return mapping.findForward("completo");
					}
					
				}
				catch(Exception e){
					System.out.println("error");
					request.setAttribute("menError", "Ocurrio un error durante el proceso");
					return mapping.findForward("ok");
				}
				
				return mapping.findForward("ok");
			}
	
	
		
		}

