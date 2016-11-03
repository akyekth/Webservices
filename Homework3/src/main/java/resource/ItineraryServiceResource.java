package resource;


import core.Itinerary;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;


@WebService
public interface ItineraryServiceResource {
    @WebMethod
    public List<Itinerary> findItineraries(String authToken,
                                           String source,
                                           String destination) throws NotAllowedException;
    @WebMethod
    public Itinerary getItinerary(String authToken,
                                  String itineraryId) throws NotAllowedException;
}

