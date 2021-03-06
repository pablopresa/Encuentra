package logica;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dataTypes.DataIDDescripcion;
/*
import com.independentsoft.exchange.ItemInfoResponse;
import com.independentsoft.exchange.Mailbox;
import com.independentsoft.exchange.Message;
import com.independentsoft.exchange.Body;
import com.independentsoft.exchange.Service;
import com.independentsoft.exchange.ServiceException;
*/
public class EnviaMail {

	public EnviaMail() {
	}

	
	
	
	
	
	
	
	
	
	public static void enviarMailLista(String [] destino, String asunto, String cuerpo)
	{
		try
		 {
			 String host = "10.108.0.18";
			    String from = "reclamos@stadium.com.uy";
			  
			
			    // Get system properties
			    Properties props = System.getProperties();
			
			    // Setup mail server
			    props.put("mail.smtp.host", host);
			
			    // Get session
			    Session session = Session.getDefaultInstance(props, null);
			
			    // Define message
			    MimeMessage message = new MimeMessage(session);
			    message.setFrom(new InternetAddress(from));
			    for (int i = 0; i < destino.length; i++) 
			    {
			    	message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino[i]));
				}
			    
			    //message.addRecipient(Message.RecipientType.TO, new InternetAddress("reclamos@stadium.com.uy"));
			    message.setSubject(asunto);
			    message.setText(cuerpo);
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
	
	}
	
	
	
	public static void enviarMail(String mailDestino, String asunto, String cuerpo)
	{
		try
		 {
			 String host = "correo.200.com.uy";
			    String from = "reclamos@stadium.com.uy";
			    String to = mailDestino;
			
			    // Get system properties
			    Properties props = System.getProperties();
			
			    // Setup mail server
			    props.put("mail.smtp.host", host);
			
			    // Get session
			    Session session = Session.getDefaultInstance(props, null);
			
			    // Define message
			    MimeMessage message = new MimeMessage(session);
			    message.setFrom(new InternetAddress(from));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			    message.setSubject(asunto);
			    message.setText(cuerpo);
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
	
	}
	public static void enviarMailSQR(String mailDestino, String asunto, String cuerpo)
	{
		try
		 {
			 String host = "10.108.0.18";
			    String from = "info@stadium.com.uy";
			    String to = mailDestino;
			
			    // Get system properties
			    Properties props = System.getProperties();
			
			    // Setup mail server
			    props.put("mail.smtp.host", host);
			
			    // Get session
			    Session session = Session.getDefaultInstance(props, null);
			
			    // Define message
			    MimeMessage message = new MimeMessage(session);
			    message.setFrom(new InternetAddress(from));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress("info@stadium.com.uy"));
			    message.setSubject(asunto);
			    
			    message.setText(cuerpo);
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
	
	}
	
	public static void enviarMailHTML(String mailDestino, String asunto, String cuerpo)
	{
		try
		 {
			 String host = "10.108.0.18";
			    String from = "expedicion@stadium.com.uy";
			    String to = mailDestino;
			
			    // Get system properties
			    Properties props = System.getProperties();
			
			    // Setup mail server
			    props.put("mail.smtp.host", host);
			
			    // Get session
			    Session session = Session.getDefaultInstance(props, null);
			
			    // Define message
			    MimeMessage message = new MimeMessage(session);
			    message.setFrom(new InternetAddress(from));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress("expedicion@stadium.com.uy"));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress("gmonzon@stadium.local"));
			    message.setSubject(asunto);
			    
			    message.setText(cuerpo, "utf-8", "html");
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
	
	}
	
	

	public static void enviarMailFormadoPP(String mailDestino, String asunto, String cuerpo, String mailOrigen)
	{
		try
		 {
			 String host = "10.108.0.18";
			    String from = mailOrigen;
			    String to = mailDestino;
			
			    // Get system properties
			    Properties props = System.getProperties();
			
			    // Setup mail server
			    props.put("mail.smtp.host", host);
			
			    // Get session
			    Session session = Session.getDefaultInstance(props, null);
			
			    // Define message
			    MimeMessage message = new MimeMessage(session);
			    message.setFrom(new InternetAddress(from));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress("reclamos@stadium.local"));
			    message.setSubject(asunto);
			    message.setText(cuerpo);
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
	
	}
	
	public static void enviarMailShare(String mailDestino, String asunto, String cuerpo, String mailOrigen)
	{
		try
		 {
			 String host = "10.108.0.18";
			    String from = mailOrigen;
			    String to = mailDestino;
			
			    // Get system properties
			    Properties props = System.getProperties();
			
			    // Setup mail server
			    props.put("mail.smtp.host", host);
			
			    // Get session
			    Session session = Session.getDefaultInstance(props, null);
			
			    // Define message
			    MimeMessage message = new MimeMessage(session);
			    message.setFrom(new InternetAddress(from));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			    message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));//le manda una copia al usuario
			    message.setSubject(asunto);
			    message.setText(cuerpo);
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
	
	}
	
	public static void enviarMailHTMLOD(String mailOrigen, List<String> mailDestinos, String asunto, String cuerpo, int idEmpresa)
	{
		Logica lo = new Logica();
		//if(lo.darIntegracionProductiva(4, idEmpresa))
		if(2==2)
		{
			try
			 {
				
				Authenticator auth = new Authenticator() {
			        @Override
			        protected PasswordAuthentication getPasswordAuthentication() {
			            // TODO Auto-generated method stub
			            return new PasswordAuthentication("encuentra@200", "@encuentra");
			        }
			    };
				
				 	String host = "correo.200.com.uy";
				    String from = mailOrigen;
				    
				
				    // Get system properties
				    Properties props = System.getProperties();
				
				    // Setup mail server
				    props.put("mail.smtp.host", host);
				    props.put("mail.smtp.auth", "true");
				
				    // Get session
				    Session session = Session.getDefaultInstance(props, auth);
				
				    // Define message
				    MimeMessage message = new MimeMessage(session);
				    message.setFrom(new InternetAddress(from));
				    for (String to : mailDestinos) 
				    {
				    	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					}
				    
				    
				    message.setSubject(asunto);
				    
				    message.setText(cuerpo, "utf-8", "html");
				    session.setDebug(true);
				    // Send message
				    Transport.send(message);
				 
			 }
			 catch (Exception e) 
			 {
				 e.printStackTrace();
			 }
			
		}
		
	
	}

	public static String MailHTMLOD_FORUS(String bodyName, String tracking, String urlTracking)
	{
		String content = "";
		try
		 {		
		    StringBuilder contentBuilder = new StringBuilder();
			try {
			    BufferedReader in = new BufferedReader(new FileReader("C:\\Program Files\\apache-tomcat-7.0.64\\webapps\\Forus\\htmls\\"+bodyName+".html"));
			    String str;
			    while ((str = in.readLine()) != null) {
			        contentBuilder.append(str);
			    }
			    in.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			content = contentBuilder.toString();
			
			
			content = content.replace("$URLTRACK", urlTracking);
			content = content.replace("$trackingNumber", tracking);		 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
		return content;
	
	}
	
	public static void enviarMailHTMLOD_FORUS( List<String> mailDestinos, String asunto, String bodyName, String tracking, String urlTracking)
	{
		try
		 {
			
			Authenticator auth = new Authenticator() {
		        @Override
		        protected PasswordAuthentication getPasswordAuthentication() {
		            // TODO Auto-generated method stub
		            return new PasswordAuthentication("no-responder@forusuruguay.com.uy", "!n0_r3plYHP!!");
		        }
		    };
			
			 String host = "smtp.forusuruguay.com.uy";
			    String from = "no-responder@forusuruguay.com.uy";
			    
			
			    // Get system properties
			    Properties props = System.getProperties();
			
			    // Setup mail server
			    props.put("mail.smtp.host", host);
			    props.put("mail.smtp.auth", "true");
			
			    // Get session
			    Session session = Session.getDefaultInstance(props, auth);
			
			    // Define message
			    MimeMessage message = new MimeMessage(session);
			    message.setFrom(new InternetAddress(from));
			    for (String to : mailDestinos) 
			    {
			    	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				}
			    
			    
			    message.setSubject(asunto);
			    
			    
			    
			    StringBuilder contentBuilder = new StringBuilder();
				try {
				    BufferedReader in = new BufferedReader(new FileReader("C:\\Program Files\\apache-tomcat-7.0.64\\webapps\\Forus\\htmls\\"+bodyName+".html"));
				    String str;
				    while ((str = in.readLine()) != null) {
				        contentBuilder.append(str);
				    }
				    in.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
				String content = contentBuilder.toString();
				
				
				content = content.replace("$URLTRACK", urlTracking);
				content = content.replace("$trackingNumber", tracking);
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    message.setText(content, "utf-8", "html");
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
	
	}

	public void enviarMailLogEcommerce(	List<DataIDDescripcion> pedidosSincronizados) 
	{
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}