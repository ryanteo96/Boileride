package DTO;

import java.util.Date;

public class DtoRideRequest {
    private int requestid;
    private int requestedby;
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
    private int status;
    private int pickupstatus;

    public DtoRideRequest() {

    }

    public DtoRideRequest(int requestid, int requestedby, String pickuplocation, String destination, Date datentime,
                          int passengers, int luggage, boolean smoking, boolean foodndrink, boolean pets, boolean ac,
                          int travelingtime, int price, int status) {
        this.requestid = requestid;
        this.requestedby = requestedby;
        this.pickuplocation = pickuplocation;
        this.destination = destination;
        this.datentime = datentime;
        this.passengers = passengers;
        this.luggage = luggage;
        this.smoking = smoking;
        this.foodndrink = foodndrink;
        this.pets = pets;
        this.ac = ac;
        this.travelingtime = travelingtime;
        this.price = price;
        this.status = status;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public int getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(int requestedby) {
        this.requestedby = requestedby;
    }

    public String getPickuplocation() {
        return pickuplocation;
    }

    public void setPickuplocation(String pickuplocation) {
        this.pickuplocation = pickuplocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDatentime() {
        return datentime;
    }

    public void setDatentime(Date datentime) {
        this.datentime = datentime;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean isFoodndrink() {
        return foodndrink;
    }

    public void setFoodndrink(boolean foodndrink) {
        this.foodndrink = foodndrink;
    }

    public boolean isPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public int getTravelingtime() {
        return travelingtime;
    }

    public void setTravelingtime(int travelingtime) {
        this.travelingtime = travelingtime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String toString() {
        return "RideRequest{" +
                "requestid=" + requestid +
                ", requestedby='" + requestedby + '\'' +
                ", pickuplocation='" + pickuplocation + '\'' +
                ", destination='" + destination + '\'' +
                ", datentime=" + datentime +
                ", passengers=" + passengers +
                ", luggage=" + luggage +
                ", smoking=" + smoking +
                ", foodndrink=" + foodndrink +
                ", pets=" + pets +
                ", ac=" + ac +
                ", travelingtime=" + travelingtime +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
