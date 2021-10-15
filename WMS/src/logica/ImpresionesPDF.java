

package logica;

import beans.encuentra.DataPicking;
import beans.encuentra.EtiquetaOsoneca;
import beans.encuentra.Ojo;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import dataTypes.*;
import helper.PropertiesHelper;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

// Referenced classes of package logica:
//            LeerFicheroTexto

public class ImpresionesPDF
{

	public ImpresionesPDF()
    {
    }

    public static String ImprimirEtiquetas(List ojos,String nombre, int idEmpresa)
        throws FileNotFoundException, DocumentException
    {
        String path = "";
        try
        {
        	
        	String ruta ="";
        	
    		PropertiesHelper pH=new PropertiesHelper("paths");
    		try
    		{
    			pH.loadProperties();
    			ruta = pH.getValue("pdf");
    		}
    		catch (Exception e) 
    		{
				
			}
    		
    		
           
            path = (new StringBuilder(String.valueOf(ruta))).append("/etiquetas.pdf").toString();
            FileOutputStream archivo = new FileOutputStream(path);
            Rectangle pageSize = new Rectangle(230F, 140F);
            Rectangle codeSize = new Rectangle(108F, 72F);
            Document documento = new Document(pageSize);
            Font font = FontFactory.getFont("Arial", 16F, 1);
            Font fontCod = FontFactory.getFont("Arial", 16F, 1);
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.setMargins(5F, 5F, 5F, 0.0F);
            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
            documento.newPage();
            for(Iterator iterator = ojos.iterator(); iterator.hasNext(); documento.newPage())
            {
                Ojo ojo = (Ojo)iterator.next();
                if(ojo.getIdOjo().endsWith("E")||ojo.getIdOjo().endsWith("P"))
                {
	                Barcode39 code39 = new Barcode39();
	                code39.setCode(ojo.getIdOjo());
	                code39.setBarHeight(80F);
	                code39.setX(2F);
	                PdfPTable table = new PdfPTable(1);
	                table.setTotalWidth(220F);
	                table.setLockedWidth(true);
	                Phrase frase = new Paragraph(nombre, fontCod);
	                PdfPCell cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(200);
	                cell.setBorder(0);
	                table.addCell(cell);
	                cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	                cell.setPadding(5F);
	                cell.setPaddingLeft(30F);
	                cell.setBorder(0);
	                table.addCell(cell);
	                documento.add(table);
                }
                else
                {
	                Barcode39 code39 = new Barcode39();
	                code39.setCode(ojo.getIdOjo());
	                code39.setBarHeight(80F);
	                code39.setX(1.3F);
	                PdfPTable table = new PdfPTable(2);
	                table.setTotalWidth(220F);
	                table.setLockedWidth(true);
	                table.setWidths(new float[] {
	                    5F, 3F
	                });
	                Phrase frase = new Phrase("            Ubicaci\363n encuentra");
	                frase.setFont(font);
	                PdfPCell cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(200);
	                cell.setColspan(2);
	                table.addCell(cell);
	                cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	                cell.setPadding(5F);
	                cell.setPaddingLeft(5F);
	                cell.setRowspan(4);
	                table.addCell(cell);
	                table.addCell((new StringBuilder("")).append(nombre).toString());
	                table.addCell((new StringBuilder("Estante: ")).append(ojo.getEstante()).toString());
	                table.addCell((new StringBuilder("Modulo: ")).append(ojo.getModulo()).toString());
	                Phrase fraseII = new Phrase(ojo.getIdOjo());
	                fraseII.setFont(fontCod);
	                cell = new PdfPCell(fraseII);
	                table.addCell(new Paragraph(ojo.getIdOjo(), fontCod));
	                documento.add(table);
                }
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
        return path;
    }

    public static String ImprimirEtiquetasCatalogo(Catalogo catalogo)
        throws FileNotFoundException, DocumentException
    {
        String path = "";
        try
        {
            String ruta = LeerFicheroTexto.LeerArchivoTXT("EtiquetasCatalogo.txt").toString();
            path = (new StringBuilder(String.valueOf(ruta))).append("/etiquetas.pdf").toString();
            FileOutputStream archivo = new FileOutputStream(path);
            Rectangle pageSize = new Rectangle(230F, 140F);
            Document documento = new Document(pageSize);
            Font font = FontFactory.getFont("Arial", 1.0F, 1);
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.setMargins(10F, 10F, 0.0F, 0.0F);
            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
            documento.newPage();
            for(Iterator iterator = catalogo.getArticulos().iterator(); iterator.hasNext(); documento.newPage())
            {
                DataArticuloCatalogo ar = (DataArticuloCatalogo)iterator.next();
                Paragraph paragraph = new Paragraph();
                paragraph.add(new Phrase(""));
                paragraph.setSpacingAfter(15F);
                paragraph.setSpacingBefore(15F);
                documento.add(paragraph);
                PdfPTable table = new PdfPTable(16);
                table.setTotalWidth(215F);
                table.setLockedWidth(true);
                Phrase frase = new Phrase("Articulo");
                frase.setFont(font);
                PdfPCell cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(200);
                cell.setColspan(5);
                table.addCell(cell);
                frase = new Phrase("Color");
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(200);
                cell.setColspan(5);
                table.addCell(cell);
                frase = new Phrase("Costo");
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(200);
                cell.setColspan(3);
                table.addCell(cell);
                frase = new Phrase("Venta");
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(200);
                cell.setColspan(3);
                table.addCell(cell);
                frase = new Phrase(ar.getArticulo());
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(200);
                cell.setColspan(5);
                table.addCell(cell);
                frase = new Phrase(ar.getColor());
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(200);
                cell.setColspan(5);
                table.addCell(cell);
                frase = new Phrase(ar.getPrecioCosto());
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(200);
                cell.setColspan(3);
                table.addCell(cell);
                frase = new Phrase(ar.getPrecioVenta());
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(200);
                cell.setColspan(3);
                table.addCell(cell);
                documento.add(table);
                Font fontII = FontFactory.getFont("Calibri", 7F);
                for(Iterator iterator1 = ar.getCurvas().iterator(); iterator1.hasNext(); documento.add(table))
                {
                    DataCurvaCatalogo c = (DataCurvaCatalogo)iterator1.next();
                    int columnas = c.getTalles().size() + 1;
                    paragraph = new Paragraph();
                    paragraph.add(new Phrase(""));
                    paragraph.setSpacingAfter(5F);
                    paragraph.setSpacingBefore(5F);
                    documento.add(paragraph);
                    table = new PdfPTable(columnas);
                    table.setTotalWidth(215F);
                    table.setLockedWidth(true);
                    for(Iterator iterator2 = c.getTalles().iterator(); iterator2.hasNext(); table.addCell(cell))
                    {
                        DataIDDescDescripcion t = (DataIDDescDescripcion)iterator2.next();
                        frase = new Phrase(t.getDesc());
                        frase.getFont().setSize(7F);
                        frase.getFont().setStyle(0);
                        frase.getFont().setFamily("Calibri");
                        cell = new PdfPCell(frase);
                    }

                    frase = new Phrase("T");
                    frase.getFont().setSize(7F);
                    frase.getFont().setStyle(0);
                    frase.getFont().setFamily("Calibri");
                    cell = new PdfPCell(frase);
                    table.addCell(cell);
                    for(Iterator iterator3 = c.getTalles().iterator(); iterator3.hasNext(); table.addCell(cell))
                    {
                        DataIDDescDescripcion t = (DataIDDescDescripcion)iterator3.next();
                        frase = new Phrase((new StringBuilder(String.valueOf(t.getId()))).toString());
                        frase.getFont().setSize(10F);
                        frase.getFont().setStyle(0);
                        frase.getFont().setFamily("Calibri");
                        System.out.println(t.getId());
                        cell = new PdfPCell(frase);
                        cell.setHorizontalAlignment(60);
                    }

                    frase = new Phrase((new StringBuilder(String.valueOf(c.getTotal()))).toString());
                    frase.getFont().setSize(10F);
                    frase.getFont().setStyle(0);
                    frase.getFont().setFamily("Calibri");
                    cell = new PdfPCell(frase);
                    cell.setHorizontalAlignment(60);
                    table.addCell(cell);
                }

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
        return path;
    }

    public static String ImprimirEtiquetasGrandes(List ojos, String nombre, int idEmpresa)
        throws FileNotFoundException, DocumentException
    {
        String path = "";
        try
        {
        	String ruta ="";
        	
    		PropertiesHelper pH=new PropertiesHelper("paths");
    		try
    		{
    			pH.loadProperties();
    			ruta = pH.getValue("pdf");
    		}
    		catch (Exception e) 
    		{
				
			}
    		
            path = (new StringBuilder(String.valueOf(ruta))).append("/etiquetas.pdf").toString();
            FileOutputStream archivo = new FileOutputStream(path);
            Rectangle pageSize = new Rectangle(293F, 431F);
            Document documento = new Document(pageSize);
            Font font = FontFactory.getFont("Arial", 25F, 1);
            Font fontCod = FontFactory.getFont("Arial", 70F, 1);
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.setMargins(10F, 10F, 10F, 10F);
            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
            documento.newPage();
            for(Iterator iterator = ojos.iterator(); iterator.hasNext(); documento.newPage())
            {
                Ojo ojo = (Ojo)iterator.next();
                if(ojo.getIdOjo().endsWith("E")||ojo.getIdOjo().endsWith("P"))
                {
                	font = FontFactory.getFont("Arial", 30F, 1);
                	Barcode39 code39 = new Barcode39();
	                code39.setCode(ojo.getIdOjo());
	                code39.setBarHeight(192F);
	                code39.setX(3F);
	                PdfPTable table = new PdfPTable(5);
	                table.setTotalWidth(289F);
	                table.setLockedWidth(true);
	                PdfPCell cell = new PdfPCell(new Paragraph((new StringBuilder("")).append(nombre).toString(), font));
	                cell.setRotation(90);
	                cell.setFixedHeight(410F);
	                cell.setHorizontalAlignment(1);
	                table.addCell(cell);
	                cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	                cell.setPadding(10F);
	                cell.setPaddingLeft(10F);
	                cell.setRotation(90);
	                cell.setHorizontalAlignment(1);
	                cell.setColspan(4);
	                table.addCell(cell);
	                documento.add(table);
                }
                else
                {
	                Barcode39 code39 = new Barcode39();
	                code39.setCode(ojo.getIdOjo());
	                code39.setBarHeight(192F);
	                code39.setX(2.0F);
	                PdfPTable table = new PdfPTable(5);
	                table.setTotalWidth(289F);
	                table.setLockedWidth(true);
	                table.setWidths(new float[] {
	                    2.0F, 2.0F, 2.0F, 2.0F, 5F
	                });
	                PdfPCell cell = new PdfPCell(new Paragraph("Ubicaci\363n encuentra", font));
	                cell.setRotation(90);
	                cell.setHorizontalAlignment(1);
	                cell.setRowspan(2);
	                cell.setFixedHeight(410F);
	                table.addCell(cell);
	                cell = new PdfPCell(new Paragraph((new StringBuilder("")).append(nombre).toString(), font));
	                cell.setRotation(90);
	                cell.setHorizontalAlignment(1);
	                cell.setFixedHeight(200F);
	                table.addCell(cell);
	                cell = new PdfPCell(new Paragraph((new StringBuilder("Estante: ")).append(ojo.getEstante()).toString(), font));
	                cell.setRotation(90);
	                cell.setHorizontalAlignment(1);
	                table.addCell(cell);
	                cell = new PdfPCell(new Paragraph((new StringBuilder("Modulo: ")).append(ojo.getModulo()).toString(), font));
	                cell.setRotation(90);
	                cell.setHorizontalAlignment(1);
	                table.addCell(cell);
	                cell = new PdfPCell(new Paragraph(ojo.getIdOjo(), fontCod));
	                cell.setRotation(90);
	                cell.setHorizontalAlignment(1);
	                table.addCell(cell);
	                cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	                cell.setPadding(10F);
	                cell.setPaddingLeft(10F);
	                cell.setColspan(4);
	                cell.setRotation(90);
	                cell.setHorizontalAlignment(1);
	                table.addCell(cell);
	                documento.add(table);
                }
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
        return path;
    }

    public static String ImprimirEtiquetasArticulos(List etiquetas, String ipAddr)
            throws FileNotFoundException, DocumentException
        {
            String path = "";
            try
            {
                String ruta = "c:\\out\\";//LeerFicheroTexto.LeerArchivoTXT("EtiquetasArti.txt").toString();
                path = (new StringBuilder(String.valueOf(ruta))).append("/").append(ipAddr).append("EtiquetasArti.pdf").toString();
                FileOutputStream archivo = new FileOutputStream(path);
                Rectangle pageSize = new Rectangle(141F, 70F);
                Document documento = new Document(pageSize);
                Font font = FontFactory.getFont("Arial", 9F, 0);
                Font fontCod = FontFactory.getFont("Arial", 9F, 1);
                Font fontPre = FontFactory.getFont("Arial", 10F, 1);
                Font fontBigPre = FontFactory.getFont("Arial", 17F, 1);
                PdfWriter writer = PdfWriter.getInstance(documento, archivo);
                documento.open();
                documento.setMargins(10F, 10F, 0.0F, 0.0F);
                documento.newPage();
                for(Iterator iterator = etiquetas.iterator(); iterator.hasNext(); documento.newPage())
                {
                    DataEtiquetaArticulo et = (DataEtiquetaArticulo)iterator.next();
                    PdfPTable table = new PdfPTable(3);
                    table.setTotalWidth(141F);
                    table.setLockedWidth(true);
                    table.setWidths(new float[] {
                        8F, 1F,1F 
                    });
                    table.setLockedWidth(true);
                    String color = et.getColor();
                    String descripcion = et.getDescripcion();
                    int largoDesc = 0;
                    if(!et.getPrecio2().equals("$"))
                    {
                    	largoDesc = 22;
                    }
                    else
                    {
                    	largoDesc = 27;
                    }
                    
                    
                    
                    if(color.length() >= 12)
                        color = color.substring(0, 12);
                    if(descripcion.length() >= largoDesc)
                        descripcion = descripcion.substring(0, largoDesc);
                    table.addCell(new Paragraph(et.getMarca(), fontCod));
                    /*precio*/
                    if(!et.getPrecio2().equals("$"))
                    {
                        
                    	 Paragraph par = new Paragraph();
                         Phrase p1 = new Phrase((new StringBuilder()).append(et.getPrecio1()).toString(), fontPre);
                         par.add(p1);
                         PdfPCell cell = new PdfPCell(par);
                         
                         cell.setRowspan(5);
                         //cell.setRightIndent(7/2);
                         cell.setRotation(90);
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         table.addCell(cell);
                    	
                    	
                    	Paragraph par2 = new Paragraph();
                        Phrase p12 = new Phrase((new StringBuilder()).append(et.getPrecio2()).toString()+"*", fontPre);
                        par2.add(p12);
                        PdfPCell cell2 = new PdfPCell(par2);
                        
                        cell2.setRowspan(5);
                        cell2.setRotation(90);
                        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                        //cell2.setRightIndent(7/2);
                        table.addCell(cell2);
                        
                    } else
                    {
                        Paragraph par = new Paragraph();
                        Phrase p1 = new Phrase((new StringBuilder()).append(et.getPrecio1()).toString(), fontBigPre);
                        par.add(p1);
                        
                        PdfPCell cell = new PdfPCell(par);
                        cell.setColspan(2);
                        cell.setRowspan(4);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRotation(90);
                        
                        
                        
                        table.addCell(cell);
                        
                    }
                    
                    
                    /*fin precio*/
                    table.addCell(new Paragraph((new StringBuilder(String.valueOf(et.getArticulo()))).append(" ").append(color).toString(), fontCod));
                    table.addCell(new Paragraph(descripcion, font));
                    String pie = "";
                    /*escalas*/
                    
                    if(!et.getPrecio2().equals("$"))
                    {
                        pie = (new StringBuilder(String.valueOf(et.getEscala2()))).append(" ").append(et.getPrecio2()).toString();
                        Paragraph par = new Paragraph();
                        Phrase p1 = new Phrase((new StringBuilder("Escalas: ")).append(et.getEscala1()).append(" ").toString(), font);
                        par.add(p1);
                        table.addCell(par);
                        Paragraph par2 = new Paragraph();
                        Phrase p12 = new Phrase((new StringBuilder("             ")).append(et.getEscala2()).append("* ").toString(), font);
                        par2.add(p12);
                        table.addCell(par2);
                    } else
                    {
                        Paragraph par = new Paragraph();
                        Phrase p1 = new Phrase((new StringBuilder("Escala: ")).append(et.getEscala1()).append(" ").toString(), font);
                        par.add(p1);
                        
                        PdfPCell cell = new PdfPCell(par);
                        table.addCell(cell);
                        
                        
                    }
                    /*fin escalas*/
                    
                    
                    
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
            return path;
        }

  

    public static String ImprimirEtiquetasComponentes(List etiquetas)
        throws FileNotFoundException, DocumentException
    {
        String path = "";
        try
        {
            String ruta = LeerFicheroTexto.LeerArchivoTXT("EtiquetasArti.txt").toString();
            path = (new StringBuilder(String.valueOf(ruta))).append("/EtiquetasCompo.pdf").toString();
            FileOutputStream archivo = new FileOutputStream(path);
            Rectangle pageSize = new Rectangle(234F, 70F);
            Document documento = new Document(pageSize);
            Font fontCod = FontFactory.getFont("Arial", 4.5F, 0);
            Font fontArt = FontFactory.getFont("Arial", 6F, 1);
            Font fontComp = FontFactory.getFont("Arial", 6F, 0);
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.setMargins(10F, 10F, 0.0F, 0.0F);
            documento.newPage();
            int puntero = 0;
            boolean saltar = false;
            for(Iterator iterator = etiquetas.iterator(); iterator.hasNext();)
            {
                DataEtiquetaComponentes et = (DataEtiquetaComponentes)iterator.next();
                if(!saltar)
                {
                    saltar = true;
                    try
                    {
                        DataEtiquetaComponentes izq = new DataEtiquetaComponentes();
                        DataEtiquetaComponentes der = new DataEtiquetaComponentes();
                        try
                        {
                            izq = et;
                            der = (DataEtiquetaComponentes)etiquetas.get(puntero + 1);
                        }
                        catch(Exception e)
                        {
                            der = new DataEtiquetaComponentes();
                            der.setArticulo("");
                            der.setOrigen("");
                            List lista = new ArrayList();
                            for(int i = 0; i < 4; i++)
                            {
                                DataIDDescDescripcion d = new DataIDDescDescripcion();
                                d.setDesc("");
                                d.setDescripcion("");
                                lista.add(d);
                            }

                            der.setComponentes(lista);
                        }
                        PdfPTable table = new PdfPTable(5);
                        table.setTotalWidth(234F);
                        table.setLockedWidth(true);
                        PdfPCell cell1 = new PdfPCell();
                        cell1.addElement(new Paragraph("Importador: Cybe S.A.", fontCod));
                        cell1.setBorder(0);
                        cell1.setPadding(0.0F);
                        PdfPCell cell2 = new PdfPCell();
                        cell2.addElement(new Paragraph("RUT: 210703920014", fontCod));
                        cell2.setBorder(0);
                        cell2.setPadding(0.0F);
                        PdfPCell cell3 = new PdfPCell();
                        cell3.addElement(new Paragraph("Importador: Cybe S.A.", fontCod));
                        cell3.setBorder(0);
                        cell3.setPadding(0.0F);
                        PdfPCell cell4 = new PdfPCell();
                        cell4.addElement(new Paragraph("RUT: 210703920014", fontCod));
                        cell4.setBorder(0);
                        cell4.setPadding(0.0F);
                        PdfPCell cell5 = new PdfPCell();
                        cell5.addElement(new Paragraph("    Direccion: ", fontCod));
                        cell5.setBorder(0);
                        cell5.setPadding(0.0F);
                        PdfPCell cell6 = new PdfPCell();
                        cell6.addElement(new Paragraph("Rep. Francesa 900", fontCod));
                        cell6.setBorder(0);
                        cell6.setPadding(0.0F);
                        PdfPCell cell7 = new PdfPCell();
                        cell7.addElement(new Paragraph("Direccion: ", fontCod));
                        cell7.setBorder(0);
                        cell7.setPadding(0.0F);
                        PdfPCell cell8 = new PdfPCell();
                        cell8.addElement(new Paragraph("Rep. Francesa 900", fontCod));
                        cell8.setBorder(0);
                        cell8.setPadding(0.0F);
                        PdfPCell cell9 = new PdfPCell();
                        cell9.addElement(new Paragraph("    Articulo: ", fontCod));
                        cell9.setBorder(0);
                        cell9.setPadding(0.0F);
                        PdfPCell cell10 = new PdfPCell();
                        cell10.addElement(new Paragraph(izq.getArticulo(), fontArt));
                        cell10.setBorder(0);
                        cell10.setPadding(0.0F);
                        PdfPCell cell11 = new PdfPCell();
                        cell11.addElement(new Paragraph("Articulo: ", fontCod));
                        cell11.setBorder(0);
                        cell11.setPadding(0.0F);
                        PdfPCell cell12 = new PdfPCell();
                        cell12.addElement(new Paragraph(der.getArticulo(), fontArt));
                        cell12.setBorder(0);
                        cell12.setPadding(0.0F);
                        PdfPCell cell13 = new PdfPCell();
                        cell13.addElement(new Paragraph("    Origen: ", fontCod));
                        cell13.setBorder(0);
                        cell13.setPadding(0.0F);
                        PdfPCell cell14 = new PdfPCell();
                        cell14.addElement(new Paragraph(izq.getOrigen(), fontCod));
                        cell14.setBorder(0);
                        cell14.setPadding(0.0F);
                        PdfPCell cell15 = new PdfPCell();
                        cell15.addElement(new Paragraph("Origen: ", fontCod));
                        cell15.setBorder(0);
                        cell15.setPadding(0.0F);
                        PdfPCell cell16 = new PdfPCell();
                        cell16.addElement(new Paragraph(der.getOrigen(), fontCod));
                        cell16.setBorder(0);
                        cell16.setPadding(0.0F);
                        PdfPCell vacio = new PdfPCell();
                        vacio.addElement(new Paragraph("", fontCod));
                        vacio.setBorder(0);
                        PdfPCell cell17 = new PdfPCell();
                        cell17.addElement(new Paragraph((new StringBuilder("    ")).append(izq.getComponentes().get(0).getDesc()).append(": ").toString(), fontComp));
                        cell17.setBorder(0);
                        cell17.setPadding(0.0F);
                        PdfPCell cell18 = new PdfPCell();
                        cell18.addElement(new Paragraph(izq.getComponentes().get(0).getDescripcion(), fontComp));
                        cell18.setBorder(0);
                        cell18.setPadding(0.0F);
                        PdfPCell cell21 = new PdfPCell();
                        cell21.addElement(new Paragraph((new StringBuilder("    ")).append(izq.getComponentes().get(1).getDesc()).append(":").toString(), fontComp));
                        cell21.setBorder(0);
                        cell21.setPadding(0.0F);
                        PdfPCell cell22 = new PdfPCell();
                        cell22.addElement(new Paragraph(izq.getComponentes().get(1).getDescripcion(), fontComp));
                        cell22.setBorder(0);
                        cell22.setPadding(0.0F);
                        PdfPCell cell25 = new PdfPCell();
                        cell25.addElement(new Paragraph((new StringBuilder("   ")).append(izq.getComponentes().get(2).getDesc()).append(": ").toString(), fontComp));
                        cell25.setBorder(0);
                        cell25.setPadding(0.0F);
                        PdfPCell cell26 = new PdfPCell();
                        cell26.addElement(new Paragraph(izq.getComponentes().get(2).getDescripcion(), fontComp));
                        cell26.setBorder(0);
                        cell26.setPadding(0.0F);
                        PdfPCell cell29 = new PdfPCell();
                        cell29.addElement(new Paragraph((new StringBuilder("    ")).append(izq.getComponentes().get(3).getDesc()).append(": ").toString(), fontComp));
                        cell29.setBorder(0);
                        cell29.setPadding(0.0F);
                        PdfPCell cell30 = new PdfPCell();
                        cell30.addElement(new Paragraph(izq.getComponentes().get(3).getDescripcion(), fontComp));
                        cell30.setBorder(0);
                        cell30.setPadding(0.0F);
                        PdfPCell cell19 = new PdfPCell();
                        cell19.addElement(new Paragraph((new StringBuilder(String.valueOf(der.getComponentes().get(0).getDesc()))).append(": ").toString(), fontComp));
                        cell19.setBorder(0);
                        cell19.setPadding(0.0F);
                        PdfPCell cell20 = new PdfPCell();
                        cell20.addElement(new Paragraph(der.getComponentes().get(0).getDescripcion(), fontComp));
                        cell20.setBorder(0);
                        cell20.setPadding(0.0F);
                        PdfPCell cell23 = new PdfPCell();
                        cell23.addElement(new Paragraph((new StringBuilder(String.valueOf(der.getComponentes().get(1).getDesc()))).append(": ").toString(), fontComp));
                        cell23.setBorder(0);
                        cell23.setPadding(0.0F);
                        PdfPCell cell24 = new PdfPCell();
                        cell24.addElement(new Paragraph(der.getComponentes().get(1).getDescripcion(), fontComp));
                        cell24.setBorder(0);
                        cell24.setPadding(0.0F);
                        PdfPCell cell27 = new PdfPCell();
                        cell27.addElement(new Paragraph((new StringBuilder(String.valueOf(der.getComponentes().get(2).getDesc()))).append(": ").toString(), fontComp));
                        cell27.setBorder(0);
                        cell27.setPadding(0.0F);
                        PdfPCell cell28 = new PdfPCell();
                        cell28.addElement(new Paragraph(der.getComponentes().get(2).getDescripcion(), fontComp));
                        cell28.setBorder(0);
                        cell28.setPadding(0.0F);
                        PdfPCell cell31 = new PdfPCell();
                        cell31.addElement(new Paragraph((new StringBuilder(String.valueOf(der.getComponentes().get(3).getDesc()))).append(": ").toString(), fontComp));
                        cell31.setBorder(0);
                        cell31.setPadding(0.0F);
                        PdfPCell cell32 = new PdfPCell();
                        cell32.addElement(new Paragraph(der.getComponentes().get(3).getDescripcion(), fontComp));
                        cell32.setBorder(0);
                        cell32.setPadding(0.0F);
                        table.addCell(cell1);
                        table.addCell(cell2);
                        table.addCell(vacio);
                        table.addCell(cell3);
                        table.addCell(cell4);
                        table.addCell(cell5);
                        table.addCell(cell6);
                        table.addCell(vacio);
                        table.addCell(cell7);
                        table.addCell(cell8);
                        table.addCell(cell9);
                        table.addCell(cell10);
                        table.addCell(vacio);
                        table.addCell(cell11);
                        table.addCell(cell12);
                        table.addCell(cell13);
                        table.addCell(cell14);
                        table.addCell(vacio);
                        table.addCell(cell15);
                        table.addCell(cell16);
                        table.addCell(cell17);
                        table.addCell(cell18);
                        table.addCell(vacio);
                        table.addCell(cell19);
                        table.addCell(cell20);
                        table.addCell(cell21);
                        table.addCell(cell22);
                        table.addCell(vacio);
                        table.addCell(cell23);
                        table.addCell(cell24);
                        table.addCell(cell25);
                        table.addCell(cell26);
                        table.addCell(vacio);
                        table.addCell(cell27);
                        table.addCell(cell28);
                        table.addCell(cell29);
                        table.addCell(cell30);
                        table.addCell(vacio);
                        table.addCell(cell31);
                        table.addCell(cell32);
                        documento.add(table);
                        documento.newPage();
                    }
                    catch(Exception exception) { }
                } else
                {
                    saltar = false;
                }
                puntero++;
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
        return path;
    }
    
    
    
    public static String ImprimirEtiquetasarticulos3L(List<DataIDDescripcion> lista, int idEmpresa)
            throws FileNotFoundException, DocumentException
        {
            String path = "";
            try
            {
            	
            	String ruta ="";
            	
        		PropertiesHelper pH=new PropertiesHelper("paths");
        		try
        		{
        			pH.loadProperties();
        			ruta = pH.getValue("pdf");
        		}
        		catch (Exception e) 
        		{
    				
    			}
        		
        		
               
                path = (new StringBuilder(String.valueOf(ruta))).append("/etiquetasF3_"+idEmpresa+".pdf").toString();
                FileOutputStream archivo = new FileOutputStream(path);
                
               
                double width = 96 / 25.4 * 72.0;
                double height = 25 / 25.4 * 72.0;
                
                
                double widthT1 = 30 / 25.4 * 72.0;
                double widthMedio = 3 / 25.4 * 72.0;
                
                double heightC = 7 / 25.4 * 72.0;
                
                Rectangle pageSize = new Rectangle((float)width, (float)height);
                
                Document documento = new Document(pageSize);
                Font font = FontFactory.getFont("Arial", 7F, 0);
                Font fontW = FontFactory.getFont("Arial", 10F, 1);
                fontW.setColor(BaseColor.BLACK);
                
                PdfWriter writer = PdfWriter.getInstance(documento, archivo);
                documento.open();
                documento.setMargins(0F, 0F, 0.0F, 0.0F);
                com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
                documento.newPage();
                
                
                int pasada = 0;
                List<List<DataIDDescripcion>> listadelistas3 = new ArrayList<List<DataIDDescripcion>>();
                
                List<DataIDDescripcion> lista3 = new ArrayList<>();
                
                for (DataIDDescripcion d : lista) 
                {
                	//calculo la cantidad de recorridas
                	int totalDeRecorridas = d.getId();
                	int resto = totalDeRecorridas%3;
                	if(resto!=0)
                		totalDeRecorridas += (3-resto);
                	
                	totalDeRecorridas = totalDeRecorridas/3;
                	
                	//creo la lista de 3
                	for (int j = 0; j < 3; j++) 
                	{
                		lista3.add(d);
                	}
                	
                	//recorro formando las listas de 3
                	for (int i = 0; i < totalDeRecorridas; i++) 
                	{
                		listadelistas3.add(lista3);
                	}
                	
                	lista3 = new ArrayList<>();
                
				}
                
                
               
                
                for (List<DataIDDescripcion> lsta : listadelistas3) 
                {
                	String[] array1={};
                	String[] array2={};
                	String[] array3={};
                	try
                	{
                		array1=lsta.get(0).getDescripcion().split(",");
                	}
                	catch (Exception e) 
                	{
                		e.printStackTrace();
					}
                	try
                	{
                		array2=lsta.get(1).getDescripcion().split(",");
                		if(array2.length==0)
                		{
                			array2= new String [] {"","" ,"","","","","","" ,"","",""};
                		}
                	}
                	catch (Exception e) 
                	{
                		e.printStackTrace();
					}
                	try
                	{
                		array3=lsta.get(2).getDescripcion().split(",");
                		if(array3.length==0)
                		{
                			array3= new String [] {"","" ,"","","","","","" ,"","",""};
                		}
                	}
                	catch (Exception e) 
                	{
                		e.printStackTrace();
					}
                	
                	
                	
                	/*********************************/
                	
                	
                	
                	documento.newPage();
                    
                    double medioheight = 8 / 25.4 * 72.0;
                    double cuartoHei = 4 / 25.4 * 72.0;
                    
        
                    PdfPTable tableM = new PdfPTable(5);
                    
                    
                    tableM.setTotalWidth((float)((widthT1*3)+(widthMedio*2)));
                    tableM.setLockedWidth(true);
                    
                    tableM.setWidths(new float[] {
                        (float)widthT1, (float)widthMedio,(float)widthT1, (float)widthMedio,(float)widthT1
                    });
                   
                    
                    PdfPTable table1 = new PdfPTable(2);
                    double widthT0 = 15 / 25.4 * 72.0;
                    
                    
                    table1.setTotalWidth((float)(widthT1));
                    table1.setLockedWidth(true);
                    
                    table1.setWidths(new float[] 
                    {
                        (float)widthT0, (float)widthT0
                    });
                    
                    
                    
                    
                    
                    PdfPTable table2 = new PdfPTable(2);
                    table2.setTotalWidth((float)(widthT1));
                    table2.setLockedWidth(true);
                    table2.setWidths(new float[] 
                    {
                        (float)widthT0, (float)widthT0
                    });
                    
                    
                    
                    PdfPTable table3 = new PdfPTable(2);
                    table3.setTotalWidth((float)(widthT1));
                    table3.setLockedWidth(true);
                    table3.setWidths(new float[] 
                    {
                        (float)widthT0, (float)widthT0
                    });
                    
                    
                    
                    
                    
                    
                    Phrase frase = new Phrase(" ", font);
                    PdfPCell cell = new PdfPCell(frase);
                    cell.setColspan(2);
                    cell.setBorderWidth(0f);

                    //1
                    table1.addCell(cell);
                    //2
                    table2.addCell(cell);
                    //3
                    table3.addCell(cell);
                    
                    
                    
                    /*1*/
                    frase = new Phrase(array1[5], fontW);
                    cell = new PdfPCell(frase);
                    cell.setColspan(2);
                    cell.setFixedHeight((float)heightC);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setBorderWidth(0f);
                    table1.addCell(cell);
                    
                    /*2*/
                    frase = new Phrase(array2[5], fontW);
                    cell = new PdfPCell(frase);
                    cell.setColspan(2);
                    cell.setFixedHeight((float)heightC);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setBorderWidth(0f);
                    table2.addCell(cell);
                    
                    /*3*/
                    try
                    {
                    frase = new Phrase(array3[5], fontW);
                    cell = new PdfPCell(frase);
                    cell.setColspan(2);
                    cell.setFixedHeight((float)heightC);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setBorderWidth(0f);
                    table3.addCell(cell);
                    }
                    catch (Exception e) 
                    {
						
					}
                    /*1*/
                    
                    Utilidades ut = new Utilidades();
                    if(ut.isNumeric(array1[3]))
                    {
                    	BarcodeEAN barcode = new BarcodeEAN();
                        barcode.setCodeType(BarcodeEAN.EAN13);
                        barcode.setCode(array1[3]);
                        barcode.setBarHeight((float)cuartoHei);
                        barcode.setX((float)0.70);
                        cell = new PdfPCell(barcode.createImageWithBarcode(cb, null, null));
                    }
                    else
                    {
                    	Barcode128 barcode = new Barcode128();
                        //barcode.setCodeType(BarcodeEAN.EAN13);
                        barcode.setCode(array1[3]);
                        barcode.setBarHeight((float)cuartoHei);
                        barcode.setX((float)0.70);
                        cell = new PdfPCell(barcode.createImageWithBarcode(cb, null, null));
                    }
                    
                    
                    
                    
                    cell.setColspan(2);
                    cell.setFixedHeight((float)medioheight);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorderWidth(0f);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table1.addCell(cell);
                    
                    /*2*/
                    
                    
                    if(ut.isNumeric(array2[3]))
                    {
                    	BarcodeEAN barcode = new BarcodeEAN();
                        barcode.setCodeType(BarcodeEAN.EAN13);
                        barcode.setCode(array1[3]);
                        barcode.setBarHeight((float)cuartoHei);
                        barcode.setX((float)0.70);
                        cell = new PdfPCell(barcode.createImageWithBarcode(cb, null, null));
                    }
                    else
                    {
                    	Barcode128 barcode = new Barcode128();
                        //barcode.setCodeType(BarcodeEAN.EAN13);
                        barcode.setCode(array1[3]);
                        barcode.setBarHeight((float)cuartoHei);
                        barcode.setX((float)0.70);
                        cell = new PdfPCell(barcode.createImageWithBarcode(cb, null, null));
                    }
                    
                    cell.setColspan(2);
                    cell.setFixedHeight((float)medioheight);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorderWidth(0f);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table2.addCell(cell);
                    
                    /*3*/
                    try
                    {
                    
                    if(ut.isNumeric(array1[3]))
                    {
                    	BarcodeEAN barcode = new BarcodeEAN();
                        barcode.setCodeType(BarcodeEAN.EAN13);
                        barcode.setCode(array1[3]);
                        barcode.setBarHeight((float)cuartoHei);
                        barcode.setX((float)0.70);
                        cell = new PdfPCell(barcode.createImageWithBarcode(cb, null, null));
                    }
                    else
                    {
                    	Barcode128 barcode = new Barcode128();
                        //barcode.setCodeType(BarcodeEAN.EAN13);
                        barcode.setCode(array1[3]);
                        barcode.setBarHeight((float)cuartoHei);
                        barcode.setX((float)0.70);
                        cell = new PdfPCell(barcode.createImageWithBarcode(cb, null, null));
                    }
                    
                    cell.setColspan(2);
                    cell.setFixedHeight((float)medioheight);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBorderWidth(0f);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table3.addCell(cell);
                    
                    }
                    catch (Exception e) 
                    {
                    	e.printStackTrace();
					}
                   
                    
                    cell = new PdfPCell(table1);
                    tableM.addCell(cell);
                   
                    
                    frase = new Phrase("", font);
                    cell = new PdfPCell(frase);
                    cell.setBorderWidth(0f);
                    tableM.addCell(cell);
                    
                    
                    cell = new PdfPCell(table2);
                    tableM.addCell(cell);
                    
                    frase = new Phrase("", font);
                    cell = new PdfPCell(frase);
                    cell.setBorderWidth(0f);
                    tableM.addCell(cell);
                    

                    cell = new PdfPCell(table3);
                    tableM.addCell(cell);
                    
                    
                  
                    
                    documento.add(tableM);
                	
                	
                	/***************************************/
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
            return "pdf/etiquetasF3_"+idEmpresa+".pdf";
        }
    
    
    public static String ImprimirEtiquetasArticulosCB(List <DataIDDescripcion> etiquetas, String ipAddr)
            throws FileNotFoundException, DocumentException
        {
    		Logica Logica = new Logica();
    		String path = "";
    		String pathHttp = "";
    		String fecha = "";
            
    		try
            {
    			String ruta ="";
            	String rutaHttp ="";
            	        	
        		PropertiesHelper pH=new PropertiesHelper("paths");
        		try
        		{
        			pH.loadProperties();
        			ruta = pH.getValue("pdf");
        			rutaHttp = pH.getValue("HTTP_pdf");
        		}
        		catch (Exception e) 
        		{
    				
    			}
        		
        		pathHttp = rutaHttp+"/r"+ipAddr+".pdf";
               
                path = (new StringBuilder(String.valueOf(ruta))).append("/r"+ipAddr+".pdf").toString();
                FileOutputStream archivo = new FileOutputStream(path);
               
                
                Rectangle pageSize = new Rectangle(141F, 70F);
                Document documento = new Document(pageSize);
                Font font = FontFactory.getFont("Arial", 9F, 0);
                Font fontCod = FontFactory.getFont("Arial", 9F, 1);
                Font fontPre = FontFactory.getFont("Arial", 10F, 1);
                Font fontBigPre = FontFactory.getFont("Arial", 17F, 1);
                
                documento.open();
                documento.setMargins(10F, 10F, 0.0F, 0.0F);
                
                PdfWriter writer = PdfWriter.getInstance(documento, archivo);
                com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
                
                
                for (DataIDDescripcion ets : etiquetas) 
                {
                	for (int i = 0; i < ets.getId(); i++) 
                	{
                		documento.newPage();
                		Barcode128 code39 = new Barcode128();
    	                code39.setCode(ets.getDescripcionB());
    	                
    	                
    	                
                		
                		PdfPTable table = new PdfPTable(1);
                        table.setTotalWidth(141F);
                		
                		
                		
                		Paragraph par = new Paragraph();
                        Phrase p1 = new Phrase(ets.getDescripcion(), fontPre);
                        PdfPCell cell = new PdfPCell(p1);
                		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                		table.addCell(cell);
                		
                		
                		
                		cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
                		table.addCell(cell);
                		
                		documento.add(table);
                		
                		
					}
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
            return path;
        }
	
	
    
    
    
    public static void imprimirTicketCambio(int idEmpresa, int cantidad, String pedido,  String articulo, int idEquipo) 
	{
		
		Logica Logica = new Logica();
		String path = "";
        String pathHttp = "";
        String fecha = "";
        try {
			fecha = Logica.getDate().getDescripcion();
		} catch (Exception e) {
			e.printStackTrace();
		}
        try
        {
        	
        	
        	String ruta ="";
        	String rutaHttp ="";
        	        	
    		PropertiesHelper pH=new PropertiesHelper("paths");
    		try
    		{
    			pH.loadProperties();
    			ruta = pH.getValue("pdf");
    			rutaHttp = pH.getValue("HTTP_pdf");
    		}
    		catch (Exception e) 
    		{
				
			}
    		
    		pathHttp = rutaHttp+"/TR"+pedido+".pdf";
           
            path = (new StringBuilder(String.valueOf(ruta))).append("/TR"+pedido+".pdf").toString();
            FileOutputStream archivo = new FileOutputStream(path);
            Rectangle pageSize = new Rectangle(230F, 200F);
            Rectangle codeSize = new Rectangle(108F, 72F);
            Document documento = new Document(pageSize);
            Font fontSmall = FontFactory.getFont("Arial", 10F, Font.BOLD);
            Font font = FontFactory.getFont("Arial", 16F, 1);
            Font fontCod = FontFactory.getFont("Arial", 20F, 1);
            Font fontPedido = FontFactory.getFont("Arial", 33F,1);
            fontPedido.setStyle("bold");
            
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.setMargins(5F, 5F, 0.0F, 0.0F);
            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
            
            for (int i = 0; i < cantidad; i++) 
            {
            	documento.newPage();
            
                
                PdfPTable table = new PdfPTable(3);
                table.setTotalWidth(220F);
                table.setLockedWidth(true);
                table.setWidths(new float[] {
                    5.5F, 2.0F,2.5F
                });
                
                
                
                Phrase frase = new Phrase();
                
                PdfPCell cell = new PdfPCell(new Paragraph("Ticket de Cambio", fontPedido));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(3);
                table.addCell(cell);
                
                PdfPCell cellcell;
                
                
                
                
                frase = new Phrase("");
                frase.setFont(fontCod);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(3);
                
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("P: "+pedido+""));
                cell.setColspan(3);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Arr: "+articulo+""));
                cell.setColspan(3);
                table.addCell(cell);
                
                
                
                
                documento.add(table);
				
			}
            
            
            
            

            documento.close();
            
            System.out.println("");
            Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
            api.PutColaImpresion("TR"+pedido, pathHttp, 0, 2,idEquipo,idEmpresa,1);
        }
        catch(DocumentException e)
        {
            e.printStackTrace();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
		
	}
    
    
    
    
    
    
    public static void imprimirTicketMovStock(int origen, int destino, String idUsuario, String comentario,
			List<DataIDDescripcion> detalle, String idDoc, int tipoComanda, int idEquipo, int idEmpresa, int cantidad_remitos) 
	{
		
		Logica Logica = new Logica();
		String path = "";
        String pathHttp = "";
        String fecha = "";
        try {
			fecha = Logica.getDate().getDescripcion();
		} catch (Exception e) {
			e.printStackTrace();
		}
        try
        {
        	String identificador = "";
        	String tipoRemito = "";
        	//int aNumber = (int)((Math.random() * 9000000)+1000000);
        	
        	switch (tipoComanda) {
			case 1:
				tipoRemito = "Picking: ";
        		identificador = "p"+idDoc;//+aNumber+"";
				break;
			case 2:
				tipoRemito = "Remito: ";
        		identificador = idDoc+"";
				break;	
			case 3:
				tipoRemito = "Mayorista";
        		identificador = "p"+idDoc;;
				break;

			default:
				break;
			}
        	
        	String ruta ="";
        	String rutaHttp ="";
        	        	
    		PropertiesHelper pH=new PropertiesHelper("paths");
    		try
    		{
    			pH.loadProperties();
    			ruta = pH.getValue("pdf");
    			rutaHttp = pH.getValue("HTTP_pdf");
    		}
    		catch (Exception e) 
    		{
				
			}
    		
    		pathHttp = rutaHttp+"/r"+identificador+".pdf";
           
            path = (new StringBuilder(String.valueOf(ruta))).append("/r"+identificador+".pdf").toString();
            FileOutputStream archivo = new FileOutputStream(path);
            Rectangle pageSize = new Rectangle(230F, 900F);
            Rectangle codeSize = new Rectangle(108F, 72F);
            Document documento = new Document(pageSize);
            Font fontSmall = FontFactory.getFont("Arial", 10F, Font.BOLD);
            Font font = FontFactory.getFont("Arial", 16F, 1);
            Font fontCod = FontFactory.getFont("Arial", 20F, 1);
            Font fontPedido = FontFactory.getFont("Arial", 33F,1);
            fontPedido.setStyle("bold");
            
            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
            documento.open();
            documento.setMargins(5F, 5F, 0.0F, 0.0F);
            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
            documento.newPage();
            
                
                PdfPTable table = new PdfPTable(3);
                table.setTotalWidth(220F);
                table.setLockedWidth(true);
                table.setWidths(new float[] {
                    5.5F, 2.0F,2.5F
                });
                
                
                Phrase frase = new Phrase("Notificacion Encuentra");
                frase.setFont(font);
                PdfPCell cell = new PdfPCell(frase);
                
                PdfPCell cellcell;
                if(tipoComanda==3) {
                	cellcell = new PdfPCell(new Paragraph(tipoRemito, fontPedido) );
                }
                else {
                	cellcell = new PdfPCell(new Paragraph(tipoRemito+idDoc, fontPedido) );
                }
                
                cellcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellcell.setColspan(3);
                table.addCell(cellcell);
                
                frase = new Phrase("");
                frase.setFont(fontCod);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(3);
                
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Fecha: "+fecha+""));
                cell.setColspan(3);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Usuario: "+idUsuario+""));
                cell.setColspan(3);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Origen: "+origen+""));
                cell.setColspan(3);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Destino: "+destino+""));
                cell.setColspan(3);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Comentario: "+comentario+""));
                cell.setColspan(3);
                table.addCell(cell);
                
                
                frase = new Phrase("Detalle del movimiento");
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(3);
                table.addCell(cell);
                
                //Collections.sort(detalle);
                int cant = 0;
                
                if(tipoComanda != 2){
                	frase = new Phrase("Articulo");
                    frase.setFont(font);
                    cell = new PdfPCell(frase);
                    table.addCell(cell);
                    
                    frase = new Phrase("Cant");
                    frase.setFont(font);
                    cell = new PdfPCell(frase);
                    table.addCell(cell);
                    
                    if(tipoComanda == 1) {
                    	frase = new Phrase("Pedido");
                        frase.setFont(font);
                        cell = new PdfPCell(frase);
                        table.addCell(cell);
                    }
                    else {
                    	frase = new Phrase("Solicitud");
                        frase.setFont(font);
                        cell = new PdfPCell(frase);
                        table.addCell(cell);
                    }
                    
                                        
                    for (DataIDDescripcion ar : detalle) 
                    { 
                    	frase = new Phrase(ar.getDescripcion());
    	                frase.setFont(font);
    	                cell = new PdfPCell(frase);
    	                table.addCell(cell);
    	                
    	                frase = new Phrase(ar.getId()+ "");
    	                frase.setFont(font);
    	                cell = new PdfPCell(frase);
    	                table.addCell(cell);
    	                
    	                frase = new Phrase(ar.getDescripcionB()+ "");
    	                frase.setFont(font);
    	                cell = new PdfPCell(frase);
    	                table.addCell(cell);
    	                
    	                cant += ar.getId();
    				}
                }
                else{
                	frase = new Phrase("Articulo");
                    frase.setFont(font);
                    cell = new PdfPCell(frase);
                    cell.setColspan(2);
                    table.addCell(cell);
                    
                    frase = new Phrase("Cant");
                    frase.setFont(font);
                    cell = new PdfPCell(frase);
                    table.addCell(cell);
                    
                    for (DataIDDescripcion ar : detalle) 
                    { 
                    	frase = new Phrase(ar.getDescripcion() );
    	                frase.setFont(font);
    	                cell = new PdfPCell(frase);
    	                cell.setColspan(2);
    	                table.addCell(cell);
    	                
    	                frase = new Phrase(ar.getId()+ "");
    	                frase.setFont(font);
    	                cell = new PdfPCell(frase);
    	                table.addCell(cell);
    	                
    	                frase = new Phrase(ar.getDescripcionC() );
    	                frase.setFont(fontSmall);
    	                cell = new PdfPCell(frase);
    	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	                cell.setColspan(3);
    	                table.addCell(cell);
    	                    	                
    	                cant += ar.getId();
    				}
                }
                
                
                
                frase = new Phrase("Unidades totales: "+cant);
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(3);
                table.addCell(cell);
                
                
                documento.add(table);
            

            documento.close();
            
            System.out.println("");
            Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
            api.PutColaImpresion("r"+identificador, pathHttp, 0, 2,idEquipo,idEmpresa,cantidad_remitos);
           
        }
        catch(DocumentException e)
        {
            e.printStackTrace();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
		
	}

	public static String ImprimirPedidosArticuloReq(List pedidos, int idEmpresa)
	        throws FileNotFoundException, DocumentException
	    {
	        String path = "";
	        String pathHttp = "";
	        
	        
	      


	        
	        int aNumber = (int)((Math.random() * 9000000)+1000000);
	        
	        
	        
	        try
	        {
	        	
	        	String ruta ="";
	        	String rutaHttp ="";
	        	
	    		PropertiesHelper pH=new PropertiesHelper("paths");
	    		try
	    		{
	    			pH.loadProperties();
	    			ruta = pH.getValue("pdf");
	    			rutaHttp = pH.getValue("HTTP_pdf");
	    		}
	    		catch (Exception e) 
	    		{
					
				}
	    		
	    		pathHttp = rutaHttp+"/"+aNumber+".pdf";
	           
	            path = (new StringBuilder(String.valueOf(ruta))).append("/"+aNumber+".pdf").toString();
	            FileOutputStream archivo = new FileOutputStream(path);
	            Rectangle pageSize = new Rectangle(230F, 450F);
	            Rectangle codeSize = new Rectangle(108F, 72F);
	            Document documento = new Document(pageSize);
	            Font font = FontFactory.getFont("Arial", 16F, 1);
	            Font fontCod = FontFactory.getFont("Arial", 20F, 1);
	            Font fontPedido = FontFactory.getFont("Arial", 43F,1);
	            fontPedido.setStyle("bold");
	            
	            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	            documento.open();
	            documento.setMargins(5F, 5F, 0.0F, 0.0F);
	            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	            documento.newPage();
	            for(Iterator iterator = pedidos.iterator(); iterator.hasNext(); documento.newPage())
	            {
	            	DataPedidoPickup pedido = (DataPedidoPickup)iterator.next();
	                Barcode39 code39 = new Barcode39();
	                code39.setCode(pedido.getIdPedido());
	                code39.setBarHeight(80F);
	                code39.setX(1.2F);
	                
	                PdfPTable table = new PdfPTable(2);
	                table.setTotalWidth(220F);
	                table.setLockedWidth(true);
	                table.setWidths(new float[] {
	                    5F, 3F
	                });
	                
	                
	                Phrase frase = new Phrase("Notificacion de picking de pedido - encuentra");
	                frase.setFont(font);
	                PdfPCell cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(2);
	                table.addCell(cell);
	                cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	                cell.setPadding(5F);
	                cell.setPaddingLeft(5F);
	                cell.setRowspan(3);
	                cell.setColspan(2);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                
	                String ped = pedido.getIdPedido();
	                String pedCorto = "";
	                if(ped.length()>7)
	                {
	                	pedCorto = "M.L"+ped.substring(ped.length()-4);
	                }
	                else
	                {
	                	pedCorto = "WEB"+ped.substring(ped.length()-4);
	                }
	                
	                
	                
	             
	                PdfPCell cellcell = new PdfPCell(new Paragraph(pedCorto, fontPedido) );
	                cellcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cellcell.setColspan(2);
	                table.addCell(cellcell);
	                
	                frase = new Phrase("Pedido: "+pedido.getIdPedido()+"");
	                frase.setFont(fontCod);
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(2);
	                
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase("Cliente: "+pedido.getCliente()+""));
	                cell.setColspan(2);
	                table.addCell(cell);
	                
	                cell = new PdfPCell(new Phrase("Fecha: "+pedido.getFecha()+""));
	                cell.setColspan(2);
	                table.addCell(cell);
	                
	                
	                frase = new Phrase("Detalle del pedido");
	                frase.setFont(font);
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(2);
	                table.addCell(cell);
	                
	                
	                frase = new Phrase("Articulo");
	                frase.setFont(font);
	                cell = new PdfPCell(frase);
	                table.addCell(cell);
	                
	                frase = new Phrase("Cantidad");
	                frase.setFont(font);
	                cell = new PdfPCell(frase);
	                table.addCell(cell);
	                
	                for (DataArtPedidoPickup ar : pedido.getArticulos()) 
	                { 
	                	frase = new Phrase(ar.getIdArticulo());
		                frase.setFont(font);
		                cell = new PdfPCell(frase);
		                table.addCell(cell);
		                
		                frase = new Phrase(ar.getCant()+ "");
		                frase.setFont(font);
		                cell = new PdfPCell(frase);
		                table.addCell(cell);
					}
	                
	                
	                
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
	        
	        
	        
	         
	        
	        return pathHttp;
	    }
	
	
	
	public static String ImprimirPedidosPickingManual(List <DataPicking> pickings, int idEmpresa, int idPicking)
	        throws FileNotFoundException, DocumentException
	    {
	        String path = "";
	        String pathHttp = "";
	        
	        Logica l = new Logica();
	        
	        
	        String ins = "";
	        boolean pri = true;
	        for (DataPicking p : pickings) 
	        {
				if(pri)
				{
					ins+=p.getIdPedido();
					pri = false;
				}
				else
				{
					ins+=","+p.getIdPedido();
				}
			}
	        
	      
	        List pedidos = l.darListaDetallePedidosEcommercePrint(ins,idEmpresa);

	        
	        
	        
	        
	        
	        try
	        {
	        	
	        	String ruta ="";
	        	String rutaHttp ="";
	        	
	    		PropertiesHelper pH=new PropertiesHelper("paths");
	    		try
	    		{
	    			pH.loadProperties();
	    			ruta = pH.getValue("pdf");
	    			rutaHttp = pH.getValue("HTTP_pdf");
	    		}
	    		catch (Exception e) 
	    		{
					
				}
	    		
	    		pathHttp = rutaHttp+"/"+idPicking+".pdf";
	           
	            path = (new StringBuilder(String.valueOf(ruta))).append("/"+idPicking+".pdf").toString();
	            FileOutputStream archivo = new FileOutputStream(path);
	            
	            Document documento = new Document(PageSize.A4);
	            
	            Font titulos = FontFactory.getFont("Arial", 13F, 1);
	            
	            titulos.setStyle("bold");
	            
	            
	            
	            
	            
	            Font font = new Font(Font.FontFamily.COURIER, 9, Font.NORMAL);
	            
	            
	            
	            PdfWriter writer = PdfWriter.getInstance(documento, archivo);
	            documento.open();
	            documento.setMargins(5F, 5F, 0.0F, 0.0F);
	            com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
	            documento.newPage();
	            for(Iterator iterator = pedidos.iterator(); iterator.hasNext(); documento.newPage())
	            {
	            	DataDetallePedidoPrint pe = (DataDetallePedidoPrint)iterator.next();
	            	
	            	 Double ttlDescuento = 0.0;
		                int ttlUnidades = 0;
		                Double importeTTL = Double.parseDouble(pe.getImportePago());
		                String articulosDescuento = "";
		                for (DataDetallePedidoPrintArt a : pe.getArticulos()) 
		                {
		                	
		                	if(a.getPrecioImp()<0)
		                	{
		                		
		                		ttlDescuento+=a.getPrecioImp()*-1;
		                		articulosDescuento +="\r\n "+a.getIdArticulo();
		                	}
		                	else
		                	{
		                		ttlUnidades+=a.getCantidad();
		                	}
		                }
		                
		                
		                Double importeSinDescuento = ttlDescuento+importeTTL;
		                
		                
		                
		                Double porcentajeDescuento = (ttlDescuento*100)/importeSinDescuento;
		                BigDecimal bd = new BigDecimal(porcentajeDescuento);
		                bd = bd.setScale(2,BigDecimal.ROUND_HALF_UP);
		                
		                porcentajeDescuento = bd.doubleValue();
		                
		                Double porcentajePaga = 100-porcentajeDescuento;
	            	
	            	
	            	
	            	
	                Barcode128 code39 = new Barcode128();
	                code39.setCode(pe.getIdPedido()+"");
	                code39.setBarHeight(15F);
	                
	                
	                PdfPTable table = new PdfPTable(8);
	                table.setTotalWidth(592F);
	                table.setLockedWidth(true);
	                table.setWidths(new float[] {100F, 50F,100F,50F,70F,40F,50F,40F});
	                
	                
	                Phrase frase = new Phrase("Detalle de picking del pedido "+pe.getIdPedido()+" - Encuentra",titulos);
	                
	                
	                PdfPCell cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setPadding(5F);
	                cell.setColspan(8);
	                table.addCell(cell);
	                
	            
	                cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(8);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setPadding(5F);
	                table.addCell(cell);
	                
	                
	                
	                
	                
	                
	                frase = new Phrase("Datos del cliente",titulos);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(8);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setPadding(5F);
	                table.addCell(cell);
	                
	                frase = new Phrase("Nombre",font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                
	                table.addCell(cell);
	                
	                frase = new Phrase("CI/RUT",font);
	                
	                
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase("E-mail",font);
	                
	                cell = new PdfPCell(frase);
	                cell.setColspan(2);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase("telefono",font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase("direccion",font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(3);
	                
	                table.addCell(cell);
	                
	                
	                
	                frase = new Phrase(pe.getNombre()+" "+pe.getApellido(),font );
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                
	                table.addCell(cell);
	                
	                String ceRu = "";
	                if(pe.getCliRuc()!=null || !pe.getCliRuc().equals("0") || !pe.getCliRuc().equals("null"))
	                {
	                	ceRu = pe.getCliRuc();
	                }
	                else
	                {
	                	ceRu = pe.getCedula();
	                }
	                
	                frase = new Phrase(ceRu,font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase(pe.getMail(),font);
	                
	                cell = new PdfPCell(frase);
	                cell.setColspan(2);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase(pe.getTelefono(),font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase(pe.getDireccion(),font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(3);
	                table.addCell(cell);
	                
	                

	                frase = new Phrase("Facturacion / Picking",titulos);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                cell.setPadding(5F);
	                cell.setColspan(8);
	                table.addCell(cell);
	                
	                
	                
	                

	                frase = new Phrase("Barras",font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase("SKU",font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase("Descripcion",font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(3);
	                table.addCell(cell);
	                
	                frase = new Phrase("Cantidad",font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase("Descuento "+articulosDescuento,font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase("Precio",font);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	               
	                
	                
	                
	                
	                
	                for (DataDetallePedidoPrintArt a : pe.getArticulos()) 
	                {
	                	if(a.getPrecioImp()>0)
	                	{
	                		code39 = new Barcode128();
		 	                code39.setCode(a.getBarra());
		 	                code39.setBarHeight(12F);
		 	               
		 	                
		 	                
		 	                cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
			                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			                cell.setPadding(5F);
			                table.addCell(cell);
		                	
		                	frase = new Phrase(a.getIdArticulo(),font);
			                
			                
			                cell = new PdfPCell(frase);
			                
			                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			                table.addCell(cell);
			                
			                frase = new Phrase(a.getDescripcion(),font);
			                
			                cell = new PdfPCell(frase);
			                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			                cell.setColspan(3);
			                table.addCell(cell);
			                
			                frase = new Phrase(a.getCantidad()+"",font);
			                
			                cell = new PdfPCell(frase);
			                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			                table.addCell(cell);
			                
			                
			                
			                Double descuentoL = (porcentajeDescuento*a.getPrecioImp())/porcentajePaga;
			                BigDecimal bdL = new BigDecimal(descuentoL);
			                bdL = bdL.setScale(2,BigDecimal.ROUND_HALF_UP);
			                descuentoL = bdL.doubleValue();
			                
			                
			                frase = new Phrase(descuentoL*-1+"",font);
			                
			                cell = new PdfPCell(frase);
			                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			                table.addCell(cell);
			                
			                frase = new Phrase(a.getPrecioImp()+"",font);
			                
			                cell = new PdfPCell(frase);
			                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			                table.addCell(cell);
	                	}
	                	
					}
	                
	                if(!pe.getCostoEnvio().equals("0"))
	                {
	                	
		                
		                code39 = new Barcode128();
	 	                code39.setCode("ENVIO");
	 	                code39.setBarHeight(12F);
	 	                
	 	                
	 	                
	 	                cell = new PdfPCell(code39.createImageWithBarcode(cb, null, null));
		                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		                table.addCell(cell);
		                
		                
		                
		                
		                frase = new Phrase("ENVIO",font);
		                
		                cell = new PdfPCell(frase);
		                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		                table.addCell(cell);
		                
		                frase = new Phrase("Costo de Envio",font);
		                
		                cell = new PdfPCell(frase);
		                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		                cell.setColspan(3);
		                table.addCell(cell);
		                
		                frase = new Phrase("1",font);
		                
		                cell = new PdfPCell(frase);
		                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		                table.addCell(cell);
		                
		                frase = new Phrase("0.0",font);
		                
		                cell = new PdfPCell(frase);
		                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		                table.addCell(cell);
		                
		                frase = new Phrase(pe.getCostoEnvio(),font);
		                
		                cell = new PdfPCell(frase);
		                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		                table.addCell(cell);
	                }
	                
	                
	               
	                
	                
	                frase = new Phrase("Total",titulos);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(5);
	                table.addCell(cell);
	                
	                frase = new Phrase(ttlUnidades+"",titulos);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase(ttlDescuento+"",titulos);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                table.addCell(cell);
	                
	                frase = new Phrase(importeTTL+"",titulos);
	                
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
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
	        
	        
	        
	         
	        
	        return pathHttp;
	    }
	
	
}
