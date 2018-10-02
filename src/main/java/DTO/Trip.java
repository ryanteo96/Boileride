package DTO;

import java.util.LinkedList;

public class Trip {
    private LinkedList<DtoRideOffer> rides;
    private int duration;

    public Trip() {

    }

    public Trip(LinkedList<DtoRideOffer> rides, int duration) {
        this.rides = rides;
        this.duration = duration;
    }

    public LinkedList<DtoRideOffer> getRides() {
        return rides;
    }

    public int getDuration() {
        return duration;
    }

    public void setRides(LinkedList<DtoRideOffer> rides) {
        this.rides = rides;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

