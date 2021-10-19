package web.ecommerce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import dataTypes.DataDetallePedido;
import logica.Logica;
import logica.Utilidades;



public class EcommercePedidoConfirmarArt extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		
		List<DataDetallePedido> pedidos = (ArrayList<DataDetallePedido>)session.getAttribute("pedidoUpdate");
		Utilidades util = new Utilidades();
		
		Usuario usu = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(usu);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String pedido = request.getParameter("pedido");
		String dep = request.getParameter("depo");
		String art = request.getParameter("arti");
		String msj="";
				
		try{			
			String query = "update ecommerce_pedido_articulos_req set subestado=2, Confirmado=1, cantConfirmada=cantidadRequerida, CTimeStamp = CURRENT_TIMESTAMP() "
						 + "where idEmpresa="+idEmpresa+" AND idPedido="+pedido+" and idArticulo='"+art+"' and Deposito="+dep; 
			
			Logica.persistir(query);
			Logica.logPedido(Long.parseLong(pedido), usu.getNumero(), 0, "Confirmando el Articulo "+art+" del Pedido "+pedido+" y deposito "+dep, 0,idEmpresa);						
			
			msj +="-Se confirmo el Árticulo "+art+" del depósito "+dep+" <br/>";
			
			int cantidadPendienteDeConfirmacion = Logica.darCantidadAConfirmar(Long.parseLong(pedido),idEmpresa);
			
			if(cantidadPendienteDeConfirmacion==0)
				Logica.updateEcommerceEstadoFecha(Long.parseLong(pedido), 2, idEmpresa, usu.getNumero(), "CURRENT_TIMESTAMP()");
			
			}
			 catch(Exception e){
				 System.out.println("catch articulo");
				 msj="Error tratando de confirmar el Articulo "+art;
				 request.setAttribute("menError", msj);
				 return mapping.findForward("ok");
			}			
				
		request.setAttribute("menError", msj);
		List<DataDetallePedido> pedidoMod = Logica.darListaDetallePedidosEcommerce(String.valueOf(pedidos.get(0).getIdPedido()), null, null, null, null, "", null,0,null,"","",idEmpresa,"","",null,true);
		session.setAttribute("pedidoUpdate",pedidoMod);
		return mapping.findForward("ok");
	}
	
		
		
	
	
}
