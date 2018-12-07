package project.mail;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * The Class ReceiverOfMails.
 */
public class ReceiverOfMails {

	
	/** The content. */
	private ArrayList<String>content=new ArrayList<String>();
	

	/** The e. */
	private static Element e;
	
	/** The n emails general. */
	public int nEmailsGeneral;
	
	/** The n emails API. */
	public int nEmailsAPI;
	
	/** The n emails hour. */
	public int nEmailsHour;
	
	/** The n emails day. */
	public int nEmailsDay;
	
	/** The n emails month. */
	public int nEmailsMonth;
	
	/** The n emails week. */
	public int nEmailsWeek;

	/**
	 * Instantiates a new receiver of mails.
	 */
	public ReceiverOfMails() {
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
	 * Receive mails in general.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the object[][]
	 */
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
				nEmailsGeneral++;
				Message message = messages[i];
				data[i][0] = "Mail";
				//				System.out.println("Email Number: " + i);
				data[(messages.length-1)-i][2] = message.getSubject();
				//				System.out.println("Subject: " + message.getSubject());
				data[(messages.length-1)-i][4] = message.getFrom()[0];
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

	/**
	 * Receive mails on api.
	 *
	 * @param username the username
	 * @param password the password
	 * @return the object[][]
	 */
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
				//System.out.println("Email Number: " + i);
				data[(messages.length-1)-i][1] = message.getSubject();
				//System.out.println("Subject: " + message.getSubject());
				data[(messages.length-1)-i][3] = message.getFrom();
				//System.out.println("From: " + message.getFrom()[0]);
				data[(messages.length-1)-i][0] = convertTime(message.getSentDate().getTime());
				//System.out.println("Sent Date: " + message.getSentDate());
				try {
					//System.out.println("Content: " + getTextFromMessage(message));
					data[(messages.length-1)-i][2] = getTextFromMessage(message);
					content.add(getTextFromMessage(message));
					nEmailsAPI++;
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

	/**
	 * Gets the text from message.
	 *
	 * @param message the message
	 * @return the text from message
	 * @throws Exception the exception
	 */
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

	/**
	 * Convert time.
	 *
	 * @param time the time
	 * @return the string
	 */
	public String convertTime(long time) {

		SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		convert.setTimeZone(TimeZone.getTimeZone("GMT"));
		String mailSendTime = convert.format(time);

		return mailSendTime;
	}

	/**
	 * Gets the mails on this hour.
	 *
	 * @param username the username
	 * @param password the password
	 * @param currentHour the current hour
	 * @param currentDay the current day
	 * @param currentMounth the current mounth
	 * @param currentYear the current year
	 * @return the mails on this hour
	 */
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
						nEmailsHour++;

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

	/**
	 * Gets the mails on this day.
	 *
	 * @param username the username
	 * @param password the password
	 * @param currentDay the current day
	 * @param currentMounth the current mounth
	 * @param currentYear the current year
	 * @return the mails on this day
	 */
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
						nEmailsDay++;

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

	/**
	 * Gets the mails on this mounth.
	 *
	 * @param username the username
	 * @param password the password
	 * @param currentMounth the current mounth
	 * @param currentYear the current year
	 * @return the mails on this mounth
	 */
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
						nEmailsMonth++;

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

	/**
	 * Gets the mails on this week.
	 *
	 * @param username the username
	 * @param password the password
	 * @param currentWeek the current week
	 * @param currentMounth the current mounth
	 * @param currentYear the current year
	 * @return the mails on this week
	 */
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
	

	
	/**
	 * Search word in gmail.
	 *
	 * @param word the word
	 * @return the object[][]
	 */
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

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public ArrayList<String> getContent()	{
		return content;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ReceiverOfMails rc=new ReceiverOfMails();
		rc.receiveMailsOnApi("es1.eic2.5@gmail.com", "MiguelNeto15");

	}	
	
	/**
	 * Gets the el.
	 *
	 * @return the el
	 */
	public Element getEl() {
		return e;

	}

	/**
	 * Gets the e.
	 *
	 * @return the e
	 */
	public static Element getE() {
		return e;
	}

	/**
	 * Gets the n emails general.
	 *
	 * @return the n emails general
	 */
	public int getnEmailsGeneral() {
		return nEmailsGeneral;
	}

	/**
	 * Gets the n emails API.
	 *
	 * @return the n emails API
	 */
	public int getnEmailsAPI() {
		return nEmailsAPI;
	}

	/**
	 * Gets the n emails hour.
	 *
	 * @return the n emails hour
	 */
	public int getnEmailsHour() {
		return nEmailsHour;
	}

	/**
	 * Gets the n emails day.
	 *
	 * @return the n emails day
	 */
	public int getnEmailsDay() {
		return nEmailsDay;
	}

	/**
	 * Gets the n emails month.
	 *
	 * @return the n emails month
	 */
	public int getnEmailsMonth() {
		return nEmailsMonth;
	}

	/**
	 * Gets the n emails week.
	 *
	 * @return the n emails week
	 */
	public int getnEmailsWeek() {
		return nEmailsWeek;
	}
}
