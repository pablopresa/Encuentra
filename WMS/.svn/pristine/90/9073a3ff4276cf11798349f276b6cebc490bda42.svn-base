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

import logica.EnviaMail;
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

import persistencia._EncuentraPersistir;

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



public class AltaNota extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
		
		List<DataDetallePedido> pedidos = (ArrayList<DataDetallePedido>)session.getAttribute("pedidoUpdate");
		List<Nota> note = (ArrayList<Nota>)session.getAttribute("notas");		
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String text = request.getParameter("notaText");
		String msj=""; 
		
		try{
		Nota nuevaNota = new Nota(pedidos.get(0).getIdPedido(),0,uLog.getNumero(),"",text,null);
		
		boolean  per =Logica.AltaNota(nuevaNota,idEmpresa);
		
		if (per){
			msj ="Se agrego una nueva Nota";
			request.setAttribute("menError", msj);
			}
		
		List<Nota> nuevaLista = Logica.ListaNota(pedidos.get(0).getIdPedido(), idEmpresa);
		
		session.setAttribute("notas",nuevaLista);
		return mapping.findForward("ok");
		
		}
		catch (Exception e){
			msj ="Hubo un error tratando de agregar la Nota";
			request.setAttribute("menError", msj);
			return mapping.findForward("ok");
		}
		
		
		
	}
		
	
		
		
	
	
}
