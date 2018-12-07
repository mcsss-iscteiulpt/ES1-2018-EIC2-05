package tests.mail;


	

	import static org.junit.Assert.*;

	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.junit.Before;
	import org.junit.Test;

import com.restfb.types.send.OpenGraphTemplatePayload.Element;

import project.mail.*;
	
	
	



	public class MailTest {
		
		private ReceiverOfMails receiver;
		private SenderOfMails sender;
		private Element el;
		
		String actualTimeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		String[] actualSplitTimeAndDate = actualTimeStamp.split(" ");
		String actualDate = actualSplitTimeAndDate[0];
		String actualTime = actualSplitTimeAndDate[1];

		String[] actualSplitDate = actualDate.split("/");
		String actualMounth = actualSplitDate[1];
		String actualYear=actualSplitDate[0];
		Calendar cal = Calendar.getInstance();
	    int actualWeek = cal.get(Calendar.WEEK_OF_YEAR);
		String actualDay = actualSplitDate[2];

		String[] actualSplitTime = actualTime.split(":");
		String actualHour = actualSplitTime[0];
		
		@Before
		public void setUp() throws Exception {
		this.receiver = new ReceiverOfMails();
		this.sender = new SenderOfMails();
		//Properties properties = new Properties();
		//properties.setProperty("mail.store.protocol", "imaps");
		//Session emailSession = Session.getDefaultInstance(properties);
		//Store emailStore = emailSession.getStore("imaps");
		//emailStore.connect("imap.gmail.com", receiver.getEl().getElementsByTagName("username").item(0).getTextContent(), receiver.getEl().getElementsByTagName("password").item(0).getTextContent());
		// getting inbox folder
		//Folder emailFolder = emailStore.getFolder("Inbox");
		//emailFolder.open(Folder.READ_ONLY);
		//Message messages[] = emailFolder.getMessages();
		}
		
		@Test
		public void testReceiver() {
			assertNotNull("should not be null", receiver);
			//fail("Not yet implemented");
		}
		
		@Test
		public void testSender() {
			assertNotNull("should not be null", sender);
			//fail("Not yet implemented");
		}
		
		@Test
		public void testReceiveMailsInGeneral() {
			
		receiver.receiveMailsInGeneral(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(), receiver.getEl().getElementsByTagName("password").item(0).getTextContent());
		assertTrue ("there are mails", receiver.getnEmailsGeneral() > 0);	
		
		}
		
		@Test
		public void testReceiveMailsInApi() {
			
		receiver.receiveMailsOnApi(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(), receiver.getEl().getElementsByTagName("password").item(0).getTextContent());
		assertTrue ("there are mails", receiver.getnEmailsAPI() > 0);	
		
		}
		
		
		@Test
		public void testReceiveMailsInMonth() {
			
		receiver.getMailsOnThisMounth(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(), receiver.getEl().getElementsByTagName("password").item(0).getTextContent(),actualMounth,actualYear);
		assertTrue ("there are mails", receiver.getnEmailsMonth() > 0);	
		
		}
		
		@Test
		public void testReceiveMailsInWeek() {
			
			receiver.getMailsOnThisWeek(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(), receiver.getEl().getElementsByTagName("password").item(0).getTextContent(),actualWeek,actualMounth,actualYear);
			assertTrue ("there are mails", receiver.getnEmailsWeek() > 0);	
		}
			
		@Test
		public void testReceiveMailsInDay() {
			
		receiver.getMailsOnThisDay(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(), receiver.getEl().getElementsByTagName("password").item(0).getTextContent(),actualDay,actualMounth,actualYear);
		assertTrue ("there are mails", receiver.getnEmailsDay() > 0);	
		
		}
		
		@Test
		public void testReceiveMailsInHour() {
			
		receiver.getMailsOnThisHour(receiver.getEl().getElementsByTagName("username").item(0).getTextContent(), receiver.getEl().getElementsByTagName("password").item(0).getTextContent(),actualHour,actualDay,actualMounth,actualYear);
		assertTrue ("there are mails", receiver.getnEmailsHour() > 0);	
		
		}
		
		
		
	}

