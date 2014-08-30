package integrationTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHomeStop {
	private static final String ROOT_URL = "http://localhost:8080/UberWeb";

	/*
	 * Test Home Page:
	 * 1. send request ROOT_URL should respond "?lat=xxx&lon=xxx" or "?GeolocationError"
	 * 2. fill the blanks in wrong format should remain on the page
	 * 3. fill the blanks in right format should redirect to Stop Page
	 */

	/*
	 * Test Stop Page:
	 * 1. should have request parameter "lat" "lon" "distance"
	 * 2. select one stop should send POST request and get departure time list
	 */
}
