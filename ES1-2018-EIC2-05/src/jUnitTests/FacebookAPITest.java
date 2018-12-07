package jUnitTests;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.restfb.FacebookClient;

import BDA.FacebookAPI;




/**
 * The Class FacebookAPITest.
 */
class FacebookAPITest {
	
	/** The facebook API. */
	private FacebookAPI facebookAPI;
	
	/** The fb client. */
	private FacebookClient fbClient;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		this.facebookAPI = new FacebookAPI();
		this.fbClient = facebookAPI.getFbClient();
	}

	/**
	 * Test facebook API.
	 */
	@Test
	public void testFacebookAPI() {
		assertNotNull("should not be null", fbClient);
		//fail("Not yet implemented");
	}
	
	

}
