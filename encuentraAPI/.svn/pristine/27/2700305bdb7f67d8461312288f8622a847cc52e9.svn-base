package beans;

import java.awt.print.PrinterException;
import java.io.IOException;
import logica.LogicaAPI;


public class IPrintREPRINT_Service 
{

	
	
	public IPrintREPRINT_Service() 
	{
		
	}	

  public static void main(String[] args) throws IOException, PrinterException 
  {  
	  
		try {
			while(true) {
				LogicaAPI.persistir("update print_spooler set printed = 0 where printed = -1 and estampa > '2020-09-28 12:00:00' and urlarchivo != ''");
				Thread.sleep(60000);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
  }
  		
  		
}