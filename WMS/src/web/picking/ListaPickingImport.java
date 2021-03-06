package web.picking;

import helper.PropertiesHelper;
import logica.Logica;
import logica.Utilidades;
import main.EcommerceProcessOrders;

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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import beans.Menu;
import beans.ProcessEcommerce;
import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import beans.encuentra.DataPicking;
import beans.encuentra.IPrint;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataIDDescripcion;


public class ListaPickingImport extends Action 
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
			int idPicking = 0;
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
			Hashtable<String, DataPicking> repos = new Hashtable<>();
			
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
						
						idPicking = Integer.parseInt(item.getString());
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
				                	if(pasada>-1)
				                	{
					                    Long orden = 0l;//0
				                		String factura = "0";//1
				                		//0 Origen	1 Destino	2 Articulo	3 Descripcion	4 Cantidad	5 Pickeada	6 Verificada	7 Remitidas	8 Consolidada	9 PedidosAfecta
				                		int depOrigen = 0;
				                		int depDestino = 0;
				                		int consolidada = 0;
				                		
					                    Cell origenC;
					                    Cell dstinoC;
					                    Cell articuloC;


					                    Cell cantidadC;
					                    Cell pickeadaC;
					                    Cell verificadaC;
					                    Cell remitidasC;
					                    Cell consolidadaC;
					                    Cell pedidosAfectaC;
					                    
					                    if(idEmpresa==6) {
					                    	origenC = null;
					                    	dstinoC = null;
					                    	depOrigen = util.darParametroEmpresaINT(idEmpresa,4);           			
					                    	depDestino = util.darParametroEmpresaINT(idEmpresa,5);
					                    	articuloC = row.getCell(6);
					                    	cantidadC = row.getCell(9);
						                    pickeadaC = row.getCell(11);
						                    verificadaC = row.getCell(12);
						                    remitidasC = row.getCell(13);
						                    consolidadaC = null;
						                    pedidosAfectaC = row.getCell(0);
					                    }
					                    else {
					                    	origenC = row.getCell(0);
						                    dstinoC = row.getCell(1);
					                    	articuloC = row.getCell(2);
					                    	cantidadC = row.getCell(4);
						                    pickeadaC = row.getCell(5);
						                    verificadaC = row.getCell(6);
						                    remitidasC = row.getCell(7);
						                    consolidadaC = row.getCell(8);
						                    pedidosAfectaC = row.getCell(9);
					                    }
					                   
					                    try {
											depOrigen = Integer.parseInt(origenC.toString().replace(".0", ""));
										} catch (Exception e) {
											e.printStackTrace();
										}
					                    try {
					                    	depDestino = Integer.parseInt(dstinoC.toString().replace(".0", ""));
										} catch (Exception e) {
											e.printStackTrace();
										}
					                    
					                    
					                    try
					                    {
					                    	
					                    	try {
					                    		consolidada = Integer.parseInt(consolidadaC.toString().replace(".0", ""));
											} catch (Exception e) {
												e.printStackTrace();
											}
					                    	
					                    	if(consolidada==1)
					                    	{
					                    		//System.out.println(pickeadaC.toString());
					                    		
					                    		int pickieadaTotal = Integer.parseInt(pickeadaC.toString().replace(".0", ""));
					                    		int verificadaTotal = Integer.parseInt(verificadaC.toString().replace(".0", ""));
					                    		int remitidaTotal = Integer.parseInt(remitidasC.toString().replace(".0", ""));
					                    		
					                    		int saldoPickeadas = pickieadaTotal;
					                    		int saldoVerificadas = verificadaTotal;
					                    		int saldoRemitidas = remitidaTotal;
					                    		
					                    		String array1 = pedidosAfectaC.toString();
					                    		String [] arreglo;
					                    		if(array1.contains("-"))
					                    		{
					                    			arreglo = array1.split("-");
					                    			if(articuloC.toString().equals("24562006"))
					                    			{
					                    				System.out.println("");
					                    			}
					                    			for (int i = 0; i < arreglo.length; i++) 
					                    			{
					                    				String [] artCant = arreglo[i].split(",");
					                    				String idPedido = artCant[0];
					                    				int cantidadSol = Integer.parseInt(artCant[1]);
					                    				
					                    				DataPicking li = new DataPicking();
					                    				
					                    				li.setIdPedido(Long.parseLong(idPedido));
					                    				li.setSol(cantidadSol);
					                    				
					                    				
					                    				if(cantidadSol<=saldoPickeadas)
					                    				{
					                    					li.setPick(cantidadSol);
					                    					saldoPickeadas-=cantidadSol;
					                    				}
					                    				else if (saldoPickeadas>0)
					                    				{
					                    					li.setPick(saldoPickeadas);
					                    					saldoPickeadas=0;
					                    				}
					                    				else
					                    				{
					                    					li.setPick(0);
					                    					
					                    				}
					                    				
					                    				if(cantidadSol<=saldoVerificadas)
					                    				{
					                    					li.setVerificada(cantidadSol);
					                    					saldoVerificadas-=cantidadSol;
					                    				}
					                    				else if (saldoVerificadas>0)
					                    				{
					                    					li.setVerificada(saldoVerificadas);
					                    					saldoVerificadas=0;
					                    				}
					                    				else
					                    				{
					                    					li.setVerificada(0);
					                    					
					                    				}
					                    				
					                    				
					                    				if(cantidadSol<=saldoRemitidas)
					                    				{
					                    					li.setRemitida(cantidadSol);
					                    					saldoRemitidas-=cantidadSol;
					                    				}
					                    				else if (saldoRemitidas>0)
					                    				{
					                    					li.setRemitida(saldoVerificadas);
					                    					saldoRemitidas=0;
					                    				}
					                    				else
					                    				{
					                    					li.setRemitida(0);
					                    					
					                    				}
					                    				
					                    				li.setOrigen(new DataIDDescripcion( depOrigen,""));
								                    	li.setDestino(new DataIDDescripcion(depDestino,""));
								                    	li.setArticulo(articuloC.toString());
								                    	
								                    	repos.put(li.getOrigen().getId()+""+li.getDestino().getId()+""+li.getArticulo()+""+idPicking+""+li.getIdPedido(), li);
								                    	System.out.println( li.getIdPedido()+"\t"+  li.getArticulo() + "\t"+ li.getSol() + "\t"+li.getPick() + "\t"+li.getVerificada() + "\t"+li.getRemitida());
								                    	
													}
					                    		}
					                    		else
					                    		{
					                    			DataPicking li = new DataPicking();
					                    			
					                    			String [] artCant = array1.split(",");
				                    				String idPedido = artCant[0];
				                    				li.setIdPedido(Long.parseLong(idPedido));
					                    			li.setOrigen(new DataIDDescripcion(depOrigen,""));
							                    	li.setDestino(new DataIDDescripcion(depDestino,""));
							                    	li.setArticulo(articuloC.toString());
							                    	li.setSol(Integer.parseInt(cantidadC.toString().replace(".0", "")));
							                    	li.setPick(Integer.parseInt(pickeadaC.toString().replace(".0", "")));
							                    	li.setVerificada(Integer.parseInt(verificadaC.toString().replace(".0", "")));
							                    	li.setRemitida(Integer.parseInt(remitidasC.toString().replace(".0", "")));
							                    	
							                    	repos.put(li.getOrigen().getId()+""+li.getDestino().getId()+""+li.getArticulo()+""+idPicking+""+li.getIdPedido(), li);
							                    	
					                    		}
					                    	}
					                    	else
					                    	{
					                    		DataPicking li = new DataPicking();
					                    		li.setIdPedido(Long.parseLong(pedidosAfectaC.toString()));
					                    		
				                    			li.setOrigen(new DataIDDescripcion(depOrigen,""));
						                    	li.setDestino(new DataIDDescripcion(depDestino,""));
						                    	li.setArticulo(articuloC.toString());
						                    	li.setSol(Integer.parseInt(cantidadC.toString().replace(".0", "")));
						                    	li.setPick(Integer.parseInt(pickeadaC.toString().replace(".0", "")));
						                    	li.setVerificada(Integer.parseInt(verificadaC.toString().replace(".0", "")));
						                    	li.setRemitida(Integer.parseInt(remitidasC.toString().replace(".0", "")));
						                    	
						                    	repos.put(li.getOrigen().getId()+""+li.getDestino().getId()+""+li.getArticulo()+""+idPicking+""+li.getIdPedido(), li);
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
				
				 
				
				
				List<DataPicking> pickings = Logica.encuentraDarPickingWMS(idPicking,idEmpresa);
				
				List<DataPicking> pickingsR = new ArrayList<>(); 
				EcommerceProcessOrders pro = new EcommerceProcessOrders();
				ProcessEcommerce process = new ProcessEcommerce();
				IPrint print = new IPrint();
				int idDepo = Integer.parseInt(uLog.getDeposito());
				boolean procesaEcommerce = util.darParametroDepositoBool(idEmpresa,idDepo,51 );
				
				Hashtable<Long, Long> pedidosSalenCentral = pro.darPedidosSinglDepo(idDepo,idEmpresa, true);
				
				
				for (DataPicking o : pickings) 
				{
					
					
					DataPicking li = repos.get(o.getOrigen().getId()+""+o.getDestino().getId()+""+o.getArticulo()+""+idPicking+""+o.getIdPedido());
					
					if(li!=null)
					{
						o.setPick(li.getPick());
						o.setVerificada(li.getVerificada());
						o.setRemitida(li.getRemitida());
						
						if(pedidosSalenCentral.get(o.getIdPedido())!=null)
						{
							if(li.getPick()>0)
							{
								pro.confirmarSKU(o.getArticulo() , idDepo, li.getPick(), o.getIdPedido(),idEmpresa, uLog);
							}
							
							
							if(li.getVerificada()>0)
							{
								DataArticuloEcommerceVerifR articuloR = Logica.darArticuloEcommerceReq(o.getArticulo(),o.getIdPedido()+"", idEmpresa);			
								
								if(articuloR==null)
								{					
									//request.setAttribute("menError", "Atenci?n, No se encontraron pedidos de este articulo buscamos en articulos pedidos confirmados, tambien en los sin confirmar. Tampoco estaba en los articulos que no se pidieron por que no habia stock. Devuelva este par a su deposito de origen.");
									request.setAttribute("menError","No se precisa, devuelvalo." );
									return mapping.findForward("no");
								}
								else if(articuloR.getMensaje()!=null)
								{
									request.setAttribute("menError",articuloR.getMensaje());
									return mapping.findForward("no");
								}
								
								else 
								{
									if (articuloR.getTotalPedido()==1 && articuloR.getTotalProcesado()+1!=articuloR.getTotalPedido())
									{
										
										Logica.logPedido(o.getIdPedido(), uLog.getNumero(), 2, "ATENCION: El pedido ya se envi?, no debe entregar estos articulos", -1, idEmpresa);
										
									}
									boolean sigo =Logica.updateEcommerceArticuloReq(articuloR.getIdArticulo(), articuloR.getIdPedido(),articuloR.getIdDeposito(),
											articuloR.getCanal(),idEmpresa, uLog);
									if(!sigo)
									{
										Logica.logPedido(o.getIdPedido(), uLog.getNumero(), 2, "ATENCION: El pedido ya se envi?, no debe entregar estos articulos", -1, idEmpresa);	
									}
									else {
										boolean canalActivo = false;
										try 
										{
											canalActivo = Logica.canalActivoEC(articuloR.getCanal(), idEmpresa);
										} 
										catch (Exception e) {}
										
										boolean etiqueta = false;					
										
										if(1==articuloR.getTotalPedido()) 
										{
												etiqueta = print.imprimirEtiqueta(articuloR, uLog.getIdEquipo(),idEmpresa,1,null);
												if(canalActivo)
												{
													articuloR.setEstadoEncuentra(3);
													process.cambioEstadoAPreparado(Logica, idEmpresa, articuloR);								
												}

												//FACTURACION AUTOMATICA
												
												util.facturacionAutomatica(articuloR.getIdPedido(),uLog.getIdEquipo(),idEmpresa,Logica);							
										}
										
										else if(articuloR.getTotalProcesado()+1==articuloR.getTotalPedido()) 
										{
											
											process.setearSession(session, articuloR);
											List<DataIDDescripcion> articulosMesa = Logica.darUbicacionPedidoEnOjos(articuloR.getIdPedido(), idEmpresa);
											articulosMesa.remove(0);
											session.setAttribute("mesa", articulosMesa);								
											etiqueta = print.imprimirEtiqueta(articuloR, uLog.getIdEquipo(),idEmpresa,1,null);
											
											if(canalActivo)
											{
												articuloR.setEstadoEncuentra(3);
												process.cambioEstadoAPreparado(Logica, idEmpresa, articuloR);
											}
											
											//FACTURACION AUTOMATICA
											
											util.facturacionAutomatica(articuloR.getIdPedido(),uLog.getIdEquipo(),idEmpresa,Logica);							
														
										}
									}
									
									
									
								}
							}
							if(li.getRemitida()>0)
							{
								
							}
						
						
						}
						
						pickingsR.add(o);
					}
					else 
					{
						System.out.println("no encontre "+o.getOrigen().getId()+""+o.getDestino().getId()+""+o.getArticulo()+""+idPicking+""+o.getIdPedido());
					}
					
					
					
					
				}
				
				
				for (DataPicking d : pickingsR) 
				{
					System.out.println(d.getArticulo() + "\t"+ d.getSol() + "\t"+d.getPick() + "\t"+d.getVerificada() + "\t"+d.getRemitida());
				}
				
				session.setAttribute("pickings", pickingsR);
				
				
				
			}
			return mapping.findForward("ok");
	}
}
