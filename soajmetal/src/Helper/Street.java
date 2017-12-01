package Helper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * @author Ala' Hashesh
 * @version 1.0
 * @since 28-11-2017
 *
 */
public class Street {

	String id;
	
	int lanes;
	
	Location start;
	
	Location end;
	
	/*
	 * In kilometers
	 */
	int length;
	
	/*
	 * Cars per minute
	 */
	@SerializedName("flow_rate")
	float flowRate;
	
	@SerializedName("current_number_of_cars")
	int currentNumberOfCars;
	
	@SerializedName("maximum_allowed_cars")
	int maximumAllowedCars;
	
	/*
	 * Connections with this street
	 */
	Map<String, Double> edges = new HashMap<>();
	
	/**
	 * Add connection from this street to another street
	 * @param street	The other street
	 * @param cost		Cost (i.e. time)
	 */
	public void addEdge(String street, Double cost) {
		if (!edges.containsKey(street)) {
			edges.put(street, cost);
		}
	}
	
	public Iterator<Map.Entry<String, Double>> getEdges() {
		return edges.entrySet().iterator();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLanes() {
		return lanes;
	}

	public void setLanes(int lanes) {
		this.lanes = lanes;
	}

	public Location getStart() {
		return start;
	}

	public void setStart(Location start) {
		this.start = start;
	}

	public Location getEnd() {
		return end;
	}

	public void setEnd(Location end) {
		this.end = end;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public float getFlowRate() {
		return flowRate;
	}

	public void setFlowRate(float flowRate) {
		this.flowRate = flowRate;
	}

	public int getCurrentNumberOfCars() {
		return currentNumberOfCars;
	}

	public void setCurrentNumberOfCars(int currentNumberOfCars) {
		this.currentNumberOfCars = currentNumberOfCars;
	}

	public int getMaximumAllowedCars() {
		return maximumAllowedCars;
	}

	public void setMaximumAllowedCars(int maximumAllowedCars) {
		this.maximumAllowedCars = maximumAllowedCars;
	}

	public void setEdges(Map<String, Double> edges) {
		this.edges = edges;
	}
	
}
