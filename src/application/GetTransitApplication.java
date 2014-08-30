package application;

import java.sql.SQLException;
import java.util.HashMap;

import models.NextBusBody;
import models.RTT;
import models.RTT.Agency;
import models.RouteForFiveOneOne;
import models.RouteForNextBus;
import adapter.FiveOneOneAPIService;
import adapter.NextbusAPIService;

import com.mobprofs.retrofit.converters.SimpleXmlConverter;

import database.TransitDatabase;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

public class GetTransitApplication {
	private static final String AGENCY = "sf-muni";
	
	private static GetTransitApplication singleton = null;
	private FiveOneOneAPIService fiveOneOneAdapter;
	private NextbusAPIService nextBusAdapter;
	private TransitDatabase dbAdapter;
	
	protected GetTransitApplication() {
		RestAdapter restAdapter;
		
		restAdapter = new RestAdapter.Builder()
			.setEndpoint(FiveOneOneAPIService.ROOT)
			.setConverter(new SimpleXmlConverter())
			.build();
		fiveOneOneAdapter = restAdapter.create(FiveOneOneAPIService.class);
		
		restAdapter = new RestAdapter.Builder()
			.setEndpoint(NextbusAPIService.ROOT)
			.setConverter(new SimpleXmlConverter())
			.build();
		nextBusAdapter = restAdapter.create(NextbusAPIService.class);

		dbAdapter = new TransitDatabase();
		
		/* 
		 * This is the function to create/update the stops' GPS location in database.
		 * Call only once when first time starting the server.
		 * Or design other method to call this method when needed.
		 */ 
		//updateStation();
	}
	
	public static GetTransitApplication getInstance() {
		if (singleton == null) {
			singleton = new GetTransitApplication();
		}
		return singleton;
	}
	
	public void close() {
		try {
			dbAdapter.close();
			singleton = null;
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	private void updateStation() {
		NextBusBody sfRouteList = nextBusAdapter.getRouteList(AGENCY);
		
		for (RouteForNextBus route : sfRouteList.getRouteList()) {
			updateStation(route.getTag());
		}
	}
	
	private void updateStation(String routeTag) {
		NextBusBody oneRoute = nextBusAdapter.getRoute(AGENCY, routeTag);
		if (oneRoute.getRouteList().size() != 1) return; // Should be only one
		
		for (RouteForNextBus route : oneRoute.getRouteList())
			dbAdapter.updateStation(route);		
	}
	
	/**
	 * Get the stop list within the GPS location
	 * @param lat
	 * @param lon
	 * @param scale
	 * @return HashMap<stopName, stopID>
	 */
	public HashMap<String, String> getStopList(double lat, double lon, double scale) {
		return dbAdapter.getStopIdList(lat, lon, scale);
	}
	
	/**
	 * Return all the buses in the station, including the departure time in 90 minutes.
	 * @param stopID
	 * @return RTT busList
	 */
	public RTT busListByStation(String stopID) {
		RTT rtt = null;
		
		try {
			rtt = fiveOneOneAdapter.
					getNextDeparturesByStopCode(FiveOneOneAPIService.TOKEN, stopID);
		} catch (RetrofitError ex) {
			ex.printStackTrace();
		}
		
		return rtt;
	}
	
	/**
	 * Return all the buses in the station, including the departure time in 90 minutes.
	 * @param stopName
	 * @param stopID
	 * @return busList in String format
	 */
	public String printBusListByStation(String stopName, String stopID) {
		StringBuffer buf = new StringBuffer();
		
		try {
			RTT rtt = busListByStation(stopID);
			if (rtt==null) return null;
			
			buf.append("Stop:" + stopName + "\n");
			buf.append(rtt.toString());
			for (Agency agency: rtt.getAgencyList()) {
				
				for (RouteForFiveOneOne route: agency.getStopList()) {
					buf.append(route.toString());
				}
			}
		} 
		catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		
		return buf.toString();
	}
	
	/**
	 * Local test
	 * Please run as java application
	 * @param args
	 */
	public static void main(String args[]) {
		GetTransitApplication test = new GetTransitApplication();
		
		HashMap<String, String> stationList = test.getStopList(37.765, -122.465, 0.005);
		System.out.println(stationList.keySet().size());
		for (String it : stationList.keySet()) {
			System.out.print(it);
			System.out.println(" " + stationList.get(it));
		}
		
		System.out.print(test.printBusListByStation("",""));
		System.out.print(test.printBusListByStation("9th Ave & Kirkham St","13217"));
	}
}
