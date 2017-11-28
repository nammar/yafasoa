package Helper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author Ala' Hashesh
 * @version 1.0
 * @since 28-11-2017
 *
 */
public class Street {

	String id;
	
	Location startPoint;
	
	Location endPoint;
	
	int lanes;
	
	/**
	 * In kilometers
	 */
	int length;
	
	/**
	 * Cars per minute
	 */
	float flowRate;
	
	int currentNumberOfCars;
	
	/**
	 * Connections with this street
	 */
	Map<String, Double> edges = new HashMap<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Location getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Location startPoint) {
		this.startPoint = startPoint;
	}

	public Location getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Location endPoint) {
		this.endPoint = endPoint;
	}

	public int getLanes() {
		return lanes;
	}

	public void setLanes(int lanes) {
		this.lanes = lanes;
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
	
}
