package logica;

/**
 * @file LeerFichero.java
 * @version 1.1
 * @author Linea de Codigo (http://lineadecodigo.com)
 * @date   29-abril-2006
 * @url    http://lineadecodigo.com/2006/12/26/leer-fichero-de-texto-con-java/
 * @descriptio Lectura del contenido de un fichero de texto
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerFicheroRecorrido 
{

	public static void LeerProperties(String nombrePropiedad) 
	{
		Logica Logica = new Logica();
		
		try {
		
			String path = "C:/Program Files/Reclamos/";
			int idEmpresa = 2;
			FileReader fr = new FileReader(path+nombrePropiedad);
			BufferedReader bf = new BufferedReader(fr);
			String sCadena;
			while ((sCadena = bf.readLine())!=null) 
			{
			
				String [] values = sCadena.split(";");
				
				String reco  = values[0];
				String idOjo = values[1];
				
					String consulta = "UPDATE `ojos` SET `IdRecorrido`="+reco+" WHERE IdEmpresa="+idEmpresa+" and `IdOjo` = '"+idOjo+"' LIMIT 1;";
					
										
					try
					{
						
						Logica.persistir(consulta);
						
						
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					
				System.out.println(sCadena);
			} 
				
			 
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}

		
	}

}
