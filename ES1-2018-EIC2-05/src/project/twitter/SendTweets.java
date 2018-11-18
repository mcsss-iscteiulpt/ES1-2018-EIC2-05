package project.twitter;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SendTweets {

	public void sendTweet(String tweetText) {
		try {

			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("9R0h5yjbeJVmcrgslBdhBYZw1")
					.setOAuthConsumerSecret("GgtCoSHsQY4wEJmasj8GkBEmwoDcVYZJRV07FamfONcKVYEgDx")
					.setOAuthAccessToken("1051492546516570112-NBX08c31mPvjTfT3yUe5tNSc1neLUK")
					.setOAuthAccessTokenSecret("hNIpSQxitQPdt8qZDiwGfqn19iCZVkNcONBUWGB7yZvtv");

			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			Status status = twitter.updateStatus(tweetText);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		SendTweets sendTweets = new SendTweets();
		sendTweets.sendTweet("HelloWord");
	}
}
