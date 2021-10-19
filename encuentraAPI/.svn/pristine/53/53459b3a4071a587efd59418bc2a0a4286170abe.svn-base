package beans;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.DocPrintJob;
import javax.print.PrintService;

import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageSettings;
import com.spire.pdf.PdfPrintPageScaling;

public class PrintExample 
{
	
		
		    public static void main(String[] args) {
		
		        //load the sample document
		
		        PdfDocument pdf = new PdfDocument();
		
		        pdf.loadFromFile("C:\\EtiquetasEncuentra\\1559009n.pdf");
		        
		        long l = 2000002712037082L;
		        
		        System.out.println(l);
		 
		
		        PrinterJob loPrinterJob = PrinterJob.getPrinterJob();
		        PrintService[] services = PrinterJob.lookupPrintServices();
		        DocPrintJob docPrintJob = null;
		        for (int i = 0; i < services.length; i++) 
	    		{
	    			System.out.println(services[i].getName());
	    			if (services[i].getName().equals("TICKET")) 
	    			{
	    				docPrintJob = services[i].createPrintJob();
	    				break;
	    			}
	    		}
		
		        
		        try 
		        {
					loPrinterJob.setPrintService(docPrintJob.getPrintService());
				} 
		        catch (PrinterException e1) 
		        {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
		        
		        
		        PageFormat loPageFormat = new PageFormat();
		        Paper paper = new Paper();
	    		
	    		
	    		
	    		
	    		 
	    		paper.setSize(10 * 72.0f, 30 * 72.0f);
	    		
	    		loPageFormat.setPaper(paper);
	    		
	    		Paper loPaper = loPageFormat.getPaper();
		        
		
				
		        //remove the default printing margins
		        double margin = 0.1d * 72d;
		
		        loPaper.setImageableArea(margin,margin,loPageFormat.getWidth()-1,loPageFormat.getHeight()-1);
		
		 
		        PdfPageSettings p = new PdfPageSettings();
		        p.setMargins(-2 * 72.0f, 0);
		        p.setWidth(8 * 72.0f);
		        
		        
		        pdf.setPageScaling(PdfPrintPageScaling.Custom_Sacle);
		        pdf.setPageSettings(p);
		        pdf.setCustomScaling(82);
		
		        loPageFormat.setPaper(loPaper);
		        loPrinterJob.setPrintable(pdf,loPageFormat);
		
		 
		
		        //display the print dialog
		        try {
					loPrinterJob.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		
		    }
		
		


}
