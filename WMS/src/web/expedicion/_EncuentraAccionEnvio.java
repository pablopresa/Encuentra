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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

import dataTypes.DataIDDescripcion;




import beans.Articulo;


import beans.Estado;

import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Transporte;

public class _EncuentraAccionEnvio extends Action 
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
				/*
				accion=1">Documentos</a></td>
				accion=2">Retomar</a>
				accion=3">Pasar a env?o</a> 
				accion=4">Eliminar</a>
				
				
				*/
				
				
				try
				{
					int idEnvio = Integer.parseInt(request.getParameter("idEnvio"));
					int accion = Integer.parseInt(request.getParameter("accion"));
					
					List <Envio> envios =  (List<Envio>) session.getAttribute("envios");
					Envio envio = null;
					
					for (Envio e : envios) 
					{
						if(e.getIdEnvio()==idEnvio)
						{
							envio = e;
							break;
						}
					}
					
					
					switch (accion) 
					{
						case 1:
						{
							session.setAttribute("envio", envio);
							return mapping.findForward("vp");
						}
						case 2://retomar
						{
							//tendria que setear el envio y devolver aalta envioII.jsp
							session.setAttribute("envio", envio);
							Integer posicionUltimo = 0;
							for (DepositoEnvio d : envio.getDepositos()) 
							{
								posicionUltimo = d.getOrdenCarga();
							}
							session.setAttribute("posicionUltimo", posicionUltimo);
							List<DepositoEnvio> depositos = Logica.darDepositosenvio(idEmpresa);
							session.setAttribute("depositosSel", depositos);
							return mapping.findForward("alta");
							
						
						}
						case 3://pasar a envio
						{
							//actualizar el estado del envio
							Logica.encuentraActualizaEstadoEnvio(1,envio.getIdEnvio(),idEmpresa);
							envio.setEstado(new DataIDDescripcion(1, "Enviado"));
							session.setAttribute("envio", envio);
							envios = Logica.encuentraDarEnvios(null, null, 2, null,0, idEmpresa);
							
							session.setAttribute("envios",envios);
							//devolver el envio a la pagina de alta de envio cuando terminan
							return mapping.findForward("fin");
							
						}
						case 4://eliminar
						{
							//actualizar el estado del envio
							Logica.encuentraActualizaEstadoEnvio(3,envio.getIdEnvio(),idEmpresa);
							envio.setEstado(new DataIDDescripcion(1, "Cancelado"));
							session.setAttribute("envio", envio);
							envios = Logica.encuentraDarEnvios(null, null, 2, null,0, idEmpresa);
							
							session.setAttribute("envios",envios);
							//devolver el envio a la pagina de alta de envio cuando terminan
							return mapping.findForward("ok");
														
						}
						case 5:
						{

							Gson g = new Gson();
							String gg = g.toJson(envio);
							System.out.println(gg);
							session.setAttribute("envio", envio);
							return mapping.findForward("manifiesto");
						}
					
						default:
						{
							break;
							
						}
					}
					
						
					session.setAttribute("envio", envio);
				}
				catch (Exception e)
				{
					String men = "no hay envio seleccionado";
					request.setAttribute("menError", men);
				}
				
				return mapping.findForward("ok");
				
			}
	
	
		
		}


