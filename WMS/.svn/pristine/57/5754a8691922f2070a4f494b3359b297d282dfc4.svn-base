package web.ecommerce;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import logica.Utilidades;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import dataTypes.DataDetallePedido;

import beans.Usuario;

public class monitorPedidosExport extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		HttpSession session = request.getSession();
		Utilidades util = new Utilidades();
		
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");		
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
				
		String cons= request.getParameter("aConsolidar");
		boolean consolida = false;
		try {
			if(cons!=null && cons.equals("true")) {
				consolida=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<DataDetallePedido> pedidos = (List<DataDetallePedido>) session.getAttribute("lstPedidosConsola");
		
		/* Creamos el documento y la primera hoja(Clientes) */
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Monitor Ecommerce");
		
		 
		/* Configuramos ancho columna 1, las otras ya quedan bien por defecto */
		int i = -1;
		sheet.setColumnWidth((short) i++,(short) 2000);
		sheet.setColumnWidth((short) i++,(short) 2000);
		sheet.setColumnWidth((short) i++,(short) 2000);
		sheet.setColumnWidth((short) i++,(short) 2000);
		sheet.setColumnWidth((short) i++,(short) 10000);
		sheet.setColumnWidth((short) i++,(short) 8000);
		sheet.setColumnWidth((short) i++,(short) 3000);
		sheet.setColumnWidth((short) i++,(short) 3000);
		sheet.setColumnWidth((short) i++,(short) 3000);
		sheet.setColumnWidth((short) i++,(short) 3000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 8000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 3000);
		sheet.setColumnWidth((short) i++,(short) 8000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 2000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 3000);
		sheet.setColumnWidth((short) i++,(short) 8000);
		sheet.setColumnWidth((short) i++,(short) 6000);
		sheet.setColumnWidth((short) i++,(short) 8000);
		sheet.setColumnWidth((short) i++,(short) 8000);
		sheet.setColumnWidth((short) i++,(short) 8000);
		sheet.setColumnWidth((short) i++,(short) 20000);
		sheet.setColumnWidth((short) i++,(short) 3000);
		sheet.setColumnWidth((short) i++,(short) 3000);
		
		 
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
		cell.setCellValue("Pedido");
		cell = row.createCell(cellnum++);
		cell.setCellStyle( styleBold );
		cell.setCellValue("Venta");
		cell = row.createCell(cellnum++);
		cell.setCellStyle( styleBold );
		cell.setCellValue("SubOrder");
		cell = row.createCell(cellnum++);
		cell.setCellStyle( styleBold );
		cell.setCellValue("Cliente");
		cell = row.createCell(cellnum++);
		cell.setCellStyle( styleBold );
	    cell.setCellValue("Estado Encuentra");
	    cell = row.createCell(cellnum++);
	    
	    if(!consolida) {
	    	cell.setCellStyle( styleBold );
	 	    cell.setCellValue("Articulo");
	 	    cell = row.createCell(cellnum++);
	 	    cell.setCellStyle( styleBold );
	 	    cell.setCellValue("Deposito pedido");
	 	    cell = row.createCell(cellnum++);
	    }
	    else {	    	 
	 	    cell.setCellStyle( styleBold );
	 	    cell.setCellValue("Cant. Depositos");
	 	    cell = row.createCell(cellnum++);
	    }
	   
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Unidades pedidas");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Unidades confirmadas");
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
	    cell.setCellValue("Usuario procesa");
	    cell = row.createCell(cellnum++);
	    if(consolida) {
		    cell.setCellStyle( styleBold );
		    cell.setCellValue("Fecha Cierre pedido");
		    cell = row.createCell(cellnum++);
	    }
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Fecha despacho");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Fecha entrega");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Forma entrega");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Destino");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Estado ecommerce");
	    cell = row.createCell(cellnum++);
	    
	    
	    	cell.setCellStyle( styleBold );
		    cell.setCellValue("Consolidar");
		    cell = row.createCell(cellnum++);
	    
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Canal");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Tracking");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Importe");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Email");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Telefono");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Observaciones");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Departamento");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Localidad");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Direccion");
	    cell = row.createCell(cellnum++);
	    cell.setCellStyle( styleBold );
	    cell.setCellValue("Cod. Postal");
		
		for (DataDetallePedido obj : pedidos) {
		    row = sheet.createRow(rownum++);
		 
		    cellnum = 0;
		    
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(""+obj.getIdPedido());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getIdMS());	
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getSubpedido());	
		    cell = row.createCell(cellnum++);
		    String cli = obj.getDescripcion().replace("<br>", " ");
		    cli = cli.substring(0,cli.length()-5);
		   	cell.setCellValue(cli);		    
		    
		     cell = row.createCell(cellnum++);
		    cell.setCellValue(obj.getEstado().replace("<br>", " "));
		     		    
		    if(!consolida) {
		    	cell = row.createCell(cellnum++);				        
			    cell.setCellValue(obj.getArticulo().replace("<br>", " "));
			    
		    }
		     
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getDeposito());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getCr());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getCc());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getCp());
		    
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getFechaR().replace("<br>", " "));
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getFechaC().replace("<br>", " "));
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getFechaP().replace("<br>", " "));
		    
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getUsuClasifica().replace("<br>", " "));
		    if(consolida) {
		    	cell = row.createCell(cellnum++);				
		    	cell.setCellValue(obj.getFechaCierre().replace("<br>", " "));
			} 
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getFechaDespacho().replace("<br>", " "));
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getFechaEntrega().replace("<br>", " "));
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getShippingType().getDescripcion());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getDestinoNombre().replace("<br>", " "));
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getEstadoEcommerce());
		    
		    	cell = row.createCell(cellnum++);				        
			    cell.setCellValue(obj.getaConsolidar());
		    		    
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getIdCanal());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getTracking());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getImporte());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getMail());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getTel());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getObs());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getDpto());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getLocalidad());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getCalle());
		    cell = row.createCell(cellnum++);				        
		    cell.setCellValue(obj.getCodPostal());
		    
		   }
		
		
		/* Guardamos el archivo, en este caso lo devolvemos por un servlet */
		response.addHeader("Content-disposition", "inline; filename=MonitorEcommerce.xls"); 
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
        
		session.setAttribute("lstPedidosConsola", new ArrayList<>());
		return mapping.findForward("ok");
	}
	
}
