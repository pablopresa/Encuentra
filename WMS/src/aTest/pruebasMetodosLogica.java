package aTest;

import java.util.List;

import com.google.gson.Gson;

import beans.reportes.StockEncuentraVisual;
import logica.Logica;

public class pruebasMetodosLogica {

	public static void main(String[] args) {
		
		Logica log = new Logica();
		//String deposito, String articulo, int idEmpresa
		List<StockEncuentraVisual> pruebas = log.darListaStockEncuentraVisual("9000", "2,A",2);
		Gson gson = new Gson();
		CrearArchivo ca = new CrearArchivo();
		ca.imprimirJson(gson.toJson(pruebas));
		System.out.println(gson.toJson(pruebas));
	}

}
