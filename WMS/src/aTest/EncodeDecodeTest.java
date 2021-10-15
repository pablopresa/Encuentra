package aTest;

import logica.Utilidades;

public class EncodeDecodeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pdf = "//meli.ues.com.uy:9443/UES_Paqueteria/imprimir_etiqueta?id=2066267&numero=UES1623773655416&env=1839307&cli=9032";
		String out = "C:\\Program Files\\apache-tomcat-7.0.64\\webapps\\WMS\\pdf\\rpENCDEC.pdf";
		Utilidades u = new Utilidades();
		
		byte[] encoded = u.encodeFile(pdf);
		String pdfInBase64 = new String(encoded);
		byte[] decoded = u.decodeFile(encoded);
		u.writeToFile(out, decoded);
	}

}
