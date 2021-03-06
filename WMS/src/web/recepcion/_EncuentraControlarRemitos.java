package web.recepcion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.MovStock;
import beans.Usuario;
import beans.encuentra.Remito;
import beans.encuentra.RemitoLinea;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;
import jsonObjects.SendMail;
import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;

public class _EncuentraControlarRemitos extends Action 
{
	@Override
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
		
		String rspVisual = "";
		List<Remito> remitosIn =  (List<Remito>) session.getAttribute("remitosIn");
		if(remitosIn==null || remitosIn.isEmpty()) {
			uLog.registrarEventoMin(session.getId(), "ERROR: No se encontraron remitos");
			return mapping.findForward("LOGIN");
		}
		
		String recontrolando = request.getParameter("Recontrola");
		boolean recontrola = false;
		
		List<Remito> remitosNO_Mail = new ArrayList<>();
		List<Remito> remitosWEBNO =  new ArrayList<>();
		
		if(recontrolando!=null && !recontrolando.equals(""))
		{
			recontrola = true;
			remitosWEBNO = (List<Remito>) session.getAttribute("remitosWEBNO");
		}
		
		List<Remito> remitosNO =  new ArrayList<>();
		
		boolean productivo = l.darIntegracionProductiva(2,uLog.getIdEmpresa());
		
		
		List<DataIDDescripcion> articulosToOjos = null;
		
		int idDepositoWEb = util.darParametroEmpresaINT(idEmpresa, 5);
		
		for (Remito r : remitosIn) 
		{
			int sumaVerificadas = 0;
			boolean todasOK=true;
			for (RemitoLinea li : r.getLineas()) 
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
						remitosNO_Mail.add(r);
					}
					
					
					/********************/
					//actualizo todasOK//
					/********************/
					todasOK = true;
				}
				else
				{
					remitosNO.add(r);
				}
				
			}
			
			boolean grabo = true;
			if(todasOK  && sumaVerificadas>0)
			{
				List<DataIDDescripcion> lista = new ArrayList<>();
				articulosToOjos = new ArrayList<>();
				for (RemitoLinea li : r.getLineas()) 
				{
					if(li.getCantidadVerificada()>0)
					{
						lista.add(new DataIDDescripcion(li.getCantidadVerificada(), li.getIdArticulo()));
						articulosToOjos.add(new DataIDDescripcion(li.getCantidadVerificada(), li.getIdArticulo()));
						articulosToOjos.get(articulosToOjos.size()-1).setIdB(r.getIdDestino());
					}
				}			
				if(productivo)
				{					
					MovStock m = new MovStock();
					m.setOrigen(r.getIdOrigen());
					m.setDestino(r.getIdDestino());
					m.setIdUsuario(uLog.getNumero());
					m.setDetalle(lista);
					m.setOrigenDoc(new Long(r.getIdPedidoWEB()));
					m.setDocSolicitud(r.getEntregaAfecta());
					m.setDoc(r.getNumeroDoc());
					m.setRazon(0);
					m.setObservacion("Confirmacion desde encuentra por "+uLog.getNombre()+" "+uLog.getApellido());
					m.setUsuario(uLog.getNombre()+" "+uLog.getApellido());
					
					Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
					grabo = api.confirmarTransferencia(m, idEmpresa);
					
				}
			}
			
			if(todasOK && r.getIdDestino()==idDepositoWEb && grabo)//si van al 1200, confirmo el envio
			{
				if(r.getIdPedidoWEB()!=0)
				{
					EcommerceProcessOrders pro = new EcommerceProcessOrders();
					for (RemitoLinea li : r.getLineas()) 
					{
						pro.confirmarSKUxDistrib(li.getIdArticulo(), r.getIdOrigen(), li.getCantidad(), r.getEntregaAfecta(), uLog.getIdEmpresa(),uLog, new Long(r.getIdPedidoWEB()));
					}
				}
				else
				{
					//va para la web pero es un mov sin distribucion
					remitosWEBNO.add(r);
					
				}
				
			}
			
			if(!grabo) {
				rspVisual += "ERP no dejo grabar la recepcion del Doc N? "+r.getNumeroDoc()+" \n\r";
			}
			else {
				if(todasOK && sumaVerificadas>0) {
					List<DataIDDescripcion> articulosCant = l.EncuentraSortearListaDICant(articulosToOjos);
					for (DataIDDescripcion d : articulosCant)
					{
						if(d.getIdB()==idDepositoWEb){
							l.encuentraUpdateOjos(idDepositoWEb+"P", d.getDescripcion(),d.getId(), false, uLog.getNumero(),false,"RRE",idEmpresa);
						}
						else{
							l.encuentraUpdateOjos("0", d.getDescripcion(),d.getId(), false, uLog.getNumero(),false,"RRE",idEmpresa);
						}
						
					}
				}				
			}
			
			
		}//fin for remitos
		
		session.setAttribute("remitosIn", null);
		
		if(!rspVisual.equals("")) {
			request.setAttribute("menError", rspVisual);
			return mapping.findForward("ok");
		}
		
		//guardo ojos tienen articulos de los recepcionados buenos
		/*List<DataIDDescripcion> articulosCant = l.EncuentraSortearListaDICant(articulosToOjos);
		for (DataIDDescripcion d : articulosCant)
		{
			if(d.getIdB()==1200){
				l.encuentraUpdateOjos("1200P", d.getDescripcion(),d.getId(), false, uLog.getNumero(),false,"RRE",idEmpresa);
			}
			else{
				l.encuentraUpdateOjos("0", d.getDescripcion(),d.getId(), false, uLog.getNumero(),false,"RRE",idEmpresa);
			}
			
		}*/
		
		if(!remitosNO.isEmpty())//hay faltantes /*si recontrola aca no entra
		{
			menError = "ATENCION, debe confirmar la recepcion de los documentos verificados **CON FALTANTES**";
			request.setAttribute("menError", menError);
			request.setAttribute("recontrola", "1");
			session.setAttribute("remitosIn", remitosNO);
			if(!remitosWEBNO.isEmpty())
			{
				session.setAttribute("remitosWEBNO", remitosWEBNO);
			}
			
			return mapping.findForward("Faltantes");
			
		}
		else //no hay faltantes /*o esta recontrolando
		{
			if(recontrola && !remitosNO_Mail.isEmpty())
			{
				menError = "Recepcion de remitos registrada exitosamente**CON FALTANTES**";
				
				
				
				
				
				PropertiesHelper pH=new PropertiesHelper("MovsStock");
				pH.loadProperties();
				String mailsD = pH.getValue("mailsNotificarRecepRemitoDif");
				List<SendMail> mails = new ArrayList<>();
				
				for (Remito r : remitosNO_Mail) 
				{
					String body1 = "<h2 style=\"text-align: center;\"><strong>Articulos <span style=\"color: #ff6600;\">Confirmados en Documento pero no vinieron </span> en el remito"+r.getNumeroDoc()+" desde "+r.getIdOrigen()+"</strong></h2> "+
							"	<table style=\"height: 36px; width: 100%; border-collapse: collapse; margin-left: auto; margin-right: auto;\" border=\"1\"> "+
							"	  <tbody> "+
							"	    <tr style=\"height: 18px; background-color: #a81865;\"> "+
							"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
							"	        <h3><strong><span style=\"color: #ffffff;\">Remito</span></strong></h3> "+
							"	      </td> "+
							"	      <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
							"	        <h3><strong><span style=\"color: #ffffff;\">Fecha</span></strong></h3> "+
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
									"	      <td style=\"width: 20%; height: 18px;\">"+r.getFecha()+"</td> "+
									"	      <td style=\"width: 20%; height: 18px;\">"+r.getIdOrigen()+"</td> "+
									"	      <td style=\"width: 20%; height: 18px;\">"+r.getIdDestino()+"</td> "+
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
					
					SendMail sm = new SendMail("REMITO"+r.getNumeroDoc()+"_"+r.getIdOrigen(),mailsD,"***Remito recepcionado con diferencias***", body1+body2+body3,
							"encuentra@200.com.uy");
					
					// Para no enviar sin lineas
					try {
						if(!r.getLineas().isEmpty())
						{
							mails.add(sm);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
				
				if(!mails.isEmpty()) {
					Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
					api.PutColaEnvioMails(mails, idEmpresa);
				}				
				 
				request.setAttribute("menError", menError);
				return mapping.findForward("ok");
			}
			
			
			
			if(!remitosWEBNO.isEmpty())//no tenia todas las distribuciones
			{
				session.setAttribute("remitosWEBNO", remitosWEBNO);
				menError = "Recepcion en visual correcta.No pudimos ubicar todos los pedidos WEB de los remitos recepcionados, si no los asigna no podra clasificar pedidos de estos articulos";
				request.setAttribute("menError", menError);
				return mapping.findForward("remitosWEBNO");
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
