package logica;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.*;

import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName; 
 
public class imprimir_EtiArticulo 
{
   int count=0;
    String iden, artic, esc1, esc2, col, marc, pre1, pre2;
    String codigo="";
   boolean band=false;
     String item_no,ident,country_o,country_o2,qty2,cus;
     private static BufferedImage bi;
     

     
    
     
    
     public void imprimirEtiquetas(String precio1, String articulo, String escala1, String escala2, String color, String marca, String precio2) throws Exception
     {
    	 List<String> servImpre = LeerFicheroTexto.LeerProperties("ImpresoraETI.txt");
	 	 String server = servImpre.get(0);
	 	 
	 	
	 	 
	 	 
	 	 String impresora = servImpre.get(1);
	 	 codigo = articulo;
	 	 
	 	
	 	 marc = marca;
	 	 //artic = "Articulo: "+articulo + " "+color;
	 	 /*
	 	  * 
	 	  * 
	 	  * 
	 	  *
	 	 esc1 = escala1;
	 	 pre1 = "Precio: "+precio1;
	 	 
	 	 
	 	 if(!escala2.equals(""))
	 	 {
	 		esc2 = escala2;
	 		pre2 = "Precio "+precio2;
	 	 }
	 	 else
	 	 {
	 		esc2 ="";
	 		pre2 = "";
	 	 }
	 	  * 
	 	  * 
	 	  * 
	 	  * 
	 	  * 
	 	  */
	 	 artic = articulo + " "+color;
	 	 esc1 = escala1;
	 	 pre1 = escala1+ " "+precio1 ;
	 	 
	 	 
	 	 if(!escala2.equals(""))
	 	 {
	 		esc2 = escala2;
	 		pre2 = escala2 + " "+precio2;
	 	 }
	 	 else
	 	 {
	 		esc2 ="";
	 		pre2 = "";
	 	 }
	 	 
	 	 
	 	 //String printName="Datamax E-4203";
	 	 //String printName="Kyocera FS-1300D KX";
	 	 //String printName="Datamax M-4206 (desde HPEXPEDICION1)";
         //String printName="DatamaxM";
	 	 String printName="";
	 	 if(server.equals(""))
	 	 {
	 		printName=impresora;
	 	 }
	 	 else
	 	 {
	 		printName="\\"+"\\"+server+"\\"+impresora;
	 	 }
	 	 
	 	 System.out.println("Impresora es!!!! "+printName);
	 	 System.out.println(printName);
	 	 PrinterJob printJob = PrinterJob.getPrinterJob();
	 	 Book book = new Book();
	 	 Paper pa = new Paper();
	 	 PageFormat pageFormat = printJob.defaultPage();
	 	 pa = pageFormat.getPaper();
	 	 pa.setSize(150, 75);//primero largo
        
	 	 //pa.setImageableArea(-30, -20, 237, 145);
	 	 //System.out.println("ancho= "+pa.getWidth());
	 	 //System.out.println("alto= "+pa.getHeight());
	 	 //System.out.println("x= "+pa.getImageableX());
	 	 //System.out.println("y= "+pa.getImageableY());
	 	 //System.out.println("anchoIM= "+pa.getImageableWidth());
	 	 //System.out.println("altoIM= "+pa.getImageableHeight());
	 	 pa.setImageableArea(0, 0, 200, 120);//los 2 primeros son el x e y 
	 	 pageFormat.setPaper(pa);
	 	 book.append(new IntroPage(), pageFormat);
	 	 System.out.println(" paginaaaaas "+book.getNumberOfPages());
	 	 printJob.setPageable(book);
	 	 //printJob.setPrintable(new IntroPage (), pageFormat);
	 	 int count = 1;
	 	 PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);//con este vemos las impresoras instaladas como mis pruebas estan en red pues por eso uso este
	 	 for (PrintService printService : services) 
	 	 {
        	System.out.println(printService.getName());
        	if(printService.getName().equals(printName))
        	{
        		System.out.println("Impresora OK");
        	}
	 	 }
     
	 	 AttributeSet aset = new HashAttributeSet();
	 	 aset.add(new PrinterName(printName, null));
	 	 services = PrintServiceLookup.lookupPrintServices(null, aset);//busca la impresora
        
	 	 for (PrintService printService : services) 
	 	 {
            PrintService printers[] = PrintServiceLookup.lookupPrintServices(null, aset);
            if (printers.length == 1) 
            {
            	printJob.setPrintService(printers[0]);//le asignamos la impresora de trabajo al job
                	
            	try 
            	{
            		printJob.setJobName("Trabajo");
            		printJob.print();//imprimimos lo  q esta en el job
                }
            	catch (Exception PrintException) 
            	{
            		PrintException.printStackTrace();
                }
            }
	 	 }
	 }
     public class IntroPage implements Printable 
     {
    	 @Override
		public int print(Graphics g, PageFormat pageFormat, int page) {
         try 
         {
        	 
        	 
                Graphics2D g2d = (Graphics2D) g;
                Paper pa = new Paper();
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                System.out.println(pageFormat.getImageableX() + " "+pageFormat.getImageableY()); 
                g2d.setPaint(Color.black);
                Rectangle2D.Double rec_item = new Rectangle2D.Double(0, 0, 200, 120);//izq, sup, ancho , largo
                //Rectangle2D.Double rec_q = new Rectangle2D.Double(15, 85, 227, 56);
                
                g2d.draw(rec_item);
                //g2d.draw(rec_q);
                
                
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                
                URL imageURL = null;

                //--- Set the URL to the image that we want to load.
                //--- NOTE: Change the path to reflect your location of the image
                //--- NOTE: Only the following image type are supported JPEG, GIF
                // and PNG.
                try {

                  imageURL = new URL(
                      "file:///c:/softdev/java/articles/javaworld/printing/part_2/ss2.jpg");
                } catch (MalformedURLException me) {
                  me.printStackTrace();
                }

                //--- Load the image and wait for it to load
            
                
                Image imagen=null;
                try
                {
	                imagen = Toolkit.getDefaultToolkit().getImage( "C:/Program Files/Reclamosbase/etiqueta_print.jpg" );
	                g.drawImage(imagen, 0, 0, null);

                }
                catch(Exception e)
                {
                	System.out.println(e.toString());
                }

                
                
                String codi = codigo;
                String codi2=codigo;
                
                
                //Barcode barcode = BarcodeFactory.createCode39(codi2, false);
                //barcode.setDrawingText(false);
                //barcode.setBarHeight(10);
                //barcode.setBarWidth(1);
                //BufferedImage image = BarcodeImageHandler.getImage(barcode);
                
                
                Font qtyFont = new Font("Calibri", Font.BOLD, 11);
                Font precioFont = new Font("Calibri", Font.BOLD, 13);
                Font marcaFont = new Font("Calibri", Font.BOLD, 12);
                
                
                try 
      			 {
      				 URL ur =  new URL ("file:///C:/Program%20Files/Reclamos/etiqueta.png"); 
      				  try 
      				  {                
      		                bi = ImageIO.read(ur);
      		          } 
      				  catch (IOException ex) {
      		                System.err.println( ex.getMessage() );
      		            }        
      				
      			 } 
      			 catch (IOException e) 
      			 {
      				// TODO Auto-generated catch block
      				e.printStackTrace();
      			 }
      			
      			
                  g2d.drawImage(bi, -23, 0, 200, 110, null);
             
               
             
                
                //g2d.drawImage(image, 10, 15, null);
                
             
                
                g2d.setFont(marcaFont);
                FontMetrics qtyfontMetrics = g2d.getFontMetrics();
                
                g2d.drawString(marc, 14, 35);
                g2d.setFont(qtyFont);
                g2d.drawString(artic, 14, 45);
                g2d.setFont(precioFont);
                g2d.drawString(pre1, 14, 58);
                g2d.setFont(qtyFont);
                //g2d.drawString(esc2, 14,98);
                g2d.setFont(precioFont);
                g2d.drawString(pre2, 14, 71);
                
             
                
                
                
                
            } catch (Exception ex) {
                Logger.getLogger(imprimir_EtiArticulo.class.getName()).log(Level.SEVERE, null, ex);
            } return PAGE_EXISTS;
   }
  }
}