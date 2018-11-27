package project.twitter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterHandler {

	Twitter twitter;

	/**
	 * Construtor da classe TwitterHandler: cria a instance necessária, através dos
	 * acesstokens e api keys e segredos, para poder usar as funcionalidades do
	 * twitter
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
	 * Devolve os tweets da nossa timeline do twitter, na nossa timeline geral da
	 * API
	 * 
	 * @return tweets
	 */
	public Object[][] tweetsInGeneral() {

		Object[][] data = { { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, };

		try {

			List<Status> statuses = twitter.getHomeTimeline();

			int i = 0;
			for (Status status : statuses) {
				data[i][0] = "Twitter";
				data[i][1] = (convertTime(status.getCreatedAt().getTime()));
				data[i][2] = "No title On Twitters";
				data[i][3] = (status.getText());
				data[i][4] = (status.getUser().getName());

				i++;

				System.out.println(
						status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	/**
	 * Devolve os tweets da nossa timeline do twitter, na nossa timeline especifica
	 * da API Twitter
	 * 
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

				System.out.println(
						status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	/**
	 * Posta tweets na nossa conta de Twitter
	 * 
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
	 * Converte o tempo de publicação do tweet que está em millisegundos para o
	 * formato abaixo
	 * 
	 * @param time
	 * @return o tempo da publicação do tweet no formato hh:mm MM/dd/yyyy
	 */
	public String convertTime(long time) {

		SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		convert.setTimeZone(TimeZone.getTimeZone("GMT"));
		String tweetTime = convert.format(time);

		return tweetTime;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public ArrayList<String> timeStamps() {
		ArrayList<String> timeStamps = new ArrayList<String>();
		try {

			List<Status> statuses = twitter.getHomeTimeline();

			for (Status status : statuses) {
				timeStamps.add((convertTime(status.getCreatedAt().getTime())));
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return timeStamps;
	}

	public Object[][] getTwittersOnThisHour(String currentHour, String currentDay, String currentMounth,
			String currentYear) {
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
				String timeAndDate = convertTime(status.getCreatedAt().getTime());

				String[] twitterSplitTimeAndDate = timeAndDate.split(" ");
				String twitterActualData = twitterSplitTimeAndDate[0];
				String twitterActualTime = twitterSplitTimeAndDate[1];

				String[] twitterActualSplitTime = twitterActualTime.split(":");
				String twitterActualHour = twitterActualSplitTime[0];

				String[] twitterActualSplitData = twitterActualData.split("/");
				String twitterActualDay = twitterActualSplitData[2];
				String twitterActualMounth = twitterActualSplitData[1];
				String twitterActualYear = twitterActualSplitData[0];

				if (currentHour.equals(twitterActualHour) && currentDay.equals(twitterActualDay)
						&& currentMounth.equals(twitterActualMounth) && currentYear.equals(twitterActualYear)) {
					data[i][0] = (convertTime(status.getCreatedAt().getTime()));
					data[i][1] = (status.getText());
					data[i][2] = (status.getUser().getName());
				}
				i++;

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	public Object[][] getTwittersOnThisDay(String currentDay, String currentMounth, String currentYear) {
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
				String timeAndDate = convertTime(status.getCreatedAt().getTime());
				String[] twitterSplitTimeAndDate = timeAndDate.split(" ");

				String twitterActualData = twitterSplitTimeAndDate[0];
				String[] twitterActualSplitData = twitterActualData.split("/");
				String twitterActualDay = twitterActualSplitData[2];
				String twitterActualMounth = twitterActualSplitData[1];
				String twitterActualYear = twitterActualSplitData[0];

				if (currentDay.equals(twitterActualDay) && currentMounth.equals(twitterActualMounth)
						&& currentYear.equals(twitterActualYear)) {
					data[i][0] = (convertTime(status.getCreatedAt().getTime()));
					data[i][1] = (status.getText());
					data[i][2] = (status.getUser().getName());
				}
				i++;

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	public Object[][] getTwittersOnThisMounth(String currentMounth, String currentYear) {
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
				String timeAndDate = convertTime(status.getCreatedAt().getTime());
				String[] twitterSplitTimeAndDate = timeAndDate.split(" ");
				String twitterActualData = twitterSplitTimeAndDate[0];
				String[] twitterActualSplitData = twitterActualData.split("/");
				String twitterActualMounth = twitterActualSplitData[1];
				String twitterActualYear = twitterActualSplitData[0];

				if (currentMounth.equals(twitterActualMounth) && currentYear.equals(twitterActualYear)) {
					data[i][0] = (convertTime(status.getCreatedAt().getTime()));
					data[i][1] = (status.getText());
					data[i][2] = (status.getUser().getName());
				}
				i++;

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	public Object[][] getTwittersOnThisWeek(int currentWeek, String currentMounth, String currentYear) {

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
				java.util.Date date = status.getCreatedAt();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int week = cal.get(Calendar.WEEK_OF_YEAR);

				String timeAndDate = convertTime(status.getCreatedAt().getTime());
				String[] twitterSplitTimeAndDate = timeAndDate.split(" ");

				String twitterActualData = twitterSplitTimeAndDate[0];
				String[] twitterActualSplitData = twitterActualData.split("/");
				String twitterActualMounth = twitterActualSplitData[1];
				String twitterActualYear = twitterActualSplitData[0];

				if (currentWeek == week && currentMounth.equals(twitterActualMounth)
						&& currentYear.equals(twitterActualYear)) {
					data[i][0] = (convertTime(status.getCreatedAt().getTime()));
					data[i][1] = (status.getText());
					data[i][2] = (status.getUser().getName());
				}
				i++;
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	public Object[][] searchWordInTweet(String word) {
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
				if (status.getText().contains(word)) {
					data[i][0] = (convertTime(status.getCreatedAt().getTime()));
					data[i][1] = (status.getText());
					data[i][2] = (status.getUser().getName());
				}
				i++;

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

}
