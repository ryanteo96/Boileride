package DTO;

import java.util.Date;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO version of accepted request object
 *
 * @version October 3, 2018
 */

public class DtoAcceptedRequest {

    private int requestid;
    private int requestedby;
    private String requestedbyname;
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
    private String phone;
    private int accepteduserstatus;
    private int acceptedstatus;

    public DtoAcceptedRequest() {
    }

    public DtoAcceptedRequest(int requestid, int requestedby, String requestedbyname, String pickuplocation, String destination, Date datentime, int passengers, int luggage, boolean smoking, boolean foodndrink, boolean pets, boolean ac, int travelingtime, int price, int status, String phone, int accepteduserstatus, int acceptedstatus) {
        this.requestid = requestid;
        this.requestedby = requestedby;
        this.requestedbyname = requestedbyname;
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
        this.phone = phone;
        this.accepteduserstatus = accepteduserstatus;
        this.acceptedstatus = acceptedstatus;
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

    public String getRequestedbyname() {
        return requestedbyname;
    }

    public void setRequestedbyname(String requestedbyname) {
        this.requestedbyname = requestedbyname;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAccepteduserstatus() {
        return accepteduserstatus;
    }

    public void setAccepteduserstatus(int accepteduserstatus) {
        this.accepteduserstatus = accepteduserstatus;
    }

    public int getAcceptedstatus() {
        return acceptedstatus;
    }

    public void setAcceptedstatus(int acceptedstatus) {
        this.acceptedstatus = acceptedstatus;
    }

    @Override
    public String toString() {
        return "DtoAcceptedRequest{" +
                "requestid=" + requestid +
                ", requestedby=" + requestedby +
                ", requestedbyname='" + requestedbyname + '\'' +
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
                ", phone='" + phone + '\'' +
                ", accepteduserstatus=" + accepteduserstatus +
                ", acceptedstatus=" + acceptedstatus +
                '}';
    }
}

