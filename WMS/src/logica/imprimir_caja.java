package logica;
import beans.encuentra.Ojo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.util.List;
import java.util.logging.*;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import net.sourceforge.barbecue.*; 
import net.sourceforge.barbecue.output.OutputException; 
 
public class imprimir_caja 
{
   int count=0;
    String iden, country, country2, qty, qty2T, qty3, cust_num;
    String item="16U-26084-03";
   boolean band=false;
     String item_no,ident,country_o,country_o2,qty2,cus;

     

     
     
     public imprimir_caja() 
     {
     }
     
     public void imprimirEtiquetas(List <Ojo> ojos) throws Exception
     {
    	 imprimir_caja obj=new imprimir_caja();
    	 for (Ojo ojo : ojos) 
    	 {
    		 obj.impresionEtiquetaUbica(ojo.getIdOjo(), "", ojo.getIdSector(),ojo.getEstante(),ojo.getModulo());
    		 System.out.println("mando ojo" + ojo.getIdOjo());
    	 }
     } 
     public void impresionEtiquetaUbica(String idEtiqueta, String CodCompuesto, int sector, int estante, int modulo) throws Exception
     {
    	 List<String> servImpre = LeerFicheroTexto.LeerProperties("ImpresoraETI.txt");
	 	 String server = servImpre.get(0);
	 	 String impresora = "datamax";//servImpre.get(1);
	 	 item = idEtiqueta;
	 	 System.out.println(item);
	 	 iden = "012122IDEM";
	 	 country = "1114";
	 	 country2 = "8966654";
	 	 qty = "Estanteria "+sector;
	 	 qty2T ="Modulo "+ modulo;
	 	 qty3 ="Estante "+ estante;
	 	 cust_num = "9999";
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
	 	 pa.setSize(234, 83.32);
        
	 	 //pa.setImageableArea(-30, -20, 237, 145);
	 	 //System.out.println("ancho= "+pa.getWidth());
	 	 //System.out.println("alto= "+pa.getHeight());
	 	 //System.out.println("x= "+pa.getImageableX());
	 	 //System.out.println("y= "+pa.getImageableY());
	 	 //System.out.println("anchoIM= "+pa.getImageableWidth());
	 	 //System.out.println("altoIM= "+pa.getImageableHeight());
	 	 pa.setImageableArea(4, 4,230, 129.32);
	 	 pageFormat.setPaper(pa);
	 	 book.append(new IntroPage(), pageFormat);
	 	 System.out.println(" paginaaaaas "+book.getNumberOfPages());
	 	 //printJob.setPageable(book);
	 	 printJob.setPrintable(new IntroPage (), pageFormat);
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
     private class IntroPage implements Printable 
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
                //Rectangle2D.Double rec_item = new Rectangle2D.Double(15, 1, 227, 84);
                //Rectangle2D.Double rec_q = new Rectangle2D.Double(15, 85, 227, 56);
                
                //g2d.draw(rec_item);
                //g2d.draw(rec_q);
                
                String country = "SISTEMA DE UBICACIONES ENCUENTRA";
                Font countryFont = new Font("Arial", Font.BOLD, 8);
                
                Font itemFont = new Font("Arial", Font.BOLD, 6);
                String n = "(4 Digitos)";
                Font nFont = new Font("Arial", Font.BOLD, 6);
                String itemn = item;
                String item3=itemn;
                Font itemnFont = new Font("Arial", Font.BOLD, 14);
                
                Barcode barcode = BarcodeFactory.createCode39(item3, false);
                barcode.setDrawingText(false);
                barcode.setBarHeight(45);
                barcode.setBarWidth(1);
                BufferedImage image = BarcodeImageHandler.getImage(barcode);
                
                Font qualityFont = new Font("Arial", Font.BOLD, 6);
                
                Font qFont = new Font("Arial", Font.BOLD, 6);
                
                Font qtyFont = new Font("Arial", Font.BOLD, 14);
                
                
                
                Font country2Font = new Font("Arial", Font.BOLD, 10);
                g2d.setFont(countryFont);
                FontMetrics countryfontMetrics = g2d.getFontMetrics();
                g2d.drawString(country, 51, 13);
               
                g2d.setFont(nFont);
                FontMetrics nfontMetrics = g2d.getFontMetrics();
                g2d.drawString(n, 30, 33);
                g2d.setFont(itemnFont);
                FontMetrics itemnfontMetrics = g2d.getFontMetrics();
                g2d.drawString(itemn, 82, 32);
                g2d.drawImage(image, 30, 40, null);
                
                g2d.setFont(qFont);
                FontMetrics qfontMetrics = g2d.getFontMetrics();
                
                g2d.setFont(qtyFont);
                FontMetrics qtyfontMetrics = g2d.getFontMetrics();
                g2d.drawString(qty, 120, 50);
                g2d.drawString(qty2T, 120, 65);
                g2d.drawString(qty3, 120, 80);
                
                
            } catch (OutputException ex) {
                Logger.getLogger(imprimir_caja.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BarcodeException ex) {
                Logger.getLogger(imprimir_caja.class.getName()).log(Level.SEVERE, null, ex);
            } return PAGE_EXISTS;
   }
  }
}