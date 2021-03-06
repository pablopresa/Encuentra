package beans;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import beans.encuentra.DataArticulo;
import beans.api.CallBackPedido;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.Utilidades;

public class ProcessEcommerce {
	
	public ProcessEcommerce() {
	}

	public String darArticulo(Hashtable<String, String> artBarra, String barra,int idEmpresa) {
		String articulo = "";
		try
		{
			articulo = artBarra.get(barra.toUpperCase());
		}
		catch(Exception e)
		{
			
				
		}
		if(articulo==null || articulo.equals(""))
		{
			List<String> articulos = new ArrayList<>(artBarra.values());
			for (String a : articulos) 
			{
				if(barra.equals(a))
				{
					//puso el articulo y no el codebar
					articulo=barra;
					break;
				}
			}						
		}
		if(articulo==null || articulo.equals(""))
		{
			Logica lo = new Logica();
			DataArticulo artReal = lo.encuentraCodArticulo(barra, idEmpresa);
			if(artReal!=null)
			{
				articulo = artReal.getId();
			}
		}
		
		return articulo;
	}
	
	public ActionForward forwardEti(ActionMapping mapping, boolean etiqueta, DataArticuloEcommerceVerifR articuloR,
			int depEc, int idEmpresa, Usuario uLog, Logica Logica) {
		
		Logica.encuentraBajaArticulosOjos(1,articuloR.getIdArticulo(), depEc+"P", idEmpresa);	//BAJO DE ZONA DE CLASIFICACION ECOMMERCE			
		Logica.IngresarMovimientoArticuloTipo(depEc+"P",articuloR.getIdDestino()+"P", articuloR.getIdPedido()+"_"+articuloR.getIdArticulo(), 1,  uLog.getNumero(),"CON",idEmpresa);
		
		//FACTURACION AUTOMATICA
		Utilidades util = new Utilidades(); 
		util.facturacionAutomatica(articuloR.getIdPedido(),uLog.getIdEquipo(),idEmpresa,Logica);
		
		//ETIQUETA
		if(etiqueta)
		{				
			Logica.encuentraMoverOjos(articuloR.getIdDestino()+"P",articuloR.getIdPedido()+"",1,uLog.getNumero(),idEmpresa);					//AGREGO ARTICULO A EXPEDICION
			Logica.IngresarMovimientoArticuloTipo("",articuloR.getIdDestino()+"P", articuloR.getIdPedido()+"", 1, uLog.getNumero(),"ADD",idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA
			return mapping.findForward("fin");
		}
		else
		{
			Logica.updateEcommerceEstado(articuloR.getIdPedido(), 25,idEmpresa,uLog);
			//request.setAttribute("menError", "ATENCION: pedido sin etiqueta");
			return mapping.findForward("finOJO");
		}
		
		
	}
	
	
	public ActionForward forwardEtiMesa(ActionMapping mapping, boolean etiqueta, DataArticuloEcommerceVerifR articuloR,
			int depEc, int idEmpresa, Usuario uLog, Logica Logica, List<DataIDDescripcion> articulosMesa, HttpServletRequest request) {
		
		List<DataArticuloEcommerceVerifR> articulosVerifFin = Logica.darArticulosEcommerceReq(articuloR.getIdPedido(), idEmpresa);
		String articulosPedido = "";
		for (DataArticuloEcommerceVerifR a : articulosVerifFin) 
		{
			articulosPedido+="\n"+a.getIdArticulo()+" - "+a.getTotalProcesado();									
		}
		
		Logica.encuentraBajaArticulosOjos(1,articuloR.getIdArticulo(), depEc+"P", idEmpresa);	//BAJO DE ZONA DE CLASIFICACION ECOMMERCE
		Logica.IngresarMovimientoArticuloTipo(depEc+"P",articuloR.getIdDestino()+"P", articuloR.getIdPedido()+"_"+articuloR.getIdArticulo(), 1,  uLog.getNumero(),"CON",idEmpresa);
		
		//FACTURACION AUTOMATICA
		Utilidades util = new Utilidades(); 
		util.facturacionAutomatica(articuloR.getIdPedido(),uLog.getIdEquipo(),idEmpresa,Logica);
		
		if(etiqueta)
		{			
			if(!articulosMesa.isEmpty())
			{				
				//Logica.vaciarOjoEcommerce(articuloR.getIdPedido(),idEmpresa,false);	
				Logica.vaciarOjoEcommerce_pedido(articuloR.getIdPedido(),idEmpresa);
			}
			else
			{				
				request.setAttribute("menError", "Atenci?n, Hay articulos ya clasificados sin ubicaci?n, Encuentrelos "+articulosPedido);				
			}
			
			Logica.encuentraMoverOjos(articuloR.getIdDestino()+"P",articuloR.getIdPedido()+"",1,uLog.getNumero(),idEmpresa);					//AGREGO ARTICULO A EXPEDICION
			Logica.IngresarMovimientoArticuloTipo("",articuloR.getIdDestino()+"P", articuloR.getIdPedido()+"", 1, uLog.getNumero(),"ADD",idEmpresa);	//REGISTRO EL MOVIMIENTO DE MERCADERIA
			
			return mapping.findForward("finB");
		}
		else
		{
			Logica.updateEcommerceEstado(articuloR.getIdPedido(), 25,idEmpresa, uLog);
			
			if(!articulosMesa.isEmpty())
			{					
				return mapping.findForward("finOJO");									
			}
			else
			{					
				request.setAttribute("menError", "Atenci?n. Hay articulos ya clasificados sin ubicaci?n, Encuentrelos "+articulosPedido);
				return mapping.findForward("finOJO");
			}
			
		}	
		
		
	}
	
	public void cambioEstadoAPreparado(Logica Logica, int idEmpresa, DataArticuloEcommerceVerifR articuloR) {
		
		/*boolean integracionActiva = false;
		integracionActiva = Logica.darIntegracionProductiva(3, idEmpresa);
				
		if(integracionActiva){
			Call_WS_analoga c = new Call_WS_analoga();
			String track = Logica.darTrackingPedido(articuloR.getIdPedido(),idEmpresa);
			String jotason2="";
			if(!track.equals("") && !track.equals(articuloR.getIdPedido()+""))
			{				
					jotason2="[ "+
							 "     { "+
							 "        \"id\":\""+articuloR.getIdEcommerce()+"\", "+
							 "        \"estado\":\"preparado\", "+
							 "		  \"trackingID\":\""+track+"\" "+
							 "     } "+
							 "]";
			} 
			else
			{
				jotason2="[ "+
						 "     { "+
						 "        \"id\":\""+articuloR.getIdEcommerce()+"\", "+
						 "        \"estado\":\"preparado\" "+
						 "     } "+
						 "]";
			}
			//llamar a WS para cerrar
				
				
				c.setPedidos(jotason2,articuloR.getCanal(),idEmpresa);
		}*/
		boolean integracionActiva = false;
		integracionActiva = Logica.darIntegracionProductiva(3, idEmpresa);
				
		if(integracionActiva){
			Logica.CambioEstadoMarketPlace(idEmpresa, articuloR);
		}
		
	}
	
	
	
public void callBackEstado(int idEmpresa, Long idPedido, int idEstado, int printer) {
		CallBackPedido cbp = new CallBackPedido();
		cbp.setIdEmpresa(idEmpresa);
		cbp.setMensaje("");
		cbp.setIdPedido(idPedido);
		cbp.setIdEstado(idEstado);	
		cbp.setPrinter(printer);
		
		Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();
		cwa.putCallBackPedidos(cbp);
	}


	public void setearSession(HttpSession session,DataArticuloEcommerceVerifR articuloR) {
		session.setAttribute("articuloR", articuloR);
		session.setAttribute("cliente", articuloR.getDescripcion());
		session.setAttribute("pedido",  articuloR.getIdPedido());
		session.setAttribute("cantidad",  articuloR.getTotalPedido());
		session.setAttribute("articuloV",  articuloR.getIdArticulo());
	}
	
	
	
}
