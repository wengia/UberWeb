Project: departure times
Technocal Track: Back-end

Link to Host (Amazon EC2): http://54.68.5.97:8080/UberWeb
Link to my GitHub:

Reason:
My expertise mainly lies on C++ and Java. I don’t know much about front end languages like Javascript and CSS. I have experience in developing Android Apps and simple Web App. Among all these projects, my passion was on building the back-end API.

Introduction:
In this project, I use Java and HTML to complete most of the coding work. This is a dynamic web application depending on Tomcat and Mysql. Mysql stores the GPS locations of all the stations providing by Nextbus. Retrofit and SimpleXML frameworks are deployed to retrieve the data from 511 and Nextbus. To test the project, JUnit is used. Few Javascript codes are written in the “home.jsp” to get the geolocation (Geolocation.getCurrentPosition() provided by Mozilla) of the client.

Trade-offs:
Limited stations. 511.org helps to provide the departure time while Nextbus is used to get the GPS location of the stations. Although 511 provides all the transit agency data in San Francisco, Nextbus only give the station location of “SF-MUNI”. So at this stage, the server only gives the bus departure time on the stations “SF-MUNI” has.

Front-end outlook. The web service looks not so good to the user. This is because my focus is mainly on the back-end.

Test. JUnit is useful for the unit test. Thus the integration test is not complete. If giving more time, I will learn Javascript and implement the integration test using Javacript based test tools.

Minimum construction. The project is scalable, but only implements the “Departure Time” features. Other features, like showing location on a map and judging the best stations nearby according to the user’s destination, are not realized. To meet the fast changing requirement, I designed the structure that would be easy for further refactor and meeting only what currently needs.
