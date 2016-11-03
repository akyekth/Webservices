package services;


import core.Flight;
import core.Itinerary;
import data.FlightData;
import resource.NotAllowedException;

import java.util.*;

public class ItineraryService {
    private static ItineraryService ourInstance = new ItineraryService();

    private FlightData data;

    private Map<String, Itinerary> itineraryMap = new HashMap<String, Itinerary>();

    public static ItineraryService getInstance() {
        return ourInstance;
    }

    private ItineraryService() {
        data = new FlightData();
    }

    public Itinerary getItinerary(String authToken, String id) throws NotAllowedException {
        if(AuthenticateService.getInstance().validToken(authToken)) {
            return itineraryMap.get(id);
        }
        else {
            throw new NotAllowedException();
        }
    }

    public List<Itinerary> findItineraries(String authToken, String source, String destination) throws NotAllowedException {
        if(AuthenticateService.getInstance().validToken(authToken)) {
            List<Itinerary> itineraries = new ArrayList<Itinerary>();
            directFlights(itineraries, source, destination);
            transitFlights(itineraries, source, destination);

            return itineraries;
        } else {
            throw new NotAllowedException();
        }
    }

    private void directFlights(List<Itinerary> itineraries, String source, String destination) {
        for(Flight f: data.getConnections(source)) {
            if (f.getDest().equals(destination)) {
                Itinerary i = new Itinerary();
                String id  = UUID.randomUUID().toString();
                i.setId(id);
                i.setSrc(source);
                i.setDest(destination);
                i.setTotalCost(f.getPrice());
                i.getRoute().add(f);

                itineraries.add(i);
                itineraryMap.put(id, i);

                return;
            }
        }
    }

    private void transitFlights(List<Itinerary> itineraries, String source, String destination) {
        for(Flight srcFlight: data.getConnections(source)) {
            for(Flight destFlight: data.getConnections(srcFlight.getDest())) {
                if(destFlight.getDest().equals(destination))  {
                    Itinerary i = new Itinerary();
                    String id  = UUID.randomUUID().toString();
                    i.setId(id);
                    i.setSrc(source);
                    i.setDest(destination);
                    i.setTotalCost(srcFlight.getPrice() + destFlight.getPrice());
                    i.getRoute().add(srcFlight);
                    i.getRoute().add(destFlight);

                    itineraries.add(i);
                    itineraryMap.put(id, i);

                    return;
                }
            }
        }
    }
}
