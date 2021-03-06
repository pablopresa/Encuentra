package web.store;

import java.util.ArrayList;
import java.util.Calendar;
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
import helper.PropertiesHelper;
import jsonObjects.SendMail;
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

import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import clientesVisual_Store.Std.clienteWSVS.WSCommunicate;

public class _EncuentraControlarRemitos extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		String menError = "";
		
		HttpSession session = request.getSession();
		Logica l = new Logica();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		List<RecepcionExpedicion> recepcionablesSEL = (List<RecepcionExpedicion>) session.getAttribute("recepcionablesS");
		
		String recontrolando = request.getParameter("Recontrola");
		boolean recontrola = false;
		
		List<DocumentoEnvio> remitosNO_Mail = new ArrayList<>();
		
		
		if(recontrolando!=null && !recontrolando.equals(""))
		{
			recontrola = true;
			
		}	
		
		boolean productivo = true;
		
		List<DocumentoEnvio> remitosNO =  new ArrayList<>();
		
		
		for (RecepcionExpedicion re : recepcionablesSEL) 
		{
			List<DocumentoEnvio> remitosNOExp =  new ArrayList<>();
			boolean todoOK = true;
			for (DocumentoEnvio d : re.getDocumentos()) 
			{
				int sumaVerificadas = 0;
				boolean todasOK=true;
				for (RemitoLinea li : d.getLineas()) 
				{
					sumaVerificadas+=li.getCantidadVerificada();
					if(li.getCantidadVerificada()!=li.getCantidad())
					{
						todasOK = false;
					}
				}
				if(!todasOK)
				{
					if(recontrola)//esta re controlando el documento, entonces aunque haya diferencias hay que recepcionar igual.
					{
						if(sumaVerificadas>0)
						{
							remitosNO_Mail.add(d);
						}
						todasOK = true;
					}
					else
					{
						remitosNO.add(d);
						remitosNOExp.add(d);
					}
				}
				if(todasOK  && sumaVerificadas>0)
				{
					List<DataIDDescripcion> lista = new ArrayList<>();
					for (RemitoLinea li : d.getLineas()) 
					{
						if(li.getCantidadVerificada()>0)
						{
							lista.add(new DataIDDescripcion(li.getCantidadVerificada(), li.getIdArticulo()));
							//articulosToOjos.add(new DataIDDescripcion(li.getCantidadVerificada(), li.getIdArticulo()));
						}
					}
					if(productivo)
					{
						/*************************************/
						/*Confirmar las TRE en Visual*/
						WSCommunicate cl = new WSCommunicate();
						int tienda = Integer.parseInt(uLog.getDeposito());
						int idEquipo = 2000+tienda;
						
						boolean ok = cl.ConfirmarTransferenciaTienda(d.getDepositoO().getId(), d.getDepositoD().getId(), d.getNumeroDoc(), lista, "Recibido por Encuentra", (short)Integer.parseInt(uLog.getDeposito()),(short) idEquipo, (short)tienda, (long)uLog.getNumero());
						
						if(ok)
						{
							l.actualizarDocRecepcionado(re.getIdEnvio(),d.getNumeroDoc(),idEmpresa);
							l.registrarRecepcionDoc(uLog.getNumero(),d.getNumeroDoc(),"",idEmpresa);
						}
						else
						{
							todoOK = false;
						}
						
						
						
						
						
						
					}
				}
				
			}//fin for remitos
			
			
			if(todoOK && remitosNOExp.isEmpty())
			{
				//registrar la recepcion de la expedicion en OK
				l.confirmarRecepcionExpedicion(re.getIdEnvio(), Integer.parseInt(uLog.getDeposito()),idEmpresa);
			}
			
		}
		
		
		//guardo ojos tienen articulos de los recepcionados buenos
		
		
		if(!remitosNO.isEmpty())//hay faltantes /*si recontrola aca no entra
		{
			menError = "ATENCION, debe confirmar la recepcion de los documentos verificados **CON FALTANTES**";
			request.setAttribute("menError", menError);
			request.setAttribute("recontrola", "1");
			
			
			
			session.setAttribute("recepcionablesS", recepcionablesSEL);
			
			
			return mapping.findForward("Faltantes");
			
		}
		else //no hay faltantes /*o esta recontrolando
		{
			if(recontrola && !remitosNO_Mail.isEmpty())
			{
				menError = "Recepcion de remitos registrada exitosamente**CON FALTANTES**";
				
				/*
				PropertiesHelper pH=new PropertiesHelper("MovsStock");
				pH.loadProperties();
				String mailsD = pH.getValue("mailsNotificarRecepRemitoDif");
				*/
				
				List<SendMail> mails = new ArrayList<>();
				
				String mailsD = util.darParametroEmpresaSTR(idEmpresa, 8);
				
				for (DocumentoEnvio r : remitosNO_Mail) 
				{
					String body1 = "<h2 style=\"text-align: center;\"><strong>Articulos <span style=\"color: #ff6600;\">Confirmados en Documento pero no vinieron </span> en el remito"+r.getNumeroDoc()+" desde "+r.getDepositoO().getId()+"</strong></h2> "+
							"	<table style=\"height: 36px; width: 100%; border-collapse: collapse; margin-left: auto; margin-right: auto;\" border=\"1\"> "+
							"	  <tbody> "+
							"	    <tr style=\"height: 18px; background-color: #a81865;\"> "+
							"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
							"	        <h3><strong><span style=\"color: #ffffff;\">Remito</span></strong></h3> "+
							"	      </td> "+
							"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
							"	        <h3><strong><span style=\"color: #ffffff;\">Origen</span></strong></h3> "+
							"	      </td> "+
							"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
							"	        <h3><strong><span style=\"color: #ffffff;\">Destino</span></strong></h3> "+
							"	      </td> "+
							"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
							"	        <h3><strong><span style=\"color: #ffffff;\">Articulo</span></strong></h3> "+
							"	      </td> "+
							"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
							"	        <h3><strong><span style=\"color: #ffffff;\">Cantidad</span></strong></h3> "+
							"	      </td> "+
							"	    </tr> ";
								
					String body2 = "";
					
					
					
					for (RemitoLinea rl : r.getLineas()) 
					{
						int cant= rl.getCantidad()-rl.getCantidadVerificada();
						if(cant>0)
						{
							body2+=
									
									"	    <tr style=\"height: 18px; border-color: #000000; text-align: center;\"> "+
									"	      <td style=\"width: 20%; height: 18px;\">"+r.getNumeroDoc()+"</td> "+
									"	      <td style=\"width: 20%; height: 18px;\">"+r.getDepositoO().getId()+"</td> "+
									"	      <td style=\"width: 20%; height: 18px;\">"+r.getDepositoD().getId()+"</td> "+
									"	      <td style=\"width: 20%; height: 18px;\">"+rl.getIdArticulo()+"</td> "+
									"	      <td style=\"width: 20%; height: 18px;\">"+cant+"</td> "+
									"	      </td> "+
									"	    </tr> ";
							
						}
						
					}			
				
					String body3 = "	  </tbody> "+
								"	</table> "+
								"	<p>&nbsp;</p> "+
								"	<p>&nbsp;</p> "+
								"	<p>&nbsp;</p> "+
								"	<p>&nbsp;</p> "+
								"	<p style=\"text-align: center;\">Notificacion generada automaticamente por Encuentra</p>";
					
					List<String> mailDestinos = new ArrayList<>();
					String[] arregloMails = mailsD.split(",");
					for (int i = 0; i < arregloMails.length; i++) 
					{
						mailDestinos.add(arregloMails[i]);
					}
					
					SendMail sm = new SendMail("REMITO"+r.getNumeroDoc()+"_"+r.getDepositoO().getId(),mailsD,"***Remito recepcionado con diferencias***", body1+body2+body3,
							"encuentra@200.com.uy");
					
					// Para no enviar sin lineas
					if(r.getLineas()!=null)
					{
						mails.add(sm);
					}
					
				}
				
				Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
				api.PutColaEnvioMails(mails, idEmpresa);
				
				
				 
				request.setAttribute("menError", menError);
				return mapping.findForward("ok");
			}
			else
			{
				menError = "Recepcion de remitos registrada exitosamente";
				request.setAttribute("menError", menError);
				return mapping.findForward("ok");
			}
			
		}
	}

}
