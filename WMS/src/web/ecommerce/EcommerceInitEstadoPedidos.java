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



public class EcommerceInitEstadoPedidos extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
	Usuario uLog = (Usuario) session.getAttribute("uLogeado");
	Utilidades util = new Utilidades();
	int idEmpresa = util.darEmpresa(uLog);
	if(idEmpresa==0)
	{
		return mapping.findForward("LOGIN");
	}

		
		List<DataIDDescripcion> estados = Logica.darListaDataIdDescripcionMYSQLConsulta("select id,descripcion from ecommerce_estado_encuentra where id in (1,2,25,3,34,4,5,6,99) and idEmpresa ="+idEmpresa);
		estados.remove(0);
		List<DataIDDescripcion> formasPago = Logica.darListaDataIdDescripcionMYSQLConsulta("select distinct 0,FormaPago from ecommerce_pedido where FormaPago not regexp '[0-9]$' AND idEmpresa ="+idEmpresa);
		formasPago.add(new DataIDDescripcion(0,"MERCADO LIBRE"));
		
		List<DataIDDescripcion> couriers = Logica.darListaDataIdDescripcionConsulMYSQL ("SELECT ECE.idDeposito, ECE.nombre FROM ecommerce_envioml ECE INNER JOIN ecommerce_pedido_destino EPD ON ECE.idDeposito = EPD.idDestino AND ECE.IdEmpresa=EPD.IdEmpresa INNER JOIN ecommerce_pedido EP ON EP.idPedido = EPD.idPedido AND EP.IdEmpresa = EPD.IdEmpresa WHERE ECE.idEmpresa="+idEmpresa+" GROUP BY ECE.idDeposito, ECE.nombre;");
		
		//List<DataIDDescripcion> destinos = Logica.darListaDataIdDescripcionMYSQLConsulta("select idDeposito,nombre from ecommerce_envioml");
		//destinos.remove(0);
		//List<DataIDDescripcion> destinos = Logica.darListaDataIdDescripcionMYSQLConsulta("select idDeposito,Nombre from depositos");
		
		
		
		
		
		session.setAttribute("estadosL", estados);
		//session.setAttribute("listadtn", destinos);
		session.setAttribute("formasPago", formasPago);
		session.setAttribute("couriers", couriers);
		
		
		return mapping.findForward("ok");
	}
	
}
