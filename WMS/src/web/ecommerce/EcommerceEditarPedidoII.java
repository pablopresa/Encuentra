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
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataDetallePedido;
import dataTypes.DataIDDescripcion;
import dataTypes.DataMovimientoStockLocales;
import eCommerce_jsonObjectsII.Cliente;

import beans.Fecha;
import beans.Usuario;
import beans.api.DataMovimiento;
import beans.api.Order;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;
//import clienteWSVS.WSCommunicate;

public class EcommerceEditarPedidoII extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica logica = new Logica();
		Utilidades util = new Utilidades();
		
		Usuario usu = (Usuario) session.getAttribute("uLogeado");
		
		int idEmpresa = util.darEmpresa(usu);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}		
		
		List<DataDetallePedido> pedidos = (ArrayList<DataDetallePedido>)session.getAttribute("pedidoUpdate");
		List<DataIDDescripcion> estadosEncuentra = logica.darListaDataIdDescripcionMYSQLConsulta("select id,descripcion from ecommerce_estado_encuentra where idEmpresa="+idEmpresa);
		List<Integer> estados = new ArrayList<>();
		estados.add(3);
		estados.add(4);
		estados.add(5);
		estados.add(6);
		estados.add(40);
		
		int state=0;
		String nuevoArt;
		int dep=0;
		
		StringBuilder msj = new StringBuilder();
		
		boolean paso = logica.pedidoPasoXEstados(String.valueOf(pedidos.get(0).getIdPedido()),estados, idEmpresa);
	
		if (!paso){	
		
		for(DataDetallePedido original: pedidos){
								
				String param = original.getArticulo();
				nuevoArt = request.getParameter(param);
				String paramI=param+"dep";
				dep = Integer.parseInt(request.getParameter(paramI));
				String paramII=param+"change";
				String isChange= request.getParameter(paramII);
				
				
				
				String consulta = "select 0,descripcion from art_descripcion where idEmpresa="+idEmpresa+" AND id ='"+nuevoArt+"'";
				DataIDDescripcion artDescDescripcion = logica.darDataIdDescripcion(consulta);
				
				if (artDescDescripcion!= null && artDescDescripcion.getDescripcion()!= null)
				{
					
					 if(!original.getArticulo().equals(nuevoArt))
					 {
						
						 try
						 {
							 int idDepoCentral = util.darParametroEmpresaINT(idEmpresa,4);
							 int idDepoWeb = util.darParametroEmpresaINT(idEmpresa,5);
								
							boolean persiste = logica.updateArtEcommerce(original.getIdPedido(), nuevoArt, original.getArticulo(), original.getDeposito(),
									idEmpresa, usu.getNumero(),idDepoCentral,idDepoWeb,original.getDocMovimiento());
							if(persiste) {
								logica.logPedido(original.getIdPedido(), usu.getNumero(), -1, 
										"Cambiando Articulo "+original.getArticulo()+" por "+nuevoArt, 0,idEmpresa);
								
								if(original.getIdEstado()>1){
									logica.updateEcommerceEstado(original.getIdPedido(),1,idEmpresa,usu);
								}
								msj.append("-Se modifico Articulo "+original.getArticulo()+" por "+nuevoArt+" <br/>");
								
								switch (idEmpresa) {
								case 1:
									//PIDO LOS PARES A DEPOSITO
									break;
								case 2:
									
									break;
								case 4:
								case 8:
									
									List<DataIDDescripcion> repo = new ArrayList<>();
									//(idArticulo,cantidad,pedido,solicitud,idEmpresa,NotaArticulo) VALUES 
									//('"+ar.getDescripcion()+"',"+ar.getId()+","+ar.getIdLong()+","+ar.getIdB()+","+idE+",'"+ar.getDescripcionB()+"')
									DataIDDescripcion r = new DataIDDescripcion(original.getCr(),nuevoArt); //cantidad-articulo
									r.setIdLong(original.getIdPedido());
									r.setIdB(original.getDocMovimiento());
									r.setDescripcionB("");
									//r.setDescripcionB(original.getNotaArt());
									
									repo.add(r);
									
									logica.darArticuloRepoFromLoadForus(repo,idDepoWeb,false,idEmpresa,idDepoCentral,2,false);
									int last = logica.darNextSincRepo(idEmpresa)-1;
									logica.actualizarOKSincRepo(0,last,idEmpresa);
									
									//MODIFICACION DE ORDEN Y MOVIMIENTOS
									Order order = new Order();
										order.setId(original.getIdPedido());
										order.setRemitos(new ArrayList<>());
										order.setDevoluciones(new ArrayList<>());
									DataMovimiento remito = new DataMovimiento();
										remito.setIdArticulo(nuevoArt);
										remito.setCantidad(original.getCr());
										remito.setOrigen(idDepoCentral);
										remito.setDestino(idDepoWeb);
										remito.setDescripcion(artDescDescripcion.getDescripcion());
									DataMovimiento devolucion = new DataMovimiento();
										devolucion.setIdArticulo(original.getArticulo());
										devolucion.setCantidad(original.getCr());
										devolucion.setOrigen(idDepoWeb);
										devolucion.setDestino(idDepoCentral);
									
									order.getRemitos().add(remito);
									order.getDevoluciones().add(devolucion);
									
									Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
									api.updateOrder(order, idEmpresa);
									break;
									
								default:
									break;
								}
							}
							else {
								msj.append("Error tratando de modificar el Articulo"+original.getArticulo());
							}							
						 }
						 catch(Exception e){
							 System.out.println("catch articulo");
							 msj.append("Error tratando de modificar el Articulo"+original.getArticulo());
							 request.setAttribute("menError", msj);
							 return mapping.findForward("ok");
						 }
						
					}
					else if(original.getDeposito()!=dep)
					{
						try{
						
						logica.persistir("update ecommerce_pedido_articulos_req set Deposito="+dep+", subestado=0, CantidadProcesasa=0, Confirmado=0,fechaImpreso= CURRENT_TIMESTAMP(), fechaConfirmado=CURRENT_TIMESTAMP(), RTimeStamp=CURRENT_TIMESTAMP(), CTimeStamp=CURRENT_TIMESTAMP(), PTimeStamp=CURRENT_TIMESTAMP(), EnvioTimeStamp=CURRENT_TIMESTAMP(), DespachoTimeStamp=CURRENT_TIMESTAMP(), RetiroTimeStamp=CURRENT_TIMESTAMP() where idEmpresa="+idEmpresa+" AND idPedido="+original.getIdPedido()+" and idArticulo='"+original.getArticulo()+"' and Deposito="+original.getDeposito());
						logica.logPedido(original.getIdPedido(), usu.getNumero(), -1, "El usuario"+usu.getNombre()+" "+usu.getApellido()+" CAMBIO DEPOSITO, pidiendo articulo " +original.getArticulo() + " a deposito "+dep, 0,idEmpresa);
						EnvMail(dep, idEmpresa);
						if(original.getIdEstado()>1){
							logica.updateEcommerceEstado(original.getIdPedido(),1,idEmpresa,usu);
						}
						
						//SI EL DEPOSITO ES 99, VUELCO EL ARTICULO EN REPOSICIONES PENDIENTES
						if(dep==99){
							List<DataIDDescripcion> pedido99 = new ArrayList<>();
							pedido99.add(new DataIDDescripcion(original.getCr(),original.getArticulo()));
							boolean manual = false;
							logica.darArticuloRepoFromLoad(pedido99,71,manual, idEmpresa);
							int last = logica.darNextSincRepo(idEmpresa)-1;
							logica.actualizarOKSincRepo(0,last, idEmpresa);//la dejo en OK 0 para que no interfiera con el picking
						}
						
						msj.append("-Se modifico Deposito "+original.getDeposito()+" por "+dep+" del Articulo "+original.getArticulo()+" <br/>");
						}
						catch(Exception e){
							System.out.println("catch deposito");
							 msj.append("Error tratando de modificar el  Deposito del Articulo "+original.getArticulo());
							 request.setAttribute("menError", msj);
							 return mapping.findForward("ok");
						}
						
						//devolver el movimiento
						//hacerlo de nuevo por el mismo articulo y el nuevo deposito
					}
					else if(isChange!=null){
						try{
							logica.persistir("update ecommerce_pedido_articulos set cambio=1 where idEmpresa="+idEmpresa+" AND idPedido="+original.getIdPedido()+" and idArticulo='"+original.getArticulo()+"'");
							logica.logPedido(original.getIdPedido(), usu.getNumero(), -1, "Marcando el articulo "+original.getArticulo()+" como un cambio", 0,idEmpresa);
							msj.append("-Se marco el articulo "+original.getArticulo()+" como un cambio <br/>");
						}
						catch(Exception e){
							System.out.println("catch cambio");
							 msj.append("Error tratando marcar el Articulo "+original.getArticulo()+" como un cambio");
							 request.setAttribute("menError", msj);
							 return mapping.findForward("ok");
						}
					}
				}
				else{
					msj.append("-El Articulo "+nuevoArt+" no es correcto <br>");
					request.setAttribute("menError", msj);
					return mapping.findForward("ok");
				}
			
			
		}  //FIN FOR
		
	}
	else{
		msj.append("-Los articulos de este pedido no se pueden modificar debido a su estado <br>");
		
	}
	
		String fact = request.getParameter("factura");
		String est = request.getParameter("estado");
		String retiro = request.getParameter("retiro");
		
		int estadoInt=-1;
		for(DataIDDescripcion e: estadosEncuentra){
			if (pedidos.get(0).getEstado().equals(e.getDescripcion()))
			{
				estadoInt=e.getId();
			}
		}
		
		try{
			if (!pedidos.get(0).getUrlFactura().equals(fact)){
			if(!fact.equals("") && !fact.equals("0")){
				//_EncuentraPersistir.persistir("update ecommerce_pedido_factura set idFactura ="+fact+" where idPedido='"+pedidos.get(0).getIdPedido()+"'");
				logica.IngresarFactura(fact, pedidos.get(0).getIdPedido(),idEmpresa);
				logica.logPedido(pedidos.get(0).getIdPedido(),usu.getNumero(),estadoInt,"Se asigno N? de Factura: "+fact,0,idEmpresa);
				msj.append("-Se modifico Id de Factura al "+fact+" <br>");
				
			}
			else{
				msj.append("-Factura no asignada <br>");
			}
		}
		
		
		if (est!=null){
			logica.updateEcommerceEstado(pedidos.get(0).getIdPedido(), 4,idEmpresa,usu);
			//Logica.logPedido(pedidos.get(0).getIdPedido(),usu.getNumero(),4,"El usuario "+usu.getNick()+" modifico el estado del Pedido a Despachado",0,idEmpresa);
			msj.append("-Se modifico el estado del Pedido a Despachado  <br>");
		}
		
		if (retiro!=null){
			logica.ecommerceRetiroFormulario(pedidos.get(0).getIdPedido(), idEmpresa);
			logica.logPedido(pedidos.get(0).getIdPedido(),usu.getNumero(),estadoInt,"Se marco el Pedido para ser retirado mediante la firma de Formulario",0,idEmpresa);
			msj.append("-Se marco el Pedido para ser retirado mediante la firma de Formulario");
		}
		
		}
		catch(Exception e){
			System.out.println("catch articulo");
			 msj.append("Error tratando de modificar datos del Pedido");
			 request.setAttribute("menError", msj.toString());
			System.out.println("catch pedido general");
		}
		
		request.setAttribute("menError", msj.toString());
		List<DataDetallePedido> pedidoMod = logica.darListaDetallePedidosEcommerce(String.valueOf(pedidos.get(0).getIdPedido()), null, null, null, null, "", null,0,null,"","",idEmpresa,"","",null,true);
		session.setAttribute("pedidoUpdate",pedidoMod);
		return mapping.findForward("ok");
	}
	
	public static void EnvMail(int dep, int idEmpresa) throws Exception{
		Logica logica = new Logica();
		/*****************************************html de mails a locales***********************************************/
		String bodyM_1= ""+
		"<p>&nbsp;</p> "+
			"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='padding: 10px 0 30px 0;'> "+
			"	<table style='border: 1px solid #cccccc; border-collapse: collapse;' border='0' width='600' cellspacing='0' cellpadding='0' align='center'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='padding: 40px 0 30px 0; color: #153643; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;' align='center' bgcolor='#70bbd9'> "+
			"	<p>Pedido de articulos facturados por la web</p> "+
			"	</td> "+
			"	</tr> "+
			"	<tr> "+
			"	<td style='padding: 40px 30px 40px 30px;' bgcolor='#ffffff'> "+
			"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='color: #153643; font-family: Arial, sans-serif; font-size: 24px;'><strong>Hola @mail@, estamos necesitando ciertos articulos que hemos vendido y se encuentran en su tienda.</strong></td> "+
			"	</tr> "+
			"	<tr> "+
			"	<td style='padding: 20px 0px 30px; color: #153643; font-family: Arial,sans-serif; font-size: 16px; line-height: 20px; text-align: center;'> "+
			"	<p>A continuaci&oacute;n encontrar&aacute; una lista con los items que se vendieron.<br /> <span style='color: #ff0000;'><strong>El cliente ya los pag&oacute;</strong></span>, solamente necesitamos que nos confirmen que los los envian.</p> "+
			"	<a href='@URL_Confirm@' style='text-decoration: none; color: black'> "+
			"	<div style='padding:9px;background-color: #ee4c50;width: 50%; height: 60px; margin: auto;'> "+
			"	<strong>Confirmar Envio de Articulos</strong> "+
			"	</div> "+
			"	</a> "+
			"	</td> "+
			"	</tr> "+
			"	<tr> "+
			"	<td> "+
			"	<table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td valign='top' width='260'> "+
			"	<table style='text-align: center; border-collapse: collapse;' border='0' width='100%' cellspacing='0' cellpadding='0'> "+
			"	<tbody> "+
			"	<tr> "+
			"	<td style='border: 1px solid silver;'>Imagen del articulo</td> "+
			"	<td style='border: 1px solid silver;'>Nombre del articulo</td> "+
			"	<td style='border: 1px solid silver;'>Cantidad Solicitada</td> "+
			"	<td style='border: 1px solid silver;'>Pedido</td> "+
			"	</tr>";
		
		
		String bodyM_b = "</tbody> "+
					" </table> "+
					" </td> "+
					" </tr> "+
					" </tbody> "+
					" </table> "+
					" </td> "+
					" </tr> "+
					" </tbody> "+
					" </table> "+
					" </td> "+
					" </tr> "+
					" </tbody> "+
					" </table> "+
					" </td> "+
					" </tr> "+
					" <tr> "+
					" <td style='padding: 30px 30px 30px 30px;' bgcolor='#ee4c50'> "+
					" <table border='0' width='100%' cellspacing='0' cellpadding='0'> "+
					" <tbody> "+
					" <tr> "+
					" <td style='color: #ffffff; font-family: Arial, sans-serif; font-size: 14px;' width='75%'>Notificaci&oacute;n generada automaticamente por encuentra<br />No es necesario que responda a esta direcci&oacute;n de correo.</td> "+
					" <td align='right' width='25%'> "+
					" <table border='0' cellspacing='0' cellpadding='0'> "+
					" <tbody> "+
					" <tr> "+
					" <td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
					" <td style='font-size: 0; line-height: 0;' width='20'>&nbsp;</td> "+
					" <td style='font-family: Arial, sans-serif; font-size: 12px; font-weight: bold;'>&nbsp;</td> "+
					" </tr> "+
					" </tbody> "+
					" </table> "+
					" </td> "+
					" </tr> "+
					" </tbody> "+
					" </table> "+
					" </td> "+
					" </tr> "+
					" </tbody> "+
					" </table> "+
					" <!--analytics--> "+
					" <p>&nbsp;</p>";
		/*******************************************fin html*********************************************/
		
		try{
		
		 DataIDDescripcion destino = logica.BuscarAlmacenEcommerce(dep, idEmpresa);
		 
		 List<String> mailsDestino = new ArrayList<>();
			
			String []contactos = destino.getDescripcion().split(",");
			for (int i = 0; i < contactos.length; i++) 
			{
				mailsDestino.add(contactos[i]);
			}
		 
		 String primerDestino = mailsDestino.get(0).split("@")[0];
			bodyM_1 = bodyM_1.replace("@mail@", primerDestino);
				
			PropertiesHelper pH=new PropertiesHelper("urlConfirmEcommerce");
			pH.loadProperties();
			String url_confirm = pH.getValue("url");
				
				
			bodyM_1 = bodyM_1.replace("@URL_Confirm@", url_confirm+dep);
				
			
			String htmlTablasMedio="";
			
			
			htmlTablasMedio+="<!-- ac? iban los Articulos--> "+
					" <tr> "+
					" <td  colspan='4' style='border: 1px solid silver;'>" +
					" ************YA NO SE MUESTRA MAS EL DETALLE************* <br/> POR FAVOR SIGA EL VINCULO PARA VER LOS ARTICULOS" +
					" </td> "+
					" </tr> "+
					" <!--Fin Articulos-->";
				
				//EnviaMail.enviarMailHTMLOD("NotificacionesEncuentra@stadium.local", mailsDestino, "Venta Web Solicita articulos a deposito "+dep, bodyM_1+htmlTablasMedio+bodyM_b);
				
				bodyM_1 = bodyM_1.replace(primerDestino, "@mail@");
				bodyM_1 = bodyM_1.replace(url_confirm+dep, "@URL_Confirm@");
		}
		catch(Exception e){
			System.out.println("LLegue al catch del mail");
		}
	}
	
}
