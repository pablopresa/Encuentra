package web.almacen;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logica.LeerFicheroTexto;
import logica.Logica;
import logica.LogicaBulto;
import logica.Utilidades;
import main.EcommerceProcessOrders;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.ProcessEcommerce;
import beans.Usuario;
import beans.elementoPicking;
import beans.api.DataMovimiento;
import beans.encuentra.DataPicking;
import beans.encuentra.IPrint;
import beans.encuentra.MovimientoAlmacen;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescripcion;
import helper.PropertiesHelper;

public class _EncuentraAltaFileRepo extends Action 
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
			 
			 List<DataIDDescripcion> articulosMover = new ArrayList<>();
			 List<DataIDDescripcion> articulosMoverSuelto = new ArrayList<>();
			
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
				
				
				
				while(it.hasNext())
				{
					//Rescatamos el fileItem
					FileItem item=(FileItem)it.next();
					//Comprobamos si es un campo de formulario
					if(!item.isFormField())
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
						
						
			           
		
			            // Finds the workbook instance for XLSX file
			            Iterator <Sheet> sheetIterator = null;
			            
			            
			            Workbook  myWorkBook = WorkbookFactory.create(new File(destino+"\\"+file.getName()));
			            sheetIterator = myWorkBook.iterator();
			            
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
					                    Long orden = 0l;//0
				                		String factura = "0";//1
				                		//0 Pedido	1 bulto	2 Articulo	3 Descripcion	4 CantidadSOL	5 estanteria	6 estante	7 modulo	8 ojoO	9 recorrido	10 CantidadM	11 OjoD

				                		
					                    Cell origenC = row.getCell(8);
					                    Cell dstinoC = row.getCell(11);
					                    Cell articuloC = row.getCell(2);
					                    Cell bultoC = row.getCell(1);
					                    Cell cantidadC = row.getCell(10);
					                   
					                    
					                    
					                    try
					                    {
					                    	
					                    	
					                    	String ojoOrigen = origenC.toString();
					                    	String ojoDestino = dstinoC.toString();
					                    	String sku = articuloC.toString();
					                    	
					                    	String bul = bultoC.toString();
					                    	
					                    	int cantidad = Integer.parseInt(cantidadC.toString().replace(".0", ""));
					                    	
					                    	
					                    	
					                    	
					                    	DataIDDescripcion toin = new DataIDDescripcion(cantidad,sku);
					                    	toin.setDescripcionB(ojoOrigen);
					                    	toin.setDescripcionC(ojoDestino);
					                    	
					                    	
					                    	if(sku.equals(bul))
					                    	{
					                    		articulosMoverSuelto.add(toin);
					                    	}
					                    	else
					                    	{
					                    		articulosMover.add(toin);
					                    	}
					                    	
					                    	
					                    	
					                    }
					                    catch(Exception e)
					                    {
					                    	e.printStackTrace();
					                    }
					                    
					                    
		
					                   
		  
				                	
				                	}
					                
				                }
				                catch(Exception e)
				                {
				                	e.printStackTrace();
				                }
				                
			            	pasada++;
			            	
			            }
			            
				            file.delete();
				            item.delete();
			                
			            }
			            
			            
			            
			           
					}
					
				}
				
				
			}
			
			if(!articulosMover.isEmpty() || !articulosMoverSuelto.isEmpty())
			{
				
				Logica lo = new Logica();
				List<MovimientoAlmacen> movimientos = lo.darMovimientosRepo(articulosMover,idEmpresa,uLog.getNumero());
				
				if(!articulosMoverSuelto.isEmpty())
				{
					
					for (DataIDDescripcion di : articulosMoverSuelto) 
					{
						List<elementoPicking> lista = new ArrayList<>();
						elementoPicking ep = new elementoPicking(di.getId(), di.getDescripcion(), 0);
						lista.add(ep);
						MovimientoAlmacen ma = new MovimientoAlmacen(di.getDescripcionB(), di.getDescripcionC(), uLog.getNumero(), lista);
						movimientos.add(ma);
					}
					 
				}
				
				
				boolean todoOk = true;
				for (MovimientoAlmacen mo : movimientos) 
				{
					if(!lo.MovimientoDeArticulos(mo,idEmpresa))
					{
						todoOk=false;
					}
				}
				
				if(todoOk)
				{
					session.setAttribute("resultMov", movimientos);
				}
				
				
				
				
			}
			
			return mapping.findForward("ok");
	}
		
}
