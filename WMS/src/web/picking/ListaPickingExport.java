package web.picking;

import helper.PropertiesHelper;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import clientesVisual_Store.Std.clienteWSVS_new.Clientes;
import dataTypes.DataArticuloEcommercePedido;
import dataTypes.DataDetallePedido;
import dataTypes.DataIDDescripcion;
import dataTypes.DataMovimientoStockLocales;
import eCommerce_jsonObjectsII.Cliente;
import beans.Fecha;
import beans.ProcessEcommerce;
import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataPicking;
import beans.encuentra.DataPickingS;
import beans.encuentra.IPrint;
import beans.encuentra.LineaTomaPedido;
import beans.encuentra.LineaTomaPedidoTalle;
import beans.encuentra.Ojo;



public class ListaPickingExport extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Logica Logica = new Logica();
		Utilidades util = new Utilidades();
		
		
		
		
		
		
		String consolidar = request.getParameter("consolidaXArt");
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		
				
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
		
		
		
		boolean incluyeDatosCliente = util.darParametroEmpresaBool(idEmpresa, 50);
		
		int idPicking = Integer.parseInt(request.getParameter("pickingWMS"));
		
		System.out.println("");
		List<DataPicking> repos = Logica.encuentraDarPickingWMS(idPicking,idEmpresa);
		
		String print = request.getParameter("print");
		
		if(print!=null && print.equals("1"))
		{
			ImpresionesPDF ip = new ImpresionesPDF();
			
			String path = ip.ImprimirPedidosPickingManual(repos, idEmpresa, idPicking).replace("http://encuentra.200.com.uy/WMS/","");
			
			session.setAttribute("URL", path);
			return mapping.findForward("redir");
			
			
		}
		
		
		String insClientes = "";
		
		
		boolean pri = true;
		for (DataPicking l : repos) 
		{
			if(pri)
			{
				pri = false;
				insClientes+=l.getIdPedido()+"";
			}
			else
			{
				insClientes+=","+l.getIdPedido();
			}
		}
		Hashtable<Long, Cliente> clientesHT = null;
		Hashtable<Long, DataIDDescripcion> destinos = null;
		
		if(incluyeDatosCliente)
	    {
			clientesHT = Logica.darClientesShippingEcommerce(insClientes, idEmpresa);
	    }
		
		destinos = Logica.darDestinosEcommercePedidos(insClientes, idEmpresa);
		
		
		
		EcommerceProcessOrders pro = new EcommerceProcessOrders();
		int idDepo = Integer.parseInt(uLog.getDeposito());
		Hashtable<Long, Long> pedidosSalenCentral = pro.darPedidosSinglDepo(idDepo,idEmpresa, true);
		
		if(consolidar.equals("1"))
		{
			
			Hashtable<String, DataPicking> lineas = new Hashtable<>();
			for (DataPicking l : repos) 
			{
				
				
				
				String direccion = "";
				String localidad = "";
				String departamento = "";
				String obs = "";
				if(incluyeDatosCliente)
			    {
					try
					{
						direccion = clientesHT.get(l.getIdPedido()).getCalle();
						localidad = clientesHT.get(l.getIdPedido()).getCiudad();
						departamento = clientesHT.get(l.getIdPedido()).getDepartamento();
						obs = clientesHT.get(l.getIdPedido()).getTelefono()+" - "+clientesHT.get(l.getIdPedido()).getNombre()+" "+clientesHT.get(l.getIdPedido()).getApellido();
					}
					catch (Exception e) 
					{
						
					}
			    }
				
				
				String key = l.getOrigen().getId()+"-"+l.getDestino().getId()+"-"+l.getArticulo();
				if(lineas.get(key)==null) 
				{
					if(l.getIdPedido()!=null && l.getIdPedido()!=0L)
					{
						l.setJustificacion(l.getIdPedido()+","+l.getSol());
						if(incluyeDatosCliente)
					    {
							l.setDireccion("("+l.getIdPedido()+" - "+direccion+")" );
							l.setLocalidad("("+l.getIdPedido()+" - "+localidad+")" );
							l.setDepartamento("("+l.getIdPedido()+" - "+departamento+")" );
							l.setObs("("+l.getIdPedido()+" - "+obs+")" );
					    }
					}
					l.setConsolidaPedidos(true);
					lineas.put(key, l);
				}
				else
				{
					DataPicking in = lineas.get(key);
					in.setJustificacion(in.getJustificacion()+"-"+l.getIdPedido()+","+l.getSol());
					if(incluyeDatosCliente)
				    {
					
						in.setDireccion( in.getDireccion()+" - ("+l.getIdPedido()+" - "+direccion+")" );
						in.setLocalidad( in.getLocalidad()+" - ("+l.getIdPedido()+" - "+localidad+")" );
						in.setDepartamento( in.getDepartamento()+" - ("+l.getIdPedido()+" - "+departamento+")" );
						in.setObs(in.getObs()+" - ("+l.getIdPedido()+" - "+obs+")" );
				    }
					
					in.setSol(in.getSol()+l.getSol());
					in.setConsolidaPedidos(true);
					lineas.put(key, in);
					
				}
			}
			
			repos = new ArrayList<>(lineas.values());
		}
		else if(incluyeDatosCliente)
	    {
			for (DataPicking l : repos) 
			{
				
				
				
				String direccion = "";
				String localidad = "";
				String departamento = "";
				String obs = "";
				
				try
				{
					direccion = clientesHT.get(l.getIdPedido()).getCalle();
					localidad = clientesHT.get(l.getIdPedido()).getCiudad();
					departamento = clientesHT.get(l.getIdPedido()).getDepartamento();
					obs = clientesHT.get(l.getIdPedido()).getTelefono()+" - "+clientesHT.get(l.getIdPedido()).getNombre()+" "+clientesHT.get(l.getIdPedido()).getApellido();
					
				}
				catch (Exception e) 
				{
					
				}
				
				l.setJustificacion(l.getIdPedido()+","+l.getSol());
				l.setDireccion(direccion);
				l.setLocalidad(localidad);
				l.setDepartamento(departamento);
				l.setObs(obs);
			}
			
		}

		/* Creamos el documento y la primera hoja(Clientes) */
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Lista de picking");
		Fecha f = new Fecha();
		
		
		if(idEmpresa==6)
		{
			Collections.sort(repos);
			/*
			ID PEDIDO
			ID CURRIER
			FECHA
			LOCAL CELULAR NOMBRE MAX 30
			DEPARTAMENTO
			LOCALIDAD
			CODIGO

			OBSERVACIONES
			CANTIDAD
			EMPRESA
			
			pickeada
			verificada
			remitida
			
			*/
			int i = -1;
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 1000);
			sheet.setColumnWidth((short) i++,(short) 2500);
			sheet.setColumnWidth((short) i++,(short) 12000);
			sheet.setColumnWidth((short) i++,(short) 3500);
			sheet.setColumnWidth((short) i++,(short) 3500);
			sheet.setColumnWidth((short) i++,(short) 5000);
			sheet.setColumnWidth((short) i++,(short) 500);
			sheet.setColumnWidth((short) i++,(short) 12000);
			sheet.setColumnWidth((short) i++,(short) 1500);
			sheet.setColumnWidth((short) i++,(short) 1500);
			sheet.setColumnWidth((short) i++,(short) 1500);
			
			
			/* Configuramos  los estilos */
			HSSFFont bold = workbook.createFont();
			bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			HSSFCellStyle styleBold = workbook.createCellStyle();
			styleBold.setFont( bold );
			
			
			int rownum = 0;
			// no lleva cabezal
			
			for (DataPicking obj : repos) 
			{
				HSSFRow row = sheet.createRow(rownum++);
				short cellnum = 0;			    
			    HSSFCell cell = row.createCell(cellnum++);
			    //PEDIDO
			    cellnum = 0;
			    DataIDDescripcion destinoP = destinos.get(obj.getIdPedido());
			    obj.setDestino(destinoP);
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getIdPedido());
			    
			    
			    String dest = "";
			    String comment = "";
			    
			    obj.setObs(obj.getObs().replace("+598", "0"));
			    
			    if (pedidosSalenCentral.containsKey(obj.getIdPedido()))
			    {
			    	  if(obj.getDestino().getId()==30 || obj.getDestino().getId()==40 || obj.getDestino().getId()==50 || obj.getDestino().getId()==60)
					    {
					    	dest+=""+obj.getDestino().getId();
					    	comment = obj.getDireccion() + " "+ obj.getObs();
					    	
					    }
					    else
					    {
					    	dest = "20";
					    	if(obj.getDestino().getDescripcionB().equals("PedidosYa") || obj.getDestino().getDescripcionB().equals("La Isla")) {
					    		comment = obj.getDireccion()+" "+ obj.getObs();
					    	}
					    	else {
					    		comment = obj.getDestino().getDescripcionB()+" "+ obj.getObs();
					    	}
					    	
					    }
			    }
			    else
			    {
			    	dest="158";
			    	comment="WEB "+ obj.getObs();
			    }
			  
			    //ID CURRIER
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(dest);
			    //FECHA
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(f.darFechaDia_Mes_Anio_Barra());
			    //LOCAL CELULAR NOMBRE MAX 30
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(comment);
			    //DEPARTAMENTO
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getDepartamento());
			    //LOCALIDAD
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getLocalidad());
			    //CODIGO
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getArticulo());
			    //LOTE - SIEMPRE VACIO
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue("");
			    //OBSERVACIONES
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getObs());
			    //CANTIDAD
			    cell = row.createCell(cellnum++);
			    cell.setCellValue(obj.getSol());
			    //EMPRESA - simpre es 4
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue("4");
			    //pickeada
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getPick());
			    //verificada
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getVerificada());
			    //remitida
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getRemitida());
			    
			    
			}
			
			
		}
		else
		{

			
			 
			/* Configuramos ancho columna 1, las otras ya quedan bien por defecto */
			int i = -1;
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 15000);
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 2000);
			sheet.setColumnWidth((short) i++,(short) 15000);
			
			
			 
			/* Configuramos  los estilos */
			HSSFFont bold = workbook.createFont();
			bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			HSSFCellStyle styleBold = workbook.createCellStyle();
			styleBold.setFont( bold );
			             
			 				 
			/* Guardamos los datos en el documento */
			int rownum = 0;						
			HSSFRow row = sheet.createRow(rownum++);
			short cellnum = 0;			    
		    HSSFCell cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Origen");
			
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Destino");
					
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Articulo");
			
			
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Descripcion");
		
			
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Cantidad");
			
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Pickeada");
			
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
		    cell.setCellValue("Verificada");
		    
		    cell = row.createCell(cellnum++);
		    cell.setCellStyle( styleBold );
		    cell.setCellValue("Remitidas");
		    
		    cell = row.createCell(cellnum++);
		    cell.setCellStyle( styleBold );
		    cell.setCellValue("Consolidada");
		    
		    cell = row.createCell(cellnum++);
		    cell.setCellStyle( styleBold );
		    cell.setCellValue("PedidosAfecta");
		    
		    
		    
		    cell = row.createCell(cellnum++);
		    cell.setCellStyle( styleBold );
		    cell.setCellValue("Enviar a");
		    
		    if(incluyeDatosCliente)
		    {
		    	cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Direccion");
			    
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Localidad");
			    
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Departamento");
			    
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue(" Obs Nombre y Celular");
		    }
		    
		    
		    
		   
		    
			
			for (DataPicking obj : repos) 
			{
			    row = sheet.createRow(rownum++);
			 
			    cellnum = 0;
			    
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getOrigen().getId());
			    
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getDestino().getId());
			    
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getArticulo());
			    
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getDescripcion());
			    
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getSol());
			    
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getPick());
			    
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getVerificada());
			    
			    cell = row.createCell(cellnum++);				        
			    cell.setCellValue(""+obj.getRemitida());
			    
			    cell = row.createCell(cellnum++);
			    if(obj.isConsolidaPedidos())
			    {
			    	cell.setCellValue("1");
			    	
			    	cell = row.createCell(cellnum++);	
			    	cell.setCellValue(""+obj.getJustificacion());
			    }
			    else
			    {
			    	cell.setCellValue("0");
			    	
			    	cell = row.createCell(cellnum++);	
			    	cell.setCellValue(""+obj.getIdPedido());
			    }
			    
			    if(pedidosSalenCentral.get(obj.getIdPedido())==null)
			    {
			    	//esto quiere decir que hay que mandarlo a ecommerce
			    	cell = row.createCell(cellnum++);				        
				    cell.setCellValue("Ecommerce");
			    }
			    else
			    {
			    	//hay que procesarlo y sacarlo
			    	cell = row.createCell(cellnum++);
			    	String destino = "Procesar";
			    	try
			    	{
			    		
			    		destino = destinos.get(obj.getIdPedido()).getDescripcionB();
			    	}
			    	catch (Exception e) 
			    	{
						
					}
				    cell.setCellValue(destino);
			    }
			    
			    
			    
			    
			    
			    if(incluyeDatosCliente)
			    {
			    	cell = row.createCell(cellnum++);				        
				    cell.setCellValue(""+obj.getDireccion());
				    
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(""+obj.getLocalidad());
				    
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(""+obj.getDepartamento());
				    
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(""+obj.getObs());
			    }
			    
			    			        
			    

			    
			   }
			
		}
		
		
		
		
		
		/* Guardamos el archivo, en este caso lo devolvemos por un servlet */
		
		
		response.addHeader("Content-disposition", "inline; filename=EncuentraWMS_pickingList_"+f.darFechaAnio_Mes_Dia_hhmm()+".xls"); 
		response.setHeader("charset", "iso-8859-1");
		response.setContentType("application/octet-stream");
		response.setStatus(HttpServletResponse.SC_OK);
		            
		OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        
		return mapping.findForward("ok");
	}
	
}
