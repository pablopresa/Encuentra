package web.mantenimiento;



import java.io.FileWriter;
import java.io.IOException;
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
import org.json.JSONObject;
import com.google.gson.Gson;

import beans.EstadoTree;
import beans.Menu;
import beans.Tree;
import beans.Usuario;
import dataTypes.DataIDDescripcion;


public class _EncuentraAsignacionPermisos extends Action 
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
					List<DataIDDescripcion> grupos = (List<DataIDDescripcion>)session.getAttribute("lstGrupos");
					
					if(grupos==null){			
					
					grupos = Logica.darListaDataIdDescripcion("seg_grupos", idEmpresa);
					grupos.remove(0);
					session.setAttribute("lstGrupos", grupos);
					
				}
				else{
					int grupo=0;
					try{
						grupo = Integer.parseInt(request.getParameter("grupo"));
					}
					catch(Exception e){
						request.setAttribute("menError", "Debe ingresar un grupo");
						uLog.registrarEventoMin(session.getId(), "(Seg_Grupos) Error: debe ingresar un grupo.");
						return mapping.findForward("ok");
					}
					List<DataIDDescripcion> listaGrupos = new ArrayList<>();
					listaGrupos.add(new DataIDDescripcion(grupo,""));
					
					List<Menu> menu = Logica.darMenu(listaGrupos,true, idEmpresa);
					
					uLog.registrarEventoMin(session.getId(), "(Seg_Grupos) Ve permisos de grupo "+grupo);
					String salidaCheck = "[";
					String salida = "[";
					boolean p = true;
					boolean p0 = true;
					for (Menu m : menu) 
					{	
						
						
						if(p)
						{	
							salida +="{ \"id\":\""+m.getId()+"\",\"text\":\""+m.getDescripcion()+"\"";
							p = false;
						}
						else
						{
							salida +=",{ \"id\":\""+m.getId()+"\",\"text\":\""+m.getDescripcion()+"\"";
						}
						if(m.isVisible())
						{
							salida+=",\"check\":true";
							if(p0)
							{
								salidaCheck +="'"+m.getId()+"'";
								p0=false;
							}
							else
							{
								salidaCheck +=",'"+m.getId()+"'";
							}
						}
						else
						{
							salida+=",\"check\":false";
						}
						salida+=",\"children\":[";
						if(m.isTieneHijos())
						{
							
							boolean pr = true;
							
							for (Menu h :m.getHijos()) 
							{
								
								
								if(pr)
								{
									salida +="{ \"id\":\""+h.getId()+"\",\"text\":\""+h.getDescripcion()+"\"";
									pr=false;
								}
								else
								{
									salida +=",{ \"id\":\""+h.getId()+"\",\"text\":\""+h.getDescripcion()+"\"";
								}
								
								if(h.isVisible())
								{
									salida+=",\"check\":true";
									if(p0)
									{
										salidaCheck +="'"+h.getId()+"'";
										p0=false;
									}
									else
									{
										salidaCheck +=",'"+h.getId()+"'";
									}
									
								}
								else
								{
									salida+=",\"check\":false";
								}
								
								salida+=",\"children\":[";
								if(h.isTieneHijos())
								{
									
									boolean pri = true;
									for (Menu n :h.getHijos()) 
									{
										
										if(pri)
										{
											salida +="{ \"id\":\""+n.getId()+"\",\"text\":\""+n.getDescripcion()+"\"";
											pri = false;
										}
										else
										{
											salida +=",{ \"id\":\""+n.getId()+"\",\"text\":\""+n.getDescripcion()+"\"";
											
										}
										if(n.isVisible())
										{	
											salida+=",\"check\":true}";
											if(p0)
											{
												salidaCheck +="'"+n.getId()+"'";
												p0=false;
											}
											else
											{
												salidaCheck +=",'"+n.getId()+"'";
											}
										}
										else
										{
											salida+=",\"check\":false}";
										}
									}
									
								}
								salida +="]}";
								
								
							}
							
							
						}
						
						salida +="]}";
						
						
                   	
					}
					salida += "]";
					salidaCheck +="]";
					System.out.println(salida);
					System.out.println(salidaCheck);
					
					/*
					Gson gson = new Gson();
					List<Tree> arbol = CrearArbol(menu);
					String representacionJSON=gson.toJson(arbol);
					
					String nombreGrupo="";
					for(DataIDDescripcion g :grupos){
						if(g.getId()==grupo){
							nombreGrupo=g.getDescripcion();
						}
					}
					*/
					//session.setAttribute("nombreGrupo", nombreGrupo);					
					//session.setAttribute("menus", representacionJSON);
					
					session.setAttribute("grupoPermisos", grupo);
					session.setAttribute("datosArbol", salida);
					session.setAttribute("datosArbolCheck", salidaCheck);
					
				}
				
				}
				catch(Exception e){
					System.out.println("error");
				}
				
				return mapping.findForward("ok");
			}
	
		public List<Tree> CrearArbol(List<Menu> menu){
			List<Tree> listaArboles = new ArrayList<>();
			Tree arbol;

			try {
				for(Menu m:menu){
					arbol = new Tree();
					arbol.setText(m.getDescripcion().replace(" ", "_"));
					arbol.setHref(m.getId());
					if(m.isVisible()){
						arbol.setState(new EstadoTree(true,false));
					}
					else{
						arbol.setState(new EstadoTree(false,false));
					}
				
					if(m.isTieneHijos()){
						arbol.setNodes(CrearArbol(m.getHijos()));
						listaArboles.add(arbol);
					}
					else{
						listaArboles.add(arbol);
					}
				
				
				}
			

		} catch (Exception e) {
			//manejar error
		}
		
		return listaArboles;
		
	}
	
	public void JWriter(){
		JSONObject obj = new JSONObject();
		obj.put("text", "Informatica");
		
		/*JSONArray list = new JSONArray();
		list.add("tag 1");
		list.add("tag 2");
		list.add("tag 3");

		obj.put("Tags", list);*/
		
		JSONObject innerObj = new JSONObject();
		/*innerObj.put("PostX","Escribir un JSON");
		innerObj.put("PostY", "Leer un JSON");
		innerObj.put("PostZ", "lalala");*/
		
		obj.put("children",innerObj);

		try {

			FileWriter file = new FileWriter("c:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\encuentra\\prueba.json");
			file.write(obj.toString());
			file.flush();			
			file.close();

		} catch (IOException e) {
			//manejar error
		}

		System.out.print(obj);
	}
	
	
		
		}

