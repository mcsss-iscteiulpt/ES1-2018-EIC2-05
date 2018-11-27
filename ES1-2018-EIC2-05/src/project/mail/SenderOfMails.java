package project.mail;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class SenderOfMails {

	public void sendMail(String toText, String subjectText, String messageText) {

		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String receiverEmail = toText;

		String subject = subjectText;

		String textMessage = messageText;

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("es1.eic2.5@gmail.com", "MiguelNeto15");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("es1.eic2.5@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
			message.setSubject(subject);
			message.setText(textMessage);
			Transport.send(message);
			System.out.println("Your message was delivered!");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		SenderOfMails sender = new SenderOfMails();
		sender.sendMail("", "", "");
	}

}
