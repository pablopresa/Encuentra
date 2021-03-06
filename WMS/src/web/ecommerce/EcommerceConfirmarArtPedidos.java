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
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataIDDescripcion;
import dataTypes.DataMovimientoStockLocales;

import beans.Fecha;
import beans.Usuario;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;



public class EcommerceConfirmarArtPedidos extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		int idDep = 0;
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario usu = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(usu);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		try
		{
			List<DataIDDescripcion>movimientoStock = new ArrayList<>();
			EcommerceProcessOrders pro = new EcommerceProcessOrders();
			List<DataArticuloEcommercePedido> articulosPedidos = (List<DataArticuloEcommercePedido>) session.getAttribute("articulosImp");
			
			for (DataArticuloEcommercePedido ar : articulosPedidos) 
			{
				if(request.getParameter(ar.getArticulo())!=null && request.getParameter(ar.getArticulo()).equals("on"))
				{
					idDep = ar.getDeposito();
					movimientoStock.add(new DataIDDescripcion(ar.getCantidad(), ar.getArticulo()));
					pro.confirmarSKU(ar.getArticulo(), ar.getDeposito(), ar.getCantidad(), ar.getIdPedido(),idEmpresa, usu);
					
				}				
			}
			
			
			DataMovimientoStockLocales dm = new DataMovimientoStockLocales(idDep, 71, movimientoStock,30);
			
			
			
			dm.Grabar("Par pedido venta web, Movimiento generado por encuentra");
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("menError", "Ocurrio un error mientras se confirmaban los pedidos");
			 return mapping.findForward("ok");
		}
		
		if(idDep!=0)
		{
			//List<DataArticuloEcommercePedido> articulosPedidos = Logica.darArticulosPedidosEcommerce(idDep);
			List<DataArticuloEcommercePedido> articulosPedidos = Logica.ArticulosPedidosEcommercePendientes(idDep, 1, idEmpresa);
			session.setAttribute("articulosImp", articulosPedidos);
		}
		
		
		return mapping.findForward("ok");
	}
	
}
