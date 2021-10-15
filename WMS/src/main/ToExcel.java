package main;


import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ToExcel 
{
	private static final String FILE_NAME = "C:\\imagenes\\out\\MyFirstExcel.xlsx";
	public static void main(String[] args) {

		 try 
	        {

			XSSFWorkbook workbook = new XSSFWorkbook();
			 
	        XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
	       /*
	        Object[][] datatypes = {
	                {"Datatype", "Type", "Size(in bytes)"},
	                {"int", "Primitive", 2},
	                {"float", "Primitive", 4},
	                {"double", "Primitive", 8},
	                {"char", "Primitive", 1},
	                {"String", "Non-Primitive", "No fixed size"}
	        };
	
	        int rowNum = 0;
	        System.out.println("Creating excel");
	        sheet.addMergedRegion(new CellRangeAddress(
	                1, //first row (0-based)
	                4, //last row  (0-based)
	                0, //first column (0-based)
	                0  //last column  (0-based)
	        ));
	        
	        
	        for (Object[] datatype : datatypes) 
	        {
	            Row row = sheet.createRow(rowNum++);
	            int colNum = 0;
	            for (Object field : datatype) 
	            {
	                Cell cell = row.createCell(colNum++);
	                if (field instanceof String) 
	                {
	                    cell.setCellValue((String) field);
	                } 
	                else if (field instanceof Integer) 
	                {
	                    cell.setCellValue((Integer) field);
	                }
	            }
	           
	        }
	        */
	        final FileInputStream stream =   new FileInputStream( "C:\\imagenes\\out\\4139396\\4139396_0555_C_.jpg" );
	        final CreationHelper helper = workbook.getCreationHelper();
	        final Drawing drawing = sheet.createDrawingPatriarch();
	
	        final ClientAnchor anchor = helper.createClientAnchor();
	        
	        anchor.setDx1(2 * XSSFShape.EMU_PER_PIXEL);
	        anchor.setDy1(2 * XSSFShape.EMU_PER_PIXEL);
	        
	        //anchor.setAnchorType( ClientAnchor.MOVE_AND_RESIZE );
	
	
	        final int pictureIndex =   workbook.addPicture(IOUtils.toByteArray(stream), Workbook.PICTURE_TYPE_JPEG);
	
	        sheet.addMergedRegion(new CellRangeAddress(
	                1, //first row (0-based)
	                8, //last row  (0-based)
	                0, //first column (0-based)
	                2  //last column  (0-based)
	        ));
	        
	        anchor.setCol1(0);
	        anchor.setRow1(1); // same row is okay
	        anchor.setRow2(4);
	        //anchor.setCol2(0);
	        final Picture pict = drawing.createPicture( anchor, pictureIndex );
	        pict.resize(2.60);
	        //sheet.autoSizeColumn(0);
	        
	       
      
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } 
		catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
         
        System.out.println("Done");
    }

	
}
