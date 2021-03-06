package web.ecommerce;

import helper.PropertiesHelper;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
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

import persistencia._EncuentraConexion;
import persistencia._EncuentraPersistir;

import cliente_rest_Invoke.Call2;
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataDetallePedido;
import dataTypes.DataIDDescripcion;
import dataTypes.DataMovimientoStockLocales;
import eCommerce_jsonObjectsII.Cliente;

import beans.Fecha;
import beans.Usuario;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;
//import clienteWSVS.WSCommunicate;



public class EcommerceEditarPedidoIII extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario usu = (Usuario) session.getAttribute("uLogeado");
		
		int idEmpresa = util.darEmpresa(usu);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		
		
		List<DataDetallePedido> pedidos = (ArrayList<DataDetallePedido>)session.getAttribute("pedidoUpdate");
		Cliente cl = (Cliente) session.getAttribute("cliente");
		
		String nombre = request.getParameter("cNombre");
		String apellido = request.getParameter("cApellido");
		String direccion = request.getParameter("cDireccion");
		String nroPuerta = request.getParameter("cNroPuerta");
		String nroApto = request.getParameter("cNroApto");
		String ciudad = request.getParameter("cCiudad");
		String departamento = request.getParameter("cDepartamento");
		String cPostal = request.getParameter("cCodigo");
		String telefono = request.getParameter("cTelefono");
		String documento = request.getParameter("cDocumento");
		String obs = request.getParameter("cObs");
		
		//Si no lo ingresan preciso inicializarlos 
		if(nombre==null)
		 nombre = "";
		if(apellido==null)
		 apellido = "";
		if(direccion==null)
		 direccion = "";
		if(nroPuerta==null) 
		 nroPuerta = "";
		if(nroApto==null) 
		 nroApto = "";
		if(ciudad==null)  
		 ciudad = "";
	    if(departamento==null) 
		 departamento = "";
		if(cPostal==null) 
		 cPostal = "";
		if(telefono==null) 
		 telefono = "";
		if(documento==null) 
		 documento = "";
		if(obs==null) 
		 obs = "";
		
		String msj = "Cambio datos cliente:";
		
		try {
			msj += mensajeCambios(cl.getNombre(), nombre, msj, "nombre");
			msj += mensajeCambios(cl.getApellido(), apellido, msj, "apellido");
			msj += mensajeCambios(cl.getCalle(), direccion, msj, "direccion");
			msj += mensajeCambios(cl.getNroPuerta(), nroPuerta, msj, "nroPuerta");
			msj += mensajeCambios(cl.getNroApto(), nroApto, msj, "nroApto");
			msj += mensajeCambios(cl.getCiudad(), ciudad, msj, "ciudad");
			msj += mensajeCambios(cl.getDepartamento(), departamento, msj, "departamento");
			msj += mensajeCambios(cl.getCp(), cPostal, msj, "cPostal");
			msj += mensajeCambios(cl.getTelefono(), telefono, msj, "telefono");
			msj += mensajeCambios(cl.getDocumentoNro(), documento, msj, "documento");
			msj += mensajeCambios(cl.getObs(), obs, msj, "obs.");
		} catch (Exception e) {
		}
		
			
		boolean persiste = Logica.updateClienteEcommerce(pedidos.get(0).getIdPedido(), idEmpresa, nombre, apellido, direccion, nroPuerta, nroApto, ciudad, departamento, cPostal, telefono, documento, obs);
		
			try {
				if(persiste) {
					try {
						Logica.logPedido(pedidos.get(0).getIdPedido(), usu.getNumero(), -1, msj, 0,idEmpresa);
					}
					catch(Exception e){
						System.out.println("Error al registrar log edit");
					}
				}
				else {
					msj="Error tratando de modificar los datos del cliente";
				}	
			}	
			catch(Exception e){
				 System.out.println("catch articulo");
				 msj="Error tratando de modificar los datos del cliente";
				 request.setAttribute("menError", msj);
				 return mapping.findForward("ok");
			 }
			
		Cliente cliente = Logica.darClienteShippingEcommerce(pedidos.get(0).getIdPedido(), idEmpresa);
		session.setAttribute("cliente",cliente);	
			
		return mapping.findForward("ok");		
				
	}

	public String mensajeCambios(String anterior, String nuevo, String msj, String parametro) {
		if (!nuevo.equals(anterior))
			msj = " // Se cambio "+ parametro + " "+anterior+" por "+ nuevo;
		else
			msj = "";
		return msj;
	}
}
