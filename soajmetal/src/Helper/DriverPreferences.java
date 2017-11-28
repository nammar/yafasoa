package Helper;

/**
 * 
 * Driver Preferences
 * 
 * @author Ala' Hashesh
 * @version 1.0
 * @since 28-11-2017
 *
 */
public class DriverPreferences extends Gene {

	String name;
	
	String id;
	
	String carId;
	
	Location currentLocation;
	
	double parkingDuration;
	
	double targetCost;
	
	Location targetLocation;
	
	/*
	 * We can tell which street from GPS
	 */
	Street currentStreet;
	
	Street targetStreet;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public double getParkingDuration() {
		return parkingDuration;
	}

	public void setParkingDuration(double parkingDuration) {
		this.parkingDuration = parkingDuration;
	}

	public double getTargetCost() {
		return targetCost;
	}

	public void setTargetCost(double targetCost) {
		this.targetCost = targetCost;
	}

	public Location getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(Location targetLocation) {
		this.targetLocation = targetLocation;
	}

	public Street getCurrentStreet() {
		return currentStreet;
	}

	public void setCurrentStreet(Street currentStreet) {
		this.currentStreet = currentStreet;
	}

	public Street getTargetStreet() {
		return targetStreet;
	}

	public void setTargetStreet(Street targetStreet) {
		this.targetStreet = targetStreet;
	}
	
}
