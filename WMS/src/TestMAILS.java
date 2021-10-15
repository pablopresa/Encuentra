import java.util.ArrayList;
import java.util.List;

import cliente_rest_Invoke.Call_WS_APIENCUENTRA;
import jsonObjects.SendMail;
import logica.EnviaMail;
import logica.Logica;


public class TestMAILS 
{
	public static void main(String[] args) 
	{	
		Logica logica = new Logica();
					
		List<SendMail> colaMails = new ArrayList<>();
		String body = EnviaMail.MailHTMLOD_FORUS("mailPascualini", "123456", "https://www.ues.com.uy/rastreo_paquete.html");
		System.out.println(body.length());
		System.out.println(body);
		
		SendMail sm = new SendMail("1","onviera@200.com.uy", "Pedido en Camino", body,"no-responder@forusuruguay.com.uy");
		
		colaMails.add(sm);
		Call_WS_APIENCUENTRA api = new Call_WS_APIENCUENTRA();
		api.PutColaEnvioMails(colaMails, 2);
		
	
	}
	

	public TestMAILS() {
	}
	
	
	
	
	
	
	

}
