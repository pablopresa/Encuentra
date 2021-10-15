package beans.api;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class BarcodeReader
{
	private List<String> barcodes;
	  

	public List<String> getBarcodes() {
		return barcodes;
	}

	public void setBarcodes(List<String> barcodes) {
		this.barcodes = barcodes;
	}
	
	
	 public BarcodeReader(String filePath, int courier, int cantidad) {
	    
		 this.barcodes = new ArrayList<>();
		 String barra = "";
		 		 		 
		 try {
			 File f = new File(filePath);
		     if(f.exists())
		     {    	
		    	 try (PDDocument document = PDDocument.load(new File(filePath))) 
		    	 {

		             document.getClass();

		             if (!document.isEncrypted()) {
		 			
		                 PDFTextStripperByArea stripper = new PDFTextStripperByArea();
		                 stripper.setSortByPosition(true);

		                 PDFTextStripper tStripper = new PDFTextStripper();

		                 String pdfFileInText = tStripper.getText(document);
		                 pdfFileInText = pdfFileInText.replaceAll("\\r?\\n","");
		                 
		                 //String lines[] = pdfFileInText.split("\\r?\\n");
		                 
		                 switch (courier) { 
		                 case 90000:
		                	 barra = pdfFileInText.substring(17,30);
		                	 this.barcodes.add(barra);
		                	 break;
		        		 case 50000:
		        		 case 60000:
		        		 case 800000:
		        			 String lines[] = pdfFileInText.split("Destinatario:");
		        			 int i = 0;
		        			 for(String l : lines) {
		        				 if(i>0) {
		        					 barra = l.split("LOGISTICA")[0];
		        					 this.barcodes.add(barra);
		        				 }
		        				 i++;
		        			 }
		        			break;

		        		default:
		        			break;
		        		}
		                 		                 		                 
		             }

		         }
		    	 catch (Exception e) 
		    	 {
		    		 e.printStackTrace();
				}
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public static void main(String[] args) {
		 String path = "C:\\Program Files\\apache-tomcat-7.0.64\\webapps\\encuentraAPI\\pdf\\4143203825.pdf";
		 BarcodeReader br= new BarcodeReader(path,90000, 1);
	}
}
