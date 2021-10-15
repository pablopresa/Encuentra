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
import dataTypes.DataDetallePedido;
import dataTypes.DataIDDescripcion;
import dataTypes.DataMovimientoStockLocales;

import beans.Fecha;
import beans.Usuario;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;



public class EcommerceEstadoPedidosALL extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		int idTienda = 0;
		/*
		try
		{
			idTienda = (Integer) session.getAttribute("idTienda");
		}
		catch(Exception e)
		{
			
		}
		*/
		
		//estado=2&
		//forma=&
		//pedido=&
		//agrpTienda=on&
		//fini=09-12-2017+-+09-12-2017&
		//sinEtqt=on
		String idPedido = request.getParameter("pedido");
		String estado = request.getParameter("estado");
		String formaPago = request.getParameter("forma");
		String facturados = request.getParameter("agrpTienda");
		String sinEt = request.getParameter("sinEtqt");
		String fechas = request.getParameter("fini");
		
		
		System.out.println(fechas);
		
		request.setAttribute("rango", fechas);
		request.setAttribute("estadoS", estado);
		String fechaI = "";
		String fechaF = "";
		
		if(fechas!=null)
		{
			String []fechaIF = fechas.split(" - ");
			
			fechaI = fechaIF[0];
			fechaF = fechaIF[1];
			
			fechaI = fechaI+" 00:00:00";
			fechaF = fechaF+" 23:59:59";
			
		}
		
		
		
		
		
		session.setAttribute("urlRedirect", getURL(request));
		
		
		
		List<DataDetallePedido> pedidos = Logica.darListaDetallePedidosEcommerce(idPedido, estado, formaPago, facturados, sinEt, fechaI, fechaF,idTienda,null,"","",idEmpresa,"","",null,true);
		
		
		session.setAttribute("lstPedidosConsola", pedidos);
		return mapping.findForward("ok");
	}
	
	public static String getURL(HttpServletRequest req) {

	    String scheme = req.getScheme();             // http
	    String serverName = req.getServerName();     // hostname.com
	    int serverPort = req.getServerPort();        // 80
	    String contextPath = req.getContextPath();   // /mywebapp
	    String servletPath = req.getServletPath();   // /servlet/MyServlet
	    String pathInfo = req.getPathInfo();         // /a/b;c=123
	    String queryString = req.getQueryString();          // d=789

	    // Reconstruct original requesting URL
	    StringBuilder url = new StringBuilder();
	    url.append(scheme).append("://").append(serverName);

	    if (serverPort != 80 && serverPort != 443) {
	        url.append(":").append(serverPort);
	    }

	    url.append(contextPath).append(servletPath);

	    if (pathInfo != null) {
	        url.append(pathInfo);
	    }
	    if (queryString != null) {
	        url.append("?").append(queryString);
	    }
	    return url.toString();
	}
	
}
