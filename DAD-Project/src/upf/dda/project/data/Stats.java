package upf.dda.project.data;

public class Stats {

	private double openedStations;
	private double bikesPerStation;
	private double percentageOfUsedBikes;
	private int bikesNearCampusPoblenou;
	
	public Stats(){
		this.openedStations = 0;
		this.bikesPerStation = 0;
		this.percentageOfUsedBikes = 0;
		this.bikesNearCampusPoblenou = 0;
	}
	
	public Stats(double openedStations, double bikesPerStation, double percentageOfUsedBikes, int bikesNearCampusPoblenou){
		this.openedStations = openedStations;
		this.bikesPerStation = bikesPerStation;
		this.percentageOfUsedBikes = percentageOfUsedBikes;
		this.bikesNearCampusPoblenou = bikesNearCampusPoblenou;
	}

	public double getOpenedStations() {
		return openedStations;
	}

	public void setOpenedStations(double openedStations) {
		this.openedStations = openedStations;
	}

	public double getBikesPerStation() {
		return bikesPerStation;
	}

	public void setBikesPerStation(double bikesPerStation) {
		this.bikesPerStation = bikesPerStation;
	}

	public double getPercentageOfUsedBikes() {
		return percentageOfUsedBikes;
	}

	public void setPercentageOfUsedBikes(double percentageOfUsedBikes) {
		this.percentageOfUsedBikes = percentageOfUsedBikes;
	}

	public int getBikesNearCampusPoblenou() {
		return bikesNearCampusPoblenou;
	}

	public void setBikesNearCampusPoblenou(int bikesNearCampusPoblenou) {
		this.bikesNearCampusPoblenou = bikesNearCampusPoblenou;
	}

	public String toString() {
		return "Stats about Bicing CAT:\nOpened stations (%) = " + openedStations + ", \nBikes per station = " + Math.round(bikesPerStation)
				+ ", \nPercentage of used bikes = " + percentageOfUsedBikes + ", \nBikes near Campus Poblenou (stations 342, 142, 152, 153, 393 and 428) = "
				+ bikesNearCampusPoblenou;
	}
	
}