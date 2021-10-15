package web.ecommerce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.Logica;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataIDDescripcion;
import beans.Menu;
import beans.Usuario;
import beans.encuentra.Tarjeta;



public class EcommerceDarArtPedidos extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica logica = new Logica();
		int idDeposito;
		boolean hrefMail=false;
		
		int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
			//int idDeposito = Integer.parseInt(request.getParameter("idDeposito"));
			try{
			String idDe = String.valueOf(session.getAttribute("idTienda")); 
			idDeposito = Integer.parseInt(idDe);
			}
			catch(Exception e){
				idDeposito = Integer.parseInt(request.getParameter("idDeposito"));
				hrefMail=true;
				session.setAttribute("idTienda", idDeposito);
			}
			
		try{	
			List<DataArticuloEcommercePedido> articulosPedidos = logica.ArticulosPedidosEcommercePendientes(idDeposito, 0, idEmpresa);
			session.setAttribute("articulosReq", articulosPedidos);
		}
		catch (Exception e)
		{
			request.setAttribute("menError", "Ocurrio un error buscando pendientes");
			 return mapping.findForward("ok");
		}
		
		//PARA EL LOGUIN Y MENU DESDE HREF MAIL
		if(hrefMail){
		
			Usuario uLog = new Usuario();
			uLog = logica.loginEncuentra("invitado", "guest", idEmpresa);
			
			//trae la lista de reposiciones
			session.setAttribute("uLogeado", uLog);
			int idGrupo = 100;
			List<DataIDDescripcion> grupos = new ArrayList<>();
			grupos.add(new DataIDDescripcion(100,""));
			
			List<Menu> menu = logica.darMenu(grupos,false, idEmpresa);
			List<Tarjeta> tarjetas = logica.darTarjetasGuest(idGrupo, idEmpresa);
			
			session.setAttribute("tarjetas", tarjetas);
			
			
			session.setAttribute("menu", menu);
		}
		
		
		return mapping.findForward("ok");
	}
	
}
