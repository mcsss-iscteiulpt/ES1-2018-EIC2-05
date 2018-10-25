package project.twitter;

import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class twitterTimeline {

	private int counter = 0;
	private int counterTotal = 0;

	public Object[][] tweets() {

		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, };

		try {

			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("9R0h5yjbeJVmcrgslBdhBYZw1")
					.setOAuthConsumerSecret("GgtCoSHsQY4wEJmasj8GkBEmwoDcVYZJRV07FamfONcKVYEgDx")
					.setOAuthAccessToken("1051492546516570112-NBX08c31mPvjTfT3yUe5tNSc1neLUK")
					.setOAuthAccessTokenSecret("hNIpSQxitQPdt8qZDiwGfqn19iCZVkNcONBUWGB7yZvtv");

			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter twitter = tf.getInstance();
			List<Status> statuses = twitter.getHomeTimeline();
			System.out.println("------------------------\n Showing home timeline \n------------------------");

			int i = 0;
			for (Status status : statuses) {
				data[i][0] = "Twitter";
				data[i][1] = (String.valueOf(status.getCreatedAt().getTime()));
				data[i][2] = (status.getText());
				data[i][3] = (status.getUser().getName());

				i++;
				// Filters only tweets from user "ISCTE"
//				if (status.getUser().getName() != null && status.getUser().getName().contains("ISCTE")) {
//					
//					counter++;
//				}

				System.out.println(
						status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
				counterTotal++;

				System.out.println(counterTotal);

			}

			// System.out.println("-------------\nNº of Results: " +
			// counter+"/"+counterTotal);

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;

	}

	public int getInitailCounter() {
		return counter;
	}

	public int getTotalCounter() {
		return counterTotal;
	}

	public static void main(String[] args) {
		twitterTimeline tweetTimeline = new twitterTimeline();
		tweetTimeline.tweets();
	}

}
