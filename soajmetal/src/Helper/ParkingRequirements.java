package Helper;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * Parking Requirements
 * 
 * @author Ala' Hashesh
 * @version 1.0
 * @since 01-12-2017
 *
 */
public class ParkingRequirements extends Gene {

	public static final int NORMAL = 1;
	public static final int IMPORTANT = 2;
	public static final int VIP = 3;
	
	String type;
	
	String date;
	
	String time;
	
	int level;
	
	@SerializedName("max_cost")
	double maxCost;
	
	double duration;
	
	@SerializedName("target_location")
	Location targetLocation;
	
	@SerializedName("targetStreet")
	String targetStreet;
	
	@SerializedName("driver_id")
	String driverId;
	
	@Override
	public String getIdentifier() {
		return driverId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(double maxCost) {
		this.maxCost = maxCost;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Location getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(Location targetLocation) {
		this.targetLocation = targetLocation;
	}

	public String getTargetStreet() {
		return targetStreet;
	}

	public void setTargetStreet(String targetStreet) {
		this.targetStreet = targetStreet;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

}
