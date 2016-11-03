package clients;


import core.Itinerary;
import resource.*;

import java.util.List;

public class BookingClient {
    public static void main(String[] args) {
        AuthServiceResourceImplService authservice = new AuthServiceResourceImplService();
        AuthServiceResource authService = authservice.getAuthServiceResourceImplPort();

        ItineraryServiceResourceImplService itservice = new ItineraryServiceResourceImplService();
        ItineraryServiceResource itineraryService = itservice.getItineraryServiceResourceImplPort();

        BookingServiceResourceImplService bservice = new BookingServiceResourceImplService();
        BookingServiceResource bookingService = bservice.getBookingServiceResourceImplPort();

        String token = authService.login("user1", "user1");
        try {
            String src = "A", dest = "E";
            List<Itinerary> itineraries = itineraryService.findItineraries(token, src, dest);
            Itinerary last = null;
            if(itineraries != null) {
                System.out.println("(FIND ME) Available flights");
                for(Itinerary i: itineraries) {
                    System.out.print(i.toString());
                    System.out.println("(FINDME) Requesting itinerary: " +
                            itineraryService.getItinerary(token, i.getId()).getRoute().size());
                    last = i;
                }

                System.out.println("(FINDME) Booking ticket with ID: " + last.getId());
                System.out.println(bookingService.bookTicket(token, "user1", last.getId(), "12345").getExtrainfo());
            }
            else {
                System.out.println("(FIND ME) No flights available from " + src + " to " + dest);
            }
        } catch (NotAllowedException e) {
            System.out.println("(FINDME) Invalid credentials");
        }
    }
}
