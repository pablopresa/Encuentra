package web.expedicion;

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
import dataTypes.DataIDDescripcion;



public class _InitEnvioEcommerceEncuentraSelDepo extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
	Usuario uLog = (Usuario) session.getAttribute("uLogeado");
	Utilidades util = new Utilidades();
	int idEmpresa = util.darEmpresa(uLog);
	if(idEmpresa==0)
	{
		return mapping.findForward("LOGIN");
	}
		try 
		{
			int depo = Integer.parseInt(request.getParameter("idDepo"));
			
			
			//String q = "	select idPedido,'' from ecommerce_pedido_destino where idDestino = "+depo+" and Cerrado = 0";
			
			String q = " select '-',if(ed.idDestino=700000,CONCAT('UES000',ed.tracking),ed.tracking),ed.idPedido from ecommerce_pedido_destino ed "+
						"inner join ecommerce_pedido ep on ep.idPedido=ed.idPedido "+
						"where ed.idDestino = "+depo+" and ed.Cerrado = 0 and ep.EstadoEncuentra in (3) AND ed.IdEmpresa="+idEmpresa;
			
			List<DataIDDescripcion> pedidosToSend = Logica.darListaDataIdDescripcionConsulMYSQL(q);
			
			String array = "";
			
			for (DataIDDescripcion p : pedidosToSend) 
			{
				array = array + p.getIdLong()+",";
			}
			
			array = array.substring(0,array.length()-1);
			
			
			
			session.setAttribute("depoSel", depo);
			session.setAttribute("arrayPedidosDepo", array);
			session.setAttribute("pedidosToSend", pedidosToSend);
			
		}
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			request.setAttribute("menError", "No hay envios");
			return mapping.findForward("no");

		}

		
		return mapping.findForward("ok");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
