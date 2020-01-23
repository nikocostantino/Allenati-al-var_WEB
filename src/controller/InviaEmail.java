package controller;

import javax.mail.*;
import javax.mail.internet.*;

import persistence.DBManager;

import java.util.*;

public class InviaEmail {
	private static InviaEmail i = null;
	
	private InviaEmail()
	{
		
	}
	
	public static InviaEmail getInstance()
	{
		if(i==null)
			i=new InviaEmail();
		
		return i;
	}
	
	
	public void inviaMail(String destinatario) {

        final String username = "allenatialvar@libero.it";
        final String password = "allenatialvarronaldo";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.libero.it");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("allenatialvar@libero.it"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            ); 
            message.setSubject("Recupero password Allenati al VAR");
            message.setText("Gentile " + DBManager.getInstance().getNomeUtentePerEmail(destinatario) +
                     "\n\n la sua password è: " + DBManager.getInstance().getPasswordUtentePerEmail(destinatario) +
            "\n\n Cordiali saluti," +
            "\n Il team di Allenati al VAR");

            Transport.send(message);

          //  System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

	
	/*
	public void inviaMail(String destinatario, String subject, String message) throws MessagingException
	{
	  boolean debug = false;
	 
	  // Impostazioni SMTP
	  Properties props = new Properties();
	  props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");	 
	  // istanzio un oggetto Session
	  Session session = Session.getDefaultInstance(props, null);
	  session.setDebug(debug);
	 
	  // creo l'oggetto Message partendo da Session
	  Message msg = new MimeMessage(session);
	 
	  // Definisco mittente
	  InternetAddress addressFrom = new InternetAddress("nicocostantino8@gmail.com");
	  msg.setFrom(addressFrom);
	 
	  // Destinatari
	  InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
	  for (int i = 0; i < recipients.length; i++)
	  {
	    addressTo[i] = new InternetAddress(recipients[i]);
	  }
	
	  InternetAddress addressTo = new InternetAddress();
	    addressTo = new InternetAddress(destinatario);

	  
	  
	  
	 msg.setRecipient(Message.RecipientType.TO, addressTo);
	    
	  // OPZIONALE: è possibile definire anche dei custom headers...
	  msg.addHeader("Brunooo", "Brunellooo");
	 
	  // Imposto il subject, il contenuto ed il content type (testo semplice)
	  msg.setSubject(subject);
	  msg.setContent(message, "text/plain");
	  
	  // Spedisco
	  Transport.send(msg);
	}*/
}
