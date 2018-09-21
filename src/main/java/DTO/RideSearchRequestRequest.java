package DTO;

import java.util.Date;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride search request request
 *
 * @version September 16, 2018
 */

public class RideSearchRequestRequest {
    private int userid;
    private String pickuplocation;
    private String destination;
    private int pickupproximity;
    private int destinationproximity;
    private Date datentime;
    private Date datentimerange;
    private int seats;
    private int luggage;
    private boolean smoking;
    private boolean foodndrink;
    private boolean pets;
    private boolean ac;

    public RideSearchRequestRequest(int u, String p, String d, int pp, int dp, Date da, Date dr,
                              int se, int l, boolean s, boolean f, boolean pe, boolean a){
        userid = u;
        pickuplocation = p;
        destination = d;
        pickupproximity = pp;
        destinationproximity = dp;
        datentime = da;
        datentimerange = dr;
        seats = se;
        luggage = l;
        smoking = s;
        foodndrink = f;
        pets = pe;
        ac = a;
    }

    public void setUserid(int userid) {
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

    public void setDatentimerange(Date datentimerange) {
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

    public int getUserid() {
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

    public Date getDatentimerange() {
        return datentimerange;
    }

    public int getSeats() {
        return seats;
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
                + ", datentime: " + datentime + ", seats: " + seats + ", luggage: " + luggage
                + ", smoking: " + smoking + ", foodndrink: " + foodndrink + ", pets: " + pets + ", ac: " + ac;
    }
}
