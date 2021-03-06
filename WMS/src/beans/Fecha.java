package beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Hashtable;
import org.joda.time.JodaTimePermission;

public class Fecha 
{
	private int dia;
	private int diaSemana;
	private int mes;
	private int anio;
	private int hora;
	private int minuto;
	private int segundo;
	private int diaDelAnio;
	private String ampm;
	
	
	public int getDiaDelAnio() {
		return diaDelAnio;
	}


	public void setDiaDelAnio(int diaDelAnio) {
		this.diaDelAnio = diaDelAnio;
	}


	public int pasarAMPM(int hora)
	{
		final Hashtable<Integer, Integer> pmtor = new Hashtable<>();
		
		pmtor.put(0, 12);
		pmtor.put(1, 13);
		pmtor.put(2, 14);
		pmtor.put(3, 15);
		pmtor.put(4, 16);
		pmtor.put(5, 17);
		pmtor.put(6, 18);
		pmtor.put(7, 19);
		pmtor.put(8, 20);
		pmtor.put(9, 21);
		pmtor.put(10, 22);
		pmtor.put(11, 23);
		
		try
		{
			return pmtor.get(hora);
		}
		catch(Exception e)
		{
			return 0;
		}
		
		
		
	}
	
	
	public Fecha(int dia, int mes, int anio, int hora, int minuto,int horaMas, String s) 
	{
		
		Calendar c = Calendar.getInstance();
		c.set(anio, mes-1, dia,hora,minuto,0);
		
		
		
		c.add(Calendar.HOUR_OF_DAY, horaMas);
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH)) + 1;
		this.anio = c.get(Calendar.YEAR);
		this.hora = c.get(Calendar.HOUR_OF_DAY);
		this.minuto = c.get(Calendar.MINUTE);
		this.diaDelAnio = c.get(Calendar.DAY_OF_YEAR);
		
		int am_pm = c.get(Calendar.AM_PM);
		
		if(am_pm==1)
		{
			this.ampm="PM";
		}
		else
		{
			this.ampm="AM";
		}
		
		this.diaDelAnio = c.get(Calendar.DAY_OF_YEAR);
	}
	
	
	public Fecha(int dia, int mes, int anio, int hora, int minuto,int semanasMas, int anioMas) 
	{
		
		Calendar c = Calendar.getInstance();
		c.set(anio, mes-1, dia);
		
		c.add(Calendar.WEEK_OF_MONTH, semanasMas);
		c.add(Calendar.YEAR, anioMas);
		
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH)) + 1;
		this.anio = c.get(Calendar.YEAR);
		this.hora = c.get(Calendar.HOUR);
		this.minuto = c.get(Calendar.MINUTE);
		int am_pm = c.get(Calendar.AM_PM);
		this.diaDelAnio = c.get(Calendar.DAY_OF_YEAR);
		
		if(am_pm==1)
		{
			this.ampm="PM";
		}
		else
		{
			this.ampm="AM";
		}
		this.diaDelAnio = c.get(Calendar.DAY_OF_YEAR);
	}
	
	public String getAmpm() {
		return ampm;
	}
	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	
	
	public String darFechaAnioMesDia()
	{
		return anio+""+ValidarNumeroMenor10(mes)+""+ValidarNumeroMenor10(dia);
	}
	
	public String darFechaAnio_Mes_Dia()
	{
		return anio+"-"+ValidarNumeroMenor10(mes)+"-"+ValidarNumeroMenor10(dia);
	}
	
	public String darFechaAnio_Mes_Dia_hhmm()
	{
		return anio+"-"+ValidarNumeroMenor10(mes)+"-"+ValidarNumeroMenor10(dia)+" "+ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto)+":00";
	}
	
	public String darFechaDia_Mes_Anio_HoraBarra()
	{
		return ValidarNumeroMenor10(dia)+"/"+ValidarNumeroMenor10(mes)+"/"+anio+" "+ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto)+":00";
	}
	
	public String darFechaDia_Mes_Anio_Barra()
	{
		return ValidarNumeroMenor10(dia)+"/"+ValidarNumeroMenor10(mes)+"/"+anio;
	}
	public String darFechaAnio_Mes_Dia_hhmmBarra()
	{
		return anio+"/"+ValidarNumeroMenor10(mes)+"/"+ValidarNumeroMenor10(dia)+" "+ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto);
	}
	
	
	
	public String darFechaAnio_Mes_Dia_hhmmBarra_http()
	{
		return anio+"_"+ValidarNumeroMenor10(mes)+"_"+ValidarNumeroMenor10(dia)+"_"+ValidarNumeroMenor10(hora)+""+ValidarNumeroMenor10(minuto);
	}
	

	public String darFechadia_mes_Anio_hhmmBarra_NOAMPM()
	{
		
		if(ampm.equals("PM"))
		{
			hora = pasarAMPM(hora);
		}
		
		
		return ValidarNumeroMenor10(dia)+"/"+ValidarNumeroMenor10(mes)+"/"+anio+" "+ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto);
	}
	
	public String darFechadia_mes_Anio_hhmmBarra_SM()
	{
		return ValidarNumeroMenor10(dia)+"/"+ValidarNumeroMenor10(mes)+"/"+anio;
	}
	
	public String darFecha_mes_dia_Anio_hhmmBarra_SM()
	{
		return ValidarNumeroMenor10(mes)+"/"+ValidarNumeroMenor10(dia)+"/"+anio;
	}
	public String darHoraMinutoSalesO()
	{
		 return ValidarNumeroMenor10(hora)+""+ValidarNumeroMenor10(minuto);
	}
	
	public String darHoraMinutoSegundoZ()
	{
		 return ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto)+":00Z";
	}
	
	public String darFechaToSQL()
	{
		//'2014-08-02 21:10:46'
		return anio+"-"+ValidarNumeroMenor10(mes)+"-"+ValidarNumeroMenor10(dia)+" "+ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto)+":00"; 
		
	}
	
	public String darFechaToMSSQL()
	{
		
		//'2014-08-02 21:10:46'
		return anio+"-"+ValidarNumeroMenor10(dia)+"-"+ValidarNumeroMenor10(mes)+" "+ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto)+":00"; 
		
	}
	
	public String darFechaToSQLSinMinutos()
	{
		//'2014-08-02 21:10:46'
		return anio+"-"+ValidarNumeroMenor10(dia)+"-"+ValidarNumeroMenor10(mes); 
		
	}
	
	public Fecha(int dia, int mes, int anio, int hora, int minuto) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.hora = hora;
		this.minuto = minuto;
		
	}
	
	
	
	public Fecha(int dia, int mes, int anio, int hora, int minuto,int semanasMas) 
	{
		
		Calendar c = Calendar.getInstance();
		c.set(anio, mes-1, dia);
		
		c.add(Calendar.WEEK_OF_MONTH, semanasMas);
		
		
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH)) + 1;
		this.anio = c.get(Calendar.YEAR);
		this.hora = c.get(Calendar.HOUR);
		this.minuto = c.get(Calendar.MINUTE);
		int am_pm = c.get(Calendar.AM_PM);
		this.diaDelAnio = c.get(Calendar.DAY_OF_YEAR);
		
		
		if(am_pm==1)
		{
			this.ampm="PM";
		}
		else
		{
			this.ampm="AM";
		}
	}
	
	
	
	
	
	
	public Fecha(String fechaSQL) 
	{
		//2014-08-02 21:10:46
		int hora = 0;
		int minuto = 0;
		
		int dia = Integer.parseInt(fechaSQL.substring(8, 10));
		int mes = Integer.parseInt(fechaSQL.substring(5, 7));
		int anio = Integer.parseInt(fechaSQL.substring(0, 4));
		try
		{
			hora =Integer.parseInt(fechaSQL.substring(11, 13));
			minuto = Integer.parseInt(fechaSQL.substring(14, 16));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, dia);
		c.set(Calendar.MONTH, mes-1);
		c.set(Calendar.YEAR, anio);
		
		
		
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.hora = hora;
		this.minuto = minuto;
		this.diaDelAnio = c.get(Calendar.DAY_OF_YEAR);
	}
	
	
	
	
	public int getDiaSemana() {
		return diaSemana;
	}


	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}


	public Fecha() 
	{
		Calendar c = Calendar.getInstance();
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH)) + 1;
		this.anio = c.get(Calendar.YEAR);
		this.hora = c.get(Calendar.HOUR);
		this.minuto = c.get(Calendar.MINUTE);
		int am_pm = c.get(Calendar.AM_PM);
		
		if(am_pm==1)
		{
			this.ampm="PM";
		}
		else
		{
			this.ampm="AM";
		}
		this.diaDelAnio = c.get(Calendar.DAY_OF_YEAR);
		
	}
	public Fecha(boolean veinticuatro, int masHoras) 
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, masHoras);
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH)) + 1;
		this.anio = c.get(Calendar.YEAR);
		this.hora = c.get(Calendar.HOUR_OF_DAY);
		this.minuto = c.get(Calendar.MINUTE);
		int am_pm = c.get(Calendar.AM_PM);
		this.diaSemana = c.get(Calendar.DAY_OF_WEEK);
		
		if(am_pm==1)
		{
			this.ampm="PM";
		}
		else
		{
			this.ampm="AM";
		}
		
		this.diaDelAnio = c.get(Calendar.DAY_OF_YEAR);
	}
	public Fecha(int masDias,int masMes, int masAnio) 
	{
		
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, masDias);
		c.add(Calendar.YEAR, masAnio);
		c.add(Calendar.MONTH, masMes);
		this.diaSemana = c.get(Calendar.DAY_OF_WEEK);
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH)) + 1;
		this.anio = c.get(Calendar.YEAR);
		this.hora = Calendar.HOUR;
		this.minuto = c.get(Calendar.MINUTE);
		this.segundo = c.get(Calendar.SECOND);
		
		int am_pm = c.get(Calendar.AM_PM);
		
		if(am_pm==1)
		{
			this.ampm="PM";
		}
		else
		{
			this.ampm="AM";
		}
		
	}
	
	public Fecha(int masDias,int masMes, int masAnio,int masHoras) 
	{
		
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, masDias);
		c.add(Calendar.YEAR, masAnio);
		c.add(Calendar.MONTH, masMes);
		c.add(Calendar.HOUR_OF_DAY, masHoras);
		
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH)) + 1;
		this.anio = c.get(Calendar.YEAR);
		this.hora = Calendar.HOUR_OF_DAY;
		this.minuto = c.get(Calendar.MINUTE);
		
		int am_pm = c.get(Calendar.AM_PM);
		
		if(am_pm==1)
		{
			this.ampm="PM";
		}
		else
		{
			this.ampm="AM";
		}
		this.diaDelAnio = c.get(Calendar.DAY_OF_YEAR);
		
	}
	
	public String FechaMostrable()
	{
		return ValidarNumeroMenor10(dia)+"/"+ValidarNumeroMenor10(mes)+"/"+anio+" "+ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto);
		
	}
	
	public String FechaMostrableSM()
	{
		return ValidarNumeroMenor10(dia)+"/"+ValidarNumeroMenor10(mes)+"/"+anio;
		
	}
	
	public String ValidarNumeroMenor10(int input)
	{
		if(input>=10)
		{
			return ""+input;
		}
		else
		{
			return "0"+input;
			
		}
	}
	
	public void FechaSuma(int masDias,int masMes, int masAnio) 
	{
		
		
		Calendar c = Calendar.getInstance();
		c.set(this.anio, this.mes, this.dia);
		
		if (this.mes==3 && this.dia==30){
			this.dia=31;
		}
		else{
		c.add(Calendar.DATE, masDias);
		c.add(Calendar.YEAR, masAnio);
		c.add(Calendar.MONTH, masMes);
		
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH));
		this.anio = c.get(Calendar.YEAR);
		this.hora = Calendar.HOUR_OF_DAY;
		this.minuto = c.get(Calendar.MINUTE);
		
		int am_pm = c.get(Calendar.AM_PM);
		
		if(am_pm==1)
		{
			this.ampm="PM";
		}
		else
		{
			this.ampm="AM";
		}
		
		}
		
		
	}
	
	public String darDia(){
		Calendar c = Calendar.getInstance();
		c.set(this.anio, this.mes, this.dia);
		
		String letraD="";
		int dia=c.get(Calendar.DAY_OF_WEEK); 
		    switch (dia){
		        case 1: letraD = "J ";
		            break;
		        case 2: letraD = "V ";
		            break;
		        case 3: letraD = "S ";
		            break;
		        case 4: letraD = "D ";
		            break;
		        case 5: letraD = "L ";
		            break;
		        case 6: letraD = "M ";
		            break;
		        case 7: letraD = "X ";
		            break;
		    }
		
		    
		   
		
		return letraD;
	}

	public int getSegundo() {
		return segundo;
	}

	public void setSegundo(int segundo) {
		this.segundo = segundo;
	}
	
	public String darFechaString()
	{		
		return ValidarNumeroMenor10(dia)+ValidarNumeroMenor10(mes)+anio+ValidarNumeroMenor10(hora)+ValidarNumeroMenor10(minuto)+ValidarNumeroMenor10(segundo); 
		
	}
	
	public Fecha now() {
		try {
			LocalDateTime ldt = LocalDateTime.now();
			this.anio = ldt.getYear();
			this.mes = ldt.getMonthValue();
			this.dia = ldt.getDayOfMonth();
			this.hora = ldt.getHour();
			this.minuto = ldt.getMinute();
			this.segundo = ldt.getSecond();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	

}
