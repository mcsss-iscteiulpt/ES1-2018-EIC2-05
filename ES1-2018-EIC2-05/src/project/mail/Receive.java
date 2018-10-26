package project.mail;


import java.util.Properties;
import javax.mail.*;



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
	public static void main(String[] args)
	{
		receiveMail("es1.eic2.5@gmail.com","MiguelNeto15");
	}
}
