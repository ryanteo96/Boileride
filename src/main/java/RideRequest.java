import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Random;

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

    public static DatabaseCommunicator SQL = new DatabaseCommunicator();


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

    public RideRequest(int requestid, int requestedby, String pickuplocation, String destination, Date datentime,
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
        Date today = new Date();
        if (datentime.compareTo(today) <= 0 ){
            return 1;
        }
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

    public int verifyTimePrice(String location, String destination, int price, int time){
        GoogleMapAPI mapAPI = new GoogleMapAPI();
        try {
            int[] distTime = mapAPI.getDistTime(location, destination);
            if (distTime == null){
                return 1;
            }
            else if (Math.abs(distTime[0]-price) > 1){
                return 2;
            }
            else if (Math.abs(distTime[1]-time) > 5*60){
                return 3;
            }
        } catch (InterruptedException e) {
            return 1;
        } catch (ApiException e) {
            return 1;
        } catch (IOException e) {
            return 1;
        }
        return 0;
    }

    public RideAcceptRequestResponse acceptRideRequest(RideAcceptRequestRequest req)
    {
        PointCalculator.chargeFailConfirmationFee(req.getUserid());
        RideAcceptRequestResponse response = new RideAcceptRequestResponse(-1);
        User user = SQL.selectUser(req.getUserid());
        if(user == null)
        {
            response.setResult(1);
        }
        else
        {
            if(user.getStatus() == 1)
            {
                RideRequest rideRequest = DatabaseCommunicator.selectRideRequest(req.getRequestid());
                if(rideRequest != null)
                {

//                    if(DatabaseCommunicator.rideRequestBy(req.getRequestid()) != req.getUserid())
                    if(rideRequest.getRequestedby() != req.getUserid())
                    {
                        if(rideRequest.getStatus() == 0 )
                        {

                            if(user.getPoints() >= rideRequest.getPrice() )
                            {
                                
                                if(DatabaseCommunicator.updateRequestStatus(req.getRequestid(), 1) == 0)
                                {
                                    int addRequestResult = DatabaseCommunicator.insertNewAcceptedRequest(user.getUserid(), req.getRequestid(), 0, 0, 0, 0, 0);
                                    if(addRequestResult == 0)
                                    {
                                        PointCalculator.reservePoints(req.getUserid(), rideRequest.getPrice());
                                        response.setResult(0);

                                        User owner = DatabaseCommunicator.selectUser(rideRequest.getRequestedby());
                                        SendEmail sender = new SendEmail();
                                        sender.sendEmail(owner.getEmail(), "Your request has been accepted", "Your request is accepted by" + user.getEmail());
                                    }
                                    else if(addRequestResult == 1)
                                    {
                                        System.out.println("There exists a record with the same userid and offerid in \"ACCEPTEDRIDEREQUEST\"");
                                    }
                                    else if(addRequestResult == 99)
                                    {
                                        System.out.println("Failed to insert new joined offer to the table \"ACCEPTEDRIDEREQUEST\"");
                                    }
                                    else
                                    {
                                        System.out.println("Something went wrong while inserting new accepted request");
                                    }

                                }
                                else
                                {
                                    System.out.println("Failed to accept ride request in DB");
                                }
                            }
                            else
                            {
                                   response.setResult(6);
                            }
                        }
                        else
                        {
                            response.setResult(5);
                        }

                    }
                    else
                    {
                        response.setResult(4);
                    }

                }
                else
                {
                    response.setResult(3);
                }
            }
            else
            {
                response.setResult(2);
            }
        }


        return  response;
    }
    public RideCancelAcceptedRequestResponse cancelAcceptedRequest(RideCancelAcceptedRequestRequest req)
    {
        RideCancelAcceptedRequestResponse response = new RideCancelAcceptedRequestResponse(-1);
        User user = SQL.selectUser(req.getUserid());
        if(user != null)
        {
            if(user.getStatus() == 1)
            {
                RideRequest rideRequest = DatabaseCommunicator.selectRideRequest(req.getRequestID());
                if(rideRequest != null)
                {
                    int removeResult = DatabaseCommunicator.removeAcceptedRequest(user.getUserid(), req.getRequestID());
                    if(removeResult == 0)
                    {
                        int result = DatabaseCommunicator.cancelRideRequest(req.getRequestID());
                        if(result == 0)
                        {
                            PointCalculator.chargeCancellationFee(req.getUserid(), rideRequest.getDatentime(), rideRequest.getPrice(), "");
                            response.setResult(0);
                            SendEmail sender = new SendEmail();
                            sender.sendEmail(user.getEmail(),"You have cancelled your request.", "Your request has been cancelled.");
                        }
                        else
                        {
                            System.out.println("Failed to cancel ride request in RIDEREQUEST table");
                        }

                    }
                    else
                    {
                        response.setResult(3);
                    }

                }
                else
                {
                    response.setResult(4);
                }
            }
            else
            {
                response.setResult(2);
            }
        }
        else
        {
            response.setResult(1);
        }
        return  response;
    }
    public RideViewRequestResponse viewRideRequestfromDB(RideViewRequestRequest request){
        PointCalculator.chargeFailConfirmationFee(request.getUserid());
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
        PointCalculator.chargeFailConfirmationFee(request.getUserid());
        int result = 0;
        int requestid = -1;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int passengerResult = verifyPassengers(request.getPassengers());
        int luggageResult = verifyLuggage(request.getLuggage());
        int priceResult = isEnoughPoints(user, request.getPrice());
        int distTimeResult = verifyTimePrice(request.getPickuplocation(), request.getDestination(), request.getPrice()/request.getPassengers(), request.getTravelingtime());
        if (userResult > 0) result = userResult;
        else if (desResult > 0) result = 4;
        else if (dateResult > 0) result = 5;
        else if (passengerResult > 0) result = 97;
        else if (luggageResult > 0) result = 97;
        else if (priceResult > 0) result = 6;
        else if (distTimeResult == 1) result = 3;
        else if (distTimeResult == 2) result = 7;
        else if (distTimeResult == 3) result = 8;
        else {
            RideRequest rideRequest = new RideRequest(request.getUserid(), request.getPickuplocation(), request.getDestination(),
                    request.getDatentime(), request.getPassengers(), request.getLuggage(), request.getSmoking(), request.getFoodndrink(),
                    request.getPets(), request.getAc(), request.getTravelingtime(), request.getPrice(), 0);
            requestid = DatabaseCommunicator.addRideRequest(rideRequest);
            if (requestid > -1){
                result = PointCalculator.reservePoints(request.getUserid(),request.getPrice());
            }
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
            } else if (rideRequest.getRequestedby() != request.getUserid() || rideRequest.getStatus() == 3) {
                result = 3;
            } else if (rideRequest.getStatus() == 2) {
                result = 5;
            } else {
                ArrayList<User> users = DatabaseCommunicator.selectUserAcceptedRequest(request.getRequestid());
                result = DatabaseCommunicator.cancelRideRequest(request.getRequestid());
                if (result == 0) {
                    String msg = "We are sorry to inform you that your accepted Ride Request from " + rideRequest.getPickuplocation() +
                            " to " + rideRequest.getDestination() + " on " + rideRequest.getDatentime() +
                            " is cancelled by the requested passengers.";
                    for (User u : users) {
                        PointCalculator.reservePoints(u.getUserid(), rideRequest.getPrice()*-1);
                        SendEmail.sendEmail(u.getEmail(), "Accepted Ride Request Cancelled", msg);
                    }
                    PointCalculator.chargeCancellationFee(request.getUserid(),rideRequest.getDatentime(),rideRequest.getPrice(), "Cancel ride request");

                }
            }
        }
        RideCancelRequestResponse res = new RideCancelRequestResponse(result);
        return res;
    }

    public RideUpdateRequestResponse updateRideRequestInDB(RideUpdateRequestRequest request) {
        PointCalculator.chargeFailConfirmationFee(request.getUserid());
        int result = 0;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int passengerResult = verifyPassengers(request.getPassengers());
        int luggageResult = verifyLuggage(request.getLuggage());
        int priceResult = isEnoughPoints(user, request.getPrice());
        int distTimeResult = verifyTimePrice(request.getPickuplocation(), request.getDestination(), request.getPrice()/request.getPassengers(), request.getTravelingtime());
        if (userResult > 0) result = userResult;
        else if (desResult > 0) result = 7;
        else if (dateResult > 0) result = 8;
        else if (passengerResult > 0) result = 97;
        else if (luggageResult > 0) result = 97;
        else if (priceResult > 0) result = 9;
        else if (distTimeResult == 1) result = 6;
        else if (distTimeResult == 2) result = 10;
        else if (distTimeResult == 3) result = 11;
        else {
            RideRequest rideRequest = DatabaseCommunicator.selectRideRequest(request.getRequestid());
            if (rideRequest == null) {
                result = 4;
            } else if (rideRequest.getRequestedby() != request.getUserid() || rideRequest.getStatus() == 3) {
                result = 3;
            } else if (rideRequest.getStatus() == 2) {
                result = 5;
            } else {
                RideRequest updatedRideRequest = new RideRequest(request.getUserid(), request.getPickuplocation(), request.getDestination(),
                        request.getDatentime(), request.getPassengers(), request.getLuggage(), request.getSmoking(), request.getFoodndrink(),
                        request.getPets(), request.getAc(), request.getTravelingtime(), request.getPrice());
                result = DatabaseCommunicator.updateRideRequest(request.getRequestid(), updatedRideRequest);
                if (result == 0) {
                    ArrayList<User> users = DatabaseCommunicator.selectUserAcceptedRequest(request.getRequestid());
                    String msg = "Your accepted Ride Request from " + rideRequest.getPickuplocation() +
                            " to " + rideRequest.getDestination() + " on " + rideRequest.getDatentime() +
                            " is updated by the requested passengers. Please go to our website to check for details.";
                    for (User u : users) {
                        PointCalculator.updatePoints(u.getUserid(),request.getPrice(),rideRequest.getPrice());
                        SendEmail.sendEmail(u.getEmail(), "Accepted Ride Request Updated", msg);
                    }
                    PointCalculator.updatePoints(request.getUserid(),request.getPrice(),rideRequest.getPrice());
                }
            }
        }
        RideUpdateRequestResponse res = new RideUpdateRequestResponse(result);
        return res;
    }

    public DtoRideRequest toDtoRideRequest() {
        DtoRideRequest drr = new DtoRideRequest();
        drr.setRequestid(requestid);
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

    public static RideRequestSearchResponse search (RideRequestSearchRequest request) throws InterruptedException, ApiException, IOException {
        RideRequestSearchResponse response = new RideRequestSearchResponse();
        GoogleMapAPI gma = new GoogleMapAPI();

        if (request.getPickupproximity() < 0 || request.getDestinationproximity() < 0 || request.getDatentime().before(new Date())||
                request.getDatentimerange() < 0 || request.getSeats() < 1 || request.getLuggage() < 0) {
            response.setResult(98);
            return response;
        }

        ArrayList<DtoRideRequest> rides = new ArrayList<>();
        ArrayList<RideRequest> rs = DatabaseCommunicator.rideRequestFromTo(gma.getCity(request.getPickuplocation()),
                gma.getCity(request.getDestination()), request.getDatentime(), new Date(request.getDatentime().getTime() + 1000 * 60 * request.getDatentimerange()),
                request.getSeats(), request.getLuggage(),
                request.isSmoking(), request.isFoodndrink(),
                request.isPets(), request.isAc());
        if (rs == null) {
            response.setResult(9);
            return response;
        }
        for (RideRequest r : rs) {
            if (gma.estimate(r.getPickuplocation(), request.getPickuplocation()) <= request.getPickupproximity() &&
                    gma.estimate(r.getDestination(), request.getDestination()) <= request.getDestinationproximity()) {
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

    public RideRequestPickupResponse getRequestPickupCode(RideRequestPickupRequest request){
        int result = 0;
        int code = 0;
        Date today = new Date();
        RideRequest rideRequest = DatabaseCommunicator.selectRideRequest(request.getRequestid());
        if (rideRequest == null) {
            result = 3;
        } else if (rideRequest.getRequestedby() != request.getUserid() || rideRequest.getStatus() != 1 ||
                Math.abs(today.getTime()-rideRequest.getDatentime().getTime())/1000 > 1800) {
            result = 4;
        } else {
            Random rand = new Random();
            code = rand.nextInt(899999) + 100000;
            result = DatabaseCommunicator.addRequestPickup(request.getRequestid(), code);
        }
        RideRequestPickupResponse res = new RideRequestPickupResponse(result, code);
        return res;
    }

    public RideRequestConfirmResponse confirmRideRequestPickup(RideRequestConfirmRequest request){
        int result = 0;
        Date today = new Date();
        AcceptedRequest acceptedRequest = DatabaseCommunicator.selectAcceptedRequest(request.getRequestid());
        if (acceptedRequest == null) {
            result = 3;
        } else if (acceptedRequest.getRequestedby() != request.getUserid() || acceptedRequest.getStatus() != 1 ||
                acceptedRequest.getRequestusercode() == 0 || acceptedRequest.getAcceptedusercode() == 0 ||
                Math.abs(today.getTime()-acceptedRequest.getDatentime().getTime())/1000 > 1800) {
            result = 4;
        } else if (acceptedRequest.getRequestuserstatus() == 1){
            result = 5;
        } else if (acceptedRequest.getAcceptedusercode() != request.getCode()){
            result = 6;
        } else {
            result = DatabaseCommunicator.updateRequestUserStatus(request.getRequestid(), 1);
            if (result == 0){
                //Make payment
                result = PointCalculator.makePayment(request.getUserid(), acceptedRequest.getUserid(), acceptedRequest.getPrice(), "Make payment for ride request");
                DatabaseCommunicator.updateRequestStatus(request.getRequestid(), 3);
            }
        }
        RideRequestConfirmResponse res = new RideRequestConfirmResponse(result);
        return res;
    }
}
