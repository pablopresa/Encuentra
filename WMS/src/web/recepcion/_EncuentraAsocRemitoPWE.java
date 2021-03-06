package web.recepcion;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import logica.Utilidades;
import main.EcommerceProcessOrders;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;

import beans.encuentra.Remito;
import beans.encuentra.RemitoLinea;



public class _EncuentraAsocRemitoPWE extends Action 
{
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		String menError = "";
		
		HttpSession session = request.getSession();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		List<Remito> remitosWEB = (List<Remito>) session.getAttribute("remitosWEBNO");
		
		
		List<Remito> remitosNO =  new ArrayList<>();
		
		
		
		for (Remito r : remitosWEB) 
		{	
			if(r.getIdDestino()==1200)//si van al 1200, confirmo el envio
			{
				
				Long pedidoWEB = util.tryParseL(request.getParameter(""+r.getNumeroDoc()));
				
				if(pedidoWEB!=0)
				{
					EcommerceProcessOrders pro = new EcommerceProcessOrders();
					for (RemitoLinea li : r.getLineas()) 
					{
						pro.confirmarSKUForus(li.getIdArticulo(), r.getIdOrigen(), li.getCantidad(), pedidoWEB, uLog.getIdEmpresa(),uLog);
					}
				}
				else
				{
					remitosNO.add(r);
				}			
				
			}			
			
		}//fin for remitos
		session.setAttribute("remitosIn", null);		
		
		if(!remitosNO.isEmpty())
		{
			session.setAttribute("remitosWEBNO", remitosNO);
			menError = "No todos los remitos se asociaron correctamente";
		}
		else
		{
			menError = "asociacion de remitos registrada exitosamente";
			session.setAttribute("remitosWEBNO", null);
		}
		
		request.setAttribute("menError", menError);
		return mapping.findForward("ok");
		
		
	
	}
	
	

}
