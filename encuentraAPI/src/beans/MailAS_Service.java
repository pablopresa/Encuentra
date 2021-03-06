package beans;

import java.util.ArrayList;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import beans.api.json.SendMail;
import logica.LogicaAPI;


public class MailAS_Service 
{

	private int id;
	
	public MailAS_Service() 
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

  public static void main(String[] args) throws InterruptedException
  {  
	  	//String empresa = "2";
	  
	  
		  System.out.println("iniciando");
		  List <SendMail> mails = null;
		  List<String> mailDestinos = null;
		  int send = 0;
		  String[] destinos = null;
		while(true){
			try 
			{	
				send = 0;
				mails = LogicaAPI.darListaToSend();
				for(SendMail m: mails)
				{
					LogicaAPI.persistir("update mail_spooler set send = "+1+" where id ='"+m.getId()+"' and idEmpresa ="+m.getIdEmpresa());
					try {
						if(!m.getBody().equals("") && !m.getDestino().equals("")){
							mailDestinos =new ArrayList<>();
							destinos = m.getDestino().split(",");
							for (String d : destinos) {
								mailDestinos.add(d);
							}
							
							switch (m.getIdEmpresa()) {
							case 2:
								
								if(m.getOrigen().contains("encuentra")){
									send = enviarMailHTMLOD(m.getOrigen(),mailDestinos, m.getAsunto(), m.getBody());
								}
								else{
									send = enviarMailHTMLOD_FORUS(mailDestinos, m.getAsunto(), m.getBody());
								}
								
								break;
							case 4:
								
								if(m.getOrigen().contains("encuentra")){
									send = enviarMailHTMLOD(m.getOrigen(),mailDestinos, m.getAsunto(), m.getBody());
								}else {
									send = enviarMailHTMLOD_El_Rey(mailDestinos, m.getAsunto(), m.getBody(), m.getAdjunto());
								}
								
								break;
								
							default:	
								if(m.getOrigen().contains("encuentra")){
									send = enviarMailHTMLOD(m.getOrigen(),mailDestinos, m.getAsunto(), m.getBody());
								}
								
								
								break;
								
							}														
							
							LogicaAPI.persistir("update mail_spooler set send = "+send+" where id ='"+m.getId()+"' and idEmpresa ="+m.getIdEmpresa());
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}

			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			Thread.sleep(60000);
		}
		
  }
  
  public static int enviarMailHTMLOD_FORUS( List<String> mailDestinos, String asunto, List<String> body)
	{
	  	int send = -1;
		try
		 {
			String all_body = "";
			for(String b:body){
				all_body += b;
			}
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
			    			    
			    message.setText(all_body, "utf-8", "html");
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			    send = 1;
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
		
		return send;
	
	}
  
  public static int enviarMailHTMLOD_El_Rey( List<String> mailDestinos, String asunto, List<String> body, String archivoAdjunto)
	{
	  	int send = -1;
		try
		 {
			String all_body = "";
			for(String b:body){
				all_body += b;
			}
			Authenticator auth = new Authenticator() {
		        @Override
		        protected PasswordAuthentication getPasswordAuthentication() {
		            // TODO Auto-generated method stub
		            return new PasswordAuthentication("comunicaciones@elrey.uy", "cOmunic2106");
		        }
		    };
			
			 String host = "Rellenar";
			    String from = "comunicaciones@elrey.uy";
			    
			
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
			    	
			    MimeMultipart multiPart = new MimeMultipart(); 
			    BodyPart texto = new MimeBodyPart();
			    texto.setText(all_body);
			    
			    multiPart.addBodyPart(texto);
			    if(!archivoAdjunto.equals("")) {
			    	BodyPart adjunto = new MimeBodyPart();
			    	
			    	adjunto.setDataHandler(new DataHandler(new FileDataSource(archivoAdjunto)));
			    	adjunto.setFileName("Factura.pdf");
			    	multiPart.addBodyPart(adjunto);
			    }
			    
			    message.setContent(multiPart);
			    
			    //message.setText(all_body, "utf-8", "html");
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			    send = 1;
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
		
		return send;
	
	}
		
  public static int enviarMailHTMLOD(String mailOrigen, List<String> mailDestinos, String asunto, List<String> body)
	{
	  	int send = -1;
		try
		 {
			String all_body = "";
			for(String b:body){
				all_body += b;
			}
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
			    
			    message.setText(all_body, "utf-8", "html");
			    session.setDebug(true);
			    // Send message
			    Transport.send(message);
			    send = 1;
			 
		 }
		 catch (Exception e) 
		 {
			 e.printStackTrace();
		 }
		
		return send;
	
	}		
}