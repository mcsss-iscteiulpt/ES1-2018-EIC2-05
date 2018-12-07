package project.facebook;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;


class FacebookAPITest {
	
	private FacebookAPI facebookAPI;
	
	@Before
	public void setUp() throws Exception {
		this.facebookAPI = new FacebookAPI();
	}

	@Test
	public void testFacebookAPI() {
		assertNotNull("should not be null", facebookAPI);
		//fail("Not yet implemented");
	}
	
	public void testGetPostsInGeneral() {
		
		
		
		assertArrayEquals(expected, actual);
	}
	
	

}
