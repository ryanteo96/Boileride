package DTO;

import java.util.Date;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * DTO version of joined offer object
 *
 * @version October 3, 2018
 */

public class DtoJoinedOffer {

    private int offerid;
    private int offeredby;
    private String offeredbyname;
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
    private int seatleft;
    private int luggageleft;
    private int price;
    private int status;
    private String phone;
    private int joinedpassenger;
    private int joinedluggage;
    private int joineduserstatus;
    private int joinedstatus;
    private int triporder;

    public DtoJoinedOffer() {
    }

    public DtoJoinedOffer(int offerid, int offeredby, String offeredbyname, String pickuplocation, String destination, Date datentime, int seats, int luggage, boolean smoking, boolean foodndrink, boolean pets, boolean ac, int travelingtime, int seatleft, int luggageleft, int price, int status, String phone, int joinedpassenger, int joinedluggage, int joineduserstatus, int joinedstatus, int triporder) {
        this.offerid = offerid;
        this.offeredby = offeredby;
        this.offeredbyname = offeredbyname;
        this.pickuplocation = pickuplocation;
        this.destination = destination;
        this.datentime = datentime;
        this.seats = seats;
        this.luggage = luggage;
        this.smoking = smoking;
        this.foodndrink = foodndrink;
        this.pets = pets;
        this.ac = ac;
        this.travelingtime = travelingtime;
        this.seatleft = seatleft;
        this.luggageleft = luggageleft;
        this.price = price;
        this.status = status;
        this.phone = phone;
        this.joinedpassenger = joinedpassenger;
        this.joinedluggage = joinedluggage;
        this.joineduserstatus = joineduserstatus;
        this.joinedstatus = joinedstatus;
        this.triporder = triporder;
    }

    public int getOfferid() {
        return offerid;
    }

    public void setOfferid(int offerid) {
        this.offerid = offerid;
    }

    public int getOfferedby() {
        return offeredby;
    }

    public void setOfferedby(int offeredby) {
        this.offeredby = offeredby;
    }

    public String getOfferedbyname() {
        return offeredbyname;
    }

    public void setOfferedbyname(String offeredbyname) {
        this.offeredbyname = offeredbyname;
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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
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

    public int getSeatleft() {
        return seatleft;
    }

    public void setSeatleft(int seatleft) {
        this.seatleft = seatleft;
    }

    public int getLuggageleft() {
        return luggageleft;
    }

    public void setLuggageleft(int luggageleft) {
        this.luggageleft = luggageleft;
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

    public int getJoinedpassenger() {
        return joinedpassenger;
    }

    public void setJoinedpassenger(int joinedpassenger) {
        this.joinedpassenger = joinedpassenger;
    }

    public int getJoinedluggage() {
        return joinedluggage;
    }

    public void setJoinedluggage(int joinedluggage) {
        this.joinedluggage = joinedluggage;
    }

    public int getJoineduserstatus() {
        return joineduserstatus;
    }

    public void setJoineduserstatus(int joineduserstatus) {
        this.joineduserstatus = joineduserstatus;
    }

    public int getJoinedstatus() {
        return joinedstatus;
    }

    public void setJoinedstatus(int joinedstatus) {
        this.joinedstatus = joinedstatus;
    }

    public int getTriporder() {
        return triporder;
    }

    public void setTriporder(int triporder) {
        this.triporder = triporder;
    }

    @Override
    public String toString() {
        return "DtoJoinedOffer{" +
                "offerid=" + offerid +
                ", offeredby=" + offeredby +
                ", offeredbyname='" + offeredbyname + '\'' +
                ", pickuplocation='" + pickuplocation + '\'' +
                ", destination='" + destination + '\'' +
                ", datentime=" + datentime +
                ", seats=" + seats +
                ", luggage=" + luggage +
                ", smoking=" + smoking +
                ", foodndrink=" + foodndrink +
                ", pets=" + pets +
                ", ac=" + ac +
                ", travelingtime=" + travelingtime +
                ", seatleft=" + seatleft +
                ", luggageleft=" + luggageleft +
                ", price=" + price +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", joinedpassenger=" + joinedpassenger +
                ", joinedluggage=" + joinedluggage +
                ", joineduserstatus=" + joineduserstatus +
                ", joinedstatus=" + joinedstatus +
                ", triporder=" + triporder +
                '}';
    }
}