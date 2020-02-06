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

        final String username = "allenati-al-var@libero.it";
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
            message.setFrom(new InternetAddress("allenati-al-var@libero.it"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            ); 
            message.setSubject("Recupero password Allenati al VAR");
            message.setText("Gentile " + DBManager.getInstance().getNomeUtentePerEmail(destinatario) + "," +
                     "\n\n la sua password è: " + DBManager.getInstance().getPasswordUtentePerEmail(destinatario) +
            "\n\n Cordiali saluti," +
            "\n Il team di Allenati al VAR");

            Transport.send(message);

          //  System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
	public void inviaMailNuovaPassword(String destinatario) {

        final String username = "allenati-al-var@libero.it";
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
            message.setFrom(new InternetAddress("allenati-al-var@libero.it"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)
            ); 
            message.setSubject("Password modificata Allenati al VAR");
            message.setText("Gentile " + DBManager.getInstance().getNomeUtentePerEmail(destinatario) +
                     "\n\n la sua password è stata modificata correttamente." +
            "\n\n Cordiali saluti," +
            "\n Il team di Allenati al VAR");

            Transport.send(message);

          //  System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

	
	
}
