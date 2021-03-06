package web.ecommerce;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import dataTypes.DataArticuloEcommerceVerifR;



public class EcommerceVerifAddOjo extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		int cantidad=1;
		String idOjo = request.getParameter("CUBI");
		String idPedido = request.getParameter("idPedido");
		String idArticulo = request.getParameter("idArticulo");
		DataArticuloEcommerceVerifR articuloR =  (DataArticuloEcommerceVerifR) session.getAttribute("articuloR");
		
		int deposito = Integer.parseInt(uLog.getDeposito());
		int existe = Logica.encuentraExisteUbica(idOjo, idEmpresa, deposito);
		
		if(existe==1)
		{
			/*Logica.encuentraBajaArticulosOjos(1,idArticulo, "1200P", idEmpresa);							//BAJAR DE LA ZONA DE CLASIFICACION
			Logica.encuentraMoverOjos(idOjo,idArticulo,1,uLog.getNumero(),idEmpresa);					//AGREGO ARTICULO A ESTANTERIA ECOMMERCE
			Logica.IngresarMovimientoArticulo("1200P",idOjo, idArticulo, 1,  uLog.getNumero(),idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA 
			
			Logica.encuentraMoverOjos(idOjo,idPedido,1,uLog.getNumero(),idEmpresa);					//AGREGO PEDIDO A ESTANTERIA ECOMMERCE
			Logica.IngresarMovimientoArticuloTipo("",idOjo, idPedido, 1,  uLog.getNumero(),"ADD",idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA 
			
			Logica.IngresarMovimientoArticuloTipo("1200P",idPedido, idArticulo, 1,  uLog.getNumero(),"CON",idEmpresa);	//REGISTRO LA CONSOLIDACION DEL ARTICULO CON EL PEDIDO
			*/
			Logica.encuentraBajaArticulosOjos(1,idArticulo, "1200P", idEmpresa);							//BAJAR DE LA ZONA DE CLASIFICACION
			Logica.encuentraMoverOjos(idOjo,idPedido+"_"+idArticulo,1,uLog.getNumero(),idEmpresa);					//AGREGO ARTICULO A ESTANTERIA ECOMMERCE
			//Logica.IngresarMovimientoArticulo("1200P",idOjo, idArticulo, 1,  uLog.getNumero(),idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA 
			
			//Logica.encuentraMoverOjos(idOjo,idPedido,1,uLog.getNumero(),idEmpresa);					//AGREGO PEDIDO A ESTANTERIA ECOMMERCE
			//Logica.IngresarMovimientoArticuloTipo("",idOjo, idPedido, 1,  uLog.getNumero(),"ADD",idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA 
			
			Logica.IngresarMovimientoArticuloTipo("1200P",idOjo, idPedido+"_"+idArticulo, 1,  uLog.getNumero(),"CON",idEmpresa);	//REGISTRO LA CONSOLIDACION DEL ARTICULO CON EL PEDIDO
			
			request.setAttribute("menError", "Correcto");
			return mapping.findForward("ok");
		}
		else
		{
			
			request.setAttribute("cliente", articuloR.getDescripcion());
			request.setAttribute("pedido",  articuloR.getIdPedido());
			request.setAttribute("cantidad",  1);
			request.setAttribute("articuloV",  articuloR.getIdArticulo());
			request.setAttribute("menError", "No Existe Ojo"); 
			return mapping.findForward("no");
		}
		
		
		
		
	}
	
}
