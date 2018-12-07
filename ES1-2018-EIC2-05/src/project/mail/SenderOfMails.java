package project.mail;

import javax.mail.*;
import javax.mail.internet.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * The Class SenderOfMails.
 */
public class SenderOfMails {
	
	/** The e. */
	private Element e;

	/**
	 * Instantiates a new sender of mails.
	 */
	public SenderOfMails() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Document document = null;
		try {
			document = builder.parse(new File("config.xml"));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.getDocumentElement().normalize();

		NodeList nList = document.getElementsByTagName("email");

		e = (Element) nList.item(0);

	}

	/**
	 * Send mail.
	 *
	 * @param toText the to text
	 * @param subjectText the subject text
	 * @param messageText the message text
	 */
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
				return new PasswordAuthentication(e.getElementsByTagName("username").item(0).getTextContent(), e.getElementsByTagName("password").item(0).getTextContent());
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(e.getElementsByTagName("username").item(0).getTextContent()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
			message.setSubject(subject);
			message.setText(textMessage);
			Transport.send(message);
			//System.out.println("Your message was delivered!");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}


}
