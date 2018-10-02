package DTO;

import org.joda.time.DateTime;

import java.util.Date;

public class RideRequestSearchRequest {
    private String userid;
    private String pickuplocation;
    private double pickupproximity;
    private String destination;
    private double destinationproximity;
    private Date datentime;
    private int datentimerange;
    private int seats;
    private int luggage;
    private boolean smoking;
    private boolean foodndrink;
    private boolean pets;
    private boolean ac;

    public RideRequestSearchRequest() {

    }

    public RideRequestSearchRequest(String userid,
                                    String pickuplocation,
                                    double pickupproximity,
                                    String destination,
                                    double destinationproximity,
                                    Date datentime,
                                    int datentimerange,
                                    int seats,
                                    int luggage,
                                    boolean smoking,
                                    boolean foodndrink,
                                    boolean pets,
                                    boolean ac) {
        this.userid = userid;
        this.pickuplocation = pickuplocation;
        this.pickupproximity = pickupproximity;
        this.destination = destination;
        this.destinationproximity = destinationproximity;
        this.datentime = datentime;
        this.datentimerange = datentimerange;
        this.seats = seats;
        this.luggage = luggage;
        this.smoking = smoking;
        this.foodndrink = foodndrink;
        this.pets = pets;
        this.ac = ac;
    }

    public String getUserid() {
        return userid;
    }

    public String getPickuplocation() {
        return pickuplocation;
    }

    public double getPickupproximity() {
        return pickupproximity;
    }

    public String getDestination() {
        return destination;
    }

    public double getDestinationproximity() {
        return destinationproximity;
    }

    public Date getDatentime() {
        return datentime;
    }

    public int getDatentimerange() {
        return datentimerange;
    }

    public int getSeats() {
        return seats;
    }

    public int getLuggage() {
        return luggage;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public boolean isFoodndrink() {
        return foodndrink;
    }

    public boolean isPets() {
        return pets;
    }

    public boolean isAc() {
        return ac;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPickuplocation(String pickuplocation) {
        this.pickuplocation = pickuplocation;
    }

    public void setPickupproximity(double pickupproximity) {
        this.pickupproximity = pickupproximity;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDestinationproximity(double destinationproximity) {
        this.destinationproximity = destinationproximity;
    }

    public void setDatentime(Date datentime) {
        this.datentime = datentime;
    }

    public void setDatentimerange(int datentimerange) {
        this.datentimerange = datentimerange;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public void setFoodndrink(boolean foodndrink) {
        this.foodndrink = foodndrink;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public String toString() {
        return "Origin: " + pickuplocation +
                ", Origin Proximity: " + pickupproximity +
                ", Destination: " + destination +
                ", Destination Proximity: " + destinationproximity +
                ", Departure: " + datentime.toString() +
                ", Departure Proximity: " + datentimerange +
                ", Number of Passengers: " + seats +
                ", Number of Luggage: " + luggage +
                ", Allow Smoking: " + smoking +
                ", Allow Food and Drink: " + foodndrink +
                ", Allow Pets: " + pets +
                ", Has AC: " + ac;
    }
}
