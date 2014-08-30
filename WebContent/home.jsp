<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript">
		function foo() {
			if (!navigator.geolocation) {
				window.location += "?GeolocationError";
				alert("Geolocation is not supported by your browser!");
			    return;
			}
			
			function success(position) {
			    var latitude  = position.coords.latitude;
			    var longitude = position.coords.longitude;
			    
			    window.location += ("?lat=" + latitude + "&lon=" + longitude);
			};
			
			function error() {
				window.location += "?GeolocationError";
				alert("Unable to retrieve your location!");
			};
			
			document.writeln("<p>loading</p>");
			navigator.geolocation.getCurrentPosition(success, error);
		}
	</script>
	<title>Uber Transit Searching Service</title>
</head>

<body>
	<center>
		<h1>Welcome to Uber Transit Searching Service</h1>
		
		<script type="text/javascript">
			// Refresh the page until finding geolocation or geolocation error
			if (window.location.href.indexOf('?lat') == -1 &&
					window.location.href.indexOf('?GeolocationError') == -1)
				foo();
		</script>
		<p>Default shows your current location</p>
		<form action="Station" method="get">
			Your latitude: 
				<input type="text" name="lat" pattern =  "[-+]?[0-9]*\.?[0-9]*" required
					value = <%= (String) request.getParameter("lat") %>
				/><br />
		    Your longitude: 
		    	<input type="text" name="lon", pattern =  "[-+]?[0-9]*\.?[0-9]*", required 
		    		value = <%= (String) request.getParameter("lon") %>
		    	/><br />
		    Scale: <input type="text" name="distance", pattern =  "[0-9]*\.?[0-9]?", required /> miles<br />
		<input type="submit" value="Go" />
		</form>
	</center>
</body>
</html>