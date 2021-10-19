package beans.encuentra;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.print.DocPrintJob;
import javax.print.PrintService;



import org.apache.pdfbox.pdmodel.PDPage;
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
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.printing.Orientation;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.wss.saml.internal.saml11.jaxb10.X509DataType.X509Certificate;

import beans.Fecha;
import beans.Usuario;
import beans.datatypes.DataIDDescripcion;
import beans.helper.PropertiesHelper;
import beans.helper.PropertiesHelperAPI;
import logica.LogicaAPI;

import javax.net.ssl.X509TrustManager;

public class IPrint 
{

	private int id;
	
	
	
	
	
	public IPrint() 
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
	
	
	
	public static String ImprimirEtiquetasNuevas(DataArticuloEcommerceVerifR ar,int idEmpresa,DataIDDescripcion env, String direccionRemite,
			String notaAlPie, int Cantidad,boolean pickup, String direccionDestino, String obs)  throws FileNotFoundException, DocumentException
	{
		LogicaAPI Logica = new LogicaAPI();
		
		//List<DataIDDescripcion> articulos, int idPedido, String factura,  String cliente,String tel, String ci, String mail, Book book2
	    
		String path = "";
		String pathSalida = "";
		String download_pdf_from="";
		String delivery ="";
		String qr="";
		String envio = "";
		String envioId="";
		String envioDir="";
		
	    try
	    {
	    	
	    	
	    	System.out.println("---");
	    	
			
			if(env!=null){
				envio = env.getDescripcion();
				envioId=String.valueOf(env.getId());
				envioDir = env.getDescripcionB();
			}
			
	    	PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");	    	
	    	try
	    	{
	    		pH.loadProperties();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}	
	    	
	    	System.out.println("antes img");
	    	
	    	String logo = pH.getValue("pdf")+idEmpresa+".png";
	    	
	    	System.out.println("despues img");
	    			
			path = pH.getValue("pdf")+ar.getIdPedido()+"Pickup.pdf";
			pathSalida = pH.getValue("HTTP_pdf")+"/"+ar.getIdPedido()+"Pickup.pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(431F,450F);
	        Document documento = new Document(pageSize);
	        Font font = FontFactory.getFont("Arial", 10F, 1);
	        Font fontsmall = FontFactory.getFont("Arial", 8F, 1);
	        Font font1100 = FontFactory.getFont("Arial", 20F, 1);
	      
	        
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.open();
	       // documento.setMargins(1F, 1F, 1F, 1F);
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	        for (int i = 0; i < Cantidad; i++) 
	        {
	        	documento.newPage();
		        
		        PdfPTable table = new PdfPTable(2);
		        //table.setTotalWidth(289F);
		        
		        Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
		        
		        String downloaded_filename_open_as_pdf="";
		      
		        
		        
		        
		        Image imagenTienda = null;
		                        	
				try {
					imagenTienda = Image.getInstance(logo);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        																	/* IMAGEN DE TIENDA*/
		        imagenTienda.scaleToFit(100, 100);	
		        PdfPCell cell = new PdfPCell(new PdfPCell(imagenTienda));
		        if(ar.getCanal()==-1){
		        	cell.setBackgroundColor(BaseColor.BLACK);
		        }                
		        //cell.setHorizontalAlignment(1);
		        cell.setPaddingTop(5F);
		        cell.setPaddingLeft(3F);
		        //cell.setColspan(3);
		        //cell.setPaddingBottom(1);
		        cell.setBorder(Rectangle.LEFT | Rectangle.TOP);
		        table.addCell(cell);
																					/* CODIGO DE BARRAS*/
		        Barcode128 code39 = new Barcode128();
		        code39.setCode(ar.getIdPedido()+"");                
		        code39.setBarHeight(50F);
		        code39.setX(1.0F);
		        cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	            cell.setHorizontalAlignment(1);
	            cell.setPaddingTop(5F);
	            //cell.setPadding(3F);
	            //cell.setColspan(6);
		        cell.setBorder(Rectangle.RIGHT | Rectangle.TOP);
	            table.addCell(cell);
	            
	            cell = new PdfPCell(new Paragraph("", font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph("Guia #:", font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT );
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph(envioId+"-"+envio,font1100));
		        cell.setHorizontalAlignment(1);
		        //cell.setPadding(3F);
		        cell.setColspan(2);
		        cell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT);
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Paragraph("Envio: VCO", font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.LEFT);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph("Pieza: "+(i+1) +" de "+Cantidad, font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.RIGHT);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph("Remitente: "+direccionRemite, font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.LEFT);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph("Peso (Total):", font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.RIGHT);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph("Cliente: ", font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.LEFT);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph("Referencia: "+ar.getIdEcommerce(), font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.RIGHT);
		        table.addCell(cell);
		        
		        Font fontClient = FontFactory.getFont("Arial", 15F, 1);
		        cell = new PdfPCell(new Paragraph(ar.getDescripcion(), fontClient));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        /*if(idEmpresa!=8) {
		        	cell.setColspan(2);
		        }	*/	        
		        cell.setBorder(Rectangle.LEFT);
		        table.addCell(cell);
		        
//		        BAS
		        //if(idEmpresa==8) {
		        	cell = new PdfPCell(new Paragraph("Cedula: "+ar.getCiCliente(), font));	        
		        	//cell.setColspan(2);
		        	cell.setBorder(Rectangle.RIGHT);
		        	table.addCell(cell);
		        //}
		        
		        cell = new PdfPCell(new Paragraph("Telefono: "+ar.getTelCliente().replace("+598", "0"), font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setColspan(2);
		        cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
		        table.addCell(cell);
		        
		        if(pickup) {
		        	cell = new PdfPCell(new Paragraph("Pick Up: "+envioDir, font));	
		        }
		        else {
		        	cell = new PdfPCell(new Paragraph("Direccion de Envio: "+direccionDestino, font));
		        }
		                
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setColspan(2);
		        cell.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Paragraph("Observaciones:"+obs, font));	        
		        //cell.setHorizontalAlignment(1);
		        cell.setFixedHeight(50F);
		        cell.setColspan(2);
		        cell.setBorder(Rectangle.BOTTOM| Rectangle.RIGHT | Rectangle.LEFT);
		        cell.setPaddingBottom(5F);
		        
		        if(pickup && idEmpresa==2)
		        {
			        code39 = new Barcode128();
			        code39.setCode(ar.getIdEcommerce()+"");                
			        code39.setBarHeight(30F);
			        code39.setX(1.0F);
			        cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
			        cell.setHorizontalAlignment(1);
		            cell.setPaddingTop(5F);
		            cell.setColspan(2);
			        cell.setBorder(Rectangle.BOTTOM| Rectangle.RIGHT | Rectangle.LEFT);
			        cell.setPaddingBottom(5F);
		        }
		        
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph("Ventana Horaria:", font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.BOTTOM| Rectangle.LEFT);
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph("Fecha Creacion: "+ar.getFecha(), font));	        
		        //cell.setHorizontalAlignment(1);
		        //cell.setFixedHeight(410F);
		        cell.setBorder(Rectangle.BOTTOM| Rectangle.RIGHT );
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);
		        
		        
		        cell = new PdfPCell(new Paragraph("Recibido por: "+ar.getDescripcion(), font));	        
		        //cell.setHorizontalAlignment(1);
		        cell.setFixedHeight(50F);
		        cell.setColspan(2);
		        cell.setBorder(Rectangle.BOTTOM| Rectangle.RIGHT | Rectangle.LEFT);
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);
		        
		        
		        //cell = new PdfPCell(new Paragraph("	Av. Italia 4346   								   						    Tel.:2613 7566 		  Montevideo-Uruguay", fontsmall));	        
		        cell = new PdfPCell(new Paragraph(notaAlPie, fontsmall));
		        //cell.setHorizontalAlignment(1);
		        cell.setColspan(2);
		        cell.setBorder(Rectangle.BOTTOM| Rectangle.RIGHT | Rectangle.LEFT);
		        table.addCell(cell);
		        
		        
		        documento.add(table);
			}
	        
	        documento.close();
	        
	    }
	    catch(DocumentException e)
	    {
	        e.printStackTrace();
	    }
	    catch(FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    
	    return pathSalida;
	        	               
	    }
	
}