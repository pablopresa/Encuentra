import java.util.List;

import beans.Usuario;
import logica.Logica;
import logica.Utilidades;
import cliente_rest_Invoke.Call_WS_analoga;
import dataTypes.DataIDDescripcion;


public class TestEstadoPedidos 
{
	public static void main(String[] args) 
	{	
		Logica logica = new Logica();
		Usuario u = logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");
		int idEmpresa = u.getIdEmpresa();
					
		/*pedidos web*/ 
		Call_WS_analoga call = new Call_WS_analoga(); 

		List<DataIDDescripcion> canalesFenicio = logica.darListaDataIdDescripcionMYSQLConsulta("select id,nombre from ecommerce_canal_ml where id not in (0,8,9,10,11,12) AND idEmpresa="+idEmpresa);
		//List<DataIDDescripcion> canalesFenicio = logica.darListaDataIdDescripcionMYSQLConsulta("select id,nombre from ecommerce_canal_ml where id in (6) AND idEmpresa="+idEmpresa);

		
		canalesFenicio.remove(0);
		
		for (DataIDDescripcion c:canalesFenicio){
			System.out.println(" CANAL "+c.getDescripcion().toUpperCase());
			System.out.println(" ESTADO PREPARADO");
			call.PedidosPorEstado("preparado",c.getId(),1,idEmpresa,3,u);	
		}
		
		for (DataIDDescripcion c:canalesFenicio){
			System.out.println(" CANAL "+c.getDescripcion().toUpperCase());
			System.out.println(" ESTADO listoretirar");
			call.PedidosPorEstado("listoretirar",c.getId(),1,idEmpresa,5,u);	
		}
		
		for (DataIDDescripcion c:canalesFenicio){
			System.out.println(" CANAL "+c.getDescripcion().toUpperCase());
			System.out.println(" ESTADO despachado");
			call.PedidosPorEstado("despachado",c.getId(),1,idEmpresa,4,u);	
		}
		
		
		for (DataIDDescripcion c:canalesFenicio){
			System.out.println(" CANAL "+c.getDescripcion().toUpperCase());
			System.out.println(" ESTADO entregado");
			call.PedidosPorEstado("entregado",c.getId(),1,idEmpresa,6,u);	
		}
	    	
		
	
	}
	

	public TestEstadoPedidos() {
	}
	
	
	
	
	
	
	

}
