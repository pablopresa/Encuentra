package beans.encuentra;
import helper.PropertiesHelper;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocPrintJob;
import javax.print.PrintService;

import logica.Logica;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
//import javax.print.attribute.standard.Slides;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.printing.Orientation;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;


import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dataTypes.DataIDDescripcion;

public class IPrintPRUEBA2PAG implements Printable
{

	//class PrintObject implements Printable
	//{
	 /*  public int print (Graphics g, PageFormat f, int pageIndex)
	   {
	      Graphics2D g2 = (Graphics2D) g;  // Allow use of Java 2 graphics on
	                                       // the print pages :

	      // A rectangle that shows the printable area of the page, allowing
	      // for margins all round. To be drawn on the first page (index = 0).
	      Rectangle2D rect = new Rectangle2D.Double(f.getImageableX(),
	                                                f.getImageableY(),
	                                                f.getImageableWidth(),
	                                                f.getImageableHeight());

	      // A simple circle to go on the second page (index = 1).
	      Ellipse2D circle = new Ellipse2D.Double(100,100,100,100);

	      switch (pageIndex)
	      {
	         case 0 :   // Page 1 : print a rectangle
	                  g2.draw((Shape) rect);
	                  return PAGE_EXISTS;
	         case 1 :      // Page 2 : print a circle
	                  g2.draw(circle);
	                  return PAGE_EXISTS;
	         default: return NO_SUCH_PAGE;        // No other pages
	      }
	   }*/
	//}

	//public class Sample1
	//{
	   public static void main (String[] args)
	   {
	      // Create an object that will hold all print parameters, such as
	      // page size, printer resolution. In addition, it manages the print
	      // process (job).
	      PrinterJob job = PrinterJob.getPrinterJob();

	      // It is first called to tell it what object will print each page.
	      job.setPrintable(new Printable() {
			
			@Override
			public int print(Graphics g, PageFormat f, int pageIndex) throws PrinterException {
				Graphics2D g2 = (Graphics2D) g;  // Allow use of Java 2 graphics on
                // the print pages :
				
				 
				
				Paper paper = new Paper();
	              double width = 4d * 72d;
	              double height = 6d * 72d;
	              double margin = 0.5d * 72d;
	              paper.setSize(width, height);
	              paper.setImageableArea(margin,margin,width - (margin * 2),height - (margin * 2));
		          f = new PageFormat();
		          f.setPaper(paper);	
		          
		          g2.translate(f.getImageableX(), f.getImageableY());
		          Font font = FontFactory.getFont("Arial", 12F, 1);
		          
		          
				switch (pageIndex)
				{
				case 0 :   // Page 1 : print a rectangle
					g2.drawString("primera PAG",50,50);
					return PAGE_EXISTS;
					
				/*case 1 :      // Page 2 : print a circle
					g2.drawString("SEGUNDA PAG",20,20);
					return PAGE_EXISTS;
				case 2 :      // Page 2 : print a circle
					g2.drawString("SEGUNDA PAG",30,30);
					return PAGE_EXISTS;
				case 3 :      // Page 2 : print a circle
					g2.drawString("SEGUNDA PAG",50,50);
					return PAGE_EXISTS;	*/
				default: return NO_SUCH_PAGE;        // No other pages
				}
			}
		});

	      // Then it is called to display the standard print options dialog.
	      if (job.printDialog())
	      {
	         // If the user has pressed OK (printDialog returns true), then go
	         // ahead with the printing. This is started by the simple call to
	         // the job print() method. When it runs, it calls the page print
	         // object for page index 0. Then page index 1, 2, and so on
	         // until NO_SUCH_PAGE is returned.
	      try { job.print(); }
	         catch (PrinterException e) { System.out.println(e); }
	      }
	   }
	//}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
  

}