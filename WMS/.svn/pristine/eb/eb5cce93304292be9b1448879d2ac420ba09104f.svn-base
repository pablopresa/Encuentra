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



public class _InitEnvioEcommerceEncuentra extends Action 
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
			
			
			//List<DataIDDescripcion> deposToSend = Logica.darListaDataIdDescripcionConsulMYSQL("select distinct DE.idDeposito, CONCAT(DE.Nombre, ' - (',SUM(1), ')') from ecommerce_pedido_destino EPD inner join depositos DE ON DE.idDeposito = EPD.idDestino WHERE EPD.Cerrado=0 group by EPD.idDestino");
			String query = "select distinct DE.idDeposito, CONCAT(DE.Nombre, ' - (',SUM(1), ')') from ecommerce_pedido_destino EPD "+ 
					"inner join depositos DE ON DE.idDeposito = EPD.idDestino AND DE.IdEmpresa=EPD.IdEmpresa "+
					"inner join ecommerce_pedido p on p.idPedido=EPD.idPedido AND p.IdEmpresa=EPD.IdEmpresa "+
					"WHERE EPD.Cerrado=0 and p.EstadoEncuentra in (3) AND EPD.IdEmpresa="+idEmpresa+" group by EPD.idDestino "; 
			
			List<DataIDDescripcion> deposToSend = Logica.darListaDataIdDescripcionConsulMYSQL(query);
						
			session.setAttribute("depoEnvio", deposToSend);
			
			

		}
		catch (Exception e) 
		{
			System.out.println("llegamos al catch");
			session.setAttribute("mensaje", e.getMessage());
			return mapping.findForward("ok");

		}

		
		return mapping.findForward("ok");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
