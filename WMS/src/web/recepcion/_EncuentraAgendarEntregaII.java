package web.recepcion;

import helper.PropertiesHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

public class _EncuentraAgendarEntregaII extends Action 
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
				
				DataRecepcion recepcion = (DataRecepcion) session.getAttribute("recepcion");
				
				
				String transporte = request.getParameter("trans");
				if(idEmpresa==2)
				{
					/*el transporte es el folio en FORUS*/
					recepcion.getProveedor().setDescripcion(recepcion.getProveedor().getDescripcion()+" "+transporte);
					
					
					for (DataOC o : recepcion.getOrdenes()) 
					{
						o.setFolio(transporte);
					}
				}
				
				
				String fecha = request.getParameter("fini");
				
				
				
				String fechaSQL = "";
				String fechaVis = "";
				try
				{
					String[]fech = fecha.split(" ");
					String[]ddmmyyyy= fech[0].split("-");
					String dd = ddmmyyyy[2];
					String mm = ddmmyyyy[1];
					String yyyy = ddmmyyyy[0];
					fechaSQL = yyyy+"-"+mm+"-"+dd + " "+fech[1];
					fechaVis = dd+"/"+mm+"/"+yyyy+" "+fech[1];
				}
				catch(Exception e)
				{
					String error = "";
					error = "Por favor seleccione una fecha valida";
					request.setAttribute("menError", error);
					return mapping.findForward("no");
				}
				
				DataRecepcion otra =  Logica.darAgendada(fechaSQL,idEmpresa);
				if(otra==null)
				{
					recepcion.setAgenda(fechaVis);
					recepcion.setFechaSQL(fechaSQL);
					recepcion.setVehiculo(transporte);
					session.setAttribute("recepcion", recepcion);
					
					
					return mapping.findForward("ok");
				
				
				}
				else if(otra.isImporta())
				{
					String error = "";
					error = "Existe una importaci?n ya agendada a esa hora Por favor seleccione otra fecha/Hora";
					request.setAttribute("menError", error);
					return mapping.findForward("no");
				}
				else
				{
					DataIDDescripcion reagenda = new DataIDDescripcion(otra.getId(), otra.getProveedor().getDescripcion());
					recepcion.setIdAgendaNomProvReagenda(reagenda);
					String error = "";
					error = "ATENCION: Existe una recepci?n ya agendada a esa hora, Si continua se notificar? a COMPRAS para que re-agende la misma";
					PropertiesHelper helper = new PropertiesHelper("mail");
					String mailOrigen = helper.getValue("IMPORTACIONES");
					String mailsDestino = helper.getValue("COMPRAS");
					
					String [] arreglo = mailsDestino.split(";");
					
					List<String> mailDestinos =Arrays.asList(arreglo);
					String asunto="";
					String cuerpo="";
					
					EnviaMail.enviarMailHTMLOD(mailOrigen, mailDestinos, asunto, cuerpo,idEmpresa);
					request.setAttribute("menError", error);
				}
				
				recepcion.setAgenda(fechaVis);
				recepcion.setFechaSQL(fechaSQL);
				recepcion.setVehiculo(transporte);
				
				
				
				session.setAttribute("recepcion", recepcion);
				
				
				return mapping.findForward("ok");
				
			}
	
	
		
		}


