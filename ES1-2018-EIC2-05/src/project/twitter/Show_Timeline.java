package project.twitter;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Show_Timeline {

	public Show_Timeline() {

	}

	public static void main(String[] args) {
		
		try {
			
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			.setOAuthConsumerKey("9R0h5yjbeJVmcrgslBdhBYZw1")
			.setOAuthConsumerSecret("GgtCoSHsQY4wEJmasj8GkBEmwoDcVYZJRV07FamfONcKVYEgDx")
			.setOAuthAccessToken("1051492546516570112-NBX08c31mPvjTfT3yUe5tNSc1neLUK")
			.setOAuthAccessTokenSecret("hNIpSQxitQPdt8qZDiwGfqn19iCZVkNcONBUWGB7yZvtv");
			
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();        		
			List<Status> statuses = twitter.getHomeTimeline();
			System.out.println("------------------------\n Showing home timeline \n------------------------");
			
			int counter=0;
			int counterTotal = 0;
			
			List<String> nameUser = new ArrayList<String>();
			List<String> contentTweets = new ArrayList<String>();
			List<Long> timeTweet = new ArrayList<Long>();
			
			for (Status status : statuses) {
				
				nameUser.add(status.getUser().getName());
				contentTweets.add(status.getText());
				timeTweet.add(status.getCreatedAt().getTime());
				// Filters only tweets from user "ISCTE"
//				if (status.getUser().getName() != null && status.getUser().getName().contains("ISCTE")) {
//					
//					counter++;
//				}
				
				System.out.println(status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
				counterTotal++;
			}
			System.out.println("-------------\nN� of Results: " + counter+"/"+counterTotal);
		} catch (Exception e) { System.out.println(e.getMessage()); }
	}
}

