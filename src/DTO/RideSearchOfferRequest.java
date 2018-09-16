package DTO;

import java.util.Date;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride search offer request
 *
 * @version September 16, 2018
 */

public class RideSearchOfferRequest {
    private String userid;
    private String pickuplocation;
    private String destination;
    private int pickupproximity;
    private int destinationproximity;
    private Date datentime;
    private int datentimerange;
    private int passengers;
    private int luggage;
    private boolean smoking;
    private boolean foodndrink;
    private boolean pets;
    private boolean ac;

    public RideSearchOfferRequest(String u, String p, String d, int pp, int dp, Date da, int dr,
                                    int pa, int l, boolean s, boolean f, boolean pe, boolean a){
        userid = u;
        pickuplocation = p;
        destination = d;
        pickupproximity = pp;
        destinationproximity = dp;
        datentime = da;
        datentimerange = dr;
        passengers = pa;
        luggage = l;
        smoking = s;
        foodndrink = f;
        pets = pe;
        ac = a;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setPickuplocation(String pickuplocation) {
        this.pickuplocation = pickuplocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPickupproximity(int pickupproximity) {
        this.pickupproximity = pickupproximity;
    }

    public void setDestinationproximity(int destinationproximity) {
        this.destinationproximity = destinationproximity;
    }

    public void setDatentime(Date datentime) {
        this.datentime = datentime;
    }

    public void setDatentimerange(int datentimerange) {
        this.datentimerange = datentimerange;
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

    public String getUserid() {
        return userid;
    }

    public String getPickuplocation() {
        return pickuplocation;
    }

    public String getDestination() {
        return destination;
    }

    public int getPickupproximity() {
        return pickupproximity;
    }

    public int getDestinationproximity() {
        return destinationproximity;
    }

    public Date getDatentime() {
        return datentime;
    }

    public int getDatentimerange() {
        return datentimerange;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getLuggage() {
        return luggage;
    }

    public boolean getSmoking() {
        return smoking;
    }

    public boolean getFoodndrink() {
        return foodndrink;
    }

    public boolean getPets() {
        return pets;
    }

    public boolean getAc() {
        return ac;
    }

    public String toString(){
        return "userid: " + userid + ", pickuplocation: " + pickuplocation + ", destination: " + destination
                + ", pickupproximity: " + pickupproximity + ", destinationproximity: " + destinationproximity
                + ", datentime: " + datentime + ", passengers: " + passengers + ", luggage: " + luggage
                + ", smoking: " + smoking + ", foodndrink: " + foodndrink + ", pets: " + pets + ", ac: " + ac;
    }
}
