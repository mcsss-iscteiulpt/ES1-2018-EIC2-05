package tests.twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.twitter.TwitterHandler;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterTest {

	private TwitterHandler twitterhandler;
	private Twitter twitter;
	
	@Before
	public void setUp() throws Exception {
		this.twitterhandler = new TwitterHandler();
		this.twitter = twitterhandler.getTwitter();
	}

	@Test
	public void testTwitterHandler() {
		assertNotNull("should not be null", twitter);
		//fail("Not yet implemented");
	}

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

}
