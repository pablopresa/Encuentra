package cliente_rest_Invoke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;


public class Prueba {

	/**
	 * @param args
	 */
	

	
	
	public static void main(String[] args) 
	{
		
		Call2 call=new Call2();
		try
		{
			String token = Call2.login2("10.108.0.218");
			//System.out.println(body);
			//System.out.println(token);
			Call2.envioSalesOrder("",token,"10.108.0.218");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static String readFile(String fileName) throws IOException 
	{
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	
	
	
	
	
	
	
	public static String darDifFechas(String fechaInicial,String fechaFinal)
	{
	String salida= "";
	
    //System.out.println("año :"+fecha.substring(6,10));
    //System.out.println("dia :"+fecha.substring(0,2));
    //System.out.println("mes :"+fecha.substring(3,5));
    //System.out.println("hora :"+fecha.substring(11,13));
    //System.out.println("minuto :"+fecha.substring(14,16));
    //System.out.println("seg :"+fecha.substring(17,19));


    
    java.util.GregorianCalendar jCal = new java.util.GregorianCalendar();
    java.util.GregorianCalendar jCal2 = new java.util.GregorianCalendar();
    //jCal.set(year, month, date, hourOfDay, minute)
    jCal.set(Integer.parseInt(fechaInicial.substring(6,10)), Integer.parseInt(fechaInicial.substring(3,5))-1, Integer.parseInt(fechaInicial.substring(0,2)), Integer.parseInt(fechaInicial.substring(11,13)),Integer.parseInt(fechaInicial.substring(14,16)), Integer.parseInt(fechaInicial.substring(17,19)));
    jCal2.set(Integer.parseInt(fechaFinal.substring(6,10)), Integer.parseInt(fechaFinal.substring(3,5))-1, Integer.parseInt(fechaFinal.substring(0,2)), Integer.parseInt(fechaFinal.substring(11,13)),Integer.parseInt(fechaFinal.substring(14,16)), Integer.parseInt(fechaFinal.substring(17,19)));
    
    //System.out.println("Date format " + dateformat.format(jCal.getTime()) + "\n");
    //System.out.println("Date format " + dateformat.format(jCal2.getTime()) + "\n");

    long diferencia = jCal2.getTime().getTime()-jCal.getTime().getTime();
    double minutos = diferencia / (1000 * 60);
    long horas = (long) (minutos / 60);
    long minuto = (long) (minutos%60);
    long segundos = diferencia % 1000;
    long dias = horas/24;
    //Calcular meses...
    //Crear vector para almacenar los diferentes dias maximos segun correponda
    String[] mesesAnio = new String[12];
    mesesAnio[0] = "31";
    //validacion de los años bisiestos
    if (jCal.isLeapYear(Calendar.YEAR)){mesesAnio[1] = "29";}else{mesesAnio[1] = "28";}
    mesesAnio[2] = "31";
    mesesAnio[3] = "30";
    mesesAnio[4] = "31";
    mesesAnio[5] = "30";
    mesesAnio[6] = "31";
    mesesAnio[7] = "31";
    mesesAnio[8] = "30";
    mesesAnio[9] = "31";
    mesesAnio[10] = "30";
    mesesAnio[11] = "31";
    int diasRestantes = (int) dias;
    //variable almacenará el total de meses que hay en esos dias
    int totalMeses = 0;
    int mesActual = Calendar.MONTH;
    //Restar los dias de cada mes desde la fecha de ingreso hasta que ya no queden sufcientes dias para 
    // completar un mes.
    for (int i=0; i<=11; i++ ){
        //Validar año, si sumando 1 al mes actual supera el fin de año, 
        // setea la variable a principio de año 
        if ((mesActual+1)>=12){
            mesActual = i;
        }
        //Validar que el numero de dias resultantes de la resta de las 2 fechas, menos los dias
        //del mes correspondiente sea mayor a cero, de ser asi totalMeses aumenta,continuar hasta 
        //que ya nos se cumpla.
        if ((diasRestantes -Integer.parseInt(mesesAnio[mesActual]))>=0){
            totalMeses ++;
            diasRestantes = diasRestantes- Integer.parseInt(mesesAnio[mesActual]);
            mesActual ++;
        }else{
            break;
        }
    }
    //Resto de horas despues de sacar los dias
    horas = horas % 24;
    
    if (totalMeses > 0){
        if (totalMeses > 1)
            salida = salida+  String.valueOf(totalMeses)+" Meses,  ";
        else
            salida = salida+  String.valueOf(totalMeses)+" Mes, ";
    }
    if (diasRestantes > 0){
        if (diasRestantes > 1)
            salida = salida+  String.valueOf(diasRestantes)+" Dias, ";
        else
            salida = salida+  String.valueOf(diasRestantes)+" Dia, ";
    }
    
    
     salida =salida +String.valueOf(horas)+":"+String.valueOf(minuto)+":"+String.valueOf(segundos)+".";
    return salida;
  }
	
	
	
	
	
	
	
	
	
	
	

}
