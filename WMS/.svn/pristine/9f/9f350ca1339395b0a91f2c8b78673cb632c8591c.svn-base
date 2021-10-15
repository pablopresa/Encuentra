package logica;
import java.io.*;
public class LeerHTML 
{
	
	public LeerHTML() {	}


	public String leer(String nombre)

	//El parametro nombre indica el nombre del archivo por ejemplo "prueba.txt" 

	{

	try
	{

		File f;
		FileReader lectorArchivo;
	
		//Creamos el objeto del archivo que vamos a leer
		f = new File(nombre);
	
		//Creamos el objeto FileReader que abrira el flujo(Stream) de datos para realizar la lectura
		lectorArchivo = new FileReader(f);
	
		//Creamos un lector en buffer para recopilar datos a travez del flujo "lectorArchivo" que hemos creado
		BufferedReader br = new BufferedReader(lectorArchivo);
	
		String l="";
		//Esta variable "l" la utilizamos para guardar mas adelante toda la lectura del archivo
	
		String aux="";/*variable auxiliar*/
		
		String numero = "A190";
		while(true)
		//este ciclo while se usa para repetir el proceso de lectura, ya que se lee solo 1 linea de texto a la vez
		{
			aux=br.readLine();
			//leemos una linea de texto y la guardamos en la variable auxiliar
			if(aux!=null)
			{
				if(aux.contains("'>"+numero))
				{
					
					int largo = aux.length();
					System.out.println(largo);
					aux = aux.substring(0,largo-12);
					aux += "; background-color:red;' title='Ubicación "+numero+" lugares disponibles 0 de 60'>"+numero+"</td>";
					System.out.println(aux);
				}
				l=l+aux;
				/*si la variable aux tiene datos se va acumulando en la variable l,
				* en caso de ser nula quiere decir que ya nos hemos leido todo
				* el archivo de texto*/
			}
			
			else
			{
				break;
			}
		}
	
			br.close();
	
			lectorArchivo.close();
	
			return l;
	
			}catch(IOException e){
			System.out.println("Error:"+e.getMessage());
			}
		return null;
		}

}
