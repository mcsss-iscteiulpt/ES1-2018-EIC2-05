package jUnitTests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;


import BDA.*;


/**
 * The Class MailTest.
 */
public class MailTest {

	/** The receiver. */
	private ReceiverOfMails receiver;
	
	/** The sender. */
	private SenderOfMails sender;

	/** The actual time stamp. */
	String actualTimeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
	
	/** The actual split time and date. */
	String[] actualSplitTimeAndDate = actualTimeStamp.split(" ");
	
	/** The actual date. */
	String actualDate = actualSplitTimeAndDate[0];
	
	/** The actual time. */
	String actualTime = actualSplitTimeAndDate[1];

	/** The actual split date. */
	String[] actualSplitDate = actualDate.split("/");
	
	/** The actual mounth. */
	String actualMounth = actualSplitDate[1];
	
	/** The actual year. */
	String actualYear = actualSplitDate[0];
	
	/** The cal. */
	Calendar cal = Calendar.getInstance();
	
	/** The actual week. */
	int actualWeek = cal.get(Calendar.WEEK_OF_YEAR);
	
	/** The actual day. */
	String actualDay = actualSplitDate[2];

	/** The actual split time. */
	String[] actualSplitTime = actualTime.split(":");
	
	/** The actual hour. */
	String actualHour = actualSplitTime[0];

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		this.receiver = new ReceiverOfMails();
		this.sender = new SenderOfMails();
		// Properties properties = new Properties();
		// properties.setProperty("mail.store.protocol", "imaps");
		// Session emailSession = Session.getDefaultInstance(properties);
		// Store emailStore = emailSession.getStore("imaps");
		// emailStore.connect("imap.gmail.com",
		// receiver.getEl().getElementsByTagName("username").item(0).getTextContent(),
		// receiver.getEl().getElementsByTagName("password").item(0).getTextContent());
		// getting inbox folder
		// Folder emailFolder = emailStore.getFolder("Inbox");
		// emailFolder.open(Folder.READ_ONLY);
		// Message messages[] = emailFolder.getMessages();
	}

	/**
	 * Test receiver.
	 */
	@Test
	public void testReceiver() {
		assertNotNull("should not be null", receiver);
		// fail("Not yet implemented");
	}

	/**
	 * Test sender.
	 */
	@Test
	public void testSender() {
		assertNotNull("should not be null", sender);
		// fail("Not yet implemented");
	}

	/**
	 * Test receive mails in general.
	 */
	@Test
	public void testReceiveMailsInGeneral() {

		receiver.receiveMailsInGeneral(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(),
				receiver.getEl().getElementsByTagName("password").item(0).getTextContent());
		assertTrue("there are mails", receiver.getnEmailsGeneral() > 0);

	}

	/**
	 * Test receive mails in api.
	 */
	@Test
	public void testReceiveMailsInApi() {

		receiver.receiveMailsOnApi(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(),
				receiver.getEl().getElementsByTagName("password").item(0).getTextContent());
		assertTrue("there are mails", receiver.getnEmailsAPI() > 0);

	}

	/**
	 * Test receive mails in month.
	 */
	@Test
	public void testReceiveMailsInMonth() {

		receiver.getMailsOnThisMounth(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(),
				receiver.getEl().getElementsByTagName("password").item(0).getTextContent(), actualMounth, actualYear);
		assertTrue("there are mails", receiver.getnEmailsMonth() > 0);

	}

	/**
	 * Test receive mails in week.
	 */
	@Test
	public void testReceiveMailsInWeek() {

		receiver.getMailsOnThisWeek(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(),
				receiver.getEl().getElementsByTagName("password").item(0).getTextContent(), actualWeek, actualMounth,
				actualYear);
		assertTrue("there are mails", receiver.getnEmailsWeek() > 0);
	}

	/**
	 * Test receive mails in day.
	 */
	@Test
	public void testReceiveMailsInDay() {

		receiver.getMailsOnThisDay(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(),
				receiver.getEl().getElementsByTagName("password").item(0).getTextContent(), actualDay, actualMounth,
				actualYear);
		assertTrue("there are mails", receiver.getnEmailsDay() > 0);

	}

	/**
	 * Test receive mails in hour.
	 */
	@Test
	public void testReceiveMailsInHour() {

		receiver.getMailsOnThisHour(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(),
				receiver.getEl().getElementsByTagName("password").item(0).getTextContent(), actualHour, actualDay,
				actualMounth, actualYear);
		assertTrue("there are mails", receiver.getnEmailsHour() > 0);

	}

}
