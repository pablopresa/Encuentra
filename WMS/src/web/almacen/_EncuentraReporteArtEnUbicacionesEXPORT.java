package web.almacen;



import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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

import beans.Usuario;
import beans.encuentra.DataOjoArticulo;


public class _EncuentraReporteArtEnUbicacionesEXPORT extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception 
	{
		HttpSession session = request.getSession();
		Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		
		try{	
			
			List<DataOjoArticulo> lista= (List<DataOjoArticulo>) session.getAttribute("listaUbicaciones");

			/* Creamos el documento y la primera hoja(Clientes) */
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Reporte Articulos en Ubicaciones");

			/* Configuramos ancho columna 1, las otras ya quedan bien por defecto */
			sheet.setColumnWidth((short) 0,(short) 5000);

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
			cell.setCellValue("Estanteria");
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Descripción");
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Estante");
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Modulo");
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Cod. Ubicación");
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Articulo");
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Cantidad");
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Stock Visual");
			cell = row.createCell(cellnum++);
			cell.setCellStyle( styleBold );
			cell.setCellValue("Actualizado");
			

			for (DataOjoArticulo obj : lista) {
				row = sheet.createRow(rownum++);

				cellnum = 0;
				
				cell = row.createCell(cellnum++);				        
				cell.setCellValue(obj.getIdSector());
				
				cell = row.createCell(cellnum++);				        
				cell.setCellValue(obj.getDescripcion());
				
				cell = row.createCell(cellnum++);				        
				cell.setCellValue(obj.getModulo());	//Estante
				
				cell = row.createCell(cellnum++);				        
				cell.setCellValue(obj.getEstante()); //Modulo

				cell = row.createCell(cellnum++);				        
				cell.setCellValue(obj.getIdOjo());
				
				cell = row.createCell(cellnum++);				        
				cell.setCellValue(obj.getArticulo());
				
				cell = row.createCell(cellnum++);				        
				cell.setCellValue(obj.getCantidad());
				
				cell = row.createCell(cellnum++);				        
				cell.setCellValue(obj.getStock());
				
				cell = row.createCell(cellnum++);				        
				cell.setCellValue(obj.getFechaupdated());

			}

			/* Guardamos el archivo, en este caso lo devolvemos por un servlet */
			response.setContentType("localhost/desktop");
			response.addHeader("Content-disposition", "inline; filename=" + URLEncoder.encode("reporteArticulosEnUbicaciones.xls", "UTF-8"));                  
			OutputStream os = response.getOutputStream();

			workbook.write(os);

			os.flush();
			os.close(); 
			
			uLog.registrarEventoMin(session.getId(), "Usuario exporta a Excel reporte articulos en ubicaciones");

			return mapping.findForward("ok");
		}
		catch (Exception e) {
			System.out.println("error");
			uLog.registrarEventoMin(session.getId(), "Error al exportar reporte articulos en ubicaciones");
			return mapping.findForward("ok");
		}


	}

}