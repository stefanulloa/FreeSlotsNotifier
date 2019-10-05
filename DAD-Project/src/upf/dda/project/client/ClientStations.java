package upf.dda.project.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import upf.dda.project.data.Station;
import upf.dda.project.data.Stations;

public class ClientStations {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		// Get all items here….
		try {
			WebTarget targetGetAllStations = client.target("http://localhost:15000").path("stations/getall");
			String allStations = targetGetAllStations.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {
					});

			System.out.println(allStations);

		} catch (Exception e) {

			e.printStackTrace();
		}

		// Get station by id

		try {
			WebTarget targetGetByName = client.target("http://localhost:15000").path("stations/get/2");

			String station = targetGetByName.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {
			});
			System.out.println("Station: " + station);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get nearby stations of a station
		WebTarget targetGetNearby = client.target("http://localhost:15000").path("stations/get/1/nearby");

		String stations = targetGetNearby.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {
			});
		System.out.println("Nearby stations: \n" + stations);
		

		//Get stats
		WebTarget targetGetStats = client.target("http://localhost:15000").path("stations/get/stats");
		String stats = targetGetStats.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<String>() {
		});
		System.out.println(stats);

	}

}
