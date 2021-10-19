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


import beans.Estado;

import beans.Sucursal;
import beans.Usuario;
import beans.encuentra.DepositoEnvio;
import beans.encuentra.DocumentoEnvio;
import beans.encuentra.Envio;
import beans.encuentra.Transporte;

public class _ImprimirEnviosIndividuales extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
 Logica Logica = new Logica();
							
				try
				{
					int idEnvio = Integer.parseInt(request.getParameter("idEnvio"));
					int idDepo = Integer.parseInt(request.getParameter("idDepo"));
					
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
						
					session.setAttribute("envio", envio);
					request.setAttribute("idDepo", idDepo);
				}
				catch (Exception e)
				{
					String men = "Sucedio un error en el proceso";
					request.setAttribute("menError", men);
				}
				
				return mapping.findForward("ok");
				
			}
	
	
		
		}


