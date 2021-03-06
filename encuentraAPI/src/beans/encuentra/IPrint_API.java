package beans.encuentra;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.security.cert.X509Certificate;

import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
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
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
//import org.apache.pdfbox.util.PDFImageWriter;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import beans.helper.PropertiesHelper;
import beans.helper.PropertiesHelperAPI;

import javax.net.ssl.X509TrustManager;

public class IPrint_API 
{

	private int id;
	
	
	
	
	
	public IPrint_API() 
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
               public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                   return null;
               }
               public void checkClientTrusted(X509Certificate[] certs, String authType) {
               }
               public void checkServerTrusted(X509Certificate[] certs, String authType) {
               }
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
				// TODO Auto-generated method stub
				
			}
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

  
		
	public static String ImprimirEtiquetasMIRTRANS(String xml, String tel, String dpto, String obs)  throws FileNotFoundException, DocumentException
    {
			String idTracking = "";
			String path = "";
			String download_pdf_from="";
			String pathSalida="";
        try
        {	        	
        	String[] datos = xml.split("\"");       	
        	PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
        	
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
            PDPage page = new PDPage(PDRectangle.A6);
            
            Rectangle pageSize = new Rectangle( page.getMediaBox().getHeight(),page.getMediaBox().getWidth());
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
            
          
            
            
            
            Barcode128 code39 = new Barcode128();
            code39.setCodeType(Barcode128.EAN13);
            code39.setCode(datos[13]);                
            code39.setBarHeight(50F);
            
            code39.setX(2.0F);
            
			
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
            
            //Obervaci?n
            if(datos.length > 31) {
            	cell = new PdfPCell(new Paragraph(datos[31],fontCod));
	            cell.setHorizontalAlignment(3);
	            cell.setColspan(1);
	            cell.setBorder(Rectangle.NO_BORDER);
	            table.addCell(cell);
            }
	            
            
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
            
            idTracking=datos[13];
            
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
        
        return pathSalida+","+idTracking;	               
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

  		

}