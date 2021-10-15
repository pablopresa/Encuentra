package web.picking;

import helper.PropertiesHelper;
import logica.Logica;
import logica.Utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.gargoylesoftware.htmlunit.javascript.host.Map;

import beans.Usuario;
import beans.encuentra.DataArticulo;
import beans.encuentra.DepositoAdmin;
import beans.encuentra.ValueObjects.VOPickingManual;
import beans.encuentra.ValueObjects.VORecepcionSinOrden;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;


public class _EncuentraPickingManual extends Action 
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
		boolean bandera = true;
		String mensajeResultado = "";
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
			 
			 List<VOPickingManual> listaSort = new ArrayList<VOPickingManual>();
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
				List <String> strings = new ArrayList<String>();
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
						// como Iexplorer, pasan la ruta absoluta, y esto crea un pequeño problema al escribir
						// el fichero en el servidor.
						try {
						item.write(new File(destino,file.getName()));
						} catch (Exception e) {
							request.setAttribute("menError", "Ingrese archivo");
		    				return mapping.findForward("ok");
						}
						
						
						int pasada= 1;
						
						
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
				                	if(pasada>1)
				                	{
				                		
				                		String articulo = ""; //0
				                		int destinoArchivo = -5; //1
				                		int cantidad = 0;//2
				                		String justificacion = "";//3
				                		int pedido = 0;//4
		
					                    Cell artCel = row.getCell(0);
					                    Cell destinoCell = row.getCell(1);
					                    Cell cantidadCell = row.getCell(2); 
					                    Cell justificacionCell = row.getCell(3);
					                    Cell pedidoCell = row.getCell(4);
					                    
					                    
					                    if(artCel == null) { // Si linea vacia , no hago nada
					                    
					                    } else {	
					                   
						                    try
						                    {	                    	
						                    	articulo = artCel.getStringCellValue().trim();
						                    	DataArticulo dataArticulo = Logica.encuentraCodArticulo(articulo, idEmpresa);
						                    	if ( dataArticulo == null) {
						                    		throw new Exception ("Articulo inexistente");
						                    	} else {
						                    		if ( dataArticulo.getId() == null) {
						                    			throw new Exception ("Articulo inexistente");
						                    		}
						                    	}

						                    }
						                    catch(Exception e)
						                    {
						                    	
						                    	mensajeResultado="Error, no existe el articulo "+articulo+". Linea "+pasada;
						                    	bandera=false;
						                    	break;
						                    }
						                    
						                    try
						                    {
						                    	destinoArchivo = (int)destinoCell.getNumericCellValue(); //Cannot get a NUMERIC value from a STRING cell
						                    	DepositoAdmin existeDepo = Logica.BuscarDeposito(destinoArchivo, idEmpresa);
						                    	if (existeDepo == null) {
						                    		throw new Exception ("Destino inexistente");
						                    	}   else {
						                    		if ( existeDepo.getNombre() == null) {
						                    			throw new Exception ("destino inexistente");
						                    		}  
						                    	}
						                    }
						                    catch(Exception e)
						                    {
						                    	try {
						                    		 destinoArchivo =Integer.parseInt(destinoCell.getStringCellValue());
						                    		 DepositoAdmin existeDepo = Logica.BuscarDeposito(destinoArchivo, idEmpresa);
								                    	if (existeDepo == null) {
								                    		throw new Exception ("Destino inexistente");
								                    	}   else {
								                    		if ( existeDepo.getNombre() == null) {
								                    			throw new Exception ("destino inexistente");
								                    		}  
								                    	}
						                    	} catch (Exception ee) {
						                    		mensajeResultado="El destino "+destinoCell+" no existe. Linea "+pasada;
							                    	bandera=false;
							                    	break;
						                    	}
						                    //	if(destinoArchivo == -5) {
							                //    	mensajeResultado="El destino "+destinoCell+" no existe. Linea "+pasada;
							                //    	bandera=false;
							                //    	break;
						                    //	}
						                    }	
						                    
						                    try
						                    {
						                    	cantidad = (int) cantidadCell.getNumericCellValue();
						                    	if (cantidad < 1) {
						                    		throw new Exception("Cantidad negativa");
						                    	}
						                    }
						                    catch(Exception e)
						                    {
						                    	mensajeResultado="Las cantidades deben ser positivas. Linea "+pasada;
						                    	bandera=false;
						                    	break;
						                    }	
						                    
						                    try
						                    {
						                    	justificacion = justificacionCell.getStringCellValue().trim();
						                    }
						                    catch(Exception e)
						                    {
						                    	justificacion = "";
						                    }	
						                    
						                    try
						                    {
						                    	pedido = (int)(pedidoCell.getNumericCellValue());
						                    	if (pedido < 0) {
						                    		throw new Exception("Pedido negativa");
						                    	}
						                    }
						                    catch(Exception e)
						                    {
						                    	
						                    	mensajeResultado="Error en el pedido "+pedido+". Linea "+pasada;
						                    	bandera = false;
						                    	break;
						                    }	
						                    
						                    if(!articulo.equals("") && articulo != null) {
						                    	VOPickingManual linea = new VOPickingManual(articulo,destinoArchivo,cantidad,justificacion,pedido);
						                    	listaSort.add(linea);
						                    }
					                	}
				                	}
						                
					                }
					                catch(Exception e)
					                {
					                	request.setAttribute("menError", "Error, intente nuevamente");
					    				return mapping.findForward("ok");	                
					                	
				                    }    
				            	pasada++;
			            	
			            }
			            
				            file.delete();
				            item.delete();
				       
				            
			            } // fin while sheet   
			            
			           
					}
					
				}
				
			if (bandera) {	
				HashMap <Integer, List<DataIDDescripcion>> reposiciones = new HashMap<Integer, List<DataIDDescripcion>>();
	            
	            for (VOPickingManual pickingManual : listaSort) {
	            	if (reposiciones.get(pickingManual.getDestinoArchivo()) == null) {
	            		List<DataIDDescripcion> listaDataID = new ArrayList<>();
	            		DataIDDescripcion DID = new DataIDDescripcion(pickingManual.getCantidad(),pickingManual.getIdArticulo());
	            		DID.setIdB(pickingManual.getPedido());
	            		DID.setIdLong(Long.parseLong(pickingManual.getPedido()+""));
	            		DID.setDescripcionB(pickingManual.getJustificacion());
	            	    listaDataID.add(DID);
	            	    reposiciones.put(pickingManual.getDestinoArchivo(), listaDataID);
	            	} else {
	            		DataIDDescripcion DID = new DataIDDescripcion(pickingManual.getCantidad(),pickingManual.getIdArticulo());
	            		DID.setIdB(pickingManual.getPedido());
	            		DID.setIdLong(Long.parseLong(pickingManual.getPedido()+""));
	            		DID.setDescripcionB(pickingManual.getJustificacion());
	            		reposiciones.get(pickingManual.getDestinoArchivo()).add(DID);
	            	}
	            }
	            				            
	            for (HashMap.Entry<Integer,List<DataIDDescripcion>> entry : reposiciones.entrySet()) {
	            	Logica.darArticuloRepoFromLoadForus(entry.getValue(),entry.getKey(),true,idEmpresa,Integer.parseInt(uLog.getDeposito()),3,false);
	            }
	            
	            request.setAttribute("menError", "ALTA CORRECTA!");
				return mapping.findForward("ok");          
		  }
			
			else
			{
				request.setAttribute("menError", mensajeResultado);
				return mapping.findForward("ok");
			}  

		
		}
			else
			{
				//request.setAttribute("menError", "Error, intente nuevamente");
				return mapping.findForward("ok");
			}  
		
	}
}
