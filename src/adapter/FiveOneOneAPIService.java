package adapter;

import models.RTT;
import retrofit.http.GET;
import retrofit.http.Query;

public interface FiveOneOneAPIService {
	public static final String ROOT = "http://services.my511.org/Transit2.0";
	public static final String TOKEN = "dcb62b7f-c58f-4cc4-91e7-090beecc6844";
	
	@GET("/GetAgencies.aspx")
	public RTT getAgencies(@Query("token") String token);
	
	@GET("/GetNextDeparturesByStopName.aspx?")
	public RTT getNextDeparturesByStopName(
			@Query("token") String token,
			@Query("agencyName") String agencyName,
			@Query("stopName") String stopName);
	
	@GET("/GetNextDeparturesByStopCode.aspx")
	public RTT getNextDeparturesByStopCode(
			@Query("token") String token, 
			@Query("stopcode") String stopcode);
}
