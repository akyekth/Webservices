package resource;


import core.Ticket;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BookingServiceResource {
    @WebMethod
    public Ticket bookTicket(String authToken,
                             String username,
                             String itineraryId,
                             String ccNumber) throws NotAllowedException;
}
