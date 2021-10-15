package main.utilidades;

import java.util.ArrayList;
import java.util.List;

import beans.Usuario;
import beans.encuentra.DataLineaRepo;
import logica.Logica;

public class PonerEnReposicionArtElRey 
{
	public static void main(String[] args) 
	{
		
		Logica logica = new Logica();
	    
		Usuario u = logica.loginEncuentraSinEmpresa("ElREY", "SINPASS");
		
		
		List<DataLineaRepo> retorno = new ArrayList<>();
		
		retorno = logica.darArticulosQueNoEstanEnReposicionArt(4);
		
		logica.insertarEnReposArt(retorno, 4);
		
		
		System.out.println("pronto!");
			
	}
	

}
