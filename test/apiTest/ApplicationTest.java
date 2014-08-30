package apiTest;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import application.GetTransitApplication;

public class ApplicationTest {

	@Test
	public void testGetInstance() {
		GetTransitApplication tester = GetTransitApplication.getInstance();
		assertNotNull("Init TransitApplication", tester);
	}

	@Test
	public void testGetStopList() {
		GetTransitApplication tester = GetTransitApplication.getInstance();
		HashMap<String, String> stopList = tester.getStopList(37.765, -122.465, 0.005);
		assertEquals("Get StopList", 14, stopList.size());
	}

	@Test
	public void testBusListByStation() {
		GetTransitApplication tester = GetTransitApplication.getInstance();
		assertNull("Get StopList and transfer to String", tester.busListByStation(""));
		assertNotNull("Get StopList and transfer to String", tester.busListByStation("13217"));
	}
	
	@Test
	public void testPrintBusListByStation() {
		GetTransitApplication tester = GetTransitApplication.getInstance();
		assertNull("Get StopList and transfer to String", 
				tester.printBusListByStation("",""));
		assertNotNull("Get StopList and transfer to String", 
				tester.printBusListByStation("9th Ave & Kirkham St", "13217"));
	}

}
