package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.GetTransitApplication;

@WebServlet("/Station")
public class TransitStopPage extends HttpServlet {
	/**
	 * The page showing the nearby stations
	 */
	private static final long serialVersionUID = -8734969439915892127L;
	private static final double PI = 3.14159265359;
	private static final double EARTHRADIUS = 3963.1676;
	private GetTransitApplication transitApp;
	
	
	public TransitStopPage() {
		super();
		transitApp = GetTransitApplication.getInstance();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		String latitude = request.getParameter("lat");
		String longitude = request.getParameter("lon");
		String distance = request.getParameter("distance");
		
		// Get Stations Near the client
		Double angle = Double.parseDouble(distance) * 180.0 / (PI * EARTHRADIUS);
		
		HashMap<String, String> stationList = transitApp.getStopList(Double.parseDouble(latitude), 
				Double.parseDouble(longitude), angle);
		
		session.setAttribute( "StationList", stationList );
		session.setAttribute("departureTime", null);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("station.jsp");
        dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		HashMap<String, String> stationList = null;
		
		if (session.getAttribute("StationList") instanceof HashMap) {
			stationList = (HashMap<String, String>) session.getAttribute("StationList");
		}
		String stopName = request.getParameter("station");
		String departureTime = transitApp.printBusListByStation(stopName, stationList.get(stopName));
		session.setAttribute("departureTime", departureTime);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("station.jsp");
        dispatcher.forward(request, response);
	}


}
