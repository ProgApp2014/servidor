package Controlador.Clases;
 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class EmailHelper {
 
	public EmailHelper(ArrayList<String> receptores, String asunto, String mensaje) {
 
		final String username = "direct.market.uy@gmail.com";
		final String password = "quegranpass";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("direct.market.uy@gmail.com"));
			Iterator it = receptores.iterator();
                        while(it.hasNext()){
                            String current = (String) it.next();
                            message.addRecipients(Message.RecipientType.TO,
				InternetAddress.parse(current));
                        }
			message.setSubject(asunto);
			message.setContent(mensaje,"text/html");
 
			Transport.send(message);
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
