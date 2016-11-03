package core;


import java.util.ArrayList;

public class Itinerary {
    private String id;
    private String src;
    private String dest;
    private Double totalCost;
    private ArrayList<Flight> route = new ArrayList<Flight>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id + " : " + src + "->" + dest + "(" + totalCost + ") \n" );

        for(Flight f: route) {
            sb.append(f.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public Double getTotalCost() {
        double result = 0d;
        for(Flight f: route) {
            result += f.getPrice();
        }

        return result;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<Flight> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Flight> route) {
        this.route = route;
    }
}
