package integraciones.erp.odoo.laIsla;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import com.google.gson.Gson;

import beans.datatypes.DataIDDescripcion;
import beans.datatypes.StockDeposito;
import integraciones.wms.Call_WS_APIENCUENTRA;
import logica.LogicaAPI;

public class Tests {

	String[] nombresMetodos = {"almacenes", "categorias","marcas",
			"talles","colores","temporadas","generos","productos",
			"stockProductos"};
	
	public static void main(String[] args) {
		Call_WS_APIENCUENTRA cwa = new Call_WS_APIENCUENTRA();
		SincroLaIsla sci = new SincroLaIsla();
		LogicaAPI logica = new LogicaAPI();
		String token = logica.darToken(6,66666);
		LaIsla li = new LaIsla();
		
		/*List<Integer> ids = Arrays.asList(new Integer[] {815007});
		o.verEstadoAlbaranes(ids);*/
		
		li.obtenerIDArticuloOdoo("0D3HY28-380");
		
		
		 Hashtable<String, ArticuloCantidadEncuentra> hashartCantEnc = new Hashtable<>();
		 
			 ArticuloCantidadEncuentra ace = new ArticuloCantidadEncuentra(1.0, 0, "BUTKG1-49-U");
			 
			 hashartCantEnc.put("BUTKG1-49-U", ace);
		 
		
		 List<StockArticulos> stockOdoo = li.buscarStockArticulo
				 (new ArrayList<>(hashartCantEnc.values()),new Hashtable<>());
		
		
		//List<DataIDDescripcion> dids = sci.obtenerDatosGenericos("productos");
		//List<DTO_Articulo> dids = sci.obtenerDatosArticulos();
		List<StockDeposito> dids = sci.obtenerStockArticulos();
		
		//List<Remito> remito = sci.albaranesListosParaRecibir();
		
		Gson gson = new Gson();
		String resultado = gson.toJson(dids);
		System.out.println(dids);
		System.out.println(resultado);
		//CrearArchivo ca = new CrearArchivo();
		//ca.imprimirJson(resultado);
		//4439876898
		//List<Long> ids = new ArrayList<>();
		//ids.add(4439876898L);
		//LaIsla.putMovimientosStocksATransito(6);
		//LaIsla.putMovimientosStocksAEncuentra(6);
	}

}
