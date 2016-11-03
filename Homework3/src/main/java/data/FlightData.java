package data;


import core.Flight;

import java.util.*;

public class FlightData {
    private Set<String> nodes = new TreeSet<String>();
    private HashMap<String, ArrayList<Flight>> flights = new HashMap<String, ArrayList<Flight>>();

    {
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");
        nodes.add("D");
        nodes.add("E");
        nodes.add("F");

        ArrayList<Flight> fromA = new ArrayList<Flight>();
        fromA.add(new Flight("ARL1", "A", "B", new Date(), new Date(), 2000d));
        fromA.add(new Flight("ARL1", "A", "C", new Date(), new Date(), 1500d));
        fromA.add(new Flight("ARL1", "A", "D", new Date(), new Date(), 1000d));
        flights.put("A", fromA);

        ArrayList<Flight> fromB = new ArrayList<Flight>();
        fromB.add(new Flight("ARL1", "B", "D", new Date(), new Date(), 2000d));
        fromB.add(new Flight("ARL1", "B", "E", new Date(), new Date(), 1500d));
        flights.put("B", fromB);

        ArrayList<Flight> fromC = new ArrayList<Flight>();
        fromC.add(new Flight("ARL1", "C", "A", new Date(), new Date(), 2000d));
        flights.put("C", fromC);

        ArrayList<Flight> fromD = new ArrayList<Flight>();
        fromD.add(new Flight("ARL1", "D", "C", new Date(), new Date(), 2000d));
        flights.put("D", fromD);

        ArrayList<Flight> fromE = new ArrayList<Flight>();
        fromE.add(new Flight("ARL1", "E", "B", new Date(), new Date(), 2000d));
        fromE.add(new Flight("ARL1", "E", "F", new Date(), new Date(), 2000d));
        flights.put("E", fromE);

        ArrayList<Flight> fromF = new ArrayList<Flight>();
        fromF.add(new Flight("ARL1", "F", "E", new Date(), new Date(), 2000d));
        flights.put("F", fromF);
    }

    public ArrayList<Flight> getConnections(String src) {
        return flights.get(src);
    }
}
