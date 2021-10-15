package web.ecommerce;

import helper.PropertiesHelper;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsonObjects.JSONDocumentLines;
import jsonObjects.JSONRespARGNSAPI;
import jsonObjects.JSONSalesOrder;

import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;
import logica.imprimir_caja;
import main.EcommerceProcessOrders;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jackson.map.ObjectMapper;

import cliente_rest_Invoke.Call2;
import cliente_rest_Invoke.Call_WS_analoga;
import cliente_rest_Invoke.JSONReader;
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescripcion;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.RspEtiqueta;

import beans.Fecha;
import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;



public class EcommerceReprintLabelPedido extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		
		try
		{
			//Long idPedido = Long.parseLong(request.getParameter("idPedido"));
			HttpSession session = request.getSession();
			Logica Logica = new Logica();
			Utilidades util = new Utilidades();
			
			Usuario usu = (Usuario) session.getAttribute("uLogeado");		
			int idEmpresa = util.darEmpresa(usu);
			if(idEmpresa==0)
			{
				return mapping.findForward("LOGIN");
			}
			
			List<DataIDDescripcion> pedido = Logica. darArticuloFactEcommerceReqIdPedido(request.getParameter("idPedido"), idEmpresa);
			pedido.remove(0);
			if(pedido.isEmpty())
			{
				request.setAttribute("menError", "No se encuentra pedido...");
				return mapping.findForward("no");
			}
			else
			{
				DataArticuloEcommerceVerifR articuloR = Logica.darArticuloEcommerceReqReclasifica(pedido.get(0).getIdLong(),0, idEmpresa);
				List<DataIDDescripcion> articulosPedidos = Logica.darListaDataIdDescripcionMYSQLConsulta("select CantidadRequerida,idArticulo from ecommerce_pedido_articulos_req where idPedido = "+articuloR.getIdPedido()+" AND idEmpresa="+idEmpresa);
				articulosPedidos.remove(0);
				int cantidad = 0;
				for (DataIDDescripcion ap : articulosPedidos) 
				{
					cantidad+=ap.getId();
				}
				
				List<DataArticuloEcommerceVerifR> articulosVerifFin = Logica.darArticulosEcommerceReq(articuloR.getIdPedido(), idEmpresa);
				session.setAttribute("articuloR", articuloR);
				request.setAttribute("cliente", articuloR.getDescripcion());
				request.setAttribute("articulosPedidoFin", articulosPedidos);
				request.setAttribute("pedido",  articuloR.getIdPedido());
				request.setAttribute("cantidad",  cantidad);
				request.setAttribute("articuloV",  articuloR.getIdArticulo());
				request.setAttribute("articulosVerifFin",articulosVerifFin);
				
				boolean etiqueta = imprimirEtiqueta(articuloR, articulosPedidos, idEmpresa);
				if(etiqueta)
				{
					Logica.updateEcommerceEstado(articuloR.getIdPedido(), 44,idEmpresa,usu);
					
					
					if(!Logica.ecommercePedidoTieneOV(articuloR.getIdPedido(), idEmpresa))
					{
					
						if(Logica.esMLPedido(articuloR.getIdPedido(), idEmpresa))
						{
							//Call_WS_meli ws = new Call_WS_meli();
							//String access_token= ws.getToken();
							Logica.ReReprocesarOrdenPedido(articuloR.getIdPedido(),idEmpresa);//,access_token);
						}
						else
						{
							Logica.ReprocesarOrdenPedido(articuloR.getIdPedido(), idEmpresa);
						}
						
						
						
						
						
					}
					
					
					Logica.vaciarOjoEcommerce(articuloR.getIdPedido(),idEmpresa,false);
					Logica.updateEcommerceEstado(articuloR.getIdPedido(), 3,idEmpresa,usu);
					return mapping.findForward("no");
				}
				else
				{
					Logica.updateEcommerceEstado(articuloR.getIdPedido(), 25,idEmpresa,usu);
					
					request.setAttribute("menError", "ATENCION: pedido sin etiqueta aun");
					return mapping.findForward("no");
				}
				
			}
			
		}
		catch(Exception e)
		{
			return mapping.findForward("no");
				
		}
		
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
		public boolean imprimirEtiqueta(DataArticuloEcommerceVerifR articuloR, List<DataIDDescripcion> lista, int idEmpresa)
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
				
				IPrint print = new IPrint();
				boolean hayEtiqueta = false;
				
				String urlEtiqueta = articuloR.getUrlEtiqueta();
				
				hayEtiqueta = etiquetaOK(urlEtiqueta);
				
				
				if(!hayEtiqueta)
				{
					
					if(articuloR.getMl()==1)
					{
						// buscar la orden y ver si es un pick o si siene los datos pero aun no tien etiqueta
						urlEtiqueta = Logica.darListaDataIdDescripcionMYSQLConsulta("select 0,URLetiqueta from ecommerce_pedido where idPedido = "+articuloR.getIdPedido()+" AND idEmpresa="+idEmpresa).get(0).getDescripcion();
						hayEtiqueta=etiquetaOK(urlEtiqueta);
						articuloR.setUrlEtiqueta(urlEtiqueta);
						IPrint.imprimeEtEccomerce(articuloR,lista,"", false,true,idEmpresa);
						
					}
					else
					{
						Call_WS_analoga c = new Call_WS_analoga();
						String URLbase = "https://www."+dom+".com.uy/tracking/";
						String funcion = "/get/etiqueta?idCompra="+articuloR.getIdPedido();
						String retorno = c.callWSGET(URLbase, funcion,articuloR.getCanal(),idEmpresa);
						
						RspEtiqueta et =JSONReader.readJsonPedidoEti(retorno);
						
						if(etiquetaOK(et.getEtiqueta()))
						{
							
							articuloR.setUrlEtiqueta(et.getEtiqueta());
							IPrint.imprimeEtEccomerce(articuloR,lista,"", false,true,idEmpresa);
							EncuentraPedido p = new EncuentraPedido();
							p.setIdPedido(articuloR.getIdPedido());
							p.setUrlEtiqueta(urlEtiqueta);
							
							//pickup
							if(et.getEtiqueta().contains("https://www."+dom+".com.uy/public/ctm/"))
							{
								String pick = et.getEtiqueta().replace("https://www."+dom+".com.uy/public/ctm/", "");
								pick = pick.replace(".pdf", "");
								int idPicking = 0;
								try
								{
									idPicking = Integer.parseInt(pick);
								}
								catch(Exception e){}
								p.updateShipping(idPicking, null,"", idEmpresa);
							}
							
							//UES
							else if(et.getEtiqueta().contains("https://system.netsuite.com/core/media/"))
							{
								p.updateShipping(800, null,"", idEmpresa);
							}
							//correo
							else if(et.getEtiqueta().contains("https://www."+dom+".com.uy/files.php/correouy/"))
							{
								DataIDDescripcion trackingURL =  Call_WS_analoga.darEtiquetaPE(et.getEtiqueta(),  p.getIdPedido(),articuloR.getCanal());
								p.updateShipping(900, trackingURL.getDescripcion(),"", idEmpresa);
								p.setUrlEtiqueta(trackingURL.getDescripcionB());
								
							}
							//DAC
							else if(et.getEtiqueta().contains("https://www."+dom+".com.uy/files.php/dac/"))
							{
								DataIDDescripcion trackingURL =  Call_WS_analoga.darEtiquetaPE(et.getEtiqueta(),  p.getIdPedido(),articuloR.getCanal());
								p.updateShipping(701, trackingURL.getDescripcion(),"", idEmpresa);
								p.setUrlEtiqueta(trackingURL.getDescripcionB());
								
							}
							
							
							p.updateEtiqueta(0, idEmpresa);
							
							hayEtiqueta = true;
						}
						else{
							IPrint.imprimeEtEccomerce(articuloR,lista,"", false,true,idEmpresa);
						}
							
						
						
					}
						
					// enviar mail
					//actualizar estado
				}
				else
				{
					articuloR.setUrlEtiqueta(urlEtiqueta);
					List<DataIDDescripcion> vert = Logica.darListaDataIdDescripcionMYSQLConsulta("select if(ED.idDestino='900',true,false) vertical ,''  from ecommerce_pedido_destino ED inner join ecommerce_pedido EP on EP.idPedido=ED.idPedido where EP.ML=0 AND ED.idPedido ="+articuloR.getIdPedido());
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
					
					  
					IPrint.imprimeEtEccomerce(articuloR,lista,"", vertical,true,idEmpresa);
					
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
