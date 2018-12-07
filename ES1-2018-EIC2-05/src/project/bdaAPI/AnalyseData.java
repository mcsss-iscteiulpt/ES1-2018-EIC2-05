package project.bdaAPI;

import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;


import com.restfb.types.Post;


import project.facebook.FacebookAPI;
import project.mail.ReceiverOfMails;
import project.twitter.TwitterHandler;
import twitter4j.Status;

public class AnalyseData {

	protected static TwitterHandler twitterHandler;
	protected static ReceiverOfMails mailHandler;
	protected static FacebookAPI facebookHandler;

	public Object[][] analyseData() {
		twitterHandler = new TwitterHandler();
		mailHandler = new ReceiverOfMails();
		facebookHandler = new FacebookAPI();
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

			int j = i;

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
			int k = j;

			
			for (List<Post> page : facebookHandler.result) {
				for (Post aPost : page) {


					data[k][0] = "Facebook";
					data[k][1] = (facebookHandler.convertTime(aPost.getUpdatedTime().getTime()));
					data[k][2] = "No title On Facebbok Posts";
					data[k][3] = (aPost.getMessage());
					data[k][4] = (facebookHandler.me.getName());

					k++;
				}

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;

	}

	public Object[][] analyseDataAtThisHour(String currentHour, String currentDay, String currentMounth,
			String currentYear) {

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
				String timeAndDate = twitterHandler.convertTime(status.getCreatedAt().getTime());

				String[] twitterSplitTimeAndDate = timeAndDate.split(" ");
				String twitterActualData = twitterSplitTimeAndDate[0];
				String twitterActualTime = twitterSplitTimeAndDate[1];

				String[] twitterActualSplitTime = twitterActualTime.split(":");
				String twitterActualHour = twitterActualSplitTime[0];

				String[] twitterActualSplitData = twitterActualData.split("/");
				String twitterActualDay = twitterActualSplitData[2];
				String twitterActualMounth = twitterActualSplitData[1];
				String twitterActualYear = twitterActualSplitData[0];

				if (currentHour.equals(twitterActualHour) && currentDay.equals(twitterActualDay)
						&& currentMounth.equals(twitterActualMounth) && currentYear.equals(twitterActualYear)) {
					data[i][0] = (twitterHandler.convertTime(status.getCreatedAt().getTime()));
					data[i][1] = (status.getText());
					data[i][2] = (status.getUser().getName());
				}
				i++;

			}
			int j = i;

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

					String timeAndDate = mailHandler.convertTime(message.getSentDate().getTime());

					String[] mailSplitTimeAndDate = timeAndDate.split(" ");
					String mailActualData = mailSplitTimeAndDate[0];
					String mailActualTime = mailSplitTimeAndDate[1];

					String[] mailActualSplitTime = mailActualTime.split(":");
					String mailActualHour = mailActualSplitTime[0];

					String[] mailActualSplitData = mailActualData.split("/");
					String mailActualDay = mailActualSplitData[2];
					String mailActualMounth = mailActualSplitData[1];
					String mailActualYear = mailActualSplitData[0];

					if (currentHour.equals(mailActualHour) && currentDay.equals(mailActualDay)
							&& currentMounth.equals(mailActualMounth) && currentYear.equals(mailActualYear)) {
						data[(messages.length - 1) - j][1] = message.getSubject();
						data[(messages.length - 1) - j][3] = message.getFrom();
						data[(messages.length - 1) - j][0] = mailHandler.convertTime(message.getSentDate().getTime());
						try {
							data[(messages.length - 1) - j][2] = ReceiverOfMails.getTextFromMessage(message);

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

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

			int k = j;


			for (List<Post> page : facebookHandler.result) {
				for (Post aPost : page) {


					String timeAndDate = facebookHandler.convertTime(aPost.getUpdatedTime().getTime());

					String[] facebookSplitTimeAndDate = timeAndDate.split(" ");
					String facebookActualData = facebookSplitTimeAndDate[0];
					String facebookActualTime = facebookSplitTimeAndDate[1];

					String[] facebookActualSplitTime = facebookActualTime.split(":");
					String facebookActualHour = facebookActualSplitTime[0];

					String[] facebookActualSplitData = facebookActualData.split("/");
					String facebookActualDay = facebookActualSplitData[2];
					String facebookActualMounth = facebookActualSplitData[1];
					String facebookActualYear = facebookActualSplitData[0];

					if (currentHour.equals(facebookActualHour) && currentDay.equals(facebookActualDay)
							&& currentMounth.equals(facebookActualMounth) && currentYear.equals(facebookActualYear)) {

						data[k][0] = (facebookHandler.convertTime(aPost.getUpdatedTime().getTime()));
						data[k][1] = (aPost.getMessage());
						data[k][2] = (facebookHandler.me.getName());
					}
					k++;

				}

			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}

	
	public Object[][] analyseDataAtThis24Hours(String currentDay, String currentMounth, String currentYear) {

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
				String timeAndDate = (twitterHandler.convertTime(status.getCreatedAt().getTime()));
				String[] twitterSplitTimeAndDate = timeAndDate.split(" ");

				String twitterActualData = twitterSplitTimeAndDate[0];
				String[] twitterActualSplitData = twitterActualData.split("/");
				String twitterActualDay = twitterActualSplitData[2];
				String twitterActualMounth = twitterActualSplitData[1];
				String twitterActualYear = twitterActualSplitData[0];

				if (currentDay.equals(twitterActualDay) && currentMounth.equals(twitterActualMounth)
						&& currentYear.equals(twitterActualYear)) {
					data[i][0] = (twitterHandler.convertTime(status.getCreatedAt().getTime()));
					data[i][1] = (status.getText());
					data[i][2] = (status.getUser().getName());
				}
				i++;

			}

			int j = i;

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

					String timeAndDate = mailHandler.convertTime(message.getSentDate().getTime());

					String[] mailSplitTimeAndDate = timeAndDate.split(" ");

					String mailActualData = mailSplitTimeAndDate[0];
					String[] mailActualSplitData = mailActualData.split("/");
					String mailActualDay = mailActualSplitData[2];
					String mailActualMounth = mailActualSplitData[1];
					String mailActualYear = mailActualSplitData[0];

					if (currentDay.equals(mailActualDay) && currentMounth.equals(mailActualMounth)
							&& currentYear.equals(mailActualYear)) {
						data[(messages.length-1)-i][j] = message.getSubject();
						data[(messages.length-1)-i][j] = message.getFrom();
						data[(messages.length-1)-i][j] = mailHandler.convertTime(message.getSentDate().getTime());
						try {
							data[(messages.length-1)-j][2] = ReceiverOfMails.getTextFromMessage(message);

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

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

			int k = j;
			

			for (List<Post> page :facebookHandler.result) {
				for (Post aPost : page) {


					String timeAndDate = facebookHandler.convertTime(aPost.getUpdatedTime().getTime());
					String[] facebookSplitTimeAndDate = timeAndDate.split(" ");

					String facebookActualData = facebookSplitTimeAndDate[0];
					String[] facebookActualSplitData = facebookActualData.split("/");
					String facebookActualDay = facebookActualSplitData[2];
					String facebookActualMounth = facebookActualSplitData[1];
					String facebookActualYear = facebookActualSplitData[0];

					if (currentDay.equals(facebookActualDay) && currentMounth.equals(facebookActualMounth)
							&& currentYear.equals(facebookActualYear)) {

						data[k][0] = (facebookHandler.convertTime(aPost.getUpdatedTime().getTime()));
						data[k][1] = (aPost.getMessage());
						data[k][2] = (facebookHandler.me.getName());
					}
					k++;

				}

			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
	
	public Object[][] analyseDataAtThisMounth(String currentMounth, String currentYear) {

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
				String timeAndDate = twitterHandler.convertTime(status.getCreatedAt().getTime());
				String[] twitterSplitTimeAndDate = timeAndDate.split(" ");
				String twitterActualData = twitterSplitTimeAndDate[0];
				String[] twitterActualSplitData = twitterActualData.split("/");
				String twitterActualMounth = twitterActualSplitData[1];
				String twitterActualYear = twitterActualSplitData[0];

				if (currentMounth.equals(twitterActualMounth) && currentYear.equals(twitterActualYear)) {
					data[i][0] = (twitterHandler.convertTime(status.getCreatedAt().getTime()));
					data[i][1] = (status.getText());
					data[i][2] = (status.getUser().getName());
				}
				i++;

			
			}

			int j = i;

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
					Message message = messages[i];

					String timeAndDate = mailHandler.convertTime(message.getSentDate().getTime());

					String[] mailSplitTimeAndDate = timeAndDate.split(" ");
					String mailActualData = mailSplitTimeAndDate[0];
					String[] mailActualSplitData = mailActualData.split("/");
					String mailActualMounth = mailActualSplitData[1];
					String mailActualYear = mailActualSplitData[0];

					if (currentMounth.equals(mailActualMounth) && currentYear.equals(mailActualYear)) {
						data[(messages.length-1)-j][1] = message.getSubject();
						data[(messages.length-1)-j][3] = message.getFrom();
						data[(messages.length-1)-j][0] = mailHandler.convertTime(message.getSentDate().getTime());
						try {
							data[(messages.length-j)-i][2] = ReceiverOfMails.getTextFromMessage(message);

						} catch (Exception e) {
							e.printStackTrace();
						}

					}

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

			int k = j;


			for (List<Post> page : facebookHandler.result) {
				for (Post aPost : page) {
					

					String timeAndDate = facebookHandler.convertTime(aPost.getUpdatedTime().getTime());
					String[] facebookSplitTimeAndDate = timeAndDate.split(" ");
					String facebookActualData = facebookSplitTimeAndDate[0];
					String[] facebookActualSplitData = facebookActualData.split("/");
					String facebookActualMounth = facebookActualSplitData[1];
					String facebookActualYear = facebookActualSplitData[0];

					if (currentMounth.equals(facebookActualMounth) && currentYear.equals(facebookActualYear)) {

						data[k][0] = (facebookHandler.convertTime(aPost.getUpdatedTime().getTime()));
						data[k][1] = (aPost.getMessage());
						data[k][2] = (facebookHandler.me.getName());
					}
					k++;

				}

			}		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
	
	public Object[][] analyseDataAtThisWeek(int currentWeek, String currentMounth, String currentYear) {

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
				java.util.Date date = status.getCreatedAt();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int week = cal.get(Calendar.WEEK_OF_YEAR);

				String timeAndDate = twitterHandler.convertTime(status.getCreatedAt().getTime());
				String[] twitterSplitTimeAndDate = timeAndDate.split(" ");

				String twitterActualData = twitterSplitTimeAndDate[0];
				String[] twitterActualSplitData = twitterActualData.split("/");
				String twitterActualMounth = twitterActualSplitData[1];
				String twitterActualYear = twitterActualSplitData[0];

				if (currentWeek == week && currentMounth.equals(twitterActualMounth)
						&& currentYear.equals(twitterActualYear)) {
					data[i][0] = (twitterHandler.convertTime(status.getCreatedAt().getTime()));
					data[i][1] = (status.getText());
					data[i][2] = (status.getUser().getName());
				}
				i++;

			
			}

			int j = i;

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
					Message message = messages[i];

					java.util.Date date = message.getSentDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int week = cal.get(Calendar.WEEK_OF_YEAR);

					String timeAndDate = mailHandler.convertTime(message.getSentDate().getTime());

					String[] mailSplitTimeAndDate = timeAndDate.split(" ");

					String mailActualData = mailSplitTimeAndDate[0];
					String[] mailActualSplitData = mailActualData.split("/");
					String mailActualMounth = mailActualSplitData[1];
					String mailActualYear = mailActualSplitData[0];

					if (currentWeek == week && currentMounth.equals(mailActualMounth)
							&& currentYear.equals(mailActualYear)) {
						data[(messages.length-1)-j][1] = message.getSubject();
						data[(messages.length-1)-j][3] = message.getFrom();
						data[(messages.length-1)-j][0] = mailHandler.convertTime(message.getSentDate().getTime());
						try {
							data[(messages.length-1)-j][2] = ReceiverOfMails.getTextFromMessage(message);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

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

			int k = j;
			
			
			for (List<Post> page : facebookHandler.result) {
				for (Post aPost : page) {

					java.util.Date date = aPost.getUpdatedTime();
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int week = cal.get(Calendar.WEEK_OF_YEAR);

					String timeAndDate = facebookHandler.convertTime(aPost.getUpdatedTime().getTime());
					String[] facebookSplitTimeAndDate = timeAndDate.split(" ");

					String facebookActualData = facebookSplitTimeAndDate[0];
					String[] facebookActualSplitData = facebookActualData.split("/");
					String facebookActualMounth = facebookActualSplitData[1];
					String facebookActualYear = facebookActualSplitData[0];

					if (currentWeek == week && currentMounth.equals(facebookActualMounth)
							&& currentYear.equals(facebookActualYear)) {

						data[k][0] = (facebookHandler.convertTime(aPost.getUpdatedTime().getTime()));
						data[k][1] = (aPost.getMessage());
						data[k][2] = (facebookHandler.me.getName());
					}
					k++;

				}

			}		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
	
	
	public static void main(String[] args) {
		AnalyseData an = new AnalyseData();
		an.analyseData();
	}
}
