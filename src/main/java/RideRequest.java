import java.util.Date;
import java.time.format.*;
import java.time.*;
import java.sql.*;

import DTO.*;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * RideRequest class handles data verification and actions related to posting, canceling and updating ride request
 *
 * @version September 14, 2018
 */

public class RideRequest {
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

    public RideRequest(int requestedby, String pickuplocation, String destination, Date datentime,
                       int passengers, int luggage, boolean smoking, boolean foodndrink, boolean pets, boolean ac,
                       int travelingtime, int price, int status) {
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

    @Override
    public String toString() {
        return "RideRequest{" +
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

    public int verifyUserid(User user){
        if (user == null){
            return 1;
        }
//        else if (user.getStatus() == 0){
//            return 2;
//        }
        return 0;
    }

    public int verifyDestination(String pickuplocation, String destination){
        if (pickuplocation.equalsIgnoreCase(destination)){
            return 1;
        }
        return 0;
    }

    public int verifyDatentime(Date datentime){
//        try {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.ENGLISH);
//            LocalDateTime date = LocalDateTime.parse(datentime, formatter);
//            Date dateFromLocalDT = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
        Date today = new Date();
        if (datentime.compareTo(today) <= 0 ){
            return 1;
        }
//        } catch (DateTimeParseException ex){
//            return 2;
//        }
        return 0;
    }

    public int isEnoughPoints(User user, int price){
        if (user.getPoints() < price) {
            return 1;
        }
        return 0;
    }

    // Integrate all data verification methods into one function
    public int verifyData(RideRequestRequest request){
        int result = 0;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int priceResult = isEnoughPoints(user, request.getPrice());
        if (userResult > 0) return userResult;
        else if (desResult > 0) return 4;
        else if (dateResult == 1) return 5;
        else if (dateResult == 2) return 7;
        else if (priceResult > 0) return 6;
        else return result;
    }

    public int addRideRequestToDB(RideRequestRequest request){
        RideRequest rideRequest = new RideRequest(request.getUserid(), request.getPickuplocation(), request.getDestination(),
                request.getDatentime(), request.getPassengers(), request.getLuggage(), request.getSmoking(), request.getFoodndrink(),
                request.getPets(), request.getAc(), request.getTravelingtime(), request.getPrice(), 0);
        int requestid = DatabaseCommunicator.addRideRequest(rideRequest);
        return requestid;
    }

    public int cancelRideRequestInDB(RideCancelRequestRequest request){
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        if (userResult > 0) return userResult;
        RideRequest rideRequest = DatabaseCommunicator.selectRideRequest(request.getRequestid());
        if (rideRequest == null){
            return 4;
        }
        else if (rideRequest.getRequestedby() != request.getUserid()){
            return 3;
        }
        else if (rideRequest.getStatus() == 2){
            return 5;
        }
        int ret = DatabaseCommunicator.cancelRideRequest(request.getRequestid());
        return ret;
    }

    public int updateRideRequestInDB(RideUpdateRequestRequest request) {
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int priceResult = isEnoughPoints(user, request.getPrice());
        if (userResult > 0) return userResult;
        else if (desResult > 0) return 7;
        else if (dateResult == 1) return 8;
        else if (dateResult == 2) return 10;
        else if (priceResult > 0) return 9;
        RideRequest rideRequest = DatabaseCommunicator.selectRideRequest(request.getRequestid());
        if (rideRequest == null){
            return 4;
        }
        else if (rideRequest.getRequestedby() != request.getUserid()){
            return 3;
        }
        else if (rideRequest.getStatus() == 2){
            return 5;
        }
        return 0;
    }

}
