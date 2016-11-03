package services;


import core.Itinerary;
import core.Ticket;
import resource.NotAllowedException;

import java.util.HashMap;
import java.util.Map;

public class BookingService {

    private static BookingService instance = null;

    private Map<String, Ticket> booked;

    private BookingService() {
        booked = new HashMap<String, Ticket>();
    }

    public static BookingService getInstance() {
        if(instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    public Ticket bookTicket(String authToken, String username, String itineraryId, String ccNumber) throws NotAllowedException {
        if(AuthenticateService.getInstance().validToken(authToken)) {
            Ticket t = new Ticket();
            t.setId(itineraryId);
            t.setFirstname("Foo Bar");
            t.setLastname(username);

            Itinerary it = ItineraryService.getInstance().getItinerary(authToken, itineraryId);
            t.setSrc(it.getSrc());
            t.setDest(it.getDest());
            t.setPrice(it.getTotalCost());
            t.setExtrainfo(it.toString());

            booked.put(itineraryId, t);

            return t;
        }
        else {
            throw new NotAllowedException();
        }
    }
}
