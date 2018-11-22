package facebook;

import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.User;

public class Facebook {

	public static void main(String[] args) {
		
		
//token tirado GRAPH API após criação da app BDA
//token não tem todas as permissões - pode ser alterado no GRAPH API 
		
		String accessToken = "EAAFIBMuA8iABAAC529ZCj8J1uZBtJDApAPJnTkV0ZAoJxPMZBoqDsi1MSLvLqZCuHCaCfP8QSTshavA8ZBoZBNOolXZAL3EQbKPjHg0xc6kotSWWQQESSkazA5XuEqElX91IiqU33o9BnzYXbzBjAXZCMfj1ipOe2SDuhMjYdWOvh923IfMZBZCaOIg";
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);
		User me = fbClient.fetchObject ("me", User.class);
		
		
		
//teste com o perfil recentemente criado		
		
		System.out.println(me.getName());
		
// App ID - 360660408005152
// App secret - 2d0c83ae88e74968f5c93f905666dfc0
		
		
//Extender o Access Token de 1h para 2 meses		
		AccessToken extendedAccessToken = fbClient.obtainExtendedAccessToken("360660408005152","2d0c83ae88e74968f5c93f905666dfc0");

//Recolher os posts do utilizador numa lista e imprimi-los na consola 	
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter = 0;
		//int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "M"
				//if (aPost.getMessage() != null && aPost.getMessage().contains("Mestrado")) {
					System.out.println("---- Post "+ counter + " ----");
					System.out.println("Id: "+"www.facebook.com/"+aPost.getId());
					System.out.println("Message: "+aPost.getMessage());
					System.out.println("Created: "+aPost.getCreatedTime());
					counter++;
				}
				//counterTotal++;
			}
		
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


