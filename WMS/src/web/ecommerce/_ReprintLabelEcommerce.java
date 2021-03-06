package web.ecommerce;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.IPrint;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataArticuloEcommerceVerifR;
import logica.Logica;
import logica.Utilidades;

public class _ReprintLabelEcommerce extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica logica = new Logica();
		Utilidades util = new Utilidades();
		IPrint print = new IPrint();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String req = request.getParameter("req");
		String idPedido = request.getParameter("idPedido");
		Long pedidoL = Long.parseLong("0");
		try {
			pedidoL = Long.parseLong(idPedido);
		} catch (Exception e) {
			request.setAttribute("menError", "Debe ingresar un numero de pedido valido");
			return mapping.findForward(req);
		}
		
		
		int idDepoWEB = util.darParametroEmpresaINT(idEmpresa,5);
		
		DataArticuloEcommerceVerifR articuloR = logica.darArticuloEcommerceReqReclasifica(pedidoL,0, idEmpresa);
		if(articuloR == null) {
			request.setAttribute("menError", "ATENCION: no se encontro el pedido solicitado");
			return mapping.findForward(req);
		}
		
		if(articuloR.getEstadoEncuentra()<3) {
			request.setAttribute("menError", "ATENCION: pedido sin clasificar");
			return mapping.findForward(req);
		}
		
		logica.logPedido(pedidoL,0,0,"Tratando de reimprimir etiqueta",0,idEmpresa);
		
		//boolean etiqueta = imprimirEtiqueta(articuloR, idEmpresa, uLog, session);
		
		boolean etiqueta = print.imprimirEtiqueta(articuloR, uLog.getIdEquipo(),idEmpresa,1,null);
		
		if(etiqueta)
		{ 
			//int est = 0;
			//est=(logica.darDataIdDescripcion("select EstadoEncuentra,'' from ecommerce_pedido where idpedido= "+articuloR.getIdPedido())).getId();
			if(articuloR.getEstadoEncuentra()==25) {
				logica.updateEcommerceEstado(articuloR.getIdPedido(), 3,idEmpresa,uLog);
				
				if(articuloR.getTotalPedido()>1) {
					//logica.vaciarOjoEcommerce(articuloR.getIdPedido(),idEmpresa,false);
					logica.vaciarOjoEcommerce_pedido(articuloR.getIdPedido(), idEmpresa);
				}
				
				logica.encuentraMoverOjos(articuloR.getIdDestino()+"P",articuloR.getIdPedido()+"",1,uLog.getNumero(),idEmpresa);					//AGREGO ARTICULO A EXPEDICION
				logica.IngresarMovimientoArticuloTipo("",articuloR.getIdDestino()+"P", articuloR.getIdPedido()+"", 1, uLog.getNumero(),"ADD",idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA
			}
				
		}
		else {
			request.setAttribute("menError", "ATENCION: pedido sin etiqueta aun");
		}
			

		
		return mapping.findForward(req);
	
	}
	
	public boolean etiquetaOK (String urlEtiqueta)
	{
		if(urlEtiqueta!=null)
		{
			if(urlEtiqueta.contains("http"))
			{
				return true;
			}
			
		}
		return false;
	}
	
	public boolean imprimirEtiqueta(DataArticuloEcommerceVerifR articuloR, int idEmpresa, Usuario uLog, HttpSession session)
	{
		
		Call_WS_APIENCUENTRA callAPI = new Call_WS_APIENCUENTRA();
		Call_WS_analoga call = new Call_WS_analoga();
		
		try
		{
			
			boolean hayEtiqueta = false;
			String urlEtiqueta ="";
			
			urlEtiqueta = articuloR.getUrlEtiqueta();
			
			if (!urlEtiqueta.equals("") && urlEtiqueta != null)
			{
				uLog.registrarEventoMin(session.getId(), "re-imprimiendo etiqueta, pedido:"+articuloR.getIdPedido()+" urlEtiqueta:"+urlEtiqueta+" idEquipo:"+uLog.getIdEquipo()+" idEmpresa:"+uLog.getIdEmpresa());
				
				if(articuloR.getIdDestino()==600000 || articuloR.getIdDestino()==900000)
				{
					callAPI.PutColaImpresion(""+articuloR.getIdPedido(), urlEtiqueta, 1, 1, uLog.getIdEquipo(),idEmpresa,1);
					hayEtiqueta = true;
				}
				else
				{
					callAPI.PutColaImpresion(""+articuloR.getIdPedido(), urlEtiqueta, 0, 1, uLog.getIdEquipo(),idEmpresa,1);
					hayEtiqueta = true;
				}
			}
			else
			{
				//Genero etiqueta
				urlEtiqueta = call.reSetEtiquetas(""+articuloR.getIdEcommerce(),articuloR.getIdPedido(), 6, idEmpresa, articuloR.getFecha(),true,null);
				uLog.registrarEventoMin(session.getId(), "generando etiqueta, pedido:"+articuloR.getIdPedido()+" urlEtiqueta:"+urlEtiqueta+" idEquipo:"+uLog.getIdEquipo()+" idEmpresa:"+uLog.getIdEmpresa());
				if (!urlEtiqueta.equals("") && urlEtiqueta != null)
				{
					
					//No se va a rotar si no esta en ecommerce_pedido_destino
					if(articuloR.getIdDestino()==600000 || articuloR.getIdDestino()==900000)
					{
						hayEtiqueta = true;
						callAPI.PutColaImpresion(""+articuloR.getIdPedido(), urlEtiqueta, 1, 1, uLog.getIdEquipo(),idEmpresa,1);
					}
					else
					{
						hayEtiqueta = true;
						callAPI.PutColaImpresion(""+articuloR.getIdPedido(), urlEtiqueta, 0, 1, uLog.getIdEquipo(),idEmpresa,1);
					}
				}
			}
				
				

			System.out.println(urlEtiqueta);
			return hayEtiqueta;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
}
