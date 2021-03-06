package web.ecommerce;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import beans.encuentra.IPrint;
import cliente_rest_Invoke.Call_WS_analoga;
import cliente_rest_Invoke.JSONReader;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.RspEtiqueta;
import logica.Logica;
import logica.Utilidades;



public class EcommercereclasificaPedido extends Action 
{ 

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		int idDep = 0;
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		ServletContext context = request.getSession().getServletContext();
		Usuario usu = (Usuario) session.getAttribute("uLogeado");		
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(usu);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
		
		
		
		
		String barra = request.getParameter("barra");
		String dosEtiquetas = request.getParameter("dosEtiquetas");
		boolean procesado=true;
		if(dosEtiquetas!=null){
			procesado=false;
		}
		String articulo=barra;
		try
		{
			if(articulo==null || articulo.equals(""))
			{
				articulo = request.getParameter("articulos");
				if(articulo==null || articulo.equals("")){
					request.setAttribute("menError", "Atenci?n, no se reconoce ningun pedido ");
					return mapping.findForward("no");
				}
				else{
					articulo = articulo.replace(" ", "");					
					articulo = articulo.replaceAll("[\n\r]","");					
					//articulo = articulo.replaceFirst(",", "");					
					//String[] listaPedidos = articulo.split(",");
					articulo=articulo.substring(0,articulo.length()-1);
					procesado=true;
				}			
			
			}					
				try
				{
					//Long idPedido = Long.parseLong(articulo);
					
					
					List<DataIDDescripcion> pedido = Logica. darArticuloFactEcommerceReqIdPedido(articulo, idEmpresa);
					pedido.remove(0);
					if(pedido.isEmpty())
					{
						request.setAttribute("menError", "No se encuentra pedido...");
						return mapping.findForward("no");
					}
					else
					{
						boolean etiqueta = false;
						
						for(DataIDDescripcion p:pedido){	
							DataArticuloEcommerceVerifR articuloR = Logica.darArticuloEcommerceReqReclasifica(p.getIdLong(),0, idEmpresa);
							List<DataIDDescripcion> articulosPedidos = Logica.darListaDataIdDescripcionMYSQLConsulta("select CantidadRequerida,idArticulo from ecommerce_pedido_articulos_req where idPedido = "+articuloR.getIdPedido()+" AND idEmpresa="+idEmpresa);
							articulosPedidos.remove(0);
							
							
							Logica.logPedido(pedido.get(0).getIdLong(),0,0,"Tratando de reimprimir etiqueta",0,idEmpresa);
							
							etiqueta = imprimirEtiqueta(articuloR, articulosPedidos,procesado,idEmpresa,usu);
						}
						
						if(!etiqueta)
						{
							request.setAttribute("menError", "ATENCION: pedido sin etiqueta aun");
							return mapping.findForward("no");
						}							
						
					}
					
				}
				catch(Exception e)
				{
					request.setAttribute("menError", "ATENCION: no se encontr? el pedido del articulo");
					return mapping.findForward("no");
						
				}
				
							
						
		}
		catch(Exception e)
		{
			
				
		}
		
		return mapping.findForward("no");
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
		public boolean imprimirEtiqueta(DataArticuloEcommerceVerifR articuloR, List<DataIDDescripcion> lista,boolean procesado,int idEmpresa, Usuario usu)
		{
			Logica Logica = new Logica();
			try
			{
				String dom="";
				if(articuloR.getCanal()==0){
					dom="stadium";
				}
				else{
					dom="misscarol";
				}
				
				int est=0;
				IPrint print = new IPrint();
				boolean hayEtiqueta = false;
				
				String urlEtiqueta = articuloR.getUrlEtiqueta();
				
				hayEtiqueta = etiquetaOK(urlEtiqueta);
				
				
				if(!hayEtiqueta)
				{
					
					if(articuloR.getMl()==1)
					{
						// buscar la orden y ver si es un pick o si siene los datos pero aun no tien etiqueta
					//	Call_WS_meli ws = new Call_WS_meli();
						
						List<DataIDDescDescripcion> canales = Logica.EcommercedarCanalesML(idEmpresa);
						String etiMeli="";
						/*
						for (DataIDDescDescripcion c : canales) 
						{
							if (etiMeli.equals("") || etiMeli==null){	
								String usr = c.getDesc();
								String secret = c.getDescripcion();
								String access_token= ws.getToken(usr,secret);
								etiMeli = ws.getEtiML(access_token,c.getId(),c.getDescII(),articuloR.getIdPedido());
							}
						}
						*/
						if(etiquetaOK(etiMeli)){
							hayEtiqueta = true;
							est=(Logica.darDataIdDescripcion("select EstadoEncuentra,'' from ecommerce_pedido where idpedido= "+articuloR.getIdPedido())).getId();
							if(est==25){
								Logica.updateEcommerceEstado(articuloR.getIdPedido(), 3,idEmpresa, usu);
							}
						}
						
						articuloR.setUrlEtiqueta(etiMeli);
						IPrint.imprimeEtEccomerce(articuloR,lista,"", false,procesado,idEmpresa);
						
					}
					else
					{
						RspEtiqueta et = new RspEtiqueta();
					
						try 
						{
							Thread.sleep(4000);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
						
						Call_WS_analoga c = new Call_WS_analoga();
						String URLbase = "https://www."+dom+".com.uy/tracking/";
						String funcion = "/get/etiqueta?idCompra="+articuloR.getIdPedido();
						String retorno = c.callWSGET(URLbase, funcion,articuloR.getCanal(),idEmpresa);
						
						et =JSONReader.readJsonPedidoEti(retorno);
						
						if(etiquetaOK(et.getEtiqueta()))
						{					
							hayEtiqueta = true;
							est=(Logica.darDataIdDescripcion("select EstadoEncuentra,'' from ecommerce_pedido where idpedido= "+articuloR.getIdPedido())).getId();
							if(est==25){
								Logica.updateEcommerceEstado(articuloR.getIdPedido(), 3,idEmpresa, usu);
							}
						}
						
						articuloR.setUrlEtiqueta(et.getEtiqueta());
						IPrint.imprimeEtEccomerce(articuloR,lista,"", false,procesado,idEmpresa);
						EncuentraPedido p = new EncuentraPedido();
						p.setIdPedido(articuloR.getIdPedido());
						p.setUrlEtiqueta(urlEtiqueta);
						p.updateEtiqueta(0, idEmpresa);
						
					}
					
						
					// enviar mail
					//actualizar estado
				}
				else
				{
					articuloR.setUrlEtiqueta(urlEtiqueta);
					List<DataIDDescripcion> vert = Logica.darListaDataIdDescripcionMYSQLConsulta("select if(ED.idDestino='900',true,false) vertical ,''  from ecommerce_pedido_destino ED inner join ecommerce_pedido EP on EP.idPedido=ED.idPedido where EP.ML=0 AND ED.idPedido ="+articuloR.getIdPedido()+" AND ED.idEmpresa="+idEmpresa);
					vert.remove(0);
					boolean vertical = false;
					try
					{
						if(!vert.isEmpty())
						{
							int verticalI = vert.get(0).getId();
							if(verticalI==1)
							{
								vertical=true;
							}
						}
					}
					catch(Exception e)
					{
						
					}
					
					  
					IPrint.imprimeEtEccomerce(articuloR,lista,"",vertical,procesado,idEmpresa);
					est=(Logica.darDataIdDescripcion("select EstadoEncuentra,'' from ecommerce_pedido where idpedido= "+articuloR.getIdPedido())).getId();
					if(est==25){
						Logica.updateEcommerceEstado(articuloR.getIdPedido(), 3,idEmpresa, usu);
					}
					
				}
				return hayEtiqueta;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
			
			
			
			
			
			
				
				
			
			
			
		}
	
}
