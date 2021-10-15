package main;

import java.util.List;

import beans.Usuario;
import dataTypes.DataIDDescripcion;
import logica.Logica;

public class SincTarjetas 
{
	public static void main(String[] args) 
	{
		
		Logica logica = new Logica();
		Usuario u = logica.loginEncuentraSinEmpresa("Encuentra", "Forus!#$");
		
		int idEmpresa = u.getIdEmpresa();
		List<DataIDDescripcion> grupos = logica.darListaDataIdDescripcionConsulMYSQL ("select  IdGrupo ,'' from tarjetas_grupos where idEmpresa="+idEmpresa+" group by idGrupo");
		
		for (DataIDDescripcion grupo : grupos) 
		{
			int idGrupo = grupo.getId();
			//logica.encuentraUpdateTarjetas(idGrupo,idEmpresa);
			
		}
			
	}
	

}
