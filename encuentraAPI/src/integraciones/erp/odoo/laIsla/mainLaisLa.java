package integraciones.erp.odoo.laIsla;

import java.util.Hashtable;

import beans.api.pedidoFactura;

public class mainLaisLa 
{
	public static void main(String[] args) 
	{
		LaIsla li= new LaIsla();
		
		//li.testRecepcion(0);
		int idSession = -1;
		
		while (true) {
			try 
			{
				idSession = li.generarIDSession();
			} 
			catch (Exception e) {System.out.println("Error al generar id session");}
					
			
			String msj = "";
			String idMail = "";
			int idEmpresa = 6;
			
			
			try {
				li.putMovimientosStocksATransito(idEmpresa);
			} 
			catch (Exception e) {
				System.out.println("Error grabando movimientos de stock");
			}
			  
			try {				
				li.putMovimientosStocksAEncuentra(idEmpresa);
			}
			catch (Exception e) 
			{
				System.out.println("Error transfiriendo albaranes a encuentra");
			}
			
			try {
				Hashtable<Integer,pedidoFactura> pedidos = li.putFacturacion(idEmpresa, idSession);
				System.out.println("");
				System.out.println("COMUNICANDO FACTURAS A WMS...");
				li.BuscarFacturas(pedidos);
			} catch (Exception e) {System.out.println("Error grabando facturas");}

			try {
				if(!msj.equals("")) {
					//MailErrores(idMail, msj);
				}				
			} catch (Exception e) {System.out.println("Error enviando mail de errores");}
			
			try 
			{
				System.out.println("sleep...");
				Thread.sleep(60000);
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
}
