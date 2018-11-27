package project.facebook;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;


public class FacebookAPI {

private AccessToken extendedAccessToken;

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
		
//token tirado GRAPH API após criação da app BDA
//token não tem todas as permissões - pode ser alterado no GRAPH API 
		
		String accessToken = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
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
		
//token tirado GRAPH API após criação da app BDA
//token não tem todas as permissões - pode ser alterado no GRAPH API 
		
		String accessToken = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
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
		
//token tirado GRAPH API após criação da app BDA
//token não tem todas as permissões - pode ser alterado no GRAPH API 
		
		String accessToken = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
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
		
//token tirado GRAPH API após criação da app BDA
//token não tem todas as permissões - pode ser alterado no GRAPH API 
		
		String accessToken = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
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
		
//token tirado GRAPH API após criação da app BDA
//token não tem todas as permissões - pode ser alterado no GRAPH API 
		
		String accessToken = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		User me = fbClient.fetchObject ("me", User.class);
		
		
		
//teste com o perfil recentemente criado		
		
		System.out.println(me.getName());
		
// App ID - 360660408005152
// App secret - 2d0c83ae88e74968f5c93f905666dfc0
		
		
setExtendedAccessToken(fbClient.obtainExtendedAccessToken("360660408005152","2d0c83ae88e74968f5c93f905666dfc0"));

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
		
//token tirado GRAPH API após criação da app BDA
//token não tem todas as permissões - pode ser alterado no GRAPH API 
		
		String accessToken = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
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
		
//Page feed da página ISCTE - mas devido às novas politicas do Facebook só pode scessível com a review da nossa App.
		
/***		
		String accessToken2 = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
		FacebookClient fbClientPage = new DefaultFacebookClient(accessToken2);
		Page page = fbClientPage.fetchObject("ISCTEIUL",Page.class);		
		Connection<Post> pageFeed = fbClientPage.fetchConnection(page.getId()+"/feed",Post.class);
		int counterP = 0;
		int counterTotalP = 0;
		for (List<Post> postPage : pageFeed) {
			for (Post aPost : postPage) {
			
				// Posts da página do facebook - ISCTE"
				
				System.out.println(aPost.getFrom().getName());
				System.out.println("www.facebook.com/"+aPost.getId());
				System.out.println("Message: "+aPost.getMessage());
				System.out.println("Created: "+aPost.getCreatedTime());
				counterP++;
		
				counterTotalP++;
			}
		}
		
***/		
		
		
}
