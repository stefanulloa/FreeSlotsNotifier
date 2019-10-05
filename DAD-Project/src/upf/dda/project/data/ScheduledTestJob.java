package upf.dda.project.data;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import twitter4j.TwitterException;
import upf.dda.project.server.APITwitter;

public class ScheduledTestJob implements Job{

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			System.out.println("Executing ScheduledTestJob at " + new Date());
			
		
			Client client = ClientBuilder.newClient();
			//Update stations cache
			WebTarget targetCacheStations = client.target("http://localhost:15000").path("stations/update");
			targetCacheStations.request(
					MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {});
			
			//Get clients list
			Client clientGetClients = ClientBuilder.newClient();
			WebTarget targetGetClients = clientGetClients.target("http://localhost:15000").path("clients/list");
			List<User> clients = targetGetClients.request(
					MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<User>>() {});
			
			
			// create tweet
			APITwitter twitter;
			twitter = new APITwitter();
//			String test = twitter.createTweet();
//			System.out.println(test);
//			
			//Notify every phone in clients db
			Client clientNotify = ClientBuilder.newClient();
			for(User client1 : clients){
				String phone = client1.getPhoneNumber();
				WebTarget targetNotify = clientNotify.target("http://localhost:15000").path("clients/notify/" + phone);
				targetNotify.request(
						MediaType.APPLICATION_JSON_TYPE).get();
				
				
			}
		
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
	}
				
}
