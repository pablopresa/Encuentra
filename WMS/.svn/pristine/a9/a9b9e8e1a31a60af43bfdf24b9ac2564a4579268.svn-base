package logica;

//Importamos clases que se usaran
import java.io.File;
import java.io.FileWriter;

public class Escribir
{
	public static void escribir(String path, String nombreArchivo, String texto)
	{
	
		//Un texto cualquiera guardado en una variable
		String saludo= texto;
		System.getProperty("line.separator");
		String salto ="\r\n";
		
		
		try
		{
			//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
			File archivo=new File(path+"/"+nombreArchivo+".TXT");
			
			//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir=new FileWriter(archivo,true);
			
			//Escribimos en el archivo con el metodo write
			
			
			escribir.write(saludo);
			escribir.write(salto);
			
			
			
			//Cerramos la conexion
			escribir.close();
		}
		
		//Si existe un problema al escribir cae aqui
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error al escribir");
		}
	}
	
	
	public static void borrar(String path, String nombreArchivo)
	{
	
		//Un texto cualquiera guardado en una variable
		
		System.getProperty("line.separator");
		String salto ="\r\n";
		
		
		try
		{
			//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
			File archivo=new File(path+"/"+nombreArchivo+".TXT");
			
			//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir=new FileWriter(archivo,false);
			
			//Escribimos en el archivo con el metodo write
			
			
			escribir.write("");
			//escribir.write(salto);
			
			
			
			//Cerramos la conexion
			escribir.close();
		}
		
		//Si existe un problema al escribir cae aqui
		catch(Exception e)
		{
			System.out.println("Error al escribir");
		}
	}
	
	
	
	
	public static void escribirSALESO(String path, String nombreArchivo, String texto)
	{
	
		//Un texto cualquiera guardado en una variable
		String saludo= texto;
		System.getProperty("line.separator");
		String salto ="\r\n";
		
		
		try
		{
			//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
			File archivo=new File(path+"/"+nombreArchivo+".csv");
			
			
			//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir=new FileWriter(archivo,true);
			
			//Escribimos en el archivo con el metodo write
			
			
			escribir.write(saludo);
			escribir.write(salto);
			
			
			
			//Cerramos la conexion
			escribir.close();
		}
		
		//Si existe un problema al escribir cae aqui
		catch(Exception e)
		{
			System.out.println("Error al escribir");
		}
	}



	public static void borrarSalesO(String path, String nombreArchivo) 
	{
		
		try
		{
			//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
			File archivo=new File(path+"/"+nombreArchivo+".csv");
			archivo.delete();
			
			
			
		}
		
		//Si existe un problema al escribir cae aqui
		catch(Exception e)
		{
			System.out.println("Error al escribir");
		}
	}
	
} 