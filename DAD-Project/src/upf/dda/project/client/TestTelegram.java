package upf.dda.project.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import upf.dda.project.data.User;
import upf.dda.project.data.Message;

public class TestTelegram {

	public static void main(String[] args) {

		// Add clients
		Client clientAdd = ClientBuilder.newClient();
		WebTarget targetAdd = clientAdd.target("http://localhost:15000").path("clients/add");

		String token = "THIS_IS_SENSIBLE_INFORMATION";

		List<String> sub1 = Arrays.asList("1", "2", "123");
		User client1 = new User("309982192", sub1, token);
		User response1 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(client1, MediaType.APPLICATION_JSON), User.class);

		List<String> sub2 = Arrays.asList("14", "23", "201");
		User client2 = new User("12312382", sub2, "asdadasdasds");
		User response2 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(client2, MediaType.APPLICATION_JSON), User.class);

		// Get clients
		Client clientGet = ClientBuilder.newClient();
		WebTarget targetGetAll = clientGet.target("http://localhost:15000").path("clients/list");
		List<User> allUsers = targetGetAll.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<User>>() {
		});

		 System.out.println("All users:\n");
		 for (User a : allUsers){
		 System.out.println( a.toString());
		 }

		 //Notify slots
		 Client clientNotify = ClientBuilder.newClient();
		 WebTarget targetNotify =
		 clientNotify.target("http://localhost:15000").path("clients/notify/309982192");
		 Response notify = targetNotify.request(
		 MediaType.APPLICATION_JSON_TYPE).get();
		

	}

}
