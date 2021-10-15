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
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import dataTypes.DataArtCatalogo;

public class LeerFicheroTexto 
{

	public static List<String> LeerProperties(String nombrePropiedad) 
	{
		List<String> retorno = new ArrayList<>();
		try {
		
			String path = "C:/Program Files/Reclamos/";
			FileReader fr = new FileReader(path+nombrePropiedad);
			BufferedReader bf = new BufferedReader(fr);
			String sCadena;
			while ((sCadena = bf.readLine())!=null) 
			{
				retorno.add(sCadena);
				System.out.println(sCadena);
			} 
				
			 
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}

		return retorno;
	}
	
	
	public static StringBuilder LeerArchivo(String nombre) 
	{
		StringBuilder retorno = new StringBuilder();
		try {
		
			String path = "C:/Program Files/Reclamos/";
			FileReader fr = new FileReader(path+nombre);
			BufferedReader bf = new BufferedReader(fr);
			String sCadena;
			while ((sCadena = bf.readLine())!=null) 
			{
				retorno.append(sCadena);
				
				retorno.append("-");
				
			} 
				
			 
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}

		return retorno;
	}
	
	public static Hashtable<String, DataArtCatalogo> LeerArchivoDBArt(String nombre) 
	{
		Hashtable<String, DataArtCatalogo>  retorno = new Hashtable<>();
		try {
		
			String path = "C:/Program Files/Reclamos/";
			FileReader fr = new FileReader(path+nombre);
			BufferedReader bf = new BufferedReader(fr);
			String sCadena;
			while ((sCadena = bf.readLine())!=null) 
			{
				try
				{
					String linea = sCadena;
					
					String[] fields = linea.split(";");
					//ZC46993;Grey;39-46;8,5;Camarao
					String idArt = fields[0];
					String color = fields[1];
					String [] escala = fields[2].split("-");
					String precio = fields[3].replace(",", ".");
					String marca = fields[4];
					int clase = Integer.parseInt(fields[5]);
					Vector escalaV = new Vector();
					
					//trabajo la escala
					//39-46
					
					int inicio = Integer.parseInt(escala[0]);
					int fin = Integer.parseInt(escala[1]);
					int pos = 0;
					for (int i = inicio; i <= fin; i++) 
					{
						System.out.println(i);
						escalaV.addElement(String.valueOf(i));
						pos++;
					}
					
					
					String[] escalaReal = (String[]) escalaV.toArray(new String[escalaV.size()]);
										
										
					DataArtCatalogo art = new DataArtCatalogo();
					art.setArticulo(idArt);
					art.setColor(color);
					art.setEscala(escalaReal);
					art.setPrecio(Double.parseDouble(precio));
					art.setMarca(marca);
					art.setClase(clase);
					
					
					retorno.put(idArt, art);
					
					
				}
				catch (Exception e)
				{
					System.out.println("linea que falla= "+sCadena);
				}
				 
				
			} 
				
			 
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}

		return retorno;
	}
	
	
	
	public static StringBuilder LeerArchivoTXT(String nombre) 
	{
		StringBuilder retorno = new StringBuilder();
		try {
		
			String path = "C:/Program Files/Reclamos/";
			FileReader fr = new FileReader(path+nombre);
			BufferedReader bf = new BufferedReader(fr);
			String sCadena;
			while ((sCadena = bf.readLine())!=null) 
			{
				retorno.append(sCadena);
				
				
				
			} 
				
			 
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}

		return retorno;
	}

}
