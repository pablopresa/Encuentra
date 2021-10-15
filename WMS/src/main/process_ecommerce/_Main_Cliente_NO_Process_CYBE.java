package main.process_ecommerce;

import java.util.List;

import beans.Usuario;


import logica.Logica;

import dataTypes.DataIDDescDescripcion;



public class _Main_Cliente_NO_Process_CYBE 
{
	public static void main(String[] args) 
	{	
		Logica logica = new Logica();
		Usuario u = logica.loginEncuentraSinEmpresa("$admin", "SINPASS");
		
		int idEmpresa = u.getIdEmpresa();
		
		System.out.println("Iniciando sincronizacion Encuentra");
		
		logica.logger(0, 100, "Iniciando sincronizacion Encuentra ",idEmpresa);
		
		
		Call_WS_meli ws = new Call_WS_meli();
		
		List<DataIDDescDescripcion> canales = logica.EcommercedarCanalesML(idEmpresa);
		
		for (DataIDDescDescripcion c : canales) 
		{
			String usr = c.getDesc();
			String secret = c.getDescripcion();
			String access_token= ws.getToken(usr,secret);
			ws.getPedidosML(access_token,c.getId(),c.getDescII(),idEmpresa,u);
		}
		
			
		/*pedidos web*/
		Call_WS_analoga call = new Call_WS_analoga(); 
		
	    call.getPedidos(-1, idEmpresa);
	    call.getPedidos(0,idEmpresa);				
		
	    
	
	}
	
	
	public _Main_Cliente_NO_Process_CYBE() 
	{
	}
	
	
	
	
	
	
	

}
