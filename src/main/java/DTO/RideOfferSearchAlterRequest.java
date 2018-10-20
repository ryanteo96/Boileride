package DTO;

import java.util.Date;
import java.util.LinkedList;

public class RideOfferSearchAlterRequest {
    private int userid;
    private int startofferid;
    private int endofferid;
    private Trip trip;

    private String originalpickuplocation;
    private String originaldestination;
    private Date originaldatentime;
    private double pickupproximity;
    private double destinationproximity;
    private int datentimerange;
    private int numrides;
    private int passengers;
    private int luggage;
    private boolean smoking;
    private boolean foodndrink;
    private boolean pets;
    private boolean ac;

//    public RideOfferSearchAlterRequest() {
//
//    }

    public RideOfferSearchAlterRequest(int userid,
                                       int startofferid,
                                       int endofferid,
                                       Trip trip,
                                       String pickuplocation,
                                       String destination,
                                       Date datentime,
                                       double pickupproximity,
                                       double destinationproximity,
                                       int datentimerange,
                                       int numrides,
                                       int passengers,
                                       int luggage,
                                       boolean smoking,
                                       boolean foodndrink,
                                       boolean pets,
                                       boolean ac) {
        this.userid = userid;
        this.startofferid = startofferid;
        this.endofferid = endofferid;
        this.trip = trip;
        this.originalpickuplocation = pickuplocation;
        this.originaldestination = destination;
        this.originaldatentime = datentime;
        this.pickupproximity = pickupproximity;
        this.destinationproximity = destinationproximity;
        this.datentimerange = datentimerange;
        this.numrides = numrides;
        this.passengers = passengers;
        this.luggage = luggage;
        this.smoking = smoking;
        this.foodndrink = foodndrink;
        this.pets = pets;
        this.ac = ac;
    }

    public int getUserid() {
        return userid;
    }

    public int getStartofferid() {
        return startofferid;
    }

    public int getEndofferid() {
        return endofferid;
    }

    public Trip getTrip() {
        return trip;
    }

    public String getOriginalpickuplocation() {
        return originalpickuplocation;
    }

    public String getOriginaldestination() {
        return originaldestination;
    }

    public Date getOriginaldatentime() {
        return originaldatentime;
    }

    public double getPickupproximity() {
        return pickupproximity;
    }

    public double getDestinationproximity() {
        return destinationproximity;
    }

    public int getDatentimerange() {
        return datentimerange;
    }

    public int getNumrides() {
        return numrides;
    }

    public int getPassengers() {
        return passengers;
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

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setStartofferid(int startofferid) {
        this.startofferid = startofferid;
    }

    public void setEndofferid(int endofferid) {
        this.endofferid = endofferid;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }


    public void setOriginalpickuplocation(String originalpickuplocation) {
        this.originalpickuplocation = originalpickuplocation;
    }

    public void setOriginaldestination(String originaldestination) {
        this.originaldestination = originaldestination;
    }

    public void setOriginaldatentime(Date originaldatentime) {
        this.originaldatentime = originaldatentime;
    }

    public void setPickupproximity(double pickupproximity) {
        this.pickupproximity = pickupproximity;
    }

    public void setDestinationproximity(double destinationproximity) {
        this.destinationproximity = destinationproximity;
    }

    public void setDatentimerange(int datentimerange) {
        this.datentimerange = datentimerange;
    }

    public void setNumrides(int numrides) {
        this.numrides = numrides;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
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
}
