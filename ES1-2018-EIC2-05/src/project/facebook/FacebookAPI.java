package project.facebook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
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

	/**
	 * Graph API access token.
	 * extended para extender a validade do token de 1h para 1 mes.
	 * 
	 */
	private String extendedAccessToken;
	
	/**
	 * instance do User Graph API.
	 * 
	 */
	public User me;

	/**
	 * Codigo do Grupo BDA Facebook onde o utilizador vai postar e buscar posts.
	 * 
	 */
	private String groupID ="1176283292538810";
	
	/**
	 * ???
	 */
	private ArrayList<String>content=new ArrayList<String>();
	
	/**
	 * Conexao e lista dos posts obtido através da conexao do cliente do facebook.
	 * 
	 */
	public Connection<Post> result;

	/**
	 * Construtor da classe FacebookAPI:
	 * cria a instance necessaria, atraves dos acess tokens para poder recolher informacoes e usar as funcionalidades do Facebook.
	 *
	 */
	@SuppressWarnings("deprecation")
	public FacebookAPI() {
		
		
		extendedAccessToken = "EAAFIBMuA8iABAArep5j6oUJlBV0knAu4Lt5ZBX5S2BYSek70KTw43usBoIRrjnq8Jd3KVCY9zX69b4zKbPzLQiRh7SEkiYv1nu0oNgZCdCGVYZCzhbKjzdpNkhZAeli9zWi7gRiBu72hegnJ6AiRh644esze0aq2myxIyCDPyNfhZBBZCcMgT7";
		fbClient = new DefaultFacebookClient(extendedAccessToken);
		me = fbClient.fetchObject ("me", User.class);
		
		result = fbClient.fetchConnection(groupID+"/feed", Post.class);

		
	}

	/**
	 * Devolve os posts do nosso grupo do facebook, na nossa timeline geral da API.
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

	/**
	 * Devolve os posts do nosso grupo do facebook, na nossa timeline especifica da API.
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

	/**
	 * Converte o formato da hora dada pelo Facebook para o formato geral.
	 * da aplicação BDA
	 * 
	 * @param time
	 * 			hora do post no formato original.
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
	 * Devolve os posts do nosso grupo do facebook, da ultima hora, na nossa timeline especifica da API.
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

	
	/**
	 * Devolve os posts do nosso grupo do facebook, do ultimo dia, na nossa timeline especifica da API.
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

	
	
	/**
	 * Devolve os posts do nosso grupo do facebook, da ultima semana, na nossa timeline especifica da API.
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
	
	
	
	/**
	 * Devolve os posts do nosso grupo do facebook, do ultimo mês, na nossa timeline especifica da API.
	 * 
	 * @param currentMounth
	 * @param currentYear
	 * 
	 * @return posts
	 *
	 */
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

	

	/**
	 * Procura posts que contenham a palavra que o utilizador introduz no filtro de pesquisa.
	 * 
	 * @param word
	 * 			palavra que o utilizador introduz.
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
	
	public ArrayList<String> getContent()	{
		return content;
	}
	
	public void post (){

		
		Scanner s = new Scanner(System.in);
		
		System.out.println("escreva o post: \n");
		
		String msg = s.nextLine();
		
		FacebookType response = fbClient.publish(groupID+"/feed", FacebookType.class, Parameter.with("message", msg));
		
		System.out.println("fb.com/"+response.getId());
		
		
	}
	
	
}
