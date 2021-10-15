package main.utilidades;

import java.util.ArrayList;
import java.util.List;

import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import dataTypes.DataIDDescripcion;
import logica.Logica;

public class PonerEnEcPedArtForusUY 
{
	public static void main(String[] args) 
	{
		
		Logica logica = new Logica();
	    
		int idEmpresa = 2;
		//Usuario u = logica.loginEncuentra("ElREY", "SINPASS", idEmpresa);
		Usuario u = logica.loginEncuentra("Super1", "Super", idEmpresa);
		
		
		List<DataIDDescripcion> retorno = new ArrayList<>();
		
		retorno = logica.darArticulosQueNoEstanEnEcPedArt(idEmpresa);
		retorno.remove(0);
		
		logica.insertarEnEcPedArt(retorno, idEmpresa);
		
		
		System.out.println("pronto!");
			
	}
	

}
