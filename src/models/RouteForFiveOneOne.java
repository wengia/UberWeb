package models;

import java.util.List;

import org.simpleframework.xml.*;

@Root(name = "Route")
public class RouteForFiveOneOne {
	@Attribute(name = "Name")
	private String name;
	
	@Attribute(name = "Code")
	private String code;
	
	@ElementList(name = "RouteDirectionList", required = false)
	private List<RouteDirection> routeDirectionList;
	
	@Root(name = "RouteDirection")
	public static class RouteDirection {
		@Attribute(name = "Name")
		private String name;
		
		@Attribute(name = "Code")
		private String code;
		
		@ElementList(name = "StopList", required = false)
		private List<Stop> stopList;
		
		@Root(name = "Stop")
		public static class Stop {
			@Attribute(name = "name")
			private String name;
			
			@Attribute(name = "StopCode")
			private String stopcode;
			
			@ElementList(name = "DepartureTimeList", entry = "DepartureTime", required = false)
			private List<String> departureTimeList;
		}
		
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append("Route: " + name + ", Code: " + code + "\n");
		for(RouteDirection dir: routeDirectionList) {
			buf.append("Direction: " + dir.name + "\n");
			for (RouteDirection.Stop stop: dir.stopList) {
				buf.append("Depart in:\n");
				for (String depart: stop.departureTimeList) {
					buf.append(depart + "minutes" + "\n");
				}
			}
		}
		
		return buf.toString();
	}
}
