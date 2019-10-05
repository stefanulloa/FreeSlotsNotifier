package upf.dda.project.data;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.TwitterException;
import upf.dda.project.server.APITwitter;
import upf.dda.project.server.BFSNServer;

public class ScheduledTestJobTelegram implements Job {

	static int LastMessage_id = 0;

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {

			System.out.println("Executing ScheduledTestJob at " + new Date());

			Client client = ClientBuilder.newClient();
			// Update stations cache
			WebTarget targetCacheStations = client.target("http://localhost:15000").path("stations/update");
			targetCacheStations.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {
			});

			// Get clients list
			Client clientGetClients = ClientBuilder.newClient();
			WebTarget targetGetClients = clientGetClients.target("http://localhost:15000").path("clients/list");
			List<User> clients = targetGetClients.request(MediaType.APPLICATION_JSON_TYPE)
					.get(new GenericType<List<User>>() {
					});

			// access update from twitter api and get message_id
			Client clientGetLastMessage_id = ClientBuilder.newClient();
			WebTarget targetLastMessage_id = clientGetLastMessage_id.target("https://api.telegram.org")
					.path("bot" + "497133224:AAGe8YnWFd3qQVMUMyfJZWoW4VzYqNbXTMo" + "/getUpdates");
			JSONObject queryResult;
			queryResult = new JSONObject(
					targetLastMessage_id.request(MediaType.APPLICATION_JSON_TYPE).get(String.class));
			JSONArray messages = queryResult.getJSONArray("result");

			JSONObject aux = (JSONObject) messages.get(messages.length() - 1);

			int Message_id = (int) aux.getJSONObject("message").getInt("message_id");

			// compare message_id and LastMessage_id if different means that
			// there's a new message so we notify

			if (Message_id > LastMessage_id) {
				Client clientNotify = ClientBuilder.newClient();
				WebTarget targetNotify = clientNotify.target("http://localhost:15000").path("clients/notify/309982192");
				Response notify = targetNotify.request(MediaType.APPLICATION_JSON_TYPE).get();

				System.out.println(Message_id + " " + LastMessage_id);

				LastMessage_id = Message_id;

				System.out.println(Message_id + " " + LastMessage_id);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
