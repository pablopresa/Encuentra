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

import persistencia._EncuentraConexion;
import persistencia._EncuentraPersistir;

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



public class EcommerceCancelarPedido extends Action 
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
	
		List<DataDetallePedido> pedidos = (ArrayList<DataDetallePedido>)session.getAttribute("pedidoUpdate");
		//List<DataDetallePedido> pedido = Logica.darListaDetallePedidosEcommerce(idPedido, null, null, null, null, "", null,0,null);
		List<Integer> estados = new ArrayList<>();
		estados.add(4);
		estados.add(5);
		estados.add(6);
		estados.add(40);
		
		
		try{
			
		
		boolean paso = Logica.pedidoPasoXEstados(String.valueOf(pedidos.get(0).getIdPedido()),estados, idEmpresa);
		
		if (!paso){
			Logica.persistir("update ecommerce_pedido set cancelado=1 where idEmpresa="+idEmpresa+" AND idPedido="+String.valueOf(pedidos.get(0).getIdPedido()));
			
			Logica.updateEcommerceEstado(pedidos.get(0).getIdPedido(), 99,idEmpresa,uLog);
			//Logica.logPedido(pedidos.get(0).getIdPedido(), uLog.getNumero(), 99, "El pedido "+pedidos.get(0).getIdPedido()+" fue cancelado", 0,idEmpresa);
			request.setAttribute("menError", "El pedido "+pedidos.get(0).getIdPedido()+" fue cancelado");
		}
		else{
			request.setAttribute("menError", "No se puede cancelar este pedido");
		}
		}
		catch(Exception e){
			request.setAttribute("menError", "Sucedio un error mientras se cancelaba el pedido");
			return mapping.findForward("ok");
		}
		
		
		return mapping.findForward("ok");
	}
	
}
