package project.twitter;

import java.util.Scanner;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class sendTweets {

	
	public sendTweets() {
		
	}
	
	public static void main(String[] args) {
		
		try {
			
			Scanner in = new Scanner(System.in);
			System.out.println("Escreva o status que pretende postar: ");
			String latestStatus = in.nextLine();
			
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("9R0h5yjbeJVmcrgslBdhBYZw1")
					.setOAuthConsumerSecret("GgtCoSHsQY4wEJmasj8GkBEmwoDcVYZJRV07FamfONcKVYEgDx")
					.setOAuthAccessToken("1051492546516570112-NBX08c31mPvjTfT3yUe5tNSc1neLUK")
					.setOAuthAccessTokenSecret("hNIpSQxitQPdt8qZDiwGfqn19iCZVkNcONBUWGB7yZvtv");

			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
		    Status status = twitter.updateStatus(latestStatus);
		    System.out.println("Successfully updated the status to [" + status.getText() + "].");
		    
		    in.close();
		} 
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
