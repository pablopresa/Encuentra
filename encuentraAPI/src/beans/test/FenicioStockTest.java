package beans.test;

import com.google.gson.Gson;

import endpoints.productosFenicio;
import integraciones.marketplaces.fenicio.apiComercios.RespuestaFenicio;

public class FenicioStockTest {
	public static void main(String[] args) {
		productosFenicio pf = new productosFenicio();
		Gson gson = new Gson();
		RespuestaFenicio rsp;
		String retorno;
		boolean entrada= true;
		
		int min = 0;
		int max = 0;
		int total = 0;
		String token = "OqeP491Y7iYLLRxOcgdhc7Gfj5P4c7nG";
		String json = "";
		while(entrada) {
			try {
				min = max;
				max = max+1000;
				json = "{\r\n" + 
					"    \"total\": "+max+",\r\n" + 
					"    \"desde\": "+min+"\r\n" + 
					"}";
				
				retorno = pf.put(json, token);
				rsp = gson.fromJson(retorno, RespuestaFenicio.class);
				total += rsp.getData().getProductos().length; 
				if(rsp.getData().getProductos().length ==0) {
					entrada = false;
				}
			} catch (Exception e) {
				entrada = false;
			}
			System.out.println("***posicion*** "+max);
			System.out.println("***TOTAL*** "+total);
			System.out.println("");			
		}
		
		
	}
}
