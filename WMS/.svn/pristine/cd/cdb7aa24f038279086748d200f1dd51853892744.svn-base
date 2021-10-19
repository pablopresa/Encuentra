package logica;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EnviaMailEx {
  public static void mail() throws Exception {
   /* if (args.length != 3) {
      System.err.println("Usage: java MailExample host from to");
      System.exit(-1);
    }
    */
	 try
	 {
		 String host = "10.108.0.2";
		    String from = "arreglos@stadium.com.uy";
		    String to = "gmonzon@cybe.local";
		
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
		    message.setSubject("The Subject");
		    message.setText("The Message");
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
