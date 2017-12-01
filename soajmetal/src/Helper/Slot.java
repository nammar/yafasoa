package Helper;

import com.google.gson.annotations.SerializedName;

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

	String id;
	
	@SerializedName("street_id")
	String streetId;
	
	Boolean availability;
	
	Location location;
	
	@SerializedName("parking_structure_id")
	String parkingStructureId;
	
	String level;
	
	@SerializedName("device_id")
	String deviceId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStreetId() {
		return streetId;
	}

	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}

	public Boolean isAvailable() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
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

	@Override
	public String getIdentifier() {
		return id;
	}
	
}
