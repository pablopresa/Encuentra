package aTest;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.itextpdf.text.DocumentException;

import beans.encuentra.IPrint;
import dataTypes.DataArticuloEcommerceVerifR;
import dataTypes.DataIDDescripcion;

public class TestEtiPickupForus {
	

	public static void main(String[] args) {
		String salida = "";
		DataArticuloEcommerceVerifR daev = new DataArticuloEcommerceVerifR();
		daev.setCanal(2);
		daev.setCiCliente("50038352");
		daev.setIdPedido(123456L);
		daev.setIdEcommerce("123845278895");
		daev.setDescripcion("Martín Guerra");
		daev.setTelCliente("098123456");
		daev.setFecha("2021-02-05");
		
		DataIDDescripcion env = new DataIDDescripcion();
		env.setId(806);
		env.setDescripcion("CAT-Centro");
		env.setDescripcionB("18 DE JULIO 935");
		
		String direccionRemite = "CD 9000";
		String notaAlPie = "Av. Italia 4346 								 						 Tel.:2613 7566 		 Montevideo-Uruguay";
		int cantidad = 1;
		boolean pickup = true;
		String direccionDestino = "18 DE JULIO 935";
		String obs = "pruebas";
		
		try {
			salida = IPrint.ImprimirEtiquetasNuevas(daev, 2, env, direccionRemite, notaAlPie, cantidad, pickup, direccionDestino, obs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		System.out.println(salida);

	}

}
