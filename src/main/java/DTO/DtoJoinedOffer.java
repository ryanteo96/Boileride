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
    private String offeredby;
    private String pickuplocation;
    private String destination;
    private Date datentime;
    private boolean smoking;
    private boolean foodndrink;
    private boolean pets;
    private boolean ac;
    private int travelingtime;
    private int price;
    private int seatleft;
    private int luggageleft;
    private int joinedpassenger;
    private int joinedluggage;
    private String phone;
    private int pickupstatus;

    public DtoJoinedOffer() {
    }

    public DtoJoinedOffer(int offerid, String offeredby, String pickuplocation, String destination, Date datentime, boolean smoking, boolean foodndrink, boolean pets, boolean ac, int travelingtime, int price, int seatleft, int luggageleft, int joinedpassenger, int joinedluggage, String phone, int pickupstatus) {
        this.offerid = offerid;
        this.offeredby = offeredby;
        this.pickuplocation = pickuplocation;
        this.destination = destination;
        this.datentime = datentime;
        this.smoking = smoking;
        this.foodndrink = foodndrink;
        this.pets = pets;
        this.ac = ac;
        this.travelingtime = travelingtime;
        this.price = price;
        this.seatleft = seatleft;
        this.luggageleft = luggageleft;
        this.joinedpassenger = joinedpassenger;
        this.joinedluggage = joinedluggage;
        this.phone = phone;
        this.pickupstatus = pickupstatus;
    }

    public int getOfferid() {
        return offerid;
    }

    public void setOfferid(int offerid) {
        this.offerid = offerid;
    }

    public String getOfferedby() {
        return offeredby;
    }

    public void setOfferedby(String offeredby) {
        this.offeredby = offeredby;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPickupstatus() {
        return pickupstatus;
    }

    public void setPickupstatus(int pickupstatus) {
        this.pickupstatus = pickupstatus;
    }

    @Override
    public String toString() {
        return "DtoJoinedOffer{" +
                "offerid=" + offerid +
                ", offeredby='" + offeredby + '\'' +
                ", pickuplocation='" + pickuplocation + '\'' +
                ", destination='" + destination + '\'' +
                ", datentime=" + datentime +
                ", smoking=" + smoking +
                ", foodndrink=" + foodndrink +
                ", pets=" + pets +
                ", ac=" + ac +
                ", travelingtime=" + travelingtime +
                ", price=" + price +
                ", seatleft=" + seatleft +
                ", luggageleft=" + luggageleft +
                ", joinedpassenger=" + joinedpassenger +
                ", joinedluggage=" + joinedluggage +
                ", phone='" + phone + '\'' +
                ", pickupstatus=" + pickupstatus +
                '}';
    }
}