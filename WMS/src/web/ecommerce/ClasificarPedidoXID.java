package web.ecommerce;

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
import beans.ProcessEcommerce;
import beans.Tareas;
import beans.Usuario;
import beans.encuentra.DataArticulo;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class ClasificarPedidoXID extends Action 
{


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession();
		
		Logica log = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		


		try{		
			boolean completo=true;
			boolean pertenece= false;
			
			String idPedido = request.getParameter("idPedido");
			Long idPedidoP = new Long(idPedido);
			List<DataArtPedidoPickup> detallePedido = new ArrayList<>();
			
			detallePedido = log.darArticulosPedidoEcommercePickup(idPedidoP,false,idEmpresa);
			request.setAttribute("idPedido", idPedido);
			
				ServletContext context = request.getSession().getServletContext();
				Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
				
				String barra = request.getParameter("barra");
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
				try {
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
				} catch (Exception e) {
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
				}
				
				if(articulo==null || articulo.equals(""))
				{
					DataArticulo artReal = log.encuentraCodArticulo(barra, idEmpresa);
					if(artReal!=null)
					{
						articulo = artReal.getId();
					}
				}
				
				
				
				int cantidad = 1;
				try {
					cantidad = Integer.parseInt(request.getParameter("qty"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if(articulo!=null && !articulo.equals("")){
					for(DataArtPedidoPickup p: detallePedido){
						//Si el articulo pertenece al pedido y cantidad pendiente es mayor a 0
						if(p.getIdArticulo().toUpperCase().equals(articulo.toUpperCase())){
							if (p.getCant() - cantidad>=0){
								p.setCant(p.getCant()-cantidad);
								pertenece=true;
								log.updatePickTiendas(idPedidoP,p.getIdArticulo(),idEmpresa,false,1,cantidad);//HAY QUE TRAER EL DEPOSITO !!!!!!!!!!!!!!!!!
								uLog.registrarEventoMin(session.getId(), "Pickea "+p.getIdArticulo()+" del "+idPedido);
								break;
							}
						}
					}
				}
				
				if(!pertenece){
					request.setAttribute("menError", "Este articulo no pertenece al pedido, o no se necesitan tantas unidades");
					session.setAttribute("detallePedido", detallePedido);
					return mapping.findForward("ok");
				}
				
				request.setAttribute("detallePedido", detallePedido);
										
				for(DataArtPedidoPickup p: detallePedido){
					//Si la cantidad total pendiente es mayor a 0
					if (p.getCant()>0){
							completo=false;
							break;
					}
				}
				
			int depEc = util.darParametroEmpresaINT(idEmpresa,5);
			DataArticuloEcommerceVerifR articuloR = log.darArticuloEcommerceReqReclasifica(idPedidoP,0, idEmpresa);
			log.encuentraBajaArticulosOjos(1,articuloR.getIdArticulo(), depEc+"P", idEmpresa);	//BAJO DE ZONA DE CLASIFICACION ECOMMERCE
			log.IngresarMovimientoArticuloTipo(depEc+"P",articuloR.getIdPedido()+"", articuloR.getIdArticulo(), 1,  uLog.getNumero(),"CON",idEmpresa);	
				
			if(completo){
				
				ProcessEcommerce process = new ProcessEcommerce();
				log.updateEcommerceEstado(new Long(idPedido), 3,idEmpresa,uLog);
				process.cambioEstadoAPreparado(log, idEmpresa, articuloR);
				uLog.registrarEventoMin(session.getId(), "Pedido "+detallePedido.get(0).getIdPedido()+" preparado (Pickup)");
				
				//ENVIAR CAMBIO DE ESTADO A EL CANAL PERTENECIENTE
				
				//FACTURACION AUTOMATICA
				util.facturacionAutomatica(articuloR.getIdPedido(),uLog.getIdEquipo(),idEmpresa,log);
				
				IPrint print = new IPrint();
				
				
				process.setearSession(session, articuloR);
				
				boolean etiqueta = false;
				boolean paramCantEtis = util.darParametroEmpresaBool(idEmpresa, 11);
				session.setAttribute("paramCantEtis", paramCantEtis);	
				
				if(paramCantEtis) {						//CONTROL PARA CANTIDAD DE ETIQUETAS
					etiqueta = true;
				}
				else {
					etiqueta = print.imprimirEtiqueta(articuloR, uLog.getIdEquipo(),idEmpresa,1,null);
				}
				
				session.setAttribute("reqEc", "XID");
				
				if (etiqueta) {
					log.encuentraMoverOjos(articuloR.getIdDestino()+"P",articuloR.getIdPedido()+"",1,uLog.getNumero(),idEmpresa);					//AGREGO ARTICULO A EXPEDICION
					log.IngresarMovimientoArticuloTipo("",articuloR.getIdDestino()+"P", articuloR.getIdPedido()+"", 1, uLog.getNumero(),"ADD",idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA
					
					return mapping.findForward("fin");
				}
				else {
					return mapping.findForward("finOJO");
				}
				
				
				/*List<DataPedidoPickup> pedidosPickup = log.darPedidosPickup(Integer.parseInt(uLog.getDeposito()),idEmpresa);
				session.setAttribute("pedidosPickup", pedidosPickup);
				return mapping.findForward("completo");*/
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
