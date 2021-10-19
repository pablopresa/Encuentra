package web.pickup;

import java.util.ArrayList;
import java.util.Arrays;
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
import main.EcommerceProcessOrders;

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
import beans.encuentra.ArticuloConteo;
import beans.encuentra.ConteoTiendas;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.Sector;
import beans.encuentra.TipoSector;

public class PickupRechazarPedido extends Action 
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

		String idArticulo = request.getParameter("idArticulo");
		List<DataArtPedidoPickup> detallePedido = (List<DataArtPedidoPickup>) session.getAttribute("detallePedido");
		try
		{	
			boolean devuelveStock = util.darParametroEmpresaBool(idEmpresa,56);
			
			EcommerceProcessOrders pro = new EcommerceProcessOrders();			
			List<DataIDDescripcion> restantes = new ArrayList<>();
			DataIDDescripcion data;
			for(DataArtPedidoPickup p: detallePedido){			
				if(p.getIdArticulo().equalsIgnoreCase(idArticulo)) {
					pro.negarSKU(p.getIdArticulo(), Long.parseLong(p.getIdPedido()), Integer.parseInt(uLog.getDeposito()), 
							"", p.getCantRequerida(),idEmpresa, true, devuelveStock, uLog);
					if(devuelveStock) {
						int depoWEB = util.darParametroEmpresaINT(idEmpresa,5);
						
						util.remitir(depoWEB, Integer.parseInt(uLog.getDeposito()), uLog.getNumero(), 
								Arrays.asList(new DataIDDescripcion[] {new DataIDDescripcion(p.getCantRequerida(),idArticulo)}), 
								Long.parseLong(p.getIdPedido()), 0, 0, 0, 
								uLog.getNombre()+" "+uLog.getApellido(), "Cancelacion de movimiento de stock", 
								idEmpresa, null, true);
					}
					else {
						data = new DataIDDescripcion(p.getCantRequerida(), 0, new Long(p.getIdPedido()), p.getIdArticulo(), "" );
						restantes.add(data);
					}
				}					
				else {
					data = new DataIDDescripcion(p.getCantRequerida(), 0, new Long(p.getIdPedido()), p.getIdArticulo(), "" );
					restantes.add(data);
				}
				
				
			}
			
			
			if(!restantes.isEmpty()) {
				int deposito = Integer.parseInt(uLog.getDeposito());
				if(!devuelveStock) {
					deposito = util.darParametroEmpresaINT(idEmpresa,4);
				}
				
				util.guardarLineasReposicion_tareas(deposito, restantes, uLog, idEmpresa);
			}
			
		}
		catch (Exception e)
		{
			log.logPedido(Long.parseLong(detallePedido.get(0).getIdPedido()), 0, 2, "error pidiendo tras rechazar", -1,idEmpresa);
		}
		
		List<DataPedidoPickup> pedidosPickup = log.darPedidosPickup(Integer.parseInt(uLog.getDeposito()),idEmpresa);
		session.setAttribute("pedidosPickup", pedidosPickup);
		return mapping.findForward("ok");
	}

}
