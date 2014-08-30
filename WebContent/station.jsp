<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.HashMap" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Station List</title>
</head>
<body>
  <center>
	<h1>Please choose one station near you!</h1>
	<form action="Station" method="post">
		<table border=1>
		  <tr>
		    <th>Choose Station</th>
		    <td>
		      <select name= "station">
		        <% HashMap<String, String> stationList = 
		        	(HashMap<String, String>) session.getAttribute("StationList");
		           for(String stopName: stationList.keySet()) { %>
			        <option value="<%= stopName %>"><%= stopName %></option>
		        <% } %>
		      </select>
		    </td>
		  </tr>
		  <tr>
		    <td colspan=2 align="right" ><input type="submit" value="Submit" /></td>
		  </tr>
		</table>
	</form>
	<h1>Departure Time</h1>
	<p><%= (String) session.getAttribute("departureTime") %></p>
  </center>
</body>
</html>