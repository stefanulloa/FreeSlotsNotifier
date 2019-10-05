package upf.dda.project.server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

public class APITwitter {

	private static String CONSUMER_KEY = "key1";
	private static String CONSUMER_SECRET = "key2";
	private static String ACCESS_TOKEN = "key3";
	private static String ACCESS_TOKEN_SECRET = "key4";
	private static String Old_Tweet="";
	
	public APITwitter() throws TwitterException{


	}
	
	public Twitter getTwitterinstance(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(CONSUMER_KEY)
		  .setOAuthConsumerSecret(CONSUMER_SECRET)
		  .setOAuthAccessToken(ACCESS_TOKEN)
		  .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}
	
	public String createTweet() throws TwitterException {
		Client client = ClientBuilder.newClient();
		WebTarget targetGetStats = client.target("http://localhost:15000").path("stations/get/stats");
		
		String stats = targetGetStats.request(
				MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {});
		
		// in case we are sending same tweet as the previous one we don't. 
		if(!Old_Tweet.equals(stats)){
		Twitter twitter = getTwitterinstance();
	    Status status = twitter.updateStatus(stats);
	    Old_Tweet=stats;
	    return status.getText();
		}
	    
		return "duplicate message";
	}
	
	
	
}
