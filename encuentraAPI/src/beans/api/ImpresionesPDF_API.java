

package beans.api;





import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.*;

import beans.datatypes.DataIDDescripcion;
import beans.encuentra.EncuentraPedido;
import beans.encuentra.EncuentraPedidoArticulo;
import beans.helper.PropertiesHelper;
import beans.helper.PropertiesHelperAPI;
import integraciones.erp.billerTata.TicketCambio;
import logica.LogicaAPI;

import java.awt.Graphics2D;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.*;
import java.util.List;




// Referenced classes of package logica:
//            LeerFicheroTexto

public class ImpresionesPDF_API
{
	private int idEmpresa;

	public ImpresionesPDF_API(int idEmpresa)
    {
		this.idEmpresa = idEmpresa;
    }
	
	

	
	public int getIdEmpresa() {
		return idEmpresa;
	}





	public void imprimirTicketMovStock(int origen, int destino, int idUsuario, String comentario,List<DataIDDescripcion> detalle, int idDoc, int destinoImpresion) 
	{

		String path = "";
        String pathHttp = "";
        String fecha = "";
        try {
			fecha = LogicaAPI.getDate().getDescripcion();
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        int aNumber = (int)((Math.random() * 9000000)+1000000);
        
        try
        {
        	String ruta ="";
        	String rutaHttp ="";
        	
    		PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
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
            
                
                PdfPTable table = new PdfPTable(2);
                table.setTotalWidth(220F);
                table.setLockedWidth(true);
                table.setWidths(new float[] {
                    5F, 3F
                });
                
                
                Phrase frase = new Phrase("Notificacion Encuentra");
                frase.setFont(font);
                PdfPCell cell = new PdfPCell(frase);
                
                                           
                PdfPCell cellcell = new PdfPCell(new Paragraph("MOV STOCK ", fontPedido) );
                cellcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellcell.setColspan(2);
                table.addCell(cellcell);
                
                frase = new Phrase("Documento: "+idDoc+"");
                frase.setFont(fontCod);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(2);
                
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Fecha: "+fecha+""));
                cell.setColspan(2);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Usuario: "+idUsuario+""));
                cell.setColspan(2);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Origen: "+origen+""));
                cell.setColspan(2);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Destino: "+destino+""));
                cell.setColspan(2);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Comentario: "+comentario+""));
                cell.setColspan(2);
                table.addCell(cell);
                
                
                frase = new Phrase("Detalle del movimiento");
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
                
                int cant = 0;
                
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
	                
	                frase = new Phrase(ar.getDescripcionC() );
	                frase.setFont(fontSmall);
	                cell = new PdfPCell(frase);
	                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	                cell.setColspan(3);
	                table.addCell(cell);
	                
	                cant += ar.getId();
				}
                
                frase = new Phrase("Unidades totales: "+cant);
                frase.setFont(font);
                cell = new PdfPCell(frase);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setColspan(2);
                table.addCell(cell);
                
                
                documento.add(table);
            

            documento.close();
            
            beans.api.json.PrintObject p = new beans.api.json.PrintObject();
			p.setId(aNumber+"");
			p.setUrl(pathHttp);
			p.setPorait(0+"");
			p.setPrinterId("2");
			p.setIdEquipo(destinoImpresion+"");
			
			Gson gson = new Gson();
			String json = gson.toJson(p);
            
			LogicaAPI.PutPrintSpooler(p,this.getIdEmpresa());
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
        
	public void ImprimirPedidosArticuloReq(List pedidos,int destinoImpresion) throws FileNotFoundException, DocumentException
	{
		String path = "";
		String pathHttp = "";

		int aNumber = (int)((Math.random() * 9000000)+1000000);

		try
		{

			String ruta ="";
			String rutaHttp ="";

			PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
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
				EncuentraPedido pedido = (EncuentraPedido)iterator.next();
				Barcode39 code39 = new Barcode39();
				code39.setCode(pedido.getIdPedido()+"");
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


				String ped = pedido.getIdPedido()+"";
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

				String nombre = "";
				try {
					nombre = "Cliente: "+pedido.getCliente().getNombre()+" "+pedido.getCliente().getApellido();
				} catch (Exception e) {
					// TODO: handle exception
				}
				cell = new PdfPCell(new Phrase(nombre));
				cell.setColspan(2);
				table.addCell(cell);
				
				String telefono = "";
				try {
					telefono = "Telefono: "+pedido.getCliente().getTelefono();
				} catch (Exception e) {
					// TODO: handle exception
				}
				cell = new PdfPCell(new Phrase(telefono));
				cell.setColspan(2);
				table.addCell(cell);
				
				String ci = "";
				try {
					ci = "Documento: "+pedido.getCliente().getDocumentoNro();
				} catch (Exception e) {
					// TODO: handle exception
				}
				cell = new PdfPCell(new Phrase(ci));
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

				for (EncuentraPedidoArticulo ar : pedido.getArticulosPedido()) 
				{ 
					frase = new Phrase(ar.getArticulo());
					frase.setFont(font);
					cell = new PdfPCell(frase);
					table.addCell(cell);

					frase = new Phrase(ar.getCantidad()+ "");
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



		beans.api.json.PrintObject p = new beans.api.json.PrintObject();
		p.setId(aNumber+"");
		p.setUrl(pathHttp);
		p.setPorait(0+"");
		p.setPrinterId("2");
		p.setIdEquipo(destinoImpresion+"");

		Gson gson = new Gson();
		String json = gson.toJson(p);

		LogicaAPI.PutPrintSpooler(p,this.getIdEmpresa()); 


	}


	public void ImprimirPedidosArticuloReq(EncuentraPedido pedido,int destinoImpresion) throws FileNotFoundException, DocumentException
	{
		String path = "";
		String pathHttp = "";

		int aNumber = (int)((Math.random() * 9000000)+1000000);

		try
		{

			String ruta ="";
			String rutaHttp ="";

			PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
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
			
				Barcode39 code39 = new Barcode39();
				code39.setCode(pedido.getIdPedido()+"");
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


				String ped = pedido.getIdPedido()+"";
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

				String nombre = "";
				try {
					nombre = "Cliente: "+pedido.getCliente().getNombre()+" "+pedido.getCliente().getApellido();
				} catch (Exception e) {
					// TODO: handle exception
				}
				cell = new PdfPCell(new Phrase(nombre));
				cell.setColspan(2);
				table.addCell(cell);
				
				String telefono = "";
				try {
					telefono = "Telefono: "+pedido.getCliente().getTelefono();
				} catch (Exception e) {
					// TODO: handle exception
				}
				cell = new PdfPCell(new Phrase(telefono));
				cell.setColspan(2);
				table.addCell(cell);
				
				String ci = "";
				try {
					ci = "Documento: "+pedido.getCliente().getDocumentoNro();
				} catch (Exception e) {
					// TODO: handle exception
				}
				cell = new PdfPCell(new Phrase(ci));
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

				for (EncuentraPedidoArticulo ar : pedido.getArticulosPedido()) 
				{ 
					frase = new Phrase(ar.getArticulo());
					frase.setFont(font);
					cell = new PdfPCell(frase);
					table.addCell(cell);

					frase = new Phrase(ar.getCantidad()+ "");
					frase.setFont(font);
					cell = new PdfPCell(frase);
					table.addCell(cell);
				}



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



		beans.api.json.PrintObject p = new beans.api.json.PrintObject();
		p.setId(aNumber+"");
		p.setUrl(pathHttp);
		p.setPorait(0+"");
		p.setPrinterId("2");
		p.setIdEquipo(destinoImpresion+"");

		Gson gson = new Gson();
		String json = gson.toJson(p);

		LogicaAPI.PutPrintSpooler(p,this.getIdEmpresa()); 


	}



	
	public void ImprimirTicketCambioBAS(List tikets,int destinoImpresion, Long idPedido, int printer) throws FileNotFoundException, DocumentException
	{
		String path = "";
		String pathHttp = "";

		

		try
		{

			String ruta ="";
			String rutaHttp ="";
			String t_Cambio_Legal = "";
			String t_Cambio_t1 = "";
			String t_Cambio_t2 = "";
			String t_Cambio_t3 = "";
			String t_Cambio_t4 = "";
			

			PropertiesHelperAPI pH=new PropertiesHelperAPI("paths");
			PropertiesHelperAPI pHB=new PropertiesHelperAPI("BAS");
			
			try
			{
				pH.loadProperties();
				pHB.loadProperties();
				ruta = pH.getValue("pdf");
				rutaHttp = pH.getValue("HTTP_pdf");
				t_Cambio_Legal = pHB.getValue("T_Cambio_Legal");
				t_Cambio_t1 = pHB.getValue("T_Cambio_t1");
				t_Cambio_t2 = pHB.getValue("T_Cambio_t2");
				t_Cambio_t3 = pHB.getValue("T_Cambio_t3");
				t_Cambio_t4 = pHB.getValue("T_Cambio_t4");
				destinoImpresion = Integer.parseInt(pHB.getValue("impresoraTicket"));
				
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			pathHttp = rutaHttp+"/rc"+idPedido+".pdf";
			
			
			System.out.println("antes img");
	    	String logo = pH.getValue("pdf")+idEmpresa+".png";
			

			path = (new StringBuilder(String.valueOf(ruta))).append("/rc"+idPedido+".pdf").toString();
			FileOutputStream archivo = new FileOutputStream(path);
			Rectangle pageSize = new Rectangle(230F, 350F);
			
			Document documento = new Document(pageSize);
			Font font = FontFactory.getFont("Arial", 8F, 1);
			Font fontW = FontFactory.getFont("Arial", 8F, 1);
			fontW.setColor(BaseColor.WHITE);
			
			Font fontCod = FontFactory.getFont("Arial", 11F, 1);
			fontCod.setStyle("bold");

			Font fontSKU = FontFactory.getFont("Arial", 10F, 1);
			
			PdfWriter writer = PdfWriter.getInstance(documento, archivo);
			documento.open();
			documento.setMargins(1F, 1F, 0.0F, 0.0F);
			com.itextpdf.text.pdf.PdfContentByte cb = writer.getDirectContent();
			documento.newPage();
			for(Iterator iterator = tikets.iterator(); iterator.hasNext();)
			{
				TicketCambio ticket = (TicketCambio)iterator.next();
				
				for (int i = 0; i < ticket.getQty(); i++) 
				{
					documento.newPage();
					PdfPTable table = new PdfPTable(3);
					
					table.setTotalWidth(228F);
					
					PdfPCell cell = new PdfPCell(new Paragraph("\r\n",fontCod));
					cell.setColspan(3);
					cell.setBorder(0);
					table.addCell(cell);
					
					
					//la imagen ocupa 3 celdas (columna)
					Image imagenTienda = null;
	             	try 
	             	{
	             		imagenTienda = Image.getInstance(logo);
					} 
	             	catch (MalformedURLException e) {e.printStackTrace();} 
	             	catch (IOException e) {e.printStackTrace();	}
				        										
				   imagenTienda.scaleToFit(180, 180);	
				   cell = new PdfPCell(new PdfPCell(imagenTienda));
				   cell.setPaddingTop(5F);
				   cell.setPaddingLeft(3F);
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
					

				   //agrego una celda en blanco
				   Phrase frase = new Phrase("");
				   frase.setFont(font);
				   cell = new PdfPCell(frase);
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setBorder(0);
				   table.addCell(cell);

				   
				   //local
				   cell = new PdfPCell(new Paragraph(t_Cambio_t1,fontW));
				   cell.setBackgroundColor(BaseColor.BLACK);
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setColspan(2);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   //agrego una celda en blanco
				   frase = new Phrase("");
				   frase.setFont(font);
				   cell = new PdfPCell(frase);
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setBorder(0);
				   table.addCell(cell);

				   
				   //direccion
				   cell = new PdfPCell(new Paragraph(t_Cambio_t2,font));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setColspan(2);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				 //agrego una celda en blanco
				   frase = new Phrase("");
				   frase.setFont(font);
				   cell = new PdfPCell(frase);
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setBorder(0);
				   table.addCell(cell);

				   
				   //telefono
				   cell = new PdfPCell(new Paragraph(t_Cambio_t3,font));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setColspan(2);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   //agrego una celda en blanco
				   frase = new Phrase("");
				   frase.setFont(font);
				   cell = new PdfPCell(frase);
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setBorder(0);
				   table.addCell(cell);

				   
				   //ata
				   cell = new PdfPCell(new Paragraph(t_Cambio_t4,font));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setColspan(2);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   //ticket de cambio
				   cell = new PdfPCell(new Paragraph("CUPON DE CAMBIO",fontCod));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   //vendedor
				   cell = new PdfPCell(new Paragraph("VENDEDOR "+ticket.getVendedor(),font));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   //aRTICULO Y DESC
				   cell = new PdfPCell(new Paragraph(ticket.getSku(),fontSKU));
				   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   cell = new PdfPCell(new Paragraph("\r\n",fontCod));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   
				   cell = new PdfPCell(new Paragraph("Pedido WEB",fontW));
				   cell.setBackgroundColor(BaseColor.BLACK);
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   cell = new PdfPCell(new Paragraph("Fecha Hora",fontW));
				   cell.setBackgroundColor(BaseColor.BLACK);
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   cell = new PdfPCell(new Paragraph("Caja",fontW));
				   cell.setBackgroundColor(BaseColor.BLACK);
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   //pedido
				   cell = new PdfPCell(new Paragraph(ticket.getNtrans(),fontSKU));
				   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				   cell.setBorder(0);
				   table.addCell(cell);
				   //fecha hora
				   cell = new PdfPCell(new Paragraph(ticket.getFecha_hora(),fontSKU));
				   cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   //fecha hora
				   cell = new PdfPCell(new Paragraph(ticket.getCaja(),fontSKU));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   cell = new PdfPCell(new Paragraph("\r\n",fontCod));
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   cell = new PdfPCell(new Paragraph("\r\n",fontCod));
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   //disfruta de lo simple
				   cell = new PdfPCell(new Paragraph("DISFRUT? DE LO SIMPLE",fontCod));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   cell = new PdfPCell(new Paragraph("\r\n",fontCod));
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				  
				   
				   //legal
				   cell = new PdfPCell(new Paragraph(t_Cambio_Legal,font));
				   cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				   cell.setColspan(3);
				   cell.setBorder(0);
				   table.addCell(cell);
				   
				   
				   documento.add(table);
				} //for sobre la cantidad
				
				
			}//for sobre el sku

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



		beans.api.json.PrintObject p = new beans.api.json.PrintObject();
		p.setId("tc"+idPedido);
		p.setUrl(pathHttp);
		p.setPorait(0+"");
		p.setPrinterId(destinoImpresion+"");
		p.setIdEquipo(printer+"");

		Gson gson = new Gson();
		String json = gson.toJson(p);

		LogicaAPI.PutPrintSpooler(p,this.getIdEmpresa()); 


	}














}
