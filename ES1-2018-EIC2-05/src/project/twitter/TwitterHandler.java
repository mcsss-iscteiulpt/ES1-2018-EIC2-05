package project.twitter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandler {

	Twitter twitter;
	
	/**
	 * Construtor da classe TwitterHandler:
	 * cria a instance necess�ria, atrav�s dos acesstokens e api keys e segredos, para poder usar as funcionalidades do twitter
	 */
	public TwitterHandler() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey("9R0h5yjbeJVmcrgslBdhBYZw1")
				.setOAuthConsumerSecret("GgtCoSHsQY4wEJmasj8GkBEmwoDcVYZJRV07FamfONcKVYEgDx")
				.setOAuthAccessToken("1051492546516570112-NBX08c31mPvjTfT3yUe5tNSc1neLUK")
				.setOAuthAccessTokenSecret("hNIpSQxitQPdt8qZDiwGfqn19iCZVkNcONBUWGB7yZvtv");

		TwitterFactory tf = new TwitterFactory(cb.build());
		this.twitter = tf.getInstance();
	}
	
	/**
	 * Devolve os tweets da nossa timeline do twitter, na nossa timeline geral da API
	 * @return tweets
	 */
	public Object[][] tweetsInGeneral() {

		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		try {

			List<Status> statuses = twitter.getHomeTimeline();

			int i = 0;
			for (Status status : statuses) {
				data[i][0] = "Twitter";
				data[i][1] = (convertTime(status.getCreatedAt().getTime()));
				data[i][2] = (status.getText());
				data[i][3] = (status.getUser().getName());

				i++;

				System.out.println(status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	/**
	 * Devolve os tweets da nossa timeline do twitter, na nossa timeline especifica da API Twitter
	 * @return tweets
	 */
	public Object[][] tweetsOnTwitterAPI() {

		Object[][] data = { { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
				{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
				{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
				{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
				{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" },
				{ "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, { "", "", "" }, };

		try {

			List<Status> statuses = twitter.getHomeTimeline();

			int i = 0;
			for (Status status : statuses) {
				data[i][0] = (convertTime(status.getCreatedAt().getTime()));
				data[i][1] = (status.getText());
				data[i][2] = (status.getUser().getName());

				i++;
				
				System.out.println(status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}
	
	/**
	 * Posta tweets na nossa conta de Twitter
	 * @param tweetText
	 */
	public void sendTweet(String tweetText) {
		try {

			Status status = twitter.updateStatus(tweetText);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Converte o tempo de publica��o do tweet que est� em millisegundos para o
	 * formato abaixo
	 * 
	 * @param time
	 * @return o tempo da publica��o do tweet no formato hh:mm MM/dd/yyyy
	 */
	private String convertTime(long time) {

		SimpleDateFormat convert = new SimpleDateFormat("hh:mm MM/dd/yyyy");
		convert.setTimeZone(TimeZone.getTimeZone("GMT"));
		String tweetTime = convert.format(time);

		return tweetTime;
	}
}