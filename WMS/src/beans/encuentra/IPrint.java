package beans.encuentra;
import helper.PropertiesHelper;
import jsonObjects.Credenciales;
import jsonObjects.Shipping;

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

import logica.Logica;
import logica.Utilidades;

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
//import org.apache.pdfbox.util.PDFImageWriter;
import org.apache.poi.hssf.util.HSSFColor.BLACK;

import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import cliente_rest_Invoke.Call_WS_MODDO;
import cliente_rest_Invoke.Call_WS_analoga;

import com.gargoylesoftware.htmlunit.javascript.host.Element;
import com.gnostice.pdfone.PdfCell;
import com.gnostice.pdfone.PdfDocument;
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
import beans.bulto;
import beans.bultoContenido;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataDescDescripcion;
import dataTypes.DataEcommerce_canales_envio;
import dataTypes.DataIDDescDescripcion;
import dataTypes.DataIDDescripcion;
import dataTypes.PickupCod;
import eCommerce_jsonObjectsII.Cliente;
import eCommerce_jsonObjectsII.EncuentraPedido;

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

	public static boolean saveFile(URL url, String file, String urlSimple, boolean loginFenicio) throws IOException {
    
	try
	{
		  
		
		if(!loginFenicio)
		{
			//stem.setProperty("https.proxyHost","https://system.na1.netsuite.com/core/media/media.nl?id=29383635&c=3793234&h=aa1168e6ced488839105&_xt=.pdf") ; 
			//System.setProperty("https.proxyPort", "443") ; 
			//System.setProperty("https.protocols", "TLSv1,TLSv1.2");
			boolean download_status = false;
		    //Datamax-O'Neil E-4205A Mark III #2
			try
            {
            // Create a trust manager that does not validate certificate chains
				TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
               @Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                   return null;
               }
               public void checkClientTrusted(X509Certificate[] certs, String authType) {
               }
               public void checkServerTrusted(X509Certificate[] certs, String authType) {
               }
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
				}
				};

				// Install the all-trusting trust manager
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

				// 	Create all-trusting host name verifier
				HostnameVerifier allHostsValid = new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};

				// Install the all-trusting host verifier
				HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);    
            
            }
            catch (Exception e)
            {
                   e.printStackTrace();
            }	
			
			
			
			
		    System.out.println("[OK] - open");
		    
		    InputStream in = url.openStream();
		    FileOutputStream fos = new FileOutputStream(new File(file));
		    System.out.println("[OK] - reading file...");
		    int length = -1;
		    byte[] buffer = new byte[1024];

		    while ((length = in.read(buffer)) > -1) {
		        fos.write(buffer, 0, length);
		    }
		    fos.close();
		    in.close();

		    download_status = true;
		    System.out.println("[OK] - downloaded");
		    return download_status;
		}
		else
		{
			DefaultHttpClient client = new DefaultHttpClient();
			 
	        try 
	        {
	        
		        PropertiesHelper pH=new PropertiesHelper("clienteAdminAnaloga");
		  		pH.loadProperties();
		  		String usr = pH.getValue("txtUsuario");
		  		String pwd = pH.getValue("txtPassword");
	        	
		  		HttpGet securedResource = new HttpGet(url.toString());            
	            HttpResponse httpResponse = client.execute(securedResource);
	            HttpEntity responseEntity = httpResponse.getEntity();
	            String strResponse = EntityUtils.toString(responseEntity);
	            
	            String [] arreglo = strResponse.split("\r\n");
	            
	            String token = "";
	            for (String s : arreglo) 
	            {
	            	//System.out.println(s);
	            	if(s.contains("  var STOKEN = '"))
	            	{
	            		token = s.replace("var STOKEN","");
	            		token = token.replace("=","");
	            		token = token.replace("\t","");
	            		token = token.replace(" ","");
	            		token = token.replace(";","");
	            		token = token.replace("'","");
	            		System.out.println(token);
	            		break;
	            		
	            	}
				}
	            int statusCode = httpResponse.getStatusLine().getStatusCode();
	            //EntityUtils.consume(responseEntity);
	 
	            System.out.println("Http status code for Unauthenticated Request: " + statusCode);// Statue code should be 200
	           // System.out.println("Response for Unauthenticated Request: n" + strResponse); // Should be login page
	            //System.out.println("================================================================n");
	 
	            HttpPost authpost = new HttpPost("https://www.stadium.com.uy/admin.php/ingresar");
	            List<NameValuePair> nameValuePairs = new ArrayList<>();
	            nameValuePairs.add(new BasicNameValuePair("txtUsuario", usr));
	            nameValuePairs.add(new BasicNameValuePair("txtPassword", pwd));
	            nameValuePairs.add(new BasicNameValuePair("token", token));
	            nameValuePairs.add(new BasicNameValuePair("_frm", "frmLogin"));
	            
	            authpost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	            authpost.setHeader("Content-Type", "application/x-www-form-urlencoded");
	 
	            httpResponse = client.execute(authpost);
	            responseEntity = httpResponse.getEntity();
	            strResponse = EntityUtils.toString(responseEntity);
	            statusCode = httpResponse.getStatusLine().getStatusCode();
	            //EntityUtils.consume(responseEntity);
	 
	            System.out.println("Http status code for Authenticattion Request: " + statusCode);// Status code should be 302
	            System.out.println("Response for Authenticattion Request: n" + strResponse); // Should be blank string
	            System.out.println("================================================================n");
	 
	            httpResponse = client.execute(securedResource);
	            responseEntity = httpResponse.getEntity();
	            
	            
	            /***********************************descargo el PDF******************************************/
	          
				InputStream is = responseEntity.getContent();
				File fileF = new File(file);
	            fileF.delete();
	            FileOutputStream fos = new FileOutputStream(new File(file));
	            int inByte;
	            while ((inByte = is.read()) != -1) 
	            {
	                fos.write(inByte);
	            }
	            is.close();
	            fos.close();
	            return true;
	           
	        }
	        catch (Exception ex) 
	        {
	            ex.printStackTrace();
	            return false;
	        }
		}
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
        return false;
        	
	}
	
  }

  public static void main(String[] args) throws IOException, PrinterException 
  {  
	  
	  
	  
	  
	  //String pdfURL = "http://10.108.0.19:8085/encuentra/pdf/70199.pdf";
	  int idEmpresa = 8;
	  
	  List<DataIDDescripcion> totest = new ArrayList<>();
	  totest.add(new DataIDDescripcion(1,"JUG010003"));
	  
	  DataArticuloEcommerceVerifR a = new DataArticuloEcommerceVerifR(4011802195L,0,1,"","Mario Aguilar", "",800000);
	  a.setCanal(1);
	  a.setMl(0);
	  a.setCiCliente("18946599");
	  a.setMailCliente("");
	  a.setTelCliente("");
	  
	  Fecha fechaActual = new Fecha();
	  
	  
	  
	  DataArticuloEcommerceVerifR articuloR = new DataArticuloEcommerceVerifR(4011802195L, 0, 1, "", "Mario Aguilar", "", 1);
		articuloR.setTelCliente("telefono");
		articuloR.setFecha(fechaActual.darFechaAnio_Mes_Dia());
		String url = "";											//GENERO ETIQUETA PICKUP
		try 
		{
			DataIDDescripcion env =  new DataIDDescripcion(1, "Gonzalo Monzon");
			env.setDescripcionB("Nueva Palmira 1905 esq. DefensaTel:?(+598) 2209.4894");
			
			
			
			IPrint.ImprimirEtiquetasNuevas(articuloR,idEmpresa,env,"Nueva Palmira 1905 esq. DefensaTel:?(+598) 2209.4894", "",3,true,"","");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}							
		   
	

  }
  
  
  public static void imprimeEtEccomerce(DataArticuloEcommerceVerifR a, List<DataIDDescripcion> articulos,String factura,boolean rotar, boolean procesado, int idEmpresa) throws IOException, PrinterException, DocumentException 
  {  
	  Logica Logica = new Logica();
	  //String pdfEti, int idPedido, List<DataIDDescripcion> articulos, String factura, String cliente,String tel,String ci, String mail, boolean rotar
	  int esPickup=0;
	  try
	  { 
		  try{
			  esPickup = Logica.darCustomShipp(a.getIdPedido(),idEmpresa).getId();
		  }
		  catch(Exception e){
			  System.out.println(e);
		  }
		  
		  if(esPickup!=1) {
		  
		PropertiesHelper pH=new PropertiesHelper("EcommerceLabelPrinter");
		pH.loadProperties();
		
	  
	  String downloaded_filename = pH.getValue("folder")+a.getIdPedido()+"pdf.pdf";
	  
	  String download_pdf_from = a.getUrlEtiqueta();
	  String downloaded_filename_open_as_pdf = downloaded_filename;

	    String printerNameDesired = pH.getValue("default");

	    PrintService[] services = PrinterJob.lookupPrintServices();
	    DocPrintJob docPrintJob = null;
	    for (int i = 0; i < services.length; i++) {
	      System.out.println(services[i]);
	    }   

	    try
	    {
	      URL url = new URL(download_pdf_from);

	      
	      int idDestino = Logica.darDestinoEcommercePedido(a.getIdPedido(),idEmpresa);
	      
	      boolean loginFenicio = false;
	   
	      
	      if(!download_pdf_from.contains("encuentra"))
	      {
		      if(idDestino==701 || idDestino==900)
		      {
		    	 if(!Logica.esMLPedido(a.getIdPedido(),idEmpresa))
		    	 {
		    		 loginFenicio=true;
		    	 }
		      }
	      }
	      
	      
	      
	      if(saveFile(url, downloaded_filename,download_pdf_from,loginFenicio)) 
	      {
	    	  
	    	  
	    	  
	        try {
	         PDDocument pdf = PDDocument.load(new File(downloaded_filename_open_as_pdf));
	          PrinterJob job = PrinterJob.getPrinterJob();
	          for (int i = 0; i < services.length; i++) 
	          {
	        	  System.out.println(services[i].getName());
	           if (services[i].getName().equalsIgnoreCase(printerNameDesired)) {
	             docPrintJob = services[i].createPrintJob();
	           }
	          }

	          job.setPrintService(docPrintJob.getPrintService());
	          
	          
	          
	          
	          
	          PDDocument doc = PDDocument.load(new File(downloaded_filename_open_as_pdf));
	          
	          PDPage page = doc.getPage(0);
	          
	          
	          // define custom paper
	          Paper paper = new Paper();
              double width = 4d * 72d;
              double height = 6d * 72d;
              double margin = 0.5d * 72d;
              paper.setSize(width, height);
              paper.setImageableArea(margin,margin,width - (margin * 2),height - (margin * 2));
	          PageFormat pageFormat = new PageFormat();
	          pageFormat.setPaper(paper);

	          if(rotar)
	          {
	        	  pageFormat.setOrientation(PageFormat.LANDSCAPE);
	          }
	          
	          
	          // override the page format
	          Book book = new Book();
	          
	          // append all pages
	          book.append(new PDFPrintable(doc, Scaling.ACTUAL_SIZE), pageFormat, doc.getNumberOfPages());
	          job.setPageable(book);

	          
	        
	           
	          //job.print();
	          
	        	  //ImprimirEtiquetasNuevas(articulos, a, factura, book,procesado);
	          
	          
	          //ImprimirEtiquetasGrandes(articulos,idPedido,factura,cliente,book);
	        

	        } catch (Exception e) {
	        	
	        		//ImprimirEtiquetasNuevas(articulos, a, factura, null,procesado);
	        	
	        	
	        	//ImprimirEtiquetasGrandes(articulos,idPedido,factura,cliente,null);
	          System.out.println("[FAIL]" + e);
	        }      
	      } else {
	        System.out.println("[FAIL] - download fail");
	        //ImprimirEtiquetasNuevas(articulos, a, factura, null,procesado);
	        //ImprimirEtiquetasGrandes(articulos,idPedido,factura,cliente,null);
	      }      
	    } catch (Exception ae) 
	    {
	    	//ImprimirEtiquetasGrandes(articulos,idPedido,factura,cliente,null);
	    	//ImprimirEtiquetasNuevas(articulos, a, factura, null,procesado);
	      System.out.println("[FAIL]" + ae);
	    }
	  }
	  else{
		  //ImprimirEtiquetasNuevas(articulos, a, factura, null,procesado);
	  }

	  }
	  catch(Exception e)
	  {
	  }
	  
  }
  
  		
  	
  		
	public static String ImprimirEtiquetasNuevas(DataArticuloEcommerceVerifR ar,int idEmpresa,DataIDDescripcion env, String direccionRemite,
			String notaAlPie, int Cantidad,boolean pickup, String direccionDestino, String obs)  throws FileNotFoundException, DocumentException
	{
		Logica Logica = new Logica();
		
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
	    	Logica.logPedido(ar.getIdPedido(), 0, 0, "IMPRESION DE ETIQUETA",1,idEmpresa);
	    	
	    	System.out.println("---");
	    	
			
			if(env!=null){
				envio = env.getDescripcion();
				envioId=String.valueOf(env.getId());
				envioDir = env.getDescripcionB();
			}
			
	    	PropertiesHelper pH=new PropertiesHelper("paths");	    	
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
		      
		        
		        //ARRANCO ETIQUETA
		        Logica.logPedido(ar.getIdPedido(), 0, 0, "EMPEZANDO CREACION DE ETIQUETA",1,idEmpresa);
		        
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
		
	public static String ImprimirEtiquetasMIRTRANS(String xml, String tel, String dpto, String obs)  throws FileNotFoundException, DocumentException
    {
			Logica Logica = new Logica();
			        
			String path = "";
			String download_pdf_from="";
			String pathSalida="";
        try
        {	        	
        	String[] datos = xml.split("\"");        	
        	PropertiesHelper pH=new PropertiesHelper("paths");
        	
        	try
        	{
        		pH.loadProperties();
        	}
        	catch(Exception e)
        	{
        		
        	}
        	
    		path = pH.getValue("pdf")+"//"+datos[13]+".pdf";
    		pathSalida = pH.getValue("HTTP_pdf")+"//"+datos[13]+".pdf";
            FileOutputStream archivo = new FileOutputStream(path);
            //Rectangle pageSize = new Rectangle(431F,450F);
            Rectangle pageSize = new Rectangle(450F,431F);
            Document documento = new Document(pageSize);
            Font font = FontFactory.getFont("Arial", 12F, 1);
            Font fontCod = FontFactory.getFont("Arial", 10F, 1);
            Font fontSmall = FontFactory.getFont("Arial", 8F, 1);
            Font fontEnvId = FontFactory.getFont("Arial", 70F, 1);
            
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
            documento.open();
           // documento.setMargins(1F, 1F, 1F, 1F);
            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
            documento.newPage();
            
            PdfPTable table = new PdfPTable(4);
            //table.setTotalWidth(289F);
            
            Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
            
            String downloaded_filename_open_as_pdf="";
           	           	            
            //ARRANCO ETIQUETA
           	            
            PdfPCell cell = new PdfPCell(new Paragraph("",fontSmall));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(datos[3],fontSmall));
            cell.setHorizontalAlignment(1);
            cell.setColspan(1);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
                            
            cell = new PdfPCell(new Paragraph(datos[5], font));
            cell.setHorizontalAlignment(4);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(datos[9],fontCod));
            cell.setHorizontalAlignment(4);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("",fontCod));
            cell.setHorizontalAlignment(4);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            Barcode39 code39 = new Barcode39();
            code39.setCode(datos[13]);                
            code39.setBarHeight(50F);
            code39.setX(1.0F);                
			
			cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
            cell.setHorizontalAlignment(1);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(datos[15],font));
            cell.setHorizontalAlignment(1);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("Tel: "+tel,fontCod));
            cell.setHorizontalAlignment(4);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(dpto,fontCod));
            cell.setHorizontalAlignment(4);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(datos[19],fontCod));
            cell.setHorizontalAlignment(4);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(datos[21],fontCod));
            cell.setHorizontalAlignment(4);
            cell.setColspan(3);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(datos[27],fontCod));
            cell.setHorizontalAlignment(4);
            cell.setColspan(1);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(datos[23],fontCod));
            cell.setHorizontalAlignment(3);
            cell.setColspan(3);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(datos[29],fontCod));
            cell.setHorizontalAlignment(3);
            cell.setColspan(1);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("CE: "+datos[25],fontCod));
            cell.setHorizontalAlignment(3);
            cell.setColspan(3);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph(datos[31],fontCod));
            cell.setHorizontalAlignment(3);
            cell.setColspan(1);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("",fontCod));
            cell.setHorizontalAlignment(3);
            cell.setColspan(4);
            cell.setBorder(Rectangle.BOTTOM);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("Obs: "+obs,fontCod));
            cell.setHorizontalAlignment(4);
            cell.setPaddingTop(4);
            cell.setColspan(4);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
                            
            
            documento.add(table);
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
		
		public static String generarEticonImg(Image imagen, int idPedido,String delivery){
			
			String path = "";
		String qr = "";
		 try
	    {
	    	PropertiesHelper pH=new PropertiesHelper("EcommerceLabelPrinter");
	    	
	    	try
	    	{
	    		pH.loadProperties();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}
			 	    		
			path = pH.getValue("folder")+idPedido+"logo.pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(215F,135F);
	        Document documento = new Document(pageSize);
	        Font font = FontFactory.getFont("Arial", 12F, 1);
	        Font fontCod = FontFactory.getFont("Arial", 18F, 1);
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.setMargins(0, 0, 0, 0);
	        documento.open();
	       
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();		            
	        
	        String downloaded_filename_open_as_pdf="";
	         	           
			Font fontEnvId = FontFactory.getFont("Arial", 10F, 1);                
	         
			//CREO ETIQUETA CON QR
			
	        /*Paragraph p1 = new Paragraph();
	        Chunk c1 = new Chunk();
	        c1.append("?GRACIAS POR TU COMPRA! \n Tu factura te fue \n enviada por mail");
	        c1.setFont(fontEnvId);
	        p1.add(c1); 	          
	        p1.setSpacingAfter(35F);
	        documento.add(p1);*/
	        
	        
	        	imagen.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
	        	imagen.setSpacingBefore(5);
	        	//imagen.setAbsolutePosition(105F, 15F);
	        	documento.add(imagen);
	        	
	        	Paragraph p1 = new Paragraph();
	            Chunk c1 = new Chunk();
	            c1.append(delivery);
	            c1.setFont(fontEnvId);
	            p1.add(c1); 	  
	            p1.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
	            p1.setSpacingAfter(35F);
	            documento.add(p1);
	        
	        
	       /* Paragraph p2 = new Paragraph();
	        Chunk c2 = new Chunk();
	        c2.append("Atencion al cliente:\n 2308 0742 \n atencionalcliente@stadium.com.uy"); 
	        c2.setFont(fontEnvId);
	        p2.add(c2);
	        documento.add(p2);*/
	        
	        documento.close();
	         
	        //PASO EL PDF A PNG
	       
	       qr= "C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/encuentra/pdf/"+idPedido+"logo.png";
	       PDDocument document = PDDocument.load(new File(path));
	      PDFRenderer pdfRenderer = new PDFRenderer(document);
	      for (int page = 0; page < document.getNumberOfPages(); ++page) {
	          BufferedImage bim = pdfRenderer.renderImageWithDPI(
	            page, 300, ImageType.RGB);
	          //ImageIOUtil.writeImage(
	           // bim, String.format(qr, page + 1, ".png"), 300);
	      }
	      
	      document.close();
	     
	       
	    }
		 catch (Exception e){
			 System.out.println(e);
		 }
		
		return qr;
	}

	public boolean etiquetaOK (String urlEtiqueta)
	{
		if(urlEtiqueta!=null)
		{
			if(urlEtiqueta.contains("http"))
			{
				return true;
			}
			
		}
		return false;
	} 
	
	public boolean imprimirEtiqueta(DataArticuloEcommerceVerifR ar, int idEquipo, int idEmp,int cantidad,int[] etiquetas)
	{
		Logica Logica = new Logica();	
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		Utilidades util = new Utilidades();
		String url = "";
		String track = "";
		try
		{			
			boolean hayEtiqueta = false;			
			
			hayEtiqueta = etiquetaOK(ar.getUrlEtiqueta());			
			
			if(!hayEtiqueta)
			{
				//BUSCO LA ETIQUETA
				if(ar.getMl()==1)
				{
					
				}
				else
				{
					DataDescDescripcion eti = null;
					int destino = 0;
					
					if(!ar.isPickup()){	//if(ar.getIdDestino()>9000){
						// llamar main de generacion de etiquetas
						switch (idEmp) {
						case 2:
							if(ar.getIdDestino()==400000) {
								List<DataEcommerce_canales_envio> envios = Logica.darListaEcommerce_canales_envio(ar.getCanal(),idEmp);
								for(DataEcommerce_canales_envio env:envios)
								{
									if((""+ar.getIdDestino()).equals(env.getIddeposito()))
									{	
										Cliente clienteSh = Logica.darClienteShippingEcommerce(ar.getIdPedido(),idEmp);
										Credenciales credenciales = new Credenciales();
										
										credenciales.setUser(env.getUsuario());
										credenciales.setPass(env.getPass());
										credenciales.setPedido(ar.getIdPedido()+"");										
										
										Shipping shipp = new Shipping();
										
										shipp.setCredenciales(credenciales);
										shipp.setCliente(clienteSh);
										
																				
										Call_WS_APIENCUENTRA enc = new Call_WS_APIENCUENTRA();
										DataDescDescripcion etiqueta = enc.setEnvio(shipp, env, ar.getIdPedido()+"", env.getFechaEntrega(), "", idEmp);
										
										if(etiqueta.getDescripcion().contains("http://"))
										{
											ar.setUrlEtiqueta(etiqueta.getDescripcion());	
											url = etiqueta.getDescripcion();
											track = etiqueta.getId();
											hayEtiqueta = true;
										}
																								
										break;
									}
								}
							}
							else {
								boolean canalActivo = false;
								try {
									canalActivo = Logica.canalActivoEC(ar.getCanal(), idEmp);
								} catch (Exception e) {}
								
								if(canalActivo) {
									if(ar.getCanal() <= 7) {
										Call_WS_analoga call = new Call_WS_analoga();
										String urlYaSeteada = call.reSetEtiquetas(""+ar.getIdEcommerce(),ar.getIdPedido(), ar.getCanal(), idEmp, ar.getFecha(),true,null);
										if(etiquetaOK(urlYaSeteada)) {
											ar.setUrlEtiqueta(urlYaSeteada);	
											hayEtiqueta = true;
										}
									}
									else {
										Call_WS_MODDO m = new Call_WS_MODDO();
										DataDescDescripcion etiqueta = m.setEtiqueta(ar.getIdPedido(), ar.getIdDestino(), ar.getCanal(), idEmp);
										
										if(etiqueta.getDescripcion().contains("http://"))
										{
											ar.setUrlEtiqueta(etiqueta.getDescripcion());	
											url = etiqueta.getDescripcion();
											track = etiqueta.getId();
											hayEtiqueta = true;
										}
									}
									
								}
							}
							
														
							break;
							case 4:
								List<DataEcommerce_canales_envio> envios = Logica.darListaEcommerce_canales_envio(ar.getCanal(),idEmp);
								if(ar.getIdDestino()==60000 || ar.getIdDestino()==50000) 
								{
								
									for(DataEcommerce_canales_envio env:envios)
									{
										if((""+ar.getIdDestino()).equals(env.getCodigo()))
										{								
											destino = Integer.parseInt(env.getIddeposito());
											
											Double costoEnvio = Logica.darCostoEnvioPedido(ar.getIdPedido(),idEmp);
											boolean freshipping = Logica.darFreeshippingEnvioPedido(ar.getIdPedido(),idEmp);
											
											// 2=cuenta corriente
											// 4=paga en destino
											int tipoEnvio = 4;
											
											if(freshipping)
											{
												tipoEnvio=2;
											}
											else if(costoEnvio>0.0)
											{
												tipoEnvio=2;
											}
											
											
											
											
											Cliente clienteSh = Logica.darClienteShippingEcommerce(ar.getIdPedido(),idEmp);
											Credenciales credenciales = new Credenciales();
											
											credenciales.setUser(env.getUsuario());
											credenciales.setPass(env.getPass());
											credenciales.setPedido("ENCUENTRA_"+ar.getIdPedido()+"");
											
											
											Shipping shipp = new Shipping();
											
											shipp.setCredenciales(credenciales);
											shipp.setCliente(clienteSh);
											
											shipp.setNombreRemite(env.getNombreRemite());
											
											
											//etiquetas= new int[]{cantS,cantM,cantL,cantXL};	
											
											//ir contra la tabla detalles articulos de la api
											
											//int totalPaquetes = 0;
											/*int pesoPedido = Logica.getPesoPedido(ar.getIdPedido());
											
											if(pesoPedido >= 30001) {
												
												shipp.setxGrandes(cantidad);
												
											}else if(pesoPedido >= 10001) {
												
												shipp.setGrandes(cantidad);
												
											}else if(pesoPedido > 2001) {
												
												shipp.setMedianos(cantidad);
												
											}else {
												
												shipp.setChicos(cantidad);
												
											}*/
											
											int totalPaquetes = 0;
											try {
												totalPaquetes = cantidad;
												
												shipp.setCantidadPaquetes(totalPaquetes);
												
												try
												{
													shipp.setxGrandes(etiquetas[3]);
												}
												catch (Exception e)
												{
													shipp.setxGrandes(0);
												}
												try
												{
													shipp.setGrandes(etiquetas[2]);
												}
												catch (Exception e)
												{
													shipp.setGrandes(0);
												}
												
												try
												{
													shipp.setMedianos(etiquetas[1]);
												}
												catch (Exception e)
												{
													shipp.setMedianos(0);
												}
												
												try
												{
													shipp.setChicos(etiquetas[0]);
												}
												catch (Exception e)
												{
													shipp.setChicos(0);
												}
												
												if(shipp.getChicos()+shipp.getGrandes()+shipp.getMedianos()+shipp.getxGrandes()==0)
												{
													totalPaquetes = 1;
													shipp.setCantidadPaquetes(totalPaquetes);
													shipp.setMedianos(1);
												}
												
											
											} catch (Exception e) {
												totalPaquetes = 1;
												shipp.setCantidadPaquetes(totalPaquetes);
												shipp.setMedianos(1);
											}
											
											shipp.setTipoShipping(tipoEnvio);
											
											Call_WS_APIENCUENTRA enc = new Call_WS_APIENCUENTRA();
											DataDescDescripcion etiqueta = enc.setEnvio(shipp, env, ar.getIdPedido()+"", "", "", idEmp);
											
											if(etiqueta.getDescripcion().contains("http://"))
											{
												url = etiqueta.getDescripcion();
												ar.setUrlEtiqueta(etiqueta.getDescripcion());
												track = etiqueta.getId();
												hayEtiqueta = true;
											}
																									
											break;
										}
									}
								}
								else if (ar.getIdDestino()==700000)//UES
								{
								
								}	
								else if(ar.getIdDestino()==40000 || ar.getIdDestino()==500000) {
									Cliente clienteSh = Logica.darClienteShippingEcommerce(ar.getIdPedido(),idEmp);
									DataIDDescripcion env = Logica.darEnvioPedido(ar.getIdPedido(),idEmp);
									int idDepoWEB = util.darParametroEmpresaINT(idEmp,5);
									DataIDDescripcion origen = Logica.darEnvioPedido(idDepoWEB, idEmp);
									url = ImprimirEtiquetasNuevas(ar,idEmp,env,origen.getDescripcion(),origen.getDescripcionB(),cantidad,false,
											clienteSh.getCalle()+" "+clienteSh.getNroPuerta() +" - "+ clienteSh.getNroApto(),clienteSh.getObs());
									track = ar.getIdPedido()+"";
								}
							break;

							default:
							break;
						}
					}
					else
					{			
						if(ar.getIdDestino()!=0) {
							DataIDDescripcion env = Logica.darEnvioPedido(ar.getIdPedido(),idEmp);
							int idDepoWEB = util.darParametroEmpresaINT(idEmp,5);
							DataIDDescripcion origen = Logica.darEnvioPedido(idDepoWEB, idEmp);
							url = ImprimirEtiquetasNuevas(ar,idEmp,env,origen.getDescripcion(),origen.getDescripcionB(),cantidad,true,"","");
							track = ar.getIdPedido()+"";
						}
						
					}
					
							
				}				
				
			}
			else if(cantidad>1) 
			{
				url = etiquetas_externas_cantidad(ar.getIdPedido(), ar.getUrlEtiqueta(), cantidad);
			}
						
			
			if(etiquetaOK(url)) {
				hayEtiqueta = true;	
				
				EncuentraPedido p = new EncuentraPedido();
				p.setIdPedido(ar.getIdPedido());
				p.setUrlEtiqueta(url);
				p.updateEtiqueta(0,idEmp);
				
				if(ar.getIdDestino()<9000) {
					p.updateShipping(ar.getIdDestino(), p.getIdPedido()+"","",idEmp);
				}
				else {
					p.updateShipping(ar.getIdDestino(), track,"",idEmp);
				}
				
				if(idEmp==4) {
					api.PutTracking(ar.getIdPedido(), "", track, ar.getIdDestino(), idEmp);
				}
				
				ar.setUrlEtiqueta(url);		
				
			}
			
			if(hayEtiqueta) {
				//PUT COLA IMPRESION
				if(ar.getIdDestino()==600000 || ar.getIdDestino()==900000){
					api.PutColaImpresion(ar.getIdPedido()+"", ar.getUrlEtiqueta(), 1, ar.getIdImpresora(),idEquipo,idEmp,1);
				}
				else{
					api.PutColaImpresion(ar.getIdPedido()+"", ar.getUrlEtiqueta(), 0, ar.getIdImpresora(),idEquipo,idEmp,1);
				}
			}
			return hayEtiqueta;
		}
		catch(Exception e)
		{			
			return false;
		}		
	}
	
	public static String ImprimirEtiquetaBultos(bulto b, int unidades,int idEmpresa)  throws FileNotFoundException, DocumentException
	{
		Logica Logica = new Logica();
		//List<DataIDDescripcion> articulos, int idPedido, String factura,  String cliente,String tel, String ci, String mail, Book book2
	    
		String path = "";
		String pathSalida = "";
		String download_pdf_from="";
		String delivery ="";
		String qr="";
		String envio = "";
		String envioId="";
		String envioDir="";
		System.out.println("");
	    try
	    {
	    	
	    	PropertiesHelper pH=new PropertiesHelper("paths");	    	
	    	try
	    	{
	    		pH.loadProperties();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}			
	    	
	    	System.out.println("");		
			path = pH.getValue("pdf")+b.getIdBulto()+".pdf";
			pathSalida = pH.getValue("HTTP_pdf")+"/"+b.getIdBulto()+".pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(431F,450F);
	        Document documento = new Document(pageSize);
	        Font font = FontFactory.getFont("Arial", 10F, 1);
	        Font fontBig = FontFactory.getFont("Arial", 40F, 1);
	        Font fontBigBig = FontFactory.getFont("Arial", 100F, 1);
	        Font font1100 = FontFactory.getFont("Arial", 20F, 1);
	      
	        
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.open();
	       // documento.setMargins(1F, 1F, 1F, 1F);
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	        documento.newPage();
	        
	        PdfPTable table = new PdfPTable(2);
	        //table.setTotalWidth(289F);
	        
	        Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
	        
	        String downloaded_filename_open_as_pdf="";
	      
	        
	        //ARRANCO ETIQUETA
	        System.out.println("EMPEZANDO CREACION DE ETIQUETA");
	        
																				/* CODIGO DE BARRAS*/
	        Barcode39 code39 = new Barcode39();
	        String idd = b.getIdBulto().replace("//.", "");
	        idd = idd.toUpperCase();
	        //idd = idd.replace("BUL.", "");
	        code39.setCode(idd);                
	        code39.setBarHeight(50F);
	        code39.setX(1.0F);
	        PdfPCell cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
            cell.setHorizontalAlignment(1);
            cell.setPaddingTop(5F);
            //cell.setPadding(3F);
            //cell.setColspan(6);
	        cell.setColspan(2);
            table.addCell(cell);
	        
	        cell = new PdfPCell(new Paragraph(b.getDescripcion(),font1100));
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);	        
	        
	        DataIDDescripcion aliasDep = Logica.darAliasDeposito(b.getDestino(),idEmpresa);
	        
	        cell = new PdfPCell(new Paragraph(aliasDep.getDescripcion(), fontBig));	        
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        cell.setBorder(Rectangle.TOP| Rectangle.RIGHT | Rectangle.LEFT);
	        table.addCell(cell);
	        
	        if(b.getDestino().length()<=5) {
	        	cell = new PdfPCell(new Paragraph(b.getDestino(), fontBigBig));	
	        }
	        else {
	        	cell = new PdfPCell(new Paragraph(b.getDestino(), fontBig));	
	        }
	                
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        cell.setBorder(Rectangle.BOTTOM| Rectangle.RIGHT | Rectangle.LEFT);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Paragraph("Unidades: "+unidades, font));	        
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        
	        String remitos = "";
	        for(DataIDDescripcion r: b.getRemitos()){
	        	remitos += r.getDescripcion()+",";
	        }
	        if(remitos.length()>0){
	        	 remitos = remitos.substring(0,remitos.length()-1);
	        }
	       
	        
	        cell = new PdfPCell(new Paragraph("Documentos: "+remitos, font));	        
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        
	        documento.add(table);
	        documento.close();
	        
	        System.out.println("TERMINANDO ETIQUETA");
	        
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
	
	public static String ImprimirEtiquetaBulto_Pedido(bulto b, int unidades,int idEmpresa, int numerador)  throws FileNotFoundException, DocumentException
	{
		Logica Logica = new Logica();
		//List<DataIDDescripcion> articulos, int idPedido, String factura,  String cliente,String tel, String ci, String mail, Book book2
	    
		String path = "";
		String pathSalida = "";
		String download_pdf_from="";
		String delivery ="";
		String qr="";
		String envio = "";
		String envioId="";
		String envioDir="";
		System.out.println("");
	    try
	    {
	    	
	    	PropertiesHelper pH=new PropertiesHelper("paths");	    	
	    	try
	    	{
	    		pH.loadProperties();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}			
	    	
	    	System.out.println("");		
			path = pH.getValue("pdf")+b.getIdBulto()+".pdf";
			pathSalida = pH.getValue("HTTP_pdf")+"/"+b.getIdBulto()+".pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(431F,450F);
	        Document documento = new Document(pageSize);
	        Font font = FontFactory.getFont("Arial", 10F, 1);
	        Font fontBig = FontFactory.getFont("Arial", 20F, 1);
	        Font fontBigBig = FontFactory.getFont("Arial", 40F, 1);
	        Font font1100 = FontFactory.getFont("Arial", 20F, 1);
	      
	        
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.open();
	       // documento.setMargins(1F, 1F, 1F, 1F);
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	        documento.newPage();
	        
	        PdfPTable table = new PdfPTable(2);
	        //table.setTotalWidth(289F);
	        
	        Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
	        
	        String downloaded_filename_open_as_pdf="";
	      
	        
	        //ARRANCO ETIQUETA
	        System.out.println("EMPEZANDO CREACION DE ETIQUETA");
	        
																				/* CODIGO DE BARRAS*/
	        Barcode39 code39 = new Barcode39();
	        String idd = b.getIdBulto().replace("//.", "");
	        idd = idd.toUpperCase();
	        //idd = idd.replace("BUL.", "");
	        
	        
	        String fragil = "FR?GIL \r\n"+
	        				"";
		    PdfPCell cell = new PdfPCell(new Paragraph(fragil,font1100));
		    cell.setHorizontalAlignment(1);
		    cell.setColspan(2);
		    table.addCell(cell);
	        
		    SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yy HH:mm");
	        Date date = new Date(System.currentTimeMillis());
	        System.out.println(formatter.format(date));
	        
	        String fecha = formatter.format(date);
	        
	        String remite = "Rte:  PEPEGANGA S.A. \r\n" + 
	        				"Domingo Aramburu 1521 Tel 2204 0164*\r\n" + 
	        				"RUT 214 047 730 016\r\n" +
	        				fecha;
	        cell = new PdfPCell(new Paragraph(remite,font));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            table.addCell(cell);
	        
	        
	        
	        code39.setCode(idd);                
	        code39.setBarHeight(30F);
	        code39.setX(1.0F);
	        cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
            cell.setHorizontalAlignment(1);
            cell.setPaddingTop(5F);
            //cell.setPadding(3F);
            //cell.setColspan(6);
	        cell.setColspan(2);
            table.addCell(cell);
	        
	        cell = new PdfPCell(new Paragraph("BULTO "+numerador+" de .....",font1100));
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);	        
	        
	        DataIDDescripcion aliasDep = Logica.darAliasDeposito(b.getDestino(),idEmpresa);
	        
	        cell = new PdfPCell(new Paragraph(aliasDep.getDescripcion(), fontBig));	   
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        cell.setBorder(Rectangle.TOP| Rectangle.RIGHT | Rectangle.LEFT);
	        table.addCell(cell);
	        
	        
	        DataIDDescripcion localidadDep = Logica.darLocalidadDeposito(b.getDestino(),idEmpresa);
	        
	        String localidad = aliasDep.getDescripcionB() + "\r\n" + 
	        		localidadDep.getDescripcion() +"\r\n" + 
	        		localidadDep.getDescripcionB();
	        
	        /*if(!localidadDep.getDescripcion().equals("") &&  !localidadDep.getDescripcionB().equals("")) {
	        	localidad = ", " + localidadDep.getDescripcion() + ", " + localidadDep.getDescripcionB();
	        }else if(!localidadDep.getDescripcion().equals("")) {
	        	localidad = ", " + localidadDep.getDescripcion();
	        }else if(!localidadDep.getDescripcionB().equals("")){
	        	localidad = ", " + localidadDep.getDescripcionB();
	        }*/
	        
	        
	        
	        //cell = new PdfPCell(new Paragraph(aliasDep.getDescripcionB() + localidad, font));	
	        cell = new PdfPCell(new Paragraph(localidad, font));
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        cell.setBorder(Rectangle.BOTTOM| Rectangle.RIGHT | Rectangle.LEFT);
	        table.addCell(cell);
	        
	        DataIDDescripcion observacion_agencia = Logica.darComentarioVenta(b.getPedido(), idEmpresa);
	        
	        cell = new PdfPCell(new Paragraph("Forma de env?o: "+observacion_agencia.getDescripcionB(), font));	        
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Paragraph("Unidades: "+unidades, font));	        
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        	        
	        cell = new PdfPCell(new Paragraph("Pedido: "+b.getPedido(), font));	        
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        
	        DataIDDescripcion descUsuario = Logica.DescripcionUsuario(b.getUsuarioClose(),idEmpresa);
	        
	        String responsable = "";
	        
	        for(int i=0; i < descUsuario.getDescripcion().length(); i++) {
	        	if(descUsuario.getDescripcion().charAt(i) == Character.toUpperCase(descUsuario.getDescripcion().charAt(i)) && descUsuario.getDescripcion().charAt(i) != ' ') {
	        		responsable += descUsuario.getDescripcion().charAt(i);
	        	}
	        }
	        
	        cell = new PdfPCell(new Paragraph("Responsable: "+responsable, font));	        
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        
	        cell = new PdfPCell(new Paragraph("Observaciones:", font));	        
	        //cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        
	        String observaciones = "";
	        
	        String observacionesVenta = observacion_agencia.getDescripcion();
	        
	        if(!observacionesVenta.equals("") && !observacionesVenta.equals(null)) {
	        	observaciones += observacionesVenta;
	        }else {
	        	observaciones += "\r\n";
	        }
	        
	        observaciones += "\r\n" + 
    				"\r\n" + 
    				"\r\n"+ 
    				"\r\n";
	        
	        cell = new PdfPCell(new Paragraph(observaciones, font));	        
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        
	        documento.add(table);
	        documento.close();
	        
	        System.out.println("TERMINANDO ETIQUETA");
	        
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
	
	public String ImprimirEtiquetasBultosR(List<bulto> bultos,String fileName, Usuario usu)  throws FileNotFoundException, DocumentException
	{
		Logica Logica = new Logica();
		//List<DataIDDescripcion> articulos, int idPedido, String factura,  String cliente,String tel, String ci, String mail, Book book2
	    
		String path = "";
		String pathSalida = "";
		String download_pdf_from="";
		String delivery ="";
		String qr="";
		String envio = "";
		String envioId="";
		String envioDir="";
		
		
		System.out.println("");
		int idRecep = 0;
	    try
	    {
	    	try {
	    		idRecep = bultos.get(0).getContenidoList().get(0).getRecepcion();
			} catch (Exception e) {
				idRecep = 0;
			}
	    	
	    	DataIDDescripcion dataRecep= Logica.dataRecepcion(idRecep);
	    	PropertiesHelper pH=new PropertiesHelper("paths");	    	
	    	try
	    	{
	    		pH.loadProperties();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}			
	    	
	    			
			path = pH.getValue("pdf")+fileName+".pdf";
			pathSalida = pH.getValue("HTTP_pdf")+"/"+fileName+".pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(431F,450F);
	        Document documento = new Document(pageSize);
	        Font font = FontFactory.getFont("Arial", 10F, 1);
	        Font fontBig = FontFactory.getFont("Arial", 70F, 1);
	        Font fontBigBig = FontFactory.getFont("Arial", 100F, 1);
	        Font font1100 = FontFactory.getFont("Arial", 20F, 1);
	      
	        
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.open();
	       // documento.setMargins(1F, 1F, 1F, 1F);
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	        
	        for (bulto b : bultos) 
	        {
	        	
	        	
	        	
	        	documento.newPage();
		        
		        PdfPTable table = new PdfPTable(2);
		        //table.setTotalWidth(289F);
		        
		        Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
		        
		        String downloaded_filename_open_as_pdf="";
		      
		        
		        //ARRANCO ETIQUETA
		        System.out.println("EMPEZANDO CREACION DE ETIQUETA");
		        
																					/* CODIGO DE BARRAS*/
		        Barcode39 code39 = new Barcode39();
		        String idd = b.getIdBulto().replace("//.", "");
		        idd = idd.toUpperCase();
		        //idd = idd.replace("BUL.", "");
		        code39.setCode(idd);                
		        code39.setBarHeight(50F);
		        code39.setX(1.0F);
		        PdfPCell cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	            cell.setHorizontalAlignment(1);
	            cell.setPaddingTop(5F);
	            //cell.setPadding(3F);
	            //cell.setColspan(6);
		        cell.setColspan(2);
	            table.addCell(cell);
	            
	            if(dataRecep!=null){
	            																						//TITULO
			        cell = new PdfPCell(new Paragraph("R."+dataRecep.getDescripcion()+dataRecep.getDescripcionB(),font1100));	//R. + PROVEEDOR + FOLIO
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);	
			        																					//FECHA
			        cell = new PdfPCell(new Paragraph(dataRecep.getDescripcionC(),font1100));	
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
	            }
	            else{
	            	//TITULO
			        cell = new PdfPCell(new Paragraph(b.getDescripcion(),font1100));	
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
	            }
		        																					
																							//USUARIO
				cell = new PdfPCell(new Paragraph(usu.getNombre()+" "+ usu.getApellido(),font));	
				cell.setHorizontalAlignment(1);
				//cell.setPadding(3F);
				cell.setColspan(2);
				cell.setPaddingBottom(5F);
				table.addCell(cell);
				
		        if(b.getContenidoList()!=null && !b.getContenidoList().isEmpty())
		        {
		        	int totalBulto = 0;
			        
			        
			        cell = new PdfPCell(new Paragraph("Articulo", font));	        
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Paragraph("Cantidad", font));	        
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
			        
			        Collections.sort(b.getContenidoList());
			        
			        for (bultoContenido c : b.getContenidoList()) 
			        {
			        	 cell = new PdfPCell(new Paragraph(c.getIdArticulo(), font));	        
					        cell.setHorizontalAlignment(1);
					        //cell.setPadding(3F);
					        cell.setPaddingBottom(5F);
					        table.addCell(cell);
					        
					        cell = new PdfPCell(new Paragraph(c.getCantidad()+"", font));	        
					        cell.setHorizontalAlignment(1);
					        //cell.setPadding(3F);
					        cell.setPaddingBottom(5F);
					        table.addCell(cell);
					        
					        
					        totalBulto+=c.getCantidad();
					}
			        
			        table = PrintArtsDescCorta(b.getContenidoList(), table, usu.getIdEmpresa(), Logica);
			        
			        cell = new PdfPCell(new Paragraph("TOTAL",font1100));
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Paragraph(""+totalBulto,font1100));
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
		        }
		        else //el bulto no tiene contenido
		        {
		        	
		        }
		        
		        
		        documento.add(table);
			}
	        
	        
	        documento.close();
	        
	        System.out.println("TERMINANDO ETIQUETA  ");
	        
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
	
	public String ImprimirEtiquetasBultosRSinOC(List<bulto> bultos,String fileName, Hashtable<String, Integer> totalesPorMark)  throws FileNotFoundException, DocumentException
	{
		Logica Logica = new Logica();
		//List<DataIDDescripcion> articulos, int idPedido, String factura,  String cliente,String tel, String ci, String mail, Book book2
	    
		String path = "";
		String pathSalida = "";
		String download_pdf_from="";
		String delivery ="";
		String qr="";
		String envio = "";
		String envioId="";
		String envioDir="";
		System.out.println("");
		int idRecep = 0;
	    try
	    {
	    	try {
	    		idRecep = bultos.get(0).getContenidoList().get(0).getRecepcion();
			} catch (Exception e) {
				idRecep = 0;
			}
	    	
	    	DataIDDescripcion dataRecep= Logica.dataRecepcion(idRecep);
	    	PropertiesHelper pH=new PropertiesHelper("paths");	    	
	    	try
	    	{
	    		pH.loadProperties();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}			
	    	
	    			
			path = pH.getValue("pdf")+fileName+".pdf";
			pathSalida = pH.getValue("HTTP_pdf")+"/"+fileName+".pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(431F,450F);
	        Document documento = new Document(pageSize);
	        Font font = FontFactory.getFont("Arial", 10F, 1);
	        Font fontBig = FontFactory.getFont("Arial", 40F, 1);
	        Font fontBigBig = FontFactory.getFont("Arial", 100F, 1);
	        Font font1100 = FontFactory.getFont("Arial", 20F, 1);
	      
	        
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.open();
	       // documento.setMargins(1F, 1F, 1F, 1F);
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	        
	        String markAnterior = "";
	    	String markActual = "";
	    	int numeroBulto = 0, totalBultosXMark=0;
	        
	        for (bulto b : bultos) 
	        {
	        	/***************** CANTIDADES DE BULTOS POR MARK ********************/
	        	bultoContenido bc = new bultoContenido();
	    		bc = b.getContenidoList().get(0);
	    		//si entro por primera vez
	    		if (markAnterior.equals(""))
	    		{
	    			markAnterior = bc.getIdArticulo();
	    			//cantidad de bultos por mark
	    			totalBultosXMark = totalesPorMark.get(bc.getIdArticulo());
	    		}
	    		
	    		markActual = bc.getIdArticulo();
	    		if (!markActual.equals(markAnterior))
	    		{
	    			markAnterior=markActual;
	    			totalBultosXMark = totalesPorMark.get(bc.getIdArticulo());
	    			numeroBulto = 1;
	    		}
	    		else
	    			//incremento numero de bulto: ej. 2 / 30
	    			numeroBulto++;
	    		/********************************************************************/
	        	
	        	documento.newPage();
		        
		        PdfPTable table = new PdfPTable(2);
		        //table.setTotalWidth(289F);
		        
		        Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
		        
		        String downloaded_filename_open_as_pdf="";
		      
		        
		        //ARRANCO ETIQUETA
		        System.out.println("EMPEZANDO CREACION DE ETIQUETA");
		        
																					/* CODIGO DE BARRAS*/
		        Barcode39 code39 = new Barcode39();
		        String idd = b.getIdBulto().replace("//.", "");
		        idd = idd.toUpperCase();
		        //idd = idd.replace("BUL.", "");
		        code39.setCode(idd);                
		        code39.setBarHeight(50F);
		        code39.setX(0.9F);
		        PdfPCell cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	            cell.setHorizontalAlignment(1);
	            cell.setPaddingTop(5F);
	            //cell.setPadding(3F);
	            //cell.setColspan(6);
		        cell.setColspan(2);
	            table.addCell(cell);
	            
	            if(dataRecep!=null){
	            																						//TITULO
			        cell = new PdfPCell(new Paragraph("R."+dataRecep.getDescripcion()+dataRecep.getDescripcionB(),font));	//R. + PROVEEDOR + FOLIO
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);	
			        																					//FECHA
			        cell = new PdfPCell(new Paragraph(dataRecep.getDescripcionC(),font));	
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
	            }
	            else{
	            	//TITULO
			        cell = new PdfPCell(new Paragraph(b.getDescripcion(),font));	
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
	            }
		        																					
		        if(b.getContenidoList()!=null && !b.getContenidoList().isEmpty())
		        {
		        	int totalBulto = 0;
			        
			        Collections.sort(b.getContenidoList());
			        
			        for (bultoContenido c : b.getContenidoList()) 
			        {
					        cell = new PdfPCell(new Paragraph(c.getIdArticulo(),fontBig));
					        cell.setHorizontalAlignment(1);
					        //cell.setPadding(3F);
					        cell.setColspan(2);
					        cell.setPaddingBottom(5F);
					        table.addCell(cell);
					        
					        totalBulto+=c.getCantidad();
					}
			        
			        //table = PrintArtsDescCorta(b.getContenidoList(), table, usu.getIdEmpresa(), Logica);
			        
			        cell = new PdfPCell(new Paragraph("Cantidades: "+totalBulto,font1100));
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Paragraph(numeroBulto+" / "+totalBultosXMark,font1100));
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
		        }
		        else //el bulto no tiene contenido
		        {
		        	
		        }
		        
		        
		        documento.add(table);
			}
	        
	        
	        documento.close();
	        
	        System.out.println("TERMINANDO ETIQUETA  ");
	        
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
	
	public PdfPTable PrintArtsDescCorta(List<bultoContenido> lista, PdfPTable table, int idEmpresa, Logica log) {
		try {
			List<DataIDDescripcion> descs = log.DarArtsDescCorta(lista, idEmpresa);
			
			Font font = FontFactory.getFont("Arial", 10F, 1);
			Font fontT = FontFactory.getFont("Arial", 12F, 1);
			PdfPCell cell = new PdfPCell(new Paragraph("Descripcion de articulos",fontT));
			cell.setHorizontalAlignment(1);
			//cell.setPadding(3F);
			cell.setColspan(2);
			cell.setPaddingBottom(5F);
			table.addCell(cell);
			
			for(DataIDDescripcion d:descs) {
				cell = new PdfPCell(new Paragraph(d.getDescripcion(), font));	        
		        cell.setHorizontalAlignment(1);
		        //cell.setPadding(3F);
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph(d.getId()+"", font));	        
		        cell.setHorizontalAlignment(1);
		        //cell.setPadding(3F);
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);
			}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}
	
	
	
	public String ImprimirEtiquetasBultosSA(List<bulto> bultos,String fileName)  throws FileNotFoundException, DocumentException
	{
		Logica Logica = new Logica();
	    
		String path = "";
		String pathSalida = "";
		String download_pdf_from="";
		String delivery ="";
		String qr="";
		String envio = "";
		String envioId="";
		String envioDir="";
		System.out.println("");
	    try
	    {
	    	
	    	PropertiesHelper pH=new PropertiesHelper("paths");	    	
	    	try
	    	{
	    		pH.loadProperties();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}			
	    	
	    			
			path = pH.getValue("pdf")+fileName+".pdf";
			pathSalida = pH.getValue("HTTP_pdf")+"/"+fileName+".pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(431F,450F);
	        Document documento = new Document(pageSize);
	        Font font = FontFactory.getFont("Arial", 10F, 1);
	        Font fontBig = FontFactory.getFont("Arial", 70F, 1);
	        Font fontBigBig = FontFactory.getFont("Arial", 100F, 1);
	        Font font1100 = FontFactory.getFont("Arial", 20F, 1);
	      
	        
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.open();
	       // documento.setMargins(1F, 1F, 1F, 1F);
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	        
	        for (bulto b : bultos) 
	        {
	        	
	        	
	        	
	        	documento.newPage();
		        
		        PdfPTable table = new PdfPTable(2);
		        //table.setTotalWidth(289F);
		        
		        Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
		        
		        String downloaded_filename_open_as_pdf="";
		      
		        
		        //ARRANCO ETIQUETA
		        System.out.println("EMPEZANDO CREACION DE ETIQUETA");
		        
																					/* CODIGO DE BARRAS*/
		        Barcode39 code39 = new Barcode39();
		        String idd = b.getIdBulto().replace("//.", "");
		        idd = idd.toUpperCase();
		        //idd = idd.replace("BUL.", "");
		        code39.setCode(idd);                
		        code39.setBarHeight(50F);
		        code39.setX(1.0F);
		        PdfPCell cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	            cell.setHorizontalAlignment(1);
	            cell.setPaddingTop(5F);
	            //cell.setPadding(3F);
	            //cell.setColspan(6);
		        cell.setColspan(2);
	            table.addCell(cell);
		        
		        cell = new PdfPCell(new Paragraph(b.getDescripcion(),font1100));
		        cell.setHorizontalAlignment(1);
		        //cell.setPadding(3F);
		        cell.setColspan(2);
		        cell.setPaddingBottom(5F);
		        table.addCell(cell);	        
		        
		        if(b.getContenidoList()!=null && !b.getContenidoList().isEmpty())
		        {
		        	int totalBulto = 0;
			        
			        
			        cell = new PdfPCell(new Paragraph("Articulo", font));	        
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Paragraph("Cantidad", font));	        
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
			        
			        Collections.sort(b.getContenidoList());
			        
			        for (bultoContenido c : b.getContenidoList()) 
			        {
			        	 cell = new PdfPCell(new Paragraph(c.getIdArticulo(), font));	        
					        cell.setHorizontalAlignment(1);
					        //cell.setPadding(3F);
					        cell.setPaddingBottom(5F);
					        table.addCell(cell);
					        
					        cell = new PdfPCell(new Paragraph(c.getCantidad()+"", font));	        
					        cell.setHorizontalAlignment(1);
					        //cell.setPadding(3F);
					        cell.setPaddingBottom(5F);
					        table.addCell(cell);
					        					        
					        totalBulto+=c.getCantidad();
					}
			        
			        table = PrintArtsDescCorta(b.getContenidoList(), table, b.getIdEmpresa(), Logica);
			        
			        cell = new PdfPCell(new Paragraph("TOTAL",font1100));
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Paragraph(""+totalBulto,font1100));
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        table.addCell(cell);
		        }
		        else //el bulto no tiene contenido
		        {
		        	
		        }
		        
		        
		        documento.add(table);
			}
	        
	        
	        documento.close();
	        
	        System.out.println("TERMINANDO ETIQUETA  ");
	        
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
	
	public DataDescDescripcion BajarPDF(String eti, String trkNmb){
		try {
						//PASO A PDF
			String path ="";
			String barra = "";
			try
			{
				PropertiesHelper pH=new PropertiesHelper("paths");
				pH.loadProperties();
				path = pH.getValue("pdf");
				String filePath = path+"/"+trkNmb+".pdf";
				
				 // Encode using basic encoder
		         //String base64encodedString = retorno.getPdfserializado();
		         
		         System.out.println("Base64 encoded string :" + eti);

		         // Decode
		         byte[] base64decodedBytes = Base64.getDecoder().decode(eti);

				 File file = new File(filePath);
		         file.delete();
		         
		         FileOutputStream fos = new FileOutputStream(new File(filePath));
		         
		         String str = eti;
		         
		         InputStream in = new ByteArrayInputStream(base64decodedBytes);
		         
		         int inByte;
		            while ((inByte = in.read()) != -1) {
		                fos.write(inByte);
		            }
		 
		            in.close();	            
		         
		         fos.write(str.getBytes());	         
		         
		         fos.close();
		         
		         
		         File f = new File(filePath);
			     if(f.exists())
			     {
			    	 path = pH.getValue("HTTP_pdf");		    	 
			    	
			    	 //es lo ultimo
			    	 try (PDDocument document = PDDocument.load(new File(filePath))) 
			    	 {

			             document.getClass();

			             if (!document.isEncrypted()) {
			 			
			                 PDFTextStripperByArea stripper = new PDFTextStripperByArea();
			                 stripper.setSortByPosition(true);

			                 PDFTextStripper tStripper = new PDFTextStripper();

			                 String pdfFileInText = tStripper.getText(document);
			                
			                 String lines[] = pdfFileInText.split("\\r?\\n");
			                 for (String line : lines) 
			                 {
			                     barra = line;
			                     break;
			                 }
			                 barra = barra.replace("*", "");
			                 System.out.println(barra);

			             }

			         }
			    	 catch (Exception e) 
			    	 {
			    		 e.printStackTrace();
					}
			    	 
			    	 
			    	 
			    	 
			        return  new DataDescDescripcion(barra, path+"/"+trkNmb+".pdf");
			     }
			     else
			     {
			    	 return new DataDescDescripcion("","");
			     }
		         				
			}
			catch (Exception e) 
			{
			 e.printStackTrace();
			}
			
			return  new DataDescDescripcion(barra, path+"/"+trkNmb+".pdf");
				
			
			
		} catch (Exception e) {
			return  new DataDescDescripcion("", "");
		}
		
	}
	
	public String etiquetas_externas_cantidad(Long idPedido,String url, int cantidad) {
		String pathSalida = "";
		try {
			PropertiesHelper pH=new PropertiesHelper("paths");	    	
	    	pH.loadProperties();	    				
	    			
	    	String source = "C:/Program Files/apache-tomcat-7.0.64/webapps/encuentraAPI/pdf/"+idPedido+".pdf";
			String path = pH.getValue("pdf")+idPedido+".pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(431F,450F);
	        Document documento = new Document(PageSize.A6);
	      
	        
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.open();
	       // documento.setMargins(1F, 1F, 1F, 1F);
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	        for (int i = 0; i < cantidad; i++) 
	        {
	        	documento.newPage();
	        	
	        	// Load existing PDF
	        	PdfReader reader;
	        	try {
	        		reader = new PdfReader(source);
				} catch (Exception e) {
					System.out.println("error source");
					source = "C:/Program Files/apache-tomcat-7.0.64/webapps/WMS/pdf/"+idPedido+".pdf";
					reader = new PdfReader(source);
				}
	        	
	        	PdfImportedPage page = writer.getImportedPage(reader, 1); 

	        	// Copy first page of existing PDF into output PDF	        	
	        	cb.addTemplate(page, 0, 0);
		        
	        }
	        
	        documento.close();
	        pathSalida = pH.getValue("HTTP_pdf")+"/"+idPedido+".pdf";
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathSalida;
	}

	public static String ImprimirEtiquetasFlotaStd(Cliente c, Long idpedido,String envio, int idEmpresa)  throws FileNotFoundException, DocumentException
    {
			Logica Logica = new Logica();
			//List<DataIDDescripcion> articulos, int idPedido, String factura,  String cliente,String tel, String ci, String mail, Book book2
        
			String path = "";
			String download_pdf_from="";
			String delivery ="";
			String qr="";
			String pathSalida="";
			//String envio = "";
        try
        {	        	
        	DataIDDescripcion env = Logica.darEnvioPedido(idpedido,idEmpresa);
  			
  			/*if(env!=null){
  				envio = env.getDescripcion();
  			}
  			*/        	
        	PropertiesHelper pH=new PropertiesHelper("paths");
        	
        	try
        	{
        		pH.loadProperties();
        	}
        	catch(Exception e)
        	{
        		
        	}
        	
    		path = pH.getValue("pdf")+"//"+idpedido+"FLOTA.pdf";
    		pathSalida = pH.getValue("HTTP_pdf")+"//"+idpedido+"FLOTA.pdf";
            FileOutputStream archivo = new FileOutputStream(path);
            //Rectangle pageSize = new Rectangle(431F,450F);
            Rectangle pageSize = new Rectangle(450F,431F);
            Document documento = new Document(pageSize);
            Font font = FontFactory.getFont("Arial", 10F, 1);
            Font fontCod = FontFactory.getFont("Arial", 17F, 1);
            Font fontSmall = FontFactory.getFont("Arial", 8F, 1);
            Font fontEnvId = FontFactory.getFont("Arial", 70F, 1);
            
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
            documento.open();
           // documento.setMargins(1F, 1F, 1F, 1F);
            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
            documento.newPage();
            
            PdfPTable table = new PdfPTable(1);
            //table.setTotalWidth(289F);
            
            Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
            
            String downloaded_filename_open_as_pdf="";
           	           	            
            //ARRANCO ETIQUETA
           	            
            PdfPCell cell = new PdfPCell(new Paragraph("?GRACIAS POR TU COMPRA!  Tu factura te fue enviada por mail",fontSmall));
            cell.setHorizontalAlignment(1);
            cell.setColspan(1);
            table.addCell(cell);
                            
            cell = new PdfPCell(new Paragraph("Pedido "+idpedido, fontCod));
            //;
            cell.setHorizontalAlignment(1);
            cell.setColspan(6);
            //cell.setFixedHeight(410F);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("ZONA: "+envio,fontCod));
            cell.setHorizontalAlignment(1);
            cell.setPadding(3F);
            cell.setColspan(6);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("CLIENTE: "+c.getNombre()+" "+c.getApellido(),fontCod));
            cell.setHorizontalAlignment(1);
            cell.setPadding(3F);
            cell.setColspan(6);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("DIRECCION: "+c.getCalle()+" "+c.getNroPuerta()+" / "+c.getNroApto(),fontCod));
            cell.setHorizontalAlignment(1);
            cell.setPadding(3F);
            cell.setColspan(6);
            table.addCell(cell);
                            
            String tel = c.getTelefono().replace("+598", "0");
            cell = new PdfPCell(new Paragraph("TELEFONO: "+tel,fontCod));
            cell.setHorizontalAlignment(1);
            cell.setPadding(3F);
            cell.setColspan(6);
            table.addCell(cell);
            
            
            Barcode39 code39 = new Barcode39();
            code39.setCode(idpedido+"");                
            code39.setBarHeight(50F);
            code39.setX(1.0F);                
			
			cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
            cell.setHorizontalAlignment(1);
            cell.setPadding(3F);
            cell.setColspan(6);
            table.addCell(cell);
            
            cell = new PdfPCell(new Paragraph("Atencion al cliente: 2308 0742. atencionalcliente@stadium.com.uy",fontSmall));
            cell.setHorizontalAlignment(1);
            cell.setColspan(6);
            table.addCell(cell);
            
            documento.add(table);
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
	
	public static String etiquetas_verificacion_destinos(int idPicking, String comentario, Usuario uLog)  throws FileNotFoundException, DocumentException
	{
		Logica Logica = new Logica();
		//List<DataIDDescripcion> articulos, int idPedido, String factura,  String cliente,String tel, String ci, String mail, Book book2
	    
		String path = "";
		String pathSalida = "";
		String download_pdf_from="";
		String delivery ="";
		String qr="";
		String envio = "";
		String envioId="";
		String envioDir="";
		System.out.println("");
	    try
	    {
	    	
	    	PropertiesHelper pH=new PropertiesHelper("paths");	    	
	    	try
	    	{
	    		pH.loadProperties();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}			
	    	
	    	System.out.println("");		
			path = pH.getValue("pdf")+"labels"+idPicking+"_"+uLog.getIdEmpresa()+".pdf";
			pathSalida = pH.getValue("HTTP_pdf")+"/labels"+idPicking+"_"+uLog.getIdEmpresa()+".pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(431F,450F);
	        Document documento = new Document(pageSize);
	        Font font = FontFactory.getFont("Arial", 12F, 1);
	        Font fontBig = FontFactory.getFont("Arial", 40F, 1);
	        Font fontBigBig = FontFactory.getFont("Arial", 100F, 1);
	        Font font1100 = FontFactory.getFont("Arial", 15F, 1);
	      
	        
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.open();
	       // documento.setMargins(1F, 1F, 1F, 1F);
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	        documento.newPage();
	        
	        PdfPTable table = new PdfPTable(2);
	        //table.setTotalWidth(289F);
	        
	        Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
	        
	        String downloaded_filename_open_as_pdf="";
	      
	        
	        //ARRANCO ETIQUETA
	        System.out.println("EMPEZANDO CREACION DE ETIQUETA");
	        
																				/* CODIGO DE BARRAS*/
	        Barcode39 code39 = new Barcode39();
	        String idd = ""+idPicking;
	        idd = idd.toUpperCase();
	        code39.setCode(idd);                
	        code39.setBarHeight(50F);
	        code39.setX(1.0F);
	        PdfPCell cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
            cell.setHorizontalAlignment(1);
            cell.setPaddingTop(5F);
            //cell.setPadding(3F);
            //cell.setColspan(6);
	        cell.setColspan(2);
            table.addCell(cell);
	        
	        cell = new PdfPCell(new Paragraph("Usuario: "+uLog.getNick(),font));
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        
	        String fecha = "";
	        try {
	        	fecha = Logica.darDataIdDescripcionAnySys("select 0,fecha from reposicion_sincronizacion s "
	        			+ "inner join reposicion_articulos r on r.idsincronizacion=s.id and r.idempresa=s.idempresa where idpicking="
	        			+idPicking).get(1).getDescripcion();
			} catch (Exception e) {
				// TODO: handle exception
			}
	        
	        cell = new PdfPCell(new Paragraph("Fecha: "+fecha,font));
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);
	        
	        //empiezo a mostrar depositos/comentario
	        try {
	        	String[] lista = comentario.split("-");
		        for (String data : lista) {
		        	cell = new PdfPCell(new Paragraph(data, font));	        
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        cell.setBorder(Rectangle.TOP| Rectangle.RIGHT | Rectangle.LEFT);
			        table.addCell(cell);
				}
			} catch (Exception e) {
				cell = new PdfPCell(new Paragraph(comentario, font));	        
		        cell.setHorizontalAlignment(1);
		        //cell.setPadding(3F);
		        cell.setColspan(2);
		        cell.setPaddingBottom(5F);
		        cell.setBorder(Rectangle.TOP| Rectangle.RIGHT | Rectangle.LEFT);
		        table.addCell(cell);
			}        
	        
	        documento.add(table);
	        documento.close();
	        
	        System.out.println("TERMINANDO ETIQUETA");
	        
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
	
	public static String etiquetas_verificacion_destinos(DataIDDescripcion destino, Usuario uLog)  throws FileNotFoundException, DocumentException
	{
		Logica Logica = new Logica();
		//List<DataIDDescripcion> articulos, int idPedido, String factura,  String cliente,String tel, String ci, String mail, Book book2
	    
		String path = "";
		String pathSalida = "";
		String download_pdf_from="";
		String delivery ="";
		String qr="";
		String envio = "";
		String envioId="";
		String envioDir="";
		System.out.println("");
	    try
	    {
	    	destino.setDescripcion(destino.getDescripcion().replace(" ", "_"));
	    	PropertiesHelper pH=new PropertiesHelper("paths");	    	
	    	try
	    	{
	    		pH.loadProperties();
	    	}
	    	catch(Exception e)
	    	{
	    		
	    	}			
	    	 
	    	System.out.println("");		
			path = pH.getValue("pdf")+"destiny"+destino.getDescripcion()+"_"+uLog.getIdEmpresa()+".pdf";
			pathSalida = pH.getValue("HTTP_pdf")+"/destiny"+destino.getDescripcion()+"_"+uLog.getIdEmpresa()+".pdf";
	        FileOutputStream archivo = new FileOutputStream(path);
	        Rectangle pageSize = new Rectangle(431F,450F);
	        Document documento = new Document(pageSize);
	        Font font = FontFactory.getFont("Arial", 12F, 1);
	        Font fontBig = FontFactory.getFont("Arial", 40F, 1);
	        Font fontBigBig = FontFactory.getFont("Arial", 100F, 1);
	        Font font1100 = FontFactory.getFont("Arial", 15F, 1);
	      
	        
	        PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	        documento.open();
	       // documento.setMargins(1F, 1F, 1F, 1F);
	        com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	        documento.newPage();
	        
	        PdfPTable table = new PdfPTable(2);
	        //table.setTotalWidth(289F);
	        
	        Font fontCodgreen = FontFactory.getFont("Arial", 8F, 1);
	        
	        String downloaded_filename_open_as_pdf="";
	      
	        
	        //ARRANCO ETIQUETA
	        System.out.println("EMPEZANDO CREACION DE ETIQUETA");
	        
	        /* REMITENTE */
	        
	        String remite = "Rte:  PEPEGANGA S.A. \r\n" + 
	        				"Domingo Aramburu 1521 Tel 2209 7709\r\n" + 
	        				"RUT 214 047 730 016";
	        PdfPCell cell = new PdfPCell(new Paragraph(remite,font));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            table.addCell(cell);
	        
	        
																				/* CODIGO DE BARRAS*/
	        Barcode39 code39 = new Barcode39();
	        String idd = ""+destino.getDescripcion();
	        idd = idd.toUpperCase();
	        code39.setCode(idd);                
	        code39.setBarHeight(30F);
	        code39.setX(1.0F);
	        cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
            cell.setHorizontalAlignment(1);
            cell.setPaddingTop(5F);
            //cell.setPadding(3F);
            //cell.setColspan(6);
	        cell.setColspan(2);
            table.addCell(cell);
	        
	        cell = new PdfPCell(new Paragraph("Destino: ",font));
	        cell.setHorizontalAlignment(1);
	        //cell.setPadding(3F);
	        cell.setColspan(2);
	        cell.setPaddingBottom(5F);
	        table.addCell(cell);	        
	        
	        //empiezo a mostrar depositos
	        	cell = new PdfPCell(new Paragraph(destino.getDescripcionB(), fontBig));	        
		        cell.setHorizontalAlignment(1);
		        //cell.setPadding(3F);
		        cell.setColspan(2);
		        cell.setPaddingBottom(5F);
		        cell.setBorder(Rectangle.TOP| Rectangle.RIGHT | Rectangle.LEFT);
		        table.addCell(cell);
		        
		        try {
		        	String des = destino.getDescripcionC();
			        cell = new PdfPCell(new Paragraph(des, fontBig));	
			                
			        cell.setHorizontalAlignment(1);
			        //cell.setPadding(3F);
			        cell.setColspan(2);
			        cell.setPaddingBottom(5F);
			        cell.setBorder(Rectangle.BOTTOM| Rectangle.RIGHT | Rectangle.LEFT);
			        table.addCell(cell);
				} catch (Exception e) {
					e.printStackTrace();
				}        
	        
	        documento.add(table);
	        documento.close();
	        
	        System.out.println("TERMINANDO ETIQUETA");
	        
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