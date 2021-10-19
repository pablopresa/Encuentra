import java.util.Hashtable;
import java.util.List;

import beans.Usuario;
import logica.Logica;
import main.utilidades.PonerEnEcPedArtForusUY;
import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataIDDescripcion;
import dataTypes.trackingPedido;
import eCommerce_jsonObjectsII.Cliente;


public class TestClienteAnaloga_resincCanal 
{
	public static void main(String[] args) 
	{	
		Logica logica = new Logica();
		Usuario u = logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");		
		int idEmpresa = u.getIdEmpresa();		
		
		List<DataIDDescripcion> canalesFenicioALL = logica.darListaDataIdDescripcionMYSQLConsulta("select id,nombre from ecommerce_canal_ml where id NOT IN (0,8,9,10,11,12)  AND idEmpresa="+idEmpresa);
		
		canalesFenicioALL.remove(0);
	
					
		 
		
		Call_WS_analoga call = new Call_WS_analoga(); 
		Hashtable<String, DataIDDescripcion> pedidos;
		pedidos = logica.darPedidosPorCanalFenicio(0,idEmpresa);
		System.out.println(pedidos.size());
		if(pedidos.size()>0) {
			for (DataIDDescripcion c:canalesFenicioALL)
			{					
				call.ReSetCanal(pedidos, c.getId(),idEmpresa);
			}
		}
		
				
	}	


	
	public TestClienteAnaloga_resincCanal() {
	}
	
	
	
	
	
	
	

}
