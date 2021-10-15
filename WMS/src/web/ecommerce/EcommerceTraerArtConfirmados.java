package web.ecommerce;

import java.util.List;

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
import dataTypes.DataArticuloEcommercePedido;



public class EcommerceTraerArtConfirmados extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
		try
		{
			//int idDeposito = Integer.parseInt(request.getParameter("idDeposito"));
			String idDe = String.valueOf(session.getAttribute("idTienda"));
			int idDeposito = Integer.parseInt(idDe);
			Usuario uLog = (Usuario) session.getAttribute("uLogeado");
			Utilidades util = new Utilidades();
			int idEmpresa = util.darEmpresa(uLog);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			
			List<DataArticuloEcommercePedido> articulosPedidos = Logica.ArticulosPedidosEcommercePendientes(idDeposito, 2, idEmpresa);
			session.setAttribute("articulosConf", articulosPedidos);
		}
		catch (Exception e)
		{
			request.setAttribute("menError", "Ocurrio un error buscando los pedidos confirmados");
			 return mapping.findForward("ok");
		}
		
		
		return mapping.findForward("ok");
	}
	
}
