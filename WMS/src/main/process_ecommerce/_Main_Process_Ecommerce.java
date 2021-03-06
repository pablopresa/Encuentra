package main.process_ecommerce;
import java.util.ArrayList;
import java.util.List;

import beans.Usuario;
import dataTypes.DataIDDescripcion;
import logica.Logica;


public class _Main_Process_Ecommerce 
{
	public static void main(String[] args) 
	{	
		
		
		Logica logica = new Logica();
		Usuario u = logica.loginEncuentraSinEmpresa("$admin", "SINPASS");
		
		int idEmpresa = u.getIdEmpresa();
		
		DataIDDescripcion D1 = new DataIDDescripcion(3, "");
		DataIDDescripcion D2 = new DataIDDescripcion(6, "");
		DataIDDescripcion D3 = new DataIDDescripcion(14, "");
		DataIDDescripcion D4 = new DataIDDescripcion(7, "");
		
		//List<DataIDDescripcion> destinosPickup = logica.darListaDataIdDescripcionMYSQLConsulta("SELECT idDeposito, '' FROM ecommerce_envioml WHERE PreparaPedidoPickup = 1");
		List<DataIDDescripcion> destinosPickup = new ArrayList<>();
		destinosPickup.add(D1);
		destinosPickup.add(D2);
		destinosPickup.add(D3);
		destinosPickup.add(D4);
		
		for (DataIDDescripcion d : destinosPickup) 
		{
			EcommerceProcessOrdersPickup.process(d.getId(),idEmpresa);
		}
	    
		EcommerceProcessOrders.process(null,new Long(0),null,0,idEmpresa);
	
	}
	
	public _Main_Process_Ecommerce() {
	}
	
	
	
	
	
	
	

}
