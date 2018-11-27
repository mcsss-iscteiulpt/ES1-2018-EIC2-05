package project.gui;

import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import com.restfb.types.User;

import project.facebook.FacebookAPI;
import project.mail.ReceiverOfMails;
import project.twitter.TwitterHandler;
import twitter4j.Status;

public class AnalyseData {

	protected static TwitterHandler twitterHandler;
	protected static ReceiverOfMails mailHandler;
	protected static FacebookAPI facebookHandler;

	public Object[][] analyseData() {
		twitterHandler=new TwitterHandler();
		mailHandler=new ReceiverOfMails();
		facebookHandler=new FacebookAPI();
		Object[][] data = { { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, };
		
		
		try {

			List<Status> statuses = twitterHandler.getTwitter().getHomeTimeline();

			int i = 0;
			
			for (Status status : statuses) {
				data[i][0] = "Twitter";
				data[i][1] = (twitterHandler.convertTime(status.getCreatedAt().getTime()));
				data[i][2] = "No title On Twitters";
				data[i][3] = (status.getText());
				data[i][4] = (status.getUser().getName());

				i++;

				System.out.println(
						status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
			}
			
			int j=i;
			
			try {
				Properties properties = new Properties();
				properties.setProperty("mail.store.protocol", "imaps");
				Session emailSession = Session.getDefaultInstance(properties);
				Store emailStore = emailSession.getStore("imaps");
				emailStore.connect("imap.gmail.com", "es1.eic2.5@gmail.com", "MiguelNeto15");
				// getting inbox folder
				Folder emailFolder = emailStore.getFolder("Inbox");
				emailFolder.open(Folder.READ_ONLY);
				Message messages[] = emailFolder.getMessages();
				for (int a = 0; a < messages.length; a++) {
					Message message = messages[a];
					data[j][0] = "Mail";
//					System.out.println("Email Number: " + i);
					data[j][2] = message.getSubject();
//					System.out.println("Subject: " + message.getSubject());
					data[j][4] = message.getFrom();
//					System.out.println("From: " + message.getFrom()[0]);
					data[j][1] = mailHandler.convertTime(message.getSentDate().getTime());
//					System.out.println("Sent Date: " + message.getSentDate());
					try {
//						System.out.println("Content: " + getTextFromMessage(message));
						data[j][3] = ReceiverOfMails.getTextFromMessage(message);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					j++;
				}
				// closing email folder
				emailFolder.close(false);
				emailStore.close();
			} catch (NoSuchProviderException nspe) {
				nspe.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int k=j;
			String accessToken = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
			@SuppressWarnings("deprecation")
			FacebookClient fbClient = new DefaultFacebookClient(accessToken);
			User me = fbClient.fetchObject ("me", User.class);
			
			
			
	//teste com o perfil recentemente criado		
			
			System.out.println(me.getName());
			
	// App ID - 360660408005152
	// App secret - 2d0c83ae88e74968f5c93f905666dfc0
			
			
	//Extender o Access Token de 1h para 2 meses		
			//Recolher os posts do utilizador numa lista e imprimi-los na consola 	
			Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
			System.out.println("\nPosts:");
			int counter = 0;
			//int counterTotal = 0;
			for (List<Post> page : result) {
				for (Post aPost : page) {
					// Filters only posts that contain the word "M"
					//if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
						System.out.println("---- Post "+ counter + " ----");
						System.out.println("Id: "+"www.facebook.com/"+aPost.getId());
						System.out.println("Message: "+aPost.getMessage());
						System.out.println("Created: "+aPost.getCreatedTime());
						counter++;
						data[k][0] = "Facebook";
						data[k][1] = (facebookHandler.convertTime(aPost.getCreatedTime().getTime()));
						data[k][2]="No title On Facebbok Posts";
						data[k][3] = (aPost.getMessage());
						data[k][4] = (aPost.getId());

						k++;
					}
					//counterTotal++;
				}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		

		
	
		
	
		return data;

	}
	
	
	public static void main(String[] args) {
		AnalyseData an=new AnalyseData();
		an.analyseData();
	}
}
