package Helper;

/**
 * 
 * Slot that a driver can park
 * 
 * @author Ala' Hashesh
 * @version 1.0
 * @since 28-11-2017
 *
 */
public class Slot extends Gene {

	Street streetId;
	
	Double availability;
	
	Location location;
	
	String parkingStructureId;
	
	String level;
	
	String deviceId;

	public Street getStreetId() {
		return streetId;
	}

	public void setStreetId(Street streetId) {
		this.streetId = streetId;
	}

	public Double getAvailability() {
		return availability;
	}

	public void setAvailability(Double availability) {
		this.availability = availability;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getParkingStructureId() {
		return parkingStructureId;
	}

	public void setParkingStructureId(String parkingStructureId) {
		this.parkingStructureId = parkingStructureId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
}
