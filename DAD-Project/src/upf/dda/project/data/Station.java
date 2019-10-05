package upf.dda.project.data;

public class Station {

	private String id;
	private String type;
	private String latitude;
	private String longitude;
	private String streetName;
	private String streetNumber;
	private String altitude;
	private String slots;
	private String bikes;
	private String nearbyStations;
	private String status;
	
	public Station(String id, String type,String latitude, 
			String longitude, String streetName, String streetNumber, 
			String altitude, String slots, String bikes, 
			String nearbyStations, String status){
		this.id = id;
		this.type = type;
		this.latitude = latitude;
		this.longitude = longitude;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.altitude = altitude;
		this.slots = slots;
		this.bikes = bikes;
		this.nearbyStations = nearbyStations;
		this.status = status;
	}
	
	public Station(){
		
		this.id = "";
		this.type = "";
		this.latitude = "";
		this.longitude = "";
		this.streetName = "";
		this.streetNumber = "";
		this.altitude = "";
		this.slots = "";
		this.bikes = "";
		this.nearbyStations = "";
		this.status = "";
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	public String getSlots() {
		return slots;
	}
	public void setSlots(String slots) {
		this.slots = slots;
	}
	public String getBikes() {
		return bikes;
	}
	public void setBikes(String bikes) {
		this.bikes = bikes;
	}
	public String getNearbyStations() {
		return nearbyStations;
	}
	public void setNearbyStations(String nearbyStations) {
		this.nearbyStations = nearbyStations;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		String aux= "ID = " + id + ". Type = " + type + ". Latitude = " + latitude + ". Longitude = " + longitude +  ". StreetName = " 
				+ streetName + ". StreetNumber = " + streetNumber + ". Altitude = " + altitude + ". Slots = " + slots + ".bikes = " + bikes 
				+ ". NearbyStations = " + nearbyStations + ". Status = " + status + "\n";
		return aux;
	}
	
}
