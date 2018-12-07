package jUnitTests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;

import BDA.TwitterHandler;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

// TODO: Auto-generated Javadoc
/**
 * The Class TwitterTest.
 */
public class TwitterTest {

	/** The twitterhandler. */
	private TwitterHandler twitterhandler;
	
	/** The twitter. */
	private Twitter twitter;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		this.twitterhandler = new TwitterHandler();
		this.twitter = twitterhandler.getTwitter();
	}

	/**
	 * Test twitter handler.
	 */
	@Test
	public void testTwitterHandler() {
		assertNotNull("should not be null", twitter);
		//fail("Not yet implemented");
	}

	/**
	 * Test tweets in general.
	 */
	@Test
	public void testTweetsInGeneral() {
		
		List<Status> status = null;
		try {
			status = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertSame("should be same", 20, status.size());
		//fail("Not yet implemented");
	}
	
	/**
	 * Test tweets on twitter API.
	 */
	@Test
	public void testTweetsOnTwitterAPI() {
		
		List<Status> status = null;
		try {
			status = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertSame("should be same", 20, status.size());
		//fail("Not yet implemented");
	}

	/**
	 * Test send tweet.
	 */
	@Test
	public void testSendTweet() {
		
		List<Status> status = null;
		List<String> statusS = new ArrayList<String>();
		String tweet = "";
		
		try {
			twitterhandler.sendTweet(tweet);
			status = twitter.getUserTimeline();
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Status stat: status) {
			statusS.add(stat.getText());
		}
		
		assertEquals("success", tweet, statusS.contains(tweet));
		//fail("Not yet implemented");
	}
	
	/**
	 * Test get twitter.
	 */
	@Test
	public void testGetTwitter() {
		
		assertNotNull("should not be null", twitter);
		
	}	
	
	/**
	 * Test convert time.
	 */
	@Test
	public void testConvertTime() {
		
		List<Status> status = null;
		List<String> statusT = new ArrayList<String>();
		
		try {
			status = twitter.getHomeTimeline();
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Status stat: status) {
			SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			convert.setTimeZone(TimeZone.getTimeZone("GMT"));
			String tweetTime = convert.format(stat.getCreatedAt().getTime());
			statusT.add(tweetTime);
		}
		
		assertSame("should be same", 20, statusT.size());
		
	}
	
	
	/**
	 * Test timestamps.
	 */
	@Test
	public void testTimestamps() {
		
		List<Status> status = null;
		List<String> statusT = new ArrayList<String>();
		
		try {
			status = twitter.getHomeTimeline();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		for (Status stat: status) {
			SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			convert.setTimeZone(TimeZone.getTimeZone("GMT"));
			String tweetTime = convert.format(stat.getCreatedAt().getTime());
			statusT.add(tweetTime);
		}
		
		assertSame("should be same", 20, statusT.size());
		
	}	
	
	/**
	 * Test get twitters on this hour.
	 */
	@Test
	public void testGetTwittersOnThisHour() {
		
		List<Status> status = null;
		List<Integer> statusT = new ArrayList<Integer>();
		
		try {
			status = twitter.getHomeTimeline();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String actualTimeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		
		String[] actualSplitTimeAndDate = actualTimeStamp.split(" ");
		String actualTime = actualSplitTimeAndDate[1];
		String[] actualSplitTime = actualTime.split(":");
		String actualHour = actualSplitTime[0];
		
		int actualHourI = Integer.valueOf(actualHour);
		
		for (Status stat: status) {
			SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			convert.setTimeZone(TimeZone.getTimeZone("GMT"));
			String tweetTime = convert.format(stat.getCreatedAt().getTime());
			
			String[] twitterSplitTimeAndDate = tweetTime.split(" ");
			String twitterActualTime = twitterSplitTimeAndDate[1];
			String[] twitterActualSplitTime = twitterActualTime.split(":");
			String twitterActualHour = twitterActualSplitTime[0];
			
			int twitterActualHourI = Integer.valueOf(twitterActualHour);
			
			statusT.add(twitterActualHourI);
		}
		
		assertTrue("should be true", statusT.get(0) < actualHourI);
		
	}

	
	/**
	 * Test get twitters on this day.
	 */
	@Test
	public void testGetTwittersOnThisDay() {
		
		List<Status> status = null;
		List<Integer> statusT = new ArrayList<Integer>();
		
		try {
			status = twitter.getHomeTimeline();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String actualTimeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		
		String[] actualSplitTimeAndDate = actualTimeStamp.split(" ");
		String actualData = actualSplitTimeAndDate[1];
		String[] actualSplitData = actualData.split("/");
		String actualDay = actualSplitData[2];
		
		int actualDayI = Integer.valueOf(actualDay);
		
		for (Status stat: status) {
			SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			convert.setTimeZone(TimeZone.getTimeZone("GMT"));
			String tweetTime = convert.format(stat.getCreatedAt().getTime());
			
			String[] twitterSplitTimeAndDate = tweetTime.split(" ");
			String twitterActualData = twitterSplitTimeAndDate[0];
			String[] twitterActualSplitData = twitterActualData.split("/");
			String twitterActualDay = twitterActualSplitData[2];
			
			int twitterActualDayI = Integer.valueOf(twitterActualDay);
			
			statusT.add(twitterActualDayI);
		}
		
		assertTrue("should be true", statusT.contains(actualDayI));
		
	}
	
	
	/**
	 * Test get twitters on this week.
	 */
	@Test
	public void testGetTwittersOnThisWeek() {
		
		List<Status> status = null;
		List<Integer> statusT = new ArrayList<Integer>();
		
		try {
			status = twitter.getHomeTimeline();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	
		
		Calendar cal = Calendar.getInstance();
	    int actualWeek = cal.get(Calendar.WEEK_OF_YEAR);
		
		for (Status stat: status) {
			Date twitterdate = stat.getCreatedAt();
			Calendar calend = Calendar.getInstance();
			calend.setTime(twitterdate);
			int twitterWeek = calend.get(Calendar.WEEK_OF_YEAR);
			
			statusT.add(twitterWeek);
		}
		
		assertTrue("should be true", statusT.contains(actualWeek));
	
	}
	
	
	/**
	 * Test get twitters on this month.
	 */
	@Test
	public void testGetTwittersOnThisMonth() {
		
		List<Status> status = null;
		List<Integer> statusT = new ArrayList<Integer>();
		
		try {
			status = twitter.getHomeTimeline();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		String actualTimeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		
		String[] actualSplitTimeAndDate = actualTimeStamp.split(" ");
		String actualDate = actualSplitTimeAndDate[0];
		String[] actualSplitDate = actualDate.split("/");
		String actualMonth = actualSplitDate[1];
		
		int actualMonthI = Integer.valueOf(actualMonth);
		
		for (Status stat: status) {
			
			SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			convert.setTimeZone(TimeZone.getTimeZone("GMT"));
			String tweetTime = convert.format(stat.getCreatedAt().getTime());
			
			String[] twitterSplitTimeAndDate = tweetTime.split(" ");
			String twitterActualData = twitterSplitTimeAndDate[0];
			String[] twitterActualSplitData = twitterActualData.split("/");
			String twitterActualMonth = twitterActualSplitData[1];
			
			int twitterActualMonthI = Integer.valueOf(twitterActualMonth);
		
			statusT.add(twitterActualMonthI);
		
		}
		
		assertTrue("should be true", statusT.contains(actualMonthI));
		
	}
	
	/**
	 * Test search word in tweet.
	 */
	@Test
	public void testSearchWordInTweet() {
		
		List<Status> status = null;
		List<String> statusS = new ArrayList<String>();
		
		try {
			status = twitter.getHomeTimeline();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		for(Status stat: status) {
			if (stat.getText().contains("a")) {
				statusS.add(stat.getText());
			}
		}
		
		assertTrue("should be true", statusS.get(0).contains("a"));
		
	}
	
	/**
	 * Test get content.
	 */
	@Test
	public void testGetContent() {
		
		List<Status> status = null;
		List<String> statusS = new ArrayList<String>();
		
		try {
			status = twitter.getHomeTimeline();
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		for(Status stat: status) {
			
			statusS.add(stat.getText());
		
		}

		assertNotNull("should not be null", statusS.get(0));
				
	}
}
