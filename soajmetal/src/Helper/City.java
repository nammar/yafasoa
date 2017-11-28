package Helper;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Ala' Hashesh
 * @version 1.0
 * @since 28-11-2017
 *
 */
public class City {

	/**
	 * Streets in a city
	 */
	private static Map<String, Street> vertices = new HashMap<>();
	
	private static City city;
	
	private City() {
		
	}
	
	public static City getInstance() {
		
		if (city == null) {
			city = new City();
		}
		
		return city;
		
	}
	
	/**
	 * Add street to the city
	 * @param name	Street unique name
	 * @param street	Street Object
	 */
	public void addStreet(String name, Street street) {
		if (!vertices.containsKey(name)) {
			vertices.put(name, street);
		}
	}
	
	/**
	 * Add joint (i.e. connection between two streets)
	 * @param s1	Street 1 name
	 * @param s2	Street 2 name
	 */
	public void addJoint(String s1, String s2, Double cost) {
		if (vertices.containsKey(s1)) {
			Street street = vertices.get(s1);
			street.addEdge(s2, cost);
		}
	}
	
}
