package web.recepcion;

import helper.PropertiesHelper;
import logica.Logica;
import logica.Utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Menu;
import beans.Usuario;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;


public class _EncuentraAltaFileFacturados extends Action 
{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession(true);
     	
     	Logica Logica = new Logica();
     	Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
			String nombreArchivo = null;
			//Ruta donde se guardara el fichero
			 PropertiesHelper pH=new PropertiesHelper("paths");
			 pH.loadProperties();
			 String path = pH.getValue("xlsx");
			 
			 File destino=new File(path);
			
			 if (!destino.exists())
			{
				//EN TEST:
				destino=new File(path);
				destino.mkdir(); 
				
			}
			 
			 List<DataIDDescripcion> listaSort = new ArrayList<>();
			//destino.delete();
			
			
			 //destino=new File("C:/");
			// Convertimos el HTTPRequest en un ContextRequest,
			// este paso es necesario en la ultima version,
			// ya que los metodos de las versiones anteriores
			// se han quedado desfasados.
			ServletRequestContext src=new ServletRequestContext(request);
			
			//Si el formulario es enviado con Multipart
			if(FileUploadBase.isMultipartContent(src))
			{
				//Necesario para evitar errores de NullPointerException
				DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
				//Creamos un FileUpload
				ServletFileUpload upload=new  ServletFileUpload(factory);
				//Procesamos el request para que nos devuelva una lista
				//con los parametros y ficheros.
				List lista = upload.parseRequest(src);
				File file= null;
				//Recorremos la lista.
				Iterator it = lista.iterator();
				List <String> strings = new ArrayList<>();
				while(it.hasNext())
				{
					//Rescatamos el fileItem
					FileItem item=(FileItem)it.next();
					//Comprobamos si es un campo de formulario
					if(item.isFormField())
					{
						String fieldname = item.getFieldName();
					}	
					else
					{
						//Si no, es un fichero y lo subimos al servidor.
						//Primero creamos un objeto file a partir del nombre del fichero.
						file=new File(item.getName());
						nombreArchivo = item.getName();
						//Lo escribimos en el disco
						// usando la ruta donde se guardara el fichero
						// y cogiendo el nombre del file
						// Nota: Se podria hacer usando el objeto item en vez del file directamente
						// Pero esto puede causar incompatibilidades segun que navegador, ya que 
						// algunos solo pasan el nombre del fichero subido, pero otros
						// como Iexplorer, pasan la ruta absoluta, y esto crea un peque?o problema al escribir
						// el fichero en el servidor.
						item.write(new File(destino,file.getName()));
						
						
						int pasada= 0;
						
						
			            FileInputStream fis = new FileInputStream(destino+"\\"+file.getName());
		
			            // Finds the workbook instance for XLSX file
			            XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
			           
			            // Return first sheet from the XLSX workbook
			            Iterator <Sheet> sheetIterator = myWorkBook.iterator();
			            while (sheetIterator.hasNext())
			            {
			            	Sheet mySheet = sheetIterator.next();
			            	// Get iterator to all the rows in current sheet
				            Iterator<Row> rowIterator = mySheet.iterator();
				            // Traversing over each row of XLSX file
				            while (rowIterator.hasNext()) 
				            {
				                Row row = rowIterator.next();
		
				                try
				                {
				                	if(pasada>0)
				                	{
					                    Long orden = 0l;//0
				                		String factura = "0";//1
		
					                    Cell ordenCel = row.getCell(0);
					                    Cell facturaCel = row.getCell(1);
					                   
					                    try
					                    {
					                    	orden = (new Double(ordenCel.getNumericCellValue())).longValue();
					                    }
					                    catch(Exception e)
					                    {
					                    	orden = (long) Integer.parseInt(ordenCel.getStringCellValue());
					                    }
					                    
					                    try
					                    {
					                    	factura = (int) facturaCel.getNumericCellValue()+"";
					                    }
					                    catch(Exception e)
					                    {
					                    	factura = Integer.parseInt(facturaCel.getStringCellValue())+"";
					                    }	
		
					                   DataIDDescripcion linea = new DataIDDescripcion(orden,factura);
					                   listaSort.add(linea);
		  
				                	
				                	}
					                
				                }
				                catch(Exception e)
				                {
				                	e.printStackTrace();
				                	
					                Iterator<Cell> cellIterator = row.cellIterator();
					                while (cellIterator.hasNext()) {
		
					                    Cell cell = cellIterator.next();
		
					                    switch (cell.getCellType()) {
					                    case Cell.CELL_TYPE_STRING:
					                        System.out.print(cell.getStringCellValue() + "\t");
					                        break;
					                    case Cell.CELL_TYPE_NUMERIC:
					                        System.out.print(cell.getNumericCellValue() + "\t");
					                        break;
					                    case Cell.CELL_TYPE_BOOLEAN:
					                        System.out.print(cell.getBooleanCellValue() + "\t");
					                        break;
					                    default :
					                 
					                    }
					                }
				                }
				                
			            	pasada++;
			            	
			            }
			            
				            file.delete();
				            item.delete();
			                
			            }   
			            
			           
					}
					
				}
				
				long pedido;
				boolean persisti = false;
				boolean sinFallos = true;
				String error = "No se pudieron asociar las siguientes facturas: ";
				 
				for (DataIDDescripcion ls : listaSort)
				{
					
					try {
				         pedido = ls.getIdLong();
				         persisti = Logica.IngresarFactura(ls.getDescripcion(), pedido,idEmpresa);
						 Logica.logPedido(pedido,uLog.getNumero(),-1,"Se asigno N? de Factura: "+ls.getDescripcion()+" mediante Excel",0,idEmpresa);
						 System.out.println("-Se modifico factura del pedido " + ls.getDescripcion());  
					} catch (NumberFormatException nfe) {
				         System.out.println("NumberFormatException: " + nfe.getMessage());
				         System.out.println("-Fallo al modificar factura del pedido " + ls.getIdLong()); 
				    }
					
					if (!persisti) 
					{
						error += ls.getDescripcion() + " - ";
					    sinFallos = false;
					}
				}
				
				if(!sinFallos)
					session.setAttribute("menError", error);
				else
					session.setAttribute("menError", "facturas asociadas correctamente.");
				
				
			}
			return mapping.findForward("ok");
	}
}
