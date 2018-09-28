import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;

import DTO.*;
import com.google.maps.errors.ApiException;
import org.joda.time.DateTime;
import org.joda.time.Interval;

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

    public RideRequest(){}

    public RideRequest(int requestedby, String pickuplocation, String destination, Date datentime,
                       int passengers, int luggage, boolean smoking, boolean foodndrink, boolean pets, boolean ac,
                       int travelingtime, int price) {
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
    }

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
        else if (user.getStatus() <= 0){
            return 2;
        }
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

    public int verifyPassengers(int passengers){
        if (passengers <= 0) {
            return 1;
        }
        return 0;
    }

    public int verifyLuggage(int luggage){
        if (luggage < 0) {
            return 1;
        }
        return 0;
    }

    public int isEnoughPoints(User user, int price){
        if (user != null && user.getPoints() < price) {
            return 1;
        }
        return 0;
    }

    public RideViewRequestResponse viewRideRequestfromDB(RideViewRequestRequest request){
        int result = 0;
        ArrayList<DtoRideRequest> requestlist = new ArrayList<DtoRideRequest>();
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        if (userResult > 0) result = userResult;
        else{
            requestlist = DatabaseCommunicator.selectRequestList(request.getUserid());
        }
        RideViewRequestResponse res = new RideViewRequestResponse(result, requestlist);
        return res;
    }

    public RideRequestResponse addRideRequestToDB(RideRequestRequest request){
        int result = 0;
        int requestid = -1;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int passengerResult = verifyPassengers(request.getPassengers());
        int luggageResult = verifyLuggage(request.getLuggage());
        int priceResult = isEnoughPoints(user, request.getPrice());
        if (userResult > 0) result = userResult;
        else if (desResult > 0) result = 4;
        else if (dateResult == 1) result = 5;
        else if (dateResult == 2) result = 7;
        else if (passengerResult > 0) result = 97;
        else if (luggageResult > 0) result = 97;
        else if (priceResult > 0) result = 6;
        else {
            RideRequest rideRequest = new RideRequest(request.getUserid(), request.getPickuplocation(), request.getDestination(),
                    request.getDatentime(), request.getPassengers(), request.getLuggage(), request.getSmoking(), request.getFoodndrink(),
                    request.getPets(), request.getAc(), request.getTravelingtime(), request.getPrice(), 0);
            requestid = DatabaseCommunicator.addRideRequest(rideRequest);
        }
        RideRequestResponse res = new RideRequestResponse(result, requestid);
        return res;
    }

    public RideCancelRequestResponse cancelRideRequestInDB(RideCancelRequestRequest request){
        int result = 0;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        if (userResult > 0) result = userResult;
        else {
            RideRequest rideRequest = DatabaseCommunicator.selectRideRequest(request.getRequestid());
            if (rideRequest == null) {
                result = 4;
            } else if (rideRequest.getRequestedby() != request.getUserid()) {
                result = 3;
            } else if (rideRequest.getStatus() == 2) {
                result = 5;
            } else {
//                User[] users = DatabaseCommunicator.selectUserAcceptedRequest(request.getRequestid());
                result = DatabaseCommunicator.cancelRideRequest(request.getRequestid());
//              String msg = "We are sorry to inform you that your accepted Ride Request from " + rideRequest.getPickuplocation() +
//                      " to " + rideRequest.getDestination() + " on " + rideRequest.getDatentime() +
//                      " is cancelled by the requested passengers.";
//                for (int i=0; i < users.length; i++){
//                    SendEmail.sendEmail(users[i].getEmail(), "Accepted Ride Request Cancelled", msg);
//                }

            }
        }
        RideCancelRequestResponse res = new RideCancelRequestResponse(result);
        return res;
    }

    public RideUpdateRequestResponse updateRideRequestInDB(RideUpdateRequestRequest request) {
        int result = 0;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int passengerResult = verifyPassengers(request.getPassengers());
        int luggageResult = verifyLuggage(request.getLuggage());
        int priceResult = isEnoughPoints(user, request.getPrice());
        if (userResult > 0) result = userResult;
        else if (desResult > 0) result = 7;
        else if (dateResult == 1) result = 8;
        else if (dateResult == 2) result = 10;
        else if (passengerResult > 0) result = 97;
        else if (luggageResult > 0) result = 97;
        else if (priceResult > 0) result = 9;
        else {
            RideRequest rideRequest = DatabaseCommunicator.selectRideRequest(request.getRequestid());
            if (rideRequest == null) {
                result = 4;
            } else if (rideRequest.getRequestedby() != request.getUserid()) {
                result = 3;
            } else if (rideRequest.getStatus() == 2) {
                result = 5;
            } else {
                RideRequest updatedRideRequest = new RideRequest(request.getUserid(), request.getPickuplocation(), request.getDestination(),
                        request.getDatentime(), request.getPassengers(), request.getLuggage(), request.getSmoking(), request.getFoodndrink(),
                        request.getPets(), request.getAc(), request.getTravelingtime(), request.getPrice());
                result = DatabaseCommunicator.updateRideRequest(request.getRequestid(), updatedRideRequest);
//                User[] users = DatabaseCommunicator.selectUserAcceptedRequest(request.getRequestid());
//                String msg = "Your accepted Ride Request from " + rideRequest.getPickuplocation() +
//                      " to " + rideRequest.getDestination() + " on " + rideRequest.getDatentime() +
//                      " is updated by the requested passengers. Please go to our website to check for details.";
//                for (int i=0; i < users.length; i++){
//                    SendEmail.sendEmail(users[i].getEmail(), "Accepted Ride Request Updated", msg);
//                }
            }
        }
        RideUpdateRequestResponse res = new RideUpdateRequestResponse(result);
        return res;
    }

    public DtoRideRequest toDtoRideRequest() {
        DtoRideRequest drr = new DtoRideRequest();
        drr.setRequestedby(requestedby);
        drr.setPickuplocation(pickuplocation);
        drr.setDestination(destination);
        drr.setDatentime(datentime);
        drr.setPassengers(passengers);
        drr.setLuggage(luggage);
        drr.setSmoking(smoking);
        drr.setFoodndrink(foodndrink);
        drr.setPets(pets);
        drr.setAc(ac);
        drr.setTravelingtime(travelingtime);
        drr.setPrice(price);
        drr.setStatus(status);
        return drr;
    }

    public static RideRequestSearchResponse search (RideRequestSearchRequest query) throws InterruptedException, ApiException, IOException {
        RideRequestSearchResponse response = new RideRequestSearchResponse();
        GoogleMapAPI gma = new GoogleMapAPI();
        // todo: add validation on query


        ArrayList<DtoRideRequest> rides = new ArrayList<>();
        ArrayList<RideRequest> rs = DatabaseCommunicator.rideRequestFromTo(gma.getCity(query.getPickuplocation()),
                gma.getCity(query.getDestination()), query.getDatentime(),
                query.getSeats(), query.getLuggage(),
                query.isSmoking(), query.isFoodndrink(),
                query.isPets(), query.isAc());
        if (rs == null) {
            response.setResult(9);
            return response;
        }
        for (RideRequest r : rs) {
            if (gma.estimate(r.getPickuplocation(), query.getPickuplocation()) <= query.getPickupproximity() &&
                    gma.estimate(r.getDestination(), query.getDestination()) <= query.getDestinationproximity() &&
                    query.getDatentime().getTime() + query.getDatentimerange() * 60 * 1000 >= r.getDatentime().getTime() &&
                    r.getPassengers() <= query.getSeats() &&
                    r.getLuggage() <= query.getLuggage() &&
                    r.isSmoking() == query.isSmoking() &&
                    r.isFoodndrink() == query.isFoodndrink() &&
                    r.isPets() == query.isPets() &&
                    r.isAc() == query.isAc()) {
                rides.add(r.toDtoRideRequest());
            }
        }
        if (rides.size() == 0) {
            response.setResult(9);
            return response;
        } else {
            response.setResult(0);
            response.setRides(rides);
            return response;
        }
    }

}
