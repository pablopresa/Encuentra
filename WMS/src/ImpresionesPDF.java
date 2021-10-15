import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import logica.LeerFicheroTexto;

import beans.encuentra.Ojo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ImpresionesPDF
{
	
	public static void main(String[] args) throws FileNotFoundException, DocumentException
	{
		
		
	
		
		String path = "";
		FileOutputStream archivo;
		try 
		{
			String ruta = LeerFicheroTexto.LeerArchivoTXT("RemitoReclamo.txt").toString();
			path = ruta+"/RemitoReclamo.pdf";
			archivo = new FileOutputStream(path);
			Rectangle pageSize = new Rectangle(230, 798);
			Document documento = new Document(pageSize);
			Font font = FontFactory.getFont("Arial", 12, Font.BOLD);
			Font fontAlta = FontFactory.getFont("Arial", 4, Font.BOLD);
			Font fontTitulo = FontFactory.getFont("Arial", 19, Font.BOLD);
			PdfWriter writer;
			writer = PdfWriter.getInstance(documento, archivo);
			documento.open();
			documento.setMargins(10f,10f, 0f, 0f);			
			 // documento.add(new Phrase("Comienzo de impresion"));
	        documento.newPage();
	        for (int i = 0; i < 2; i++) 
	        {
	        	 Image img = null;
					try 
					{
						img = Image.getInstance("C:\\logo.png");
					} catch (Exception e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Phrase vacio = new Phrase("\n");
					vacio.setFont(fontAlta);
					Paragraph pa = new Paragraph(vacio);
					
					documento.add(pa);
					
					
					//pongo el logo
					documento.add(img);
					documento.add(pa);
					//lo que va debajo del logo
					Phrase frase = new Phrase("Remito de Reclamo \n Stadium01");
					frase.setFont(font);
					Paragraph p = new Paragraph(frase);
					p.setAlignment(Element.ALIGN_CENTER);
					documento.add(p);
					
					documento.add(pa);
					// datos del cliente
					PdfPTable table = new PdfPTable(2);
			        table.setTotalWidth(230);
			        
			        
			        
			        table.setLockedWidth(true);
			        table.setWidths(new float[]{3, 5});
			        
			        PdfPCell cell;
			        
			        Phrase titulo = new Phrase("                 Datos del Cliente");
			        titulo.setFont(fontTitulo);
			        
			        cell = new PdfPCell(titulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setHorizontalAlignment(200);
			        cell.setColspan(2);
			        //cell.setBorder(Rectangle.NO_BORDER);
			        table.addCell(cell);
			        
			        //nombre
			        Phrase subTitulo = new Phrase("Nombre:");
			        subTitulo.setFont(fontTitulo);
			        cell = new PdfPCell(subTitulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase("Gonzalo Monzón"));
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        table.addCell(cell);
			        
			        //Cedula
			        subTitulo = new Phrase("Cedula:");
			        subTitulo.setFont(fontTitulo);
			        cell = new PdfPCell(subTitulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase("4216880"));
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        
			        table.addCell(cell);
			        
			        
			        //telefono
			     
			        subTitulo = new Phrase("Telefono:");
			        subTitulo.setFont(fontTitulo);
			        cell = new PdfPCell(subTitulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        table.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase("23621029"));
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        
			        table.addCell(cell);
			        
			       
			        
					documento.add(table);
			        
			        
					documento.add(pa);
					documento.add(pa);
					documento.add(pa);
					
					//datos del reclamo
					
					PdfPTable tableII = new PdfPTable(2);
			        tableII.setTotalWidth(230);
			        
			        
			        
			        tableII.setLockedWidth(true);
			        tableII.setWidths(new float[]{4, 5});
			        
			       
			        
			        titulo = new Phrase("              Datos del Reclamo");
			        titulo.setFont(fontTitulo);
			        
			        cell = new PdfPCell(titulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setHorizontalAlignment(200);
			        cell.setColspan(2);
			        //cell.setBorder(Rectangle.NO_BORDER);
			        tableII.addCell(cell);
			        
			        //nombre
			        subTitulo = new Phrase("Reclamo N°:");
			        subTitulo.setFont(fontTitulo);
			        cell = new PdfPCell(subTitulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        tableII.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase("15689"));
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        tableII.addCell(cell);
			        
			        //Cedula
			        subTitulo = new Phrase("Fecha:");
			        subTitulo.setFont(fontTitulo);
			        cell = new PdfPCell(subTitulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        tableII.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase("15-01-2014"));
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        
			        tableII.addCell(cell);
			        
			        
			        //telefono
			     
			        subTitulo = new Phrase("Articulo:");
			        subTitulo.setFont(fontTitulo);
			        cell = new PdfPCell(subTitulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        tableII.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase("001.00603000222.0"));
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        
			        tableII.addCell(cell);
			        
			       //razon
			        subTitulo = new Phrase("Razón:");
			        subTitulo.setFont(fontTitulo);
			        cell = new PdfPCell(subTitulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        tableII.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase("Cierres"));
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        
			        tableII.addCell(cell);
			        
			        
			        //razon
			        subTitulo = new Phrase("CFE:");
			        subTitulo.setFont(fontTitulo);
			        cell = new PdfPCell(subTitulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        tableII.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase("A-15487"));
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        
			        tableII.addCell(cell);
			        
			        
					documento.add(tableII);
					
					
					
					documento.add(pa);
					documento.add(pa);
					documento.add(pa);
					documento.add(pa);
					
					//Observaciones
					
					PdfPTable tableIII = new PdfPTable(1);
			        tableIII.setTotalWidth(230);
			        
			        
			        
			        tableIII.setLockedWidth(true);
			      
			       //razon
			        subTitulo = new Phrase("Observaciones:");
			        subTitulo.setFont(fontTitulo);
			        cell = new PdfPCell(subTitulo);
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        
			        tableIII.addCell(cell);
			        
			        cell = new PdfPCell(new Phrase("dfjdfkjdjfjid fkjdfldfjk jksf kjsljjkl klsjkljch lsjil klsjkl jsldcj kljsldv ljslijc"));
			        cell.setPadding(10f/1);
			        cell.setPaddingLeft(10f);
			        cell.setBorder(Rectangle.NO_BORDER);
			        
			        tableIII.addCell(cell);
			        
			        
					documento.add(tableIII);
					
					
					documento.add(pa);
					documento.add(pa);
					documento.add(pa);
					documento.add(pa);
					
					
			         //cell = new PdfPCell(img);
			       // cell.setPaddingLeft(40f);
			        //table.addCell(cell);
			        
			        //table.addCell(new Paragraph(et.getMarca(),fontCod));
			        //table.addCell(new Paragraph(et.getArticulo()+" "+et.getColor(),fontCod));
			        //table.addCell(new Paragraph(et.getDescripcion(),fontCod));
			        //String pie = "";
			        //table.addCell(new Paragraph("Precios: "+et.getEscala1() +" "+et.getPrecio1()+ "\n"+pie ,fontCod));
			        
			       //agrego el codigo
			      
					
					documento.add(pa);
					
					Phrase fraseFIN = new Phrase("Usuario: gmonzon");
					fraseFIN.setFont(font);
					p = new Paragraph(fraseFIN);
					p.setAlignment(Element.ALIGN_CENTER);
					documento.add(p);
					
					documento.newPage();
			}
			
			documento.close();
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Document darDocumento(List<Ojo> ojos)
	{
		
		
		 
		
		  
		
		
		
		return null;
		
		
		
	}
	
	
	
}