package beans;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


import javax.print.DocPrintJob;
import javax.print.PrintService;

import logica.LogicaAPI;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import javax.print.attribute.standard.Slides;
import org.apache.pdfbox.pdmodel.PDDocument;

import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;
//import org.apache.pdfbox.util.PDFImageWriter;


import com.itextpdf.text.Rectangle;

import beans.api.DataPrintable;
import beans.datatypes.DataIDDescripcion;


public class IPrintAS_Service2 
{

	private int id;
	
	public IPrintAS_Service2() 
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

  public static void main(String[] args) throws IOException, PrinterException 
  {  
	  
	  
	  List<String> propiedades = LeerProperties();
	  String empresa = "";
	  String equipo = "";
	  String impresora1 = "";
	  String impresora2 = "";
	  String impresora3 = "";
	  String impresora4 = "";
	  
	  //String impresorasDiponibles = darImpresorasDisponibles();
	  
	  for (String p : propiedades) 
	  {
		  String prop = p.split("=")[0];
		  String val = p.split("=")[1];
		  
		  switch (prop) 
		  {
		  	case "IdEmpresa":
		  		empresa=val;
			break;
		  	case "idEquipo":
		  		equipo=val;
			break;
		  	case "Impresora1":
		  		impresora1=val;
		  	break;
		  	case "Impresora2":
		  		impresora2=val;
		  	break;
		  	case "Impresora3":
		  		impresora3=val;
		  	break;
		  	case "Impresora4":
		  		impresora4=val;
		  	break;

		  	default:
			break;
		}
		  
	  }
	  
	  
	  
	  
	  while (true) 
	  {
		  System.out.println("iniciando");
		try 
		{
			
			List <DataPrintable> etiquetas = new ArrayList<>();
			
			StringBuilder ip = new StringBuilder("");
			StringBuilder hostname = new StringBuilder("");
			
			darIpHostname(ip,hostname);
			
			if (empresa.equals("1"))
				etiquetas = LogicaAPI.darListaToPrint("select id, urlArchivo,porait, printerID from print_spooler where printed = 0 AND printerID = "+equipo+" AND idEmpresa = "+empresa+"");
			else if(empresa.equals("2")||empresa.equals("4")){
				etiquetas = LogicaAPI.darListaToPrint("select id, urlArchivo,porait, printerID from print_spooler where printed = 0 AND idequipo = "+equipo+" AND idEmpresa = "+empresa+"");
			}
			else{
				etiquetas = LogicaAPI.darListaToPrint("select id, urlArchivo,porait, printerID from print_spooler where printed = 0 AND idEmpresa = "+empresa+"");
			}
				
			
			if(etiquetas.isEmpty())
			{
				System.out.println("Esperando para imprimir...");
				LogicaAPI.persistir("INSERT INTO impresoras_estado (idEmpresa, idEquipo, ip_nombre, estado, fechaUpdate) "
						+ "VALUES ("+ empresa +","+equipo+",'"+ip+hostname+"','esperando',CURRENT_TIMESTAMP()) "
						+ "ON DUPLICATE KEY UPDATE ip_nombre = '"+ip+hostname+"', estado = 'esperando', fechaUpdate = CURRENT_TIMESTAMP(), Impresora1='"+impresora1+"', Impresora2='"+impresora2+"', Impresora3='"+impresora3+"', Impresora4='"+impresora4+"';");
						//, Impresoras_Disponibles='"+impresorasDiponibles+"'
			}
			else
			{
				System.out.println("Imprimiendo");
				for (DataPrintable et : etiquetas) 
				{
					boolean puede = false;
					
					boolean rotar = false;
					
					if(et.getPorait().equals("1"))
					{
						rotar=true;
					}
					
					String printer = "";
					switch (et.getPrinterID()) 
					{
						case "0":
							printer = impresora1;
						break;
						case "1":
							printer = impresora1;
						break;
						case "2":
							printer = impresora2;
						break;
						case "3":
							printer = impresora3;
						break;
						case "4":
							printer = impresora4;
						break;
							
						default:
							printer = impresora1;
						break;
					}
					
					
					
					puede = imprimeEtEccomerce(et.getUrlArchivo(),et.getId(),rotar,printer,empresa);
					
					if(!puede)
					{
						LogicaAPI.persistir("UPDATE `print_spooler` SET `printed`='-1' WHERE  `id`='"+et.getId()+"';");
						LogicaAPI.persistir("INSERT INTO impresoras_estado (idEmpresa, idEquipo, ip_nombre, estado, fechaUpdate) "
								+ "VALUES ("+ empresa +","+equipo+",'"+ip+hostname+"','error -1',CURRENT_TIMESTAMP()) "
								+ "ON DUPLICATE KEY UPDATE ip_nombre = '"+ip+hostname+"', estado = 'esperando', fechaUpdate = CURRENT_TIMESTAMP(), Impresora1='"+impresora1+"', Impresora2='"+impresora2+"', Impresora3='"+impresora3+"', Impresora4='"+impresora4+"';");
								//, Impresoras_Disponibles='"+impresorasDiponibles+"'
					}
					else
					{
						LogicaAPI.persistir("UPDATE `print_spooler` SET `printed`='1' WHERE  `id`='"+et.getId()+"';");
						LogicaAPI.persistir("INSERT INTO impresoras_estado (idEmpresa, idEquipo, ip_nombre, estado, fechaUpdate) "
								+ "VALUES ("+ empresa +","+equipo+",'"+ip+hostname+"','esperando',CURRENT_TIMESTAMP()) "
								+ "ON DUPLICATE KEY UPDATE ip_nombre = '"+ip+hostname+"', estado = 'imprimiendo', fechaUpdate = CURRENT_TIMESTAMP(), Impresora1='"+impresora1+"', Impresora2='"+impresora2+"', Impresora3='"+impresora3+"', Impresora4='"+impresora4+"';");
								//, Impresoras_Disponibles='"+impresorasDiponibles+"'
					}
				}

				
			}
			
			Thread.sleep(1000);			
			etiquetas = null;

		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			StringBuilder ip = new StringBuilder("");
			StringBuilder hostname = new StringBuilder("");
			
			darIpHostname(ip,hostname);
			  
			LogicaAPI.persistir("INSERT INTO impresoras_estado (idEmpresa, idEquipo, ip_nombre, estado, fechaUpdate) "
					+ "VALUES ("+ empresa +","+equipo+",'"+ip+hostname+"','esperando',CURRENT_TIMESTAMP()) "
					+ "ON DUPLICATE KEY UPDATE ip_nombre = '"+ip+hostname+"', estado = 'catch error', fechaUpdate = CURRENT_TIMESTAMP(), Impresora1='"+impresora1+"', Impresora2='"+impresora2+"', Impresora3='"+impresora3+"', Impresora4='"+impresora4+"';");
					//, Impresoras_Disponibles='"+impresorasDiponibles+"'
		}
		
	  }
	  
	

  }

public static String darImpresorasDisponibles() {
	String impresorasDiponibles = "";
	  PrintService[] services = PrinterJob.lookupPrintServices();
	  int i = 0;
	  for (i = 0; i < services.length-1; i++) 
		{
			impresorasDiponibles += services[i].getName()+ ", ";
		}
	  impresorasDiponibles += services[i].getName();
	return impresorasDiponibles;
}

private static void darIpHostname(StringBuilder ip, StringBuilder hostname) {
	
	try 
	{
		InetAddress addr = InetAddress.getLocalHost();
		ip.append("IP: "+addr.getHostAddress());
		hostname.append(" HOSTNAME: "+addr.getHostName());
		System.out.println(ip+" "+hostname);
	} 
	catch (Exception e) 
	{
		ip.append("error al obtener la ip");
		hostname.append("error al obtener el hostname");
	}
}
  
  
  public static boolean imprimeEtEccomerce(String urlPDF, String filename, boolean rotar, String printerNameDesired, String idEmpresa ) throws IOException, PrinterException 
  {  
	  
	  try
	  { 
		  		  
		if(true) 
		{
		  
		  
			
			String downloaded_filename = "C:\\EtiquetasEncuentra\\"+filename+".pdf";
		  
			String download_pdf_from = urlPDF;
			String downloaded_filename_open_as_pdf = downloaded_filename;
	
		    
	
		    PrintService[] services = PrinterJob.lookupPrintServices();
		    DocPrintJob docPrintJob = null;
		   System.out.println("");
		    try
		    {
		      URL url = new URL(download_pdf_from);
		      if(saveFile(url, downloaded_filename)) 
		      {
		    	try 
		    	{
		    		PDDocument pdf = PDDocument.load(new File(downloaded_filename_open_as_pdf));
		    		PrinterJob job = PrinterJob.getPrinterJob();
		    		for (int i = 0; i < services.length; i++) 
		    		{
		    			System.out.println(services[i].getName());
		    			if (services[i].getName().equals(printerNameDesired)) 
		    			{
		    				docPrintJob = services[i].createPrintJob();
		    				break;
		    			}
		    		}
		    		
		          
		    		job.setPrintService(docPrintJob.getPrintService());
		    		PDDocument doc = PDDocument.load(new File(downloaded_filename_open_as_pdf));
		    		PDPage page = doc.getPage(0);
		    		PageFormat pageFormat = new PageFormat();
		    		
		    	
		    		
		    		switch (idEmpresa) 
		    		{

						case "3"://unilam
						{
							if(rotar)
				    		{
				    			// define custom paper
				    			Paper paper = new Paper();
					    		double width = 6d * 72d;
					    		double height = 4.3d * 72d;
					    		double margin = 0.1d * 72d;
					    		paper.setSize(width, height);
					    		//margen izquierdo , margen suoerior
					    		paper.setImageableArea(margin-3,margin-13,width - (margin * 2),height - (margin * 2));
					    		
					    		pageFormat.setPaper(paper);
				    			pageFormat.setOrientation(PageFormat.LANDSCAPE);
				    			
				    		}
				    		else
				    		{
				    			// define custom paper
					    		Paper paper = new Paper();
					    		
					    		//margen izquierdo , margen suoerior
					    		
						    		if (filename.startsWith("up"))
						    		{
							    		double width = 215d;
							    		double height = 450d;
							    		double margin = 15d;
							    		paper.setSize(width, height);
							    		paper.setImageableArea(margin,margin,width - (margin * 2),height);
							    		pageFormat.setPaper(paper);
						    		}
						    		else if(download_pdf_from.contains("Pickup"))
					    			{
					    				
							    		double width = page.getMediaBox().getWidth();
							    		double height = page.getMediaBox().getHeight();
							    		double margin = 0.1d * 72d;
							    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
							    		//margen izquierdo , margen suoerior
							    		paper.setImageableArea(margin-69,margin-20,width,height);
							    		
							    		pageFormat.setPaper(paper);
					    			}
					    			else //UES
					    			{

					    				
					    				double width = page.getMediaBox().getWidth();
							    		double height = page.getMediaBox().getHeight();
							    		double margin = 0.1d * 72d;
							    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
							    		//margen izquierdo , margen suoerior
							    		paper.setImageableArea(margin-157,margin-20,width,height);
							    		
							    		pageFormat.setPaper(paper);
					    			}
						    		/*
						    		else
						    		{
							    		double width = 244.8d;
							    		double height = 864d;
							    		double margin = 7.2d;
							    		paper.setSize(width, height);
						    			paper.setImageableArea(margin-4,margin+200,width - (margin * 2),height - (margin * 2));
						    		}*/
						    		
					    		
				    		}
				    		Book book = new Book();
				    		// append all pages
				    		//book.append(new PDFPrintable(doc, Scaling.STRETCH_TO_FIT), pageFormat, doc.getNumberOfPages());
				    		//book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    		//book.append(new PDFPrintable(doc, Scaling.SHRINK_TO_FIT), pageFormat, doc.getNumberOfPages());
				    		book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    		job.setPageable(book);
				    		job.print();
			  	          	return true;
						}
						
						case "2": //FORUS
						{
							Book book = new Book();
							if(download_pdf_from.contains("SDL")) {
								rotar = true;
							}
							
							if(rotar)
				    		{						
				    			if(download_pdf_from.contains("SDL")) 
				    			{
				    				Paper paper = new Paper();
						    		double width = page.getMediaBox().getWidth();
						    		double height = page.getMediaBox().getHeight();
						    		double margin = 0.1d * 72d;
						    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
						    		//margen izquierdo , margen suoerior
						    		paper.setImageableArea(margin-60,margin+60,width,height);
						    		//paper.setImageableArea(margin-3,margin-13,width - (margin * 2),height - (margin * 2));
						    		pageFormat.setPaper(paper);
					    			pageFormat.setOrientation(PageFormat.LANDSCAPE);
				    				book.append(new PDFPrintable(doc, Scaling.SHRINK_TO_FIT), pageFormat, doc.getNumberOfPages());
								}
				    			else 
				    			{
				    				Paper paper = new Paper();
						    		double width = page.getMediaBox().getWidth();
						    		double height = page.getMediaBox().getHeight();
						    		double margin = 0.1d * 72d;
						    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
						    		//margen izquierdo , margen suoerior
						    		paper.setImageableArea(margin-60,margin+60,width,height);
						    		//paper.setImageableArea(margin-3,margin-13,width - (margin * 2),height - (margin * 2));
						    		pageFormat.setPaper(paper);
					    			pageFormat.setOrientation(PageFormat.LANDSCAPE);
				    				book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    			}
				    			
				    			
				    		}
				    		else
				    		{
				    			// define custom paper
				    			
				    			if(download_pdf_from.contains("Pickup"))
				    			{
				    				Paper paper = new Paper();
						    		double width = page.getMediaBox().getWidth();
						    		double height = page.getMediaBox().getHeight();
						    		double margin = 0.1d * 72d;
						    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
						    		//margen izquierdo , margen suoerior
						    		paper.setImageableArea(margin-69,margin-20,width,height);
						    		
						    		pageFormat.setPaper(paper);
						    		book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    			}
				    			else if(download_pdf_from.contains("netsuite")) //UES
				    			{

				    				Paper paper = new Paper();
						    		double width = page.getMediaBox().getWidth();
						    		double height = page.getMediaBox().getHeight();
						    		double margin = 0.1d * 72d;
						    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
						    		//margen izquierdo , margen suoerior
						    		paper.setImageableArea(margin-157,margin-20,width,height);
						    		
						    		pageFormat.setPaper(paper);
						    		book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    			}
				    			else if(download_pdf_from.contains("pdf/r"))
				    			{
				    				//CITIZEN CT-S310II
				    				//Funciona como un ca?o
				    				
				    				Paper paper = new Paper();
						    		double width = page.getMediaBox().getWidth();
						    		double height = page.getMediaBox().getHeight();
						    		double margin = 0.1d * 72d;
						    		paper.setSize(width,height);
						    		//margen izquierdo , margen superior
						    		paper.setImageableArea(margin-4,margin,width,height);
						    		
						    		pageFormat.setPaper(paper);
						    		book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    			}
				    			else
				    			{

				    				Paper paper = new Paper();
						    		double width = page.getMediaBox().getWidth();
						    		double height = page.getMediaBox().getHeight();
						    		double margin = 0.1d * 72d;
						    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
						    		//margen izquierdo , margen suoerior
						    		paper.setImageableArea(margin-70,margin,width,height);
						    		
						    		pageFormat.setPaper(paper);
						    		book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    			
				    			}
				    		}
				    		// append all pages
				    		
				    		job.setPageable(book);
				    		job.print();
			  	          	return true;
						}
						
						case "4": //ElRey
						{
							Book book = new Book();
							if(rotar)
				    		{
								// define custom paper
				    			Paper paper = new Paper();
					    		double width = page.getMediaBox().getWidth();
					    		double height = page.getMediaBox().getHeight();
					    		double margin = 0.1d * 72d;
					    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
					    		//margen izquierdo , margen suoerior
					    		paper.setImageableArea(margin-60,margin+60,width,height);
					    		//paper.setImageableArea(margin-3,margin-13,width - (margin * 2),height - (margin * 2));
					    		pageFormat.setPaper(paper);
				    			pageFormat.setOrientation(PageFormat.LANDSCAPE);
				    			book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    		}
				    		else
				    		{
				    			// define custom paper
				    			// CITIZEN CT-S4500
				    			
				    			if(download_pdf_from.contains("pdf/r"))
				    			{
				    				Paper paper = new Paper();
						    		double width = page.getMediaBox().getWidth();
						    		double height = page.getMediaBox().getHeight();
						    		double margin = 0.1d * 72d;
						    		paper.setSize(width,height);
						    		//margen izquierdo , margen suoerior
						    		paper.setImageableArea(margin,margin,width,height);
						    		


						    		paper.setSize(4 * 72, 12 * 72);
						    	    paper.setImageableArea(0.0 * 72, 0.0 * 72, 3.9 * 72, 5.9 * 72);
						    		
								    pageFormat.setPaper(paper);
								    
								   // pageFormat.setPaper(paper);
						    		book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());

				    			}
				    			else
				    			{
				    				Paper paper = new Paper();
						    		double width = page.getMediaBox().getWidth();
						    		double height = page.getMediaBox().getHeight();
						    		double margin = 0.1d * 72d;
						    		paper.setSize(4 * 72, 400);
						    		//margen izquierdo , margen suoerior
						    		paper.setImageableArea(margin,margin-15,width,height);
						    		
						    		pageFormat.setPaper(paper);
						    		book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    				
				    			}
				    		}
					        
				    	
				    		// append all pages
				    		
				    		job.setPageable(book);
				    		job.print();
			  	          	return true;
						}
							
	
						default:
						{
							if(rotar)
				    		{
				    			// define custom paper
								
								
					            
					          
								
				    			Paper paper = new Paper();
					    		double width = page.getMediaBox().getWidth();
					    		double height = page.getMediaBox().getHeight();
					    		double margin = 0.1d * 72d;
					    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
					    		//margen izquierdo , margen suoerior
					    		paper.setImageableArea(margin,margin,width,height);
					    		//paper.setImageableArea(margin-3,margin-13,width - (margin * 2),height - (margin * 2));
					    		pageFormat.setPaper(paper);
				    			pageFormat.setOrientation(PageFormat.LANDSCAPE);
				    			
				    		}
				    		else
				    		{
				    			// define custom paper
				    			Paper paper = new Paper();
					    		double width = page.getMediaBox().getWidth();
					    		double height = page.getMediaBox().getHeight();
					    		double margin = 0.1d * 72d;
					    		paper.setSize(page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
					    		//margen izquierdo , margen suoerior
					    		paper.setImageableArea(margin,margin,width,height);
					    		
					    		pageFormat.setPaper(paper);
				    		}
					        
				    		Book book = new Book();
				    		// append all pages
				    		book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
				    		job.setPageable(book);
				    		job.print();
			  	          	return true;
						}
						
					}
		        
		        }
		        catch (Exception e) 
		        {
		        	e.printStackTrace();
		        	System.out.println("[FAIL]" + e);
		        	return false;
		        }      
		      } 
		      else 
		      {
		        System.out.println("[FAIL] - download fail");
		        return false;
		      
		      }      
	    	} 
	    	catch (Exception ae) 
	    	{
	    		System.out.println("[FAIL]" + ae);
	    		return false;
	    	}
		}
	
	 
	  }
	  catch(Exception e)
	  {
		  return false;
	  }
	  return false;
	
	
	   
  }
  
  public static List<String> LeerProperties() 
	{
		List<String> retorno = new ArrayList<>();
		try {
		
			String path = "C:/Program Files/200/Encuentra/ImpresoraEncuentra.txt";
			FileReader fr = new FileReader(path);
			BufferedReader bf = new BufferedReader(fr);
			String sCadena;
			while ((sCadena = bf.readLine())!=null) 
			{
				retorno.add(sCadena);
				System.out.println(sCadena);
			} 
				
			 
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}

		return retorno;
	}
  		
  		
  		
  		
  		
  		public static boolean saveFile(URL url, String file) throws IOException {
  		    
  			try
  			{
  				  
  				
  				if(true)
  				{
  					try (InputStream in = url.openStream()) {
  					   Files.copy(in, Paths.get(file), StandardCopyOption.REPLACE_EXISTING);
  					} catch (IOException e) {
  					   // handle exception
  					}
  					
  					return true;
  				}
  				
  				
  				
  			}
  			catch (Exception e)
  			{
  				e.printStackTrace();
  		        return false;
  		        	
  			}
			return false;
  			
  		  }
  		
  		
}