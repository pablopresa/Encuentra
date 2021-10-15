package main;

import beans.Usuario;
import logica.Logica;

public class SincReposicion 
{
	public static void main(String[] args) 
	{
	
		TestUnit tu = new TestUnit();
		Logica Logica = new Logica();
		Usuario u = Logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");
		
		int idEmpresa = u.getIdEmpresa();
		tu.Sincronizar(true,idEmpresa);		
	}
	

}
