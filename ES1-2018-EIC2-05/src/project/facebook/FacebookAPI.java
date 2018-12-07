package project.facebook;

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

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;

public class FacebookAPI {
	
	private FacebookClient fbClient;
	
	public FacebookClient getFbClient() {
		return fbClient;
	}

	
	private String extendedAccessToken;
	
	
	public User me;

	
	private ArrayList<String>content=new ArrayList<String>();
	
	public Connection<Post> result;
	
	
	private static Element el;

	
	@SuppressWarnings("deprecation")
	public FacebookAPI() {
		
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

		NodeList nList = document.getElementsByTagName("facebook");

		el = (Element) nList.item(0);
		
		fbClient = new DefaultFacebookClient(el.getElementsByTagName("accesstoken").item(0).getTextContent());
		me = fbClient.fetchObject ("me", User.class);
		
		result = fbClient.fetchConnection(el.getElementsByTagName("groupid").item(0).getTextContent()+"/feed", Post.class);

		
	}

	
	public Object[][] getPostsInGeneral() {

		Object[][] data = { { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
	
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" },
				{ "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, { "", "", "", "", "" }, };

		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {


				data[i][0] = "Facebook";
				data[i][1] = (convertTime(aPost.getUpdatedTime().getTime()));
				data[i][2] = "No title On Facebbok Posts";
				data[i][3] = (aPost.getMessage());
				data[i][4] = (me.getName());

				i++;
			}

		}
		return data;

	}

	
	public Object[][] getPostsOnTheApi() {

		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				

				data[i][0] = (convertTime(aPost.getUpdatedTime().getTime()));
				data[i][1] = (aPost.getMessage());
				data[i][2] = (me.getName());
				
				content.add(aPost.getMessage());

				i++;
			}

		}
		return data;

	}

	
	public String convertTime(long time) {

		SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		convert.setTimeZone(TimeZone.getTimeZone("GMT"));
		String postTime = convert.format(time);

		return postTime;
	}

	public Object[][] getPostsOnThisHour(String currentHour, String currentDay, String currentMounth,
			String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };


		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {


				String timeAndDate = convertTime(aPost.getUpdatedTime().getTime());

				String[] facebookSplitTimeAndDate = timeAndDate.split(" ");
				String facebookActualData = facebookSplitTimeAndDate[0];
				String facebookActualTime = facebookSplitTimeAndDate[1];

				String[] facebookActualSplitTime = facebookActualTime.split(":");
				String facebookActualHour = facebookActualSplitTime[0];

				String[] facebookActualSplitData = facebookActualData.split("/");
				String facebookActualDay = facebookActualSplitData[2];
				String facebookActualMounth = facebookActualSplitData[1];
				String facebookActualYear = facebookActualSplitData[0];

				if (currentHour.equals(facebookActualHour) && currentDay.equals(facebookActualDay)
						&& currentMounth.equals(facebookActualMounth) && currentYear.equals(facebookActualYear)) {

					data[i][0] = (convertTime(aPost.getUpdatedTime().getTime()));
					data[i][1] = (aPost.getMessage());
					data[i][2] = (me.getName());
				}
				i++;

			}

		}
		return data;
	}

	
	
	 
	public Object[][] getPostsOnThisDay(String currentDay, String currentMounth, String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {


				String timeAndDate = convertTime(aPost.getUpdatedTime().getTime());
				String[] facebookSplitTimeAndDate = timeAndDate.split(" ");

				String facebookActualData = facebookSplitTimeAndDate[0];
				String[] facebookActualSplitData = facebookActualData.split("/");
				String facebookActualDay = facebookActualSplitData[2];
				String facebookActualMounth = facebookActualSplitData[1];
				String facebookActualYear = facebookActualSplitData[0];

				if (currentDay.equals(facebookActualDay) && currentMounth.equals(facebookActualMounth)
						&& currentYear.equals(facebookActualYear)) {

					data[i][0] = (convertTime(aPost.getUpdatedTime().getTime()));
					data[i][1] = (aPost.getMessage());
					data[i][2] = (me.getName());
				}
				i++;

			}

		}
		return data;
	}

	
	
	public Object[][] getPostsOnThisWeek(int currentWeek, String currentMounth, String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {

				java.util.Date date = aPost.getUpdatedTime();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int week = cal.get(Calendar.WEEK_OF_YEAR);

				String timeAndDate = convertTime(aPost.getUpdatedTime().getTime());
				String[] facebookSplitTimeAndDate = timeAndDate.split(" ");

				String facebookActualData = facebookSplitTimeAndDate[0];
				String[] facebookActualSplitData = facebookActualData.split("/");
				String facebookActualMounth = facebookActualSplitData[1];
				String facebookActualYear = facebookActualSplitData[0];

				if (currentWeek == week && currentMounth.equals(facebookActualMounth)
						&& currentYear.equals(facebookActualYear)) {

					data[i][0] = (convertTime(aPost.getUpdatedTime().getTime()));
					data[i][1] = (aPost.getMessage());
					data[i][2] = (me.getName());
				}
				i++;

			}

		}
		return data;
	}
	
	
	
	
	public Object[][] getPostsOnThisMounth(String currentMounth, String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				

				String timeAndDate = convertTime(aPost.getUpdatedTime().getTime());
				String[] facebookSplitTimeAndDate = timeAndDate.split(" ");
				String facebookActualData = facebookSplitTimeAndDate[0];
				String[] facebookActualSplitData = facebookActualData.split("/");
				String facebookActualMounth = facebookActualSplitData[1];
				String facebookActualYear = facebookActualSplitData[0];

				if (currentMounth.equals(facebookActualMounth) && currentYear.equals(facebookActualYear)) {

					data[i][0] = (convertTime(aPost.getUpdatedTime().getTime()));
					data[i][1] = (aPost.getMessage());
					data[i][2] = (me.getName());
				}
				i++;

			}

		}
		return data;
	}

	

	
	public Object[][] searckWordInFacebook(String word) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				if (aPost.getMessage().contains(word)) {

					data[i][0] = (convertTime(aPost.getUpdatedTime().getTime()));
					data[i][1] = (aPost.getMessage());
					data[i][2] = (me.getName());

					i++;
				}
			}

		}
		return data;

	}
	
	public ArrayList<String> getContent()	{
		return content;
	}
	
	public String getAcessToken()	{
		return extendedAccessToken;
	}
	
	
	public void post (String message){		
		FacebookType response = fbClient.publish(el.getElementsByTagName("groupid").item(0).getTextContent()+"/feed", FacebookType.class, Parameter.with("message", message));
		
		System.out.println("novo post em fb.com/"+response.getId());
	}
	
	
}
