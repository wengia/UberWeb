package controllerTest;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.TransitHomePage;

public class HomepageTest {

	@Test
	public void testTransitHomePage() {
		TransitHomePage tester = new TransitHomePage();
		assertNotNull("Init Home Page", tester);
	}

}
