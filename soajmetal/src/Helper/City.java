package Helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 
 * @author Ala' Hashesh
 * @version 1.0
 * @since 28-11-2017
 *
 */
public class City {

	/*
	 * Streets in a city
	 */
	private static Map<String, Street> vertices = new HashMap<>();

	private static City city;

	private Slot[] slots;

	private DriverPreferences[] driversPreferences;
	
	private ParkingRequirements[] parkingRequirements;

	private City() {
		initCity();
	}

	public static City getInstance() {

		if (city == null) {
			city = new City();
		}

		return city;

	}

	private void initCity() {
		Gson gson = new Gson();

		try {

			/* Read Streets */
			String streetsJson = new String(Files.readAllBytes(Paths.get("inputs/streets.json")));
			Street[] streets = gson.fromJson(streetsJson, Street[].class);

			for(Street s : streets) {
				vertices.put(s.getId(), s);
			}
			
			/* Read Slots */
			String slotsJson = new String(Files.readAllBytes(Paths.get("inputs/slots.json")));
			slots = gson.fromJson(slotsJson, Slot[].class);

			/* Read Driver Preferences */
			String driverPreferencesJson = new String(Files.readAllBytes(Paths.get("inputs/drivers.json")));
			driversPreferences = gson.fromJson(driverPreferencesJson, DriverPreferences[].class);
			
			/* Read Parking Requirements */
			String parkingRequirementsJson = new String(Files.readAllBytes(Paths.get("inputs/parking_requirements.json")));
			parkingRequirements = gson.fromJson(parkingRequirementsJson, ParkingRequirements[].class);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Add street to the city
	 * 
	 * @param name
	 *            Street unique name
	 * @param street
	 *            Street Object
	 */
	public void addStreet(String name, Street street) {
		if (!vertices.containsKey(name)) {
			vertices.put(name, street);
		}
	}

	/**
	 * Add joint (i.e. connection between two streets)
	 * 
	 * @param s1
	 *            Street 1 name
	 * @param s2
	 *            Street 2 name
	 */
	public void addJoint(String s1, String s2, Double cost) {
		if (vertices.containsKey(s1)) {
			Street street = vertices.get(s1);
			street.addEdge(s2, cost);
		}
	}

	/**
	 * Get a specific street in the city
	 * 
	 * @param name
	 * @return The street with the provided name
	 */
	public Street getStreet(String name) {

		if (vertices.containsKey(name)) {
			vertices.get(name);
		}

		return null;
	}
}
