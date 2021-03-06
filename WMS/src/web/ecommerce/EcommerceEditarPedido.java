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
import eCommerce_jsonObjectsII.Cliente;

import beans.Fecha;
import beans.Nota;
import beans.Usuario;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;



public class EcommerceEditarPedido extends Action 
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
		
		String idPedido = request.getParameter("idPedido");
		
		
		List<DataDetallePedido> pedidos = Logica.darListaDetallePedidosEcommerce(idPedido, null, null, null, null, "", null,0,null,"","",idEmpresa,"","",null,true);
		
		//List<DataIDDescripcion> deps = Logica.darListaDataIdDescripcionConsulMYSQL("select IdAlmacenERP, IdAlmacen from ecommerce_almacenes  order by IdAlmacenERP ");
		List<DataIDDescripcion> deps = Logica.darListaDataIdDescripcionConsulMYSQL("SELECT idDeposito, idDepositoSAP FROM depositos WHERE idDeposito<9001 AND idEmpresa="+idEmpresa+" ORDER BY idDeposito ");
		
		List<Nota> listaNotas = Logica.ListaNota(Long.parseLong(idPedido), idEmpresa);
		
		Cliente cliente = Logica.darClienteShippingEcommerce(Long.parseLong(idPedido), idEmpresa);
		
	
		session.setAttribute("notas",listaNotas);
		session.setAttribute("deps", deps);
		session.setAttribute("cliente",cliente);
		session.setAttribute("pedidoUpdate",pedidos);
		
		return mapping.findForward("ok");
	}
	
}
