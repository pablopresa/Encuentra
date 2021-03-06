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

import persistencia.MSSQL;
import persistencia._EncuentraPersistir;

import cliente_rest_Invoke.Call2;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataDetallePedido;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.DataIDIDDescripcion;
import dataTypes.DataMovimientoStockLocales;
import eCommerce_jsonObjectsII.Cliente;

import beans.Fecha;
import beans.Usuario;
import beans.api.DataMovimiento;
import beans.api.Order;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;



public class EcommercePedidoDeleteArt extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
 Logica Logica = new Logica();
		
		List<DataDetallePedido> pedidos = (ArrayList<DataDetallePedido>)session.getAttribute("pedidoUpdate");
		Utilidades util = new Utilidades();
		
		Usuario usu = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(usu);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		String pedido = request.getParameter("pedido");
		String dep = request.getParameter("depo");
		String art = request.getParameter("arti");
		String canti = request.getParameter("canti");
		
		List<DataIDDescripcion> estadosEncuentra = Logica.darListaDataIdDescripcionMYSQLConsulta("select id,descripcion from ecommerce_estado_encuentra where idEmpresa="+idEmpresa);
		List<Integer> estados = new ArrayList<>();
		estados.add(3);
		estados.add(4);
		estados.add(5);
		estados.add(6);
		estados.add(40);
		
		int state=pedidos.get(0).getIdEstado();		
		String msj="";
		boolean cambioEstado= false;
				
		boolean paso = Logica.pedidoPasoXEstados(pedido,estados, idEmpresa);
	
		if (!paso){	
											
				String consulta = "select * from art_descripcion where idEmpresa="+idEmpresa+" AND id ='"+art+"'";
				boolean sigo = Logica.hayRegistro(consulta);
				
				if (sigo){					 
					int idDepoCentral = util.darParametroEmpresaINT(idEmpresa,4);
					 int idDepoWeb = util.darParametroEmpresaINT(idEmpresa,5);
					 
						 try{
							 String qry="update ecommerce_import_venta " + 
							 "set totalunidades=totalunidades-1, importepago=importepago- "+
							 "(select convert( "+
							 "(select precioImp from ecommerce_import_ventalinea where idEmpresa="+idEmpresa+" AND idventa= "+pedido+" and idarticulo='"+art+"'),decimal(10,1))) "+
							 "where idEmpresa="+idEmpresa+" AND idventa="+pedido;
						Logica.persistir(qry);
						Logica.persistir("delete from ecommerce_import_ventalinea where idEmpresa="+idEmpresa+" AND idVenta="+pedido+" and idArticulo='"+art+"'");
						Logica.logPedido(Long.parseLong(pedido), usu.getNumero(), -1, "Actualizando Orden por eliminacion de Articulo del pedido "+pedido, 0,idEmpresa);
						
						Logica.persistir("delete from ecommerce_pedido_articulos_req where idEmpresa="+idEmpresa+" AND idPedido="+pedido+" and idArticulo='"+art+"' and Deposito="+dep);
						Logica.persistir("delete from ecommerce_pedido_articulos where idEmpresa="+idEmpresa+" AND idPedido="+pedido+" and idArticulo='"+art+"'");
						Logica.logPedido(Long.parseLong(pedido), usu.getNumero(), -1, "Eliminando el Articulo "+art+" del Pedido "+pedido, 0,idEmpresa);						
						
						//MODIFICACION DE ORDEN Y MOVIMIENTOS
						Order order = new Order();
							order.setId(new Long(pedido));
							order.setRemitos(new ArrayList<>());
							order.setDevoluciones(new ArrayList<>());
						DataMovimiento devolucion = new DataMovimiento();
							devolucion.setIdArticulo(art);
							devolucion.setCantidad(Integer.parseInt(canti));
							devolucion.setOrigen(Integer.parseInt(dep));
							devolucion.setDestino(idDepoCentral);
						
						order.getDevoluciones().add(devolucion);
						
						Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
						api.updateOrder(order, idEmpresa);
						
						
						List<Integer> listaHermanos=new ArrayList<>();
						for(DataDetallePedido p:pedidos){
							if(!p.getArticulo().equals(art)){
								int estadoArt=1;
								if (!p.getFechaC().equals(p.getFechaR())){
									estadoArt=2;
									if(p.getCr()==p.getCp()){
										estadoArt=3;
									}
								}
								listaHermanos.add(estadoArt);
							}
						}
						if(listaHermanos.size()>1){
							int estado1 = listaHermanos.get(0);
							for(Integer e:listaHermanos ){
								if(e==estado1){
									cambioEstado=true;
								}
								else{
									cambioEstado=false;
									break;
								}
							}
							if(cambioEstado && estado1>state){
								Logica.updateEcommerceEstado(Long.parseLong(pedido),state+1,idEmpresa,usu);
								state=state+1;
							}
							
						}
						else{
							if(listaHermanos.get(0)>state){
								Logica.updateEcommerceEstado(Long.parseLong(pedido),listaHermanos.get(0),idEmpresa,usu);
								state=listaHermanos.get(0);
							}
						}					
						
						msj +="-Se elimino el Articulo "+art+" <br/>";
						
						if(state==3){
							Long ped=Long.parseLong(pedido);
							if(Logica.procesarOrdenPedido(ped, idEmpresa))
							{
								try
								{				
									String urlFact ="";
									DataIDIDDescripcion factVenta = MSSQL.darIdOrdenVenta(ped+"").get(0);
									
									
									Logica.altaOrdenPedido(ped, factVenta.getId(), factVenta.getIid(),urlFact,idEmpresa);
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
							} 
							
						}
						
						 }
						 catch(Exception e){
							 System.out.println("catch articulo");
							 msj="Error tratando de eliminar el Articulo "+art;
							 request.setAttribute("menError", msj);
							 return mapping.findForward("ok");
						 }			
					
				}
				else{
					msj += "-Los articulos de este pedido no se pueden eliminar porque no figuran en la base de datos <br>";
				}
	}
	else{
		msj += "-Los articulos de este pedido no se pueden eliminar debido a su estado <br>";
		
	}
				
		request.setAttribute("menError", msj);
		List<DataDetallePedido> pedidoMod = Logica.darListaDetallePedidosEcommerce(String.valueOf(pedidos.get(0).getIdPedido()), null, null, null, null, "", null,0,null,"","",idEmpresa,"","",null,true);
		session.setAttribute("pedidoUpdate",pedidoMod);
		return mapping.findForward("ok");
	}
	
		
		
	
	
}
