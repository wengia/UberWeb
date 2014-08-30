package database;

import java.sql.SQLException;
import java.util.HashMap;

import models.RouteForNextBus;

public class TransitDatabase {
	private JDBCadapter adapter;
	private String URL;
	private String driver;
	private String user;
	private String passwd;

	public TransitDatabase() {
		URL = "jdbc:mysql://localhost:3306/uber";
		driver = "com.mysql.jdbc.Driver";
		user = "root";
		passwd = "1111";

		adapter = new JDBCadapter(URL, driver, user, passwd);
	}
	
	public void close() throws SQLException {
		adapter.close();
	}
	
	public void updateStation(RouteForNextBus route) {
		adapter.insertStop(route);
	}
	
	public HashMap<String, String> getStopIdList(double lat, double lon, double scale) {
		return adapter.selectStop(lat - scale, lat + scale, lon - scale, lon + scale);
	}
}
