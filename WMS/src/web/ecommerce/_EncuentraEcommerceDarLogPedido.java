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
import dataTypes.DataEcommerce_LogPedido;

public class _EncuentraEcommerceDarLogPedido extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
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
		
		
		String levelIN = request.getParameter("level");
		String level = "";
		
		String idPedidoSTR = request.getParameter("idPedido");
		
		
		String forward = "";
		String min = request.getParameter("min");
		
		if(min!=null)
		{
			forward = "min";
		}
		else
		{
			forward="ok";
		}
			
		
		
		
		String idPedido = "";
		if(idPedidoSTR != null && !idPedidoSTR.equals(""))
		{
			try
			{
				Long numero = Long.parseLong(idPedidoSTR);
				idPedido = String.valueOf(numero);
			}
			catch(NumberFormatException e)
			{
				String error = "";
				error = "Por favor un número de pedido valido";
				request.setAttribute("menError", error);
				return mapping.findForward(forward);
			}
			catch (Exception e) 
			{
				String error = "";
				error = "error desconocido";
				request.setAttribute("menError", error);
				return mapping.findForward(forward);
			
			}
		}
		
		
		String fecha = request.getParameter("fini");
		
		String fechaSQL = "";
		
		if(fecha!=null && !fecha.equals(""))
		{
			//String fechaVis = "";
			try
			{
				String[]fech = fecha.split(" ");
				String[]ddmmyyyy= fech[0].split("/");
				String dd = ddmmyyyy[0];
				String mm = ddmmyyyy[1];
				String yyyy = ddmmyyyy[2];
				fechaSQL = yyyy+"-"+mm+"-"+dd + " "+fech[1];
				//fechaVis = dd+"/"+mm+"/"+yyyy+" "+fech[1];
			}
			catch(Exception e)
			{
				String error = "";
				error = "Por favor seleccione una fecha valida";
				request.setAttribute("menError", error);
				return mapping.findForward(forward);
			}
		}
		
		String req = request.getQueryString();
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
	       if (ipAddress == null) {  
	         ipAddress = request.getRemoteAddr();  
	   }
		
		
		try
		{
			String[] values = req.split("&");
			//deposito=26&deposito=41&Submit=Filtrar
			System.out.println(req + " desde "+ipAddress);
			
			if(levelIN!=null && !levelIN.equals(""))
			{
				level = buscarFiltro("level", values);
			}
			
		}
		catch(Exception e)
		{
			
		}
		
		List<DataEcommerce_LogPedido> retorno = Logica.darLogPedidoEcommerce(fechaSQL,idPedido,level, idEmpresa);
		session.setAttribute("listaEcommerceLogs", retorno);
		return mapping.findForward(forward);
		
		
		
		
	
	
	}
	
	public String buscarFiltro (String buscado, String[]values)
	{
		 String retorno= "";
		 System.out.println(values);
		 for (int i = 0; i < values.length; i++) 
		 {
			 if(values[i].contains(buscado))
			 {
				 try
				 {
					 String valor = values[i].split("=")[1];
					 retorno+=valor+",";
				 }
				 catch (Exception e)
				 {
					 
				 }
				 
			 }
		 }
		
		 if(!retorno.equals(""))
		 {
			 return retorno.substring(0, retorno.length()-1);
		 }
		 return retorno;
	}
	
	
	
}








































