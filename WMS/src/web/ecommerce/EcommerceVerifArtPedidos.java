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

import com.independentsoft.exchange.FindFolderResponse;

import cliente_rest_Invoke.Call2;
import cliente_rest_Invoke.Call_WS_analoga;
import cliente_rest_Invoke.JSONReader;
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescripcion;
import dataTypes.DataPosiblePedido;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.Pedido;
import eCommerce_jsonObjectsII.RspEtiqueta;

import beans.Fecha;
import beans.ProcessEcommerce;
import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;



public class EcommerceVerifArtPedidos extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{			
		
				int idDep = 0;
				HttpSession session = request.getSession();
				Logica Logica = new Logica();
				Utilidades util = new Utilidades();
				
				Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
				int idEmpresa = util.darEmpresa(uLog);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				int idDepoWEB = util.darParametroEmpresaINT(idEmpresa,5);
				int idDepoCentral = util.darParametroEmpresaINT(idEmpresa,4);
				
				if(idDepoWEB==-1 || idDepoCentral==-1)
				{
					request.setAttribute("menError", "Atencion, falta parametrizar depositos");
					return mapping.findForward("LOGIN");
				}
				IPrint print = new IPrint();
				ProcessEcommerce process = new ProcessEcommerce();
				ServletContext context = request.getSession().getServletContext();
				Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
				String pedido = request.getParameter("pedido");
				String picking = request.getParameter("idPickingVE");
				boolean unPedido = false;
				String articulo = "";			
				
				
				if(pedido==null)
				{
					String barra = request.getParameter("barra");
					articulo = process.darArticulo(artBarra, barra,idEmpresa);					
					
					if(articulo==null || articulo.equals(""))
					{
						request.setAttribute("menError", "Atenci?n, no se reconoce ningun articulo con el c?digo/barra "+barra);
						//saveToken(request);
						return mapping.findForward("no");
					}	
					
					int idPicking = 0;
					try {
						idPicking = Integer.parseInt(picking);
						session.setAttribute("idPickingVE", picking);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					List<DataPosiblePedido> posiblesPedidos = Logica.ClasificacionDarPedidos(articulo,idPicking, idEmpresa,idDepoCentral);
					
					if(posiblesPedidos.isEmpty()){
						request.setAttribute("menError", "En el picking "+idPicking+" no se encuentra el articulo "+articulo);
						return mapping.findForward("no");
					}
					else{
						if(posiblesPedidos.size()>1){
							session.setAttribute("posiblesPedidos", posiblesPedidos);
							session.setAttribute("miArticulo", articulo);
							System.out.println("");
							return mapping.findForward("pedidos");
						}
						else{
							unPedido = true;
							pedido = String.valueOf(posiblesPedidos.get(0).getIdPedido());
						}
					}					
				}
				
				if(!unPedido){
					articulo = (String) session.getAttribute("miArticulo");
					session.removeAttribute("miArticulo");
				}
				
				DataArticuloEcommerceVerifR articuloR = Logica.darArticuloEcommerceReq(articulo,pedido, idEmpresa);			
								
				if(articuloR==null)
				{					
					//request.setAttribute("menError", "Atenci?n, No se encontraron pedidos de este articulo buscamos en articulos pedidos confirmados, tambien en los sin confirmar. Tampoco estaba en los articulos que no se pidieron por que no habia stock. Devuelva este par a su deposito de origen.");
					request.setAttribute("menError","No se precisa, devuelvalo." );
					return mapping.findForward("no");
				}
				else if(articuloR.getMensaje()!=null)
				{
					request.setAttribute("menError",articuloR.getMensaje());
					return mapping.findForward("no");
				}
				
				else {
					if (articuloR.getTotalPedido()==1 && articuloR.getTotalProcesado()+1!=articuloR.getTotalPedido())
					{
						request.setAttribute("menError", "ATENCION: El pedido ya se envi?, no debe entregar estos articulos");
						return mapping.findForward("no");
					}
					boolean sigo =Logica.updateEcommerceArticuloReq(articuloR.getIdArticulo(), articuloR.getIdPedido(),articuloR.getIdDeposito(),
							articuloR.getCanal(),idEmpresa, uLog);
					if(!sigo)
					{
						request.setAttribute("menError", "ATENCION: El pedido ya se envi?, no debe entregar estos articulos");
						return mapping.findForward("no");
					}
					boolean canalActivo = false;
					try {
						canalActivo = Logica.canalActivoEC(articuloR.getCanal(), idEmpresa);
					} catch (Exception e) {}
					
					boolean paramCantEtis = false;
					
					boolean etiqueta = false;					
					
					if(1==articuloR.getTotalPedido()) {
						
							process.setearSession(session, articuloR);
														
							paramCantEtis = util.darParametroEmpresaBool(idEmpresa, 11);
							session.setAttribute("paramCantEtis", paramCantEtis);	
							
							if(paramCantEtis) {						//CONTROL PARA CANTIDAD DE ETIQUETAS
								etiqueta = true;
							}
							else {
								etiqueta = print.imprimirEtiqueta(articuloR, uLog.getIdEquipo(),idEmpresa,1,null);
							}
							
							if(canalActivo)
							{
								articuloR.setEstadoEncuentra(3);
								process.cambioEstadoAPreparado(Logica, idEmpresa, articuloR);								
							}
							//process.callBackEstado(idEmpresa, articuloR, 3);													
							return process.forwardEti(mapping, etiqueta, articuloR, idDepoWEB, idEmpresa, uLog, Logica);							
					}
					
					if(articuloR.getTotalProcesado()+1==articuloR.getTotalPedido()) {
						
						process.setearSession(session, articuloR);
						List<DataIDDescripcion> articulosMesa = Logica.darUbicacionPedidoEnOjos(articuloR.getIdPedido(), idEmpresa);
						articulosMesa.remove(0);
							
						session.setAttribute("mesa", articulosMesa);								
						
						paramCantEtis = util.darParametroEmpresaBool(idEmpresa, 11);
						session.setAttribute("paramCantEtis", paramCantEtis);
						
						if(paramCantEtis) {						//CONTROL PARA CANTIDAD DE ETIQUETAS
							etiqueta = true;
						}
						else {
							etiqueta = print.imprimirEtiqueta(articuloR, uLog.getIdEquipo(),idEmpresa,1,null);
						}
						
						if(canalActivo)
						{
							articuloR.setEstadoEncuentra(3);
							process.cambioEstadoAPreparado(Logica, idEmpresa, articuloR);
						}
						//process.callBackEstado(idEmpresa, articuloR, 3);	
						return process.forwardEtiMesa(mapping, etiqueta, articuloR, idDepoWEB, idEmpresa, uLog, Logica,
								articulosMesa, request);						
					}
					
					if(articuloR.getTotalProcesado()+1<articuloR.getTotalPedido()) {
						
						process.setearSession(session, articuloR);						
						
						if(articuloR.getTotalProcesado()>0)
						{
							List<DataIDDescripcion> articulosMesa = Logica.darUbicacionPedidoEnOjos(articuloR.getIdPedido(), idEmpresa);
							articulosMesa.remove(0);
							
							session.setAttribute("mesa", articulosMesa);
							return mapping.findForward("cubih");
						}						
						return mapping.findForward("cubi");
					}
					
					return mapping.findForward("ok");
				}
				
	}

	
	
	
}
