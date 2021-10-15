package aTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataLineaRepo;
import dataTypes.DataIDDescripcion;
import logica.Logica;
import logica.Utilidades;

public class GenerarEtiquetasVerificacion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<DataIDDescripcion> lista = Arrays.asList(new DataIDDescripcion[] {new DataIDDescripcion(121,"61008")});

		Logica Logica = new Logica(); 
		Utilidades u = new Utilidades();
		Usuario us = new Usuario();
		us.setNumero(1119);
		us.setIdEmpresa(5);
		us.setIdEquipo(99);
		//List<bulto> bultos = Logica.darBultosToPrintPicking(46669,5); 
		/*for (bulto b : bultos) 
		{
			//u.imprimirEtiqueta(b, b.getUnidades(), 5);
		}*/
				
		for(DataIDDescripcion l: lista)
		{
			
			List<DataLineaRepo> data = Logica.encuentraDarArtRepos(Integer.parseInt(l.getDescripcion()), "",5, l.getId());
			u.etiquetas_verificacion_destinos(data,us,Integer.parseInt(l.getDescripcion()),5,"");
			
			
			
		}
	}

}
