package RailwaysReservation;

import java.util.HashMap;

public class Ticket {
    private String pnrNumber;
    private String ticketDate;
    private String from;
    private String to;
    private int routeLength;
    private HashMap<String, String> passengerList = new HashMap<>();

    public Ticket(String ticketDate, String from, String to, HashMap<String, String> passengerList) {
        this.ticketDate = ticketDate;
        this.from = from;
        this.to = to;
        this.passengerList = passengerList;
    }

    public String getPnrNumber() {
        return pnrNumber;
    }

    public void setPnrNumber(String pnrNumber) {
        this.pnrNumber = pnrNumber;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(String ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(int routeLength) {
        this.routeLength = routeLength;
    }

    public HashMap<String, String> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(HashMap<String, String> passengerList) {
        this.passengerList = passengerList;
    }
}
