
import java.io.IOException;
import java.util.*;
import java.util.Date;

import DTO.*;
import com.google.maps.errors.ApiException;

/**
 * CS 40800 - Project: Boileride
 * A web application for ride sharing
 *
 * RideOffer class handles data verification and actions related to posting, canceling and updating ride offer
 *
 * @version September 16, 2018
 */

public class RideOffer {
    private int offeredby;
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

    public RideOffer(){}

    public RideOffer(int offeredby, String pickuplocation, String destination, Date datentime,
                     int seats, int luggage, boolean smoking, boolean foodndrink, boolean pets, boolean ac,
                     int travelingtime, int price) {
        this.offeredby = offeredby;
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
    }

    public RideOffer(int offeredby, String pickuplocation, String destination, Date datentime,
                     int seats, int luggage, boolean smoking, boolean foodndrink, boolean pets, boolean ac,
                     int travelingtime, int price, int seatleft, int luggageleft, int status) {
        this.offeredby = offeredby;
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
    }

    public int getOfferedby() {
        return offeredby;
    }

    public void setOfferedby(int offeredby) {
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

    @Override
    public String toString() {
        return "RideOffer{" +
                "offeredby=" + offeredby +
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

    public int verifySeats(int seats){
        if (seats <= 0) {
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

    public RideViewOfferResponse viewRideOfferfromDB(RideViewOfferRequest request){
        int result = 0;
        ArrayList<DtoRideOffer> offerlist = new ArrayList<DtoRideOffer>();
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        if (userResult > 0) result = userResult;
        else{
            offerlist = DatabaseCommunicator.selectOfferList(request.getUserid());
        }
        RideViewOfferResponse res = new RideViewOfferResponse(result, offerlist);
        return res;
    }

    public RideOfferResponse addRideOfferToDB(RideOfferRequest request){
        int result = 0;
        int offerid = -1;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int seatResult = verifySeats(request.getSeats());
        int luggageResult = verifyLuggage(request.getLuggage());
        int priceResult = isEnoughPoints(user, request.getPrice());
        if (userResult > 0) result = userResult;
        else if (desResult > 0) result = 4;
        else if (dateResult == 1) result = 5;
        else if (dateResult == 2) result = 7;
        else if (seatResult > 0) result = 97;
        else if (luggageResult > 0) result = 97;
        else if (priceResult > 0) result = 6;
        else {
            RideOffer rideOffer = new RideOffer(request.getUserid(), request.getPickuplocation(), request.getDestination(),
                    request.getDatentime(), request.getSeats(), request.getLuggage(), request.getSmoking(), request.getFoodndrink(),
                    request.getPets(), request.getAc(), request.getTravelingtime(), request.getPrice(), request.getSeats(), request.getLuggage(), 0);
            offerid = DatabaseCommunicator.addRideOffer(rideOffer);
        }
        RideOfferResponse res = new RideOfferResponse(result, offerid);
        return res;
    }

    public RideCancelOfferResponse cancelRideOfferInDB(RideCancelOfferRequest request){
        int result = 0;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        if (userResult > 0) result = userResult;
        else {
            RideOffer rideOffer = DatabaseCommunicator.selectRideOffer(request.getOfferid());
            if (rideOffer == null) {
                result = 4;
            } else if (rideOffer.getOfferedby() != request.getUserid()) {
                result = 3;
            } else if (rideOffer.getStatus() == 2) {
                result = 5;
            } else {
//                User[] users = DatabaseCommunicator.selectUserJoinedOffer(offer.getOfferid());
                result = DatabaseCommunicator.cancelRideOffer(request.getOfferid());
//              String msg = "We are sorry to inform you that your joined Ride Offer from " + rideOffer.getPickuplocation() +
//                      " to " + rideOffer.getDestination() + " on " + rideOffer.getDatentime() +
//                      " is cancelled by the offered driver.";
//                for (int i=0; i < users.length; i++){
//                    SendEmail.sendEmail(users[i].getEmail(), "Joined Ride Offer Cancelled", msg);
//                }

            }
        }
        RideCancelOfferResponse res = new RideCancelOfferResponse(result);
        return res;
    }

    public RideUpdateOfferResponse updateRideOfferInDB(RideUpdateOfferRequest request) {
        int result = 0;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int seatResult = verifySeats(request.getSeats());
        int luggageResult = verifyLuggage(request.getLuggage());
        int priceResult = isEnoughPoints(user, request.getPrice());
        if (userResult > 0) result = userResult;
        else if (desResult > 0) result = 7;
        else if (dateResult == 1) result = 8;
        else if (dateResult == 2) result = 10;
        else if (seatResult > 0) result = 97;
        else if (luggageResult > 0) result = 97;
        else if (priceResult > 0) result = 9;
        else {
            RideOffer rideOffer = DatabaseCommunicator.selectRideOffer(request.getOfferid());
            if (rideOffer == null) {
                result = 4;
            } else if (rideOffer.getOfferedby() != request.getUserid()) {
                result = 3;
            } else if (rideOffer.getStatus() == 2) {
                result = 5;
            } else {
                RideOffer updatedRideOffer = new RideOffer(request.getUserid(), request.getPickuplocation(), request.getDestination(),
                        request.getDatentime(), request.getSeats(), request.getLuggage(), request.getSmoking(), request.getFoodndrink(),
                        request.getPets(), request.getAc(), request.getTravelingtime(), request.getPrice());
                result = DatabaseCommunicator.updateRideOffer(request.getOfferid(), updatedRideOffer);
//                User[] users = DatabaseCommunicator.selectUserJoinedOffer(request.getOfferid());
//                String msg = "Your joined Ride Offer from " + rideOffer.getPickuplocation() +
//                      " to " + rideOffer.getDestination() + " on " + rideOffer.getDatentime() +
//                      " is updated by the offered driver. Please go to our website to check for details.";
//                for (int i=0; i < users.length; i++){
//                    SendEmail.sendEmail(users[i].getEmail(), "Joined Ride Offer Updated", msg);
//                }
            }
        }
        RideUpdateOfferResponse res = new RideUpdateOfferResponse(result);

        return res;
    }

    public DtoRideOffer toDtoRideOffer() {
        DtoRideOffer dro = new DtoRideOffer();
        dro.setOfferedby(offeredby);
        dro.setPickuplocation(pickuplocation);
        dro.setDestination(destination);
        dro.setDatentime(datentime);
        dro.setSeats(seats);
        dro.setLuggage(luggage);
        dro.setSmoking(smoking);
        dro.setFoodndrink(foodndrink);
        dro.setPets(pets);
        dro.setAc(ac);
        dro.setTravelingtime(travelingtime);
        dro.setPrice(price);
        dro.setSeatleft(seatleft);
        dro.setLuggageleft(luggageleft);
        dro.setStatus(status);
        return dro;
    }

    public static RideOfferSearchResponse search(RideOfferSearchRequest query) throws InterruptedException, ApiException, IOException {
        RideOfferSearchResponse response = new RideOfferSearchResponse();
        GoogleMapAPI gma = new GoogleMapAPI();
        // todo: add validation on query


        int buffer = 15;
        LinkedList<Trip> trips = new LinkedList<>();
        PriorityQueue<TmpTrip> heap = new PriorityQueue<>((a, b)->b.getEstimation() - a.getEstimation());
        TmpTrip start = new TmpTrip();
        start.setRides(new LinkedList<>());
        RideOffer first = new RideOffer();
        first.setDestination(query.getPickuplocation());
        first.setTravelingtime(0);
        start.getRides().add(first);
        heap.add(start);
        while (!heap.isEmpty()) {
            TmpTrip curr = heap.poll();
            if (gma.getCity(curr.getRides().getLast().getDestination()).equals(gma.getCity(query.getDestination()))) {
                if (curr.getRides().size() - 1 <= query.getNumrides() && query.getDatentime().getTime() + query.getDatentimerange() * 60 * 1000 > curr.getRides().getFirst().getDatentime().getTime()) {
                    curr.getRides().removeFirst();
                    trips.addLast(curr.toTrip());
                }
                continue;
            }
            RideOffer lro = curr.getRides().getLast();
            ArrayList<RideOffer> rs = DatabaseCommunicator.rideOfferFrom(gma.getCity(lro.getDestination()),
                    new Date(query.getDatentime().getTime() + 1000 * 60 * (lro.getTravelingtime() + buffer)),
                    lro.getSeatleft(), lro.getLuggageleft(),
                    lro.isSmoking(), lro.isFoodndrink(),
                    lro.isPets(), lro.isAc());
            if (rs == null) {
                response.setResult(9);
                return response;
            }
            for (RideOffer r : rs) {
                TmpTrip t = new TmpTrip();
                t.setRides(new LinkedList<>());
                for (RideOffer a : curr.getRides()) {
                    t.getRides().add(a);
                }
                if (r.getDatentime().after(new Date(t.getRides().getLast().getDatentime().getTime() + 1000 * 60 * (t.getRides().getLast().getTravelingtime() + buffer)))) {
                    t.getRides().add(r);
                    t.setDuration(curr.getDuration() + r.getTravelingtime());
                    t.setEstimation(t.getDuration() + gma.estimate(r.getDestination(), query.getDestination()));
                    heap.add(t);
                }
            }
        }
        if (trips.size() == 0) {
            response.setResult(9);
            return response;
        } else {
            response.setResult(0);
            response.setTrips(trips);
            return response;
        }
    }

}
