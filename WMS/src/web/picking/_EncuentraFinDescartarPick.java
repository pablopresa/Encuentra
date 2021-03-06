package web.picking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.DataPicking;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataIDDescripcion;
import jsonObjects.SendMail;
import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;

public class _EncuentraFinDescartarPick extends Action 
{


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = null;
		String comentario = "";
		int pick = 0;
		try {
			session = request.getSession();
			Logica logica = new Logica();
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			
			String idPick = request.getParameter("idPick"); //picking
			
			try {
				pick = Integer.parseInt(idPick);
			} catch (Exception e) {
				request.setAttribute("menError", "No se pudo obtener el numero de picking");	
				return mapping.findForward("ok");
			}
			
			//SETEO LA REDIRECCION
			String pag = request.getParameter("pag");
			session.setAttribute("destino", pag);
					
			//CAMBIO ESTADO REPO
			logica.UpdatePickingStatus(7, pick,idEmpresa);
			
			//COMENTARIO LOG
			comentario = "Se marco el picking "+pick+ " como finalizado";
			if(pag.equals("verifica")){
				comentario += " desde desktop";
			}
			else{
				comentario += " desde colector";
			}
			uLog.registrarEventoHilo(session.getId(), comentario, pick, 112);
			
			
			List<DataPicking> noEncontrados = logica.encuentraDarNoEncontrados(pick,idEmpresa);
			
			//DEVOLUCIONES
			int idDepoWEB = util.darParametroEmpresaINT(idEmpresa,5);
			boolean devuelveStock = util.darParametroEmpresaBool(idEmpresa,56);
			if(devuelveStock) {
				EcommerceProcessOrders pro = new EcommerceProcessOrders();			
				
				Map<Long, List<DataIDDescripcion>> devoluciones = new HashMap<>();
				for(DataPicking ne: noEncontrados) {
					DataIDDescripcion data = new DataIDDescripcion(ne.getSol(),ne.getArticulo());
					if(devoluciones.get(ne.getIdPedido())==null) {
						devoluciones.put(ne.getIdPedido(), new ArrayList<>());
						devoluciones.get(ne.getIdPedido()).add(data);
					}
					else {
						devoluciones.get(ne.getIdPedido()).add(data);
					}
					pro.negarSKU(ne.getArticulo(), ne.getIdPedido(), ne.getOrigen().getId(), 
							"", ne.getSol(),idEmpresa, false, devuelveStock, uLog);
				}
				
				for (Long key : devoluciones.keySet()) {
					util.remitir(idDepoWEB, Integer.parseInt(uLog.getDeposito()), uLog.getNumero(), 
							devoluciones.get(key), key, 0, 0, 0, 
							uLog.getNombre()+" "+uLog.getApellido(), "Cancelacion de movimiento de stock", 
							idEmpresa, null, true);
				}
				
				
			}
			
			//ENVIO MAIL NO ENCONTRADOS
			if(!noEncontrados.isEmpty()){
				noEncontrados(noEncontrados, idEmpresa);
			}
		} catch (Exception e) {
			request.setAttribute("menError", "Sucedio un error queriendo finalizar el picking");
		}
		
		return mapping.findForward("ok");

		
	
	}
	
	public void noEncontrados(List<DataPicking> noEncontrados, int idEmpresa){
		try {
			String body1 = "<h2 style=\"text-align: center;\"><strong>Articulos <span style=\"color: #ff6600;\">no encontrados</span> en el picking</strong></h2> "+
						" <table style=\"height: 36px; width: 100%; border-collapse: collapse; margin-left: auto; margin-right: auto;\" border=\"1\"> "+
						" <tbody> "+
						" <tr style=\"height: 18px; background-color: #a81865;\"> "+
						" <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
						" <h3><strong><span style=\"color: #ffffff;\">Picking</span></strong></h3> "+
						" </td> "+
						" <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
						" <h3><strong><span style=\"color: #ffffff;\">Usuario</span></strong></h3> "+
						" </td> "+
						" <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
						" <h3><strong><span style=\"color: #ffffff;\">Articulo</span></strong></h3> "+
						" </td> "+
						" <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
						" <h3><strong><span style=\"color: #ffffff;\">Cantidad</span></strong></h3> "+
						" </td> "+
						" <td style=\"width: 20%; text-align: center; height: 18px;\"> "+
						" <h3><strong><span style=\"color: #ffffff;\">Distribucion</span></strong></h3> "+
						" </td> "+
						" </tr> ";
						
			String body2 = "";
			
			
			
			for (DataPicking n : noEncontrados) 
			{
				int cant= n.getSol()-n.getVerificada();
				body2+=
			
						" <tr style=\"height: 18px; border-color: #000000; text-align: center;\"> "+
						" <td style=\"width: 20%; height: 18px;\">"+n.getIdPicking()+"</td> "+
						" <td style=\"width: 20%; height: 18px;\">"+n.getUsuario().getDescripcion()+"</td> "+
						" <td style=\"width: 20%; height: 18px;\">"+n.getArticulo()+"</td> "+
						" <td style=\"width: 20%; height: 18px;\">"+cant+"</td> "+
						" <td style=\"width: 20%; height: 18px;\">"+n.getSolicitud()+"</td> "+
						" </td> "+
						" </tr> ";
			}			
		
			String body3 = " </tbody> "+
						" </table> "+
						" <p>&nbsp;</p> "+
						" <p>&nbsp;</p> "+
						" <p>&nbsp;</p> "+
						" <p>&nbsp;</p> "+
						" <p style=\"text-align: center;\">Notificacion generada automaticamente por Encuentra</p>";
			
			/*
			PropertiesHelper pH=new PropertiesHelper("MovsStock");
			pH.loadProperties();
			String mailsD = pH.getValue("mailsNotificarLOCALES");
			*/
			
			Utilidades util = new Utilidades();
			String mailsD = util.darParametroEmpresaSTR(idEmpresa, 7);
			List<String> mailDestinos = new ArrayList<>();
			String[] arregloMails = mailsD.split(",");
			for (int i = 0; i < arregloMails.length; i++) 
			{
				mailDestinos.add(arregloMails[i]);
			}
			
			SendMail sm = new SendMail("P"+noEncontrados.get(0).getIdPicking(),mailsD,"Articulos no encontrados", body1+body2+body3,
					"encuentra@200.com.uy");
			Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
			List<SendMail> mails = new ArrayList<>();
			mails.add(sm);
			api.PutColaEnvioMails(mails, idEmpresa);
			//em.enviarMailHTMLOD("encuentra@200.com.uy", mailDestinos, "Articulos no encontrados", body1+body2+body3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
