package beans.encuentra;

import java.util.Calendar;

public class Fecha 
{
	private int dia;
	private int mes;
	private int anio;
	private int hora;
	private int minuto;
	
	
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
	
	public String darFechaDia_Mes_Anio_HoraBarra()
	{
		return ValidarNumeroMenor10(dia)+"/"+ValidarNumeroMenor10(mes)+"/"+anio+" "+ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto)+":00";
	}
	
	public String darFechaT()
	{
		//2017-02-16T17:22:25.000-0300
		return anio+"-"+ValidarNumeroMenor10(mes)+"-"+ValidarNumeroMenor10(dia)+"T"+ValidarNumeroMenor10(hora)+":"+ValidarNumeroMenor10(minuto)+":00.000-0300"; 

	}
	public String darHoraMinutoSalesO()
	{
		 return ValidarNumeroMenor10(hora)+""+ValidarNumeroMenor10(minuto);
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
	public Fecha(String fechaSQL) 
	{
		//2014-08-02 21:10:46
		int dia = Integer.parseInt(fechaSQL.substring(8, 10));
		int mes = Integer.parseInt(fechaSQL.substring(5, 7));
		int anio = Integer.parseInt(fechaSQL.substring(0, 4));
		int hora =Integer.parseInt(fechaSQL.substring(11, 13));
		int minuto = Integer.parseInt(fechaSQL.substring(14, 16));
		
		
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.hora = hora;
		this.minuto = minuto;
	}
	
	
	
	
	public Fecha() 
	{
		Calendar c = Calendar.getInstance();
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH)) + 1;
		this.anio = c.get(Calendar.YEAR);
		this.hora = Calendar.HOUR_OF_DAY;
		this.minuto = c.get(Calendar.MINUTE);
		
	}
	public Fecha(int masDias,int masMes, int masAnio) 
	{
		
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, masDias);
		c.add(Calendar.YEAR, masAnio);
		c.add(Calendar.MONTH, masMes);
		
		
		this.dia = c.get(Calendar.DATE);
		this.mes = (c.get(Calendar.MONTH)) + 1;
		this.anio = c.get(Calendar.YEAR);
		this.hora = Calendar.HOUR_OF_DAY;
		this.minuto = c.get(Calendar.MINUTE);
		
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
	
	
	
	
	
	

}
