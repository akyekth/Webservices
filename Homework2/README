 #Web services
 
 ﬂight ticket reservation service built upon SOAP and web services. The testing of these services was done by the clients
 who parsed a wsdl ﬁle.
 
 ### Task 1
The ﬁrst step is to build a user login system as we don’t want them to go on a rampage without any control. In order to accomplish this we compare the user’s credentials against some hardcoded userbase. If the check succeeds then we return a token(a random number) otherwise a ”not-valid” token.
Included with the report, we have the ﬂightReservation-doc.wsdl used to generated the authentication web service.
wsimport -keep authorize.wsdl
The rest of the clients used the remote wsdl ﬁles:
wsimport -keep http://localhost:8080/soap/itinerary?WSDL wsimport -keep http://localhost:8080/soap/book?WSDL
The available web services are(all veriﬁed through the passed auth token): • Authorize a user(/soap/auth/login) • Search ﬂights given a source and a destination(/soap/itinerary) • Search itinerary by id(/soap/itinerary) • bookTicketsForItinenary(/soap/book) • issueTickets(/soap/book)
...


 Task 2
The test clients are the following: • AuthClient • ItineraryClient(Checks both operations) •
BookTicket To deploy the .war ﬁle we run the following command:

mvn clean package 
asadmin start-domain asadmin 
deploy target/foo.war

The SOAPHandler can be used for logging and metrics analysis but we didn’t implement it in our project.
