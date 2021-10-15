import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import persistencia.MSSQL;




public class Fotos {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Calendar c = Calendar.getInstance();
		
		
		 Calendar cal1 = Calendar.getInstance();
		 int mes = cal1.get(Calendar.MONTH);
		 mes +=1;
		 String mesSTR;
		 
		 if(mes<10)
		 {
			 mesSTR = "0"+mes;
		 }
		 else
		 {
			 mesSTR = String.valueOf(mes);
		 }
		 
		 
		 int dia = cal1.get(Calendar.DATE);
		 String diaSTR;
		 if(dia<10)
		 {
			 diaSTR = "0"+dia;
		 }
		 else
		 {
			 diaSTR = String.valueOf(dia);
		 }
		 
		
		 
		 int anio = cal1.get(Calendar.YEAR);
		 
		String fechaSQL = "'"+anio+"-"+diaSTR+"-"+mesSTR+" 00:00:00'";
		     
		String path = "Z:/";
		File directorio = new File(path);
		String [] ficheros = directorio.list();
		String line;
		for (int i = 0; i < ficheros.length; i++) {
		    try {
		        BufferedReader br = new BufferedReader(new FileReader(path + ficheros[i]));
		        System.out.println("Contenido del fichero " + ficheros[i]);
		       
			    
				String articulo = ficheros[i].substring(0,13);
				System.out.println("art "+articulo);
				
				
				 String consulta = "update Articulo  set FecModif = "+fechaSQL+" where IdArticulo like '"+articulo+"%';";
				 
				 System.out.println(consulta);
				 
				try 
				{
					MSSQL.persistir(consulta);
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

	}

}
