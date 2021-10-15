package web.ecommerce;



import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import logica.Logica;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataDetallePedido;


public class _EncuentraConsolaExport extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
			{
				HttpSession session = request.getSession();
 Logica Logica = new Logica();
				
				List<DataDetallePedido> pedidos = (List<DataDetallePedido>) session.getAttribute("lstPedidosConsola");
				
				/* Creamos el documento y la primera hoja(Clientes) */
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("Monitor Ecommerce");
				
				 
				/* Configuramos ancho columna 1, las otras ya quedan bien por defecto */
				sheet.setColumnWidth((short) 0,(short) 4000);
				sheet.setColumnWidth((short) 1,(short) 8000);
				sheet.setColumnWidth((short) 2,(short) 6000);
				sheet.setColumnWidth((short) 3,(short) 3000);
				sheet.setColumnWidth((short) 4,(short) 3000);
				sheet.setColumnWidth((short) 5,(short) 5000);
				sheet.setColumnWidth((short) 6,(short) 6000);
				sheet.setColumnWidth((short) 7,(short) 3000);
				sheet.setColumnWidth((short) 8,(short) 3000);
				sheet.setColumnWidth((short) 9,(short) 3000);
				sheet.setColumnWidth((short) 10,(short) 6000);
				sheet.setColumnWidth((short) 11,(short) 6000);
				sheet.setColumnWidth((short) 12,(short) 6000);
				sheet.setColumnWidth((short) 13,(short) 3000);
				sheet.setColumnWidth((short) 14,(short) 3000);
				
				 
				/* Configuramos  los estilos */
				HSSFFont bold = workbook.createFont();
				bold.setBoldweight(Font.BOLDWEIGHT_BOLD);
				HSSFCellStyle styleBold = workbook.createCellStyle();
				styleBold.setFont( bold );
				             
				 				 
				/* Guardamos los datos en el documento */
				int rownum = 0;						
				HSSFRow row = sheet.createRow(rownum++);
				short cellnum = 0;			    
			    HSSFCell cell = row.createCell(cellnum++);
				cell.setCellStyle( styleBold );
				cell.setCellValue("Pedido");
				cell = row.createCell(cellnum++);
				cell.setCellStyle( styleBold );
				cell.setCellValue("Cliente");
				cell = row.createCell(cellnum++);
				cell.setCellStyle( styleBold );
			    cell.setCellValue("Estado");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Orden");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Factura");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("F. Pago");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Articulo");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Deposito pedido");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Unidades pedidas");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Unidades procesadas");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Fecha pedido");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Fecha confirmado");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Fecha procesado");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Destino");
			    cell = row.createCell(cellnum++);
			    cell.setCellStyle( styleBold );
			    cell.setCellValue("Canal");
				
				for (DataDetallePedido obj : pedidos) {
				    row = sheet.createRow(rownum++);
				 
				    cellnum = 0;
				    
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(""+obj.getIdPedido());
				    
				    cell = row.createCell(cellnum++);
				    if(obj.getDescripcion()!=null){
				    	String cliente = obj.getDescripcion().replace("<br/>", " ");					    				        
					    cell.setCellValue(""+cliente);
				    }
				    else{
				    	cell.setCellValue("");
				    }
				    
				     cell = row.createCell(cellnum++);
				    cell.setCellValue(obj.getEstado());
				     cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getIdOrden());
				     cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getIdFactura());
				    
				    cell = row.createCell(cellnum++);			
				    if(obj.getFormaPago().contains("-")){
				    	cell.setCellValue("MERCADO LIBRE");
				    }
				    else{
				    	cell.setCellValue(obj.getFormaPago());
				    }
				     	        
				    
				     cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getArticulo());
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getDeposito());
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getCr());
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getCp());
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getFechaR());
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getFechaC());
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getFechaP());
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getIdDestino());
				    cell = row.createCell(cellnum++);				        
				    cell.setCellValue(obj.getIdCanal());
				        
				    }
				
				 
				/* Guardamos el archivo, en este caso lo devolvemos por un servlet */
				response.setContentType("localhost/desktop");
				response.addHeader("Content-disposition", "inline; filename=" + URLEncoder.encode("MonitorEcommerce.xls", "UTF-8"));                  
				OutputStream os = response.getOutputStream();
				                         
				workbook.write(os);
				             
				os.flush();
				os.close(); 
				
				
				return mapping.findForward("ok");
			}
				
				
				
				
			
	
	
		
		}

