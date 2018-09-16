package DTO;

import java.util.Date;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride update request request
 *
 * @version September 16, 2018
 */

public class RideUpdateRequestRequest {
    private String userid;
    private String requestid;
    private String pickuplocation;
    private String destination;
    private Date datentime;
    private int passengers;
    private int luggage;
    private boolean smoking;
    private boolean foodndrink;
    private boolean pets;
    private boolean ac;
    private int travelingtime;
    private int price;

    public RideUpdateRequestRequest(String u, String r, String p, String d, Date da, int pa,
                              int l, boolean s, boolean f, boolean pe, boolean a,
                              int t, int pr){
        userid = u;
        requestid = r;
        pickuplocation = p;
        destination = d;
        datentime = da;
        passengers = pa;
        luggage = l;
        smoking = s;
        foodndrink = f;
        pets = pe;
        ac = a;
        travelingtime = t;
        price = pr;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setRequestid(String requestid) { this.requestid = requestid; }

    public void setPickuplocation(String pickuplocation) {
        this.pickuplocation = pickuplocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDatentime(Date datentime) {
        this.datentime = datentime;
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

    public void setTravelingtime(int travelingtime) {
        this.travelingtime = travelingtime;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUserid() {
        return userid;
    }

    public String getRequestid() { return requestid; }

    public String getPickuplocation() {
        return pickuplocation;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDatentime() {
        return datentime;
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

    public int getTravelingtime() {
        return travelingtime;
    }

    public int getPrice() {
        return price;
    }

    public String toString(){
        return "userid: " + userid + ", requestid: " + requestid + ", pickuplocation: " + pickuplocation
                + ", destination: " + destination + ", datentime: " + datentime + ", passengers: "
                + passengers + ", luggage: " + luggage + ", smoking: " + smoking + ", foodndrink: "
                + foodndrink + ", pets: " + pets + ", ac: " + ac + ", travelingtime" + travelingtime
                + ", price: " + price;
    }
}
