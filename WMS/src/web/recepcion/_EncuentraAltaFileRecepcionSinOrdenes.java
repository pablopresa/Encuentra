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

import beans.Usuario;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;


public class _EncuentraAltaFileRecepcionSinOrdenes extends Action 
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
		
		String provedor = "";
		
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
			 
			 List<VORecepcionSinOrden> listaSort = new ArrayList<>();
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
						if (fieldname.contentEquals("provedor")) 
							if(!item.getString().equals(""))
								provedor = item.getString();
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
				                	
				                		String articulo = ""; //0
				                		int cantidad = 0;//1
				                		int cantBultos = 0;//2
				                		int cantTotal = 0;//3
		
					                    Cell artCel = row.getCell(0);
					                    Cell cantidadCell = row.getCell(1);
					                    Cell bultosCell = row.getCell(2); 
					                    Cell cantidadTotalCell = row.getCell(3);
					                   
					                    try
					                    {
					                    	articulo = artCel.getStringCellValue().trim();
					                    }
					                    catch(Exception e)
					                    {
					                    	articulo = "NO IDENTIFICADO";
					                    }
					                    
					                    try
					                    {
					                    	cantidad = Integer.parseInt(cantidadCell.getStringCellValue());
					                    }
					                    catch(Exception e)
					                    {
					                    	cantidad = (int) cantidadCell.getNumericCellValue();
					                    }	
					                    
					                    try
					                    {
					                    	cantBultos = Integer.parseInt(bultosCell.getStringCellValue());
					                    }
					                    catch(Exception e)
					                    {
					                    	cantBultos = (int) bultosCell.getNumericCellValue();
					                    }	
					                    
					                    try
					                    {
					                    	cantTotal = Integer.parseInt(cantidadTotalCell.getStringCellValue());
					                    }
					                    catch(Exception e)
					                    {
					                    	cantTotal = (int) cantidadTotalCell.getNumericCellValue();
					                    }	
					                    
					                   VORecepcionSinOrden linea = new VORecepcionSinOrden(articulo,cantidad,cantBultos,cantTotal);
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
				
				 session.setAttribute("ordenesAux", listaSort);
		         session.setAttribute("provedor", provedor);
				
				return mapping.findForward("ok");
			}
			else
			{
				return mapping.findForward("error");
			}
		
	}
}
