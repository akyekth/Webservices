package resource;


import core.Itinerary;
import services.ItineraryService;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface="resource.ItineraryServiceResource")
public class ItineraryServiceResourceImpl implements ItineraryServiceResource {

    public List<Itinerary> findItineraries(String authToken, String source, String destination) throws NotAllowedException {
        return ItineraryService.getInstance().findItineraries(authToken, source, destination);
    }

    public Itinerary getItinerary(String authToken, String itineraryId) throws NotAllowedException {
        return ItineraryService.getInstance().getItinerary(authToken, itineraryId);
    }

}
