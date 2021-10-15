package beans.api;

import java.text.Normalizer;

public class Utilidades 
{
	public Utilidades(){}

	public String validarComillas(String texto) {
		if(texto.contains("\'")) {
			texto = texto.replace('\'',' ');
		}
		if(texto.contains("\"")) {
			texto = texto.replace('\"',' ');
		}
		return texto;
	}
	
	public int tryParse (String in)
	{
		int retorno = 0;
		try
		{
			retorno = Integer.parseInt(in);
		}
		catch (Exception e) 
		{
			
		}
		
		return retorno;
	}

	public String limpiarS (String in)
	{
		String cadenaNormalize = Normalizer.normalize(in, Normalizer.Form.NFD);   
		String cadenaSinAcentos = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
		
		return cadenaSinAcentos;
	}
	
	
	public String soloNumeros(String cadena) {
		String salida = "";
		try {
			salida = cadena.replaceAll("[A-Za-z]", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

}
