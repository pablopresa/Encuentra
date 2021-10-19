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
import dataTypes.DataPedidoArticuloEcommerceVerif;
import eCommerce_jsonObjectsII.EncuentraPedido;
import eCommerce_jsonObjectsII.Pedido;
import eCommerce_jsonObjectsII.RspEtiqueta;

import beans.Fecha;
import beans.Usuario;
import beans.encuentra.DataOjoArticulo;
import beans.encuentra.IPrint;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;



public class EcommerceVerifArtVerificados extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{			
		
				int idDep = 0;
				HttpSession session = request.getSession();
				Logica Logica = new Logica();
				Usuario uLog = (Usuario) session.getAttribute("uLogeado");
				Utilidades util = new Utilidades();
				int idEmpresa = util.darEmpresa(uLog);
				if(idEmpresa==0)
				{
					return mapping.findForward("LOGIN");
				}
				
				ServletContext context = request.getSession().getServletContext();
				Hashtable<String, String> artBarra = (Hashtable<String, String>) context.getAttribute("barras_"+idEmpresa);
				
				String barra = request.getParameter("barra");
				String articulo="";
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
						if(barra.equalsIgnoreCase(a))
						{
							//puso el articulo y no el codebar
							articulo=barra;
							break;
						}
					}
					
					
				}
				
				
				if(articulo==null || articulo.equals(""))
				{
					request.setAttribute("menError", "Atención, no se reconoce ningun articulo con el código/barra "+barra);
					saveToken(request);
					return mapping.findForward("no");
				}	
				System.out.println("");
				
				
				List<DataPedidoArticuloEcommerceVerif> pedidosVerificadosArt = Logica.darListaPedidosVerificadosArt(articulo, idEmpresa);
			   
				request.setAttribute("PedidosVer", pedidosVerificadosArt);
				
			 return mapping.findForward("ok");
	}
	
	
}
