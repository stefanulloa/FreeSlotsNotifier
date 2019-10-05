package upf.dda.project.client;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import upf.dda.project.data.User;

public class ClientSubscribeUsers {
	
	public static void main(String[] args) {
		
		//Add clients
		Client clientAdd = ClientBuilder.newClient();
		WebTarget targetAdd = clientAdd.target("http://localhost:15000").path("clients/add");
		
		String token = "497133224:AAGe8YnWFd3qQVMUMyfJZWoW4VzYqNbXTMo";
		
		List<String> sub1 = Arrays.asList("1", "2", "123");
		User client1 = new User("1", sub1, token);
		User response1 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(client1, MediaType.APPLICATION_JSON), User.class);
		System.out.println(response1.toString());
				

		List<String> sub3 = Arrays.asList("4", "76", "194");
		User client3 = new User("pepe", sub3, token);
		User response3 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(client3, MediaType.APPLICATION_JSON), User.class);
		System.out.println(response3.toString());
		
		List<String> sub4 = Arrays.asList("210", "50", "184", "20", "30");
		User client4 = new User("1", sub4, token);
		User response4 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(client4, MediaType.APPLICATION_JSON), User.class);
		System.out.println(response4.toString());
		
		
		System.out.println("Users added.");
		
		
		Client clientUpdate = ClientBuilder.newClient();
		WebTarget targetUpdate = clientUpdate.target("http://localhost:15000").path("clients/update/1/200,15");
		User response5 = targetUpdate.request(MediaType.APPLICATION_JSON_TYPE)
				.put(Entity.entity(client4, MediaType.APPLICATION_JSON), User.class);
		System.out.println(response5.toString());
//		targetUpdate.request().put(Entity.entity(String.class, String.valueOf(true)));
		
	}

}