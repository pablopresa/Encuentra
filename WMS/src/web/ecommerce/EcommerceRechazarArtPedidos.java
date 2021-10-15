package web.ecommerce;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;



public class EcommerceRechazarArtPedidos extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		int idDep = 0;
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario usu = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(usu);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String idPedido = request.getParameter("idPedido");
		String tipo = request.getParameter("tip");
		try
		{
			
			EcommerceProcessOrders pro = new EcommerceProcessOrders();
			
			String idDepo = request.getParameter("idDepo");
			String idArticulo = request.getParameter("idArticulo");
			String motivo = request.getParameter("motivo");
			String cantidad = request.getParameter("cantidad");
			
			
			idDep = Integer.parseInt(idDepo);

			
			pro.negarSKU(idArticulo, Long.parseLong(idPedido), Integer.parseInt(idDepo), motivo, Integer.parseInt(cantidad),idEmpresa,
					false,false,usu);
			System.out.println("");
		}
		catch (Exception e)
		{
			Logica.logPedido(Long.parseLong(idPedido), 0, 2, "error pidiendo tras rechazar", -1,idEmpresa);
		}
		
		/**if(idDep!=0)
		{
			List<DataArticuloEcommercePedido> articulosPedidos = Logica.darArticulosPedidosEcommerce(idDep);
			session.setAttribute("articulosReq", articulosPedidos);
		}
		
		
		return mapping.findForward("ok");**/
		try{
			if (tipo.equals("pend")){
				return mapping.findForward("volverPendientes");
			}
			else{
				return mapping.findForward("volverImpresos");
			}
		}
		catch(Exception e)
		{
			request.setAttribute("menError", "Ocurrio un error redireccionando");
			if (tipo.equals("pend")){
				return mapping.findForward("volverPendientes");
			}
			else{
				return mapping.findForward("volverImpresos");
			}
		}
		
	}
	
}
