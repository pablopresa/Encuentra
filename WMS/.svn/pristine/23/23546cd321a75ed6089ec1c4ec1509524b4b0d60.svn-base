package logica;
import beans.encuentra.Ojo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.*;
import java.util.List;
import java.util.logging.*;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;

import net.sourceforge.barbecue.*; 
 
public class imprimir_Vale 
{
   int count=0;
    String iden, country, country2, funcionario, importeNum, cust_num;
    String fechaStr, nombreFun,firma, declaracion, declaracionII,  declaracionIII;
   boolean band=false;
     String item_no,ident,country_o,country_o2,qty2,cus;
     String sucursal;
     

     
     
 public imprimir_Vale() {
	}
public void imprimirEtiquetas(List <Ojo> ojos) throws Exception
     {
    	 imprimir_Vale obj=new imprimir_Vale();
    	 
    		 obj.impresionEtiquetaUbica("1", "25/05/2013", "Gonzalo Monzon", "1.000", 1119, "UN MIL");
    		
    	
    	 
    	 
  } 
 public void impresionEtiquetaUbica(String salta, String fecha, String nombre, String importe, int numFunc, String valor) throws Exception
 {
	 
	 
	 
     
	 //List<String> servImpre = LeerFicheroTexto.LeerProperties("ImpresoraETI.txt");
	 
	 //String server = servImpre.get(0);
	 //String impresora = servImpre.get(1);
	 	
        fechaStr = fecha;
        System.out.println(fechaStr);
        funcionario = "Funcionario: "+numFunc ;
        nombreFun = "Nombre: "+nombre;
        firma = "Firma: ........................................... \n \n";
        
        importeNum =" importe: $U "+ importe;
        declaracion = "Recibí el importe y copia de esta liquidación";
        declaracionII = "no teniendo nada que reclamar por ningún";
        declaracionIII = "concepto.";
        //String printName="Datamax E-4203";
        //String printName="Kyocera FS-1300D KX";
        //String printName="Datamax M-4206 (desde HPEXPEDICION1)";
        
        //String printName="DatamaxM";
        
        sucursal = salta;
        
        
        //String printName="\\"+"\\"+server+"\\"+impresora;
        
        String printName="CITIZEN CT-S310II";
        System.out.println("Impresora es!!!! "+printName);
        
        
        PrinterJob printJob = PrinterJob.getPrinterJob();
        Book book = new Book();
        
        Paper pa = new Paper();
        
        PageFormat pageFormat = printJob.defaultPage();
        pa = pageFormat.getPaper();
        
        pa.setSize(229, 390);
        
        //pa.setImageableArea(-30, -20, 237, 145);
        //System.out.println("ancho= "+pa.getWidth());
        //System.out.println("alto= "+pa.getHeight());
        //System.out.println("x= "+pa.getImageableX());
        //System.out.println("y= "+pa.getImageableY());
        //System.out.println("anchoIM= "+pa.getImageableWidth());
        //System.out.println("altoIM= "+pa.getImageableHeight());
        pa.setImageableArea(0, 0, 230, 300);
        
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
private class IntroPage implements Printable 
{
         @Override
		public int print(Graphics g, PageFormat pageFormat, int page) {
            try {
                Graphics2D g2d = (Graphics2D) g;
              
               
                
                
                    
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                System.out.println(pageFormat.getImageableX() + " "+pageFormat.getImageableY()); 
                g2d.setPaint(Color.black);
                //Rectangle2D.Double rec_item = new Rectangle2D.Double(15, 1, 227, 84);
                //Rectangle2D.Double rec_q = new Rectangle2D.Double(15, 85, 227, 56);
                
                //g2d.draw(rec_item);
                //g2d.draw(rec_q);
                if(sucursal!=null)
                {
                	 Font nFont = new Font("Arial", Font.BOLD, 19);
                	 g2d.setFont(nFont);
                     
                     g2d.drawString("FIN "+sucursal, 12, 60);
                }
                else
                {
                
	                String country = "CYBE SA   -  STADIUM";
	                Font countryFont = new Font("Arial", Font.BOLD, 8);
	                
	                Font itemFont = new Font("Arial", Font.BOLD, 6);
	                
	                /*
	                String n = "(4 Digitos)";
	                */
	                Font nFont = new Font("Arial", Font.BOLD, 9);
	                String itemn = fechaStr;
	                
	                String item3=itemn;
	                Font itemnFont = new Font("Arial", Font.BOLD, 14);
	               
	                Barcode barcode = BarcodeFactory.createCode39(item3, false);
	                barcode.setDrawingText(false);
	                barcode.setBarHeight(45);
	                barcode.setBarWidth(1);
	                //BufferedImage image = BarcodeImageHandler.getImage(barcode);
	                
	                Font qualityFont = new Font("Arial", Font.BOLD, 6);
	                
	                Font qFont = new Font("Arial", Font.BOLD, 6);
	                
	                Font qtyFont = new Font("Arial", Font.BOLD, 14);
	                
	                
	                
	                Font country2Font = new Font("Arial", Font.BOLD, 10);
	                g2d.setFont(countryFont);
	                FontMetrics countryfontMetrics = g2d.getFontMetrics();
	                g2d.drawString(country, 48, 8);
	               
	                g2d.setFont(nFont);
	                FontMetrics nfontMetrics = g2d.getFontMetrics();
	                g2d.drawString(itemn, 170, 7);
	                g2d.drawString(declaracion, 12, 115);
	                g2d.drawString(declaracionII, 12, 125);
	                g2d.drawString(declaracionIII, 12, 135);
	                g2d.setFont(itemnFont);
	                FontMetrics itemnfontMetrics = g2d.getFontMetrics();
	                
	                //g2d.drawImage(image, 30, 40, null);
	                
	                g2d.setFont(qFont);
	                FontMetrics qfontMetrics = g2d.getFontMetrics();
	                
	                g2d.setFont(qtyFont);
	                FontMetrics qtyfontMetrics = g2d.getFontMetrics();
	                g2d.drawString(funcionario, 12, 40);
	                g2d.drawString(nombreFun, 12, 60);
	                
	                g2d.drawString(importeNum, 12, 100);
	                g2d.drawString(firma, 12, 190);
                }
                
            } 
            catch ( Exception e) 
            {
                Logger.getLogger(imprimir_Vale.class.getName()).log(Level.SEVERE, null, e);
            }
			return page;
   }
  }
}