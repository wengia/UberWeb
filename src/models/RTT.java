package models;

import java.util.List;

import org.simpleframework.xml.*;

@Root(name = "RTT")
public class RTT {
	@ElementList(name = "AgencyList")
	private List<Agency> agencyList;

	@Root(name = "Agency")
	public static class Agency {
		@Attribute(name = "Name")
		private String name;
		
		@Attribute(name = "HasDirection")
		private String hasDirection;
		
		@Attribute(name = "Mode")
		private String mode;
		
		@ElementList(name = "RouteList", required = false)
		private List<RouteForFiveOneOne> stopList;
		
		public List<RouteForFiveOneOne> getStopList() {return stopList;}
	}
	
	public List<Agency> getAgencyList() {return agencyList;}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		try {
			for(Agency agency: agencyList)
				buf.append( "Agency: " + agency.name + 
						", HasDirection: " + agency.hasDirection + 
						", Mode: " + agency.mode + "\n");
		}
		catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		
		return buf.toString();
	}
}
