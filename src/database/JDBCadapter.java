package database;

import java.sql.*;
import java.util.HashMap;

import models.RouteForNextBus;
import models.RouteForNextBus.Stop;

public class JDBCadapter {
	/**
	 * The IO to database
	 */
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;
	
	public JDBCadapter(String url, String driverName, String user, String passwd) {
		try {
			Class.forName(driverName);
			System.out.println("Opening db connection");
			
			connection = DriverManager.getConnection(url, user, passwd);
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void close() throws SQLException {
        System.out.println("Closing db connection");
        if (preparedStatement != null) preparedStatement.close();
        connection.close();
    }
	
	/**
	 * Select all the stop within the latitude and longitude
	 * 
	 * @param lat_min
	 * @param lat_max
	 * @param lon_min
	 * @param lon_max
	 * @return HashMap<stopName, stopId>
	 */
	public HashMap<String, String> selectStop( 
			double lat_min, double lat_max, double lon_min, double lon_max) {
		HashMap<String, String> stopList = new HashMap<String, String>();
		String query = "select * from station where "
				+ "lat > ? and lat < ? and "
				+ "lon > ? and lon < ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, lat_min);
			preparedStatement.setDouble(2, lat_max);
			preparedStatement.setDouble(3, lon_min);
			preparedStatement.setDouble(4, lon_max);
			ResultSet rs = preparedStatement.executeQuery();
			
			while( rs.next() ) {
				String stopId = rs.getString("stopID");
				String stopName = rs.getString("stopName");
				//System.out.println("stopID " + stopId ); // for debug
				stopList.put(stopName, stopId);
			}
			
			rs.close();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return stopList;
	}
	
	/**
	 * Insert the stop from nextbus
	 * @param route
	 */
	public void insertStop(RouteForNextBus route) {
		String query = "REPLACE INTO station "
				+ "(stopID, stopName, lat, lon) VALUES "
				+ "(?,?,?,?)";

		try {
			preparedStatement = connection.prepareStatement(query);
			for (Stop stop: route.getStopList()) {				
				preparedStatement.setString(1, stop.getStopId());
				preparedStatement.setString(2, stop.getTitle());
				preparedStatement.setDouble(3, Double.parseDouble(stop.getLatitude()));
				preparedStatement.setDouble(4, Double.parseDouble(stop.getLongitude()));
	
				// execute insert SQL statement
				preparedStatement.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete stop
	 * @param stopId
	 */
	public void deleteContact(String stopId) {
		String query = "DELETE FROM station WHERE stopID = ?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, stopId);

			// execute insert SQL statement
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
