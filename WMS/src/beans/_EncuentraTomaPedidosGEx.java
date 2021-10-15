package beans;

import helper.PropertiesHelper;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dataTypes.DataIDDescripcion;

public class _EncuentraTomaPedidosGEx 
{
	public _EncuentraTomaPedidosGEx(){}
	
	
	@SuppressWarnings("deprecation")
	public List<String> geneExel (List<DataIDDescripcion> lineasAll)
	{
		 List<String> errores = new ArrayList<>();
		 Fecha fecha = new Fecha();
		 try 
	        {
			 PropertiesHelper pH=new PropertiesHelper("paths");
			 pH.loadProperties();
			 String path = pH.getValue("xlsx");
			 
			 String FILE_NAME = path+"/FORUS_Invent_"+fecha.darFechaAnio_Mes_Dia_hhmmBarra_http()+".xlsx";
			 errores.add("FORUS_Invent_"+fecha.darFechaAnio_Mes_Dia_hhmmBarra_http()+".xlsx");
			 XSSFWorkbook workbook = new XSSFWorkbook();
			 
			        
			 lineasAll.remove(0);
			        
			         
			        
			        
			        
			        	
			        	XSSFSheet sheet = workbook.createSheet("invent");
				        
				        
				        
				        /********************************CABEZAL******************************/
				      //AGREGO LA FILA
				        Row row0 = sheet.createRow((short)0);
				        // Create a cell and put a value in it.
				        //#f2f2f2
				       

				        Cell cell00 = row0.createCell(0);
				        cell00.setCellValue("IdArticulo");
				        
				        
				        Cell cell01 = row0.createCell(1);
				        cell01.setCellValue("Descripcion");
				        
				        
				        Cell cell02 = row0.createCell(2);
				        cell02.setCellValue("Cantidad");
				        
				        XSSFCellStyle style0 = workbook.createCellStyle();
				        style0.setBorderBottom(CellStyle.BORDER_MEDIUM);
				        style0.setBorderTop(CellStyle.BORDER_MEDIUM);
				        style0.setBorderRight(CellStyle.BORDER_MEDIUM);
				        style0.setBorderLeft(CellStyle.BORDER_MEDIUM);
				        style0.setVerticalAlignment(VerticalAlignment.CENTER);
				        style0.setAlignment(HorizontalAlignment.CENTER);
				        style0.setWrapText(true);
				        
				        style0.setFillForegroundColor(new XSSFColor(new java.awt.Color(249, 244, 79)));
						style0.setFillPattern(CellStyle.SOLID_FOREGROUND);
				        
						cell00.setCellStyle(style0);
						cell01.setCellStyle(style0);
					    cell02.setCellStyle(style0);
					   
				        
				        
				        
				        
					  
					    
					    
					    
					    int posTemp=1;
					    for (DataIDDescripcion linea : lineasAll) 
				        {
					    	
					    	//AGREGO LA FILA
					    	Row row = sheet.createRow((short)posTemp);
					    	// Create a cell and put a value in it.
							//#f2f2f2
							Cell cell1 = row.createCell(0);
							cell1.setCellValue(linea.getDescripcion());
						
							Cell cell2 = row.createCell(1);
							cell2.setCellValue(linea.getDescripcionB());
								        
							Cell cell3 = row.createCell(2);
							cell3.setCellValue(linea.getId());
							
							XSSFCellStyle style = workbook.createCellStyle();
							style.setBorderBottom(CellStyle.BORDER_MEDIUM);
							style.setBorderTop(CellStyle.BORDER_MEDIUM);
							style.setBorderRight(CellStyle.BORDER_MEDIUM);
							style.setBorderLeft(CellStyle.BORDER_MEDIUM);
							style.setVerticalAlignment(VerticalAlignment.CENTER);
							style.setAlignment(HorizontalAlignment.CENTER);
							style.setWrapText(true);
							style.setFillForegroundColor(new XSSFColor(new java.awt.Color(208, 220, 234)));
						    style.setFillPattern(CellStyle.SOLID_FOREGROUND);
							
						    cell1.setCellStyle(style);
							cell2.setCellStyle(style);
							cell3.setCellStyle(style);
							
				        posTemp++;
				        }
				        
					    sheet.autoSizeColumn(0);
					    sheet.autoSizeColumn(1);
					    sheet.autoSizeColumn(2);
				        
			        	
						
					
			        
				
				
				
				
	      
	            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
	            workbook.write(outputStream);
	            workbook.close();
	        } 
			catch (Exception e) 
	        {
	            e.printStackTrace();
	        } 
	        
         
		 
		 
		 
		 
		 for (String s: errores) 
		 {
			 System.out.println(s);
		 }
		 
		 
		 
		 return errores;
	}
}
