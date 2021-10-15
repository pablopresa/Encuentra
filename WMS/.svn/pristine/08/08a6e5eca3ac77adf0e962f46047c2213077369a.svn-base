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


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataIDDescripcion;




import beans.Articulo;
import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Transporte;

public class _EncuentraModOrdenEnvio extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
 Logica Logica = new Logica();
				//acc
				//idDeposito
				
				String mov = request.getParameter("acc");
				int deposito = Integer.parseInt(request.getParameter("idDeposito"));
				
				Envio envio = (Envio) session.getAttribute("envio");
				
				 
				
				int pos = 1;//es en la posicion que me encuentro
				List <DepositoEnvio>listAux = new ArrayList<>();
				if(mov.equals("eliminar"))
				{
					for (DepositoEnvio dep : envio.getDepositos())
					{
						if(dep.getIdDeposito()!=deposito)
						{
							dep.setOrdenCarga(pos);
							listAux.add(dep);
						}
						pos++;
					}
					
					Collections.sort(listAux);
					envio.setDepositos(listAux);
					session.setAttribute("envio",envio);
				}
				else
				{
					try
					{
						/*
						if(mov.equals("sube"))
						{
							//hay que restarle uno al que tengo
							listAux.get(pos).setOrdenCarga(listAux.get(pos).getOrdenCarga()-1);
							//y sumarle 1 al anterior
							listAux.get(pos-1).setOrdenCarga(listAux.get(pos-1).getOrdenCarga()+1);
						}
						else
						{
							//hay que sumarle uno al que tengo
							listAux.get(pos).setOrdenCarga(listAux.get(pos).getOrdenCarga()+1);
							//y restarle 1 al siguiente
							listAux.get(pos+1).setOrdenCarga(listAux.get(pos+1).getOrdenCarga()-1);
						}
						*/
						int posi = 0;
						for (DepositoEnvio dep : envio.getDepositos())
						{
							
							if(dep.getIdDeposito()==deposito)
							{
								DepositoEnvio aux = dep;
								if(mov.equals("sube"))
								{
									if(envio.getDepositos().get(posi-1)!=null)
									{
										envio.getDepositos().get(posi-1).setOrdenCarga(envio.getDepositos().get(posi-1).getOrdenCarga()-1);
										envio.getDepositos().get(posi).setOrdenCarga(envio.getDepositos().get(posi).getOrdenCarga()+1);
									}
									
									
								}
								else
								{
									if(envio.getDepositos().get(posi+1)!=null)
									{
										envio.getDepositos().get(posi+1).setOrdenCarga(envio.getDepositos().get(posi+1).getOrdenCarga()+1);
										envio.getDepositos().get(posi).setOrdenCarga(envio.getDepositos().get(posi).getOrdenCarga()-1);
									}
									
									
								}
	
							}
													
							posi++; //obtengo la posicion
							
								
						}
						
						listAux = envio.getDepositos();
						for (DepositoEnvio de : listAux) 
						{
							System.out.println(de.getIdDeposito() + " carga " +de.getOrdenCarga());
						}
						
						Collections.sort(listAux);
						envio.setDepositos(listAux);
						session.setAttribute("envio",envio);
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
					
				
				
				
				
				return mapping.findForward("ok");
				
			}
	
	
		
		}


