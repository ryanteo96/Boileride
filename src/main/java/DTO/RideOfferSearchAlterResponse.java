package DTO;

import java.util.LinkedList;

public class RideOfferSearchAlterResponse {
    private int result;
    private LinkedList<Trip> trips;

    public RideOfferSearchAlterResponse() {

    }

    public RideOfferSearchAlterResponse(int result) {
        this.result = result;
    }

    public RideOfferSearchAlterResponse(int result, LinkedList<Trip> trips) {
        this.result = result;
        this.trips = trips;
    }

    public int getResult() {
        return result;
    }

    public LinkedList<Trip> getTrips() {
        return trips;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setTrips(LinkedList<Trip> trips) {
        this.trips = trips;
    }
}
