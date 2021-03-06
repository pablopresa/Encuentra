package web.almacen;



import java.util.ArrayList;
import java.util.List;

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
import beans.encuentra.ArticuloMedidas;
import beans.encuentra.DataOjoArticulo;


public class _EncuentraArticulosAsignarMedidas extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				try{
					HttpSession session = request.getSession();
					Logica Logica = new Logica();
					Utilidades util = new Utilidades();
					
					Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
					int idEmpresa = util.darEmpresa(uLog);
					if(idEmpresa==0)
					{
						return mapping.findForward("LOGIN");
					}
					
					String art = request.getParameter("art");
					int ancho=0;
					int alto=0;
					int prof=0;
					ArticuloMedidas medidas; 
					List<ArticuloMedidas> listaMedidas = new ArrayList<>();
					List<DataOjoArticulo> listaArt = new ArrayList<>();
					
					if(art.equals("")){
						
						listaArt = (List<DataOjoArticulo>) session.getAttribute("sinmedidas");
						
						for(DataOjoArticulo a:listaArt){
							if(!request.getParameter("ancho"+a.getArticulo()).equals("0")){
								try{
									ancho = Integer.parseInt(request.getParameter("ancho"+a.getArticulo()));
									alto = Integer.parseInt(request.getParameter("alto"+a.getArticulo()));
									prof = Integer.parseInt(request.getParameter("profundidad"+a.getArticulo()));							
								}
								catch(Exception e){
									String error="Error en tipo de datos";
									session.setAttribute("menError", error);
								}	
								
								medidas=new ArticuloMedidas(a.getArticulo(),ancho,alto,prof);
								listaMedidas.add(medidas);
								
							}
						}
						
					}
					else{
						String a = request.getParameter("ancho"+art);
						try{
							ancho = Integer.parseInt(request.getParameter("ancho"+art));
							alto = Integer.parseInt(request.getParameter("alto"+art));
							prof = Integer.parseInt(request.getParameter("profundidad"+art));							
						}
						catch(Exception e){
							String error="Error en tipo de datos";
							session.setAttribute("menError", error);
						}	
						
						medidas=new ArticuloMedidas(art,ancho,alto,prof);
						listaMedidas.add(medidas);
						
					}
					
					Logica.ingresarMedidasArticulo(listaMedidas,idEmpresa);
					
					int deposito = 0;
					try {deposito = Integer.parseInt(uLog.getDeposito());}catch (Exception e) { }
					listaArt = Logica.DarArtsSinMedidas(idEmpresa, deposito);
					
					session.setAttribute("sinmedidas", listaArt);
				
				}
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
	
	
		
		}

