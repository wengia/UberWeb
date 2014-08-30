package models;

import java.util.List;

import org.simpleframework.xml.*;

@Root(name = "route")
public class RouteForNextBus {
	@Attribute(name = "tag")
	private String tag;
	
	@Attribute(name = "title")
	private String title;
	
	@Attribute(name = "shortTitle", required = false)
	private String shortTitle;
	
	// Detail of One Route
	@Attribute(name = "color", required = false)
	private String color;
	
	@Attribute(name = "oppositeColor", required = false)
	private String oppositeColor;
	
	@Attribute(name = "latMin", required = false)
	private String latMin;
	
	@Attribute(name = "latMax", required = false)
	private String latMax;
	
	@Attribute(name = "lonMin", required = false)
	private String lonMin;
	
	@Attribute(name = "lonMax", required = false)
	private String lonMax;
	
	@ElementList(inline = true, required = false)
	private List<Stop> stopList;
	
	@ElementList(inline = true, required = false)
	private List<Direction> directions;
	
	@Root(name = "stop")
	public static class Stop {
		@Attribute(name = "tag")
		private String tag;
		
		@Attribute(name = "title", required = false)
		private String title;
		
		@Attribute(name = "lat", required = false)
		private String latitude;
		
		@Attribute(name = "lon", required = false)
		private String longitude;
		
		@Attribute(name = "stopId", required = false)
		private String stopId;
		
		public String getStopId() {return stopId;}
		
		public String getTitle() {return title;}
		
		public String getLatitude() {return latitude;}
		
		public String getLongitude() {return longitude;}
	}
	
	@Root(name = "direction")
	public static class Direction{
		@Attribute(name = "tag")
		private String tag;
		
		@Attribute(name = "title")
		private String title;
		
		@Attribute(name = "name")
		private String name;
		
		@Attribute(name = "useForUI")
		private String useForUI;
		
		@ElementList(inline = true)
		private List<Stop> dirStopList;
	}
	
	public String getTag() {return tag;}
	
	public List<Stop> getStopList() {return stopList;};
}
