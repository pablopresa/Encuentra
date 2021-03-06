package logica;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import beans.encuentra.DataOjo;

import logica.Logica;
import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import persistencia._EncuentraConexion;

public class DibujarMapa 
{
	
	
	
	

	
	public void dibujarMapa() 
	{
		Logica Logica = new Logica();
		String HTML = "";
		try
		{
		
			File fe;
			/*****************************SOLO EN TEST********************************************/
			//fe = new File("C:/Users/Gonza/Workspaces/MyEclipse 8.6/.metadata/.me_tcat/webapps/Arreglos/mapaLectura_archivos/sheet001.htm");
			/*****************************EN PRODUCCION********************************************/
			fe = new File("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/Arreglos/mapaLectura_archivos/sheet001.htm");
			
			FileWriter w = new FileWriter(fe);
			
			
			
			BufferedWriter bw = new BufferedWriter(w);
			
		
			PrintWriter wr = new PrintWriter(bw);
			
			
			
			Hashtable<String, DataOjo> ojos = null;
			List<String> ides = new ArrayList<>();

			
			
			_EncuentraConexion econ = new _EncuentraConexion();
			Connection cone;
			cone =econ.getConnection();
			
			String consulta = "SELECT O.`idOjo`, O.`idArticulo`,A.Descripcion , "+
			" O.`Cantidad`, A.AnchoCaja, A.AltoCaja, A.ProfCaja, OS.Ancho, OS.Alto, OS.Profindidad, OS.IdEstanteria, OS.idEstante, OS.idModulo "+ 
			" FROM `ojostienenarticulos` O, `articulos` A, `ojos` OS "+
			" WHERE O.idArticulo = A.IdArticulo AND O.idOjo = OS.IdOjo";
			
			String consul = "SELECT O.`idOjo`, O.`idArticulo`,A.Descripcion ,  O.`Cantidad`, A.AnchoCaja, A.AltoCaja, A.ProfCaja, OS.Ancho, OS.Alto, OS.Profindidad, OS.IdEstanteria, OS.idEstante, OS.idModulo, TE.Ancho, TE.Alto " +
					       " FROM `ojostienenarticulos` O, `articulos` A, `ojos` OS, `estanterias` E, `tipoestanteria` TE " +
					       " WHERE  E.TipoSector = TE.idTipo AND OS.IdEstanteria = E.idEstanteria AND	O.idArticulo = A.IdArticulo AND O.idOjo = OS.IdOjo";
			
			ojos = econ.darOjosOcupados(consul);

			Enumeration e = ojos.keys();
			String clave;
			while( e.hasMoreElements())
			{
			  clave =  (String) e.nextElement();
			  DataOjo doj = ojos.get(clave);
			  ides.add(doj.getIdOjo());
			}
				  
			wr.write("");//escribimos en el archivo
			wr.println(""); //concatenamos en el archivo sin borrar lo existente
			
			
			 
			
						
		
			File f;
			FileReader lectorArchivo;
		
			//Creamos el objeto del archivo que vamos a leer
			/****************************SOLO EN TESTING**************************/
			//f = new File("C:/Users/Gonza/Workspaces/MyEclipse 8.6/.metadata/.me_tcat/webapps/Arreglos/mapaLectura_archivos/leer.htm");
			
			/****************************EN PRODUCCION**************************/
			f = new File("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/Arreglos/mapaLectura_archivos/leer.htm");
			
			//Creamos el objeto FileReader que abrira el flujo(Stream) de datos para realizar la lectura
			lectorArchivo = new FileReader(f);
			
			//System.out.println(lectorArchivo.getEncoding());
		
			//Creamos un lector en buffer para recopilar datos a travez del flujo "lectorArchivo" que hemos creado
			BufferedReader br = new BufferedReader(lectorArchivo);
			
			
			//Esta variable "l" la utilizamos para guardar mas adelante toda la lectura del archivo
		
			String aux="";//variable auxiliar
			
			int linea = 0;
			for (String numero : ides) 
			{
				System.out.println(numero);
			}
			String hora = Logica.darFechaActual();
			
			while(true)
			//este ciclo while se usa para repetir el proceso de lectura, ya que se lee solo 1 linea de texto a la vez
			{
				aux=br.readLine();
				
				
				//leemos una linea de texto y la guardamos en la variable auxiliar
				if(aux!=null)
				{	
					linea ++;
					
					
					if(aux.contains(">Generado:<"))
						
					{
						aux = aux.replaceAll(">Generado:<",">Generado: "+hora+"<");
					}
					
					
					for (String numero : ides) 
					{
						if(aux.contains(">"+numero+"<"))
						
						{
							
							int largo = aux.length();
							//System.out.println(largo);
							
							
							DataOjo d = ojos.get(numero);
							
							//System.out.println(aux);
							
							aux = aux.replaceAll(">"+numero+"<",
												 ">" +
												
												 "<a href='/../../../../../../Arreglos/DarOjosArti.do?idOjo="+numero+"' target='_BLANK'>" +
												 "<div " +
												 "style='background-color:orange; height:100%; margin-top:0px; width:100%;' " +
												 "title='Ubicaci?n "+numero+" lugares disponibles "+d.getCantLibre()+" Ocupados "+d.getCantOcupada()+"'>" +
												 ""+numero+"" +
												 "</div>" +
												 "</a>" +
												
												 "<");
							
//							aux += "style='background-color:red;' title='Ubicaci?n "+numero+" lugares disponibles "+d.getCantLibre()+" Ocupados "+d.getCantOcupada()+" '>"+numero+"</td>";
							//System.out.println(aux);
						}
						
						
						//si la variable aux tiene datos se va acumulando en la variable l,
						//en caso de ser nula quiere decir que ya nos hemos leido todo
					
						
					}
					wr.println(aux);
					
					
				}
				
				else
				{
					break;
				}
			}
			//wr.println(l);
			

			
			br.close();
	
			lectorArchivo.close();
			
			
			
			
			
			wr.close();
			bw.close();
	
		}
		
		catch (Exception e) 
		{
			System.out.println("Error:"+e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
				
	}

}
