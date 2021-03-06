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


public class TestClienteAnaloga 
{
	public static void main(String[] args) 
	{	
		Logica logica = new Logica();
		Usuario u = logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");		
		int idEmpresa = u.getIdEmpresa();		
		
		List<DataIDDescripcion> canalesFenicioALL = logica.darListaDataIdDescripcionMYSQLConsulta("select id,nombre from ecommerce_canal_ml where id NOT IN (0,8,9,10,11,12)  AND idEmpresa="+idEmpresa);
		//List<DataIDDescripcion> canalesFenicioALL = logica.darListaDataIdDescripcionMYSQLConsulta("select id,nombre from ecommerce_canal_ml where id IN (5)  AND idEmpresa="+idEmpresa);
		
		canalesFenicioALL.remove(0);
	
					
		/*pedidos web*/ 
		
		Hashtable<String, DataIDDescripcion> destinoPedidos = new Hashtable<>();
		Call_WS_analoga call = new Call_WS_analoga(); 
		
		for (DataIDDescripcion c:canalesFenicioALL)
		{
			destinoPedidos = call.DestinoPedidos(c.getId(), 1, idEmpresa,destinoPedidos);
		}
 		
		Hashtable<String, DataIDDescripcion> pedidos;
		
	    call.getPedidos(idEmpresa,destinoPedidos);
	   
	    /*
	    Call_WS_MODDO moddo = new Call_WS_MODDO();
	    String token = moddo.getToken();
	    moddo.getPedidos(token, "[1,39]", 336);
	    */
		
	    
	    		
		//Hashtable<String, DataIDDescripcion> pedidos;
		
		
		pedidos = logica.darPedidosPorCanalFenicio(0,idEmpresa);
		System.out.println(pedidos.size());
		if(pedidos.size()>0) {
			for (DataIDDescripcion c:canalesFenicioALL)
			{					
				//call.ReSetCanal(pedidos, c.getId(),idEmpresa);
			}
		}
		
		
		List<DataIDDescripcion> canalesFenicio = logica.canalesActivosEC(idEmpresa);
		canalesFenicio.remove(0);		
		for (DataIDDescripcion c:canalesFenicio)
		{	
			pedidos = logica.darPedidosPorCanalFenicio(c.getId(),idEmpresa);							
			call.SetEtiquetas(pedidos, c.getId(),idEmpresa,destinoPedidos);					
		}
		
		//poner en ec pedArt
		// EcPedAr = new PonerEnEcPedArtForusUY();
		//EcPedAr.main(null);
		
		/*
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		StringBuilder sb = new StringBuilder();
		List<trackingPedido> trackings = logica.PedidosATrackear();
		for(trackingPedido t:trackings) {
			DataIDDescripcion track = api.getTracking(t, idEmpresa);
			if(track != null && track.getDescripcion() != null) {
				if(track.getDescripcion().toUpperCase().equals("ENTREGADO")) {
					String jotason="[ "+
							 "     { "+
							 "        \"id\":\""+t.getIdEcommerce()+"\", "+
							 "        \"estado\":\"entregado\" "+
							 "     } "+
							 "]";
					call.setPedidos(jotason, t.getCanal(), idEmpresa);
					logica.updateEcommerceEstado(t.getIdPedido(), 6, idEmpresa, u.getNumero());
				}
			}
			
		}
		
		
		TestEstadoPedidos estadosFenicio = new TestEstadoPedidos();
		estadosFenicio.main(null);
	*/
		TestEstadoPedidos estadosFenicio = new TestEstadoPedidos();
		estadosFenicio.main(null);
		
	}	


	
	public TestClienteAnaloga() {
	}
	
	
	
	
	
	
	

}
