package web.ecommerce;

import java.util.ArrayList;
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
import beans.encuentra.IPrint;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPedidoPickup;
import eCommerce_jsonObjectsII.EncuentraPedido;



public class PrintEtiquetaEC extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		IPrint print = new IPrint();
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String forw = (String) session.getAttribute("reqEc");
		session.removeAttribute("reqEc");
		
		int[] etiquetas = null;
		int cantidad=1;
		String etisS = request.getParameter("numEtisS");		
		String etisM = request.getParameter("numEtisM");		
		String etisL = request.getParameter("numEtisL");		
		String etisXL = request.getParameter("numEtisXL");	
		int cantS = 0;
		int cantM = 0;
		int cantL = 0;
		int cantXL = 0;
		DataArticuloEcommerceVerifR articuloR =  (DataArticuloEcommerceVerifR) session.getAttribute("articuloR");		
		
		cantS = util.tryParse(etisS);
		cantM = util.tryParse(etisM);
		cantL = util.tryParse(etisL);
		cantXL =util.tryParse(etisXL);
		cantidad = cantS + cantM + cantL + cantXL;
		if(cantidad==0)
		{
			cantidad = 1;
			cantM=1;
		}
		
		etiquetas= new int[]{cantS,cantM,cantL,cantXL};		
		 
		
		
		boolean salida = false;
		
		salida = print.imprimirEtiqueta(articuloR, uLog.getIdEquipo(), idEmpresa, cantidad, etiquetas);
		
		if(!salida) {
			if(articuloR.getEstadoEncuentra()==3) {
				Logica.updateEcommerceEstado(articuloR.getIdPedido(), 25,idEmpresa,uLog);
			}
			request.setAttribute("menError", "No se encontro etiqueta");
		}
		
		if(forw!=null && forw.equals("XID")) {
			List<DataPedidoPickup> pedidosPickup = Logica.darPedidosPickup(Integer.parseInt(uLog.getDeposito()),idEmpresa);
			session.setAttribute("pedidosPickup", pedidosPickup);
			return mapping.findForward(forw);	
		}
		
		return mapping.findForward("ok");	
		
	}
	
}
