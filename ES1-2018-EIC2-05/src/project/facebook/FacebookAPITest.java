package project.facebook;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.restfb.FacebookClient;

import project.twitter.TwitterHandler;
import twitter4j.Twitter;


class FacebookAPITest {
	
	private FacebookAPI facebookAPI;
	private FacebookClient fbClient;
	
	@Before
	public void setUp() throws Exception {
		this.facebookAPI = new FacebookAPI();
		this.fbClient = facebookAPI.getFbClient();
	}

	@Test
	public void testFacebookAPI() {
		assertNotNull("should not be null", fbClient);
		//fail("Not yet implemented");
	}
	
	

}
