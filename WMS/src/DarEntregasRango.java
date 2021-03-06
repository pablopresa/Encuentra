import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dataTypes.DataEnvioFecha;
import dataTypes.DataIDIDID;

import logica.Logica;


public class DarEntregasRango 
{

	/**
	 * @param args
	 */
	
	
	static String fechaI = "01/05/2015 00:00";
	static String fechaF = "21/05/2015 00:00";
	
	
	public static void main(String[] args) 
	{
		Logica logica = new Logica();
		String fechaIn = darFechaSQL(fechaI);
		String fechaFin = darFechaSQL(fechaF);
		
		
		Date fechaI = darFecha(fechaIn);
		
		Date fechaF = darFecha(fechaFin);
		
		
		fechaIn = fechaIn.substring(0, 10);
		fechaFin = fechaFin.substring(0,10);
		List<DataEnvioFecha> enviosXFecha = null;
		
		
		List<DataIDIDID> sucursales = enviosXFecha.get(0).getDepositoUnidades();
		List<String> columnas = new ArrayList<>();
		
		List<List<String>> filas = new ArrayList<List<String>>();
		
		/*
		String fila = "                ! ";
		for (DataIDIDID s : sucursales) 
		{
			fila+=s.getId1()+" ! ";
		}
		System.out.println(fila);
		 */
		boolean pri = true;
		for (DataEnvioFecha e : enviosXFecha) 
		{
			if(pri)
			{
				pri = false;
			}
			else
			{
				if(e!=null && !e.getFecha().equals(""))
				{
					
					Date fechaDato = darFecha(e.getFecha());
					String fechaMostrable = darFechaString(fechaDato);
					//String linea2 = fechaMostrable + " ! ";
					
					columnas.add(fechaMostrable);
					
					boolean encontre = false;
					for (DataIDIDID sucursal : sucursales) 
					{
						
						int cant = darCantidadSiEsta(e.getDepositoUnidades(), sucursal.getId1());
						
						//linea2 += cant + " ! ";
						if(cant!=0)
						{
							columnas.add(String.valueOf(cant));
						}
						else
						{
							columnas.add("");
						}
						
					}
					
					//System.out.println(linea2);
					filas.add(columnas);
					
				}
			}
		}
		
		
		
	
		
		
		

	}
	
	public static String darFechaString(Date fecha)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE",new Locale("ES"));
		
		
		int diaMes = fecha.getDate();
		String nombreDia = formatter.format(fecha.getTime());
		String nombreMes = dateMonth(fecha);
		
		return nombreDia+ " "+diaMes+" de "+nombreMes;
	}
	
	public static Date darFecha (String in)
	{
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",new Locale("ES"));
	      try 
	      {
			return formatter.parse(in);
	      } 
	      catch (ParseException e) 
	      {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
	      }
	     
	}
	
	public static String darFechaSQL(String in)
	{
		String[]fech = in.split(" ");
		String[]ddmmyyyy= fech[0].split("/");
		String dd = ddmmyyyy[0];
		String mm = ddmmyyyy[1];
		String yyyy = ddmmyyyy[2];
		return yyyy+"-"+mm+"-"+dd + " "+fech[1];
	}
	
	
    public static String dateMonth(Date date)
    {
        String result="";
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int month=0;
        
        try{
          month=calendar.get(Calendar.MONTH);
        }catch(Exception ex){}
        switch(month){
         case 0:
           {
             result="Enero";
             break;
           }
         case 1:
           {
             result="Febrero";
             break;
           }
         case 2:
           {
             result="Marzo";
             break;
           }
         case 3:
           {
             result="Abril";
             break;
           }
         case 4:
           {
             result="Mayo";
             break;
           }
         case 5:
           {
             result="Junio";
             break;
           }
         case 6:
           {
             result="Julio";
             break;
           }
         case 7:
           {
             result="Agosto";
             break;
           }
         case 8:
           {
             result="Septiembre";
             break;
           }
         case 9:
           {
             result="Octubre";
             break;
           }
         case 10:
           {
             result="Noviembre";
             break;
           }
         case 11:
           {
             result="Diciembre";
             break;
           }
         default:
           {
             result="Error";
             break;
           }
        }
        return result;
       }
    
    public static int darCantidadSiEsta(List<DataIDIDID> list, int idBuscado)
    {
    	
    	int cantidad = 0;
    	
    	for (DataIDIDID d : list) 
    	{
			if(d.getId1()==idBuscado)
			{
				return d.getId2();
			}
		}
    	return cantidad;
    }

}
