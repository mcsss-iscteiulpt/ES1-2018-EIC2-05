package project.twitter;

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

public class TwitterHandler {

	Twitter twitter;

	/**
	 * Construtor da classe TwitterHandler: cria a instance necessária, através dos
	 * acesstokens e api keys e segredos, para poder usar as funcionalidades do
	 * twitter
	 */
	public TwitterHandler() {
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

				//System.out.println(status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
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

				//System.out.println(status.getUser().getName() + ":" + status.getText() + " :" + status.getCreatedAt().getTime());
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
			//System.out.println("Successfully updated the status to [" + status.getText() + "].");
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

	/**
	 * 
	 * @return uma lista com todos os timestamp de cada tweet da timeline
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
	 * 
	 * @param currentHour
	 * @param currentDay
	 * @param currentMounth
	 * @param currentYear
	 * @return os tweets que foram publicados na ultima hora
	 */
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

	/**
	 * 
	 * @param currentDay
	 * @param currentMounth
	 * @param currentYear
	 * @return os tweets que foram publicados no ultimo dia
	 */
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

	/**
	 * 
	 * @param currentMounth
	 * @param currentYear
	 * @return os tweets que foram pulicados no ultimo mes
	 */
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

	/**
	 * 
	 * @param currentWeek
	 * @param currentMounth
	 * @param currentYear
	 * @return os tweets que foram publicados na ultima semana
	 */
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

	/**
	 * 
	 * @param word
	 * @return os tweets que continham a palavra pesquisada
	 */
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

	/**
	 * 
	 * @return a instancia do twitter
	 */
	public Twitter getTwitter() {
		// TODO Auto-generated method stub
		return twitter;
	}
	
}
