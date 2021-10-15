package web.almacen.bas;

import helper.PropertiesHelper;
import logica.Logica;
import logica.Utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Usuario;
import dataTypes.DTO_Articulo;
import dataTypes.DataIDDescripcion;


public class ImportRecepcion extends Action 
{

	@SuppressWarnings("rawtypes")
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HttpSession session = request.getSession(true);
     	
     	Logica logica = new Logica();
     	Usuario uLog = (Usuario) session.getAttribute("uLogeado");
		Utilidades util = new Utilidades();
		int idEmpresa = util.darEmpresa(uLog);
		if(idEmpresa==0)
		{
			return mapping.findForward("LOGIN");
		}
			//Ruta donde se guardara el fichero
			 PropertiesHelper pH=new PropertiesHelper("paths");
			 pH.loadProperties();
			 String path = pH.getValue("xlsx");
			 
			 File destino=new File(path);
			
			 if (!destino.exists())
			{
				destino=new File(path);
				destino.mkdir(); 
				
			}
			 
			 List<DataIDDescripcion> listaSort = new ArrayList<>();
			
			
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
						//Lo escribimos en el disco
						// usando la ruta donde se guardara el fichero
						// y cogiendo el nombre del file
						// Nota: Se podria hacer usando el objeto item en vez del file directamente
						// Pero esto puede causar incompatibilidades segun que navegador, ya que 
						// algunos solo pasan el nombre del fichero subido, pero otros
						// como Iexplorer, pasan la ruta absoluta, y esto crea un pequeño problema al escribir
						// el fichero en el servidor.
						item.write(new File(destino,file.getName()));
						
						
						int pasada= 0;
						
						
			           
		
			            // Finds the workbook instance for XLSX file
			            Iterator <Sheet> sheetIterator = null;
			            
						try(Workbook myWorkBook = WorkbookFactory.create(new File(destino + "\\" + file.getName()));) {
							
							sheetIterator = myWorkBook.iterator();
						}
						catch(Exception e) {
							session.setAttribute("menError", "Puede que el archivo "+ file.getName() +" se encuentre abierto. Ciérrelo e intente de nuevo");
							return mapping.findForward("ok");
						}
			            
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
				                		DataIDDescripcion articulo = new DataIDDescripcion();
				                		//0 Local	1Tipo Plan	2De/A 	3Nro	4Línea	5Artículo	6Descripción	7Intención	8A Recepcionar	9Recepcionado	10Diferencia	11En Cero			12Cod Bar	12Estilo+Color	13Marca	14Proveedor	15Color	16Talle	17Estilo	18BU	19Genero	20Material	21Categoria	22Grupo	23Temporada	24Año	25Precio	26Costo
				                		//5 articulo
				                		//6 Descripción
				                		//9 Recepcionado
				                		//12 Cod Bar
				                		String arti;
				                		String descripcion;
				                		String barras;
				                		int cantidad = 0;
				                		
					                    Cell articuloC;
					                    Cell descripcionC;
					                    Cell barrasC;
					                    Cell cantidadC;
					                    
					                   	articuloC = row.getCell(5);
					                   	descripcionC = row.getCell(6);
					                   	barrasC = row.getCell(14);
					                    cantidadC = row.getCell(9);
					                   
					                    try {
											cantidad = Integer.parseInt(cantidadC.toString().replace(".0", ""));
										} catch (Exception e) {
											e.printStackTrace();
										}
					                   
					                    arti = articuloC.toString().replace(" ", "");
					                    
					                    /*if(arti.startsWith("0"))
					                    {
					                    	arti = arti.replaceFirst("0", "");
					                    }
					                    */
					                    
					                    descripcion = descripcionC.toString();
					                    barras = barrasC.toString();
					                    
					                    
					                    articulo.setId(cantidad);
					                    articulo.setDescripcion(descripcion.replace("  ", ""));
					                    articulo.setDescripcionB(barras);
					                    articulo.setDescripcionC(arti);
					                    listaSort.add(articulo);
					                    
			            	
				                	}
				                }
				                catch (Exception e) 
				                {
				                	e.printStackTrace();
								}
				                
				                pasada++;
				            }
				            
			            }
			            file.delete();
			            item.delete();
			            
			            
			           
					}
					
				}
			}

		List<DTO_Articulo> articulos = new ArrayList<>();
		List<DataIDDescripcion> artOjos = new ArrayList<>();

		for (DataIDDescripcion di : listaSort) {
			DTO_Articulo dt = new DTO_Articulo(di.getDescripcionC(), di.getDescripcion(), "0", "0", "0", "0", "0", "1","0", "0");
			dt.getCodigoBarras().add(di.getDescripcionB());
			dt.setIdCategoria("0");
			dt.setIdProveedor("0");
			dt.setIdTemporada("0");
			articulos.add(dt);

			DataIDDescripcion toR = new DataIDDescripcion(di.getId(), di.getDescripcionC());
			artOjos.add(toR);

		}
		String idOjo = "0";
		List<String> queries = new ArrayList<>();
		for (DataIDDescripcion d : artOjos) {
			queries.addAll(logica.query_encuentraUpdateOjos(idOjo, d.getDescripcion(), d.getId(), false,
					uLog.getNumero(), uLog.isInventario(), "REC", uLog.getIdEmpresa()));
		}

		logica.persistirArticulos(articulos, uLog.getIdEmpresa());
		logica.getEper().persistirL(queries);

		session.setAttribute("articulosRecibidos", listaSort);

		return mapping.findForward("ok");
	}
}
