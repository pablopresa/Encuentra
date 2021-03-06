import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import persistencia.MSSQL;

class Objutil
{
	private int seccion;
	private int marca;
	private String talleIn;
	private String talleOut;
	public int getSeccion() {
		return seccion;
	}
	public void setSeccion(int seccion) {
		this.seccion = seccion;
	}
	public int getMarca() {
		return marca;
	}
	public void setMarca(int marca) {
		this.marca = marca;
	}
	public String getTalleIn() {
		return talleIn;
	}
	public void setTalleIn(String talleIn) {
		this.talleIn = talleIn;
	}
	public String getTalleOut() {
		return talleOut;
	}
	public void setTalleOut(String talleOut) {
		this.talleOut = talleOut;
	}
	public Objutil(int seccion, int marca, String talleIn, String talleOut) {
		this.seccion = seccion;
		this.marca = marca;
		this.talleIn = talleIn;
		this.talleOut = talleOut;
	}
	
	
	
}


public class MainECM 
{
	
	public static void main(String args[]) 
	{
		try
		{
			int seccion = 3;
			String path = "C:/Users/gonza/Desktop/ECM/"+seccion +".csv";
			FileReader fr = new FileReader(path);
			BufferedReader bf = new BufferedReader(fr);
			String sCadena;
			List<List<String>> lineas = new ArrayList<List<String>>();
			while ((sCadena = bf.readLine())!=null) 
			{
				List<String> linea = new ArrayList<>();
				sCadena = sCadena.trim();
				sCadena=sCadena.replaceAll(" ", "");
				String[] arreglo = sCadena.split(",");
				for (int i = 0; i < arreglo.length; i++) 
				{
					linea.add(arreglo[i]);
				}
				lineas.add(linea);
				
				
			}
			
			int pos=0;
			
			List<Objutil> tuplas = new ArrayList<>();
			List<String> tallas = lineas.get(0);
			
			
			
			for (List<String> list : lineas) 
			{
				if(pos>0)
				{
					String marcaSTR = list.get(0);
					try
					{
						int marca = Integer.parseInt(marcaSTR);
						int pasadaMarca = 0;
						for (String t : tallas) 
						{
							if(pasadaMarca>0)
							{
								String talleIn =list.get(pasadaMarca);
								String talleOut=t;
								
								if(!talleIn.equals(""))
								{
									Objutil tupla = new Objutil(seccion, marca, talleIn, talleOut);
									tuplas.add(tupla);
								}
								
							}
							
							pasadaMarca ++;
						}
					}
					catch (Exception e)
					{
						
					}
				}
				pos++;
			}
			
			
			for (Objutil t : tuplas) 
			{
				//System.out.println(ins);
				try
				{
					MSSQL.persistirECM("INSERT INTO MapSize (Seccion, Marca, TalleIn, TalleOur) VALUES ("+t.getSeccion()+","+ t.getMarca()+",'"+t.getTalleIn()+"','"+t.getTalleOut()+"')");
				}
				catch (Exception e)
				{
					
				}
				
			}
			
			 
		} catch (FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch (IOException ioe){
			ioe.printStackTrace();
		}

    }

    public static void stopService(String serviceName) throws IOException,
            InterruptedException {

        String executeCmd = "cmd /c start \"C:\\Program Files\\Reclamos\\frenar.bat\"";
        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
        int processComplete = runtimeProcess.waitFor();

        System.out.println("processComplete: " + processComplete);

        if (processComplete == 1) {// if values equal 1 process failed
            System.out.println("Service failed");
        }

        else if (processComplete == 0) {
            System.out.println("Service Success");
        }

    }
	
	
	
}































