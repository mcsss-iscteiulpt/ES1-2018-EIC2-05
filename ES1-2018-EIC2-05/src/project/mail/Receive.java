package project.mail;



import java.util.Properties;
import javax.mail.*;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;




public class Receive {
	public static void receiveMail(String username, String password)
	{
		try{
			Properties properties = new Properties();
			properties.setProperty("mail.store.protocol", "imaps");
			Session emailSession = Session.getDefaultInstance(properties);
			Store emailStore = emailSession.getStore("imaps");
			emailStore.connect("imap.gmail.com",username,password);
			//getting inbox folder 
			Folder emailFolder = emailStore.getFolder("Inbox");
			emailFolder.open(Folder.READ_ONLY);
			Message messages[] = emailFolder.getMessages();
			for(int i = 0;  i < messages.length; i++)
			{
				Message message = messages[i];
				System.out.println("Email Number: " + i);
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Sent Date: " + message.getSentDate());
				try {
					System.out.println("Content: " + getTextFromMessage(message) );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//closing email folder
			emailFolder.close(false);
			emailStore.close();
		}catch(NoSuchProviderException nspe)
		{
			nspe.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String getTextFromMessage(Message message) throws Exception {
	    if (message.isMimeType("text/plain")){
	        return message.getContent().toString();
	    }else if (message.isMimeType("multipart/*")) {
	        String result = "";
	        MimeMultipart mimeMultipart = (MimeMultipart)message.getContent();
	        int count = mimeMultipart.getCount();
	        for (int i = 0; i < count; i ++){
	            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	            if (bodyPart.isMimeType("text/plain")){
	                result = result + "\n" + bodyPart.getContent();
	                break;  //without break same text appears twice in my tests
	            } else if (bodyPart.isMimeType("text/html")){
	                String html = (String) bodyPart.getContent();
	                result = result + "\n" + Jsoup.parse(html).text();

	            }
	        }
	        
	        return result;
	    }
	    return "";
	}
	
	
	public static void main(String[] args)
	{
		receiveMail("es1.eic2.5@gmail.com","MiguelNeto15");
	}
}
