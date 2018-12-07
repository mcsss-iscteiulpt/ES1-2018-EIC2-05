package BDA;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class TwitterHandler.
 */
public class TwitterHandler {

	/** The twitter. */
	Twitter twitter;
	
	/** The content. */
	private ArrayList<String>content;

	
	/**
	 * Instantiates a new twitter handler.
	 */
	public TwitterHandler() {

		content=new ArrayList<String>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Document document = null;
		try {
			document = builder.parse(new File("config.xml"));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.getDocumentElement().normalize();
		
		NodeList nList = document.getElementsByTagName("twitter");
		
		Element e = (Element) nList.item(0);

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(e.getElementsByTagName("consumerkey").item(0).getTextContent())
				.setOAuthConsumerSecret(e.getElementsByTagName("consumersecret").item(0).getTextContent())
				.setOAuthAccessToken(e.getElementsByTagName("accesstoken").item(0).getTextContent())
				.setOAuthAccessTokenSecret(e.getElementsByTagName("accesstokensecret").item(0).getTextContent());

		TwitterFactory tf = new TwitterFactory(cb.build());
		this.twitter = tf.getInstance();
	}



	/**
	 * Tweets in general.
	 *
	 * @return the object[][]
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

				//System.out.println(status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}


	/**
	 * Tweets on twitter API.
	 *
	 * @return the object[][]
	 */
	public Object[][] tweetsOnTwitterAPI() {
		content=new ArrayList<String>();
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

				content.add(status.getText());
				
				i++;

				//System.out.println(status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	
	/**
	 * Send tweet.
	 *
	 * @param tweetText the tweet text
	 */
	public void sendTweet(String tweetText) {
		try {

			twitter.updateStatus(tweetText);
			//System.out.println("Successfully updated the status to [" + status.getText() + "].");
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
	/**
	 * Convert time.
	 *
	 * @param time the time
	 * @return the string
	 */
	public String convertTime(long time) {

		SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		convert.setTimeZone(TimeZone.getTimeZone("GMT"));
		String tweetTime = convert.format(time);

		return tweetTime;
	}

	
	/**
	 * Time stamps.
	 *
	 * @return the array list
	 */
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

	
	/**
	 * Gets the twitters on this hour.
	 *
	 * @param currentHour the current hour
	 * @param currentDay the current day
	 * @param currentMounth the current mounth
	 * @param currentYear the current year
	 * @return the twitters on this hour
	 */
	public Object[][] getTwittersOnThisHour(String currentHour, String currentDay, String currentMounth,
			String currentYear) {
		content=new ArrayList<String>();
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
				content.add(status.getText());
				i++;

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	
	/**
	 * Gets the twitters on this day.
	 *
	 * @param currentDay the current day
	 * @param currentMounth the current mounth
	 * @param currentYear the current year
	 * @return the twitters on this day
	 */
	public Object[][] getTwittersOnThisDay(String currentDay, String currentMounth, String currentYear) {
		content=new ArrayList<String>();
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
				content.add(status.getText());
				i++;

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	
	/**
	 * Gets the twitters on this mounth.
	 *
	 * @param currentMounth the current mounth
	 * @param currentYear the current year
	 * @return the twitters on this mounth
	 */
	public Object[][] getTwittersOnThisMounth(String currentMounth, String currentYear) {
		content=new ArrayList<String>();
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
				content.add(status.getText());
				i++;

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	
	/**
	 * Gets the twitters on this week.
	 *
	 * @param currentWeek the current week
	 * @param currentMounth the current mounth
	 * @param currentYear the current year
	 * @return the twitters on this week
	 */
	public Object[][] getTwittersOnThisWeek(int currentWeek, String currentMounth, String currentYear) {
		content=new ArrayList<String>();

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
				content.add(status.getText());
				i++;
				
			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}

	
	/**
	 * Search word in tweet.
	 *
	 * @param word the word
	 * @return the object[][]
	 */
	public Object[][] searchWordInTweet(String word) {
		content=new ArrayList<String>();
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
				content.add(status.getText());
				i++;

			}
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return data;
	}
	
	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public ArrayList<String> getContent()	{
		return content;
	}

	/**
	 * Gets the twitter.
	 *
	 * @return the twitter
	 */
	public Twitter getTwitter() {
		return twitter;
	}
	
}
