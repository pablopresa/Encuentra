package web.picking;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.MovStock;
import beans.Usuario;
import beans.encuentra.DataPicking;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataIDDescripcion;

public class Resend_MovStock extends Action{

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
		
		String id = request.getParameter("id");
		
		//MovStock mov = Logica.MovStock(Integer.parseInt(id),idEmpresa);
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		MovStock mov = api.darMovStock(Integer.parseInt(id),idEmpresa);
		
		if(mov!=null && !mov.getDetalle().isEmpty()) {
			DataIDDescripcion respVisual = null;
			int idDepoWEB = util.darParametroEmpresaINT(idEmpresa,5);
			int idDepoCentral = util.darParametroEmpresaINT(idEmpresa,4);
			
			if(mov.getDestino()==idDepoWEB) {
				respVisual = api.movStock(mov, true,  idEmpresa);
				if(!respVisual.getDescripcion().equals("")){
					request.setAttribute("menError",  "DOC "+mov.getDoc()+" - "+respVisual.getDescripcion() + " <br/>");
					return mapping.findForward("ok");
				}
				else {
					EcommerceProcessOrders pro = new EcommerceProcessOrders();
					System.out.println("VERIFICANDO PEDIDOS WEB");									//VERIFICO PEDIDO WEB
					Long idPedido = new Long("0");
					try {
						idPedido = Logica.darIdPedidoDocumento(mov.getDoc(), idEmpresa).getIdLong();
					} catch (Exception e) {}
					for(DataIDDescripcion d: mov.getDetalle()) {
						pro.confirmarSKUForus(d.getDescripcion(), idDepoCentral, d.getId(),idPedido,idEmpresa,uLog);
						
						Logica.encuentraBajaArticulosOjos(d.getId(),d.getDescripcion(), "1",idEmpresa);									//BAJAR DE LA ZONA DE PICKING
						Logica.encuentraMoverOjos(idDepoWEB+"P",d.getDescripcion(),d.getId(),uLog.getNumero(),idEmpresa);					//AGREGO ARTICULO A CLASIFICADOR ECOMMERCE
						Logica.IngresarMovimientoArticulo("1",idDepoWEB+"P", d.getDescripcion(), d.getId(), uLog.getNumero(),idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA 
					}
				}
			}
			else {
				respVisual = api.movStock(mov, true, idEmpresa);
				if(respVisual.getId()==0) {
					request.setAttribute("menError", respVisual.getDescripcion());
					return mapping.findForward("ok");
				}
				else {
					int tipoComanda = 2;
					String obsTicket = "Movimiento hacia tienda "+mov.getDestino();
					if(mov.isEntrega()) {
						tipoComanda = 3;
						obsTicket = "Movimiento hacia tienda "+mov.getDestino();
					}
					ImpresionesPDF.imprimirTicketMovStock(mov.getOrigen(), mov.getDestino(), uLog.getNick(), obsTicket, 
							mov.getDetalle(),respVisual.getId()+"",tipoComanda, uLog.getIdEquipo(),idEmpresa, 1);
				}
			}
		}
		else {
			request.setAttribute("menError", "No se pudo encontrar el movimiento");
			return mapping.findForward("ok");
		}
		
		//List<MovStock> MovStock = Logica.queueMovsStock(idEmpresa);		
		//session.setAttribute("MovStock", MovStock);
		//return mapping.findForward("ok");
		request.setAttribute("monitor", "");
		return mapping.findForward("queue");	
	
	}
	
	
	
	
	
}








































