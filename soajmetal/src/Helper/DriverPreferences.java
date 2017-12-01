package Helper;

import com.google.gson.annotations.SerializedName;

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

	String id;
	
	String name;
	
	String carId;
	
	@SerializedName("current_location")
	Location currentLocation;
	
	@SerializedName("current_street")
	String currentStreet;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Street getCurrentStreet() {
		return City.getInstance().getStreet(currentStreet);
	}

	public void setCurrentStreet(String currentStreet) {
		this.currentStreet = currentStreet;
	}

	@Override
	public String getIdentifier() {
		return id;
	}
	
}
