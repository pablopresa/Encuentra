package aTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.Usuario;
import beans.bulto;
import beans.bultoContenido;
import beans.encuentra.DataLineaRepo;
import dataTypes.DataIDDescripcion;
import logica.ImpresionesPDF;
import logica.Logica;
import logica.Utilidades;

public class GenerarTicketPicking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//List<DataIDDescripcion> lista = Arrays.asList(new DataIDDescripcion[] {new DataIDDescripcion(122,"47720")});

		Logica Logica = new Logica(); 
		Utilidades u = new Utilidades();
		Usuario us = new Usuario();
		us.setNumero(1119);
		us.setIdEmpresa(5);
		List<DataIDDescripcion> remitoEC = Logica.remitoEc(72326, 8);
		ImpresionesPDF.imprimirTicketMovStock(1, 1000, "***", 
				"Remito del picking "+72326, 
				remitoEC, 72326+"",1, 1,8, 1);
	}

}
