package project.mail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import org.jsoup.Jsoup;

public class ReceiverOfMails {
	
	private ArrayList<String>content=new ArrayList<String>();
	
	public Object[][] receiveMailsInGeneral(String username, String password)	{
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
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, };

		try {
			Properties properties = new Properties();
			properties.setProperty("mail.store.protocol", "imaps");
			Session emailSession = Session.getDefaultInstance(properties);
			Store emailStore = emailSession.getStore("imaps");
			emailStore.connect("imap.gmail.com", username, password);
			// getting inbox folder
			Folder emailFolder = emailStore.getFolder("Inbox");
			emailFolder.open(Folder.READ_ONLY);
			Message messages[] = emailFolder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				data[i][0] = "Mail";
//				System.out.println("Email Number: " + i);
				data[(messages.length-1)-i][2] = message.getSubject();
//				System.out.println("Subject: " + message.getSubject());
				data[(messages.length-1)-i][4] = message.getFrom();
//				System.out.println("From: " + message.getFrom()[0]);
				data[(messages.length-1)-i][1] = convertTime(message.getSentDate().getTime());
//				System.out.println("Sent Date: " + message.getSentDate());
				try {
//					System.out.println("Content: " + getTextFromMessage(message));
					data[(messages.length-1)-i][3] = getTextFromMessage(message);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		return data;

	}

	public Object[][] receiveMailsOnApi(String username, String password) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, };
		try {
			Properties properties = new Properties();
			properties.setProperty("mail.store.protocol", "imaps");
			Session emailSession = Session.getDefaultInstance(properties);
			Store emailStore = emailSession.getStore("imaps");
			emailStore.connect("imap.gmail.com", username, password);
			// getting inbox folder
			Folder emailFolder = emailStore.getFolder("Inbox");
			emailFolder.open(Folder.READ_ONLY);
			Message messages[] = emailFolder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
//				System.out.println("Email Number: " + i);
				data[(messages.length-1)-i][1] = message.getSubject();
//				System.out.println("Subject: " + message.getSubject());
				data[(messages.length-1)-i][3] = message.getFrom();
//				System.out.println("From: " + message.getFrom()[0]);
				data[(messages.length-1)-i][0] = convertTime(message.getSentDate().getTime());
//				System.out.println("Sent Date: " + message.getSentDate());
				try {
//					System.out.println("Content: " + getTextFromMessage(message));
					data[(messages.length-1)-i][2] = getTextFromMessage(message);
					content.add(getTextFromMessage(message));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		return data;

	}

	public static String getTextFromMessage(Message message) throws Exception {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			String result = "";
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			int count = mimeMultipart.getCount();
			for (int i = 0; i < count; i++) {
				BodyPart bodyPart = mimeMultipart.getBodyPart(i);
				if (bodyPart.isMimeType("text/plain")) {
					result = result + "\n" + bodyPart.getContent();
					break; // without break same text appears twice in my tests
				} else if (bodyPart.isMimeType("text/html")) {
					String html = (String) bodyPart.getContent();
					result = result + "\n" + Jsoup.parse(html).text();

				}
			}

			return result;
		}
		return "";
	}

	public String convertTime(long time) {

		SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		convert.setTimeZone(TimeZone.getTimeZone("GMT"));
		String mailSendTime = convert.format(time);

		return mailSendTime;
	}

	public Object[][] getMailsOnThisHour(String username, String password, String currentHour, String currentDay,
			String currentMounth, String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, };
		try {
			Properties properties = new Properties();
			properties.setProperty("mail.store.protocol", "imaps");
			Session emailSession = Session.getDefaultInstance(properties);
			Store emailStore = emailSession.getStore("imaps");
			emailStore.connect("imap.gmail.com", username, password);
			// getting inbox folder
			Folder emailFolder = emailStore.getFolder("Inbox");
			emailFolder.open(Folder.READ_ONLY);
			Message messages[] = emailFolder.getMessages();

			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];

				String timeAndDate = convertTime(message.getSentDate().getTime());

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
					data[(messages.length-1)-i][1] = message.getSubject();
					data[(messages.length-1)-i][3] = message.getFrom();
					data[(messages.length-1)-i][0] = convertTime(message.getSentDate().getTime());
					try {
						data[(messages.length-1)-i][2] = getTextFromMessage(message);

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
		return data;
	}

	public Object[][] getMailsOnThisDay(String username, String password, String currentDay, String currentMounth,
			String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, };
		try {
			Properties properties = new Properties();
			properties.setProperty("mail.store.protocol", "imaps");
			Session emailSession = Session.getDefaultInstance(properties);
			Store emailStore = emailSession.getStore("imaps");
			emailStore.connect("imap.gmail.com", username, password);
			// getting inbox folder
			Folder emailFolder = emailStore.getFolder("Inbox");
			emailFolder.open(Folder.READ_ONLY);
			Message messages[] = emailFolder.getMessages();

			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];

				String timeAndDate = convertTime(message.getSentDate().getTime());

				String[] mailSplitTimeAndDate = timeAndDate.split(" ");

				String mailActualData = mailSplitTimeAndDate[0];
				String[] mailActualSplitData = mailActualData.split("/");
				String mailActualDay = mailActualSplitData[2];
				String mailActualMounth = mailActualSplitData[1];
				String mailActualYear = mailActualSplitData[0];

				if (currentDay.equals(mailActualDay) && currentMounth.equals(mailActualMounth)
						&& currentYear.equals(mailActualYear)) {
					data[(messages.length-1)-i][1] = message.getSubject();
					data[(messages.length-1)-i][3] = message.getFrom();
					data[(messages.length-1)-i][0] = convertTime(message.getSentDate().getTime());
					try {
						data[(messages.length-1)-i][2] = getTextFromMessage(message);

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
		return data;
	}

	public Object[][] getMailsOnThisMounth(String username, String password, String currentMounth, String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, };
		try {
			Properties properties = new Properties();
			properties.setProperty("mail.store.protocol", "imaps");
			Session emailSession = Session.getDefaultInstance(properties);
			Store emailStore = emailSession.getStore("imaps");
			emailStore.connect("imap.gmail.com", username, password);
			// getting inbox folder
			Folder emailFolder = emailStore.getFolder("Inbox");
			emailFolder.open(Folder.READ_ONLY);
			Message messages[] = emailFolder.getMessages();

			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];

				String timeAndDate = convertTime(message.getSentDate().getTime());

				String[] mailSplitTimeAndDate = timeAndDate.split(" ");
				String mailActualData = mailSplitTimeAndDate[0];
				String[] mailActualSplitData = mailActualData.split("/");
				String mailActualMounth = mailActualSplitData[1];
				String mailActualYear = mailActualSplitData[0];

				if (currentMounth.equals(mailActualMounth) && currentYear.equals(mailActualYear)) {
					data[(messages.length-1)-i][1] = message.getSubject();
					data[(messages.length-1)-i][3] = message.getFrom();
					data[(messages.length-1)-i][0] = convertTime(message.getSentDate().getTime());
					try {
						data[(messages.length-1)-i][2] = getTextFromMessage(message);

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
		return data;
	}

	public Object[][] getMailsOnThisWeek(String username, String password, int currentWeek, String currentMounth,
			String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, };
		try {
			Properties properties = new Properties();
			properties.setProperty("mail.store.protocol", "imaps");
			Session emailSession = Session.getDefaultInstance(properties);
			Store emailStore = emailSession.getStore("imaps");
			emailStore.connect("imap.gmail.com", username, password);
			// getting inbox folder
			Folder emailFolder = emailStore.getFolder("Inbox");
			emailFolder.open(Folder.READ_ONLY);
			Message messages[] = emailFolder.getMessages();

			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];

				java.util.Date date = message.getSentDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int week = cal.get(Calendar.WEEK_OF_YEAR);

				String timeAndDate = convertTime(message.getSentDate().getTime());

				String[] mailSplitTimeAndDate = timeAndDate.split(" ");

				String mailActualData = mailSplitTimeAndDate[0];
				String[] mailActualSplitData = mailActualData.split("/");
				String mailActualMounth = mailActualSplitData[1];
				String mailActualYear = mailActualSplitData[0];

				if (currentWeek == week && currentMounth.equals(mailActualMounth)
						&& currentYear.equals(mailActualYear)) {
					data[(messages.length-1)-i][1] = message.getSubject();
					data[(messages.length-1)-i][3] = message.getFrom();
					data[(messages.length-1)-i][0] = convertTime(message.getSentDate().getTime());
					try {
						data[(messages.length-1)-i][2] = getTextFromMessage(message);

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
		return data;
	}
	
	
	public Object[][] searchWordInGmail(String word)	{
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, };
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
			for (int i = 0; i < messages.length; i++) {
				
				Message message = messages[i];
				if(message.getSubject().contains(word))	{
//				System.out.println("Email Number: " + i);
				data[(messages.length-1)-i][1] = message.getSubject();
//				System.out.println("Subject: " + message.getSubject());
				data[(messages.length-1)-i][3] = message.getFrom();
//				System.out.println("From: " + message.getFrom()[0]);
				data[(messages.length-1)-i][0] = convertTime(message.getSentDate().getTime());
//				System.out.println("Sent Date: " + message.getSentDate());
				try {
//					System.out.println("Content: " + getTextFromMessage(message));
					data[(messages.length-1)-i][2] = getTextFromMessage(message);

				}
				catch (Exception e) {
					// TODO Auto-generated catch block
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
		return data;
	}

	public ArrayList<String> getContent()	{
		return content;
	}
	
	public static void main(String[] args) {
		ReceiverOfMails rc=new ReceiverOfMails();
		rc.receiveMailsOnApi("es1.eic2.5@gmail.com", "MiguelNeto15");
	}
}
