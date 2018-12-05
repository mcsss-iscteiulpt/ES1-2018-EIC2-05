package project.facebook;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;


public class FacebookAPI {

	private AccessToken extendedAccessToken;
	private Element e;
	
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

		e = (Element) nList.item(0);
	}

	public Object[][] getPostsInGeneral()	{

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

		//token tirado GRAPH API ap�s cria��o da app BDA
		//token n�o tem todas as permiss�es - pode ser alterado no GRAPH API

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(e.getElementsByTagName("accesstoken").item(2).getTextContent());
		User me = fbClient.fetchObject ("me", User.class);



		//teste com o perfil recentemente criado		

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0


		//Extender o Access Token de 1h para 2 meses		
		//Recolher os posts do utilizador numa lista e imprimi-los na consola 	
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter = 0;
		//int counterTotal = 0;
		int i=0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				//if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post "+ counter + " ----");
				System.out.println("Id: "+"www.facebook.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counter++;
				data[i][0] = "Facebook";
				data[i][1] = (convertTime(aPost.getCreatedTime().getTime()));
				data[i][2]="No title On Facebbok Posts";
				data[i][3] = (aPost.getMessage());
				data[i][4] = (aPost.getId());

				i++;
			}
			//counterTotal++;
		}
		return data;

	}


	public Object[][] getPostsOnTheApi()	{

		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		//token tirado GRAPH API ap�s cria��o da app BDA
		//token n�o tem todas as permiss�es - pode ser alterado no GRAPH API 

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(e.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject ("me", User.class);



		//teste com o perfil recentemente criado		

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0


		//Extender o Access Token de 1h para 2 meses		
		//Recolher os posts do utilizador numa lista e imprimi-los na consola 	
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter = 0;
		//int counterTotal = 0;
		int i=0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				//if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post "+ counter + " ----");
				System.out.println("Id: "+"www.facebook.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counter++;
				data[i][0] = (convertTime(aPost.getCreatedTime().getTime()));
				data[i][1] = (aPost.getMessage());
				data[i][2] = (aPost.getId());

				i++;
			}
			//counterTotal++;
		}
		return data;

	}

	public String convertTime(long time) {

		SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		convert.setTimeZone(TimeZone.getTimeZone("GMT"));
		String tweetTime = convert.format(time);

		return tweetTime;
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

		//token tirado GRAPH API ap�s cria��o da app BDA
		//token n�o tem todas as permiss�es - pode ser alterado no GRAPH API 

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(e.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject ("me", User.class);



		//teste com o perfil recentemente criado		

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0


		//Extender o Access Token de 1h para 2 meses		
		//Recolher os posts do utilizador numa lista e imprimi-los na consola 	
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter = 0;
		//int counterTotal = 0;
		int i=0;

		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				//if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post "+ counter + " ----");
				System.out.println("Id: "+"www.facebook.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counter++;

				String timeAndDate = convertTime(aPost.getCreatedTime().getTime());

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

					data[i][0] = (convertTime(aPost.getCreatedTime().getTime()));
					data[i][1] = (aPost.getMessage());
					data[i][2] = (aPost.getId());
				}
				i++;

			}
			//counterTotal++;
		}
		return data;
	}

	public Object[][] getPostsOnThisDay(String currentDay, String currentMounth,
			String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		//token tirado GRAPH API ap�s cria��o da app BDA
		//token n�o tem todas as permiss�es - pode ser alterado no GRAPH API 

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(e.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject ("me", User.class);



		//teste com o perfil recentemente criado		

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0


		//Extender o Access Token de 1h para 2 meses		
		//Recolher os posts do utilizador numa lista e imprimi-los na consola 	
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter = 0;
		//int counterTotal = 0;
		int i=0;

		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				//if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post "+ counter + " ----");
				System.out.println("Id: "+"www.facebook.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counter++;

				String timeAndDate = convertTime(aPost.getCreatedTime().getTime());
				String[] facebookSplitTimeAndDate = timeAndDate.split(" ");

				String facebookActualData = facebookSplitTimeAndDate[0];
				String[] facebookActualSplitData = facebookActualData.split("/");
				String facebookActualDay = facebookActualSplitData[2];
				String facebookActualMounth = facebookActualSplitData[1];
				String facebookActualYear = facebookActualSplitData[0];

				if (currentDay.equals(facebookActualDay) && currentMounth.equals(facebookActualMounth)
						&& currentYear.equals(facebookActualYear)) {

					data[i][0] = (convertTime(aPost.getCreatedTime().getTime()));
					data[i][1] = (aPost.getMessage());
					data[i][2] = (aPost.getId());
				}
				i++;

			}
			//counterTotal++;
		}
		return data;
	}

	public Object[][] getPostsOnThisMounth(String currentMounth,
			String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		//token tirado GRAPH API ap�s cria��o da app BDA
		//token n�o tem todas as permiss�es - pode ser alterado no GRAPH API 

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(e.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject ("me", User.class);



		//teste com o perfil recentemente criado		

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0


		setExtendedAccessToken(fbClient.obtainExtendedAccessToken(e.getElementsByTagName("appid").item(0).getTextContent(),e.getElementsByTagName("appsecret").item(1).getTextContent()));

		//Recolher os posts do utilizador numa lista e imprimi-los na consola 	
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter = 0;
		//int counterTotal = 0;
		int i=0;

		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				//if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post "+ counter + " ----");
				System.out.println("Id: "+"www.facebook.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counter++;

				String timeAndDate = convertTime(aPost.getCreatedTime().getTime());
				String[] facebookSplitTimeAndDate = timeAndDate.split(" ");
				String facebookActualData = facebookSplitTimeAndDate[0];
				String[] facebookActualSplitData = facebookActualData.split("/");
				String facebookActualMounth = facebookActualSplitData[1];
				String facebookActualYear = facebookActualSplitData[0];

				if (currentMounth.equals(facebookActualMounth) && currentYear.equals(facebookActualYear)) {

					data[i][0] = (convertTime(aPost.getCreatedTime().getTime()));
					data[i][1] = (aPost.getMessage());
					data[i][2] = (aPost.getId());
				}
				i++;

			}
			//counterTotal++;
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

		//token tirado GRAPH API ap�s cria��o da app BDA
		//token n�o tem todas as permiss�es - pode ser alterado no GRAPH API 

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(e.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject ("me", User.class);



		//teste com o perfil recentemente criado		

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0


		//Extender o Access Token de 1h para 2 meses		
		//Recolher os posts do utilizador numa lista e imprimi-los na consola 	
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter = 0;
		//int counterTotal = 0;
		int i=0;

		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				//if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post "+ counter + " ----");
				System.out.println("Id: "+"www.facebook.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counter++;

				java.util.Date date = aPost.getCreatedTime();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int week = cal.get(Calendar.WEEK_OF_YEAR);


				String timeAndDate = convertTime(aPost.getCreatedTime().getTime());
				String[] facebookSplitTimeAndDate = timeAndDate.split(" ");

				String facebookActualData = facebookSplitTimeAndDate[0];
				String[] facebookActualSplitData = facebookActualData.split("/");
				String facebookActualMounth = facebookActualSplitData[1];
				String facebookActualYear = facebookActualSplitData[0];

				if (currentWeek == week && currentMounth.equals(facebookActualMounth)
						&& currentYear.equals(facebookActualYear)) {

					data[i][0] = (convertTime(aPost.getCreatedTime().getTime()));
					data[i][1] = (aPost.getMessage());
					data[i][2] = (aPost.getId());
				}
				i++;

			}
			//counterTotal++;
		}
		return data;
	}


	public AccessToken getExtendedAccessToken() {
		return extendedAccessToken;
	}


	public void setExtendedAccessToken(AccessToken extendedAccessToken) {
		this.extendedAccessToken = extendedAccessToken;
	}	

	//Page feed da p�gina ISCTE - mas devido �s novas politicas do Facebook s� pode scess�vel com a review da nossa App.

	/***		
		String accessToken2 = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
		FacebookClient fbClientPage = new DefaultFacebookClient(accessToken2);
		Page page = fbClientPage.fetchObject("ISCTEIUL",Page.class);		
		Connection<Post> pageFeed = fbClientPage.fetchConnection(page.getId()+"/feed",Post.class);
		int counterP = 0;
		int counterTotalP = 0;
		for (List<Post> postPage : pageFeed) {
			for (Post aPost : postPage) {

				// Posts da p�gina do facebook - ISCTE"

				System.out.println(aPost.getFrom().getName());
				System.out.println("www.facebook.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counterP++;

				counterTotalP++;
			}
		}

	 ***/
	
	public static void main(String[] args) {
		FacebookAPI fb = new FacebookAPI();
		fb.getPostsOnTheApi();
	}


}
