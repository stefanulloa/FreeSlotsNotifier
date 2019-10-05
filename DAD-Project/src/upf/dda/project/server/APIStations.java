package upf.dda.project.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import upf.dda.project.data.Station;
import upf.dda.project.data.Stations;
import upf.dda.project.data.Stats;

@Path("/stations")
public class APIStations {

	// Methods to be implemented: get stations, get station, get nearby
	// stations, get stats
	
	
	static Stations stations = new Stations();
	

	
	// Get all stations
	@GET
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cacheStations() throws MalformedURLException {
		
		//jasonToList parses the information of bicing web.
		jsonToList();
		return Response.status(200).build();
	}

	// Get all stations
	@GET
	@Path("/getall")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() throws Exception {
		
		if (stations.getStations().isEmpty())
			throw new WebApplicationException(Status.NOT_FOUND);
		
		return Response.status(200).entity(stations.toString()).build();
	}

	// Get all the information about a specific station
	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") String id) throws Exception {
	
		
		//uses getID from class Station  
		try{

		return Response.status(200).entity(stations.getId(id).toString()).build();
		
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		
	}

	// Get all the nearby stations of a specific station
	@GET
	@Path("/get/{id}/nearby")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNearbyStations(@PathParam("id") String id) {

		
		try {
			
			Station station = new Station();
			
			station = stations.getId(id);
			
			//split the string and then get each station 
			
			String[] nearbyStations = station.getNearbyStations().split(", ");

			Stations stationsNearby = new Stations();

			for (String id2 : nearbyStations) {

				stationsNearby.add(stations.getId(id2));
				
			}
			
			String aux = stationsNearby.toString();
			
			return Response.status(200).entity(aux).build();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(204).entity("user not exist").build();

	}

	private static String getStationInfo(List<Station> stationsList, String id) {
		String res = "";
		for (Station station : stationsList) {
			if (station.getId().equals(id)) {
				res = station.toString();
				break;
			}
		}
		return res;
	}

	// Get stats about the stations
	@GET
	@Path("/get/stats")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStats() {
	
	
		try {
			
			int totalBikes = 0;
			double openedStations = 0;
			double bikesPerStation = 0;
			double percentageOfUsedBikes = 0;
			int bikesNearCampusPoblenou = 0;
			List<Station> stationsList;
			stationsList = stations.getStations();
			int totalStations = stationsList.size();
			for (Station station : stationsList) {
				totalBikes += Integer.parseInt(station.getSlots());
				if (station.getStatus().equals("OPN")) {
					++openedStations;
				}
				bikesPerStation += Double.parseDouble(station.getBikes());
				if (station.getId().equals("342") || station.getId().equals("142") || station.getId().equals("152")
						|| station.getId().equals("153") || station.getId().equals("393") || station.getId().equals("428"))
					bikesNearCampusPoblenou += Double.parseDouble(station.getBikes());
				percentageOfUsedBikes += Double.parseDouble(station.getBikes());
			}
			openedStations = openedStations / totalStations;
			bikesPerStation = bikesPerStation / totalStations;
			percentageOfUsedBikes = percentageOfUsedBikes / totalBikes;
			Stats statistics = new Stats(openedStations, bikesPerStation, percentageOfUsedBikes, bikesNearCampusPoblenou);
			return Response.status(200).entity(statistics.toString()).build();
			

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(204).entity("Stations list empty").build();
	
	}

	
	
	private static void jsonToList() throws MalformedURLException {
		try {
			// error control for when there is not net
			
			URL url = new URL("http://wservice.viabicing.cat/v2/stations");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responseCode = con.getResponseCode();
			if (responseCode != 200) {
				System.err.println("Connection failed.");
				return;
				
			} else {

				Client client = ClientBuilder.newClient();
				
				//target the bicing web

				WebTarget BicingData = client.target("http://wservice.viabicing.cat").path("/v2/stations");

				// use the gson library to parse the string
				Gson gson = new Gson();

				String BicingString = BicingData.request(MediaType.APPLICATION_JSON_TYPE)
						.get(new GenericType<String>() {
						});
				
				// Add the stations to our variable stations

				stations = gson.fromJson(BicingString, Stations.class);

			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
