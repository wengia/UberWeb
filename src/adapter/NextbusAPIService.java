package adapter;

import models.NextBusBody;
import retrofit.http.GET;
import retrofit.http.Query;

public interface NextbusAPIService {
	public static final String ROOT = "http://webservices.nextbus.com/service";
	
	@GET("/publicXMLFeed?command=routeList")
	public NextBusBody getRouteList(
			@Query("a") String agency); // should be "sf-muni" to San Francisco
	
	@GET("/publicXMLFeed?command=routeConfig&terse")
	public NextBusBody getRoute(
			@Query("a") String agency,  // should be "sf-muni" to San Francisco
			@Query("r") String routeTag); // should obtain from getRoute(String)
	
}
