package integraciones.erp.odoo.laIsla;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import beans.datatypes.DataIDDescripcion;

public class mianTest 
{
	public static void main(String[] args) 
	{
		//LaIsla li= new LaIsla();
		
		
		int idSession = -1;
		try 
		{
			//idSession = li.generarIDSession();
		} 
		catch (Exception e) {System.out.println("Error al generar id session");}
		
		//li.testRecepcion(793270);
				
		//li.cerrarSession(idSession);
		
		List<ArticuloCantidadEncuentra> artCantEnc = new ArrayList<>();
		 
		 ArticuloCantidadEncuentra ace = new ArticuloCantidadEncuentra(1.0, 0, "157196C-400");
		 
		 artCantEnc.add(ace);			 
		
		 
		 LaIsla li = new LaIsla();
		 
		
		 List<StockArticulos> stockOdoo = li.buscarStockArticulo(artCantEnc, new Hashtable<>());
		 
		 System.out.println("end");
		
	}

	
	
}
