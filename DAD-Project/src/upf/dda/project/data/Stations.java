package upf.dda.project.data;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;



public class Stations {

	private List<Station> stations;
	
	public Stations(){
		stations = new ArrayList<Station>();
		
	}
	
	public Stations(List<Station> stations){
		this.stations = stations;
	}

	public List<Station> getStations() throws Exception {

		if (stations.isEmpty())
			throw new WebApplicationException("Stations list is empty");
		else
			return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}


	public String toString() {
		String aux= "";
		for (Station station : stations) {
			aux +=station.toString();
		}
		return aux;
	}
	
	public Station getId (String id) throws Exception  {
		
		for (Station station : stations) {
			if (station.getId().equals(id)) {
				return station;
			}
		}
	
		throw new WebApplicationException("this station does not exist");
	}
	
	public void add(Station station) {
		stations.add(station);

	}
	
}
