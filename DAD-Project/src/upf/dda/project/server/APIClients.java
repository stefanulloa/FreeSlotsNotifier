package upf.dda.project.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.Client;

import upf.dda.project.data.User;
import upf.dda.project.data.Users;
import upf.dda.project.data.Message;
import upf.dda.project.data.Station;
import upf.dda.project.data.Stations;

@Path("/clients")
public class APIClients {

	// Methods to be implemented: get clients, subscribe clients to stations,
	// Notify slots
	static Users clients = new Users();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addClient(User client) {

		// check if exist
		if (clients.exist(client)) {
			try {
				//update sub list if exist
				for (String a : client.getListSubscriptions()) {
					updateClientsList(clients.getUser(client.getPhoneNumber()).getPhoneNumber(), a);
		
				}
			}

			catch (Exception e) {

				e.printStackTrace();

			}

		}
		else //otherwise just do a normal add
			clients.add(client);

		return Response.status(200).entity(clients.getUser(client.getPhoneNumber())).build();
	}

	// Get a client
	@GET
	@Path("/get/{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getClient(@PathParam("phone") String phone) {

		
		return clients.getUser(phone);
	
	}

	// Get all clients
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAll() {

		return clients.getUsers();
	}

	@GET
	@Path("/notify/{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response notify(@PathParam("phone") String phone) throws Exception {
		// Check if phone exists
		// get the chatID
		// prepare the message
		// send the message

		// Check if phone exists
			if (clients.exist(phone)){
			
			String token = "";
			List<String> subscriptions = new ArrayList<String>();

			token = clients.getUser(phone).getToken();
			subscriptions = clients.getUser(phone).getListSubscriptions();
			
		
			// Get the chatID
			long chatID = 0;
			Client clientGetChatID = ClientBuilder.newClient();
			WebTarget targetChatId = clientGetChatID.target("https://api.telegram.org").path("bot" + token + "/getUpdates");
			String queryResult = targetChatId.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {
			});
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(queryResult);
			JsonNode result = rootNode.path("result");
			Iterator<JsonNode> itemsResult = result.get(0).elements();
			while (itemsResult.hasNext()) {
				JsonNode item = itemsResult.next();
				JsonNode chatIdNode = item.path("chat").path("id");
				chatID = chatIdNode.longValue();
			}
			
			
			// Prepare the message
			String messageToTelegram = "";
			for (String sub : subscriptions) {
				messageToTelegram += "Station " + sub + " has " + APIStations.stations.getId(sub).getSlots() + " free slots.\n";
			}
			
			
			
			// connect to telegram chat
			Client clientNotify = ClientBuilder.newClient();
			Message message = new Message(chatID, messageToTelegram);
			try {

				WebTarget targetSendMessage = clientNotify.target("https://api.telegram.org")
						.path("/bot" + "497133224:AAGe8YnWFd3qQVMUMyfJZWoW4VzYqNbXTMo" + "/sendMessage");
				String response = targetSendMessage.request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE),
						String.class);
				System.out.println("RESPONSE: \n" + response);

			} catch (Exception e) {

				e.printStackTrace();
			}

			
			return Response.status(200).entity(message).build();

		}

		
		else 
			return Response.status(204).entity("user does not exist").build();
		

	}

	@PUT
	@Path("/update/{phone}/{stationIDs}")
	public Response updateClientsList(@PathParam("phone") String phone, @PathParam("stationIDs") String stationIDs) {

		//if exist we add elements to its sub list
		if (clients.exist(phone)) {
			try {

				String[] IDs = stationIDs.split(",");

				for (String id : IDs) {

					clients.getUser(phone).getListSubscriptions().add(id);

				}
				
			}

			catch (Exception e) {

				e.printStackTrace();

			}
			
		}
		
		return Response.status(200).entity(clients.getUser(phone)).build();
	}
}
