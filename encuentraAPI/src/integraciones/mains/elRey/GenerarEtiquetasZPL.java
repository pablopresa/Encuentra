package integraciones.mains.elRey;

import java.util.ArrayList;
import java.util.List;

import beans.datatypes.DataIDDescripcion;
import logica.LogicaAPI;

public class GenerarEtiquetasZPL {

	public GenerarEtiquetasZPL() {
	}

	public static void main(String[] args) {
		
		List <DataIDDescripcion> lista = new ArrayList<>();
		LogicaAPI logica = new logica.LogicaAPI();
		lista = logica.darEtiquetasZPL("2021-05-17 00:00:00");
		
		for (DataIDDescripcion data: lista)
		{
			String pedido = String.valueOf(data.getIdLong());
			String urlE = SinPedidosProducteca.downloadPDFZPL("http://api.labelary.com/v1/printers/8dpmm/labels/4x6/0/", data.getDescripcion(), pedido);
		}
		System.out.println("termine");
	}

}
