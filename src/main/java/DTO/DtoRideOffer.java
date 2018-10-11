package DTO;

import java.util.ArrayList;
import java.util.Date;

public class DtoRideOffer {

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
    private int price;
    private int seatleft;
    private int luggageleft;
    private int status;
    private ArrayList<Integer> joinedby;
    private ArrayList<String> joinedbyname;
    private ArrayList<String> phone;
    private ArrayList<Integer> offeruserstatus;

    public DtoRideOffer() {

    }

    public DtoRideOffer(int offerid, int offeredby, String offeredbyname, String pickuplocation, String destination, Date datentime, int seats, int luggage, boolean smoking, boolean foodndrink, boolean pets, boolean ac, int travelingtime, int price, int seatleft, int luggageleft, int status, ArrayList<Integer> joinedby, ArrayList<String> joinedbyname, ArrayList<String> phone, ArrayList<Integer> offeruserstatus) {
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
        this.price = price;
        this.seatleft = seatleft;
        this.luggageleft = luggageleft;
        this.status = status;
        this.joinedby = joinedby;
        this.joinedbyname = joinedbyname;
        this.phone = phone;
        this.offeruserstatus = offeruserstatus;
    }

    public DtoRideOffer(int offerid, int offeredby, Date datentime, int price) {
        this.offerid = offerid;
        this.offeredby = offeredby;
        this.datentime = datentime;
        this.price = price;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Integer> getJoinedby() {
        return joinedby;
    }

    public void setJoinedby(ArrayList<Integer> joinedby) {
        this.joinedby = joinedby;
    }

    public ArrayList<String> getJoinedbyname() {
        return joinedbyname;
    }

    public void setJoinedbyname(ArrayList<String> joinedbyname) {
        this.joinedbyname = joinedbyname;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }

    public ArrayList<Integer> getOfferuserstatus() {
        return offeruserstatus;
    }

    public void setOfferuserstatus(ArrayList<Integer> offeruserstatus) {
        this.offeruserstatus = offeruserstatus;
    }

    @Override
    public String toString() {
        return "DtoRideOffer{" +
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
                ", price=" + price +
                ", seatleft=" + seatleft +
                ", luggageleft=" + luggageleft +
                ", status=" + status +
                ", joinedby=" + joinedby.toString() +
                ", joinedbyname=" + joinedbyname.toString() +
                ", phone=" + phone.toString() +
                ", offeruserstatus=" + offeruserstatus.toString() +
                '}';
    }
}
