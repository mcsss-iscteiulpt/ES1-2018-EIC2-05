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
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

public class FacebookAPI {

	/**
	 * instance do Facebook Graph API client.
	 * 
	 */
	private FacebookClient fbClient;

	/**
	 * getter do Facebook Graph API client.
	 * 
	 */
	public FacebookClient getFbClient() {
		return fbClient;
	}

	private AccessToken extendedAccessToken;

	/**
	 * Graph API access token. extended para extender a validade do token de 1h para
	 * 1 mes.
	 * 
	 */

	/**
	 * instance do User Graph API.
	 * 
	 */
	public User me;

	/**
	 * Codigo do Grupo BDA Facebook onde o utilizador vai postar e buscar posts.
	 * 
	 */

	/**
	 * ???
	 */

	private ArrayList<String> content = new ArrayList<String>();

	public Connection<Post> result;

	private Element el;

	/**
	 * Construtor da classe FacebookAPI: cria a instance necessaria, atraves dos
	 * acess tokens para poder recolher informacoes e usar as funcionalidades do
	 * Facebook.
	 *
	 */
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

	}

	/**
	 * Conexao e lista dos posts obtido atravÃ©s da conexao do cliente do facebook.
	 * 
	 */

	/**
	 * Devolve os posts do nosso grupo do facebook, na nossa timeline geral da API.
	 * 
	 * @return posts
	 *
	 */
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

		// token tirado GRAPH API após criação da app BDA
		// token não tem todas as permissões - pode ser alterado no GRAPH API
		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(
				el.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject("me", User.class);

		// teste com o perfil recentemente criado

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0

		// Extender o Access Token de 1h para 2 meses
		// Recolher os posts do utilizador numa lista e imprimi-los na consola
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

		System.out.println("\nPosts:");
		int counter = 0;
		// int counterTotal = 0;

		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				// if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post " + counter + " ----");
				System.out.println("Id: " + "www.facebook.com/" + aPost.getId());
				System.out.println("Message: " + aPost.getMessage());
				System.out.println("Created: " + aPost.getCreatedTime());
				counter++;

				data[i][0] = "Facebook";
				data[i][1] = (convertTime(aPost.getCreatedTime().getTime()));
				data[i][2] = "No title On Facebbok Posts";

				data[i][1] = (convertTime(aPost.getUpdatedTime().getTime()));
				data[i][2] = "No title On Facebbok Posts";
				data[i][3] = (aPost.getMessage());
				data[i][4] = (me.getName());

				i++;
			}

			// counterTotal++;
			// counterTotal++;

		}
		return data;

	}

	/**
	 * Devolve os posts do nosso grupo do facebook, na nossa timeline especifica da
	 * API.
	 * 
	 * @return posts
	 *
	 */
	public Object[][] getPostsOnTheApi() {

		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		// token tirado GRAPH API após criação da app BDA
		// token não tem todas as permissões - pode ser alterado no GRAPH API

		@SuppressWarnings("deprecation")

		FacebookClient fbClient = new DefaultFacebookClient(
				el.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject("me", User.class);

		// teste com o perfil recentemente criado

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0

		// Extender o Access Token de 1h para 2 meses
		// Recolher os posts do utilizador numa lista e imprimi-los na consola
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

		System.out.println("\nPosts:");
		int counter = 0;
		// int counterTotal = 0;
		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				// if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post " + counter + " ----");
				System.out.println("Id: " + "www.facebook.com/" + aPost.getId());
				System.out.println("Message: " + aPost.getMessage());
				System.out.println("Created: " + aPost.getCreatedTime());
				counter++;
				data[i][0] = (convertTime(aPost.getCreatedTime().getTime()));

				data[i][0] = (convertTime(aPost.getUpdatedTime().getTime()));
				data[i][1] = (aPost.getMessage());
				data[i][2] = (me.getName());

				content.add(aPost.getMessage());

				i++;
			}

			// counterTotal++;

		}
		return data;

	}

	/**
	 * Converte o formato da hora dada pelo Facebook para o formato geral. da
	 * aplicaÃ§Ã£o BDA
	 * 
	 * @param time hora do post no formato original.
	 * 
	 * @return posts
	 *
	 */
	public String convertTime(long time) {

		SimpleDateFormat convert = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		convert.setTimeZone(TimeZone.getTimeZone("GMT"));
		String postTime = convert.format(time);

		return postTime;
	}

	/**
	 * Devolve os posts do nosso grupo do facebook, da ultima hora, na nossa
	 * timeline especifica da API.
	 * 
	 * @param currentHour
	 * @param currentDay
	 * @param currentMounth
	 * @param currentYear
	 * 
	 * 
	 * @return posts
	 *
	 */
	public Object[][] getPostsOnThisHour(String currentHour, String currentDay, String currentMounth,
			String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		// token tirado GRAPH API após criação da app BDA
		// token não tem todas as permissões - pode ser alterado no GRAPH API

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(
				el.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject("me", User.class);

		// teste com o perfil recentemente criado

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0

		// Extender o Access Token de 1h para 2 meses
		// Recolher os posts do utilizador numa lista e imprimi-los na consola
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

		System.out.println("\nPosts:");
		int counter = 0;
		// int counterTotal = 0;

		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				// if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post " + counter + " ----");
				System.out.println("Id: " + "www.facebook.com/" + aPost.getId());
				System.out.println("Message: " + aPost.getMessage());
				System.out.println("Created: " + aPost.getCreatedTime());

				counter++;

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

			// counterTotal++;

		}
		return data;
	}

	/**
	 * Devolve os posts do nosso grupo do facebook, do ultimo dia, na nossa timeline
	 * especifica da API.
	 *
	 * @param currentDay
	 * @param currentMounth
	 * @param currentYear
	 * 
	 * @return posts
	 *
	 */
	public Object[][] getPostsOnThisDay(String currentDay, String currentMounth, String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		// token tirado GRAPH API após criação da app BDA
		// token não tem todas as permissões - pode ser alterado no GRAPH API

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(
				el.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject("me", User.class);

		// teste com o perfil recentemente criado

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0

		// Extender o Access Token de 1h para 2 meses
		// Recolher os posts do utilizador numa lista e imprimi-los na consola
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

		System.out.println("\nPosts:");
		int counter = 0;

		// int counterTotal = 0;
		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				// if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post " + counter + " ----");
				System.out.println("Id: " + "www.facebook.com/" + aPost.getId());
				System.out.println("Message: " + aPost.getMessage());
				System.out.println("Created: " + aPost.getCreatedTime());
				counter++;

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

			// counterTotal++;

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

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(
				el.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject("me", User.class);

		// teste com o perfil recentemente criado

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0

		setExtendedAccessToken(
				fbClient.obtainExtendedAccessToken(el.getElementsByTagName("appid").item(0).getTextContent(),
						el.getElementsByTagName("appsecret").item(1).getTextContent()));

		// Recolher os posts do utilizador numa lista e imprimi-los na consola
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

		System.out.println("\nPosts:");
		int counter = 0;

		// int counterTotal = 0;
		int i = 0;

		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"

				// if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post " + counter + " ----");
				System.out.println("Id: " + "www.facebook.com/" + aPost.getId());
				System.out.println("Message: " + aPost.getMessage());
				System.out.println("Created: " + aPost.getCreatedTime());

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

			// counterTotal++;

		}
		return data;
	}

	/**
	 * Devolve os posts do nosso grupo do facebook, da ultima semana, na nossa
	 * timeline especifica da API.
	 * 
	 * @param currentWeek
	 * @param currentMounth
	 * @param currentYear
	 * 
	 * @return posts
	 *
	 */
	public Object[][] getPostsOnThisWeek(int currentWeek, String currentMounth, String currentYear) {
		Object[][] data = { { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" }, { "", "", "", "" },
				{ "", "", "", "" }, { "", "", "", "" } };

		// token tirado GRAPH API após criação da app BDA
		// token não tem todas as permissões - pode ser alterado no GRAPH API

		@SuppressWarnings("deprecation")
		FacebookClient fbClient = new DefaultFacebookClient(
				el.getElementsByTagName("accesstoken").item(0).getTextContent());
		User me = fbClient.fetchObject("me", User.class);

		System.out.println(me.getName());

		// App ID - 360660408005152
		// App secret - 2d0c83ae88e74968f5c93f905666dfc0

		// Extender o Access Token de 1h para 2 meses
		// Recolher os posts do utilizador numa lista e imprimi-los na consola
		Connection<Post> result = fbClient.fetchConnection("me/feed", Post.class);

		System.out.println("\nPosts:");
		int counter = 0;

		// int counterTotal = 0;
		int i = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				// if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
				System.out.println("---- Post " + counter + " ----");
				System.out.println("Id: " + "www.facebook.com/" + aPost.getId());
				System.out.println("Message: " + aPost.getMessage());
				System.out.println("Created: " + aPost.getCreatedTime());
				counter++;

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

			// counterTotal++;

		}
		return data;
	}

	/**
	 * Devolve os posts do nosso grupo do facebook, do ultimo mÃªs, na nossa
	 * timeline especifica da API.
	 * 
	 * @param currentMounth
	 * @param currentYear
	 * 
	 * @return posts
	 *
	 */

	public void setExtendedAccessToken(AccessToken extendedAccessToken) {
		this.extendedAccessToken = extendedAccessToken;
	}

	/**
	 * Procura posts que contenham a palavra que o utilizador introduz no filtro de
	 * pesquisa.
	 * 
	 * @param word palavra que o utilizador introduz.
	 * 
	 * @return posts
	 *
	 */
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

	public ArrayList<String> getContent() {
		return content;
	}

	// Page feed da página ISCTE - mas devido às novas politicas do Facebook só pode
	// scessível com a review da nossa App.

	/***
	 * String accessToken2 =
	 * "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
	 * FacebookClient fbClientPage = new DefaultFacebookClient(accessToken2); Page
	 * page = fbClientPage.fetchObject("ISCTEIUL",Page.class); Connection<Post>
	 * pageFeed = fbClientPage.fetchConnection(page.getId()+"/feed",Post.class); int
	 * counterP = 0; int counterTotalP = 0; for (List<Post> postPage : pageFeed) {
	 * for (Post aPost : postPage) {
	 * 
	 * // Posts da página do facebook - ISCTE"
	 * 
	 * System.out.println(aPost.getFrom().getName());
	 * System.out.println("www.facebook.com/"+aPost.getId());
	 * System.out.println("Message: "+aPost.getMessage());
	 * System.out.println("Created: "+aPost.getCreatedTime()); counterP++;
	 * 
	 * counterTotalP++; >>>>>>> refs/heads/config }
	 * 
	 * } <<<<<<< HEAD return data;
	 * 
	 * }
	 * 
	 * public ArrayList<String> getContent() { return content; }
	 * 
	 * public void post (){
	 * 
	 * <<<<<<< HEAD /*** String accessToken2 =
	 * "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
	 * FacebookClient fbClientPage = new DefaultFacebookClient(accessToken2); Page
	 * page = fbClientPage.fetchObject("ISCTEIUL",Page.class); Connection<Post>
	 * pageFeed = fbClientPage.fetchConnection(page.getId()+"/feed",Post.class); int
	 * counterP = 0; int counterTotalP = 0; for (List<Post> postPage : pageFeed) {
	 * for (Post aPost : postPage) {
	 * 
	 * // Posts da página do facebook - ISCTE"
	 * 
	 * System.out.println(aPost.getFrom().getName());
	 * System.out.println("www.facebook.com/"+aPost.getId());
	 * System.out.println("Message: "+aPost.getMessage());
	 * System.out.println("Created: "+aPost.getCreatedTime()); counterP++;
	 * 
	 * counterTotalP++; } }
	 * 
	 ***/

	public AccessToken getAcessToken() {
		return extendedAccessToken;
	}

	public static void main(String[] args) {
		FacebookAPI fb = new FacebookAPI();
		fb.getPostsOnTheApi();
	}

}
