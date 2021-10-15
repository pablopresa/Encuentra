import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.DocumentException;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataIDDescripcion;


public class CambioEstadoFenicio 
{
	public static void main(String[] args) throws FileNotFoundException, DocumentException 
	{	
		Call_WS_analoga ws = new Call_WS_analoga();
		List<DataIDDescripcion> lista = new ArrayList<>();
		
		///Estados
		// preparando, preparado, listoretirar, entregado
		
		
		// IMPORTANTE SETEAR BIEN EL CANAL
		int canal = 7;
		
		lista.add(new DataIDDescripcion(1313558	,""));





		
		String jotason ="[";
		
		for(DataIDDescripcion i:lista)
		{
			jotason+=
					 "     { "+
					 "        \"id\":\""+i.getId()+"\", "+
					 "        \"estado\":\"listoretirar\", "+
					 "		  \"trackingID\":\""+i.getDescripcion()+"\" "+
					 "     },";			
		}
		jotason = jotason.substring(0,jotason.length()-1)+"]";
		ws.setPedidos(jotason,canal,2);
		
		System.out.println("Fin de cambios de estado.");
		
	}
	

	public CambioEstadoFenicio() {
	}
	
	
	
	
	
	
	

}
