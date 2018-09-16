package DTO;

import java.util.Date;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO for ride offer request
 *
 * @version September 16, 2018
 */

public class RideOfferRequest {
    private String userid;
    private String pickuplocation;
    private String destination;
    private Date datentime;
    private int seats;
    private int luggage;
    private boolean smoking;
    private boolean foodndrink;
    private boolean pets;
    private boolean ac;
    private int travelingtime;
    private int price;

    public RideOfferRequest(String u, String p, String d, Date da, int se,
                              int l, boolean s, boolean f, boolean pe, boolean a,
                              int t, int pr){
        userid = u;
        pickuplocation = p;
        destination = d;
        datentime = da;
        seats = se;
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

    public void setPickuplocation(String pickuplocation) {
        this.pickuplocation = pickuplocation;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDatentime(Date datentime) {
        this.datentime = datentime;
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

    public void setTravelingtime(int travelingtime) {
        this.travelingtime = travelingtime;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public Date getDatentime() {
        return datentime;
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

    public int getTravelingtime() {
        return travelingtime;
    }

    public int getPrice() {
        return price;
    }

    public String toString(){
        return "userid: " + userid + ", pickuplocation: " + pickuplocation
                + ", destination: " + destination + ", datentime: " + datentime
                + ", seats: " + seats + ", luggage: " + luggage
                + ", smoking: " + smoking + ", foodndrink: " + foodndrink
                + ", pets: " + pets + ", ac: " + ac + ", travelingtime" + travelingtime
                + ", price: " + price;
    }
}
