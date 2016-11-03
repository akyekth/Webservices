package clients;


import core.*;
import core.Itinerary;
import resource.*;

import java.util.List;

public class ItineraryClient {
    public static void main(String[] args) {
        AuthServiceResourceImplService authservice = new AuthServiceResourceImplService();
        AuthServiceResource authService = authservice.getAuthServiceResourceImplPort();

        ItineraryServiceResourceImplService itservice = new ItineraryServiceResourceImplService();
        ItineraryServiceResource itineraryService = itservice.getItineraryServiceResourceImplPort();

        String token = authService.login("user1", "user1");
        try {
            String src = "A", dest = "E";
            List<core.Itinerary> itineraries = itineraryService.findItineraries(token, src, dest);
            if(itineraries != null) {
                System.out.println("(FIND ME) Available flights");
                for(Itinerary i: itineraries) {
                    System.out.print(i.toString());
                    System.out.println("(FINDME) Requesting itinerary: " +
                            itineraryService.getItinerary(token, i.getId()).getRoute().size());
                }
            }
            else {
                System.out.println("(FIND ME) No flights available from " + src + " to " + dest);
            }
        } catch (NotAllowedException e) {
            System.out.println("(FINDME) Invalid credentials");
        }
    }
}
