package core;


import java.util.Date;

public class Flight {
    private String airline;
    private String src;
    private String dest;
    private Date departure;
    private Date arrival;
    private Double price;

    public Flight() {}

    public Flight(String airline, String src, String dest, Date departure, Date arrival, Double price) {
        this.airline = airline;
        this.src = src;
        this.dest = dest;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
    }

    @Override
    public String toString() {
        return "[" + airline + "] " + src + "->" + dest + "@" + price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
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

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
