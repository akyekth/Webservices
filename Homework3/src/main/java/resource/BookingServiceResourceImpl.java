package resource;


import core.Ticket;
import services.BookingService;

public class BookingServiceResourceImpl implements BookingServiceResource {
    public Ticket bookTicket(String authToken, String username, String itineraryId, String ccNumber) throws NotAllowedException {
        return BookingService.getInstance().bookTicket(authToken,username, itineraryId, ccNumber);
    }
}
