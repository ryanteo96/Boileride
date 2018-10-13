
import java.io.IOException;
import java.util.*;
import java.util.Date;

import DTO.*;
import com.google.gson.Gson;
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
    private int offerid;
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

    public RideOffer(int offerid, int offeredby, String pickuplocation, String destination, Date datentime,
                     int seats, int luggage, boolean smoking, boolean foodndrink, boolean pets, boolean ac,
                     int travelingtime, int price, int seatleft, int luggageleft, int status) {
        this.offerid = offerid;
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
        PointCalculator.chargeFailConfirmationFee(request.getUserid());
        int result = 0;
        int offerid = -1;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int seatResult = verifySeats(request.getSeats());
        int luggageResult = verifyLuggage(request.getLuggage());
        int priceResult = isEnoughPoints(user, request.getPrice());
        int distTimeResult = verifyTimePrice(request.getPickuplocation(), request.getDestination(), request.getPrice(), request.getTravelingtime());
        if (userResult > 0) result = userResult;
        else if (desResult > 0) result = 4;
        else if (dateResult == 1) result = 5;
        else if (seatResult > 0) result = 97;
        else if (luggageResult > 0) result = 97;
        else if (priceResult > 0) result = 6;
        else if (distTimeResult == 1) result = 3;
        else if (distTimeResult == 2) result = 7;
        else if (distTimeResult == 3) result = 8;
        else {
            RideOffer rideOffer = new RideOffer(request.getUserid(), request.getPickuplocation(), request.getDestination(),
                    request.getDatentime(), request.getSeats(), request.getLuggage(), request.getSmoking(), request.getFoodndrink(),
                    request.getPets(), request.getAc(), request.getTravelingtime(), request.getPrice(), request.getSeats(), request.getLuggage(), 0);
            offerid = DatabaseCommunicator.addRideOffer(rideOffer);
            if (offerid > -1){
                result = PointCalculator.reservePoints(request.getUserid(), request.getPrice());
            }
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
                ArrayList<User> users = DatabaseCommunicator.selectUserJoinedOffer(request.getOfferid());
                result = DatabaseCommunicator.cancelRideOffer(request.getOfferid());
                if (result == 0) {
                    String msg = "We are sorry to inform you that your joined Ride Offer from " + rideOffer.getPickuplocation() +
                            " to " + rideOffer.getDestination() + " on " + rideOffer.getDatentime() +
                            " is cancelled by the offered driver.";
                    for (User u : users) {
                        PointCalculator.reservePoints(u.getUserid(),rideOffer.getPrice()*-1);
                        SendEmail.sendEmail(u.getEmail(), "Joined Ride Offer Cancelled", msg);
                    }
                    PointCalculator.chargeCancellationFee(request.getUserid(), rideOffer.getDatentime(), rideOffer.getPrice(), "Cancel ride offer");
                }
            }
        }
        RideCancelOfferResponse res = new RideCancelOfferResponse(result);
        return res;
    }

    public RideUpdateOfferResponse updateRideOfferInDB(RideUpdateOfferRequest request) {
        PointCalculator.chargeFailConfirmationFee(request.getUserid());
        int result = 0;
        User user = DatabaseCommunicator.selectUser(request.getUserid());
        int userResult = verifyUserid(user);
        int desResult = verifyDestination(request.getPickuplocation(), request.getDestination());
        int dateResult = verifyDatentime(request.getDatentime());
        int seatResult = verifySeats(request.getSeats());
        int luggageResult = verifyLuggage(request.getLuggage());
        int priceResult = isEnoughPoints(user, request.getPrice());
        int distTimeResult = verifyTimePrice(request.getPickuplocation(), request.getDestination(), request.getPrice(), request.getTravelingtime());
        if (userResult > 0) result = userResult;
        else if (desResult > 0) result = 7;
        else if (dateResult == 1) result = 8;
        else if (seatResult > 0) result = 97;
        else if (luggageResult > 0) result = 97;
        else if (priceResult > 0) result = 9;
        else if (distTimeResult == 1) result = 6;
        else if (distTimeResult == 2) result = 10;
        else if (distTimeResult == 3) result = 11;
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
                if (result == 0) {
                    ArrayList<User> users = DatabaseCommunicator.selectUserJoinedOffer(request.getOfferid());
                    String msg = "Your joined Ride Offer from " + rideOffer.getPickuplocation() +
                            " to " + rideOffer.getDestination() + " on " + rideOffer.getDatentime() +
                            " is updated by the offered driver. Please go to our website to check for details.";
                    for (User u : users) {
                        PointCalculator.updatePoints(u.getUserid(), request.getPrice(), rideOffer.getPrice());
                        SendEmail.sendEmail(u.getEmail(), "Joined Ride Offer Updated", msg);
                    }
                    PointCalculator.updatePoints(request.getUserid(), request.getPrice(), rideOffer.getPrice());
                }
            }
        }
        RideUpdateOfferResponse res = new RideUpdateOfferResponse(result);

        return res;
    }

    public DtoRideOffer toDtoRideOffer() {
        DtoRideOffer dro = new DtoRideOffer();
        dro.setOfferid(offerid);
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

    public static RideOfferSearchResponse search(RideOfferSearchRequest request) throws InterruptedException, ApiException, IOException {
        RideOfferSearchResponse response = new RideOfferSearchResponse();

        if (request.getPickupproximity() < 0 || request.getDestinationproximity() < 0 ||
                request.getDatentimerange() < 0 || request.getNumrides() < 1 ||
                request.getPassengers() < 1 || request.getLuggage() < 0) {
            response.setResult(1);
            return response;
        }

        GoogleMapAPI gma = new GoogleMapAPI();
        int buffer = 0;
        LinkedList<Trip> trips = new LinkedList<>();
        PriorityQueue<TmpTrip> heap = new PriorityQueue<>((a, b)->b.getEstimation() - a.getEstimation());
        TmpTrip start = new TmpTrip();
        start.setRides(new LinkedList<>());
        RideOffer first = new RideOffer();
        first.setDestination(request.getPickuplocation());
        first.setDatentime(new Date(request.getDatentime().getTime() - 1000 * 60 * buffer));
        first.setTravelingtime(0);
        start.getRides().add(first);
        heap.add(start);
        while (!heap.isEmpty()) {
            TmpTrip curr = heap.poll();
            if (gma.estimate(request.getDestination(), curr.getRides().getLast().getDestination()) <= request.getDestinationproximity()) {
                curr.getRides().removeFirst();
                if (curr.getRides().size() <= request.getNumrides()
                        && request.getDatentime().getTime() + request.getDatentimerange() * 60 * 1000 >= curr.getRides().getFirst().getDatentime().getTime()) {
                    trips.addLast(curr.toTrip());
                }
                continue;
            }
            RideOffer lro = curr.getRides().getLast();
            ArrayList<RideOffer> rs = DatabaseCommunicator.rideOfferFrom(gma.getCity(lro.getDestination()),
                    new Date(lro.getDatentime().getTime() + 1000 * 60 * (lro.getTravelingtime() + buffer)),
                    request.getPassengers(), request.getLuggage(),
                    request.isSmoking(), request.isFoodndrink(),
                    request.isPets(), request.isAc());
            if (rs == null) {
                response.setResult(9);
                return response;
            }
            for (RideOffer r : rs) {
//                System.out.println(new Gson().toJson(r));
                TmpTrip t = new TmpTrip();
                t.setRides(new LinkedList<>());
                for (RideOffer a : curr.getRides()) {
                    t.getRides().add(a);
                }
                t.getRides().add(r);
                t.setDuration(curr.getDuration() + r.getTravelingtime());
                t.setEstimation(t.getDuration() + gma.estimate(r.getDestination(), request.getDestination()));
                heap.add(t);
            }
        }
        if (trips.size() == 0) {
            response.setResult(9);
            return response;
        }
        response.setResult(0);
        response.setTrips(trips);
        return response;
    }

    public RideOfferPickupResponse getOfferPickupCode(RideOfferPickupRequest request){
        int result = 0;
        int code = 0;
        Date today = new Date();
        RideOffer rideOffer = DatabaseCommunicator.selectRideOffer(request.getOfferid());
        if (rideOffer == null) {
            result = 3;
        } else if (rideOffer.getOfferedby() != request.getUserid() || rideOffer.getStatus() != 1
                || Math.abs(today.getTime()-rideOffer.getDatentime().getTime())/1000 > 1800) {
            result = 4;
        } else {
            Random rand = new Random();
            code = rand.nextInt(899999) + 100000;
            result = DatabaseCommunicator.addOfferPickup(request.getOfferid(), code);
        }
        RideOfferPickupResponse res = new RideOfferPickupResponse(result, code);
        return res;
    }

    public RideOfferConfirmResponse confirmRideOfferPickup(RideOfferConfirmRequest request){
        int result = 0;
        Date today = new Date();
        JoinedOffer joinedOffer = DatabaseCommunicator.selectJoinedOffer(request.getJoineduserid(), request.getOfferid());
        if (joinedOffer == null) {
            result = 3;
        } else if (joinedOffer.getOfferedby() != request.getUserid() || joinedOffer.getStatus() != 1
                || joinedOffer.getOfferusercode() == 0 || joinedOffer.getJoinedusercode() == 0
                || Math.abs(today.getTime()-joinedOffer.getDatentime().getTime())/1000 > 1800) {
            result = 4;
        } else if (joinedOffer.getOfferuserstatus() == 1){
            result = 5;
        } else if (joinedOffer.getJoinedusercode() != request.getCode()) {
            result = 6;
        } else {
            result = DatabaseCommunicator.updateOfferUserStatus(request.getUserid(), request.getOfferid(), 1);
            if (result == 0){
                //Get payment
                result = PointCalculator.getPayment(request.getUserid(), joinedOffer.getUserid(), joinedOffer.getPrice(), "Receive payment from ride offer");
                DatabaseCommunicator.updateOfferStatus(request.getOfferid(), joinedOffer.getUserid(),3);
            }
        }
        RideOfferConfirmResponse res = new RideOfferConfirmResponse(result);
        return res;
    }

    public static RideOfferSearchAlterResponse searchAlter(RideOfferSearchAlterRequest request) throws InterruptedException, ApiException, IOException {
        RideOfferSearchAlterResponse response = new RideOfferSearchAlterResponse();


        // validate request
        if (request.getTrip() == null ||
                request.getPickupproximity() < 0 || request.getDestinationproximity() < 0 ||
                request.getDatentimerange() < 0 || request.getNumrides() < 1 ||
                request.getPassengers() < 1 || request.getLuggage() < 0) {
            response.setResult(1);
            return response;
        }

        int a = -1, b = -1;
        for (int i = 0; i < request.getTrip().getRides().size(); i++) {
            int currid = request.getTrip().getRides().get(i).getOfferid();
            if (request.getStartofferid() == currid) {
                a = i;
            }
            if (request.getEndofferid() == currid) {
                b = i;
            }
        }
        if (a == -1 || b == -1 || a > b) {
            response.setResult(1);   // invalid start or end offer id;
            return response;
        }

        String suborigin, subdestination;
        Date subdeparture, subarrival;
        int subnumrides = request.getNumrides() - a - 1 - (request.getTrip().getRides().size() - b - 1);

        if (a > 0) {
            suborigin = request.getTrip().getRides().get(a - 1).getDestination();
            subdeparture = new Date(request.getTrip().getRides().get(a - 1).getDatentime().getTime() +
                    request.getTrip().getRides().get(a - 1).getTravelingtime() * 60 * 1000);
        } else {
            suborigin = request.getOriginalpickuplocation();
            subdeparture = request.getTrip().getRides().get(a).getDatentime();
        }

        if (b < request.getTrip().getRides().size() - 1) {
            subdestination = request.getTrip().getRides().get(b + 1).getPickuplocation();
            subarrival = request.getTrip().getRides().get(b + 1).getDatentime();
        } else {
            subdestination = request.getOriginaldestination();
            subarrival = new Date(Long.MAX_VALUE);
        }

        GoogleMapAPI gma = new GoogleMapAPI();

        int buffer = 0;
        LinkedList<Trip> trips = new LinkedList<>();
        PriorityQueue<TmpTrip> heap = new PriorityQueue<>((t1, t2)->t2.getEstimation() - t1.getEstimation());
        TmpTrip start = new TmpTrip();
        start.setRides(new LinkedList<>());
        RideOffer first = new RideOffer();
        first.setDestination(suborigin);
        first.setDatentime(new Date(subdeparture.getTime() - 1000 * 60 * buffer));
        first.setTravelingtime(0);
        start.getRides().add(first);
        heap.add(start);
        while (!heap.isEmpty()) {
            TmpTrip curr = heap.poll();
            // check if sub destination is arrived and within proximity
            if ((b == request.getTrip().getRides().size() - 1
                    && gma.estimate(curr.getRides().getLast().getDestination(), subdestination) <= request.getDestinationproximity())
                    || (b < request.getTrip().getRides().size() - 1 && gma.getCity(curr.getRides().getLast().getDestination()).equals(gma.getCity(subdestination)))) {
                curr.getRides().removeFirst();
                // if the first ride of the original trip is also changed and if pick up location is within proximity
                if ((a == 0 && gma.estimate(curr.getRides().getFirst().getPickuplocation(), suborigin) <= request.getPickupproximity()
                        && request.getOriginaldatentime().getTime() + request.getDatentimerange() > curr.getRides().getFirst().getDatentime().getTime())
                        || (a > 0)) {
                    // check if number of rides exceeds
                    if (curr.getRides().size() <= subnumrides) {
                        trips.addLast(curr.toTrip());
                    }
                }
                continue;
            }

            RideOffer lro = curr.getRides().getLast();
            ArrayList<RideOffer> rs = DatabaseCommunicator.rideOfferFrom(gma.getCity(lro.getDestination()),
                    new Date(lro.getDatentime().getTime() + 1000 * 60 * (lro.getTravelingtime() + buffer)),
                    request.getPassengers(), request.getLuggage(),
                    request.isSmoking(), request.isFoodndrink(),
                    request.isPets(), request.isAc());
            if (rs == null) {
                continue;
            }
            for (RideOffer r : rs) {
//                System.out.println(new Gson().toJson(r));
                TmpTrip t = new TmpTrip();
                t.setRides(new LinkedList<>());
                for (RideOffer tro : curr.getRides()) {
                    t.getRides().add(tro);
                }
                t.getRides().add(r);
                t.setDuration(curr.getDuration() + r.getTravelingtime());
                t.setEstimation(t.getDuration() + gma.estimate(r.getDestination(), subdestination));
                heap.add(t);
            }
        }

        if (trips.size() == 0) {
            // should not reach here, or critical error
            response.setResult(123);
            return response;
        }
        for (Trip tt : trips) {
            int aa = a - 1, bb =b + 1, duration = tt.getDuration();
            while (aa >= 0) {
                tt.getRides().addFirst(request.getTrip().getRides().get(aa));
                duration += request.getTrip().getRides().get(aa).getTravelingtime();
                aa--;
            }

            while (bb <= request.getTrip().getRides().size() - 1) {
                tt.getRides().addLast(request.getTrip().getRides().get(bb));
                duration += request.getTrip().getRides().get(bb).getTravelingtime();
                bb++;
            }
        }

        response.setResult(0);
        response.setTrips(trips);
        return response;
    }

}
