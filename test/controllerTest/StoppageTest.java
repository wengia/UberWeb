package controllerTest;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.TransitStopPage;

public class StoppageTest {

	@Test
	public void testTransitStopPage() {
		TransitStopPage tester = new TransitStopPage();
		assertNotNull("Init Stop Page", tester);
	}


}
